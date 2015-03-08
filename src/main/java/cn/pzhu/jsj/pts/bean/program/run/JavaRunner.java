package cn.pzhu.jsj.pts.bean.program.run;

import org.springframework.beans.factory.annotation.Autowired;

import cn.pzhu.jsj.pts.bean.program.cmd.CommandInvoker;

public class JavaRunner implements Runner {
	@Autowired
	private CommandInvoker commandInvoker;

	@Override
	public boolean run(String programName, String input) {
		commandInvoker.setCmd("java -classpath "+programName);
		return commandInvoker.execute(input);
	}

	@Override
	public String getErrorMsg() {
		return commandInvoker.getErrorMsg();
	}

	@Override
	public String getNormalMsg() {
		return commandInvoker.getNormalMsg();
	}

}
