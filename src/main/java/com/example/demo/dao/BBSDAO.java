package com.example.demo.dao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.mapper.BBSXMLMapper;
import com.example.demo.mapper.FileXMLMapper;
import com.example.demo.util.FileUtils;
import com.example.demo.vo.BBSVO;
import com.example.demo.vo.FileVO;
import com.example.demo.vo.ReplyVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Repository
public class BBSDAO {
	
	private HttpSession session;

	@Autowired
	private BBSXMLMapper m;
	
	@Autowired
	public BBSDAO(HttpSession session) {
		this.session = session;
	}
	
	@Autowired
	private FileUtils fileUtils;
	
	@Autowired
	private FileXMLMapper fileMapper;

	
	public boolean add(BBSVO vo, MultipartFile[] files) {
		int queryResult = 0;
		
		if (vo.getNum() == 0) {
			int bno = fileMapper.readLastBno();
			vo.setNum(bno+1);
		}
		
		List<FileVO> fileList = fileUtils.uploadFiles(files, vo.getNum());
		if (CollectionUtils.isEmpty(fileList) == false) {
			for(int i=0; i<fileList.size(); i++) { 						// 오라클 클라우드에서 mybatis가 오류가 발생하여
				queryResult = fileMapper.insertAttach(fileList.get(i));	// <foreach>사용이 어려워서 하나씩 insert 함
			}
			
			if (queryResult < 1) {
				queryResult = 0;
			}
		}

		return (queryResult > 0);
	}
	
	public int add(BBSVO vo) {		
		
		String uid = (String)session.getAttribute("uid");
        String email = (String)session.getAttribute("email");
	      
	       LocalTime seoulNow = LocalTime.now(ZoneId.of("Asia/Seoul"));
	       LocalDate dateNow = LocalDate.now(ZoneId.of("Asia/Seoul"));
	       System.out.println(dateNow);
	       int seoulHour = seoulNow.getHour();
	       int seoulminute = seoulNow.getMinute();

	       String date = dateNow.toString() +" "+ seoulHour +":" +seoulminute;
	       
	      vo.setUid(uid);
	      vo.setEmail(email);
	      vo.setDate(date);
	      
	      return m.insertAdd(vo);
	}
	
	public List<ReplyVO> reply(int num) {
		return m.selectReply(num);
	}

	public PageInfo<BBSVO> getList(int pageNum, int pageSize, String ctgr) {
		PageHelper.startPage(pageNum, pageSize);
		
		PageInfo<BBSVO> pageInfo = new PageInfo<>();
		if(ctgr.equals("all")) {
		    pageInfo = new PageInfo<>(m.getAllList());
		} else if(ctgr.equals("mypage")) {
			String uid = (String) session.getAttribute("uid"); 
			pageInfo = new PageInfo<>(m.getMyList(uid));
		}
			else {
			pageInfo = new PageInfo<>(m.getList(ctgr));
		}
	    return pageInfo;
	}

	public int incrementCnt(int num) {
		return m.updateCnt(num);
	}
	
	public BBSVO detail(int num) {
		return m.detail(num);
	}

	public int edit(BBSVO vo) {
		
		// 파일이 추가, 삭제, 변경된 경우
		if("Y".equals(vo.getChangeYn())) {
			fileMapper.deleteAttach(vo.getNum());
			
			// fileIdxs에 포함된 num을 가지는 파일의 삭제여부를 'N'으로 업데이트
			if (CollectionUtils.isEmpty(vo.getFileIdxs()) == false) {
				// 오라클 클라우드에서 mybatis가 오류가 발생하여  <foreach>사용이 어려워서 하나씩 insert 함
				for(int i=0; i<vo.getFileIdxs().size(); i++) {
					fileMapper.undeleteAttach(vo.getFileIdxs().get(i));
				}
			}
		}
		
		return m.edit(vo);
	}

	public int delete(int num) {
		return m.delete(num);
	}

	public List<FileVO> getFileList(int num) {
		int fileTotalCount = fileMapper.selectAttachTotalCount(num);
		if (fileTotalCount < 1) {
			return Collections.emptyList();
		}
		return fileMapper.selectAttachList(num);
	}

	public FileVO getAttachDetail(Long idx) {
		return fileMapper.selectAttachDetail(idx);
	}
	
	public PageInfo<ReplyVO> getCmtList(int pageNum, int pageSize, String uid) {
		PageHelper.startPage(pageNum, pageSize);

		PageInfo<ReplyVO> pageInfo = new PageInfo<>();

		pageInfo = new PageInfo<>(m.getMyCmtList(uid));

		return pageInfo;
	}

	public List<BBSVO> listTitle(List<Integer> bno) {
		List<BBSVO> bbs = new ArrayList<>();
		for(int i=0; i<bno.size(); i++) {
			bbs.add(m.detail(bno.get(i)));
		}
		return bbs;
	}

	public PageInfo<BBSVO> getReplyList(int pageNum, int pageSize, String uid) {
		PageHelper.startPage(pageNum, pageSize);

		PageInfo<BBSVO> pageInfo = new PageInfo<>();

		pageInfo = new PageInfo<>(m.getMyReplyList(uid));

		return pageInfo;
	}

	public PageInfo<BBSVO> getMyLikeList(int pageNum, int pageSize, String uid) {
		PageHelper.startPage(pageNum, pageSize);

		PageInfo<BBSVO> pageInfo = new PageInfo<>();

		pageInfo = new PageInfo<>(m.getMyLikeList(uid));

		return pageInfo;
	}

	public PageInfo<BBSVO> getMyDelList(int pageNum, int pageSize, String uid) {
		PageHelper.startPage(pageNum, pageSize);

		PageInfo<BBSVO> pageInfo = new PageInfo<>();

		pageInfo = new PageInfo<>(m.getMyDelList(uid));

		return pageInfo;
	}


}