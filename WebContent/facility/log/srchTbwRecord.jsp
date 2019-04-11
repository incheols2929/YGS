<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="geomex.svc.handler.Code"%>
<%@page import="geomex.utils.Utils"%>
<%@ page import="java.net.URLDecoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="geomex.pkg.srch.srchReservoirRecord"%>
<%
	request.getParameter("UTF-8");
	String pnu = request.getParameter("pnu");
	String ftridn = request.getParameter("ftr_idn");
	String user_name = request.getParameter("user_name");
	String mngcde = Code.getTbwMNGPositionPrint(ftridn);
   // String gbn = "FTR004"; //시설물 구분 : 관정
    String tableName="";
    ftridn = "468702016001036"; //테스트 코드
    String gbn = "FTR001"; //테스트 코드
    
    if(!gbn.equals("")||!gbn.equals(null)){
     if(gbn.equals("FTR001")){
   	  tableName = "ag_reservoir_as"; //저수지
     }else if(gbn.equals("FTR002")){
   	  tableName = "ag_drain_ps";//배수장
     }else if(gbn.equals("FTR003")){
   	  tableName = "ag_pump_ps";//양수장
     }else if(gbn.equals("FTR004")){
   	  tableName = "ag_tbw_ps";//관정
     }else if(gbn.equals("FTR005")){
   	  tableName = "ag_culvert_ps";//집수암거
     }else if(gbn.equals("FTR006")){
   	  tableName = "";//농로
     }else if(gbn.equals("FTR007")){
   	  tableName = "";//집수정
     }else if(gbn.equals("FTR008")){
   	  tableName = "ag_seawall_ps";//방조제
     }else if(gbn.equals("FTR009")){
   	  tableName = "ag_cwip_as";//취입보
     }else if(gbn.equals("FTR010")){
   	  tableName = "ag_basin_as";//저류지
     }
    }

	ArrayList<srchReservoirRecord> list = Code.getSrchFacilityCONS_YMD(ftridn);
	ArrayList<srchReservoirRecord> U_list = Code.getSrchFacilityAg_rep_dt(ftridn); //유지보수
	ArrayList<srchReservoirRecord> LandAreaList =Code.getSrchFacilityAg_parc_dt(ftridn,tableName,gbn);
	ArrayList<srchReservoirRecord> Img_list = Code.getSrchFacilityImages(ftridn);
	//ArrayList<srchReservoirRecord> joosang_list = Code.getSrchTbwL_VAL(ftridn);
	//ArrayList<srchReservoirRecord> ag_cons_dt = Code.getSrchFacilityAg_Cons_dt(ftridn);
	 
	
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
<title>:: 시설물 상세정보 ::</title>
<script type="text/javascript" src="../script/jquery-1.6.2.js"></script>
<script type="text/javascript" src="../script/jquery.layout-latest.js"></script>
<script type="text/javascript" src="../script/jquery-ui-latest.js"></script>
<script type="text/javascript" src="../log/js/print.js"></script>
<style>
@page {
	size: landscape;
}

@page { @bottom-right { content:Pagecounter(page);
	
}

}
body {
	font-size: 12px;
	font-family: NanumGothic;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: scroll;
}

.line_border {
	width: 100%;
	border-top: 2px solid #000000;
	border-bottom: 2px solid #000000;
}

.line_border_1 {
	border-bottom: 1px solid #000000;
}

.line_border_2 {
	border-right: 1px solid #000000;
	border-bottom: 1px solid #000000;
}

.line_border_right {
	border-bottom: 1px solid #000000;
}

.line_border_left {
	border-left: 1px solid #000000;
	border-bottom: 1px solid #000000;
}

.line_border_3 {
	border-top: 2px solid #000000;
	border-right: 1px solid #000000;
	border-bottom: 0px solid #000000;
}

.line_border_4 {
	border-top: 2px solid #000000;
	border-left: 0px solid #000000;
	border-bottom: 0px solid #000000;
}

