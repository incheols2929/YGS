<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@ page import="java.net.URLDecoder"%>
<%@page import="geomex.svc.handler.Code"%>
<%@page import="geomex.pkg.srch.srchReservoirRecord"%>
<%
  request.setCharacterEncoding("UTF-8");
  String ftride  = request.getParameter("ftride");
  String gbn  = request.getParameter("gbn");
  String tableName="";
 if(!gbn.equals("")||!gbn.equals(null)){
  if(gbn.equals("FTR001")){
	  tableName = "ag_reservoir_as"; //저수지
  }else if(gbn.equals("FTR002")){
	  tableName = "ag_drain_ps";//배수장
  }else if(gbn.equals("FTR003")){
	  tableName = "ag_pump_ps";//양수장
  }else if(gbn.equals("FTR004")){
	  tableName = "ag_tbw_ps";//관정
  }else if(gbn.equals("FTR005")){
	  tableName = "ag_culvert_ps";//집수암거
  }else if(gbn.equals("FTR006")){
	  tableName = "";//농로
  }else if(gbn.equals("FTR007")){
	  tableName = "";//집수정
  }else if(gbn.equals("FTR008")){
	  tableName = "ag_seawall_ps";//방조제
  }else if(gbn.equals("FTR009")){
	  tableName = "ag_cwip_as";//취입보
  }else if(gbn.equals("FTR010")){
	  tableName = "ag_basin_as";//저류지
  }
 }
  
  
  ArrayList<srchReservoirRecord> list = Code.getSrchFacilityAg_parc_dt(ftride,tableName,gbn);
%>
<list>
 <%
  for(int i=0;i<list.size();i++){
 %>
  <landarea><%= list.get(i).getLAND_AREA() %></landarea>
  <traarea><%= list.get(i).getTRA_AREA() %></<traarea>>
  <ownrnm><%= list.get(i).getOWNR_NM() %></ownrnm>
  <jimok><%= list.get(i).getJimok() %></jimok>
  <etc><%= list.get(i).getETC() %></etc>
  <juso><%= list.get(i).getJuso() %></juso>
 <%
  }
 %>
<list>