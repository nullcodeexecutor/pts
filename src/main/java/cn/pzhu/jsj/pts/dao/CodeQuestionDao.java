package cn.pzhu.jsj.pts.dao;

import java.util.List;
import java.util.Map;

import cn.pzhu.jsj.pts.domain.CodeQuestion;
import cn.pzhu.jsj.pts.dto.CodeQuestionDto;

public interface CodeQuestionDao {
	
	List<CodeQuestionDto> findByArgs(Map<String, Object> args);

	Integer findCountByArgs(Map<String, Object> args);
	
	CodeQuestion findById(Integer id);
	
	boolean update(CodeQuestion codeQuestion);
	
	boolean insert(CodeQuestion codeQuestion);
	
	boolean deleteById(Integer id);
	
	List<CodeQuestion> findByPaperId(Integer paperId);
}
