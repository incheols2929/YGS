<?xml version="1.0" encoding="UTF-8"?>
<%@page import="java.net.URLDecoder"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>

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
		String usr_nm = Utils.getParam(request, "usr_nm");
		String usr_pw = Utils.getParam(request, "usr_pw");
		String tel_num = Utils.getParam(request, "tel_num");
		String email = Utils.getParam(request, "email");

		UserinfoBean UB = new UserinfoBean();
		boolean bool = UB.getUserUpdate(usr_id, usr_nm, usr_pw, tel_num, email);
%>

<data>
	<%
		if(bool){
	%>
		<result>success</result>
	<%		
		}else{
	%>
		<result>fail</result>
	<%			
		}
	%>
</data>

