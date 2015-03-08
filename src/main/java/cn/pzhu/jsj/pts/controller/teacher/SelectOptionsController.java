package cn.pzhu.jsj.pts.controller.teacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.pzhu.jsj.pts.controller.BaseController;
import cn.pzhu.jsj.pts.domain.SelectOption;
import cn.pzhu.jsj.pts.service.SelectOptionService;
import cn.pzhu.jsj.pts.service.SelectQuestionService;

@Controller("teacher_SelectOptionsController")
@RequestMapping(value = "/teacher/selectOptions/")
public class SelectOptionsController extends BaseController{
	@Autowired
	private SelectOptionService selectOptionService;
    
    @RequestMapping("/query")
    public void query(Model model,
            @RequestParam(value = "selectQuestionId") Integer selectQuestionId,
            @RequestParam(value = "page", required=false) Integer page,
            @RequestParam(value = "rows", required=false) Integer rows){
    	List<SelectOption> options = selectOptionService.findBySelectQuestionId(selectQuestionId);
    	model.addAttribute("rows", options);
    	model.addAttribute("total", options.size());
    }
    
    @RequestMapping("/insert")
    public void insert(Model model,
            @RequestParam(value = "selectQuestionId") Integer selectQuestionId,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "value") String value){
    	SelectOption selectOption = new SelectOption();
    	selectOption.setSelectQuestionId(selectQuestionId);
    	selectOption.setContent(content);
    	selectOption.setValue(value);
    	model.addAttribute("flg", selectOptionService.insert(selectOption));
    }
    
    @RequestMapping("/delete")
    public void delete(Model model,
            @RequestParam(value = "id") Integer id){
    	model.addAttribute("flg", selectOptionService.deleteById(id));
    }
    
    @RequestMapping("/modify")
    public void modify(Model model,
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "value") String value){
    	SelectOption selectOption = new SelectOption();
    	selectOption.setId(id);
    	selectOption.setContent(content);
    	selectOption.setValue(value);
    	model.addAttribute("flg", selectOptionService.update(selectOption));
    }
    
}
