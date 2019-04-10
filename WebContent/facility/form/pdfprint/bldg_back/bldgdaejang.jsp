<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.svc.handler.Code"%>
<%@page import="geomex.pkg.sys.eais.Djyrecaptitle"%>
<%@page import="geomex.pkg.sys.eais.DjyBldgList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>    
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<%
	request.getParameter("UTF-8");
	String key = request.getParameter("key");
	String pnu = request.getParameter("pnu");
	String user_name = request.getParameter("user_name");
	
	Djyrecaptitle dbl = new Djyrecaptitle();
	ArrayList<Djyrecaptitle> list = new ArrayList<Djyrecaptitle>();
	DjyBldgList dbl2 = new DjyBldgList();
	ArrayList<DjyBldgList> onlist = new ArrayList<DjyBldgList>();
	
	try {
		list = dbl.getDjyrecaptitle(key);
		onlist = dbl2.getBldgList(pnu);
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	int mgm_cnt = list.size();
	int m_page_list_cnt = 7;
	int u_page_list_cnt = 15; 
	int u_total_page = 0;
	int m_cnt = 0;
	
	if(mgm_cnt > (m_page_list_cnt)){
		u_total_page = ((mgm_cnt-1-m_page_list_cnt)/(u_page_list_cnt));
		if(mgm_cnt%(u_page_list_cnt) > 0) u_total_page += 1;
	}
	
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
<title>:: 건축물대장 총괄표제부 ::</title>

<style type="text/css">
	@page {
    	size: landscape;
	}
	
	@page { @bottom-right { content: Page counter(page);} }  
	
	body{
		font-size: 12px;
		font-family: NanumGothic;
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
	}

	.line_border{
		border-top:2px solid #000000; 
		border-bottom:2px solid #000000;
	}
	.line_border_1{
		border-bottom:1px solid #000000;
	}
	.line_border_2{
		border-right:1px solid #000000;
		border-bottom:1px solid #000000;
	}
	.line_border_3{
		border-right:1px solid #000000;
	}
	
	.line_border_4{
		border-top:2px solid #000000;
		border-right:1px solid #000000;
		border-bottom:1px solid #000000;
	}
	
	.line_border_5{
		border-top:2px solid #000000;
		border-bottom:1px solid #000000;
	}
	.line_border_6{
		border-bottom:2px solid #000000;
	}
	
	.line_border_7{
		border-top:2px solid #000000; 
	}
	
	.line_border_8{
		border-top:1px solid #000000; 
		border-bottom:1px solid #000000;
	}
	#wapp_table{
		background: url(dajang_bg.gif) no-repeat; 
		background-position: center;
	}
</style>

