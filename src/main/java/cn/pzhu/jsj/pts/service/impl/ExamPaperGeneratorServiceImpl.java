package cn.pzhu.jsj.pts.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.dao.CodeQuestionDao;
import cn.pzhu.jsj.pts.dao.ExamDao;
import cn.pzhu.jsj.pts.dao.ExamPaperDao;
import cn.pzhu.jsj.pts.dao.GenerateDao;
import cn.pzhu.jsj.pts.dao.PaperInfoDao;
import cn.pzhu.jsj.pts.dao.SelectQuestionDao;
import cn.pzhu.jsj.pts.dao.TfQuestionDao;
import cn.pzhu.jsj.pts.domain.Exam;
import cn.pzhu.jsj.pts.domain.ExamPaper;
import cn.pzhu.jsj.pts.domain.PaperInfo;
import cn.pzhu.jsj.pts.dto.GeneratorPaperArgs;
import cn.pzhu.jsj.pts.service.ExamPaperGeneratorService;

@Service("examPaperGeneratorService")
public class ExamPaperGeneratorServiceImpl implements ExamPaperGeneratorService{
	@Autowired
	private ExamPaperDao examPaperDao;
	@Autowired
	private SelectQuestionDao selectQuestionDao;
	@Autowired
	private TfQuestionDao tfQuestionDao;
	@Autowired
	private CodeQuestionDao codeQuestionDao;
	@Autowired
	private PaperInfoDao paperInfoDao;
	@Autowired
	private ExamDao examDao;

	@Override
	public Integer generate(Integer examId, Integer examPaperNum,
			GeneratorPaperArgs selectGeneratorPaperArgs,
			GeneratorPaperArgs tfGeneratorPaperArgs,
			GeneratorPaperArgs codeGeneratorPaperArgs) {
		//删除原来生成的题目
		List<ExamPaper> examPapers = examPaperDao.findByExamId(examId);
		for(ExamPaper examPaper : examPapers){
			paperInfoDao.deleteByPaperId(examPaper.getId());
		}	
		examPaperDao.deleteByExamId(examId);
		
		for(int i=0;i<examPaperNum;i++){
			ExamPaper examPaper = new ExamPaper();
			examPaper.setCreateTime(new Date());
			examPaper.setExamId(examId);
			examPaperDao.insert(examPaper);
		}
		examPapers = examPaperDao.findByExamId(examId);
		int sumScore = 0;
		for(ExamPaper examPaper : examPapers){
			sumScore = 0;
			sumScore += generateQuestion(examPaper.getId(), selectGeneratorPaperArgs, (GenerateDao)selectQuestionDao, Constant.QUESTION_TYPE_SELECT);
			sumScore += generateQuestion(examPaper.getId(), tfGeneratorPaperArgs, (GenerateDao)tfQuestionDao, Constant.QUESTION_TYPE_TF);
			sumScore += generateQuestion(examPaper.getId(), codeGeneratorPaperArgs, (GenerateDao)codeQuestionDao, Constant.QUESTION_TYPE_CODE);
		}	
		Exam exam = examDao.findById(examId);
		exam.setScore(sumScore);
		examDao.update(exam);
		return sumScore;
	}
	
	private Integer generateQuestion(Integer examPaperId, GeneratorPaperArgs args, GenerateDao generateDao, Integer questionType){
		List<Integer> questionIdList = new ArrayList<Integer>();
		//放一个0到list中，为了sql语句not in的方便
		questionIdList.add(0);
		Map<String, Object> map = new HashMap<String, Object>();
		if(args.getCreator() == 0){
			map.put("teacherId", args.getTeacherId());			
		}
		map.put("startTime", args.getStartTime());
		map.put("endTime", args.getEndTime());
		map.put("list", questionIdList);
		map.put("languageType", args.getLanguageType());
		
		if(args.getQuestionNum()>0){
			for(int i=0;i<args.getQuestionNum();i++){
				int count = generateDao.countNotSelected(map);
				if(count==0){
					break;
				}
				int offset = new Random().nextInt(count);
				map.put("offset", offset);
				Integer id = generateDao.randomSelectId(map);
				questionIdList.add(id);
			}
		}else{
			for(int i=0;i<args.getEasyNum();i++){
				map.put("difficultyType", Constant.DIFFICULTY_TYPE_EASY);
				int count = generateDao.countNotSelected(map);
				if(count==0){
					break;
				}
				int offset = new Random().nextInt(count);
				map.put("offset", offset);
				Integer id = generateDao.randomSelectId(map);
				questionIdList.add(id);
			}
			for(int i=0;i<args.getGeneraNum();i++){
				map.put("difficultyType", Constant.DIFFICULTY_TYPE_GENERAL);
				int count = generateDao.countNotSelected(map);
				if(count==0){
					break;
				}
				int offset = new Random().nextInt(count);
				map.put("offset", offset);
				Integer id = generateDao.randomSelectId(map);
				questionIdList.add(id);
			}
			for(int i=0;i<args.getDiffcultyNum();i++){
				map.put("difficultyType", Constant.DIFFICULTY_TYPE_DIFFICULT);
				int count = generateDao.countNotSelected(map);
				if(count==0){
					break;
				}
				int offset = new Random().nextInt(count);
				map.put("offset", offset);
				Integer id = generateDao.randomSelectId(map);
				questionIdList.add(id);
			}
		}
		
		PaperInfo paperInfo = null;
		for(Integer questionId : questionIdList){
			if(questionId == 0){
				continue;
			}
			paperInfo = new PaperInfo();
			paperInfo.setType(questionType);
			paperInfo.setPaperId(examPaperId);
			paperInfo.setQuestionId(questionId);
			paperInfo.setScore(args.getScore());
			paperInfoDao.insert(paperInfo);
		}	
		return (questionIdList.size()-1)*args.getScore();
	}
	
}
