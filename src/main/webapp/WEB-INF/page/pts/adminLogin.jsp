
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>请登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-Language" content="zh-CN"/>
    <meta http-equiv="pragma" content="no-cach" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <script>
    	function endWith(src, s){
    		  if(s==null||s==""||src.length==0||s.length>src.length)
    		     return false;
    		  if(src.substring(src.length-s.length)==s)
    		     return true;
    		  else
    		     return false;
    	}    	
		if(!endWith(window.parent.location.href, "${pageContext.request.contextPath}/adminLogin")){
			window.parent.location.href="${pageContext.request.contextPath}/adminLogin";
		}
    </script>    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/index.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/easyui/themes/gray/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/easyui/themes/icon.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script>
        function submitForm(){
            $('#ff').form('submit');
        }
        function clearForm(){
            $('#ff').form('clear');
        }
    </script>
    <style type="text/css">
        .main{
            width:400px;
            margin:0 auto;
            padding-top: 100px;
        }
        .demo-info{
            background:#FFFEE6;
            color:#FF2200;
            padding:10px;
            width:380px;
        }
        .demo-tip{
            width:16px;
            height:16px;
            margin-right:8px;
            float:left;
        }
    </style>
</head>
<body>
<div class="main">
 <c:if test="${!empty message}">
    <div class="demo-info">
        <div class="demo-tip icon-tip"></div>
        <div>${message}</div>
    </div>
    </c:if>
    <c:if test="${fn:contains(SPRING_SECURITY_LAST_EXCEPTION,'BadCredentialsException')}">
    <div class="demo-info">
        <div class="demo-tip icon-tip"></div>
        <div>密码错误</div>
    </div>
    </c:if>
    <c:if test="${fn:contains(SPRING_SECURITY_LAST_EXCEPTION,'DisabledException')}">
    <div class="demo-info">
        <div class="demo-tip icon-tip"></div>
        <div>该用户被禁止登录</div>
    </div>
    </c:if>
    <c:if test="${fn:contains(SPRING_SECURITY_LAST_EXCEPTION,'AuthenticationServiceException')}">
    <div class="demo-info">
        <div class="demo-tip icon-tip"></div>
        <div>不存在用户 ${SPRING_SECURITY_LAST_USERNAME}</div>
    </div>
    </c:if>
    <div style="margin:5px 0;"></div>
    <div class="easyui-panel" title="请登录" style="width:400px">
        <form id="ff" action='${pageContext.request.contextPath }/j_spring_security_check' method='POST'>
            <div style="padding:10px 0 0px 65px">
                <table>
                    <tr>
                        <td>User:</td>
                        <td><input class="easyui-validatebox" type="text" name="j_username" value="${SPRING_SECURITY_LAST_USERNAME}"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input class="easyui-validatebox" type="password" name="j_password" /></td>
                    </tr>
                </table>
                <div style="padding:15px 0 25px 60px">
                    <input type="submit" class="easyui-linkbutton" value="登录"/>
                    &nbsp;&nbsp;
                    <input type="reset" class="easyui-linkbutton" value="重置"/>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>