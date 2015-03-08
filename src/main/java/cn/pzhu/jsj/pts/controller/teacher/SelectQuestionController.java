package cn.pzhu.jsj.pts.controller.teacher;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.pzhu.jsj.pts.controller.BaseController;
import cn.pzhu.jsj.pts.domain.SelectQuestion;
import cn.pzhu.jsj.pts.domain.Teacher;
import cn.pzhu.jsj.pts.domain.TfQuestion;
import cn.pzhu.jsj.pts.service.SelectQuestionService;
import cn.pzhu.jsj.pts.service.TfQuestionService;

@Controller("teacher_SelectQuestionController")
@RequestMapping(value = "/teacher/selectQuestion/")
public class SelectQuestionController extends BaseController{
	@Autowired
	private SelectQuestionService selectQuestionService;

    @RequestMapping("/index")
    public String index(Model model){
        return "teacher/selectQuestion";
    }

    @RequestMapping("/queryQuestion")
    public void query(Model model,
            @RequestParam(value = "selectQuestionId") Integer selectQuestionId){
    	model.addAttribute("selectQuestion", selectQuestionService.findById(selectQuestionId));
    }
    
    @RequestMapping("/query")
    public void query(Model model,
            @RequestParam(value = "languageType", required=false) Integer languageType,
            @RequestParam(value = "difficultyType", required=false) Integer difficultyType,
            @RequestParam(value = "startTime", required=false) Long startTime,
            @RequestParam(value = "endTime", required=false) Long endTime,
            @RequestParam(value = "creator", required=false) Integer flg,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer pageSize){
    	Integer teacherId = null;
    	if(flg != null && flg == 0){
    		teacherId = ((Teacher)getRoleUser()).getId();
    	}
    	model.addAttribute("rows", selectQuestionService.findSelectQuestionDto(languageType, difficultyType, startTime, endTime, teacherId, page, pageSize));
    	model.addAttribute("total", selectQuestionService.findCountSelectQuestionDto(languageType, difficultyType, startTime, endTime, teacherId));
    }
    
    @RequestMapping("/insert")
    public void insert(Model model,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "answer") String answer,
            @RequestParam(value = "languageType") Integer languageType,
            @RequestParam(value = "difficultyType") Integer difficultyType,
            @RequestParam(value = "createTime") Long createTime){
    	Teacher teacher = (Teacher)getRoleUser();
    	SelectQuestion selectQuestion = new SelectQuestion();
    	selectQuestion.setContent(content);
    	selectQuestion.setAnswer(answer);
    	selectQuestion.setCreateTime(new Date(createTime));
    	selectQuestion.setDifficultyType(difficultyType);
    	selectQuestion.setLanguageType(languageType);
    	selectQuestion.setTeacherId(teacher.getId());
    	
    	model.addAttribute("flg", selectQuestionService.insert(selectQuestion));
    }
    
    @RequestMapping("/delete")
    public void delete(Model model,
            @RequestParam(value = "id") Integer id){
    	model.addAttribute("flg", selectQuestionService.deleteById(id));
    }
    
    @RequestMapping("/modify")
    public void modify(Model model,
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "answer") String answer,
            @RequestParam(value = "languageType") Integer languageType,
            @RequestParam(value = "difficultyType") Integer difficultyType,
            @RequestParam(value = "createTime") Long createTime){
    	SelectQuestion selectQuestion = new SelectQuestion();
    	selectQuestion.setId(id);
    	selectQuestion.setContent(content);
    	selectQuestion.setAnswer(answer);
    	selectQuestion.setCreateTime(new Date(createTime));
    	selectQuestion.setDifficultyType(difficultyType);
    	selectQuestion.setLanguageType(languageType);
    	model.addAttribute("flg", selectQuestionService.update(selectQuestion));
    }
    
}
