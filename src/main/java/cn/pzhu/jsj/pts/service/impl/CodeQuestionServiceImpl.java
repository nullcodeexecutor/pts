package cn.pzhu.jsj.pts.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pzhu.jsj.pts.dao.CodeAnswerDao;
import cn.pzhu.jsj.pts.dao.CodeQuestionDao;
import cn.pzhu.jsj.pts.domain.CodeQuestion;
import cn.pzhu.jsj.pts.dto.CodeQuestionDto;
import cn.pzhu.jsj.pts.service.CodeQuestionService;

@Service("codeQuestionService")
public class CodeQuestionServiceImpl implements CodeQuestionService {
	@Autowired
	private CodeQuestionDao codeQuestionDao;
	@Autowired
	private CodeAnswerDao codeAnswerDao;

	@Override
	public List<CodeQuestionDto> findCodeQuestionDto(Integer languageType,
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
		return codeQuestionDao.findByArgs(args);
	}

	@Override
	public Integer findCountCodeQuestionDto(Integer languageType, Integer difficultyType,
			Long startTime, Long endTime, Integer teacherId) {
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
		return codeQuestionDao.findCountByArgs(args);
	}

	@Override
	public CodeQuestion findById(Integer id) {
		return codeQuestionDao.findById(id);
	}

	@Override
	public boolean update(CodeQuestion codeQuestion) {
		CodeQuestion old = codeQuestionDao.findById(codeQuestion.getId());
		codeQuestion.setTeacherId(old.getTeacherId());
		return codeQuestionDao.update(codeQuestion);
	}

	@Override
	public boolean insert(CodeQuestion codeQuestion) {
		return codeQuestionDao.insert(codeQuestion);
	}

	@Override
	public boolean deleteById(Integer id) {
		codeAnswerDao.deleteByCodeQuestionId(id);
		return codeQuestionDao.deleteById(id);
	}

	@Override
	public List<CodeQuestion> findByPaperId(Integer paperId) {
		return codeQuestionDao.findByPaperId(paperId);
	}

}
