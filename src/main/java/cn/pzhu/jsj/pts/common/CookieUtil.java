package cn.pzhu.jsj.pts.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: wangwg
 * Date: 11-5-15
 * Time: 下午12:46
 * To change this template use File | Settings | File Templates.
 */
public class CookieUtil {
     /**
    * 保存Cookie
    * @param request
    * @param response
    * @param cookieName
    * @param value
    * @param expiry                多少秒过期 , 负数表示不保存，关掉浏览器时就删除该cookie
    */
   public static void saveCookie(HttpServletRequest request,   HttpServletResponse response, String cookieName, String value, int expiry) {
       Cookie cookie = new Cookie(cookieName,value);
       /*if (requestUrl.indexOf("cutt.com") >= 0) {
           cookie.setDomain("cutt.com");
       }*/
       cookie.setPath("/");
       if (expiry > 0) {
           cookie.setMaxAge(expiry);
       }
       response.addCookie(cookie);
   }
   /**
    * 获取Cookie
    * @param request
    * @param cookieName
    * @return
    */
   public static Cookie getCookie(HttpServletRequest request, String cookieName) {
       Cookie retCookie = null;
       Cookie[] cookies = request.getCookies();
       if (cookies == null) {
           return null;
       }
       for (Cookie cookie : cookies) {
           String cookiePath = cookie.getPath();
           if ((cookiePath == null || cookiePath.equals("/"))
                   &&  cookieName.trim().equalsIgnoreCase(cookie.getName().trim())) {
               retCookie = cookie;
               break;
           }
       }
       return retCookie;
   }
}
