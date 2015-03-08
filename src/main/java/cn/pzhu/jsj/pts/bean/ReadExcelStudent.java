package cn.pzhu.jsj.pts.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import cn.pzhu.jsj.pts.domain.Student;

public class ReadExcelStudent {
	private String xlsPath;	
	
	public List<Student> readStudent(File file) throws Exception{
		List<Student> stus = new ArrayList<Student>();
		InputStream in = null;
		try{
			in = new FileInputStream(file);
			Workbook wb = WorkbookFactory.create(in); 
	        Cell cell = null;
	        Sheet st = wb.getSheetAt(0);
	        for (int rowIndex=0; rowIndex<st.getLastRowNum(); rowIndex++) {
	        	Student student = new Student();
	            Row row = st.getRow(rowIndex);
	            if (row == null) {
	                continue;
	            }
	            for (short columnIndex=0, size=row.getLastCellNum(); columnIndex<size; columnIndex++) {
	                cell = row.getCell(columnIndex);
	                if(null != cell){
	                	setValue(cell, columnIndex, student);
	                }
	            }
	            if(!(student.getAccount() == null || student.getAccount().equals(""))){
					stus.add(student);
	            }
	        }
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return stus;
	}
	
	public void delete(File file){
		if(file.exists() && file.isFile()){
			file.delete();
		}
	}

	private void setValue(Cell cell, int index, Student student){
		String value = cell.getStringCellValue();
		if(index == 0){
			student.setAccount(value);
		}else if(index == 1){
			student.setName(value);
		}else if(index == 2){
			student.setPwd(value);
		}else if(index == 3){
			student.setClassName(value);
		}else if(index == 4){
			student.setDepartName(value);
		}
	}

	public String getXlsPath() {
		return xlsPath;
	}

	public void setXlsPath(String xlsPath) {
		this.xlsPath = xlsPath;
	}	
	
}
