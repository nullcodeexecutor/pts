package cn.pzhu.jsj.pts.dto;

import java.util.Date;

public class TFQuestionDto {
	private Integer id;
	private String content;
	private Integer answer;	
	private Integer languageType;
	private Integer difficultyType;
	private Date createTime;
	private String teacherName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getAnswer() {
		return answer;
	}
	public void setAnswer(Integer answer) {
		this.answer = answer;
	}
	public Integer getLanguageType() {
		return languageType;
	}
	public void setLanguageType(Integer languageType) {
		this.languageType = languageType;
	}
	public Integer getDifficultyType() {
		return difficultyType;
	}
	public void setDifficultyType(Integer difficultyType) {
		this.difficultyType = difficultyType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
}
