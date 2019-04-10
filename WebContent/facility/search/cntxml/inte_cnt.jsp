<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="geomex.svc.handler.Code"%>
<%@page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="geomex.utils.Utils"%>

<%
	//통합검색 카운트
	request.setCharacterEncoding("UTF-8");
	String lry_id = request.getParameter("lry_id"); 
	String lry_nm = request.getParameter("lry_nm"); 
	String cond_type = request.getParameter("cond_type"); 
	String included = request.getParameter("included"); 
	String match = request.getParameter("match"); 
	String range_1 = request.getParameter("range_1"); 
	String range_2 = request.getParameter("range_2");
		
	ConditionSearchBean CS = new ConditionSearchBean();

	//String tbl_id = Code.getLyrTBL(lry_id);
	String tbl_id = Code.getLyrNM(lry_id); //형변환을 해서 테이블명을 가지고온다.
	int cnt = CS.getInteTotalCnt(tbl_id, lry_nm, cond_type, included, match, range_1, range_2);

%>
<data>
	<tot><%= cnt%></tot>
</data>