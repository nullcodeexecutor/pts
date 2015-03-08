package cn.pzhu.jsj.pts.controller.teacher;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.pzhu.jsj.pts.bean.Answer;
import cn.pzhu.jsj.pts.bean.program.CompileAndRun;
import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.controller.BaseController;
import cn.pzhu.jsj.pts.domain.CodeQuestion;
import cn.pzhu.jsj.pts.domain.ExamPaper;
import cn.pzhu.jsj.pts.domain.TfQuestion;
import cn.pzhu.jsj.pts.dto.ExamDto;
import cn.pzhu.jsj.pts.dto.SelectQuestionDto;
import cn.pzhu.jsj.pts.service.CodeAnswerService;
import cn.pzhu.jsj.pts.service.CodeQuestionService;
import cn.pzhu.jsj.pts.service.ExamPaperService;
import cn.pzhu.jsj.pts.service.ExamService;
import cn.pzhu.jsj.pts.service.SelectQuestionService;
import cn.pzhu.jsj.pts.service.TfQuestionService;

@Controller("teacher_PreviewController")
@RequestMapping(value = "/teacher/preview/")
public class PreviewController extends BaseController{
	@Autowired
	private ExamPaperService examPaperService;
	@Autowired
	private ExamService examService;
    @Autowired
    private TfQuestionService tfQuestionService;
    @Autowired
    private SelectQuestionService selectQuestionService;
    @Autowired
    private CodeQuestionService codeQuestionService;
    
    private CompileAndRun getCCompileAndRun(){
    	ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
    	return (CompileAndRun) ac.getBean("cCompileAndRun");
    }
    
    private CompileAndRun getJavaCompileAndRun(){
    	ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
    	return (CompileAndRun) ac.getBean("javaCompileAndRun");
    }
    
    @RequestMapping("/index/{examPaperId}")
    public String preview(Model model, @PathVariable("examPaperId")Integer examPaperId){
    	session.setAttribute("preview_examPaperId", examPaperId);
    	ExamPaper examPaper = examPaperService.findById(examPaperId);
    	ExamDto examDto = examService.findExamDtoById(examPaper.getExamId());
    	model.addAttribute("exam", examDto);
    	return "teacher/preview";
    }
    
    @RequestMapping("/queryTFQuestion")
    public void queryTFQuestion(Model model){
    	Integer examPaperId = (Integer)session.getAttribute("preview_examPaperId");
    	List<TfQuestion> tfQuestions = tfQuestionService.findByPaperId(examPaperId);
    	model.addAttribute("tfQuestions", tfQuestions);
    }
    
    @RequestMapping("/querySelectQuestion")
    public void querySelectQuestion(Model model){
    	Integer examPaperId = (Integer)session.getAttribute("preview_examPaperId");
    	List<SelectQuestionDto> selectQuestionDtos = selectQuestionService.findSelectQuestionDtoByPaperId(examPaperId);
    	model.addAttribute("selectQuestions", selectQuestionDtos);
    }
    
    @RequestMapping("/queryCodeQuestion")
    public void queryCodeQuestion(Model model){
    	Integer examPaperId = (Integer)session.getAttribute("preview_examPaperId");
    	List<CodeQuestion> codeQuestions = codeQuestionService.findByPaperId(examPaperId);    	
    	model.addAttribute("codeQuestions", codeQuestions);
    }
    
    @RequestMapping("/run")
    public void run(Model model,
    		@RequestParam(value="code") String code,
    		@RequestParam(value="languageType") Integer languageType,
    		@RequestParam(value="input") String input){
    	if(languageType == Constant.COMPILER_TYPE_C){
    		model.addAttribute("result", getCCompileAndRun().exec(code, input));
    	}
    	if(languageType == Constant.COMPILER_TYPE_JAVA){
    		model.addAttribute("result", getJavaCompileAndRun().exec(code, input));
    	}
    }
    
    
}
