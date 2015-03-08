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
	var newRow = undefined;
    function save(id, name, description){
    	$.ajax({
    		 type: "POST",
             url: '${pageContext.request.contextPath}/teacher/course/modify.json',
             data: "id="+id+"&name="+name+"&description="+description,
             dataType: "json", 
             success: function (data) {
            	  if(data.flg){
                  	$.messager.show({
                  		title:'成功',
                  		msg:data.msg,
                  		timeout:3000,
                  		showType:'slide'
                  	});
                  }else{
                	  $.messager.alert('更新失败',data.msg,'info');
                      $('#coursedatagrid').datagrid('rejectChanges');
                  }
             }
    	});
    }
    function editRow(index){
        $('#coursedatagrid').datagrid('beginEdit', index);
    }
    function deleteRow(index){
        var row = $('#coursedatagrid').datagrid('getRows')[index];
        $.messager.confirm('Confirm','你确定要删除该课程吗?',function(r){
            if (r){
            	$.ajax({
           		 type: "POST",
                    url: '${pageContext.request.contextPath}/teacher/course/delete.json',
                    data: "id="+row.id,
                    dataType: "json", 
                    success: function (data) {
                   	  if(data.flg){
                          $('#coursedatagrid').datagrid('deleteRow', index);
                         	$.messager.show({
                         		title:'成功',
                         		msg:data.msg,
                         		timeout:3000,
                         		showType:'slide'
                         	});
                         }else{
                       	  	$.messager.alert('删除失败',data.msg,'info');
                         }
                    }
           		});
            }
        });
    }
    function insertRow(index){
    	newRow = undefined;
    	if(!$('#coursedatagrid').datagrid('validateRow', index)){
    		return;
    	}
        $('#coursedatagrid').datagrid('endEdit', index);
        var row = $('#coursedatagrid').datagrid('getRows')[index];
    	$.ajax({
	   		 type: "POST",
	            url: '${pageContext.request.contextPath}/teacher/course/insert.json',
	            data: "name="+row.name+"&description="+row.description,
	            dataType: "json", 
	            success: function (data) {
	           	  if(data.flg){
	                  $('#coursedatagrid').datagrid('reload');
	                 	$.messager.show({
	                 		title:'成功',
	                 		msg:data.msg,
	                 		timeout:3000,
	                 		showType:'slide'
	                 	});
	                 }else{
	               	  $.messager.alert('插入失败',data.msg,'info');
	                  $('#coursedatagrid').datagrid('deleteRow', index);
	                 }
	            }
	   	});
    }

    function cancelNewRow(index){
        $('#coursedatagrid').datagrid('deleteRow', index);
    	newRow = undefined;
    }
    function saveRow(index){
    	if(!$('#coursedatagrid').datagrid('validateRow', index)){
    		return;
    	}
        $('#coursedatagrid').datagrid('endEdit', index);
        var row = $('#coursedatagrid').datagrid('getRows')[index];
        save(row.id, row.name, row.description);
    }
    function cancelRow(index){
        $('#coursedatagrid').datagrid('cancelEdit', index);
    }
    function updateActions(index){
		$('#coursedatagrid').datagrid('updateRow',{
			index: index,
			row:{}
		});
	}
    $(function(){
        $("#coursedatagrid").datagrid({
            url:'${pageContext.request.contextPath}/teacher/course/query.json',
            singleSelect : true,
            columns : [[
                {title:'名称',field:'name',width:50,align:'left',sortable:false,
                    editor: {
                        type:'validatebox',
                        options : {
                            required: true,
                            validType: 'length[2,20]',
                            valueField : 'name'
                        }
                    }
                },
                {title:'描述',field:'description',width:200,align:'left',sortable:false,
                    editor: {
                        type:'validatebox',
                        options : {
                            required: false,
                            valueField : 'description'
                        }
                    }
                },
                {field:'action',title:'操作',width:70,align:'left',  
                    formatter:function(value,row,index){  
                    	if(!newRow && index == newRow){
                            var s = '<a class="easyui-linkbutton" onclick="insertRow('+newRow+')">添加</a> ';
                            var c = '<a class="easyui-linkbutton" onclick="cancelNewRow('+newRow+')">取消</a>';  
                            return s+c;                      		
                    	}
                        if (row.editing){  
                            var s = '<a class="easyui-linkbutton" onclick="saveRow('+index+')">更新</a>';
                            var c = '<a class="easyui-linkbutton" onclick="cancelRow('+index+')">取消</a> ';  
                            return c+s;  
                        } else {  
                            var e = '<a class="easyui-linkbutton" onclick="editRow('+index+')">编辑</a> ';  
                            var d = '<a class="easyui-linkbutton" onclick="deleteRow('+index+')">删除</a>';  
                            return e+d;  
                        }  
                    }  
                }  
            ]],
            toolbar: [{
                text: '添加', iconCls: 'icon-add', handler: function () {
                	if(newRow == undefined){
                   	 $("#coursedatagrid").datagrid('insertRow', {
                            index: 0,
                            row: {}
                        });
                        newRow = 0;
                        $("#coursedatagrid").datagrid('beginEdit', 0);
                	}
                }
            }],
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
<table id="coursedatagrid"></table>
</body>
</html>

