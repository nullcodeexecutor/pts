package cn.pzhu.jsj.pts.service;

import java.util.List;

import cn.pzhu.jsj.pts.dto.AdminDto;

public interface AdminService {

	List<AdminDto> findAdminDto(Integer page, Integer pageSize);
	
	Integer findCount();
	
	boolean delete(Integer id);
	
	boolean insert(String account, String name, String email, Integer enabled, String pwd);
	
	boolean update(Integer id, String account, String name, String email, Integer enabled);
	
}
