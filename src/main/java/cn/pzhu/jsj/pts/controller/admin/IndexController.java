package cn.pzhu.jsj.pts.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.service.MenuService;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-20
 * Time: 上午11:19
 * To change this template use File | Settings | File Templates.
 */
@Controller("admin_IndexController")
@RequestMapping(value = "/admin/*")
public class IndexController {
	@Autowired
	private MenuService menuService;

    @RequestMapping("/index")
    public String index(){
        return "admin/index";
    }

    @RequestMapping("/menu")
    public void menu(Model model){
    	model.addAttribute("menus", menuService.findByRole(Constant.USER_ROLE_ADMIN));
    }
    
}
