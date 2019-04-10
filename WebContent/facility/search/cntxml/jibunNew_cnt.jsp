<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="geomex.utils.Utils"%>

<%
	String sgg 	= Utils.getParam(request, "sgg");
	String umd 	= Utils.getParam(request, "umd");
	String ri 	= Utils.getParam(request, "ri");
	String bon 	= Utils.getParam(request, "bon");
	String bu 	= Utils.getParam(request, "bu");
	String san 	= Utils.getParam(request, "san");
	
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
	int cnt = CS.getJibunNewTotalCnt2(dcode, bon, bu, san);
	
%>
<data>
	<tot><%=cnt%></tot>
</data>