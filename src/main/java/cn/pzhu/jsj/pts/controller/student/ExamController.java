package cn.pzhu.jsj.pts.controller.student;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.pzhu.jsj.pts.bean.Answer;
import cn.pzhu.jsj.pts.bean.program.CompileAndRun;
import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.controller.BaseController;
import cn.pzhu.jsj.pts.domain.CodeQuestion;
import cn.pzhu.jsj.pts.domain.Exam;
import cn.pzhu.jsj.pts.domain.ExamPaper;
import cn.pzhu.jsj.pts.domain.Grade;
import cn.pzhu.jsj.pts.domain.PaperInfo;
import cn.pzhu.jsj.pts.domain.SelectQuestion;
import cn.pzhu.jsj.pts.domain.TfQuestion;
import cn.pzhu.jsj.pts.dto.ExamDto;
import cn.pzhu.jsj.pts.dto.SelectQuestionDto;
import cn.pzhu.jsj.pts.service.CodeAnswerService;
import cn.pzhu.jsj.pts.service.CodeQuestionService;
import cn.pzhu.jsj.pts.service.ExamPaperService;
import cn.pzhu.jsj.pts.service.ExamService;
import cn.pzhu.jsj.pts.service.GradeService;
import cn.pzhu.jsj.pts.service.PaperInfoService;
import cn.pzhu.jsj.pts.service.SelectQuestionService;
import cn.pzhu.jsj.pts.service.TfQuestionService;

@Controller("student_ExamController")
@RequestMapping(value = "/student/exam/")
public class ExamController extends BaseController{
    @Autowired
    private ExamService examService;
    @Autowired
    private TfQuestionService tfQuestionService;
    @Autowired
    private SelectQuestionService selectQuestionService;
    @Autowired
    private CodeQuestionService codeQuestionService;
    @Autowired
    private ExamPaperService examPaperService;
    @Autowired
    private PaperInfoService paperInfoService;
    @Autowired
    private CodeAnswerService codeAnswerService;
    @Autowired
    private GradeService gradeService;
    
    private CompileAndRun getCCompileAndRun(){
    	ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
    	return (CompileAndRun) ac.getBean("cCompileAndRun");
    }
    
    private CompileAndRun getJavaCompileAndRun(){
    	ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
    	return (CompileAndRun) ac.getBean("javaCompileAndRun");
    }
    
    private boolean timeIsCorrect(Answer answer){
    	long nowTime = new Date().getTime();
    	if(nowTime<answer.getEndTime() && nowTime>answer.getStartTime()){
    		return true;
    	}
    	return false;
    }

    @RequestMapping("/start")
    public void start(Model model){
    	if(session.getAttribute(Constant.SESSION_ANSWER) == null){
        	if(session.getAttribute("examId") == null){
        		model.addAttribute("flg", false);
        		model.addAttribute("msg", "请重新登录");
        		return;
        	}
        	Integer examId = (Integer) session.getAttribute("examId");
        	Exam exam = examService.findById(examId);   	
          	
        	if(exam.getCourseId() != getStudent().getCourseId()){
        		model.addAttribute("flg", false);
        		model.addAttribute("msg", "请重新登录");
        		return;
        	}
        	
        	if(exam.getType() == Constant.EXAM_TYPE_EXAM && gradeService.findByStudentIdAndExamId(getStudent().getId(), examId).size()>0){
        		model.addAttribute("flg", false);
        		model.addAttribute("msg", "你已经进行过该考试");
        		return;
        	}      
        	
        	Answer answer = new Answer(getStudent().getId(), examId);
        	//随机选择一张试卷
        	if(answer.getPaperId() == null || 0 == answer.getPaperId()){
    	    	ExamPaper examPaper = examPaperService.findRandomByExamId(answer.getExamId());
    	    	if(examPaper == null){
    	    		model.addAttribute("flg", false);
            		model.addAttribute("msg", "没有试卷");
            		return;
    	    	}
    	    	answer.setPaperId(examPaper.getId());
        	}
        	long startTime = exam.getStartTime().getTime();
        	long endTime = exam.getEndTime().getTime();
        	
        	answer.setStartTime(startTime);
        	answer.setEndTime(endTime);
        	long nowTime = new Date().getTime();
        	
        	if(nowTime<answer.getStartTime()){
        		model.addAttribute("flg", false);
        		model.addAttribute("msg", "考试还没有开始");
        		return;        		
        	}
        	
        	if(nowTime>answer.getEndTime()){
        		model.addAttribute("flg", false);
        		model.addAttribute("msg", "考试已经结束");
        		return;
        	}
        	
        	session.setAttribute(Constant.SESSION_ANSWER, answer);
    	}
    	
    	Answer answer = (Answer)session.getAttribute(Constant.SESSION_ANSWER);    	
    	long nowTime = new Date().getTime();    	
    	
		model.addAttribute("flg", true);
        model.addAttribute("startTime", answer.getStartTime());  
        model.addAttribute("endTime", answer.getEndTime());  	
        model.addAttribute("nowTime", nowTime);  	
    }
    
