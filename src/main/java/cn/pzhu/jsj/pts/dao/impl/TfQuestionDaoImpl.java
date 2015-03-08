package cn.pzhu.jsj.pts.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.pzhu.jsj.pts.dao.GenerateDao;
import cn.pzhu.jsj.pts.dao.TfQuestionDao;
import cn.pzhu.jsj.pts.domain.TfQuestion;
import cn.pzhu.jsj.pts.dto.TFQuestionDto;
import cn.pzhu.jsj.pts.mapper.TfQuestionMapper;

@Repository("tfQuestionDao")
public class TfQuestionDaoImpl extends SqlSessionDaoSupport implements TfQuestionDao, GenerateDao {
	@Autowired
	private TfQuestionMapper tfQuestionMapper;

	@Override
	public List<TFQuestionDto> findByArgs(Map<String, Object> args) {
        return (List<TFQuestionDto>)this.getSqlSession().selectList("cn.pzhu.jsj.pts.mapper.question.selectTFQuestion", args);
	}

	@Override
	public Integer findCountByArgs(Map<String, Object> args) {
        return (Integer)this.getSqlSession().selectOne("cn.pzhu.jsj.pts.mapper.question.selectCountTFQuestion", args);
	}

	@Override
	public boolean insert(TfQuestion tfQuestion) {
		return 1 == tfQuestionMapper.insert(tfQuestion);
	}

	@Override
	public boolean delete(Integer id) {
		return 1 == tfQuestionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TfQuestion findById(Integer id) {
		return tfQuestionMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(TfQuestion tfQuestion) {
		return 1 == tfQuestionMapper.updateByPrimaryKey(tfQuestion);
	}

	@Override
	public List<TfQuestion> findByPaperId(Integer paperId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("paperId", paperId);
        return (List<TfQuestion>)this.getSqlSession().selectList("cn.pzhu.jsj.pts.mapper.examquestion.selectTFQuestion", args);
	}

	@Override
	public Integer countNotSelected(Map<String, Object> map) {
        return (Integer)this.getSqlSession().selectOne("cn.pzhu.jsj.pts.mapper.generatepaper.selectCountTFQuestion", map);
	}

	@Override
	public Integer randomSelectId(Map<String, Object> map) {
        return (Integer)this.getSqlSession().selectOne("cn.pzhu.jsj.pts.mapper.generatepaper.selectRandomTFQuestionId", map);
	}

}
