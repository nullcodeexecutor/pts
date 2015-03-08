package cn.pzhu.jsj.pts.service;

import cn.pzhu.jsj.pts.domain.PaperInfo;

public interface PaperInfoService {
	
	PaperInfo find(Integer type, Integer questionId, Integer examPaperId);
	
}
