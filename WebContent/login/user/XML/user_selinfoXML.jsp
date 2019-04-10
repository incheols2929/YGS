<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.svc.handler.Code"%>
<%@page import="java.util.List" %>    
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@page import="geomex.pkg.usr.Userinfo"%>
<%@page import="geomex.pkg.usr.UserinfoBean"%>
<%
		request.setCharacterEncoding("utf-8");
		String usr_id = Utils.getParam(request, "usr_id");
		UserinfoBean UB = new UserinfoBean();
		Userinfo UI = new Userinfo();
		UI = UB.getUserInfo(usr_id);
		String decode = new String(Base64.decodeBase64(UI.getUsr_pw()));
%>

<data>
	<result>
		<usr_id><%= UI.getUsr_id()%></usr_id> 
		<usr_nm><%= UI.getUsr_nm()%></usr_nm> 
		<dept_cd><%= UI.getDept_cd()%></dept_cd> 
		<tel_num><%= UI.getTel_num()%></tel_num> 
		<email><%= UI.getEmail()%></email> 
		<usr_pw><%= new String(Base64.decodeBase64(decode))%></usr_pw>
	</result>
</data>

