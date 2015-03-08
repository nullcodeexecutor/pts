package cn.pzhu.jsj.pts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.domain.Exam;
import cn.pzhu.jsj.pts.domain.Student;
import cn.pzhu.jsj.pts.service.ExamService;
import cn.pzhu.jsj.pts.service.StudentService;
import cn.pzhu.jsj.pts.service.TeacherService;

@Controller("studentEntryController")
@RequestMapping(value = "/*")
public class StudentEntryController extends BaseController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ExamService examService;
    
    @RequestMapping("/entry")
    public String examEntry(Model model,
    						@RequestParam("examId")Integer examId){
    	clear();
		session.removeAttribute("courseId");
    	Exam exam = examService.findById(examId);    
		session.setAttribute("examId", examId);
		if(isStuentLogin() && getStudent().getCourseId() == exam.getCourseId()){
	        return "redirect:/student/exam/index";
		}	
        return "pts/entry";    		
    }
    
    @RequestMapping("/grade")
    public String grade(Model model,
    						@RequestParam("courseId")Integer courseId){
    	clear();
		session.removeAttribute("examId");
    	if(isStuentLogin() && getStudent().getCourseId() == courseId){
			session.removeAttribute("courseId");
    		return "redirect:/student/grade/index";
		}
		session.setAttribute("courseId", courseId);
        return "pts/entry";        	
    }

    /**
	 * 学生登录的逻辑
	 */
    @RequestMapping("/studentLogin")
    public String studentLoginHandle(Model model,
									@RequestParam("account")String account,
									@RequestParam("pwd")String pwd){
    	Student student = null;
    	if(null != session.getAttribute("examId")){
    		Integer examId = (Integer)session.getAttribute("examId");
        	student = studentService.findByAccountAndExamId(account, examId);
    	}else if(null != session.getAttribute("courseId")){
    		Integer courseId = (Integer)session.getAttribute("courseId");
        	student = studentService.findByAccountAndCourseId(account, courseId);
    	}    	
		clear();
		addValue("account", account);
		addValue("pwd", pwd);
    	if(student==null){
			addError("account", "该账号不存在");
    	}
    	if(student != null && !student.getPwd().equals(pwd)){
			addError("pwd", "密码错误");
    	}
    	if(isError()){
    		setValueAndError();
            return "pts/entry";    		  	
    	}    	
    	if(null != session.getAttribute("examId")){
        	session.setAttribute(Constant.SESSION_STUDENT, student);    
    		Integer examId = (Integer)session.getAttribute("examId");
    		return "redirect:/entry?examId="+examId;
    	}else if(null != session.getAttribute("courseId")){
        	session.setAttribute(Constant.SESSION_STUDENT, student);    
    		Integer courseId = (Integer)session.getAttribute("courseId");
    		session.removeAttribute("courseId");
    		return "redirect:/grade?courseId="+courseId;
    	}    	
    	return "redirect:/index";
    }

}
