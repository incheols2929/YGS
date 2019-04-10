<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>:: 레이어 관리 ::</title>
<link rel="shortcut icon" type="image/x-icon" href="/favicon.ico">
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css" href="./management.css" />
<script type="text/javascript" src="../script/jquery-1.6.2.js"></script>
<script type="text/javascript" src="../script/jquery.showLoading.js"></script>
<script type="text/javascript" src="./management.js"></script>

<script type="text/javascript">
var user_id = "<%=id%>";
var session_grp_id = "<%=session_ugrp_id%>";
</script>

</head>
<body>

<img id="backImg" src="/login/img/login/bg.jpg" /><!-- 1920픽셀 x 1018픽셀 -->

<div id="popstyle">
	<img id="ci" src="./img/ci.png" /><!-- 245픽셀 x 50픽셀 -->
	<div id="lyr_middle"> 
		<div id="leftDivs"><!-- IE는 왼쪽보더 0으로. -->
			<div id="leftWrap">
				<div class="titleWrap">
					<img src="./img/title1.png" />
				</div>
				<div id="lyr_category"></div> 
			</div>
		</div>
		<div id="arrowWrap">
			<div id="img_addbtn">
				<img src='./img/apply.png' onclick="_LYR.ctrl.addLyr();" title="레이어추가" />
			</div>
		</div>
		<div id="rightDivs">
			<div id="rightWrap">
				<div class="titleWrap">
					<img src="./img/title2.png" />
				</div>
				<div id="lyr_real"></div>
				<div id="btnWrap">
					<p id="manageArea">
						<img src='./img/menu_btn1.png' onmouseover="this.src='./img/menu_btn1_over.png'" onmouseout="this.src='./img/menu_btn1.png'" onclick="_LYR.ctrl.addGrpPop();" />
						<img src='./img/menu_btn2.png' onmouseover="this.src='./img/menu_btn2_over.png'" onmouseout="this.src='./img/menu_btn2.png'" onclick="_LYR.ctrl.lyrBoxCheckRemove();" />
						<img src='./img/menu_btn3.png' onmouseover="this.src='./img/menu_btn3_over.png'" onmouseout="this.src='./img/menu_btn3.png'" onclick="_LYR.ctrl.CheckRemove();" />
					</p>
				</div>
			</div>
		</div>
	</div>
	<div id="lyr_bottom" style="text-align: center;">
		<p id="lyr_p">
			<img id="lyr_btn2" src='./img/btn1.png' onmouseover="this.src='./img/btn1_over.png'" onmouseout="this.src='./img/btn1.png'" onclick="_LYR.ctrl._geoBaseStyleReset();" style="display:none;" title="시스템에 초기설정된 스타일로 변경"/>
			<img id="lyr_btn1" src='./img/btn4.png' onmouseover="this.src='./img/btn4_over.png'" onmouseout="this.src='./img/btn4.png'" onclick="_LYR.ctrl.ReplacedMapStyle();" title="변경된 스타일을 현재 스타일로 적용"/>
			<img id="lyr_btn3" src='./img/btn3.png' onmouseover="this.src='./img/btn3_over.png'" onmouseout="this.src='./img/btn3.png'" onclick="_LYR.ctrl.EditTitleName();" title="신규스타일 추가"/>
		</p>
	</div>
</div>

<!-- 그룹명추가 -->
<div id="grpnmadd_box" style="display:none; border:2px solid #0051bc; text-align:center; position: absolute; top:50%; left:50%;  margin:-30px 0 0 -110px; width:280px; height:30px; background-color: #ffffff;">
	<p style="padding: 5px 0 0 0;">
		그룹명 : <input type="text" id="grpnm" name="grpnm" value="" class="popInput"/>
		<a href="#" class="gray" onclick="_LYR.ctrl.addGrpClose();">확인</a>
		<a href="#" class="gray" onclick="$('#grpnmadd_box').hide();">닫기</a>
	</p>
</div>

<!-- 그룹명수정 -->
<div id="grpnmedit_box" style="display:none; border:2px solid #0051bc; text-align:center; position: absolute; top:50%; left:50%;  margin:-30px 0 0 -110px; width:280px; height:30px; background-color: #ffffff;">
	<p style="padding: 5px 0 0 0;">
		그룹명수정 : <input type="text" id="grpnmedit" name="grpnmedit" value="" class="popInput"/>
		<a href="#" class="gray" onclick="_LYR.ctrl.editGrpnm();">확인</a>
		<a href="#" class="gray" onclick="$('#grpnmedit_box').hide();">닫기</a>
	</p>
</div>

<!-- 스타일 타이틀명 변경-->
<div id="styleTitle_box" style="display:none; border:2px solid #0051bc; text-align:center; position: absolute; top:50%; left:50%;  margin:-30px 0 0 -110px; width:280px; height:30px; background-color: #ffffff;">
	<p style="padding: 5px 0 0 0;">
		스타일명 : <input type="text" id="styleTirle" name="styleTirle" value="" class="popInput"/>
		<a href="#" class="gray" onclick="_LYR.ctrl.CloseTitleName();">확인</a>
		<a href="#" class="gray" onclick="$('#styleTitle_box').hide();">닫기</a>
	</p>
</div>

</body>
</html>