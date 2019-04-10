<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/xml" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@ page import="geomex.svc.handler.Code"%>
<%@ include file="../../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->
<%
	ArrayList<String> list = Code.getFuncList(); //기능카운트를 가져온다.
	String auth = request.getParameter("auth");
%>
<!-- 업무권한 기능체크 -->
<data>
	<auth><%= Code.getAuthCheck(auth, id, session_ugrp_id) %></auth> 
</data>
<%-- <data>
	<auth>
		<%
			for(int i=0; i<list.size(); i++){
				if(id != null){
					%>
						<<%= list.get(i).toString() %>><%= Code.getAuthCheck(list.get(i).toString(), id, session_ugrp_id) %></<%= list.get(i).toString() %>>
					<%
				}
			}
		%>
	</auth>
</data> --%>