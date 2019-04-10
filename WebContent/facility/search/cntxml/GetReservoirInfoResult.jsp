<?xml version="1.0" encoding="UTF-8"?>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
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
<%@page import="java.util.*" %>
<%@page import = "java.io.*" %>
<%@include file="../../../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->
<%
request.setCharacterEncoding("UTF-8");
String ftr_idn = request.getParameter("ftr_idn");
ConditionSearchBean CS = new ConditionSearchBean();
ArrayList<ConditionSearch> relist = CS.getReservoir(ftr_idn);
%>
  <data>
  <%
   for(int i=0; i<relist.size(); i++){
  %>
  <리스트>
   <관리번호><%= relist.get(i).getFtr_idn() %></관리번호>
   <시설명><%= relist.get(i).getFtr_nm() %></시설명>
   <주소><%= relist.get(i).getJuso() %></주소>
   <날짜><%= relist.get(i).getRes_ymd() %></날짜>
   <총저수량><%=relist.get(i).getRes_sto() %></총저수량>
   <현재저수량><%=relist.get(i).getRes_to() %></현재저수량>
   <유효저수량><%=relist.get(i).getRes_eff() %></유효저수량>
   <수해면적><%=relist.get(i).getBen_area() %></수해면적>
   <유역면적><%=relist.get(i).getBas_area() %></유역면적>
  </리스트>
  <%
  }
  %>
  </data>