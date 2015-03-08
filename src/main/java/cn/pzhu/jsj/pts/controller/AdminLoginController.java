package cn.pzhu.jsj.pts.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.pzhu.jsj.pts.bean.ReadExcelStudent;
import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.domain.Student;
import cn.pzhu.jsj.pts.service.TeacherService;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-1-27
 * Time: 上午10:27
 * To change this template use File | Settings | File Templates.
 */
@Controller("adminLoginController")
@RequestMapping(value = "/*")
public class AdminLoginController extends BaseController {
    @Autowired
    private TeacherService teacherService;
    
    @RequestMapping("/adminLogin")
    public String adminLogin(){
        return "pts/adminLogin";
    }

}
