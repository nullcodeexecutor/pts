package cn.pzhu.jsj.pts.controller.teacher;

import java.io.File;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.pzhu.jsj.pts.bean.ReadExcelStudent;
import cn.pzhu.jsj.pts.controller.BaseController;
import cn.pzhu.jsj.pts.domain.Student;
import cn.pzhu.jsj.pts.service.StudentService;

@Controller("teacher_StudentController")
@RequestMapping(value = "/teacher/student/")
public class StudentController extends BaseController{
	@Autowired
	private StudentService studentService;
	@Autowired
	private ReadExcelStudent readExcelStudent;

    @RequestMapping("/index")
    public String index(Model model){
        return "teacher/student";
    }

    @RequestMapping("/query")
    public void query(Model model,
                      @RequestParam(value = "courseId", defaultValue="-1") Integer courseId,
                      @RequestParam(value = "page") Integer page,
                      @RequestParam(value = "rows") Integer pageSize,
                      @RequestParam(value = "account", required=false) String account,
                      @RequestParam(value = "name", required=false) String name,
                      @RequestParam(value = "className", required=false) String className,
                      @RequestParam(value = "departName", required=false) String departName){
    	model.addAttribute("rows", studentService.find(courseId, account, name, className, departName, page, pageSize));
    	model.addAttribute("total", studentService.findTotal(courseId, account, name, className, departName));
    }
    
    @RequestMapping(value="/insert", method=RequestMethod.POST)
    public void insert(Model model,
                      @RequestParam(value = "courseId", defaultValue="-1") Integer courseId,
                      @RequestParam(value = "name") String name,
                      @RequestParam(value = "account") String account,
                      @RequestParam(value = "pwd") String pwd,
                      @RequestParam(value = "className") String className,
                      @RequestParam(value = "departName") String departName){
    	if(courseId == -1){
    		model.addAttribute("flg", false);
    		return;
    	}
    	if(studentService.findByAccountAndCourseId(account, courseId) != null){
        	model.addAttribute("flg", false);
        	model.addAttribute("msg", "该学生已经存在");
    		return;
    	}
    	Student student = new Student();
    	student.setCourseId(courseId);
    	student.setName(name);
    	student.setAccount(account);
    	student.setPwd(pwd);
    	student.setClassName(className);
    	student.setDepartName(departName);
    	
    	model.addAttribute("flg", studentService.insert(student));
    }
    
    @RequestMapping(value="/modify", method=RequestMethod.POST)
    public void modify(Model model,
                      @RequestParam(value = "id") Integer id,
                      @RequestParam(value = "name") String name,
                      @RequestParam(value = "pwd") String pwd,
                      @RequestParam(value = "className") String className,
                      @RequestParam(value = "departName") String departName){

    	Student student = new Student();
    	student.setId(id);
    	student.setName(name);
    	student.setPwd(pwd);
    	student.setClassName(className);
    	student.setDepartName(departName);
    	
    	model.addAttribute("flg", studentService.update(student));
    }
    
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public void delete(Model model, @RequestParam(value = "id") Integer id){    	
    	model.addAttribute("flg", studentService.deleteById(id));
    }

    @RequestMapping(value="/uploadXls")
    public String uploadXls(Model model,
						@RequestParam(value = "xls") MultipartFile file,
                        @RequestParam(value="courseId", defaultValue="-1")Integer courseId){
    	if(courseId<=0){
            model.addAttribute("uploadmsg", "导入失败，未选择课程");
            return "teacher/student";
    	}
        File targetFile = new File(readExcelStudent.getXlsPath()+file.getName());
        if(targetFile.exists() && targetFile.isFile()){
        	targetFile.delete();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Student> students = null;
		try {
			students = readExcelStudent.readStudent(targetFile);
		} catch (Exception e) {
	        readExcelStudent.delete(targetFile);    
	        model.addAttribute("uploadmsg", "读取学生信息失败");
	        return "teacher/student";
		}
        readExcelStudent.delete(targetFile);        
        for(Student student : students){
        	student.setCourseId(courseId);
        }        
        String msg = studentService.execBatchInsert(students);
        model.addAttribute("uploadmsg", msg);
        return "teacher/student";
    }
    
}
