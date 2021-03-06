package cn.pzhu.jsj.pts.domain;

import java.util.Date;

public class Exam {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column exam.id
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column exam.name
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column exam.course_id
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	private Integer courseId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column exam.start_time
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	private Date startTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column exam.end_time
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	private Date endTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column exam.score
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	private Integer score;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column exam.status
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	private Integer status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column exam.type
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	private Integer type;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column exam.id
	 * @return  the value of exam.id
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column exam.id
	 * @param id  the value for exam.id
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column exam.name
	 * @return  the value of exam.name
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column exam.name
	 * @param name  the value for exam.name
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column exam.course_id
	 * @return  the value of exam.course_id
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public Integer getCourseId() {
		return courseId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column exam.course_id
	 * @param courseId  the value for exam.course_id
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column exam.start_time
	 * @return  the value of exam.start_time
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column exam.start_time
	 * @param startTime  the value for exam.start_time
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column exam.end_time
	 * @return  the value of exam.end_time
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column exam.end_time
	 * @param endTime  the value for exam.end_time
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column exam.score
	 * @return  the value of exam.score
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column exam.score
	 * @param score  the value for exam.score
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column exam.status
	 * @return  the value of exam.status
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column exam.status
	 * @param status  the value for exam.status
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column exam.type
	 * @return  the value of exam.type
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column exam.type
	 * @param type  the value for exam.type
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public void setType(Integer type) {
		this.type = type;
	}
}