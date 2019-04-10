<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="geomex.utils.Utils"%>

<%
	String sgg 	= Utils.getParam(request, "sgg"); //Utils.java에있는 getParam메소드를 오버라이드 함 'sgg'가 Utils.java에 name파라미터임
	String umd 	= Utils.getParam(request, "umd");
	String ri 	= Utils.getParam(request, "ri");
	
	String bon 	= Utils.getParam(request, "bon");
	String bu 	= Utils.getParam(request, "bu");
	String san 	= Utils.getParam(request, "san");
	
	/* 시군이 정해지면 관련된 음면동만 검색되고 관련된 리만 검색될수 있게 해줌
	 * 
	 */
	String dcode = "";
	if("".equals(umd)){  //공백과 umd같은가
		dcode = sgg;
	}else{
		dcode = umd;
	}
	
	if("".equals(ri)){ 
		dcode = umd;
	}else{
		dcode = ri;
	}
	//부서관리코드로 전체 기관명 찾아서 가져옴
	ConditionSearchBean CS = new ConditionSearchBean();
	//int cnt = CS.getJibunTotalCnt(sgg, umd, ri, bon, bu, san);
	int cnt = CS.getJibunTotalCnt2(dcode, bon, bu, san);	 //pnu값을 불러오는것같음
%>
<data>
	<tot><%=cnt%></tot>
</data>