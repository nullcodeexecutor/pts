package cn.pzhu.jsj.pts.service;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-1-27
 * Time: 上午11:33
 * To change this template use File | Settings | File Templates.
 */
public interface LoginService {

	Object loadUserByAccountAndRole(String account, String role);

}
