<?xml version="1.0" encoding="UTF-8"?>
<%@page import="java.net.URLDecoder"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="geomex.pkg.srch.ConditionSearch"%>
<%@page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.svc.handler.Code"%>    
<%@page import="java.util.List" %>  
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@ include file="/ssesion/ssesion.jsp" %>
<%
request.setCharacterEncoding("UTF-8");
int rid = Code.getSrch_rid(); //이력정보 기본키

if(rid == 0){
	rid = 1; // 없으면 1
}else{
    rid = Code.getidnCount(); //max값에서 1더함
}


String ftr_idn = request.getParameter("ftr_idn");
String ftr_nm = request.getParameter("ftr_nm");
String juso = request.getParameter("juso");
String resymd = request.getParameter("res_ymd");
String year = resymd.substring(0,4);
String month = resymd.substring(5,7);
String day = resymd.substring(8,10);
String res_ymd  = year+month+day;//날짜
 String res_sto = request.getParameter("res_sto");
String res_to = request.getParameter("res_to");
String res_eff = request.getParameter("res_eff");
String ben_area = request.getParameter("ben_area");
String bas_area = request.getParameter("bas_area");
String etc = request.getParameter("etc");
String ftr_gbn="Y";
ConditionSearchBean CE = new ConditionSearchBean();
ArrayList<ConditionSearch> MG = new ArrayList<ConditionSearch>();
boolean divbool = CE.getReservoir_Mtce_insert(rid, ftr_idn, ftr_nm, juso, res_ymd, res_sto, res_to, res_eff, ben_area, bas_area,ftr_gbn, etc);
%>
<data>
 <%
  if(divbool){
 %>
 <result>success</result>
 <%
  }else{
 %>
 <result>fail</result>
 <%
  }
 %>
</data>
