package cn.pzhu.jsj.pts.dao;

import cn.pzhu.jsj.pts.domain.Exam;
import cn.pzhu.jsj.pts.dto.ExamDto;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-26
 * Time: 下午2:32
 * To change this template use File | Settings | File Templates.
 */
public interface ExamDao {

	Integer findCountByCourseId(Integer courseId);

    List<Exam> findByCourseId(Integer courseId, Integer offset, Integer pageSize);

	Integer findCountByStatus(Integer status);
    
    List<Exam> findByStatus(Integer status, Integer offset, Integer pageSize);    
    
    List<ExamDto> findExamDto(Map<String, Object> args);

    Integer findCountExamDto(Map<String, Object> args);
    
    Exam findById(Integer id);
    
    boolean insert(Exam exam);
    
    boolean deleteById(Integer id);
    
    boolean update(Exam exam);
}
