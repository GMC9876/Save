package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.CalendarVO;

@Mapper
public interface MainXMLMapper {
	
	@Select("SELECT * FROM calendar")
	List<CalendarVO> selectAllCalendar();
}
