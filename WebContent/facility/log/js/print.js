// 필지정보를 가져온다.
function LandInfoList(ftride,gbn){
	debugger;
	var str = '';
	var $div = $(' <div class="page_layout">'
			     +' <div class="page_ctt page_break" id="LandInformattionWrap">'
			     +' </div>'
	             +'</div>'); 
	str+='<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border">';
	str+=' <tr> ';
	str+='  <td colspan="6" width="23%" height="45" align="center" class="line_border_right" style="font-weight:bold;">&nbsp;필지정보</td> ';
	str+=' </tr> ';
	str+=' <tr> ';
	str+='  <td width="13%" height="35" align="center" class="line_border_2" style="font-weight:bold;">&nbsp;지번</td> ';
	str+='	<td width="13%" height="35" align="center" class="line_border_2" style="font-weight:bold;">&nbsp;지목</td> ';
	str+='	<td width="13%" height="35" align="center" class="line_border_2" style="font-weight:bold;">&nbsp;필지면적</td> ';
	str+='	<td width="13%" height="35" align="center" class="line_border_2" style="font-weight:bold;">&nbsp;편입면적</td> ';
	str+='	<td width="13%" height="35" align="center" class="line_border_2" style="font-weight:bold;">&nbsp;소유자</td> ';
	str+='	<td width="13%" height="35" align="center" class="line_border_right" style="font-weight:bold;">&nbsp;기타</td> ';
	str+=' </tr> ';
	$.ajax({
		url: "../log/xml/LandInfoList.jsp",///전송페이지
		contentType : "text/html; charset=utf-8",//서버고 전송시킬 contnet-type을 설정
		dataType : "html", //요청한 데이터 타입
		data: {ftride:ftride,gbn:gbn},
		success: function(xml){//전송 성공하면 실행문
			$(xml).find("list").each(function(){ //시군구부터 순차적으로 하나씩 접근
				var landarea = $(this).find("land_area").text();
				var traarea = $(this).find("tra_area").text();
				var ownrnm = $(this).find("ownr_nm").text();
				var jimok = $(this).find("jimok").text();
				var etc = $(this).find("etc").text();
				var juso = $(this).find("juso").text();
				debugger;
			});
			
			
		}
	});
	str+=' <tr> ';
	str+='  <td width="13%" height="35" align="center" class="line_border_2">&nbsp;</td>'; 
	str+='	<td width="13%" height="35" align="center" class="line_border_2">&nbsp;</td>'; 
	str+='	<td width="13%" height="35" align="center" class="line_border_2">&nbsp;</td>';
	str+='	<td width="13%" height="35" align="center" class="line_border_2">&nbsp;</td>';
	str+='	<td width="13%" height="35" align="center" class="line_border_2">&nbsp;</td>';
	str+='	<td width="13%" height="35" align="center" class="line_border_right">&nbsp;</td>';
	str+=' </tr>';
	str+='</table>';
	
	$('.page_body').append($div);
  $('#LandInformattionWrap').html(str);
}

//유지보수정보를 가져온다.
function MainTenanceList(ftride,gbn){
	var str = '';
	var $div = $(' <div class="page_layout">'
			     +' <div class="page_ctt page_break" id="MainTenanceInformattionWrap">'
			     +' </div>'
	             +'</div>'); 
	str+='<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border">';
	str+=' <tr>';
	str+='  <td colspan="5" width="23%" height="45" align="center" class="line_border_right" style="font-weight:bold;">&nbsp;유지보수정보</td>';
	str+=' </tr>';
	str+=' <tr>';
	str+='  <td width="13%" height="30" align="center" class="line_border_2">&nbsp;보수일자</td>';
	str+='	<td width="13%" height="30" align="center" class="line_border_2">&nbsp;보수내역</td>';
	str+='	<td width="13%" height="30" align="center" class="line_border_2">&nbsp;시공자명</td>';
	str+='	<td width="13%" height="30" align="center" class="line_border_2">&nbsp;공사비</td>';
	str+='	<td width="13%" height="30" align="center" class="line_border_right">&nbsp;기타</td>';
	str+=' </tr>';
    str+=' <tr>';
    str+='  <td width="13%" height="35" align="center" class="line_border_2">&nbsp;</td>'; 
    str+='	<td width="13%" height="35" align="center" class="line_border_2">&nbsp;</td>';
    str+='	<td width="13%" height="35" align="center" class="line_border_2">&nbsp;</td>';
    str+='	<td width="13%" height="35" align="center" class="line_border_2">&nbsp;</td>';
    str+='	<td width="13%" height="35" align="center" class="line_border_right">&nbsp;</td>';
    str+=' </tr>';
    str+='</table>';
	
	$('.page_body').append($div);
  $('#MainTenanceInformattionWrap').html(str);
	
}
//점검정보 표출
function InspectionInfoList(ftride,gbn){
	var str = '';
	var $div = $(' <div class="page_layout">'
			     +' <div class="page_ctt page_break" id="InspectionInformattionWrap">'
			     +' </div>'
	             +'</div>'); 
	
   str+='<table border="0" cellpadding="0" cellspacing="0" width="100%" class="line_border">';
   str+=' <tr>';
   str+='  <td colspan="5" width="23%" height="45" align="center" class="line_border_right" style="font-weight:bold;">&nbsp;점검정보</td>';
   str+=' </tr>';
   str+=' <tr>';
   str+='  <td rowspan="2" width="20%" height="30" align="center" class="line_border_2">&nbsp;점검내역</td>';
   str+='  <td rowspan="2" width="13%" height="30" align="center" class="line_border_2">&nbsp;점검일</td>';
   str+='  <td colspan="2" width="26%" height="30" align="center" class="line_border_2">&nbsp;점검자</td>';
   str+='  <td rowspan="2" width="26%" height="30" align="center" class="line_border_right">&nbsp;점검결과</td>';
   str+=' </tr>';
   str+=' <tr>';
   str+='  <td width="13%" height="30" align="center" class="line_border_2">&nbsp; 직급</td>';
   str+='  <td width="13%" height="30" align="center" class="line_border_2">&nbsp; 성명</td>';
   str+=' </tr>';
   str+=' <tr>';
   str+='  <td width="13%" height="30" align="center" class="line_border_2">&nbsp;</td>'; 
   str+='  <td width="13%" height="30" align="center" class="line_border_2">&nbsp;</td>';
   str+='  <td width="13%" height="30" align="center" class="line_border_2">&nbsp;</td>';
   str+='  <td width="13%" height="30" align="center" class="line_border_2">&nbsp;</td>';
   str+='  <td width="13%" height="30" align="center" class="line_border_right">&nbsp;</td>';
   str+=' </tr>';
   str+='</table>';
	
	
	
	$('.page_body').append($div);
   $('#InspectionInformattionWrap').html(str);
}






