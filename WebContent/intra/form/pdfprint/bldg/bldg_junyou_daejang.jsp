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
/* 전유부 */

	request.setCharacterEncoding("UTF-8");
	String pnu = request.getParameter("pnu");
	String bno = request.getParameter("bno");	// ho
	String bno2 = request.getParameter("bno2");	// dong
	String user_name = request.getParameter("user_name");

	
	KrasGmxConn kgc = new KrasGmxConn();
	DjUnmarshaller djUm = new DjUnmarshaller();

	
	// 전유부 리스트
	JeonyubldgSet vo = (JeonyubldgSet) djUm.getUnmarshal( JeonyubldgSet.class, kgc.getData(SVC.GetJeonyubldg, pnu, bno2) );
	
	// 전유부 정보
	DjyexposSet vo2 = (DjyexposSet) djUm.getUnmarshal( DjyexposSet.class, kgc.getData(SVC.GetDjyexpos, pnu, bno) );
	
	// 건축물 대장 리스트 (고유번호)
	BldgList vo3 = (BldgList) djUm.getUnmarshal( BldgList.class, kgc.getData(SVC.GetBldgList, pnu).replace("<bldg-list>", "").replace("</bldg-list>", "") );
	
	// 호 명칭
	String ho = "";
	for ( int i = 0; i < vo.getHoNameList().size(); i++ ) {
		if ( bno.equals(vo.getHoNameList().get(i).getBno()) ) {
			ho = vo.getHoNameList().get(i).getVal() + "호";
		}
	}
	
	// 고유번호
	String SerialNum = vo3.getSerialNo();
	// 지번코드
	String JibunNum = SerialNum.split("-")[2];
	JibunNum = JibunNum.substring(0, 4)+"-"+JibunNum.substring(4, 8);
	
	//페이지갯수구하기
	int j_cnt = vo2.getDjyflroulnList().size();	//전유구분 리스트 갯수
	int s_cnt = vo2.getDjyownrList().size(); 	//소유자현황 리스트 갯수

	int j_list_cnt = 3;
	int s_list_cnt = 2;
	
	int j_list_cnt2 = 6;
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
<title>:: 집합건축물대장(전유부) ::</title>

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
									<td width="90%" align="center" height="35"><b style="font-size: 25px;">집합건축물대장(전유부)</b></td>
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
						<td align="center" height="40">
							<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border">
								<tr>
									<td height="40" align="left" valign="top">대지위치</td>
									<td style="border-right:1px solid #000000;"><%= vo.getDaejiPosition() %>&nbsp;</td>
									<td align="left" valign="top">지번</td>
									<td style="border-right:1px solid #000000;"><%= JibunNum %>&nbsp;</td>
									<td align="left" valign="top">명칭 및 번호</td>
									<td style="border-right:1px solid #000000;"><%= vo.getDongNm() %>&nbsp;</td>
									<td align="left" valign="top">호명칭</td>
									<td><%= ho %></td>
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
												<td colspan="5" height="30" class="line_border_2">전유구분</td>
											</tr>
											<tr align="center">
												<td height="70" class="line_border_2">구분</td>
												<td class="line_border_2">층별</td>
												<td class="line_border_2">구조</td>
												<td class="line_border_2">용도</td>
												<td class="line_border_2">면적(㎡)</td>
											</tr>
											<%
												Collections.reverse(vo2.getDjyownrList());
											%>
											<%
							 					for(int i = 0 ; i < j_list_cnt ; i++){
							 						System.out.println(vo2.getDjyflroulnList().get(jcnt));
							 						String MAIN_ATCH_GB_CD = ""; //구분
							 						String FLR_NO_NM = "";
							 						String STRCT_CD = "";
							 						String MAIN_PURPS_CD = "";
							 						String AREA = "";
							 						if(jcnt < j_cnt){
							 							MAIN_ATCH_GB_CD = vo2.getDjyflroulnList().get(jcnt).getMainAtchGbCdNm();
							 							FLR_NO_NM = vo2.getDjyflroulnList().get(jcnt).getFlrNoNm();
							 							STRCT_CD = vo2.getDjyflroulnList().get(jcnt).getStrctCdNm();
							 							MAIN_PURPS_CD = vo2.getDjyflroulnList().get(jcnt).getMainPurpsCdNm();
							 							AREA =  vo2.getDjyflroulnList().get(jcnt).getArea();
							 							jcnt++;
							 						}
						 					%>
											<tr align="center">
												<td height="30" class="line_border_2"><%=MAIN_ATCH_GB_CD %>&nbsp;</td>
												<td class="line_border_2"><%=FLR_NO_NM %>&nbsp;</td>
												<td class="line_border_2"><%=STRCT_CD %>&nbsp;</td>
												<td class="line_border_2"><%=MAIN_PURPS_CD %>&nbsp;</td>
												<td class="line_border_2"><%=AREA %>&nbsp;</td>
											</tr>
											<%
							 					}
											%>
											<tr align="center">
												<td height="30" colspan="5" class="line_border_2" style="text-decoration: line-through;">공용부분</td>
											</tr>
											<tr align="center">
												<td height="30" class="line_border_2" style="text-decoration: line-through;">구분</td>
												<td class="line_border_2" style="text-decoration: line-through;">층별</td>
												<td class="line_border_2" style="text-decoration: line-through;">구조</td>
												<td class="line_border_2" style="text-decoration: line-through;">용도</td>
												<td class="line_border_2" style="text-decoration: line-through;">면적(㎡)</td>
											</tr>
											<tr align="center">
												<td height="30" class="line_border_2"></td>
												<td class="line_border_2"></td>
												<td class="line_border_2"></td>
												<td class="line_border_2"></td>
												<td class="line_border_2"></td>
											</tr>
											<tr align="center">
												<td height="30" class="line_border_2"></td>
												<td class="line_border_2"></td>
												<td class="line_border_2"></td>
												<td class="line_border_2"></td>
												<td class="line_border_2"></td>
											</tr>
											<tr align="center">
												<td height="30" class="line_border_2"></td>
												<td class="line_border_2"></td>
												<td class="line_border_2"></td>
												<td class="line_border_2"></td>
												<td class="line_border_2"></td>
											</tr>
											<tr align="center">
												<td height="30" class="line_border_2"></td>
												<td class="line_border_2"></td>
												<td class="line_border_2"></td>
												<td class="line_border_2"></td>
												<td class="line_border_2"></td>
											</tr>
											<tr align="center">
												<td height="30" class="line_border_3"></td>
												<td class="line_border_3"></td>
												<td class="line_border_3"></td>
												<td class="line_border_3"></td>
												<td class="line_border_3"></td>
											</tr>
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
							 					for(int i = 0 ; i < s_list_cnt ; i++){
							 						String CHANG_CAUS_DAY = ""; //변동일자
							 						String CHANG_CAUS_CD = ""; //변동원인
							 						String OWNSH_QUOTA = ""; //소유권지분
							 						String NM = ""; //성명
							 						String ADDR_NM = ""; //주소
							 						String JMNO = ""; //주민번호
							 						
							 						if(scnt < s_cnt){
							 							CHANG_CAUS_DAY = vo2.getDjyownrList().get(scnt).getChangCausDay();
							 							CHANG_CAUS_CD = vo2.getDjyownrList().get(scnt).getChangCausCdNm();
							 							OWNSH_QUOTA = vo2.getDjyownrList().get(scnt).getJibunDesc();
							 							NM = vo2.getDjyownrList().get(scnt).getNm();
							 							ADDR_NM = vo2.getDjyownrList().get(scnt).getAddr();
							 							JMNO = vo2.getDjyownrList().get(scnt).getRegNo();
				
							 							scnt++;
							 						}
						 					%>
											<tr align="center">
												<td height="30" class="line_border_2"><%=NM %>&nbsp;</td>
												<td colspan="2" rowspan="2" class="line_border_2"><%= ADDR_NM %>&nbsp;</td>
												<td rowspan="2" class="line_border_2"><%= OWNSH_QUOTA%>&nbsp;</td>
												<td class="line_border_1"><%=CHANG_CAUS_DAY %>&nbsp;</td>
											</tr>
											<tr align="center">
												<td height="30" class="line_border_2"><%=JMNO %>&nbsp;</td>
												<td class="line_border_1"><%=CHANG_CAUS_CD %>&nbsp;</td>
											</tr>
											<%
							 					}
											%>
											
											<tr align="center">
												<td colspan="5" height="30" class="line_border_1" style="text-decoration: line-through;">공동주택(아파트) 가격(단위:원)</td>
											</tr>
											<tr align="center">
												<td colspan="2" height="30" class="line_border_2" style="text-decoration: line-through;">기준일</td>
												<td colspan="3" class="line_border_1" style="text-decoration: line-through;">공동주택(아파트)가격</td>
											</tr>
											<tr align="center">
												<td colspan="2" height="30" class="line_border_2"></td>
												<td colspan="3" class="line_border_1"></td>
											</tr>
											<tr align="center">
												<td colspan="2" height="30" class="line_border_2"></td>
												<td colspan="3" class="line_border_1"></td>
											</tr>
											<tr align="center">
												<td colspan="2" height="30" class="line_border_2"></td>
												<td colspan="3" class="line_border_1"></td>
											</tr>
											<tr>
												<td colspan="5" height="30" style="text-decoration: line-through;">* 『 부동산 가격공시 및 감정평가에관한 법률』 제17조에 따른 공동주택가격만 표시됩니다.</td>
											</tr>
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

