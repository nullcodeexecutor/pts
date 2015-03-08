function timer(intDiff){
	var hour_show = $("#hour_show");
	var minute_show = $("#minute_show");
	var second_show = $("#second_show");
	window.setInterval(function(){
	var day=0,
		hour=0,
		minute=0,
		second=0;//时间默认值		
	if(intDiff > 0){
		day = Math.floor(intDiff / (60 * 60 * 24));
		hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
		minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
		second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
	}
	if (minute <= 9) minute = '0' + minute;
	if (second <= 9) second = '0' + second;
	hour_show.html('<s id="h"></s>'+hour+'时');
	minute_show.html('<s></s>'+minute+'分');
	second_show.html('<s></s>'+second+'秒');
	intDiff--;
	}, 1000);
} 

function loadinOpen(ele){
	var metaOffset = ele.parent().offset();
	$("#loading").css("display", "block");
	$("#loading").offset({ top: metaOffset.top+2, left: metaOffset.left+400 });
	$("#loading").css("display", "block");
}
function loadinClose(ele){
	$("#loading").css("display", "none");
}


function loadSelectQuestions(){
    var questions = [];
    $.ajax({
		   type: "GET",
		   url: path+"/student/exam/querySelectQuestion.json",
		   async: false,
		   success: function(data){
			   questions = data.selectQuestions;
		   }
	});
    return questions;
}

function loadTFQuestions(){
    var questions = [];
    $.ajax({
		   type: "GET",
		   url: path+"/student/exam/queryTFQuestion.json",
		   async: false,
		   success: function(data){
			   questions = data.tfQuestions;
		   }
	});
    return questions;
}


function loadCodeQuestions(){
    var questions = [];
    $.ajax({
		   type: "GET",
		   url: path+"/student/exam/queryCodeQuestion.json",
		   async: false,
		   success: function(data){
			   questions = data.codeQuestions;
		   }
	});
    return questions;
}

function loadQuestions(){
    function questionModel(){
        var self = this;
        self.tfQuestions = ko.observableArray();
        self.selectQuestions = ko.observableArray();
        self.codeQuestions = ko.observableArray();        
    }
    var viewModel = new questionModel();
    ko.applyBindings(viewModel);
    
    var selectQuestions = loadSelectQuestions();
    var tfQuestions = loadTFQuestions();
    var codeQuestions = loadCodeQuestions();
    
    viewModel.selectQuestions(selectQuestions);
    viewModel.tfQuestions(tfQuestions);
    viewModel.codeQuestions(codeQuestions);
    
    if(selectQuestions.length>0){
    	$("#question-switch").css("display", "block");
    	$("#question-switch ul").append("<li><a href='#top_question_select' class='question-button'>选择题</a></li>");
    }
    if(tfQuestions.length>0){
    	$("#question-switch").css("display", "block");
    	$("#question-switch ul").append("<li><a href='#top_question_tf' class='question-button'>判断题</a></li>");
    }
    if(codeQuestions.length>0){
    	$("#question-switch").css("display", "block");
    	$("#question-switch ul").append("<li><a href='#top_question_code' class='question-button'>编程题</a></li>");
    }    
}

function getLanguageType(type){
	if(type=='2'){
		return 'c_cpp';
	}
	if(type=='3'){
		return 'java';
	}	
	return "c_cpp";
}

function setLanguageSwitch(e, languageType){
	if(languageType=='1'){
		e.val('2');
		return;
	}	
	if(languageType=='2'){
		e.val(languageType);		
	}
	if(languageType=='3'){
		e.val(languageType);
	}	
	e.attr("disabled","disabled");  
}



function initAce(){	
	$(".code_shower").each(function(){
	    var editor = ace.edit(this);
	    editor.setTheme("ace/theme/github");
	    editor.getSession().setMode("ace/mode/"+getLanguageType($(this).attr("language")));
	    editor.setFontSize(12);
	    editor.setReadOnly(true);
	    if(editor.getValue()==""){
	        $(this).css("display", "none");
	    }
	});
	$(".code_editor").each(function(){
	    var editor = ace.edit(this);
	    editor.setTheme("ace/theme/github");
	    editor.getSession().setMode("ace/mode/"+getLanguageType($(this).attr("language")));
	    editor.setFontSize(14);
	    
	    var select = $(this).parent().find(".languageSwitch");
	    setLanguageSwitch(select, $(this).attr("language"));
	});
	$(".data_input").each(function(){
	    var editor = ace.edit(this);
	    editor.setTheme("ace/theme/github");
	    editor.setFontSize(14);
	});
	$(".result_shower").each(function(){
	    var editor = ace.edit(this);
	    editor.setTheme("ace/theme/github");
	    editor.setReadOnly(true);
	    editor.setFontSize(14);
	});
}

