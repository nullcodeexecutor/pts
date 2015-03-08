package cn.pzhu.jsj.pts.dto;

import java.util.Date;
import java.util.List;

import cn.pzhu.jsj.pts.domain.SelectOption;

public class SelectQuestionDto {
	private Integer id;
	private String content;
	private String answer;	
	private Integer languageType;
	private Integer difficultyType;
	private Date createTime;
	private String teacherName;
	private List<SelectOption> options;
	
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
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
	public List<SelectOption> getOptions() {
		return options;
	}
	public void setOptions(List<SelectOption> options) {
		this.options = options;
	}
	
}
