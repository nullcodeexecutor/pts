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
	function reloadData(){
		$('#codequestiondatagrid').datagrid('reload');
	}
	function closeWindow(){
		$("#codeQuestionWindow").window("close");
	}

    function editRow(index){
        var row = $('#codequestiondatagrid').datagrid('getRows')[index];
		$("#codeQuestionWindow").window("open");
		window.open('${pageContext.request.contextPath}/teacher/codeQuestion/codeQuestionInfo?id='+row.id,'codeQuestionInfo');
    }
    function deleteRow(index){
        var row = $('#codequestiondatagrid').datagrid('getRows')[index];
        $.messager.confirm('Confirm','你确定要删除该记录吗?',function(r){
            if (r){
            	$.ajax({
           		 type: "POST",
                    url: '${pageContext.request.contextPath}/teacher/codeQuestion/delete.json',
                    data: "id="+row.id,
                    dataType: "json", 
                    success: function (data) {
                   	  if(data.flg){
                          $('#codequestiondatagrid').datagrid('deleteRow', index);
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
    function updateActions(index){
		$('#codequestiondatagrid').datagrid('updateRow',{
			index: index,
			row:{}
		});
	}
    var languageFormatter = function(language){
    	if(language == 1){
    		return "任意";
    	}
		if(language == 2){
    		return "C语言";
    	}
		if(language == 3){
			return "JAVA语言";
		}
    	return "";
    }
    var difficultyFormatter = function(difficulty){
		if(difficulty == 1){
			return "容易";
		}
		if(difficulty == 2){
			return "一般";
		}
		if(difficulty == 3){
			return "困难";
		}
    	return "";
    }
    var dateFormatter = function(time){
        var date = new Date();
        date.setTime(time);
        return dateFormat("yyyy/MM/dd HH:mm", date);
    }
    var answerFormatter = function(answer){
    	if(answer == 0){
    		return "错误";
    	}
    	if(answer == 1){
    		return "正确";
    	}
    	return "";
    }
    $(function(){
        $("#codequestiondatagrid").datagrid({
            url:'${pageContext.request.contextPath}/teacher/codeQuestion/query.json',
            singleSelect : true,
            columns : [[
                {title:'内容',field:'content',width:200,align:'left',sortable:false},
                {title:'代码',field:'code',width:50,align:'left',sortable:false},
                {title:'语言',field:'languageType',width:30,align:'left',sortable:false,formatter:languageFormatter},
                {title:'难度',field:'difficultyType',width:20,align:'left',sortable:false,formatter:difficultyFormatter},
                {title:'创建时间',field:'createTime',width:60,align:'left',sortable:false,formatter:dateFormatter},
                {title:'教师姓名',field:'teacherName',width:25,align:'left',sortable:false},
                {field:'action',title:'操作',width:30,align:'left',  
                    formatter:function(value,row,index){  
                           var e = '<a class="easyui-linkbutton" onclick="editRow('+index+')">编辑</a> ';  
                           var d = '<a class="easyui-linkbutton" onclick="deleteRow('+index+')">删除</a>';  
                           return e+d;  
                    }  
                }  
            ]],
            toolbar: "#toolbar",
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
        $("#add").click(function(){
    		$("#codeQuestionWindow").window("open");
    		window.open('${pageContext.request.contextPath}/teacher/codeQuestion/codeQuestionInfo','codeQuestionInfo');
        });     
        $("#query").click(function(){
        	var toolbar = $("#toolbar");
        	var languageType = toolbar.find("select[name='languageType'] option:selected").val();
        	var difficultyType = toolbar.find("select[name='difficultyType'] option:selected").val();
        	var startTime = new Date(toolbar.find("input[name='startTime']").val()).getTime();
        	var endTime = new Date(toolbar.find("input[name='endTime']").val()).getTime();
        	var creator = toolbar.find("input[name='creator']:checked").val();
        	var params = {};
        	if(languageType != 0){
        		params.languageType = languageType;
        	}
        	if(difficultyType != 0){
        		params.difficultyType = difficultyType;
        	}
        	if(!isNaN(startTime)){
        		params.startTime = startTime;
        	}
        	if(!isNaN(endTime)){
        		params.endTime = endTime;
        	}
        	params.creator = creator;
        	$("#codequestiondatagrid").datagrid("options").queryParams = params;
        	$("#codequestiondatagrid").datagrid("reload");
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
			<a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>  
		</span>
		<span style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label>语言类型:</label>
			<select name="languageType">
				<option value="0">请选择</option>
				<option value="1">任意</option>
				<option value="2">C</option>
				<option value="3">JAVA</option>
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		<span style="float:left">
			<label>难度类型:</label>
			<select name="difficultyType">
				<option value="0">请选择</option>
				<option value="1">容易</option>
				<option value="2">一般</option>
				<option value="3">困难</option>
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		<span style="float:left">
			<label>创建时间:</label>
			<input class="easyui-datetimebox" name="startTime" data-options="showSeconds:false,editable:false" style="width:150px"/>
			至  	
			<input class="easyui-datetimebox" name="endTime" data-options="showSeconds:false,editable:false" style="width:150px"/> 
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>	
		<span style="float:left">
			<label>题目来源:</label>
			<input type="radio" value="0" name="creator" />自己
			<input type="radio" value="1" name="creator" checked/>所有教师
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>	
		<span style="float:left">
			<a id="query" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>  
		</span>
</div>
<table id="codequestiondatagrid"></table>

<div id="codeQuestionWindow" class="easyui-window" title="代码题" style="width:600px;height:400px" data-options="iconCls:'icon-save',modal:true,closed:true">
	<iframe name="codeQuestionInfo" src="" scrolling="auto" frameborder="0" style="width:100%;height:100%"/>        
</div>
</body>
</html>

