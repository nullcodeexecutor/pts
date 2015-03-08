package cn.pzhu.jsj.pts.dao;

import java.util.List;
import java.util.Map;

import cn.pzhu.jsj.pts.domain.Grade;
import cn.pzhu.jsj.pts.dto.ExamGradeDto;
import cn.pzhu.jsj.pts.dto.StudentGradeDto;

public interface GradeDao {
	
	boolean insert(Grade grade);

	List<ExamGradeDto> findByCourseId(Integer courseId);
	
	List<StudentGradeDto> findByArgs(Map<String, Object> args);
	
	Integer countByArgs(Map<String, Object> args);
	
	List<Grade> findByStudentIdAndExamId(Integer studentId, Integer examId);
	
	void deleteByExamIdAndStudentId(Integer examId, Integer studentId);
	
}
