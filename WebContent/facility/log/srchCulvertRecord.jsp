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

	String mngcde = Code.getCulvertMNGPositionPrint(ftridn);


	ArrayList<srchReservoirRecord> list = Code.getSrchFacilityCONS_YMD(ftridn);
	ArrayList<srchReservoirRecord> F_list = Code.getSrchFacilityAg_parc_dt(ftridn);
	ArrayList<srchReservoirRecord> U_list = Code.getSrchFacilityAg_rep_dt(ftridn);
	ArrayList<srchReservoirRecord> Img_list = Code.getSrchFacilityImages(ftridn);
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
	background: url(dajang_bg.gif) no-repeat;
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
							style="font-size: 25px;">집수암거관리대장</b></td>
						<td width="10%" align="right"><input type="submit" id="submit" value="인쇄" onclick="printOK();"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center" height="40">
				<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border">
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
						<td colspan="4" width="15%" align="center" class="line_border_2"><%=Code.getSrchCulvertFTR_NM(ftridn)%></td>
						<td width="10%" align="left" valign="top" class="line_border_1">&nbsp;소재지</td>
						<td width="18%" align="left" valign="bottom" class="line_border_2"><%=Code.getSrchCulvertJUSO(ftridn)%></td>
						<td width="10%" align="left" valign="top" class="line_border_1">&nbsp;관리자</td>
						<td width="10%" align="center" valign="bottom"
							class="line_border_2"><%=Code.getSrchCulvertMNG_NAM(ftridn)%>
						</td>
						<td width="10%" align="left" valign="top" class="line_border_1">&nbsp;관리자연락처</td>
						<td width="20%" align="center" valign="bottom"
							class="line_border_right"><%=Code.getSrchCulvertTEL_NUM(ftridn)%>
						</td>
					</tr>
						<tr>
					
						<td colspan="4" width="10%" height="30" align="left" valign="top" class="line_border_1">&nbsp;착공일자</td>
						<td  width="20%" align="center" valign="bottom"
							class="line_border_2"><%=Code.getSrchCulvertBEG_YMD(ftridn)%>
						</td>
						<td  width="10%" align="left" valign="top" class="line_border_1">&nbsp;준공일자</td>
						<td  width="10%" align="center" valign="bottom"
							class="line_border_2"><%=Code.getSrchCulvertFNS_YMD(ftridn)%>
						</td>
						<td colspan="2" width="10%" align="left" valign="top" class="line_border_1">&nbsp;수원공구분</td>
						<td colspan="2" width="20%" align="center" valign="bottom"
							class="line_border_right"><%=Code.getSrchCulvertITW_CDE(ftridn)%>
						</td>
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
			<td rowspan="2" width="20%" height="30" align="center"
				class="line_border_2">&nbsp;집수암거 제원</td>
		<td width="9%" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;관경(mm)</td>
			<td colspan="4" width="15%" align="center" class="line_border_2"><%=Code.getSrchCulvertPIP_DIP(ftridn) + " mm"%>
			</td>
			<td colspan="2" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;매설심도(m)</td>
			<td width="15%" align="center" class="line_border_2"><%=Code.getSrchCulvertPIP_DEP(ftridn)  + "m"%></td>
			<td colspan="2" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;매설길이(m)</td>
			<td width="15%" align="center" class="line_border_right"><%=Code.getSrchCulvertPIP_LEN(ftridn) + " m"%></td>
		</tr>
		
	
			<tr>
				
		<td width="9%" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;자연수위(EL.m)</td>
			<td colspan="4" width="15%" align="center" class="line_border_2"><%=Code.getSrchCulvertNAT_WAL(ftridn)%>
			</td>
			<td colspan="2" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;안정수위(EL.m)</td>
			<td width="15%" align="center" class="line_border_2"><%=Code.getSrchCulvertSAF_WAL(ftridn)%></td>
			<td colspan="2" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;수혜면적(ha)</td>
			<td width="15%" align="center" class="line_border_right"><%=Code.getSrchCulvertPRS_ARA(ftridn) + " ha"%></td>
			
			
		</tr>
		
	
		<tr>
			<td height="20"></td>
		</tr>
	</table>
