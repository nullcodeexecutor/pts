package cn.pzhu.jsj.pts.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.pzhu.jsj.pts.dao.SelectOptionDao;
import cn.pzhu.jsj.pts.domain.SelectOption;
import cn.pzhu.jsj.pts.domain.SelectOptionExample;
import cn.pzhu.jsj.pts.mapper.SelectOptionMapper;

@Repository("selectOptionDao")
public class SelectOptionDaoImpl implements SelectOptionDao {
	@Autowired
	private SelectOptionMapper selectOptionMapper;	

	@Override
	public List<SelectOption> findBySelectQuestionId(Integer selectQuestionId) {
		SelectOptionExample selectOptionExample = new SelectOptionExample();
		SelectOptionExample.Criteria criteria = selectOptionExample.createCriteria();
		criteria.andSelectQuestionIdEqualTo(selectQuestionId);
        String order = "value asc";
        selectOptionExample.setOrderByClause(order);
		return selectOptionMapper.selectByExample(selectOptionExample);
	}

	@Override
	public void deleteBySelectQuestionId(Integer selectQuestionId) {
		SelectOptionExample selectOptionExample = new SelectOptionExample();
		SelectOptionExample.Criteria criteria = selectOptionExample.createCriteria();
		criteria.andSelectQuestionIdEqualTo(selectQuestionId);
		selectOptionMapper.deleteByExample(selectOptionExample);
	}

	@Override
	public SelectOption findById(Integer id) {
		return selectOptionMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(SelectOption selectOption) {
		return 1 == selectOptionMapper.updateByPrimaryKey(selectOption);
	}

	@Override
	public boolean deleteById(Integer id) {
		return 1 == selectOptionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public boolean insert(SelectOption selectOption) {
		return 1 == selectOptionMapper.insert(selectOption);
	}

}
