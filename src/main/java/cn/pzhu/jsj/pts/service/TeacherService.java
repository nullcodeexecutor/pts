package cn.pzhu.jsj.pts.service;

import java.util.List;

import cn.pzhu.jsj.pts.domain.Teacher;
import cn.pzhu.jsj.pts.dto.TeacherDto;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-26
 * Time: 下午3:20
 * To change this template use File | Settings | File Templates.
 */
public interface TeacherService {

    Teacher findByAccount(String account);
    
    boolean addTeacher(String account, String name, String pwd);
    
    boolean update(Teacher teacher, String pwd);
    
    List<Teacher> find(Integer page, Integer pageSize);
    
    List<TeacherDto> findTeacherDto(Integer page, Integer pageSize);
    
    Integer findCount();

    boolean update(Integer id, String account, String name, Integer enabled);
    
    boolean delete(Integer id);

}