<!-- 이미지 -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
				<td colspan="2" width="30%" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;이미지</td>
				</tr>
				<tr class="line_border_5" style="width: 100%">
						 <%
							if (Img_list.size() != 0) {
								for (int i = 0; i < Img_list.size(); i++) {
						%>

						<td class="line_border_5" width="200px" align="center" height="300px"><img
							alt="error" src="/ygsimg/<%=Img_list.get(i).FILE_PATH%>"
							height="400px" width="480px" align="center"></td>
						<td  class="line_border_5" width="10">&nbsp;</td>
						<%
							}
							}else{
						%>
						<td class="line_border_5" height="400px" width="480px" align="center">이미지가 없습니다</td>
						<td class="line_border_5" height="400px" width="480px" align="center">이미지가 없습니다</td>
						<%
							}
						%> 
					</tr>
				</table>
<table border="0" cellpadding="0" cellspacing="0" width="auto">
					<tr>
							<td height="20"></td>
					</tr>
				</table>

	</div>
	</div>
	
			
	<!-- 설치비 -->
	<div class="page_layout">
	<div class="page_ctt page_break">
	<table border="0" cellpadding="0" cellspacing="0" width="100%"
		class="line_border">
		<tr>
			<td colspan="2" width="30%" height="30" align="left" valign="top"
				class="line_border_1">&nbsp;시공자</td>
			<td colspan="2" width="20%" align="center" class="line_border_2"><%=Code.getSrchFacilityOPR_NAM(ftridn)%></td>
			<td colspan="2" width="30%" align="left" valign="top"
				class="line_border_1">&nbsp;도급자</td>
			<td colspan="2" width="20%" align="left" valign="bottom"
				class="line_border_right"><%=Code.getSrchFacilityGCN_NAM(ftridn)%></td>
		</tr>
		<tr>
			<td rowspan="2" width="20%" height="30" align="center"
				class="line_border_2">&nbsp;설치비(천원)</td>
			<td width="10%" height="30" align="center" class="line_border_2">&nbsp;국비보조</td>
			<td width="10%" height="30" align="center" class="line_border_2">&nbsp;융자</td>
			<td width="10%" height="30" align="center" class="line_border_2">&nbsp;시도군비</td>
			<td width="10%" height="30" align="center" class="line_border_2">&nbsp;자부담</td>
			<td width="10%" height="30" align="center" class="line_border_2">&nbsp;기타</td>
			<td width="10%" height="30" align="center" class="line_border_right">&nbsp;합계</td>
		</tr>
		<tr>
			<td width="10%" height="30" align="center" class="line_border_2">&nbsp;<%=Code.getSrchFacilityNAT_AMT(ftridn)%></td>
			<td width="10%" height="30" align="center" class="line_border_2">&nbsp;<%=Code.getSrchFacilityBND_AMT(ftridn)%></td>
			<td width="10%" height="30" align="center" class="line_border_2">&nbsp;<%=Code.getSrchFacilityCIT_AMT(ftridn)%></td>
			<td width="10%" height="30" align="center" class="line_border_2">&nbsp;<%=Code.getSrchFacilitySEL_AMT(ftridn)%></td>
			<td width="10%" height="30" align="center" class="line_border_2">&nbsp;<%=Code.getSrchFacilityCET_AMT(ftridn)%></td>
			<td width="10%" height="30" align="center" class="line_border_right">&nbsp;<%=Code.getSrchFacilityTCT_AMT(ftridn)%></td>
		</tr>
		<tr>
			<td height="20"></td>
		</tr>
	</table>

	<!-- 공사관리 제원별 -->
	<table border="0" cellpadding="0" cellspacing="0" width="100%"
		class="line_border">

		<tr>
			<%
				if (list.size() != 0) {
			%>
			<td rowspan="<%=list.size() + 1%>" width="20%" height="30"
				align="center" class="line_border_2">&nbsp;제원별</td>
			<%
				} else {
			%>
			<td rowspan="2" width="20%" height="30" align="center"
				class="line_border_2">&nbsp;제원별</td>
			<%
				}
			%>

			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;년도</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;공사비</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;국비</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;지방비</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;자부담</td>
			<td width="13%" height="30" align="center" class="line_border_right">&nbsp;융자</td>
		</tr>
		<%
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=list.get(i).CONS_YMD%></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=list.get(i).CONS_TOT%></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=list.get(i).NAT_AMT%></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=list.get(i).CIT_AMT%></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=list.get(i).EXP_AMT%></td>
			<td width="13%" height="30" align="center" class="line_border_right">&nbsp;<%=list.get(i).BND_AMT%></td>
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
			<td width="13%" height="30" align="center" class="line_border_right">&nbsp;</td>
		</tr>
		<%
			}
		%>
		<tr>
			<td height="10"></td>
		</tr>
	</table>


	<!-- 공사관리 지출내역 -->
	<table border="0" cellpadding="0" cellspacing="0" width="100%"
		class="line_border">

		<tr>
			<%
				if (list.size() != 0) {
			%>
			<td rowspan="<%=list.size() + 1%>" width="20%" height="30"
				align="center" class="line_border_2">&nbsp;지출내역</td>
			<%
				} else {
			%>
			<td rowspan="2" width="20%" height="30" align="center"
				class="line_border_2">&nbsp;지출내역</td>
			<%
				}
			%>

			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;순공사비</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;자재비</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;관리비기타</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;주요공사내역</td>
			<td width="13%" height="30" align="center" class="line_border_right">&nbsp;기타</td>
		</tr>
		<%
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=list.get(i).DPC_AMT%></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=list.get(i).DGV_AMT%></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=list.get(i).DET_AMT%></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=list.get(i).CONS_DES%></td>
			<td width="13%" height="30" align="center" class="line_border_right">&nbsp;<%=list.get(i).ETC%></td>
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
			<td width="13%" height="30" align="center" class="line_border_right">&nbsp;</td>
		</tr>
		<%
			}
		%>
		<tr>
			<td height="10"></td>
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

			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;필지면적</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;편입면적</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;소유자</td>
			<td width="13%" height="30" align="center" class="line_border_right">&nbsp;기타</td>
		</tr>
		<%
			if (F_list.size() != 0) {
				for (int i = 0; i < F_list.size(); i++) {
		%>
		<tr>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=F_list.get(i).LAND_AREA%></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=F_list.get(i).TRA_AREA + " ㎡ " %></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=F_list.get(i).OWNR_NM%></td>
			<td width="13%" height="30" align="center" class="line_border_right">&nbsp;<%=F_list.get(i).F_ETC%></td>
		</tr>
		<%
			}
			} else {
		%>
		<tr>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;</td>
			<td width="13%" height="30" align="center" class="line_border_right">&nbsp;</td>
		</tr>
		<%
			}
		%>
		<tr>
			<td height="20"></td>
		</tr>
	</table>

	<!-- 유지보수 -->
	<table border="0" cellpadding="0" cellspacing="0" width="100%"
		class="line_border">

		<tr>
			<%
				if (U_list.size() != 0) {
			%>
			<td rowspan="<%=U_list.size() + 1%>" width="20%" height="30"
				align="center" class="line_border_2">&nbsp;유지보수</td>
			<%
				} else {
			%>
			<td rowspan="2" width="20%" height="30" align="center"
				class="line_border_2">&nbsp;유지보수</td>
			<%
				}
			%>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;보수일자</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;보수내역</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;시공자명</td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;공사비</td>
			<td width="13%" height="30" align="center" class="line_border_right">&nbsp;기타</td>
		</tr>
		<%
			if (U_list.size() != 0) {
				for (int i = 0; i < U_list.size(); i++) {
		%>
		<tr>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=U_list.get(i).REP_YMD%></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=U_list.get(i).REP_DES%></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=U_list.get(i).U_OPR_NAM%></td>
			<td width="13%" height="30" align="center" class="line_border_2">&nbsp;<%=U_list.get(i).REP_AMT%></td>
			<td width="13%" height="30" align="center" class="line_border_right">&nbsp;<%=U_list.get(i).U_ETC%></td>
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
			<td width="13%" height="30" align="center" class="line_border_right">&nbsp;</td>
		</tr>
		<%
			}
		%>
		<tr>
			<td height="20"></td>
		</tr>
	</table>
	

	
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td height="60" align="center"><span style="font-size: 14px;"><b>내부열람용으로만
						사용 하실수 있습니다.</b></span><br /> 출력일 : <%= Utils.formatTxtYMD(Utils.getStrSec()).substring(0, 8)%></td>
		</tr>
		<tr>
			<td height="10" class="line_border_6" align="right"></td>
		</tr>
	</table>
	</div>
	</div>
	</div>
	</div>
</body>
</html>