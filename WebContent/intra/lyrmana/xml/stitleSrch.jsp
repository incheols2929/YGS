<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.svc.handler.Code"%>  

<%
	request.setCharacterEncoding("UTF-8");
	String user =  request.getParameter("user");
	String title = request.getParameter("title");
	String TF = Code.getStyleTitle(user, title);
%>

<data>
	<%
		if("T".equals(TF)){
	%>
		<title>T</title>
	<%
		}else{
	%>
		<title>F</title>
	<%
		}
	%>
</data>