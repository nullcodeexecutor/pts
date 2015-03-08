package cn.pzhu.jsj.pts.dao.impl;

import cn.pzhu.jsj.pts.dao.StudentDao;
import cn.pzhu.jsj.pts.domain.ExamExample;
import cn.pzhu.jsj.pts.domain.Student;
import cn.pzhu.jsj.pts.domain.StudentExample;
import cn.pzhu.jsj.pts.mapper.StudentMapper;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-28
 * Time: 下午5:46
 * To change this template use File | Settings | File Templates.
 */
@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student findById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

	@Override
	public List<Student> find(Student student, Integer offset, Integer pageSize) {
		StudentExample studentExample = new StudentExample();
		StudentExample.Criteria criteria = studentExample.createCriteria();
		
		if(student.getCourseId() != null){
			criteria.andCourseIdEqualTo(student.getCourseId());
		}
		if(StringUtils.isNotBlank(student.getName())){
			criteria.andNameEqualTo(student.getName());
		}
		if(StringUtils.isNotBlank(student.getClassName())){
			criteria.andClassNameEqualTo(student.getClassName());
		}
		if(StringUtils.isNotBlank(student.getDepartName())){
			criteria.andDepartNameEqualTo(student.getDepartName());
		}
		if(StringUtils.isNotBlank(student.getAccount())){
			criteria.andAccountEqualTo(student.getAccount());
		}
		
        String order = new StringBuffer("").append("id asc limit ").append(offset).append(",").append(pageSize).toString();
        studentExample.setOrderByClause(order);
		return studentMapper.selectByExample(studentExample);
	}

	@Override
	public Integer findCount(Student student) {
		StudentExample studentExample = new StudentExample();
		StudentExample.Criteria criteria = studentExample.createCriteria();
		
		if(student.getCourseId() != null){
			criteria.andCourseIdEqualTo(student.getCourseId());
		}
		if(StringUtils.isNotBlank(student.getName())){
			criteria.andNameEqualTo(student.getName());
		}
		if(StringUtils.isNotBlank(student.getClassName())){
			criteria.andClassNameEqualTo(student.getClassName());
		}
		if(StringUtils.isNotBlank(student.getDepartName())){
			criteria.andDepartNameEqualTo(student.getDepartName());
		}
		if(StringUtils.isNotBlank(student.getAccount())){
			criteria.andAccountEqualTo(student.getAccount());
		}
		return studentMapper.countByExample(studentExample);
	}

	@Override
	public boolean insert(Student student) {
		return 1 == studentMapper.insert(student);
	}

	@Override
	public Student findByAccountAndCourseId(String account, Integer courseId) {
		StudentExample studentExample = new StudentExample();
		StudentExample.Criteria criteria = studentExample.createCriteria();
		criteria.andAccountEqualTo(account);
		criteria.andCourseIdEqualTo(courseId);
		List<Student> stus = studentMapper.selectByExample(studentExample);
		if(stus.size() == 0){
			return null;
		}
		return stus.get(0);
	}

	@Override
	public boolean update(Student student) {
		return 1 == studentMapper.updateByPrimaryKey(student);
	}

	@Override
	public boolean deleteById(Integer id) {
		return 1 == studentMapper.deleteByPrimaryKey(id);
	}

}
