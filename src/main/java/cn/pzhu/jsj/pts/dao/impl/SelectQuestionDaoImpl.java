package cn.pzhu.jsj.pts.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.pzhu.jsj.pts.dao.GenerateDao;
import cn.pzhu.jsj.pts.dao.SelectQuestionDao;
import cn.pzhu.jsj.pts.domain.SelectQuestion;
import cn.pzhu.jsj.pts.domain.SelectQuestionExample;
import cn.pzhu.jsj.pts.dto.SelectQuestionDto;
import cn.pzhu.jsj.pts.dto.TFQuestionDto;
import cn.pzhu.jsj.pts.mapper.SelectQuestionMapper;

@Repository("selectQuestionDao")
public class SelectQuestionDaoImpl extends SqlSessionDaoSupport implements SelectQuestionDao, GenerateDao {
	@Autowired
	private SelectQuestionMapper selectQuestionMapper;

	@Override
	public List<SelectQuestionDto> findSelectQuestionDtoByArgs(Map<String, Object> args) {
        return (List<SelectQuestionDto>)this.getSqlSession().selectList("cn.pzhu.jsj.pts.mapper.question.selectSelectQuestion", args);
	}

	@Override
	public Integer findCountSelectQuestionDtoByArgs(Map<String, Object> args) {
        return (Integer)this.getSqlSession().selectOne("cn.pzhu.jsj.pts.mapper.question.selectCountSelectQuestion", args);
	}

	@Override
	public SelectQuestion findById(Integer id) {
		return selectQuestionMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(SelectQuestion selectQuestion) {
		return 1 == selectQuestionMapper.updateByPrimaryKey(selectQuestion);
	}

	@Override
	public boolean insert(SelectQuestion selectQuestion) {
		return 1 == selectQuestionMapper.insert(selectQuestion);
	}

	@Override
	public boolean deleteById(Integer id) {
		return 1 == selectQuestionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<SelectQuestionDto> findSelectQuestionDtoByPaperId(
			Integer paperId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("paperId", paperId);
        return (List<SelectQuestionDto>)this.getSqlSession().selectList("cn.pzhu.jsj.pts.mapper.examquestion.selectSelectQuestion", args);
	}

	@Override
	public Integer randomSelectId(Map<String, Object> args) {
        return (Integer)this.getSqlSession().selectOne("cn.pzhu.jsj.pts.mapper.generatepaper.selectRandomSelectQuestionId", args);
	}

	@Override
	public Integer countNotSelected(Map<String, Object> args) {
        return (Integer)this.getSqlSession().selectOne("cn.pzhu.jsj.pts.mapper.generatepaper.selectCountSelectQuestion", args);
	}

}
