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
		</style>
<script type="text/javascript">
	var enabledFormatter = function(enabled){
		return enabled==1 ? '正常' : '禁用';
	}
    function editRow(index){
        $('#teacherdatagrid').datagrid('beginEdit', index);
        var account = $('#teacherdatagrid').datagrid('getEditor', { index: index, field: 'account' });
        $(account.target).attr('disabled',true)
    }
    function deleteRow(index){
        var row = $('#teacherdatagrid').datagrid('getRows')[index];
        $.messager.confirm('Confirm','你确定要删除该课程吗?',function(r){
            if (r){
            	$.ajax({
           		 type: "POST",
                    url: '${pageContext.request.contextPath}/admin/teacher/delete.json',
                    data: "id="+row.teacherId,
                    dataType: "json", 
                    success: function (data) {
                   	  if(data.flg){
                          $('#teacherdatagrid').datagrid('deleteRow', index);
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
    	if(!$('#teacherdatagrid').datagrid('validateRow', index)){
    		return;
    	}
        $('#teacherdatagrid').datagrid('endEdit', index);
        var row = $('#teacherdatagrid').datagrid('getRows')[index];
        $.ajax({
   		 type: "POST",
            url: '${pageContext.request.contextPath}/admin/teacher/modify.json',
            data: "id="+row.teacherId+"&name="+row.name+"&account="+row.account+"&enabled="+row.enabled,
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
                     $('#teacherdatagrid').datagrid('rejectChanges');
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
        var row = $('#teacherdatagrid').datagrid('getRows')[index];
        
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
        	                     $('#teacherdatagrid').datagrid('rejectChanges');
        	                 }
        	           		$("#modifyPwdWindow").dialog("close");   
        	            }
        	   	});
        	}
        });
    }
    function cancelRow(index){
        $('#teacherdatagrid').datagrid('cancelEdit', index);
    }
    function updateActions(index){
		$('#teacherdatagrid').datagrid('updateRow',{
			index: index,
			row:{}
		});
	}
    $(function(){
        $("#teacherdatagrid").datagrid({
            url:'${pageContext.request.contextPath}/admin/teacher/query.json',
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
                {title:'状态',field:'enabled',width:80,align:'left',sortable:false,formatter:enabledFormatter,
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
<table id="teacherdatagrid"></table>

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

</body>
</html>