function initEditorEvent(){
	$(".run").each(function(){
		var runBtn = $(this);
		runBtn.click(function(){
			loadinOpen(runBtn);
			var e = $(this).parent().parent().find(".code_editor")[0];
		    var editor = ace.edit(e);			
			var data_input = $(this).parent().parent().find(".data_input");
			var result_shower = $(this).parent().parent().find(".result_shower");
			var inputEditor = ace.edit(data_input[0]);
			
			var type = $(this).parent().parent().find(".languageSwitch option:selected").val();
		    $.ajax({
         		 type: "POST",
                  url: path+'/student/exam/run.json',
                  data: "code="+encodeURIComponent(editor.getValue())+"&languageType="+type+"&input="+inputEditor.getValue(),
                  dataType: "json", 
                  success: function (data) {
			    	var result = ace.edit(result_shower[0]);
			    	result.setTheme("ace/theme/github");
			    	result.setReadOnly(true);
			    	result.setValue(data.result);
					loadinClose(runBtn);
                  }
         	});
		});
	});
	$(".languageSwitch").each(function(){
		$(this).change(function(){
			var e = $(this).parent().parent().parent().find(".code_editor")[0];
		    var editor = ace.edit(e);
		    editor.getSession().setMode("ace/mode/"+getLanguageType($(this).val()));
		});		
	});
}

function anwserSubmit(){
	//选择题和判断题提交答案
	$("#examContent input[type='radio']").each(function(){
		$(this).click(function(){
			var name = $(this).attr("name");
			var value = $(this).attr("value");
			$.ajax({
        		 type: "POST",
                 url: path+'/student/exam/submitAnwser.json',
                 data: "name="+name+"&value="+value,
                 dataType: "json", 
                 success: function (data) {
                 }
        	});
		});
	});
}

function codeAnswerSubmit(){
	$(".meta .submit").each(function(){
		var btn = $(this);
		btn.click(function(){
			loadinOpen(btn);
			var languageType = $(this).parent().find(".languageSwitch option:selected").val();
			var questionId = $(this).attr("questionId");
			var codeEditor = $(this).parent().parent().find(".code_editor")[0];
			var editor = ace.edit(codeEditor);
			var code = encodeURIComponent(editor.getValue());
			$.ajax({
       		 type: "POST",
                url: path+'/student/exam/submitCodeAnwser.json',
                data: "languageType="+languageType+"&questionId="+questionId+"&code="+code,
                dataType: "json", 
                success: function (data) {
					if(data.flg){
						btn.css("background", "#00AA00");
					}else{
						btn.css("background", "#DD0000");
					}	
					loadinClose(btn);
                }
       	});
		});
	});
}

function submitExam(){
	window.onbeforeunload=function(){};
	window.document.location.href = path+"/student/exam/submitExam";
}

function endExam(){
	$.ajax({
		   type: "GET",
		   url: path+"/student/exam/isComplete.json",
		   async: false,
		   success: function(data){
				if(!data.flg){
					if(confirm("您还没有做完，确定要提交试卷吗")){
						submitExam();
					} 
				}else{
					submitExam();
				}				
		   }
	});
}
function initTimer(){
	var t = false;
	$.ajax({
		   type: "GET",
		   url: path+"/student/exam/start.json",
		   async: false,
		   success: function(data){
				t = data.flg;
				if(!data.flg){
					alert(data.msg);
					return;
				}
				
				if((data.endTime-data.nowTime)<(24*60*60*1000)){
					timer((data.endTime-data.nowTime)/1000);
					$("#time-item").css("display", "block");					
				}	
		   }
	});
	return t;
}

$(function(){
	window.onbeforeunload=function(event){
        return "请先提交考试，否则您的答卷将丢失";
    }
    $("#startExam").click(function(){
    	var flg = initTimer();
    	if(!flg){
    		return;
    	}  	
        
        $(this).css("display", "none");
        $("#examContent").css("display", "block");
        loadQuestions();
        initAce();
        initEditorEvent();
        anwserSubmit();
        codeAnswerSubmit();
        $("#endExam").css("display", "inline-block");
        $("#endExam").click(function(){
        	endExam();
        });
    });
});





