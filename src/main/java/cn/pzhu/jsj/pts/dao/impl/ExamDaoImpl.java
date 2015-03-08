package cn.pzhu.jsj.pts.dao.impl;

import cn.pzhu.jsj.pts.dao.ExamDao;
import cn.pzhu.jsj.pts.domain.Admin;
import cn.pzhu.jsj.pts.domain.Exam;
import cn.pzhu.jsj.pts.domain.ExamExample;
import cn.pzhu.jsj.pts.dto.ExamDto;
import cn.pzhu.jsj.pts.mapper.ExamMapper;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-26
 * Time: 下午2:32
 * To change this template use File | Settings | File Templates.
 */
@Repository("examDao")
public class ExamDaoImpl extends SqlSessionDaoSupport implements ExamDao {
    @Autowired
    private ExamMapper  examMapper;

    @Override
    public Integer findCountByCourseId(Integer courseId) {
        ExamExample examExample = new ExamExample();
        ExamExample.Criteria criteria = examExample.createCriteria();
        criteria.andCourseIdEqualTo(courseId);
        return examMapper.countByExample(examExample);
    }

    @Override
    public List<Exam> findByCourseId(Integer courseId, Integer offset, Integer pageSize) {
        ExamExample examExample = new ExamExample();
        ExamExample.Criteria criteria = examExample.createCriteria();
        criteria.andCourseIdEqualTo(courseId);
        String order = new StringBuffer("").append("id desc limit ").append(offset).append(",").append(pageSize).toString();
        examExample.setOrderByClause(order);
        return examMapper.selectByExample(examExample);
    }

	@Override
	public List<Exam> findByStatus(Integer status, Integer offset, Integer pageSize) {
		ExamExample examExample = new ExamExample();
		ExamExample.Criteria criteria = examExample.createCriteria();
		criteria.andStatusEqualTo(status);
        String order = new StringBuffer("").append("id desc limit ").append(offset).append(",").append(pageSize).toString();
        examExample.setOrderByClause(order);
		return examMapper.selectByExample(examExample);
	}

	@Override
	public Integer findCountByStatus(Integer status) {
		ExamExample examExample = new ExamExample();
		ExamExample.Criteria criteria = examExample.createCriteria();
		criteria.andStatusEqualTo(status);
		return examMapper.countByExample(examExample);
	}

	@Override
	public List<ExamDto> findExamDto(Map<String, Object> args) {
        return (List<ExamDto>)this.getSqlSession().selectList("cn.pzhu.jsj.pts.mapper.exam.selectExamDto", args);
    }

	@Override
	public Integer findCountExamDto(Map<String, Object> args) {
        return (Integer)this.getSqlSession().selectOne("cn.pzhu.jsj.pts.mapper.exam.selectCountExamDto", args);
	}

	@Override
	public Exam findById(Integer id) {
		return examMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean insert(Exam exam) {
		return 1 == examMapper.insert(exam);
	}

	@Override
	public boolean deleteById(Integer id) {
		return 1 == examMapper.deleteByPrimaryKey(id);
	}

	@Override
	public boolean update(Exam exam) {
		return 1 == examMapper.updateByPrimaryKey(exam);
	}
	
}
