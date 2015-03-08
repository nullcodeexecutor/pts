package cn.pzhu.jsj.pts.dao.impl;

import cn.pzhu.jsj.pts.dao.AdminDao;
import cn.pzhu.jsj.pts.domain.Admin;
import cn.pzhu.jsj.pts.domain.AdminExample;
import cn.pzhu.jsj.pts.domain.Student;
import cn.pzhu.jsj.pts.domain.StudentExample;
import cn.pzhu.jsj.pts.dto.AdminDto;
import cn.pzhu.jsj.pts.dto.TeacherDto;
import cn.pzhu.jsj.pts.mapper.AdminMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-1-28
 * Time: 下午1:14
 * To change this template use File | Settings | File Templates.
 */
@Repository("adminDao")
public class AdminDaoImpl extends SqlSessionDaoSupport implements AdminDao {
    private Log log = LogFactory.getLog(AdminDao.class);

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public List<Admin> findAll() {
        log.info("find all");
//        AdminExample example = new AdminExample();
//        return adminMapper.selectByExample(example);
        Map map = new HashMap<String, Integer>();
        map.put("status", 1);
        return ( List<Admin>)this.getSqlSession().selectList("cn.pzhu.jsj.pts.mapper.AdminMapper.selectByStatus", map);
    }

    @Override
    public Admin findById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

	@Override
	public Admin findByAccount(String account) {
		AdminExample adminExample = new AdminExample();
		AdminExample.Criteria criteria = adminExample.createCriteria();
		criteria.andAccountEqualTo(account);
		List<Admin> list = adminMapper.selectByExample(adminExample);
		if(list.size() == 0){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<AdminDto> findAdminDto(Map<String, Object> args) {
		return (List<AdminDto>)this.getSqlSession().selectList("cn.pzhu.jsj.pts.mapper.user.selectAdminDto", args);
	}

	@Override
	public Integer findCount() {
		AdminExample adminExample = new AdminExample();
		return adminMapper.countByExample(adminExample);
	}

	@Override
	public boolean deleteById(Integer id) {
		return 1 == adminMapper.deleteByPrimaryKey(id);
	}

	@Override
	public boolean insert(Admin admin) {
		return 1 == adminMapper.insert(admin);
	}

	@Override
	public boolean update(Admin admin) {
		return 1 == adminMapper.updateByPrimaryKey(admin);
	}
}
