package cn.pzhu.jsj.pts.domain;

public class User {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.id
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.name
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.pwd
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    private String pwd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.enabled
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    private Integer enabled;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.role
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    private String role;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.id
     *
     * @return the value of user.id
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.name
     *
     * @return the value of user.name
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.name
     *
     * @param name the value for user.name
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.pwd
     *
     * @return the value of user.pwd
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.pwd
     *
     * @param pwd the value for user.pwd
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.enabled
     *
     * @return the value of user.enabled
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.enabled
     *
     * @param enabled the value for user.enabled
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.role
     *
     * @return the value of user.role
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.role
     *
     * @param role the value for user.role
     *
     * @mbggenerated Mon Apr 14 15:03:25 CST 2014
     */
    public void setRole(String role) {
        this.role = role;
    }
}