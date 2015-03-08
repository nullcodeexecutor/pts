package cn.pzhu.jsj.pts.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.pzhu.jsj.pts.common.MD5Util;
import cn.pzhu.jsj.pts.domain.User;
import cn.pzhu.jsj.pts.service.AdminService;
import cn.pzhu.jsj.pts.service.TeacherService;
import cn.pzhu.jsj.pts.service.UserService;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-20
 * Time: 上午11:19
 * To change this template use File | Settings | File Templates.
 */
@Controller("admin_AdminController")
@RequestMapping(value = "/admin/admin/")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;

    @RequestMapping("/index")
    public String index(Model model){
        return "admin/admin";
    }   

    @RequestMapping("/query")
    public void query(Model model,
    			@RequestParam("page")Integer page,
    			@RequestParam("rows")Integer pageSize){
    	model.addAttribute("rows", adminService.findAdminDto(page, pageSize));
    	model.addAttribute("total", adminService.findCount());
    }
    
    @RequestMapping("/modify")
    public void modify(Model model,
    			@RequestParam("id")Integer id,
    			@RequestParam("account")String account,
    			@RequestParam("name")String name,
    			@RequestParam("email")String email,
    			@RequestParam("enabled")Integer enabled){
    	model.addAttribute("flg", adminService.update(id, account, name, email, enabled));
    }
    
    @RequestMapping("/insert")
    public void insert(Model model,
    			@RequestParam("account")String account,
    			@RequestParam("name")String name,
    			@RequestParam(value="email", required=false)String email,
    			@RequestParam("enabled")Integer enabled,
    			@RequestParam("pwd")String pwd){
    	if(userService.findByName(account) != null){
        	model.addAttribute("flg", false);
        	model.addAttribute("msg", "该账号已经存在");
    		return;
    	}
    	model.addAttribute("flg", adminService.insert(account, name, email, enabled, MD5Util.Md5(pwd)));
    }
    
    @RequestMapping("/delete")
    public void delete(Model model,
    			@RequestParam("id")Integer id){
    	model.addAttribute("flg", adminService.delete(id));
    }

}