</head>
<body>
 <table border="0" cellpadding="0" cellspacing="0" width="1025" id="wapp_table"> 
	<tr>
		<td align="center" height="35">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td width="90%" align="center" height="35"><b style="font-size: 25px;">건축물대장 총괄표제부</b></td>
					<td width="10%">장번호 : 1-1</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center" height="40">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border">
				<tr>
					<td width="15%" height="40" align="left" valign="top">고유번호</td>
					<td width="35%" style="border-right:1px solid #000000;"><%=onlist.get(0).LAND_CD %></td>
					<td width="15%" align="left" valign="top">접수번호</td>
					<td width="35%"><%=Utils.getStrSec() %></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="10"></td>
	</tr>
	<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border">
				<tr>
					<td height="30" align="left" valign="top" class="line_border_1">&nbsp;대지위치</td>
					<td colspan="3" align="center" class="line_border_2"><%= list.get(0).ADDR %></td>
					<td align="left" valign="top"  class="line_border_1">&nbsp;지번</td>
					<td align="right" valign="bottom" class="line_border_2"><%= Utils.formatPNU(pnu) %></td>
					<td align="left" valign="top" class="line_border_1">&nbsp;명칭 및 번호</td>
					<td align="right" valign="bottom" class="line_border_2"><%= onlist.get(0).BLD_NM %></td>
					<td align="left" valign="top" class="line_border_1">&nbsp;특이사항</td>
					<td align="right" valign="bottom" class="line_border_1"><%= list.get(0).REMARK %></td>
				</tr>
				<tr>
					<td height="30" align="left" valign="top" class="line_border_1">&nbsp;대지면적</td>
					<td align="right" valign="bottom" class="line_border_2"><%= list.get(0).PLATAREA%></td>
					<td align="left" valign="top" class="line_border_1">&nbsp;연면적</td>
					<td align="right" valign="bottom" class="line_border_2"><%= list.get(0).TOTALAREA%></td>
					<td align="left" valign="top" class="line_border_1" style="text-decoration: line-through;">&nbsp;지역</td>
					<td align="right" valign="bottom" class="line_border_2">-</td>
					<td align="left" valign="top" class="line_border_1" style="text-decoration: line-through;">&nbsp;지구</td>
					<td align="right" valign="bottom" class="line_border_2">-</td>
					<td align="left" valign="top" class="line_border_1" style="text-decoration: line-through;">&nbsp;구역</td>
					<td align="right" valign="bottom" class="line_border_1">-</td>
				</tr>
				<tr>
					<td height="30" align="left" valign="top" class="line_border_1">&nbsp;건축면적</td>
					<td align="right" valign="bottom" class="line_border_2"><%= list.get(0).ARCHAREA%></td>
					<td align="left" valign="top" class="line_border_1">&nbsp;용적률산정용연면적</td>
					<td align="right" valign="bottom" class="line_border_2"><%= list.get(0).VL_RAT_AREA%></td>
					<td align="left" valign="top" class="line_border_1">&nbsp;건축물수</td>
					<td align="right" valign="bottom" class="line_border_2"><%= list.get(0).BL_CNT %></td>
					<td align="left" valign="top" class="line_border_1">&nbsp;주용도</td>
					<td align="right" valign="bottom" colspan="3" class="line_border_1"><%= list.get(0).BL_USABILITY %></td>
				</tr>
				<tr>
					<td height="30" align="left" valign="top">&nbsp;건폐율</td>
					<td align="right" valign="bottom" class="line_border_3"><%= list.get(0).BC_RAT%></td>
					<td align="left" valign="top">&nbsp;용적률</td>
					<td align="right" valign="bottom" class="line_border_3"><%= list.get(0).VL_RAT%></td>
					<td align="left" valign="top">&nbsp;총 호수</td>
					<td align="right" valign="bottom" class="line_border_3"><%= list.get(0).TOTAL_BL_NUM%></td>
					<td align="left" valign="top">&nbsp;총 주차대수</td>
					<td align="right" valign="bottom" class="line_border_3"><%= list.get(0).PARKING_CNT %></td>
					<td align="left" valign="top">&nbsp;부속건축물</td>
					<td align="right" valign="bottom"><%= list.get(0).ACC_BL%></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="10"></td>
	</tr>
	<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border_7">
				<tr>
					<td colspan="9" height="25" align="center" class="line_border_1">건축물현황</td>
				</tr>
				<tr align="center">
					<td height="25" class="line_border_2">구분</td>
					<td class="line_border_2">건축물명칭(번호)</td>
					<td class="line_border_2">건축물 주구조</td>
					<td class="line_border_2">건축물지붕</td>
					<td class="line_border_2">층수</td>
					<td class="line_border_2">용도</td>
					<td class="line_border_2">연면적(㎡)</td>
					<td class="line_border_2" style="text-decoration: line-through;">변동일자</td>
					<td class="line_border_1" style="text-decoration: line-through;">변동원인</td>
				</tr>
				<%
 					for(int i = 0 ; i < m_page_list_cnt ; i++){
 						
 						String MAIN_ATCH_GB_CD = ""; //구분(주건축물, 부건축물)
						String DONG_NM  = ""; //건축물명칭
 						String STRCT_CD = ""; //건축물 주구조
 						String ROOF_CD = ""; //건축물 지붕
 						String GRND_CNT = ""; //층수
 						String MAIN_PURPS_CD = "";
 						String ETC_PURPS = "";
 						
 						if(m_cnt < mgm_cnt){
 							if(list.size() != 0){
 								MAIN_ATCH_GB_CD = list.get(m_cnt).BL_TYPE;
 								DONG_NM = list.get(m_cnt).BL_NM; 
 								STRCT_CD = list.get(m_cnt).STRCT;
 								ROOF_CD = list.get(m_cnt).ROOF;
 								GRND_CNT = list.get(m_cnt).FLR;
 								MAIN_PURPS_CD = list.get(m_cnt).USABILITY;
 								ETC_PURPS = list.get(m_cnt).AREA + "㎡";
 							}
 							m_cnt++;
 						}
 				%>
				<tr align="center">
					<td class="line_border_2" height="25"><%=MAIN_ATCH_GB_CD %></td>
					<td class="line_border_2"><%=DONG_NM %></td>
					<td class="line_border_2"><%=STRCT_CD %></td>
					<td class="line_border_2"><%=ROOF_CD %></td>
					<td class="line_border_2"><%=GRND_CNT %></td>
					<td class="line_border_2"><%=MAIN_PURPS_CD %></td>
					<td class="line_border_2"><%=ETC_PURPS%></td>
					<td class="line_border_2">-</td>
					<td class="line_border_1">-</td>
				</tr>
				<%
 					}
				%>
				
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td height="40" align="center">
						<span style="font-size:14px; "><b>내부 열람용으로만 사용 하실수 있습니다.</b></span>
						<br></br>출력일 : <%=Utils.formatTxtYMD(Utils.getStrSec()) %> 출력자 : <%=user_name %>
					</td>
				</tr>
				<tr>
					<td height="30" class="line_border_6" align="right"></td>
				</tr>
			</table>
		</td>
	</tr>
 </table>
 
 <!-- /////////건축물현황(을)/////////////////////////////////////// -->
 
 <%
 	for(int i = 1 ; i <= u_total_page; i++){
 %>
 <table border="0" cellpadding="0" cellspacing="0" width="1025" id="wapp_table" style="page-break-inside: avoid;"> 
	<tr>
		<td align="center" height="35">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="35">
				<tr>
					<td width="90%" align="center" height="35"><b style="font-size: 25px;">건축물현황(을)</b></td>
					<td width="10%">장번호 : 1-<%=(i+1) %></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center" height="40">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="40" class="line_border">
				<tr>
					<td width="15%" height="40" align="left" valign="top">고유번호</td>
					<td width="35%" style="border-right:1px solid #000000;"><%= onlist.get(0).LAND_CD %></td>
					<td width="15%" align="left" valign="top">접수번호</td>
					<td width="35%"><%=Utils.getStrSec() %></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="10"></td>
	</tr>
	<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border">
				<tr>
					<td height="30" align="left" valign="top" class="line_border_1">&nbsp;대지위치</td>
					<td colspan="3" align="center" class="line_border_2"><%= list.get(0).ADDR %></td>
					<td align="left" valign="top" class="line_border_1">&nbsp;지번</td>
					<td align="right" valign="bottom" class="line_border_2"><%= Utils.formatPNU(pnu) %></td>
					<td align="left" valign="top" class="line_border_1">&nbsp;명칭 및 번호</td>
					<td align="right" valign="bottom" class="line_border_2"><%= list.get(0).BLD_NM %></td>
					<td align="left" valign="top" class="line_border_1">&nbsp;특이사항</td>
					<td align="right" valign="bottom" class="line_border_1"><%= list.get(0).REMARK %></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="10"></td>
	</tr>
	<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border_7">
				<tr>
					<td colspan="9" height="25" align="center" class="line_border_1">건축물현황</td>
				</tr>
				<tr align="center">
					<td height="25" class="line_border_2">구분</td>
					<td class="line_border_2">건축물명칭(번호)</td>
					<td class="line_border_2">건축물 주구조</td>
					<td class="line_border_2">건축물지붕</td>
					<td class="line_border_2">층수</td>
					<td class="line_border_2">용도</td>
					<td class="line_border_2">연면적(㎡)</td>
					<td class="line_border_2" style="text-decoration: line-through;">변동일자</td>
					<td class="line_border_1" style="text-decoration: line-through;">변동원인</td>
				</tr>
				<%
				
 					for(int j = 0 ; j < u_page_list_cnt ; j++){
 						
 						String MAIN_ATCH_GB_CD = ""; //구분(주건축물, 부건축물)
						String DONG_NM  = ""; //건축물명칭
 						String STRCT_CD = ""; //건축물 주구조
 						String ROOF_CD = ""; //건축물 지붕
 						String GRND_CNT = ""; //층수
 						String MAIN_PURPS_CD = "";
 						String ETC_PURPS = "";
 						
 						if(m_cnt < mgm_cnt){
 							if(list.size() != 0){
 								MAIN_ATCH_GB_CD = list.get(m_cnt).BL_TYPE;
 								DONG_NM = list.get(m_cnt).BL_NM; 
 								STRCT_CD = list.get(m_cnt).STRCT;
 								ROOF_CD = list.get(m_cnt).ROOF;
 								GRND_CNT = list.get(m_cnt).FLR;
 								MAIN_PURPS_CD = list.get(m_cnt).USABILITY;
 								ETC_PURPS = list.get(m_cnt).AREA + "㎡";
 							}
 							m_cnt++;
 						}
 				%>
				<tr align="center">					
					<td class="line_border_2" height="25"><%=MAIN_ATCH_GB_CD %></td>
					<td class="line_border_2"><%=DONG_NM %></td>
					<td class="line_border_2"><%=STRCT_CD %></td>
					<td class="line_border_2"><%=ROOF_CD %></td>
					<td class="line_border_2"><%=GRND_CNT %></td>
					<td class="line_border_2"><%=MAIN_PURPS_CD%></td>
					<td class="line_border_2"><%=ETC_PURPS %></td>
					<td class="line_border_2">-</td>
					<td class="line_border_1">-</td>
				</tr>
				<%
 					}	
				%>
			</table>
		</td>
	</tr>
 </table>
 
 <%
 	}
 %>
 
 <!-- 총괄표제부 마지막 -->
 <table border="0" cellpadding="0" cellspacing="0" width="1025" id="wapp_table" style="page-break-inside: avoid;"> 
	<tr>
		<td align="center"  height="35">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="35">
				<tr>
					<td width="90%" align="center" height="35"></td>
					<td width="10%">장번호 : 2-1</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="40" class="line_border">
				<tr>
					<td width="15%" height="40" align="left" valign="top">고유번호</td>
					<td width="35%" style="border-right:1px solid #000000;"><%= onlist.get(0).LAND_CD %></td>
					<td width="15%" align="left" valign="top">접수번호</td>
					<td width="35%"><%=Utils.getStrSec() %></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td colspan="2" height="30" align="center" class="line_border_2" style="text-decoration: line-through;">변동사항</td>
					<td colspan="3" align="center"  class="line_border_2">주차장</td>
					<td rowspan="2" align="center" class="line_border_2" width="15" style="text-decoration: line-through;">승<br/>강<br/>기</td>
					<td class="line_border_1" align="left" valign="top" style="text-decoration: line-through;">승용</td>
					<td class="line_border_2">-</td>
					<td align="center"  class="line_border_1" style="text-decoration: line-through;">관련지번</td>
				</tr>
				<tr>
					<td height="30" align="center" class="line_border_2" width="150" style="text-decoration: line-through;">변동일자</td>
					<td align="center" class="line_border_2" width="250" style="text-decoration: line-through;">변동내용 및 원인</td>
					<td rowspan="2" align="center" class="line_border_2" width="15">옥내</td>
					<td height="25" class="line_border_1" align="left" valign="top">자주식</td>
					<td align="right" class="line_border_2"><%= list.get(0).INSIDE_SF_NUM +"대"%> <%= list.get(0).INSIDE_SF_AREA +"㎡"%>&nbsp;</td>
					<td class="line_border_1" align="left" valign="top" style="text-decoration: line-through;">비상용</td>
					<td class="line_border_2">-</td>
					<td rowspan="4"  align="center" class="line_border_1">-</td>
				</tr>
				<tr>
					<td align="center" rowspan="5" class="line_border_2">-</td>
					<td align="center" rowspan="5" class="line_border_2">-</td>
					<td height="30" class="line_border_1" align="left" valign="top">기계식</td>
					<td align="right" class="line_border_2"><%= list.get(0).INSIDE_MT_NUM + "대" %> <%= list.get(0).INSIDE_MT_AREA + "㎡"%>&nbsp;</td>
					<td rowspan="3" align="center" class="line_border_2" style="text-decoration: line-through;">오수<br/>정화<br/>시설</td>
					<td rowspan="2" class="line_border_1" align="left" valign="top" style="text-decoration: line-through;">형식</td>
					<td rowspan="2" class="line_border_2">-</td>
				</tr>
				<tr>
					<td rowspan="2" align="center" class="line_border_2">옥외</td>
					<td height="30" class="line_border_1" align="left" valign="top">자주식</td>
					<td align="right" class="line_border_2"><%= list.get(0).OUTSIDE_SF_NUM+ "대"%> <%= list.get(0).OUTSIDE_SF_AREA + "㎡"%>&nbsp;</td>
				</tr>
				<tr>
					<td height="30" class="line_border_1" align="left" valign="top">기계식</td>
					<td align="right" class="line_border_2"><%= list.get(0).OUTSIDE_MT_NUM +"대"%> <%= list.get(0).OUTSIDE_MT_AREA +"㎡"%>&nbsp;</td>
					<td class="line_border_1" align="left" valign="top" style="text-decoration: line-through;">용량</td>
					<td class="line_border_2">-</td>
				</tr>
				<tr>
					<td align="center" colspan="7" height="30" class="line_border_1">건축물 에너지소비정보 및 기타 인증 정보</td>
				</tr>
				<tr>
					<td align="center" colspan="7">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td colspan="2" height="30" class="line_border_2">에너지효율</td>
								<td colspan="2" class="line_border_2">에너지성능지표(EPI)점수</td>
								<td colspan="2" class="line_border_2">친환경건축물 인증</td>
								<td colspan="2" class="line_border_1">지능형건축물 인증</td>
							</tr>
							<tr>
								<td height="30" class="line_border_1" align="left" valign="top">등급</td>
								<td class="line_border_2"  align="right" valign="bottom"><%= list.get(0).ENG_CLASS %></td>
								<td rowspan="2" colspan="2" class="line_border_2"> <%= list.get(0).ENG_PFMC %>점</td>
								<td class="line_border_1" align="left" valign="top"><%= list.get(0).IBL_CLASS %> 등급</td>
								<td class="line_border_2" align="right" valign="bottom"><%= list.get(0).IBL_CLASS %></td>
								<td class="line_border_1" align="left" valign="top">등급</td>
								<td class="line_border_1" align="right" valign="bottom"><%= list.get(0).IBL_CLASS %></td>
							</tr>
							<tr>
								<td height="30" class="line_border_1"  align="left" valign="top">에너지 절감율</td>
								<td class="line_border_2" align="right" valign="bottom"><%= list.get(0).ENG_SAVE +"%"%></td>
								<td class="line_border_1" align="left" valign="top">인증점수</td>
								<td class="line_border_2" align="right" valign="bottom"><%= list.get(0).GBL_NUM + "점"%></td>
								<td class="line_border_1" align="left" valign="top">인증점수</td>
								<td class="line_border_1" align="right" valign="bottom"><%= list.get(0).IBL_NUM +"점"%></td>
							</tr>
							<tr>
								<td colspan="2" height="30" class="line_border_2" style="text-decoration: line-through;">변동일자</td>
								<td colspan="4" class="line_border_2" style="text-decoration: line-through;">변동내용 및 원인</td>
								<td colspan="2" class="line_border_1" style="text-decoration: line-through;">그밖의 기재 사항</td>
							</tr>
							<tr>
								<td colspan="2" height="200" class="line_border_2">-</td>
								<td colspan="4" class="line_border_2">-</td>
								<td colspan="2" class="line_border_1">-</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
 </table>
 
 
</body>
</html>