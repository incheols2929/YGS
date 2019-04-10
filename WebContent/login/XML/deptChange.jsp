<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@page import="geomex.pkg.usr.Userinfo"%>
<%@page import="geomex.pkg.usr.UserinfoBean"%>
<%@ include file="../../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->

<%
	String cha = request.getParameter("cha");
	String user_id = id;
	UserinfoBean UB = new UserinfoBean();
	
	if("new".equals(cha)){ //변경
		String dept_cd = request.getParameter("dept_cd");	
		boolean bool = UB.setDeptcdChange(dept_cd, user_id);
%>
<data>
	<%
		if(bool){
			session.setAttribute("SESSION_DEPT_YN", "Y");
			session.setAttribute("SESSION_DEPT", dept_cd);
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

<%
	}else{ //유지
		boolean bool = UB.setDeptcdKeep(user_id);
%>
<data>
	<%
		if(bool){
			session.setAttribute("SESSION_DEPT_YN", "N");
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

<%
	}
%>

