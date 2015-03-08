package cn.pzhu.jsj.pts.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class GradeExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public GradeExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andScoreIsNull() {
			addCriterion("score is null");
			return (Criteria) this;
		}

		public Criteria andScoreIsNotNull() {
			addCriterion("score is not null");
			return (Criteria) this;
		}

		public Criteria andScoreEqualTo(Integer value) {
			addCriterion("score =", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreNotEqualTo(Integer value) {
			addCriterion("score <>", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreGreaterThan(Integer value) {
			addCriterion("score >", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
			addCriterion("score >=", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreLessThan(Integer value) {
			addCriterion("score <", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreLessThanOrEqualTo(Integer value) {
			addCriterion("score <=", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreIn(List<Integer> values) {
			addCriterion("score in", values, "score");
			return (Criteria) this;
		}

		public Criteria andScoreNotIn(List<Integer> values) {
			addCriterion("score not in", values, "score");
			return (Criteria) this;
		}

		public Criteria andScoreBetween(Integer value1, Integer value2) {
			addCriterion("score between", value1, value2, "score");
			return (Criteria) this;
		}

		public Criteria andScoreNotBetween(Integer value1, Integer value2) {
			addCriterion("score not between", value1, value2, "score");
			return (Criteria) this;
		}

		public Criteria andStudentIdIsNull() {
			addCriterion("student_id is null");
			return (Criteria) this;
		}

		public Criteria andStudentIdIsNotNull() {
			addCriterion("student_id is not null");
			return (Criteria) this;
		}

		public Criteria andStudentIdEqualTo(Integer value) {
			addCriterion("student_id =", value, "studentId");
			return (Criteria) this;
		}

		public Criteria andStudentIdNotEqualTo(Integer value) {
			addCriterion("student_id <>", value, "studentId");
			return (Criteria) this;
		}

		public Criteria andStudentIdGreaterThan(Integer value) {
			addCriterion("student_id >", value, "studentId");
			return (Criteria) this;
		}

		public Criteria andStudentIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("student_id >=", value, "studentId");
			return (Criteria) this;
		}

		public Criteria andStudentIdLessThan(Integer value) {
			addCriterion("student_id <", value, "studentId");
			return (Criteria) this;
		}

		public Criteria andStudentIdLessThanOrEqualTo(Integer value) {
			addCriterion("student_id <=", value, "studentId");
			return (Criteria) this;
		}

		public Criteria andStudentIdIn(List<Integer> values) {
			addCriterion("student_id in", values, "studentId");
			return (Criteria) this;
		}

		public Criteria andStudentIdNotIn(List<Integer> values) {
			addCriterion("student_id not in", values, "studentId");
			return (Criteria) this;
		}

		public Criteria andStudentIdBetween(Integer value1, Integer value2) {
			addCriterion("student_id between", value1, value2, "studentId");
			return (Criteria) this;
		}

		public Criteria andStudentIdNotBetween(Integer value1, Integer value2) {
			addCriterion("student_id not between", value1, value2, "studentId");
			return (Criteria) this;
		}

		public Criteria andExamIdIsNull() {
			addCriterion("exam_id is null");
			return (Criteria) this;
		}

		public Criteria andExamIdIsNotNull() {
			addCriterion("exam_id is not null");
			return (Criteria) this;
		}

		public Criteria andExamIdEqualTo(Integer value) {
			addCriterion("exam_id =", value, "examId");
			return (Criteria) this;
		}

		public Criteria andExamIdNotEqualTo(Integer value) {
			addCriterion("exam_id <>", value, "examId");
			return (Criteria) this;
		}

		public Criteria andExamIdGreaterThan(Integer value) {
			addCriterion("exam_id >", value, "examId");
			return (Criteria) this;
		}

		public Criteria andExamIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("exam_id >=", value, "examId");
			return (Criteria) this;
		}

		public Criteria andExamIdLessThan(Integer value) {
			addCriterion("exam_id <", value, "examId");
			return (Criteria) this;
		}

		public Criteria andExamIdLessThanOrEqualTo(Integer value) {
			addCriterion("exam_id <=", value, "examId");
			return (Criteria) this;
		}

		public Criteria andExamIdIn(List<Integer> values) {
			addCriterion("exam_id in", values, "examId");
			return (Criteria) this;
		}

		public Criteria andExamIdNotIn(List<Integer> values) {
			addCriterion("exam_id not in", values, "examId");
			return (Criteria) this;
		}

		public Criteria andExamIdBetween(Integer value1, Integer value2) {
			addCriterion("exam_id between", value1, value2, "examId");
			return (Criteria) this;
		}

		public Criteria andExamIdNotBetween(Integer value1, Integer value2) {
			addCriterion("exam_id not between", value1, value2, "examId");
			return (Criteria) this;
		}

		public Criteria andPaperIdIsNull() {
			addCriterion("paper_id is null");
			return (Criteria) this;
		}

		public Criteria andPaperIdIsNotNull() {
			addCriterion("paper_id is not null");
			return (Criteria) this;
		}

		public Criteria andPaperIdEqualTo(Integer value) {
			addCriterion("paper_id =", value, "paperId");
			return (Criteria) this;
		}

		public Criteria andPaperIdNotEqualTo(Integer value) {
			addCriterion("paper_id <>", value, "paperId");
			return (Criteria) this;
		}

		public Criteria andPaperIdGreaterThan(Integer value) {
			addCriterion("paper_id >", value, "paperId");
			return (Criteria) this;
		}

		public Criteria andPaperIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("paper_id >=", value, "paperId");
			return (Criteria) this;
		}

		public Criteria andPaperIdLessThan(Integer value) {
			addCriterion("paper_id <", value, "paperId");
			return (Criteria) this;
		}

		public Criteria andPaperIdLessThanOrEqualTo(Integer value) {
			addCriterion("paper_id <=", value, "paperId");
			return (Criteria) this;
		}

		public Criteria andPaperIdIn(List<Integer> values) {
			addCriterion("paper_id in", values, "paperId");
			return (Criteria) this;
		}

		public Criteria andPaperIdNotIn(List<Integer> values) {
			addCriterion("paper_id not in", values, "paperId");
			return (Criteria) this;
		}

		public Criteria andPaperIdBetween(Integer value1, Integer value2) {
			addCriterion("paper_id between", value1, value2, "paperId");
			return (Criteria) this;
		}

		public Criteria andPaperIdNotBetween(Integer value1, Integer value2) {
			addCriterion("paper_id not between", value1, value2, "paperId");
			return (Criteria) this;
		}

		public Criteria andSubmitTimeIsNull() {
			addCriterion("submit_time is null");
			return (Criteria) this;
		}

		public Criteria andSubmitTimeIsNotNull() {
			addCriterion("submit_time is not null");
			return (Criteria) this;
		}

		public Criteria andSubmitTimeEqualTo(Date value) {
			addCriterion("submit_time =", value, "submitTime");
			return (Criteria) this;
		}

		public Criteria andSubmitTimeNotEqualTo(Date value) {
			addCriterion("submit_time <>", value, "submitTime");
			return (Criteria) this;
		}

		public Criteria andSubmitTimeGreaterThan(Date value) {
			addCriterion("submit_time >", value, "submitTime");
			return (Criteria) this;
		}

		public Criteria andSubmitTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("submit_time >=", value, "submitTime");
			return (Criteria) this;
		}

		public Criteria andSubmitTimeLessThan(Date value) {
			addCriterion("submit_time <", value, "submitTime");
			return (Criteria) this;
		}

		public Criteria andSubmitTimeLessThanOrEqualTo(Date value) {
			addCriterion("submit_time <=", value, "submitTime");
			return (Criteria) this;
		}

		public Criteria andSubmitTimeIn(List<Date> values) {
			addCriterion("submit_time in", values, "submitTime");
			return (Criteria) this;
		}

		public Criteria andSubmitTimeNotIn(List<Date> values) {
			addCriterion("submit_time not in", values, "submitTime");
			return (Criteria) this;
		}

		public Criteria andSubmitTimeBetween(Date value1, Date value2) {
			addCriterion("submit_time between", value1, value2, "submitTime");
			return (Criteria) this;
		}

		public Criteria andSubmitTimeNotBetween(Date value1, Date value2) {
			addCriterion("submit_time not between", value1, value2,
					"submitTime");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table grade
	 * @mbggenerated  Wed May 07 15:57:43 CST 2014
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table grade
     *
     * @mbggenerated do_not_delete_during_merge Mon Apr 14 15:03:25 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}