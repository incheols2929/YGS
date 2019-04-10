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
		String dept_cd = Utils.getParam(request, "dept_cd");
		String tel_num = Utils.getParam(request, "tel_num");
		String email = Utils.getParam(request, "email");
		String reg_date = Utils.getStrHour();

		UserinfoBean UB = new UserinfoBean();
		boolean bool = UB.getUserDeptUpdate(usr_id, usr_nm, usr_pw, tel_num, email, dept_cd, reg_date);
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

