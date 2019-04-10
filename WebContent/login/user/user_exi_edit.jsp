<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String usr_id = request.getParameter("usr_id");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css" href="../css/lne.css" />
<script type="text/javascript" src="../script/jquery-1.6.2.js"></script>
<script type="text/javascript" src="../script/user/mt_user.js"></script>
<script type="text/javascript" src="../../script/jquery.selectboxes.js"></script>
<script type="text/javascript" src="../script/base64.js"></script>
<script type="text/javascript">
$(document).ready(function () { 
	//_MTUSR.form.deptcode(); //부서코드를 호출한다.
	_MTUSR.UE.selinfo("<%=usr_id%>");
});
</script>
<style type="text/css">

	.abc{
		line-height: 20px;
		background-color: #bed9ff;
		margin-top:3px;
	}
</style>
<title>:: 사용자 정보수정 ::</title>
 
</head>
<body>
<div id="exi_wapp">
	<div id="exi_subject">
		<img src="../img/login_img/reg_title.gif"/>
		<img src="../img/back_img_btn.gif" style="margin: 0 0 1px 145px; cursor: pointer;" onclick="history.back();"/>
	</div>
	<div id="exi_main">
		<div id="exi_form">
			<div id="exi_text" style="border:0px solid #000000;"></div>
			<div id="exi_select" style="border:0px solid #000000;">
				<form id="exifrm">
				<div>
					<input type="text" id="usr_id" name="usr_id" style="width:100%; height:18px; border:1px solid #d9d9d9;"/>
					<input type="text" id="usr_nm" name="usr_nm" style="width:100%; height:18px; margin-top:5px; border:1px solid #d9d9d9;"/>
					<input type="password" id="usr_pw" name="usr_pw" style="width:100%; height:18px; margin-top:5px; border:1px solid #d9d9d9;"/>
					<input type="password" id="usr_pw_re" name="usr_pw_re" style="width:100%; height:18px; margin-top:5px; border:1px solid #d9d9d9;"/>
					<input type="text" id="dept_nm" name="dept_nm" style="width:145px; height:18px; margin-top:5px; border:1px solid #d9d9d9;"/> <img src="../img/reg_dept_btn.gif"  onclick="_MTUSR.form.deptLikeSrch();" align="absmiddle" style="cursor: pointer;"/>
					<select id="dept_cd" disabled="disabled" name="dept_cd" style="width:221px;  height:20px; margin-top:5px; border:1px solid #d9d9d9;">
						<option class="abc" value="">:: 선택하여 주십시오! ::</option>
					</select>
					<input type="text" id="tel_num" name="tel_num" style="width:100%; height:18px; margin-top:5px; border:1px solid #d9d9d9;"/>
					<input type="text" id="email" name="email" style="width:100%; height:18px; margin-top:5px; border:1px solid #d9d9d9;"/>
				</div>
				</form>
				<div style="width:217px; height:26px; text-align:left; margin-top:5px;">
					<img src="../img/login_img/acc_ok.gif" style="cursor: pointer;" onclick="_MTUSR.UE.deptedit();"/> <img src="../img/login_img/acc_reset.gif" style="cursor: pointer;" onclick="_MTUSR.form.reset('exifrm');"/>
				</div>
			</div>
		</div>
		<div id="exi_regTXT">
			<p style="padding:0 0 0 25px; color: #000000;"><b>※ 타 시스템 사용자 승인여부 신청</b></p> 
		</div>
		<div id="exi_phonenumber">
			<p style="padding:0 0 0 25px; color: #707070;">문의 : 토지자원과 033-249-2846</p> 
		</div>
		<div id="exi_content">
			<p style="line-height: 18px; padding:10px 0 0 23px;">
				* 기존 시스템 <b>정책결정지원 인트라넷</b>, <b>투자유치지원시스템</b>에 등록된 사용자는<br/>&nbsp;&nbsp;&nbsp;<b>부서, 연락처, 이메일</b>을 입력하여 주십시오!<br/>
				* 입력된 모든정보는 관리자 승인 및 본인확인을 위한 정보로 사용되므로 모든정보는 정확히 <br>&nbsp;&nbsp;&nbsp;입력하셔야 합니다.
			</p>
		</div>
	</div>
	<div id="warning" style="display:none;  background:url(../img/warning_img.gif) no-repeat; width:370px; height:38px; margin:5px 0 0 80px; ">
		<p style="padding:10px 0 0 0; color: #ffffff; text-align: center; "><b id="message"></b></p>
	</div>
</div>

</body>
</html>