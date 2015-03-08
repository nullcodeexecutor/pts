package cn.pzhu.jsj.pts.dao;

import java.util.List;

import cn.pzhu.jsj.pts.domain.ExamPaper;

public interface ExamPaperDao {
	
	Integer findCountByExamId(Integer examId);
	
	List<ExamPaper> findByExamId(Integer examId, Integer offset, Integer size);
	
	void deleteByExamId(Integer examId);
	
	boolean insert(ExamPaper examPaper);
	
	List<ExamPaper> findByExamId(Integer examId);
	
	boolean deleteById(Integer id);
	
	ExamPaper findById(Integer id);

}
