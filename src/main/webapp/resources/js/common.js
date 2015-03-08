function dateFormat(fmt, date) { 
    var o = {
        "M+": date.getMonth() + 1, //月份
        "d+": date.getDate(), //日
        "H+": date.getHours(), //小时
        "m+": date.getMinutes(), //分
        "s+": date.getSeconds(), //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
 $.extend($.fn.datagrid.defaults.editors, {
     datetimebox: {
         init: function(container, options){
             var input = $('<input class="easyui-datetimebox" style="width:150px">').appendTo(container);
             return input.datetimebox({
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
         },
         getValue: function(target){
        	 var dateStr = $(target).parent().find('input.combo-value').val();
             return new Date(dateStr).getTime();
        },
         setValue: function(target, value){
        	 var date = new Date();
             date.setTime(value);
             $(target).datetimebox("setValue",dateFormat("yyyy/MM/dd HH:mm", date));
         },
         resize: function(target, width){
            var input = $(target);
            if ($.boxModel == true){
                 input.width(width - (input.outerWidth() - input.width()));
            } else {
                 input.width(width);
             }
         }
     }
});