.line_border_5 {
	border-top: 2px solid #000000;
	border-bottom: 1px solid #000000;
}

.line_border_6 {
	border-bottom: 2px solid #000000;
}

.line_border_7 {
	border-top: 2px solid #000000;
}


.line_border_8 {
	border-right: 1px solid #000000;
}


.line_border_8 {
	border-right: 1px solid #000000;
}

#wapp_table {
	width: 100%;
	background-position: center;
}
#submit  
{         
    background-color: #ffb94b;  
    background-image: -webkit-gradient(linear, left top, left bottom, from(#fddb6f), to(#ffb94b));  
    background-image: -webkit-linear-gradient(top, #fddb6f, #ffb94b);  
   background-image: -moz-linear-gradient(top, #fddb6f, #ffb94b);  
    background-image: -ms-linear-gradient(top, #fddb6f, #ffb94b);  
    background-image: -o-linear-gradient(top, #fddb6f, #ffb94b);  
    background-image: linear-gradient(top, #fddb6f, #ffb94b);  
      
    -moz-border-radius: 3px;  
    -webkit-border-radius: 3px;  
    border-radius: 3px;  
      
    text-shadow: 0 1px 0 rgba(255,255,255,0.5);  
      
     -moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.3) inset;  
     -webkit-box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.3) inset;  
     box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.3) inset;      
      
    border-width: 1px;  
    border-style: solid;  
    border-color: #d69e31 #e3a037 #d5982d #e3a037;  
  
    float: left;  
    height: 30px;  
    padding: 0;  
    width: 100px;  
    cursor: pointer;  
    font: bold 15px Arial, Helvetica;  
    color: #8f5a0a;  
}  
  
#submit:hover,#submit:focus  
{         
    background-color: #fddb6f;  
    background-image: -webkit-gradient(linear, left top, left bottom, from(#ffb94b), to(#fddb6f));  
    background-image: -webkit-linear-gradient(top, #ffb94b, #fddb6f);  
    background-image: -moz-linear-gradient(top, #ffb94b, #fddb6f);  
    background-image: -ms-linear-gradient(top, #ffb94b, #fddb6f);  
    background-image: -o-linear-gradient(top, #ffb94b, #fddb6f);  
    background-image: linear-gradient(top, #ffb94b, #fddb6f);  
}     
  
#submit:active  
{         
    outline: none;  
     
     -moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;  
     -webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;  
     box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;          
}  
  
#submit::-moz-focus-inner  
{  
  border: none;  
}  

.joosangdo{border-collapse: collapse; width:45%; height:40%; margin-top:24px;}
.joosangdo td{border: 1px solid black; width:50px; height:50px;}
.joosangdo tg{border: 1px solid black; width:50px; height:30px;}
.joosangdo tr{border: 1px solid black;}
/* .joosangdo th, td{border: 1px solid black;} */

</style>
<link rel="stylesheet" type="text/css" href="../css/htmlPrint.css"/>
<script type="text/javascript">
	var initBody;
	var U_list = <%=U_list.size()%>;
    var ftride = <%= ftridn %>;
    var gbn = '<%= gbn %>';
	
	function beforePrint(){
		initBody = document.body.innerHTML;
		document.body.innerHTML = printForm.innerHTML;
	}
	
	function afterPrint(){ //인쇄가 끝난 후 실행되는 내용
		document.body.innerHTML = initBody; //body 안의 내용을 원래대로 되돌린다
	}

	function printOK(){
		 alert("인쇄하시려면 기본설정에서 가로로 인쇄해주세요!");
		 window.onbeforeprint = beforePrint;
		 window.onafterprint = afterPrint;
		 window.print();
		 window.close();
	}
	<%-- var pageAdd = <%= %>; --%>
	$(document).ready(function(){
		if(U_list > 18){
			$('#last_ment').hide();	
		}
		
	//	LandInfoList(ftride,gbn);//필지정보를 가져온다.
	//	MainTenanceList(ftride,gbn); //유지보수 정보를 가져온다.
	//	InspectionInfoList(ftride,gbn); // 점검내역정보를 가져온다.
	});

</script>
</head>
<body>
	<!-- 전체 틀  -->
	<!-- 상단  -->
<div class="print_bg">
	<div class="page_body">
	
	<div class="page_layout">
	<div class="page_ctt page_break">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" id="wapp_table">
		<tr>
			<td align="center" height="35">
				<table border="0" cellpadding="0" cellspacing="0" width="auto">
					<tr>
						<td width="90%" align="center" height="35"><b
							style="font-size: 25px;">관정관리대장</b></td>
						<td width="10%"><input type="submit" id="submit" value="인쇄" onclick="printOK();"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center" height="40">
				<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border">
					<tr>
						<td width="15%" height="45" align="left" valign="top">관리번호</td>
						<td width="20%" style="border-right: 1px solid #000000;"><%=ftridn%></td>
						<td width="15%" align="left" valign="top">접수번호</td>
						<td width="20%"style="border-right: 1px solid #000000;"><%=Utils.getStrSec()%></td>
						<td width="15%" align="left" valign="top">관리기관</td>
						<td width="25%"><%=mngcde%></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="10"></td>
		</tr>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%"
					class="line_border">
					<tr>
						<td width="6%" height="40" align="left" valign="top"
							class="line_border_1">&nbsp;시설명</td>
						<td colspan="4" width="15%" align="center" class="line_border_2"><%=Code.getSrchTbwFTR_NM(ftridn)%></td>
						<td width="10%" align="left" valign="top" class="line_border_1">&nbsp;소재지</td>
						<td width="30%" align="left" valign="center" class="line_border_2"><%=Code.getSrchTbwJUSO(ftridn)%></td>
						<td width="10%" align="left" valign="top" class="line_border_1">&nbsp;관리자</td>
						<td width="10%" align="center" valign="center" class="line_border_2"><%=Code.getSrchTbwMNG_NAM(ftridn)%>
						</td>
						<td width="10%" align="left" valign="top" class="line_border_1">&nbsp;관리자연락처</td>
						<td width="20%" align="center" valign="center" class="line_border_right"><%=Code.getSrchTbwTEL_NUM(ftridn)%>
						</td>
						
					</tr>
						<tr>
						<td width="6%" height="40" align="left" valign="top"
							class="line_border_1">&nbsp;관리상황</td>
						<td colspan="4" width="15%" align="center" class="line_border_2"><%=Code.getSrchTbwTWM_CDE(ftridn)%></td>
						<td width="10%" align="left" valign="top" class="line_border_1">&nbsp;착공일자</td>
						<td width="30%" align="left" valign="center" class="line_border_2"><%=Code.getSrchTbwBEG_YMD(ftridn)%></td>
						<td width="10%" align="left" valign="top" class="line_border_1">&nbsp;준공일자</td>
						<td width="10%" align="center" valign="center" class="line_border_2"><%=Code.getSrchTbwFNS_YMD(ftridn)%>
						</td>
						<td width="10%" align="left" valign="top" class="line_border_1">&nbsp;설치용도</td>
						<td width="20%" align="center" valign="center"
							class="line_border_right"><%=Code.getSrchTbwTWP_CDE(ftridn)%>
						</td>
						<tr>
						<td width="6%" height="40" align="left" valign="top"
							class="line_border_1">&nbsp;시설유형</td>
						<td colspan="4" width="15%" align="center" class="line_border_2"><%=Code.getSrchTbwTWT_CDE(ftridn)%></td>
						<td width="10%" align="left" valign="top" class="line_border_1">&nbsp;생활용수(호)</td>
						<td width="30%" align="left" valign="center" class="line_border_2"><%=Code.getSrchTbwLIF_WAL(ftridn)%></td>
						<td width="10%" align="left" valign="top" class="line_border_1">&nbsp;급수인구(명)</td>
						<td width="10%" align="center" valign="center"
							class="line_border_2"><%=Code.getSrchTbwPEO_NUM(ftridn)%>
						</td>
						<td width="10%" align="left" valign="top" class="line_border_1">&nbsp;몽리면적(ha)</td>
						<td width="20%" align="center" valign="center"
							class="line_border_right"><%=Code.getSrchTbwDEN_ARA(ftridn)%>
						</td>
						
					</tr>
				</table>
				<!-- 구분 / 시설내용 -->
				<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border_right">
				  <tr>
				    <td width="19.3%" height="45" align="center" class="line_border_2">&nbsp;구분</td>
				    <td width="" height="45" align="center" class="line_border_1">&nbsp;시설내용</td>
				  </tr>
			    </table>
			    <!-- 착정 및 이용시설 내용 -->
			    <table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border_right">
			     <tr>
			       <td rowspan="5" width="20%" height="30" align="center" class="line_border_2">&nbsp;착정 및 이용시설 내용</td>
			       <td rowspan="2" width="10%" height="60" align="center" class="line_border_2">&nbsp;구경</td>
			       <td height="30" align="left" valign="top" class="line_border_1">&nbsp;착정(mm)</td>
		           <!-- 착정값  -->
		           <td align="center" class="line_border_2"><%= Code.getSrchTbwDIG_CAL(ftridn) %></td>
		           <td rowspan="2" colspan="2" height="30" align="left" valign="top" class="line_border_1">&nbsp;심도(m)</td>
		           <!-- 심도값  -->
		           <td rowspan="2" width="15%" align="center" class="line_border_2"><%= Code.getSrchTbwPIP_DEP(ftridn) %></td>
		           <td rowspan="2" colspan="2" height="30" align="left" valign="top" class="line_border_1">&nbsp;채수량(㎥/일)</td>
		           <td rowspan="2" width="15%" align="center" class="line_border_right"><%=Code.getSrchTbwWTS_CAR(ftridn)%></td>
			     </tr>
			     <tr>
			       <td height="30" align="left" valign="top" class="line_border_1">&nbsp;우물(mm)</td>
			       <!-- 우물 -->
			       <td align="center" class="line_border_2"><%= Code.getSrchTbwWEL_CAL(ftridn) %></td>
			     </tr>
			     
			     <tr>
			      <td rowspan="2" width="7%" height="60" align="center" class="line_border_2">&nbsp;전동기</td>
			      <td height="30" align="left" valign="top" class="line_border_1">&nbsp;일반</td>
			      <td align="center" class="line_border_2"><%=Code.getSrchTbwREG_MOT(ftridn)%></td>
			      <td rowspan="2" width="7%" height="60" align="center" class="line_border_2">&nbsp;원동기</td>
			      <td height="30" align="left" valign="top" class="line_border_1">&nbsp;엔진</td>
			      <td align="center" class="line_border_2"><%=Code.getSrchTbwENG_MOR(ftridn)%></td>
			      <td rowspan="2" colspan="2" width="10%" height="30" align="left" valign="top" class="line_border_1">&nbsp;전기인입거리(km)</td>
			      <td rowspan="2" width="15%" align="center" class="line_border_right"><%=Code.getSrchTbwELE_DTN(ftridn) + "km"%></td>
			     </tr>
			     <tr>
			      <td height="30" align="left" valign="top" class="line_border_1">&nbsp;수중</td>
			      <td align="center" class="line_border_2"><%=Code.getSrchTbwUND_MOT(ftridn)%> </td>
			      <td height="30" align="left" width="5%" valign="top" class="line_border_1">&nbsp;양수기</td>
			      <td align="center" class="line_border_2"><%=Code.getSrchTbwWAT_MOR(ftridn)%></td>
			     </tr>
			     <tr>
			       <td colspan="2" width="9%" height="40" align="left" valign="top" class="line_border_1">&nbsp;송수거리(km)</td>
			       <td width="15%" align="center" class="line_border_2"><%=Code.getSrchTbwSUP_DTN(ftridn)%></td>
			       <td colspan="2" height="32" align="left" valign="top" class="line_border_1">&nbsp;오염방지시설</td>
			       <td width="15%" align="center" class="line_border_2"><%=Code.getSrchTbwPOL_FAC(ftridn)%></td>
			       <td colspan="2" height="32" align="left" valign="top" class="line_border_1">&nbsp;</td>
			       <td width="15%" align="center" class="line_border_right">&nbsp;</td>
			     </tr>
			     
			      <tr>
			       <td rowspan="4" width="20%" height="30" align="center" class="line_border_2">&nbsp;시설주요내용</td>
			       <td colspan="2" width="9%" height="40" align="left" valign="top" class="line_border_1">&nbsp;자연수위(m)</td>
		           <td width="15%" align="center" class="line_border_2"><%=Code.getSrchTbwNAT_WAL(ftridn)%></td>
		           <td colspan="2" height="40" align="left" valign="top" class="line_border_1">&nbsp;안정수위(m)</td>
			       <td width="15%" align="center" class="line_border_2"><%=Code.getSrchTbwSAF_WAL(ftridn)%></td>
			       <td colspan="2" height="40" align="left" valign="top" class="line_border_1">&nbsp;우물자재PIPE(m)</td>
			       <td width="15%" align="center" class="line_border_right"><%=Code.getSrchTbwWEL_WAL(ftridn)%></td>
			      </tr>
			     
			      <tr>
			       <td colspan="2" rowspan="3"  width="9%" height="40" align="left" valign="top" class="line_border_1">&nbsp;유공관(m)</td>
			       <td rowspan="3" width="15%" align="center" class="line_border_2"><%=Code.getSrchTbwPFE_PMP(ftridn)%></td>
			       <td rowspan="3" colspan="2" height="40" align="left" valign="top" class="line_border_1">&nbsp;기타사항</td>
			       <td rowspan="3" width="15%" align="center" class="line_border_2"><%=Code.getSrchTbwETC(ftridn)%></td>
			       <td rowspan="3" height="90" align="center" class="line_border_2">&nbsp;준공검사</td>
			       <td height="30" align="left" valign="top" class="line_border_1">&nbsp;일자</td>
			       <td align="center" class="line_border_right"><%=Code.getSrchTbwCMP_YMD(ftridn)%></td>	
				  </tr>
				 
				  <tr>
				   	<td height="30" align="left" valign="top" class="line_border_1">&nbsp;직급</td>
			        <td align="center" class="line_border_right"><%=Code.getSrchTbwLNK_NM(ftridn)%></td>
				  </tr> 
				  
				  <tr>
			       <td height="30" align="left" valign="top" class="line_border_1">&nbsp;성명</td>
			       <td align="center" class="line_border_right"><%=Code.getSrchTbwCMP_NM(ftridn)%></td>
		          </tr>
			    <!-- 여기까지 착정 및 이용시설 내용  / 시설 주요내용  -->

			    <!-- 설치비 제원별  -->
			    <tr>
			     <td rowspan="2" width="20%" height="60" align="center"class="line_border_2">&nbsp;설치비<br>제원별</td>
			     <td colspan="2" width="9%" height="30" align="left" valign="top"class="line_border_1">&nbsp;국비보조</td>
			     <td align="center" class="line_border_2"><%=Code.getSrchTbwNAT_AMT(ftridn)%></td>
			     <td width="11.5%" colspan="2" height="30" align="left" valign="top"class="line_border_1">&nbsp;지방비</td>
			     <td align="center" class="line_border_2"><%=Code.getSrchTbwCIT_AMT(ftridn)%></td>
			     <td width="15%" colspan="2" height="30" align="left" valign="top"class="line_border_1">&nbsp;융자</td>
			     <td align="center" class="line_border_right"><%=Code.getSrchTbwBND_AMT(ftridn)%></td>
			    </tr>
			    <tr>
			     <td colspan="2" width="9%" height="30" align="left" valign="top"class="line_border_1">&nbsp;자부담등</td>
			     <td width="15%" align="center" class="line_border_2"><%=Code.getSrchTbwSEL_AMT(ftridn)%></td>
			     <td colspan="2" height="30" align="left" valign="top"class="line_border_1">&nbsp;계</td>
			     <td width="15%" align="center" class="line_border_2"><%=Code.getSrchTbwTCT_AMT(ftridn)%></td> 
			     <td colspan="2" height="30" align="left" valign="top"class="line_border_1">&nbsp;</td>
			     <td width="15%" align="center" class="line_border_right">&nbsp;</td>
		        </tr>
			    <!-- 여기까지  설치비 제원별  -->
			 </table>
		</tr>
	</table>
   </div>
  </div>
  <!-- 새로운 페이지 -->
  <!-- 이미지 -->
  <div class="page_layout">
	<div class="page_ctt page_break">
	  <table border="0" cellpadding="0" cellspacing="0" width="100%">
	   <tr>
         <td colspan="2" width="30%" height="30" align="left" valign="top" class="line_border_1">&nbsp;이미지</td>
	   </tr>
	   <tr class="line_border_5" style="width: 100%">
	   <%
	    if (Img_list.size() != 0) {
			for (int i = 0; i < Img_list.size(); i++) {
	   %>
	    <td class="line_border_5" width="200px" align="center" height="300px">
		<img alt="error" src="/ygsimg/<%=Img_list.get(i).FILE_PATH%>" height="300px" width="480px" align="center"></td> 
		<td  class="line_border_5" width="10">&nbsp;</td>
	   
	    <%
			}
		 }else{
		%>
		<td class="line_border_5" height="300px" width="480px" align="center">이미지가 없습니다</td>
		<td class="line_border_5" height="300px" width="480px" align="center">이미지가 없습니다</td>
		<%
		 }
		%>
	   </tr>   
	  </table>
	  
	  <!-- 지질주상도이미지를 추가함 -->
	  <table border="0" cellpadding="0" cellspacing="0" width="50%" style="float:left; margin-top:15px;">
	    <tr>
	 	  <td height="20"><img alt="error" src="../img/comums2.png" height="300px" width="480px" align="center"></td>
	 	</tr>
	  </table>
	  
	  <!-- 주상도 상세제원 -->
	  <table class="joosangdo" border="0" cellpadding="0" cellspacing="0">
	   <colgroup>
		<col width="25%">
		<col width="25%">
		<col width="25%">
		<col width="25%">
	   </colgroup>
   	   <tr> <!-- 지질주상도이미지를 추가함 -->
		<th  align="center">토층</th>
		<th  align="center">깊이</th>
		<th  align="center">설치도</th>
		<th  align="center">길이</th>
	   </tr>
	   <tr>
		<td align="center"><%=Code.getSrchJoosangL1(ftridn)%></td>
		<td align="center"><%=Code.getSrchJoosangL1_v(ftridn) %></td>
		<td align="center">그라우팅</td>
		<td align="center"><%=Code.getSrchJoosangGrouting(ftridn) %></td>
	   </tr>
	   <tr>
		<td align="center"><%=Code.getSrchJoosangL2(ftridn) %></td>
		<td align="center"><%=Code.getSrchJoosangL2_v(ftridn) %></td>
		<td align="center">케이싱</td>
		<td align="center"><%=Code.getSrchJoosangCasing(ftridn) %></td>
	   </tr>
	   <tr>
		<td align="center"><%=Code.getSrchJoosangL3(ftridn) %></td>
		<td align="center"><%=Code.getSrchJoosangL3_v(ftridn) %></td>
		<td align="center"></td>
		<td align="center"></td>
	   </tr>
	   <tr>
		<td align="center"><%=Code.getSrchJoosangL4(ftridn) %></td>
		<td align="center"><%=Code.getSrchJoosangL4_v(ftridn) %></td>
		<td align="center"></td>
		<td align="center"></td>
	   </tr>  
	  </table>
	</div>
   </div>
   
  <!-- 필지정보를 가져온다. -->
  <div class="page_layout"> 
     <div class="page_ctt page_break" id="LandInformattionWrap"> 
		<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border">
		 <tr> 
		  <td colspan="6" width="23%" height="45" align="center" class="line_border_right" style="font-weight:bold;">&nbsp;필지정보</td> 
		 </tr> 
		 <tr> 
		  <td width="20%" height="35" align="center" class="line_border_2" style="font-weight:bold;">&nbsp;지번</td> 
		  <td width="5%" height="35" align="center" class="line_border_2" style="font-weight:bold;">&nbsp;지목</td> 
		  <td width="13%" height="35" align="center" class="line_border_2" style="font-weight:bold;">&nbsp;필지면적</td> 
		  <td width="13%" height="35" align="center" class="line_border_2" style="font-weight:bold;">&nbsp;편입면적</td> 
	      <td width="13%" height="35" align="center" class="line_border_2" style="font-weight:bold;">&nbsp;소유자</td> 
		  <td width="13%" height="35" align="center" class="line_border_right" style="font-weight:bold;">&nbsp;기타</td> 
	     </tr>
	    <!-- LandAreaList -->
	    <%
	    if(LandAreaList.size() != 0 ){
	     for(int i = 0; i < 17; i++){
	    	 System.out.println("1.");
	    %>
	      <tr> 
	             <td width="20%" height="35" align="center" class="line_border_2">&nbsp;<%= LandAreaList.get(i).getJuso() %></td> 
	             <td width="6%" height="35" align="center" class="line_border_2">&nbsp;<%= LandAreaList.get(i).getJimok() %></td> 
	             <td width="13%" height="35" align="center" class="line_border_2">&nbsp;<%= LandAreaList.get(i).getLandarea() %></td>
	             <td width="13%" height="35" align="center" class="line_border_2">&nbsp;<%= LandAreaList.get(i).getTraarea() %></td>
	             <td width="13%" height="35" align="center" class="line_border_2">&nbsp;<%= LandAreaList.get(i).getOwnrnm() %></td>
	             <td width="13%" height="35" align="center" class="line_border_right">&nbsp;<%= LandAreaList.get(i).getEtc() %></td>
	      </tr>
        <%
	     }
	     for(int i = 17; i < LandAreaList.size(); i++){
	    	 System.out.println("2.");
	    %>
	      <tr> 
	             <td width="20%" height="35" align="center" class="line_border_2">&nbsp;<%= LandAreaList.get(i).getJuso() %></td> 
	             <td width="6%" height="35" align="center" class="line_border_2">&nbsp;<%= LandAreaList.get(i).getJimok() %></td> 
	             <td width="13%" height="35" align="center" class="line_border_2">&nbsp;<%= LandAreaList.get(i).getLandarea() %></td>
	             <td width="13%" height="35" align="center" class="line_border_2">&nbsp;<%= LandAreaList.get(i).getTraarea() %></td>
	             <td width="13%" height="35" align="center" class="line_border_2">&nbsp;<%= LandAreaList.get(i).getOwnrnm() %></td>
	             <td width="13%" height="35" align="center" class="line_border_right">&nbsp;<%= LandAreaList.get(i).getEtc() %></td>
	      </tr>
        <%
	     }
	    }
        %>
		</table>
	</div>
  </div>
	     
	     
    
    
    
  
 </div>
</div>
</body>
</html>