
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
    <script type="text/javascript">
    	$(function(){
    		var form = $(".registerForm form");
    		form.submit(function(){
    			var account = form.find("input[name='account']").val();
    			var name = form.find("input[name='name']").val();
    			var pwd = form.find("input[name='pwd']").val();
    			var repwd = form.find("input[name='repwd']").val();
    			return true;
    		});    		
    	});
    </script>
    <style type="text/css">
    	form{
    		width:250px;
    	}
    	.row .error{
			width:210px;
    		color: #AA3333;
			text-align:left;
			margin-bottom:5px;
    	}
    	.row input{
    		margin-bottom:0px;
    		margin-top:10px;
    	}
    </style>
</head>
<body>


	<div id="wrapper">
        <!-- You can name the links with lowercase, they will be transformed to uppercase by CSS, we prefered to name them with uppercase to have the same effect with disabled stylesheet -->
        <%@include file="head.jsp" %>
        <!-- // #end mainNav -->
        
        <div id="containerHolder">
			<div id="container">
				<center>
				<form method="post" action="registry">
					<div class="row">
						${message }
				 	 </div>
				  <div class="row">
					    <input type="text" name="account" value="${values.account}" placeholder="账号" />
					    <div class="error">${errors.account }</div>
				  </div>
				  <div class="row">
				        <input type="text" name="name" value="${values.name}" placeholder="姓名" />
						<div class="error">${errors.name }</div>
				  </div>
				  <div class="row">
				        <input type="password" name="pwd" value="${values.pwd}" placeholder="密码" />
						<div class="error">${errors.pwd }</div>
				  </div>
				  <div class="row">
				        <input type="password" name="repwd" value="${values.repwd}" placeholder="重复密码" />
						<div class="error">${errors.repwd }</div>
				  </div>
				  <div class="row">
				       <input type="submit" class="btn btn-info" value="注册" />
				  </div>
				</form>
				</center>
                <div class="clear"></div>
            </div>
            <!-- // #container -->
        </div>	
        <!-- // #containerHolder -->
        
        <p id="footer">welcome to pts.</a></p>
    </div>

</body>
</html>