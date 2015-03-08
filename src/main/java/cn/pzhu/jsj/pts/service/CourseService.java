package cn.pzhu.jsj.pts.service;

import java.util.List;

import cn.pzhu.jsj.pts.domain.Course;
import cn.pzhu.jsj.pts.dto.CourseDto;

public interface CourseService {

    Integer findTotalByTeacherId(Integer teacherId);

    List<Course> findByTeacherId(Integer teacherId, Integer page, Integer pageSize);
    
    boolean modify(Course course);
    
    boolean insert(Course course);
    
    boolean delete(Integer id, Integer teacherId);
    
    CourseDto findCourseDtoByCourseId(Integer id);

}
