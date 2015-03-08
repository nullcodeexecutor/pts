package cn.pzhu.jsj.pts.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.dao.MenuDao;
import cn.pzhu.jsj.pts.domain.Menu;
import cn.pzhu.jsj.pts.dto.MenuDto;
import cn.pzhu.jsj.pts.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;

	@Override
	public List<MenuDto> findByRole(String role) {
		List<Menu> parents = menuDao.find(0, role, 1);
		
		List<MenuDto> dtos = new ArrayList<MenuDto>();
		MenuDto dto = null;
		for(Menu menu : parents){
			List<Menu> children = menuDao.find(menu.getId(), role, 1);
			List<MenuDto> childDtos = new ArrayList<MenuDto>();
			for(Menu m : children){
				MenuDto d = new MenuDto();
				d.setName(m.getName());
				d.setUrl(m.getUrl());
				childDtos.add(d);
			}
			dto = new MenuDto();
			dto.setName(menu.getName());
			dto.setMenus(childDtos);
			dtos.add(dto);
		}
		return dtos;
	}

}
