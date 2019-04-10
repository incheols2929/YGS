<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="geomex.svc.handler.Code"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.pkg.srch.ConditionSearch"%>
<%@page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.ArrayList"%>
 <script type="text/javascript">
 function create_excel() {   
		window.location.href = "ReservoirInfoExcel.jsp";
	}
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Searchresult</title>
</head>
<body>
<div id="header_bar" style=" width:100%; height:auto; ">
           <ul style="list-style:none;margin:0;padding:0; width:100%; height:auto;"> 
              <li style="float:left; "><img id="img" src="../img/info_bar.png"/></li>
              <li style="float:right; padding: 5px 15px 5px 0; "><a href="#" onclick="create_excel();"><img src="../img/excel_btn.png" title="엑셀저장"></a></li>
         </ul>
   </div>
 <!-- 표  -->
 <div id="tableChart" class="tableChart" style="margin-top:10px;">
   <table cellpadding="0" cellspacing="0" border="0" class="fixed" style="width:100%;">
      <colgroup>
       <col width="11%" /><col width="11%" /><col width="26%" /><col width="11%" /><col width="8%" />
       <col width="8%" /><col width="8%" /><col width="8%" /><col width="8%" />
      </colgroup>
      <thead>
       <tr>
        <th class="f">관리번호</th>
        <th class="f">시설명</th>
        <th class="f">주소</th>
        <th class="f">날짜</th>
        <th class="f">총저수량</th>
        <th class="f">현재저수량</th>
        <th class="f">유효저수량</th>
        <th class="f">수해면적</th>
        <th class="f">유역면적</th>
       </tr>
      </thead> 
            <tbody style="text-align: center; font:12px verdana;">
        <tr>
         <td colspan="9">데이터가 존재하지 않습니다.</td>
        </tr>
      </tbody>
     </table>
 </div>
</body>
</html>