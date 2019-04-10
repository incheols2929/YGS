<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="
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
/* 표제부 */

	request.setCharacterEncoding("UTF-8");
	String bno = request.getParameter("bno");
	String pnu = request.getParameter("pnu");
	String user_name = request.getParameter("user_name");
	
	KrasGmxConn kgc = new KrasGmxConn();
	DjUnmarshaller djUm = new DjUnmarshaller();

	
	// 표제부 / 일반건축물
	DjytitleSet vo = (DjytitleSet) djUm.getUnmarshal( DjytitleSet.class, kgc.getData(SVC.GetDjytitle, pnu, bno) );

	// 건축물 간략정보
	BldgInfoSet vo2 = (BldgInfoSet) djUm.getUnmarshal( BldgInfoSet.class, kgc.getData(SVC.GetBldgInfo, pnu).replace("<bldg-list>", "").replace("</bldg-list>", "") );
	
	// 건축물대장 리스트 (get 고유번호)
	BldgList vo3 = (BldgList) djUm.getUnmarshal( BldgList.class, kgc.getData(SVC.GetBldgList, pnu).replace("<bldg-list>", "").replace("</bldg-list>", "") );
	
	// 고유번호
	String SerialNum = vo3.getSerialNo();
	String JibunNum = SerialNum.split("-")[2];
	JibunNum = JibunNum.substring(0,4) + "-" + JibunNum.substring(4,8);
	
	int mgm_cnt = 0;	//층별현황리스트 사이즈
	if ( vo.getDjyflroulnList() != null ) {
		mgm_cnt = vo.getDjyflroulnList().size();
		
	}
	
	int m_page_list_cnt = 6;
	int u_page_list_cnt = 13;
	int u_total_page = 0;
	int m_cnt = 0;
	
	if(mgm_cnt > (m_page_list_cnt*2)){
		
		u_total_page = ((mgm_cnt-1-m_page_list_cnt*2)/(u_page_list_cnt*2));
		
		if(mgm_cnt%(u_page_list_cnt*2) > 0) u_total_page += 1;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
									<td width="35%" style="border-right:1px solid #000000;"><%= SerialNum %></td>
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
									<td height="35" align="left" valign="top" class="line_border_1">&nbsp;대지위치</td>
									<td colspan="3" align="center" class="line_border_2"><%= vo.getDaejiPosition() %></td>
									<td align="left" valign="top"  class="line_border_1">&nbsp;지번</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getJibun() %></td>
									<td align="left" valign="top" class="line_border_1">&nbsp;명칭 및 번호</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getDjyDongNm() %></td>
									<td align="left" valign="top" class="line_border_1">&nbsp;호수</td>
									<td align="right" valign="bottom" class="line_border_1"><%= vo.getHoCnt() %>가구</td>
								</tr>
								<tr>
									<td height="35" align="left" valign="top" class="line_border_1">&nbsp;대지면적</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getPlatArea() +"㎡"%></td>
									<td align="left" valign="top" class="line_border_1">&nbsp;연면적</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getTotArea() +"㎡" %></td>
									<td align="left" valign="top" class="line_border_1" style="text-decoration: line-through;">&nbsp;지역</td>
									<td align="right" valign="bottom" class="line_border_2">-</td>
									<td align="left" valign="top" class="line_border_1" style="text-decoration: line-through;">&nbsp;지구</td>
									<td align="right" valign="bottom" class="line_border_2">-</td>
									<td align="left" valign="top" class="line_border_1" style="text-decoration: line-through;">&nbsp;구역</td>
									<td align="right" valign="bottom" class="line_border_1">-</td>
								</tr>
								<tr>
									<td height="35" align="left" valign="top" class="line_border_1">&nbsp;건축면적</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getArchArea() %></td>
									<td align="left" valign="top" class="line_border_1">&nbsp;용적률산정용연면적</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getVlRatEstmTotarea() %></td>
									<td align="left" valign="top" class="line_border_1">&nbsp;주구조</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getStrctCdNm() %></td>
									<td align="left" valign="top" class="line_border_1">&nbsp;주용도</td>
									<td align="right" valign="bottom"  class="line_border_2"><%= vo.getMainPurpsCdNm() %></td>
									<td align="left" valign="top" class="line_border_1">&nbsp;층수</td>
									<td align="right" valign="bottom" class="line_border_1"><%= vo.getFlrCnt() %></td>
								</tr>
								<tr>
									<td height="35" align="left" valign="top">&nbsp;건폐율</td>
									<td align="right" valign="bottom" class="line_border_3"><%= vo.getBcRat() %></td>
									<td align="left" valign="top">&nbsp;용적률</td>
									<td align="right" valign="bottom" class="line_border_3"><%= vo.getVlRat() %></td>
									<td align="left" valign="top">&nbsp;높이</td>
									<td align="right" valign="bottom" class="line_border_3"><%= vo.getHeit() %></td>
									<td align="left" valign="top">&nbsp;지붕</td>
									<td align="right" valign="bottom" class="line_border_3"><%= vo.getRoofCdNm() %></td>
									<td align="left" valign="top">&nbsp;부속건축물</td>
									<td align="right" valign="bottom"><%= vo.getAtchBld() %></td>
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
							 						
							 						String FLR_GB_CD = ""; 	// 구분
							 						String MAIN_ATCH_GB_CD = "";
							 						String FLR_NO_NM = ""; //층별
							 						String STRCT_CD = ""; //구조
							 						String AREA = ""; //면적(㎡)
							 						String ETC_PURPS = "";
							 						
							 						if(m_cnt < mgm_cnt){
							 							FLR_GB_CD = vo.getDjyflroulnList().get(m_cnt).getFlrGbCdNm();
							 							FLR_NO_NM = vo.getDjyflroulnList().get(m_cnt).getFlrNoNm();
							 							STRCT_CD = vo.getDjyflroulnList().get(m_cnt).getStrctCdNm();
							 							ETC_PURPS = vo.getDjyflroulnList().get(m_cnt).getEtcPurps();
						 								AREA = vo.getDjyflroulnList().get(m_cnt).getArea();
							 							m_cnt++;
							 						}
							 				%>
											<tr align="center">
												<td height="30" class="line_border_2"><%= FLR_GB_CD %></td>
												<td class="line_border_2"><%=FLR_NO_NM %></td>
												<td class="line_border_2"><%=STRCT_CD %></td>
												<td class="line_border_2"><%=ETC_PURPS %></td>
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
							 						String MAIN_ATCH_GB_CD = "";
							 						String FLR_NO_NM = ""; //층별
							 						String STRCT_CD = ""; //구조
							 						String MAIN_PURPS_CD = ""; //용도
							 						String AREA = ""; //면적(㎡)
							 						String ETC_PURPS = "";
							 						
							 						if(m_cnt < mgm_cnt){
							 							FLR_GB_CD = vo.getDjyflroulnList().get(m_cnt).getFlrGbCdNm();
							 							FLR_NO_NM =  vo.getDjyflroulnList().get(m_cnt).getFlrNoNm(); 
						 								STRCT_CD = vo.getDjyflroulnList().get(m_cnt).getStrctCdNm();
						 								ETC_PURPS = vo.getDjyflroulnList().get(m_cnt).getEtcPurps();
						 								AREA = vo.getDjyflroulnList().get(m_cnt).getArea();
							 							m_cnt++;
							 						}
							 				%>
											<tr align="center">
												<td height="30" class="line_border_2"><%= FLR_GB_CD %></td>
												<td class="line_border_2"><%=FLR_NO_NM %></td>
												<td class="line_border_2"><%=STRCT_CD %></td>
												<td class="line_border_2"><%=ETC_PURPS %></td>
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
										<span style="font-size:14px; "><b>내부열람용으로만 사용 하실수 있습니다.</b></span><br/> 
										출력일 : <%=Utils.formatTxtYMD(Utils.getStrSec()) %> 출력자 : <%=user_name%>
									</td>
								</tr>
								<tr>
									<td height="30" class="line_border_6" align="right"></td>
								</tr>
							</table>
						</td>
					</tr>
				 </table>
			</div>
		</div>

 <!-- /////////집합건축물대장(표제부, 을)/////////////////////////////////////// -->
 <%
 	for(int i = 1 ; i <= u_total_page; i++){
 %>
 
<div class="page_layout">
	<div class="page_ctt page_break">
		 <table border="0" cellpadding="0" cellspacing="0" width="100%" id="wapp_table"> 
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
							<td width="35%" style="border-right:1px solid #000000;"><%=SerialNum %></td>
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
					 					for(int j = 0 ; j < u_page_list_cnt ; j++){
					 						
					 						String FLR_GB_CD = ""; // 구분
					 						String MAIN_ATCH_GB_CD = "";
					 						String FLR_NO_NM = ""; //층별
					 						String STRCT_CD = ""; //구조
					 						String MAIN_PURPS_CD = ""; //용도
					 						String AREA = ""; //면적(㎡)
					 						String ETC_PURPS = "";
					 						
					 						if(m_cnt < mgm_cnt){
				 								FLR_GB_CD = vo.getDjyflroulnList().get(m_cnt).getFlrGbCdNm();
					 							FLR_NO_NM =  vo.getDjyflroulnList().get(m_cnt).getFlrNoNm(); 
				 								STRCT_CD = vo.getDjyflroulnList().get(m_cnt).getStrctCdNm();
				 								ETC_PURPS = vo.getDjyflroulnList().get(m_cnt).getEtcPurps();
				 								AREA = vo.getDjyflroulnList().get(m_cnt).getArea();
					 							m_cnt++;
					 						}
					 				%>
									<tr align="center">
										<td height="30" class="line_border_2"><%= FLR_GB_CD %></td>
										<td class="line_border_2"><%=FLR_NO_NM %></td>
										<td class="line_border_2"><%=STRCT_CD %></td>
										<td class="line_border_2"><%=ETC_PURPS %></td>
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
					 					for(int j = 0 ; j < u_page_list_cnt ; j++){
					 						
					 						String FLR_GB_CD = ""; // 구분
					 						String MAIN_ATCH_GB_CD = "";
					 						String FLR_NO_NM = ""; //층별
					 						String STRCT_CD = ""; //구조
					 						String MAIN_PURPS_CD = ""; //용도
					 						String  AREA = ""; //면적(㎡)
					 						String ETC_PURPS = "";
					 						
					 						if(m_cnt < mgm_cnt){
				 								FLR_GB_CD = vo.getDjyflroulnList().get(m_cnt).getFlrGbCdNm();
					 							FLR_NO_NM =  vo.getDjyflroulnList().get(m_cnt).getFlrNoNm(); 
				 								STRCT_CD = vo.getDjyflroulnList().get(m_cnt).getStrctCdNm();
				 								ETC_PURPS = vo.getDjyflroulnList().get(m_cnt).getEtcPurps();
				 								AREA = vo.getDjyflroulnList().get(m_cnt).getArea();
					 							m_cnt++;
					 						}
					 						
					 				%>
									<tr align="center">
										<td height="30" class="line_border_2"><%= FLR_GB_CD %></td>
										<td class="line_border_2"><%=FLR_NO_NM %></td>
										<td class="line_border_2"><%=STRCT_CD %></td>
										<td class="line_border_2"><%=ETC_PURPS %></td>
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
	</div>
</div>
<%
	}
%>

 <!-- 표제부마지막 -->
<div class="page_layout">
	<div class="page_ctt page_break">
		 <table border="0" cellpadding="0" cellspacing="0" width="100%" id="wapp_table"> 
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
							<td width="35%" style="border-right:1px solid #000000;"><%=SerialNum %></td>
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
							<td class="line_border_1"><%= vo.getPmsDay() %></td>
						</tr>
						<tr align="center">
							<td class="line_border_2" height="28"></td>
							<td class="line_border_2"></td>
							<td class="line_border_2"></td>
							<td class="line_border_2" rowspan="2">옥내</td>
							<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">자주식</td>
							<td class="line_border_2"><%= vo.getIndrAutoUtcnt() %>대 <%= vo.getIndrAutoArea() %>㎡</td>
							<td class="line_border_2" width="3%">승용</td>
							<td class="line_border_2"><%= vo.getRideUseElvtCnt() +"대"%></td>
							<td class="line_border_2" width="3%">비상용</td>
							<td class="line_border_2"><%= vo.getEmgenUseElvtCnt() +"대"%></td>
							<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">착공일자</td>
							<td class="line_border_1"><%= vo.getStcnsDay() %></td>
						</tr>
						<tr align="center">
							<td class="line_border_2" height="28"></td>
							<td class="line_border_2"></td>
							<td class="line_border_2"></td>
							<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">기계식</td>
							<td class="line_border_2"><%= vo.getIndrMechUtcnt() %>대 <%= vo.getIndrMechArea() %>㎡</td>
							<td class="line_border_2" colspan="4" style="text-decoration: line-through;">오수정화시설</td>
							<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">사용승인일자</td>
							<td class="line_border_1"><%= vo.getUseaprDay() %></td>
						</tr>
						<tr align="center">
							<td class="line_border_2" height="28"></td>
							<td class="line_border_2"></td>
							<td class="line_border_2"></td>
							<td class="line_border_2" rowspan="2">옥외</td>
							<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">자주식</td>
							<td class="line_border_2"><%= vo.getOudrAutoUtcnt() %>대 <%= vo.getOudrAutoArea() %>㎡</td>
							<td class="line_border_1" colspan="2" align="left" valign="top" style="padding-top:2px;">형식</td>
							<td class="line_border_2" colspan="2"></td>
							<td class="line_border_1" colspan="2" align="center" style="text-decoration: line-through;">관련지번</td>
						</tr>
						<tr align="center">
							<td class="line_border_2" height="28"></td>
							<td class="line_border_2"></td>
							<td class="line_border_2"></td>
							<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">기계식</td>
							<td class="line_border_2"><%= vo.getOudrMechUtcnt() %>대 <%= vo.getOudrMechArea() %>㎡</td>
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
							<td class="line_border_2"><%= vo.getEngrGrade() %></td>
							<td class="line_border_2" rowspan="2"><%= vo.getEngrEpi() %> 점</td>
							<td class="line_border_1" colspan="2"  align="left" valign="top" style="padding-top:2px;">등급</td>
							<td class="line_border_2"><%= vo.getGnBldGrade() %></td>
							<td class="line_border_1" colspan="2"  align="left" valign="top" style="padding-top:2px;">등급</td>
							<td class="line_border_2" colspan="2"><%= vo.getItgBldGrade() %></td>
						</tr>
						<tr align="center">
							<td height="28" class="line_border_1"  align="left" valign="top" style="padding-top:2px;">에너지절감율</td>
							<td class="line_border_2"><%= vo.getEngrRat() %> %</td>
							<td class="line_border_1" colspan="2"  align="left" valign="top" style="padding-top:2px;">인증점수</td>
							<td class="line_border_2"><%= vo.getGnBldCert() %>점</td>
							<td class="line_border_1" colspan="2"  align="left" valign="top" style="padding-top:2px;">인증점수</td>
							<td class="line_border_2" colspan="2"><%= vo.getItgBldCert() %>점</td>
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
	</div>
</div>
</div>
</div>
 
 
</body>
</html>