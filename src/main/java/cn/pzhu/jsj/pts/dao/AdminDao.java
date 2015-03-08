package cn.pzhu.jsj.pts.dao;

import java.util.List;
import java.util.Map;

import cn.pzhu.jsj.pts.domain.Admin;
import cn.pzhu.jsj.pts.dto.AdminDto;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-1-28
 * Time: 下午1:14
 * To change this template use File | Settings | File Templates.
 */
public interface AdminDao {

    List<Admin> findAll();

    Admin findById(Integer id);
    
    Admin findByAccount(String account);
    
    List<AdminDto> findAdminDto(Map<String, Object> args);
    
    Integer findCount();
    
    boolean deleteById(Integer id);
    
    boolean insert(Admin admin);
    
    boolean update(Admin admin);

}
