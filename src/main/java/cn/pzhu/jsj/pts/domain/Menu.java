package cn.pzhu.jsj.pts.domain;

import java.util.List;

public class Menu {
	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.id
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.name
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.url
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.parent_id
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    private Integer parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.status
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.role
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    private String role;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.sort
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    private Integer sort;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.id
     *
     * @return the value of menu.id
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.id
     *
     * @param id the value for menu.id
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.name
     *
     * @return the value of menu.name
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.name
     *
     * @param name the value for menu.name
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.url
     *
     * @return the value of menu.url
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.url
     *
     * @param url the value for menu.url
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.parent_id
     *
     * @return the value of menu.parent_id
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.parent_id
     *
     * @param parentId the value for menu.parent_id
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.status
     *
     * @return the value of menu.status
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.status
     *
     * @param status the value for menu.status
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.role
     *
     * @return the value of menu.role
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.role
     *
     * @param role the value for menu.role
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.sort
     *
     * @return the value of menu.sort
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.sort
     *
     * @param sort the value for menu.sort
     *
     * @mbggenerated Thu Apr 17 11:39:35 CST 2014
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}