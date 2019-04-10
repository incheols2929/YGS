<!-- 총괄 표제부 -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="geomex.pkg.sys.eais.Djyrecaptitle"%> 
<%@page import="geomex.svc.handler.Code"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>    
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>
<%@include file="../../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->

<%
	Object SESSION_BIZID = session.getAttribute("SESSION_BIZID");
	String session_bizid = (String)SESSION_BIZID;
	String dept_nm = Code.getDeptNM(session_dept);

	String key = new String(Base64.decodeBase64(request.getParameter("key")));
	String pnu = new String(Base64.decodeBase64(request.getParameter("pnu")));
	
	Djyrecaptitle dbl = new Djyrecaptitle();
	ArrayList<Djyrecaptitle> list = new ArrayList<Djyrecaptitle>();
	
	try {
		list = dbl.getDjyrecaptitle(key);
	} catch (Exception e) {
		e.printStackTrace();
	}
%>

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

<script type="text/javascript" src="../script/jquery-1.6.2.js"></script>
<script type="text/javascript">
function printFrmOpen(){
	$("#print_form").show();
};
function printFrmClose(){
	$("#print_form").hide();
};
function LogInsertPrint(){
	
	var use_resn = $("#use_resn").val();
	var user_id = $("#user_id").val();
	var user_name = $("#user_name").val();
	var dept_id = $("#dept_id").val();
	var func_id = $("#func_id").val();
	var biz_id = $("#biz_id").val();
	var pnu_val = $("#pnu_val").val();
	var key_val = $("#key_val").val();
	var ouln_val = $("#ouln_val").val();
	var pnu = "<%=pnu%>";
	console.log(key_val);console.log(pnu);console.log(user_name);
	
	if(use_resn == ""){
		alert("사용목적을 입력 하여주십시오!");
		$("#use_resn").focus();
		return false;
	}

	$.ajax({
		type:"POST",
		url:"../log/xml/LogRecord.jsp",
		data : {use_resn:use_resn, user_id:user_id, dept_id:dept_id, func_id:func_id, biz_id:biz_id,pnu_val:pnu_val,key_val:key_val},
		dataType : "xml",
		success: function(xml){
			if($(xml).find("result").length != 0){
				var retult = $(xml).find("result").text();
				if(retult == "success"){
					if(confirm("건축물대장을 PDF로 다운로드 하시겠습니까?")) {
						printFrmClose();
						window.opener._PC.init.BldgDjydaeJangDownload(key_val, pnu, user_name);
						window.close();
			        } else {
			            return false;
			        }
				}else{
					alert("사용목적을 다시 입력하여 주십시오!");
				}
			}
			
		},
		beforeSend : function(){},
		complete : function(){},
		error: function(xhr, status, error) {
			alert(error);
		}	
	});
}
</script>

<div style="height:17px; margin-top:5px;">
	<img src="../img/pp_img_1.gif" align="absmiddle"/> <b>건축물정보(총괄표제)</b>
