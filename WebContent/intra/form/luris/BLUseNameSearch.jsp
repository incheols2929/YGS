<!-- 지역지구별행위제한 -->

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.jsoup.*"%>
<%@ page import="org.jsoup.nodes.*"%>
<%@ page import="org.jsoup.select.*"%>
<%@ page import="org.apache.commons.lang3.StringUtils"%>
<%@ page import="geomex.utils.DateUtil"%>
<%
	String usrId = "";
	usrId = request.getParameter("id");

String urlString = "http://25.151.218.239:8080/intra/form/_pass/BLUseNameSearch_test.jsp";

Document doc = Jsoup.connect(urlString).get();

out.println(doc.toString());
%>
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../../../ssesion/ssesion.jsp" %>
<!-- 
/**************************************************
* 작성자 : 최기연
* 내 용 : 토지이용행위명을 api를 통해 가지고온다.
***************************************************
 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/intra/css/main.css" />
<script type="text/javascript" src="/login/script/jquery-1.11.0.js"></script>
<script type="text/javascript" src="/intra/script/gws.utils.js"></script>
<script type="text/javascript" src="/intra/script/jquery.spin.min.js"></script>
<script src="http://www.google.com/jsapi"></script>
<title>토지이용행위명</title>

<script>
var start = 0;  
var end  =  0;
var pageSize = 9;   //리스트에 보여질 글의 수
var pageBlock  = 5;  //한페지에 보여질 블럭의 수
var pageCount  =  0;   //전체 블럭의 수 계산
var startPage  =  0;   //시작블럭
var endPage  = 0;  //끝블럭
var number = 0;   // 인덱스계산을 위한 변수
var count = 0;   //db에 있는 전체 데이터의 수
var currentPage = 0;
var pageNum = 0;

var id = "<%=id%>";

$(document).ready(function(){
	BLUSEARCH(pageNum);
//	_DE.BULSEARCHreset();
	
});


function BLUSEARCH(pagenum){

	var blu_name = $('#blu_name').val();

	if(blu_name == ''){
		var str = '';
		str += '<div id="blu_contetnt">';
			str += '<div class="BLUseName_msg">';
				str += '<p>토지이용 정보 검색명을 입력 하십시오!</p>';
			str += '</div>';
		str += '</div>';
	
		$('#blu_contetnt').html(str);
		$('#blu_name').focus();
		return false;
	}	
	
	if (pageNum == 0){
		pageNum = "1"; 
	}else{
		pageNum = pagenum;
    }
	$.ajax({
		type: "POST",
		url: "./luris_proxy.jsp",
		data: {svc : "GetBLUseNameSearch", landUseNm : encodeURIComponent(blu_name), pageNo : pageNum, usrid : id},
		dataType: "xml",
		success: function(xml){
			if($(xml).find("response").length != 0){
				
				if($(xml).find("LunCd").length != 0){
				var str="";
				var pstr="";
				var bgcolor = "";
				var totalSize = $(xml).find("totalSize").text();
				var totalPage = $(xml).find("totalPage").text();
				var listSize = $(xml).find("listSize").text();
				var pageNo = $(xml).find("pageNo").text();
			
				if(totalSize != 0){
					currentPage = Number(pageNum); 
					start = (currentPage - 1) * pageSize + 1;  // 블럭당 보이는 글의 수 시작값
					end = start + pageSize - 1;     //블럭당 보이는 글의수 끝값
					count = totalSize;
					number = count - (currentPage - 1) * pageSize;  //인덱스 값 구하기
				}

				str +="<table border='0' cellpadding='0' cellspacing='0'  width='100%' height='auto'>";
				
				$(xml).find("LunCd").each(function(){
					var lun_nm =  $(this).find("LUN_NM").text();
					var lun_cd =  $(this).find("LUN_CD").text();
					var rnum =  $(this).find("RNUM").text();
					var exist_rg =  $(this).find("EXIST_RG").text();
					
					if(exist_rg == "Y"){
						bgcolor = "bgcolor='#fff5e9' ";
					}else{
						bgcolor = "";
					}
					
					str +="<tr " + bgcolor + ">";
					str +="<td height='25' style='border-bottom:1px solid #c1c1c1;'>&nbsp; <a href='#' onclick='setLUNNM(\"" + lun_nm + "\");'>" + lun_nm + "</a></td>";
					str +="</tr>";
				});
				str +="</table>";
				
				//페이징처리 ******************************************************************************************************************************
			
				pageCount = Math.floor((count / pageSize) + (count % pageSize == 0 ? 0 : 1));
				startPage = Math.floor(currentPage - ((currentPage-1)%pageBlock));
				endPage = Math.floor(startPage + pageBlock - 1); //끝 페이지 구하기
				if(endPage > pageCount)endPage = pageCount;
			
					if (startPage > pageBlock) {
						pstr += " <img src='../../img/prev.gif' border='0' align='absmiddle'  style='cursor: pointer;' onclick='BLUSEARCH("+(startPage - pageBlock)+");'/> "; 
					}else{
					}

					for (var i = startPage; i <= endPage; i++) {
						if(i == currentPage){
							pstr += " <a href='javascript:BLUSEARCH("+i+")'><b>" + i + "</b></a> ";
						}else{
							pstr += " <a href='javascript:BLUSEARCH("+i+")'>" + i + "</a> ";
						}
						if(i != endPage){
							pstr += " | ";
						}
					}
					if (endPage < pageCount) {
						pstr += " <img src='../../img/next.gif' border='0' align='absmiddle' style='cursor: pointer;' onclick='BLUSEARCH("+(startPage + pageBlock)+");'/> ";
					}else{
					}
				
				$("#blu_contetnt").html(str);
				$("#blu_page").html(pstr);
				
				}else{
					var str = '';
					str += '<div id="blu_contetnt">';
						str += '<div class="BLUseName_msg">';
							str += '<p>토지이용 정보가 존재하지 않습니다.</p>';
						str += '</div>';
					str += '</div>';
					$('#blu_contetnt').html(str);
					$('#blu_page').html('');
				}
				
			}else{
				var str = '';
				str += '<div id="blu_contetnt">';
					str += '<div class="BLUseName_msg">';
						str += '<p>토지이용 정보가 존재하지 않습니다.</p>';
					str += '</div>';
				str += '</div>';
				$('#blu_contetnt').html(str);
				$('#blu_page').html('');
			}
		},
		beforeSend : function(){
			//$("#lyr_content_box").showLoading();
		},
		complete : function(){
			//$("#lyr_content_box").hideLoading();
		},
		error: function(xhr, status, error){
				alert( error);
		}
	});
}


function setLUNNM(txt){
    opener.document.getElementById('luGrStr').value = txt;
    window.close();
}



//웹폰트 적용 하기
/*  google.load( "webfont", "1" );
 google.setOnLoadCallback(function() {
  WebFont.load({ custom: {
   families: [ "NanumGothic" ],
   urls: ["http://106.0.2.131/intra/css/webfont.css"]
  //urls: [ "http://fontface.kr/NanumGothic/css" ]
  }});
 }); */
</script>

</head>
<body onload="focus();">
	<div id="blu_top" style="height:60px; border-top:3px solid #0084ff;">
		<table border="0" cellpadding = "0" cellspacing="0" width="100%" height="100%">
			<tr align="center">
				<td colspan="2" style="border-bottom:1px solid #339dff;"><b>토지이용정보검색</b></td>
			</tr>
			<tr>
				<td style="border-bottom:1px solid #87c5ff;  background-color:#ebebeb; border-right:1px solid #87c5ff;" align="center">검색</td>
				<td style="border-bottom:1px solid #87c5ff;">&nbsp;<input type="text" id="blu_name" name="blu_name" tabindex="1" style="width:70%"/> 
				<button onclick="BLUSEARCH(0)" tabindex="2" style="cursor: pointer;">검색</button>
				<button onclick="srchLurisAreaClose();" tabindex="3" align="absmiddle" style="cursor: pointer;">닫기</button>
			</td>
			</tr>
		</table>
	</div>
	<div id="blu_contetnt" style="position:relative; border:0px solid #000000; height:360px; overflow: auto;">
	</div>
	<div id="blu_page" style="border:0px solid #000000; text-align:center; height:25px;"></div>
</body>
</html>