<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@page import="geomex.pkg.usr.Work"%>
<%@page import="geomex.pkg.usr.WorkBean"%>
<%@ include file="../../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->

<%
	//Object SESSION_ID = session.getAttribute("SESSION_ID");
	//String id = (String)SESSION_ID;	

	WorkBean WB = new WorkBean();
	ArrayList<Work> CS = new ArrayList<Work>();
	ArrayList<Work> list = new ArrayList<Work>();
	CS = WB.getWork(id, session_ugrp_id);
%>

<data>
	<%
		String r_auth_yn = ""; 
		for(int i =0; i<CS.size(); i++){
			
			if("".equals(CS.get(i).getR_auth_yn()) || "N".equals(CS.get(i).getR_auth_yn())){
				r_auth_yn = "N";
			}else{
				r_auth_yn = "Y";
			}
	%>		
	<work> 
		<seq><%= CS.get(i).getBiz_seq() %></seq>
		<bizid><%= CS.get(i).getBiz_id() %></bizid>
		<subject><%= CS.get(i).getBiz_nm() %></subject>
		<url><%= CS.get(i).getConn_url() %></url>
		<img_name><%= CS.get(i).getImg_nm() %></img_name>
		<content><%= CS.get(i).getBiz_note() %></content> 
		<access_date><%= CS.get(i).getReg_date() %></access_date>
		<r_auth_yn><%= r_auth_yn%></r_auth_yn>
	</work>
	<%
		}
	%>
</data>