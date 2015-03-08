package cn.pzhu.jsj.pts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pzhu.jsj.pts.dao.CourseDao;
import cn.pzhu.jsj.pts.dao.TeacherDao;
import cn.pzhu.jsj.pts.domain.Course;
import cn.pzhu.jsj.pts.domain.Teacher;
import cn.pzhu.jsj.pts.dto.CourseDto;
import cn.pzhu.jsj.pts.service.CourseService;

@Service("courseService")
public class CourseServiceImpl implements CourseService{
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private TeacherDao teacherDao;

	@Override
	public Integer findTotalByTeacherId(Integer teacherId) {
		return courseDao.findCountByTeacherId(teacherId);
	}

	@Override
	public List<Course> findByTeacherId(Integer teacherId, Integer page,
			Integer pageSize) {
		return courseDao.findByTeacherId(teacherId, (page-1)*pageSize, pageSize);
	}

	@Override
	public boolean modify(Course course) {
		return courseDao.update(course);
	}

	@Override
	public boolean insert(Course course) {
		return courseDao.insert(course);
	}

	@Override
	public boolean delete(Integer id, Integer teacherId) {
		return courseDao.delete(id, teacherId);
	}

	@Override
	public CourseDto findCourseDtoByCourseId(Integer id) {
		Course course = courseDao.findById(id);
		Teacher teacher = teacherDao.findById(course.getTeacherId());
		CourseDto courseDto = new CourseDto();
		courseDto.setName(course.getName());
		courseDto.setDescription(course.getDescription());
		courseDto.setTeacherName(teacher.getName());
		return courseDto;
	}

}
