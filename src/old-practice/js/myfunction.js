// JavaScript Document

//动态的显示当前时间
;(function($){
	"use strict";
	var time = "";
	//获得当前的时间
	function currentTime(){
		var nowDate = new Date();
		var year = nowDate.getFullYear();
		var month = changeNum(nowDate.getMonth()+1);
		var date = changeNum(nowDate.getDate());
		var hour = changeNum(nowDate.getHours());
		var miunte = changeNum(nowDate.getMinutes());
		var second = changeNum(nowDate.getSeconds());

		return year+"-"+month+"-"+date+" "+hour+":"+miunte+":"+second;	
	}
	function changeNum(t){
		return t < 10 ? "0" + t : t;
	}
	$.fn.showCurrentTime = function(){
		var div = $(this);
		return this.each(function(){
			setInterval(function(){
				time = currentTime();
				div.text(time);				
			},1000);
		});
		
	};
})(jQuery);
