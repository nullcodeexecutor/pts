package cn.pzhu.jsj.pts.service.impl;

import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.dao.AdminDao;
import cn.pzhu.jsj.pts.dao.StudentDao;
import cn.pzhu.jsj.pts.dao.TeacherDao;
import cn.pzhu.jsj.pts.dao.UserDao;
import cn.pzhu.jsj.pts.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-13
 * Time: 上午11:03
 * To change this template use File | Settings | File Templates.
 */
public class PTSUserDetailServiceImpl implements UserDetailsService, LoginService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private AdminDao adminDao;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException, DataAccessException {
        cn.pzhu.jsj.pts.domain.User user = userDao.findByName(name);
        if(null == user){
            return null;
        }
        boolean enabled = false;
        if(user.getEnabled()!=0){
            enabled = true;
        }
        Collection<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        grantedAuths.add(new GrantedAuthorityImpl(user.getRole()));
        return new User(name, user.getPwd(), enabled, true, true, true, grantedAuths);
    }
    
    @Override
    public Object loadUserByAccountAndRole(String account, String role){
        if(Constant.USER_ROLE_ADMIN.equals(role)){
            return adminDao.findByAccount(account);
        }
        if(Constant.USER_ROLE_TEACHER.equals(role)){
        	return teacherDao.findByAccount(account);
        }
        return null;
    }
}
