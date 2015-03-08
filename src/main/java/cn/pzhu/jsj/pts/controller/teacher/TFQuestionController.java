package cn.pzhu.jsj.pts.controller.teacher;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.pzhu.jsj.pts.controller.BaseController;
import cn.pzhu.jsj.pts.domain.Teacher;
import cn.pzhu.jsj.pts.domain.TfQuestion;
import cn.pzhu.jsj.pts.service.TfQuestionService;

@Controller("teacher_TFQuestionController")
@RequestMapping(value = "/teacher/tfQuestion/")
public class TFQuestionController extends BaseController{
	@Autowired
	private TfQuestionService tfQuestionService;

    @RequestMapping("/index")
    public String index(Model model){
        return "teacher/tfQuestion";
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
    	model.addAttribute("rows", tfQuestionService.findTFQuestionDto(languageType, difficultyType, startTime, endTime, teacherId, page, pageSize));
    	model.addAttribute("total", tfQuestionService.findCountTFQuestionDto(languageType, difficultyType, startTime, endTime, teacherId));
    }
    
    @RequestMapping("/insert")
    public void insert(Model model,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "answer") Integer answer,
            @RequestParam(value = "languageType") Integer languageType,
            @RequestParam(value = "difficultyType") Integer difficultyType,
            @RequestParam(value = "createTime") Long createTime){
    	Teacher teacher = (Teacher)getRoleUser();
    	TfQuestion tfQuestion = new TfQuestion();
    	tfQuestion.setAnswer(answer);
    	tfQuestion.setContent(content);
    	tfQuestion.setCreateTime(new Date(createTime));
    	tfQuestion.setDifficultyType(difficultyType);
    	tfQuestion.setLanguageType(languageType);
    	tfQuestion.setTeacherId(teacher.getId());
    	model.addAttribute("flg", tfQuestionService.insert(tfQuestion));
    }
    
    @RequestMapping("/delete")
    public void delete(Model model,
            @RequestParam(value = "id") Integer id){
    	model.addAttribute("flg", tfQuestionService.delete(id));
    }
    
    @RequestMapping("/modify")
    public void modify(Model model,
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "answer") Integer answer,
            @RequestParam(value = "languageType") Integer languageType,
            @RequestParam(value = "difficultyType") Integer difficultyType,
            @RequestParam(value = "createTime") Long createTime){
    	TfQuestion tfQuestion = new TfQuestion();
    	tfQuestion.setId(id);
    	tfQuestion.setAnswer(answer);
    	tfQuestion.setContent(content);
    	tfQuestion.setCreateTime(new Date(createTime));
    	tfQuestion.setDifficultyType(difficultyType);
    	tfQuestion.setLanguageType(languageType);
    	
    	model.addAttribute("flg", tfQuestionService.update(tfQuestion));
    }
    
}
