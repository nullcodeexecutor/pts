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
    var dateFormatter = function(time){
        var date = new Date();
        date.setTime(time);
        return dateFormat("yyyy/MM/dd HH:mm", date);
    }
    function deleteRow(index){
        var row = $('#exampaperdatagrid').datagrid('getRows')[index];
        $.messager.confirm('Confirm','你确定要删除该记录吗?',function(r){
            if (r){
            	$.ajax({
           		 type: "POST",
                    url: '${pageContext.request.contextPath}/teacher/examPaper/delete.json',
                    data: "id="+row.id,
                    dataType: "json", 
                    success: function (data) {
                   	  if(data.flg){
                          $('#exampaperdatagrid').datagrid('deleteRow', index);
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
    $(function(){
        $("#exampaperdatagrid").datagrid({
            url:'${pageContext.request.contextPath}/teacher/examPaper/query.json',
            singleSelect : true,
            queryParams : {"examId":-1},
            columns : [[
                {title:'ID',field:'id',width:50,align:'left',sortable:false},
                {title:'创建时间',field:'createTime',width:100,align:'left',sortable:false,formatter:dateFormatter},
                {field:'action',title:'操作',width:150,align:'left',  
                    formatter:function(value,row,index){   
	                     		 var e = '<a class="easyui-linkbutton" target="_blank" href="${pageContext.request.contextPath}/teacher/preview/index/'+row.id+'">预览</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';  
			                     var d = '<a class="easyui-linkbutton" onclick="deleteRow('+index+')">删除</a>';  
			                     return e+d;  
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
                	$("#exampaperdatagrid").datagrid("options").queryParams = {"courseId":$("#course option:selected").val()};
            	} 	
            }
    	});     
        $("#query").click(function(){
        	var examId = $("#exam option:selected").val();
        	if(!examId){
        		return;
        	}
        	$("#exampaperdatagrid").datagrid("options").queryParams = {"examId":examId};
        	$("#exampaperdatagrid").datagrid("reload");
        });
        $("#course").change(function(){
        	var courseId = $(this).val();
        	if(courseId == '-1'){
          	  $.messager.alert('提示','请选择课程','info');
        	}
        	$.ajax({
        		type: "POST",
                url: '${pageContext.request.contextPath}/teacher/exam/queryAll.json',
                data: 'courseId='+courseId,
                dataType: "json", 
                success: function (data) {
                	for(var i=0;i<data.exams.length;i++){
                    	$("#exam").append("<option value='"+data.exams[i].id+"'>"+data.exams[i].name+"</option>");
                	}
                	if(data.exams.length>0){
                    	$("#gradedatagrid").datagrid("options").queryParams = {"examId":$("#exam option:selected").val()};
                	} 	
                }
        	});   
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
			<option value="-1">请选择课程</option>
		</select>&nbsp;&nbsp;&nbsp;&nbsp;
	</span>
	<span style="float:left">
		<label>考试:</label>
		<select name="examId" id="exam">
		</select>
	</span>
	<a id="query" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="float:left;margin-left:10px;margin-right:30px">查询</a>
</div>
<table id="exampaperdatagrid"></table>
</body>
</html>