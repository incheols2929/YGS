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
	
	String subject = Utils.getParam(request, "subject");
	String category = Utils.getParam(request, "category");
	String content = Utils.getParam(request, "content");
	String date = "";
	String reguser = "show"; // 관리자 아이디
	
	//현재날짜 계산
	Calendar cal = Calendar.getInstance();
	Date currentTime=cal.getTime();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
	date = formatter.format(currentTime);
	
	NoticeBean NB = new NoticeBean();
	boolean bool = NB.setNoriceInsert(reguser, subject, category, content, date);
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