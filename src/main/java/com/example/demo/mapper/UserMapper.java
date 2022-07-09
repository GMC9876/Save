package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.KakaoVO;
import com.example.demo.vo.NaverVO;
import com.example.demo.vo.UserVO;

@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM user WHERE uid=#{uid}")
	UserVO selectLogin(UserVO vo);
	
	@Insert("INSERT INTO user VALUES(NULL,#{uid},#{pwd},#{phone},#{address},#{name},#{email})")
	int insertUser(UserVO user);
	
	@Select("SELECT * FROM user WHERE uid=#{uid} AND pwd=#{pwd}")
	UserVO getUser(UserVO user);
	
	@Select("SELECT * FROM user WHERE uid=#{uid}")
	UserVO checkById(String uid);
	
	@Update("UPDATE user SET pwd=#{pwd} WHERE uid=#{uid}")
	int updateUser(UserVO user);
	
	@Update("UPDATE user SET pwd=#{pwd} WHERE uid=#{uid} AND email=#{email}")
	int lostPw(UserVO user);
	
	@Insert("INSERT INTO kakaouser VALUES(NULL,#{uid},#{email},#{kakaoid},NULL)")
	int kakaoUser(KakaoVO kakao);
	
	@Select("SELECT * FROM kakaouser WHERE uid=#{uid}")
	KakaoVO checkByKakaoId(KakaoVO vo);
	
	@Insert("INSERT INTO naveruser VALUES(NULL,#{uid},#{email},#{naverid},NULL)")
	int naverUser(NaverVO naver);
	
	@Select("SELECT * FROM naveruser WHERE uid=#{uid}")
	NaverVO checkByNaverId(NaverVO vo);
}
