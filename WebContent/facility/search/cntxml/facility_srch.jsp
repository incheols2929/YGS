<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.pkg.srch.ConditionSearch"%>
<%@page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@page import="java.util.List" %>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%
	String facility = Utils.getParam(request, "facility");
	
	ConditionSearchBean CS = new ConditionSearchBean();
	ArrayList<ConditionSearch> MS = new ArrayList<ConditionSearch>();
	//int cnt = CS.getJibunTotalCnt(sgg, umd, ri, bon, bu, san);
	//int cnt = CS.getJibunTotalCnt3(dcode,facility,rsv_nm);	 //pnu값을 불러오는것같음
	MS = CS.getTableCode(facility);	 //pnu값을 불러오는것같음
%>
<data>
   	<%
		for(int i =0; i<MS.size(); i++){
	%>		
	<srch>
		<facility><%= MS.get(i).getFacility() %></facility>
	</srch>
	<%

		}
	%>
</data>