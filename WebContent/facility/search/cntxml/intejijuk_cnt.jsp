<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="geomex.svc.handler.Code"%>
<%@page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="geomex.utils.Utils"%>

<%
	//통합검색 카운트
	request.setCharacterEncoding("UTF-8");
	
	String sgg = Utils.getParam(request, "sgg");
	String umd = Utils.getParam(request, "umd");
	String ri = Utils.getParam(request, "ri");
	String owngb = Utils.getParam(request, "owngb");
	String jimok = Utils.getParam(request, "jimok");
	String uznecode1 = Utils.getParam(request, "uznecode1");
	String uznecode2 = Utils.getParam(request, "uznecode2");
	String uznecode3 = Utils.getParam(request, "uznecode3");
	String area1 = Utils.getParam(request, "area1");
	String area2 = Utils.getParam(request, "area2");
	String base_year = Utils.getParam(request, "base_year");
	String jiga1 = Utils.getParam(request, "jiga1");
	String jiga2 = Utils.getParam(request, "jiga2");
	
	String dcode = "";
	
	
	if("".equals(umd)){
		dcode = sgg;
	}else{
		dcode = umd;
	}
	
	if("".equals(ri)){
		dcode = umd;
	}else{
		dcode = ri;
	}
	
	ConditionSearchBean CS = new ConditionSearchBean();
	int cnt = CS.getInteCbndTotalCnt(dcode, owngb, jimok, area1, area2, uznecode2, uznecode3, base_year, jiga1, jiga2);
%>
<data>
	<tot><%= cnt%></tot>
</data>