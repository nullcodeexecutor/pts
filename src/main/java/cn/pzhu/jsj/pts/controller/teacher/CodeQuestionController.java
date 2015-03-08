package cn.pzhu.jsj.pts.controller.teacher;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.pzhu.jsj.pts.controller.BaseController;
import cn.pzhu.jsj.pts.domain.CodeQuestion;
import cn.pzhu.jsj.pts.domain.SelectQuestion;
import cn.pzhu.jsj.pts.domain.Teacher;
import cn.pzhu.jsj.pts.domain.TfQuestion;
import cn.pzhu.jsj.pts.service.CodeQuestionService;
import cn.pzhu.jsj.pts.service.SelectQuestionService;
import cn.pzhu.jsj.pts.service.TfQuestionService;

@Controller("teacher_CodeQuestionController")
@RequestMapping(value = "/teacher/codeQuestion/")
public class CodeQuestionController extends BaseController{
	@Autowired
	private CodeQuestionService codeQuestionService;

    @RequestMapping("/index")
    public String index(Model model){
        return "teacher/codeQuestion";
    }
    
    @RequestMapping("/codeQuestionInfo")
    public String codeQuestionInfo(Model model,
            @RequestParam(value = "id", required=false) Integer id){
    	if(id != null){
    		model.addAttribute("codeQuestionId", id);
    	}
        return "teacher/codeQuestionInfo";
    }
    
    @RequestMapping("/queryById")
    public void queryById(Model model,
            @RequestParam(value = "id") Integer id){
		model.addAttribute("codeQuestion", codeQuestionService.findById(id));
    }

    @RequestMapping("/queryQuestion")
    public void query(Model model,
            @RequestParam(value = "selectQuestionId") Integer selectQuestionId){
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
    	model.addAttribute("rows", codeQuestionService.findCodeQuestionDto(languageType, difficultyType, startTime, endTime, teacherId, page, pageSize));
    	model.addAttribute("total", codeQuestionService.findCountCodeQuestionDto(languageType, difficultyType, startTime, endTime, teacherId));
    }
    
    @RequestMapping("/insert")
    public void insert(Model model,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "code") String code,
            @RequestParam(value = "languageType") Integer languageType,
            @RequestParam(value = "difficultyType") Integer difficultyType,
            @RequestParam(value = "createTime") Long createTime){    
    	Teacher teacher = (Teacher)getRoleUser();
    	
    	CodeQuestion codeQuestion = new CodeQuestion();
    	codeQuestion.setTeacherId(teacher.getId());
    	codeQuestion.setContent(content);
    	codeQuestion.setCode(code);
    	codeQuestion.setLanguageType(languageType);
    	codeQuestion.setDifficultyType(difficultyType);
    	codeQuestion.setCreateTime(new Date(createTime));
    	
    	model.addAttribute("flg", codeQuestionService.insert(codeQuestion));
    }
    
    @RequestMapping("/delete")
    public void delete(Model model,
            @RequestParam(value = "id") Integer id){
    	model.addAttribute("flg", codeQuestionService.deleteById(id));
    }
    
    @RequestMapping("/modify")
    public void modify(Model model,
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "code") String code,
            @RequestParam(value = "languageType") Integer languageType,
            @RequestParam(value = "difficultyType") Integer difficultyType,
            @RequestParam(value = "createTime") Long createTime){
    	CodeQuestion codeQuestion = new CodeQuestion();
    	codeQuestion.setId(id);
    	codeQuestion.setContent(content);
    	codeQuestion.setCode(code);
    	codeQuestion.setLanguageType(languageType);
    	codeQuestion.setDifficultyType(difficultyType);
    	codeQuestion.setCreateTime(new Date(createTime));
    	model.addAttribute("flg", codeQuestionService.update(codeQuestion));
    }
    
}
