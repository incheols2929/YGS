<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="geomex.svc.handler.Code"%>
<%@ page import="geomex.pkg.usr.UserinfoBean"%>
<%@ include file="../ssesion/ssesion.jsp" %>
<%
	String dept_nm = Code.getDeptNM(session_dept);//해당하는 부서(기관명)를 찾음
	UserinfoBean uib = new UserinfoBean();
	String purpose = uib.getPurpose(id); //사용목적판단하는부분
	if(purpose == null || purpose == ""){
		purpose = "";
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="shortcut icon" type="image/x-icon" href="/favicon_1.ico">
<link rel="stylesheet" type="text/css" href="./css/bookmark.css" />
<link rel="stylesheet" type="text/css" href="./css/button.css" />
<link rel="stylesheet" type="text/css" href="./css/main.css" />
<link rel="stylesheet" type="text/css" href="./css/info.css" />
<link rel="stylesheet" type="text/css" href="./css/fnd.css" />
<link rel="stylesheet" type="text/css" href="./css/qtip.css" />
<link rel="stylesheet" type="text/css" href="./css/styles.css" />
<script type="text/javascript" src="./script/jquery-1.6.2.js"></script>
<script type="text/javascript" src="./script/bookmark.js"></script>
<script type="text/javascript" src="./script/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript" src="./script/jquery.bpopup-0.5.1.min.js"></script>
<script type="text/javascript" src="./script/redips-drag.js"></script>
<script type="text/javascript" src="./script/jquery.qtip-1.0.0-rc3.js"></script>
<script type="text/javascript" src="./script/jquery.selectboxes.js"></script>
<script type="text/javascript" src="./script/FNB.js"></script>
<script type="text/javascript" src="./script/notice.js"></script>
<!-- <script type="text/javascript" src="./script/deployJava.js"></script> -->
<script type="text/javascript">
var session_id = "<%= id%>";  
var session_ugrp_id = "<%= session_ugrp_id%>";
var session_dept_yn = "<%= session_dept_yn%>";
var dept = "<%= session_dept%>";
</script>
<title>:: 영광군 농업기반 시설물시스템 ::</title>
</head>
<body>

<img id="backImg" src="./img/login/bg.jpg" /> <!-- 배경이미지 1920픽셀x1018픽셀 -->

<div id="wrap">
	
	<!-- CI / HOME -->
	<div id="top_btn"> 
       	<img src="./img/bookmark/ci.png" /> <!-- 정선군 로고 이미지 240픽셀x48픽셀 -->
       	<!-- <img id="go_main" src="./img/bookmark/btn_goback.png" onclick="alert(); return false;fnb.connect.logout();" /> -->
   	</div>
   	
	<!-- 즐겨찾기 -->
	<div id="left">
		<div id="header1">
			<img src="./img/bookmark/title1.png" style="vertical-align: bottom;" />
			<img src="./img/bookmark/btn1.png" onclick="fnb.connect.showPOP();" style="margin-left: 60px;" />
		</div>
		<div id="box1">
			<div id="left_middle">
				<div id="enjoysch"></div> 
			</div>
		</div>
	</div>
  
	<!-- 공지사항 -->
	<div id="right1">
		<div id="header2">
			<img src="./img/bookmark/title2.png" style="vertical-align: bottom;" />
			<img src="./img/bookmark/btn2.png" onclick="_N.init.paging(1, 2);" style="margin-left: 560px;" />
		</div>
		<div id="box2">
			<div id="notice_middle">
				<div id="notice_box"></div>
			</div>
		</div>
	</div>
	
	<!-- 도움말 -->
	<div id="help">
		<div id="header3">
			<img src="./img/bookmark/title3.png" style="vertical-align: bottom;" />
		</div>
		<div style="margin: 10px;"> 
            <a href='./file/userhelp.zip'>사용자 메뉴얼</a> 
           <!--  	<a href='#'>사용자 메뉴얼</a>-->
		</div>
		<div style="margin: 10px;"> 
		    <a href="./file/javahelp.zip">자바관련문제해결가이드</a>
		     <!--  <a href='#'>자바관련 문제해결 가이드</a> -->
		</div>
		<div style="margin: 10px;"> 
		    <a href="./file/reserhelp.zip">저수량 관리 메뉴얼</a>
		     <!--  <a href='#'>자바관련 문제해결 가이드</a> -->
		</div>
	</div>
	
	<!-- 시스템명 / 로그아웃 -->
	<div id="logoutWrap">
		<img src="./img/bookmark/logo_text.png" /><br><!-- 완도군 로고270픽셀x100픽셀 -->
		<img class="btn" src="./img/bookmark/btn_mypage.png" onclick="location.href='./user/user_edit.jsp';" />
		<img class="btn" src="./img/bookmark/btn_logout.png" onclick="fnb.connect.logout();" />
	</div>
	
	<!-- 다운로드 -->
	<div class="right2">
		<div align="center" class="downWrap">
			<img src="./img/bookmark/down1_img.png" />
			<img src="./img/bookmark/down1_text.png" />
			<button class="downBtn" page="./file/jre-8u161_i586.exe"></button> <!-- 자바사이트 이동 -->
		</div>
	</div>
	<div class="right2">
		<div align="center" class="downWrap">
			<img src="./img/bookmark/down2_img.png" />
			<img src="./img/bookmark/down2_text.png" />
			<button class="downBtn" page="https://get.adobe.com/kr/reader/"></button><!-- 아도비사이트 이동 -->
		</div>
	</div>
	<div class="right2">
		<div align="center" class="downWrap">
			<img src="./img/bookmark/down3_img.png" />
			<img src="./img/bookmark/down3_text.png" />
			 <button class="downBtn" page="./file/DDAS2012.zip" ></button><!-- C/S 운영관리 이동 -->
		    
		</div>
	</div>
	
	<!-- 사용목적 -->
	<div id="right3">
		<div id="header4">
			<img src="./img/bookmark/title4.png" style="vertical-align: bottom;" />
		</div>
		<div id="enter_view">
			<input type="text" id="input_purpose" size="47" value="<%=purpose %>" /> 
			<button id="enter_btn" onclick="fnb.connect.purposeAdd();"></button>
		</div>
	</div>
	
</div>

<!-- 즐겨찾기_폴더추가  FORM -->
<div id="folder_create" style="position:relative; border:0px solid #000000; display:none; background:url(img/forder_create_form.gif); width:329px; height:112px;">
	<div class="bClose" style="cursor:pointer;font-weight:bold;position:absolute; right:8px; text-decoration:none;top:6px;"><img src="img/bclose.gif"/></div>
	<div class="enjsch" style="cursor:pointer;font-weight:bold;position:absolute; left:8px; top:10px; text-decoration:none; ">즐겨찾기</div>
	<div class="" style="border:0px solid #000000; width:278px; margin:38px 0 0 28px;"><b>폴더명 :</b> <input id="forder_name" type="text" style="width:210px; height:100%;" value=""/></div>
	<div class="" style="border:0px solid #000000; width:278px; margin:10px 0 0 125px;"><img src="img/ok_btn.gif" onclick="fnb.connect.createForder();"/></div>
</div>
<!-- 즐겨찾기_폴더추가  FORM 끝-->

<!-- 즐겨찾기_아이템추가  FORM -->
<div id="item_create" style="position:relative; border:0px solid #000000; display:none; background:url(img/additem_img.gif); width:329px; height:137px;">
	<div class="bClose" style="cursor:pointer;font-weight:bold; position:absolute; right:8px; text-decoration:none;top:6px;"><img src="img/bclose.gif"/></div>
	<div class="enjsch" style="cursor:pointer;font-weight:bold;position:absolute; left:8px; top:10px; text-decoration:none; ">즐겨찾기</div>
	<div class="" style="border:0px solid #000000; position:absolute; top:36px; left:25px; width:288px;"><b>사이트 이름 :</b> <input id="sitename" type="text" style="width:210px; height:100%;" value=""/></div>
	<div class="" style="border:0px solid #000000; position:absolute; top:60px; left:25px; width:288px;"><b>사이트 주소 :</b> <input id="urlname" type="text" style="width:210px; height:100%;" value="http://"/></div>
	<div class="" style="border:0px solid #000000; position:absolute; top:90px; left:140px; width:288px;"><img src="img/ok_btn.gif" onclick="fnb.connect.createItems();"/></div>
</div>
<!-- 즐겨찾기_아이템추가  FORM 끝-->

<!-- 공지사항 리스트  -->
<div id="pop_notice">
	<div class="bClose" style="cursor:pointer;font-weight:bold; position:absolute; right:8px; text-decoration:none;top:6px;"><img src="img/bclose.gif"/></div><!-- 닫힘버튼 -->
	<div id="pop_top"></div>
	<div id="pop_middle">
		<div id="inner_top"><img src="img/rhdwltkgkd.gif"/></div>
		<div id="inner_middle"></div>
		<div id="inner_bottom"></div>
	</div>
	<div id="pop_bottom"></div>
</div>
<!-- 공지사항 리스트 끝-->

<!-- 추가 부서코드 변경시 팝업 추가 20120807 -->
<div id="dept_Change_pop" style="position:relative; border:0px solid #000000; display:none;  background:url(img/dept_form.gif);  width:350px; height:180px;">
	<table border="0" cellpadding="0" cellspacing = "0" width="100%" height="100%">
		<tr>
			<td colspan="2" height="38"><img src="img/dept_text_sub.gif" style="margin-left:6px;"/> <img class="bClose" src="img/bclose.gif" align="right" style="margin-right:6px;"/></td>
		</tr>
		<tr>
			<td width="25%" height="25" align="center"><b>현재 부서명 :</b> </td>
			<td><b><%=dept_nm %></b></td>
		</tr>
		<tr>
			<td width="25%" height="25" align="center"><b>부서명 :</b> </td>
			<td><input type="text" id="dept_name_text" name="dept_name_text" style="width:180px; height:17px;"/> <img src="img/dept_srch.gif" style="cursor: pointer;" align="absmiddle" onclick="fnb.connect.deptnmList();"/></td>
		</tr>
		<tr>
			<td height="25"></td>
			<td>
				<select id="dept_cd" name="dept_cd" disabled="disabled" style="width:188px;">
					<option class="abc" value="">:: 선택하여 주십시오! ::</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="25"></td>
			<td height="30" align="left"><img src="img/cha_btn.gif" onclick="fnb.connect.deptUpdateNew();"/> <img src="img/uji_btn.gif" onclick="fnb.connect.deptUpdateKeep();"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center">* 부서명 또는 부서가 변경된 사용자는 <span style="color:red;">해당부서</span>로 변경하여 주십시오!</td>
		</tr>
	</table>	
</div>

<!-- 자바버전 FORM -->
<div id="javaVersion" style="position:relative; border:0px solid #000000; display:none; background:url(img/forder_create_form.gif); width:329px; height:112px;">
	<div align="center" class="" style="border:0px solid #000000; width:278px; margin:10px 0 0 28px;"><b>자바 버전이 최신 버전이 아닙니다. <br>최신 버전이 아닐 경우, 자바 정책상<br>시스템을 사용할 수 없습니다.
	확인을 누르시면 자바 최신버전 다운로드 페이지로 이동합니다.</b></div>
	<div class="" style="border:0px solid #000000; width:278px; margin:10px 0 0 125px;"><img src="img/ok_btn.gif" onclick="location.href='http://www.java.com/ko/'"/></div>
</div>
<!-- 자바버전 FORM 끝-->

</body>
</html>