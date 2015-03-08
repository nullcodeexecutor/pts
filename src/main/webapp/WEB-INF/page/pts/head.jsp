
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<ul id="mainNav">
        	<li><a href="${pageContext.request.contextPath}/index">所有考试</a></li> <!-- Use the "active" class for the active menu item  -->
        	<c:if test="${!empty pts_student }">
			<li>
				<a href="${pageContext.request.contextPath}/student/grade/index">查看成绩</a>
			</li>
			</c:if>        	
			<!-- <li><a href="#">考试说明</a></li>	 -->		
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