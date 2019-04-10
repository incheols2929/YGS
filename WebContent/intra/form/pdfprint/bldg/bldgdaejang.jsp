<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="
java.util.Collections,

org.apache.commons.io.IOUtils,
org.apache.commons.lang3.StringUtils,

geomex.kras.gmx.KrasGmxConn,
geomex.kras.gmx.KrasGmxConn.SVC,
geomex.kras.gmx.DjUnmarshaller,
geomex.kras.gmx.vo.*,

geomex.utils.*
"
%>
<%
/* 총괄표제부 */

	request.setCharacterEncoding("UTF-8");
	String bno = request.getParameter("bno");
	String pnu = request.getParameter("pnu");
	String user_name = request.getParameter("user_name");
	
	KrasGmxConn kgc = new KrasGmxConn();
	DjUnmarshaller djUm = new DjUnmarshaller();
	
	
	DjyrecaptitleSet vo = (DjyrecaptitleSet) djUm.getUnmarshal( DjyrecaptitleSet.class, kgc.getData(SVC.GetDjyrecaptitle, pnu) );
	
	BldgInfoSet vo2 = (BldgInfoSet) djUm.getUnmarshal( BldgInfoSet.class, kgc.getData(SVC.GetBldgInfo, pnu).replace("<bldg-list>", "").replace("</bldg-list>", "") );
	
	BldgList vo3 = (BldgList) djUm.getUnmarshal( BldgList.class, kgc.getData(SVC.GetBldgList, pnu).replace("<bldg-list>", "").replace("</bldg-list>", "") );

	String SerialNum =vo3.getSerialNo();
	String JibunNum = SerialNum.split("-")[2];
	JibunNum = JibunNum.substring(0,4) + "-" +JibunNum.substring(4,8);
	
	Collections.reverse(vo.getDjytitleList());
	
	int mgm_cnt = vo.getDjytitleList().size();
	int m_page_list_cnt = 7;
	int u_page_list_cnt = 15;
	int u_total_page = 0;
	int m_cnt = 0;
	
	if(mgm_cnt > (m_page_list_cnt)){
		u_total_page = ((mgm_cnt-1-m_page_list_cnt)/(u_page_list_cnt));
		if(mgm_cnt%(u_page_list_cnt) > 0) u_total_page += 1;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
		margin: 0;
		padding: 0;
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
<link rel="stylesheet" type="text/css" href="../../../css/htmlPrint.css"/>
</head>

<body>

<div id="menubar">
	<div class="msg">인쇄 &gt; 페이지설정에서 용지방향을 가로로 선택한 후 인쇄하세요.</div>
</div>

<div class="print_bg">
	<div class="page_body">
		<div class="page_layout">
			<div class="page_ctt">
				 <table border="0" cellpadding="0" cellspacing="0" width="100%" id="wapp_table"> 
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
									<td width="35%" style="border-right:1px solid #000000;"><%=SerialNum %>&nbsp;</td>
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
									<td colspan="3" align="center" class="line_border_2"><%= vo.getDaejiPosition() %>&nbsp;</td>
									<td align="left" valign="top"  class="line_border_1">&nbsp;지번</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getJibunDesc() %>&nbsp;</td>
									<td align="left" valign="top" class="line_border_1">&nbsp;명칭 및 번호</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getDjyDongNm() %>&nbsp;</td>
									<td align="left" valign="top" class="line_border_1">&nbsp;특이사항</td>
									<td align="right" valign="bottom" class="line_border_1"><%= vo.getSpCmt() %>&nbsp;</td>
								</tr>
								<tr>
									<td height="30" align="left" valign="top" class="line_border_1">&nbsp;대지면적</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getPlatArea() %>&nbsp;</td>
									<td align="left" valign="top" class="line_border_1">&nbsp;연면적</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getPlatArea() %>&nbsp;</td>
									<td align="left" valign="top" class="line_border_1" style="text-decoration: line-through;">&nbsp;지역</td>
									<td align="right" valign="bottom" class="line_border_2">-</td>
									<td align="left" valign="top" class="line_border_1" style="text-decoration: line-through;">&nbsp;지구</td>
									<td align="right" valign="bottom" class="line_border_2">-</td>
									<td align="left" valign="top" class="line_border_1" style="text-decoration: line-through;">&nbsp;구역</td>
									<td align="right" valign="bottom" class="line_border_1">-</td>
								</tr>
								<tr>
									<td height="30" align="left" valign="top" class="line_border_1">&nbsp;건축면적</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getArchArea() %>&nbsp;</td>
									<td align="left" valign="top" class="line_border_1">&nbsp;용적률산정용연면적</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getVlRatEstmTotarea() %>&nbsp;</td>
									<td align="left" valign="top" class="line_border_1">&nbsp;건축물수</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getMainBldCnt() %>&nbsp;</td>
									<td align="left" valign="top" class="line_border_1">&nbsp;주용도</td>
									<td align="right" valign="bottom" colspan="3" class="line_border_1"><%= vo.getMainPurpsCdNm() %>&nbsp;</td>
								</tr>
								<tr>
									<td height="30" align="left" valign="top">&nbsp;건폐율</td>
									<td align="right" valign="bottom" class="line_border_3"><%= vo.getBcRat() %>&nbsp;</td>
									<td align="left" valign="top">&nbsp;용적률</td>
									<td align="right" valign="bottom" class="line_border_3"><%= vo.getVlRat() %>&nbsp;</td>
									<td align="left" valign="top">&nbsp;총 호수</td>
									<td align="right" valign="bottom" class="line_border_3"><%= vo.getHoCnt() %>&nbsp;</td>
									<td align="left" valign="top">&nbsp;총 주차대수</td>
									<td align="right" valign="bottom" class="line_border_3"><%= vo.getTotPkngCnt() %>&nbsp;</td>
									<td align="left" valign="top">&nbsp;부속건축물</td>
									<td align="right" valign="bottom"><%= vo.getAtchBld() %>&nbsp;</td>
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
									<td class="line_border_2">연면적(m2)</td>
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
				 						String ETC_PURPS = "";
				 						String TOTAREA = "";
				 						
				 						if(m_cnt < mgm_cnt){
				 							if(vo.getDjytitleList().size() != 0){
				 								System.out.println(vo.getDjytitleList().get(m_cnt));
				 								MAIN_ATCH_GB_CD = vo.getDjytitleList().get(m_cnt).getMainAtchGbCdNm();
				 								DONG_NM = vo.getDjytitleList().get(m_cnt).getDongNm(); 
				 								STRCT_CD = vo.getDjytitleList().get(m_cnt).getStrctCdNm();
				 								ROOF_CD = vo.getDjytitleList().get(m_cnt).getRoofCdNm();
				 								GRND_CNT =  vo.getDjytitleList().get(m_cnt).getFlrCnt();
				 								ETC_PURPS = vo.getDjytitleList().get(m_cnt).getMainPurpsCdNm();
				 								TOTAREA = vo.getDjytitleList().get(m_cnt).getTotarea();
				 							}
				 							m_cnt++;
				 						}
				 				%>
								<tr align="center">
									<td class="line_border_2" height="25"><%=MAIN_ATCH_GB_CD %>&nbsp;</td>
									<td class="line_border_2"><%=DONG_NM %>&nbsp;</td>
									<td class="line_border_2"><%=STRCT_CD %>&nbsp;</td>
									<td class="line_border_2"><%=ROOF_CD %>&nbsp;</td>
									<td class="line_border_2"><%=GRND_CNT %>&nbsp;</td>
									<td class="line_border_2"><%=ETC_PURPS%>&nbsp;</td>
									<td class="line_border_2"><%=TOTAREA %>&nbsp;</td>
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
										<span style="font-size:14px; "><b>내부열람용으로만 사용 하실수 있습니다.</b></span><br/> 
										출력일 : <%=Utils.formatTxtYMD(Utils.getStrSec()) %> 출력자 : <%=user_name%>
									</td>
								</tr>
								<tr>
									<td height="30" class="line_border_6" align="right">&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				 </table>
			</div>
		</div>
 
 <!-- /////////건축물현황(을)/////////////////////////////////////// -->
<%
 	for(int i = 1 ; i <= u_total_page; i++){
 %>

<div class="page_layout">
	<div class="page_ctt page_break">
		 <table border="0" cellpadding="0" cellspacing="0" width="100%" id="wapp_table" style=""> 
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
							<td width="35%" style="border-right:1px solid #000000;"><%=SerialNum %>&nbsp;</td>
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
							<td colspan="3" align="center" class="line_border_2"><%= vo.getDaejiPosition() %>&nbsp;</td>
							<td align="left" valign="top"  class="line_border_1">&nbsp;지번</td>
							<td align="right" valign="bottom" class="line_border_2"><%= vo.getJibunDesc() %>&nbsp;</td>
							<td align="left" valign="top" class="line_border_1">&nbsp;명칭 및 번호</td>
							<td align="right" valign="bottom" class="line_border_2"><%= vo.getDjyDongNm() %>&nbsp;</td>
							<td align="left" valign="top" class="line_border_1">&nbsp;특이사항</td>
							<td align="right" valign="bottom" class="line_border_1"><%= vo.getSpCmt() %>&nbsp;</td>
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
							<td class="line_border_2">연면적(m2)</td>
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
		 						String ETC_PURPS = "";
		 						String TOTAREA = "";
		 						
		 						if(m_cnt < mgm_cnt){
		 							if(vo.getDjytitleList().size() != 0){
		 								MAIN_ATCH_GB_CD = vo.getDjytitleList().get(m_cnt).getMainAtchGbCdNm();
		 								DONG_NM = vo.getDjytitleList().get(m_cnt).getDongNm(); 
		 								STRCT_CD = vo.getDjytitleList().get(m_cnt).getStrctCdNm();
		 								ROOF_CD = vo.getDjytitleList().get(m_cnt).getRoofCdNm();
		 								GRND_CNT =  vo.getDjytitleList().get(m_cnt).getFlrCnt();
		 								ETC_PURPS = vo.getDjytitleList().get(m_cnt).getMainPurpsCdNm();
		 								TOTAREA = vo.getDjytitleList().get(m_cnt).getTotarea();
		 							}
		 							m_cnt++;
		 						}
		 				%>
						<tr align="center">					
							<td class="line_border_2" height="25"><%=MAIN_ATCH_GB_CD %>&nbsp;</td>
							<td class="line_border_2"><%=DONG_NM %>&nbsp;</td>
							<td class="line_border_2"><%=STRCT_CD %>&nbsp;</td>
							<td class="line_border_2"><%=ROOF_CD %>&nbsp;</td>
							<td class="line_border_2"><%=GRND_CNT %>&nbsp;</td>
							<td class="line_border_2"><%=ETC_PURPS%>&nbsp;</td>
							<td class="line_border_2"><%=TOTAREA %>&nbsp;</td>
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
	</div>
</div>
 
 <%
 	}
 %>
 
 <!-- 총괄표제부 마지막 -->
<div class="page_layout">
	<div class="page_ctt page_break">
		 <table border="0" cellpadding="0" cellspacing="0" width="100%" id="wapp_table" style=""> 
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
							<td width="35%" style="border-right:1px solid #000000;"><%=SerialNum %>&nbsp;</td>
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
							<td align="right" class="line_border_2"><%= vo.getIndrAutoUtcnt() +"대"%> <%= vo.getIndrAutoArea() +"㎡"%>&nbsp;</td>
							<td class="line_border_1" align="left" valign="top" style="text-decoration: line-through;">비상용</td>
							<td class="line_border_2">-</td>
							<td rowspan="4"  align="center" class="line_border_1">-</td>
						</tr>
						<tr>
							<td rowspan="5" class="line_border_2">-</td>
							<td rowspan="5" class="line_border_2">-</td>
							<td height="30" class="line_border_1" align="left" valign="top">기계식</td>
							<td align="right" class="line_border_2"><%= vo.getIndrMechUtcnt() + "대" %> <%= vo.getIndrMechArea() + "㎡"%>&nbsp;</td>
							<td rowspan="3" align="center" class="line_border_2" style="text-decoration: line-through;">오수<br/>정화<br/>시설</td>
							<td rowspan="2" class="line_border_1" align="left" valign="top" style="text-decoration: line-through;">형식</td>
							<td rowspan="2" class="line_border_2">-</td>
						</tr>
						<tr>
							<td rowspan="2" align="center" class="line_border_2">옥외</td>
							<td height="30" class="line_border_1" align="left" valign="top">자주식</td>
							<td align="right" class="line_border_2"><%= vo.getOudrAutoUtcnt()+ "대"%> <%= vo.getOudrAutoArea() + "㎡"%>&nbsp;</td>
						</tr>
						<tr>
							<td height="30" class="line_border_1" align="left" valign="top">기계식</td>
							<td align="right" class="line_border_2"><%= vo.getOudrMechUtcnt() +"대"%> <%= vo.getOudrMechArea() +"㎡"%>&nbsp;</td>
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
										<td class="line_border_2"  align="right" valign="bottom"><%= vo.getEngrGrade() %>&nbsp;</td>
										<td rowspan="2" colspan="2" class="line_border_2"> <%= vo.getEngrEpi() %>점</td>
										<td class="line_border_1" align="left" valign="top"><%= vo.getGnBldGrade() %> 등급</td>
										<td class="line_border_2" align="right" valign="bottom"><%= vo.getGnBldGrade() %>&nbsp;</td>
										<td class="line_border_1" align="left" valign="top">등급</td>
										<td class="line_border_1" align="right" valign="bottom"><%= vo.getItgBldGrade() %>&nbsp;</td>
									</tr>
									<tr>
										<td height="30" class="line_border_1"  align="left" valign="top">에너지 절감율</td>
										<td class="line_border_2" align="right" valign="bottom"><%= vo.getEngrRat() +"%"%></td>
										<td class="line_border_1" align="left" valign="top">인증점수</td>
										<td class="line_border_2" align="right" valign="bottom"><%= vo.getGnBldCert() + "점"%></td>
										<td class="line_border_1" align="left" valign="top">인증점수</td>
										<td class="line_border_1" align="right" valign="bottom"><%= vo.getItgBldCert() +"점"%></td>
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
	</div>
	</div>
</div>
</div>
</body>
</html>