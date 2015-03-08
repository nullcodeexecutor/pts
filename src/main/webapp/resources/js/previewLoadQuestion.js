

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
		   url: path+"/teacher/preview/querySelectQuestion.json",
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
		   url: path+"/teacher/preview/queryTFQuestion.json",
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
		   url: path+"/teacher/preview/queryCodeQuestion.json",
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
                  url: path+'/teacher/preview/run.json',
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

$(function(){
    $("#startExam").click(function(){        
        $(this).css("display", "none");
        $("#examContent").css("display", "block");
        loadQuestions();
        initAce();
        initEditorEvent();
        $("#endExam").css("display", "inline-block");
    });
});





