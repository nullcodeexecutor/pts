package cn.pzhu.jsj.pts.service;

import cn.pzhu.jsj.pts.domain.Exam;
import cn.pzhu.jsj.pts.dto.ExamDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-26
 * Time: 下午2:39
 * To change this template use File | Settings | File Templates.
 */
public interface ExamService {

    Integer findTotalByCourseId(Integer courseId);

    List<Exam> findByCourseId(Integer courseId, Integer page, Integer pageSize);

	Integer findCountNotEnd();
	
    List<Exam> findNotEnd(Integer page, Integer pageSize);
    
    List<ExamDto> findExamDto(Integer page, Integer pageSize);
    
    Integer findCountExamDto(Integer page, Integer pageSize);
    
    List<ExamDto> findExamDto(String examName, String teacherName, String courseName, Integer page, Integer pageSize);
    
    Integer findCountExamDto(String examName, String teacherName, String courseName, Integer page, Integer pageSize);
    
    ExamDto findExamDtoById(Integer examId);
    
    boolean insert(Exam exam);
    
    boolean delete(Integer id, Integer teacherId);
    
    boolean update(Exam exam);
    
    Exam findById(Integer id);
}
