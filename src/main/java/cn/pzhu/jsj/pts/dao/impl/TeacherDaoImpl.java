package cn.pzhu.jsj.pts.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.pzhu.jsj.pts.dao.TeacherDao;
import cn.pzhu.jsj.pts.domain.Teacher;
import cn.pzhu.jsj.pts.domain.TeacherExample;
import cn.pzhu.jsj.pts.dto.TeacherDto;
import cn.pzhu.jsj.pts.mapper.TeacherMapper;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-26
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
@Repository("teacherDao")
public class TeacherDaoImpl extends SqlSessionDaoSupport implements TeacherDao {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher findByAccount(String account) {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andAccountEqualTo(account);
        List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
        if(teachers.size()>0){
            return teachers.get(0);
        }
        return null;
    }

	@Override
	public Teacher findById(Integer id) {
		return teacherMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean insert(Teacher teacher) {
		return 1 == teacherMapper.insert(teacher);
	}

	@Override
	public boolean update(Teacher teacher) {
		return 1 == teacherMapper.updateByPrimaryKey(teacher);
	}

	@Override
	public List<Teacher> find(Integer offset, Integer pageSize) {
		TeacherExample teacherExample = new TeacherExample();
        String order = new StringBuffer("").append("id asc limit ").append(offset).append(",").append(pageSize).toString();
        teacherExample.setOrderByClause(order);
		return teacherMapper.selectByExample(teacherExample);
	}

	@Override
	public Integer count() {
		TeacherExample teacherExample = new TeacherExample();
		return teacherMapper.countByExample(teacherExample);
	}

	@Override
	public List<TeacherDto> findTeacherDto(Map<String, Object> args) {
		return (List<TeacherDto>)this.getSqlSession().selectList("cn.pzhu.jsj.pts.mapper.user.selectTeacherDto", args);
	}

	@Override
	public boolean delete(Integer id) {
		return 1==teacherMapper.deleteByPrimaryKey(id);
	}

}
