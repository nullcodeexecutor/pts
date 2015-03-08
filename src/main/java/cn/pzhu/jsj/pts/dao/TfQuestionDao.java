package cn.pzhu.jsj.pts.dao;

import java.util.List;
import java.util.Map;

import cn.pzhu.jsj.pts.domain.TfQuestion;
import cn.pzhu.jsj.pts.dto.TFQuestionDto;

public interface TfQuestionDao {
	
	List<TFQuestionDto> findByArgs(Map<String, Object> args);
	
	Integer findCountByArgs(Map<String, Object> args);
	
	boolean insert(TfQuestion tfQuestion);
	
	boolean delete(Integer id);
	
	TfQuestion findById(Integer id);
	
	boolean update(TfQuestion tfQuestion);
	
	List<TfQuestion> findByPaperId(Integer paperId);
	
}
