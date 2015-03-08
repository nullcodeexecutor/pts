package cn.pzhu.jsj.pts.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pzhu.jsj.pts.dao.ExamPaperDao;
import cn.pzhu.jsj.pts.dao.PaperInfoDao;
import cn.pzhu.jsj.pts.domain.ExamPaper;
import cn.pzhu.jsj.pts.service.ExamPaperService;
import cn.pzhu.jsj.pts.service.PaperInfoService;

@Service("examPaperService")
public class ExamPaperServiceImpl implements ExamPaperService {
	@Autowired
	private ExamPaperDao examPaperDao;
	@Autowired
	private PaperInfoDao paperInfoDao;

	@Override
	public ExamPaper findRandomByExamId(Integer examId) {
		int count = examPaperDao.findCountByExamId(examId);
		if(count == 0){
			return null;
		}
		int offset = new Random().nextInt(count);
		return examPaperDao.findByExamId(examId, offset, 1).get(0);
	}

	@Override
	public List<ExamPaper> findByExamId(Integer examId, Integer page, Integer pageSize) {
		return examPaperDao.findByExamId(examId, (page-1)*pageSize, pageSize);
	}

	@Override
	public Integer findCountByExamId(Integer examId) {
		return examPaperDao.findCountByExamId(examId);
	}

	@Override
	public boolean deleteById(Integer id) {
		paperInfoDao.deleteByPaperId(id);
		return examPaperDao.deleteById(id);
	}

	@Override
	public ExamPaper findById(Integer id) {
		return examPaperDao.findById(id);
	}

}
