package com.example.demo.controller;

import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.service.BBSSVC;
import com.example.demo.service.UserSVC;
import com.example.demo.vo.BBSVO;
import com.example.demo.vo.FileVO;
import com.example.demo.vo.ReplyVO;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;

@Controller
public class BBSController {

	@Autowired
	HttpSession session;
	
	@Autowired
	private UserSVC login;
	
	@Autowired
	private BBSSVC svc;
	
	@GetMapping("/ppt")
	public String ppt() {
		return "ppt";
	}

	@GetMapping("/mypage/{page}/{pgNum}")
	public String mypage(@PathVariable("page")String page, @PathVariable("pgNum")int pg, Model m) {

		if(login.isLogin()) {
			String uid = (String) session.getAttribute("uid");

			if(page.equals("bbs_list")) {
				String ctgr = "mypage";
				PageInfo<BBSVO> pgInfo = svc.getList(pg, 5, ctgr);
				m.addAttribute("pageInfo", pgInfo);
				return "mypage_bbs_list";

			} else if(page.equals("bbs_cmt")) {
				PageInfo<ReplyVO> pgInfo = svc.getCmtList(pg, 5, uid);
				List<Integer> bno = new ArrayList<>();
				for(int i=0; i<pgInfo.getList().size(); i++) {
					bno.add(pgInfo.getList().get(i).getBno());
				}
				m.addAttribute("pageInfo", pgInfo);
				m.addAttribute("bbs", svc.listTitle(bno));
				return "mypage_bbs_cmt";

			} else if(page.equals("bbs_reply")) {
				PageInfo<BBSVO> pgInfo = svc.getReplyList(pg, 5, uid);
				m.addAttribute("pageInfo", pgInfo);
				return "mypage_bbs_reply";

			} else if(page.equals("bbs_like")) {
				PageInfo<BBSVO> pgInfo = svc.getMyLikeList(pg, 5, uid);
				m.addAttribute("pageInfo", pgInfo);
				return "mypage_bbs_like";

			} else if(page.equals("bbs_delete")) {
				PageInfo<BBSVO> pgInfo = svc.getMyDelList(pg, 5, uid);
				m.addAttribute("pageInfo", pgInfo);
				return "mypage_bbs_delete";
			}


		} else {
			return "redirect:/login";
		}
		return null;
	}
	
	@PostMapping("/bbs_delete/{num}")
	@ResponseBody
	public String delete(@PathVariable("num")int num) {
		return String.format("{\"deleted\":%b}", svc.delete(num));
	}
	
	@PostMapping("/bbs_edit")
	@ResponseBody
	public RedirectView edited(BBSVO vo, @RequestParam String ctgr, @RequestParam MultipartFile[] files) {
		svc.edit(vo);
		if (files != null) {
			svc.add(vo, files);
		}
		return new RedirectView("/bbs_detail/"+vo.getNum()+"/"+ctgr);
	}
	
	@GetMapping("/bbs_edit/{num}/{ctgr}")
	public String edit(@PathVariable("num")int num, Model m, @PathVariable("ctgr")String ctgr) {
		if(login.isLogin()) {
			m.addAttribute("edit", svc.detail(num));
			m.addAttribute("category", ctgr);
			List<FileVO> fileList = svc.getFileList(num);
			m.addAttribute("fileList", fileList);
			return "bbs_edit";
		} else {
			return "redirect:/login";
		}
	}
	
	@PostMapping(value="/uploadSummernoteImageFile", produces = "application/json")
	@ResponseBody
	public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
		
		JsonObject jsonObject = new JsonObject();
		
		String fileRoot = "/opt/tomcat9/webapps/ROOT/WEB-INF/classes/static/summernote2/";	//????????? ?????? ?????? ??????
		String originalFileName = multipartFile.getOriginalFilename();	//???????????? ?????????
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//?????? ?????????
				
		String savedFileName = UUID.randomUUID() + extension;	//????????? ?????? ???
		
