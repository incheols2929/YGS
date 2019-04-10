<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="geomex.pkg.usr.Notice"%>
<%@page import="geomex.pkg.usr.NoticeBean"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="java.util.List" %>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>

<%
	NoticeBean NB = new NoticeBean();
	String tot = NB.getNoticecount();	//공지사항 정보 관리
%>

<data>
	<tot><%=tot%></tot><!-- 공지사항 데이터 담아감 -->
</data>
