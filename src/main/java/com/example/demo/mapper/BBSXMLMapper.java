package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.BBSVO;
import com.example.demo.vo.ReplyVO;

@Mapper
public interface BBSXMLMapper {
	
	@Insert("INSERT INTO bbs VALUES(NULL,#{uid},#{email},#{title},#{content},#{date},0,0,0,#{changeYn},#{deleteYn},#{category})")
	int insertAdd(BBSVO vo);
	
	@Select("SELECT * FROM bbs  WHERE deleteYn='N' ORDER BY num DESC")
	List<BBSVO> getAllList();
	
	@Select("SELECT * FROM bbs WHERE deleteYn='N' AND category=#{ctgr} ORDER BY num DESC")
	List<BBSVO> getList(String ctgr);
	
	@Select("SELECT * FROM bbs WHERE num=#{num}")
	BBSVO detail(int num);
	
	@Update("UPDATE bbs SET title=#{title}, content=#{content} WHERE num=#{num}")
	int edit(BBSVO vo);
	
	@Update("UPDATE bbs SET deleteYn='Y' WHERE num=#{num}")
	int delete(int num);
	
	@Select("SELECT * FROM bbs_comment WHERE bno=#{num} ORDER BY IF(ISNULL(grp), rno, grp*1), grps;")
	List<ReplyVO> selectReply(int num);
	
	@Update("UPDATE bbs SET cnt = cnt + 1 WHERE num = #{num}")
	int updateCnt(int num);
	
	@Select("SELECT * FROM bbs WHERE deleteYn='N' AND uid=#{uid} ORDER BY num DESC")
	List<BBSVO> getMyList(String uid);
	
	@Select("SELECT c.rno, c.bno, c.grp, c.grps, c.uid, c.cmt, c.repDate FROM bbs_comment c LEFT JOIN bbs ON (c.bno = bbs.num) WHERE c.uid=#{uid} AND c.cmt!=\"\" AND c.grp is null AND bbs.deleteYn = 'N' ORDER BY c.rno DESC")
	List<ReplyVO> getMyCmtList(String uid);
	
	@Select("SELECT DISTINCT b.num, b.uid, b.title, b.content, b.date, b.cnt, b.reply, b.liked FROM bbs b LEFT JOIN bbs_comment c ON (b.num = c.bno) WHERE c.uid=#{uid} AND c.grp is not null AND b.deleteYn = 'N' ORDER BY b.num DESC")
	List<BBSVO> getMyReplyList(String uid);
	
	@Select("SELECT b.num, b.uid, b.title, b.content, b.date, b.cnt, b.reply, b.liked FROM bbs b LEFT JOIN bbs_like l ON (b.num = l.bno) WHERE l.like_uid=#{uid} AND b.deleteYn = 'N' ORDER BY b.num DESC")
	List<BBSVO> getMyLikeList(String uid);
	
	@Select("SELECT * FROM bbs WHERE deleteYn='Y'AND uid=#{uid} ORDER BY num DESC")
	List<BBSVO> getMyDelList(String uid);
}
