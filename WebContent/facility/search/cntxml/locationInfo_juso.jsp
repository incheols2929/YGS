<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.net.URLDecoder"%>
<%@page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@page import="geomex.pkg.srch.ConditionSearch"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="geomex.utils.Utils"%>
<%
	request.setCharacterEncoding("UTF-8");
    String sggCode = "46870";
    String sgg = "영광군";
    String emdcode = "";
    String emdvalue = "";
    String juso = "";
    ConditionSearchBean CS = new ConditionSearchBean();
    ArrayList<ConditionSearch> cslist = CS. getUmdAll(sggCode);  //선택한 시군구 전체 목록을 들고옴
    
/*     for(int i=0; i<cslist.size();i++){
    	emdcode = cslist.get(i).getCode();
    	System.out.println("읍면동 코드" +emdcode);
    	emdvalue = cslist.get(i).getValue();
    	System.out.println("읍면동 명" +emdvalue);
    	
    	
    }
     */
%>
<data>
	<%
		for(int i =0; i<cslist.size(); i++){
			emdvalue = cslist.get(i).getValue();
			juso = sgg+" " + emdvalue;
			
			System.out.println("읍면동 명" + juso);
	%>
	  <gbn>
	    <code><%=cslist.get(i).getCode() %></code>
	     <value><%= juso %></value>
	  </gbn>
	  <%
	  } 
	  %>
</data>