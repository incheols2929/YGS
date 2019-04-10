<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="geomex.svc.handler.Code"%>
<%@page import="java.net.URLDecoder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->
<%
	//단위업무관리ID를 생성
	Object SESSION_BIZID = session.getAttribute("SESSION_BIZID");
	String session_bizid = (String)SESSION_BIZID;
	String dept_nm = Code.getDeptNM(session_dept);//사용자 정보 부서명불러옴
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>:: 지도인쇄 사용목적::</title>
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<script type="text/javascript" src="../script/jquery-1.6.2.js"></script>
<script type="text/javascript" src="../../geomex/gmx.Ctrl.js"></script>
<script src="http://www.google.com/jsapi"></script>
<style type="text/css">
	.border-line1{
		border-right:1px solid #b2b2b2;
		border-bottom:1px solid #b2b2b2;
	}
	.border-line2{
		border-bottom:1px solid #b2b2b2;
	}
	.table_border{
		border:4px solid #0068b7;
	}
</style>

<script type="text/javascript">

function getMapImg(){
	
	var use_resn = $("#use_resn").val();
	var user_id = $("#user_id").val();
	var dept_id = $("#dept_id").val();
	var func_id = $("#func_id").val();
	var biz_id = $("#biz_id").val();
	var title_id = $("#title_id").val();
	var mapName = $("#mapName").val();
	
	if(use_resn == ""){
		alert("사용목적을 입력 하여주십시오!");
		$("#use_resn").focus();
		return false;
	}
	
	$.ajax({
		type:"POST",
		url:"./xml/LogRecord.jsp",
		data : {use_resn:use_resn, user_id:user_id, dept_id:dept_id, func_id:func_id, biz_id:biz_id},
		dataType : "xml",
		success: function(xml){
			if($(xml).find("result").length != 0){
				var retult = $(xml).find("result").text();
				if(retult == "success"){
					//부모창의 지도인쇄 스크립트 호출
					opener.document.reform.titleName.value = title_id;
					opener.document.reform.mapName.value = mapName;
					opener.parent.index._MAINMAP.printAddonScreen();
					window.close();
				}else{
					alert("사용목적을 다시 입력하여 주십시오!");
				}
			}
		},
		beforeSend : function(){},
		complete : function(){},
		error: function(xhr, status, error) {
			alert(error);
		}	
	});
}

</script>
</head>
<body  style="margin:0; padding:0;">
<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" class="table_border">
	<tr>
		<td colspan="3" height="25" align="center" class="border-line2" bgcolor="#c4d7f1"><b>- 지도인쇄 사용목적 입력창 -</b></td>
	</tr>
	<tr align="center">
		<td rowspan="3" class="border-line1" bgcolor="#c4d7f1">사용자정보</td>
		<td height="25" class="border-line1">&nbsp;사용자명</td>
		<td height="25" class="border-line2" align="left">&nbsp;<%=session_name %></td>
	</tr>
	<tr>
		<td height="25" width="20%" class="border-line1" align="center">&nbsp;사용자아이디</td>
		<td height="25" class="border-line2" align="left">&nbsp;<%=id%></td>
	</tr>
	<tr>
		<td height="25" class="border-line1" align="center">&nbsp;부서명</td>
		<td height="25" class="border-line2" align="left">&nbsp;<%=dept_nm %></td>
	</tr>
	<tr>
		<td height="25" class="border-line1" align="center" bgcolor="#c4d7f1">&nbsp;타이틀</td>
		<td height="25" colspan="2" class="border-line2" align="left">&nbsp;<input type="text" id="title_id" name="title_id" value="영광군 농업기반 시설물 시스템" style="width:95%;"/></td>
	</tr>
	<tr>
		<td height="25" class="border-line1" align="center" bgcolor="#c4d7f1">&nbsp;지도이름</td>
		<td height="25" colspan="2" class="border-line2" align="left">&nbsp;<input type="text" id="mapName" name="mapName" value="영광군" style="width:95%;"/></td>
	</tr>
	<tr align="center">
		<td width="20%" height="80" class="border-line1" bgcolor="#c4d7f1">사용목적</td>
		<td width="80%" colspan="2" class="border-line2">
			<textarea id="use_resn" name="use_resn" rows="4" style="width:95%;"></textarea>
		</td>
	</tr> 
	<tr>
		<td align="center" height="25" colspan="3"  class="border-line2" bgcolor="#e3e4e4">* 사용목적을 입력해 주세요!</td>
	</tr>
	<tr>
		<td height="25" colspan="3" align="center" bgcolor="#e3e4e4"><a href="#"onclick="getMapImg();"><img src="../img/btn_1.gif" align="absmiddle"/></a> <a href="#" onclick="window.close();"><img src="../img/btn_2.gif" align="absmiddle"/></a></td>
	</tr>
	<input type="hidden" id="user_id" name="user_id" value="<%=id%>"/><!-- 사용자아이디 -->
	<input type="hidden" id="dept_id" name="dept_id" value="<%=session_dept%>"/><!-- 부서코드 -->
	<input type="hidden" id="func_id" name="func_id" value="FW001"/><!-- 단위기능관리ID -->
	<input type="hidden" id="biz_id" name="biz_id" value="<%=session_bizid%>"/><!-- 단위업무관리ID -->
</table>
</body>
</html>