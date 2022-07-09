package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.GrpDAO;
import com.example.demo.vo.DataVO;
import com.example.demo.vo.GrpVO;

@Service
public class GrpSVC {
	
	@Autowired
	private GrpDAO dao;
	
	public List<GrpVO> getList() {
		return dao.getList();
	}

}
