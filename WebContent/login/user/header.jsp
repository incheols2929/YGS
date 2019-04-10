<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../ssesion/ssesion.jsp" %>
<!-- Style -->
<style>
	#header {
		background-image: url("/login/img/reg/top_bg.png");
		width: 100%;
		height: 42px;
	}
	
	#header #headerWrap {
		position: relative;
		width: 1000px;
		height: 47px;
		margin: 0 auto;
	}
	
	#header #headerWrap #login {
		position: absolute;
		right: 0;
		top: 8px;
		cursor: pointer;
	}
	
	#topMenu {
		background-image: url("/login/img/reg/menu_bg.png");
		width: 100%;
		height: 77px;
		text-align: center;
	}
	
	#topMenu .top_btn {
		margin: 20px;
		cursor: pointer;
		background-color: transparent;
	}
	
	#topMenu #reg {
		border: 0;
		width: 115px;
		height: 37px;
		background-image: url("/login/img/reg/btn1_normal.png");
	}#topMenu #reg:HOVER {
		background-image: url("/login/img/reg/btn1_over.png");
	}#topMenu #reg:ACTIVE {
		background-image: url("/login/img/reg/btn1_on.png");
	}
	
	#topMenu #inq {
		border: 0;
		width: 115px;
		height: 37px;
		background-image: url("/login/img/reg/btn2_normal.png");
	}#topMenu #inq:HOVER {
		background-image: url("/login/img/reg/btn2_over.png");
	}#topMenu #inq:ACTIVE {
		background-image: url("/login/img/reg/btn2_on.png");
	}
	
	#topMenu #edit {
		border: 0;
		width: 115px;
		height: 37px;
		background-image: url("/login/img/reg/btn3_normal.png");
	}#topMenu #edit:HOVER {
		background-image: url("/login/img/reg/btn3_over.png");
	}#topMenu #edit:ACTIVE {
		background-image: url("/login/img/reg/btn3_on.png");
	}
</style>


<!-- Element -->
<div id="header">
	<div id="headerWrap">
		<img id="ci" src="../img/reg/logo.png" /> <!-- 상단로고 290픽셀x39픽셀 -->
<%
if(id != null){
%>
		<img id="login" src="../img/reg/logout_btn.png" /> 
<%
}else{
%>
		<img id="login" src="../img/reg/login_btn.png" />
<%
}
%>
	</div>
</div>

<div id="topMenu">
	<button id="reg" class="top_btn" onclick="location.href='/login/user/user_reg.jsp';"></button><!-- 등록신청 버튼 -->
	<button id="inq" class="top_btn" onclick="location.href='/login/user/acc_inquiry.jsp';"></button> <!-- 계정문의 버튼 -->
	<button id="edit" class="top_btn"></button>
</div>


<!-- Script -->
<script>
<%
if(id != null){
%>
var isLogin = true;
<%
}else{
%>
var isLogin = false;
<%
}
%>
(function(){
	$("#edit").on("click", function(){
		if("null" == "<%=id%>"){
			if(confirm("사용자 정보 확인 및 수정은 로그인이 필요합니다.\n로그인 화면으로 이동하시겠습니까?")){
				location.href = "/";
			}
		}else{
			location.href = '/login/user/user_edit.jsp'; //사용자 정보수정
		}
	});
	
	if(location.pathname == "/login/user/user_reg.jsp"){ //사용자 등록신청
		$("#reg").css("background-image", "url('/login/img/reg/btn1_on.png')");
	}else if(location.pathname == "/login/user/acc_inquiry.jsp"){ //계정문의
		$("#inq").css("background-image", "url('/login/img/reg/btn2_on.png')");
	}else if(location.pathname == "/login/user/user_edit.jsp"){
		$("#edit").css("background-image", "url('/login/img/reg/btn3_on.png')");
	}
	
	$("#login").on("click", function(){
		if(isLogin){
			if(confirm("로그아웃 하시겠습니까?")){
				location.href = "/login/logout.jsp";
			}
		}else{
			if(confirm("로그인 페이지로 이동하시겠습니까?")){
				location.href = "/";
			}
		}
	});
})();
</script>