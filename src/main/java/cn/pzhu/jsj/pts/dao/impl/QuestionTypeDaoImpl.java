package cn.pzhu.jsj.pts.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.pzhu.jsj.pts.dao.QuestionTypeDao;
import cn.pzhu.jsj.pts.domain.QuestionType;
import cn.pzhu.jsj.pts.mapper.QuestionTypeMapper;

@Repository("questionTypeDao")
public class QuestionTypeDaoImpl implements QuestionTypeDao {
	@Autowired
	private QuestionTypeMapper questionTypeMapper;

	@Override
	public QuestionType findById(Integer id) {
		return questionTypeMapper.selectByPrimaryKey(id);
	}

}
