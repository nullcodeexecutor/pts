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
    $(function(){
        $("#gradedatagrid").datagrid({
            url:'${pageContext.request.contextPath}/teacher/grade/query.json',
            singleSelect : true,
            queryParams : {"examId":-1},
            columns : [[
                {title:'学号',field:'account',width:80,align:'left',sortable:false},
                {title:'姓名',field:'studentName',width:80,align:'left',sortable:false},
                {title:'班级',field:'className',width:80,align:'left',sortable:false},
                {title:'学院',field:'departName',width:80,align:'left',sortable:false},
                {title:'分数',field:'score',width:80,align:'left',sortable:false},
                {title:'提交时间',field:'submitTime',width:80,align:'left',sortable:false,formatter:dateFormatter}
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
            selectOnCheck : true
        });   
        $.ajax({
    		type: "POST",
            url: '${pageContext.request.contextPath}/teacher/course/queryAll.json',
            dataType: "json", 
            success: function (data) {
            	for(var i=0;i<data.courses.length;i++){
                	$("#course").append("<option value='"+data.courses[i].id+"'>"+data.courses[i].name+"</option>");
            	}
            }
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
        $("#query").click(function(){

        	var examId = $("#exam option:selected").val();
        	if(!examId){
        		return;
        	}

        	var toolbar = $("#toolbar");
        	var account = toolbar.find("input[name='account']").val();
        	var name = toolbar.find("input[name='name']").val();
        	var className = toolbar.find("input[name='className']").val();
        	var departName = toolbar.find("input[name='departName']").val();
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
        	params.examId = examId; 
        	$("#gradedatagrid").datagrid("options").queryParams = params;
        	$("#gradedatagrid").datagrid("reload");
        });
    });
</script>
<style>
	#toolbar{
		height: 24px;
		padding:13px;
	}
</style>
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
</div>
<table id="gradedatagrid"></table>
