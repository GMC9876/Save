package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.DataVO;
import com.example.demo.vo.GrpVO;

@Mapper
public interface GrpMapper {
	
	@Select("SELECT datetime AS datetime, hour AS hour, 공급능력 AS supply, 현재수요 AS demand, 공급예비력 AS supplyrp, 운영예비력 AS operationrp FROM korea")
	List<GrpVO> getList();
	
	@Select("SELECT * FROM ${location} where dt=#{date}")
	List<DataVO> location(DataVO data);
	
	@Select("SELECT * FROM 서울 where dt=#{date} AND hour=12")
	List<DataVO> seoul(DataVO data);
	
	@Select("SELECT * FROM 부산 where dt=#{date} AND hour=12")
	List<DataVO> busan(DataVO data);
	
	@Select("SELECT * FROM 인천 where dt=#{date} AND hour=12")
	List<DataVO> incheon(DataVO data);
	
	@Select("SELECT * FROM 대구 where dt=#{date} AND hour=12")
	List<DataVO> daegu(DataVO data);
	
	@Select("SELECT * FROM 광주 where dt=#{date} AND hour=12")
	List<DataVO> gwangju(DataVO data);
	
	@Select("SELECT * FROM 대전 where dt=#{date} AND hour=12")
	List<DataVO> daejeon(DataVO data);
	
	@Select("SELECT * FROM 울산 where dt=#{date} AND hour=12")
	List<DataVO> ulsan(DataVO data);
}
