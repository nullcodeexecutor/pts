package cn.pzhu.jsj.pts.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.dao.ExamDao;
import cn.pzhu.jsj.pts.dao.GradeDao;
import cn.pzhu.jsj.pts.domain.Exam;
import cn.pzhu.jsj.pts.domain.Grade;
import cn.pzhu.jsj.pts.dto.ExamGradeDto;
import cn.pzhu.jsj.pts.dto.StudentGradeDto;
import cn.pzhu.jsj.pts.service.GradeService;

@Service("gradeService")
public class GradeServiceImpl implements GradeService {
	@Autowired
	private GradeDao gradeDao;
	@Autowired
	private ExamDao examDao;

	@Override
	public boolean insert(Grade grade, Integer studentId) {
		Exam exam = examDao.findById(grade.getExamId());
		if(exam.getType() == Constant.EXAM_TYPE_EXAM){
			gradeDao.deleteByExamIdAndStudentId(exam.getId(), studentId);
		}
		return gradeDao.insert(grade);
	}

	@Override
	public List<ExamGradeDto> findByCourseId(Integer courseId) {
		return gradeDao.findByCourseId(courseId);
	}

	@Override
	public List<StudentGradeDto> findByExamId(Integer examId, String account, String name, String className, String departName, Integer page,
			Integer pageSize) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("examId", examId);
		args.put("offset", (page-1)*pageSize);
		args.put("pageSize", pageSize);
		if(StringUtils.isNotBlank(account)){
			args.put("account", account);
		}
		if(StringUtils.isNotBlank(name)){
			args.put("name", name);
		}
		if(StringUtils.isNotBlank(className)){
			args.put("className", className);
		}
		if(StringUtils.isNotBlank(departName)){
			args.put("departName", departName);
		}
		return gradeDao.findByArgs(args);
	}

	@Override
	public Integer findCountByExamId(Integer examId, String account, String name, String className, String departName) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("examId", examId);
		if(StringUtils.isNotBlank(account)){
			args.put("account", account);
		}
		if(StringUtils.isNotBlank(name)){
			args.put("name", name);
		}
		if(StringUtils.isNotBlank(className)){
			args.put("className", className);
		}
		if(StringUtils.isNotBlank(departName)){
			args.put("departName", departName);
		}
		return gradeDao.countByArgs(args);
	}

	@Override
	public List<Grade> findByStudentIdAndExamId(Integer studentId,
			Integer examId) {
		List<Grade> list = gradeDao.findByStudentIdAndExamId(studentId, examId);
		return list;
	}

}
