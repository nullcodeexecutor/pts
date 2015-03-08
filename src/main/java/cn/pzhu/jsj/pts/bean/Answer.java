package cn.pzhu.jsj.pts.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-27
 * Time: 下午6:09
 */
public class Answer {

	private Integer studentId;
    private Integer examId;
    private Integer paperId;
    private Integer score;
    private Long startTime;
    private Long endTime;
    
//    private Map<Integer, Map<Integer, Boolean>> answer = null;
    
    private Map<Integer, Boolean> selectAnswer = null;
    private Map<Integer, Boolean> tfAnswer = null;
    private Map<Integer, Boolean> codeAnswer = null;

    public Answer(Integer studentId, Integer examId){
    	this.studentId = studentId;
        this.examId = examId;
        this.score = 0;
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

	public void addSelectAnswer(Integer questionId){
    	if(selectAnswer == null){
        	selectAnswer = new HashMap<Integer, Boolean>();
    	}
    	selectAnswer.put(questionId, null);
    }
    
    public void addTFAnswer(Integer questionId){
    	if(tfAnswer == null){
    		tfAnswer = new HashMap<Integer, Boolean>();
    	}
    	tfAnswer.put(questionId, null);
    }
    
    public void addCodeAnswer(Integer questionId){
    	if(codeAnswer == null){
    		codeAnswer = new HashMap<Integer, Boolean>();
    	}
    	codeAnswer.put(questionId, null);
    }

    public void setSelectAnswer(Integer questionId, Boolean isCorrect, int theScore) {
    	synchronized(score){
	    	if(!selectAnswer.containsKey(questionId)){
	    		return;
	    	}
	    	if(selectAnswer.get(questionId) == null){
	    		selectAnswer.put(questionId, false);
	    	}
	    	Boolean previousIsCorrect = selectAnswer.get(questionId);
	    	if(previousIsCorrect ^ isCorrect){//表示两个bool值不一致
	          if(previousIsCorrect){//表示previousIsCorrect为true，isCorrect为false。这种情况要减分
	              score -= theScore;
	          }else{//表示previousIsCorrect为false，isCorrect为true。这种情况要加分
	              score += theScore;
	          }
	          selectAnswer.put(questionId, isCorrect);
	    	}
    	}
	}

	public void setTfAnswer(Integer questionId, Boolean isCorrect, int theScore) {
		synchronized(score){
			if(!tfAnswer.containsKey(questionId)){
	    		return;
	    	}
	    	if(tfAnswer.get(questionId) == null){
	    		tfAnswer.put(questionId, false);
	    	}
	    	Boolean previousIsCorrect = tfAnswer.get(questionId);
	    	if(previousIsCorrect ^ isCorrect){//表示两个bool值不一致
	          if(previousIsCorrect){//表示previousIsCorrect为true，isCorrect为false。这种情况要减分
	              score -= theScore;
	          }else{//表示previousIsCorrect为false，isCorrect为true。这种情况要加分
	              score += theScore;
	          }
	          tfAnswer.put(questionId, isCorrect);
	    	}
		}
	}

	public void setCodeAnswer(Integer questionId, Boolean isCorrect, int theScore) {
		synchronized(score){
			if(!codeAnswer.containsKey(questionId)){
	    		return;
	    	}
	    	if(codeAnswer.get(questionId) == null){
	    		codeAnswer.put(questionId, false);
	    	}
	    	Boolean previousIsCorrect = codeAnswer.get(questionId);
	    	if(previousIsCorrect ^ isCorrect){//表示两个bool值不一致
	          if(previousIsCorrect){//表示previousIsCorrect为true，isCorrect为false。这种情况要减分
	              score -= theScore;
	          }else{//表示previousIsCorrect为false，isCorrect为true。这种情况要加分
	              score += theScore;
	          }
	          codeAnswer.put(questionId, isCorrect);
	    	}
		}
	}
    

//    /**
//     * setAnswer(1, 2, true, 5);
//     * @param type
//     * @param questionId
//     * @param isCorrect
//     * @param theScore
//     */
//    public void setAnswer(Integer type, Integer questionId, Boolean isCorrect, int theScore){
//        if(null == answer){
//            answer = new HashMap<Integer, Map<Integer, Boolean>>(4);
//        }
//        if(answer.containsKey(type) && answer.get(type) != null){
//            Boolean previousIsCorrect = answer.get(type).get(questionId);
//            if(null == previousIsCorrect){
//                if(isCorrect){
//                    score += theScore;
//                }
//            }else{
//                if(previousIsCorrect ^ isCorrect){//表示两个bool值不一致
//                      if(previousIsCorrect){//表示previousIsCorrect为true，isCorrect为false。这种情况要减分
//                          score -= theScore;
//                      }else{//表示previousIsCorrect为false，isCorrect为true。这种情况要加分
//                          score += theScore;
//                      }
//                }
//            }
//            answer.get(type).put(questionId, isCorrect);
//        }else{
//            Map<Integer, Boolean> map = new HashMap<Integer, Boolean>(10);
//            map.put(questionId, isCorrect);
//            answer.put(type, map);
//        }
//    }

//    public Boolean getAnswer(Integer type, Integer questionId){
//        if(null == answer){
//            return null;
//        }
//        if(null == answer.get(type)){
//            return null;
//        }
//        if(null == answer.get(type).get(questionId)){
//            return null;
//        }
//        return answer.get(type).get(questionId);
//    }    
    
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	
	/**
	 * 是否每道题都做了
	 * @return
	 */
	public boolean isComplete(){
		for(Integer questionId : selectAnswer.keySet()){
			if(selectAnswer.get(questionId) == null){
				return false;
			}
		}
		for(Integer questionId : tfAnswer.keySet()){
			if(tfAnswer.get(questionId) == null){
				return false;
			}
		}
		for(Integer questionId : codeAnswer.keySet()){
			if(codeAnswer.get(questionId) == null){
				return false;
			}
		}
		return true;
	}
    
}
