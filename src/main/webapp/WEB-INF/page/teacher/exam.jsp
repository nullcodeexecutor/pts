<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
    var statusFormatter = function(status){
        return status==1 ? '正常' : '结束';
    }
    var typeFormatter = function(type){
        return type==1 ? '考试' : '练习';
    }
    var dateFormatter = function(time){
        var date = new Date();
        date.setTime(time);
        return dateFormat("yyyy/MM/dd HH:mm", date);
    }
	var newRow = undefined;
    function editRow(index){
        $('#examdatagrid').datagrid('beginEdit', index);
    }
    function deleteRow(index){
        var row = $('#examdatagrid').datagrid('getRows')[index];
        $.messager.confirm('Confirm','你确定要删除该考试吗?',function(r){
            if (r){
            	$.ajax({
           		 type: "POST",
                    url: '${pageContext.request.contextPath}/teacher/exam/delete.json',
                    data: "id="+row.id,
                    dataType: "json", 
                    success: function (data) {
                   	  if(data.flg){
                          $('#examdatagrid').datagrid('deleteRow', index);
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
    	if(!$('#examdatagrid').datagrid('validateRow', index)){
    		return;
    	}
        $('#examdatagrid').datagrid('endEdit', index);
        var row = $('#examdatagrid').datagrid('getRows')[index];
    	$.ajax({
   		 type: "POST",
            url: '${pageContext.request.contextPath}/teacher/exam/insert.json',
            data: "courseId="+$("#course option:selected").val()+"&name="+row.name+"&score="+row.score+"&status="+row.status+"&startTime="+row.startTime+"&endTime="+row.endTime+"&type="+row.type,
            dataType: "json", 
            success: function (data) {
           	  if(data.flg){
                  $('#examdatagrid').datagrid('reload');
                 	$.messager.show({
                 		title:'成功',
                 		msg:'插入成功',
                 		timeout:3000,
                 		showType:'slide'
                 	});
                 }else{
               	  $.messager.alert('插入失败','插入失败','info');
                  $('#examdatagrid').datagrid('deleteRow', index);
                 }
            }
   	});
    }
    function cancelNewRow(index){
        $('#examdatagrid').datagrid('deleteRow', index);
    	newRow = undefined;
    }
    function saveRow(index){
    	if(!$('#examdatagrid').datagrid('validateRow', index)){
    		return;
    	}
        $('#examdatagrid').datagrid('endEdit', index);
        var row = $('#examdatagrid').datagrid('getRows')[index];
        $.ajax({
   		 type: "POST",
            url: '${pageContext.request.contextPath}/teacher/exam/modify.json',
            data: "id="+row.id+"&name="+row.name+"&score="+row.score+"&status="+row.status+"&startTime="+row.startTime+"&endTime="+row.endTime+"&type="+row.type,
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
                     $('#examdatagrid').datagrid('rejectChanges');
                 }
            }
   	});
    }
    function cancelRow(index){
        $('#examdatagrid').datagrid('cancelEdit', index);
    }
    function updateActions(index){
		$('#examdatagrid').datagrid('updateRow',{
			index: index,
			row:{}
		});
	}
    $(function(){
        $("#examdatagrid").datagrid({
            url:'${pageContext.request.contextPath}/teacher/exam/query.json',
            singleSelect : true,
            queryParams : {"courseId":-1},
            columns : [[
                {title:'名称',field:'name',width:80,align:'left',sortable:false,
                    editor: {
                        type:'validatebox',
                        options : {
                            required: true,
                            validType: 'length[2,20]',
                            valueField : 'name'
                        }
                    }
                },
                {title:'分数',field:'score',width:80,align:'left',sortable:false,
                    editor: {
                        type:'numberbox',
                        options : {
                            required: true,
                            min: 1,
                            max: 100,
                            valueField : 'score'
                        }
                    }
                },
                {title:'开始时间',field:'startTime',width:100,align:'left',sortable:false,formatter:dateFormatter,
                    editor: {
                        type:'datetimebox',
                        options : {
                            required: true,
                            showSeconds: false,
                            valueField : 'startTime'
                        }
                    }
                },
                {title:'结束时间',field:'endTime',width:100,align:'left',sortable:false,formatter:dateFormatter,
                    editor: {
                        type:'datetimebox',
                        options : {
                            required: true,
                            showSeconds: false,
                            valueField : 'endTime'
                        }
                    }
                },
                {title:'状态',field:'status',width:80,align:'left',sortable:false,formatter:statusFormatter,
                    editor:{
                        type:'combobox',
                        options:{
                            valueField:'status',
                            textField:'text',
                            data: [{
                                "status":1,
                                "text":"正常"
                            },{
                                "status":2,
                                "text":"结束"
                            }],
                            required:true
                        }
                    }
                },
                {title:'类型',field:'type',width:80,align:'left',sortable:false,formatter:typeFormatter,
                    editor:{
                        type:'combobox',
                        options:{
                            valueField:'type',
                            textField:'text',
                            data: [{
                                "type":1,
                                "text":"考试"
                            },{
                                "type":2,
                                "text":"练习"
                            }],
                            required:true
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
                	$("#examdatagrid").datagrid("options").queryParams = {"courseId":$("#course option:selected").val()};
            	} 	
            }
    	});     
        $("#query").click(function(){
        	$("#examdatagrid").datagrid("options").queryParams = {"courseId":$("#course option:selected").val()};
        	$("#examdatagrid").datagrid("reload");
        });
        $("#addExam").click(function(){
        	if(newRow == undefined){
    	    	 $("#examdatagrid").datagrid('insertRow', {
    	             index: 0,
    	             row: {}
    	         });
    	         newRow = 0;
    	         $("#examdatagrid").datagrid('beginEdit', 0);
        	}
        });        
    });
</script>
<style>
	#toolbar{
		height: 24px;
		padding:13px;
	}
</style>
</head>
<body>
<div id="toolbar">
	<span style="float:left">
		<label>课程:</label>
		<select name="courseId" id="course">
		</select>
	</span>
	<a id="query" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="float:left;margin-left:10px;margin-right:30px">查询</a>
	<a id="addExam" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>  
</div>
<table id="examdatagrid"></table>
</body>
</html>
