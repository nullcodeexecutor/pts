package cn.pzhu.jsj.pts.service;

import java.util.List;

import cn.pzhu.jsj.pts.domain.User;

public interface UserService {

	User findByName(String name);

	User findById(Integer id);
	
	List<User> find(Integer page, Integer pageSize);
	
	Integer findCount();
	
	boolean update(User user);
	
	boolean deleteById(Integer id);
	
	boolean updatePwd(String account, String pwd);

}
