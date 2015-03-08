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
    function editRow(index){
        $('#tfquestiondatagrid').datagrid('beginEdit', index);
    }
    function deleteRow(index){
        var row = $('#tfquestiondatagrid').datagrid('getRows')[index];
        $.messager.confirm('Confirm','你确定要删除该记录吗?',function(r){
            if (r){
            	$.ajax({
           		 type: "POST",
                    url: '${pageContext.request.contextPath}/teacher/tfQuestion/delete.json',
                    data: "id="+row.id,
                    dataType: "json", 
                    success: function (data) {
                   	  if(data.flg){
                          $('#tfquestiondatagrid').datagrid('deleteRow', index);
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
    	newRow = undefined;
    	if(!$('#tfquestiondatagrid').datagrid('validateRow', index)){
    		return;
    	}
        $('#tfquestiondatagrid').datagrid('endEdit', index);
        var row = $('#tfquestiondatagrid').datagrid('getRows')[index];
    	$.ajax({
	   		 type: "POST",
	            url: '${pageContext.request.contextPath}/teacher/tfQuestion/insert.json',
	            data: "content="+encodeURIComponent(row.content)+"&answer="+row.answer+"&languageType="+row.languageType+"&difficultyType="+row.difficultyType+"&createTime="+row.createTime,
	            dataType: "json", 
	            success: function (data) {
	           	  if(data.flg){
	                  $('#tfquestiondatagrid').datagrid('reload');
	                 	$.messager.show({
	                 		title:'成功',
	                 		msg:"添加成功",
	                 		timeout:3000,
	                 		showType:'slide'
	                 	});
	                 }else{
	               	  $.messager.alert('添加失败',data.msg,'info');
	                  $('#tfquestiondatagrid').datagrid('deleteRow', index);
	                 }
	            }
	   	});
    }

    function cancelNewRow(index){
        $('#tfquestiondatagrid').datagrid('deleteRow', index);
    	newRow = undefined;
    }
    function saveRow(index){
    	if(!$('#tfquestiondatagrid').datagrid('validateRow', index)){
    		return;
    	}
        $('#tfquestiondatagrid').datagrid('endEdit', index);
        var row = $('#tfquestiondatagrid').datagrid('getRows')[index];
    	$.ajax({
    		 type: "POST",
             url: '${pageContext.request.contextPath}/teacher/tfQuestion/modify.json',
             data: "id="+row.id+"&content="+encodeURIComponent(row.content)+"&answer="+row.answer+"&languageType="+row.languageType+"&difficultyType="+row.difficultyType+"&createTime="+row.createTime,
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
                      $('#tfquestiondatagrid').datagrid('rejectChanges');
                  }
             }
    	});
    }
    function cancelRow(index){
        $('#tfquestiondatagrid').datagrid('cancelEdit', index);
    }
    function updateActions(index){
		$('#tfquestiondatagrid').datagrid('updateRow',{
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
        $("#tfquestiondatagrid").datagrid({
            url:'${pageContext.request.contextPath}/teacher/tfQuestion/query.json',
            singleSelect : true,
            columns : [[
                {title:'内容',field:'content',width:200,align:'left',sortable:false,
                    editor: {
                        type:'validatebox',
                        options : {
                            required: true,
                            validType: 'length[2,400]',
                            valueField : 'name'
                        }
                    }
                },
                {title:'答案',field:'answer',width:20,align:'left',sortable:false,formatter:answerFormatter,
                	editor:{
                        type:'combobox',
                        options:{
                            valueField:'answer',
                            textField:'text',
                            data: [{
                                "answer":0,
                                "text":"错误"
                            },{
                                "answer":1,
                                "text":"正确"
                            }],
                            required:true
                        }
                    }
                },
                {title:'语言',field:'languageType',width:30,align:'left',sortable:false,formatter:languageFormatter,
                	editor:{
                        type:'combobox',
                        options:{
                            valueField:'languageType',
                            textField:'text',
                            data: [{
                                "languageType":1,
                                "text":"任意"
                            },{
                                "languageType":2,
                                "text":"C语言"
                            },{
                                "languageType":3,
                                "text":"JAVA语言"
                            }],
                            required:true
                        }
                    }
                },
                {title:'难度',field:'difficultyType',width:20,align:'left',sortable:false,formatter:difficultyFormatter,
                	editor:{
                        type:'combobox',
                        options:{
                            valueField:'difficultyType',
                            textField:'text',
                            data: [{
                                "difficultyType":1,
                                "text":"容易"
                            },{
                                "difficultyType":2,
                                "text":"一般"
                            },{
                                "difficultyType":3,
                                "text":"困难"
                            }],
                            required:true
                        }
                    }
                },
                {title:'创建时间',field:'createTime',width:60,align:'left',sortable:false,formatter:dateFormatter,
                	editor: {
                        type:'datetimebox',
                        options : {
                            required: true,
                            showSeconds: false,
                            valueField : 'createTime'
                        }
                    }
                },
                {title:'教师姓名',field:'teacherName',width:25,align:'left',sortable:false},
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
        	if(newRow == undefined){
    	    	 $("#tfquestiondatagrid").datagrid('insertRow', {
    	             index: 0,
    	             row: {}
    	         });
    	         newRow = 0;
    	         $("#tfquestiondatagrid").datagrid('beginEdit', 0);
        	}
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
        	$("#tfquestiondatagrid").datagrid("options").queryParams = params;
        	$("#tfquestiondatagrid").datagrid("reload");
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
<table id="tfquestiondatagrid"></table>
</body>
</html>