    @RequestMapping("/index")
    public String index(Model model, HttpServletResponse response)throws IOException{
    	clear();
    	if(session.getAttribute("examId") == null){
    		return "redirect:/index";
    	}
    	Integer examId = (Integer) session.getAttribute("examId");
    	ExamDto examDto = examService.findExamDtoById(examId);
    	if(examDto.getStatus() == Constant.EXAM_STATUS_END){
    		response.sendError(HttpServletResponse.SC_FORBIDDEN);
    	}
    	model.addAttribute("exam", examDto);
        return "student/exam";
    }
    
    @RequestMapping("/queryTFQuestion")
    public void queryTFQuestion(Model model){
    	Answer answer = (Answer)session.getAttribute(Constant.SESSION_ANSWER);
    	if(!timeIsCorrect(answer)){
    		return;
    	}
    	List<TfQuestion> tfQuestions = tfQuestionService.findByPaperId(answer.getPaperId());
    	
    	for(TfQuestion tfQuestion : tfQuestions){
    		answer.addTFAnswer(tfQuestion.getId());
    	}
    	
    	model.addAttribute("tfQuestions", tfQuestions);
    }
    
    @RequestMapping("/querySelectQuestion")
    public void querySelectQuestion(Model model){
    	Answer answer = (Answer)session.getAttribute(Constant.SESSION_ANSWER);
    	if(!timeIsCorrect(answer)){
    		return;
    	}
    	List<SelectQuestionDto> selectQuestionDtos = selectQuestionService.findSelectQuestionDtoByPaperId(answer.getPaperId());
    	
    	for(SelectQuestionDto dto : selectQuestionDtos){
    		answer.addSelectAnswer(dto.getId());
    	}
    	
    	model.addAttribute("selectQuestions", selectQuestionDtos);
    }
    
    @RequestMapping("/queryCodeQuestion")
    public void queryCodeQuestion(Model model){
    	Answer answer = (Answer)session.getAttribute(Constant.SESSION_ANSWER);
    	if(!timeIsCorrect(answer)){
    		return;
    	}
    	List<CodeQuestion> codeQuestions = codeQuestionService.findByPaperId(answer.getPaperId());
    	
    	for(CodeQuestion codeQuestion : codeQuestions){
    		answer.addCodeAnswer(codeQuestion.getId());
    	}
    	
    	model.addAttribute("codeQuestions", codeQuestions);
    }
    
    @RequestMapping("/run")
    public void run(Model model,
    		@RequestParam(value="code") String code,
    		@RequestParam(value="languageType") Integer languageType,
    		@RequestParam(value="input") String input){
    	Answer answer = (Answer)session.getAttribute(Constant.SESSION_ANSWER);
    	if(!timeIsCorrect(answer)){
    		return;
    	}
    	if(languageType == Constant.COMPILER_TYPE_C){
    		model.addAttribute("result", getCCompileAndRun().exec(code, input));
    	}
    	if(languageType == Constant.COMPILER_TYPE_JAVA){
    		model.addAttribute("result", getJavaCompileAndRun().exec(code, input));
    	}
    }
    
