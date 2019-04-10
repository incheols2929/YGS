<?xml version="1.0" encoding="UTF-8"?>
<%@page import="java.net.URLDecoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.svc.handler.Code"%>
<%@page import="geomex.pkg.srch.ConditionSearch"%>
<%@page import="java.util.List" %>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>

<%
	request.setCharacterEncoding("UTF-8");
	String dept_nm = request.getParameter("dept_nm");
	ArrayList<ConditionSearch> CS = new ArrayList<ConditionSearch>();
	CS = Code.getDeptSrchTxt(dept_nm);

%>

<data>
	  <%
		for(int i=0; i<CS.size(); i++){
	%>
		<dept>
			<code><%= CS.get(i).dept_cd%></code>
			<name><%= CS.get(i).dept_nm%></name>		
		</dept>
	<%
		}
	%>
</data>

