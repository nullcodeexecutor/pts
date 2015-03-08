package cn.pzhu.jsj.pts.controller.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.pzhu.jsj.pts.controller.BaseController;
import cn.pzhu.jsj.pts.domain.Student;
import cn.pzhu.jsj.pts.dto.CourseDto;
import cn.pzhu.jsj.pts.dto.ExamGradeDto;
import cn.pzhu.jsj.pts.service.CourseService;
import cn.pzhu.jsj.pts.service.GradeService;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-28
 * Time: 下午3:29
 * To change this template use File | Settings | File Templates.
 */
@Controller("student_GradeController")
@RequestMapping(value = "/student/grade/")
public class GradeController extends BaseController{
	@Autowired
	private GradeService gradeService;
	@Autowired
	private CourseService courseService;

    @RequestMapping("/index")
    public String index(Model model){
    	clear();
    	Student student = getStudent();
    	List<ExamGradeDto> gradeDtos = gradeService.findByCourseId(student.getCourseId());
    	CourseDto courseDto = courseService.findCourseDtoByCourseId(student.getCourseId());
    	model.addAttribute("course", courseDto);
    	model.addAttribute("grades", gradeDtos);
        return "student/grade";
    }

}
