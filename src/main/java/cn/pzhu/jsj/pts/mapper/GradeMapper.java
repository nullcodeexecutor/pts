package cn.pzhu.jsj.pts.mapper;

import cn.pzhu.jsj.pts.domain.Grade;
import cn.pzhu.jsj.pts.domain.GradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GradeMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	int countByExample(GradeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	int deleteByExample(GradeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	int insert(Grade record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	int insertSelective(Grade record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	List<Grade> selectByExample(GradeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	Grade selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	int updateByExampleSelective(@Param("record") Grade record,
			@Param("example") GradeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	int updateByExample(@Param("record") Grade record,
			@Param("example") GradeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	int updateByPrimaryKeySelective(Grade record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	int updateByPrimaryKey(Grade record);
}