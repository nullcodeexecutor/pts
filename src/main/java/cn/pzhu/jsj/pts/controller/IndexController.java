package cn.pzhu.jsj.pts.controller;

import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.service.ExamService;
import cn.pzhu.jsj.pts.service.LoginService;
import cn.pzhu.jsj.pts.service.TeacherService;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-1-27
 * Time: 上午10:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/*")
public class IndexController extends BaseController {
    @Autowired
    private ExamService examService;    

    @RequestMapping("/index")
    public String index(){
        return "pts/index";
    }

    @RequestMapping("/queryExam")
    public void queryExam(Model model,
    		@RequestParam(value="page") Integer page,
    		@RequestParam(value="pageSize") Integer pageSize,
    		@RequestParam(value="examName", required=false) String examName,
    		@RequestParam(value="teacherName", required=false) String teacherName,
    		@RequestParam(value="courseName", required=false) String courseName){
        model.addAttribute("rows",examService.findExamDto(examName, teacherName, courseName, page, pageSize));
        model.addAttribute("total", examService.findCountExamDto(examName, teacherName, courseName, page, pageSize));    	
    }

}
