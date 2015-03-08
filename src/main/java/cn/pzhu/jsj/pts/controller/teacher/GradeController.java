package cn.pzhu.jsj.pts.controller.teacher;

import cn.pzhu.jsj.pts.controller.BaseController;
import cn.pzhu.jsj.pts.domain.Exam;
import cn.pzhu.jsj.pts.domain.Teacher;
import cn.pzhu.jsj.pts.service.ExamService;
import cn.pzhu.jsj.pts.service.GradeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.awt.*;
import java.util.Date;

@Controller("teacher_GradeController")
@RequestMapping(value = "/teacher/grade/")
public class GradeController extends BaseController{
    @Autowired
    private GradeService gradeService;

    @RequestMapping("/index")
    public String index(Model model){
        return "teacher/grade";
    }

    @RequestMapping("/query")
    public void query(Model model,
                      @RequestParam(value = "examId", defaultValue="-1") Integer examId,
                      @RequestParam(value = "page") Integer page,
                      @RequestParam(value = "rows") Integer pageSize,
                      @RequestParam(value = "account", required=false) String account,
                      @RequestParam(value = "name", required=false) String name,
                      @RequestParam(value = "className", required=false) String className,
                      @RequestParam(value = "departName", required=false) String departName){
    	model.addAttribute("rows", gradeService.findByExamId(examId, account, name, className, departName, page, pageSize));
    	model.addAttribute("total", gradeService.findCountByExamId(examId, account, name, className, departName));
    }
    
}
