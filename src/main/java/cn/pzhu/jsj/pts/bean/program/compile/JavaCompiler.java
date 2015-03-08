package cn.pzhu.jsj.pts.bean.program.compile;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import cn.pzhu.jsj.pts.bean.program.cmd.CommandInvoker;

/**
 * c语言的编译器
 */
public class JavaCompiler implements Compiler {
	@Autowired
	private CommandInvoker commandInvoker;
	
	private String programName;
	
	private String filePath;
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public boolean compile(String srcName) {
		this.programName = filePath + " Main";
		commandInvoker.setCmd("javac "+filePath+"Main.java");
		return commandInvoker.execute();
	}

	@Override
	public String getErrorMsg() {
		return commandInvoker.getErrorMsg();
	}

	@Override
	public String getProgramName() {
		return this.programName;
	}

}
