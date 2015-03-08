package cn.pzhu.jsj.pts.bean.program.compile;

public interface Compiler {
	
	/**
	 * 编译
	 */
	boolean compile(String srcName);
	
	String getErrorMsg();
	
	/**
	 * @return 返回编译后可运行程序的文件名
	 */
	String getProgramName();
	
}
