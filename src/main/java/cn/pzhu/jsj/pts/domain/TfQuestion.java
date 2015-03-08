package cn.pzhu.jsj.pts.domain;

import java.util.Date;

public class TfQuestion {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tf_question.id
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tf_question.content
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	private String content;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tf_question.answer
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	private Integer answer;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tf_question.language_type
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	private Integer languageType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tf_question.difficulty_type
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	private Integer difficultyType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tf_question.create_time
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tf_question.teacher_id
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	private Integer teacherId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tf_question.id
	 * @return  the value of tf_question.id
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tf_question.id
	 * @param id  the value for tf_question.id
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tf_question.content
	 * @return  the value of tf_question.content
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	public String getContent() {
		return content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tf_question.content
	 * @param content  the value for tf_question.content
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tf_question.answer
	 * @return  the value of tf_question.answer
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	public Integer getAnswer() {
		return answer;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tf_question.answer
	 * @param answer  the value for tf_question.answer
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	public void setAnswer(Integer answer) {
		this.answer = answer;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tf_question.language_type
	 * @return  the value of tf_question.language_type
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	public Integer getLanguageType() {
		return languageType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tf_question.language_type
	 * @param languageType  the value for tf_question.language_type
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	public void setLanguageType(Integer languageType) {
		this.languageType = languageType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tf_question.difficulty_type
	 * @return  the value of tf_question.difficulty_type
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	public Integer getDifficultyType() {
		return difficultyType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tf_question.difficulty_type
	 * @param difficultyType  the value for tf_question.difficulty_type
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	public void setDifficultyType(Integer difficultyType) {
		this.difficultyType = difficultyType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tf_question.create_time
	 * @return  the value of tf_question.create_time
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tf_question.create_time
	 * @param createTime  the value for tf_question.create_time
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tf_question.teacher_id
	 * @return  the value of tf_question.teacher_id
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	public Integer getTeacherId() {
		return teacherId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tf_question.teacher_id
	 * @param teacherId  the value for tf_question.teacher_id
	 * @mbggenerated  Fri Apr 18 23:58:47 CST 2014
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
}