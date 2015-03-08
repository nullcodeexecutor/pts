package cn.pzhu.jsj.pts.dao;

import java.util.Map;

public interface GenerateDao {
	
	Integer countNotSelected(Map<String, Object> map);
	
	Integer randomSelectId(Map<String, Object> map);
	
}
