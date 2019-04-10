<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.net.URLDecoder"%>
<%@page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="geomex.utils.Utils"%>

<%

	request.setCharacterEncoding("UTF-8");
	String sgg = Utils.getParam(request, "sgg");
	String road  = URLDecoder.decode(Utils.getParam(request, "road") , "utf-8"); //한글처리
	String buld_bon = Utils.getParam(request, "buld_bon");
	String buld_bu = Utils.getParam(request, "buld_bu");
	String buld_se_cd = Utils.getParam(request, "buld_se_cd");
	ConditionSearchBean CS = new ConditionSearchBean();
	//int cnt = CS.getDoroTotalCnt(sgg, road, buld_bon, buld_bu);
	int cnt = CS.getDoroTotalCnt2(sgg, road, buld_bon, buld_bu, buld_se_cd);
%>
<data>
	<tot><%= cnt%></tot>
</data>