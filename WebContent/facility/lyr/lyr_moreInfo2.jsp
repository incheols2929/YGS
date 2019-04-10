<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.pkg.sys.lris.Etlrgst"%>
<%@page import="geomex.pkg.sys.lris.EtlrgstBean"%>
<%@page import="geomex.pkg.sys.lris.LRGST83"%>
<%@page import="geomex.pkg.sys.lris.LRGST81"%>
<%@page import="geomex.pkg.sys.lris.LRGST"%>
<%@page import="geomex.pkg.srch.ConditionSearch"%>
<%@page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@page import="geomex.utils.PageNavi"%>
<%@page import="geomex.utils.PageNaviBean"%>
<%@page import="geomex.svc.handler.Code"%>
<%@page import="geomex.pkg.sys.luris.Luris"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URLDecoder"%>
<%@ include file="../../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	//단위업무관리ID를 생성
	Object SESSION_BIZID = session.getAttribute("SESSION_BIZID");
	String session_bizid = (String)SESSION_BIZID;
	String dept_nm = Code.getDeptNM(session_dept);

	request.setCharacterEncoding("UTF-8");

	String lyr_nm = request.getParameter("lyr_nm");
	String lyr_id = request.getParameter("lyr_id");
	String srch_cond = request.getParameter("srch_cond");
	String resultcnt = request.getParameter("resultcnt");
	String wtk = request.getParameter("wtk");
	String pagenum = request.getParameter("pagenum");
	
	int pnum = Integer.parseInt(pagenum);
	int cnt = Integer.parseInt(resultcnt);
	
	String tbl_id = Code.getLyrNM(lyr_nm); //테이블명을 가지고온다.
	String colkey = Code.getTblkey(tbl_id); //키필드 칼럼명을 가져온다.
	
	ConditionSearchBean CS = new ConditionSearchBean();
	PageNaviBean pn = new PageNaviBean();
	PageNavi po = pn.getPageInfo(pnum, cnt, 20, 15);
	
	String psnum =  Integer.toString(po.start);
	String ps =  Integer.toString(po.pageSize);
	
	ArrayList<ConditionSearch> relist = CS.getSrchLrycontent(tbl_id, colkey, wtk, srch_cond, psnum, ps, lyr_nm); //결과목록을 가져온다.
	ArrayList<ConditionSearch> TL = CS.getTBLcolList(tbl_id);
	
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
	String width = "";
	String excelChk = "";
	
	if(TL.size() != 0){
		if("lp_pa_cbnd".equals(tbl_id)){	
			colspan = (TL.size()+13);
			width = "1800px";
		}else{
			colspan = TL.size();
			width = "1500px";
		}
		
		String excelAuth = Code.getAuthCheck("FW003", id, session_ugrp_id); ///엑셀 DB와 'FW003' 동일해야함
		excelChk = "_PC.init.excelFormOn();";
		if(!"Y".equals(excelAuth)){
			excelChk = "alert('해당 기능에 대한 권한이 없습니다.');";
		}
		
	}else{
		
		colspan = 0;
		width = "100%";
		excelChk = "alert('정보가 존재하지 않습니다.');";
	}
	
%>

