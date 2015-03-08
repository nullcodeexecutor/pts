package cn.pzhu.jsj.pts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pzhu.jsj.pts.dao.SelectOptionDao;
import cn.pzhu.jsj.pts.domain.SelectOption;
import cn.pzhu.jsj.pts.service.SelectOptionService;

@Service("selectOptionService")
public class SelectOptionServiceImpl implements SelectOptionService {
	@Autowired
	private SelectOptionDao selectOptionDao;
	
	@Override
	public List<SelectOption> findBySelectQuestionId(Integer selectQuestionId) {
		return selectOptionDao.findBySelectQuestionId(selectQuestionId);
	}

	@Override
	public boolean update(SelectOption selectOption) {
		SelectOption old = selectOptionDao.findById(selectOption.getId());
		selectOption.setSelectQuestionId(old.getSelectQuestionId());
		return selectOptionDao.update(selectOption);
	}

	@Override
	public boolean deleteById(Integer id) {
		return selectOptionDao.deleteById(id);
	}

	@Override
	public boolean insert(SelectOption selectOption) {
		return selectOptionDao.insert(selectOption);
	}

}
