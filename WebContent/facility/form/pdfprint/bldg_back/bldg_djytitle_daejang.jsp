<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.pkg.sys.eais.DjyBldgList"%>
<%@page import="geomex.pkg.sys.eais.Djytitle"%> 
<%@page import="geomex.svc.handler.Code"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>    
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<%
	request.getParameter("UTF-8");
	String key = request.getParameter("key");
	String pnu = request.getParameter("pnu");
	String user_name = request.getParameter("user_name");
	
	Djytitle dbl = new Djytitle();
	ArrayList<Djytitle> list = new ArrayList<Djytitle>();
	DjyBldgList dbl2 = new DjyBldgList();
	ArrayList<DjyBldgList> onlist = new ArrayList<DjyBldgList>();
	
	try {
		list = dbl.getDjytitle(key);
		onlist = dbl2.getBldgList(pnu);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	int mgm_cnt = list.size();
	int m_page_list_cnt = 6;
	int u_page_list_cnt = 13;
	int u_total_page = 0;
	int m_cnt = 0;
	
	if(mgm_cnt > (m_page_list_cnt*2)){
		
		u_total_page = ((mgm_cnt-1-m_page_list_cnt*2)/(u_page_list_cnt*2));
		
		if(mgm_cnt%(u_page_list_cnt*2) > 0) u_total_page += 1;
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
<title>:: 집합건축물대장(표제부, 갑) ::</title>

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
					<td width="90%" align="center" height="35"><b style="font-size: 25px;">집합건축물대장(표제부, 갑)</b></td>
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
					<td width="35%" style="border-right:1px solid #000000;"><%= onlist.get(0).LAND_CD %></td>
					<td width="15%" align="left" valign="top">접수번호</td>
					<td width="35%"><%= Utils.getStrSec() %></td>
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
					<td height="35" align="left" valign="top" class="line_border_1">&nbsp;대지위치</td>
					<td colspan="3" align="center" class="line_border_2"><%= list.get(0).ADDR %></td>
					<td align="left" valign="top"  class="line_border_1">&nbsp;지번</td>
					<td align="right" valign="bottom" class="line_border_2"><%= Utils.formatPNU(pnu) %></td>
					<td align="left" valign="top" class="line_border_1">&nbsp;명칭 및 번호</td>
					<td align="right" valign="bottom" class="line_border_2"><%=  list.get(0).BLD_NM %></td>
					<td align="left" valign="top" class="line_border_1">&nbsp;호수</td>
					<td align="right" valign="bottom" class="line_border_1"><%= list.get(0).BL_NUM %>가구</td>
				</tr>
				<tr>
					<td height="35" align="left" valign="top" class="line_border_1">&nbsp;대지면적</td>
					<td align="right" valign="bottom" class="line_border_2"><%= list.get(0).PLATAREA %></td>
					<td align="left" valign="top" class="line_border_1">&nbsp;연면적</td>
					<td align="right" valign="bottom" class="line_border_2"><%= list.get(0).TOTALAREA %></td>
					<td align="left" valign="top" class="line_border_1" style="text-decoration: line-through;">&nbsp;지역</td>
					<td align="right" valign="bottom" class="line_border_2">-</td>
					<td align="left" valign="top" class="line_border_1" style="text-decoration: line-through;">&nbsp;지구</td>
					<td align="right" valign="bottom" class="line_border_2">-</td>
					<td align="left" valign="top" class="line_border_1" style="text-decoration: line-through;">&nbsp;구역</td>
					<td align="right" valign="bottom" class="line_border_1">-</td>
				</tr>
				<tr>
					<td height="35" align="left" valign="top" class="line_border_1">&nbsp;건축면적</td>
					<td align="right" valign="bottom" class="line_border_2"><%= list.get(0).ARCHAREA %></td>
					<td align="left" valign="top" class="line_border_1">&nbsp;용적률산정용연면적</td>
					<td align="right" valign="bottom" class="line_border_2"><%= list.get(0).VL_RAT_AREA %></td>
					<td align="left" valign="top" class="line_border_1">&nbsp;주구조</td>
					<td align="right" valign="bottom" class="line_border_2"><%= list.get(0).BL_STRCT %></td>
					<td align="left" valign="top" class="line_border_1">&nbsp;주용도</td>
					<td align="right" valign="bottom"  class="line_border_2"><%= list.get(0).BL_USABILITY %></td>
					<td align="left" valign="top" class="line_border_1">&nbsp;층수</td>
					<td align="right" valign="bottom" class="line_border_1"><%= list.get(0).FLR %></td>
				</tr>
				<tr>
					<td height="35" align="left" valign="top">&nbsp;건폐율</td>
					<td align="right" valign="bottom" class="line_border_3"><%= list.get(0).BC_RAT %></td>
					<td align="left" valign="top">&nbsp;용적률</td>
					<td align="right" valign="bottom" class="line_border_3"><%= list.get(0).VL_RAT %></td>
					<td align="left" valign="top">&nbsp;높이</td>
					<td align="right" valign="bottom" class="line_border_3"><%= list.get(0).HEIGHT %></td>
					<td align="left" valign="top">&nbsp;지붕</td>
					<td align="right" valign="bottom" class="line_border_3"><%= list.get(0).BL_ROOF %></td>
					<td align="left" valign="top">&nbsp;부속건축물</td>
					<td align="right" valign="bottom"><%= list.get(0).ACC_BL %></td>
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
					<td width="50%">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr align="center">
								<td colspan="5" height="35" class="line_border_2">건축물현황</td>
							</tr>
							<tr align="center">
								<td height="30" class="line_border_2">구분</td>
								<td class="line_border_2">층별</td>
								<td class="line_border_2">구조</td>
								<td class="line_border_2">용도</td>
								<td class="line_border_2">면적(㎡)</td>
							</tr>
							
							<%
			 					for(int i = 0 ; i < m_page_list_cnt ; i++){
			 						
			 						String FLR_GB_CD = ""; // 구분
			 						String FLR_NO_NM = ""; //층별
			 						String STRCT_CD = ""; //구조
			 						String MAIN_PURPS_CD = ""; //용도
			 						String AREA = ""; //면적(㎡)
			 						
			 						if(m_cnt < mgm_cnt){
			 							FLR_GB_CD = list.get(m_cnt).FLR_TYPE;
			 							FLR_NO_NM = list.get(m_cnt).FLR_NM;
		 								STRCT_CD = list.get(m_cnt).FLR_STRCT;
		 								MAIN_PURPS_CD = list.get(m_cnt).FLR_USABILITY;
		 								AREA =  list.get(m_cnt).FLR_AREA + "㎡";
			 							m_cnt++;
			 						}
			 				%>
							<tr align="center">
								<td height="30" class="line_border_2"><%= FLR_GB_CD %></td>
								<td class="line_border_2"><%=FLR_NO_NM %></td>
								<td class="line_border_2"><%=STRCT_CD %></td>
								<td class="line_border_2"><%=MAIN_PURPS_CD %></td>
								<td class="line_border_2"><%=AREA %></td>
							</tr>
							<%
								}
							%>
						</table>
					</td>
					<td width="50%">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr align="center">
								<td colspan="5" height="35" class="line_border_1">건축물현황</td>
							</tr>
							<tr align="center">
								<td height="30" class="line_border_2">구분</td>
								<td class="line_border_2">층별</td>
								<td class="line_border_2">구조</td>
								<td class="line_border_2">용도</td>
								<td class="line_border_1">면적(㎡)</td>
							</tr>
							<%
		 						for(int i = 0 ; i < m_page_list_cnt ; i++){
		 							
		 							String FLR_GB_CD = ""; // 구분
		 							String FLR_NO_NM = ""; //층별
		 							String STRCT_CD = ""; //구조
		 							String MAIN_PURPS_CD = ""; //용도
		 							String AREA = ""; //면적(㎡)
		 							
		 							if(m_cnt < mgm_cnt){
		 								FLR_GB_CD = list.get(m_cnt).FLR_TYPE;
		 								FLR_NO_NM = list.get(m_cnt).FLR_NM;
	 									STRCT_CD = list.get(m_cnt).FLR_STRCT;
	 									MAIN_PURPS_CD = list.get(m_cnt).FLR_USABILITY;
	 									AREA =  list.get(m_cnt).FLR_AREA + "㎡";
		 								m_cnt++;
		 							}
		 					%>
							<tr align="center">
								<td height="30" class="line_border_2"><%= FLR_GB_CD %></td>
								<td class="line_border_2"><%=FLR_NO_NM %></td>
								<td class="line_border_2"><%=STRCT_CD %></td>
								<td class="line_border_2"><%=MAIN_PURPS_CD %></td>
								<td class="line_border_1"><%=AREA %></td>
							</tr>
							<%
								}
							%>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td height="60" align="center">
						<span style="font-size:14px; "><b>내부 열람용으로만 사용 하실수 있습니다.</b></span><br/> 
						출력일 : <%=Utils.formatTxtYMD(Utils.getStrSec()) %> 출력자 : <%=user_name %>
					</td>
				</tr>
				<tr>
					<td height="30" class="line_border_6" align="right"></td>
				</tr>
			</table>
		</td>
	</tr>
 </table>
 
 <!-- /////////집합건축물대장(표제부, 을)/////////////////////////////////////// -->
 <%
 	for(int i = 1 ; i <= u_total_page; i++){
 %>
 <table border="0" cellpadding="0" cellspacing="0" width="1025" id="wapp_table" style="page-break-inside: avoid;"> 
	<tr>
		<td align="center" height="35">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="35">
				<tr>
					<td width="90%" align="center" height="35"><b style="font-size: 25px;">집합건축물(표제부, 을)</b></td>
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
			<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border_7">
				<tr>
					<td width="50%">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr align="center">
								<td colspan="5" height="30" class="line_border_2">건축물현황</td>
							</tr>
							<tr align="center">
								<td height="30" class="line_border_2">구분</td>
								<td class="line_border_2">층별</td>
								<td class="line_border_2">구조</td>
								<td class="line_border_2">용도</td>
								<td class="line_border_2">면적(㎡)</td>
							</tr>
							<%
	 							for(int j = 0 ; j < m_page_list_cnt ; j++){
	 							
	 								String FLR_GB_CD = ""; // 구분
	 								String FLR_NO_NM = ""; //층별
	 								String STRCT_CD = ""; //구조
	 								String MAIN_PURPS_CD = ""; //용도
	 								String AREA = ""; //면적(㎡)
	 								
	 								if(m_cnt < mgm_cnt){
	 									FLR_GB_CD = list.get(m_cnt).FLR_TYPE;
	 									FLR_NO_NM = list.get(m_cnt).FLR_NM;
 										STRCT_CD = list.get(m_cnt).FLR_STRCT;
 										MAIN_PURPS_CD = list.get(m_cnt).FLR_USABILITY;
 										AREA =  list.get(m_cnt).FLR_AREA + "㎡";
	 									m_cnt++;
	 								}
	 						%>
							<tr align="center">
								<td height="30" class="line_border_2"><%= FLR_GB_CD %></td>
								<td class="line_border_2"><%=FLR_NO_NM %></td>
								<td class="line_border_2"><%=STRCT_CD %></td>
								<td class="line_border_2"><%=MAIN_PURPS_CD %></td>
								<td class="line_border_2"><%=AREA %></td>
							</tr>
							<%
								}
							%>
						</table>
					</td>
					<td width="50%">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr align="center">
								<td colspan="5" height="30" class="line_border_1">건축물현황</td>
							</tr>
							<tr align="center">
								<td height="30" class="line_border_2">구분</td>
								<td class="line_border_2">층별</td>
								<td class="line_border_2">구조</td>
								<td class="line_border_2">용도</td>
								<td class="line_border_1">면적(㎡)</td>
							</tr>
							<%
 								for(int j = 0 ; j < m_page_list_cnt ; j++){
		 							
 									String FLR_GB_CD = ""; // 구분
 									String FLR_NO_NM = ""; //층별
 									String STRCT_CD = ""; //구조
 									String MAIN_PURPS_CD = ""; //용도
 									String AREA = ""; //면적(㎡)
 									
 									if(m_cnt < mgm_cnt){
 										FLR_GB_CD = list.get(m_cnt).FLR_TYPE;
 										FLR_NO_NM = list.get(m_cnt).FLR_NM;
										STRCT_CD = list.get(m_cnt).FLR_STRCT;
										MAIN_PURPS_CD = list.get(m_cnt).FLR_USABILITY;
										AREA = list.get(m_cnt).FLR_AREA + "㎡";
 										m_cnt++;
 									}
 							%>
							<tr align="center">
								<td height="30" class="line_border_2"><%= FLR_GB_CD %></td>
								<td class="line_border_2"><%=FLR_NO_NM %></td>
								<td class="line_border_2"><%=STRCT_CD %></td>
								<td class="line_border_2"><%=MAIN_PURPS_CD %></td>
								<td class="line_border_1"><%=AREA %></td>
							</tr>
							<%
								}
							%>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
 </table>
 <%
 	}
 %>

 
 <!-- 표제부마지막 -->
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
				<tr align="center">
					<td height="28" class="line_border_2" style="text-decoration: line-through;">구분</td>
					<td class="line_border_2" style="text-decoration: line-through;">성명</td>
					<td class="line_border_2" style="text-decoration: line-through;">면허(등록)번호</td>
					<td class="line_border_2" colspan="3">주차장</td>
					<td class="line_border_2" colspan="4">승강기</td>
					<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">허가일자</td>
					<td class="line_border_1"><%= list.get(0).PMT_YMD %></td>
				</tr>
				<tr align="center">
					<td class="line_border_2" height="28"></td>
					<td class="line_border_2"></td>
					<td class="line_border_2"></td>
					<td class="line_border_2" rowspan="2">옥내</td>
					<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">자주식</td>
					<td class="line_border_2"><%= list.get(0).INSIDE_SF_NUM %>대 <%= list.get(0).INSIDE_SF_AREA %>㎡</td>
					<td class="line_border_2" width="3%">승용</td>
					<td class="line_border_2"><%= list.get(0).RIDING %></td>
					<td class="line_border_2" width="3%">비상용</td>
					<td class="line_border_2"><%= list.get(0).RIDING_EMER %></td>
					<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">착공일자</td>
					<td class="line_border_1"><%= list.get(0).CON_YMD %></td>
				</tr>
				<tr align="center">
					<td class="line_border_2" height="28"></td>
					<td class="line_border_2"></td>
					<td class="line_border_2"></td>
					<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">기계식</td>
					<td class="line_border_2"><%= list.get(0).INSIDE_MT_NUM %>대 <%= list.get(0).INSIDE_MT_AREA %>㎡</td>
					<td class="line_border_2" colspan="4" style="text-decoration: line-through;">오수정화시설</td>
					<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">사용승인일자</td>
					<td class="line_border_1"><%= list.get(0).ACC_YMD %></td>
				</tr>
				<tr align="center">
					<td class="line_border_2" height="28"></td>
					<td class="line_border_2"></td>
					<td class="line_border_2"></td>
					<td class="line_border_2" rowspan="2">옥외</td>
					<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">자주식</td>
					<td class="line_border_2"><%= list.get(0).OUTSIDE_SF_NUM %>대 <%= list.get(0).OUTSIDE_SF_AREA %>㎡</td>
					<td class="line_border_1" colspan="2" align="left" valign="top" style="padding-top:2px;">형식</td>
					<td class="line_border_2" colspan="2"></td>
					<td class="line_border_1" colspan="2" align="center" style="text-decoration: line-through;">관련지번</td>
				</tr>
				<tr align="center">
					<td class="line_border_2" height="28"></td>
					<td class="line_border_2"></td>
					<td class="line_border_2"></td>
					<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">기계식</td>
					<td class="line_border_2"><%= list.get(0).OUTSIDE_MT_NUM %>대 <%= list.get(0).OUTSIDE_MT_AREA %>㎡</td>
					<td class="line_border_1" colspan="2" align="left" valign="top" style="padding-top:2px;">용량</td>
					<td class="line_border_2" colspan="2"></td>
					<td class="line_border_1" colspan="2" rowspan="8"></td>
				</tr>
				<tr>
					<td colspan="10" height="10" class="line_border_2"></td>
				</tr>
				<tr align="center">
					<td colspan="10" height="30" class="line_border_2">건축물 에너지 소비정보 및 그밖의 인증정보</td>
				</tr>
				<tr align="center">
					<td colspan="2" height="28" class="line_border_2">에너지효율</td>
					<td class="line_border_2">에너지성능지표(EPI)점수</td>
					<td class="line_border_2" colspan="3">친환경건축물 인증</td>
					<td class="line_border_2" colspan="4">지능형 건축물 인증</td>
				</tr>
				<tr align="center">
					<td height="28" class="line_border_1"  align="left" valign="top" style="padding-top:2px;"> 등급</td>
					<td class="line_border_2"><%= list.get(0).ENG_CLASS %></td>
					<td class="line_border_2" rowspan="2"><%= list.get(0).ENG_PFMC %>점</td>
					<td class="line_border_1" colspan="2"  align="left" valign="top" style="padding-top:2px;">등급</td>
					<td class="line_border_2"><%= list.get(0).GBL_CLASS %></td>
					<td class="line_border_1" colspan="2"  align="left" valign="top" style="padding-top:2px;">등급</td>
					<td class="line_border_2" colspan="2"><%= list.get(0).IBL_CLASS %></td>
				</tr>
				<tr align="center">
					<td height="28" class="line_border_1"  align="left" valign="top" style="padding-top:2px;">에너지절감율</td>
					<td class="line_border_2"><%= list.get(0).ENG_SAVE %> %</td>
					<td class="line_border_1" colspan="2"  align="left" valign="top" style="padding-top:2px;">인증점수</td>
					<td class="line_border_2"><%= list.get(0).GBL_NUM %>점</td>
					<td class="line_border_1" colspan="2"  align="left" valign="top" style="padding-top:2px;">인증점수</td>
					<td class="line_border_2" colspan="2"><%= list.get(0).IBL_NUM %>점</td>
				</tr>
				<tr>
					<td class="line_border_2" colspan="10" height="10"></td>
				</tr>
				<tr align="center">
					<td colspan="10" height="30" class="line_border_2" style="text-decoration: line-through;">변동사항</td>
				</tr>
				<tr align="center">
					<td height="28" class="line_border_2" style="text-decoration: line-through;">변동일자</td>
					<td colspan="2" class="line_border_2" style="text-decoration: line-through;">변동내용 및 원인</td>
					<td colspan="2" class="line_border_2" style="text-decoration: line-through;">변동일자</td>
					<td colspan="5" class="line_border_2" style="text-decoration: line-through;">변동내용 및 원인</td>
					<td colspan="2" class="line_border_1" style="text-decoration: line-through;">그밖의 기재사항</td>
				</tr>
				<tr align="center">
					<td height="100" class="line_border_3"></td>
					<td colspan="2" class="line_border_3"></td>
					<td colspan="2" class="line_border_3"></td>
					<td colspan="5" class="line_border_3"></td>
					<td colspan="2"></td>
				</tr>
			</table>
		</td>
	</tr>
 </table>
 
 
</body>
</html>