package cn.pzhu.jsj.pts.dto;

import java.util.Date;
import java.util.List;

import cn.pzhu.jsj.pts.domain.CodeAnswer;
import cn.pzhu.jsj.pts.domain.SelectOption;

public class CodeQuestionDto {
	private Integer id;
	private String content;
	private String code;
	private Integer languageType;
	private Integer difficultyType;
	private Date createTime;
	private String teacherName;
	private List<CodeAnswer> answers;		
	
	public List<CodeAnswer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<CodeAnswer> answers) {
		this.answers = answers;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
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
