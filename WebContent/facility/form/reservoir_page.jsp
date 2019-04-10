<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../../../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->
<%
    request.setCharacterEncoding("UTF-8");
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
<script language=javascript>
$(function() {
	
	_CS.init.GetUmd3();
	
	  $('table tr').mouseover(function(){
	      $(this).css("backgroundColor","#ccc");
	   });
	   $('table tr').mouseout(function(){
	      $(this).css("backgroundColor","#fff");
	   }); 
	
});

</script>
</head>
<body>
<!-- 상단헤더  -->
 <div class="header">
  <h1>
     <label for="point_name">저수지 저수량 관리</label>
  </h1>
  <div class="org">
   <span>관할기관 : </span>
   <span id="agcnm">전라남도 영광군</span>
  </div>
 </div>
<!-- 중앙 -->
<div class="contents">
<!--  검색 -->
  <div class="search wl_search" >
   <fieldset>
       <label>읍면동 </label>
          <select id="umd" name="umd" class="umd" style="width: 120px; margin:7px; padding:0; font-size: 13px;" onchange="_CS.init.GetReservoir_info(this.value);">
              <option></option>
          </select>
       <label>저수지 명 </label> 
       <select id="res" name="res" class="res" style="width: 120px; margin:7px; padding:0; font-size: 13px;" onchange="">
              <option></option>
          </select>  
      <!--  <label>리 </label>
          <select id="ri" name="ri" class="ri" style="width: 120px; margin:7px; padding:0; font-size: 13px;" onchange="">
              <option></option>
          </select>   
       <label>지번 </label>
         <input type="text" id="bon" name="bon" maxlength="4" value="" style="width: 41px; height:26px; top:7px; font-size: 13px; margin-right:7px;" />
         <input type="text" id="bu" name="bu" maxlength="4" value="" style="width: 41px; height:26px; top:7px; font-size: 13px;" />
       <label>산</label>
          <input type="checkbox" id="san" name="san" style=" width:15px; margin-top:15px; padding:0; height:15px; " />  -->
  </fieldset>
  <span class="dampop_tabarea">
   <ul>
    <li>
      <button class="serch" onclick="_SA.init .SrchReset();">
        <span>검색</span>
      </button>
    </li>
    <li>
     <button id="inset" class="inset"  onclick="_PC.init.Reservoir_insert_pop();">
      <span>추가</span>
     </button>
    </li>
   </ul> 
  </span>
  </div>
 
  
  <!-- 검색결과  -->
 <div id="table_full_box"> 
  <div id="table" class="table">
     <table id="fixed" cellpadding="0" cellspacing="0" border="0" class="fixed" style="width:100%;">
      <colgroup>
      <col width="14%" /><col width="14%" /><col width="26%" /><col width="13%" /><col width="10%" /><col width="14%" /><col width="7%" />
      <col />
      </colgroup>
      <thead>
       <tr>
        <th class="f">관리번호</th>
        <th class="f">시설명</th>
        <th class="f">주소</th>
        <th class="f">준공년도</th>
        <th class="f">관리자</th>
        <th class="f">관리자연락처</th>
        <th class="f">선택하기</th>
       </tr>
      </thead>
      <tbody style="text-align: center; font:12px verdana;">
        <tr>
         <td colspan="7">데이터가 존재하지 않습니다.</td>
        </tr>
      </tbody>
     </table> 
  </div>
  <div id="page_list" style=" text-align: center">
     <p id="total_page" style="padding:3px 0 0 0; font:12px verdana; color:#505050;"></p>
  </div>
 </div>  
         <!-- 검색결과 저수율 내역 --> 
    <jsp:include page="searchresult.jsp" flush="false"/>
 </div>
</body>
</html>