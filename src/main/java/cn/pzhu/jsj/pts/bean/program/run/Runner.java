package cn.pzhu.jsj.pts.bean.program.run;

public interface Runner {

	/**
	 * 运行
	 */
	boolean run(String programName, String input);
	
	String getErrorMsg();
	
	/**
	 * 获取到程序正常的输出
	 */
	String getNormalMsg();
}
