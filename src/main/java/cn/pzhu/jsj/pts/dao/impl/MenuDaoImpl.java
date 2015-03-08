package cn.pzhu.jsj.pts.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.pzhu.jsj.pts.dao.MenuDao;
import cn.pzhu.jsj.pts.domain.Menu;
import cn.pzhu.jsj.pts.domain.MenuExample;
import cn.pzhu.jsj.pts.mapper.MenuMapper;

@Repository("menuDao")
public class MenuDaoImpl implements MenuDao {
	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> find(Integer parentId, String role, Integer status) {
		MenuExample menuExample = new MenuExample();
		MenuExample.Criteria criteria = menuExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		criteria.andRoleEqualTo(role);
		criteria.andStatusEqualTo(status);
		menuExample.setOrderByClause("sort asc");
		return menuMapper.selectByExample(menuExample);
	}

}
