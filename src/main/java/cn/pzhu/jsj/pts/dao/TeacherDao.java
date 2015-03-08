package cn.pzhu.jsj.pts.dao;

import java.util.List;
import java.util.Map;

import cn.pzhu.jsj.pts.domain.Teacher;
import cn.pzhu.jsj.pts.dto.TeacherDto;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-26
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public interface TeacherDao {

    Teacher findByAccount(String account);
    
    Teacher findById(Integer id);
    
    boolean insert(Teacher teacher);
    
    boolean update(Teacher teacher);
    
    List<Teacher> find(Integer offset, Integer pageSize);

    List<TeacherDto> findTeacherDto(Map<String, Object> args);

    Integer count();
    
    boolean delete(Integer id);
    
}
