package cn.pzhu.jsj.pts.common;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-2-20
 * Time: 下午3:10
 * To change this template use File | Settings | File Templates.
 */
public class Constant {
    /**
     *  用户角色
     */
    public static final String USER_ROLE_ADMIN = "ROLE_ADMIN";
    public static final String USER_ROLE_TEACHER = "ROLE_TEACHER";
    /**
     * 用户是否可用  对应user表中的enabled字段
     */
    public static final Integer USER_ENABLED = 1;
    public static final Integer USER_UNENABLED = 1;

    /**
     *  与question_type表中对应,此表的数据不可改变
     */
    public static final Integer QUESTION_TYPE_SELECT = 1;
    public static final Integer QUESTION_TYPE_TF = 2;
    public static final Integer QUESTION_TYPE_CODE = 3;
    
    /**
     * 表示这个题目是否是题库中的题目
     */
    public static final Integer WAREHOUSE_TYPE_YES = 1;
    public static final Integer WAREHOUSE_TYPE_NO = 2;
    
    /**
     * 表示这个题目的困难度
     */
    public static final Integer DIFFICULTY_TYPE_EASY = 1;
    public static final Integer DIFFICULTY_TYPE_GENERAL = 2;
    public static final Integer DIFFICULTY_TYPE_DIFFICULT = 3;

    /**
     *  代码题允许的程序类型,any,c,java
     */
    public static final Integer COMPILER_TYPE_ANY = 1;
    public static final Integer COMPILER_TYPE_C = 2;
    public static final Integer COMPILER_TYPE_JAVA = 3;

    /**
     *  判断题答案对应数据库中的存储
     */
    public static final Integer TF_QUESTION_TRUE = 1;
    public static final Integer TF_QUESTION_FALSE = 0;

    /**
     *  考试类型，平时练习模式和考试模式。考试模式将有开始时间和结束时间
     */
    public static final Integer EXAM_TYPE_EXAM = 1;
    public static final Integer EXAM_TYPE_EXERCISE = 2;
    
    /**
     *  试卷状态  对应exam表中的status字段  1表示没有结束（正常状态），
     *  2表示已经结束（结束的意思并不是考试时间过了，而是表示已经删除，学生不能再看见该考试）
     */
    public static final Integer EXAM_STATUS_NOTEND = 1;
    public static final Integer EXAM_STATUS_END = 2;
    
    /**
     * session中存放的key
     */
    public static final String SESSION_USER = "pts_user";
    public static final String SESSION_STUDENT = "pts_student";
    public static final String SESSION_ANSWER = "pts_answer";

}
