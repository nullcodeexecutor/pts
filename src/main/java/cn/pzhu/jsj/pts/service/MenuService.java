package cn.pzhu.jsj.pts.service;

import java.util.List;

import cn.pzhu.jsj.pts.dto.MenuDto;

public interface MenuService {
	
	List<MenuDto> findByRole(String role);
	
}
