function resize(){
	$("#wrapParent").height(function(){
		var height = $(window).height() - ($("#header").height() + $("#topMenu").height() + $("#footer").height());
		if(height < 440){
			return 440;
		}else{
			return height;
		}
	});
	
	$("#wrap").css("padding-top",
		($("#wrapParent").height() - $("#wrap").height()) / 2 + "px"	
	);
}

$(document).ready(function (){
	
	resize();
	
	$(window).resize(function(){
		resize();
	});
	
	$("input, select").focus(function(){
		$(this).parent().parent().css("background-color", "#efefef");
	}).focusout(function(){
		$(this).parent().parent().css("background-color", "#ffffff");
	});
	
	$("#usr_id").focus();
	
	$("#backBtn").on("click", function(){
		if(isLogin){
			if(confirm("북마크 페이지로 이동하시겠습니까?")){
				location.href = "../bookmark.jsp";
			}
		}else{
			if(confirm("로그인 페이지로 이동하시겠습니까?")){
				location.href = "../login.jsp";
			}
		}
	});
	
	$("#usr_id").focus(function(){
		$(this).next().show();
		
		if($(this).val().length < 6){
			$(this).next().attr("src", "../img/reg/no.png");
		}else{
			$(this).next().attr("src", "../img/reg/ok.png");
		}
	}).keyup(function(){
		$(this).next().show();
		
		if($(this).val().length < 6){
			$(this).next().attr("src", "../img/reg/no.png");
		}else{
			$(this).next().attr("src", "../img/reg/ok.png");
		}
	});
	
	$("#usr_nm").focus(function(){
		$(this).next().show();
		
		if($(this).val() == ""){
			$(this).next().attr("src", "../img/reg/no.png");
		}else{
			$(this).next().attr("src", "../img/reg/ok.png");
		}
	}).keyup(function(){
		if($(this).val() == ""){
			$(this).next().attr("src", "../img/reg/no.png");
		}else{
			$(this).next().attr("src", "../img/reg/ok.png");
		}
	});
	
	$("#usr_pw").focus(function(){
		$(this).next().show();
		
		if($(this).val().length < 6){
			$(this).next().attr("src", "../img/reg/no.png");
		}else{
			$(this).next().attr("src", "../img/reg/ok.png");
		}
	}).keyup(function(){
		if($(this).val().length < 6){
			$(this).next().attr("src", "../img/reg/no.png");
		}else{
			$(this).next().attr("src", "../img/reg/ok.png");
		}
	});
	
	$("#usr_pw_re").focus(function(){
		$(this).next().show();
		
		if($(this).val() != $("#usr_pw").val()){
			$(this).next().attr("src", "../img/reg/no.png");
		}else{
			$(this).next().attr("src", "../img/reg/ok.png");
		}
	}).keyup(function(){
		if($(this).val() != $("#usr_pw").val()){
			$(this).next().attr("src", "../img/reg/no.png");
		}else{
			$(this).next().attr("src", "../img/reg/ok.png");
		}
	});
	
	$("#dept_nm").focus(function(){
		$("#chkImgDept").show();
		
		if($("#dept_cd").val() == ""){
			$("#chkImgDept").attr("src", "../img/reg/no.png");
		}else{
			$("#chkImgDept").attr("src", "../img/reg/ok.png");
		}
	}).keyup(function(){
		if($("#dept_cd").val() == ""){
			$("#chkImgDept").attr("src", "../img/reg/no.png");
		}else{
			$("#chkImgDept").attr("src", "../img/reg/ok.png");
		}
	});
	
	$("#dept_cd").on("change", function(){
		if($(this).val() == ""){
			$("#chkImgDept").attr("src", "../img/reg/no.png");
		}else{
			$("#chkImgDept").attr("src", "../img/reg/ok.png");
		}
	});
	
	$("#tel_num").focus(function(){
		$(this).next().show();
		
		if($(this).val() == ""){
			$(this).next().attr("src", "../img/reg/no.png");
		}else{
			$(this).next().attr("src", "../img/reg/ok.png");
		}
	}).keyup(function(){
		if($(this).val() == ""){
			$(this).next().attr("src", "../img/reg/no.png");
		}else{
			$(this).next().attr("src", "../img/reg/ok.png");
		}
	});
	
	$("#email").focus(function(){
		$(this).next().show();
		
		var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;
		
		if(filter.test($('#email').val()) != true){
			$(this).next().attr("src", "../img/reg/no.png");
		}else{
			$(this).next().attr("src", "../img/reg/ok.png");
		}
	}).keyup(function(){
		var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;
		
		if(filter.test($('#email').val()) != true){
			$(this).next().attr("src", "../img/reg/no.png");
		}else{
			$(this).next().attr("src", "../img/reg/ok.png");
		}
	});
	
});
