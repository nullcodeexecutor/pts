package cn.pzhu.jsj.pts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pzhu.jsj.pts.dao.PaperInfoDao;
import cn.pzhu.jsj.pts.domain.PaperInfo;
import cn.pzhu.jsj.pts.service.PaperInfoService;

@Service("paperInfoService")
public class PaperInfoServiceImpl implements PaperInfoService {
	@Autowired
	private PaperInfoDao paperInfoDao;

	@Override
	public PaperInfo find(Integer type, Integer questionId, Integer examPaperId) {
		return paperInfoDao.find(type, questionId, examPaperId);
	}

}
