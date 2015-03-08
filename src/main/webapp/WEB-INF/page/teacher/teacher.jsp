<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/easyui/themes/gray/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/easyui/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/resources/js/common.js'> </script>
    
<script type="text/javascript">
    var dateFormatter = function(time){
        var date = new Date();
        date.setTime(time);
        return dateFormat("yyyy/MM/dd HH:mm", date);
    }
    function editRow(index){
        $('#teacherdatagrid').datagrid('beginEdit', index);
        var account = $('#teacherdatagrid').datagrid('getEditor', { index: index, field: 'account' });
        $(account.target).attr('disabled',true)
    }
    function saveRow(index){
    	if(!$('#teacherdatagrid').datagrid('validateRow', index)){
    		return;
    	}
        $('#teacherdatagrid').datagrid('endEdit', index);
        var row = $('#teacherdatagrid').datagrid('getRows')[index];
        if(row.pwd != row.repwd){
         	  $.messager.alert('提示', '两次键入的密码不一致','info');
         	  return;
        }
        $.ajax({
   		 type: "POST",
            url: '${pageContext.request.contextPath}/teacher/teacher/modify.json',
            data: "name="+row.name+"&pwd="+row.pwd+"&repwd="+row.repwd,
            dataType: "json", 
            success: function (data) {
           	  if(data.flg){
                  $('#teacherdatagrid').datagrid('reload');
                 	$.messager.show({
                 		title:'成功',
                 		msg:'更新成功',
                 		timeout:3000,
                 		showType:'slide'
                 	});
                 }else{
               	  	$.messager.alert('更新失败','更新失败','info');
                     $('#teacherdatagrid').datagrid('rejectChanges');
                 }
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
            url:'${pageContext.request.contextPath}/teacher/teacher/query.json',
            singleSelect : true,
            columns : [[
                {title:'账号',field:'account',width:80,align:'left',sortable:false,
                    editor: {
                        type:'validatebox',
                        options : {
                            required: true,
                            validType: 'length[4,20]',
                            valueField : 'account'
                        }
                    }
                },
                {title:'姓名',field:'name',width:80,align:'left',sortable:false,
                    editor: {
                        type:'validatebox',
                        options : {
                        	required: true,
                            validType: 'length[2,4]',
                            valueField : 'name'
                        }
                    }
                },
                {title:'密码',field:'pwd',width:80,align:'left',sortable:false,
                    editor: {
                    	type:'validatebox',
                        options : {
                            required: true,
                            validType: 'length[6,20]',
                            valueField : 'pwd'
                        }
                    }
                },
                {title:'重复密码',field:'repwd',width:80,align:'left',sortable:false,
                    editor: {
                    	type:'validatebox',
                        options : {
                            required: true,
                            validType: 'length[6,20]',
                            valueField : 'repwd'
                        }
                    }
                },
                {field:'action',title:'操作',width:70,align:'left',  
                    formatter:function(value,row,index){  
                        if (row.editing){  
                            var s = '<a class="easyui-linkbutton" onclick="saveRow('+index+')">更新</a>';
                            var c = '<a class="easyui-linkbutton" onclick="cancelRow('+index+')">取消</a> ';  
                            return c+s;  
                        } else {  
                            var e = '<a class="easyui-linkbutton" onclick="editRow('+index+')">编辑</a> ';  
                            return e;  
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
            selectOnCheck : true,
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
<table id="teacherdatagrid"></table>
