package cn.pzhu.jsj.pts.dto;

import java.util.Date;

import org.joda.time.DateTime;

import cn.pzhu.jsj.pts.common.Constant;

/**
 * 用于学生查看成绩时使用
 * @author xhz
 *
 */
public class ExamGradeDto {
	private Integer paperId;
	private Integer score;
	private Date submitTime;
	private String submitTimeStr;
	
	private Integer examId;
	private Integer examScore;
	private String examName;
	private Date startTime;
	private Date endTime;
	private String startTimeStr;
	private String endTimeStr;
	private Integer type;
	private String typeStr;	
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
		this.typeStr = type==Constant.EXAM_TYPE_EXAM ? "考试" : "练习";
	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
		this.submitTimeStr = new DateTime(submitTime.getTime()).toString("yyyy/MM/dd HH:mm");
	}
	public String getSubmitTimeStr() {
		return submitTimeStr;
	}
	public void setSubmitTimeStr(String submitTimeStr) {
		this.submitTimeStr = submitTimeStr;
	}
	public Integer getExamScore() {
		return examScore;
	}
	public void setExamScore(Integer examScore) {
		this.examScore = examScore;
	}
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		this.startTimeStr = new DateTime(startTime.getTime()).toString("yyyy/MM/dd HH:mm");
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
		this.endTimeStr = new DateTime(endTime.getTime()).toString("yyyy/MM/dd HH:mm");
	}
	public String getStartTimeStr() {
		return startTimeStr;
	}
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}
	public String getEndTimeStr() {
		return endTimeStr;
	}
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}	
	
}
