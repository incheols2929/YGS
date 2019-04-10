<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="geomex.pkg.usr.UseLog"%>
<%@page import="geomex.pkg.usr.UseLogBean"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.svc.handler.Code"%>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@ include file="../../../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->
<%
	request.setCharacterEncoding("UTF-8");
	String use_resn  = request.getParameter("use_resn");
	String user_id  = request.getParameter("user_id");
	
	String dept_id  = request.getParameter("dept_id");
	String func_id = request.getParameter("func_id");
	String biz_id = request.getParameter("biz_id");
	String pnu = request.getParameter("pnu_val");
	String key_val = request.getParameter("key_val");
	
	String use_time = "";

	use_time = Utils.getStrSec(); //현재 문자열 반환
	
	String key =  (pnu==null) ? "" : pnu;
	key += (key_val==null)? "" : " , " + key_val;
	
	UseLogBean UB = new UseLogBean();
	boolean bool = UB.setUseRecord(use_resn, user_id, dept_id, func_id, biz_id, use_time,key, session_ip); //기능사용이력정보 기록(로그인, 로그아웃포함)
%>

<data>
	<%
		if(bool){
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

