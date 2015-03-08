package cn.pzhu.jsj.pts.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.pzhu.jsj.pts.dao.PaperInfoDao;
import cn.pzhu.jsj.pts.domain.PaperInfo;
import cn.pzhu.jsj.pts.domain.PaperInfoExample;
import cn.pzhu.jsj.pts.mapper.PaperInfoMapper;

@Repository("paperInfoDao")
public class PaperInfoDaoImpl implements PaperInfoDao{
	@Autowired
	private PaperInfoMapper paperInfoMapper;

	@Override
	public PaperInfo find(Integer type, Integer questionId, Integer examPaperId) {
		PaperInfoExample paperInfoExample = new PaperInfoExample();
		PaperInfoExample.Criteria criteria = paperInfoExample.createCriteria();
		criteria.andTypeEqualTo(type);
		criteria.andQuestionIdEqualTo(questionId);
		criteria.andPaperIdEqualTo(examPaperId);
		List<PaperInfo> infos = paperInfoMapper.selectByExample(paperInfoExample);
		if(infos.size() == 0){
			return null;
		}
		return infos.get(0);
	}

	@Override
	public boolean insert(PaperInfo paperInfo) {
		return paperInfoMapper.insert(paperInfo) == 1;
	}

	@Override
	public void deleteByPaperId(Integer paperId) {	
		PaperInfoExample paperInfoExample = new PaperInfoExample();
		PaperInfoExample.Criteria criteria = paperInfoExample.createCriteria();
		criteria.andPaperIdEqualTo(paperId);
		paperInfoMapper.deleteByExample(paperInfoExample);
	}

}
