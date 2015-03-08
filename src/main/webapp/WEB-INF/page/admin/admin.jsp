<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
		<style>
			#modifyPwdWindow{
				padding: 20px;
			}
			#addAdminWindow{
				padding: 20px;
			}
		</style>
<script type="text/javascript">
	var enabledFormatter = function(enabled){
		return enabled==1 ? '正常' : '禁用';
	}
    function editRow(index){
        $('#admindatagrid').datagrid('beginEdit', index);
        var account = $('#admindatagrid').datagrid('getEditor', { index: index, field: 'account' });
        $(account.target).attr('disabled',true)
    }
    function deleteRow(index){
        var row = $('#admindatagrid').datagrid('getRows')[index];
        $.messager.confirm('Confirm','你确定要删除该课程吗?',function(r){
            if (r){
            	$.ajax({
           		 type: "POST",
                    url: '${pageContext.request.contextPath}/admin/admin/delete.json',
                    data: "id="+row.adminId,
                    dataType: "json", 
                    success: function (data) {
                   	  if(data.flg){
                          $('#admindatagrid').datagrid('deleteRow', index);
                         	$.messager.show({
                         		title:'成功',
                         		msg:'删除成功',
                         		timeout:3000,
                         		showType:'slide'
                         	});
                         }else{
                       	  	$.messager.alert('删除失败','删除失败','info');
                         }
                    }
           		});
            }
        });
    }
    function saveRow(index){
    	if(!$('#admindatagrid').datagrid('validateRow', index)){
    		return;
    	}
        $('#admindatagrid').datagrid('endEdit', index);
        var row = $('#admindatagrid').datagrid('getRows')[index];
        $.ajax({
   		 type: "POST",
            url: '${pageContext.request.contextPath}/admin/admin/modify.json',
            data: "id="+row.adminId+"&account="+row.account+"&name="+row.name+"&email="+row.email+"&enabled="+row.enabled,
            dataType: "json", 
            success: function (data) {
           	  if(data.flg){
                 	$.messager.show({
                 		title:'成功',
                 		msg:"更新成功",
                 		timeout:3000,
                 		showType:'slide'
                 	});
                 }else{
               	  $.messager.alert('更新失败',"更新失败",'info');
                     $('#admindatagrid').datagrid('rejectChanges');
                 }
            }
   		});
    }
    
    function modifyPwd(index){
    	$('#modifyPwdWindow').dialog({   
    	    title: '修改密码',   
    	    width: 250,   
    	    height: 180,   
    	    closed: false,   
    	    cache: false,   
    	    modal: true  
    	});   
        var row = $('#admindatagrid').datagrid('getRows')[index];
        
        $("#modifyPwd").unbind("click")
        $("#modifyPwd").click(function(){
        	var pwd = $("#modifyPwdWindow input[name='pwd']");
        	var repwd = $("#modifyPwdWindow input[name='repwd']");
        	if(pwd.validatebox("isValid") && repwd.validatebox("isValid")){
        		if(pwd.val()!=repwd.val()){
                 	 $.messager.alert('提示',"两次键入额密码必须一致",'info');
        			return;
        		}	
        		 $.ajax({
        	   		 type: "POST",
        	            url: '${pageContext.request.contextPath}/admin/user/modifyPwd.json',
        	            data: "account="+row.account+"&pwd="+pwd.val(),
        	            dataType: "json", 
        	            success: function (data) {
        	           	  if(data.flg){
        	                 	$.messager.show({
        	                 		title:'成功',
        	                 		msg:"修改成功",
        	                 		timeout:3000,
        	                 		showType:'slide'
        	                 	});
        	                 }else{
        	               	  $.messager.alert('更新失败',"修改失败",'info');
        	                     $('#admindatagrid').datagrid('rejectChanges');
        	                 }
        	           		$("#modifyPwdWindow").dialog("close");   
        	            }
        	   	});
        	}
        });
    }
    function cancelRow(index){
        $('#admindatagrid').datagrid('cancelEdit', index);
    }
    function updateActions(index){
		$('#admindatagrid').datagrid('updateRow',{
			index: index,
			row:{}
		});
	}
    function cancelNewRow(index){
        $('#admindatagrid').datagrid('deleteRow', index);
    	newRow = undefined;
    }
    function addAdmin(){
        $("#addAdmin").unbind("click")
        $("#addAdmin").click(function(){
        	var w = $("#addAdminWindow");

        	var account = w.find("input[name='account']");
        	var name = w.find("input[name='name']");
        	var email = w.find("input[name='email']");
        	var status = w.find("select[name='status'] option:selected").val();
        	var pwd = w.find("input[name='pwd']");
        	var repwd = w.find("input[name='repwd']");
        	
        	if(account.validatebox("isValid") && name.validatebox("isValid") && email.validatebox("isValid") && pwd.validatebox("isValid") && repwd.validatebox("isValid")){
        		if(pwd.val()!=repwd.val()){
                 	 $.messager.alert('提示',"两次键入额密码必须一致",'info');
        			return;
        		}	
        		var args;
        		if(email!=''){
        			args = "account="+account.val()+"&name="+name.val()+"&pwd="+pwd.val()+"&email="+email.val()+"&enabled="+status;
        		}else{
        			args = "account="+account.val()+"&name="+name.val()+"&pwd="+pwd.val()+"&enabled="+status;
        		}
        		 $.ajax({
        	   		 type: "POST",
        	            url: '${pageContext.request.contextPath}/admin/admin/insert.json',
        	            data: args,
        	            dataType: "json", 
        	            success: function (data) {
        	           	  if(data.flg){
        	           		  	account.val("");
        	           		  	name.val("");
        	           		  	email.val("");
        	           		  	pwd.val("");
        	           		  	repwd.val("");
          	           			$("#addAdminWindow").dialog("close");  
        	                 	$.messager.show({
        	                 		title:'成功',
        	                 		msg:"添加成功",
        	                 		timeout:3000,
        	                 		showType:'slide'
        	                 	});
       	                     	$('#admindatagrid').datagrid('reload');
        	                 }else{
        	               	  $.messager.alert('添加失败',data.msg,'info');
        	                 } 
        	            }
        	   	});
        	}
        	
        });    	
    }
    $(function(){
        $("#admindatagrid").datagrid({
            url:'${pageContext.request.contextPath}/admin/admin/query.json',
            singleSelect : true,
            columns : [[
                {title:'账号',field:'account',width:50,align:'left',sortable:false,
                    editor: {
                        type:'validatebox',
                        options : {
                            required: true,
                            validType: 'length[2,20]',
                            valueField : 'name'
                        }
                    }
                },
                {title:'姓名',field:'name',width:50,align:'left',sortable:false,
                    editor: {
                        type:'validatebox',
                        options : {
                            required: true,
                            validType: 'length[2,20]',
                            valueField : 'name'
                        }
                    }
                },
                {title:'邮箱',field:'email',width:70,align:'left',sortable:false,
                    editor: {
                        type:'validatebox',
                        options : {
                            validType: 'email',
                            valueField : 'name'
                        }
                    }
                },
                {title:'状态',field:'enabled',width:50,align:'left',sortable:false,formatter:enabledFormatter,
                	editor:{
                        type:'combobox',
                        options:{
                            valueField:'enabled',
                            textField:'text',
                            data: [{
                                "enabled":1,
                                "text":"正常"
                            },{
                                "enabled":0,
                                "text":"禁用"
                            }],
                            required:true
                        }
                    }
                },
                {field:'action',title:'操作',width:100,align:'left',
                    formatter:function(value,row,index){  
                        if (row.editing){  
                            var s = '<a class="easyui-linkbutton" onclick="saveRow('+index+')">更新</a>';
                            var c = '<a class="easyui-linkbutton" onclick="cancelRow('+index+')">取消</a> ';  
                            return c+s;  
                        } else {  
                        	var a = '<a class="easyui-linkbutton" onclick="modifyPwd('+index+')">修改密码</a>&nbsp;&nbsp;&nbsp;&nbsp;';
                            var e = '<a class="easyui-linkbutton" onclick="editRow('+index+')">编辑</a> ';  
                            var d = '<a class="easyui-linkbutton" onclick="deleteRow('+index+')">删除</a>';  
                            return a+e+d;  
                        }  
                    }  
                }  
            ]],
            idField : 'teacherId',
            toolbar: [{
                text: '添加', iconCls: 'icon-add', handler: function () {
                	$('#addAdminWindow').dialog({   
                	    title: '增加管理员',   
                	    width: 250,   
                	    height: 330,   
                	    closed: false,   
                	    cache: false,   
                	    modal: true  
                	});   
                	addAdmin();
                }
            }],
            pagination : true,
            pageSize : 20,
            pageList : [10, 20, 30],
            fitColumns : true,
            rownumbers : true,
            maximized : true,
            minimizable : false,
            remoteSort : false,
            border : false,
            onBeforeEdit:function(index,row){
				row.editing = true;
				updateActions(index);
			},
			onAfterEdit:function(index,row){
				row.editing = false;
				updateActions(index);
			},
			onCancelEdit:function(index,row){
				row.editing = false;
				updateActions(index);
			}
        });
    });
