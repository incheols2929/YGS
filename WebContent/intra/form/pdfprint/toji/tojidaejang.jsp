<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="
org.apache.commons.io.IOUtils,
org.apache.commons.lang3.StringUtils,

java.util.*,

geomex.kras.gmx.KrasGmxConn,
geomex.kras.gmx.KrasGmxConn.SVC,
geomex.kras.gmx.DjUnmarshaller,
geomex.kras.gmx.vo.*,
geomex.utils.*
"
%>
<%
/* 토지대장 */

	request.setCharacterEncoding("UTF-8");
	String pnu = request.getParameter("pnu");
	String user_name = request.getParameter("user_name");
	
	String san = "";
	
	if(pnu.length() == 19){
		san = pnu.substring(10, 11);	
	}
	
	//1 : 토지 , 2 : 산(임야)
	if("1".equals(san)){
		san = "";	
	}else if("2".equals(san)){
		san = "산";
	}else{
		san = "";
	}
	
	KrasGmxConn kgc = new KrasGmxConn();
	DjUnmarshaller djUm = new DjUnmarshaller();
	
	
	// 토지대장
	TojiDaejang2Set vo = (TojiDaejang2Set) djUm.getUnmarshal( TojiDaejang2Set.class, kgc.getData(SVC.GetTojiDaejangPrint2, pnu) );
	
	// 건축물 간략정보
	LandInfoSet vo2 = (LandInfoSet) djUm.getUnmarshal( LandInfoSet.class, kgc.getData(SVC.GetLandInfo, pnu) );
	
	String gounum = pnu.substring(0, 10) + "-" + pnu.substring(10, 15) + "-" + pnu.substring(15, 19);
	String tojiaddr = vo2.getAddr();
	
	
	String JIMK_NM = vo2.getLandInfo().getJimkNm(); //지목
	String AREA = vo2.getLandInfo().getArea(); //면적
	String OWN_GBN_NM = vo2.getLandInfo().getOwnGbnNm();
	String SHAP_NUM = vo2.getLandInfo().getShapNum();
	String LAND_MOVE_WHY_CODE_NM = vo2.getLandInfo().getLandMoveWhyCodeNm();
	String MAP_NO_BONO = vo2.getLandInfo().getMapNoBono(); //도면번호
	String JIBUN = pnu.substring(11, 15) + "-" + pnu.substring(15, 19);
	String SCALE_NM = vo2.getLandInfo().getScaleNm(); //축척
	String SCALE_CODE = vo2.getLandInfo().getScaleCode();
	
	//페이지 계산
	int tot_page_num = 0;
	int page_num = 0;
	
	int landhist_cnt = vo.getLandMovHistList().getLandMovHistList().size(); //토지이동
	int ownerhist_cnt = vo.getOwnHistList().getOwnHistList().size(); //소유권 변동
	int jiga_cnt = vo.getJigaList().getJigaList().size(); // 개별공시지가
	
	int page_list_cnt = 4;
	int total_page = (landhist_cnt > ownerhist_cnt)?((landhist_cnt-1)/page_list_cnt):((ownerhist_cnt-1)/page_list_cnt);

	int land_cnt = 0;
	int owner_cnt = 0;
	 
	if(total_page == 0){
		tot_page_num = 1;
		page_num = 1;
	}else{
	    tot_page_num = total_page;
	}
	
	
	/*
	 * 공시지가 list 중 최근 5개
	 */
	ArrayList<String> JiGaYearList = new  ArrayList<String>();	// 공시지가 기준일
	ArrayList<String> JiGaWonList = new ArrayList<String>();	// 공시지가
	
	int JiGaSize = vo.getJigaList().getJigaList().size();
	
	for ( int i = 0 ; i < 5 ; i++ ) {
		if ( JiGaSize - i > 0 ) {
			JiGaYearList.add(vo.getJigaList().getJigaList().get(i).getBaseYear());
			JiGaWonList.add(vo.getJigaList().getJigaList().get(i).getJiga());
		} else {
			// 5개보다 작으면 앞에 공백을 채운다
			JiGaYearList.add(0, "");
			JiGaWonList.add(0, "");
		}
	}
	Collections.reverse(JiGaYearList);
	Collections.reverse(JiGaWonList);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
