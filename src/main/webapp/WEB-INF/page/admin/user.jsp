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
<script type="text/javascript">
	var enabledFormatter = function(enabled){
		return enabled==1 ? '正常' : '禁用';
	}
	var roleFormatter = function(role){
		if(role=='ROLE_ADMIN') return '管理员';
		if(role=='ROLE_TEACHER') return '教师';
		return "";
	}
    function editRow(index){
        $('#userdatagrid').datagrid('beginEdit', index);
    }
    function deleteRow(index){
        var row = $('#userdatagrid').datagrid('getRows')[index];
        $.messager.confirm('Confirm','你确定要删除该记录吗?',function(r){
            if (r){
            	$.ajax({
           		 type: "POST",
                    url: '${pageContext.request.contextPath}/admin/user/delete.json',
                    data: "id="+row.id,
                    dataType: "json", 
                    success: function (data) {
                   	  if(data.flg){
                          $('#userdatagrid').datagrid('deleteRow', index);
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
    	if(!$('#userdatagrid').datagrid('validateRow', index)){
    		return;
    	}
        $('#userdatagrid').datagrid('endEdit', index);
        var row = $('#userdatagrid').datagrid('getRows')[index];
        $.ajax({
     		 type: "POST",
              url: '${pageContext.request.contextPath}/admin/user/modify.json',
              data: "id="+row.id+"&enabled="+row.enabled+"&password="+row.password,
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
                 	  	$.messager.alert('提示',"修改失败",'info');
                   }
             	 $('#userdatagrid').datagrid('reload');
              }
     		});
    }
    function cancelRow(index){
        $('#userdatagrid').datagrid('cancelEdit', index);
    }
    function updateActions(index){
		$('#userdatagrid').datagrid('updateRow',{
			index: index,
			row:{}
		});
	}
    function updateEnabled(index, enabled){
        var row = $('#userdatagrid').datagrid('getRows')[index];
        $.ajax({
      		 type: "POST",
               url: '${pageContext.request.contextPath}/admin/user/modify.json',
               data: "id="+row.id+"&enabled="+enabled,
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
                  	  	$.messager.alert('提示',"修改失败",'info');
                    }
              	 $('#userdatagrid').datagrid('reload');
               }
      		});
    }
    $(function(){
        $("#userdatagrid").datagrid({
            url:'${pageContext.request.contextPath}/admin/user/query.json',
            singleSelect : true,
            columns : [[
                {title:'账号',field:'name',width:100,align:'left',sortable:false},
                {title:'密码',field:'password',width:100,align:'left',sortable:false,
                    editor: {
                        type:'validatebox',
                        options : {
                            required: true,
                            valueField : 'password'
                        }
                    }
                },
                {title:'角色',field:'role',width:80,align:'left',sortable:false,formatter:roleFormatter},
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
                {field:'action',title:'操作',width:80,align:'left',  
                    formatter:function(value,row,index){                      	
                        if (row.editing){  
                            var s = '<a class="easyui-linkbutton" onclick="saveRow('+index+')">更新</a> ';
                            var c = '<a class="easyui-linkbutton" onclick="cancelRow('+index+')">取消</a> ';  
                            var j;
                            if(row.enabled == 1){
                            	j = '<a class="easyui-linkbutton" onclick="updateEnabled('+index+',0)">禁用</a> ';  
                            }else if(row.enabled == 0){
                            	j = '<a class="easyui-linkbutton" onclick="updateEnabled('+index+',1)">启用</a> ';
                            }
                            return c+s+j;  
                        } else {  
                            var e = '<a class="easyui-linkbutton" onclick="editRow('+index+')">编辑</a> ';  
                            var d = '<a class="easyui-linkbutton" onclick="deleteRow('+index+')">删除</a> ';  
                            var j;
                            if(row.enabled == 1){
                            	j = '<a class="easyui-linkbutton" onclick="updateEnabled('+index+',0)">禁用</a> ';  
                            }else if(row.enabled == 0){
                            	j = '<a class="easyui-linkbutton" onclick="updateEnabled('+index+',1)">启用</a> ';
                            }
                            return e+d+j;  
                        }  
                    }  
                }  
            ]],
            idField : 'id',
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
<table id="userdatagrid"></table>
</body>
</html>

