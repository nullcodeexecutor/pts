package cn.pzhu.jsj.pts.controller.teacher;

import cn.pzhu.jsj.pts.controller.BaseController;
import cn.pzhu.jsj.pts.domain.Course;
import cn.pzhu.jsj.pts.domain.Teacher;
import cn.pzhu.jsj.pts.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;

@Controller("teacher_CourseController")
@RequestMapping(value = "/teacher/course/")
public class CourseController extends BaseController{
    @Autowired
    private CourseService courseService;

    @RequestMapping("/index")
    public String index(Model model){
        return "teacher/course";
    }
    
    @RequestMapping("/queryAll")
    public void queryAll(Model model){
        Teacher teacher = (Teacher)getRoleUser();
        model.addAttribute("courses",courseService.findByTeacherId(teacher.getId(), 1, Integer.MAX_VALUE));
    }

    @RequestMapping("/query")
    public void query(Model model,
                      @RequestParam(value = "page") Integer page,
                      @RequestParam(value = "rows") Integer pageSize){
        Teacher teacher = (Teacher)getRoleUser();
        model.addAttribute("rows",courseService.findByTeacherId(teacher.getId(), page, pageSize));
        model.addAttribute("total", courseService.findTotalByTeacherId(teacher.getId()));
    }
    
    @RequestMapping(value="/modify", method=RequestMethod.POST)
    public void modify(Model model,
                      @RequestParam(value = "id") Integer id,
                      @RequestParam(value = "name") String name,
                      @RequestParam(value = "description") String description){
        Teacher teacher = (Teacher)getRoleUser();
        Course course = new Course();
        course.setId(id);
        course.setName(name);
        course.setDescription(description);
        course.setTeacherId(teacher.getId());
        
        if(courseService.modify(course)){
            model.addAttribute("flg", true);
            model.addAttribute("msg", "更新成功");
        }else{
            model.addAttribute("flg", false);
            model.addAttribute("msg", "更新失败");
        }
    }
    
    @RequestMapping(value="/insert", method=RequestMethod.POST)
    public void insert(Model model,
                      @RequestParam(value = "name") String name,
                      @RequestParam(value = "description", required=false) String description){
        Teacher teacher = (Teacher)getRoleUser();
        Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        course.setTeacherId(teacher.getId());
        
        if(courseService.insert(course)){
            model.addAttribute("flg", true);
            model.addAttribute("msg", "新增课程成功");
        }else{
            model.addAttribute("flg", false);
            model.addAttribute("msg", "新增课程失败");
        }
    }
    
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public void delete(Model model,
            		  @RequestParam(value = "id") Integer id){
        Teacher teacher = (Teacher)getRoleUser();
        if(courseService.delete(id, teacher.getId())){
            model.addAttribute("flg", true);
            model.addAttribute("msg", "删除课程成功");
        }else{
            model.addAttribute("flg", false);
            model.addAttribute("msg", "删除课程失败");
        }
    }

}
