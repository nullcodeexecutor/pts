package cn.pzhu.jsj.pts.mapper;

import cn.pzhu.jsj.pts.domain.TfQuestion;
import cn.pzhu.jsj.pts.domain.TfQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TfQuestionMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tf_question
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	int countByExample(TfQuestionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tf_question
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	int deleteByExample(TfQuestionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tf_question
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tf_question
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	int insert(TfQuestion record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tf_question
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	int insertSelective(TfQuestion record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tf_question
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	List<TfQuestion> selectByExample(TfQuestionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tf_question
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	TfQuestion selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tf_question
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	int updateByExampleSelective(@Param("record") TfQuestion record,
			@Param("example") TfQuestionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tf_question
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	int updateByExample(@Param("record") TfQuestion record,
			@Param("example") TfQuestionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tf_question
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	int updateByPrimaryKeySelective(TfQuestion record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tf_question
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	int updateByPrimaryKey(TfQuestion record);
}