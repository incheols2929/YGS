<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../ssesion/ssesion.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="shortcut icon" type="image/x-icon" href="/favicon_1.ico">
<link rel="stylesheet" type="text/css" href="./css/login.css" />
<script type="text/javascript" src="./script/jquery-1.11.0.js"></script>
<script type="text/javascript" src="./script/login.js"></script>
<script type="text/javascript" src="./script/user/mt_user.js"></script>
<script type="text/javascript" src="./script/base64.js"></script>
<!-- <script type="text/javascript" src="http://cssrefresh.frebsite.nl/js/cssrefresh.js"></script> -->
<title>:: 영광군 농업기반 시설물시스템 ::</title>
</head>
<body>
  <!-- 메인 배경 이미지 -->
	<img id="backImg" src="./img/login/bg.jpg" /><!-- 1920픽셀x1018픽셀 -->
	<!--  로그인 테두리 이미지 -->
	<div id="wrap">
		<img id="logo" src="./img/login/main_text.png" /> <!-- 294픽셀x294픽셀 -->
		<div id="memberLogin">
			<table id="loginWrap">
				<tr>
					<td id="idBack">
						<input type="text" id="usr_id" tabindex="1" />
						<div id="regWrap">
							<a href="./user/user_reg.jsp">등록신청</a>
						</div>
					</td>
					<td id="pwBack">
						<input type="password" id="usr_pw" tabindex="2" />
						<div id="inqWrap">
							<a href="./user/acc_inquiry.jsp">계정문의</a>
						</div>
					</td>
					<td id="btnBack"> <!-- 로그인 이미지 버튼을 클릭하면 login.js 26번줄 -->
						<img id="login_btn" tabindex="3" src="./img/login/btn_login_normal.png" />
						
					</td>
				</tr>
			</table>
		</div>
	</div>

</body>
</html>