package cn.pzhu.jsj.pts.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.common.MD5Util;
import cn.pzhu.jsj.pts.dao.TeacherDao;
import cn.pzhu.jsj.pts.dao.UserDao;
import cn.pzhu.jsj.pts.domain.Teacher;
import cn.pzhu.jsj.pts.domain.User;
import cn.pzhu.jsj.pts.dto.TeacherDto;
import cn.pzhu.jsj.pts.service.TeacherService;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-26
 * Time: 下午3:20
 * To change this template use File | Settings | File Templates.
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Teacher findByAccount(String account) {
        return teacherDao.findByAccount(account);
    }

	@Override
	public boolean addTeacher(String account, String name, String pwd) {
		if(null != userDao.findByName(account)){
			return false;
		}if(null != teacherDao.findByAccount(account)){
			return false;
		}
		User user = new User();
		user.setName(account);
		user.setPwd(MD5Util.Md5(pwd));
		user.setRole(Constant.USER_ROLE_TEACHER);
		user.setEnabled(Constant.USER_ENABLED);
		
		Teacher teacher = new Teacher();
		teacher.setAccount(account);
		teacher.setName(name);
		
		return userDao.insert(user) && teacherDao.insert(teacher);
	}

	@Override
	public boolean update(Teacher teacher, String pwd) {
		User user = userDao.findByName(teacher.getAccount());
		user.setPwd(MD5Util.Md5(pwd));

		return userDao.update(user) && teacherDao.update(teacher);
	}

	@Override
	public List<Teacher> find(Integer page, Integer pageSize) {
		return teacherDao.find((page-1)*pageSize, pageSize);
	}

	@Override
	public Integer findCount() {
		return teacherDao.count();
	}

	@Override
	public List<TeacherDto> findTeacherDto(Integer page, Integer pageSize) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("offset", (page-1)*pageSize);
		args.put("pageSize", pageSize);
		return teacherDao.findTeacherDto(args);
	}

	@Override
	public boolean update(Integer id, String account, String name,
			Integer enabled) {
		Teacher teacher = teacherDao.findById(id);
		teacher.setName(name);
		
		User user = userDao.findByName(account);
		user.setEnabled(enabled);
		
		return teacherDao.update(teacher) && userDao.update(user);
	}

	@Override
	public boolean delete(Integer id) {
		Teacher teacher = teacherDao.findById(id);
		String account = teacher.getAccount();
		return teacherDao.delete(id) && userDao.deleteByName(account);
	}
}
