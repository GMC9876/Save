package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.ReplyVO;

@Mapper
public interface ReplyXMLMapper {
	
	@Insert("INSERT INTO bbs_comment VALUES(#{rno},#{bno},#{grp},#{grps},#{uid},#{cmt},#{repDate})")
	int insertAdd(ReplyVO vo);
	
	@Delete("DELETE FROM bbs_comment WHERE rno=#{rno}")
	int deleteReply(ReplyVO vo);
	
	@Update("UPDATE bbs SET reply = reply + 1 WHERE num = #{bno}")
	int updateReply(int bno);
	
	@Select("SELECT MAX(grps) FROM bbs_comment WHERE rno=#{grp} or grp=#{grp}")
	int selectMaxgrps(int grp);
	
	@Select("SELECT count(rno) FROM bbs_comment WHERE grp=#{rno}")
	int countReply(int rno);
	
	@Update("UPDATE bbs_comment SET cmt='' WHERE rno=#{rno}")
	int comentDelete(ReplyVO vo);
	
	@Update("UPDATE bbs SET reply = reply - 1 WHERE num = #{bno}")
	int updateCountReply(ReplyVO vo);
	
	@Delete("DELETE FROM bbs_comment WHERE cmt='' and rno=#{grp}")
	int deleteReplyAll(ReplyVO vo);
	
	
	String selectCmt(ReplyVO vo);
	
}
