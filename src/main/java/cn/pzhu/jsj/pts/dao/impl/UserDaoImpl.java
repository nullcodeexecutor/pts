package cn.pzhu.jsj.pts.dao.impl;

import cn.pzhu.jsj.pts.dao.UserDao;
import cn.pzhu.jsj.pts.domain.User;
import cn.pzhu.jsj.pts.domain.UserExample;
import cn.pzhu.jsj.pts.mapper.UserMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-13
 * Time: 上午10:55
 * To change this template use File | Settings | File Templates.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    private Log log = LogFactory.getLog(UserDaoImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(String name) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(example);
        if(users.size()>0){
            return users.get(0);
        }
        return null;
    }

	@Override
	public boolean insert(User user) {
		return 1 == userMapper.insert(user);
	}

	@Override
	public boolean update(User user) {
		return 1 == userMapper.updateByPrimaryKey(user);
	}

	@Override
	public List<User> find(Integer offset, Integer pageSize) {
		UserExample userExample = new UserExample();
        String order = new StringBuffer("").append("id desc limit ").append(offset).append(",").append(pageSize).toString();
        userExample.setOrderByClause(order);
		return userMapper.selectByExample(userExample);
	}

	@Override
	public Integer count() {
		UserExample userExample = new UserExample();
		return userMapper.countByExample(userExample);
	}

	@Override
	public User findById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean deleteById(Integer id) {
		return 1 == userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public boolean deleteByName(String name) {
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andNameEqualTo(name);
		return 1 == userMapper.deleteByExample(userExample);
	}
}
