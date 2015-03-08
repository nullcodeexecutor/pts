package cn.pzhu.jsj.pts.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.pzhu.jsj.pts.dao.CourseDao;
import cn.pzhu.jsj.pts.domain.Course;
import cn.pzhu.jsj.pts.domain.CourseExample;
import cn.pzhu.jsj.pts.domain.ExamExample;
import cn.pzhu.jsj.pts.mapper.CourseMapper;

@Repository("courseDao")
public class CourseDaoImpl implements CourseDao {
	@Autowired
	private CourseMapper courseMapper;

	@Override
	public Course findById(Integer id) {
		return courseMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer findCountByTeacherId(Integer teacherId) {
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andTeacherIdEqualTo(teacherId);
        return courseMapper.countByExample(courseExample);
	}

	@Override
	public List<Course> findByTeacherId(Integer teacherId, Integer offset,
			Integer pageSize) {
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andTeacherIdEqualTo(teacherId);
        String order = new StringBuffer("").append("id desc limit ").append(offset).append(",").append(pageSize).toString();
        courseExample.setOrderByClause(order);
        return courseMapper.selectByExample(courseExample);
	}

	@Override
	public boolean update(Course course) {
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andIdEqualTo(course.getId());
        criteria.andTeacherIdEqualTo(course.getTeacherId());
		return 1 == courseMapper.updateByExample(course, courseExample);
	}

	@Override
	public boolean insert(Course course) {
		return 1 == courseMapper.insert(course);
	}

	@Override
	public boolean delete(Integer id, Integer teacherId) {
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andTeacherIdEqualTo(teacherId);
		return 1 == courseMapper.deleteByExample(courseExample);
	}

}
