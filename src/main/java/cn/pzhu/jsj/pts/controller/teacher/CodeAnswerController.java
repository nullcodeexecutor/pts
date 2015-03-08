package cn.pzhu.jsj.pts.controller.teacher;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.pzhu.jsj.pts.controller.BaseController;
import cn.pzhu.jsj.pts.domain.CodeAnswer;
import cn.pzhu.jsj.pts.domain.CodeQuestion;
import cn.pzhu.jsj.pts.domain.SelectQuestion;
import cn.pzhu.jsj.pts.domain.Teacher;
import cn.pzhu.jsj.pts.domain.TfQuestion;
import cn.pzhu.jsj.pts.service.CodeAnswerService;
import cn.pzhu.jsj.pts.service.CodeQuestionService;
import cn.pzhu.jsj.pts.service.SelectQuestionService;
import cn.pzhu.jsj.pts.service.TfQuestionService;

@Controller("teacher_CodeAnswerController")
@RequestMapping(value = "/teacher/codeAnswer/")
public class CodeAnswerController extends BaseController{
	@Autowired
	private CodeQuestionService codeQuestionService;
	@Autowired
	private CodeAnswerService codeAnswerService;
    
    @RequestMapping("/query")
    public void query(Model model,
            @RequestParam(value = "codeQuestionId") Integer codeQuestionId){
		model.addAttribute("codeAnswers", codeAnswerService.findByCodeQuestionId(codeQuestionId));
    }
    
    @RequestMapping("/insert")
    public void insert(Model model,
            @RequestParam(value = "codeQuestionId") Integer codeQuestionId,
            @RequestParam(value = "inputData") String inputData,
            @RequestParam(value = "outputData") String outputData){    
    	CodeAnswer codeAnswer = new CodeAnswer();
    	codeAnswer.setInputData(inputData);
    	codeAnswer.setOutputData(outputData);
    	codeAnswer.setQuestionId(codeQuestionId);
    	
    	model.addAttribute("flg", codeAnswerService.insert(codeAnswer));
    }
    
    @RequestMapping("/delete")
    public void delete(Model model,
            @RequestParam(value = "id") Integer id){
    	model.addAttribute("flg", codeAnswerService.deleteById(id));
    }
    
    @RequestMapping("/modify")
    public void modify(Model model,
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "inputData") String inputData,
            @RequestParam(value = "outputData") String outputData){
    	CodeAnswer codeAnswer = new CodeAnswer();
    	codeAnswer.setInputData(inputData);
    	codeAnswer.setOutputData(outputData);
    	codeAnswer.setId(id);
    	model.addAttribute("flg", codeAnswerService.update(codeAnswer));
    }
    
}
