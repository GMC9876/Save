package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.LikeVO;

@Mapper
public interface LikeXMLMapper {
	
	@Update("UPDATE bbs SET liked = liked + 1 WHERE num=#{bno}")
	int updateAddLike(LikeVO vo);
	
	@Update("UPDATE bbs SET liked = liked - 1 WHERE num=#{bno}")
	int updateDelLike(LikeVO vo);
	
	@Select("SELECT like_uid FROM bbs_like WHERE bno=#{bno} AND like_uid=#{like_uid}")
	String findLikeUid(LikeVO vo);
	
	@Insert("INSERT INTO bbs_like VALUES(NULL,#{bno},#{like_uid})")
	int insertLikeUid(LikeVO vo);
	
	@Delete("DELETE FROM bbs_like WHERE bno=#{bno} AND like_uid=#{like_uid}")
	int deleteLikeUid(LikeVO vo);
}
