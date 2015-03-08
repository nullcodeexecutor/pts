package test;


import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.pzhu.jsj.pts.bean.program.CompileAndRun;
import cn.pzhu.jsj.pts.dao.CodeQuestionDao;
import cn.pzhu.jsj.pts.service.CodeAnswerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/*.xml"})
public class CompileAndRunTest {
	 @Autowired
	 private CompileAndRun cCompileAndRun;
	 @Autowired
	 private CodeQuestionDao codeQuestionDao;
	 @Autowired
	 private CodeAnswerService codeAnswerService;

//    @Test
    public void compileAndRun(){
    	String src = codeQuestionDao.findById(5).getCode();
    	System.out.println(src);
    	String input = "10\n32";
    	System.out.println("result:"+cCompileAndRun.exec(src, input));
    }
    
    @Test
    public void autoJugde(){
    	String src = codeQuestionDao.findById(5).getCode();
		Map<String, String> inputAndOutput = codeAnswerService.findInputOutputByCodeQuestionId(5);
		
    	System.out.println(src);
    	
    	System.out.println("result:"+cCompileAndRun.exec(src, inputAndOutput));
    }
    
}
