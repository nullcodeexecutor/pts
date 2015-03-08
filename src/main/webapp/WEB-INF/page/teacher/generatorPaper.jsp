<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/easyui/themes/gray/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/easyui/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/resources/js/common.js'> </script>
<style>
	body{
		padding: 20px;
	}
	.questionArea{
		margin-left: 40px;
		margin-top: 10px;
		margin-bottom: 10px
	}
	#loading{
		position:relative;
	    cursor: pointer;    
	    margin: 0;
	    opacity: 0.5;
	    padding: 0;
	    display:none;
	    z-index: 100001;
	    left:400px;
	    top:-200px;		
	}
</style>
<script type="text/javascript">
	function json2Args(pre, o) {
		var arr = [];
		var fmt = function(s) {
			if (typeof s == 'object' && s != null) return json2str(s);
				return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s;
		}
		for (var i in o) arr.push("" + pre +"_"+i + "=" + o[i]);
		return arr.join('&');
	}
	function checkGeneratorInfo(generatorInfo){
		if(isNaN(generatorInfo.startTime)){
			generatorInfo.startTime = '';
		}
		if(isNaN(generatorInfo.endTime)){
			generatorInfo.endTime = '';
		}
		if(generatorInfo.questionNum==''){
			generatorInfo.questionNum='0';
		}
		if(generatorInfo.easyNum==''){
			generatorInfo.easyNum='0';
		}
		if(generatorInfo.generaNum==''){
			generatorInfo.generaNum='0';
		}
		if(generatorInfo.diffcultyNum==''){
			generatorInfo.diffcultyNum='0';
		}
		if(generatorInfo.questionNum=='0' && generatorInfo.easyNum=='0' && generatorInfo.generaNum=='0' && generatorInfo.diffcultyNum=='0'){
    	  	$.messager.alert('提示','题目数不能都为0','info');			
			return false;
		}
		return true;
	}
	function getGeneratorInfo(id){
		var questionArea = $("#"+id);
		var generatorInfo = {
				languageType: questionArea.find("select option:selected").val(),
				startTime: new Date(questionArea.find("input[name='startTime']").val()).getTime(),
				endTime: new Date(questionArea.find("input[name='endTime']").val()).getTime(),
				creator: questionArea.find("input[name='creator_"+id+"']:checked").val(),
				score: questionArea.find("input[name='score']").val(),
				questionNum: questionArea.find("input[name='questionNum']").val(),
				easyNum: questionArea.find("input[name='easyNum']").val(),
				generaNum: questionArea.find("input[name='generaNum']").val(),
				diffcultyNum: questionArea.find("input[name='diffcultyNum']").val()
		};
		return generatorInfo;
	}
	$(function(){
		$.ajax({
    		type: "POST",
            url: '${pageContext.request.contextPath}/teacher/course/queryAll.json',
            dataType: "json", 
            success: function (data) {
        		$("#course").html("");
        		$("#course").append("<option value='-1'>请选择课程</option>");
            	for(var i=0;i<data.courses.length;i++){
                	$("#course").append("<option value='"+data.courses[i].id+"'>"+data.courses[i].name+"</option>");
            	}
            	if(data.courses.length>0){
                	$("#studentdatagrid").datagrid("options").queryParams = {"courseId":$("#course option:selected").val()};
            	} 	
            }
    	});  
		$("#course").change(function(){
			var courseId = $(this).val();
			$.ajax({
	    		type: "POST",
	            url: '${pageContext.request.contextPath}/teacher/exam/queryByCourseId.json',
	            data: "courseId="+courseId,
	            dataType: "json", 
	            success: function (data) {
            		$("#exam").html("");
	            	for(var i=0;i<data.exams.length;i++){
	                	$("#exam").append("<option value='"+data.exams[i].id+"'>"+data.exams[i].name+"</option>");
	            	}
	            }
	    	});  
		});
		function getArgs(){
			var examId = $("#exam option:selected").val();
			var examPaperNum = $("#examPaperNum").val();
			
			if(!examId){
	    	  	$.messager.alert('提示','请选择考试','info');
	    	  	return;
			}
			if(examPaperNum==''){
	    	  	$.messager.alert('提示','请选择生成的试卷数量','info');
	    	  	return;
			}
			
			var select_generatorInfo = getGeneratorInfo("selectQuestion");
			if(!checkGeneratorInfo(select_generatorInfo)) return "";
			
			var tf_generatorInfo = getGeneratorInfo("tfQuestion");
			if(!checkGeneratorInfo(tf_generatorInfo)) return "";
			
			var code_generatorInfo = getGeneratorInfo("codeQuestion");
			if(!checkGeneratorInfo(code_generatorInfo)) return "";
			
			var args = "examId="+examId+"&examPaperNum="+examPaperNum+"&"+
				json2Args("select", select_generatorInfo)+"&"+
				json2Args("tf", tf_generatorInfo)+"&"+
				json2Args("code", code_generatorInfo);
			return args;
		}
		$("#generateExamPaper").click(function(){
			var args = getArgs();
			if(args == ''){
				return;
			}
			 $.messager.confirm('Confirm','你确定要生成试卷吗?该过程需要耐心等待',function(r){
		            if (r){
		            	$("#loading").css("display","block");
		    			$.ajax({
		    	    		type: "POST",
		    	            url: '${pageContext.request.contextPath}/teacher/generatorPaper/generate.json',
		    	            data: args,
		    	 		   	async: false,
		    	            dataType: "json", 
		    	            success: function (data) {
		    		    	  	$.messager.alert('提示','试卷的满分是 '+data.sumScore,'info');
		             			location.reload();
		    	            }
		    	    	});  
		    			
		            }
			 });
		});
	});
