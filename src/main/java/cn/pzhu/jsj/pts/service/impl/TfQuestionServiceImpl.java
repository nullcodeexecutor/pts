package cn.pzhu.jsj.pts.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.dao.TfQuestionDao;
import cn.pzhu.jsj.pts.domain.TfQuestion;
import cn.pzhu.jsj.pts.domain.TfQuestionExample;
import cn.pzhu.jsj.pts.dto.TFQuestionDto;
import cn.pzhu.jsj.pts.mapper.TfQuestionMapper;
import cn.pzhu.jsj.pts.service.TfQuestionService;

@Service("tfQuestionService")
public class TfQuestionServiceImpl implements TfQuestionService {
	@Autowired
	private TfQuestionDao tfQuestionDao;

	@Override
	public List<TFQuestionDto> findTFQuestionDto(Integer languageType,
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
		return tfQuestionDao.findByArgs(args);
	}

	@Override
	public Integer findCountTFQuestionDto(Integer languageType,
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
		return tfQuestionDao.findCountByArgs(args);
	}

	@Override
	public boolean insert(TfQuestion tfQuestion) {
		return tfQuestionDao.insert(tfQuestion);
	}

	@Override
	public boolean delete(Integer id) {
		return tfQuestionDao.delete(id);
	}

	@Override
	public boolean update(TfQuestion tfQuestion) {
		TfQuestion old = tfQuestionDao.findById(tfQuestion.getId());
		tfQuestion.setTeacherId(old.getTeacherId());
		return tfQuestionDao.update(tfQuestion);
	}

	@Override
	public List<TfQuestion> findByPaperId(Integer paperId) {
		return tfQuestionDao.findByPaperId(paperId);
	}

	@Override
	public TfQuestion findById(Integer id) {
		return tfQuestionDao.findById(id);
	}

	

}
