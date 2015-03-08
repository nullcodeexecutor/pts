package cn.pzhu.jsj.pts.dao;

import java.util.List;

import cn.pzhu.jsj.pts.domain.Student;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-28
 * Time: 下午5:46
 * To change this template use File | Settings | File Templates.
 */
public interface StudentDao {

    Student findById(Integer id);
    
    List<Student> find(Student student, Integer offset, Integer pageSize);

    Integer findCount(Student student);
    
    boolean insert(Student student);

	Student findByAccountAndCourseId(String account, Integer courseId);
	
	boolean update(Student student);

	boolean deleteById(Integer id);

}