    @RequestMapping("/submitAnwser")
    public void submitAnwser(Model model,
    		@RequestParam(value="name") String name,
    		@RequestParam(value="value") String value){
    	Answer answer = (Answer)session.getAttribute(Constant.SESSION_ANSWER);    	
    	if(!timeIsCorrect(answer)){
    		return;
    	}
    	String[] typeAndId = name.split("_");
		Integer questionId = Integer.parseInt(typeAndId[1]);
    	if(typeAndId[0].equals("select")){
    		SelectQuestion selectQuestion = selectQuestionService.findById(questionId);
    		PaperInfo paperInfo = paperInfoService.find(Constant.QUESTION_TYPE_SELECT, questionId, answer.getPaperId());
    		answer.setSelectAnswer(questionId, selectQuestion.getAnswer().equals(value), paperInfo.getScore());    		
    	}else if(typeAndId[0].equals("tf")){
    		TfQuestion tfQuestion = tfQuestionService.findById(questionId);
    		PaperInfo paperInfo = paperInfoService.find(Constant.QUESTION_TYPE_TF, questionId, answer.getPaperId());
    		int tfValue = Integer.parseInt(value);
    		answer.setTfAnswer(questionId, tfQuestion.getAnswer() == tfValue, paperInfo.getScore());    		
    	}
    }
    
    @RequestMapping("/isComplete")
    public void isComplete(Model model){
    	Answer answer = (Answer)session.getAttribute(Constant.SESSION_ANSWER);  
    	if(!timeIsCorrect(answer)){
    		return;
    	}
		model.addAttribute("flg", answer.isComplete());    	
    }
    
    @RequestMapping("/submitExam")
    public String submitExam(Model model){
    	Answer answer = (Answer)session.getAttribute(Constant.SESSION_ANSWER);  
    	if(!timeIsCorrect(answer)){
    		return "pts/index";
    	}
    	Grade grade = new Grade();
    	grade.setExamId(answer.getExamId());
    	grade.setPaperId(answer.getPaperId());
    	grade.setScore(answer.getScore());
    	grade.setStudentId(getStudent().getId());
    	grade.setSubmitTime(new Date());
    	
    	gradeService.insert(grade, getStudent().getId());
    	
    	model.addAttribute("score", answer.getScore());    	

    	session.removeAttribute(Constant.SESSION_ANSWER);
    	session.removeAttribute("examId");
    	
		return "redirect:/student/grade/index";
    }
    
    
    @RequestMapping("/submitCodeAnwser")
    public void submitCodeAnwser(Model model,
    		@RequestParam(value="languageType") Integer languageType,
    		@RequestParam(value="questionId") Integer questionId,
    		@RequestParam(value="code") String code){ 
    	Answer answer = (Answer)session.getAttribute(Constant.SESSION_ANSWER);
    	if(!timeIsCorrect(answer)){
    		return;
    	}
    	CodeQuestion codeQuestion = codeQuestionService.findById(questionId);
    	if(codeQuestion.getLanguageType() != Constant.COMPILER_TYPE_ANY && codeQuestion.getLanguageType() != languageType){
    		model.addAttribute("flg", false);
    		return;
    	}
		PaperInfo paperInfo = paperInfoService.find(Constant.QUESTION_TYPE_CODE, questionId, answer.getPaperId());
		Map<String, String> inputAndOutput = codeAnswerService.findInputOutputByCodeQuestionId(questionId);
		
    	if(languageType == Constant.COMPILER_TYPE_C){
    		boolean isCorrect = getCCompileAndRun().exec(code, inputAndOutput);
    		answer.setCodeAnswer(questionId, isCorrect, paperInfo.getScore());
    		model.addAttribute("flg", isCorrect);
    		return;
    	}
    	if(languageType == Constant.COMPILER_TYPE_JAVA){
    		boolean isCorrect = getJavaCompileAndRun().exec(code, inputAndOutput);
			answer.setCodeAnswer(questionId, isCorrect, paperInfo.getScore());
			model.addAttribute("flg", isCorrect);
    	}
    }


}
