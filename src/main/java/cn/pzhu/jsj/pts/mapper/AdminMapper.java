package cn.pzhu.jsj.pts.mapper;

import cn.pzhu.jsj.pts.domain.Admin;
import cn.pzhu.jsj.pts.domain.AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    int countByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    int deleteByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    int insert(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    int insertSelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    List<Admin> selectByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    Admin selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    int updateByPrimaryKeySelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    int updateByPrimaryKey(Admin record);
}