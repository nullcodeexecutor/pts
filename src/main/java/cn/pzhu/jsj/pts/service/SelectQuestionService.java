package cn.pzhu.jsj.pts.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import cn.pzhu.jsj.pts.domain.SelectQuestion;
import cn.pzhu.jsj.pts.dto.SelectQuestionDto;

public interface SelectQuestionService {

	List<SelectQuestionDto> findSelectQuestionDto(Integer languageType, Integer difficultyType, Long startTime, Long endTime, Integer teacherId, Integer page, Integer pageSize);
	
	Integer findCountSelectQuestionDto(Integer languageType, Integer difficultyType, Long startTime, Long endTime, Integer teacherId);
	
	SelectQuestion findById(Integer id);
	
	boolean update(SelectQuestion selectQuestion);
	
	boolean insert(SelectQuestion selectQuestion);
	
	boolean deleteById(Integer id);
	
	List<SelectQuestionDto> findSelectQuestionDtoByPaperId(Integer paperId);
	
}
