package cn.pzhu.jsj.pts.dto;

/**
 * @author xhz
 *
 */
public class GeneratorPaperArgs {
	private Integer languageType;
	private Long startTime;
	private Long endTime;
	private Integer creator;
	private Integer score;
	private Integer questionNum;
	private Integer easyNum;
	private Integer generaNum;
	private Integer diffcultyNum;	
	private Integer teacherId;
	
	public GeneratorPaperArgs(){
		super();
	}
	
	public GeneratorPaperArgs(Integer languageType, Long startTime,
			Long endTime, Integer creator, Integer score, Integer questionNum,
			Integer easyNum, Integer generaNum, Integer diffcultyNum, Integer teacherId) {
		super();
		this.languageType = languageType;
		this.startTime = startTime;
		this.endTime = endTime;
		this.creator = creator;
		this.score = score;
		this.questionNum = questionNum;
		this.easyNum = easyNum;
		this.generaNum = generaNum;
		this.diffcultyNum = diffcultyNum;
		this.teacherId = teacherId;
	}	
	
	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getLanguageType() {
		return languageType;
	}
	public void setLanguageType(Integer languageType) {
		this.languageType = languageType;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getQuestionNum() {
		return questionNum;
	}
	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}
	public Integer getEasyNum() {
		return easyNum;
	}
	public void setEasyNum(Integer easyNum) {
		this.easyNum = easyNum;
	}
	public Integer getGeneraNum() {
		return generaNum;
	}
	public void setGeneraNum(Integer generaNum) {
		this.generaNum = generaNum;
	}
	public Integer getDiffcultyNum() {
		return diffcultyNum;
	}
	public void setDiffcultyNum(Integer diffcultyNum) {
		this.diffcultyNum = diffcultyNum;
	}
	
}
