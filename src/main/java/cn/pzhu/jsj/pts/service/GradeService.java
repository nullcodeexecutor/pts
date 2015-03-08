package cn.pzhu.jsj.pts.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import cn.pzhu.jsj.pts.domain.Grade;
import cn.pzhu.jsj.pts.dto.ExamGradeDto;
import cn.pzhu.jsj.pts.dto.StudentGradeDto;

public interface GradeService {

	boolean insert(Grade grade, Integer studentId);
	
	List<ExamGradeDto> findByCourseId(Integer courseId);
	
	List<StudentGradeDto> findByExamId(Integer examId, String account, String name, String className, String departName, Integer page, Integer pageSize);
	
	Integer findCountByExamId(Integer examId, String account, String name, String className, String departName);
	
	List<Grade> findByStudentIdAndExamId(Integer studentId, Integer examId);
	
}
