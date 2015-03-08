package cn.pzhu.jsj.pts.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.pzhu.jsj.pts.common.MD5Util;
import cn.pzhu.jsj.pts.domain.User;
import cn.pzhu.jsj.pts.service.TeacherService;
import cn.pzhu.jsj.pts.service.UserService;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-20
 * Time: 上午11:19
 * To change this template use File | Settings | File Templates.
 */
@Controller("admin_TeacherController")
@RequestMapping(value = "/admin/teacher/")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;

    @RequestMapping("/index")
    public String index(Model model){
        return "admin/teacher";
    }   

    @RequestMapping("/query")
    public void query(Model model,
    			@RequestParam("page")Integer page,
    			@RequestParam("rows")Integer pageSize){
    	model.addAttribute("rows", teacherService.findTeacherDto(page, pageSize));
    	model.addAttribute("total", teacherService.findCount());
    }
    
    @RequestMapping("/modify")
    public void modify(Model model,
    			@RequestParam("id")Integer id,
    			@RequestParam("account")String account,
    			@RequestParam("name")String name,
    			@RequestParam("enabled")Integer enabled){
    	model.addAttribute("flg", teacherService.update(id, account, name, enabled));
    }
    
    @RequestMapping("/delete")
    public void delete(Model model,
    			@RequestParam("id")Integer id){
    	model.addAttribute("flg", teacherService.delete(id));
    }

}