</div>
<div style="border-bottom:0px solid #e3e3e3; margin-top:4px;">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="border1">
		<tr>
			<td align="center" height="22" bgcolor="#eeeded" class="border2">대지위치</td>
			<td colspan="3" class="border2">&nbsp;<%= list.get(0).ADDR %></td>
			<td align="center" bgcolor="#eeeded" class="border2">명칭 및 번호</td>
			<td class="border3">&nbsp;<%= list.get(0).BLD_NM %></td>
		</tr>
		<tr align="center">
			<td height="22"  bgcolor="#eeeded" class="border2">대지면적</td>
			<td class="border2" align="left">&nbsp;<%= list.get(0).PLATAREA %></td>
			<td bgcolor="#eeeded" class="border2">연면적</td>
			<td class="border2" align="left">&nbsp;<%= list.get(0).TOTALAREA %></td>
			<td bgcolor="#eeeded" class="border2">건축물수</td>
			<td  class="border3" align="left">&nbsp;<%= list.get(0).BL_CNT %></td>
		</tr>
		<tr align="center">
			<td height="22"  bgcolor="#eeeded" class="border2">건축면적</td>
			<td class="border2" align="left">&nbsp;<%= list.get(0).ARCHAREA%></td>
			<td bgcolor="#eeeded" class="border2">용적률산정용연면적</td>
			<td class="border2" align="left">&nbsp;<%= list.get(0).VL_RAT_AREA%></td>
			<td bgcolor="#eeeded" class="border2">총호수</td>
			<td  class="border3" align="left">&nbsp;<%= list.get(0).TOTAL_BL_NUM%></td>
		</tr>
		<tr align="center">
			<td height="22"  bgcolor="#eeeded" class="border2">건폐율</td>
			<td class="border2" align="left">&nbsp;<%= list.get(0).BC_RAT%></td>
			<td bgcolor="#eeeded" class="border2">용적율</td>
			<td class="border2" align="left">&nbsp;<%= list.get(0).VL_RAT%></td>
			<td bgcolor="#eeeded" class="border2">총주차대수</td>
			<td  class="border3" align="left">&nbsp;<%= list.get(0).PARKING_CNT%></td>
		</tr>
		<tr align="center">
			<td height="22"  bgcolor="#eeeded" class="border2">주용도</td>
			<td colspan="3" class="border2" align="left">&nbsp;<%= list.get(0).BL_USABILITY%></td>
			<td bgcolor="#eeeded" class="border2">부속건축물</td>
			<td  class="border3" align="left">&nbsp;<%= list.get(0).ACC_BL%></td>
		</tr>
		<tr align="center">
			<td height="22" bgcolor="#eeeded">특이사항</td>
			<td colspan="5" align="left"> &nbsp;<%= list.get(0).REMARK %></td>
		</tr>
	</table>
</div>

<div style="height:17px; margin-top:5px;">
	<img src="../img/pp_img_3.gif" align="absmiddle" id="slide_page_img2"/> <b>동별현황</b>
</div>
<div style=" border-bottom:0px solid #e3e3e3; margin-top:4px;">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="border1">
		<tr bgcolor="#eeeded" align="center">
			<td height="25" width="10%" class="border4">구분</td>
			<td height="25" width="10%" class="border4">건축물명칭</td>
			<td height="25" width="15%" class="border4">구조</td>
			<td height="25" width="15%" class="border4">지붕</td>
			<td height="25" width="10%" class="border4">층수</td>
			<td height="25" width="10%" class="border4">용도</td>
			<td width="10%" bgcolor="#eeeded">면적(㎡)</td>
		</tr>
	</table>
</div>
<div id="slide_page_content2" style="height:150px; display:block; border-bottom:0px solid #e3e3e3; overflow: auto;">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="table_border1">
		<%
			String style3="";
			String style4="";
			if(list.size() != 0){
				for(int i=0; i<list.size(); i++){
					if((i+1) == list.size()){
						style3="border4";
						style4="";
					}else{
						style3="border2";
						style4="border3";
					}
					
		%>
		<tr bgcolor="#f9f9f9" align="center">
			<td height="20" width="10%" class="<%=style3 %>"><%= list.get(i).BL_TYPE %></td>
			<td height="20" width="10%" class="<%=style3 %>"><%= list.get(i).BL_NM %></td>
			<td height="20" width="15%" class="<%=style3 %>"><%= list.get(i).STRCT%></td>
			<td height="20" width="15%" class="<%=style3 %>"><%= list.get(i).ROOF %></td>
			<td height="20" width="10%" class="<%=style3 %>"><%= list.get(i).FLR%></td>
			<td height="20" width="10%" class="<%=style3 %>"><%= list.get(i).USABILITY %></td>
			<td width="10%" class="<%=style4 %>"><%= list.get(i).AREA %></td>
		</tr>
		<%
				}
			}else{
		%>
			<tr bgcolor="#f9f9f9" align="center">
			<td height="60" >정보가 존재하지 않습니다.</td>
			</tr>
		<%
			}
		%>
	</table>
</div>


<div style="height:17px; margin-top:5px;">
	<img src="../img/pp_img_1.gif" align="absmiddle"/> <b>주차장</b>
