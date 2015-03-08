package cn.pzhu.jsj.pts.dao;

import java.util.List;

import cn.pzhu.jsj.pts.domain.CodeAnswer;

public interface CodeAnswerDao {
	
	void deleteByCodeQuestionId(Integer codeQuestionId);
	
	List<CodeAnswer> findByCodeQuestionId(Integer codeQuestionId);
	
	boolean insert(CodeAnswer codeAnswer);
	
	boolean deleteById(Integer id);
	
	CodeAnswer findById(Integer id);
	
	boolean update(CodeAnswer codeAnswer);
	
}
