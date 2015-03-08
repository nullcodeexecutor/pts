package cn.pzhu.jsj.pts.service.impl;

import cn.pzhu.jsj.pts.common.Constant;
import cn.pzhu.jsj.pts.dao.CourseDao;
import cn.pzhu.jsj.pts.dao.ExamDao;
import cn.pzhu.jsj.pts.dao.TeacherDao;
import cn.pzhu.jsj.pts.domain.Course;
import cn.pzhu.jsj.pts.domain.Exam;
import cn.pzhu.jsj.pts.domain.Teacher;
import cn.pzhu.jsj.pts.dto.ExamDto;
import cn.pzhu.jsj.pts.service.ExamService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-26
 * Time: 下午2:39
 * To change this template use File | Settings | File Templates.
 */
@Service("examService")
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamDao examDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private CourseDao courseDao;

    @Override
    public Integer findTotalByCourseId(Integer courseId) {
        return examDao.findCountByCourseId(courseId);
    }

    @Override
    public List<Exam> findByCourseId(Integer courseId, Integer page, Integer pageSize) {
        return examDao.findByCourseId(courseId, (page-1)*pageSize, pageSize);
    }

	@Override
	public List<Exam> findNotEnd(Integer page, Integer pageSize) {
		return examDao.findByStatus(Constant.EXAM_STATUS_NOTEND, (page-1)*pageSize, pageSize);
	}

	@Override
	public Integer findCountNotEnd() {
		return examDao.findCountByStatus(Constant.EXAM_STATUS_NOTEND);
	}

	@Override
	public List<ExamDto> findExamDto(Integer page, Integer pageSize) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("offset", (page-1)*pageSize);
		args.put("pageSize", pageSize);
		args.put("status", Constant.EXAM_STATUS_NOTEND);
		return examDao.findExamDto(args);
	}

	@Override
	public List<ExamDto> findExamDto(String examName, String teacherName, String courseName,
			Integer page, Integer pageSize) {
		return examDao.findExamDto(getArgs(examName, teacherName, courseName, page, pageSize));
	}

	@Override
	public Integer findCountExamDto(Integer page, Integer pageSize) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("offset", (page-1)*pageSize);
		args.put("pageSize", pageSize);
		args.put("status", Constant.EXAM_STATUS_NOTEND);
		return examDao.findCountExamDto(args);
	}

	@Override
	public Integer findCountExamDto(String examName, String teacherName, String courseName,
			Integer page, Integer pageSize) {
		return examDao.findCountExamDto(getArgs(examName, teacherName, courseName, page, pageSize));
	}
	
	private Map<String, Object> getArgs(String examName, String teacherName, String courseName,
			Integer page, Integer pageSize){
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("offset", (page-1)*pageSize);
		args.put("pageSize", pageSize);
		args.put("status", Constant.EXAM_STATUS_NOTEND);
		if(StringUtils.isNotBlank(examName)){
			args.put("examName", "%"+examName+"%");
		}
		if(StringUtils.isNotBlank(teacherName)){
			args.put("teacherName", "%"+teacherName+"%");
		}
		if(StringUtils.isNotBlank(courseName)){
			args.put("courseName", "%"+courseName+"%");
		}
		return args;
	}

	@Override
	public ExamDto findExamDtoById(Integer examId) {
		Exam exam = examDao.findById(examId);
		Course course = courseDao.findById(exam.getCourseId());
		Teacher teacher = teacherDao.findById(course.getTeacherId());
		ExamDto examDto = new ExamDto();
		
		examDto.setId(exam.getId());
		examDto.setEndTime(exam.getEndTime());
		examDto.setName(exam.getName());
		examDto.setScore(exam.getScore());
		examDto.setStartTime(exam.getStartTime());
		examDto.setStatus(exam.getStatus());
		examDto.setTeacherAccount(teacher.getAccount());
		examDto.setTeacherId(teacher.getId());
		examDto.setTeacherName(teacher.getName());
		examDto.setCourseId(course.getId());
		examDto.setCourseName(course.getName());
		
		return examDto;
	}

	@Override
	public boolean insert(Exam exam) {
		return examDao.insert(exam);
	}

	@Override
	public boolean delete(Integer id, Integer teacherId) {
		Exam exam = examDao.findById(id);
		Course course = courseDao.findById(exam.getCourseId());
		if(course.getTeacherId() != teacherId){
			return false;
		}
		return examDao.deleteById(id);
	}

	@Override
	public boolean update(Exam exam) {
		Exam record = examDao.findById(exam.getId());
		exam.setCourseId(record.getCourseId());
		return examDao.update(exam);
	}

	@Override
	public Exam findById(Integer id) {
		return examDao.findById(id);
	}

}
