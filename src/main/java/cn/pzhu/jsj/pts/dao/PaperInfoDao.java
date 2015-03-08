package cn.pzhu.jsj.pts.dao;

import cn.pzhu.jsj.pts.domain.PaperInfo;

public interface PaperInfoDao {
	
	PaperInfo find(Integer type, Integer questionId, Integer examPaperId);
	
	boolean insert(PaperInfo paperInfo);
	
	void deleteByPaperId(Integer paperId);

}