<style>
	.border1{
		border : 1px solid #e3e3e3;
	}
	.border2{
		border-right : 1px dotted #dedede;
		border-bottom : 1px dotted #dedede;
	}
	.border3{
		border-right : 1px solid #dedede;
		border-bottom : 1px solid #dedede;
	}
	.border4{
		border-bottom : 1px solid #e3e3e3;
	}
	.border5{
		border-right : 1px dotted #ffffff;
	}
	.table_border1{
		border-right : 1px solid #dedede;
		border-bottom : 1px solid #dedede;
		border-left : 1px solid #dedede;
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
	
	.style_border_1{
		border-right:1px solid #6e86ff;
		border-bottom:1px solid #6e86ff;
	}
	.style_border_2{
		border-bottom:1px solid #6e86ff;
	}
</style>
</head>
<body>
<div id="lyr_wapp" class="srch" style="border:0px solid #000000; height:100%;">
	<div id="lyr_sunject" class="border3" style="height:25px;">
		<button onclick="_PC.init.frameClose();" style="position: absolute; right: 5px; width: 55px; cursor: pointer;">닫기</button><!-- 하단 프레임 클로즈  -->
		<p style="padding:5px;"><img src="img/pp_img_1.gif" align="absmiddle"/> <b>레이어 상세정보</b></p>
	</div>
	<div id="lyr_text" style="height:25px;">
		<div style="float: left;"><p style="padding:5px 0 0 0;">&nbsp;&nbsp;&nbsp;<b style="color:blue;"><span id="layernm"><%=lyr_nm %></span></b> 레이어 범위검색결과 총 <b style="color:blue;"><span id="layercnt"><%=resultcnt %></span>건</b>의 정보가 검색 되었습니다.</p></div>
		<div style="float: right; border:0px solid #000000; margin:2px 3px 0 0;">
			
				<img src="img/excel_btn.gif" style="cursor: pointer;" onclick="<%=excelChk%>"/>	 <!-- 엑셀 -->
		</div>
	</div>
	<div id="lyr_content_box" style="border:0px solid #000000; overflow: auto;">
		<table border="0" cellpadding="0" cellspacing="0" height="auto" width="100%" class="border1">
			<tr bgcolor="#32b69f;" align="center">
				<!-- td height="25" width='15%'>결과값</td-->
				<%
					if(TL.size() != 0){
						for(int i=0; i<TL.size(); i++){
				%>
					<td height="25" class="border2" style="color: #ffffff;"><b><%=TL.get(i).getValue() %></b></td>
				<%
						}
						if("lp_pa_cbnd".equals(tbl_id)){
				%>
					<!--<td class="border2" style="color: #ffffff;"><b>지목</b></td>
						<td class="border2" style="color: #ffffff;"><b>소유구분</b></td>
						<td class="border2" style="color: #ffffff;"><b>면적</b></td>
						<td class="border3" style="color: #ffffff;"><b>공시지가</b></td>-->
					<td height="25" class="border2" style="color: #ffffff;">소재지</td>
					<!-- <td height="25" class="border2" style="color: #ffffff;">대장구분</td>
					<td height="25" class="border2" style="color: #ffffff;">지목</td>
					<td height="25" class="border2" style="color: #ffffff;">면적</td>
					<td height="25" class="border2" style="color: #ffffff;">공시지가</td>
					<td height="25" class="border2" style="color: #ffffff;">소유구분</td>
					<td height="25" class="border2" style="color: #ffffff;">공유인수</td>
					<td height="25" class="border2" style="color: #ffffff;">소유자명</td>
					<td height="25" class="border2" style="color: #ffffff;">소유권변동원인</td>
					<td height="25" class="border2" style="color: #ffffff;">소유원변동일자</td>
					<td height="25" class="border2" style="color: #ffffff;">토지이동사유</td>
					<td height="25" class="border2" style="color: #ffffff;">토지이동일자</td> -->
					<td height="25" class="border3" style="color: #ffffff;">용도지역</td>
					
				<%			
						}
						
					}
				%>
			</tr>
			<%
				for(int i=0; i<relist.size(); i++){
					String key = relist.get(i).getCode();
			%>
			<tr align="center" style="cursor: pointer;"  onmouseover="TRbackgroundColoron(this);"  onmouseout="TRbackgroundColorout(this);" onclick="parent.index._MAINMAP.searchAndMoveNoScale('<%=lyr_nm %>', '<%=key%>');">
				<!--  td height="25" width='15%'>결과값</td-->
				<%
					if(TL.size() != 0){
						for(int j=0; j<TL.size(); j++){
							String tables = tbl_id;     //시설물 테이블인지
                            String facility = tables.substring(0, 2);	//시설물 테이블이 맞다면 테이블 앞 3자리만 가져옴
                            
                          /*   if(facility =="ag"){
								System.out.println("안녕!");
							}else if( !("ag".equals(facility)) ){
								System.out.println("하이!");
							} 
							  */
                            String cont = CS.getLyrDetailList(key, tbl_id, colkey, TL.get(j).getCode()); //통합검색 상세정보 리스트

				%>
					<td height="25" class="border3"><%=cont%></td>
				<%
						}
						if("lp_pa_cbnd".equals(tbl_id)){
							//LRGST81 = LS.getBase(key); //기본정보를 가지고온다.
							
							jigaList = AB.getBaseInfo(key); //공시지가 조회
							String jiga = "";
							
							
							if(jigaList.size() == 0){
								jiga = "&nbsp;";
							}
							
							//토지정보를 가지고온다.
							etlist = EB.getEtlrgstList(key);  // 토지대장의 정보를 가지고온다.
							
							String JIMK_NM = ""; //지목코드를 가져옴
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
								JIMK_NM = Code.getJimokCode(etlist.get(0).getJIMK_CD()); //지목코드를 가져옴
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
						<td class="border3"><%= Code.getAddrcreate(key)%></td><!-- 소재지 --> <!-- pnu를 가지고 주소를 만든다. -->
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
									<%= Code.getMtuznelyrnm(lulist.get(j).getUcode()) %>(<%= Code.getLAW_NM(lulist.get(j).getUcode()) %>)<br/> <!-- //토지이용계획의 국토의 계획 및 이용에 관한 법률에 따른 지역지구명을 가지고온다.//  법령명 -->
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
			<tr>
				<td align="center" colspan="<%=colspan%>" height="30" bgcolor="#eeeeee">
					<%
					
					po.pageCount = (po.count / po.pageSize) + (po.count % po.pageSize == 0 ? 0 : 1);
					po.startPage = po.currentPage - ((po.currentPage-1)%po.pageBlock);
					po.endPage = po.startPage + po.pageBlock - 1; //끝 페이지 구하기
					if(po.endPage > po.pageCount)po.endPage = po.pageCount;
					
						if(po.startPage > po.pageBlock){
					%>
						<img src='./img/prev.gif' border='0' align='absmiddle'  style='cursor: pointer;' onclick="parent.index._CS.init.getLryInfopage('<%=lyr_nm%>', '<%=lyr_id%>', '<%=srch_cond%>', '<%=resultcnt%>', '<%=wtk%>', '<%=po.startPage - po.pageBlock%>');"/>
					<%		
						}
					%>
					<%
						for (int i = po.startPage; i <= po.endPage; i++) {
							if(i == po.currentPage){
					%>
								<a href="#" onclick="parent.index._CS.init.getLryInfopage('<%=lyr_nm%>', '<%=lyr_id%>', '<%=srch_cond%>', '<%=resultcnt%>', '<%=wtk%>', '<%=i%>');"><b><%=i %></b></a>
					<%
							}else{
					%>
								<a href="#" onclick="parent.index._CS.init.getLryInfopage('<%=lyr_nm%>', '<%=lyr_id%>', '<%=srch_cond%>', '<%=resultcnt%>', '<%=wtk%>', '<%=i%>');"><%=i %></a>
					<%
							}
							if(i != po.endPage){
					%>
								<%="|" %>
					<%
							}
						}
					%>
					<%
					if(po.endPage < po.pageCount){
					%>
						<img src='./img/next.gif' border='0' align='absmiddle' style='cursor: pointer;' onclick="parent.index._CS.init.getLryInfopage('<%=lyr_nm%>', '<%=lyr_id%>', '<%=srch_cond%>', '<%=resultcnt%>', '<%=wtk%>', '<%=po.startPage + po.pageBlock%>');"/>
					
					<%	
					}
					%>
				</td>
			</tr>
		</table>
	</div>
	
	<div  id="excelfrm" style="display:none; position: absolute; top:50%; left:50%; margin:-100px 0 0 -160px; width:350px; height:250px; background-color:#ffffff;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" class="table_border">
			<tr>
				<td colspan="3" height="25" align="center" class="border-line2" bgcolor="#c4d7f1"><b>- 엑셀내려받기 -</b></td>
			</tr>
			<tr align="center">
				<td rowspan="3" class="border-line1" bgcolor="#c4d7f1">사용자정보</td>
				<td height="25" class="border-line1">&nbsp;사용자명</td>
				<td height="25" class="border-line2" align="left">&nbsp;<%=session_name %></td>
			</tr>
			<tr>
				<td height="25" width="20%" class="border-line1" align="center">&nbsp;사용자아이디</td>
				<td height="25" class="border-line2" align="left">&nbsp;<%=id%></td>
			</tr>
			<tr>
				<td height="25" class="border-line1" align="center">&nbsp;부서명</td>
				<td height="25" class="border-line2" align="left">&nbsp;<%=dept_nm %></td>
			</tr>
			<tr align="center">
				<td width="20%" height="80" class="border-line1" bgcolor="#c4d7f1">사용목적</td>
				<td width="80%" colspan="2" class="border-line2">
					<textarea id="use_resn" name="use_resn" rows="4" style="width:95%;"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center" height="25" colspan="3"  class="border-line2" bgcolor="#e3e4e4">* 사용목적을 입력해 주세요!</td>
			</tr>
			<tr>
				<td height="25" colspan="3" align="center" bgcolor="#e3e4e4"><a href="#" onclick="_PC.init.excelLogInsert();">확인</a> | <a href="#" onclick="_PC.init.excelFormClose();">닫기</a></td>
			</tr>
			<input type="hidden" id="user_id" name="user_id" value="<%=id%>"/><!-- 사용자아이디 -->
			<input type="hidden" id="dept_id" name="dept_id" value="<%=session_dept%>"/><!-- 부서코드 -->
			<input type="hidden" id="func_id" name="func_id" value="FW010"/><!-- 단위기능관리ID -->
			<input type="hidden" id="biz_id" name="biz_id" value="<%=session_bizid%>"/><!-- 단위업무관리ID -->
			
			<input type="hidden" id="lyr_nm" name="lyr_nm" value="<%=lyr_nm%>"/>
			<input type="hidden" id="lyr_id" name="lyr_id" value="<%=lyr_id%>"/>
			<input type="hidden" id="srch_cond" name="srch_cond" value="<%=srch_cond%>"/>
			<input type="hidden" id="wtk" name="wtk" value="<%=wtk%>"/>
		</table>
	</div>
	
	<!-- 레이어수집 정보 테이블-->
	<div id="lyrClctTable" style="display:none; border:2px solid #0051bc; text-align:center; position: absolute; top:30px; left:50%; margin:0 0 0 -190px; width:400px; height:250px; background-color: #ffffff;">
		<div style="height:40px;">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
				<tr bgcolor="#eeeeee" align="center">
					<td height="20" class='style_border_1' width="15%"><b>관리기관</b></td>
					<td class='style_border_2' width="60%"><b>갱신일</b></td>
				</tr>
			</table>
		</div>
		<div id="clctdata_box" style="border:0px solid #000000; height:180px; overflow: auto;">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
				<tr>
					<td align="center">레이어를 선택하여 주십시오!</td>
				</tr>
			</table>
		</div>
		<div style="border:0px solid #000000; height:30px; text-align: center;">
			<p style='padding:3px 0 0 0;'><a href="#" onclick="_PC.init.dataClctInfoClose();"><img src="./img/btn_2.gif" align="absmiddle"/></a></p>
		</div>
	</div>
	
</div>