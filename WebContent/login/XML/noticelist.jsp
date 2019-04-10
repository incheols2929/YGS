<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@page import="geomex.pkg.usr.Notice"%>
<%@page import="geomex.pkg.usr.NoticeBean"%>
<%@page import="geomex.utils.Utils"%>  

<%
	String stnum = Utils.getParam(request, "stnum");
	String pagenum = Utils.getParam(request, "pagenum");

	NoticeBean NB = new NoticeBean();
	ArrayList<Notice> NS = new ArrayList<Notice>();//Notice타입을 받는 ArrayList 생성
	
	if(stnum != ""){
		NS = NB.getNoticelist(stnum, pagenum);	
	}
%>

<data>
	<%
		for(int i =0; i<NS.size(); i++){
	%>
	<notice>
		<seq><%= NS.get(i).getReq_seq() %></seq>
		<reguser><%= NS.get(i).getReg_usr_id() %></reguser>
		<subject><%= NS.get(i).getSubj_txt() %></subject>
		<content><![CDATA[<%= NS.get(i).getConts_txt() %>]]></content>
		<category><%= NS.get(i).getNotc_typ() %></category>
		<reg_date><%= NS.get(i).getReg_date() %></reg_date>
		<read_count><%= NS.get(i).getRead_cnt() %></read_count>
	</notice>
	<%
		}
	%>
</data>