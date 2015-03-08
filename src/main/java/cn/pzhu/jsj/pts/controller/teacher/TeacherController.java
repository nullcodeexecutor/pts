package cn.pzhu.jsj.pts.controller.teacher;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.pzhu.jsj.pts.controller.BaseController;
import cn.pzhu.jsj.pts.domain.Teacher;
import cn.pzhu.jsj.pts.service.StudentService;
import cn.pzhu.jsj.pts.service.TeacherService;

@Controller("teacher_TeacherController")
@RequestMapping(value = "/teacher/teacher/")
public class TeacherController extends BaseController{
	@Autowired
	private TeacherService teacherService;

    @RequestMapping("/index")
    public String index(Model model){
        return "teacher/teacher";
    }

    @RequestMapping("/query")
    public void query(Model model){
    	Teacher teacher = (Teacher)getRoleUser();
    	List<Teacher> list = new ArrayList<Teacher>();
    	list.add(teacher);
    	model.addAttribute("rows", list);
    	model.addAttribute("total", 1);
    }
    
    @RequestMapping("/modify")
    public void modify(Model model,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "pwd") String pwd){
    	Teacher teacher = (Teacher)getRoleUser();
    	teacher.setName(name);    	
    	model.addAttribute("flg", teacherService.update(teacher, pwd));
    	
    	resetRoleUser();
    }

}
