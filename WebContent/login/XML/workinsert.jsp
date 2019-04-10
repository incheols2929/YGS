<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>
<%@page import="geomex.utils.Utils"%>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@page import="geomex.pkg.usr.Work"%>
<%@page import="geomex.pkg.usr.WorkBean"%>

<%

	String system_sitename = Utils.getParam(request, "sitename");
	String system_urlname = Utils.getParam(request, "urlname");
	String system_imgname = Utils.getParam(request, "imgname");
	String system_content = Utils.getParam(request, "content");
	
	String system_date = ""; 
	
	//현재날짜 계산
	Calendar cal = Calendar.getInstance();
	Date currentTime=cal.getTime();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
	String sTimestr = formatter.format(currentTime);
	
	WorkBean WB = new WorkBean();
	boolean bool = WB.getWorkInsert(system_sitename, system_urlname, system_imgname, system_content, sTimestr);
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