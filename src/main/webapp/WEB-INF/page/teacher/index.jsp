<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
    <title>WELCOME</title>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
      <meta http-equiv="Content-Language" content="zh-CN"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/index.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/easyui/themes/gray/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/easyui/themes/icon.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/resources/js/common.js'> </script>
	<script type="text/javascript" src='${pageContext.request.contextPath}/resources/js/init.js'> </script>
    <script type="text/javascript">   
   	 var _menus=undefined;
   	 var path = "${pageContext.request.contextPath}";
    	$.ajax({
  		 type: "GET",
           url: '${pageContext.request.contextPath}/teacher/menu.json',
           dataType: "json",
           async: false,
           success: function (data) {
        	   _menus = data;
           }
  		});
        $(function() {

            //openPwd();
            $('#editpass').click(function() {
                $('#w').window('open');
            });
            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
                    if (r) {
                        location.href = '${pageContext.request.contextPath}/j_spring_security_logout';
                    }
                });

            });		
			
        });

		function select(title, url){
            if ($('#tabs').tabs('exists', title)){
                $('#tabs').tabs('select', title);
            }else{
                $('#tabs').tabs('add',{  
                    title:title, 
                    href:url,
                    closable:true
                });   
            }
        }

    </script>
    <style>
        #head{
            overflow: hidden; height: 100px; background: url(${pageContext.request.contextPath}/resources/images/bg.png) #eeeeee repeat-x center 100%;font-family: Verdana, 微软雅黑,黑体;
        }
        #title {
            float: left;
            width: 320px;
            text-indent: 2%;
            font-size: 24px;
            font-weight: 700;
            font-style: normal;
            text-decoration: none;
            line-height: 50px;
            color: #1E8C99;
        }
    </style>
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">

    <div region="north" id="head" split="true" border="false">
        <span id="title">程序类考试系统-教师端</span>
        <span style="float:left; margin-top:20px" class="head">
            欢迎您！&nbsp;<sec:authentication property="principal.username"/>&nbsp;&nbsp;
            &nbsp;&nbsp;
            <a href="#" style="text-decoration:none;font-size:14px;color: #1E8C99;" id="loginOut">安全退出</a>
        </span>
    </div>

    <div region="south" split="true" style="height: 45px; background: #EEEEEE">
        <div class="footer">
            © 2014 攀枝花学院数学与计算机学院 版权所有 Copyright 王远涛<br/>
            	攀枝花东区机场路10号攀枝花学院
        </div>
    </div>

    <div region="west" split="true" title="导航菜单" style="width:180px;" id="west">
            <div id="accordion" fit="true" border="false">					
			</div>

    </div>
    
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden;" id="home">
				
			<h1>欢迎您 &nbsp;<sec:authentication property="principal.username"/></h1>

			</div>
		</div>
    </div>
    


</body>
</html>