jQuery.fn.center = function () {
	this.css("margin-top", (jQuery(window).height() - this.outerHeight()) / 2 + jQuery(window).scrollTop() + "px");
	return this;
};

$(document).ready(function (){

	$("#wrap").center();
	
	$(window).resize(function(){
		$("#wrap").center();
	});
	
	$("#usr_id").focus(function(){
		$("#regWrap").slideToggle({queue : false});
	}).focusout(function(){
		$("#regWrap").slideToggle({queue : false});
	});
	
	$("#usr_pw").focus(function(){
		$("#inqWrap").slideToggle({queue : false});
	}).focusout(function(){
		$("#inqWrap").slideToggle({queue : false});
	});
	
	///여기로 이동 클릭 이벤트 mt_user.js
	$("#login_btn").on("click", function(){
		_MTUSR.form.deprcd_Check();
		
	});
	
});