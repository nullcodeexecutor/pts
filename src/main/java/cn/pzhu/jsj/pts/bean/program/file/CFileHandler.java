package cn.pzhu.jsj.pts.bean.program.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CFileHandler extends FileHandler {
	private String filePath;
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String saveSrc(String src) {
		String srcName = filePath + System.currentTimeMillis() + new Random().nextInt(1000) + ".c";
		File srcFile = new File(srcName);
		if(srcFile.exists()){
			srcFile.delete();
		}
		try {	
			BufferedWriter bw = new BufferedWriter(new FileWriter(srcFile));
			bw.write(src);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return srcName;
	}

}
