<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="geomex.pkg.srch.ConditionSearch"%>
<%@page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@page import="geomex.pkg.usr.Userinfo"%>
<%@page import="geomex.pkg.usr.UserinfoBean"%>
<%@page import="geomex.pkg.usr.UseLog"%>
<%@page import="geomex.pkg.usr.UseLogBean"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.svc.handler.Code"%>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@ include file="../../../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->
<%
  request.setCharacterEncoding("UTF-8");
  String umd 	= Utils.getParam(request, "umd");
  String res 	= Utils.getParam(request, "res");
  String snum = request.getParameter("snum");
  String pagenum = request.getParameter("pagenum");
	
  ConditionSearchBean CS = new ConditionSearchBean();
  ArrayList<ConditionSearch> MG = new ArrayList<ConditionSearch>();
  MG = CS.getReservoir_Result(umd, res, snum, pagenum);
  
  %>
  <data>
  <%
   for(int i=0; i<MG.size(); i++){
  %>
  <리스트>
   <관리번호><%= MG.get(i).getFtr_idn() %></관리번호>
   <시설명><%= MG.get(i).getFtr_nm() %></시설명>
   <주소><%= MG.get(i).getJuso() %></주소>
   <준공년도><%= MG.get(i).getFns_ymd() %></준공년도>
   <관리자><%=MG.get(i).getMng_nam() %></관리자>
   <관리자연락처><%=MG.get(i).getTel_num() %></관리자연락처>
  </리스트>
  <%
  }
  %>
  </data>