package cn.pzhu.jsj.pts.bean.program;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.pzhu.jsj.pts.bean.program.compile.Compiler;
import cn.pzhu.jsj.pts.bean.program.file.FileHandler;
import cn.pzhu.jsj.pts.bean.program.run.Runner;

public class CompileAndRun {
	private FileHandler fileHandler;
	private Compiler compiler;
	private Runner runner;

	/**
	 * 通过程序源文件和输入数据得到程序输出
	 * @param src
	 * @param input
	 * @return
	 */
	public String exec(String src, String input){
    	List<String> names = new ArrayList<String>(2);
    	try{
	    	String srcName = fileHandler.saveSrc(src);
	    	names.add(srcName);
	    	
	    	if(!compiler.compile(srcName)){
	    		return compiler.getErrorMsg();
	    	}	    	
	    	String programName = compiler.getProgramName();
	    	
	    	names.add(programName);
	    	if(!runner.run(programName, input)){
	    		return runner.getErrorMsg();
	    	}
	    	return runner.getNormalMsg();
    	}finally{
    		//删除产生的源码文件和程序文件
    		fileHandler.delete(names);
    	}
    }
    
    public boolean exec(String src,  Map<String, String> inputOutput){
    	List<String> names = new ArrayList<String>(2);
    	try{
	    	String srcName = fileHandler.saveSrc(src);
	    	names.add(srcName);
	    	
	    	if(!compiler.compile(srcName)){
	    		return false;
	    	}	    	
	    	String programName = compiler.getProgramName();
	    	
	    	names.add(programName);
	    	
	    	for(String input : inputOutput.keySet()){
	    		String output = inputOutput.get(input);
	    		if(!runner.run(programName, input)){
		    		return false;
		    	}
	    		System.out.println(runner.getNormalMsg()+"---");
	    		if(!runner.getNormalMsg().equals(output)){
	    			return false;
	    		}
	    	}
	    	return true;
    	}finally{
    		//删除产生的源码文件和程序文件
    		fileHandler.delete(names);
    	}
    }

	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}

	public void setCompiler(Compiler compiler) {
		this.compiler = compiler;
	}

	public void setRunner(Runner runner) {
		this.runner = runner;
	}  
    

}
