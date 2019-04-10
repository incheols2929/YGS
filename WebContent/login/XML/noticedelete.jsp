<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>
<%@page import="geomex.utils.Utils"%>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@page import="geomex.pkg.usr.Notice"%>
<%@page import="geomex.pkg.usr.NoticeBean"%>

<%
	
	String seq = Utils.getParam(request, "seq");
	NoticeBean NB = new NoticeBean();
	boolean bool = NB.setNoticeDelete(seq);
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