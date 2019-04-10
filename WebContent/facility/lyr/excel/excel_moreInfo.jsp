<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="geomex.svc.handler.Code"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.utils.PageNavi"%>
<%@page import="geomex.utils.PageNaviBean"%>
<%@page import="geomex.pkg.sys.lris.Etlrgst"%>
<%@page import="geomex.pkg.sys.lris.EtlrgstBean"%>
<%@page import="geomex.pkg.sys.lris.LRGST83"%>
<%@page import="geomex.pkg.sys.lris.LRGST81"%>
<%@page import="geomex.pkg.sys.lris.LRGST"%>
<%@page import="geomex.pkg.sys.luris.Luris"%>
<%@page import="geomex.pkg.srch.ConditionSearch"%>
<%@page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.ArrayList"%>


<%
	request.setCharacterEncoding("UTF-8");

	String lyr_nm = request.getParameter("lyr_nm");
	String lyr_id = request.getParameter("lyr_id");
	String srch_cond = request.getParameter("srch_cond");
	String wtk = request.getParameter("wtk");
	
	String tbl_id = Code.getLyrNM(lyr_nm); //테이블명을 가지고온다.
	String colkey = Code.getTblkey(tbl_id); //키필드 칼럼명을 가져온다.
	
	ConditionSearchBean CS = new ConditionSearchBean();
	
	ArrayList<ConditionSearch> relist = CS.getSrchLrycontent(tbl_id, colkey, wtk, srch_cond, lyr_nm); //결과목록을 가져온다.
	ArrayList<ConditionSearch> TL = CS.getTBLcolList(tbl_id);
	
	//System.out.println(lyr_nm + ", " + lyr_id + ", " + srch_cond + ", " + resultcnt + ", " + wtk + ", " + pagenum);
	String base = "";
	LRGST LS = new LRGST();
	LRGST81[] LRGST81 = null;
	
	//토지대장 정보를 가지고온다.
	EtlrgstBean EB = new EtlrgstBean();
	ArrayList<Etlrgst> etlist = null;
	
	//공시지가
	LRGST83 AB = new LRGST83();
	ArrayList<LRGST83> jigaList = new ArrayList<LRGST83>();
	
	//용도지역지구
	ArrayList<Luris> lulist = null;
	
	int colspan = 0;
	if(TL.size() != 0){
		if("lp_pa_cbnd".equals(tbl_id)){
			colspan = (TL.size()+4);
		}else{
			colspan = TL.size();	
		}
	}else{
		colspan = 0;
	}
	
	String nowdate = Utils.getStrSec();
	String filename = nowdate+".xls";
	
	response.setContentType("application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition", "filename="+filename); 
	response.setHeader("Content-Description", "JSP Generated Data");
	
	String clientBrowser= new String(); // 브라우저 버전 
	 clientBrowser=request.getHeader("User-Agent");

	 if (clientBrowser.indexOf("MSIE 5.5")>-1 || clientBrowser.indexOf("MSIE 6.0") > -1) 
	 {
	    response.setHeader("Content-Disposition","attachment;filename="+filename);
	 } 
	 else 
	 {
	    response.setHeader("Content-Disposition","filename="+filename);
	 }
	 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>::레이어 상세 정보::</title>
