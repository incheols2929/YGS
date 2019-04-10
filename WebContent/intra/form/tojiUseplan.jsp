<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="
org.apache.commons.lang3.StringUtils,

geomex.kras.gmx.KrasGmxConn,
geomex.kras.gmx.KrasGmxConn.SVC,
geomex.kras.gmx.DjUnmarshaller,
geomex.kras.gmx.vo.*,

geomex.utils.*,
geomex.svc.handler.Code,
geomex.pkg.usr.UseLogBean,
java.net.URLEncoder
"
%>

<%@ include file="../../ssesion/ssesion.jsp" %>
<%
String pnu = URLEncoder.encode( StringUtils.trimToEmpty(request.getParameter("pnu")) , "UTF-8");

if ( id != null ) {
	
//대장기본정보 권한체크
String auth = Code.getAuthCheckB("FW006", id, session_ugrp_id);
UseLogBean UB = new UseLogBean();
if(!"N".equals(auth)){
	//기능사용이력정보등록
			
	//시스템연계로그정보
	String worknm = "행위제한법령정보조회";
	String org_cd = "42770";
	String sys_cd = "LRIS";
	String link_typ = "WBV";
	String stime = Utils.getStrSec();
	
	KrasGmxConn kgc = new KrasGmxConn();
	DjUnmarshaller djUm = new DjUnmarshaller();
	
	// 토지대장
	TojiDaejang2Set vo = (TojiDaejang2Set) djUm.getUnmarshal( TojiDaejang2Set.class, kgc.getData(SVC.GetTojiDaejangPrint2, pnu) );
	
	// 건축물 간략정보
	LandInfoSet vo2 = (LandInfoSet) djUm.getUnmarshal( LandInfoSet.class, kgc.getData(SVC.GetLandInfo, pnu) );
	
	// 용도지역지구목록
	
	UseZoneList vo3 = (UseZoneList) djUm.getUnmarshal( UseZoneList.class, kgc.getData(SVC.GetUseZoneList, pnu) );
	
	String rslt_cd = ""; 
	String err_desc = ""; 
	String etime = "";
	String logtime = "";
	
	rslt_cd = "S";
	etime = Utils.getStrSec();
	
	
	String JIMK_NM = "";
	String AREA = "";
	String OWN_GBN_NM = "";
	
	JIMK_NM = vo2.getLandInfo().getJimkNm();
	AREA = vo2.getLandInfo().getArea(); 
	OWN_GBN_NM = vo2.getLandInfo().getOwnGbnNm();
%>	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>:: 행위제한법령정보 ::</title>
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css" href="../css/luris.css" />
<link rel="stylesheet" type="text/css" href="../css/toji-form.css" />
<script type="text/javascript" src="../script/jquery-1.6.2.js"></script>
<script type="text/javascript" src="../script/gws.condSrch.js"></script>
<script type="text/javascript" src="../script/gws.pageCtrl.js"></script>
<script type="text/javascript" src="../script/gws.Ctrl.js"></script>
<script type="text/javascript" src="../script/gws.utils.js"></script>
<script type="text/javascript" src="../script/jquery.spin.min.js"></script>
<script type="text/javascript" src="../../geomex/geomex.js"></script>
<script type="text/javascript" src="../../geomex/gmx.Ctrl.js"></script>
<script type="text/javascript">
var initBody;
var SESSION_ID = "<%=id%>";

function setLawTitle(title){
	$("#lawTitle").text("* [강원도 "+$("#pnuSelect option:selected").text()+"] "+title+" 법령정보");
	$("#lawTitle").attr("val", title);
}

function afterPrint(){ //인쇄가 끝난 후 실행되는 내용
	$("body").children().show();
}

function printOK(){
	 window.onbeforeprint = function(){
		 initBody = $("body").html();
		 $("body").children().hide();
		 $("#slide_page_content_3").css("overflow", "hidden");
		 $(".PrintArea").show();
	 };
	 window.onafterprint = function(){
		 $("body").children().show();
		 $(".PrintArea").show();
	 };
	 window.print();
	 window.close();
}

function LogInsertPrint(obj){
	var use_resn = $("#use_resn").val();
	var user_id = $("#user_id").val();
	var user_name = $("#user_name").val();
	var dept_id = $("#dept_id").val();
	var func_id = $("#func_id").val();
	var biz_id = $("#biz_id").val();
	var pnu_val = $("#pnu_val").val();
	var key_val = $("#key_val").val();
	var ouln_val = $("#ouln_val").val();
	
	if(use_resn == ""){
		alert("사용목적을 입력 하여주십시오!");
		$("#use_resn").focus();
		return false;
	}
	$.ajax({
		type:"POST",
		url:"../log/xml/LogRecord.jsp",
		data : {use_resn:use_resn, user_id:user_id, dept_id:dept_id, func_id:func_id, biz_id:biz_id,pnu_val:KEY,key_val:key_val},
		dataType : "xml",
		success: function(xml){
			if($(xml).find("result").length != 0){
				var retult = $(xml).find("result").text();
				if(retult == "success"){
					if(obj == "UseplanPrint"){
						if($("#print_ucode").length){
							var print_ucode = $("#print_ucode").val();
							if(print_ucode == ""){
								alert("국토의 계획및 이용계획에 관한 법률을 선택하여 주십시오!");
								return false;
							}else{
								_PC.init.printFrmClose();
								printOK();
							}
						}
					}
					_PC.init.printFrmClose();
				}else{
					alert("사용목적을 다시 입력하여 주십시오!");
				}
			}
			
		},
		error: function(xhr, status, error) {
			alert(error);
		}	
	});
}
</script>
<style>
	html{
		font-family:NanumGothic,나눔고딕,굴림,Gulim,AppleGothic,sans-serif; font-size: 9pt;
	}
	table{
		font-family:NanumGothic,나눔고딕,굴림,Gulim,AppleGothic,sans-serif; font-size: 8pt;
	}
	.border1{
		border : 1px solid #e3e3e3;
	}
	.border2{
		border-right : 1px solid #e3e3e3;
		border-bottom : 1px solid #e3e3e3;
	}
	.border3{
		border-bottom : 1px solid #e3e3e3;
	}
	.border4{
		border-right : 1px solid #e3e3e3;
	}
	.table_border1{
		border-right : 1px solid #e3e3e3;
		border-bottom : 1px solid #e3e3e3;
		border-left : 1px solid #e3e3e3;
	}
	
	.border-line1{
		border-right:1px solid #b2b2b2;
		border-bottom:1px solid #b2b2b2;
	}
	.border-line2{
		border-bottom:1px solid #b2b2b2;
	}
	.table_border{
		border:4px solid #0068b7;
	}
</style>
</head>
<body>

<div class="toji-div">
	<table class="border1" id="toji-div-tbl">
		<tr>
			<td height="24" width="20%" class="border4 bgcolor" align="center">토지소재지</td>
			<td width="80%">&nbsp;<%= vo2.getAddr() %></td>
		</tr>
	</table>
</div>

<div class="toji-base-title">
	<div class="deajang-title-icon"></div><b> 기본정보</b>
</div>

<div class="toji-div">
	<table class="border1" id="toji-div-tbl">
		<tr align="center">
			<td height="22" class="border2 bgcolor">발급승인여부</td>
			<td class="border3" colspan="3" align="left">&nbsp;
			<%
			if ( vo3.getAllPlanList().size() > 0 ) {
			%>
				승인
			<%
			} else {
			%>
				미승인
			<%
			}
			%>
			</td>
		</tr>
		<tr align="center">
			<td height="22" width="15%" class="border2 bgcolor">지목</td>
			<td width="35%" class="border2"><%=JIMK_NM %></td>
			<td width="15%" class="border2 bgcolor">면적</td>
			<td width="35%" class="border3"><%= AREA + "㎡" %></td>
		</tr>
		<tr align="center">
			<td height="22" class="border4 bgcolor">소유구분</td>
			<td width="20%" class="border4"><%=OWN_GBN_NM %></td>
			<td height="22" class="border4 bgcolor">개별공시지가</td>
			<td><%=vo2.getJiga() %></td>
		</tr>
	</table>
</div>

<div class="toji-base-title">
	<div class="deajang-title-icon"></div><b> 토지이용계획</b>
</div>
<div class="toji-div">
	<table class="border1" id="toji-div-tbl">
		<tr align="center">
			<td height="80" width="10%" class="border2 bgcolor" rowspan="2">지역.지구등<br>지정여부</td>
			<td width="35%" class="border2 bgcolor">「 국토의 계획 및 이용에 관한 법률」에 따른 지역.지구등</td>
			<td width="55%" class="border3">
				<div class="toji-paging">
				<%
					if(vo3.getCtrPlanList() != null){
						for(int i=0; i<vo3.getCtrPlanList().size(); i++){
								if((i+1) != vo3.getCtrPlanList().size()){
				%>
					<a href="javascript:_PC.init.tojiUseplanPasing('<%=vo3.getCtrPlanList().get(i).getUcode()%>');"><%= vo3.getCtrPlanList().get(i).getUname() %></a>,&nbsp;
				<%
								}else{
				%>
					<a href="javascript:_PC.init.tojiUseplanPasing('<%=vo3.getCtrPlanList().get(i).getUcode()%>');"><%= vo3.getCtrPlanList().get(i).getUname() %></a>
				<%
								}
						}
					}
				%>		
				</div>
				</td>
		</tr>
		<tr align="center">
			<td class="border4 bgcolor">다른법령등에 따른 지역.지구등</td>
			<td>
				<div class="toji-paging">
				<%
					if(vo3.getOthPlanList() != null){
						for(int i=0; i<vo3.getOthPlanList().size(); i++){
								if((i+1) != vo3.getOthPlanList().size()){
				%>
					<a href="javascript:_PC.init.tojiUseplanPasing('<%=vo3.getOthPlanList().get(i).getUcode()%>');"><%= vo3.getOthPlanList().get(i).getUname() %>(<%= vo3.getOthPlanList().get(i).getLawNm() %>)</a>,&nbsp;
				<%
								}else{
				%>
					<a href="javascript:_PC.init.tojiUseplanPasing('<%=vo3.getOthPlanList().get(i).getUcode()%>');"><%= vo3.getOthPlanList().get(i).getUname() %>(<%= vo3.getOthPlanList().get(i).getLawNm() %>)</a>
				<%
								}
						}
					}
				%>		
				</div>
			</td>
		</tr>
	</table>
</div>

<div class="toji-slide-title tojiUseplan-slide-title3">
	<div class="deajang-title-icon"></div><b> 지역.지국등 안에서의 행위제한내용</b>
</div>
<div class="toji-slide-content" id="slide_page_content_3">
	<table id="toji-div-tbl">
		<tr>
			<td align="center"><b>국토의 계획 및 이용계획에 관한 법률</b> 에 따른 지역.지구를 선택 하여 주십시오!</td>
		</tr>
	</table>
</div>

<div class="print-div" >
	<table id="toji-div-tbl">
		<tr>
			<td align="center">
				<!-- <a href="#" onclick="_PC.init.printFrmOpen();">
					<img src="img/print_btn_2.gif" style="margin-right:3px;"/>
				</a> -->
				<!--button class="btn-deajang-print">인쇄</button-->
      			<input type="hidden" id="print_ucode" name="print_ucode" value=""/>
			</td>
		</tr>
	</table>
</div>

<!-- <div style="margin-top:4px; float: right;">
	<a href="#" onclick="_PC.init.printFrmOpen();"><img src="img/print_btn_2.gif" style="margin-right:3px;"/></a>
	<input type="hidden" id="print_ucode" name="print_ucode" value=""/>
</div> -->
<!-- <div style="margin-top:4px; width:100%; border:0px solid #000000; ">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
		<tr>
			<td align="center">
				<button class="btn-genbldg-print">대장 출력</button>
				<button class="" id="">출력</button>
				<a href="#" onclick="_PC.init.printFrmOpen();"><img src="img/print_btn_2.gif" style="margin-right:3px;"/></a>
				<input type="hidden" id="print_ucode" name="print_ucode" value=""/>
			</td>
		</tr>
	</table>
</div>
 -->
<div id="print_form">
	<table class="print-form-tbl table_border">
		<tr>
			<td colspan="3" height="25" align="center" class="border-line2 print-bgcolor"><b>- 행위제한법령정보 인쇄 사용목적 입력창 -</b></td>
		</tr>
		<tr align="center">
			<td rowspan="3" class="border-line1 print-bgcolor">사용자정보</td>
			<td height="25" class="border-line1">&nbsp;사용자명</td>
			<td height="25" class="border-line2" align="left">&nbsp;<%=session_name %></td>
		</tr>
		<tr>
			<td height="25" width="20%" class="border-line1" align="center">&nbsp;사용자아이디</td>
			<td height="25" class="border-line2" align="left">&nbsp;<%=id%></td>
		</tr>
		<tr>
			<td height="25" class="border-line1" align="center">&nbsp;부서명</td>
			<td height="25" class="border-line2" align="left">&nbsp;<%=session_dept %></td>
		</tr>
		<tr align="center">
			<td width="20%" height="80" class="border-line1 print-bgcolor">사용목적</td>
			<td width="80%" colspan="2" class="border-line2">
				<textarea id="use_resn" name="use_resn" rows="4"></textarea>
			</td>
		</tr>
		<tr>
			<td  height="25" colspan="3"  class="border-line2 print-purpose-msg">&nbsp;* 사용목적을 반드시 기재하여주십시오! </td>
		</tr>
		<tr>
			<td height="25" colspan="3" align="center" class="print-purpose-msg">
				<!-- <a href="#" onclick="_PC.init.LogInsertPrint('UseplanPrint');">
					<img src="./img/btn_1.gif" align="absmiddle"/>
				</a>
				<a href="#" onclick="_PC.init.printFrmClose();">
					<img src="./img/btn_2.gif" align="absmiddle"/>
				</a> -->
				<button class="btn-print-form-print" id="tojiUseplan-form-print">인쇄</button>
				<button class="btn-print-form-close">닫기</button>
			</td>
		</tr>
		<input type="hidden" id="user_id" name="user_id" value="<%=id%>"/><!-- 사용자아이디 -->
		<input type="hidden" id="dept_id" name="dept_id" value="<%=session_dept%>"/><!-- 부서코드 -->
		<input type="hidden" id="func_id" name="func_id" value="FW006"/><!-- 단위기능관리ID -->
		<%-- <input type="hidden" id="biz_id" name="biz_id" value="<%=session_bizid%>"/> --%><!-- 단위업무관리ID -->
		<input type="hidden" id="pnu_val" name="pnu_val" value="<%=pnu%>"/>
	</table>
</div>

<%
	//시스템연계로그기록
	logtime = Utils.getStrSec();

	UB.setLinkLogUpdate(id, stime, rslt_cd, etime, err_desc, logtime);
	
} else {
%>

<div class="toji-div">
	<table class="border1" id="toji-div-tbl">
		<tr>
			<td height="24" width="20%" class="border4 bgcolor" align="center">토지소재지</td>
			<td width="80%">&nbsp;</td>
		</tr>
	</table>
</div>

<div class="toji-base-title">
	<div class="deajang-title-icon"></div><b> 기본정보</b>
</div>

<div class="toji-div">
	<table class="border1" id="toji-div-tbl">
		<tr align="center">
			<td height="22" class="border2 bgcolor">발급승인여부</td>
			<td class="border3" colspan="3" align="left">&nbsp;</td>
		</tr>
		<tr align="center">
			<td height="22" width="15%" class="border2 bgcolor">지목</td>
			<td width="35%" class="border2"></td>
			<td width="15%" class="border2 bgcolor">면적</td>
			<td width="35%" class="border3"></td>
		</tr>
		<tr align="center">
			<td height="22" class="border4 bgcolor">소유구분</td>
			<td width="20%" class="border4"></td>
			<td height="22" class="border4 bgcolor">개별공시지가</td>
			<td></td>
		</tr>
	</table>
</div>

<div class="toji-base-title">
	<div class="deajang-title-icon"></div><b> 토지이용계획</b>
</div>

<div class="toji-div">
	<table class="border1" id="toji-div-tbl">
		<tr align="center">
			<td height="80" width="10%" class="border2 bgcolor" rowspan="2">지역.지구등<br>지정여부</td>
			<td width="35%" class="border2 bgcolor">「 국토의 계획 및 이용에 관한 법률」에 따른 지역.지구등</td>
			<td width="55%" class="border3">
				<div class="toji-paging"></div>
			</td>
		</tr>
		<tr align="center">
			<td class="border4 bgcolor">다른법령등에 따른 지역.지구등</td>
			<td>
				<div class="toji-paging"></div>
			</td>
		</tr>
	</table>
</div>

<div class="toji-slide-title tojiUseplan-slide-title3">
	<div class="deajang-title-icon"></div><b> 지역.지국등 안에서의 행위제한내용</b>
</div>
<div class="toji-slide-content" id="slide_page_content_3">
	<table id="toji-div-tbl">
		<tr>
			<td align="center"><b>국토의 계획 및 이용계획에 관한 법률</b> 에 따른 지역.지구를 선택 하여 주십시오!</td>
		</tr>
	</table>
</div>


<div class="no-auth-deajang-box">
	<div class="no-auth-deajang-msg">
		<p><b>사용권한</b>이 없습니다.</p>
	</div>
</div>

<%	
}
}
%>
</body>
</html>