<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	String mngcde = Code.getSeawallMNGPositionPrint(ftridn);


	ArrayList<srchReservoirRecord> list = Code.getSrchFacilityCONS_YMD(ftridn);
	ArrayList<srchReservoirRecord> F_list = Code.getSrchFacilityAg_parc_dt(ftridn);
	ArrayList<srchReservoirRecord> U_list = Code.getSrchFacilityAg_rep_dt(ftridn);
	ArrayList<srchReservoirRecord> Img_list = Code.getSrchFacilityImages(ftridn);
	ArrayList<srchReservoirRecord> seawall_img_list = Code.getSrchSeawallImagChk(ftridn); //방조재이미지 
	ArrayList<srchReservoirRecord> ag_cons_dt = Code.getSrchFacilityAg_Cons_dt(ftridn);
	ArrayList<srchReservoirRecord> seawall_etc_list = Code.getSrchSeawallImagesEtc(ftridn); //방조재기타이미지 
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
<title>:: 시설물 상세정보 ::</title>
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
</style>
<link rel="stylesheet" type="text/css" href="../css/htmlPrint.css"/>
<script type="text/javascript" src="../script/jquery-1.6.2.js"></script>
<script type="text/javascript" src="../script/jquery.layout-latest.js"></script>
<script type="text/javascript" src="../script/jquery-ui-latest.js"></script>
<script type="text/javascript">
	var initBody;
	
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
	var U_list = <%=U_list.size()%>;

	$(document).ready(function(){
		if(U_list > 18){
			$('#last_ment').hide();	
		}
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
	<table border="0" cellpadding="0" cellspacing="0" width="1150"
		id="wapp_table">
		<tr>
			<td align="center" height="35">
				<table border="0" cellpadding="0" cellspacing="0" width="auto">
					<tr>
						<td width="90%" align="center" height="35"><b
							style="font-size: 25px;">방조제관리대장</b></td>
						<td width="10%" align="right"><input type="submit" id="submit" value="인쇄" onclick="printOK();"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center" height="40">
				<table border="0" cellpadding="0" cellspacing="0" width="100%"
					class="line_border">
					<tr>
						<td width="15%" height="40" align="left" valign="top">관리번호</td>
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
						<td width="6%" height="30" align="left" valign="top"
							class="line_border_1">&nbsp;시설명</td>
						<td colspan="4" width="15%" align="center" class="line_border_2"><%=Code.getSrchSeawallFTR_NM(ftridn)%></td>
						<td width="10%" align="left" valign="top" class="line_border_1">&nbsp;소재지</td>
						<td width="18%" align="left" valign="bottom" class="line_border_2"><%=Code.getSrchSeawallJUSO(ftridn)%></td>
						<td width="10%" align="left" valign="top" class="line_border_1">&nbsp;관리자</td>
						<td width="10%" align="center" valign="bottom"
							class="line_border_2"><%=Code.getSrchSeawallMNG_NAM(ftridn)%>
						</td>
						<td width="10%" align="left" valign="top" class="line_border_1">&nbsp;관리자연락처</td>
						<td width="20%" align="center" valign="bottom"
							class="line_border_right"><%=Code.getSrchSeawallTEL_NUM(ftridn)%>
						</td>
					</tr>
						<tr>
					
						<td colspan="6" width="10%" height="30" align="left" valign="top" class="line_border_1">&nbsp;착공일자</td>
						<td  width="20%" align="center" valign="bottom"
							class="line_border_2"><%=Code.getSrchSeawallBEG_YMD(ftridn)%>
						</td>
						<td colspan="2" width="10%" align="left" valign="top" class="line_border_1">&nbsp;준공일자</td>
						<td colspan="2" width="20%" align="center" valign="bottom"
							class="line_border_right"><%=Code.getSrchSeawallFNS_YMD(ftridn)%>
						</td>
						</tr>
				</table>
			</td>
		</tr>
	</table>
	<table border="0" cellpadding="0" cellspacing="0" width="100%"
		class="line_border5">
		<tr>
			<td width="20%" height="35" align="center" class="line_border_3">&nbsp;구분</td>
			<td height="35" align="center" class="line_border_4">&nbsp;시설내용</td>
		</tr>
	</table>
	<table border="0" cellpadding="0" cellspacing="0" width="100%"
		class="line_border">
		<tr>
			<td rowspan="4" width="20%" height="30" align="center"
				class="line_border_2">&nbsp;시설제원</td>
		<td width="9%" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;구역면적(ha)</td>
			<td colspan="4" width="15%" align="center" class="line_border_2"><%=Code.getSrchSeawallZON_ARA(ftridn) + " ha"%>
			</td>
			<td colspan="2" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;몽리면적(ha)</td>
			<td width="15%" align="center" class="line_border_2"><%=Code.getSrchSeawallDEN_ARA(ftridn)  + "ha"%></td>
			<td colspan="2" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;방조제 연장(m)</td>
			<td width="15%" align="center" class="line_border_right"><%=Code.getSrchSeawallWAL_LEN(ftridn) + " m"%></td>
		</tr>
		
			<tr>
		<td width="9%" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;배수갑문 개소</td>
			<td colspan="4" width="15%" align="center" class="line_border_2"><%=Code.getSrchSeawallGAT_NUM(ftridn)%>
			</td>
			<td colspan="2" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;배수갑문 연수</td>
			<td width="15%" align="center" class="line_border_2"><%=Code.getSrchSeawallGAT_EACH(ftridn)%></td>
			<td colspan="2" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;포용조수량(천㎥)</td>
			<td width="15%" align="center" class="line_border_right"><%=Code.getSrchSeawallEBB_FLW(ftridn) + " 천㎥"%></td>
		</tr>
			<tr>
		<td width="9%" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;대안거리(㎞)</td>
			<td colspan="4" width="15%" align="center" class="line_border_2"><%=Code.getSrchSeawallEFEC_LEN(ftridn) + " ㎞"%>
			</td>
			<td colspan="2" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;제고(최고)(m)</td>
			<td width="15%" align="center" class="line_border_2"><%=Code.getSrchSeawallTOT_JEA(ftridn)  + "m"%></td>
			<td colspan="2" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;제고(평고)(m)</td>
			<td width="15%" align="center" class="line_border_right"><%=Code.getSrchSeawallAVG_JEA(ftridn) + " m"%></td>
		</tr>
		
					<tr>
		<td width="9%" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;여유고(m)</td>
			<td colspan="4" width="15%" align="center" class="line_border_2"><%=Code.getSrchSeawallSPR_JEA(ftridn) + " m"%>
			</td>
			<td colspan="2" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;편입연도</td>
			<td width="15%" align="center" class="line_border_2"><%=Code.getSrchSeawallTRN_YMD(ftridn)%></td>
			<td colspan="2" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;</td>
			<td width="15%" align="center" class="line_border_right">&nbsp;</td>
		</tr>
	</table>
	
	
	<!-- 설치비 -->
	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border">
		<tr>
			<td rowspan="2" width="20%" height="30" align="center"class="line_border_2">&nbsp;설치비<br>제원별</td>
			<td colspan="2" width="9%" height="40" align="left" valign="top"class="line_border_1">&nbsp;국비보조</td>
			<td align="center" class="line_border_2"><%=Code.getSrchSeaWallNAT_AMT(ftridn)%></td>
			<td width="11.5%" colspan="2" height="40" align="left" valign="top"class="line_border_1">&nbsp;지방비</td>
			<td align="center" class="line_border_2"><%=Code.getSrchSeaWallCIT_AMT(ftridn)%></td>
			<td width="15%" colspan="2" height="40" align="left" valign="top"class="line_border_1">&nbsp;융자</td>
			<td align="center" class="line_border_right"><%=Code.getSrchSeaWallBND_AMT(ftridn)%></td>
		</tr>
		
		<tr>
			<td colspan="2" width="9%" height="40" align="left" valign="top"class="line_border_1">&nbsp;자부담등</td>
			<td width="15%" align="center" class="line_border_2"><%=Code.getSrchSeaWallSEL_AMT(ftridn)%></td>
			<td colspan="2" height="40" align="left" valign="top"class="line_border_1">&nbsp;계</td>
			<td width="15%" align="center" class="line_border_2"><%=Code.getSrchSeaWallALL_AMT(ftridn)%></td> 
			<td colspan="2" height="40" align="left" valign="top"class="line_border_1">&nbsp;</td>
			<td width="15%" align="center" class="line_border_right">&nbsp;</td>
		</tr>
		
		<tr>
			<td rowspan="2" width="20%" height="30" align="center"class="line_border_2">&nbsp;설치비<br>지출내역</td>
			<td colspan="2" width="9%" height="40" align="left" valign="top"class="line_border_1">&nbsp;순공사비</td>
			<td align="center" class="line_border_2">&nbsp;<%=Code.getSrchSeaWallDPC_AMT(ftridn) %></td>
			<td  width="11.5%" colspan="2" height="40" align="left" valign="top"class="line_border_1">&nbsp;자재비</td>
			<td align="center" class="line_border_2">&nbsp;<%=Code.getSrchSeaWallDGV_AMT(ftridn) %></td>
			<td width="15%" colspan="2" height="40" align="left" valign="top"class="line_border_1">&nbsp;용지매수비</td>
			<td align="center" class="line_border_right"><%=Code.getSrchSeaWallLAND_AMT(ftridn) %></td> 
			
		</tr>
		<tr>
			<td colspan="2" width="9%" height="40" align="left" valign="top"class="line_border_1">&nbsp;설계공감비</td>
			<td width="15%" align="center" class="line_border_2"><%=Code.getSrchSeaWallDSN_AMT(ftridn) %></td>
			<td colspan="2" height="40" align="left" valign="top"class="line_border_1">&nbsp;관리비 기타</td>
			<td width="15%" align="center" class="line_border_2"><%=Code.getSrchSeaWallDET_AMT(ftridn) %></td>
			<td colspan="2" height="40" align="left" valign="top"class="line_border_1">&nbsp;계</td>
			<td width="15%" align="center" class="line_border_right"><%=Code.getSrchSeaWallTCO_AMT(ftridn) %></td>
		</tr>
	</table>
	
	<!-- 필지정보 -->
	<table border="0" cellpadding="0" cellspacing="0" width="100%"
		class="line_border">

		<tr>
			<%
				if (F_list.size() != 0) {
			%>
			<td rowspan="<%=F_list.size() + 1%>" width="20%" height="30"
				align="center" class="line_border_2">&nbsp;필지정보</td>
			<%
				} else {
			%>
			<td rowspan="2" width="20%" height="30" align="center"
				class="line_border_2">&nbsp;필지정보</td>
			<%
				}
			%>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;지번</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;지목</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;필지면적</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;편입면적</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;소유자</td>
			<td width="14%" height="30" align="center" class="line_border_right">&nbsp;기타</td>
<!--		<td width="13%" height="30" align="center" class="line_border_2">&nbsp;지번</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;지목</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;필지면적</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;편입면적</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;소유자</td>
			<td width="13%" height="30" align="center" class="line_border_right">&nbsp;기타</td> -->
		</tr>
		<%
			if (F_list.size() != 0) {
				for (int i = 0; i < F_list.size(); i++) {
					//DB에 본번 부번택스트에 0010 형태의 0을 없애기위해 int형으로 형변환하였음
					int bon = Integer.parseInt(F_list.get(i).bon);
					int bu = Integer.parseInt(F_list.get(i).bu);
		%>
		<tr><!-- 20180710추가 -->
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=bon +"-" + bu %> </td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=F_list.get(i).jimok %> </td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=F_list.get(i).LAND_AREA%></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=F_list.get(i).TRA_AREA  + " ㎡ "%></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=F_list.get(i).OWNR_NM%></td>
			<td width="14%" height="30" align="center" class="line_border_right">&nbsp;<%=F_list.get(i).F_ETC%></td>
		</tr>
		<%
			}
			} else {
		%>
		<tr>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;</td>
			<td width="14%" height="30" align="center" class="line_border_right">&nbsp;</td>
		</tr>
		<%
			}
		%>
	</table>

	
	</div>
</div>
	
<div class="page_layout">
	<div class="page_ctt page_break">
<!-- 이미지 -->
					<table border="0" cellpadding="0" cellspacing="0" width="1030px">
						<tr>
							<td colspan="2" width="30px" height="20" align="left" valign="top" class="line_border_1">&nbsp;이미지</td>
						</tr>
							
						<tr class="line_border_5">
<%-- 					
						 <%
							if (seawall_img_list.size() != 0) {
								for (int i = 0; i < seawall_img_list.size(); i++) {
						%>

						<td class="line_border_5" width="200px" align="center" height="300px">
							<img alt="error" src="/ygsimg/<%=seawall_img_list.get(i).FILE_PATH%>" height="380px" width="480px" align="center"></td>
						<td class="line_border_5" width="10">&nbsp;</td>
						<%
								}
							}else{
						%>
						<td class="line_border_5" height="380px" width="480px" align="center">이미지가 없습니다</td>
						<td class="line_border_5" height="380px" width="480px" align="center">이미지가 없습니다</td>
						<%
							}
						%> 
						 --%>
							<% if(seawall_img_list.size() != 0){  %>
								
								<!-- 방조재 현황이미지 -->
								<td class="line_border_5" width="200px" align="left" height="300px">
									<img alt="error" src="/ygsimg/<%=Code.getSrchSeawallImages(ftridn)%>" height="380px" width="480px" align="center">
								</td>
								
								<td class="line_border_5" width="10">&nbsp;</td> <!-- 중앙여백 -->
								
								<td class="line_border_5" width="200px" align="right" height="300px">
									<img alt="error" src="/ygsimg/<%=Code.getSrchSeawallImagesPoint(ftridn)%>" height="380px" width="480px" align="center">
								</td>
								
							<% }else{ %>
								<td class="line_border_5" height="380px" width="100%" align="center">이미지가 없습니다</td>
							<% } %>
						</tr>
				</table>
				
				<!-- 배수갑문 -->
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="float:left; margin-top:15px;">
					<tr>
						<% if(seawall_etc_list.size() != 0) {%>
						
							<%for(int i=0; i<seawall_etc_list.size(); i++){ %>
							<%int seawallsize = seawall_etc_list.size(); 
								seawallsize = seawallsize*110; //2개일때 width값이 220이 이상적이고 3개일때 330이 이상적이다 
							%>
								<td height="20">
									<img alt="error" src="/ygsimg/<%=seawall_etc_list.get(i).FILE_PATH%>" height="300px" width="<%=seawallsize %>" align="center">
								</td>
							<% } %>
							
						<% } else { %>
							<td class="line_border_5" height="380px" width="100%" align="center">이미지가 없습니다</td>
						<% } %>
					</tr>
				</table>
	</div>
</div>
				

<div class="page_layout">
	<div class="page_ctt page_break">


	
	
	<!-- 유지보수 -->
	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border">

		<tr>
<%-- 			<%
				if (ag_cons_dt.size() != 0) {
			%>
			<td rowspan="<%=ag_cons_dt.size() + 1%>" width="20%" height="30"
				align="center" class="line_border_2">&nbsp;유지보수</td>
			<%
				} else {
			%>
			<td rowspan="2" width="20%" height="30" align="center"
				class="line_border_2">&nbsp;유지보수</td>
			<%
				}
			%> --%>
			<td rowspan="21" width="20%" height="30" align="center" class="line_border_2">&nbsp;유지보수</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;보수일자</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;보수내역</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;시공자명</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;공사비</td>
			<td width="13%" height="30" align="center" class="line_border_right">&nbsp;기타</td>
		</tr>
		
		<% if(ag_cons_dt.size() != 0){
			for (int i = 0; i < ag_cons_dt.size(); i++) {
			
		%>
		<tr>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=ag_cons_dt.get(i).cons_ymd %></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=ag_cons_dt.get(i).cons_des %></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=ag_cons_dt.get(i).opr_nm %></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=ag_cons_dt.get(i).cons_tot %></td>
			<td width="13%" height="30" align="center" class="line_border_right">&nbsp;<%=ag_cons_dt.get(i).cons_etc %></td>
		</tr>
		
		<%}
		}else{
			%>
			<tr>
				<td width="13%" height="30" align="center" class="line_border_right" colspan="5">&nbsp;데이터가 없습니다.</td>
			</tr>
			<%	
			}
		%>
	</table>
	</div>
	</div> 
	
	
<div class="page_layout">
	<div class="page_ctt page_break">
		
		<% int l_size = U_list.size();	
			int l_size_over = l_size-18;
		%>
		<!-- 점검내역 -->
		<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border" id="cons_table">

		<tbody>
		<tr>
			<td rowspan="<%=l_size+2 %>" width="20%" height="30" align="center" class="line_border_2">&nbsp;점검내역</td>
			<td rowspan="2" width="13%" height="30" align="center" class="line_border_2">&nbsp;점검일</td>
			<td colspan="2" width="26%" height="30" align="center" class="line_border_2">&nbsp;점검자</td>
			<td rowspan="2" width="26%" height="30" align="center" class="line_border_right">&nbsp;점검결과</td>
		</tr>
		<tr>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp; 직급</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp; 성명</td>
		</tr>		
		
		<%
			if (U_list.size() != 0) {

				
				if(U_list.size() > 18){ //점검내역의 항목이 18개 이상일 시 페이지 추가됨
					
					for(int i = 0; i < 18; i++){
						%>
						<tr>
							<!-- <td width="13%" height="30" align="center" class="line_border_2">&nbsp;</td> -->
							<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=U_list.get(i).REP_YMD%></td>
							<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=U_list.get(i).OPR_LANKS%></td>
							<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=U_list.get(i).U_OPR_NAM%></td>
							<td width="13%" height="30" align="center" class="line_border_right"><%=U_list.get(i).REP_DES%></td>
						</tr>
						
						<%
					} //for end
					
					%>
						<script>

							//생성될 페이지를 $div에 담음 
							var $div = $('<div class="page_layout"><div class="page_ctt page_break" id="cons_over"></div></div>');

							//var div = document.createElement('div');
							
							//표 타이틀 html
							var str = '';
							str+= '	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border" id="cons_table"> ' ;
							str+= '		<tbody id="cons_body"> ';
							str+= '		<tr> '   ;
							str+= '			<td rowspan="<%=l_size-16 %>" width="20%" height="30" align="center" class="line_border_2">&nbsp;점검내역</td>   ' ;
							str+= '			<td rowspan="2" width="13%" height="30" align="center" class="line_border_2">&nbsp;점검일</td> ' ;
							str+= '			<td colspan="2" width="26%" height="30" align="center" class="line_border_2">&nbsp;점검자</td> ' ;
							str+= '			<td rowspan="2" width="26%" height="30" align="center" class="line_border_right">&nbsp;점검결과</td>' ;
							str+= '		</tr> ' ;
							str+= '		<tr> ' ;
							str+= '			<td width="13%" height="30" align="center" class="line_border_2">&nbsp; 직급</td> '   ;
							str+= '			<td width="13%" height="30" align="center" class="line_border_2">&nbsp; 성명</td> '   ;
							str+= '</tr>'   ;

							str+= '<table border="0" cellpadding="0" cellspacing="0" width="100%">';
							str+= '	<tr> ';
							str+= '		<td height="60" align="center"><span style="font-size: 14px;"><b>내부열람용으로만';
							str+= '				사용 하실수 있습니다.</b></span><br /> 출력일 : <%= Utils.formatTxtYMD(Utils.getStrSec()).substring(0, 8)%></td>';
							str+= '	</tr>';
							str+= '	<tr>';
							str+= '		<td height="10" class="line_border_6" align="right"></td>';
							str+= '	</tr>';
							str+= '</table>';							

							$('.page_body').append($div);
							$('#cons_over').html(str);

							
						</script>
					<%
					for(int i=18; i < U_list.size(); i++){
					%>
					
						
						
							<script>
							var str2 = '	<tr> ';
								str2 += '	<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=U_list.get(i).REP_YMD%></td>';
								str2 += '		<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=U_list.get(i).OPR_LANKS%></td>';
								str2 += '		<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=U_list.get(i).U_OPR_NAM%></td>';
								str2 += '		<td width="13%" height="30" align="center" class="line_border_right"><%=U_list.get(i).REP_DES%></td>;'
								str2 += '	</tr>';
							$('#cons_body').append(str2);
								
							</script>
						<%					
						
					}
					
					
					
				}else{
				
					for (int i = 0; i < U_list.size(); i++) {
			%>
						<tr>
							<!-- <td width="13%" height="30" align="center" class="line_border_2">&nbsp;</td> -->
							<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=U_list.get(i).REP_YMD%></td>
							<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=U_list.get(i).OPR_LANKS%></td>
							<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=U_list.get(i).U_OPR_NAM%></td>
							<td width="13%" height="30" align="center" class="line_border_right"><%=U_list.get(i).REP_DES%></td>
						</tr>
			<%
					} //for end
				} //else end
			} else {
		%>
		<tr>
			<td width="13%" height="30" align="center" class="line_border_right" colspan="5">&nbsp;데이터가 없습니다.</td>
		</tr>
		<%
			}
		%>

		<tr>
			<td height="20"></td>
		</tr>
	</tbody>
	</table>	
	
		<table border="0" cellpadding="0" cellspacing="0" width="100%" id="last_ment">
			<tr>
				<td height="60" align="center"><span style="font-size: 14px;"><b>내부열람용으로만
						사용 하실수 있습니다.</b></span><br /> 출력일 : <%= Utils.formatTxtYMD(Utils.getStrSec()).substring(0, 8)%></td>
			</tr>
			<tr>
				<td height="10" class="line_border_6" align="right"></td>
			</tr>
		</table>
	
	</div>
</div><!-- page layout end -->
			
		</div>
	</div>
</body>
</html>