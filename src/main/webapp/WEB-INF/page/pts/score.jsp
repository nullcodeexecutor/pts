
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
	<link href="${pageContext.request.contextPath}/resources/css/transdmin.css" rel="stylesheet" type="text/css" media="screen" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap/css/bootstrap.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/knockout/knockout-3.0.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/css/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/css/bootstrap/js/bootstrap-paginator.js"></script>
    <style type="text/css">
    </style>
</head>
<body>

	<div id="wrapper">
        <!-- You can name the links with lowercase, they will be transformed to uppercase by CSS, we prefered to name them with uppercase to have the same effect with disabled stylesheet -->
        <ul id="mainNav">
        	<li><a href="${pageContext.request.contextPath}/index" class="active">所有考试</a></li> <!-- Use the "active" class for the active menu item  -->
        	<li><a href="#">考试说明</a></li>
        	<li class="logout">
	        	<c:if test="${empty SPRING_SECURITY_CONTEXT.authentication }">
					<a href="${pageContext.request.contextPath}/adminLogin">管理入口</a>
				</c:if>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a href="${pageContext.request.contextPath}/admin/index" target="_blank">进入管理页</a>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_TEACHER')">
					<a href="${pageContext.request.contextPath}/teacher/index" target="_blank">进入管理页</a>
				</sec:authorize>
			</li>

        	
        </ul>
        <!-- // #end mainNav -->
        
        <div id="containerHolder">
			<div id="container">
				分数：${score }
				
                <div class="clear"></div>
            </div>
            <!-- // #container -->
        </div>	
        <!-- // #containerHolder -->
        
        <p id="footer">welcome to pts.</a></p>
    </div>
    

</body>
</html>