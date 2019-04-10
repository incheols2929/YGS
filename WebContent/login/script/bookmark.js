jQuery.fn.center = function () {
	this.css("margin-top", (jQuery(window).height() - this.outerHeight()) / 2 + jQuery(window).scrollTop() + "px");
	return this;
};

$("#input_purpose").keyup(function(e){
	if(e.which == 13){
		fnb.connect.purposeAdd();
	}
});

$(document).ready(function (){
	
	$("#input_purpose").focus();
	$("#wrap").center();
	
	if(session_id == "null"){
		alert("접속권한이 존재 하지 않습니다.\n로그인 페이지로 이동합니다.");
		window.location = "./login.jsp";
	}
	
	$(window).resize(function(){
		$("#wrap").center();
	});
	
	
	fnb.connect.favoritescreate(); //즐겨찾기생성
	_N.init.paging(0, 1);//공지사항을 불러온다.
	fnb.connect.provide(); //
	
	if(session_dept_yn == ""){
		fnb.connect.deptChangePOP();
	}
	
	$(".downBtn").click(function(){
		window.open($(this).attr("page"), "_blank");
	});
	
});
