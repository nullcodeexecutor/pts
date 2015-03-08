package cn.pzhu.jsj.pts.controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.pzhu.jsj.pts.controller.BaseController;
import cn.pzhu.jsj.pts.service.CodeAnswerService;
import cn.pzhu.jsj.pts.service.CodeQuestionService;
import cn.pzhu.jsj.pts.service.ExamPaperService;
import cn.pzhu.jsj.pts.service.ExamService;

@Controller("teacher_ExamPaperController")
@RequestMapping(value = "/teacher/examPaper/")
public class ExamPaperController extends BaseController{
	@Autowired
	private ExamPaperService examPaperService;
	@Autowired
	private ExamService examService;
    
    @RequestMapping("/index")
    public String index(Model model){
    	return "teacher/examPaper";
    }
    
    @RequestMapping("/query")
    public void query(Model model,
            @RequestParam(value = "examId") Integer examId,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer pageSize){
    	model.addAttribute("rows", examPaperService.findByExamId(examId, page, pageSize));
    	model.addAttribute("total", examPaperService.findCountByExamId(examId));
    }
    
    @RequestMapping("/delete")
    public void delete(Model model,
            @RequestParam(value = "id") Integer id){
    	model.addAttribute("flg", examPaperService.deleteById(id));
    }
    
}
