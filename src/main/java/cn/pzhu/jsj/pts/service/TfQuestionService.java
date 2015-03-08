package cn.pzhu.jsj.pts.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import cn.pzhu.jsj.pts.domain.TfQuestion;
import cn.pzhu.jsj.pts.dto.TFQuestionDto;

public interface TfQuestionService {
	
	List<TFQuestionDto> findTFQuestionDto( Integer languageType, Integer difficultyType,
			Long startTime, Long endTime, Integer teacherId, Integer page, Integer pageSize);	
	
	Integer findCountTFQuestionDto( Integer languageType, Integer difficultyType,
			Long startTime, Long endTime, Integer teacherId);	
	
	boolean insert(TfQuestion tfQuestion);
	
	boolean delete(Integer id);
	
	boolean update(TfQuestion tfQuestion);
	
	List<TfQuestion> findByPaperId(Integer paperId);

	TfQuestion findById(Integer id);
	
}
