package cn.pzhu.jsj.pts.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.pzhu.jsj.pts.dao.CodeAnswerDao;
import cn.pzhu.jsj.pts.domain.CodeAnswer;
import cn.pzhu.jsj.pts.domain.CodeAnswerExample;
import cn.pzhu.jsj.pts.mapper.CodeAnswerMapper;

@Repository("codeAnswerDao")
public class CodeAnswerDaoImpl implements CodeAnswerDao{
	@Autowired
	private CodeAnswerMapper codeAnswerMapper;
	
	@Override
	public void deleteByCodeQuestionId(Integer codeQuestionId) {
		CodeAnswerExample codeAnswerExample = new CodeAnswerExample();
		CodeAnswerExample.Criteria criteria = codeAnswerExample.createCriteria();
		criteria.andQuestionIdEqualTo(codeQuestionId);
		codeAnswerMapper.deleteByExample(codeAnswerExample);
	}

	@Override
	public List<CodeAnswer> findByCodeQuestionId(Integer codeQuestionId) {
		CodeAnswerExample codeAnswerExample = new CodeAnswerExample();
		CodeAnswerExample.Criteria criteria = codeAnswerExample.createCriteria();
		criteria.andQuestionIdEqualTo(codeQuestionId);
		return codeAnswerMapper.selectByExample(codeAnswerExample);
	}

	@Override
	public boolean insert(CodeAnswer codeAnswer) {
		return 1 == codeAnswerMapper.insert(codeAnswer);
	}

	@Override
	public boolean deleteById(Integer id) {
		return 1 == codeAnswerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public CodeAnswer findById(Integer id) {
		return codeAnswerMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(CodeAnswer codeAnswer) {
		return 1 == codeAnswerMapper.updateByPrimaryKey(codeAnswer);
	}

}