<!-- 전유부 뒷장 -->
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
							<td width="90%" align="center" height="35"><b style="font-size: 25px;">집합건축물대장(전유부)</b></td>
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
							<td style="border-right:1px solid #000000;"><%= vo.getDaejiPosition() %>&nbsp;</td>
							<td align="left" valign="top">지번</td>
							<td style="border-right:1px solid #000000;"><%= JibunNum %>&nbsp;</td>
							<td align="left" valign="top">명칭 및 번호</td>
							<td style="border-right:1px solid #000000;"><%= vo.getDongNm() %>&nbsp;</td>
							<td align="left" valign="top">호명칭</td>
							<td><%= ho %></td>
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
										<td colspan="5" height="30" class="line_border_2">전유구분</td>
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
					 						
					 						String MAIN_ATCH_GB_CD = ""; //구분
					 						String FLR_NO_NM = "";
					 						String STRCT_CD = "";
					 						String MAIN_PURPS_CD = "";
					 						String AREA = "";
					 						
					 						if(jcnt < j_cnt){
					 							MAIN_ATCH_GB_CD = vo2.getDjyflroulnList().get(jcnt).getMainAtchGbCdNm();
					 							FLR_NO_NM = vo2.getDjyflroulnList().get(jcnt).getFlrNoNm();
					 							STRCT_CD = vo2.getDjyflroulnList().get(jcnt).getStrctCdNm();
					 							MAIN_PURPS_CD = vo2.getDjyflroulnList().get(jcnt).getMainPurpsCdNm();
					 							AREA =  vo2.getDjyflroulnList().get(jcnt).getArea();
					 							jcnt++;
					 						}
					 						
					 						String style_b = "";
					 						
					 						if(j_list_cnt2 != (j+1)){
					 							style_b = "line_border_2";
					 						}else{
					 							style_b = "line_border_1";
					 						}
				 					%>
									<tr align="center">
										<td height="30" class="line_border_2"><%=MAIN_ATCH_GB_CD %>&nbsp;</td>
										<td class="line_border_2"><%=FLR_NO_NM %>&nbsp;</td>
										<td class="line_border_2"><%=STRCT_CD %>&nbsp;</td>
										<td class="line_border_2"><%=MAIN_PURPS_CD %>&nbsp;</td>
										<td class="line_border_2"><%=AREA %>&nbsp;</td>
									</tr>
									<%
					 					}
									%>
									<tr align="center">
										<td height="30" colspan="5" class="line_border_2" style="text-decoration: line-through;">공용부분</td>
									</tr>
									<tr align="center">
										<td height="30" class="line_border_2" style="text-decoration: line-through;">구분</td>
										<td class="line_border_2" style="text-decoration: line-through;">층별</td>
										<td class="line_border_2" style="text-decoration: line-through;">구조</td>
										<td class="line_border_2" style="text-decoration: line-through;">용도</td>
										<td class="line_border_2" style="text-decoration: line-through;">면적(㎡)</td>
									</tr>
									<tr align="center">
										<td height="30" class="line_border_2"></td>
										<td class="line_border_2">&nbsp;</td>
										<td class="line_border_2">&nbsp;</td>
										<td class="line_border_2">&nbsp;</td>
										<td class="line_border_2">&nbsp;</td>
									</tr>
									<tr align="center">
										<td height="30" class="line_border_2"></td>
										<td class="line_border_2">&nbsp;</td>
										<td class="line_border_2">&nbsp;</td>
										<td class="line_border_2">&nbsp;</td>
										<td class="line_border_2">&nbsp;</td>
									</tr>
									<tr align="center">
										<td height="30" class="line_border_2"></td>
										<td class="line_border_2">&nbsp;</td>
										<td class="line_border_2">&nbsp;</td>
										<td class="line_border_2">&nbsp;</td>
										<td class="line_border_2">&nbsp;</td>
									</tr>
									<tr align="center">
										<td height="30" class="line_border_2"></td>
										<td class="line_border_2">&nbsp;</td>
										<td class="line_border_2">&nbsp;</td>
										<td class="line_border_2">&nbsp;</td>
										<td class="line_border_2">&nbsp;</td>
									</tr>
									<tr align="center">
										<td height="30" class="line_border_2"></td>
										<td class="line_border_2">&nbsp;</td>
										<td class="line_border_2">&nbsp;</td>
										<td class="line_border_2">&nbsp;</td>
										<td class="line_border_2">&nbsp;</td>
									</tr>
									<tr align="center">
										<td height="30" class="line_border_3"></td>
										<td class="line_border_3">&nbsp;</td>
										<td class="line_border_3">&nbsp;</td>
										<td class="line_border_3">&nbsp;</td>
										<td class="line_border_3">&nbsp;</td>
									</tr>
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
					 					for(int j = 0 ; j < s_list_cnt2 ; j++){
					 						
					 						String CHANG_CAUS_DAY = ""; //변동일자
					 						String CHANG_CAUS_CD = ""; //변동원인
					 						String OWNSH_QUOTA = ""; //소유권지분
					 						String NM = ""; //성명
					 						String ADDR_NM = ""; //주소
					 						String JMNO = ""; //주민번호
					 						
					 						if(scnt < s_cnt){
					 							CHANG_CAUS_DAY = vo2.getDjyownrList().get(scnt).getChangCausDay();
					 							CHANG_CAUS_CD = vo2.getDjyownrList().get(scnt).getChangCausCdNm();
					 							OWNSH_QUOTA = vo2.getDjyownrList().get(scnt).getJibunDesc();
					 							NM = vo2.getDjyownrList().get(scnt).getNm();
					 							ADDR_NM = vo2.getDjyownrList().get(scnt).getAddr();
					 							JMNO = vo2.getDjyownrList().get(scnt).getRegNo();
		
					 							scnt++;
					 						}
					 						
					 						String style_1 = "";
					 						String style_2 = "";
					 						
					 						if(s_list_cnt2 != (j+1)){
					 							style_1 = "line_border_2";
					 							style_2 = "line_border_1";
					 						}else{
					 							style_1 = "line_border_3";
					 							style_2 = "";
					 						}
					 						
				 					%>	
									<tr align="center">
										<td height="30" class="<%=style_1%>"><%=NM %>&nbsp;</td>
										<td colspan="2" rowspan="2" class="<%=style_1%>"><%= ADDR_NM %>&nbsp;</td>
										<td rowspan="2" class="<%=style_1%>"><%= OWNSH_QUOTA%>&nbsp;</td>
										<td class="<%=style_2%>"><%=CHANG_CAUS_DAY %>&nbsp;</td>
									</tr>
									<tr align="center">
										<td height="30" class="<%=style_1%>"><%=JMNO %>&nbsp;</td>
										<td class="<%=style_2%>"><%=CHANG_CAUS_CD %>&nbsp;</td>
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

<!-- ////////////////// 마지막장 ////////////////// -->
 
<div class="page_layout">
	<div class="page_ctt page_break">
		  <table border="0" cellpadding="0" cellspacing="0" width="100%" id="wapp_table"> 
			<tr>
				<td align="center" height="35">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="90%" align="center" height="35"></td>
							<td width="10%">장번호 : 2-1</td>
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
						<tr align="center">
							<td colspan="2" width="50%"  height="30" class="line_border_2" style="text-decoration: line-through;">변동사항</td>
							<td colspan="2" width="50%" class="line_border_1" style="text-decoration: line-through;">건축물현황도</td>
						</tr>
						<tr align="center">
							<td  height="30" class="line_border_2" style="text-decoration: line-through;">변동일자</td>
							<td class="line_border_2" style="text-decoration: line-through;">변동내용및 원인</td>
							<td colspan="2" rowspan="4" class="line_border_1"></td>
						</tr>
						<tr>
							<td height="400" class="line_border_2" valign="top" align="center"></td>
							<td class="line_border_2" valign="top"></td>
						</tr>
						<tr align="center">
							<td colspan="2" height="30" class="line_border_2" style="text-decoration: line-through;">그 밖의 기재사항</td>
						</tr>
						<tr align="center">
							<td colspan="2" rowspan="2" height="70" class="line_border_2"></td>
						</tr>
						<tr align="center">
							<td class="line_border_2" style="text-decoration: line-through;">축척 </td>
							<td class="line_border_1" style="text-decoration: line-through;">도면작성자&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(서명 또는 인)</td>
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