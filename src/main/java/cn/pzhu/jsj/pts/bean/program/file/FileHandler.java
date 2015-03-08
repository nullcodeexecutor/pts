package cn.pzhu.jsj.pts.bean.program.file;

import java.io.File;
import java.util.List;

public abstract class FileHandler {
	
	/**
	 * 把源代码存入磁盘，返回文件的名字和路径
	 * @param src
	 * @return
	 */
	public abstract String saveSrc(String src);
	
	/**
	 * 删除源文件和编译后的可运行的程序文件
	 * @param srcPath
	 * @param programPath
	 */
	public void delete(List<String> names){
		for(String name : names){
			File file = new File(name);
			if(file.exists() && file.isFile()){
				file.delete();
			}
		}
	}

}
