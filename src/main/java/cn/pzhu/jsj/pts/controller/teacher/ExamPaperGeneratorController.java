package cn.pzhu.jsj.pts.controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.pzhu.jsj.pts.controller.BaseController;
import cn.pzhu.jsj.pts.domain.Teacher;
import cn.pzhu.jsj.pts.dto.GeneratorPaperArgs;
import cn.pzhu.jsj.pts.service.ExamPaperGeneratorService;

@Controller("teacher_ExamPaperGeneratorController")
@RequestMapping(value = "/teacher/generatorPaper/")
public class ExamPaperGeneratorController extends BaseController {
	@Autowired
	private ExamPaperGeneratorService examPaperGeneratorService;
	
	@RequestMapping("/index")
    public String index(Model model){
        return "teacher/generatorPaper";
    }
	
	/**
	 *	languageType: questionArea.find("select option:selected").val(),
	 *	startTime: new Date(questionArea.find("input[name='startTime']").val()).getTime(),
	 *	endTime: new Date(questionArea.find("input[name='endTime']").val()).getTime(),
	 *	creator: questionArea.find("input[name='creator_"+id+"']:checked").val(),
	 *	score: questionArea.find("input[name='score']").val(),
	 *	questionNum: questionArea.find("input[name='questionNum']").val(),
	 *	easyNum: questionArea.find("input[name='easyNum']").val(),
	 *	generaNum: questionArea.find("input[name='generaNum']").val(),
	 *	diffcultyNum: questionArea.find("input[name='diffcultyNum']").val()
	 */
	@RequestMapping("/generate")
    public void generate(Model model,
            @RequestParam(value = "examId") Integer examId,
            @RequestParam(value = "examPaperNum") Integer examPaperNum,    
            
            @RequestParam(value = "select_languageType") Integer selectLanguageType,
            @RequestParam(value = "select_startTime") Long selectStartTime,
            @RequestParam(value = "select_endTime") Long selectEndTime,
            @RequestParam(value = "select_creator") Integer selectCreator,
            @RequestParam(value = "select_score") Integer selectScore,
            @RequestParam(value = "select_questionNum") Integer selectQuestionNum,
            @RequestParam(value = "select_easyNum") Integer selectEasyNum,
            @RequestParam(value = "select_generaNum") Integer selectGeneraNum,
            @RequestParam(value = "select_diffcultyNum") Integer selectDiffcultyNum,
            
            @RequestParam(value = "tf_languageType") Integer tfLanguageType,
            @RequestParam(value = "tf_startTime") Long tfStartTime,
            @RequestParam(value = "tf_endTime") Long tfEndTime,
            @RequestParam(value = "tf_creator") Integer tfCreator,
            @RequestParam(value = "tf_score") Integer tfScore,
            @RequestParam(value = "tf_questionNum") Integer tfQuestionNum,
            @RequestParam(value = "tf_easyNum") Integer tfEasyNum,
            @RequestParam(value = "tf_generaNum") Integer tfGeneraNum,
            @RequestParam(value = "tf_diffcultyNum") Integer tfDiffcultyNum,
            
            @RequestParam(value = "code_languageType") Integer codeLanguageType,
            @RequestParam(value = "code_startTime") Long codeStartTime,
            @RequestParam(value = "code_endTime") Long codeEndTime,
            @RequestParam(value = "code_creator") Integer codeCreator,
            @RequestParam(value = "code_score") Integer codeScore,
            @RequestParam(value = "code_questionNum") Integer codeQuestionNum,
            @RequestParam(value = "code_easyNum") Integer codeEasyNum,
            @RequestParam(value = "code_generaNum") Integer codeGeneraNum,
            @RequestParam(value = "code_diffcultyNum") Integer codeDiffcultyNum){
		Integer teacherId = ((Teacher)getRoleUser()).getId();
		GeneratorPaperArgs selectGeneratorPaperArgs = new GeneratorPaperArgs(selectLanguageType, 
														selectStartTime, selectEndTime, selectCreator, selectScore, selectQuestionNum,
														selectEasyNum, selectGeneraNum, selectDiffcultyNum, teacherId);
		GeneratorPaperArgs tfGeneratorPaperArgs = new GeneratorPaperArgs(tfLanguageType, 
														tfStartTime, tfEndTime, tfCreator, tfScore, tfQuestionNum,
														tfEasyNum, tfGeneraNum, tfDiffcultyNum, teacherId);
		GeneratorPaperArgs codeGeneratorPaperArgs = new GeneratorPaperArgs(codeLanguageType, 
														codeStartTime, codeEndTime, codeCreator, codeScore, codeQuestionNum,
														codeEasyNum, codeGeneraNum, codeDiffcultyNum, teacherId);
		model.addAttribute("sumScore", examPaperGeneratorService.generate(examId, examPaperNum, selectGeneratorPaperArgs, tfGeneratorPaperArgs, codeGeneratorPaperArgs));
    }
	
}
