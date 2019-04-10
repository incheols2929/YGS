<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="geomex.svc.handler.Code"%>
<%@page import="java.net.URLDecoder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->
<%
request.setCharacterEncoding("UTF-8");
	//단위업무관리ID를 생성
	Object SESSION_BIZID = session.getAttribute("SESSION_BIZID");
	String session_bizid = (String)SESSION_BIZID;
	String dept_nm = Code.getDeptNM(session_dept);
	
	String func = request.getParameter("func");
	
	String pnu = request.getParameter("pnu");
	String key = request.getParameter("key");
	String sub_key = request.getParameter("sub_key");
	String func_id = request.getParameter("func_id");
	String bno2 = request.getParameter("bno2");
	String bno = request.getParameter("bno");
%>
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>:: 사용목적::</title>
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<script type="text/javascript" src="../script/jquery-1.6.2.js"></script>
<script type="text/javascript" src="../../geomex/gmx.Ctrl.js"></script>
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
	var funcType = "<%=func%>";
	var pnu = "<%=pnu%>";
	var key = "<%=key%>";
	var sub_key = "<%=sub_key%>";
	var use_resn = $("#use_resn").val();
	var user_id = $("#user_id").val();
	var dept_id = $("#dept_id").val();
	var func_id = "<%=func_id%>";
	var biz_id = $("#biz_id").val();
	var bno2 = "<%=bno2%>";
	var bno = "<%=bno%>";
	
	if(use_resn == ""){
		alert("사용목적을 입력 하여주십시오!");i
		$("#use_resn").focus();
		return false;
	}
	
	$.ajax({
		type:"POST",
		url:"./xml/LogRecord.jsp", //로그기록 
		data : {use_resn:use_resn, user_id:user_id, dept_id:dept_id, func_id:func_id, biz_id:biz_id},
		dataType : "xml",
		success: function(xml){
			if($(xml).find("result").length != 0){
				var retult = $(xml).find("result").text();
				if(retult == "success"){
					if(funcType === "총괄표제부"){
						window.open("../form/pdfprint/bldg/bldgdaejang.jsp?pnu="+pnu+"&user_name="+encodeURIComponent(user_id), '_blank');
						window.close();
					}else if(funcType === "일반건축물" || funcType === "표제부"){
						if(funcType === "일반건축물"){
							popup = window.open("../form/pdfprint/bldg/bldg_gen_daejang.jsp?pnu="+pnu+"&bno="+sub_key+"&user_name="+encodeURIComponent(user_id), '_blank');
						}else{
							popup = window.open("../form/pdfprint/bldg/bldg_djytitle_daejang.jsp?pnu="+pnu+"&bno="+sub_key+"&user_name="+encodeURIComponent(user_id), '_blank');
						}
						window.close();
					}else if(funcType === "전유부"){
						window.open("../form/pdfprint/bldg/bldg_junyou_daejang.jsp?pnu="+pnu+"&user_name="+encodeURIComponent(user_id)+"&bno="+bno+"&bno2="+bno2+"", '_blank');
						window.close();
					}else if(funcType === "토지대장"){
						window.open("../form/pdfprint/toji/tojidaejang.jsp?pnu="+pnu+"&user_name="+encodeURIComponent(user_id), '_blank');
						window.close();
					}
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
		<td colspan="3" height="25" align="center" class="border-line2" bgcolor="#c4d7f1"><b>- 사용목적 -</b></td>
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
	<tr align="center">
		<td width="20%" height="80" class="border-line1" bgcolor="#c4d7f1">사용목적</td>
		<td width="80%" colspan="2" class="border-line2">
			<textarea id="use_resn" name="use_resn" rows="4" style="font-size:12px; width:95%;"></textarea>
		</td>
	</tr>
	<tr>
		<td align="center" height="25" colspan="3"  class="border-line2" bgcolor="#e3e4e4">* 사용목적을 입력해 주세요!</td>
	</tr>
	<tr>
		<td height="25" colspan="3" align="center" bgcolor="#e3e4e4"><a href="#" onclick="getMapImg();"><img src="../img/btn_1.gif" align="absmiddle"/></a> <a href="#" onclick="window.close();"><img src="../img/btn_2.gif" align="absmiddle"/></a></td>
	</tr>
	<input type="hidden" id="user_id" name="user_id" value="<%=id%>"/><!-- 사용자아이디 -->
	<input type="hidden" id="dept_id" name="dept_id" value="<%=session_dept%>"/><!-- 부서코드 -->
	<input type="hidden" id="func_id" name="func_id" value="<%=func_id%>"/><!-- 단위기능관리ID -->
	<input type="hidden" id="biz_id" name="biz_id" value="<%=session_bizid%>"/><!-- 단위업무관리ID -->
</table>
</body>
</html>