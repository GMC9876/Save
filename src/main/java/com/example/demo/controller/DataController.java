package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dao.GrpDAO;
import com.example.demo.service.GrpSVC;
import com.example.demo.vo.DataVO;
import com.example.demo.vo.UserVO;

@SessionAttributes("uid")
@Controller
public class DataController {
	
	@Autowired
	private GrpDAO dao;
	
	@RequestMapping("/location")
	@ResponseBody
	public Map<String, Object> location(Model model, DataVO data) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seoul",dao.seoul(data));
		map.put("busan",dao.busan(data));
		map.put("daegu",dao.daegu(data));
		map.put("incheon",dao.incheon(data));
		map.put("gwangju",dao.gwangju(data));
		map.put("daejeon",dao.daejeon(data));
		map.put("ulsan",dao.ulsan(data));
		map.put("data",dao.location(data));
		
		return map;
	}
}
