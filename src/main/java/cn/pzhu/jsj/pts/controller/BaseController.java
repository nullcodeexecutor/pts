package cn.pzhu.jsj.pts.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.domain.Student;
import cn.pzhu.jsj.pts.service.LoginService;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-19
 * Time: 下午3:01
 * To change this template use File | Settings | File Templates.
 */
public class BaseController {
	@Resource
    private LoginService ptsUserDetailService;

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpSession session;

    private Map<String, String> errors = new HashMap<String, String>();
    private Map<String, String> values = new HashMap<String, String>();
    
    protected Student getStudent() {
    	Object obj = session.getAttribute(Constant.SESSION_STUDENT);
    	if(obj != null){
    		return (Student)obj;
    	}
		return null;
	}
    
    protected void setStudent(Student student) {
    	session.setAttribute(Constant.SESSION_STUDENT, student);
	}

    protected void addError(String errorName, String message){
        errors.put(errorName, message);
    }

    protected void addValue(String name, String value){
        values.put(name, value);
    }

    protected void clear(){
        errors.clear();
        values.clear();
    	session.removeAttribute("errors");
    	session.removeAttribute("values");
    }

    protected boolean isError(){
        return !errors.isEmpty();
    }

    protected void setValueAndError(){
    	session.setAttribute("errors", errors);
    	session.setAttribute("values", values);
    }

    protected Object getRoleUser(){
        Object user = session.getAttribute(Constant.SESSION_USER);
        if(user == null){
        	session.setAttribute(Constant.SESSION_USER, 
        			ptsUserDetailService.loadUserByAccountAndRole(getUsername(), getRole()));
        }
        return session.getAttribute(Constant.SESSION_USER);
    }
    
    protected void resetRoleUser(){
        session.removeAttribute(Constant.SESSION_USER);
        session.setAttribute(Constant.SESSION_USER, 
        			ptsUserDetailService.loadUserByAccountAndRole(getUsername(), getRole()));
    }
    
    protected String getUsername() {
        Object principal =  SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        UserDetails user =  (UserDetails)principal;
    	return user.getUsername();
	}
    
    protected String getRole() {
        Object principal =  SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        UserDetails user =  (UserDetails)principal;
    	return user.getAuthorities().iterator().next().getAuthority();    	
    }
    
    protected boolean isLogin(){
        Object principal =  SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            return true;
        }    
        return false;
    }
    
    protected boolean isStuentLogin(){
    	if(session.getAttribute(Constant.SESSION_STUDENT)!=null){
    		return true;
    	}
    	return false;
    }

}
