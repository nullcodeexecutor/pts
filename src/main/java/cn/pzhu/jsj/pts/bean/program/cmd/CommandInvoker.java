package cn.pzhu.jsj.pts.bean.program.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * 命令行调用者
 * 通过java去调用命名
 * 
 * 重要：  如果是去调用window命令，这些命令是输出的字符编码方式是gbk。 
 * 要想在程序中得到正确的字符必须这样
 * BufferedReader br = new BufferedReader(new InputStreamReader(errorStream, "GBK"));
 * 但是，直接运行学生的程序又不一样。这些程序输出的字符编码方式是UTF-8。这就不需要转码。
 * 这个地方比较麻烦。
 * 
 * @author wangyuantao
 */
public class CommandInvoker {
	
	private String cmd;
	private String errorMsg;
	private String normalMsg;
	
	private String envEncoding;

	/**
	 * @param input 表示运行这个命令后，给这个进程的输入数据
	 * @return 当返回true时，表示运行正确，可以得到normalMsg
	 * 		      当返回为false时，表示有错误产生，可以得到errorMsg
	 */
	public boolean execute(String input){
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(this.cmd);
			inputHandle(process.getOutputStream(), input);
			errorHandle(process.getErrorStream());		
			normalHandle(process.getInputStream());
			//不能根据返回值来判断，这和系统有关，和自己写的程序的exit()有关
			process.waitFor();
			if(this.errorMsg != null && !this.errorMsg.equals("")){
				return false;
			}
		} catch (Exception e) {
			errorMsg = "后台调用出现异常";
			return false;
		}
		return true;
	}
	
	public boolean execute(){
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(this.cmd);
			errorHandle(process.getErrorStream());
			normalHandle(process.getInputStream());
			process.waitFor();
			if(this.errorMsg != null && !this.errorMsg.equals("")){
				return false;
			}
		} catch (Exception e) {
			errorMsg = "后台调用出现异常";
			return false;
		}
		return true;
	}
	
	private void inputHandle(OutputStream out, String outText){
		PrintWriter print = new PrintWriter(out);
		print.write(outText);
		print.close();
	}
	
	private void errorHandle(InputStream errorStream) throws IOException{
		StringBuffer message = new StringBuffer("");
		BufferedReader br = new BufferedReader(new InputStreamReader(errorStream, this.envEncoding));
		String line = "";
		while((line=br.readLine())!=null){
			message.append(line+"\n");
		}
		String s = message.toString();
		if(s.length()>1){
			this.errorMsg = s.substring(0, s.length()-1);
		}else{
			this.errorMsg = "";
		}
		br.close();
	}
	
	/**
	 * 为什么inputStream.available()等于0
	 */
	private void normalHandle(InputStream inputStream) throws IOException{
		StringBuffer message = new StringBuffer("");
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		while((line=br.readLine())!=null){
			message.append(line+"\n");
		}
		String s = message.toString();
		if(s.length()>1){
			this.normalMsg = s.substring(0, s.length()-1);
		}else{
			this.normalMsg = "";
		}
		br.close();
	}
	
	public String getErrorMsg(){
		return this.errorMsg;
	}
	
	public String getNormalMsg(){
		return this.normalMsg;
	}
	
	public void setCmd(String cmd){
		this.cmd = cmd;
	}
	
	public String getEnvEncoding() {
		return envEncoding;
	}

	public void setEnvEncoding(String envEncoding) {
		this.envEncoding = envEncoding;
	}
	
}
