package cn.pzhu.jsj.pts.dao;

import java.util.List;
import java.util.Map;

import cn.pzhu.jsj.pts.domain.SelectQuestion;
import cn.pzhu.jsj.pts.dto.SelectQuestionDto;

public interface SelectQuestionDao {
	List<SelectQuestionDto> findSelectQuestionDtoByArgs(Map<String, Object> args);

	Integer findCountSelectQuestionDtoByArgs(Map<String, Object> args);
	
	SelectQuestion findById(Integer id);
	
	boolean update(SelectQuestion selectQuestion);
	
	boolean insert(SelectQuestion selectQuestion);
	
	boolean deleteById(Integer id);
	
	List<SelectQuestionDto> findSelectQuestionDtoByPaperId(Integer paperId);
}
