<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
	<title>WELCOME</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="Content-Language" content="zh-CN"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/easyui/themes/gray/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/easyui/themes/icon.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src='${pageContext.request.contextPath}/resources/js/common.js'> </script>
		
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/knockout/knockout-3.0.0.js"></script>
    <script src="${pageContext.request.contextPath}/resources/plugin/ace/ace.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		function getCodeQuestion(editor){
			var id = $("#codeQuestionForm input[name='codeQuestionId']").val();
			if(id == ''){
				return;
			}
			$.ajax({
          		 type: "POST",
                   url: '${pageContext.request.contextPath}/teacher/codeQuestion/queryById.json',
                   data: "id="+id,
                   dataType: "json", 
                   success: function (data) {
                	   editor.setValue(data.codeQuestion.code);
                	   $("#codeQuestionForm textarea[name='content']").val(data.codeQuestion.content);
                	   $("#codeQuestionForm select[name='languageType']").val(data.codeQuestion.languageType);
                	   $("#codeQuestionForm select[name='difficultyType']").val(data.codeQuestion.difficultyType);
                	   var date = new Date();
                	   date.setTime(data.codeQuestion.createTime);
                	   $("#createTime").datetimebox('setValue', dateFormat("yyyy/MM/dd HH:mm", date));
                   }
          	});
		}
		function insertCodeQuestion(content, code, languageType, difficultyType, createTime){
			$.ajax({
         		 type: "POST",
                  url: '${pageContext.request.contextPath}/teacher/codeQuestion/insert.json',
                  data: "content="+content+"&code="+code+"&languageType="+languageType+"&difficultyType="+difficultyType+"&createTime="+createTime,
                  dataType: "json", 
                  success: function (data) {
                	  if(data.flg){
                         	$.messager.show({
                         		title:'成功',
                         		msg:'新增成功',
                         		timeout:3000,
                         		showType:'slide'
                         	});
            	    		window.parent.closeWindow();
            	    		window.parent.reloadData();
                        }else{
                      	  	$.messager.alert('删除失败','新增失败','info');
                        }
                  }
         	});
		}
		function modifyCodeQuestion(id, content, code, languageType, difficultyType, createTime){
			$.ajax({
        		 type: "POST",
                 url: '${pageContext.request.contextPath}/teacher/codeQuestion/modify.json',
                 data: "id="+id+"&content="+content+"&code="+code+"&languageType="+languageType+"&difficultyType="+difficultyType+"&createTime="+createTime,
                 dataType: "json", 
                 success: function (data) {
                	 if(data.flg){
                        	$.messager.show({
                        		title:'成功',
                        		msg:'修改成功',
                        		timeout:3000,
                        		showType:'slide'
                        	});
                 			location.reload();
                       }else{
                     	  	$.messager.alert('删除失败','修改失败','info');
                       }
                 }
        	});
		}
		$(function(){		
			var ele = $(".code_editor")[0];
		    var editor = ace.edit(ele);
		    editor.setTheme("ace/theme/github");
		    editor.getSession().setMode("ace/mode/c_cpp");
			getCodeQuestion(editor);
			
			$("#codeQuestionForm select[name='languageType']").change(function(){
				var languageType = $(this).val();
				if(languageType == 1 || languageType == 2){
				    editor.getSession().setMode("ace/mode/c_cpp");					
				}
				if(languageType == 3){
				    editor.getSession().setMode("ace/mode/java");	
				}
			});			
			
			$("#createTime").datetimebox({
				formatter:function(date){
                    return dateFormat("yyyy/MM/dd HH:mm", date);
                },
                parser:function(s){
	               	 var t = Date.parse(s);
	           		if (!isNaN(t)){
	           			return new Date(t);
	           		} else {
	           			return new Date();
	           		}
               }
			});
			
			$("#save").click(function(){
				var id = $("#codeQuestionForm input[name='codeQuestionId']").val();
				var content = encodeURIComponent($("#codeQuestionForm textarea[name='content']").val());
				var code = encodeURIComponent(editor.getValue());
         	    var languageType = $("#codeQuestionForm select[name='languageType'] option:selected").val();
         	    var difficultyType = $("#codeQuestionForm select[name='difficultyType'] option:selected").val();
         	    var createTime = new Date($('#createTime').datetimebox('getValue')).getTime();
				if(id == ''){
					insertCodeQuestion(content, code, languageType, difficultyType, createTime);
				}else{
					modifyCodeQuestion(id, content, code, languageType, difficultyType, createTime);
				}
			});
			var id = $("#codeQuestionForm input[name='codeQuestionId']").val();
			if(id != ''){
				$("#addCodeAnswerPanle").css("display", "block");
			}
		  	function codeAnwerModel(){
		        var self = this;
		        self.codeAnwers = ko.observableArray();
		    }
		    var viewModel = new codeAnwerModel();
		    ko.applyBindings(viewModel);
			var id = $("#codeQuestionForm input[name='codeQuestionId']").val();
			if(id == ''){
				viewModel.codeAnwers([]);
			}else{
				$.ajax({
	        		 type: "POST",
	                 url: '${pageContext.request.contextPath}/teacher/codeAnswer/query.json',
	                 data: "codeQuestionId="+id,
	                 dataType: "json", 
	                 success: function (data) {
	                	 viewModel.codeAnwers(data.codeAnswers);
	                 }
	        	});
			}
			$("#addCodeAnswer").click(function(){
				var id = $("#codeQuestionForm input[name='codeQuestionId']").val();
				var inputData = encodeURIComponent($("#inputData").val());
				var outputData = encodeURIComponent($("#outputData").val());
				if(outputData == ''){
             	  	$.messager.alert('不能为空','输出数据不能为空','info');
					return;
				}
				$.ajax({
	        		 type: "POST",
	                 url: '${pageContext.request.contextPath}/teacher/codeAnswer/insert.json',
	                 data: "codeQuestionId="+id+"&inputData="+inputData+"&outputData="+outputData,
	                 dataType: "json", 
	                 success: function (data) {
	                	 if(data.flg){
	                		 $.messager.show({
	                        		title:'成功',
	                        		msg:'新增成功',
	                        		timeout:3000,
	                        		showType:'slide'
	                        });
	               			location.reload();
	                	 }else{
	                     	  	$.messager.alert('失败','新增失败','info');
	                       }
	                 }
	        	});
			});
		});
		function deleteCodeAnswer(id){
			 $.messager.confirm('Confirm','你确定要删除该记录吗?',function(r){
		            if (r){
						$.ajax({
			       		 type: "POST",
			                url: '${pageContext.request.contextPath}/teacher/codeAnswer/delete.json',
			                data: "id="+id,
			                dataType: "json", 
			                success: function (data) {
			               	 if(data.flg){
			               		 $.messager.show({
			                       		title:'成功',
			                       		msg:'删除成功',
			                       		timeout:3000,
			                       		showType:'slide'
			                       });
			              			location.reload();
			               	 }else{
			                    	  	$.messager.alert('失败','删除失败','info');
			                      }
			                }
			       		});
		            }
			 });
		}
		function updateCodeAnswer(e, id){
			var codeAnswerArea = $(e).parent();
			var outputData = encodeURIComponent(codeAnswerArea.find("textarea[name='outputData']").val());
			var intputData = codeAnswerArea.find("textarea[name='inputData']").val();
			inputData = encodeURIComponent(codeAnswerArea.find("textarea[name='inputData']").val());
			if(outputData == ''){
         	  	$.messager.alert('不能为空','输出数据不能为空','info');
				return;
			}
			$.ajax({
       		 type: "POST",
                url: '${pageContext.request.contextPath}/teacher/codeAnswer/modify.json',
                data: "id="+id+"&inputData="+inputData+"&outputData="+outputData,
                dataType: "json", 
                success: function (data) {
               	 if(data.flg){
               		 $.messager.show({
                       		title:'成功',
                       		msg:'修改成功',
                       		timeout:3000,
                       		showType:'slide'
                       });
              			location.reload();
               	 }else{
                    	  	$.messager.alert('失败','修改失败','info');
                      }
                }
       	});
		}
	</script>
	<style>
		.code_editor{
			height: 200px;
			width:500px;
		}
		.codeAnswerRow{
			height: 100px;
			width:600px;
		}
		.codeAnswerRow textarea{
			float:left;
		}.codeAnswerRow a{
			margin-top:10px;
			float:left;
		}
	</style>
