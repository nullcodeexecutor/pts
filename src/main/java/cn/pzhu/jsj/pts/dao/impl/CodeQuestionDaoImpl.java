package cn.pzhu.jsj.pts.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.pzhu.jsj.pts.dao.CodeQuestionDao;
import cn.pzhu.jsj.pts.dao.GenerateDao;
import cn.pzhu.jsj.pts.domain.CodeQuestion;
import cn.pzhu.jsj.pts.domain.CodeQuestionExample;
import cn.pzhu.jsj.pts.dto.CodeQuestionDto;
import cn.pzhu.jsj.pts.dto.SelectQuestionDto;
import cn.pzhu.jsj.pts.mapper.CodeQuestionMapper;

@Repository("codeQuestionDao")
public class CodeQuestionDaoImpl extends SqlSessionDaoSupport implements CodeQuestionDao, GenerateDao{
	@Autowired
	private CodeQuestionMapper codeQuestionMapper;
	
	@Override
	public List<CodeQuestionDto> findByArgs(Map<String, Object> args) {
        return (List<CodeQuestionDto>)this.getSqlSession().selectList("cn.pzhu.jsj.pts.mapper.question.selectCodeQuestion", args);
	}

	@Override
	public Integer findCountByArgs(Map<String, Object> args) {
        return (Integer)this.getSqlSession().selectOne("cn.pzhu.jsj.pts.mapper.question.selectCountCodeQuestion", args);
	}

	@Override
	public CodeQuestion findById(Integer id) {
		return codeQuestionMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(CodeQuestion codeQuestion) {
		return 1 == codeQuestionMapper.updateByPrimaryKey(codeQuestion);
	}

	@Override
	public boolean insert(CodeQuestion codeQuestion) {
		return 1 == codeQuestionMapper.insert(codeQuestion);
	}

	@Override
	public boolean deleteById(Integer id) {
		return 1 == codeQuestionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<CodeQuestion> findByPaperId(Integer paperId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("paperId", paperId);
        return (List<CodeQuestion>)this.getSqlSession().selectList("cn.pzhu.jsj.pts.mapper.examquestion.selectCodeQuestion", args);
	}

	@Override
	public Integer countNotSelected(Map<String, Object> map) {
        return (Integer)this.getSqlSession().selectOne("cn.pzhu.jsj.pts.mapper.generatepaper.selectCountCodeQuestion", map);
	}

	@Override
	public Integer randomSelectId(Map<String, Object> map) {
        return (Integer)this.getSqlSession().selectOne("cn.pzhu.jsj.pts.mapper.generatepaper.selectRandomCodeQuestionId", map);
	}

}
