package cn.pzhu.jsj.pts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pzhu.jsj.pts.common.MD5Util;
import cn.pzhu.jsj.pts.dao.UserDao;
import cn.pzhu.jsj.pts.domain.User;
import cn.pzhu.jsj.pts.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User findByName(String name) {
		return userDao.findByName(name);
	}

	@Override
	public List<User> find(Integer page, Integer pageSize) {
		return userDao.find((page-1)*pageSize, pageSize);
	}

	@Override
	public Integer findCount() {
		return userDao.count();
	}

	@Override
	public User findById(Integer id) {
		return userDao.findById(id);
	}

	@Override
	public boolean update(User user) {
		return userDao.update(user);
	}

	@Override
	public boolean deleteById(Integer id) {
		return userDao.deleteById(id);
	}

	@Override
	public boolean updatePwd(String account, String pwd) {
		User user = userDao.findByName(account);
		user.setPwd(MD5Util.Md5(pwd));
		return userDao.update(user);
	}

}
