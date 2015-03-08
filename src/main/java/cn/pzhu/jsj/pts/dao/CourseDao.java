package cn.pzhu.jsj.pts.dao;

import java.util.List;

import cn.pzhu.jsj.pts.domain.Course;

public interface CourseDao {
	
	Course findById(Integer id);

	Integer findCountByTeacherId(Integer teacherId);

    List<Course> findByTeacherId(Integer teacherId, Integer offset, Integer pageSize);
    
    boolean update(Course course);
    
    boolean insert(Course course);
    
    boolean delete(Integer id, Integer teacherId);

}
