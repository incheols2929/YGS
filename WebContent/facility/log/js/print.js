// 필지정보를 가져8온다.
/*function LandInfoList(ftride,gbn){
	var ftride = "468702016001036"; //테스트
	var gbn = "FTR001"; //테스트
	var $div = $(' <div class="page_layout">'
		     +' <div class="page_ctt page_break" id="LandInformattionWrap">'
		     +' </div>'
            +'</div>'); 
	$.ajax({
		type : "POST",
		url: "../log/xml/LandInfoList.jsp",
		data: {ftride:ftride,gbn:gbn},
		dataType : "json",
		success : function(json){
		  var str = '';
          debugger;			
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
	      var landarea = "";
	      var traarea = "";
	      var ownrnm = "";
	      var jimok = "";
	      var juso = "";
	      var etc = "";
	      
	      $.each(json,function(key,val){
	    	   //행이 10개이상 일 경우 새 페이지
	    	  if(val.length > 10){
				for(var i=0;i< 10 ; i++){
					str+=' <tr> ';
					if(key == "juso"){
					 str+='  <td width="13%" height="35" align="center" class="line_border_2">&nbsp;'+val[i]+'</td>'; 
					}else if(key == "jimok"){
					 str+='	<td width="13%" height="35" align="center" class="line_border_2">&nbsp;'+val[i]+'</td>'; 
					}else if(key == "landarea"){
					 str+='	<td width="13%" height="35" align="center" class="line_border_2">&nbsp;'+val[i]+'</td>';
					}else if(key == "traarea"){
						str+='	<td width="13%" height="35" align="center" class="line_border_2">&nbsp;'+val[i]+'</td>';
					}else if(key == "ownrnm"){
						str+='	<td width="13%" height="35" align="center" class="line_border_2">&nbsp;'+val[i]+'</td>';
					}else if(key == "etc"){
						str+='	<td width="13%" height="35" align="center" class="line_border_right">&nbsp;'+val[i]+'</td>';
					}
				    str+=' </tr>';
				 
				}
	    	  }else{
	    		  //새 페이지 표출
	    		  
	    	  }
	      });
	  	 str+='</table>';
	  	 $('.page_body').append($div);
		$('#LandInformattionWrap').html(str);
	
	   }
	});
}*/
function LandInfoList(ftride,gbn){
	var ftride = "468702016001036"; //테스트
	var gbn = "FTR001"; //테스트
	var str = '';
	/*var $div = $(' <div class="page_layout">'
			     +' <div class="page_ctt page_break" id="LandInformattionWrap">'
			     +' </div>'
	             +'</div>');*/ 
	$.ajax({
		url: "../log/xml/LandInfoList.jsp",
		dataType : "xml", 
		data: {ftride:ftride,gbn:gbn},
		success: function(xml){
		 str+='<div class="page_layout"> ';
		 str+=' <div class="page_ctt page_break" id="LandInformattionWrap"> ';
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
		if($(xml).find('data').length != 0){
			  $(xml).find('list').each(function(){
				var landarea = $(this).find("landarea").text();
				var traarea = $(this).find("traarea").text();
				var ownrnm = $(this).find("ownrnm").text();
				var jimok = $(this).find("jimok").text();
				var etc = $(this).find("etc").text();
				var juso = $(this).find("juso").text();
				debugger;
				
				str+=' <tr> ';
				str+='  <td width="13%" height="35" align="center" class="line_border_2">&nbsp;'+juso+'</td>'; 
				str+='	<td width="13%" height="35" align="center" class="line_border_2">&nbsp;'+jimok+'</td>'; 
				str+='	<td width="13%" height="35" align="center" class="line_border_2">&nbsp;'+landarea+'</td>';
				str+='	<td width="13%" height="35" align="center" class="line_border_2">&nbsp;'+traarea+'</td>';
				str+='	<td width="13%" height="35" align="center" class="line_border_2">&nbsp;'+ownrnm+'</td>';
				str+='	<td width="13%" height="35" align="center" class="line_border_right">&nbsp;'+etc+'</td>';
				str+=' </tr>';
				str+='</table>';
			  str+=' </div>';
			  str+='</div>'; 
			});
				//$('.page_body').append($div);
				 $('#LandInformattionWrap').html(str);
		}else{
			debugger;
		}
				
			
		}
	});

	
	
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