<title>:: 토지대장 ::</title>
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
		cursor: default;
	}

	.line_border{
		border-left:2px solid #000000;
		border-right:2px solid #000000;
		border-bottom:2px solid #000000;
	}
	.line_border_1{
		border-right:1px solid #000000;
		border-bottom:1px solid #000000;
	}
	.line_border_2{
		border-bottom:1px solid #000000;
	}
	.line_border_3{
		border-left:2px solid #000000;
		border-right:2px solid #000000;
		border-bottom:2px solid #000000;
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
			<table border="0" cellpadding="0" cellspacing="0" class="line_border" id="wapp_table"> 
			 	<tr align="center">
			 		<td width="70" height="30" class="line_border_4" id="go">고유번호</td>
			 		<td colspan="3" class="line_border_5" align="center" valign="middle"><%=gounum %></td>
			 		<td colspan="3" rowspan="3" width="250" class="line_border_3">
						<%
			 				if("산".equals(san)){
			 			%>	
			 				<b style="font-size: 30px;">임야대장</b>	
			 			<%	
			 				}else{
			 			%>
			 				<b style="font-size: 30px;">토지대장</b>
			 			<%
			 				}
			 			%>
					</td>
			 		<td width="70" class="line_border_4">도면번호</td>
			 		<td width="80" class="line_border_4"><%=MAP_NO_BONO %>&nbsp;</td>
			 		<td width="70" class="line_border_4">발급번호</td>
			 		<td width="150" class="line_border_5"><%=Utils.getStrSec() %></td>
			 	</tr> 
			 	<tr align="center">
			 		<td height="30" class="line_border_1">토지소재</td>
			 		<td colspan="3" class="line_border_2"><%=tojiaddr %>&nbsp;</td>
			 		<td class="line_border_1">장번호</td>
			 		<td class="line_border_1"><%=tot_page_num+"-1"%></td>
			 		<td class="line_border_1">처리시각</td>
			 		<td class="line_border_2"><%= Utils.formatTxtHMS(Utils.getStrSec()) %></td>
			 	</tr>
			 	<tr align="center">
			 		<td height="30" class="line_border_1">지번</td>
			 		<td width="100" class="line_border_1"><%=JIBUN %></td>
			 		<td width="60" class="line_border_1">축척</td>
			 		<td width="100" class="line_border_2"><%=SCALE_NM%></td>
			 		<td class="line_border_1" style="text-decoration: line-through;">비고</td>
			 		<td class="line_border_1">-</td>
			 		<td class="line_border_1">작성자</td>
			 		<td class="line_border_2"><%=user_name%></td>
			 	</tr>
			 	<tr>
			 		<td colspan="11">
			 			<table border="0.5"  cellpadding="0" cellspacing="0" width="100%">
			 				<tr align="center">
					 			<td colspan="3" height="25" class="line_border_1">토지표시</td>
					 			<td colspan="3" class="line_border_2">소유자</td>
					 		</tr>
			 				<tr align="center">
			 					<td rowspan="2" width="60" class="line_border_1">지목</td>
			 					<td rowspan="2" width="80" class="line_border_1">면적(㎡)</td>
			 					<td rowspan="2" width="300" class="line_border_1">사유</td>
			 					<td height="25" width="130" class="line_border_1">변동일자</td>
			 					<td colspan="2" class="line_border_2">주소</td>
			 				</tr>
			 				<tr align="center">
			 					<td height="25" class="line_border_1">변동원인</td>
			 					<td width="300" class="line_border_1">성명 또는 명칭</td>
			 					<td width="170" class="line_border_2">등록번호</td>
			 				</tr>
			 				
			 				<%
								Collections.reverse(vo.getLandMovHistList().getLandMovHistList());
								Collections.reverse(vo.getOwnHistList().getOwnHistList());
								Collections.reverse(vo.getShareInfoList().getShareInfoList());
			 				%>
			 				
			 				<%
			 					for(int i = 0 ; i < page_list_cnt ; i++){
			 						
			 						String JIMOK = "";
			 						String AREA_2 = "";
			 						String LAND_MOVE_WHY = "";
			 						
			 						if(land_cnt < landhist_cnt){
			 							JIMOK = "("+vo.getLandMovHistList().getLandMovHistList().get(land_cnt).getJimkNm1()+")<br/>"+ vo.getLandMovHistList().getLandMovHistList().get(land_cnt).getJimkCode1();
			 							AREA_2 = vo.getLandMovHistList().getLandMovHistList().get(land_cnt).getArea1() + "㎡";
										
			 							LAND_MOVE_WHY = "("+vo.getLandMovHistList().getLandMovHistList().get(land_cnt).getLandMoveWhyCode1()+") "+Utils.formatTxtYMD(vo.getLandMovHistList().getLandMovHistList().get(land_cnt).getLandMoveYmd1());
										LAND_MOVE_WHY += "<br/>"+vo.getLandMovHistList().getLandMovHistList().get(land_cnt).getLandMoveWhyCode();
			 							land_cnt++;
			 						}
			 						
									String OWNR_ADDR = ""; //소유자 주소
									String OWNR_NM = ""; //소유자 명
									String OWNSP_CH_YMD = ""; //변동일자
									String OWNSP_CH_CAU_GBN_NM =""; //변동원인
									String OWNR_REG_SNO = ""; //등록번호
									String OWN_GBN = "";
									
									if(owner_cnt < ownerhist_cnt){
			 							OWNR_ADDR = vo.getOwnHistList().getOwnHistList().get(owner_cnt).getOwnrAddr();
			 							OWNSP_CH_YMD = Utils.formatTxtYMD(vo.getOwnHistList().getOwnHistList().get(owner_cnt).getOwnspChYmd());
			 							OWNSP_CH_CAU_GBN_NM = "("+vo.getOwnHistList().getOwnHistList().get(owner_cnt).getOwnspChCauGbn()+")"+vo.getOwnHistList().getOwnHistList().get(owner_cnt).getOwnspChCauGbnNm();
			 							OWNR_NM = vo.getOwnHistList().getOwnHistList().get(owner_cnt).getOwnrNm();
			 							OWNR_REG_SNO = vo.getOwnHistList().getOwnHistList().get(owner_cnt).getOwnrRegSno();
			 							OWN_GBN  = vo.getOwnHistList().getOwnHistList().get(owner_cnt).getOwnGbn();
			 							owner_cnt++;
			 						}
			 				%>
			 				<tr align="center">
			 					<td rowspan="2" width="60" class="line_border_1"><%=JIMOK%>&nbsp;</td>
			 					<td rowspan="2" width="80" class="line_border_1"><%=AREA_2 %>&nbsp;</td>
			 					<td rowspan="2" width="300" align="left" class="line_border_1" style="padding:0 0 0 2px;"><%= LAND_MOVE_WHY%>&nbsp;</td> 
			 					<td width="130" height="30" class="line_border_1">&nbsp;<%=OWNSP_CH_YMD %>&nbsp;</td>
			 					<td colspan="2" align="left" style="padding:0 0 0 2px;" class="line_border_2"><%= OWNR_ADDR %>&nbsp;</td>
			 				</tr>
			 				<tr align="center">
			 					<td height="30" class="line_border_1"><%=OWNSP_CH_CAU_GBN_NM %>&nbsp;</td>
			 					<td width="300" align="left" style="padding:0 0 0 2px;" class="line_border_1"><%=OWNR_NM %>&nbsp;</td>
			 					<td width="170" class="line_border_2"><%=OWNR_REG_SNO %>&nbsp;</td>
			 				</tr>
			 				<%
			 					}
			 				%>
			 			</table>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td colspan="11">
			 			<table border="0"  cellpadding="0" cellspacing="0" width="100%" height="auto" id="toji">
			 				<tr align="center">
					 			<td width="150" height="35" class="line_border_1">등급수정<br/>년월일</td>
					 			<td width="100" class="line_border_1">&nbsp;</td>
					 			<td width="100" class="line_border_1">&nbsp;</td>
					 			<td width="100" class="line_border_1">&nbsp;</td>
					 			<td width="100" class="line_border_1">&nbsp;</td>
					 			<td width="100" class="line_border_1">&nbsp;</td>
					 			<td width="100" class="line_border_1">&nbsp;</td>
					 			<td width="100" class="line_border_1">&nbsp;</td>
					 			<td width="100" class="line_border_2">&nbsp;</td>
					 		</tr>
					 		<tr align="center">
					 			<td height="35" class="line_border_1">토지등급<br/>(기준수확량등급)</td>
					 			<td class="line_border_1">&nbsp;</td>
					 			<td class="line_border_1">&nbsp;</td>
					 			<td class="line_border_1">&nbsp;</td>
					 			<td class="line_border_1">&nbsp;</td>
					 			<td class="line_border_1">&nbsp;</td>
					 			<td class="line_border_1">&nbsp;</td>
					 			<td class="line_border_1">&nbsp;</td>
					 			<td class="line_border_2">&nbsp;</td>
					 		</tr>
					 		<tr align="center">
					 			<td height="30" class="line_border_1">개별공시지가기준일</td>
					 			<%
					 				for(int i=0; i<5; i++) {
					 			%>
					 			<td class="line_border_1"><%= JiGaYearList.get(i) %>&nbsp;</td>
					 			<%
					 				}
					 			%>
					 			<td class="line_border_1">&nbsp;</td>
					 			<td class="line_border_1">&nbsp;</td>
					 			<td class="line_border_2">&nbsp;</td>
					 		</tr>
					 		<tr align="center">
					 			<td height="30" class="line_border_1">개별공시지가(원/㎡)</td>
					 			<%
					 				for(int i=0; i<5; i++) {
					 			%>
					 			<td class="line_border_1"><%= Utils.getCommaCreate(JiGaWonList.get(i)) %>&nbsp;</td>
					 			<%
					 				}
					 			%>
					 			<td class="line_border_1">&nbsp;</td>
					 			<td class="line_border_1">&nbsp;</td>
					 			<td class="line_border_2">&nbsp;</td>
					 		</tr>
			 			</table>
			 		</td>
			 	</tr>
			 	<tr align="center">
			 		<td colspan="11" height="60" style="line-height: 20px;">
			 		 	<span style="font-size:14px; "><b>내부열람용으로만 사용 하실수 있습니다.</b></span><br/> 
			 		 	출력일 : <%=Utils.formatTxtYMD(Utils.getStrSec()) %>  출력자 : <%=user_name%>
			 		</td>
			 	</tr>
			 	<!-- <tr align="center">
			 		<td colspan="11" height="60">
			 			<div style="margin-left:500px;"><b style="font-size: 20px;"></b></div>
			 		</td>
			 	</tr> -->
			</table>
		</div>
	</div>

<%
 	
 	for(int i = 1 ; i <= total_page ; i++  ){
 	
 %>

<div class="page_layout">
	<div class="page_ctt page_break">
		 <table border="0" cellpadding="0" cellspacing="0" class="line_border" id="wapp_table"> 
		 	<tr align="center">
		 		<td width="70" height="30" class="line_border_4" id="go">고유번호</td>
		 		<td colspan="3" class="line_border_5" align="center" valign="middle"><%=gounum %>&nbsp;</td>
		 		<td colspan="3" rowspan="3" width="250" class="line_border_3"><b style="font-size: 30px;">토지대장</b></td>
		 		<td width="70" class="line_border_4">도면번호</td>
		 		<td width="80" class="line_border_4"><%=MAP_NO_BONO %></td>
		 		<td width="70" class="line_border_4">발급번호</td>
		 		<td width="150" class="line_border_5"><%=Utils.getStrSec() %></td>
		 	</tr> 
		 	<tr align="center">
		 		<td height="30" class="line_border_1">토지소재</td>
		 		<td colspan="3" class="line_border_2"><%=tojiaddr %>&nbsp;</td>
		 		<td class="line_border_1">장번호</td>
		 		<td class="line_border_1"><%=tot_page_num+"-"+(i+1)%></td>
		 		<td class="line_border_1">처리시각</td>
		 		<td class="line_border_2"><%= Utils.formatTxtHMS(Utils.getStrSec()) %></td>
		 	</tr>
		 	<tr align="center">
		 		<td height="30" class="line_border_1">지번</td>
		 		<td width="100" class="line_border_1"><%=JIBUN %></td>
		 		<td width="60" class="line_border_1">축척</td>
		 		<td width="100" class="line_border_2"><%=SCALE_NM%></td>
		 		<td class="line_border_1" style="text-decoration: line-through;">비고</td>
		 		<td class="line_border_1">-</td>
		 		<td class="line_border_1">작성자</td>
		 		<td class="line_border_2">-</td>
		 	</tr>
		 	<tr>
		 		<td colspan="11">
		 			<table border="0.5"  cellpadding="0" cellspacing="0" width="100%">
		 				<tr align="center">
				 			<td colspan="3" height="25" class="line_border_1">토지표시</td>
				 			<td colspan="3" class="line_border_2">소유자</td>
				 		</tr>
		 				<tr align="center">
		 					<td rowspan="2" width="60" class="line_border_1">지목</td>
		 					<td rowspan="2" width="80" class="line_border_1">면적(㎡)</td>
		 					<td rowspan="2" width="300" class="line_border_1">사유</td>
		 					<td height="25" width="130" class="line_border_1">변동일자</td>
		 					<td colspan="2" class="line_border_2">주소</td>
		 				</tr>
		 				<tr align="center">
		 					<td height="25" class="line_border_1">변동원인</td>
		 					<td width="300" class="line_border_1">성명 또는 명칭</td>
		 					<td width="170" class="line_border_2">등록번호</td>
		 				</tr>
		 				
		 				<%
		 					for(int j = 0 ; j < page_list_cnt ; j++){
		 						
		 						String JIMOK = "";
		 						String AREA_2 = "";
		 						String LAND_MOVE_WHY = "";
		
		 						if(land_cnt < landhist_cnt){
									JIMOK = "("+vo.getLandMovHistList().getLandMovHistList().get(land_cnt).getJimkNm1()+")<br/>"+ vo.getLandMovHistList().getLandMovHistList().get(land_cnt).getJimkCode1();
									AREA_2 = vo.getLandMovHistList().getLandMovHistList().get(land_cnt).getArea1() + "㎡";
								
									LAND_MOVE_WHY = "("+vo.getLandMovHistList().getLandMovHistList().get(land_cnt).getLandMoveWhyCode1()+") "+Utils.formatTxtYMD(vo.getLandMovHistList().getLandMovHistList().get(land_cnt).getLandMoveYmd1());
									LAND_MOVE_WHY += "<br/>"+vo.getLandMovHistList().getLandMovHistList().get(land_cnt).getLandMoveWhyCode();
									land_cnt++;
								}
								
								String OWNR_ADDR = ""; //소유자 주소
								String OWNR_NM = ""; //소유자 명
								String OWNSP_CH_YMD = ""; //변동일자
								String OWNSP_CH_CAU_GBN_NM =""; //변동원인
								String OWNR_REG_SNO = ""; //등록번호
								String OWN_GBN = "";
							
								if(owner_cnt < ownerhist_cnt){
									OWNR_ADDR = vo.getOwnHistList().getOwnHistList().get(owner_cnt).getOwnrAddr();
									OWNSP_CH_YMD = Utils.formatTxtYMD(vo.getOwnHistList().getOwnHistList().get(owner_cnt).getOwnspChYmd());
									OWNSP_CH_CAU_GBN_NM = "("+vo.getOwnHistList().getOwnHistList().get(owner_cnt).getOwnspChCauGbn()+")"+vo.getOwnHistList().getOwnHistList().get(owner_cnt).getOwnspChCauGbnNm();
									OWNR_NM = vo.getOwnHistList().getOwnHistList().get(owner_cnt).getOwnGbn();  //2012.03.08 김경호
									OWNR_REG_SNO = vo.getOwnHistList().getOwnHistList().get(owner_cnt).getOwnrRegSno();
									OWN_GBN  = vo.getOwnHistList().getOwnHistList().get(owner_cnt).getOwnGbn();
									owner_cnt++;
								}
		 				%>
		 				<tr align="center">
		 					<td rowspan="2" width="60" class="line_border_1"><%=JIMOK%>&nbsp;</td>
		 					<td rowspan="2" width="80" class="line_border_1"><%=AREA_2 %>&nbsp;</td>
		 					<td rowspan="2" width="300" align="left" class="line_border_1" style="padding:0 0 0 2px;"><%= LAND_MOVE_WHY%>&nbsp;</td> 
		 					<td width="130" height="30" class="line_border_1"><%=OWNSP_CH_YMD %>&nbsp;</td>
		 					<td colspan="2" align="left" style="padding:0 0 0 2px;" class="line_border_2"><%=OWN_GBN %>&nbsp;</td>
		 				</tr>
		 				<tr align="center">
		 					<td height="30" class="line_border_1"><%=OWNSP_CH_CAU_GBN_NM %>&nbsp;</td>
		 					<td width="300" align="left" style="padding:0 0 0 2px;" class="line_border_1"><%=OWNR_ADDR %>&nbsp;</td>
		 					<td width="170" class="line_border_2"><%=OWNR_REG_SNO %>&nbsp;</td>
		 				</tr>
		 				<%
		 					}
		 				%>
		 			</table>
		 		</td>
		 	</tr>
		 	<tr>
		 		<td colspan="11">
		 			<table border="0"  cellpadding="0" cellspacing="0" width="100%" height="auto" id="toji">
		 				<tr align="center">
				 			<td width="150" height="35" class="line_border_1">등급수정<br/>년월일</td>
				 			<td width="100" class="line_border_1">&nbsp;</td>
				 			<td width="100" class="line_border_1">&nbsp;</td>
				 			<td width="100" class="line_border_1">&nbsp;</td>
				 			<td width="100" class="line_border_1">&nbsp;</td>
				 			<td width="100" class="line_border_1">&nbsp;</td>
				 			<td width="100" class="line_border_1">&nbsp;</td>
				 			<td width="100" class="line_border_1">&nbsp;</td>
				 			<td width="100" class="line_border_2">&nbsp;</td>
				 		</tr>
				 		<tr align="center">
				 			<td height="35" class="line_border_1">토지등급<br/>(기준수확량등급)</td>
				 			<td class="line_border_1">&nbsp;</td>
				 			<td class="line_border_1">&nbsp;</td>
				 			<td class="line_border_1">&nbsp;</td>
				 			<td class="line_border_1">&nbsp;</td>
				 			<td class="line_border_1">&nbsp;</td>
				 			<td class="line_border_1">&nbsp;</td>
				 			<td class="line_border_1">&nbsp;</td>
				 			<td class="line_border_2">&nbsp;</td>
				 		</tr>
				 		<tr align="center">
				 			<td height="30" class="line_border_1">개별공시지가기준일</td>
				 			<%
								for(int j=0; j<5; j++) {
				 			%>
				 			<td class="line_border_1"><%=JiGaYearList.get(j) %>&nbsp;</td>
				 			<%
				 				}
				 			%>
				 			<td class="line_border_1">&nbsp;</td>
				 			<td class="line_border_1">&nbsp;</td>
				 			<td class="line_border_2">&nbsp;</td>
				 		</tr>
				 		<tr align="center">
				 			<td height="30" class="line_border_1">개별공시지가(원/㎡)</td>
				 			<%
				 				for(int j=0; j<5; j++) {
				 			%>
				 			<td class="line_border_1"><%=JiGaWonList.get(j) %>&nbsp;</td>
				 			<%
				 				}
				 			%>
				 			<td class="line_border_1">&nbsp;</td>
				 			<td class="line_border_1">&nbsp;</td>
				 			<td class="line_border_2">&nbsp;</td>
				 		</tr>
		 			</table>
		 		</td>
		 	</tr>
		 	<tr align="center">
		 		<td colspan="11" height="60" style="line-height: 20px;">
		 		 	<span style="font-size:14px; "><b>내부열람용으로만 사용 하실수 있습니다.</b></span><br/> 
		 		 	출력일 : <%=Utils.formatTxtYMD(Utils.getStrSec()) %> 출력자 : <%=user_name%>
		 		</td>
		 	</tr>
		 	<!-- <tr align="center">
		 		<td colspan="11" height="60">
		 			<div style="margin-left:500px;"><b style="font-size: 20px;"></b></div>
		 		</td>
		 	</tr> -->
		 </table>
	</div>
</div>
<%
 	}
%>

<!-- //////// 공유지연명부 ///// -->

<%

//공유지 연명부 페이지 계산 

int share_cnt = vo.getShareInfoList().getShareInfoList().size();
int g_page_list_cnt = 10;
int g_total_page = ((share_cnt-1)/g_page_list_cnt);
int g_cnt = 0;

	if(share_cnt != 0){
		
%>

<div class="page_layout">
	<div class="page_ctt page_break">
		<table border="0" cellpadding="0" cellspacing="0" class="line_border" id="wapp_table"> 
		 	<tr align="center">
		 		<td  width="80" height="30" class="line_border_4" id="go">고유번호</td>
		 		<td  width="250" class="line_border_5" align="center"><%=gounum %>&nbsp;</td>
		 		<td colspan="2" width="385" class="line_border_3"><b style="font-size: 30px;">공유지 연명부</b></td>
		 		<td width="80" class="line_border_4">장번호</td>
		 		<td width="250" class="line_border_5"><%="1"%></td>
		 	</tr> 
		 	<tr align="center">
		 		<td height="30"  class="line_border_1">토지소재</td>
		 		<td class="line_border_1"><%=tojiaddr %>&nbsp;</td>
		 		<td class="line_border_1">지번</td>
		 		<td class="line_border_1" width="150"><%=JIBUN %></td>
		 		<td class="line_border_1" style="text-decoration: line-through;">비고</td>
		 		<td class="line_border_2">-</td>
		 	</tr> 
		 	<tr align="center">
		 		<td colspan="6">
		 			<table border="0" cellpadding="0" cellspacing="0" width="100%">
		 				<tr>
		 					<td rowspan="3" width="60" class="line_border_1">순번</td>
		 					<td rowspan="2" width="100" class="line_border_1">변동일자</td>
		 					<td rowspan="3" width="100" class="line_border_1">소유권지분</td>
		 					<td colspan="2" height="23" class="line_border_2">소유자</td>
		 				</tr>
		 				<tr>
		 					<td rowspan="2" class="line_border_1">주소</td>
		 					<td height="23" class="line_border_2">등록번호</td>
		 				</tr>
		 				<tr>
		 					<td height="23" class="line_border_1">변동원인</td>
		 					<td height="23" width="250" class="line_border_2">성명 또는 명칭</td>
		 				</tr>
		 				
		 				<%
		 					for(int i = 0 ; i < g_page_list_cnt ; i++){
		 						
		 						String SHAP_SNO = ""; //공유인 일련번호
		 						String OWNSP_CH_YMD = ""; //변동일자
		 						String OWNSP_COSM = ""; //공유지분
								String OWNR_ADDR = ""; //주소
								String OWNR_REG_NO = ""; //소유자 등록번호
								String OWNSP_CH_CAU_GBN_TXT = ""; //소유권 변동사유합침
								String OWNSP_CH_CAU_GBN = ""; //소유권 변동사유코드
								String OWNSP_CH_CAU_GBN_NM = ""; //소유권 변동사유
								String OWNR_NM = ""; //소유자명
								
		 						if(g_cnt < share_cnt){
		 							SHAP_SNO = vo.getShareInfoList().getShareInfoList().get(g_cnt).getShapSno();
		 							OWNSP_CH_YMD = Utils.formatTxtYMD(vo.getShareInfoList().getShareInfoList().get(g_cnt).getOwnspChYmd());
		 							OWNSP_COSM = vo.getShareInfoList().getShareInfoList().get(g_cnt).getOwnspCosm();
		 							OWNR_ADDR = vo.getShareInfoList().getShareInfoList().get(g_cnt).getOwnrAddr();
		 							OWNR_REG_NO = vo.getShareInfoList().getShareInfoList().get(g_cnt).getOwnrRegNo();
		 							OWNSP_CH_CAU_GBN_TXT = "("+vo.getShareInfoList().getShareInfoList().get(g_cnt).getOwnspChCauGbn()+") ";
		 							OWNSP_CH_CAU_GBN_TXT += vo.getShareInfoList().getShareInfoList().get(g_cnt).getOwnspChCauGbnNm();
		 							OWNR_NM = vo.getShareInfoList().getShareInfoList().get(g_cnt).getOwnrNm();
		 							g_cnt++;
		 						}
		 				%>
		 				<tr>
		 					<td rowspan="2" class="line_border_1"><%=SHAP_SNO %>&nbsp;</td>
		 					<td height="23" class="line_border_1"><%=OWNSP_CH_YMD %>&nbsp;</td>
		 					<td rowspan="2" class="line_border_1"><%=OWNSP_COSM %>&nbsp;</td>
		 					<td rowspan="2" class="line_border_1" align="left"><%=OWNR_ADDR %>&nbsp;</td>
		 					<td height="23" class="line_border_2"><%=OWNR_REG_NO %>&nbsp;</td>
		 				</tr>
		 				<tr>
		 					<td height="23" class="line_border_1"><%=OWNSP_CH_CAU_GBN_TXT %>&nbsp;</td>
		 					<td height="23" class="line_border_2"><%=OWNR_NM %>&nbsp;</td>
		 				</tr>
		 				<%
		 					}
		 				%>
		 			</table>
		 		</td>
		 	</tr> 
		 </table>
		
		 <%
		 	for(int i = 1 ; i <= g_total_page; i++){
		 %>
		 
		 <table border="0" cellpadding="0" cellspacing="0" class="line_border" id="wapp_table"> 
		 	<tr align="center">
		 		<td  width="80" height="30" class="line_border_4" id="go">고유번호</td>
		 		<td  width="250" class="line_border_5" align="center"><%=gounum %>&nbsp;</td>
		 		<td colspan="2" width="385" class="line_border_3"><b style="font-size: 30px;">공유지 연명부</b></td>
		 		<td width="80" class="line_border_4">장번호</td>
		 		<td width="250" class="line_border_5"><%=(i+1)%></td>
		 	</tr> 
		 	<tr align="center">
		 		<td height="30"  class="line_border_1">토지소재</td>
		 		<td class="line_border_1"><%=tojiaddr %>&nbsp;</td>
		 		<td class="line_border_1">지번</td>
		 		<td class="line_border_1" width="150"><%=JIBUN %></td>
		 		<td class="line_border_1" style="text-decoration: line-through;">비고</td>
		 		<td class="line_border_2">-</td>
		 	</tr> 
		 	<tr align="center">
		 		<td colspan="6">
		 			<table border="0" cellpadding="0" cellspacing="0" width="100%">
		 				<tr>
		 					<td rowspan="3" width="60" class="line_border_1">순번</td>
		 					<td rowspan="2" width="100" class="line_border_1">변동일자</td>
		 					<td rowspan="3" width="100" class="line_border_1">소유권지분</td>
		 					<td colspan="2" height="23" class="line_border_2">소유자</td>
		 				</tr>
		 				<tr>
		 					<td rowspan="2" class="line_border_1">주소</td>
		 					<td height="23" class="line_border_2">등록번호</td>
		 				</tr>
		 				<tr>
		 					<td height="23" class="line_border_1">변동원인</td>
		 					<td height="23" width="250" class="line_border_2">성명 또는 명칭</td>
		 				</tr>
		 				
		 				<%
		 					for(int j = 0 ; j < g_page_list_cnt ; j++){
		 						
		 						String SHAP_SNO = ""; //공유인 일련번호
		 						String OWNSP_CH_YMD = ""; //변동일자
		 						String OWNSP_COSM = ""; //공유지분
								String OWNR_ADDR = ""; //주소
								String OWNR_REG_NO = ""; //소유자 등록번호
								String OWNSP_CH_CAU_GBN_TXT = ""; //소유권 변동사유합침
								String OWNSP_CH_CAU_GBN = ""; //소유권 변동사유코드
								String OWNSP_CH_CAU_GBN_NM = ""; //소유권 변동사유
								String OWNR_NM = ""; //소유자명
								
		 						if(g_cnt < share_cnt){
		 							SHAP_SNO = vo.getShareInfoList().getShareInfoList().get(g_cnt).getShapSno();
		 							OWNSP_CH_YMD = Utils.formatTxtYMD(vo.getShareInfoList().getShareInfoList().get(g_cnt).getOwnspChYmd());
		 							OWNSP_COSM = vo.getShareInfoList().getShareInfoList().get(g_cnt).getOwnspCosm();
		 							OWNR_ADDR = vo.getShareInfoList().getShareInfoList().get(g_cnt).getOwnrAddr();
		 							OWNR_REG_NO = vo.getShareInfoList().getShareInfoList().get(g_cnt).getOwnrRegNo();
		 							OWNSP_CH_CAU_GBN_TXT = "("+vo.getShareInfoList().getShareInfoList().get(g_cnt).getOwnspChCauGbn()+") ";
		 							OWNSP_CH_CAU_GBN_TXT += vo.getShareInfoList().getShareInfoList().get(g_cnt).getOwnspChCauGbnNm();
		 							OWNR_NM = vo.getShareInfoList().getShareInfoList().get(g_cnt).getOwnrNm();
		 							g_cnt++;
		 						}
		 				%>
		 				<tr>
		 					<td rowspan="2" class="line_border_1"><%=SHAP_SNO %>&nbsp;</td>
		 					<td height="23" class="line_border_1"><%=OWNSP_CH_YMD %>&nbsp;</td>
		 					<td rowspan="2" class="line_border_1"><%=OWNSP_COSM %>&nbsp;</td>
		 					<td rowspan="2" class="line_border_1" align="left"><%=OWNR_ADDR %>&nbsp;</td>
		 					<td height="23" class="line_border_2"><%=OWNR_REG_NO %>&nbsp;</td>
		 				</tr>
		 				<tr>
		 					<td height="23" class="line_border_1"><%=OWNSP_CH_CAU_GBN_TXT %>&nbsp;</td>
		 					<td height="23" class="line_border_2"><%=OWNR_NM %>&nbsp;</td>
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
 	} //for문
	} //if문
%>
	</div>
</div>

</body>
</html>