</head>
<body>
<table border="1" cellpadding="0" cellspacing="0" height="auto" style="border:1px solid #rrrrrr;">
	<tr>
		<td colspan="<%=TL.size()%>">검색하신 <b><%=lyr_nm %></b>에 대한 정보입니다.</td>
	</tr>
	<tr bgcolor="#32b69f" align="center">
		<!-- td height="25" width='15%'>결과값</td-->
		<%
			if(TL.size() != 0){
				for(int i=0; i<TL.size(); i++){
		%>
			<td height="25"><b><%=TL.get(i).getValue() %></b></td>
		<%
				}
				if("lp_pa_cbnd".equals(tbl_id)){
		%>

			<td style="color: #ffffff;">소재지</td>
			<!-- <td style="color: #ffffff;">대장구분</td>
			<td style="color: #ffffff;">지목</td>
			<td style="color: #ffffff;">면적</td>
			<td style="color: #ffffff;">공시지가</td>
			<td style="color: #ffffff;">소유구분</td>
			<td style="color: #ffffff;">공유인수</td>
			<td style="color: #ffffff;">소유자명</td>
			<td style="color: #ffffff;">소유권변동원인</td>
			<td style="color: #ffffff;">소유원변동일자</td>
			<td style="color: #ffffff;">토지이동사유</td>
			<td style="color: #ffffff;">토지이동일자</td> -->
			<td style="color: #ffffff;">용도지역</td>
			
		<%			
				}
				
			}
		%>
	</tr>
	<%
		for(int i=0; i<relist.size(); i++){
			String key = relist.get(i).getCode();
	%>
	<tr align="center">
		<!--  td height="25" width='15%'>결과값</td-->
		<%
			if(TL.size() != 0){
				for(int j=0; j<TL.size(); j++){
					String cont = CS.getLyrDetailList(key, tbl_id, colkey, TL.get(j).getCode());
		%>
			<td height="25" ><%=cont%></td>
		<%
				}
				if("lp_pa_cbnd".equals(tbl_id)){
					//LRGST81 = LS.getBase(key); //기본정보를 가지고온다.
					jigaList = AB.getBaseInfo(key);
					String jiga = "";
					
					if(jigaList.size() == 0){
						jiga = "&nbsp;";
					}
					
					//토지정보를 가지고온다.
					etlist = EB.getEtlrgstList(key);
					
					String JIMK_NM = "";
					String AREA = "";
					String OWN_GBN_NM = "";
					String LGGBN = "";
					String SHAP_NUM = "";
					String OWNR_NM = "";
					String OWNSP_CH_CAU = "";
					String OWNSP_CH_YMD = "";
					String LAND_MOVE_WHY = "";
					String LAND_MOVE_YMD = "";
					/*if(LRGST81.length != 0){
						JIMK_NM = LRGST81[0].JIMK_NM;
						AREA = LRGST81[0].AREA; 
						OWN_GBN_NM = LRGST81[0].OWN_GBN_NM;
					}else{
						JIMK_NM = "";
						AREA = "";
						OWN_GBN_NM = "";
					}*/
					if(etlist.size() != 0){
						JIMK_NM = Code.getJimokCode(etlist.get(0).getJIMK_CD());
						AREA = Utils.getCommaCreate(etlist.get(0).getLAND_AREA()) + "㎡";
						OWN_GBN_NM = etlist.get(0).getOWN_GBN().substring((etlist.get(0).getOWN_GBN().indexOf("-")+1), etlist.get(0).getOWN_GBN().length());
						LGGBN = Code.getLGGBN(etlist.get(0).getLG_GBN());
						SHAP_NUM = etlist.get(0).getSHAP_NUM();
						OWNR_NM = etlist.get(0).getOWNR_NM();
						OWNSP_CH_CAU = etlist.get(0).getOWNSP_CH_CAU().substring((etlist.get(0).getOWNSP_CH_CAU().indexOf("-")+1), etlist.get(0).getOWNSP_CH_CAU().length());
						OWNSP_CH_YMD = etlist.get(0).getOWNSP_CH_YMD();
						LAND_MOVE_WHY = etlist.get(0).getLAND_MOVE_WHY();
						LAND_MOVE_YMD = etlist.get(0).getLAND_MOVE_YMD();
					}else{
						JIMK_NM = "&nbsp;";
						AREA = "&nbsp;";
						OWN_GBN_NM = "&nbsp;";
						LGGBN = "&nbsp;";
						SHAP_NUM = "&nbsp;";
						OWNR_NM = "&nbsp;";
						OWNSP_CH_CAU = "&nbsp;";
						OWNSP_CH_YMD = "&nbsp;";
						LAND_MOVE_WHY = "&nbsp;";
						LAND_MOVE_YMD = "&nbsp;";
					}
					
		%>
						<td class="border3"><%= Code.getAddrcreate(key)%></td><!-- 소재지 -->
						<%-- <td class="border3"><%= LGGBN%></td><!-- 대장구분 -->
						<td class="border3"><%= JIMK_NM%></td><!-- 지목 -->
						<td class="border3"><%= AREA%></td><!-- 면적 -->
						<td class="border3"><%= jigaList.get(0).JIGA%></td><!-- 공시지가 -->
						<td class="border3"><%= OWN_GBN_NM%></td><!-- 소유구분 -->
						<td class="border3"><%= SHAP_NUM%></td><!-- 공유인수 -->
						<td class="border3"><%= OWNR_NM%></td><!-- 소유자명 -->
						<td class="border3"><%= OWNSP_CH_CAU%></td><!-- 소유권변동원인 -->
						<td class="border3"><%= OWNSP_CH_YMD%></td><!-- 소유권변동일자 -->
						<td class="border3"><%= LAND_MOVE_WHY %></td><!-- 토지이동사유 -->
						<td class="border3"><%= LAND_MOVE_YMD%></td><!-- 토지이동일자 --> --%>
						<td class="border4" align='center'>
							<p style="padding:5px;">
								<%
								//용도지역지구
								lulist = CS.getIncludeUzone(key);
								String areaname = "";
								if(lulist.size() != 0){
									for(int j=0; j<lulist.size(); j++){
											if((j+1) != lulist.size()){
								%>
									<%= Code.getMtuznelyrnm(lulist.get(j).getUcode()) %>(<%= Code.getLAW_NM(lulist.get(j).getUcode()) %>)<br/>
								<%
												}else{
								%>
										<%= Code.getMtuznelyrnm(lulist.get(j).getUcode()) %>(<%= Code.getLAW_NM(lulist.get(j).getUcode()) %>)<br/>
								<%
												}
										}
									}
								%>
							</p>
						</td><!-- 용도지역 -->
		<%			
					
				}
				
			}
		%>
	</tr>
	<%
		}
	%>
</table>
</body>
</html>