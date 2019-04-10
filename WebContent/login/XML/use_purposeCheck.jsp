<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.pkg.usr.UserinfoBean"%>

<%
	request.setCharacterEncoding("UTF-8");
	String purpose = request.getParameter("values");
	session.setAttribute("SESSION_PURPOSE", purpose);
	Object id = session.getAttribute("SESSION_ID");
	String usr_id = (String)id;
	UserinfoBean uib = new UserinfoBean();
	uib.setPurpose(purpose, usr_id); //시스템 사용목적을 등록한다
%>
