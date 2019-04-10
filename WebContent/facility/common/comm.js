if(!window._SA) _SA = {};
if(!window._PA) _PA = {};

_PA.info = {
		start : 0,  
		end : 0,
		pageSize:9,   //리스트에 보여질 글의 수
		pageBlock :5,  //한페지에 보여질 블럭의 수
		pageCount : 0,   //전체 블럭의 수 계산
		startPage : 0,   //시작블럭
		endPage :0,  //끝블럭
		number:0,   // 인덱스계산을 위한 변수
		count:0,   //db에 있는 전체 데이터의 수
		currentPage:0,
		pageNum: 0
};

/*// JavaScript Document
var userAgent   = navigator.userAgent;
var ie          = (userAgent.indexOf('MSIE') != -1);
var trident= navigator.userAgent.match(/Trident\/(\d)/i);
var ie_num= (String(trident)).split(',');
$(function() {
	// 날짜 선택
	$.datepicker.regional['ko'] = {
		inline: true, 
		dateFormat: "yy-mm-dd",     날짜 포맷  
		prevText: 'prev', 
		nextText: 'next', 
		showButtonPanel: true,     버튼 패널 사용  
		changeMonth: true,         월 선택박스 사용  
		changeYear: true,         년 선택박스 사용  
		showOtherMonths: true,     이전/다음 달 일수 보이기  
		selectOtherMonths: true,     이전/다음 달 일 선택하기  
		showOn: "both", 
		buttonImage: "/images/comm/search_cal.png", 
		buttonText: "날짜선택",
		buttonImageOnly: true,
		minDate: '-30y', 
		closeText: '닫기', 
		currentText: '오늘', 
		showMonthAfterYear: true,         년과 달의 위치 바꾸기  
		 한글화  
		monthNames : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], 
		monthNamesShort : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], 
		dayNames : ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesShort : ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesMin : ['일', '월', '화', '수', '목', '금', '토'],
		showAnim: 'fadeIn'
	};
	$.datepicker.setDefaults($.datepicker.regional['ko']);
	$.timepicker.regional['ko'] = {
			timeOnlyTitle: '시간 선택',
			timeText: '시간선택',
			hourText: '시',
			minuteText: '분',
			secondText: '초',
			closeText: '닫기', 
			currentText: '오늘', 
			minuteGrid: 10,
			timeFormat: 'HH:mm',
			amNames: ['AM', 'A'],
			pmNames: ['PM', 'P'],
			isRTL: false
		};
	$.timepicker.regional['koM'] = {
			timeOnlyTitle: '시간 선택',
			timeText: '시간선택',
			hourText: '시',
			minuteText: '분',
			secondText: '초',
			closeText: '닫기', 
			currentText: '오늘', 
			timeFormat: 'HH:mm',
			amNames: ['AM', 'A'],
			pmNames: ['PM', 'P'],
			minuteGrid: 10,
			stepMinute: 1,
			isRTL: false
	};
	$.timepicker.regional['koH'] = {
			timeOnlyTitle: '시간 선택',
			timeText: '시간선택',
			hourText: '시',
			closeText: '닫기', 
			currentText: '오늘', 
			timeFormat: 'HH',
			amNames: ['AM', 'A'],
			pmNames: ['PM', 'P'],
			isRTL: false
	};
	$.timepicker.regional['koD'] = {
			dateFormat: "yy-mm-dd",
			closeText: '닫기', 
			currentText: '오늘', 
			isRTL: false
	};
	$.timepicker.setDefaults($.timepicker.regional['ko']);
	
//	$("input.datetimepicker").datetimepicker({});
	$("input.datetimepicker").datetimepicker($.timepicker.regional['ko']);
	$("input.datetimepickerMinute").datetimepicker($.timepicker.regional['koM']);
	$("input.datetimepickerHour").datetimepicker($.timepicker.regional['koH']);
	$("input.datetimepickerDay").datepicker($.datepicker.regional['koD']);
	
	$("input.datetimepickerHour").datetimepicker({
		inline: true, 
		dateFormat: "yy-mm-dd",     날짜 포맷  
		timeFormat: 'HH', 시간 포멧
		prevText: 'prev', 
		nextText: 'next', 
		showButtonPanel: true,     버튼 패널 사용  
		changeMonth: true,         월 선택박스 사용  
		changeYear: true,         년 선택박스 사용  
		showOtherMonths: true,     이전/다음 달 일수 보이기  
		selectOtherMonths: true,     이전/다음 달 일 선택하기  
		showOn: "both", 
		buttonImage: "../images/comm/search_cal.png", 
		buttonText: "날짜선택",
		buttonImageOnly: true,
		minDate: '-30y', 
		closeText: '닫기', 
		currentText: '오늘', 
		showMonthAfterYear: true,         년과 달의 위치 바꾸기  
		 한글화  
		monthNames : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], 
		monthNamesShort : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], 
		dayNames : ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesShort : ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesMin : ['일', '월', '화', '수', '목', '금', '토'],
		showAnim: 'fadeIn',
		timeText: '시간선택',
		hourText: '시'
	});
	_CS.init.GetUmd3();
});*/
_SA.init ={
		//저수지 시설 검색
		SrchReset : function(){
			_PA.info.start = 0;  
			_PA.info.end = 0;
			_PA.info.pageSize = 6; // 화면에 보이는 최대목록 갯수 (페이지갯수를 정의)
			_PA.info.pageBlock = 5; // 화면 밑에 목록 숫자  (페이지를 나눔)
			_PA.info.pageCount = 0;
			_PA.info.startPage = 0;
			_PA.info.endPage = 0;
			_PA.info.number = 0;
			_PA.info.count = 0;
			_PA.info.currentPage = 0;
			_PA.info.pageNum = 0;
			_SA.init.SrchinfoPaging(0);
		},
		SrchinfoPaging : function(pagenum){
			var umd = $("#umd").val();
			var res = $("#res").val();
			
			if(umd == ""){
				alert("읍면동을 선택하여 주십시오!");
				$("#umd").focus();
				return false;
			}
			
			if(_PA.info.pageNum == 0){ 
				_PA.info.pageNum = 1; 
	    	}else{
	    		_PA.info.pageNum = pagenum;
		    }
			
			  $.ajax({
		          type: "POST",
		          url:"../search/cntxml/Srch_ReservoirCnt.jsp",
		          data : {umd:umd,res:res}, //서버로 전송할 데이터
		          dataType: "xml",
		          success:function(xml){
		        		if($(xml).find("data").length != 0){
							_PA.info.currentPage = _PA.info.pageNum;
							_PA.info.start = (_PA.info.currentPage - 1) * _PA.info.pageSize + 1;  // 블럭당 보이는 글의 수 시작값
							_PA.info.end = _PA.info.start + _PA.info.pageSize - 1;     //블럭당 보이는 글의수 끝값
							_PA.info.count = $(xml).find("data").find("tot").text();
							_PA.info.number = _PA.info.count - (_PA.info.currentPage - 1) * _PA.info.pageSize;  //인덱스 값 구하기
							_SA.init.GetSrchReservoir_Result(umd,res , _PA.info.start, _PA.info.pageSize); //지번검색
							
	             }
		        }
		     });
		},
		GetSrchReservoir_Result : function(umd,res,start,pageSize){
			 $.ajax({
				 type: "POST",
		          url:"../search/cntxml/GetSrchReservoir_Result.jsp",
		          data : {umd:umd,res:res,snum:start,pagenum:pageSize}, //서버로 전송할 데이터
		          dataType: "xml",
		          success:function(xml){
		          var str="";
		          str+="<table cellpadding='0' cellspacing='0' border='0' class='fixed' style='width:100%;'>";
		          str+="<colgroup>";
		        	  str+="<col width='14%' /><col width='14%' /><col width='26%' /><col width='13%' /><col width='10%' /><col width='14%' /><col width='7%' />";
		        	  str+="<col />";
		        		  str+="</colgroup>";
		        			  str+="<thead>";
		        				  str+=" <tr>";
		        					  str+="<th class='f'>관리번호</th>";
		        					  str+="<th class='f'>시설명</th>";
		        					  str+="<th class='f'>주소</th>";
		        					  str+="<th class='f'>준공년도</th>";
		        					  str+="<th class='f'>관리자</th>";
		        					  str+="<th class='f'>관리자연락처</th>";
		        					  str+="<th class='f'>선택하기</th>";
		        					  str+="</tr>";
		        						  str+="</thead>";
		        	  
		        						  if($(xml).find('리스트').length != 0){
		  									$(xml).find('리스트').each(function(idx, item){
		  										var ftr_idn = $(this).find("관리번호").text();
		  										var ftr_nm = $(this).find("시설명").text();
		  										var juso = $(this).find("주소").text();
		  										var fns_ymd = $(this).find("준공년도").text();
		  										var mng_nam = $(this).find("관리자").text();
		  										var tel_num = $(this).find("관리자연락처").text();
		  										
		  										str+="<tbody style='text-align: center; font:12px verdana;'>";
		  										    str+="<tr style='cursor:pointer;' onmouseover='this.style.background=\"#fcecae\";' onmouseleave='this.style.background=\"#FFFFFF\";' >";
		  										      str+="<td width='14%' onclick='_SA.init.Reservoir_info_page(\""+ftr_idn+"\")'>"+ ftr_idn+"<a href='#'></a></td>";
		  										      str+="<td width='14%' onclick='_SA.init.Reservoir_info_page(\""+ftr_idn+"\");'>"+ ftr_nm+"<a href='#' onclick=''></a></td>";
		  										      str+="<td width='26%' onclick='_SA.init.Reservoir_info_page(\""+ftr_idn+"\");'>"+ juso+"<a href='#' onclick=''></a></td>";
		  										      str+="<td width='13%'>"+ fns_ymd+"</td>";
		  										      str+="<td width='10%'>"+ mng_nam+"</td>";
		  										      str+="<td width='14%'>"+ tel_num+"</td>";
		  										    str+="<td width='7%'><input type='checkbox' id='sel' name='sel' onclick='_SA.init.Search_info(\""+ftr_idn+"\");'style=' width:13px; margin-top:5px; padding:0; height:13px; ' /></td>";
		  											str+="</tr>";
		  									    str+="</tbody>";
		  									});
		  									 str += "</table>";
		  									 $("#table").html(str);
		  									 $("#total_page").html(_SA.init.Resvoir_pageing()); //신고인정보페이징
		  									 
		  					         }else{
		  					        	alert("데이터가 존재하지 않습니다. 다시 확인 후 검색해주세요.");
		  					        	str += " <tbody style='text-align: center; font:12px verdana;'>";
		  					        	str += " <tr>";
		  					        		str += " <td colspan='7'>데이터가 존재하지 않습니다.</td>";
		  					        		str += "</tr>";
		  					        			str += " </tbody>";
		  					        				str += "</table> ";
		  					        				
		  					        	 $("#table").html(str);
		  					        	 $("#total_page").html("");
		  					         }
		  			          },
		  			          complete : function(){
		          },
		     });
		},
		Search_info : function(ftr_idn){
			var ftr_idn=ftr_idn;
			var lists = [];
			 $("input[name='sel']:checked").each(function(i){   //jQuery로 for문 돌면서 check 된값 배열에 담는다
				   lists.push($(this).val());
				   //클릭 이벤트 발생한 요소가 체크 상태인 경우
			        if ($(this).prop('checked')) {
			            //체크박스 그룹의 요소 전체를 체크 해제후 클릭한 요소 체크 상태지정
			            $('input[type="checkbox"][name="sel"]').prop('checked', false);
			            $(this).prop('checked', true);
			        }
		     });
			 
			if(lists == "on" || lists =="on,on"){
			 $.ajax({
				 type: "POST",
				 data:{'sel':lists,ftr_idn:ftr_idn},
				 url:"/facility/search/cntxml/Session_ftr_idn.jsp",
				 dataType: "xml",
			     success:function(xml){
			    	 location.href="/facility/form/reservoir_page.jsp";
			    	 
			     }
			 });
			}else{
				$.ajax({
					 type: "POST",
					 data:{'sel':lists,ftr_idn:ftr_idn},
					 url:"/facility/search/cntxml/Session_remove_ftr_idn.jsp",
					 dataType: "xml",
				     success:function(xml){
				    	 location.href="/facility/form/reservoir_page.jsp";
				    	 
				     }
				 });
				
			}
		},
		//팝업창 닫기
		Popup_close : function(){
			var ulr = "/facility/form/Reservoir_insert_pop.jsp";
			$.ajax({
				 type: "POST",
				 data:{ftr_idn:ftr_idn},
				 url:"/facility/search/cntxml/Session_remove_ftr_idn.jsp",
				 dataType: "xml",
			     success:function(xml){
			    	 window.close();
			     }
			 });
			 window.close();
		},
			
		//저수지 검색결과 출력
		Reservoir_info_page : function(ftr_idn){
			var ftr_idn = ftr_idn;             //관리번호
			var ftr_nm = ftr_nm;           //시설명
			var juso = juso;			       //주소
			var res_ymd = res_ymd;    //날짜
			var res_sto = res_sto;       //총저수량
			var res_to = res_to;          //현재저수량
			var res_eff = res_eff;        //유효저수량
			var ben_area = ben_area; //수해면적
			var bas_area = bas_area; //유역면적
			var etc = etc;                   //기타
			
			//excel다운을 위한 세션저장
			var lists = [];
			 $("input[name='sel']:checked").each(function(i){   //jQuery로 for문 돌면서 check 된값 배열에 담는다
				   lists.push($(this).val());
				   //클릭 이벤트 발생한 요소가 체크 상태인 경우
			        if ($(this).prop('checked')) {
			            //체크박스 그룹의 요소 전체를 체크 해제후 클릭한 요소 체크 상태지정
			            $('input[type="checkbox"][name="sel"]').prop('checked', false);
			            $(this).prop('checked', true);
			        }
		     });
			 
			if(lists =! "on" || lists != "on,on"){
			 $.ajax({
				 type: "POST",
				 data:{'sel':lists,ftr_idn:ftr_idn},
				 url:"/facility/search/cntxml/Session_ftr_idn.jsp",
				 dataType: "xml",
			     success:function(xml){
			    	 location.href="/facility/form/reservoir_page.jsp";
			    	 
			     }
			 });
			}
			$.ajax({
		          type: "POST",
		          url:"../search/cntxml/GetReservoirInfoResult.jsp",
		          data : {ftr_idn:ftr_idn}, //서버로 전송할 데이터
		          dataType: "xml",
		          success:function(xml){
		          var str="";
		          str+="<table cellpadding='0' cellspacing='0' border='0' class='fixed' style='width:100%;'>";
		          str+="<colgroup>";
		        	  str+="<col width='11%' /><col width='11%' /><col width='26%' /><col width='11%' /><col width='8%' /><col width='8%' /><col width='8%' /><col width='8%' /><col width='8%' />";
		        	  str+="<col />";
		        		  str+="</colgroup>";
		        			  str+="<thead>";
		        				  str+=" <tr>";
		        					  str+="<th class='f'>관리번호</th>";
		        					  str+="<th class='f'>시설명</th>";
		        					  str+="<th class='f'>주소</th>";
		        					  str+="<th class='f'>날짜</th>";
		        					  str+="<th class='f'>총저수량</th>";
		        					  str+="<th class='f'>현재저수량</th>";
		        					  str+="<th class='f'>유효저수량</th>";
		        					  str+="<th class='f'>수해면적</th>";
		        					  str+="<th class='f'>유역면적</th>";
		        					  str+="</tr>";
		        						  str+="</thead>";
		        	  
		        						  if($(xml).find('리스트').length != 0){
		  									$(xml).find('리스트').each(function(idx, item){
		  										var ftr_idn = $(this).find("관리번호").text();
		  										var ftr_nm = $(this).find("시설명").text();
		  										var juso = $(this).find("주소").text();
		  										var res_ymd = $(this).find("날짜").text();
		  										var res_sto = $(this).find("총저수량").text();
		  										var res_to = $(this).find("현재저수량").text();
		  										var res_eff = $(this).find("유효저수량").text();
		  										var ben_area = $(this).find("수해면적").text();
		  										var bas_area = $(this).find("유역면적").text();
		  										var a_null ="";
		  										
		  										str+="<tbody style='text-align: center; font:12px verdana;'>";
		  										    str+="<tr style='cursor:pointer;' onmouseover='this.style.background=\"#fcecae\";' onmouseleave='this.style.background=\"#FFFFFF\";'>";
		  										      str+="<td width='11%'>" + ftr_idn +"</td>";
		  										      str+="<td width='11%'>"+ ftr_nm +"</td>";
		  										      str+="<td width='26%'>"+ juso  +"</td>";
		  										      str+="<td width='11%'>"+ res_ymd+"</td>";
		  										      if(res_eff == "" || res_eff == "null"){
		  										    	str+="<td width='8%'>"+ a_null + "</td>"; 
		  										      }else{  
		  										    	  str+="<td width='8%'>"+ res_sto+"</td>";
		  										      }
		  										    if(res_eff == "" || res_eff == "null"){
		  										    	str+="<td width='8%'>"+ a_null + "</td>"; 
		  										    }else{
		  										      str+="<td width='8%'>"+ res_to+"</td>";
		  										      }
		  										      if(res_eff == "" || res_eff == "null"){
		  										    	str+="<td width='8%'>"+ a_null + "</td>"; 
		  										    }else{
		  										    	  str+="<td width='8%'>"+ res_eff +"</td>";
		  										   }
		  										    
		  										  if(ben_area == "" || ben_area == "null"){
		  											str+="<td width='8%'>"+ a_null + "</td>"; 
		  										  }else{
		  											str+="<td width='8%'>"+ ben_area+ "</td>"; 
		  										  }
		  											if(bas_area == "" || bas_area == "null"){
		  												str+="<td width='8%'>"+ a_null+ "</td>";		
		  											}else{
		  												str+="<td width='8%'>"+ bas_area+ "</td>";		
		  											}
		  										
		  											str+="</tr>";
		  									    str+="</tbody>";
		  									});
		  									 str += "</table>";
		  									 $("#tableChart").html(str);
		  									 $("#total_page").html(_SA.init.Resvoir_pageing()); //신고인정보페이징
		  					         }else{
		  					        	alert("데이터가 존재하지 않습니다. 다시 확인 후 검색해주세요.");
		  					        	str += " <tbody style='text-align: center; font:12px verdana;'>";
		  					        	str += " <tr>";
		  					        		str += " <td colspan='9'>데이터가 존재하지 않습니다.</td>";
		  					        		str += "</tr>";
		  					        			str += " </tbody>";
		  					        				str += "</table> ";
		  					        				
		  					        	 $("#tableChart").html(str);
		  					        	 $("#total_page").html("");
		  					         }
		  			          },
		  			          complete : function(){
		          },
		     });
			
			
			

		},
		
		
		//저수지 저수량 저장
		reservoir_save : function(){
			var ftr_idn = $("#ftr_idn").text();
			var ftr_nm = $("#ftr_nm").text();
			var juso = $("#juso").text();
			var res_ymd = $("#res_ymd").val();
			var res_sto = $("#res_sto").text();
			var res_to = $("#res_to").val();
			var res_eff = $("#res_eff").val();
			var ben_area=$("#ben_area").val();
			var bas_area=$("#bas_area").val();
			var etc = $("#etc").val();
			if(res_ymd == ""){
				alert("날짜를 선택해주세요!");
				$("#res_ymd").focus();
				return false;
			}
			
			$.ajax({
				type:"POST",
				url:"/facility/search/cntxml/reservoir_save.jsp", 
				data : {ftr_idn:ftr_idn,ftr_nm:ftr_nm,juso:juso,res_ymd:res_ymd,res_sto:res_sto,res_to:res_to,res_eff:res_eff,ben_area:ben_area,bas_area:bas_area,etc:etc},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("result").length != 0){
						if($(xml).find("result").text() == "success"){
							alert("저수량 신규정보 등록을 완료하였습니다.");
							parent.close();
							window.close();
							self.close();
						}else if($(xml).find("result").text() == "fail"){
							alert("등록에 실패 하였습니다.\n다시 입력하여 주십시오!");
							$("#res_ymd").val("");
							$("#res_ymd").focus();
						}
					}
				},
				complete : function(){
					
				},
				error: function(xhr, status, error) {
					
				}	
			});
		},
		
		//검색 페이징 처리
		Resvoir_pageing : function(){
			var pstr="";
			_PA.info.pageCount = Math.floor((_PA.info.count / _PA.info.pageSize) + (_PA.info.count % _PA.info.pageSize == 0 ? 0 : 1));
			_PA.info.startPage = Math.floor(_PA.info.currentPage - ((_PA.info.currentPage-1)%_PA.info.pageBlock));
			_PA.info.endPage = Math.floor(_PA.info.startPage + _PA.info.pageBlock - 1); //끝 페이지 구하기
			if(_PA.info.endPage > _PA.info.pageCount)_PA.info.endPage = _PA.info.pageCount;
					if (_PA.info.startPage > _PA.info.pageBlock) {
						pstr += " <img src='./img/prev.gif' border='0' align='absmiddle'  style='cursor: pointer;' onclick='_SA.init.SrchinfoPaging("+(_PA.info.startPage - _PA.info.pageBlock)+");'/> "; 
					}else{
					}

					for (i = _PA.info.startPage; i <= _PA.info.endPage; i++) {
						if(i == _PA.info.currentPage){
							pstr += " <a href='javascript:_SA.init.SrchinfoPaging("+i+")'></a> ";
						}else{
							pstr += " <a href='javascript:_SA.init.SrchinfoPaging("+i+")'>" + i + "</a> ";
						}
						if(i != _PA.info.endPage){
							pstr += " | ";
						}
					}
					if (_PA.info.endPage < _PA.info.pageCount) {
						pstr += " <img src='./img/next.gif' border='0' align='absmiddle' style='cursor: pointer;' onclick='_SA.init.SrchinfoPaging("+(_PA.info.startPage + _PA.info.pageBlock)+");'/> ";
					}else{
					}
				return pstr;
		}
		
		
};
