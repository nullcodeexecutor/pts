package cn.pzhu.jsj.pts.dao;

import java.util.List;

import cn.pzhu.jsj.pts.domain.Menu;

public interface MenuDao {

	List<Menu> find(Integer parentId, String role, Integer status);
	
}
