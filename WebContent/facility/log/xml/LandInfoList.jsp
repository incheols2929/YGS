<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.io.StringReader"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.net.URLConnection"%>

<%@ page import="java.awt.Image"%>
<%@ page import="java.awt.image.BufferedImage"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.IOException"%>
<%@ page import="javax.imageio.ImageIO"%>
<%@page import="geomex.svc.handler.Code"%>
<%@page import="geomex.pkg.srch.srchReservoirRecord"%>
<%@ page import="geomex.pkg.srch.ConditionSearch"%>
<%@ page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@ page import="org.apache.commons.lang3.StringUtils"%>
<%@ page import="org.codehaus.jettison.json.*"%>
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
  
  ArrayList<srchReservoirRecord> list =Code.getSrchFacilityAg_parc_dt(ftride,tableName,gbn);  
  String landarea = "";
  String traarea = "";
  String ownrnm = "";
  String jimok = "";
  String juso = "";
  String etc = "";
  
  %>
 <data>
 <%
  for(int i=0; i<list.size(); i++){
	  landarea = list.get(i).getLandarea();
	  traarea= list.get(i).getTraarea();
	  ownrnm = list.get(i).getOwnrnm();
	  jimok = list.get(i).getJimok();
	  juso = list.get(i).getJuso();
	  etc = list.get(i).getEtc();
	  %> 
	  <list>
	    <landarea><%= landarea %></landarea>
	    <traarea><%= traarea %></traarea>
	    <ownrnm><%= ownrnm %></ownrnm>
	    <jimok><%= jimok %></jimok>
	    <juso><%= juso %></juso>
	    <etc><%= etc %></etc>
	  </list>
	  <%
  }
 %>
 </data>
 







