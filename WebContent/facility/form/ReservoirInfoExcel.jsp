<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="geomex.pkg.srch.ConditionSearch"%>
<%@page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="../../../ssesion/ssesion.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
//파일명 현재날짜로 저장
DecimalFormat df = new DecimalFormat("00");
Calendar currentCalendar = Calendar.getInstance();
String strYear = Integer.toString(currentCalendar.get(Calendar.YEAR));
String strMonth = df.format(currentCalendar.get(Calendar.MONTH) + 1);
String strDay = df.format(currentCalendar.get(Calendar.DATE));
String file = strYear + strMonth + strDay + new String((".xls").getBytes("euc-kr"),"iso-8859-1");;
request.setCharacterEncoding("UTF-8");
String ftr_idn = session_ftr_idn; //관리번호 가져옴
ConditionSearchBean CS = new ConditionSearchBean();
ArrayList<ConditionSearch> relist = CS.reservoirExcelExport(ftr_idn);
response.setHeader("Content-Disposition","attachment; filename=\"" + file + "\"");
response.setHeader("Content-Description", "Statistic Data");
response.setContentType("application/vnd.ms-excel");
System.out.println("실행 순서확인"+ftr_idn);
%>
<script type="text/javascript" src="../common/comm.js"></script>
<script type="text/javascript" src="../script/jquery.selectboxes.js"></script>
<script type="text/javascript" src="../script/gws.condSrch.js"></script>
<script type="text/javascript" src="../script/gws.pageCtrl.js"></script>
<style>
	#re { 
	font: 13px/normal verdana; 
	text-align: center; 
	font-size-adjust: none; 
	font-stretch: normal;
}
	#thc{
	font: 13px/normal verdana; 
	color:#07718f;
	text-align: center;
	font-size: 14;
	background:#F7FFFE;
	}
</style>
	<!-- table border=1 이여야 셀 테두리가 생김 -->
	<table class="fixed" cellpadding="0" cellspacing="0" border="1" class="fixed" style="width:100%;" align="center">
		<colgroup>
       		<col width="10%" /><col width="5%" /><col width="15%" /><col width="10%" /><col width="10%" />
       		<col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" /><col width="10%" />
      </colgroup>
	    <tr>
			<th id="thc">관리번호</th><th id="thc">시설명</th><th id="thc">주소</th><th id="thc">날짜</th><th id="thc">총저수량</th>
			<th id="thc">현재저수량</th><th id="thc">유효저수량</th><th id="thc">수해면적</th><th id="thc">유역면적</th><th id="thc">기타</th>
		</tr>
		<tr>
  <%
   for(int i=0; i<relist.size(); i++){
  %>
			<td id="re">&nbsp;<%= relist.get(i).getFtr_idn()  == null ?"": relist.get(i).getFtr_idn()%></td>
			<td id="re">&nbsp;<%= relist.get(i).getFtr_nm() == null ?"": relist.get(i).getFtr_nm()%></td>
			<td id="re">&nbsp;<%= relist.get(i).getJuso() == null ?"": relist.get(i).getJuso()%></td>
			<td id="re">&nbsp;<%= relist.get(i).getRes_ymd() == null ?"": relist.get(i).getRes_ymd()%></td>
			<td id="re">&nbsp;<%=relist.get(i).getRes_sto() == null ?"": relist.get(i).getRes_sto()%></td>
			<td id="re">&nbsp;<%=relist.get(i).getRes_to() == null ?"": relist.get(i).getRes_to()%></td>
			<td id="re">&nbsp;<%=relist.get(i).getRes_eff() == null ?"": relist.get(i).getRes_eff() %></td>
			<td id="re">&nbsp;<%=relist.get(i).getBen_area() == null ?"": relist.get(i).getBen_area()%></td>
			<td id="re">&nbsp;<%=relist.get(i).getBas_area() == null ?"": relist.get(i).getBas_area() %></td>
			<td id="re">&nbsp;<%=relist.get(i).getEtc() == null ?"": relist.get(i).getEtc() %></td>
		</tr>
   <%
  }
  %>
	</table>
