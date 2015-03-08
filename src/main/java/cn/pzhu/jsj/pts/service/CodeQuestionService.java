package cn.pzhu.jsj.pts.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import cn.pzhu.jsj.pts.domain.CodeQuestion;
import cn.pzhu.jsj.pts.dto.CodeQuestionDto;

public interface CodeQuestionService {

	List<CodeQuestionDto> findCodeQuestionDto(Integer languageType, Integer difficultyType, Long startTime, Long endTime, Integer teacherId, Integer page, Integer pageSize);
	
	Integer findCountCodeQuestionDto(Integer languageType, Integer difficultyType, Long startTime, Long endTime, Integer teacherId);
	
	CodeQuestion findById(Integer id);
	
	boolean update(CodeQuestion codeQuestion);
	
	boolean insert(CodeQuestion codeQuestion);
	
	boolean deleteById(Integer id);
	
	List<CodeQuestion> findByPaperId(Integer paperId);
	
}
