package cn.pzhu.jsj.pts.service;

import java.util.List;

import cn.pzhu.jsj.pts.domain.Student;

public interface StudentService {

	List<Student> find(Integer courseId, String account, String name, String className, String departName, Integer page, Integer pageSize);
	
	Integer findTotal(Integer courseId, String account, String name, String className, String departName);
	
	boolean insert(Student student);
	
	Student findByAccountAndCourseId(String account, Integer courseId);
	
	boolean update(Student student);

	boolean deleteById(Integer id);
	
	String execBatchInsert(List<Student> students);	
	
	Student findByAccountAndExamId(String account, Integer examId);
	
}
