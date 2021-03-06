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
<title>:: 사용자 등록신청 ::</title>
</head>
<body>

<%@ include file="./header.jsp" %> <!-- 헤더부분 상단 이미지 등등 -->
<div id="wrapParent">
	<div id="wrap">
		<img id="titleImg" src="../img/reg/title1.png" />
		<div id="content">
			
			<table cellspacing="0">
				<tr>
					<th><img src="../img/reg/text1.png" class="textImg" /></th>
					<td>
						<input type="text" id="usr_id" name="usr_id" class="wide" size="30" />
						<img src="../img/reg/no.png" class="chkImg" />
					</td>
				</tr>
				<tr>
					<th><img src="../img/reg/text3.png" class="textImg" /></th>
					<td>
						<input type="password" id="usr_pw" name="usr_pw" class="wide" size="30"/>
						<img src="../img/reg/no.png" class="chkImg" />
					</td>
				</tr>
		        <tr>
					<th><img src="../img/reg/text4.png" class="textImg" /></th>
					<td>
						<input type="password" id="usr_pw_re" name="usr_pw_re" class="wide" size="30"/>
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
				<button id="backBtn"></button>
				<!-- 등록하기 버튼 이미지 -->
				<button id="okBtn" onclick="_MTUSR.form.reg();" style="background-image: url('/login/img/reg/btn_ok.png');"></button>
			</div>
			
			<div id="warning">
				<p style="color: gray; text-align: center;">
					<b id="message" style="color: gray; font-size: 15px; "></b>
				</p>
			</div>
				
		</div>
	</div>
</div>

<%@ include file="./footer.jsp" %>

</body>
</html>