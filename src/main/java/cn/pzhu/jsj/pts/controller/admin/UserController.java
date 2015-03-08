package cn.pzhu.jsj.pts.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.pzhu.jsj.pts.common.MD5Util;
import cn.pzhu.jsj.pts.domain.User;
import cn.pzhu.jsj.pts.service.UserService;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-20
 * Time: 上午11:19
 * To change this template use File | Settings | File Templates.
 */
@Controller("admin_UserController")
@RequestMapping(value = "/admin/user/")
public class UserController {
	@Autowired
	private UserService userService;

    @RequestMapping("/index")
    public String index(Model model){
        return "admin/user";
    }   

    @RequestMapping("/query")
    public void query(Model model,
    			@RequestParam("page")Integer page,
    			@RequestParam("rows")Integer pageSize){
    	model.addAttribute("rows", userService.find(page, pageSize));
    	model.addAttribute("total", userService.findCount());
    }
    
    @RequestMapping("/modify")
    public void modify(Model model,
    			@RequestParam("id")Integer id,
    			@RequestParam("enabled")Integer enabled,
    			@RequestParam(value="password", required=false)String password){
    	User user = userService.findById(id);
    	user.setEnabled(enabled);
    	if(password != null){
    		user.setPwd(MD5Util.Md5(password));
    	}
    	model.addAttribute("flg", userService.update(user));
    }
    
    @RequestMapping("/modifyPwd")
    public void modify(Model model,
    			@RequestParam("account")String account,
    			@RequestParam("pwd")String pwd){
    	model.addAttribute("flg", userService.updatePwd(account, pwd));
    }    
    
    @RequestMapping("/delete")
    public void delete(Model model,
    			@RequestParam("id")Integer id){
    	model.addAttribute("flg", userService.deleteById(id));
    }

}
