package cn.pzhu.jsj.pts.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.pzhu.jsj.pts.dao.GradeDao;
import cn.pzhu.jsj.pts.domain.Grade;
import cn.pzhu.jsj.pts.domain.GradeExample;
import cn.pzhu.jsj.pts.dto.ExamGradeDto;
import cn.pzhu.jsj.pts.dto.StudentGradeDto;
import cn.pzhu.jsj.pts.mapper.GradeMapper;

@Repository("gradeDao")
public class GradeDaoImpl extends SqlSessionDaoSupport implements GradeDao {
	@Autowired
	private GradeMapper gradeMapper;

	@Override
	public boolean insert(Grade grade) {
		return 1 == gradeMapper.insert(grade);
	}

	@Override
	public List<ExamGradeDto> findByCourseId(Integer courseId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("courseId", courseId);
        return (List<ExamGradeDto>)this.getSqlSession().selectList("cn.pzhu.jsj.pts.mapper.grade.selectGradeDto", args);
	}

	@Override
	public List<StudentGradeDto> findByArgs(Map<String, Object> args) {
        return (List<StudentGradeDto>)this.getSqlSession().selectList("cn.pzhu.jsj.pts.mapper.grade.selectStudentGradeDto", args);
	}

	@Override
	public Integer countByArgs(Map<String, Object> args) {
		return (Integer)this.getSqlSession().selectOne("cn.pzhu.jsj.pts.mapper.grade.countStudentGradeDto", args);
	}

	@Override
	public List<Grade> findByStudentIdAndExamId(Integer studentId,
			Integer examId) {
		GradeExample gradeExample = new GradeExample();
		GradeExample.Criteria criteria = gradeExample.createCriteria();
		criteria.andExamIdEqualTo(examId);
		criteria.andStudentIdEqualTo(studentId);
		return gradeMapper.selectByExample(gradeExample);
	}

	@Override
	public void deleteByExamIdAndStudentId(Integer examId, Integer studentId) {
		GradeExample gradeExample = new GradeExample();
		GradeExample.Criteria criteria = gradeExample.createCriteria();
		criteria.andExamIdEqualTo(examId);
		criteria.andStudentIdEqualTo(studentId);
		gradeMapper.deleteByExample(gradeExample);
	}

}
