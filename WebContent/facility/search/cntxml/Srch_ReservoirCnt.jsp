<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="geomex.pkg.srch.ConditionSearch"%>
<%@page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.svc.handler.Code"%>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%
    request.setCharacterEncoding("UTF-8");
	String umd 	=  request.getParameter("umd");
    String res = request.getParameter("res");
	ConditionSearchBean CS = new ConditionSearchBean();
	int cnt = CS.getReservoirTotalCnt(umd, res);
%>
<data>
 	<tot><%=cnt %></tot>
</data>