</script>
</head>
<body>
<table id="admindatagrid"></table>

<div id="modifdyPwd" class="easyui-dialog" title="修改密码" style="width:400px;height:200px;"  
        data-options="iconCls:'icon-save',resizable:false,modal:true,closed: true">  
</div>  
<div id="modifyPwdWindow">	
	<span>
		<label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
		<input name="pwd" type="password" class="easyui-validatebox" data-options="required:true" />  
	</span><br/><br/>
	<span>
		<label>重复密码:</label>
		<input name="repwd" type="password" class="easyui-validatebox" data-options="required:true" />  
	</span><br/><br/>
	<center>
		<a id="modifyPwd" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>  
	</center>
</div>  
<div id="addAdminWindow">	
	<span>
		<label>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</label>
		<input name="account" type="text" class="easyui-validatebox" data-options="required:true" />  
	</span><br/><br/>
	<span>
		<label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
		<input name="name" type="text" class="easyui-validatebox" data-options="required:true" />  
	</span><br/><br/>
	<span>
		<label>电子邮箱:</label>
		<input name="email" type="text" class="easyui-validatebox" data-options="validType: 'email'" />  
	</span><br/><br/>
	<span>
		<label>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</label>
		<select name="status">
				<option value="1">启用</option>
				<option value="0">禁用</option>
		</select>
	</span><br/><br/>
	<span>
		<label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
		<input name="pwd" type="password" class="easyui-validatebox" data-options="required:true" />  
	</span><br/><br/>
	<span>
		<label>重复密码:</label>
		<input name="repwd" type="password" class="easyui-validatebox" data-options="required:true" />  
	</span><br/><br/>
	<center>
		<a id="addAdmin" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>  
	</center>
</div>  

</body>
</html>

