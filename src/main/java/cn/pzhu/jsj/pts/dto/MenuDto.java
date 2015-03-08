package cn.pzhu.jsj.pts.dto;

import java.util.List;

public class MenuDto {
	private String name;
	private String url;
	private List<MenuDto> menus;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<MenuDto> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuDto> menus) {
		this.menus = menus;
	}
	
}