		File targetFile = new File(fileRoot + savedFileName);	
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//?????? ??????
			jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
			jsonObject.addProperty("responseCode", "success");
				
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//????????? ?????? ??????
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	@GetMapping("/bbs_download/{idx}")
	public void downloadAttachFile(@PathVariable("idx")long idx, Model model, HttpServletResponse response) {

		FileVO fileInfo = svc.getAttachDetail(idx);
		if (fileInfo == null || "Y".equals(fileInfo.getDeleteYn())) {
			throw new RuntimeException("?????? ????????? ?????? ??? ????????????.");
		}

		//String uploadDate = fileInfo.getInsertTime().format(DateTimeFormatter.ofPattern("yyMMdd"));
		//String uploadPath = Paths.get("/opt", "tomcat9", "webapps", "ROOT", "WEB-INF", "classes",
		//           "static", "upload").toString();
		String uploadPath1 = "/opt/tomcat9/webapps/ROOT/WEB-INF/classes/static/upload/";
		String filename = fileInfo.getOriginalName();
		File file = new File(uploadPath1, fileInfo.getSaveName());
		System.out.println("file = " + file.getPath());
		try {
			byte[] data = FileUtils.readFileToByteArray(file);
			System.out.println("data = "+data.toString());
			response.setContentType("application/octet-stream");
			response.setContentLength(data.length);
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");
			System.out.println("resonse = "+response.toString());
			response.getOutputStream().write(data);
			response.getOutputStream().flush();
			response.getOutputStream().close();

		} catch (IOException e) {
			System.out.println("e:"+e);
			System.out.println("e getMessage:"+e.getMessage());
			System.out.println("e toString:"+e.toString());
			System.out.println("e getStackTrace:"+e.getStackTrace());
			throw new RuntimeException("?????? ??????????????? ?????????????????????.");

		} catch (Exception e) {
			throw new RuntimeException("???????????? ????????? ?????????????????????.");
		}
	}
	
	@GetMapping("/bbs_detail/{num}/{ctgr}")
	public String detail(@PathVariable("num")int num, Model m, @PathVariable("ctgr")String ctgr,
			final HttpServletRequest request, final HttpServletResponse response) {
		
		// ????????? ?????? ????????????
	    Cookie cookies[] = request.getCookies();
	    Map mapCookie = new HashMap();
	    if(request.getCookies() != null){
	    	for (int i = 0; i < cookies.length; i++) {
		        Cookie obj = cookies[i];
		        mapCookie.put(obj.getName(),obj.getValue());
	      	}
	    }

	    // ????????? ???????????? read_count ??? ????????????
	    String cookie_read_count = (String) mapCookie.get("read_count");
	    // ????????? ????????? ????????? ??????
	    String new_cookie_read_count = "|" + num;

	    // ????????? ????????? ????????? ???????????? ???????????? ??? ??????
	    if ( StringUtils.indexOfIgnoreCase(cookie_read_count, new_cookie_read_count) == -1 ) {
	    	// ?????? ?????? ?????? ??????
	        Cookie cookie = new Cookie("read_count", cookie_read_count + new_cookie_read_count);
	        //cookie.setMaxAge(1000); // ?????????
	        response.addCookie(cookie);

	        // ????????? ????????????
	        svc.incrementCnt(num);
	    }
		
		m.addAttribute("detail", svc.detail(num));
		m.addAttribute("category", ctgr);
		String uid = (String)session.getAttribute("uid");
    	m.addAttribute("uid", uid);
		m.addAttribute("reply", svc.reply(num));
		
		List<FileVO> fileList = svc.getFileList(num);
		m.addAttribute("fileList", fileList);
		return "bbs_detail";
	}
	
	@PostMapping("/bbs_add")
	@ResponseBody
	public RedirectView add(BBSVO vo, @RequestParam MultipartFile[] files) {
		if (files != null) {
			svc.add(vo, files);
		}
		svc.add(vo);
		
		return new RedirectView("/bbs_list/1/all");
	}
	
	@GetMapping("/bbs_add")
	public String addWrite(Model m) {
		if(login.isLogin()) {
			return "bbs_add";
		} else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/bbs_list/{pgNum}/{ctgr}")
	public String GetList(@PathVariable("pgNum")int pg, Model m, @PathVariable("ctgr")String ctgr) {
		PageInfo<BBSVO> pgInfo = svc.getList(pg, 8, ctgr);
		m.addAttribute("category", ctgr);
		m.addAttribute("pageInfo", pgInfo);
		
	    return "bbs_list";
	}
}