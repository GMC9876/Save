package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dao.UserDAO;
import com.example.demo.service.GrpSVC;

import com.example.demo.service.MainSVC;
import com.example.demo.service.NaverSVC;
import com.example.demo.service.UserSVC;
import com.example.demo.vo.CalendarVO;

import com.example.demo.vo.UserVO;


@SessionAttributes("uid")
@Controller
public class MainController {
	
	@Autowired
	private NaverSVC naverSVC;
	
	@Autowired
	private MainSVC mSvc;
	
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private UserSVC svc;
	
	@Autowired
	private GrpSVC gsvc;

	
	@GetMapping("/")
	public String main() {
		return "front";
	}

	@GetMapping("/front")
	public String front(Model model) {
		
		return "front";
		
	}
	
	@GetMapping("/index")
	public String indexPage(Model model) {
		if(svc.isLogin()) {
		    model.addAttribute("list",gsvc.getList()); 
			return "index";
		}else {
			model.addAttribute("msg","로그인 후에 사용해주세요");
			return "redirect:/login";
		}
	}

	@GetMapping("/charts")
	public String charts(Model model) {
		if(svc.isLogin()) {
			return "charts";
		}else {
			model.addAttribute("msg","로그인 후에 사용해주세요");
			return "redirect:/login";
		}
	}
	
	@GetMapping("/charts2")
	public String charts2(Model model) {
		if(svc.isLogin()) {
			return "charts2";
		}else {
			model.addAttribute("msg","로그인 후에 사용해주세요");
			return "redirect:/login";
		}
	}
	
	@GetMapping("/password")
	public String password(Model model) {
		if(svc.isLogin()) {
			return "password";
		}else {
			model.addAttribute("msg","로그인 후에 사용해주세요");
			return "redirect:/login";
		}
	}
	
	@GetMapping("/update")
	public String updatePage(Model model) {
		String uid = (String) model.getAttribute("uid");
		if(svc.isLogin()) {
			UserVO user = dao.checkById(uid);
			model.addAttribute("user",user);
			return "password";
		}else {
			model.addAttribute("msg","로그인 후에 사용해주세요");
			return "redirect:/login";
		}
	}

	@GetMapping("/login")
	public String loginPage(Model model, HttpSession session) {
		if(svc.isLogin()) {
			return "front";
		}else {
			String naverAuthUrl = naverSVC.getAuthorizationUrl(session);
			System.out.println("네이버:" + naverAuthUrl);
			model.addAttribute("url", naverAuthUrl);
			return "login";
		}
	}

	
	@GetMapping("/adduser")
	public String addUser() {
		return "register";
	}
	
	@GetMapping("/calendar")
	public String calendar2(HttpServletRequest request,Model model) {
		if(svc.isLogin()) {
			List<CalendarVO> list =mSvc.getCalendar();
			request.setAttribute("calendarList", list);
			return "calendar";
		}else {
			model.addAttribute("msg","로그인 후에 사용해주세요");
			return "redirect:/login";
		}
	}
		
	@GetMapping("/findpw")
	public String findpw() {
		return "findpw";
	}
	

}
