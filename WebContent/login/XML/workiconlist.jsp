<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@page import="geomex.pkg.usr.Work"%>
<%@page import="geomex.pkg.usr.WorkBean"%>
<%@page import="geomex.utils.Utils"%>  

<%

	/////// 아이콘 해당파일명을 가져온다. /////////
	String path = "/";
	ServletContext context = getServletContext();
	String iconFolder = context.getRealPath(path);
	File dir = new File(iconFolder+"login\\img\\icon\\");
	String[] filename = dir.list();
	
%>
<data>
	<%
		for(int i=0; i< filename.length; i++){
	%>
	<icon>
		<iconname><%= "이미지_" + i%></iconname>
		<iconrealname><%=filename[i]%></iconrealname>
	</icon>
	<%
		}
	%>
</data>
