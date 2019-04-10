<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->
<%@page import="geomex.pkg.usr.UseLog"%>
<%@page import="geomex.pkg.usr.UseLogBean"%>
<%
	//로그아웃시 접속종료시간을 저장한다.
	UseLogBean ULB = new UseLogBean();
	//ULB.setConnLogDistime(id, session_time_2, now_time);
	
	if(id != null){
		ULB.setUseRecord("사용자로그아웃", id, session_dept, "L0002", "L", now_time, "", session_ip);
	}

	session.invalidate();
	response.sendRedirect("./login.jsp");
%>
 