</head>
<body>
<div></div>
<form id="codeQuestionForm">
	<input type="hidden" name="codeQuestionId" value="${codeQuestionId}"/>
	<textarea rows="5" cols="50" name="content" class="easyui-validatebox" data-options="required:true,validType: 'length[2,400]'"></textarea><br/>
	<select name="languageType">
		<option value="1">任意</option>
		<option value="2">C</option>
		<option value="3">JAVA</option>
	</select>
	<select name="difficultyType">
		<option value="1">容易</option>
		<option value="2">一般</option>
		<option value="3">困难</option>
	</select>
	<input id="createTime" class="easyui-datetimebox" name="createTime" data-options="required:true,showSeconds:false" style="width:150px"/>  	
	 <pre class='code_editor'></pre>
</form>
<a id="save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>  
<br/><hr/>
<div data-bind="foreach: codeAnwers">
	<div class="codeAnswerRow">
		<textarea rows="5" cols="30" name="inputData" data-bind="text: inputData">
		</textarea>
		<textarea rows="5" cols="30" name="outputData" data-bind="text: outputData">
		</textarea>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" data-bind="attr:{ onclick: 'updateCodeAnswer(this,'+id+')' }">修改</a> 
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" data-bind="attr:{ onclick: 'deleteCodeAnswer('+id+')' }">删除</a> 
	</div>
</div>	<br/>
<div id="addCodeAnswerPanle" class="codeAnswerRow" style="display:none">
	<textarea rows="5" cols="30" name="inputData" id="inputData"></textarea>
	<textarea rows="5" cols="30" name="outputData" id="outputData"></textarea>
	<a id="addCodeAnswer" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a> 
</div>
</body>
</html>

