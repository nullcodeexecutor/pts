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
	<script type="text/javascript" src='${pageContext.request.contextPath}/resources/js/init.js'> </script>
    <script type="text/javascript">
   	 var _menus=undefined;
   	 var path = "${pageContext.request.contextPath}";
    	$.ajax({
  		 type: "GET",
           url: '${pageContext.request.contextPath}/admin/menu.json',
           dataType: "json",
           async: false,
           success: function (data) {
        	   _menus = data;
           }
  		});
        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        }
        //关闭登录窗口
        function close() {
            $('#w').window('close');
        }

        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');

            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }

            $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
                msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
                $newpass.val('');
                $rePass.val('');
                close();
            })
            
        }

        $(function() {

            //openPwd();
            $('#editpass').click(function() {
                $('#w').window('open');
            });

            $('#btnEp').click(function() {
                serverLogin();
            })         

            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
                    if (r) {
                        location.href = '${pageContext.request.contextPath}/j_spring_security_logout';
                    }
                });

            })


			
			
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
        <span id="title">程序类考试系统-管理员端</span>
        <span style="float:left; margin-top:20px" class="head">
            欢迎您！<sec:authentication property="principal.username"/>&nbsp;&nbsp;
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
    
    
    <!--修改密码窗口
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="Password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="Password" class="txt01" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)"
                        onclick="closeLogin()">取消</a>
            </div>
        </div>
    </div>
    -->

	<%--<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>--%>


</body>
</html>