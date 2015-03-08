package cn.pzhu.jsj.pts.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.dao.AdminDao;
import cn.pzhu.jsj.pts.dao.UserDao;
import cn.pzhu.jsj.pts.domain.Admin;
import cn.pzhu.jsj.pts.domain.User;
import cn.pzhu.jsj.pts.dto.AdminDto;
import cn.pzhu.jsj.pts.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private UserDao userDao;

	@Override
	public List<AdminDto> findAdminDto(Integer page, Integer pageSize) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("offset", (page-1)*pageSize);
		args.put("pageSize", pageSize);
		return adminDao.findAdminDto(args);
	}

	@Override
	public Integer findCount() {
		return adminDao.findCount();
	}

	@Override
	public boolean delete(Integer id) {
		Admin admin = adminDao.findById(id);
		
		return userDao.deleteByName(admin.getAccount()) && adminDao.deleteById(id);
	}

	@Override
	public boolean insert(String account, String name, String email,
			Integer enabled, String pwd) {
		User user = new User();
		user.setEnabled(enabled);
		user.setRole(Constant.USER_ROLE_ADMIN);
		user.setName(account);
		user.setPwd(pwd);
		
		Admin admin = new Admin();
		admin.setAccount(account);
		admin.setName(name);
		admin.setEmail(email);
		
		return userDao.insert(user) && adminDao.insert(admin);
	}

	@Override
	public boolean update(Integer id, String account, String name,
			String email, Integer enabled) {
		User user = userDao.findByName(account);
		user.setEnabled(enabled);
		
		Admin admin = new Admin();
		admin.setId(id);
		admin.setAccount(account);
		admin.setName(name);
		admin.setEmail(email);
		
		return userDao.update(user) && adminDao.update(admin);
	}

}
