package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.FileVO;

@Mapper
public interface FileXMLMapper {
	
	@Insert("INSERT INTO bbs_file (idx,bno,originalName,saveName,size,deleteYn,insertTime,deleteTime) VALUES	( #{idx}, #{bno}, #{originalName}, #{saveName}, #{size}, 'N', NOW(), NULL)")
	public int insertAttach(FileVO attachList);
	
	@Select("SELECT * FROM bbs_file WHERE deleteYn = 'N' AND idx = #{idx}")
	public FileVO selectAttachDetail(long idx);
	
	@Delete("UPDATE bbs_file SET deleteYn = 'Y', deleteTime = NOW() WHERE bno = #{bno}")
	public int deleteAttach(int idx);
	
	@Select("SELECT * FROM bbs_file WHERE deleteYn = 'N' AND bno = #{bno}")
	public List<FileVO> selectAttachList(int idx);
	
	@Select("SELECT COUNT(*) FROM bbs_file WHERE deleteYn = 'N' AND bno = #{bno}")
	public int selectAttachTotalCount(int idx);
	
	@Select("SELECT * FROM bbs ORDER BY num DESC LIMIT 1")
	public int readLastBno();
	
	@Update("UPDATE bbs_file SET deleteYn = 'N' WHERE idx = #{idx}")
	public int undeleteAttach(Long idx);
}
