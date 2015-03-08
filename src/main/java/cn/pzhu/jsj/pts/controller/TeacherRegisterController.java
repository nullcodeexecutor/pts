package cn.pzhu.jsj.pts.controller;

import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.service.ExamService;
import cn.pzhu.jsj.pts.service.LoginService;
import cn.pzhu.jsj.pts.service.TeacherService;
import cn.pzhu.jsj.pts.service.UserService;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("registerController")
@RequestMapping(value = "/*")
public class TeacherRegisterController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;

    @RequestMapping("/teacherRegister")
    public String teacher(Model model){
        return "pts/teacherRegister";
    }
    
    @RequestMapping("/registry")
    public String registry(Model model,
    		@RequestParam(value="account")String account,
    		@RequestParam(value="name")String name,
            @RequestParam(value="pwd")String pwd,
            @RequestParam(value="repwd")String repwd){
		if(validate(account, name, pwd, repwd)){
			setValueAndError();
		    return "redirect:/teacherRegister";
		}
		if(!teacherService.addTeacher(account, name, repwd)){
			model.addAttribute("message", "注册失败");
		    return "pts/teacherRegister";
		}
		model.addAttribute("message", "注册成功，请登录");
        return "pts/adminLogin";
    }
    
    private boolean validate(String account, String name, String pwd, String repwd){
    	 clear();
    	 addValue("account", account);
         addValue("name", name);
         addValue("pwd", pwd);
         addValue("repwd", repwd);
         if(userService.findByName(account) != null){
             addError("account", "该账号已经被使用");
         }
         if(account.length()<4){
             addError("account", "账号长度不能小于4");
         }
         if(account.length()>20){
             addError("account", "账号长度不能大于20");
         }
         if(name.length()<2){
             addError("name", "姓名长度不能小于2");
         }
         if(name.length()>4){
             addError("name", "姓名长度不能大于4");
         }
         if(pwd.length()<6){
             addError("pwd", "密码长度不能小于6");
         }
         if(pwd.length()>20){
             addError("pwd", "密码长度不能大于20");
         }
         if(!pwd.equals(repwd)){
             addError("repwd", "重复密码与密码不一致");
         }
         return isError();
    }

}
