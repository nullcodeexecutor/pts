package cn.pzhu.jsj.pts.service;

import java.util.List;

import cn.pzhu.jsj.pts.domain.SelectOption;

public interface SelectOptionService {

	List<SelectOption> findBySelectQuestionId(Integer selectQuestionId);
	
	boolean update(SelectOption selectOption);
	
	boolean deleteById(Integer id);
	
	boolean insert(SelectOption selectOption);
	
}
