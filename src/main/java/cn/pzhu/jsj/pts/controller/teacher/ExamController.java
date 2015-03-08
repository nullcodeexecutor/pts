package cn.pzhu.jsj.pts.controller.teacher;

import cn.pzhu.jsj.pts.controller.BaseController;
import cn.pzhu.jsj.pts.domain.Exam;
import cn.pzhu.jsj.pts.domain.Teacher;
import cn.pzhu.jsj.pts.service.ExamService;
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

@Controller("teacher_ExamController")
@RequestMapping(value = "/teacher/exam/")
public class ExamController extends BaseController{
    @Autowired
    private ExamService examService;

    @RequestMapping("/index")
    public String index(Model model){
        return "teacher/exam";
    }

    @RequestMapping("/query")
    public void query(Model model,
                      @RequestParam(value = "courseId", defaultValue="-1") Integer courseId,
                      @RequestParam(value = "page") Integer page,
                      @RequestParam(value = "rows") Integer pageSize){
        model.addAttribute("rows",examService.findByCourseId(courseId, page, pageSize));
        model.addAttribute("total", examService.findTotalByCourseId(courseId));
    }

    @RequestMapping("/queryAll")
    public void queryAll(Model model,
                      @RequestParam(value = "courseId", defaultValue="-1") Integer courseId){
        model.addAttribute("exams",examService.findByCourseId(courseId, 1, Integer.MAX_VALUE));
    }
    
    @RequestMapping("/queryByCourseId")
    public void queryByCourseId(Model model,
                      @RequestParam(value = "courseId") Integer courseId){
        model.addAttribute("exams",examService.findByCourseId(courseId, 1, Integer.MAX_VALUE));
    }
    
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public void delete(Model model,
                      @RequestParam(value = "id") Integer id){
    	Teacher teacher = (Teacher)getRoleUser();
        model.addAttribute("flg",examService.delete(id, teacher.getId()));
    }
    
    @RequestMapping(value="/insert", method=RequestMethod.POST)
    public void insert(Model model,
                      @RequestParam(value = "courseId", defaultValue="-1") Integer courseId,
                      @RequestParam(value = "name") String name,
                      @RequestParam(value = "score") Integer score,
                      @RequestParam(value = "status") Integer status,
                      @RequestParam(value = "startTime") Long startTime,
                      @RequestParam(value = "endTime") Long endTime,
                      @RequestParam(value = "type") Integer type){
    	Exam exam = new Exam();
    	exam.setCourseId(courseId);
    	exam.setName(name);
    	exam.setScore(score);
    	exam.setStatus(status);
    	exam.setStartTime(new Date(startTime));
    	exam.setEndTime(new Date(endTime));
    	exam.setType(type);
    	
        model.addAttribute("flg",examService.insert(exam));
    }
    
    @RequestMapping(value="/modify", method=RequestMethod.POST)
    public void modify(Model model,
                      @RequestParam(value = "id") Integer id,
                      @RequestParam(value = "name") String name,
                      @RequestParam(value = "score") Integer score,
                      @RequestParam(value = "status") Integer status,
                      @RequestParam(value = "startTime") Long startTime,
                      @RequestParam(value = "endTime") Long endTime,
                      @RequestParam(value = "type") Integer type){
    	Exam exam = new Exam();
    	exam.setId(id);
    	exam.setName(name);
    	exam.setScore(score);
    	exam.setStatus(status);
    	exam.setStartTime(new Date(startTime));
    	exam.setEndTime(new Date(endTime));
    	exam.setType(type);
    	
        model.addAttribute("flg",examService.update(exam));
    }

}
