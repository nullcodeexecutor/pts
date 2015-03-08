<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>在线考试</title>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/foundation/foundation.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/exam.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/code.css" />
    
    <script>
    	var path = "${pageContext.request.contextPath}";
    </script>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/knockout/knockout-3.0.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/goTop.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/previewLoadQuestion.js"></script>
    <script src="${pageContext.request.contextPath}/resources/plugin/ace/ace.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
    </script>
</head>
<body>
<div id="jqContent" class="" style="text-align: left; ">
    <div id="headerCss" style="overflow-x: hidden; overflow-y: hidden; "></div>
    <div id="mainCss">
        <div id="examBox">
            <div id="examHead">
                <center><h3>${exam.name }</h3></center>
                    <span>
                    	${exam.teacherName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	${exam.startTimeStr }&nbsp;&nbsp;&nbsp;&nbsp;${exam.endTimeStr }
                    </span>
                <br/><br/>
                <center>
                    <a id="startExam" class="button">开始考试</a>
                </center>
            </div>
            <div id="examContent">
                <div class="select_question" data-bind="visible: selectQuestions().length>0">
                    <a href="#top_question_select" name="top_question_select" class="button disabled small expand question_button">选择题</a>
                    <div class="question_info"></div>
                    <div data-bind="foreach: selectQuestions">
                        <div class="columns">
                            <label data-bind="text: content"></label><br/>
                            <div data-bind="foreach: options">
                                <input type="radio" data-bind="attr:{ name: 'select_'+$parent.id, value: value }"><label data-bind="text: content"></label><br/>
                            </div>
                        </div>
                        <hr/>
                    </div>
                </div>
                <div class="tf_question" data-bind="visible: tfQuestions().length>0">
                    <a href="#top_question_tf" name="top_question_tf" class="button disabled small expand question_button">判断题</a>
                    <div class="question_info"></div>
                    <div data-bind="foreach: tfQuestions">
                        <div class="columns">
                            <label data-bind="text: content"></label><br/>
                            <input type="radio" data-bind="attr:{ name: 'tf_'+id}" value="1"><label>T</label>
                            <input type="radio" data-bind="attr:{ name: 'tf_'+id}" value="0"><label>F</label>
                        </div>
                        <hr/>
                    </div>
                </div>

                <div class="code_question" data-bind="visible: codeQuestions().length>0">
                    <a href="#top_question_code" name="top_question_code" class="button disabled small expand question_button">编程题</a>
                    <div class="question_info"></div>
                    <div data-bind="foreach: codeQuestions">
                        <div class="columns">
                            <label data-bind="text: content"></label>
                            <pre class="code_shower" data-bind="attr:{ language: languageType },text: code"></pre>
                            <div class="code_answer">
                                <pre class='code_editor' data-bind="attr:{ language: languageType },text: code">
                                </pre>
                                <div class="meta">
                                    <input type="button" value="RUN" class="run"/>
                                    <select class="languageSwitch" name="languageSwitch">
                                    	<option value="2">C</option>
                                    	<option value="3">JAVA</option>
                                    </select>									
                                    <input type="button" value="SUBMIT" class="submit" data-bind="attr:{ questionId: id }" style="float: right"/>
                                </div>
                                <div>
                                	<div class='data_input'></div>
                                	<div class='result_shower'></div>
                                </div>
                            </div>
                        </div>
                        &nbsp;<br/><hr/>
                    </div>
                </div>
                
                <center>
                    <a id="endExam" class="button">提交试卷</a>
                </center>

            </div>
            <!-- end examBox -->

        </div>
        <!-- end mainCss -->

    </div>
    <div id="footercss">
        <div id="footerLeft"></div><div id="footerCenter"></div><div id="footerRight"></div>
    </div>
</div>

<div id="question-switch">
    <ul class="question-group">
        
    </ul>
</div>
<img alt="Top_arrow" id="top_arrow" class="top_arrow" src="${pageContext.request.contextPath}/resources/images/goTop.png"/>
<img alt="loading" id="loading" src="${pageContext.request.contextPath}/resources/images/loading.gif"/>
<div id="time-item">
	<strong id="hour_show">0时</strong>
	<strong id="minute_show">0分</strong>
	<strong id="second_show">0秒</strong>
</div>
</body>
</html>