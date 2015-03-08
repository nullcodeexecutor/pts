package cn.pzhu.jsj.pts.service;

import java.util.List;
import java.util.Map;

import cn.pzhu.jsj.pts.domain.CodeAnswer;

public interface CodeAnswerService {

	List<CodeAnswer> findByCodeQuestionId(Integer codeQuestionId);
	
	boolean insert(CodeAnswer codeAnswer);
	
	boolean deleteById(Integer id);
	
	boolean update(CodeAnswer codeAnswer);
	
	Map<String, String> findInputOutputByCodeQuestionId(Integer codeQuestionId);
	
}
