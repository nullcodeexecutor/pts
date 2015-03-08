$(function(){
    $("#top_arrow").hide();
    $(window).scroll(function(){
        if ($(window).scrollTop()>100){
            $("#top_arrow").fadeIn(400);
        }
        else{
            $("#top_arrow").fadeOut(400);
        }
    });
    $("#top_arrow").click(function(){
        $('body,html').animate({scrollTop:0},400);
        return false;
    });
});