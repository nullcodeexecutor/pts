package cn.pzhu.jsj.pts.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pzhu.jsj.pts.dao.CodeAnswerDao;
import cn.pzhu.jsj.pts.domain.CodeAnswer;
import cn.pzhu.jsj.pts.service.CodeAnswerService;

@Service("codeAnswerService")
public class CodeAnswerServiceImpl implements CodeAnswerService{
	@Autowired
	private CodeAnswerDao codeAnswerDao;

	@Override
	public List<CodeAnswer> findByCodeQuestionId(Integer codeQuestionId) {
		return codeAnswerDao.findByCodeQuestionId(codeQuestionId);
	}

	@Override
	public boolean insert(CodeAnswer codeAnswer) {
		return codeAnswerDao.insert(codeAnswer);
	}

	@Override
	public boolean deleteById(Integer id) {
		return codeAnswerDao.deleteById(id);
	}

	@Override
	public boolean update(CodeAnswer codeAnswer) {
		CodeAnswer old = codeAnswerDao.findById(codeAnswer.getId());
		codeAnswer.setQuestionId(old.getQuestionId());
		return codeAnswerDao.update(codeAnswer);
	}

	@Override
	public Map<String, String> findInputOutputByCodeQuestionId(
			Integer codeQuestionId) {
		List<CodeAnswer> answers =  codeAnswerDao.findByCodeQuestionId(codeQuestionId);
		Map<String, String> map = new HashMap<String, String>();
		for(CodeAnswer codeAnswer : answers){
			map.put(codeAnswer.getInputData(), codeAnswer.getOutputData());
		}
		return map;
	}

}