</div>
<div style=" border-bottom:0px solid #e3e3e3; margin-top:4px;">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="border1">
		<tr align="center">
			<td height="40" bgcolor="#eeeded" width="10%" class="border4">옥내 <br>기계식</td>
			<td width="10%" class="border4"><span><%=list.get(0).INSIDE_MT_NUM %>대</span><br><span><%=list.get(0).INSIDE_MT_AREA + "㎡" %></span></td>
			<td width="10%" bgcolor="#eeeded" class="border4">옥내 <br>자주식</td>
			<td width="10%" class="border4"><span><%=list.get(0).INSIDE_SF_NUM %>대</span><br><span><%=list.get(0).INSIDE_SF_AREA + "㎡" %></span></td>
			<td width="10%" bgcolor="#eeeded" class="border4">옥외 <br>기계식</td>
			<td width="10%" class="border4"><span><%=list.get(0).OUTSIDE_MT_NUM %>대</span><br><span><%=list.get(0).OUTSIDE_MT_AREA + "㎡" %></span></td>
			<td width="10%" bgcolor="#eeeded" class="border4">옥외 <br>자주식</td>
			<td width="10%"><span><%=list.get(0).OUTSIDE_SF_NUM %>대</span><br><span><%=list.get(0).OUTSIDE_SF_AREA + "㎡" %></span></td>
		</tr>
	</table>
</div>

<div style="height:17px; margin-top:5px; ">
	<img src="../img/pp_img_1.gif" align="absmiddle" id="slide_page_img3"/> <b>건축물 에너지 소비정보 및 그밖의 인증 정보</b>
</div>
<div style=" border-bottom:0px solid #e3e3e3; margin-top:4px;">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="border1">
		<tr bgcolor="#eeeded" align="center">
			<td height="30" width="25%" class="border2">에너지효율</td>
			<td height="30" width="25%" class="border2">에너지성능지표(EPI) 점수</td>
			<td height="30" width="25%" class="border2">친환경건축물 인증</td>
			<td class="border3" width="25%" >지능형건축물 인증</td>
		</tr>
		<tr  align="center">
			<td height="20"class="border2"><%=list.get(0).ENG_CLASS %> 등급</td>
			<td height="20"  class="border4"  rowspan="2"> <%=list.get(0).ENG_PFMC %>점</td>
			<td height="20"class="border2"><%=list.get(0).GBL_CLASS %> 등급</td>
			<td class="border3"> <%=list.get(0).IBL_CLASS %> 등급</td>
		</tr>
		<tr align="center">
			<td height="20" class="border4">에너지절감율 <%=list.get(0).ENG_SAVE%> %</td>
			<td height="20"class="border4">인증점수 <%=list.get(0).ENG_PFMC%> 점</td>
			<td>인증점수 <%=list.get(0).IBL_NUM%> 점</td>
		</tr>
	</table>
</div>

<div align="center" style="margin-top:20px; width:100%;  border:0px solid #000000; ">
	<a href="#" onclick="printFrmOpen();"><img src="../img/pdf_down_load.gif"/></a>
</div>

<div id="print_form" style="display:none; position: absolute; top:50%; left:50%; margin:-100px 0 0 -160px; width:350px; height:250px; background-color:#ffffff;">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" class="table_border">
		<tr>
			<td colspan="3" height="25" align="center" class="border-line2" bgcolor="#c4d7f1"><b>- 건축물대장 인쇄 사용목적 입력창 -</b></td>
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
			<td align="center" height="25" colspan="3" class="border-line2" bgcolor="#e3e4e4">* 사용목적은 시스템 진입시 작성된 목적을 이용합니다! </td>
		</tr>
		<tr>
			<td height="25" colspan="3" align="center" bgcolor="#e3e4e4"><a href="#" onclick="LogInsertPrint();"><img src="../img/btn_1.gif" align="absmiddle"/></a> <a href="#" onclick="printFrmClose();"><img src="../img/btn_2.gif" align="absmiddle"/></a></td>
		</tr>
		<input type="hidden" id="user_id" name="user_id" value="<%=id%>"/><!-- 사용자아이디 -->
		<input type="hidden" id="user_name" name="user_name" value="<%=session_name%>"/><!-- 사용자명 -->
		<input type="hidden" id="dept_id" name="dept_id" value="<%=session_dept%>"/><!-- 부서코드 -->
		<input type="hidden" id="func_id" name="func_id" value="FW013"/><!-- 단위기능관리ID -->
		<input type="hidden" id="biz_id" name="biz_id" value="<%=session_bizid%>"/><!-- 단위업무관리ID -->
		<input type="hidden" id="key_val" name="key_val" value="<%=key%>"/>
	</table>
</div>
