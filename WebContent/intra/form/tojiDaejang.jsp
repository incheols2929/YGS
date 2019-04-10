<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="
javax.xml.bind.Unmarshaller,

org.apache.commons.io.IOUtils,
org.apache.commons.lang3.StringUtils,

geomex.kras.gmx.KrasGmxConn,
geomex.kras.gmx.KrasGmxConn.SVC,
geomex.kras.gmx.DjUnmarshaller,
geomex.kras.gmx.vo.*,

geomex.utils.*,
geomex.svc.handler.Code,
geomex.pkg.usr.UseLogBean
"
%>
<%@ include file="../../ssesion/ssesion.jsp" %>
<%
String pnu = StringUtils.trimToEmpty(request.getParameter("pnu"));

if ( id != null ) {
	
//대장기본정보 권한체크
String auth = Code.getAuthCheckB("FW004", id, session_ugrp_id); //주소로 치고 들어올 경우 권한 체크

String JIMK_NM = ""; //지목
String AREA = ""; //면적
String OWN_GBN_NM = ""; //소유구분
String SHAP_NUM = ""; //공유인수
String LAND_MOVE_WHY_CODE_NM = ""; //토지이동사유

if(!"N".equals(auth)){
	
	//시스템연계로그정보
	String worknm = "토지대장조회";
	String org_cd = "42770";
	String sys_cd = "LRGS";
	String link_typ = "MWV";
	String stime = Utils.getStrSec(); //문자열 반환
	
	UseLogBean UB = new UseLogBean(); //사용 로그정보 기록
	
	KrasGmxConn kgc = new KrasGmxConn(); // 실제 운용될 ip연결
	DjUnmarshaller djUm = new DjUnmarshaller(); //마샬링 기법사용

	
	// 토지대장
	TojiDaejang2Set vo = (TojiDaejang2Set) djUm.getUnmarshal( TojiDaejang2Set.class, kgc.getData(SVC.GetTojiDaejangPrint2, pnu) );
	
	// 건축물 간략정보
	LandInfoSet vo2 = (LandInfoSet) djUm.getUnmarshal( LandInfoSet.class, kgc.getData(SVC.GetLandInfo, pnu) );
	
	
	String rslt_cd = ""; 
	String err_desc = ""; 
	String etime = "";
	String logtime = "";
	
	JIMK_NM = vo2.getLandInfo().getJimkNm();  //지목
	AREA = vo2.getLandInfo().getArea();  //면적
	OWN_GBN_NM = vo2.getLandInfo().getOwnGbnNm();//소유구분
	SHAP_NUM = vo2.getLandInfo().getShapNum();//공유인수
	LAND_MOVE_WHY_CODE_NM = vo2.getLandInfo().getLandMoveWhyCodeNm();//토지이동사유
	
	rslt_cd = "S";
  	etime = Utils.getStrSec();
  
%>

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
			<td height="24" width="15%" class="border2 bgcolor">지목</td>
			<td colspan="2" class="border2" align="left">&nbsp;<%=JIMK_NM %></td>
			<td width="15%" class="border2 bgcolor">면적</td>
			<td colspan="2" class="border3" align="left">&nbsp;<%=AREA %>㎡</td>
		</tr>
		<tr align="center">
			<td height="24" class="border2 bgcolor">소유구분</td>
			<td colspan="2" class="border2" align="left">&nbsp;<%=OWN_GBN_NM %></td>
			<td class="border2 bgcolor">공유인수</td>
			<td colspan="2" class="border3" align="left">&nbsp;<%=SHAP_NUM %></td>
		</tr>
		<tr align="center">
			<td height="24" class="border2 bgcolor">공시지가</td>
			<td colspan="5" align="left" class="border3">&nbsp;<%= Utils.getCommaCreate(vo.getJigaList().getJigaList().get(0).getJiga()) + "원 (" + vo.getJigaList().getJigaList().get(0).getBaseYear() + "년 기준)" %></td>
		</tr>
		<tr align="center">
			<td height="24" class="border4 bgcolor">토지이동사유</td>
			<td colspan="5" align="left">&nbsp;<%=LAND_MOVE_WHY_CODE_NM %></td>
		</tr>
	</table>
</div>

<div class="toji-slide-title slide-title1">
	<div class="deajang-title-icon"></div><b> 토지이동 정보</b>
</div>

<div class="toji-div">
	<table class="border1" id="toji-div-tbl">
		<tr class="bgcolor" align="center">
			<td height="25" width="30%" class="border4">토지이동일</td>
			<td width="70%" class="bgcolor">토지이동사유</td>
		</tr>
	</table>
</div>

<div class="toji-slide-content-1" id="slide_page_content_1">
	<table class="table_border1" id="toji-div-tbl">
		<% 
			String style1="";
			String style2="";
			
			for(int i=0; i<vo.getLandMovHistList().getLandMovHistList().size(); i++){
				if((i+1) == vo.getLandMovHistList().getLandMovHistList().size()){
					style1="border4";
					style2="";
					
				}else{
					style1="border2";
					style2="border3";
					
				}
				
				String tojidata = "";
				
				if(vo.getLandMovHistList().getLandMovHistList().get(i).getLandMoveYmd1().length() == 8) {
					tojidata = Utils.formatTxtYMD(vo.getLandMovHistList().getLandMovHistList().get(i).getLandMoveYmd1());
					
				} else {
					tojidata = vo.getLandMovHistList().getLandMovHistList().get(i).getLandMoveYmd1();
					
				}
		%>
		
		<tr class="slide-content-bgcolor" align="center">
			<td height="23" width="30%" class="<%=style1 %>"><%= tojidata %></td>
			<td width="70%" class="<%=style2 %>" align="left">&nbsp;<%=vo.getLandMovHistList().getLandMovHistList().get(i).getLandMoveWhyCode() %></td>
		</tr>
		
		<%
			}
		%>
	</table>
</div>

<div class="toji-slide-title slide-title2">
	<div class="deajang-title-icon"></div><b> 개별공시지가</b>
</div>

<div class="toji-div">
	<table class="border1" id="toji-div-tbl">
		<tr class="bgcolor" align="center">
			<td height="25" width="30%" class="border4">기준년월일</td>
			<td width="70%" class="bgcolor">개별공시지가</td>
		</tr>
	</table>
</div>

<div class="toji-slide-content-2" id="slide_page_content_2">
	<table class="table_border1" id="toji-div-tbl">
		<%
			String style3="";
			String style4="";

			if(vo.getJigaList().getJigaList().size() != 0){
				for(int i=0; i<vo.getJigaList().getJigaList().size(); i++){
					if((i+1) == vo.getJigaList().getJigaList().size()){
						style3="border4";
						style4="";
						
					}else{
						style3="border2";
						style4="border3";
						
					}
		%>
		<tr class="slide-content-bgcolor" align="center">
			<td height="23" width="30%" class="<%=style3 %>"><%=vo.getJigaList().getJigaList().get(i).getBaseYear()%>년</td><%-- <%=ablist.get(i).getBASE_YEAR() + "년 "  +ablist.get(i).getBASE_MON() + "월"%> --%>
			<td width="70%" class="<%=style4 %>"><%= Utils.getCommaCreate(vo.getJigaList().getJigaList().get(i).getJiga())  + "원"%></td>
		</tr>
		<%
				}
			}else{
		%>
			<tr class="slide-content-bgcolor" align="center">
				<td height="80" >정보가 존재 하지 않습니다.</td>
			</tr>
		<%
			}
		%>
	</table>
</div>

<div class="toji-slide-title slide-title6">
	<div class="deajang-title-icon"></div><b> 소유권이동 정보</b>
</div>

<div class="toji-div">
	<table class="border1" id="toji-div-tbl">
		<tr class="bgcolor" align="center">
			<td height="25" width="20%" class="border4">변동일자</td>
			<td height="25" width="20%" class="border4">변동원인</td>
			<td height="25" width="20%" class="border4">성명또는명칭</td>
			<td width="40%" class="bgcolor">주소</td>
		</tr>
	</table>
</div>

<div class="toji-slide-content-6" id="slide_page_content_6">
	<table class="table_border1" id="toji-div-tbl">
		<%
			String style5="";
			String style6="";
			
			for(int i=0; i<vo.getOwnHistList().getOwnHistList().size(); i++){
				if((i+1) == vo.getOwnHistList().getOwnHistList().size()){
					style5="border4";
					style6="";
					
				}else {
					style5="border2";
					style6="border3";
					
				}
		%>
		<tr class="slide-content-bgcolor" align="center">
			<td height="23" width="20%" class="<%=style5 %>"><%=Utils.formatTxtYMD(vo.getOwnHistList().getOwnHistList().get(i).getOwnspChYmd()) %></td>
			<td height="23" width="20%" class="<%=style5 %>"><%=vo.getOwnHistList().getOwnHistList().get(i).getOwnspChCauGbnNm() %></td>
			<td height="23" width="20%" class="<%=style5 %>"><%=vo.getOwnHistList().getOwnHistList().get(i).getOwnrNm() %></td>
			<td width="40%" class="<%=style6 %>"><%=vo.getOwnHistList().getOwnHistList().get(i).getOwnrAddr() %></td>
		</tr>
		<%
			}
		%>
	</table>
</div>

<div class="toji-slide-title slide-title4">
	<div class="deajang-title-icon"></div><b> 공유인 정보</b>
</div>

<div class="toji-div">
	<table class="border1" id="toji-div-tbl">
		<tr class="bgcolor" align="center">
			<td height="23" width="15%" class="border4">변동일자</td>
			<td height="23" width="20%" class="border4">변동원인</td>
			<td height="23" width="10%" class="border4">지분</td>
			<td height="23" width="15%" class="border4">성명또는명칭</td>
			<td width="40%" class="bgcolor">주소</td>
		</tr>
	</table>
</div>

<div class="toji-slide-content-4" id="slide_page_content_4">
	<table class="table_border1" id="toji-div-tbl">
		<%
			String style7="";
			String style8="";
			if(vo.getShareInfoList().getShareInfoList().size() != 0){
				for(int i=0; i<vo.getShareInfoList().getShareInfoList().size(); i++){
					if((i+1) == vo.getShareInfoList().getShareInfoList().size()){
						style7="border4";
						style8="";
						
					}else{
						style7="border2";
						style8="border3";
						
					}
		%>
		<tr class="slide-content-bgcolor" align="center">
			<td height="23" width="15%" class="<%=style7 %>"><%=Utils.formatTxtYMD(vo.getShareInfoList().getShareInfoList().get(i).getOwnspChYmd()) %></td>
			<td height="23" width="20%" class="<%=style7 %>"><%=vo.getShareInfoList().getShareInfoList().get(i).getOwnspChCauGbnNm()%></td>
			<td height="23" width="10%" class="<%=style7 %>"><%=vo.getShareInfoList().getShareInfoList().get(i).getOwnspCosm()%></td>
			<td height="23" width="15%" class="<%=style7 %>"><%=vo.getShareInfoList().getShareInfoList().get(i).getOwnrNm()%></td>
			<td width="40%" class="<%=style8 %>"><%=vo.getShareInfoList().getShareInfoList().get(i).getOwnrAddr()%></td>
		</tr>
		<%
				}
			}else{
		%>
			<tr class="slide-content-bgcolor" align="center">
				<td height="60" width="15%" colspan="5">정보가 존재하지 않습니다.</td>
			</tr>
		<%
			}
		%>
	</table>
</div>

<div class="print-div" >
	<table id="toji-div-tbl">
		<tr>
			<td align="center">
				<button class="green" onclick="$('#kras_wrap, #kras_content').hide();">닫기</button>
			</td>
		</tr>
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
			<td width="80%"></td>
		</tr>
	</table>
</div>

<div class="toji-base-title">
	<div class="deajang-title-icon"></div><b> 기본정보</b>
</div>

<div class="toji-div">
	<table class="border1" id="toji-div-tbl">
		<tr align="center">
			<td height="24" width="15%" class="border2 bgcolor">지목</td>
			<td colspan="2" class="border2" align="left">&nbsp;</td>
			<td width="15%" class="border2 bgcolor">면적</td>
			<td colspan="2" class="border3" align="left">&nbsp;</td>
		</tr>
		<tr align="center">
			<td height="24" class="border2 bgcolor">소유구분</td>
			<td colspan="2" class="border2" align="left">&nbsp;</td>
			<td class="border2 bgcolor">공유인수</td>
			<td colspan="2" class="border3" align="left">&nbsp;</td>
		</tr>
		<tr align="center">
			<td height="24" class="border2 bgcolor">공시지가</td>
			<td colspan="5" align="left" class="border3">&nbsp;</td>
		</tr>
		<tr align="center">
			<td height="24" class="border4 bgcolor">토지이동사유</td>
			<td colspan="5" align="left">&nbsp;</td>
		</tr>
	</table>
</div>

<div class="toji-slide-title slide-title1">
	<div class="deajang-title-icon"></div><b> 토지이동 정보</b>
</div>

<div class="toji-div">
	<table class="border1" id="toji-div-tbl">
		<tr class="bgcolor" align="center">
			<td height="25" width="30%" class="border4">토지이동일</td>
			<td width="70%" class="bgcolor">토지이동사유</td>
		</tr>
	</table>
</div>

<div class="toji-slide-content-1" id="slide_page_content_1">
	<table class="table_border1" id="toji-div-tbl">
		<tr class="slide-content-bgcolor" align="center">
			<td height="80" >정보가 존재 하지 않습니다.</td>
		</tr>
	</table>
</div>

<div class="toji-slide-title slide-title2">
	<div class="deajang-title-icon"></div><b> 개별공시지가</b>
</div>

<div class="toji-div">
	<table class="border1" id="toji-div-tbl">
		<tr class="bgcolor" align="center">
			<td height="25" width="30%" class="border4">기준년월일</td>
			<td width="70%" class="bgcolor">개별공시지가</td>
		</tr>
	</table>
</div>

<div class="toji-slide-content-2" id="slide_page_content_2">
	<table class="table_border1" id="toji-div-tbl">
		<tr class="slide-content-bgcolor" align="center">
			<td height="80" >정보가 존재 하지 않습니다.</td>
		</tr>
	</table>
</div>

<div class="toji-slide-title slide-title3">
	<div class="deajang-title-icon"></div><b> 소유권이동 정보</b>
</div>

<div class="toji-div">
	<table class="border1" id="toji-div-tbl">
		<tr class="bgcolor" align="center">
			<td height="25" width="20%" class="border4">변동일자</td>
			<td height="25" width="20%" class="border4">변동원인</td>
			<td height="25" width="20%" class="border4">성명또는명칭</td>
			<td width="40%" class="bgcolor">주소</td>
		</tr>
	</table>
</div>

<div class="toji-slide-content-3" id="slide_page_content_3">
	<table class="table_border1" id="toji-div-tbl">
		<tr class="slide-content-bgcolor" align="center">
			<td height="60" width="15%" colspan="5">정보가 존재하지 않습니다.</td>
		</tr>
	</table>
</div>

<div class="toji-slide-title slide-title4">
	<div class="deajang-title-icon"></div><b> 공유인 정보</b>
</div>

<div class="toji-div">
	<table class="border1" id="toji-div-tbl">
		<tr class="bgcolor" align="center">
			<td height="23" width="15%" class="border4">변동일자</td>
			<td height="23" width="20%" class="border4">변동원인</td>
			<td height="23" width="10%" class="border4">지분</td>
			<td height="23" width="15%" class="border4">성명또는명칭</td>
			<td width="40%" class="bgcolor">주소</td>
		</tr>
	</table>
</div>

<div class="toji-slide-content-4" id="slide_page_content_4">
	<table class="table_border1" id="toji-div-tbl">
		<tr class="slide-content-bgcolor" align="center">
			<td height="60" width="15%" colspan="5">정보가 존재하지 않습니다.</td>
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