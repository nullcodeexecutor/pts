package cn.pzhu.jsj.pts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pzhu.jsj.pts.dao.CourseDao;
import cn.pzhu.jsj.pts.dao.ExamDao;
import cn.pzhu.jsj.pts.dao.StudentDao;
import cn.pzhu.jsj.pts.domain.Exam;
import cn.pzhu.jsj.pts.domain.Student;
import cn.pzhu.jsj.pts.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private ExamDao examDao;

	@Override
	public List<Student> find(Integer courseId, String account, String name, String className,
			String departName, Integer page, Integer pageSize) {
		Student student = new Student();
		student.setCourseId(courseId);
		student.setName(name);
		student.setClassName(className);
		student.setDepartName(departName);	
		student.setAccount(account);
		return studentDao.find(student, (page-1)*pageSize, pageSize);
	}

	@Override
	public Integer findTotal(Integer courseId, String account, String name, String className,
			String departName) {
		Student student = new Student();
		student.setCourseId(courseId);
		student.setName(name);
		student.setClassName(className);
		student.setDepartName(departName);
		student.setAccount(account);
		return studentDao.findCount(student);
	}

	@Override
	public boolean insert(Student student) {
		return studentDao.insert(student);
	}

	@Override
	public Student findByAccountAndCourseId(String account, Integer courseId) {
		return studentDao.findByAccountAndCourseId(account, courseId);
	}

	@Override
	public boolean update(Student student) {
		Student stu = studentDao.findById(student.getId());
		
		stu.setClassName(student.getClassName());
		stu.setDepartName(student.getDepartName());
		stu.setName(student.getName());
		stu.setPwd(student.getPwd());
		
		return studentDao.update(stu);
	}

	@Override
	public boolean deleteById(Integer id) {
		return studentDao.deleteById(id);
	}

	@Override
	public String execBatchInsert(List<Student> students) {
		StringBuffer str = new StringBuffer("");
		StringBuffer fialed = new StringBuffer("");
		str.append("需操作的总学生数 ").append(students.size()).append("<br/>");
		int insertNum = 0;
		int updateNum = 0;
		for(Student student : students){
			Student stu = studentDao.findByAccountAndCourseId(student.getAccount(), student.getCourseId());
			if(stu == null){
				if(studentDao.insert(student)){
					insertNum ++;
				}else{
					fialed.append(student.getAccount()).append(" ");
				}
			}else{
				student.setId(stu.getId());
				if(studentDao.update(student)){
					updateNum ++;
				}else{
					fialed.append(student.getAccount()).append(" ");					
				}
			}
		}
		str.append("新增学生数 "+insertNum).append("<br/>");
		str.append("更新学生数 "+updateNum);
		if(!fialed.toString().equals("")){
			str.append("<br/>操作失败的学生账号如下:<br/>").append(fialed.toString());
		}
		return str.toString();
	}

	@Override
	public Student findByAccountAndExamId(String account, Integer examId) {
		Exam exam = examDao.findById(examId);
		return studentDao.findByAccountAndCourseId(account, exam.getCourseId());
	}

}
