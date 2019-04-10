<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.codec.binary.Base64"%>
<%@ page import="geomex.pkg.sys.lris.AhpdHprcBean"%>
<%@ page import="geomex.pkg.sys.lris.AhpdHprc"%>
<%@ page import="geomex.svc.handler.Code"%>
<%@ page import="geomex.utils.Utils"%>
<%@ page import="geomex.utils.Base64Util"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ include file="../../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->
<%
	String pnu = new String(Base64.decodeBase64(request.getParameter("pnu")));
	String addr = request.getParameter(URLDecoder.decode("addr"));
	String dong_seqno = request.getParameter("dong_seqno");
	AhpdHprcBean ah = new AhpdHprcBean();
	ArrayList<AhpdHprc> list = ah.getHprcList(pnu, dong_seqno);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>:: 개별주택가격 ::</title>
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<script type="text/javascript" src="../script/jquery-1.6.2.js"></script>
<script type="text/javascript" src="../script/gws.condSrch.js"></script>
<script type="text/javascript" src="../script/gws.pageCtrl.js"></script>
<script type="text/javascript">
$(document).ready(function (){
	if($.browser.msie){
		jQuery.fn.center = function () {
			this.css("margin-top", (jQuery(window).height() - this.outerHeight()) / 2 + jQuery(window).scrollTop() + "px");
			return this;
		},jQuery('#wrap').center();
	}else{
		jQuery.fn.center = function () {
			this.css("margin-top", "20px");
			return this;
		},jQuery('#wrap').center();
	}
});
</script>
</head>
<body>

<div id="wrap" style="margin:0 auto; width:900px; height:500px;">
	<table border='2' style="border-collapse: collapse; border-color: black; width: 900px; height: 500px;">
		<tr align="center" height="30px;" style="font-size: 15px; font-weight: bold; color: black; background-color: #c4d7f1;">
			<td colspan="7">신 청 대 상 주 택</td>
			<td>확 인 내 용</td>
		</tr>
		<tr align="center" height="25px;" style="font-size: 15px; font-weight: bold; color: black; background-color: #c4d7f1;">
			<td rowspan="2">가격기준년도<br>(기준일)</td>
			<td rowspan="2">건물번호</td>
			<td rowspan="2">주택소재지</td>
			<td colspan="2">대지면적(㎡)</td>
			<td colspan="2">건물연면적(㎡)</td>
			<td rowspan="2">개별주택가격(원)</td>
		</tr>
		<tr align="center" height="25px;" style="font-size: 15px; font-weight: bold; color: black; background-color: #c4d7f1;">
			<td>전체</td>
			<td>산정</td>
			<td>전체</td>
			<td>산정</td>
		</tr>
		
		<%
			if(list.size() == 0){
		%>
			<tr align="center">
				<td colspan="8">검색된 결과가 없습니다.</td>
			</tr>
		<%
			}else{
				for(int i=0; i<list.size(); i++){
					
		%>
				<tr align="center">
					<td><%= list.get(i).hprc_year %>년 <%= list.get(i).base_mon %>월 01일</td>
					<td><%= list.get(i).dong_seqno %></td>
					<td><%= addr %></td>
					<td><%= list.get(i).land_area %></td>
					<td><%= list.get(i).calc_area %></td>
					<td><%= list.get(i).bldg_garea %></td>
					<td><%= list.get(i).res_area %></td>
					<td><%= Utils.getCommaCreate(list.get(i).hprc) %></td>
				</tr>
		<%
				}
			}
		%>
		
	</table>
</div><br>

</body>
</html>