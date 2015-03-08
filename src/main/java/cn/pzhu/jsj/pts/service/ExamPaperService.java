package cn.pzhu.jsj.pts.service;

import java.util.List;

import cn.pzhu.jsj.pts.domain.ExamPaper;

public interface ExamPaperService {
	
	ExamPaper findRandomByExamId(Integer examId);
	
	List<ExamPaper> findByExamId(Integer examId, Integer page, Integer pageSize);
	
	Integer findCountByExamId(Integer examId);
	
	boolean deleteById(Integer id);
	
	ExamPaper findById(Integer id);

}
