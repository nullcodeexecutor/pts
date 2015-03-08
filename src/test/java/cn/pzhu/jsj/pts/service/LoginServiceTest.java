package cn.pzhu.jsj.pts.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: wangyuantao
 * Date: 14-1-27
 * Time: 下午2:13
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/*.xml"})
public class LoginServiceTest {
    Log log = LogFactory.getLog(LoginServiceTest.class);
    @Autowired
    private LoginService loginService;

    @Test
    public void login(){
        log.error("error haha");
    }
}