</script>
</head>
<body>
	<span style="float:left">
		<label>课程:</label>
		<select name="courseId" id="course">
		</select>
		&nbsp;&nbsp;&nbsp;&nbsp;
	</span>
	<span style="float:left">
		<label>考试:</label>
		<select name="examId" id="exam">
		</select>
		&nbsp;&nbsp;&nbsp;&nbsp;
	</span>
	<span style="float:left">
		<label>生成试卷套数:</label>
		<input class="easyui-numberspinner" id="examPaperNum" style="width:50px;" value="1" data-options="min:1,max:20,editable:false">  
		&nbsp;&nbsp;&nbsp;&nbsp;
	</span>
	<br/><br/><br/><hr/>
	
	选择题<br/>
	<div class="questionArea" id="selectQuestion">
		<span style="float:left">
			<label>语言类型:</label>
			<select name="languageType">
				<option value="1">任意</option>
				<option value="2">C</option>
				<option value="3">JAVA</option>
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
			<input type="radio" value="0" name="creator_selectQuestion" checked />自己
			<input type="radio" value="1" name="creator_selectQuestion" />所有教师
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>		
		<span style="float:left">
			<label>每题分数:</label>
			<input class="easyui-numberspinner" name="score" style="width:50px;" value="5" data-options="min:1,max:100,editable:false"/>  
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>		
		<br/><br/>
		<span style="float:left">
			<label>总题目数:</label>
			<input class="easyui-numberspinner" name="questionNum" style="width:50px;" data-options="min:0,max:100,editable:false"/>  
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		<span style="float:left">
			<label>容易难度的题目数:</label>
			<input class="easyui-numberspinner" name="easyNum" style="width:50px;" data-options="min:0,max:100,editable:false"/>  
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		<span style="float:left">
			<label>一般难度的题目数:</label>
			<input class="easyui-numberspinner" name="generaNum" style="width:50px;" data-options="min:0,max:100,editable:false"/>  
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		<span style="float:left">
			<label>困难难度的题目数:</label>
			<input class="easyui-numberspinner" name="diffcultyNum" style="width:50px;" data-options="min:0,max:100,editable:false"/>  
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
	</div>
	
	
	
	<br/><br/><hr/>
	判断题<br/>
	<div class="questionArea" id="tfQuestion">
		<span style="float:left">
			<label>语言类型:</label>
			<select name="languageType">
				<option value="1">任意</option>
				<option value="2">C</option>
				<option value="3">JAVA</option>
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
			<input type="radio" value="0" name="creator_tfQuestion" checked />自己
			<input type="radio" value="1" name="creator_tfQuestion"/>所有教师
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>		
		<span style="float:left">
			<label>每题分数:</label>
			<input class="easyui-numberspinner" name="score" style="width:50px;" value="5" data-options="min:1,max:100,editable:false"/>  
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>		
		<br/><br/>
		<span style="float:left">
			<label>总题目数:</label>
			<input class="easyui-numberspinner" name="questionNum" style="width:50px;" data-options="min:0,max:100,editable:false"/>  
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		<span style="float:left">
			<label>容易难度的题目数:</label>
			<input class="easyui-numberspinner" name="easyNum" style="width:50px;" data-options="min:0,max:100,editable:false"/>  
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		<span style="float:left">
			<label>一般难度的题目数:</label>
			<input class="easyui-numberspinner" name="generaNum" style="width:50px;" data-options="min:0,max:100,editable:false"/>  
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		<span style="float:left">
			<label>困难难度的题目数:</label>
			<input class="easyui-numberspinner" name="diffcultyNum" style="width:50px;" data-options="min:0,max:100,editable:false"/>  
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
	</div>
	
	
	
	
	<br/><br/><hr/>
	编程题<br/>
	<div class="questionArea" id="codeQuestion">
		<span style="float:left">
			<label>语言类型:</label>
			<select name="languageType">
				<option value="1">任意</option>
				<option value="2">C</option>
				<option value="3">JAVA</option>
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
			<input type="radio" value="0" name="creator_codeQuestion" checked />自己
			<input type="radio" value="1" name="creator_codeQuestion"/>所有教师
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>		
		<span style="float:left">
			<label>每题分数:</label>
			<input class="easyui-numberspinner" name="score" style="width:50px;" value="5" data-options="min:1,max:100,editable:false"/>  
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>		
		<br/><br/>
		<span style="float:left">
			<label>总题目数:</label>
			<input class="easyui-numberspinner" name="questionNum" style="width:50px;" data-options="min:0,max:100,editable:false"/>  
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		<span style="float:left">
			<label>容易难度的题目数:</label>
			<input class="easyui-numberspinner" name="easyNum" style="width:50px;" data-options="min:0,max:100,editable:false"/>  
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		<span style="float:left">
			<label>一般难度的题目数:</label>
			<input class="easyui-numberspinner" name="generaNum" style="width:50px;" data-options="min:0,max:100,editable:false"/>  
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		<span style="float:left">
			<label>困难难度的题目数:</label>
			<input class="easyui-numberspinner" name="diffcultyNum" style="width:50px;" data-options="min:0,max:100,editable:false"/>  
			&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
	</div><br/><br/>
	<a href="#" class="easyui-linkbutton" id="generateExamPaper">生成试卷</a>  	
<img alt="loading" id="loading" src="${pageContext.request.contextPath}/resources/images/loading.gif"/>
</body>
</html>