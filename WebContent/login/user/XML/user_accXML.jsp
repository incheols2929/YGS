<?xml version="1.0" encoding="UTF-8"?>
<%@page import="java.net.URLDecoder"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.svc.handler.Code"%>
<%@page import="java.util.List" %>    
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>
<%@page import="geomex.pkg.usr.Userinfo"%>
<%@page import="geomex.pkg.usr.UserinfoBean"%>

<%
		request.setCharacterEncoding("utf-8");
		
		String usr_id = Utils.getParam(request, "usr_id");
		String usr_nm = Utils.getParam(request, "usr_nm");
		String dept_cd = Utils.getParam(request, "dept_cd");
		String tel_num = Utils.getParam(request, "tel_num");
		String email = Utils.getParam(request, "email");
		
		UserinfoBean UB = new UserinfoBean();
		Userinfo UI = new Userinfo();
		boolean bool = UB.getInquiry(usr_id, usr_nm, dept_cd, tel_num, email);	//계정문의확인
%>

<data>
	<%
		if(bool){
			UI = UB.getUserInfo(usr_id); //계정문의 및 사용자 정보수정시 사용한다.
			String decode = new String(Base64.decodeBase64(UI.getUsr_pw()));
			
	%>
		<result id="success">
			<id><%= UI.getUsr_id()%></id>
			<pw><%= new String(Base64.decodeBase64(decode))%></pw>
		</result>
		
	<%
		}else{
	%>
		<result id="fail">
			<id></id>
			<pw></pw>
		</result>
	<%
		}
	%>

	<di></di>	
</data>

