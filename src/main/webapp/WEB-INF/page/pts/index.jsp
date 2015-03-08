
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>程序类考试系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-Language" content="zh-CN"/>
    <meta http-equiv="pragma" content="no-cach" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
	<link href="${pageContext.request.contextPath}/resources/css/transdmin.css" rel="stylesheet" type="text/css" media="screen" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap/css/bootstrap.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/knockout/knockout-3.0.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/css/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/css/bootstrap/js/bootstrap-paginator.js"></script>
    <script>
    	var currentPage = 1;
    	var pageSize = 5;
    	var viewModel = undefined;
    	var queryOptions = {
    			examName:'',
    			courseName:'',
    			teacherName:''
    	};
    	function initPage(totalPages){
	        var options = {
		            currentPage: currentPage,
		            totalPages: totalPages,
		            alignment:"center",
		            itemTexts: function (type, page, current) {
	                    switch (type) {
	                    case "first":
	                        return "首页";
	                    case "prev":
	                        return "上一页";
	                    case "next":
	                        return "下一页";
	                    case "last":
	                        return "末页";
	                    case "page":
	                        return ""+page;
	                    }
	                },
	                onPageChanged: function(e,oldPage,newPage){
	                    currentPage = newPage;
	                    bindData();
	                }
		        }
		        $('#example').bootstrapPaginator(options);    		
    	}
    	function bindData(){
    		var args = "page="+currentPage+"&pageSize="+pageSize;
    		if(queryOptions.examName != ''){
    			args += ("&examName="+queryOptions.examName);
    		}
    		if(queryOptions.courseName != ''){
    			args += ("&courseName="+queryOptions.courseName);
    		}
    		if(queryOptions.teacherName != ''){
    			args += ("&teacherName="+queryOptions.teacherName);
    		}
    		$.ajax({
 			   type: "GET",
 			   url: "${pageContext.request.contextPath}/queryExam.json",
 			   data: args,
 			   success: function(data){
 				   var totalPages = (data.total%pageSize==0)?(data.total/pageSize):(data.total/pageSize+1);
 				   if(data.rows.length==0){
 					   $("#message").html("没有数据");
 					   viewModel.notHasData(true);
 					   viewModel.hasData(false);
 				   }else{
 					   viewModel.notHasData(false);
 					   viewModel.hasData(true);
 				   }
 				   if(data.total<=pageSize){
 					   viewModel.pageable(false);
 				   }else{
 					   viewModel.pageable(true);
 				   }
 				   viewModel.exams(data.rows);
 				   initPage(totalPages);
 			   }
 			});
    	}
    	$(function(){
    		$("#query").click(function(){
    			var form = $(this).parent();
    			queryOptions.examName = form.find("input[name='examName']").val();
    			queryOptions.courseName = form.find("input[name='courseName']").val();
    			queryOptions.teacherName = form.find("input[name='teacherName']").val();
    			currentPage = 1;
                bindData();
                return false;
    		});
    		function examModel(){
		        var self = this;
		        self.exams = ko.observableArray();
		        self.notHasData = ko.observable(true);
		        self.hasData = ko.observable(false);
		        self.pageable = ko.observable(false);
		 	}
		    viewModel = new examModel();
		    ko.applyBindings(viewModel);
		    bindData();
    	});
    </script>
    <style type="text/css">
    </style>
</head>
<body>

	<div id="wrapper">
        <!-- You can name the links with lowercase, they will be transformed to uppercase by CSS, we prefered to name them with uppercase to have the same effect with disabled stylesheet -->
        <%@include file="head.jsp" %>
        <!-- // #end mainNav -->
        
        <div id="containerHolder">
			<div id="container">
				
				
				<form class="form-inline">
				  <input type="text" name="examName" class="input-medium" placeholder="考试"/>
				  <input type="text" name="courseName" class="input-medium" placeholder="课程"/>
				  <input type="text" name="teacherName" class="input-medium" placeholder="教师"/>
				  <button class="btn" id="query">查找</button>
				</form>
				<div id="message" style="padding-left:20px" data-bind="visible: exams().length<1">
				    loading...
				</div>
				
				<table data-bind="visible: exams().length>0" class="table table-bordered">
				  <thead>
				    <tr>
				      <th>考试名称</th>
				      <th>类型</th>
				      <th>开始时间</th>
				      <th>结束时间</th>
				      <th>课程</th>
				      <th>教师</th>
				    </tr>
				  </thead>
				  <tbody data-bind="foreach:exams">
				    <tr>
				      <td><a data-bind="attr:{ href: 'entry?examId='+id },text:name" target="_blank"></a></td>
				      <td data-bind="text: type == 1 ? '考试' : '练习'"></td>
				      <td data-bind="text:startTimeStr"></td>
				      <td data-bind="text:endTimeStr"></td>
				      <td><a data-bind="attr:{ href: 'grade?courseId='+courseId },text:courseName"></a></td>
				      <td data-bind="text:teacherName"></td>
				    </tr>
				  </tbody>
				</table>
				 <div id="example" data-bind="visible: pageable" style="cursor: pointer;"></div>
				
                <div class="clear"></div>
            </div>
            <!-- // #container -->
        </div>	
        <!-- // #containerHolder -->
        
        <p id="footer">welcome to pts.</a></p>
    </div>
    

</body>
</html>