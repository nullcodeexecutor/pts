package cn.pzhu.jsj.pts.domain;

public class Course {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.id
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.name
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.description
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.teacher_id
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    private Integer teacherId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.id
     *
     * @return the value of course.id
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.id
     *
     * @param id the value for course.id
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.name
     *
     * @return the value of course.name
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.name
     *
     * @param name the value for course.name
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.description
     *
     * @return the value of course.description
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.description
     *
     * @param description the value for course.description
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.teacher_id
     *
     * @return the value of course.teacher_id
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.teacher_id
     *
     * @param teacherId the value for course.teacher_id
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}