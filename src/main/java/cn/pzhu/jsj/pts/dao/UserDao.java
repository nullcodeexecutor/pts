package cn.pzhu.jsj.pts.dao;

import java.util.List;

import cn.pzhu.jsj.pts.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-13
 * Time: 上午10:55
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao {

    User findByName(String name);
    
    boolean insert(User user);
    
    boolean update(User user);
    
    List<User> find(Integer offset, Integer pageSize);
    
    Integer count();
    
    User findById(Integer id);
    
    boolean deleteById(Integer id);
    
    boolean deleteByName(String name);

}
