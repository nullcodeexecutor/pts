
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>查看成绩</title>
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
         <%@include file="../pts/head.jsp" %>
        <!-- // #end mainNav -->
        
        <div id="containerHolder">
			<div id="container">				
				<h6>${pts_student.name }
				&nbsp;&nbsp;${pts_student.account }
				&nbsp;&nbsp;${pts_student.pwd }
				&nbsp;&nbsp;${pts_student.className }
				&nbsp;&nbsp;${pts_student.departName }
				</h6>
				${course.name }&nbsp;&nbsp;&nbsp;&nbsp;
				${course.teacherName }<br/>
				${course.description }<br/><br/>
				<table class="table table-bordered">
				  <thead>
				    <tr>
				      <th>考试名称</th>
				      <th>提交时间</th>
				      <th>类型</th>
				      <th>开始时间</th>
				      <th>结束时间</th>
				      <th>满分</th>
				      <th>分数</th>
				    </tr>
				  </thead>
				  <tbody>
				  	<c:forEach items="${grades}" var="grade">
				    <tr>
				      <td><a href="${pageContext.request.contextPath}/entry?examId=${grade.examId}" target="_blank">${grade.examName}</a></td>
				      <td>${grade.submitTimeStr}</td>
				      <td>${grade.typeStr}</td>
				      <td>${grade.startTimeStr}</td>
				      <td>${grade.endTimeStr}</td>
				      <td>${grade.examScore}</td>
				      <td>${grade.score}</td>
				    </tr>
				    </c:forEach>
				  </tbody>
				</table>
                <div class="clear"></div>
            </div>
            <!-- // #container -->
        </div>	
        <!-- // #containerHolder -->
        
        <p id="footer">welcome to pts.</a></p>
    </div>
    

</body>
</html>