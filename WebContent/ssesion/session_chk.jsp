<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@ include file="ssesion.jsp" %> <!-- 디렉티브 인크루드 -->
<data>
	<%
		if(id != null){
	%>
		<chk>T</chk>
	<%
		}else{
	%>
		<chk>F</chk>
	<%
		}
	%>
</data>