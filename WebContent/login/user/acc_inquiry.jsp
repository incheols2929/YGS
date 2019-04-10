<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="shortcut icon" type="image/x-icon" href="/favicon.ico">
<link rel="stylesheet" type="text/css" href="../css/reg.css" />
<link rel="stylesheet" type="text/css" href="../css/button.css" />
<script type="text/javascript" src="../script/jquery-1.11.0.js"></script>
<script type="text/javascript" src="../script/jquery.selectboxes.js"></script>
<script type="text/javascript" src="../script/user_reg.js"></script>
<script type="text/javascript" src="../script/user/mt_user.js"></script>
<script type="text/javascript" src="../script/base64.js"></script>
<title>:: 계정문의 ::</title>
</head>
<body>

<%@ include file="./header.jsp" %>

<div id="wrapParent">
	<div id="wrap">
		<img id="titleImg" src="../img/reg/title2.png" />
		<div id="content">
		
			<table cellspacing="0">
				<tr>
					<th><img src="../img/reg/text1.png" class="textImg" /></th>
					<td>
						<input type="text" id="usr_id" name="usr_id" class="wide" size="30" />
						<img src="../img/reg/no.png" class="chkImg" />
					</td>
				</tr>
			</table>
			
			<table cellspacing="0">
				<tr>
					<th><img src="../img/reg/text2.png" class="textImg" /></th>
					<td>
						<input type="text" id="usr_nm" name="usr_nm" class="wide" size="30"/>
						<img src="../img/reg/no.png" class="chkImg" />
					</td>
				</tr>
				<tr>
					<th><img src="../img/reg/text5.png" class="textImg" /></th>
					<td>
						<input  id="dept_nm" name="dept_nm" size="22"/>
						<button id="dept_btn" onclick="_MTUSR.form.deptLikeSrch();"></button>
						<img src="../img/reg/no.png" class="chkImg" id="chkImgDept" />
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
						<select id="dept_cd" disabled="disabled" name="dept_cd" class="wide">
							<option class="abc" value="">:: 선택하여 주십시오! ::</option>
						</select>
					</td>
				</tr>
				<tr>
					<th><img src="../img/reg/text6.png" class="textImg" /></th>
					<td>
						<input type="text" id="tel_num" name="tel_num" class="wide" size="30" />
						<img src="../img/reg/no.png" class="chkImg" />
					</td>
				</tr>
				<tr>
					<th><img src="../img/reg/text7.png" class="textImg" /></th>
					<td>
						<input type="text" id="email" name="email" class="wide" size="30" />
						<img src="../img/reg/no.png" class="chkImg" />
					</td>
				</tr>
			</table>
			
			<div id="btnWrap">
				<button id="backBtn"></button> <!-- 계정문의 이미지 버튼 -->
				<button id="okBtn" onclick="_MTUSR.form.acc();" style="background-image: url('/login/img/reg/btn_ok2.png');"></button> <!-- 계정문의 -->
			</div>
			
			<div id="warning">
				<p style="color: gray; text-align: center;">
					<b id="message" style="color: gray; font-size: 15px; "></b>
				</p>
			</div>
			<div id="acc_display2" style="display:none; font-size: 15px;" align="center">
				<p style="line-height: 25px; font-weight: bold; color: gray;">
					* 문의하신 사용자 아이디 : <b style="color:red;"><span id="sp_id">***</span></b> 의 비밀번호는 <b style="color:red;"><span id="sp_pw">***</span></b> 입니다.<br>
					* 보안을 위해서 확인후 창을 바로 닫아 주시기  바랍니다.<br>
					* <a href="../login.jsp" style="color:red; text-decoration:none;">여기</a>를 누르시면 로그인페이지로 이동합니다.
				</p>
			</div>
			
		</div>
	</div>
</div>

<%@ include file="./footer.jsp" %>

<div id="acc_display2" style="display:none; font-size: 13px;" align="center">
	<p style="line-height: 18px; ">
		* 문의하신 사용자 아이디 : <b style="color:red;"><span id="sp_id">***</span></b> 의 비밀번호는 <b style="color:red;"><span id="sp_pw">***</span></b> 입니다.<br>
		* 보안을 위해서 확인후 창을 바로 닫아 주시기  바랍니다.<br>
		* <a href="../login.jsp" style="color:red; text-decoration:none;">뒤로가기</a>을 누르시면 로그인페이지로 이동합니다.
	</p>
</div>

</body>
</html>