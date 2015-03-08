package cn.pzhu.jsj.pts.dao;

import java.util.List;

import cn.pzhu.jsj.pts.domain.SelectOption;

public interface SelectOptionDao {
	
	List<SelectOption> findBySelectQuestionId(Integer selectQuestionId);
	
	void deleteBySelectQuestionId(Integer selectQuestionId);
	
	SelectOption findById(Integer id);
	
	boolean update(SelectOption selectOption);
	
	boolean deleteById(Integer id);
	
	boolean insert(SelectOption selectOption);

}
