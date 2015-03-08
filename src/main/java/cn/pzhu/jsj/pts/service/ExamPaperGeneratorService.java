package cn.pzhu.jsj.pts.service;

import cn.pzhu.jsj.pts.dto.GeneratorPaperArgs;

public interface ExamPaperGeneratorService {
	
	Integer generate(Integer examId, Integer examPaperNum, GeneratorPaperArgs selectGeneratorPaperArgs, 
			GeneratorPaperArgs tfGeneratorPaperArgs, GeneratorPaperArgs codeGeneratorPaperArgs);
	
}
