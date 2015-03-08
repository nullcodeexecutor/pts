package cn.pzhu.jsj.pts.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.dao.SelectOptionDao;
import cn.pzhu.jsj.pts.dao.SelectQuestionDao;
import cn.pzhu.jsj.pts.domain.SelectOption;
import cn.pzhu.jsj.pts.domain.SelectQuestion;
import cn.pzhu.jsj.pts.dto.SelectQuestionDto;
import cn.pzhu.jsj.pts.service.SelectQuestionService;

@Service("selectQuestionService")
public class SelectQuestionServiceImpl implements SelectQuestionService{
	@Autowired
	private SelectQuestionDao selectQuestionDao;
	@Autowired
	private SelectOptionDao selectOptionDao;

	@Override
	public List<SelectQuestionDto> findSelectQuestionDto(Integer languageType,
			Integer difficultyType, Long startTime, Long endTime, Integer teacherId, Integer page,
			Integer pageSize) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("offset", (page-1)*pageSize);
		args.put("pageSize", pageSize);
		if(languageType != null){
			args.put("languageType", languageType);
		}
		if(difficultyType != null){
			args.put("difficultyType", difficultyType);
		}
		if(startTime != null){
			args.put("startTime", startTime);
		}
		if(endTime != null){
			args.put("endTime", endTime);
		}
		if(teacherId != null){
			args.put("teacherId", teacherId);			
		}
		return selectQuestionDao.findSelectQuestionDtoByArgs(args);
	}

	@Override
	public Integer findCountSelectQuestionDto(Integer languageType,
			Integer difficultyType, Long startTime, Long endTime, Integer teacherId) {
		Map<String, Object> args = new HashMap<String, Object>();
		if(languageType != null){
			args.put("languageType", languageType);
		}
		if(difficultyType != null){
			args.put("difficultyType", difficultyType);
		}
		if(startTime != null){
			args.put("startTime", startTime);
		}
		if(endTime != null){
			args.put("endTime", endTime);
		}
		if(teacherId != null){
			args.put("teacherId", teacherId);			
		}
		return selectQuestionDao.findCountSelectQuestionDtoByArgs(args);
	}

	@Override
	public SelectQuestion findById(Integer id) {
		return selectQuestionDao.findById(id);
	}

	@Override
	public boolean update(SelectQuestion selectQuestion) {
		SelectQuestion old = selectQuestionDao.findById(selectQuestion.getId());
		selectQuestion.setTeacherId(old.getTeacherId());
		return selectQuestionDao.update(selectQuestion);
	}

	@Override
	public boolean insert(SelectQuestion selectQuestion) {
		return selectQuestionDao.insert(selectQuestion);
	}

	@Override
	public boolean deleteById(Integer id) {
		selectOptionDao.deleteBySelectQuestionId(id);
		return selectQuestionDao.deleteById(id);
	}

	@Override
	public List<SelectQuestionDto> findSelectQuestionDtoByPaperId(
			Integer paperId) {
		 List<SelectQuestionDto> selectQuestionDtos = selectQuestionDao.findSelectQuestionDtoByPaperId(paperId);
		 for(SelectQuestionDto dto : selectQuestionDtos){
			 dto.setOptions(selectOptionDao.findBySelectQuestionId(dto.getId()));
		 }
		return selectQuestionDtos;
	}

}
