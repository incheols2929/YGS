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
/* 일반건축물 */

	request.setCharacterEncoding("UTF-8");
	String bno = request.getParameter("bno");
	String pnu = request.getParameter("pnu");
	String user_name = request.getParameter("user_name");
	
	KrasGmxConn kgc = new KrasGmxConn();
	DjUnmarshaller djUm = new DjUnmarshaller();
	
	
	// 표제부 / 일반건축물
	DjytitleSet vo = (DjytitleSet) djUm.getUnmarshal( DjytitleSet.class, kgc.getData(SVC.GetDjytitle, pnu, bno) );
	
	// 건축물대장 리스트 (get 고유번호)
	BldgList vo2 = (BldgList) djUm.getUnmarshal( BldgList.class, kgc.getData(SVC.GetBldgList, pnu).replace("<bldg-list>", "").replace("</bldg-list>", "") );
	
	// 총광표제부 (get 특이사항)
	DjyrecaptitleSet vo3 = (DjyrecaptitleSet) djUm.getUnmarshal( DjyrecaptitleSet.class, kgc.getData(SVC.GetDjyrecaptitle, pnu) );
	
	String SerialNum = vo2.getSerialNo();
	String JibunNum = SerialNum.split("-")[2];
	JibunNum = JibunNum.substring(0, 4) + "-" + JibunNum.substring(4, 8);
	
	
	int j_cnt = vo.getDjyflroulnList().size();	//건축물현황 리스트 
	int s_cnt = vo.getOwnerInfoList().size();	//소유자현황 리스트 갯수
	
	int j_list_cnt = 10;
	int s_list_cnt = 3;
	
	int j_list_cnt2 = 14;
	int s_list_cnt2 = 7;
	
	int j_total_page = 0;
	int s_total_page = 0;
	
	int tot_page = 0;
	
	int jcnt = 0;
	int scnt = 0;

	//전유구분리스트 
	if(j_cnt  > j_list_cnt){
		j_total_page = ((j_cnt-1-j_list_cnt)/(j_list_cnt2));
		if(j_cnt%(j_list_cnt2*2) > 0) j_total_page += 1;
	}
			
	//소유구분리스트 
	if(s_cnt  > s_list_cnt){
		s_total_page = ((s_cnt-1-s_list_cnt)/(s_list_cnt2));
		if(s_cnt%(s_list_cnt2*2) > 0) s_total_page += 1;
	}
			
	if(j_total_page == s_total_page){
		tot_page = j_total_page;
	}else if(j_total_page < s_total_page){
		tot_page = s_total_page;
	}else if(j_total_page > s_total_page){
		tot_page = j_total_page;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
<title>:: 일반건축물대장(갑) ::</title>

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
									<td width="90%" align="center" height="35"><b style="font-size: 25px;">일반건축물대장(갑)</b></td>
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
									<td height="35" align="left" valign="top" class="line_border_1">&nbsp;대지위치</td>
									<td colspan="3" align="center" class="line_border_2"><%= vo.getDaejiPosition() %>&nbsp;</td>
									<td align="left" valign="top"  class="line_border_1">&nbsp;지번</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getJibun() %>&nbsp;</td>
									<td align="left" valign="top" class="line_border_1">&nbsp;명칭 및 번호</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getDjyDongNm() %>&nbsp;</td>
									<td align="left" valign="top" class="line_border_1">&nbsp;특이사항</td>
									<td align="right" valign="bottom" class="line_border_1"><%= vo3.getSpCmt() %>&nbsp;</td>
								</tr>
								<tr>
									<td height="35" align="left" valign="top" class="line_border_1">&nbsp;대지면적</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getPlatArea()%>&nbsp;</td>
									<td align="left" valign="top" class="line_border_1">&nbsp;연면적</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getTotArea() %>&nbsp;</td>
									<td align="left" valign="top" class="line_border_1" style="text-decoration: line-through;">&nbsp;지역</td>
									<td align="right" valign="bottom" class="line_border_2">-</td>
									<td align="left" valign="top" class="line_border_1" style="text-decoration: line-through;">&nbsp;지구</td>
									<td align="right" valign="bottom" class="line_border_2">-</td>
									<td align="left" valign="top" class="line_border_1" style="text-decoration: line-through;">&nbsp;구역</td>
									<td align="right" valign="bottom" class="line_border_1">-</td>
								</tr>
								<tr>
									<td height="35" align="left" valign="top" class="line_border_1">&nbsp;건축면적</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getArchArea()%>&nbsp;</td>
									<td align="left" valign="top" class="line_border_1">&nbsp;용적률산정용연면적</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getVlRatEstmTotarea()%>&nbsp;</td>
									<td align="left" valign="top" class="line_border_1">&nbsp;주구조</td>
									<td align="right" valign="bottom" class="line_border_2"><%= vo.getStrctCdNm() %>&nbsp;</td>
									<td align="left" valign="top" class="line_border_1">&nbsp;주용도</td>
									<td align="right" valign="bottom"  class="line_border_2"><%= vo.getMainPurpsCdNm() %>&nbsp;</td>
									<td align="left" valign="top" class="line_border_1">&nbsp;층수</td>
									<td align="right" valign="bottom" class="line_border_1"><%= vo.getFlrCnt()%>&nbsp;</td>
								</tr>
								<tr>
									<td height="35" align="left" valign="top">&nbsp;건폐율</td>
									<td align="right" valign="bottom" class="line_border_3"><%= vo.getBcRat() %>&nbsp;</td>
									<td align="left" valign="top">&nbsp;용적률</td>
									<td align="right" valign="bottom" class="line_border_3"><%= vo.getVlRat() %>&nbsp;</td>
									<td align="left" valign="top">&nbsp;높이</td>
									<td align="right" valign="bottom" class="line_border_3"><%= vo.getHeit() %>&nbsp;</td>
									<td align="left" valign="top">&nbsp;지붕</td>
									<td align="right" valign="bottom" class="line_border_3"><%= vo.getRoofCdNm() %>&nbsp;</td>
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
							<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border">
								<tr>
									<td width="50%" valign="top">
										<table border="0" cellpadding="0" cellspacing="0" width="100%">
											<tr align="center">
												<td colspan="5" height="35" class="line_border_2">건축물현황</td>
											</tr>
											<tr align="center">
												<td height="30" class="line_border_2">구분</td>
												<td class="line_border_2">층별</td>
												<td class="line_border_2">구조</td>
												<td class="line_border_2">용도</td>
												<td class="line_border_2">면적</td>
											</tr>
											<%
							 					for(int i = 0 ; i < j_list_cnt ; i++){
							 						
							 						String FLR_GB_CD = ""; // 구분
							 						String FLR_NO_NM = ""; //층별
							 						String STRCT_CD = ""; //구조
							 						String ETC_PURPS = "";
							 						String AREA = ""; //면적(㎡)
							 						
							 						if(jcnt < j_cnt){
							 							FLR_GB_CD = vo.getDjyflroulnList().get(jcnt).getFlrGbCdNm();
							 							FLR_NO_NM = vo.getDjyflroulnList().get(jcnt).getFlrNoNm(); 
						 								STRCT_CD = vo.getDjyflroulnList().get(jcnt).getStrctCdNm();
						 								ETC_PURPS = vo.getDjyflroulnList().get(jcnt).getEtcPurps();
						 								AREA = vo.getDjyflroulnList().get(jcnt).getArea();
						 								jcnt++;
							 						}
							 						
							 						String style_1 = "";
							 						String style_2 = "";
							 						
							 						if(j_list_cnt != (i+1)){
							 							style_1 = "line_border_2";
							 						}else{
							 							style_1 = "line_border_3";
							 						}
							 						
						 					%>
											<tr align="center">
												<td height="30" class="<%=style_1%>"><%= FLR_GB_CD %>&nbsp;</td>
												<td class="<%=style_1%>"><%=FLR_NO_NM%>&nbsp;</td>
												<td class="<%=style_1%>"><%=STRCT_CD%>&nbsp;</td>
												<td class="<%=style_1%>"><%=ETC_PURPS%>&nbsp;</td>
												<td class="<%=style_1%>"><%=AREA%>&nbsp;</td>
											</tr>
											<%
												}
											%>							
										</table>
									</td>
									<td width="50%"  valign="top">
										<table border="0" cellpadding="0" cellspacing="0" width="100%">
											<tr align="center">
												<td colspan="4" height="35" class="line_border_1">소유자현황</td>
											</tr>
											<tr align="center">
												<td height="30" class="line_border_2">성명(명칭)</td>
												<td class="line_border_2" rowspan="2">주소</td>
												<td class="line_border_2" rowspan="2">소유권<br/>지분</td>
												<td class="line_border_1">변동일자</td>
											</tr>
											<tr align="center">
												<td height="30" class="line_border_2">주민(법인)등록번호<br/>(부동산등기용등록번호)</td>
												<td class="line_border_1">변동원인</td>
											</tr>
											<%
							 					for(int i = 0 ; i < s_list_cnt ; i++){
							 						
							 						String CHANG_CAUS_DAY = ""; //변동일자
							 						String CHANG_CAUS_CD = ""; //변동원인
							 						String OWNSH_QUOTA = ""; //소유권지분
							 						String NM = ""; //성명
							 						String ADDR_NM = ""; //주소
							 						String JMNO = ""; //주민번호
							 						
							 						if(scnt < s_cnt){
							 							CHANG_CAUS_DAY = vo.getOwnerInfoList().get(scnt).getChgYmd();
							 							CHANG_CAUS_CD = vo.getOwnerInfoList().get(scnt).getChgRsnNm();
							 							OWNSH_QUOTA = vo.getOwnerInfoList().get(scnt).getJibunDesc();
							 							NM = vo.getOwnerInfoList().get(scnt).getOwnerNm();
							 							ADDR_NM = vo.getOwnerInfoList().get(scnt).getDetlAddr();
							 							JMNO = vo.getOwnerInfoList().get(scnt).getResGbnNm();
				
							 							scnt++;
							 						}
						 					%>
											<tr align="center">
												<td height="30" class="line_border_2"><%=NM %>&nbsp;</td>
												<td class="line_border_2" rowspan="2"><%= ADDR_NM %>&nbsp;</td>
												<td class="line_border_2" rowspan="2"><%= OWNSH_QUOTA%>&nbsp;</td>
												<td class="line_border_1"><%= Utils.formatTxtYMD(CHANG_CAUS_DAY) %>&nbsp;</td>
											</tr>
											<tr align="center">
												<td height="30" class="line_border_2"><%=JMNO %>&nbsp;</td>
												<td class="line_border_1"><%= CHANG_CAUS_CD %>&nbsp;</td>
											</tr>
											<%
												}
											%>
											<tr align="center">
												<td colspan="4" height="50">
													<span style="font-size:14px;"><b>내부열람용으로만 사용 하실수 있습니다.</b></span><br/> 
													출력일 : <%=Utils.formatTxtYMD(Utils.getStrSec()) %> 출력자 : <%=user_name%>
												</td>
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
 <%
 	for(int i = 1 ; i <= tot_page; i++){
 %>

<div class="page_layout">
	<div class="page_ctt page_break"> 
		  <table border="0" cellpadding="0" cellspacing="0" width="100%" id="wapp_table"> 
			<tr>
				<td align="center" height="35">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="90%" align="center" height="35"><b style="font-size: 25px;">일반건축물대장(을)</b></td>
							<td width="10%">장번호 : 1-<%=(i+1) %></td>
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
				<td align="center" height="40">
					<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border">
						<tr>
							<td height="40" align="left" valign="top">대지위치</td>
							<td style="border-right:1px solid #000000;">123</td>
							<td align="left" valign="top">지번</td>
							<td style="border-right:1px solid #000000;">123</td>
							<td align="left" valign="top">명칭 및 번호</td>
							<td style="border-right:1px solid #000000;">123</td>
							<td align="left" valign="top">호명칭</td>
							<td>123</td>
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
							<td width="50%" valign="top">
								<table border="0" cellpadding="0" cellspacing="0" width="100%">
									<tr align="center">
										<td colspan="5" height="30" class="line_border_2">건축물현황</td>
									</tr>
									<tr align="center">
										<td height="70" class="line_border_2">구분</td>
										<td class="line_border_2">층별</td>
										<td class="line_border_2">구조</td>
										<td class="line_border_2">용도</td>
										<td class="line_border_2">면적(㎡)</td>
									</tr>
									<%
					 					for(int j = 0 ; j < j_list_cnt2 ; j++){
					 						
					 						String FLR_GB_CD = ""; // 구분
					 						String FLR_NO_NM = ""; //층별
					 						String STRCT_CD = ""; //구조
					 						String AREA = ""; //면적(㎡)
					 						String ETC_PURPS = "";
					 						
					 						if(jcnt < j_cnt){
					 							FLR_GB_CD = vo.getDjyflroulnList().get(jcnt).getFlrGbCdNm();
					 							FLR_NO_NM = vo.getDjyflroulnList().get(jcnt).getFlrNoNm(); 
				 								STRCT_CD = vo.getDjyflroulnList().get(jcnt).getStrctCdNm();
				 								ETC_PURPS = vo.getDjyflroulnList().get(jcnt).getEtcPurps();
				 								AREA = vo.getDjyflroulnList().get(jcnt).getArea();
				 								jcnt++;
					 						}
					 						
					 						String style_1 = "";
					 						String style_2 = "";
					 						
					 						if(j_list_cnt != (i+1)){
					 							style_1 = "line_border_2";
					 						}else{
					 							style_1 = "line_border_3";
					 						}
					 						
				 					%>	
									<tr align="center">
										<td height="30" class="<%=style_1%>"><%=FLR_GB_CD %>&nbsp;</td>
										<td class="<%=style_1%>"><%=FLR_NO_NM %>&nbsp;</td>
										<td class="<%=style_1%>"><%=STRCT_CD %>&nbsp;</td>
										<td class="<%=style_1%>"><%=ETC_PURPS %>&nbsp;</td>
										<td class="<%=style_1%>"><%=AREA %>&nbsp;</td>
									</tr>
									<%
										}
									%>	
								</table>
							</td>
							<td width="50%" valign="top">
								<table border="0" cellpadding="0" cellspacing="0" width="100%">
									<tr align="center">
										<td colspan="5" height="30" class="line_border_1">소유자현황</td>
									</tr>
									<tr align="center">
										<td height="30" class="line_border_2">성명(명칭)</td>
										<td colspan="2" rowspan="2" class="line_border_2">주소</td>
										<td rowspan="2" class="line_border_2">소유권지분</td>
										<td class="line_border_1">변동일자</td>
									</tr>
									<tr align="center">
										<td height="40" class="line_border_2">주민(법인)등록번호<br/>(부동산등기용등록번호)</td>
										<td class="line_border_1">변동원인</td>
									</tr>
									<%
					 					for(int j = 0 ; j < s_list_cnt2; j++){
					 						
					 						String CHANG_CAUS_DAY = ""; //변동일자
					 						String CHANG_CAUS_CD = ""; //변동원인
					 						String OWNSH_QUOTA = ""; //소유권지분
					 						String NM = ""; //성명
					 						String ADDR_NM = ""; //주소
					 						String JMNO = ""; //주민번호
					 						
					 						if(scnt < s_cnt){
					 							CHANG_CAUS_DAY = vo.getOwnerInfoList().get(scnt).getChgYmd();
					 							CHANG_CAUS_CD = vo.getOwnerInfoList().get(scnt).getChgRsnNm();
					 							OWNSH_QUOTA = vo.getOwnerInfoList().get(scnt).getJibunDesc();
					 							NM = vo.getOwnerInfoList().get(scnt).getOwnerNm();
					 							ADDR_NM = vo.getOwnerInfoList().get(scnt).getDetlAddr();
					 							JMNO = vo.getOwnerInfoList().get(scnt).getResGbnNm();
		
					 							scnt++;
					 						}
				 					%>
									<tr align="center">
										<td height="30" class="line_border_2"><%=NM %>&nbsp;</td>
										<td colspan="2" rowspan="2" class="line_border_2"><%= ADDR_NM %>&nbsp;</td>
										<td rowspan="2" class="line_border_2"><%= OWNSH_QUOTA %>&nbsp;</td>
										<td class="line_border_1"><%= Utils.formatTxtYMD(CHANG_CAUS_DAY) %>&nbsp;</td>
									</tr>
									<tr align="center">
										<td height="30" class="line_border_2"><%=JMNO %>&nbsp;</td>
										<td class="line_border_1"><%=CHANG_CAUS_CD %>&nbsp;</td>
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
 
 <!-- 일반건축물 마지막 -->
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
						<tr align="center">
							<td height="28" class="line_border_2" style="text-decoration: line-through;">구분</td>
							<td class="line_border_2" style="text-decoration: line-through;">성명</td>
							<td class="line_border_2" style="text-decoration: line-through;">면허(등록)번호</td>
							<td class="line_border_2" colspan="3">주차장</td>
							<td class="line_border_2" colspan="4">승강기</td>
							<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">허가일자</td>
							<td class="line_border_1"><%= vo.getPmsDay() %>&nbsp;</td>
						</tr>
						<tr align="center">
							<td class="line_border_2" height="28"></td>
							<td class="line_border_2"></td>
							<td class="line_border_2"></td>
							<td class="line_border_2" rowspan="2">옥내</td>
							<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">자주식</td>
							<td class="line_border_2"><%= vo.getIndrAutoUtcnt() %>대 <%= vo.getIndrAutoArea() %>㎡&nbsp;</td>
							<td class="line_border_2" width="3%">승용</td>
							<td class="line_border_2"><%= vo.getRideUseElvtCnt() +"대"%>&nbsp;</td>
							<td class="line_border_2" width="3%">비상용</td>
							<td class="line_border_2"><%= vo.getEmgenUseElvtCnt() +"대"%>&nbsp;</td>
							<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">착공일자</td>
							<td class="line_border_1"><%= vo.getStcnsDay() %>&nbsp;</td>
						</tr>
						<tr align="center">
							<td class="line_border_2" height="28"></td>
							<td class="line_border_2"></td>
							<td class="line_border_2"></td>
							<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">기계식</td>
							<td class="line_border_2"><%= vo.getIndrMechUtcnt() %>대 <%= vo.getIndrMechArea() %>㎡&nbsp;</td>
							<td class="line_border_2" colspan="4" style="text-decoration: line-through;">오수정화시설</td>
							<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">사용승인일자</td>
							<td class="line_border_1"><%= vo.getUseaprDay() %>&nbsp;</td>
						</tr>
						<tr align="center">
							<td class="line_border_2" height="28"></td>
							<td class="line_border_2"></td>
							<td class="line_border_2"></td>
							<td class="line_border_2" rowspan="2">옥외</td>
							<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">자주식</td>
							<td class="line_border_2"><%= vo.getOudrAutoUtcnt() %>대 <%= vo.getOudrAutoArea() %>㎡&nbsp;</td>
							<td class="line_border_1" colspan="2" align="left" valign="top" style="padding-top:2px;">형식</td>
							<td class="line_border_2" colspan="2"></td>
							<td class="line_border_1" colspan="2" align="center" style="text-decoration: line-through;">관련지번</td>
						</tr>
						<tr align="center">
							<td class="line_border_2" height="28"></td>
							<td class="line_border_2"></td>
							<td class="line_border_2"></td>
							<td class="line_border_1" align="left" valign="top" style="padding-top:2px;">기계식</td>
							<td class="line_border_2"><%= vo.getOudrMechUtcnt() %>대 <%= vo.getOudrMechArea() %>㎡&nbsp;</td>
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
							<td class="line_border_2"><%= vo.getEngrGrade() %>&nbsp;</td>
							<td class="line_border_2" rowspan="2"><%= vo.getEngrEpi() %> 점</td>
							<td class="line_border_1" colspan="2"  align="left" valign="top" style="padding-top:2px;">등급</td>
							<td class="line_border_2"><%= vo.getGnBldGrade() %>&nbsp;</td>
							<td class="line_border_1" colspan="2"  align="left" valign="top" style="padding-top:2px;">등급</td>
							<td class="line_border_2" colspan="2"><%= vo.getItgBldGrade() %>&nbsp;</td>
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
							<td height="100" class="line_border_3">&nbsp;</td>
							<td colspan="2" class="line_border_3">&nbsp;</td>
							<td colspan="2" class="line_border_3">&nbsp;</td>
							<td colspan="5" class="line_border_3">&nbsp;</td>
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