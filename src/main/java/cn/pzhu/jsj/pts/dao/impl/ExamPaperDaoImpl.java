package cn.pzhu.jsj.pts.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.pzhu.jsj.pts.dao.ExamPaperDao;
import cn.pzhu.jsj.pts.domain.ExamPaper;
import cn.pzhu.jsj.pts.domain.ExamPaperExample;
import cn.pzhu.jsj.pts.mapper.ExamPaperMapper;

@Repository("examPaperDao")
public class ExamPaperDaoImpl implements ExamPaperDao{
	@Autowired
	private ExamPaperMapper examPaperMapper;

	@Override
	public Integer findCountByExamId(Integer examId) {
		ExamPaperExample examPaperExample = new ExamPaperExample();
		ExamPaperExample.Criteria criteria = examPaperExample.createCriteria();
		criteria.andExamIdEqualTo(examId);
		return examPaperMapper.countByExample(examPaperExample);
	}

	@Override
	public List<ExamPaper> findByExamId(Integer examId, Integer offset,
			Integer size) {
		ExamPaperExample examPaperExample = new ExamPaperExample();
		ExamPaperExample.Criteria criteria = examPaperExample.createCriteria();
		criteria.andExamIdEqualTo(examId);
		examPaperExample.setOrderByClause(new StringBuffer("id asc limit ").append(offset).append(",").append(size).toString());
		return examPaperMapper.selectByExample(examPaperExample);
	}

	@Override
	public void deleteByExamId(Integer examId) {
		ExamPaperExample examPaperExample = new ExamPaperExample();
		ExamPaperExample.Criteria criteria = examPaperExample.createCriteria();
		criteria.andExamIdEqualTo(examId);
		examPaperMapper.deleteByExample(examPaperExample);
	}

	@Override
	public boolean insert(ExamPaper examPaper) {
		return 1 == examPaperMapper.insert(examPaper);
	}

	@Override
	public List<ExamPaper> findByExamId(Integer examId) {
		ExamPaperExample examPaperExample = new ExamPaperExample();
		ExamPaperExample.Criteria criteria = examPaperExample.createCriteria();
		criteria.andExamIdEqualTo(examId);
		return examPaperMapper.selectByExample(examPaperExample);
	}

	@Override
	public boolean deleteById(Integer id) {
		return 1 == examPaperMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ExamPaper findById(Integer id) {
		return examPaperMapper.selectByPrimaryKey(id);
	}

}
