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
	var newRow = undefined;
    function editRow(index){
        $('#studentdatagrid').datagrid('beginEdit', index);
        var account = $('#studentdatagrid').datagrid('getEditor', { index: index, field: 'account' });
        $(account.target).attr('disabled',true)
    }
    function deleteRow(index){
        var row = $('#studentdatagrid').datagrid('getRows')[index];
        $.messager.confirm('Confirm','你确定要删除该课程吗?',function(r){
            if (r){
            	$.ajax({
           		 type: "POST",
                    url: '${pageContext.request.contextPath}/teacher/student/delete.json',
                    data: "id="+row.id,
                    dataType: "json", 
                    success: function (data) {
                   	  if(data.flg){
                          $('#studentdatagrid').datagrid('deleteRow', index);
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
    function insertRow(index){
    	if(!$("#course option:selected").val()){
       	  	$.messager.alert('禁止添加','没有选择课程','info');
			return;
    	}
    	newRow = undefined;
    	if(!$('#studentdatagrid').datagrid('validateRow', index)){
    		return;
    	}
        $('#studentdatagrid').datagrid('endEdit', index);
        var row = $('#studentdatagrid').datagrid('getRows')[index];
    	$.ajax({
   		 type: "POST",
            url: '${pageContext.request.contextPath}/teacher/student/insert.json',
            data: "courseId="+$("#course option:selected").val()+"&name="+row.name+"&account="+row.account+"&pwd="+row.pwd+"&className="+row.className+"&departName="+row.departName,
            dataType: "json", 
            success: function (data) {
           	  if(data.flg){
                  $('#studentdatagrid').datagrid('reload');
                 	$.messager.show({
                 		title:'成功',
                 		msg:'添加成功',
                 		timeout:3000,
                 		showType:'slide'
                 	});
                 }else{
               	  $.messager.alert('插入失败',(data.msg?data.msg:'添加失败'),'info');
                  $('#studentdatagrid').datagrid('deleteRow', index);
                 }
            }
   	});
    }
    function cancelNewRow(index){
        $('#studentdatagrid').datagrid('deleteRow', index);
    	newRow = undefined;
    }
    function saveRow(index){
    	if(!$('#studentdatagrid').datagrid('validateRow', index)){
    		return;
    	}
        $('#studentdatagrid').datagrid('endEdit', index);
        var row = $('#studentdatagrid').datagrid('getRows')[index];
        $.ajax({
   		 type: "POST",
            url: '${pageContext.request.contextPath}/teacher/student/modify.json',
            data: "id="+row.id+"&name="+row.name+"&pwd="+row.pwd+"&className="+row.className+"&departName="+row.departName,
            dataType: "json", 
            success: function (data) {
           	  if(data.flg){
                 	$.messager.show({
                 		title:'成功',
                 		msg:'更新成功',
                 		timeout:3000,
                 		showType:'slide'
                 	});
                 }else{
               	  $.messager.alert('更新失败','更新失败','info');
                     $('#studentdatagrid').datagrid('rejectChanges');
                 }
            }
   	});
    }
    function cancelRow(index){
        $('#studentdatagrid').datagrid('cancelEdit', index);
    }
    function updateActions(index){
		$('#studentdatagrid').datagrid('updateRow',{
			index: index,
			row:{}
		});
	}
    $(function(){
        $("#studentdatagrid").datagrid({
            url:'${pageContext.request.contextPath}/teacher/student/query.json',
            singleSelect : true,
            queryParams : {"courseId":-1},
            columns : [[
                {title:'学号',field:'account',width:80,align:'left',sortable:false,
                    editor: {
                        type:'validatebox',
                        options : {
                            required: true,
                            validType: 'length[2,20]',
                            valueField : 'account'
                        }
                    }
                },
                {title:'姓名',field:'name',width:80,align:'left',sortable:false,
                    editor: {
                        type:'validatebox',
                        options : {
                        	required: true,
                            validType: 'length[2,5]',
                            valueField : 'name'
                        }
                    }
                },
                {title:'密码',field:'pwd',width:80,align:'left',sortable:false,
                    editor: {
                    	type:'validatebox',
                        options : {
                            required: true,
                            validType: 'length[2,20]',
                            valueField : 'pwd'
                        }
                    }
                },
                {title:'班级',field:'className',width:80,align:'left',sortable:false,
                    editor: {
                        type:'validatebox',
                        options : {
                            required: false,
                            validType: 'length[0,20]',
                            valueField : 'className'
                        }
                    }
                },
                {title:'学院',field:'departName',width:80,align:'left',sortable:false,
                	editor: {
                        type:'validatebox',
                        options : {
                            required: false,
                            validType: 'length[0,20]',
                            valueField : 'departName'
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
            toolbar : "#toolbar",
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
        $.ajax({
    		type: "POST",
            url: '${pageContext.request.contextPath}/teacher/course/queryAll.json',
            dataType: "json", 
            success: function (data) {
            	for(var i=0;i<data.courses.length;i++){
                	$("#course").append("<option value='"+data.courses[i].id+"'>"+data.courses[i].name+"</option>");
            	}
            	if(data.courses.length>0){
                	$("#studentdatagrid").datagrid("options").queryParams = {"courseId":$("#course option:selected").val()};
                	$("#studentdatagrid").datagrid("reload");
            	} 	
            }
    	});   
        $("#add").click(function(){
        	if(newRow == undefined){
    	    	 $("#studentdatagrid").datagrid('insertRow', {
    	             index: 0,
    	             row: {}
    	         });
    	         newRow = 0;
    	         $("#studentdatagrid").datagrid('beginEdit', 0);
        	}
        });  
        $("#uploadXls").click(function(){
        	if($("#uploadForm input[name='xls']").validatebox("isValid")){
        		if($("#course option:selected").val() && $("#course option:selected").val() > 0 ){
        			$("#uploadcourse").val($("#course option:selected").val());
                	$("#uploadForm").submit();
        		}else{
                 	  $.messager.alert('提示','请选择课程','info');
        		}
        	}
        });
    	var message = "${uploadmsg}";
    	if(message != ""){
       	  	$.messager.alert('提示', message,'info');
    	}
    	$("#query").click(function(){
        	var toolbar = $("#toolbar");
        	var account = toolbar.find("input[name='account']").val();
        	var name = toolbar.find("input[name='name']").val();
        	var className = toolbar.find("input[name='className']").val();
        	var departName = toolbar.find("input[name='departName']").val();
        	var courseId = $("#course option:selected").val();
        	var params = {};
        	if(account != ''){
        		params.account = account;
        	}
        	if(name != ''){
        		params.name = name;
        	}
        	if(className != ''){
        		params.className = className;
        	}
        	if(departName != ''){
        		params.departName = departName;
        	}
        	if(courseId){
        		params.courseId = courseId; 
        	}
        	$("#studentdatagrid").datagrid("options").queryParams = params;
        	$("#studentdatagrid").datagrid("reload");
        });
    });
</script>
<style>
	#toolbar{
		height: 50px;
		padding:13px;
	}
</style>
<div id="toolbar">
	<span style="float:left">
			<a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>  
	</span>
	<span style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label>课程:</label>
		<select name="courseId" id="course">
		</select>
	</span>
	<span style="float:left">&nbsp;&nbsp;
		<label>学号:</label>
		<input name="account" class="easyui-validatebox" />
	</span>
	<span style="float:left">&nbsp;&nbsp;
		<label>姓名:</label>
		<input name="name" class="easyui-validatebox" />
	</span>
	<span style="float:left">&nbsp;&nbsp;
		<label>班级:</label>
		<input name="className" class="easyui-validatebox" />
	</span>
	<span style="float:left">&nbsp;&nbsp;
		<label>学院:</label>
		<input name="departName" class="easyui-validatebox" />
	</span>
	<span style="float:left">
			<a id="query" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>  
	</span>
	<br/><br/>
	<form id="uploadForm" method="post" action="${pageContext.request.contextPath}/teacher/student/uploadXls" enctype ="multipart/form-data">
		<input type="hidden" name="courseId" value="-1" id="uploadcourse"/>
		<input class="easyui-validatebox textbox" type="file" accept=".xls,.xlsx" name="xls" data-options="required:true" style="float:left;margin-left:120px;margin-right:10px;width:150px"/>	
		<a id="uploadXls" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="float:left;margin-left:0px;margin-right:30px">导入学生</a>
	</form>
</div>
<table id="studentdatagrid"></table>
