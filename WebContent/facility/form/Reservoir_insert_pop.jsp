<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="geomex.pkg.srch.ConditionSearch"%>
<%@page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.svc.handler.Code"%>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@ include file="../../../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->
<%
request.setCharacterEncoding("UTF-8");
String ftr_idn = session_ftr_idn; //관리번호 가져옴
System.out.println(ftr_idn);
String ftr_nm = Code.getSrchRes_ftr_nm(ftr_idn); //시설명
String juso = Code.getSrchRes_juso(ftr_idn);//주소
String res_sto = Code.getSrchRes_rsv_vol(ftr_idn);
String ftr_gbn = Code.getSrchRes_ftr_gbn(ftr_idn);
System.out.println(ftr_gbn); //Y값 받아옴
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=Edge"></meta>
<title>저수지 이력관리</title>
<link href="../css/popup.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/jquery-ui.min.css"/>
<script type="text/javascript" src="../script/jquery-1.11.1.min.js" ></script>
<script type="text/javascript" src="../script/jquery-ui-1.11.1.min.js" ></script>
<script type="text/javascript" src="../script/jquery-ui-timepicker-addon.js" ></script>
<script type="text/javascript" src="../common/comm.js"></script>
<script type="text/javascript" src="../script/jquery.selectboxes.js"></script>
<script type="text/javascript" src="../script/gws.condSrch.js"></script>
<script type="text/javascript" src="../script/gws.pageCtrl.js"></script>
<script type="text/javascript">
//ftr_idn null 확인
 var ftr_idn = "<%=ftr_gbn%>"; 
if(ftr_idn != "N"){
	$(document).ready(function(){
		$("#res_ymd").datepicker({
			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			dayNamesMin:["일","월","화","수","목","금","토"],
			prevText: 'prev', 
			nextText: 'next', 
			changeMonth: true,    //달력 셀렉트 박스 유무
			changeYear: true,    //년 셀렉트 박스 유무
			buttonImageOnly : true,
			buttonImage : "../images/button/butt_calender.gif",
			dateFormat: "yy-mm-dd", //날짜 출력 형식
	 });
	});
}else{
	alert("선택하기 상자를 클릭후 진행해 주세요.");
	parent.close();
	window.close();
	self.close();
}
</script>

</head>

<body>
 <!-- 상단로고 290픽셀x39픽셀 -->
 <div id="header"  style=" background-color: #2a649d;  width: 100%; height: 59px; position: relative;">
    <h1>
     <label for="point_name">저수량 정보 입력</label>
  </h1>
 </div>
 

 <div id="inserttable" class="inserttable" style="">
     <table id="fixed" cellpadding="0" cellspacing="0" border="0" class="fixed" style="width:100%;">
        <colgroup>
         <col width="25%" /><col width="25%" /><col width="25%" /><col width="25%" />
      </colgroup>
       <tr>
        <th class="f">관리번호</th>
        <td style="text-align: center; font:12px verdana;" id="ftr_idn"><%= ftr_idn %></td>
        <th class="f" colspan="2"></th>
       </tr>
       <tr>
        <th class="f">시설명</th>
        <td style="text-align: center; font:12px verdana;" id="ftr_nm"><%= ftr_nm %></td>
        <th class="f" >날짜</th>
        <td><input type="text" id="res_ymd" class="res_ymd" style="width:100%; font-size:12px; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
       </tr>
       <tr>
        <th class="f">주소</th>
        <td style="text-align: center; font:12px verdana;" colspan="3" id="juso"><%= juso %></td>
       </tr>
       <tr>
        <th class="f">총 저수량(천㎡)</th>
        <td ><input type="text" id="res_sto" class="res_sto" value="<%=res_sto %>" style=" text-align:center; width:100%; font-size:12px; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
        <th class="f">유효 저수량(천㎡)</th>
        <td><input type="text" id="res_to" class="res_to" style="width:100%; font-size:12px; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
       </tr>
       <tr>
        <th class="f">현재저수량(천㎡)</th>
        <td colspan="3"><input type="text" id="res_eff" class="res_eff" style="width:100%; font-size:12px; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
       </tr>
       <tr>
        <th class="f">수혜면적(ha)</th>
        <td><input type="text" id="ben_area" class="ben_area" style="width:100%; font-size:12px; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
        <th class="f">유역면적(ha)</th>
        <td><input type="text" id="bas_area" class="bas_area" style="width:100%; font-size:12px; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></td>
       </tr>
       <tr style="height:201px;">
             <th class="f">메모</th>
               <td colspan="4" style="height:120px;"><textarea id="etc"name="etc" class="etc" cols="60" row="100" style="width:100%; height:100%; border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;"></textarea></td>
       </tr>
     </table> 
  </div>
  
   <div class="contents">
  <span class="save_button">
   <ul>
    <li>
      <button id="save" class="save" onclick="_SA.init.reservoir_save();">
        <span>저장</span>
      </button>
    </li>
    <li>
     <button id="cancel" class="cancel"  onclick="_SA.init.Popup_close();">
      <span>취소</span>
     </button>
    </li>
   </ul> 
  </span>
 </div>
 
 
 </body>
</html>