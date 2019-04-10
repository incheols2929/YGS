<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>
<%@page import="geomex.utils.Utils"%>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@page import="geomex.pkg.usr.Work"%>
<%@page import="geomex.pkg.usr.WorkBean"%>

<%
	Object SESSION_ID = session.getAttribute("SESSION_ID");
	String id = (String)SESSION_ID;	

	String sortnum = Utils.getParam(request, "sortnum");
	WorkBean WB = new WorkBean();
	boolean bool = WB.getWorkUpdate(id, sortnum);
%>

<data>
	<%
		if(bool){
	%>
		<val>success</val>
	<%
		}else{
	%>
		<val>fail</val>
	<%
		}
	%>
</data>