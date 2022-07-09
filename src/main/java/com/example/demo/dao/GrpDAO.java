package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.mapper.GrpMapper;
import com.example.demo.vo.DataVO;
import com.example.demo.vo.GrpVO;

@Repository
public class GrpDAO {
	
	@Autowired
	private GrpMapper gmapper;
	
	public List<GrpVO> getList() {
		return gmapper.getList();
	}

	public List<DataVO> location(DataVO data) {
		return gmapper.location(data);
	}
	
	public List<DataVO> seoul(DataVO data) {
		return gmapper.seoul(data);
	}
	
	public List<DataVO> busan(DataVO data) {
		return gmapper.busan(data);
	}
	
	public List<DataVO> daegu(DataVO data) {
		return gmapper.daegu(data);
	}
	
	public List<DataVO> incheon(DataVO data) {
		return gmapper.incheon(data);
	}
	
	public List<DataVO> gwangju(DataVO data) {
		return gmapper.gwangju(data);
	}
	
	public List<DataVO> daejeon(DataVO data) {
		return gmapper.daejeon(data);
	}
	
	public List<DataVO> ulsan(DataVO data) {
		return gmapper.ulsan(data);
	}

}
