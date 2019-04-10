if(!window._CS) _CS = {};
if(!window._PA) _PA = {};
if(!window._DE) _DE = {};

var LYR_TEMP  = new Array();
var SELECT_TEMP  = new Array();
var SEARCH_LINK_TYPE = "ADDR";

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

_CS.init = {
		////외부연계검색타입을 설정한다.
		setSearchLinkType : function(type){
			SEARCH_LINK_TYPE = type;
		},
		//지번주소검색조건 페이지를 불러온다.
		srchjibunLoding : function(obj){
			if(obj == 1){
				$.ajax({
					url: "./search/srchJibun.jsp?imgch="+obj,
					contentType : "text/html; charset=utf-8",
					dataType : "html",
					success: function(htm){
						outerLayout.open("west");
						$("#con_sch_full_box").html(htm);
						reSize(); //사이즈재설정
						_CS.init.GetSGG(); //시군구 목록을 가져오기 위해
					},
					beforeSend : function(){
						//$("#con_sch_full_box").showLoading();
					},
					complete : function(){
						//$("#con_sch_full_box").hideLoading();
					},
					error: function(xhr, status, error){
							alert(">>>>>>>>>>> : 에러");
					}
				});
			}else{
				this.srchjibunNewLoding(); //새주소에서 검색
			}
				
		},
		//새주소에서 검색 2011-12-26 추가
		srchjibunNewLoding : function(){
			$.ajax({
				url: "./search/srchJibunNew.jsp", //전송페이지
				contentType : "text/html; charset=utf-8",//서버고 전송시킬 contnet-type을 설정
				dataType : "html", //요청한 데이터 타입
				success: function(htm){//전송 성공하면 실행문
					outerLayout.open("west");//화면 왼쪽에 레이아웃 오픈
					$("#con_sch_full_box").html(htm);
					reSize(); //사이즈재설정
					_CS.init.GetSGG();
					$("#sch_line").html("");
					
					gwsUtils.setWorkImgChange('1');
					if(SEARCH_LINK_TYPE == "KLIS"){
						$("#help_txt").text("* 토지대장을 검색합니다.");
						gwsUtils.setWorkImgChange('9');
					}else if(SEARCH_LINK_TYPE == "EAIS"){
						$("#help_txt").text("* 건축물대장을 검색합니다.");
						gwsUtils.setWorkImgChange('10');
					}else if(SEARCH_LINK_TYPE == "LURIS"){
						$("#help_txt").text("* 토지이용규제를 검색합니다.");
						gwsUtils.setWorkImgChange('11');
					}
					_CS.init.GetUMD(4687000000);
				},
				beforeSend : function(){
					//$("#con_sch_full_box").showLoading();
				},
				complete : function(){
					//$("#con_sch_full_box").hideLoading();
				},
				error: function(xhr, status, error){
						alert(">>>>>>>>>>> : 에러");
				}
			});	
		},
		//시군구목록을가져온다
		GetSGG : function(sggcode){
			$("#sgg").removeOption(/./);
		    $("#sgg").addOption("", ":: 선택하여주십시오! ::", true); //초기화시 ""공란에 값추가
			$("#umd").removeOption(/./);
		    $("#umd").addOption("", ":: 선택하여주십시오! ::", true);
		    $("#ri").removeOption(/./);
		    $("#ri").addOption("", ":: 선택하여주십시오! ::", true);
		    
		    $.ajax({
				type: 'POST',
		    	url: '../../svc',
		    	data : {svc : "GetSGG"},
		    	dataType: 'xml',
		    	success: function(xml){
					var conditions_box = "";
					$(xml).find("시군구").each(function(){ //시군구부터 순차적으로 하나씩 접근
						var code = $(this).find("시군구코드").text();//this요소에 하위요소인 시군구코드를 선택,선택요소에 포함하는 텍스트를 반환해서 변수code에 담음
						var name = $(this).find("시군구명").text();//this요소에 하위요소인 시군구명을 선택,선택요소에 포함하는 텍스트를 반환해서 변수에 담음
						if(name === "영광군"){ 
							conditions_box += "<option value='" + code + "' selected>" + name + "</option>";
						}
					});
					$("#sgg").append(conditions_box).prop("disabled", "disabled"); //선택한태그 sgg에 새 속성과 값을 생성하거나 기존의 속성을 변경
				},
		    	error: function(){ 
		        	alert('Error loading XML document'); 
		    	}
			});
		},
		//읍면동 목록을가져온다.
		GetUMD : function(sggcode){
			
	       $("#umd").removeOption(/./);
	       $("#umd").addOption("", ":: 선택하여주십시오! ::", true);
	       $("#ri").removeOption(/./);
	       $("#ri").addOption("", ":: 선택하여주십시오! ::", true);
	       
	       if(sggcode != ""){
			   $.ajax({
			       type: "POST",
			       url: "../../svc",
			       data: {svc : "GetUMD", sgg : sggcode},
			       dataType: "xml",
			       success: function(xml) {
			    	   var conditions_box = "";
			           $(xml).find('읍면동').each(function(idx){
			               var code = $(this).find('읍면동코드').text();
			               var name = $(this).find('읍면동명').text();
			               conditions_box += "<option value='" + code + "'>" + name + "</option>";
				           //$("#umd").addOption(code, name, false);
			           });
			           $("#umd").append(conditions_box);
			       },
			       error: function(){
			           alert("에러");
			       }
			   	});
	       }
		},
		//리 목록을가져온다.
		GetRI : function(umdcode){
			
	       $("#ri").removeOption(/./);
	       $("#ri").addOption("", ":: 선택하여주십시오! ::", true);
	       if(umdcode != ""){
			   $.ajax({
			       type: "POST",
			       url: "../../svc",
			       data: {svc : "GetRI", umd : umdcode},
			       dataType: "xml",
			       success: function(xml) {
			    	   var conditions_box = "";
			           $(xml).find('리').each(function(idx){
			               var code = $(this).find('리코드').text();
			               var name = $(this).find('리명').text();
			               conditions_box += "<option value='" + code + "'>" + name + "</option>";
				           //$("#ri").addOption(code, name, false);
			           });
			           $("#ri").append(conditions_box);
			       },
			       error: function(){
			           alert("에러");
			       }
			   	});
	       }
		},
		GetCodeValue : function(sggcode){
			$("#decode").val(sggcode.substring(0,5));
			if(sggcode.substring(5,10) !== "00000"){
				$("#decode").val(sggcode);
			}
		},
		//// 추가
		GetCodeView : function(code){
			if($("#decode").length){
				//$("#decode").val(code);
				if($("#decode").val().length === 10){
					$("#decode").val($("#decode").val().substring(0,5)+""+code.substring(5, 10));
				}else{
					$("#decode").val($("#decode").val()+""+code.substring(5, 10));
				}
			}
		},
		GetKeyCode : function(code){
			if(!((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105)|| event.keyCode == 8)){
				alert("숫자만 입력하여 주십시오!");
				return false;
			}else{
				/*if(code.length == 10){
					this.GetUMD0904("4281000000", code);
				}*/
				if(code.length == 10){
					this.GetUMD0904($("#decode").val().substring(0,5)+""+"00000", code);
				}
			}
			
			
		},
		GetUMD0904 : function(sggcode, objcode){
			   var umdcode = objcode.substring(0, 7) + "000";
				
		       $("#umd").removeOption(/./);
		       $("#umd").addOption("", ":: 선택하여주십시오! ::", true);
		       $("#ri").removeOption(/./);
		       $("#ri").addOption("", ":: 선택하여주십시오! ::", true);
		       
		       if(sggcode != ""){
				   $.ajax({
				       type: "POST",
				       url: "../../svc",
				       data: {svc : "GetUMD", sgg : sggcode},
				       dataType: "xml",
				       success: function(xml) {
				    	   var conditions_box = "";
				           $(xml).find('읍면동').each(function(idx){
				               var code = $(this).find('읍면동코드').text();
				               var name = $(this).find('읍면동명').text();
				               if(code == umdcode){
				            	   conditions_box += "<option value='" + code + "' selected='selected'>" + name + "</option>";
				               }else{
				            	   conditions_box += "<option value='" + code + "'>" + name + "</option>";
				               }
				           });
				           $("#umd").append(conditions_box);
				           
				           _CS.init.GetRI0904(umdcode, objcode);
				           
				       },
				       error: function(){
				           alert("에러");
				       }
				   	});
		       }
			},
			GetRI0904 : function(umdcode, objcode){
			   			
		       $("#ri").removeOption(/./);
		       $("#ri").addOption("", ":: 선택하여주십시오! ::", true);
		       if(umdcode != ""){
				   $.ajax({
				       type: "POST",
				       url: "../../svc",
				       data: {svc : "GetRI", umd : umdcode},
				       dataType: "xml",
				       success: function(xml) {
				    	   var conditions_box = "";
				           $(xml).find('리').each(function(idx){
				               var code = $(this).find('리코드').text();
				               var name = $(this).find('리명').text();
				               
				               if(code == objcode){
				            	   conditions_box += "<option value='" + code + "' selected='selected'>" + name + "</option>";
				               }else{
				            	   conditions_box += "<option value='" + code + "'>" + name + "</option>";
				               }
				           });
				           $("#ri").append(conditions_box);
				       },
				       error: function(){
				           alert("에러");
				       }
				   	});
		       }
			},
		//pnu값을만든다.
		createPNU : function(){
			
			var dcode = "";
			var pnu;
			var sgg = $("#sgg").val();
			var umd = $("#umd").val();
			var ri = $("#ri").val();
			var bon = $("#bon").val();
			var bu = $("#bu").val();
			var san = $("input:checkbox[name=san]:checked").val();
			var len1,len2;
			
			if(!umd){
				dcode = sgg;
			}else{
				dcode = umd;
			}
			
			if(!ri){
				dcode = umd;
			}else{
				dcode = ri;
			}
			
			if(san == "on"){
				san = "2";
			}else{
				san = "1";
			}
			
			len1 = (4-bon.length);
			len2 = (4-bu.length);

			for(var i=0; i<len1; i++){
				bon = "0"+bon;
			}
			for(var i=0; i<len2; i++){
				bu = "0"+bu;
			}	
		},
		jibunReset : function(){
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
			_CS.init.Jibunpaging(0);
		},
		jibunNewReset : function(){
			_PA.info.start = 0;  
			_PA.info.end = 0;
			_PA.info.pageSize = 6;
			_PA.info.pageBlock = 5;
			_PA.info.pageCount = 0;
			_PA.info.startPage = 0;
			_PA.info.endPage = 0;
			_PA.info.number = 0;
			_PA.info.count = 0;
			_PA.info.currentPage = 0;
			_PA.info.pageNum = 0;
			_CS.init.JibunNewpaging(0);
		},
		Jibunpaging : function(pagenum){
			
			var sgg = $("#sgg").val(); //입력된 이름을 변수 sgg에 담음
			var umd = $("#umd").val();  // 위와 동일
			var ri = $("#ri").val(); 
			var bon = $("#bon").val();
			var bu = $("#bu").val();//동일
			if(SEARCH_LINK_TYPE === "KLIS" || SEARCH_LINK_TYPE === "EAIS" || SEARCH_LINK_TYPE === "LURIS"){
				if($("#bu").val() == ""){
					bu = "0";
				}
			}
			var san = $("#san").val(); //입력된 이름을 변수 san에 담음
			
			if(sgg == ""){ 
				alert("시군구를 입력하여 주십시오!");
				$("#sgg").focus();
				return false;
			}
			
			if(umd == ""){
				alert("읍면동을 선택하여 주십시오!");
				$("#umd").focus();
				return false;
			}
			
			if($("#san").is(":checked")){
				san = "2";
			}else{
				san = "1";
			}
			
			if(_PA.info.pageNum == 0){ 
				_PA.info.pageNum = 1; 
	    	}else{
	    		_PA.info.pageNum = pagenum;
		    }
			
			$.ajax({ //외부에 데이터를 전송하거나 요청
				type:"GET",
				url:"./search/cntxml/jibun_cnt.jsp",
				data : {sgg:sgg, umd:umd, ri:ri, bon:bon, bu:bu, san:san}, //서버로 전송할 데이터
				dataType : "xml",
				success: function(xml){ //성공헀을 때
					if($(xml).find("data").length != 0){
						_PA.info.currentPage = _PA.info.pageNum;
						_PA.info.start = (_PA.info.currentPage - 1) * _PA.info.pageSize + 1;  // 블럭당 보이는 글의 수 시작값
						_PA.info.end = _PA.info.start + _PA.info.pageSize - 1;     //블럭당 보이는 글의수 끝값
						_PA.info.count = $(xml).find("data").find("tot").text();
						_PA.info.number = _PA.info.count - (_PA.info.currentPage - 1) * _PA.info.pageSize;  //인덱스 값 구하기
						_CS.init.GetResultContent(sgg, umd, ri, bon, bu, san, _PA.info.start, _PA.info.pageSize); //지번검색
					}
				},
				beforeSend : function(){
					$("#result_content_box").showLoading();
		        },
		        error: function(xhr, status, error) {
						alert("에러");
				}
			});
		},
		
		JibunNewpaging : function(pagenum){
			
			var sgg = $("#sgg").val();
			var umd = $("#umd").val();
			var ri = $("#ri").val();
			var bon = $("#bon").val();
			var bu = $("#bu").val();
			var san = $("#san").val();
			
			if(sgg == ""){
				alert("시군구를 입력하여 주십시오!");
				$("#sgg").focus();
				return false;
			}
			
			if(umd == ""){
				alert("읍면동을 선택하여 주십시오!");
				$("#umd").focus();
				return false;
			}
			
			if($("#san").is(":checked")){
				san = "2";
			}else{
				san = "1";
			}
			
			if (_PA.info.pageNum == 0){ 
				_PA.info.pageNum = 1; 
	    	}else{
	    		_PA.info.pageNum = pagenum;
		    }
			
			$.ajax({
				type:"GET",
				url:"./search/cntxml/jibunNew_cnt.jsp",
				data : {sgg:sgg, umd:umd, ri:ri, bon:bon, bu:bu, san:san},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("data").length != 0){
						_PA.info.currentPage = Number(_PA.info.pageNum); 
						_PA.info.start = (_PA.info.currentPage - 1) * _PA.info.pageSize + 1;  // 블럭당 보이는 글의 수 시작값
						_PA.info.end = _PA.info.start + _PA.info.pageSize - 1;     //블럭당 보이는 글의수 끝값
						_PA.info.count = $(xml).find("data").find("tot").text();
						_PA.info.number = _PA.info.count - (_PA.info.currentPage - 1) * _PA.info.pageSize;  //인덱스 값 구하기
						_CS.init.GetResultNewContent(sgg, umd, ri, bon, bu, san, _PA.info.start, _PA.info.pageSize);
					}
				},
				error: function(xhr, status, error) {
						alert("에러");
				}
			});
		},
		
		////지번검색
		GetResultContent : function(sgg, umd, ri, bon, bu, san, start, pageSize){
			$.ajax({
			       type: "POST",
			       url: "../../svc",
			       data: {svc : "GetAddrSrchList", sgg:sgg, umd:umd, ri:ri, bon:bon, bu:bu, san:san, snum:start, pagenum:pageSize},
			       dataType: "xml",
			       success: function(xml){
			    	   var str="";
			    	   if($(xml).find('리스트').length != 0){
			    		   $(xml).find('리스트').each(function(idx, item){
			    			var pnu = $(this).find("pnu").text();
			    			var jibunaddr = $(this).find("지번주소").text();
			    			var doroaddr = $(this).find("도로주소").text();
			    			var bldgname = $(this).find("빌딩명").text();
			    			var key = $(this).find("도로키값").text();
			    			var lrynm = $(this).find("레이어명").text();
			    			
							str+="<div style='margin-top:3px;'>";
							str+="<table border='0' cellpadding='0' cellspacing='0' width='100%;' height='auto' bgcolor='#ffffff' style='border:1px solid #86c0ef'>";
								str+="<tr align='center'>";
									str+="<td height='20' width='25%' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>지번</td>";
									if(SEARCH_LINK_TYPE === "KLIS"){
										str+="<td width='65%' align='left' style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\"); _DE.tojiInfo(\"" + pnu + "\",\""+jibunaddr+"\");'>" + jibunaddr + "</a></td>";
									}else if(SEARCH_LINK_TYPE === "EAIS"){
										str+="<td width='65%' align='left' style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\"); _DE.bldgShortInfo(\"" + pnu + "\",\""+jibunaddr+"\");'>" + jibunaddr + "</a></td>";
									}else if(SEARCH_LINK_TYPE === "LURIS"){
										str+="<td width='65%' align='left' style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\"); _DE.lurisInfo(\"" + pnu + "\",\""+jibunaddr+"\");'>" + jibunaddr + "</a></td>";
									}else if(SEARCH_LINK_TYPE === "ADDR"){
										str+="<td width='65%' align='left' style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\");'>" + jibunaddr + "</a></td>";
									}
									str+="<td width='10%' style='border-bottom:1px dotted #c9c8c8;'>";
									if(jibunaddr != ""){ //jibunaddr가 공백이 아니면(값이 들어있으면)
										if(SEARCH_LINK_TYPE === "KLIS"){
											str+="<img src='img/info.gif' onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\"); _DE.tojiInfo(\"" + pnu + "\",\""+jibunaddr+"\");' style='cursor:pointer;'/>";
										}else if(SEARCH_LINK_TYPE === "EAIS"){
											str+="<img src='img/info.gif' onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\"); _DE.bldgShortInfo(\"" + pnu + "\",\""+jibunaddr+"\");' style='cursor:pointer;'/>";
										}else if(SEARCH_LINK_TYPE === "LURIS"){
											str+="<img src='img/info.gif' onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\"); _DE.lurisInfo(\"" + pnu + "\",\""+jibunaddr+"\");' style='cursor:pointer;'/>";
										}else if(SEARCH_LINK_TYPE === "ADDR"){
											str+="<img src='img/info_.gif' onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\");' style='cursor:pointer;'/>";
										}
									}else{ //공백이면 이 이미지를 보여줌
										str+="<img src='img/info_off.gif'/>";
									}
									str+="</td>";
								str+="</tr>";
								str+="<tr align='center'>";
									str+="<td height='20' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>도로명주소</td>";
									str+="<td align='left' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveNoScale(\""+lrynm+"\", \"" + key+ "\");'>" + doroaddr + "</a></td>";
									str+="<td style='border-bottom:1px dotted #c9c8c8;'>";
									if(doroaddr != ""){
										str+="<img src='img/info_.gif'onclick='_MAINMAP.searchAndMoveNoScale(\""+lrynm+"\", \"" + key+ "\");' style='cursor:pointer;'/>"; //_DE.doroInfo(\"" + pnu + "\", \"\");
									}else{
										str+="<img src='img/info_off_.gif'/>";
									}
									str+="</td>";
								str+="</tr>";
							str+="</table>";
							str+="</div>";
							if(SEARCH_LINK_TYPE === "KLIS"){
								_MAINMAP.searchAndMoveNoScale('연속지적', pnu);
								_DE.tojiInfo(pnu, jibunaddr);
								$("#result_content_box").hide();
							}else if(SEARCH_LINK_TYPE === "EAIS"){
								_MAINMAP.searchAndMoveNoScale('연속지적', pnu);
								_DE.bldgShortInfo(pnu, jibunaddr);
								$("#result_content_box").hide();
							}else if(SEARCH_LINK_TYPE === "LURIS"){
								_MAINMAP.searchAndMoveNoScale('연속지적', pnu);
								_DE.lurisInfo(pnu, jibunaddr);
								$("#result_content_box").hide();
							}
			    		   });
				           $("#result_content").html(str);
				           //페이징처리
				           $("#total_page").html(_PA.init.jibunbotpageing());
			    	   }else{
			    		   	var pstr = "";
							pstr +="<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>";
							pstr +="<p style='padding: 20px 0 0 0'>검색한결과값이 존재하지않습니다.</p>";
							pstr +="</div>";
							$("#result_content").html(pstr);
							$("#total_page").html("");
			    	   } 
			       },
					complete : function(){
						$("#result_content_box").hideLoading();
						$("#svh_btn_img").css({"border":"0px"});
					},
					error: function(xhr, status, error) {
							alert(">>>>>>>>>>> : 에러");
					}
			   	});
		},
		
		//지번주소 2011-12-26
		GetResultNewContent : function(sgg, umd, ri, bon, bu, san, start, pageSize){
			$.ajax({
			       type: "POST",
			       url: "../../svc",
			       data: {svc : "GetAddrNewSrchList", sgg:sgg, umd:umd, ri:ri, bon:bon, bu:bu, san:san, snum:start, pagenum:pageSize},
			       dataType: "xml",
			       success: function(xml){
			    	   var str="";
			    	   if($(xml).find('리스트').length != 0){
			    		   $(xml).find('리스트').each(function(idx, item){
			    			   var pnu = $(this).find("pnu").text();
				    			var jibunaddr = $(this).find("지번주소").text();
				    			var doroaddr = $(this).find("도로주소").text();
				    			var bldgname = $(this).find("빌딩명").text();
				    			var key = $(this).find("도로키값").text();
				    			var lrynm = $(this).find("레이어명").text();
				    			
				    			str+="<div style='margin-top:3px;'>";
				    			str+="<table border='0' cellpadding='0' cellspacing='0' width='100%;' height='auto' bgcolor='#ffffff' style='border:1px solid #86c0ef'>";
									str+="<tr align='center'>";
										str+="<td height='20' width='25%' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>지번</td>";
										str+="<td width='75%' align='left' style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\"); _DE.tojiInfo(\"" + pnu + "\",\""+jibunaddr+"\");'>" + jibunaddr + "</a></td>";
										str+="<td width='10%' style='border-bottom:1px dotted #c9c8c8;'>";
										if(jibunaddr != ""){
											str+="<img src='img/info.gif' onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\"); _DE.tojiInfo(\"" + pnu + "\",\""+jibunaddr+"\");' style='cursor:pointer;'/>";
										}else{
											str+="<img src='img/info_off.gif'/>";
										}
										str+="</td>";
									str+="</tr>";
									str+="<tr align='center'>";
										str+="<td height='20' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>도로명주소</td>";
										str+="<td align='left' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveNoScale(\""+lrynm+"\", \"" + key+ "\"); _DE.tojiInfo(\"" + pnu + "\",\""+jibunaddr+"\");'>" + doroaddr + "</a></td>";
										str+="<td style='border-bottom:1px dotted #c9c8c8;'>";
										if(doroaddr != ""){
											str+="<img src='img/info.gif'onclick='_MAINMAP.searchAndMoveNoScale(\""+lrynm+"\", \"" + key+ "\"); _DE.tojiInfo(\"" + pnu + "\",\""+jibunaddr+"\");' style='cursor:pointer;'/>";
										}else{
											str+="<img src='img/info_off.gif'/>";
										}
										str+="</td>";
									str+="</tr>";
									/*str+="<tr align='center'>";
										str+="<td height='20' bgcolor='#b2dcfd' style='border-right:1px dotted #c9c8c8;'>건물명</td>";
										str+="<td align='left' style='border-right:1px dotted #c9c8c8;'>&nbsp;";
										if(doroaddr != "-"){
											str+="<a href='#' onclick='_MAINMAP.searchAndMoveNoScale(\""+lrynm+"\", \"" + key+ "\"); _DE.bldgInfo(\"" + pnu + "\");'>"+bldgname+"</a>";
										}else{
											str+= bldgname;
										}
										str+="</td>";
										str+="<td>";
										if(doroaddr != ""){
											str+="<img src='img/info.gif' onclick='_MAINMAP.searchAndMoveNoScale(\""+lrynm+"\", \"" + key+ "\"); _DE.bldgInfo(\"" + pnu + "\");' style='cursor:pointer;'/>";
										}else{
											str+="<img src='img/info_off.gif' />";
										}
										str+="</td>";
									str+="</tr>";*/
								str+="</table>";
								str+="</div>";
								
								 $("#result_content").html(str);
						         $("#total_page").html(_PA.init.jibunNewbotpageing());
			    		   });
			    		}else{
			    		   	var pstr = "";
							pstr +="<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>";
							pstr +="<p style='padding: 20px 0 0 0'>검색한결과값이 존재하지않습니다.</p>";
							pstr +="</div>";
							$("#result_content").html(pstr);
							$("#total_page").html("");
			    	   } 
			       },
			       beforeSend : function(){
						$("#result_content_box").showLoading();
					},
					complete : function(){
						$("#result_content_box").hideLoading();
						$("#svh_btn_img").css({"border":"0px"});
					},
					error: function(xhr, status, error) {
							alert(">>>>>>>>>>> : 에러");
					}
			   	});
		},
		
		
		////도로명주소 페이지를 가져온다.
		srchRoadAddrLoding : function(){
			$.ajax({
				url: "./search/srchRoadAddr.jsp",
				contentType : "text/html; charset=utf-8",
				dataType : "html",
				success: function(htm){
					$("#con_sch_full_box").html(htm);
					if($.browser.msie){
						$("#con_sch_full_box .grayBTN").width(50).height(26);
					}
					reSize(); //사이즈재설정
					gwsUtils.setWorkImgChange('2');
					if(SEARCH_LINK_TYPE == "KLIS"){
						$("#help_txt").text("* 토지대장을 검색합니다.");
						gwsUtils.setWorkImgChange('9');
					}else if(SEARCH_LINK_TYPE == "EAIS"){
						$("#help_txt").text("* 건축물대장을 검색합니다.");
						gwsUtils.setWorkImgChange('10');
					}else if(SEARCH_LINK_TYPE == "LURIS"){
						$("#help_txt").text("* 토지이용규제를 검색합니다.");
						gwsUtils.setWorkImgChange('11');
					}
					_CS.init.GetSGG(); //시군구를 불러옴
				},
				beforeSend : function(){
					//$("#con_sch_full_box").showLoading();
				},
				complete : function(){
					//$("#con_sch_full_box").hideLoading();
				},
				error: function(xhr, status, error) {
						alert(">>>>>>>>>>> : 에러");
				}
			});	
		},
		//도로
		GetRoad : function(obj){
				var sgg = $("#sgg").val();
			   $("#road").removeOption(/./);
		       $("#road").addOption("", ":: 선택하여주십시오! ::", true);
		       
		       if(!sgg){
		    	   alert("시군을 선택하여 주십시오!");
		    	   $("#sgg").focus();
		    	   return false;
		       }
		       
			   $.ajax({
			       type: "POST",
			       url: "../../svc",
			       data: {svc : "GetRoad", sgg:sgg, cons:encodeURIComponent(obj)},
			       dataType: "xml",
			       success: function(xml) {
			    	   var conditions_box = "";
			           $(xml).find('도로').each(function(idx){
			               var code = $(this).find('도로코드').text();
			               var name = $(this).find('도로명').text();
			               conditions_box += "<option value='" + code + "'>" + name + "</option>";
			           });
			           $("#road").append(conditions_box);
			       },
			       error: function(){
			           alert("에러");
			       }
			   	});
		},
		//도로명주소 페이지값
		DoroReset : function(){
			_PA.info.start = 0;  
			_PA.info.end = 0;
			_PA.info.pageSize = 6;
			_PA.info.pageBlock = 5;
			_PA.info.pageCount = 0;
			_PA.info.startPage = 0;
			_PA.info.endPage = 0;
			_PA.info.number = 0;
			_PA.info.count = 0;
			_PA.info.currentPage = 0;
			_PA.info.pageNum = 0;
			_CS.init.Doropaging(0);
		},
		Doropaging : function(pagenum){
			
			var sgg = $("#sgg").val(); //시군구 코드
			var road = $("#road").val();
			var buld_bon = $("#buld_bon").val();
			var buld_bu = $("#buld_bu").val();
			var buld_se_cd = $("#buld_se_cd").val();
			
			if(!sgg){
				alert("시군구를 입력하여 주십시오!");
				return false;
			}
			
			if (_PA.info.pageNum == 0){ 
				_PA.info.pageNum = 1; 
	    	}else{
	    		_PA.info.pageNum = pagenum;
		    }
			
			$.ajax({
				type:"GET",
				url:"./search/cntxml/doro_cnt.jsp",
				data : {sgg:sgg, road:encodeURIComponent(road), buld_bon:buld_bon, buld_bu:buld_bu, buld_se_cd:buld_se_cd},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("data").length != 0){
						_PA.info.currentPage = Number(_PA.info.pageNum); 
						_PA.info.start = (_PA.info.currentPage - 1) * _PA.info.pageSize + 1;  // 블럭당 보이는 글의 수 시작값
						_PA.info.end = _PA.info.start + _PA.info.pageSize - 1;     //블럭당 보이는 글의수 끝값
						_PA.info.count = $(xml).find("data").find("tot").text();
						_PA.info.number = _PA.info.count - (_PA.info.currentPage - 1) * _PA.info.pageSize;  //인덱스 값 구하기
						_CS.init.GetResultRoadAddr(sgg, road, buld_bon, buld_bu, _PA.info.start, _PA.info.pageSize, buld_se_cd);
					}
				},
				beforeSend : function(){
					$("#result_content_box").showLoading();
				},
				error: function(xhr, status, error) {
					alert("에러");
				}
			});
		},
		////도로주소 검색 결과값을 가져온다.
		GetResultRoadAddr : function(sgg, road, buld_bon, buld_bu, start, pageSize, buld_se_cd){
			$.ajax({
			       type: "POST",
			       url: "../../svc",
			       data: {svc : "GetRoadSrchList", sgg:sgg, road:encodeURIComponent(road), buld_bon:buld_bon, buld_bu:buld_bu, snum:start, pagenum:pageSize, buld_se_cd:buld_se_cd},
			       dataType: "xml",
			       success: function(xml){
			    	   var str="";
			    	   if($(xml).find('도로명').length != 0){
			    		   $(xml).find('도로명').each(function(idx, item){
			    			
			    			var pnu = $(this).find("pnu").text();
			    			var doro_code = $(this).find("도로명코드").text();
			    			var key = $(this).find("key").text();
			    			var layerNM = $(this).find("레이어명").text();
			    			var doro_addr = $(this).find("도로명주소").text();
			    			var buld_name = $(this).find("건물명").text();
			    			var jibun_addr = $(this).find("지번주소").text(); 
			    		
			    			str+="<div style='margin-top:3px;'>";
							str+="<table border='0' cellpadding='0' cellspacing='0' width='100%;' height='auto' bgcolor='#ffffff' style='border:1px solid #86c0ef'>";
								str+="<tr align='center'>";
									str+="<td height='20' width='25%' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>도로명주소</td>";
									if(SEARCH_LINK_TYPE === "KLIS"){
										str+="<td width='65%' align='left' style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveEqb(\""+layerNM+"\", \""+key+"\"); _DE.tojiInfo(\"" + pnu + "\",\""+jibun_addr+"\");'>" + doro_addr + "</a></td>";
									}else if(SEARCH_LINK_TYPE === "EAIS"){
										str+="<td width='65%' align='left' style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveEqb(\""+layerNM+"\", \""+key+"\"); _DE.bldgShortInfo(\"" + pnu + "\",\""+jibun_addr+"\");'>" + doro_addr + "</a></td>";
									}else if(SEARCH_LINK_TYPE === "LURIS"){
										str+="<td width='65%' align='left' style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveEqb(\""+layerNM+"\", \""+key+"\"); _DE.lurisInfo(\"" + pnu + "\",\""+jibun_addr+"\");'>" + doro_addr + "</a></td>";
									}else if(SEARCH_LINK_TYPE === "ADDR"){
										str+="<td width='65%' align='left' style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveEqb(\""+layerNM+"\", \""+key+"\");'>" + doro_addr + "</a></td>";
									}
									str+="<td width='10%' style='border-bottom:1px dotted #c9c8c8;'>";
									if(doro_addr != ""){
										if(SEARCH_LINK_TYPE === "KLIS"){
											str+="<img src='img/info.gif' onclick='_MAINMAP.searchAndMoveEqb(\""+layerNM+"\", \""+key+"\"); _DE.tojiInfo(\"" + pnu + "\",\""+jibun_addr+"\");' style='cursor:pointer;'/>";
										}else if(SEARCH_LINK_TYPE === "EAIS"){
											str+="<img src='img/info.gif' onclick='_MAINMAP.searchAndMoveEqb(\""+layerNM+"\", \""+key+"\"); _DE.bldgShortInfo(\"" + pnu + "\",\""+jibun_addr+"\");' style='cursor:pointer;'/>";
										}else if(SEARCH_LINK_TYPE === "LURIS"){
											str+="<img src='img/info.gif' onclick='_MAINMAP.searchAndMoveEqb(\""+layerNM+"\", \""+key+"\"); _DE.lurisInfo(\"" + pnu + "\",\""+jibun_addr+"\");' style='cursor:pointer;'/>";
										}else if(SEARCH_LINK_TYPE === "ADDR"){
											str+="<img src='img/info_.gif' onclick='_MAINMAP.searchAndMoveEqb(\""+layerNM+"\", \""+key+"\");' style='cursor:pointer;'/>";
										}
									}else{
										str+="<img src='img/info_off.gif'/>";
									}
									str+="</td>";
								str+="</tr>";
								str+="<tr align='center'>";
									str+="<td height='20' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>지번주소</td>";
									if(SEARCH_LINK_TYPE === "KLIS"){
										str+="<td align='left' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\"); _DE.tojiInfo(\"" + pnu + "\",\""+jibun_addr+"\");'>" + jibun_addr + "</a></td>";
									}else if(SEARCH_LINK_TYPE === "EAIS"){
										str+="<td align='left' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\"); _DE.bldgShortInfo(\"" + pnu + "\",\""+jibun_addr+"\");'>" + jibun_addr + "</a></td>";
									}else if(SEARCH_LINK_TYPE === "LURIS"){
										str+="<td align='left' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\"); _DE.lurisInfo(\"" + pnu + "\",\""+jibun_addr+"\");'>" + jibun_addr + "</a></td>";
									}else if(SEARCH_LINK_TYPE === "ADDR"){
										str+="<td align='left' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\");'>" + jibun_addr + "</a></td>";
									}
									str+="<td style='border-bottom:1px dotted #c9c8c8;'>";
									if(jibun_addr != ""){
										if(SEARCH_LINK_TYPE === "KLIS"){
											str+="<img src='img/info.gif'onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\"); _DE.tojiInfo(\"" + pnu + "\",\""+jibun_addr+"\");' style='cursor:pointer;'/>";
										}else if(SEARCH_LINK_TYPE === "EAIS"){
											str+="<img src='img/info.gif'onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\"); _DE.bldgShortInfo(\"" + pnu + "\",\""+jibun_addr+"\");' style='cursor:pointer;'/>";
										}else if(SEARCH_LINK_TYPE === "LURIS"){
											str+="<img src='img/info.gif'onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\"); _DE.lurisInfo(\"" + pnu + "\",\""+jibun_addr+"\");' style='cursor:pointer;'/>";
										}else if(SEARCH_LINK_TYPE === "ADDR"){
											str+="<img src='img/info_.gif'onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \"" + pnu+ "\");' style='cursor:pointer;'/>";
										}
									}else{
										str+="<img src='img/info.gif'/>";
									}
									str+="</td>";
								str+="</tr>";
							str+="</table>";
							str+="</div>";
							if(SEARCH_LINK_TYPE === "KLIS"){
								_MAINMAP.searchAndMoveEqb(layerNM, key);
								_DE.tojiInfo(pnu, doro_addr);
								$("#result_content_box").hide();
							}else if(SEARCH_LINK_TYPE === "EAIS"){
								_MAINMAP.searchAndMoveEqb(layerNM, key);
								_DE.bldgShortInfo(pnu, doro_addr);
								$("#result_content_box").hide();
							}else if(SEARCH_LINK_TYPE === "LURIS"){
								_MAINMAP.searchAndMoveEqb(layerNM, key);
								_DE.lurisInfo(pnu, doro_addr);
								$("#result_content_box").hide();
							}
			    		   });
				           $("#result_content").html(str);
				           //페이징처리
				           $("#total_page").html(_PA.init.dorobotpageing());
			    	   }else{
			    		   	var pstr = "";
							pstr +="<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>";
							pstr +="<p style='padding: 20px 0 0 0'>검색한결과값이 존재하지않습니다.</p>";
							pstr +="</div>";
							$("#result_content").html(pstr);
							$("#total_page").html("");
			    	   } 
			       },
					complete : function(){
						$("#result_content_box").hideLoading();
					},
					error: function(xhr, status, error) {
							alert(">>>>>>>>>>> : 에러");
					}
			   	});
		},
		//명칭검색 페이지를가져온다.
		srchDesiLoding : function(){
			$.ajax({
				url: "./search/srchDesi.jsp",
				contentType : "text/html; charset=utf-8",
				dataType : "html",
				success: function(htm){
					outerLayout.open("west");
					$("#con_sch_full_box").html(htm);
					reSize(); //사이즈재설정
				},
				beforeSend : function(){
					//$("#con_sch_full_box").showLoading();
				},
				complete : function(){
					//$("#con_sch_full_box").hideLoading();
				},
				error: function(xhr, status, error) {
						alert(">>>>>>>>>>> : 에러");
				}
			});	
		},
		
		//명칭검색의 명칭, 주소, 새주소를 분리한다. 2012-04-23
		apiDivision : function(){
			var api_val = $('input[name=radio_dorocross]:checked').val();
			
			if(api_val == "N"){
				//네이버 명칭검색
				this.desiReset(0);
			}else{
				//다음 주소 및 새주소
				this.DaumAddrReset(0);
			}
		},
		//수정 2012-04-27
		radio_check : function(obj){
			if(obj == "N"){
				$("#text_name_desi").text("명칭");
				$("#alert_mea").text("예) 완도군청, ***아파트");
			}else{
				$("#text_name_desi").text("주소");
				$("#alert_mea").text("예) 전라남도 완도군 완도읍 봉의동 15 ");
			}
		},
		//다음 로컬 api 2012-04-23
		DaumAddrReset : function(){
			_PA.info.start = 0;  
			_PA.info.end = 0;
			_PA.info.pageSize = 9;
			_PA.info.pageBlock = 5;
			_PA.info.pageCount = 0;
			_PA.info.startPage = 0;
			_PA.info.endPage = 0;
			_PA.info.number = 0;
			_PA.info.count = 0;
			_PA.info.currentPage = 0;
			_PA.info.pageNum = 0;
			_CS.init.DaumAddrPaging(0);
		},
		//다음 주소 페이징처리한다. 2012-04-23
		DaumAddrPaging : function(pagenum){

			var desi = $("#desi").val();
			var api_val = $('input[name=radio_dorocross]:checked').val();
			
			if(desi == ""){
				var n_text = "";
				if(api_val == "N"){
					n_text = "명칭";
				}else{
					n_text = "주소";
				}
				var mea = "* <b style='color:red;'>"+n_text+"</b>을 입력하여 주십시오!";
				$("#alert_mea").html(mea);
	    		$("#desi").focus();
	    		return false;
	    	 }else{
	    		var mea = "* 명칭은 필수 항목 입니다.";
				$("#alert_mea").html(mea);
	    	 }
			
			if (_PA.info.pageNum == 0){ 
				_PA.info.pageNum = 1; 
	    	}else{
	    		_PA.info.pageNum = pagenum;
		    }
			$.ajax({
				type:"POST",
				url:"../../svc",
				data : {svc : "GetDaumAddrCoord", key : "6402f804eddb06dd403efffbf97fdf6d6a7fd0fd", q : encodeURIComponent(desi), stnum : _PA.info.pageNum, pagenum : "10"},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("channel").find("totalCount").text() != 0){
						_PA.info.currentPage = Number(_PA.info.pageNum); 
						_PA.info.start = (_PA.info.currentPage - 1) * _PA.info.pageSize + 1;  // 블럭당 보이는 글의 수 시작값
						_PA.info.end = _PA.info.start + _PA.info.pageSize - 1;     //블럭당 보이는 글의수 끝값
						_PA.info.count = $(xml).find("channel").find("totalCount").text();
						_PA.info.number = _PA.info.count - (_PA.info.currentPage - 1) * _PA.info.pageSize;  //인덱스 값 구하기
						_CS.init.DaumAddrResult(_PA.info.start, _PA.info.pageSize);
					}else{
						var pstr = "";
						pstr +="<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>";
						pstr +="<p style='padding: 20px 0 0 0'>검색한결과값이 존재하지않습니다.</p>";
						pstr +="</div>";
						$("#desi_result_content").html(pstr);
						$("#total_page").html("");
					}
				},
				beforeSend : function(){},
				complete : function(){
					$("#svh_btn_img").css({"border":"0px"});
				},
				error: function(xhr, status, error) {
						alert("에러");
				}
			});	
		},
		//다음 주소 결과값 표출 2012-04-23
		DaumAddrResult : function(start, pageSize){
			var desi = $("#desi").val();
			
			$.ajax({
				type:"GET",
				url:"../../svc",
				data : {svc : "GetDaumAddrCoord", key : "6402f804eddb06dd403efffbf97fdf6d6a7fd0fd", q : encodeURIComponent(desi), stnum : start, pagenum : pageSize},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("item").length != 0){
						var str = "";
						$(xml).find("item").each(function(idx, item){
							var addr = $(this).find("localName_1").text()+" "+$(this).find("localName_2").text()+" "+$(this).find("localName_3").text()+" "+$(this).find("mainAddress").text();
								if($(this).find("subAddress").text() != 0){
									addr += "-"+$(this).find("subAddress").text();
								}
							var newaddr = $(this).find("newAddress").text();
							var lng = $(this).find("lng").text();
							var lat = $(this).find("lat").text();
							
							var crood = _CS.init.getDaumTransCoord(lng, lat);//좌표를 가져온다.
							
							var cxy = crood.split(",");
							var cx = cxy[0]; //좌표변환 x값
							var cy = cxy[1]; //좌표변환 y값
                      
							str+="<div style='margin-top:3px;'>";
							str+="<table border='0' cellpadding='0' cellspacing='0' width='100%;' height='auto' bgcolor='#ffffff' style='border:1px solid #86c0ef'>";
							str+="<tr align='center'>";
							str+="<td height='20' width='15%' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>주소</td>";
							//str+="<td width='65%' align='left' style='border-bottom:1px dotted #c9c8c8;'><a href='#' onclick='_MAINMAP.moveExtentCenter(\""+cx+"\", \"" +cy+ "\",\"2000\");'>&nbsp;" + title + "</a></td>";
							str+="<td width='65%' align='left' style='border-bottom:1px dotted #c9c8c8;'><a href='#' onclick='_MAINMAP.locateCenter(\""+cx+"\", \"" +cy+ "\");'>&nbsp;" + addr + "</a></td>";
							str+="</tr>";
							str+="<tr align='center'>";
							str+="<td height='20' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>새주소</td>";
							str+="<td align='left' style='border-right:1px dotted #c9c8c8;'>&nbsp;" + newaddr + "</td>";
							str+="</tr>";
							str+="</table>";
							str+="</div>";
							
						});
						$("#desi_result_content").html(str);
						$("#total_page").html(_PA.init.Daumbottompageing());
					}else{
						var pstr = "";
						pstr +="<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>";
						pstr +="<p style='padding: 20px 0 0 0'>검색한결과값이 존재하지않습니다.</p>";
						pstr +="</div>";
						$("#desi_result_content").html(pstr);
						$("#total_page").html("");
					}
				},
				beforeSend : function(){
					$("#desi_result_content_box").showLoading();
				},
				complete : function(){
					$("#desi_result_content_box").hideLoading();
					$("#svh_btn_img").css({"border":"0px"});
				},
				error: function(xhr, status, error) {
					alert(">>>>>> : " + error);
				}
			});
		},
		//네이버명칭검색
		desiReset : function(){
			_PA.info.start = 0;  
			_PA.info.end = 0;
			_PA.info.pageSize = 9;
			_PA.info.pageBlock = 5;
			_PA.info.pageCount = 0;
			_PA.info.startPage = 0;
			_PA.info.endPage = 0;
			_PA.info.number = 0;
			_PA.info.count = 0;
			_PA.info.currentPage = 0;
			_PA.info.pageNum = 0;
			_CS.init.desiPaging(0);
		},
		//명칭검색 페이징처리한다.
		desiPaging : function(pagenum){

			var desi = $("#desi").val();
			
			if(desi == ""){				
				var mea = "* <b style='color:red;'>명칭</b>을 입력하여 주십시오!";
				$("#alert_mea").html(mea);
	    		$("#desi").focus();
	    		return false;
	    	 }else{
	    		var mea = "* 명칭은 필수 항목 입니다.";
				$("#alert_mea").html(mea);
	    	 }
			
			if (_PA.info.pageNum == 0){ 
				_PA.info.pageNum = 1; 
	    	}else{
	    		_PA.info.pageNum = pagenum;
		    }
			
			$.ajax({
				type:"POST",
				url:"../../svc",
				data : {svc : "GetDesi", key : "42ac7d6471883838514d1d54874cd052", query : encodeURIComponent(desi), stnum : _PA.info.pageNum, pagenum : "10"},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("rss").find("total").text() != 0){
						_PA.info.currentPage = Number(_PA.info.pageNum); 
						_PA.info.start = (_PA.info.currentPage - 1) * _PA.info.pageSize + 1;  // 블럭당 보이는 글의 수 시작값
						_PA.info.end = _PA.info.start + _PA.info.pageSize - 1;     //블럭당 보이는 글의수 끝값
						_PA.info.count = $(xml).find("rss").find("total").text();
						_PA.info.number = _PA.info.count - (_PA.info.currentPage - 1) * _PA.info.pageSize;  //인덱스 값 구하기
						_CS.init.srchContDesi(_PA.info.start, _PA.info.pageSize);
					}else{
						var pstr = "";
						pstr +="<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>";
						pstr +="<p style='padding: 20px 0 0 0'>검색한결과값이 존재하지않습니다.</p>";
						pstr +="</div>";
						$("#desi_result_content").html(pstr);
						$("#total_page").html("");
					}
				},
				beforeSend : function(){},
				complete : function(){
					$("#svh_btn_img").css({"border":"0px"});
				},
				error: function(xhr, status, error) {
						alert("에러");
				}
			});	
		},
		//명칭검색 결과값을 가져온다.
		srchContDesi : function(start, pageSize){
			
			var desi = $("#desi").val();
			
			$.ajax({
				type:"GET",
				url:"../../svc",
				data : {svc : "GetDesi", key : "42ac7d6471883838514d1d54874cd052", query : encodeURIComponent(desi), stnum : start, pagenum : pageSize},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("item").length != 0){
						var str = "";
						$(xml).find("item").each(function(idx, item){
							var title = $(this).find("title").text();
							var address = $(this).find("address").text();
							var mapx = $(this).find("mapx").text();
							var mapy = $(this).find("mapy").text();
							var crood = _CS.init.getTransCoord(mapx, mapy);//좌표를 가져온다.
							
							var cxy = crood.split(",");
							var cx = cxy[0]; //좌표변환 x값
							var cy = cxy[1]; //좌표변환 y값

							/*str+="<div style='margin-top:3px;'>";
							str+="<table border='0' cellpadding='0' cellspacing='0' width='100%;' height='auto' bgcolor='#ffffff' style='border:1px solid #86c0ef'>";
							str+="<tr align='center'>";
							str+="<td height='20' width='15%' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>명칭</td>";
							str+="<td width='65%' align='left' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'><a href='#' onclick='_MAINMAP.moveExtent(\""+cx+"\", \"" +cy+ "\",\"2000\");'>&nbsp;" + title + "</a></td>";
							str+="<td width='10%' align='center' style='border-bottom:1px dotted #c9c8c8;'><img src='img/info.gif' onclick='_DE.de_bldgInfo();' style='cursor:pointer;'/></td>";
							str+="</tr>";
							str+="<tr align='center'>";
							str+="<td height='20' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>지번</td>";
							str+="<td align='left' style='border-right:1px dotted #c9c8c8;' colspan='2'>&nbsp;" + address + "</td>";
							str+="</tr>";
							str+="</table>";
							str+="</div>";*/
							
							str+="<div style='margin-top:3px;'>";
							str+="<table border='0' cellpadding='0' cellspacing='0' width='100%;' height='auto' bgcolor='#ffffff' style='border:1px solid #86c0ef'>";
							str+="<tr align='center'>";
							str+="<td height='20' width='15%' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>명칭</td>";
							//str+="<td width='65%' align='left' style='border-bottom:1px dotted #c9c8c8;'><a href='#' onclick='_MAINMAP.moveExtentCenter(\""+cx+"\", \"" +cy+ "\",\"2000\");'>&nbsp;" + title + "</a></td>";
							str+="<td width='65%' align='left' style='border-bottom:1px dotted #c9c8c8;'><a href='#' onclick='_MAINMAP.locateCenter(\""+cx+"\", \"" +cy+ "\");'>&nbsp;" + title + "</a></td>";
							str+="</tr>";
							str+="<tr align='center'>";
							str+="<td height='20' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>지번</td>";
							str+="<td align='left' style='border-right:1px dotted #c9c8c8;'>&nbsp;" + address + "</td>";
							str+="</tr>";
							str+="</table>";
							str+="</div>";
						});
						$("#desi_result_content").html(str);
						$("#total_page").html(_PA.init.bottompageing());
					}else{
						var pstr = "";
						pstr +="<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>";
						pstr +="<p style='padding: 20px 0 0 0'>검색한결과값이 존재하지않습니다.</p>";
						pstr +="</div>";
						$("#desi_result_content").html(pstr);
						$("#total_page").html("");
					}
				},
				beforeSend : function(){
					$("#desi_result_content_box").showLoading();
				},
				complete : function(){
					$("#desi_result_content_box").hideLoading();
					$("#svh_btn_img").css({"border":"0px"});
				},
				error: function(xhr, status, error) {
					alert(">>>>>> : " + error);
				}
			});
		},
		//네이버 지역검색의 좌표를 변환한다.
		getTransCoord : function(mapx, mapy){
			
			var coord="";
			var cx = "";
			var cy = "";
			
			$.ajax({
				type:"post",
				url:"../geomex/utils/naver_transCoordXml.jsp",
				data : {tmx:mapx, tmy:mapy},
				dataType : "xml",
				async: false,
				success: function(xml){
					if($(xml).find("Coord").length != 0){
						var x = $(xml).find("x").text();
						var y = $(xml).find("y").text();
						coord = x + "," + y;
					}
				},
				beforeSend : function(){},
				complete : function(){},
				error: function(xhr, status, error) {
						alert(">>>>>> : " + error);
				}
			});
			return coord;
		},
		//다음의 영상좌표를 wgs로 바꾼다.
		getDaumTransCoord : function(mapx, mapy){
			
			var coord="";		
			
			$.ajax({
				type:"post",
				url: "../../svc",
			    data: {svc : "GetDaumCoord", apikey:"6402f804eddb06dd403efffbf97fdf6d6a7fd0fd", x:mapx, y:mapy, fromCoord:"WGS84", toCoord:"WTM", output:"xml"},
				dataType : "xml",
				async: false,
				success: function(xml){
				     if($(xml).find('result').length != 0){
						var x=Number($(xml).find('result').attr("x"))+100000;
						var x = $(xml).find('result').attr("x");
						var y = $(xml).find('result').attr("y");
						
					   coord = x + "," + y;
					}else{
						if($(xml).find('apierror').length != 0){
							alert($(xml).find('dmessage').text());
						}
					}
				},
				beforeSend : function(){},
				complete : function(){},
				error: function(xhr, status, error) {
						alert(">>>>>> : " + error);
				}
			});
			
			return coord;

		},
		
		//도로검색페이지를 가져온다.
		srchRoadLoding : function(){
			$.ajax({
				url: "./search/srchRoad.jsp",
				contentType : "text/html; charset=utf-8",
				dataType : "html",
				success: function(htm){
					outerLayout.open("west");
					$("#con_sch_full_box").html(htm);
					reSize(); //사이즈재설정
					_CS.init.GetSGG();
				},
				beforeSend : function(){
					//$("#con_sch_full_box").showLoading();
				},
				complete : function(){
					//$("#con_sch_full_box").hideLoading();
				},
				error: function(xhr, status, error) {
						alert(">>>>>>>>>>> : 에러");
				}
			});	
		},
		//도로검색결과값을 가져온다.
		GetResultRoad : function(obj){
			
			var sgg = $("#sgg").val();
			var radiovalue = $('input[name=radio_dorocross]:checked').val();
			var roadname = obj;
			
			
			if(sgg == ""){
				 $("#messg_txt").html("* <b>시군</b>을 선택하여주십시오!");
				$("#sgg").focus();
				return false;
			}
			
			if(radiovalue == ""){
				$("#messg_txt").html("* <b>검색대상</b>을 선택하여 주십시오!");
				$("input[name=radio_dorocross]").focus();
				return false;
			}
			
			if(roadname == ""){
				$("#messg_txt").html("* <b>도로명 자음</b>을 선택하여 주십시오!");
				return false;
			}
			
			
			//alert(sgg + ", " + radiovalue + ", " + roadname);
			
			$.ajax({
			       type: "POST",
			       url: "../../svc",
			       data: {svc : "GetRoadCross", sgg:sgg, dorocross:radiovalue, road:encodeURIComponent(roadname)},
			       dataType: "xml",
			       success: function(xml){
			    	   
			    	   var str="";
			    	   
			    	   if(radiovalue == "road"){
			    		   //alert(radiovalue);
			    		   if($(xml).find('도로').length != 0){
				    		   $(xml).find('도로').each(function(idx, item){

				    			var rodocode = $(this).find("도로코드").text();
				    			var rodoname = $(this).find("도로명").text();
				    			var engname = $(this).find("영문명").text();
				    			var origin = $(this).find("기점").text();
				    			var end = $(this).find("종점").text();
				    			var leng = $(this).find("길이").text();
				    			var reason = $(this).find("부여사유").text();
				    			var sigcd = $(this).find("시군구코드").text();
				    			
				    			str+="<div style='margin-top:3px;'>";
				    			str+="<table border='0' cellpadding='0' cellspacing='0' width='100%;' height='auto' bgcolor='#ffffff' style='border:1px solid #86c0ef'>";
					    			str+="<tr align='center'>";
						    			str+="<td height='20' width='20%' bgcolor='#b2dcfd' style='border-right:1px dotted #c9c8c8;'>도로명</td>";
						    			str+="<td width='70%'  align='left'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveRoad(\"도로구간\", \""+sigcd+""+rodocode+"\");'>" + rodoname + "</a></td>";
						    			//str+="<td width='70%'  align='left' style='border-right:1px dotted #c9c8c8;'>&nbsp;" + rodoname + "</td>";
						    			//str+="<td><img src='img/info.gif' onclick='_DE.doroInfo(\"\", \"doro\");' style='cursor:pointer;'/></td>";
					    			str+="</tr>";
				    			str+="</table>";
				    			str+="</div>";
				    			
				    		   });
					           $("#doro_result_content").html(str);
					           //페이징처리
					           //$("#total_page").html(_PA.init.bottompageing());
				    	   }else{
				    		   	var pstr = "";
								pstr +="<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>";
								pstr +="<p style='padding: 20px 0 0 0'>검색한결과값이 존재하지않습니다.</p>";
								pstr +="</div>";
								$("#doro_result_content").html(pstr);
								$("#total_page").html("");
				    	   } 
			    	   }else{
			    		   //alert(radiovalue);
			    		   if($(xml).find('교차로').length != 0){
				    		   $(xml).find('교차로').each(function(idx, item){

				    			var crsrd_num = $(this).find("교차로일련번호").text();
				    			var crsrd_name = $(this).find("교차로명").text();
				    			var sig_cd = $(this).find("시군구코드").text();
				    			
				    			str+="<div style='margin-top:3px;'>";
				    			str+="<table border='0' cellpadding='0' cellspacing='0' width='100%;' height='auto' bgcolor='#ffffff' style='border:1px solid #86c0ef'>";
					    			str+="<tr align='center'>";
						    			str+="<td height='20' width='20%' bgcolor='#b2dcfd' style='border-right:1px dotted #c9c8c8;'>교차로명</td>";
						    			str+="<td width='70%'  align='left'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveCross(\"교차로\", \"" + sig_cd+""+crsrd_num+"\");'>" + crsrd_name + "</a></td>";
						    			//str+="<td width='70%'  align='left' style='border-right:1px dotted #c9c8c8;'>&nbsp;" + crsrd_name + "</td>";
						    			//str+="<td><img src='img/info.gif' onclick='_DE.doroInfo(\"doro\");' style='cursor:pointer;'/></td>";
					    			str+="</tr>";					    			
				    			str+="</table>";
				    			str+="</div>";
				    			
				    		   });
					           $("#doro_result_content").html(str);
					           //페이징처리
					           //$("#total_page").html(_PA.init.bottompageing());
				    	   }else{
				    		   	var pstr = "";
								pstr +="<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>";
								pstr +="<p style='padding: 20px 0 0 0'>검색한결과값이 존재하지않습니다.</p>";
								pstr +="</div>";
								$("#doro_result_content").html(pstr);
								$("#total_page").html("");
				    	   } 
			    		   
			    	   }  
			       },
			       beforeSend : function(){
						$("#doro_result_content_box").showLoading();
					},
					complete : function(){
						$("#doro_result_content_box").hideLoading();
					},
					error: function(xhr, status, error) {
							alert(">>>>>>>>>>> : 에러");
					}
			   	});
		},
		//통합검색페이지를 가져온다.
		srchInteLoding : function(){
			$.ajax({
				url: "./search/srchInte.jsp",
				contentType : "text/html; charset=utf-8",
				dataType : "html",
				success: function(htm){
					outerLayout.open("west");
					$("#con_sch_full_box").html(htm);
					reSize(); //사이즈재설정
					_CS.init.GetFullLayerXML();
				},
				beforeSend : function(){
				},
				complete : function(){
				},
				error: function(xhr, status, error) {
						alert(">>>>>>>>>>> : 에러");
				}
			});	
		},
		//레이어목록을 가져온다.
		GetLayers : function(){
			$.ajax({
				type: 'POST',
		    	url: '../../svc',
		    	data : {svc : "GetLayers"},
		    	dataType: 'xml',
		    	success: function(xml){
					var conditions_box = "";
					$(xml).find("레이어").each(function(){
						var code = $(this).find("테이블ID").text();
						var name = $(this).find("레이어명").text();
						var lyr_id = $(this).find("레이어관리ID").text();
						conditions_box += "<option value='" + lyr_id + "'>" + name + "</option>";
					});
					$("#layer_name").append(conditions_box);	
				},
		    	error: function(){
		        	alert('Error loading XML document');
		    	}
			});
		},
		//버퍼검색에 필요한 버페레이어명을 가지고온다.
		GetBufferLayers : function(){
			$.ajax({
				type: 'POST',
		    	url: '../../svc',
		    	data : {svc : "GetLayers"},
		    	dataType: 'xml',
		    	success: function(xml){
					var conditions_box = "";
					$(xml).find("레이어").each(function(){
						var code = $(this).find("테이블ID").text();
						var name = $(this).find("레이어명").text();
						var lyr_id = $(this).find("레이어관리ID").text();
						conditions_box += "<option value='" + name + "'>" + name + "</option>";
					});
					$("#layer_name").append(conditions_box);	
				},
		    	error: function(){
		        	alert('Error loading XML document');
		    	}
			});
		},
		//통합검색
		//레이어대상항목을 가져온다.
		GetLyrInfo : function(obj){
			
			//대상레이어가 연속지적일경우 검색조건폼을 바꿔준다.
			var jibun_sch_box = $("#jibun_sch_box");
			var general_box = $("#general_box");
			var jijuk_srch_box = $("#jijuk_srch_box");
			
			if("연속지적" == obj){
				general_box.hide();
				jijuk_srch_box.show();
				jibun_sch_box.css({"height":"350px"});
				_S.utils.autoParentHeight("con_sch_full_box", "inte_result_content_box", 418);
				_S.utils.autoParentHeight("inte_result_content_box", "inte_result_content", 58);
				
				var hidden_btn = document.getElementById("hidden_btn");
				var detail_btn = document.getElementById("detail_btn");
				
				hidden_btn.onclick = function(){
					_CS.init.InteJijukReset();
				};
				
				$("#detail_btn").show();
				
				detail_btn.onclick = function(){
					_CS.init.getLryInteJijukpage(1);
				};
				
				/*$("#hidden_btn").attr("onclick", "_CS.init.InteJijukReset()");
				$("#detail_btn").show();
				$("#detail_btn").attr("onclick", "_CS.init.getLryInteJijukpage(1);");*/
				//시군구함수호출
				_CS.init.GetSGG();
				//지목코드를 가지고온다.
				_CS.init.GetCombineCode('jimok');
				//소유자구분코드
				_CS.init.GetCombineCode('owngb');
				//공시지가 년도
				_CS.init.GetCombineCode('base_year');
				//용도지역-대분류
				_CS.init.GetUzneCodeDae();
				
				//enter 적용
				/*$("#sgg, #road, #ri, #owngb, #area1, #area2, #jimok, #uznecode1, #uznecode2, #uznecode3, #base_year, #jiga1, #jiga2").keypress(function(e) {
				    var code=(e.keyCode?e.keyCode:e.which);
				    if(code==13) { // Enter keycode
				    	_CS.init.InteJijukReset();
				    }
				});*/ 

			}else{
				general_box.show();
				jijuk_srch_box.hide();
				jibun_sch_box.css({"height":"175px"});
				_S.utils.autoParentHeight("con_sch_full_box", "inte_result_content_box", 243);
				_S.utils.autoParentHeight("inte_result_content_box", "inte_result_content", 58);
				
				var hidden_btn = document.getElementById("hidden_btn");
				var detail_btn = document.getElementById("detail_btn");
				
				hidden_btn.onclick = function(){
					_CS.init.InteReset();
				};
				
				$("#detail_btn").show();
				
				detail_btn.onclick = function(){
					_CS.init.getLryIntepage(1);
				};
				
				
				/*$("#hidden_btn").attr("onclick", "_CS.init.InteReset()");
				$("#detail_btn").show();
				$("#detail_btn").attr("onclick", "_CS.init.getLryIntepage(1);");*/
				
				
				
				$("#layer_item").removeOption(/./);
			    $("#layer_item").addOption("", ":: 선택하여주십시오! ::", true);
				
				$.ajax({
					type: 'POST',
			    	url: '../../svc',
			    	data : {svc : "GetLyrInfo", lyr_id:encodeURIComponent(obj)},
			    	dataType: 'xml',
			    	success: function(xml){
						var conditions_box = "";
						if($(xml).find("칼럼정보").length != 0){
							$(xml).find("칼럼정보").each(function(){
								var code = $(this).find("칼럼명").text();
								var name = $(this).find("한글명").text();
								conditions_box += "<option value='" + code + "'>" + name + "</option>";
							});
						}else{
							$("#layer_item").removeOption(/./);
						    $("#layer_item").addOption("", ":: 칼럼이 존재하지 않습니다. ::", true);
						}
						$("#layer_item").append(conditions_box);	
					},
			    	error: function(){
			        	alert('Error loading XML document');
			    	}
				});
				
				//enter 적용
				/*$("#f_layer_name, #layer_item, #cond_type, #included, #match, #range_1, #range_2").keypress(function(e) {
				    var code=(e.keyCode?e.keyCode:e.which);
				    if(code==13) { // Enter keycode
				    	_CS.init.InteReset();
				    }
				});*/
				
				$("#included, #match, #range_1, #range_2").focus(function(e) {
					$("#svh_btn_img").css({"border":"0px dashed red"});
				});

				$("#included").keypress(function(e){
					var code=(e.keyCode?e.keyCode:e.which);
				    if(code==13) { // Enter keycode
				    	$("#svh_btn_img").css({"border":"1px dashed red"});
				    	$("#hidden_btn").focus();
				    	//_CS.init.InteReset();
				    }
				});
				$("#match").keypress(function(e){
					var code=(e.keyCode?e.keyCode:e.which);
				    if(code==13) { // Enter keycode
				    	$("#svh_btn_img").css({"border":"1px dashed red"});
				    	$("#hidden_btn").focus();
				    	//_CS.init.InteReset();
				    }
				});
				$("#range_1").keypress(function(e){
					var code=(e.keyCode?e.keyCode:e.which);
				    if(code==13) { // Enter keycode
				    	$("#range_2").focus();
				    	//_CS.init.InteReset();
				    }
				});
				$("#range_2").keypress(function(e){
					var code=(e.keyCode?e.keyCode:e.which);
				    if(code==13) { // Enter keycode
				    	$("#svh_btn_img").css({"border":"1px dashed red"});
				    	$("#hidden_btn").focus();
				    }
				});
			}
		},
		//지목, 소유자구분, 
		GetCombineCode : function(obj){
			
			var addpo = ""; 
			if(obj == "jimok"){
				addpo = ":: 선택하여주십시오! ::";
			}else if(obj == "owngb"){
				addpo = ":: 선택하여주십시오! ::";
			}else if(obj == "base_year"){
				addpo = ":: 기준년도 ::";
			}
			
			$("#"+obj).removeOption(/./);
		    $("#"+obj).addOption("", addpo, true);
			
			$.ajax({
				type: 'POST',
		    	url: '../../svc',
		    	data : {svc : "GetCombineCode", kind:obj},
		    	dataType: 'xml',
		    	success: function(xml){
					var conditions_box = "";
					$(xml).find("코드").each(function(){
						var code = $(this).find("코드값").text();
						var codenm = $(this).find("코드명").text();
						conditions_box += "<option value='" + code + "'>" + codenm + "</option>";
					});
					$("#"+obj).append(conditions_box);	
				},
		    	error: function(){
		        	alert('Error loading XML document');
		    	}
			});
		},
		//용도지역 --대분류 
		GetUzneCodeDae : function(){
			
			$("#uznecode1").removeOption(/./);
		    $("#uznecode1").addOption("", ":: 대분류 ::", true);
		    $("#uznecode2").removeOption(/./);
			$("#uznecode2").addOption("", ":: 중분류 ::", true);
			$("#uznecode3").removeOption(/./);
			$("#uznecode3").addOption("", ":: 소분류 ::", true);
			
			$.ajax({
				type: 'POST',
		    	url: '../../svc',
		    	data : {svc : "GetUzneCodeDae"},
		    	dataType: 'xml',
		    	success: function(xml){
					var conditions_box = "";
					$(xml).find("코드").each(function(){
						var code = $(this).find("코드값").text();
						var codenm = $(this).find("코드명").text();
						conditions_box += "<option value='" + code + "'>" + codenm + "</option>";
					});
					$("#uznecode1").append(conditions_box);	
				},
		    	error: function(){
		        	alert('Error loading XML document');
		    	}
			});
		},
		//용도지역 --중분류
		GetUzneCodeJoong : function(obj){
			$("#uznecode2").removeOption(/./);
			$("#uznecode2").addOption("", ":: 중분류 ::", true);
			$("#uznecode3").removeOption(/./);
			$("#uznecode3").addOption("", ":: 소분류 ::", true);
			$.ajax({
				type: 'POST',
		    	url: '../../svc',
		    	data : {svc : "GetUzneCodeJoong", law_gbn : encodeURIComponent(obj)},
		    	dataType: 'xml',
		    	success: function(xml){
					var conditions_box = "";
					$(xml).find("코드").each(function(){
						var code = $(this).find("코드값").text();
						var codenm = $(this).find("코드명").text();
						conditions_box += "<option value='" + code + "'>" + codenm + "</option>";
					});
					$("#uznecode2").append(conditions_box);	
				},
		    	error: function(){
		        	alert('Error loading XML document');
		    	}
			});
		},
		//용도지역 --소분류
		GetUzneCodeSo : function(obj){
			$("#uznecode3").removeOption(/./);
			$("#uznecode3").addOption("", ":: 소분류 ::", true);
			$.ajax({
				type: 'POST',
		    	url: '../../svc',
		    	data : {svc : "GetUzneCodeSo", lyr_id : encodeURIComponent(obj)},
		    	dataType: 'xml',
		    	success: function(xml){
					var conditions_box = "";
					$(xml).find("코드").each(function(){
						var code = $(this).find("코드값").text();
						var codenm = $(this).find("코드명").text();
						conditions_box += "<option value='" + code + "'>" + codenm + "</option>";
					});
					$("#uznecode3").append(conditions_box);	
				},
		    	error: function(){
		        	alert('Error loading XML document');
		    	}
			});
		},
		termsChange : function(obj){
			
			switch(obj){
				case "INCLUDED" :
					$("#included_box").show("fast");
					$("#match_box").hide();
					$("#range_box").hide();
					$("#match").val("");
					$("#range_1").val("");
					$("#range_2").val("");
					break;
				case "MATCH" :
					$("#match_box").show("fast");
					$("#included_box").hide();
					$("#range_box").hide();
					$("#included").val("");
					$("#range_1").val("");
					$("#range_2").val("");
					break;
				case "RANGE" :
					$("#range_box").show("fast");
					$("#match_box").hide();
					$("#included_box").hide();
					$("#included").val("");
					$("#match").val("");
					break;
				case "" :
					$("#included_box").hide();
					$("#match_box").hide();
					$("#range_box").hide();
					$("#included").val("");
					$("#match").val("");
					$("#range_1").val("");
					$("#range_2").val("");
					break;
			}
		},
		//통합검색연속지적검색시
		InteJijukReset : function(){
			_PA.info.start = 0;  
			_PA.info.end = 0;
			_PA.info.pageSize = 15;
			_PA.info.pageBlock = 5;
			_PA.info.pageCount = 0;
			_PA.info.startPage = 0;
			_PA.info.endPage = 0;
			_PA.info.number = 0;
			_PA.info.count = 0;
			_PA.info.currentPage = 0;
			_PA.info.pageNum = 0;
			_CS.init.InteJijukpaging(0);
		},
		//통합검색 페이지값
		InteReset : function(){
			_PA.info.start = 0;  
			_PA.info.end = 0;
			_PA.info.pageSize = 15;
			_PA.info.pageBlock = 5;
			_PA.info.pageCount = 0;
			_PA.info.startPage = 0;
			_PA.info.endPage = 0;
			_PA.info.number = 0;
			_PA.info.count = 0;
			_PA.info.currentPage = 0;
			_PA.info.pageNum = 0;
			_CS.init.Intepaging(0);
		},
		InteJijukpaging : function(pagenum){

			var sgg = $("#sgg").val();
			var umd = $("#umd").val();
			var ri = $("#ri").val();
			var owngb = $("#owngb").val();
			var jimok = $("#jimok").val();
			var uznecode1 = $("#uznecode1").val();
			var uznecode2 = $("#uznecode2").val();
			var uznecode3 = $("#uznecode3").val();
			var area1 = $("#area1").val();
			var area2 = $("#area2").val();
			var base_year = $("#base_year").val();
			var jiga1 = $("#jiga1").val();
			var jiga2 = $("#jiga2").val();
			
			if(sgg == ""){
				alert("시군구를 선택 하여 주십시오!");
				$("#sgg").focus();
				return false;
			}
			
			if(umd == ""){
				alert("읍면동을 선택 하여 주십시오!");
				$("#umd").focus();
				return false;
			}
			
			if (_PA.info.pageNum == 0){ 
				_PA.info.pageNum = 1; 
	    	}else{
	    		_PA.info.pageNum = pagenum;
		    }
			
			$.ajax({
				type:"POST",
				url:"./search/cntxml/intejijuk_cnt.jsp",
				data : {sgg : sgg, umd:umd, ri : ri, owngb:owngb, jimok:jimok, uznecode1:uznecode1,  uznecode2:uznecode2, uznecode3:uznecode3, area1:area1, area2:area2, base_year:base_year, jiga1:jiga1, jiga2:jiga2},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("data").length != 0){
						_PA.info.currentPage = Number(_PA.info.pageNum); 
						_PA.info.start = (_PA.info.currentPage - 1) * _PA.info.pageSize + 1;  // 블럭당 보이는 글의 수 시작값
						_PA.info.end = _PA.info.start + _PA.info.pageSize - 1;     //블럭당 보이는 글의수 끝값
						_PA.info.count = $(xml).find("data").find("tot").text();
						_PA.info.number = _PA.info.count - (_PA.info.currentPage - 1) * _PA.info.pageSize;  //인덱스 값 구하기
						_CS.init.GetIntegrationjijuk(sgg, umd, ri, owngb, 
												jimok, uznecode1, uznecode2, 
												uznecode3, area1, area2, base_year, 
												jiga1, jiga2,
												_PA.info.start, _PA.info.pageSize);
					}
				},
				error: function(xhr, status, error) {
						alert("에러");
				}
			});
			
			
		},
		//통합검색 count
		Intepaging : function(pagenum){
			
			var layer_name = $("#f_layer_name").val();
			var layer_item = $("#layer_item").val();
			var cond_type = $("#cond_type").val();
			
			//검색조건 where절
			var included = $("#included").val();
			var match = $("#match").val();
			var range_1 = $("#range_1").val();
			var range_2 = $("#range_2").val();
			
			if(layer_name == ""){
				$("#mess_txt_box").html("* <b>대상레이어</b>를 선택 하십시오!");
				$("#layer_name").focus();
				return false;
			}
			if(layer_item == ""){
				$("#mess_txt_box").html("* <b>대상항목</b>을 선택 하십시오!");
				$("#layer_item").focus();
				return false;
			}
			if(cond_type == ""){
				$("#mess_txt_box").html("* <b>조건</b>을 선택 하십시오!");
				$("#cond_type").focus();
				return false;
			}
			
			if(cond_type == "INCLUDED"){
				if(included == ""){
					$("#mess_txt_box").html("* <b>조건검색값</b>을 입력 하십시오!");
					$("#included").focus();
					return false;
				}
			}else if(cond_type == "MATCH"){
				if(match == ""){
					$("#mess_txt_box").html("* <b>조건검색값</b>을 입력 하십시오!");
					$("#match").focus();
					return false;
				}
			}else if(cond_type == "RANGE"){
				if(range_1 == ""){
					$("#mess_txt_box").html("* <b>조건검색값</b>을 입력 하십시오!");
					$("#range_1").focus();
					return false;
				}
				if(range_2 == ""){
					$("#mess_txt_box").html("* <b>조건검색값</b>을 입력 하십시오!");
					$("#range_2").focus();
					return false;
				}
			}
			
			if (_PA.info.pageNum == 0){ 
				_PA.info.pageNum = 1; 
	    	}else{
	    		_PA.info.pageNum = pagenum;
		    }
			
			$.ajax({
				type:"GET",
				url:"./search/cntxml/inte_cnt.jsp",
				data : {lry_id : layer_name, lry_nm:layer_item, cond_type : cond_type, included:included, match:match, range_1:range_1, range_2:range_2},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("data").length != 0){
						_PA.info.currentPage = Number(_PA.info.pageNum); 
						_PA.info.start = (_PA.info.currentPage - 1) * _PA.info.pageSize + 1;  // 블럭당 보이는 글의 수 시작값
						_PA.info.end = _PA.info.start + _PA.info.pageSize - 1;     //블럭당 보이는 글의수 끝값
						_PA.info.count = $(xml).find("data").find("tot").text();
						_PA.info.number = _PA.info.count - (_PA.info.currentPage - 1) * _PA.info.pageSize;  //인덱스 값 구하기
						_CS.init.GetIntegration(layer_name, layer_item, cond_type, included, match, range_1, range_2, _PA.info.start, _PA.info.pageSize);
					}
				},
				error: function(xhr, status, error) {
						alert("에러");
				}
			});
		},
		//통합검색 연속지적 선택 했을시 2011-12-16
		GetIntegrationjijuk : function(sgg, umd, ri, owngb, 
										jimok, uznecode1, uznecode2, 
										uznecode3, area1, area2, base_year, 
										jiga1, jiga2,start, pageSize){
			$.ajax({
			       type: "POST",
			       url: "../../svc",
			       data: {svc : "GetLyrSrchInteList", sgg : sgg, umd : umd, ri : ri, owngb : owngb, 
													jimok : jimok, uznecode1 : uznecode1, uznecode2 : uznecode2, 
													uznecode3 : uznecode3, area1 : area1, area2 : area2, base_year : base_year, 
													jiga1 : jiga1, jiga2 : jiga2, snum : start, pagenum : pageSize},
			       dataType: "xml",
			       success: function(xml){
			    	   var str="";
			    	   if($(xml).find('통합').length != 0){
			    		   $(xml).find('통합').each(function(idx, item){
			    			
			    			var code = $(this).find("관리코드").text();
			    			var val = $(this).find("결과값").text();
			    			
			    			str+="<div style='margin-top:2px;'>";
				    			str+="<table border='0' cellpadding='0' cellspacing='0' width='100%;' height='auto' bgcolor='#ffffff' style='border:1px solid #86c0ef'>";
					    			str+="<tr align='center'>";
						    			str+="<td width='100%' height='25'align='left'>&nbsp;- <a href='#' onclick='_MAINMAP.searchAndMoveNoScale(\"연속지적\", \""+code+"\");'>" + val + "</a></td>";
					    			str+="</tr>";
				    			str+="</table>";
			    			str+="</div>";

			    		   });
				           $("#inte_result_content").html(str);
				           //페이징처리
				           $("#total_page").html(_PA.init.intebotJijukpageing());
			    	   }else{
			    		   	var pstr = "";
							pstr +="<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>";
							pstr +="<p style='padding: 20px 0 0 0'>검색한결과값이 존재하지않습니다.</p>";
							pstr +="</div>";
							$("#inte_result_content").html(pstr);
							$("#total_page").html("");
			    	   } 
			       },
			       beforeSend : function(){
						$("#inte_result_content_box").showLoading();
					},
					complete : function(){
						$("#inte_result_content_box").hideLoading();
					},
					error: function(xhr, status, error) {
							alert(">>>>>>>>>>> : 에러");
					}
			   	});
		},
		//통합검색
		GetIntegration : function(layer_name, layer_item, cond_type, included, match, range_1, range_2, start, pageSize){
			
			$.ajax({
			       type: "POST",
			       url: "../../svc",
			       data: {svc : "GetLyrSrchList", lry_id : encodeURIComponent(layer_name), lry_nm:layer_item, cond_type : cond_type, 
							included:encodeURIComponent(included), match:encodeURIComponent(match), 
							range_1:encodeURIComponent(range_1), range_2:encodeURIComponent(range_2), snum : start, pagenum : pageSize},
			       dataType: "xml",
			       success: function(xml){
			    	   var str="";
			    	   if($(xml).find('통합').length != 0){
			    		   $(xml).find('통합').each(function(idx, item){
			    			
			    			var code = $(this).find("관리코드").text();
			    			var val = $(this).find("결과값").text();
			    			
			    			str+="<div style='margin-top:2px;'>";
				    			str+="<table border='0' cellpadding='0' cellspacing='0' width='100%;' height='auto' bgcolor='#ffffff' style='border:1px solid #86c0ef'>";
					    			str+="<tr align='center'>";
						    			str+="<td height='20' width='25%' bgcolor='#b2dcfd' style='border-right:1px dotted #c9c8c8;' title='" + code + "'>";
						    			if(code.length > 6){
						    				str+= code.substring(0, 6)+"...";
						    			}else{
						    				str+= code;
						    			}
						    			str+="</td>";
						    			str+="<td width='75%' align='left'>&nbsp;<a href='#' onclick='_MAINMAP.searchAndMoveNoScale(\""+layer_name+"\",\""+code+"\")'>" + val + "</a></td>";
						    			//str+="<td width='65%' align='left' style='border-right:1px dotted #c9c8c8;'>&nbsp;" + val + "</td>";
						    			//str+="<td><img src='img/info.gif' onclick='_DE.inteInfo(\""+ code+"\",\""+layer_name+"\");' style='cursor:pointer;'/></td>";
					    			str+="</tr>";
				    			str+="</table>";
			    			str+="</div>";

			    		   });
				           $("#inte_result_content").html(str);
				           //페이징처리
				           $("#total_page").html(_PA.init.intebotpageing());
			    	   }else{
			    		   	var pstr = "";
							pstr +="<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>";
							pstr +="<p style='padding: 20px 0 0 0'>검색한결과값이 존재하지않습니다.</p>";
							pstr +="</div>";
							$("#inte_result_content").html(pstr);
							$("#total_page").html("");
			    	   } 
			       },
			       beforeSend : function(){
						$("#inte_result_content_box").showLoading();
					},
					complete : function(){
						$("#inte_result_content_box").hideLoading();
					},
					error: function(xhr, status, error) {
							alert(">>>>>>>>>>> : 에러");
					}
			   	});
		},
		//레이어목록을 가져온다.(체크박스)
		GetLayersCheckbox : function(){
			$.ajax({
				type: 'POST',
		    	url: '../../svc',
		    	data : {svc : "GetLayers"},
		    	dataType: 'xml',
		    	success: function(xml){
					var str = "";
					str += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='auto'>";
					$(xml).find("레이어").each(function(){
						var code = $(this).find("테이블ID").text();
						var name = $(this).find("레이어명").text();
						var sho_yn = $(this).find("레이어보이기여부").text();
						var lyr_id = $(this).find("레이어관리ID").text();
						str += "<tr>";
							str += "<td align='center' width='10%'>";
							if(sho_yn == "Y"){
								str += "<input type='checkbox' id='lry_name_chk' name='lry_name_chk' checked='checked' value='" + lyr_id + "'/>";
							}else{
								str += "<input type='checkbox' id='lry_name_chk' name='lry_name_chk'  value='" + lyr_id + "'/>";
							}
							str += "</td>";
							str += "<td width='90%'>" + name + "</td>";
						str += "</tr>";
					});
					str += "</table>";
					$("#layer_name_box").html(str);	
					
					//alert($("#lry_name_chk").length);
					
				},
		    	error: function(){
		        	alert('Error loading XML document');
		    	}
			});
		},
		//영역검색페이지를 호출한다.
		srchPreserveLoding : function(){
			$.ajax({
				url: "./search/srchPreserve.jsp",
				contentType : "text/html; charset=utf-8",
				dataType : "html",
				success: function(htm){
					outerLayout.open("west");
					$("#con_sch_full_box").html(htm);
					reSize(); //사이즈재설정
					LYR_TEMP = new Array();
					SELECT_TEMP = new Array();
					_CS.init.GetVisibleLayerRadioXML(); //현재축척의 레이어 xml을 라디오박스로 불러온다.
					
					gwsUtils.setWorkImgChange('4'); //중단 기본업무 버튼 이미지를 변경한다.
				},
				beforeSend : function(){
				},
				complete : function(){
				},
				error: function(xhr, status, error) {
					    alert(">>>>>>>>>>> : 에러");
				}
			});	
		},
		//사각형검색 및 다각형 검색결과
		GetBoxPolyList : function(wktPolygon){
			
			var list="";
			var lty_len = $("input[name='lry_name_chk']:checked").length;
			
	    if(lty_len == 0){
				alert("검색대상을 선택 하여 주십시오!");
				return false;
			}else{
				//$("input[name='lry_name_chk']:checked").each(function(){list.push(this.value)});
				if(lty_len == 1){
					list = $("input[name='lry_name_chk']:checked").val();
				}else{
					$("input[name='lry_name_chk']:checked").each(function(idx){
						if((idx+1) != lty_len){
							list += this.value + ",";
						}else{
							list += this.value;
						}
					});
				}
			}
			
			var srch_cond = $("input[name='srch_cond']:checked").val();
			
			$.ajax({
			       //type: "GET",
				   type: "POST",
			       url: "../../svc",
			       data: {svc : "GetSrchBoxPolyList", lrys:list, srch_cond : srch_cond, wtk : wktPolygon},
			       dataType: "xml",
			       success: function(xml){
			    	   var str="";
			    	   if($(xml).find('영역검색').length != 0){
			    		   $(xml).find('영역검색').each(function(idx, item){
			    			
			    			var lyr_nm = $(this).find("레이어명").text();
			    			var lyr_id = $(this).find("레이어ID").text();
			    			var resultcnt = $(this).find("결과값").text();
			    			var gid = $(this).find("_gid").text();
			    			var wtk = wktPolygon;
			    			
			    			str+="<div style='margin-top:2px;'>";
				    			str+="<table border='0' cellpadding='0' cellspacing='0' width='100%;' height='auto' bgcolor='#ffffff' style='border:1px solid #86c0ef'>";
					    			str+="<tr align='center'>";
						    			str+="<td height='20' width='45%' bgcolor='#b2dcfd' style='border-right:1px dotted #c9c8c8;'>" + lyr_nm +"</td>";
						    			str+="<td width='45%' align='left' style='border-right:1px dotted #c9c8c8;'>&nbsp;" + resultcnt + "</td>";
						    			//str+="<td width='10%'><img src='img/info.gif' onclick='_CON.skill.contentAttribute(\""+url+"\");' style='cursor:pointer;'/></td>";
						    			str+="<td width='10%'><img src='img/info.gif' onclick='_CS.init.getLryInfopage(\""+lyr_nm+"\",\""+lyr_id+"\",\""+srch_cond+"\", \""+resultcnt +"\",\""+wtk+"\", \"1\");' style='cursor:pointer;'/></td>";
					    			str+="</tr>";
				    			str+="</table>";
			    			str+="</div>";

			    		   });
				           $("#pre_result_content").html(str);
				           //페이징처리
				           //$("#total_page").html(_PA.init.bottompageing());
			    	   }else{
			    		   	var pstr = "";
							pstr +="<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>";
							pstr +="<p style='padding: 20px 0 0 0'>검색한 결과값이 존재하지않습니다.</p>";
							pstr +="</div>";
							$("#pre_result_content").html(pstr);
							$("#total_page").html("");
			    	   } 
			       },
			       beforeSend : function(){
						$("#pre_result_content_box").showLoading();
					},
					complete : function(){
						$("#pre_result_content_box").hideLoading();
					},
					error: function(xhr, status, error) {
						alert(">>>>>>>>>>> : 에러");
					}
			   	});
		},
		//통합검색의 상세보기 페이지로 이동한다.
		getLryIntepage : function(pagenum){
			//수정 2012-12-14
			
			var layer_name = $("#f_layer_name").val();
			var layer_item = $("#layer_item").val();
			var cond_type = $("#cond_type").val();
			
			//검색조건 where절
			var included = $("#included").val();
			var match = $("#match").val();
			var range_1 = $("#range_1").val();
			var range_2 = $("#range_2").val();
			
			if(layer_name == ""){
				$("#mess_txt_box").html("* <b>대상레이어</b>를 선택 하십시오!");
				$("#layer_name").focus();
				return false;
			}
			if(layer_item == ""){
				$("#mess_txt_box").html("* <b>대상항목</b>을 선택 하십시오!");
				$("#layer_item").focus();
				return false;
			}
			if(cond_type == ""){
				$("#mess_txt_box").html("* <b>조건</b>을 선택 하십시오!");
				$("#cond_type").focus();
				return false;
			}
			
			if(cond_type == "INCLUDED"){
				if(included == ""){
					$("#mess_txt_box").html("* <b>조건검색값</b>을 입력 하십시오!");
					$("#included").focus();
					return false;
				}
			}else if(cond_type == "MATCH"){
				if(match == ""){
					$("#mess_txt_box").html("* <b>조건검색값</b>을 입력 하십시오!");
					$("#match").focus();
					return false;
				}
			}else if(cond_type == "RANGE"){
				if(range_1 == ""){
					$("#mess_txt_box").html("* <b>조건검색값</b>을 입력 하십시오!");
					$("#range_1").focus();
					return false;
				}
				if(range_2 == ""){
					$("#mess_txt_box").html("* <b>조건검색값</b>을 입력 하십시오!");
					$("#range_2").focus();
					return false;
				}
			}
			
			var url = "lyr/lyr_moreInte.jsp";
			var param = "?lry_id="+encodeURIComponent(layer_name)+"&lry_nm="+layer_item+"&cond_type="+cond_type;
				param += "&included="+encodeURIComponent(included)+"&match="+encodeURIComponent(match);
				param += "&range_1="+encodeURIComponent(range_1)+"&range_2="+encodeURIComponent(range_2)+"&pagenum="+pagenum;
			_CON.skill.contentAttribute(url+""+param);
		},

		//통합검색의 상세보기 연속지적 페이지로 이동한다.
		getLryInteJijukpage : function(pagenum){
			//수정 2012-12-16

			var sgg = $("#sgg").val();
			var umd = $("#umd").val();
			var ri = $("#ri").val();
			var owngb = $("#owngb").val();
			var jimok = $("#jimok").val();
			var uznecode1 = $("#uznecode1").val();
			var uznecode2 = $("#uznecode2").val();
			var uznecode3 = $("#uznecode3").val();
			var area1 = $("#area1").val();
			var area2 = $("#area2").val();
			var base_year = $("#base_year").val();
			var jiga1 = $("#jiga1").val();
			var jiga2 = $("#jiga2").val();
			
			
			if(sgg == ""){
				alert("시군구를 선택하여주십시오!");
				$("#sgg").focus();
				return false;
			}
			if(umd == ""){
				alert("읍면동을 선택하여주십시오!");
				$("#umd").focus();
				return false;
			}
			
			
			var url = "lyr/lyr_moreInteJijuk.jsp";
			var param = "?sgg="+sgg+"&umd="+umd+"&ri="+ri;
				param += "&owngb="+owngb+"&jimok="+jimok;
				param += "&uznecode1="+uznecode1+"&uznecode2="+uznecode2;
				param += "&uznecode3="+uznecode3+"&area1="+area1;
				param += "&area2="+area2+"&base_year="+base_year;
				param += "&jiga1="+jiga1+"&jiga2="+jiga2;
				param += "&pagenum="+pagenum;
				
			_CON.skill.contentAttribute(url+""+param);
			
		},

		//레이어상세정보 페이지로 이동후 ajax 호출
		getLryInfopage : function(lyr_nm, lyr_id, srch_cond, resultcnt, wtk, pagenum){
			//var param = "?lyr_nm="+encodeURIComponent(lyr_nm)+"&lyr_id="+lyr_id+"&srch_cond="+srch_cond+"&resultcnt="+resultcnt+"&wtk="+wtk+"&pagenum="+pagenum;
			var url = "lyr/lyr_moreInfo2.jsp";
			var param = {lyr_nm : lyr_nm, lyr_id : lyr_id, srch_cond : srch_cond, resultcnt : resultcnt, wtk : wtk, pagenum : pagenum};
			_CON.skill.contentAdd(url,"",param);
			//_CON.skill.contentAttribute(url+""+param);
			/*_CON.skill.division('h_div');
			_CON.skill.srch("srch");
			var divsero = document.getElementById("divsero");
			var divgaro = document.getElementById("divgaro");
			
			divsero.onclick = function(){

			},divgaro.onclick = function(){
				
			};*/
			
		},
		//반경검색_레이어상세정보 페이지로 이동후 ajax 호출
		getLryCircleInfopage : function(lyr_nm,lyr_id,srch_cond,resultcnt,cx,cy,distance, pagenum){
			//var param = "?lyr_nm="+encodeURIComponent(lyr_nm)+"&lyr_id="+lyr_id+"&srch_cond="+srch_cond+"&resultcnt="+resultcnt+"&cx="+cx+"&cy="+cy+"&distance="+distance+"&pagenum="+pagenum;
			var url = "lyr/lyr_circleInfo2.jsp";
			var param = {lyr_nm : lyr_nm, lyr_id : lyr_id, srch_cond : srch_cond, resultcnt : resultcnt, cx : cx, cy : cy, distance : distance, pagenum : pagenum};
			_CON.skill.contentAdd(url,"",param);
			//_CON.skill.srch('srch', url, param);
			/*_CON.skill.division('h_div');
			_CON.skill.srch("srch");
			var divsero = document.getElementById("divsero");
			var divgaro = document.getElementById("divgaro");
			
			divsero.onclick = function(){

			},divgaro.onclick = function(){
				
			};*/
			//var url = "lyr/lyr_circleinfo.jsp";
			//_CON.skill.contentAttribute(url);
			//_PC.init.getLyrCircleInfo(lyr_nm,lyr_id,srch_cond,resultcnt,cx,cy,distance);			
		},
		//반경검색
		GetCircleList : function(cx, cy, distance){
			var list="";
			var lty_len = $("input[name='lry_name_chk']:checked").length;
			
			if(lty_len == 0){
				alert("검색대상을 선택 하여 주십시오!");
				return false;
			}else{
				//$("input[name='lry_name_chk']:checked").each(function(){list.push(this.value)});
				if(lty_len == 1){
					list = $("input[name='lry_name_chk']:checked").val();
				}else{
					$("input[name='lry_name_chk']:checked").each(function(idx){
						if((idx+1) != lty_len){
							list += this.value + ",";
						}else{
							list += this.value;
						}
					});
				}
			}
			
			var srch_cond = $("input[name='srch_cond']:checked").val();
			
			
			$.ajax({
			       //type: "GET",
				   type: "POST",
			       url: "../../svc",
			       data: {svc : "GetSrchCircleList", lrys:list, srch_cond : srch_cond, cx : cx, cy : cy, distance:distance},
			       dataType: "xml",
			       success: function(xml){
			    	   var str="";
			    	   if($(xml).find('반경검색').length != 0){
			    		   $(xml).find('반경검색').each(function(idx, item){
			    			
		    			    var lyr_nm = $(this).find("레이어명").text();
			    			var lyr_id = $(this).find("레이어ID").text();
			    			var resultcnt = $(this).find("결과값").text();
			    			var gid = $(this).find("_gid").text();
			    			var cx_cir = cx;
			    			var cy_cir = cy;
			    			var distance_cir = distance;
			    			
			    			str+="<div style='margin-top:2px;'>";
				    			str+="<table border='0' cellpadding='0' cellspacing='0' width='100%;' height='auto' bgcolor='#ffffff' style='border:1px solid #86c0ef'>";
					    			str+="<tr align='center'>";
						    			str+="<td height='20' width='45%' bgcolor='#b2dcfd' style='border-right:1px dotted #c9c8c8;'>" + lyr_nm +"</td>";
						    			str+="<td width='45%' align='left' style='border-right:1px dotted #c9c8c8;'>&nbsp;" + resultcnt + "</td>";
						    			str+="<td width='10%'><img src='img/info.gif' onclick='_CS.init.getLryCircleInfopage(\""+lyr_nm+"\",\""+lyr_id+"\",\""+srch_cond+"\", \""+resultcnt +"\",\""+cx_cir+"\",\""+cy_cir+"\",\""+distance_cir+"\",\"1\");' style='cursor:pointer;'/></td>";
						    			//str+="<td width='10%'><img src='img/info.gif' onclick='_CON.skill.contentAttribute(\"lyr/lyr_moreinfo.jsp\");' style='cursor:pointer;'/></td>";
					    			str+="</tr>";
				    			str+="</table>";
			    			str+="</div>";

			    		   });
				           $("#pre_result_content").html(str);
				           //페이징처리
				           //$("#total_page").html(_PA.init.bottompageing());
			    	   }else{
			    		   	var pstr = "";
							pstr +="<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>";
							pstr +="<p style='padding: 20px 0 0 0'>검색한 결과값이 존재하지않습니다.</p>";
							pstr +="</div>";
							$("#pre_result_content").html(pstr);
							$("#total_page").html("");
			    	   } 
			       },
			       beforeSend : function(){
						$("#pre_result_content_box").showLoading();
					},
					complete : function(){
						$("#pre_result_content_box").hideLoading();
					},
					error: function(xhr, status, error) {
							alert(">>>>>>>>>>> : 에러");
					}
			   	});
		},
		//버퍼검색페이지를 호출한다.
		srchBufferLoding : function(){
			$.ajax({
				url: "./search/srchBuffer.jsp", //초기호출
				contentType : "text/html; charset=utf-8",
				dataType : "html",
				success: function(htm){
					outerLayout.open("west");
					$("#con_sch_full_box").html(htm);
					reSize(); //사이즈재설정
					LYR_TEMP = new Array(); 
					SELECT_TEMP = new Array();
					_CS.init.GetVisibleLayerXML();//현재축척의 레이어 xml을 가지고온다.
					_CS.init.GetVisibleLayerRadioXML(); //현재축척의 레이어 xml을 라디오박스로 불러온다.
					
					gwsUtils.setWorkImgChange('5'); //메뉴이미지5번째
				},
				beforeSend : function(){
				},
				complete : function(){
				},
				error: function(xhr, status, error) {
						alert(">>>>>>>>>>> : 에러");
				}
			});	
		},
		//버퍼검색
		GetBufferList : function(wktPolygon){
			
			var list="";
			var lty_len = $("input[name='lry_name_chk']:checked").length;
			
			if(lty_len == 0){
				alert("검색대상을 선택 하여 주십시오!");
				return false;
			}else{
				if(lty_len == 1){
					list = $("input[name='lry_name_chk']:checked").val();
				}else{
					$("input[name='lry_name_chk']:checked").each(function(idx){
						if((idx+1) != lty_len){
							list += this.value + ",";
						}else{
							list += this.value;
						}
					});
				}
			}
			var srch_cond = $("input[name='srch_cond']:checked").val();
			//encodeURIComponent()
			$.ajax({
			       //type: "GET",
				   type: "POST",
			       url: "../../svc",
			       data: {svc : "GetSrchBufferList",lrys:list, srch_cond : srch_cond, wtk : wktPolygon},
			       dataType: "xml",
			       success: function(xml){
			    	   var str="";
			    	   if($(xml).find('버퍼검색').length != 0){ //요청한 xml데이터에서 버퍼검색태그의 개수가 0과 같지 않을 경우 블록 내의 실행문을 실행
			    		   $(xml).find('버퍼검색').each(function(idx, item){ //버퍼검색 태그의 총 개수만큼 function(){...} 실행
			    			
			    			    var lyr_nm = $(this).find("레이어명").text(); //버퍼검색 태그만큼 함수 내의 실행문이 실행될대마다 $(this)에는 버퍼검색 태그가 순차적으로 선택된다 
				    			var lyr_id = $(this).find("레이어ID").text();  //그리고 레이어명 태그에 텍스트 변수lyr_nm을 할당한다.
				    			var resultcnt = $(this).find("결과값").text();
				    			var gid = $(this).find("_gid").text();
				    			var wtk = wktPolygon;
				    			
				    			str+="<div style='margin-top:2px;'>";
					    			str+="<table border='0' cellpadding='0' cellspacing='0' width='100%;' height='auto' bgcolor='#ffffff' style='border:1px solid #86c0ef'>";
						    			str+="<tr align='center'>";
							    			str+="<td height='20' width='45%' bgcolor='#b2dcfd' style='border-right:1px dotted #c9c8c8;'>" + lyr_nm +"</td>";
							    			str+="<td width='45%' align='left' style='border-right:1px dotted #c9c8c8;'>&nbsp;" + resultcnt + "</td>";
							    			str+="<td width='10%'><img src='img/info.gif' onclick='_CS.init.getLryInfopage(\""+lyr_nm+"\",\""+lyr_id+"\",\""+srch_cond+"\", \""+resultcnt +"\",\""+wtk+"\", \"1\");' style='cursor:pointer;'/></td>";
						    			str+="</tr>";
					    			str+="</table>";
				    			str+="</div>";

			    		   });
				           $("#buf_result_content").html(str);
				           //페이징처리
				           //$("#total_page").html(_PA.init.bottompageing());
			    	   }else{
			    		   	var pstr = "";
							pstr +="<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>";
							pstr +="<p style='padding: 20px 0 0 0'>검색한 결과값이 존재하지않습니다.</p>";
							pstr +="</div>";
							$("#buf_result_content").html(pstr);
							$("#total_page").html("");
			    	   } 
			       },
			       beforeSend : function(){
						$("#buf_result_content_box").showLoading();
					},
					complete : function(){
						$("#buf_result_content_box").hideLoading();
					},
					error: function(xhr, status, error) {
						alert(">>>>>>>>>>> : 에러");
					}
			   	});
		},
		radioCtrl : function(obj){
			if(obj != ""){
				$(":radio[name='srch_target']").attr("disabled",true);
				$(":radio[name='srch_target']").attr("checked",false);
			}else{
				$(":radio[name='srch_target']").attr("disabled",false);
			}
		},
		//정보(속성)보기페이지를 호출한다.
		srchInfoLoding : function(){
			$.ajax({
				url: "./search/srchInfo.jsp",
				contentType : "text/html; charset=utf-8",
				dataType : "html",
				success: function(htm){
					outerLayout.open("west");
					$("#con_sch_full_box").html(htm);
					reSize(); //사이즈재설정
					LYR_TEMP = new Array();
					SELECT_TEMP = new Array();
					_CS.init.srchInfoLyrXML();
				},
				beforeSend : function(){
				},
				complete : function(){
				},
				error: function(xhr, status, error) {
						alert(">>>>>>>>>>> : 에러");
				}
			});	
		},
		//좌표검색 2012-04-24
		srchCoordLoading : function(){
			$.ajax({
				url: "./search/srchCoord.jsp",
				contentType : "text/html; charset=utf-8",
				dataType : "html",
				success: function(htm){
					outerLayout.open("west");
					$("#con_sch_full_box").html(htm);
					reSize(); //사이즈재설정
				},
				beforeSend : function(){
				},
				complete : function(){
				},
				error: function(xhr, status, error) {
						alert(">>>>>>>>>>> : 에러");
				}
			});	
		},
		//좌표검색 폼 변경 2012-04-24 
		srchCoordFormChange : function(val){
			if(val == "tm"){
				$("#now_Coord_box").hide();
				$("#coord_srch_box_3").hide();
				$("#coord_srch_box_2").hide();
				$("#coord_srch_box_1").show();
			}else if(val == "test"){
				$("#now_Coord_box").hide();
				$("#coord_srch_box_1").hide();
				$("#coord_srch_box_2").hide();
				$("#coord_srch_box_3").show();
			}else{
				$("#now_Coord_box").hide();
				$("#coord_srch_box_1").hide();
				$("#coord_srch_box_2").show();
				$("#coord_srch_box_3").hide();
			}
		},
		//좌표검색의DMS, Degree 폼 변경
		boxFormChange : function(val){
			if(val == "dms"){
				$("#coord_kind_box_2").hide();
				$("#coord_kind_box_1").show();
			}else{
				$("#coord_kind_box_1").hide();
				$("#coord_kind_box_2").show();
			}
		},
		//TM전환 2012-04-24
		coordConvertCheck1 : function(){
			var oval = $("input[name=oval]:checked").val();
			var s_mean = $("#s_means option:selected").val();
			var s_add = $("input:checkbox[id='s_add']").is(":checked");
			var s_add_text = "";
			var XN = $("#XN").val();
			var YE = $("#YE").val();
			
			
			if(XN == ""){
				alert("X값을 입력하여 주십시오!");
				$("#XN").focus();
				return false;
			}
			
			if(YE == ""){
				alert("Y값을 입력하여 주십시오!");
				$("#YE").focus();
				return false;
			}
			
			
			var _COORD = "";
			var _SP = "";
			var _TC = ""; 
			
			if(s_add){
				s_add_text = "Y";
			}else{
				s_add_text = "N";
			}
			
			if(s_mean == "s_1"){//중부
				_COORD = "TM127";
				_SP = "500000.0";
				if(s_add_text == "Y"){
					_TC = "127.00289";
				}else{
					_TC = "127.0";
				}
			}else if(s_mean == "s_2"){ //서부
				_COORD = "TM125";
				_SP = "500000.0";
				if(s_add_text == "Y"){
					_TC = "125.00289";
				}else{
					_TC = "125.0";
				}
			}else if(s_mean == "s_3"){//동부
				_COORD = "TM129";
				_SP = "500000.0";
				if(s_add_text == "Y"){
					_TC = "129.00289";
				}else{
					_TC = "129.0";
				}
			}else if(s_mean == "s_4"){//동해
				_COORD = "TM131";
				_SP = "500000.0";
				if(s_add_text == "Y"){
					_TC = "131.00289";
				}else{
					_TC = "131.0";
				}
			}else if(s_mean == "s_5"){//중부
				_COORD = "TM127";
				_SP = "600000.0";
				if(s_add_text == "Y"){
					_TC = "127.00289";
				}else{
					_TC = "127.0";
				}
			}else if(s_mean == "s_6"){//서부
				_COORD = "TM125"; 
				_SP = "600000.0";
				if(s_add_text == "Y"){
					_TC = "125.00289";
				}else{
					_TC = "125.0";
				}
			}else if(s_mean == "s_7"){//동부
				_COORD = "TM129";
				_SP = "600000.0";
				if(s_add_text == "Y"){
					_TC = "129.00289";
				}else{
					_TC = "129.0";
				}
			}else if(s_mean == "s_8"){//동해
				_COORD = "TM131";
				_SP = "600000.0";
				if(s_add_text == "Y"){
					_TC = "131.00289";
				}else{
					_TC = "131.0";
				}
			}
			
			
			$.ajax({
				type:"post",
				url: "./search/coord/CoordChangeXML.jsp",
			    data: {OVAL:oval, COORD : _COORD, SP : _SP, TC:_TC, XN : XN, YE : YE},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("Coord").length != 0){
						var x = $(xml).find("x").text();
						var y = $(xml).find("y").text();
						var lat = $(xml).find("latitude").text(); //위도
						var long = $(xml).find("longitude").text(); //경도
						_MAINMAP.locateCenter(x, y);
						$("#now_Coord_box").show();
						$("#la_text").text("위도");
						$("#lo_text").text("경도");
						$("#x_text").text(lat);
						$("#y_text").text(long);
					}
				},
				beforeSend : function(){},
				complete : function(){},
				error: function(xhr, status, error) {alert(">>>>>> : " + error);}
			});
			
			
		},
		//DMS, degree 2012-04-24
		coordConvertCheck2 : function(){
			
			var kk = $("input[name=lat_Kind]:checked").val();
			var dd = $("input[name=dd]:checked").val();
			
			if(kk == "BESSEL"){
				kk = "BESSEL";
			}else{
				kk = "GRS80";
			}
			
			if(dd == "dms"){
				
				var latD1 = parseFloat($("#dms_d_1").val()) || 0;
				var latM1 = parseFloat($("#dms_m_1").val()) || 0;
				var latS1 = parseFloat($("#dms_s_1").val()) || 0;
				
				var latD2 = parseFloat($("#dms_d_2").val()) || 0;
				var latM2 = parseFloat($("#dms_m_2").val()) || 0;
				var latS2 = parseFloat($("#dms_s_2").val()) || 0;
				
				
				
				var latDMS1 = latD1 + (latM1/60) + (latS1/3600); //경도 도분초 계산
				var latDMS2 = latD2 + (latM2/60) + (latS2/3600); //위도 도분초 계산
				
				
				/*$.ajax({
					type: "POST",
				    url: "../../svc",
				    data: {svc : "GetDaumCoord", apikey:"6402f804eddb06dd403efffbf97fdf6d6a7fd0fd", x:latDMS1, y:latDMS2, fromCoord:"WGS84", toCoord:"WTM", output:"xml"},
					dataType : "xml",
					success: function(xml){
						if($(xml).find('result').length != 0){
							var x = $(xml).find('result').attr("x");
							var y = $(xml).find('result').attr("y");
							_MAINMAP.locateCenter(x, y);
							$("#now_Coord_box").show();
							$("#x_text").text(x);
							$("#y_text").text(y);
						}else{
							if($(xml).find('apierror').length != 0){
								alert($(xml).find('dmessage').text());
							}
						}
					},
					beforeSend : function(){},
					complete : function(){},
					error: function(xhr, status, error){
						alert(">>>>>> : " + error);
					}	
				});*/
				
				$.ajax({
					type:"post",
					url:"./search/coord/CoordDMSXML.jsp",
					data : {latDMS1:latDMS1, latDMS2:latDMS2, COORD:kk},
					dataType : "xml",
					success: function(xml){
						if($(xml).find("Coord").length != 0){
							var x = $(xml).find("x").text();
							var y = $(xml).find("y").text();
							_MAINMAP.locateCenter(x, y);
							$("#now_Coord_box").show();
							$("#la_text").text("X(남북)");
							$("#lo_text").text("Y(동서)");
							$("#x_text").text(y);
							$("#y_text").text(x);
						}
					},
					beforeSend : function(){},
					complete : function(){},
					error: function(xhr, status, error) {
							alert(">>>>>> : " + error);
					}
				});
				
			}else{
				
				var degree_d1 = $("#degree_d1").val(); //경도
				var degree_d2 = $("#degree_d2").val(); //위도
				
				if(degree_d1 == ""){
					alert("경도를 입력하여 주십시오!");
					$("#degree_d1").focus();
					return false;
				}
				
				if(degree_d2 == ""){
					alert("위도를 입력하여 주십시오!");
					$("#degree_d2").focus();
					return false;
				}
				
				/*$.ajax({
					type: "POST",
				    url: "../../svc",
				    data: {svc : "GetDaumCoord", apikey:"6402f804eddb06dd403efffbf97fdf6d6a7fd0fd", x:degree_d1, y:degree_d2, fromCoord:"WGS84", toCoord:"WTM", output:"xml"},
					dataType : "xml",
					success: function(xml){
						if($(xml).find('result').length != 0){
							var x = $(xml).find('result').attr("x");
							var y = $(xml).find('result').attr("y");
							_MAINMAP.locateCenter(x, y);
							$("#now_Coord_box").show();
							$("#x_text").text(x);
							$("#y_text").text(y);
						}else{
							if($(xml).find('apierror').length != 0){
								alert($(xml).find('dmessage').text());
							}
						}
					},
					beforeSend : function(){},
					complete : function(){},
					error: function(xhr, status, error){
						alert(">>>>>> : " + error);
					}	
				});*/
				
				$.ajax({
					type:"post",
					url:"./search/coord/CoordDMSXML.jsp",
					data : {latDMS1:degree_d1, latDMS2:degree_d2, COORD:kk},
					dataType : "xml",
					success: function(xml){
						if($(xml).find("Coord").length != 0){
							var x = $(xml).find("x").text();
							var y = $(xml).find("y").text();
							_MAINMAP.locateCenter(x, y);
							$("#now_Coord_box").show();
							$("#la_text").text("X(남북)");
							$("#lo_text").text("Y(동서)");
							$("#x_text").text(y);
							$("#y_text").text(x);
						}
					},
					beforeSend : function(){},
					complete : function(){},
					error: function(xhr, status, error) {
							alert(">>>>>> : " + error);
					}
				});
				
			}
		},
		coordConvertCheck3 : function(){
			var zone = $("#ZONE").val();
			var east = $("#EAST").val();
			var north = $("#NORTH").val();
			
			$.ajax({
				type:"post",
				url:"./search/coord/CoordMGRSXML.jsp",
				data : {zone:zone, east:east, north:north},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("Coord").length != 0){
						var x = $(xml).find("x").text();
						var y = $(xml).find("y").text();
						var lat = $(xml).find("latitude").text();
						var lon = $(xml).find("longitude").text();
						var wkt = $(xml).find("wkt").text();
						var style = $(xml).find("style").text();
						_MAINMAP.setClearScreen();
						_MAINMAP.addShapeAndMove(style, wkt);
						_MAINMAP.locateCenter(y, x);
						$("#now_Coord_box").show();
						$("#la_text").text("위도");
						$("#lo_text").text("경도");
						$("#x_text").text(lat);
						$("#y_text").text(lon);
					}else{
						alert("좌표를 정확히 입력해 주세요!");
					}
				},
				beforeSend : function(){},
				complete : function(){},
				error: function(xhr, status, error) {
						alert(">>>>>> : " + error);
				}
			});
		},
		srchInfoLyrXML : function(){
			//xml을 가지고온다.
			var xml = "<?xml version='1.0' encoding='UTF-8'?>";
				xml += _MAINMAP.getVisibleLayerXML();//현재축적을 XML로 불러옴
			var xmlDoc = $.parseXML(xml);
			var $xml = $(xmlDoc);
			
			$("#info_layer_name").removeOption(/./);
			//$("#info_layer_name").addOption("", ":: 소분류 ::", false);
			
			if($xml.find("MapLayers").length != 0){
				var conditions_box = "";
				$xml.find("Group").each(function(grpidx){
					
					var GN = $(this).attr("name");
					
					if(GN != "항공영상"){
						//var lrynm = $(this).find("Layer").text();
						$(this).find("Layer").each(function(idx){
							var lrynm = $(this).text();
							//if(lrynm != "" && lrynm != "네이버영상"){
								if(SELECT_TEMP.length != 0){
									if(SELECT_TEMP[0] == lrynm){
										conditions_box += "<option value='" + lrynm + "' selected='selected'>" + lrynm + "</option>";
									}else{
										conditions_box += "<option value='" + lrynm + "'>" + lrynm + "</option>";
									}
								}else{
									conditions_box += "<option value='" + lrynm + "'>" + lrynm + "</option>";
								}
							//}
						});
					}
				});
				$("#info_layer_name").append(conditions_box);
				/*if($("#info_layer_name > option").lenght != 0){
					$("#info_layer_name > option").each(function(idx){
						var option = $(this);
						if((idx+1) == 2){
							option.attr("selected", true);
						}else{
							option.attr("selected", false);
						}
					});
					//정보보기 초기셋팅값으로 설정 호출 한다.
					_MAINMAP.selectInfoLayer();
				}*/
			}
		},
		
		setSelectObjArr : function(obj){
			
			SELECT_TEMP  = new Array();
			SELECT_TEMP[0] = obj;
			
		},
		
		//현재축척의 레이어 xml을 가지고온다.
		GetVisibleLayerXML : function(){
			//xml을 가지고온다.
			
			$("#layer_name").removeOption(/./);
			//$("#info_layer_name").addOption("", ":: 소분류 ::", false);

			var xml = "<?xml version='1.0' encoding='UTF-8'?>";
				xml += _MAINMAP.getVisibleLayerXML();
			var xmlDoc = $.parseXML(xml);
			var $xml = $(xmlDoc);
			
			if($xml.find("MapLayers").length != 0){
				var conditions_box = "";
				$xml.find("Group").each(function(){
					var GN = $(this).attr("name");
					if(GN != "항공영상"){
						//var lrynm = $(this).find("Layer").text();
						$(this).find("Layer").each(function(idx){
							var lrynm = $(this).text();
							//if(lrynm != "" && lrynm != "네이버영상"){
								if(SELECT_TEMP.length != 0){
									if(SELECT_TEMP[0] == lrynm){
										conditions_box += "<option value='" + lrynm + "' selected='selected'>" + lrynm + "</option>";
									}else{
										conditions_box += "<option value='" + lrynm + "'>" + lrynm + "</option>";
									}
								}else{
									conditions_box += "<option value='" + lrynm + "'>" + lrynm + "</option>";
								}
							//}
						});
					}
				});
				$("#layer_name").append(conditions_box);
			}
		},
		//지도화면의 전체레이어를 가지고온다.(통합검색에쓰인다)
		GetFullLayerXML : function(){
			//xml을 가지고온다.
			var xml = "<?xml version='1.0' encoding='UTF-8'?>";
				xml += _MAINMAP.getLayerXML();
			var xmlDoc = $.parseXML(xml);
			var $xml = $(xmlDoc);
			
			if($xml.find("MapLayers").length != 0){
				var conditions_box = "";
				$xml.find("Group").each(function(){
					var group = $(this).attr("name");
					if(group != "행정구역" && group !="항공영상"){
						$(this).find("Layer").each(function(idx){
							var lrynm = $(this).text();
							if(lrynm != ""){
								conditions_box += "<option value='" + lrynm + "'>" + lrynm + "</option>";
							}
						});
						
					}
				});
				$("#f_layer_name").append(conditions_box);
			}
		},
		//현재축척의 레이어 xml을 라디오박스로 불러온다.
		GetVisibleLayerRadioXML : function(){
			
			//xml을 가지고온다.
			var xml = "<?xml version='1.0' encoding='UTF-8'?>";
				xml += _MAINMAP.getVisibleLayerXML();
			var xmlDoc = $.parseXML(xml);
			var $xml = $(xmlDoc);
			
			if($xml.find("MapLayers").length != 0){
				var str = "";
				str += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='auto'>";
				$xml.find("Group").each(function(){
					var GN = $(this).attr("name");
					//var lrynm = $(this).find("Layer").text();
					var chk = "";
					if(GN != "항공영상"){
						$(this).find("Layer").each(function(){
							var lrynm = $(this).text();
							//var g_name = $(this).find().attr("name");
							//if(lrynm != "" && lrynm != "네이버영상"){
									str += "<tr>";
									str += "<td align='center' width='10%'>";
										str += "<input type='checkbox' id='lry_name_chk'  name='lry_name_chk'  value='" + lrynm + "' onclick='_CS.init.setLyrRadioBoxArr(this.value);' />";
									str += "</td>";
									str += "<td width='90%'>" + lrynm + "</td>";
									str += "</tr>";	
							//}
						});
					}

				});
				str += "</table>";
				$("#layer_name_box").html(str);
				
				//체크한레이어 목록을 가져온다.
				if(LYR_TEMP.length != 0){
					for(var i=0; i<LYR_TEMP.length; i++){
						$("input[name=lry_name_chk]:checkbox").each(function(idx){
							var lyr_nm = $(this).val();
							if(LYR_TEMP[i] == lyr_nm){
								$(this).attr("checked","checked");
							}
						});
					}
				}
				
			}
		},
		setLyrRadioBoxArr : function(obj){
			
			LYR_TEMP = new Array();
			
			if($("input[name=lry_name_chk]:checkbox:checked").length != 0){
				$("input[name=lry_name_chk]:checkbox:checked").each(function(idx){
					LYR_TEMP[idx] = $(this).val();
				});
			}else{
				LYR_TEMP = new Array();
			}
		},
		//영상오프셋 페이지 호출
		setOffset : function(){
			$.ajax({
				url: "./offset/offset.jsp",
				contentType : "text/html; charset=utf-8",
				dataType : "html",
				success: function(htm){
					outerLayout.open("west");
					$("#con_sch_full_box").html(htm);
					reSize(); //사이즈재설정
				},
				beforeSend : function(){
				},
				complete : function(){
				},
				error: function(xhr, status, error) {
					alert(">>>>>>>>>>> : 에러");
				}
			});	
		},
		
		//정보보기의 내용 리스트를 가지고온다.
		selectedLryInfo : function(lyr, col, key){
			$.ajax({
			       type: "POST",
			       url: "../../svc",
			       data: {svc : "GetLyrSelectedList", lyr:encodeURIComponent(lyr), col:col, key:key},
			       dataType: "xml",
			       success: function(xml){
			    	   var str="";
			    	   if($(xml).find('상세정보').length != 0){
			    		   str+="<table border='0' cellpadding='0' cellspacing='0' width='100%' height='auto'>";
			    		   $(xml).find('상세정보').each(function(idx, item){
			    			var item_value = $(this).find("항목").text();
			    			var valuename = $(this).find("값").text();
			    				str+="<tr>";
			    					str+="<td align='center' width='30%' height='20' bgcolor='#b2dcfd' style='border-bottom:1px solid #c9c8c8; border-right:1px solid #c9c8c8;'>"+item_value+"</td>";
			    					str+="<td width='70%' bgcolor='#ffffff' style='border-bottom:1px solid #c9c8c8;'>&nbsp;" + valuename + "</td>";
			    				str+="</tr>";
			    		    });
			    		   
			    		   /*
			    		    *연속지적 검색시 일필지기본정보를 가지고온다. 2011-12-14 
			    		    */
			    		   if($(xml).find('존재여부').text() == "Y"){
			    			   if($(xml).find('일필지기본').length != 0){				    		   	
					    		   	var jimok = $(xml).find('지목명').text(); //지목명
					    		   	var sou = $(xml).find('소유구분명').text(); //소유구분
					    		   	var area = $(xml).find('면적').text(); //면적
					    		   	var jiga = $(xml).find('공시지가').text(); //공시지가
					    		   	var fuladdr = $(xml).find('소재지').text(); //소재지
					    		   	str+="<tr>";
				    		   			str+="<td colspan='2' align='center' width='30%' height='20' bgcolor='#b2dcfd' style='border-bottom:1px solid #c9c8c8;'><b>기본정보</b></td>";
				    		   		str+="</tr>";
				    		   		str+="<tr>";
				    		   			str+="<td align='center' width='30%' height='20' bgcolor='#b2dcfd' style='border-bottom:1px solid #c9c8c8; border-right:1px solid #c9c8c8;'>소재지</td>";
				    		   			str+="<td width='70%' bgcolor='#ffffff' style='border-bottom:1px solid #c9c8c8;'>&nbsp;" + fuladdr + "</td>";
				    		   		str+="</tr>";
					    		   	str+="<tr>";
					    		   		str+="<td align='center' width='30%' height='20' bgcolor='#b2dcfd' style='border-bottom:1px solid #c9c8c8; border-right:1px solid #c9c8c8;'>소유구분</td>";
					    		   		str+="<td width='70%' bgcolor='#ffffff' style='border-bottom:1px solid #c9c8c8;'>&nbsp;" + sou + "</td>";
					    		   	str+="</tr>";
					    			str+="<tr>";
				    		   			str+="<td align='center' width='30%' height='20' bgcolor='#b2dcfd' style='border-bottom:1px solid #c9c8c8; border-right:1px solid #c9c8c8;'>지목</td>";
				    		   			str+="<td width='70%' bgcolor='#ffffff' style='border-bottom:1px solid #c9c8c8;'>&nbsp;" + jimok + "</td>";
				    		   		str+="</tr>";
				    		   		str+="<tr>";
				    		   			str+="<td align='center' width='30%' height='20' bgcolor='#b2dcfd' style='border-bottom:1px solid #c9c8c8; border-right:1px solid #c9c8c8;'>면적</td>";
				    		   			str+="<td width='70%' bgcolor='#ffffff' style='border-bottom:1px solid #c9c8c8;'>&nbsp;" + area + "</td>";
				    		   		str+="</tr>";
				    		   		str+="<tr>";
			    		   				str+="<td align='center' width='30%' height='20' bgcolor='#b2dcfd' style='border-bottom:1px solid #c9c8c8; border-right:1px solid #c9c8c8;'>공시지가</td>";
			    		   				str+="<td width='70%' bgcolor='#ffffff' style='border-bottom:1px solid #c9c8c8;'>&nbsp;" + jiga + "</td>";
			    		   			str+="</tr>";
			    			   }
			    		   }
			    		   
			    		   
			    		   str+="</table>";
				           $("#info_result_content").html(str);
			    	   }else{
			    		   	var pstr = "";
							pstr +="<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>";
							pstr +="<p style='padding: 20px 0 0 0'>검색한결과값이 존재하지않습니다.</p>";
							pstr +="</div>";
							$("#info_result_content").html(pstr);
			    	   }
			       },
			       beforeSend : function(){
						$("#info_result_content").showLoading();
					},
					complete : function(){
						$("#info_result_content").hideLoading();
					},
					error: function(xhr, status, error) {
							alert(">>>>>>>>>>> : 에러");
					}
			   	});
		},

		// 시설물의 정보를 보여줌
		selectedFmsLryInfo : function(path, key){
			var src = path+"/view.jsp?k="+key;
			var $iframe = $('<iframe id="info_view" name="info_view" frameborder="0" scrolling="no"></iframe>');
			$iframe.attr('src', src);
			$iframe.css({'width':'100%', 'height':'100%'});
			$("#info_result_content").html($iframe);
		}
};

//대장 정보요청
_DE = {
		
	//토지정보	
	tojiInfo : function(pnu, addr){
		
		if(pnu != ""){
			
			$.ajax({
		       type: "POST",
		       url: "/svc",
		       data: {svc : "GetLandInfo", pnu : pnu},
		       dataType: "xml",
		       success: function(xml){
		    	   var d_str = "";
		    	   var type = "토지대장";
		    	   if($(xml).find('일필지기본').length != 0){
		    		   	var jimok = $(xml).find('지목명').text(); //지목명
		    		   	var sou = $(xml).find('소유구분명').text(); //소유구분
		    		   	var area = $(xml).find('면적').text(); //면적
		    		   	var sou_name = $(xml).find('소유자명').text();
		    		   	var jiga = $(xml).find('공시지가').text(); //공시지가
		    		   	var shap_num = $(xml).find('공유인수').text();
		    		   	var land_move_ymd = $(xml).find('최종토지이동일자').text();
		    		   	var land_move_why = $(xml).find('최종토지이동사유').text();
		    		   	var own_ch_ymd = $(xml).find('최종소유권변동일자').text();
		    		   	var own_ch_why = $(xml).find('최종소유권변동사유').text();
		    		   	
		    		   	if(addr == null || addr == ""){
		    		   		addr = $(xml).find('소재지').text();
		    		   	}
	    		   
	    				d_str += "<div id='detail_info_box'>";
	    				d_str += "	<button class='green' onclick='_DE.tojiInfo(\""+pnu+"\", \""+addr+"\")'>토지기본</button><button class='green' onclick='_DE.jigaInfo(\""+pnu+"\", \""+addr+"\")'>공시지가</button>";
	    				d_str += "<table id='landInfoArea' border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>";
	    				d_str += "<tr>";
	    				d_str += "	<td align='center' width='30%' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>소재지</td>";
	    				d_str += "	<td style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8;'>&nbsp;" + addr + "</td>";
	    				d_str += "</tr>";
	    				d_str += "<tr>";
	    				d_str += "	<td align='center' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>지목</td>";
	    				d_str += "	<td style='border-bottom:1px dotted #c9c8c8;'>&nbsp;" + jimok + "</td>";
	    				d_str += "</tr>";
	    				d_str += "<tr>";
	    				d_str += "	<td align='center' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>면적</td>";
	    				d_str += "	<td style='border-bottom:1px dotted #c9c8c8;'>&nbsp;" + area + "(㎡)</td>";
	    				d_str += "</tr>";
	    				d_str += "<tr>";
	    				d_str += "	<td align='center' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>소유구분</td>";
	    				d_str += "	<td style='border-bottom:1px dotted #c9c8c8;'>&nbsp;" + sou + "</td>";
	    				d_str += "</tr>";
	    				d_str += "<tr>";
	    				d_str += "	<td align='center' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>소유자</td>";
	    				d_str += "	<td style='border-bottom:1px dotted #c9c8c8;'>&nbsp;" + sou_name + "</td>";
	    				d_str += "</tr>";
	    				d_str += "<tr>";
	    				d_str += "	<td align='center' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>공유인수</td>";
	    				d_str += "	<td style='border-bottom:1px dotted #c9c8c8;'>&nbsp;" + shap_num + "</td>";
	    				d_str += "</tr>";
	    				d_str += "<tr>";
	    				d_str += "	<td align='center' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>공시지가</td>";
	    				d_str += "	<td style='border-bottom:1px dotted #c9c8c8;'>&nbsp;" + jiga + "</td>";
	    				d_str += "</tr>";
	    				d_str += "<tr>";
	    				d_str += "	<td align='center' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>토지이동일</td>";
	    				d_str += "	<td style='border-bottom:1px dotted #c9c8c8;'>&nbsp;" + land_move_ymd + "</td>";
	    				d_str += "</tr>";
	    				d_str += "<tr>";
	    				d_str += "	<td align='center' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>토지사유</td>";
	    				d_str += "	<td style='border-bottom:1px dotted #c9c8c8;'>&nbsp;" + land_move_why + "</td>";
	    				d_str += "</tr>";
	    				d_str += "<tr>";
	    				d_str += "	<td align='center' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>소유권변동일</td>";
	    				d_str += "	<td style='border-bottom:1px dotted #c9c8c8;'>&nbsp;" + own_ch_ymd + "</td>";
	    				d_str += "</tr>";
	    				d_str += "<tr>";
	    				d_str += "	<td align='center' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>소유권변동원인</td>";
	    				d_str += "	<td style='border-bottom:1px dotted #c9c8c8;'>&nbsp;" + own_ch_why + "</td>";
	    				d_str += "</tr>";
	    				d_str += "</table>";
	    				d_str += "<div align='right' style='margin-top: 5px;'>";
	    				if(!gwsUtils.checkAuth("FW004", null)){
	    					var alert = '해당 기능에 대한 권한이 없습니다.';
	    					d_str += "	<button class='green' style='cursor:pointer;' onclick='alert(\""+alert+"\");'>토지대장 상세정보</button>";
	    				}else{
	    					d_str += "	<button class='green' style='cursor:pointer;' onclick='_PC.init.setPDFLogPop(\""+type+"\",\""+pnu+"\",\""+pnu+"\", null, &#39;FW004&#39;);'>토지대장 상세정보</button>";
	    				};
	    				d_str += "</div>";
	    				d_str += "</div>";
	    				$("#result_content_box").hide();
	    				$("#detail_info").html(d_str);
	    				$("#detail_info").show("fast");
		    	   }
		       },
		       beforeSend : function(){
		    	   $("#con_sch_full_box").showLoading();
			   },
			   complete : function(){
				   $("#con_sch_full_box").hideLoading();
			   },
			   error: function(xhr, status, error){
		    	   alert(xhr + ", " + status + ", " + error);
		       }
		   	});
			//_S.utils.autoParentHeight("con_sch_full_box", "result_content_box",435);
			//_S.utils.autoParentHeight("result_content_box", "result_content", 30);
		}
	},
	jigaInfo : function(pnu, addr){
		if(pnu != ""){
			$.ajax({
		       type: "POST",
		       url: "/svc",
		       data: {svc : "GetJigaInfo", pnu : pnu},
		       dataType: "xml",
		       success: function(xml){
		    	   var d_str = "";
		    	   if($(xml).find('공시지가').length != 0){
	    				d_str += "<div id='detail_info_box'>";
	    				d_str += "	<button class='green' onclick='_DE.tojiInfo(\""+pnu+"\", \""+addr+"\")'>토지기본</button><button class='green' onclick='_DE.jigaInfo(\""+pnu+"\", \""+addr+"\")'>공시지가</button>";
	    				d_str += "<table id='jigaInfoArea' border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>";
	    				d_str += "<tr>";
	    				d_str += "	<td align='center' width='60%' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>기준일자</td>";
	    				d_str += "	<td align='center' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>개별공시지가</td>";
	    				d_str += "</tr>";
	    				$(xml).find('공시지가').find("기준년월일").each(function(i){
	    					var jiga_ymd = $(this).text(); //지목명
			    		   	var jiga = $(this).parent().find("개별공시지가").eq(i).text(); //소유구분
	    					d_str += "<tr align='center'>";
		    				d_str += "	<td style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8;'><br>&nbsp;" + jiga_ymd + "<br><br></td>";
		    				d_str += "	<td style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8;'><br>&nbsp;" + jiga + "<br><br></td>";
		    				d_str += "</tr>";
		    				i++;
	    				});
	    				d_str += "</table>";
	    				d_str += "</div>";
	    				$("#result_content_box").hide();
	    				$("#detail_info").html(d_str);
	    				$("#detail_info").show("fast");
		    	   }
		       },
		       beforeSend : function(){
		    	   $("#con_sch_full_box").showLoading();
			   },
			   complete : function(){
				   $("#con_sch_full_box").hideLoading();
			   },
			   error: function(xhr, status, error){
		    	   alert(xhr + ", " + status + ", " + error);
		       }
		   	});
			//_S.utils.autoParentHeight("con_sch_full_box", "result_content_box",435);
			//_S.utils.autoParentHeight("result_content_box", "result_content", 30);
		}
	},
	////건축물대장 약식 정보
	bldgShortInfo : function(pnu, addr){
		if(pnu != ""){
			$.ajax({
		       type: "POST",
		       url: "/svc",
		       data: {svc : "GetBldgList", pnu : pnu},
		       dataType: "xml",
		       success: function(xml){
		    	   addr = $(xml).find('소재지').text();
		    	   var d_str = "";
					   d_str += "<div id='detail_info_box'>";
					   d_str += "	<div id='HprcInfoArea'>";
					   
					   d_str += "	</div>";
					   d_str += "</div>";
		    	   if($(xml).find('건축물리스트').length != 0){
		    		    d_str += "<br><br><p style='padding:5px'><img src='img/p.gif' align='absmiddle'> 건축물대장</p>";
	    				d_str += "<table id='bldgInfoArea' border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>";
	    				d_str += "<tr id='floorArea'>";
	    				d_str += "	<td align='center' width='30%' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'><b>전유부 선택</b></td>";
	    				d_str += "	<td align='center' colspan='2' width='50%' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>";
	    				d_str += "		<select id='floorList' class='' disabled='disabled' style='width: 100px;'></select><button class='grayBTN' id='floorSend'>선택</button>";
	    				d_str += "	</td>";
	    				d_str += "</tr>";
	    				d_str += "<tr>";
	    				d_str += "	<td align='center' width='30%' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>대장목록</td>";
	    				d_str += "	<td align='center' width='50%' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>명칭 및 번호</td>";
	    				d_str += "	<td align='center' width='20%' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>주용도</td>";
	    				d_str += "</tr>";
	    				$(xml).find('건축물리스트').each(function(){
	    					var clickEvent = "";
	    					if(!gwsUtils.checkAuth("FW005", null)){
	    						if($(this).find('대장종류').text() === "총괄표제부"){
		    						//clickEvent = "_PC.init.genbldg('" + $(this).find('대장종류').attr("KEY") + "','" + pnu + "')";
		    						clickEvent = "alert('해당 기능에 대한 권한이 없습니다.');";
		    						//PDFType, key, pnu, sub_key
		    					}else if($(this).find('대장종류').text() === "표제부" || $(this).find('대장종류').text() === "일반건축물"){
		    						//clickEvent = "_PC.init.pyojebldg('" + $(this).find('대장종류').attr("KEY") + "','" + pnu + "')";
		    						clickEvent = "alert('해당 기능에 대한 권한이 없습니다.');";
		    					}else{
		    						clickEvent = "alert('해당 기능에 대한 권한이 없습니다.');";
		    					}
		    				}else{
		    					if($(this).find('대장종류').text() === "총괄표제부"){
		    						//clickEvent = "_PC.init.genbldg('" + $(this).find('대장종류').attr("KEY") + "','" + pnu + "')";
		    						clickEvent = "_PC.init.setPDFLogPop('"+$(this).find('대장종류').text()+"', '"+$(this).find('대장종류').attr("KEY")+"', '"+pnu+"', '"+ $(this).find('대장종류').attr("bno") +"', &#39;FW005&#39;);";
		    						//PDFType, key, pnu, sub_key
		    					}else if($(this).find('대장종류').text() === "표제부" || $(this).find('대장종류').text() === "일반건축물"){
		    						//clickEvent = "_PC.init.pyojebldg('" + $(this).find('대장종류').attr("KEY") + "','" + pnu + "')";
		    						clickEvent = "_PC.init.setPDFLogPop('"+$(this).find('대장종류').text()+"', '"+$(this).find('대장종류').attr("KEY")+"', '"+pnu+"', '"+ $(this).find('대장종류').attr("bno") +"', &#39;FW005&#39;);";
		    					}else{
		    						clickEvent = "";
		    					}
		    				}
	    					
	    					var J_PKNM = "";
	    					var bldg_title = '<a href="#" onclick="'+clickEvent+'">'+$(this).find('대장종류').text()+"</a>";
	    					var JBLDRGST_PK = $(this).find('전유부').attr("J_KEY");
	    					if($(this).find('전유부').text() !== ""){
	    						if($(this).find('대장종류').text() !== "일반건축물"){
	    							if(JBLDRGST_PK != ""){
	    								var ADDR = $(xml).find('소재지').text();
	    								var BLD_NM = $(this).find('명칭및번호').text();
	    								var JBLDRGST_NM = $(this).find('전유부').text(); 
	    								var BNO = $(this).find('전유부').attr("bno");
				    			   		J_Click = "_PC.init.jeonyubldg('" + ADDR + "','" + JBLDRGST_PK + "','" + BNO + "','" + pnu + "')";
				    			   		//J_Click = "_PC.init.setPDFLogPop('"+JBLDRGST_NM+"', '"+JBLDRGST_NM+"', '"+pnu+"', '"+JBLDRGST_PK+"');";
				    			   		J_PKNM = '<br><a href="#" id="J_' + JBLDRGST_PK + '" onclick="' + J_Click + '">(' + JBLDRGST_NM + ')</a>';
				    			   	}else{
				    			   		J_PKNM = "";
				    			   	}
	    						}
	    					}
			    		   	var bd_nm = $(this).find('명칭및번호').text();
			    		   	var bd_use = $(this).find('주용도').text();
	    					d_str += "<tr align='center'>";
		    				d_str += "	<td height='20' style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8;'>&nbsp;" + bldg_title + J_PKNM + "</td>";
		    				d_str += "	<td style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8;'>&nbsp;" + bd_nm + "</td>";
		    				d_str += "	<td style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8;'>&nbsp;" + bd_use + "</td>";
		    				d_str += "</tr>";
	    				});
	    				d_str += "</table>";
	    				$("#result_content_box").hide();
	    				$("#detail_info").html(d_str);
	    				_DE.hprcInfo(pnu, addr);
	    				$("#detail_info").show("fast");
	    				//_S.utils.autoParentHeight("con_sch_full_box", "result_content_box",435);
	    				//_S.utils.autoParentHeight("result_content_box", "result_content", 30);
		    	   }else{
		    		   alert("건축물대장에 대한 정보가 없습니다!");
		    	   }
		       },
		       beforeSend : function(){
		    	   $("#con_sch_full_box").showLoading();
			   },
			   complete : function(){
				   $("#con_sch_full_box").hideLoading();
			   },
			   error: function(xhr, status, error){
		    	   alert(xhr + ", " + status + ", " + error);
		       }
		   	});
		}
	},
	////개별주택가격 약식정보 (조회로그기록X)
	hprcInfo : function(pnu, addr){
		$.ajax({
			type: "POST",
	        url: "/svc",
	        data: {svc : "GetHprcInfo", pnu : pnu},
	        dataType: "xml",
	        success: function(xml){
	        	   var d_str = "";
	        	   d_str += "<p style='padding:5px'><img src='img/p.gif' align='absmiddle'> 개별주택가격</p>";
				   d_str += "<table id='bldgPriceInfoArea' border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>";
				   if($(xml).find("개별주택가격").children().length !== 0){
					   d_str += "<tr>";
					   d_str += "	<td align='center' width='30%' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>소재지</td>";
					   d_str += "	<td style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8;'>&nbsp;" + addr + "</td>";
					   d_str += "</tr>";
					   d_str += "<tr>";
					   d_str += "	<td align='center' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>동순번</td>";
					   d_str += "	<td align='center' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>개별주택가격</td>";
					   d_str += "</tr>";
					   $(xml).find("개별주택가격").find("주택가격정보").each(function(i){
						   var price = gwsUtils.setComma($(this).find("주택공시가격").text());
						   d_str += "<tr align='center' onclick='_PC.init.ahpdHprc(\""+pnu+"\", \""+addr+"\", \""+$(this).find("동일련번호").text()+"\");' style='cursor:pointer;'>";
						   d_str += "	<td height='20' style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8;'>&nbsp;"+$(this).find("동일련번호").text()+"</td>";
						   d_str += "	<td style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8;'>&nbsp;"+price+"</td>";
						   d_str += "</tr>";
					   });
				   }else{
					   d_str += "<tr>";
					   d_str += "	<td align='center' width='30%' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>소재지</td>";
					   d_str += "	<td style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8;'>&nbsp;" + addr + "</td>";
					   d_str += "</tr>";
					   d_str += "<tr>";
					   d_str += "	<td align='center' colspan='2' height='25' style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>정보가 없습니다.</td>";
					   d_str += "</tr>";
				   }
				   d_str += "</table>";
				   $("#HprcInfoArea").html(d_str);
	        },
	        beforeSend : function(){
	    	   $("#con_sch_full_box").showLoading();
		    },
		    complete : function(){
			   $("#con_sch_full_box").hideLoading();
		    },
	        error: function(xhr, status, error){
		    	   alert(xhr + ", " + status + ", " + error);
		    }
		});
	},
	////Luris 약식정보
	lurisInfo : function(pnu, addr){
		$.ajax({
	       type: "POST",
	       url: "/svc",
	       data: {svc : "GetUseplanInfo", pnu : pnu, usrid : SESSION_ID},
	       dataType: "xml",
	       success: function(xml){
	    	   var d_str = "";
	    	   if($(xml).find('용도지역목록').length != 0){
    				d_str += "<div id='detail_info_box'>";
    				d_str += "<table id='lurisInfoArea' border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>";
    				d_str += "<tr>";
				    d_str += "	<td width='100%' height='25' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>소재지</td>";
				    d_str += "</tr>";
				    d_str += "<tr>";
				    d_str += "	<td style='border-bottom:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8;'><br>&nbsp;" + addr + "<br><br></td>";
				    d_str += "</tr>";
    				d_str += "<tr>";
    				d_str += "	<td width='100%' height='25' bgcolor='#b2dcfd' style='border:1px dotted #c9c8c8;'>「 국토의 계획 및 이용에 관한 법률」에 따른 지역지구등</td>";
    				d_str += "</tr>";
    				$(xml).find('용도지역목록').find("국토계획이용").each(function(){
    					var laws_1 = $(this).find("용도지역지구명").text();
    					
    					d_str += "<tr>";
    					d_str += "	<td style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8;'><br>&nbsp;" + laws_1 + "<br><br></td>";
    					d_str += "</tr>";
    				});
					d_str += "<tr>";
    				d_str += "	<td width='100%' height='25' bgcolor='#b2dcfd' style='border:1px dotted #c9c8c8;'>다른법령등에 따른 지역지구등</td>";
    				d_str += "</tr>";
    				$(xml).find('용도지역목록').find("다른법령").each(function(){
    					var laws_2 = $(this).find("용도지역지구명").text();
						d_str += "<tr>";
						d_str += "	<td style='border-bottom:1px dotted #c9c8c8;border-right:1px dotted #c9c8c8;border-top:1px dotted #c9c8c8;'><br>&nbsp;" + laws_2 + "<br><br></td>";
						d_str += "</tr>";
					});
    				d_str += "</table><br>";
    				d_str += "<div align='right'>";
    				d_str += "	<button class='green' style='width:155px;' onclick='_PC.init.tojiUseplan(\""+pnu+"\");'>행위제한법령정보</button><br>";
    				d_str += "	<button class='green' style='width:155px;' onclick='_PC.init.rdAct(\""+pnu+"\");'>지역지구별행위제한</button><br>";
    				d_str += "	<button class='green' style='width:155px;' onclick='_PC.init.tojiAct(\""+pnu+"\");'>조건별행위제한</button><br>";
    				d_str += "</div>";
    				d_str += "</div>";
    				$("#result_content_box").hide();
    				$("#detail_info").html(d_str);
    				$("#detail_info").show("fast");
    				//_S.utils.autoParentHeight("con_sch_full_box", "result_content_box",435);
    				//_S.utils.autoParentHeight("result_content_box", "result_content", 30);
	    	   }
	       },
	       beforeSend : function(){
	    	   $("#con_sch_full_box").showLoading();
		   },
		   complete : function(){
			   $("#con_sch_full_box").hideLoading();
		   },
		   error: function(xhr, status, error){
	    	   alert(xhr + ", " + status + ", " + error);
	       }
	   	});
	},
	doroInfo : function(pnu, obj){

		if(pnu != ""){
		$.ajax({
		       type: "POST",
		       url: "../../svc",
		       data: {svc : "GetRoadAddrInfo", pnu : pnu},
		       dataType: "xml",
		       success: function(xml){
		   			
		    	   var d_str = "";
		   			
		    	   if($(xml).find('도로정보').length != 0){
		    		   	
			    		   	var rn = $(xml).find('도로명').text();
			    		   	var engrn = $(xml).find('영문명').text();
			    		   	var rbpcn = $(xml).find('기점').text();
			    		   	var repcn = $(xml).find('종점').text();
			    		   	var roadbt = $(xml).find('도로폭').text(); 
			    		   	var roadlt = $(xml).find('도록길이').text();
			    		   	var alwnc = $(xml).find('부여사유').text(); 
			    		   	
			    		   	d_str += "<div id='' style='height:21px; border:0px solid #000000;'>";
			    			d_str += "<div style='float: left;'><img src='img/road_info.gif'/></div>";
			    			d_str += "</div>";
			    			d_str += "<div id='detail_info_box' style='border:1px solid #c9c8c8; background-color: #f7f6f6; height:138px;'>";
			    			d_str += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>";
			    			d_str += "<tr>";
			    			d_str += "<td align='center' width='20%' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>도로명</td>";
			    			d_str += "	<td width='80' style='border-bottom:1px dotted #c9c8c8;' colspan='3'>&nbsp;" + rn + "</td>";
			    			d_str += "</tr>";
			    			d_str += "<tr>";
			    			d_str += "	<td align='center' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>영문명</td>";
			    			d_str += "	<td style='border-bottom:1px dotted #c9c8c8;'  colspan='3'>&nbsp;" + engrn + "</td>";
			    			d_str += "</tr>";
			    			d_str += "<tr>";
			    			d_str += "	<td align='center' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>기점</td>";
			    			d_str += "	<td style='border-bottom:1px dotted #c9c8c8;'  colspan='3'>&nbsp;" + rbpcn + "</td>";
			    			d_str += "</tr>";
			    			d_str += "<tr>";
			    			d_str += "	<td align='center' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>종점</td>";
			    			d_str += "	<td style='border-bottom:1px dotted #c9c8c8;'  colspan='3'>&nbsp;" + repcn + "</td>";
			    			d_str += "</tr>";
			    			d_str += "<tr>";
			    			d_str += "	<td width='20%' align='center' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>폭</td>";
			    			d_str += "	<td width='30%' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>&nbsp;" + roadbt + "</td>";
			    			d_str += "	<td width='20%' align='center' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>길이</td>";
			    			d_str += "	<td width='30%' style='border-bottom:1px dotted #c9c8c8;'>&nbsp;" + roadlt + "</td>";
			    			d_str += "</tr>";
			    			d_str += "<tr>";
			    			d_str += "	<td align='center' bgcolor='#b2dcfd' style='border-right:1px dotted #c9c8c8;'>부여사유</td>";
			    			d_str += "	<td colspan='3'>&nbsp;" + alwnc + "</td>";
			    			d_str += "</tr>";
			    			d_str += "</table>";
			    			d_str += "</div>";
			    			$("#detail_info").html(d_str);
		    	   }
		       },
		       beforeSend : function(){},
		       complete : function(){},
			   error: function(xhr, status, error){
		    	   alert(xhr + ", " + status + ", " + error);
		       }
		   	});

		if(obj != "doro"){
			_S.utils.autoParentHeight("con_sch_full_box", "result_content_box",400);
			_S.utils.autoParentHeight("result_content_box", "result_content", 30);
		}else{
			_S.utils.autoParentHeight("con_sch_full_box", "doro_result_content_box", 362);
			_S.utils.autoParentHeight("doro_result_content_box", "doro_result_content", 30);
		}
		$("#detail_info").show("fast");
		
		}

	},
	bldgInfo : function(pnu, addr){
		if(pnu != ""){
			$.ajax({
			       type: "POST",
			       url: "/svc",
			       data: {svc : "GetBldgList", pnu : pnu},
			       dataType: "xml",
			       success: function(xml){
			    	   _PC.init.frameOpen();
			    	   var srchFrame = $('body', parent.srch.document);
			    	   $(srchFrame).html("");
			    	   
			    	   var str = "";
			    	   
			    	   if($(xml).find('건축물리스트').length != 0){
			    		   var ADDR = $(xml).find('소재지').text();
			    		   var LAND_CD = $(xml).find('고유번호').text();
			    		   
			    		   str += "<div style='border-bottom:0px solid #e3e3e3; margin-top:4px;'>";
			    		   str += " <input type='hidden' id='pnu' value='" + pnu + "'/>";
			    		   str += "	<table border='0' cellpadding='0' cellspacing='0' width='100%' class='border1'>";
			    		   str += "		<tr>";
			    		   str += "			<td height='24' width='20%' class='border4' bgcolor='#eeeded' align='center'>토지소재지</td>";
			    		   str += "			<td width='80%'>　　" + ADDR + "</td>";
			    		   str += "			<td width='10%'><button onclick='_PC.init.frameClose();' style='width: 48px; cursor: pointer;'>닫기</button></td>";
			    		   str += "		</tr>";
			    		   str += "	</table>";
			    		   str += "</div>";
			    		   
			    		   str += "<div style='height:17px; margin:5px 0 5px 3px;'><img src='img/pp_img_3.gif' align='absmiddle'/> <b>건축물대장</b> (고유번호 : "+LAND_CD+")</div>";
			    		   str += "<table id='bldgTable' border='0' cellpadding='0' cellspacing='0' width='100%' class='border1'>";
			    		   str += "	<tr bgcolor='#eeeded' align='center'>";
			    		   str += "		<td height='24' width='20%' class='border4'><b>대장종류</b></td>";
			    		   str += "		<td height='24' width='30%' class='border4'><b>명칭및번호</b></td>";
			    		   str += "		<td height='24' width='30%' class='border4'><b>주용도</b></td>";
			    		   str += "		<td width='20%' bgcolor='#eeeded'><b>연면적</b></td>";
			    		   str += "	</tr>";
			    		   
			    		   $(xml).find("건축물리스트").each(function(){
			    			   	var BLDRGST_PK = $(this).find('대장종류').attr("KEY");
			    			   	var BLDRGST_NM = $(this).find('대장종류').text();
			    			   	var JBLDRGST_PK = $(this).find('전유부').attr("J_KEY");
			    			   	var JBLDRGST_NM = $(this).find('전유부').text(); 
			    			   	var BLD_NM = $(this).find('명칭및번호').text();
			    			   	var USABILITY = $(this).find('주용도').text();
			    			   	var TOTALAREA = $(this).find('연면적').text();
			    			   	
			    			   	var BldgClick = "";
			    			   	
			    			   	if(BLDRGST_NM == "총괄표제부"){
			    			   		BldgClick = "_PC.init.genbldg('" + BLDRGST_PK + "','" + pnu + "')";
			    			   	}else if(BLDRGST_NM == "표제부" || BLDRGST_NM == "일반건축물"){
			    			   		BldgClick = "_PC.init.pyojebldg('" + BLDRGST_PK + "','" + pnu + "')";
			    			   	}else{
			    			   		BldgClick = "";
			    			   	}
			    			   	
			    			   	var J_PKNM = "";
			    			   	var J_Click = "";
			    			   	
			    			   	if(JBLDRGST_PK != ""){
			    			   		J_Click = "_PC.init.jeonyubldg('" + ADDR + "','" + JBLDRGST_PK + "','" + BLD_NM + "','" + pnu + "')";
			    			   		J_PKNM = '<a href="#" id="J_' + JBLDRGST_PK + '" onclick="' + J_Click + '">(' + JBLDRGST_NM + ')</a>';
			    			   	}else{
			    			   		J_PKNM = "";
			    			   	}
			    			   	
				    			str += "	<tr align='center'>";
				    			str += "		<td height='24' width='20%' class='border4'><a href='#' id=" + BLDRGST_PK + " onclick=" + BldgClick + ">" + BLDRGST_NM + "</a>" + J_PKNM + "</td>";
					    		str += "		<td height='24' width='30%' class='border4'><p>" + BLD_NM + "</p></td>";
					    		str += "		<td height='24' width='30%' class='border4'><p>" + USABILITY + "</p></td>";
					    		str += "		<td width='20%' ><p>" + TOTALAREA + " ㎡</p></td>";
					    		str += "	</tr>";
				    		});
				    		
				    		str += "</table>";
			    	   }else{
			    		   str = "<div style='height:17px; margin-top:5px;'>" +
			    		   		"	<img src='img/pp_img_3.gif' align='absmiddle'/> <b>건축물대장의 검색결과가 없습니다.</b> " +
			    		   		"	<button onclick='_PC.init.frameClose();' style='position: absolute; right: 0px; width: 48px; cursor: pointer;'>닫기</button> " +
			    		   		"</div>";
			    	   }
			    	   $(srchFrame).html(str);
			       },
			       beforeSend : function(){},
			       complete : function(){},
				   error: function(xhr, status, error){
			    	   alert(xhr + ", " + status + ", " + error);
			       }
			   	});
			}
	},
	de_bldgInfo : function(){
		
		var d_str = "";
		d_str += "<div id='' style='height:21px; border:0px solid #000000;'>";
		d_str += "<div style='float: left;'><img src='img/bldg_info.gif'/></div>";
		d_str += "</div>";
		d_str += "<div id='detail_info_box' style='border:1px solid #c9c8c8; background-color: #f7f6f6; height:138px;'>";
		d_str += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>";
		d_str += "<tr>";
		d_str += "<td align='center' width='20%'  bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>도로명주소</td>";
		d_str += "<td style='border-bottom:1px dotted #c9c8c8;' colspan='3'>&nbsp;춘천시 석사동 917-1</td>";
		d_str += "</tr>";
		d_str += "<tr>";
		d_str += "<td align='center' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>지번주소</td>";
		d_str += "<td style='border-bottom:1px dotted #c9c8c8;' colspan='3'>&nbsp;군유지</td>";
		d_str += "</tr>";
		d_str += "<tr>";
		d_str += "<td width='20%' align='center' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>주용도</td>";
		d_str += "<td width='30%' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>&nbsp;</td>";
		d_str += "<td width='20%' align='center' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>건물수</td>";
		d_str += "<td width='30%' style='border-bottom:1px dotted #c9c8c8;'>&nbsp;</td>";
		d_str += "</tr>";
		d_str += "<tr>";
		d_str += "<td align='center' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>대지면적</td>";
		d_str += "<td style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>&nbsp;</td>";
		d_str += "<td align='center' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>건축면적</td>";
		d_str += "<td style='border-bottom:1px dotted #c9c8c8;'>&nbsp;</td>";
		d_str += "</tr>";
		d_str += "<tr>";
		d_str += "<td align='center' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>연면적</td>";
		d_str += "<td style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>&nbsp;</td>";
		d_str += "<td width='20%' align='center' bgcolor='#b2dcfd' style='border-bottom:1px dotted #c9c8c8; border-right:1px dotted #c9c8c8;'>연면적1</td>";
		d_str += "<td style='border-bottom:1px dotted #c9c8c8;'>&nbsp;</td>";
		d_str += "</tr>";
		d_str += "<tr>";
		d_str += "<td align='center' bgcolor='#b2dcfd' style='border-right:1px dotted #c9c8c8;'>건폐율</td>";
		d_str += "<td style='border-right:1px dotted #c9c8c8;'>&nbsp;</td>";
		d_str += "<td width='20%' align='center' bgcolor='#b2dcfd' style='border-right:1px dotted #c9c8c8;'>용적율</td>";
		d_str += "<td >&nbsp;</td>";
		d_str += "</tr>";
		d_str += "</table>";
		d_str += "</div>";
		
		$("#detail_info").html(d_str);
		_S.utils.autoParentHeight("con_sch_full_box", "desi_result_content_box", 352);
		_S.utils.autoParentHeight("desi_result_content_box", "desi_result_content", 30);
		$("#detail_info").show("fast");
	},
	inteInfo : function(key, lry_id){
		
		$.ajax({
		       type: "POST",
		       url: "/svc",
		       data: {svc : "GetLyrDetailList", key:key, lry_id:lry_id},
		       dataType: "xml",
		       success: function(xml){
		    	   var str="";
		    	   if($(xml).find('상세정보').length != 0){
		    		   str+="<table border='0' cellpadding='0' cellspacing='0' width='100%' height='auto'>";
		    		   $(xml).find('상세정보').each(function(idx, item){
		    			var item_value = $(this).find("항목").text();
		    			var valuename = $(this).find("값").text();
		    				str+="<tr>";
		    					str+="<td align='center' width='30%' height='20' bgcolor='#b2dcfd' style='border-bottom:1px solid #c9c8c8; border-right:1px solid #c9c8c8;'>"+item_value+"</td>";
		    					str+="<td width='70%' bgcolor='#ffffff' style='border-bottom:1px solid #c9c8c8;'>&nbsp;" + valuename + "</td>";
		    				str+="</tr>";
		    		   });
		    		   /*
		    		    *연속지적 검색시 일필지기본정보를 가지고온다. 2011-12-14 
		    		    */
		    		   if($(xml).find('존재여부').text() == "Y"){
		    			   if($(xml).find('일필지기본').length != 0){				    		   	
				    		   	var jimok = $(xml).find('지목명').text(); //지목명
				    		   	var sou = $(xml).find('소유구분명').text(); //소유구분
				    		   	var area = $(xml).find('면적').text(); //면적
				    		   	var jiga = $(xml).find('공시지가').text(); //공시지가
				    		   	var fuladdr = $(xml).find('소재지').text(); //소재지
				    		   	str+="<tr>";
			    		   			str+="<td colspan='2' align='center' width='30%' height='20' bgcolor='#b2dcfd' style='border-bottom:1px solid #c9c8c8;'><b>기본정보</b></td>";
			    		   		str+="</tr>";
			    		   		str+="<tr>";
			    		   			str+="<td align='center' width='30%' height='20' bgcolor='#b2dcfd' style='border-bottom:1px solid #c9c8c8; border-right:1px solid #c9c8c8;'>소재지</td>";
			    		   			str+="<td width='70%' bgcolor='#ffffff' style='border-bottom:1px solid #c9c8c8;'>&nbsp;" + fuladdr + "</td>";
			    		   		str+="</tr>";
				    		   	str+="<tr>";
				    		   		str+="<td align='center' width='30%' height='20' bgcolor='#b2dcfd' style='border-bottom:1px solid #c9c8c8; border-right:1px solid #c9c8c8;'>소유구분</td>";
				    		   		str+="<td width='70%' bgcolor='#ffffff' style='border-bottom:1px solid #c9c8c8;'>&nbsp;" + sou + "</td>";
				    		   	str+="</tr>";
				    			str+="<tr>";
			    		   			str+="<td align='center' width='30%' height='20' bgcolor='#b2dcfd' style='border-bottom:1px solid #c9c8c8; border-right:1px solid #c9c8c8;'>지목</td>";
			    		   			str+="<td width='70%' bgcolor='#ffffff' style='border-bottom:1px solid #c9c8c8;'>&nbsp;" + jimok + "</td>";
			    		   		str+="</tr>";
			    		   		str+="<tr>";
			    		   			str+="<td align='center' width='30%' height='20' bgcolor='#b2dcfd' style='border-bottom:1px solid #c9c8c8; border-right:1px solid #c9c8c8;'>면적</td>";
			    		   			str+="<td width='70%' bgcolor='#ffffff' style='border-bottom:1px solid #c9c8c8;'>&nbsp;" + area + "</td>";
			    		   		str+="</tr>";
			    		   		str+="<tr>";
		    		   				str+="<td align='center' width='30%' height='20' bgcolor='#b2dcfd' style='border-bottom:1px solid #c9c8c8; border-right:1px solid #c9c8c8;'>공시지가</td>";
		    		   				str+="<td width='70%' bgcolor='#ffffff' style='border-bottom:1px solid #c9c8c8;'>&nbsp;" + jiga + "</td>";
		    		   			str+="</tr>";
		    			   }
		    		   }

		    		   str+="</table>";
			           $("#detail_info_box").html(str);
		    	   }else{
		    		   	var pstr = "";
						pstr +="<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>";
						pstr +="<p style='padding: 20px 0 0 0'>검색한결과값이 존재하지않습니다.</p>";
						pstr +="</div>";
						$("#detail_info_box").html(pstr);
		    	   } 
		       },
		       beforeSend : function(){
					//$("#detail_info_box").showLoading();
				},
				complete : function(){
					//$("#detail_info_box").hideLoading();
				},
				error: function(xhr, status, error) {
						alert(">>>>>>>>>>> : 에러");
				}
		   	});
		
		_S.utils.autoParentHeight("con_sch_full_box", "inte_result_content_box", 464);
		_S.utils.autoParentHeight("inte_result_content_box", "inte_result_content", 58);
		$("#detail_info").show("fast");
		
	}
};

_PA.init = {
		
		//화면의 페이지처리
		bottompageing : function(){
			var pstr="";
			_PA.info.pageCount = Math.floor((_PA.info.count / _PA.info.pageSize) + (_PA.info.count % _PA.info.pageSize == 0 ? 0 : 1));
			_PA.info.startPage = Math.floor(_PA.info.currentPage - ((_PA.info.currentPage-1)%_PA.info.pageBlock));
			_PA.info.endPage = Math.floor(_PA.info.startPage + _PA.info.pageBlock - 1); //끝 페이지 구하기
			if(_PA.info.endPage > _PA.info.pageCount)_PA.info.endPage = _PA.info.pageCount;
			
					if (_PA.info.startPage > _PA.info.pageBlock) {
						pstr += " <img src='./img/prev.gif' border='0' align='absmiddle'  style='cursor: pointer;' onclick='_CS.init.desiPaging("+(_PA.info.startPage - _PA.info.pageBlock)+");'/> "; 
					}else{
					}

					for (var i = _PA.info.startPage; i <= _PA.info.endPage; i++) {
						if(i == _PA.info.currentPage){
							pstr += " <a href='javascript:_CS.init.desiPaging("+i+")'><b>" + i + "</b></a> ";
						}else{
							pstr += " <a href='javascript:_CS.init.desiPaging("+i+")'>" + i + "</a> ";
						}
						if(i != _PA.info.endPage){
							pstr += " | ";
						}
					}
					if (_PA.info.endPage < _PA.info.pageCount) {
						pstr += " <img src='./img/next.gif' border='0' align='absmiddle' style='cursor: pointer;' onclick='_CS.init.desiPaging("+(_PA.info.startPage + _PA.info.pageBlock)+");'/> ";
					}else{
					}
				
				return pstr;
		},
		//다음 주소검색시 페이지 2012-04-23
		Daumbottompageing : function(){
			var pstr="";
			_PA.info.pageCount = Math.floor((_PA.info.count / _PA.info.pageSize) + (_PA.info.count % _PA.info.pageSize == 0 ? 0 : 1));
			_PA.info.startPage = Math.floor(_PA.info.currentPage - ((_PA.info.currentPage-1)%_PA.info.pageBlock));
			_PA.info.endPage = Math.floor(_PA.info.startPage + _PA.info.pageBlock - 1); //끝 페이지 구하기
			if(_PA.info.endPage > _PA.info.pageCount)_PA.info.endPage = _PA.info.pageCount;
			
					if (_PA.info.startPage > _PA.info.pageBlock) {
						pstr += " <img src='./img/prev.gif' border='0' align='absmiddle'  style='cursor: pointer;' onclick='_CS.init.DaumAddrPaging("+(_PA.info.startPage - _PA.info.pageBlock)+");'/> "; 
					}else{
					}

					for (var i = _PA.info.startPage; i <= _PA.info.endPage; i++) {
						if(i == _PA.info.currentPage){
							pstr += " <a href='javascript:_CS.init.DaumAddrPaging("+i+")'><b>" + i + "</b></a> ";
						}else{
							pstr += " <a href='javascript:_CS.init.DaumAddrPaging("+i+")'>" + i + "</a> ";
						}
						if(i != _PA.info.endPage){
							pstr += " | ";
						}
					}
					if (_PA.info.endPage < _PA.info.pageCount) {
						pstr += " <img src='./img/next.gif' border='0' align='absmiddle' style='cursor: pointer;' onclick='_CS.init.DaumAddrPaging("+(_PA.info.startPage + _PA.info.pageBlock)+");'/> ";
					}else{
					}
				
				return pstr;
		},
		//지번화면의 페이지처리
		jibunbotpageing : function(){
			var pstr="";
			_PA.info.pageCount = Math.floor((_PA.info.count / _PA.info.pageSize) + (_PA.info.count % _PA.info.pageSize == 0 ? 0 : 1));
			_PA.info.startPage = Math.floor(_PA.info.currentPage - ((_PA.info.currentPage-1)%_PA.info.pageBlock));
			_PA.info.endPage = Math.floor(_PA.info.startPage + _PA.info.pageBlock - 1); //끝 페이지 구하기
			if(_PA.info.endPage > _PA.info.pageCount)_PA.info.endPage = _PA.info.pageCount;
			
					if (_PA.info.startPage > _PA.info.pageBlock) {
						pstr += " <img src='./img/prev.gif' border='0' align='absmiddle'  style='cursor: pointer;' onclick='_CS.init.Jibunpaging("+(_PA.info.startPage - _PA.info.pageBlock)+");'/> "; 
					}else{
					}

					for (i = _PA.info.startPage; i <= _PA.info.endPage; i++) {
						if(i == _PA.info.currentPage){
							pstr += " <a href='javascript:_CS.init.Jibunpaging("+i+")'><b>" + i + "</b></a> ";
						}else{
							pstr += " <a href='javascript:_CS.init.Jibunpaging("+i+")'>" + i + "</a> ";
						}
						if(i != _PA.info.endPage){
							pstr += " | ";
						}
					}
					if (_PA.info.endPage < _PA.info.pageCount) {
						pstr += " <img src='./img/next.gif' border='0' align='absmiddle' style='cursor: pointer;' onclick='_CS.init.Jibunpaging("+(_PA.info.startPage + _PA.info.pageBlock)+");'/> ";
					}else{
					}
				
				return pstr;
		},
		//새주소 지번화면의 페이지처리
		jibunNewbotpageing : function(){
			var pstr="";
			_PA.info.pageCount = Math.floor((_PA.info.count / _PA.info.pageSize) + (_PA.info.count % _PA.info.pageSize == 0 ? 0 : 1));
			_PA.info.startPage = Math.floor(_PA.info.currentPage - ((_PA.info.currentPage-1)%_PA.info.pageBlock));
			_PA.info.endPage = Math.floor(_PA.info.startPage + _PA.info.pageBlock - 1); //끝 페이지 구하기
			if(_PA.info.endPage > _PA.info.pageCount)_PA.info.endPage = _PA.info.pageCount;
			
					if (_PA.info.startPage > _PA.info.pageBlock) {
						pstr += " <img src='./img/prev.gif' border='0' align='absmiddle'  style='cursor: pointer;' onclick='_CS.init.JibunNewpaging("+(_PA.info.startPage - _PA.info.pageBlock)+");'/> "; 
					}else{
					}

					for (i = _PA.info.startPage; i <= _PA.info.endPage; i++) {
						if(i == _PA.info.currentPage){
							pstr += " <a href='javascript:_CS.init.JibunNewpaging("+i+")'><b>" + i + "</b></a> ";
						}else{
							pstr += " <a href='javascript:_CS.init.JibunNewpaging("+i+")'>" + i + "</a> ";
						}
						if(i != _PA.info.endPage){
							pstr += " | ";
						}
					}
					if (_PA.info.endPage < _PA.info.pageCount) {
						pstr += " <img src='./img/next.gif' border='0' align='absmiddle' style='cursor: pointer;' onclick='_CS.init.JibunNewpaging("+(_PA.info.startPage + _PA.info.pageBlock)+");'/> ";
					}else{
					}
				
				return pstr;
		},
		//도로의 페이지처리
		dorobotpageing : function(){
			var pstr="";
			_PA.info.pageCount = Math.floor((_PA.info.count / _PA.info.pageSize) + (_PA.info.count % _PA.info.pageSize == 0 ? 0 : 1));
			_PA.info.startPage = Math.floor(_PA.info.currentPage - ((_PA.info.currentPage-1)%_PA.info.pageBlock));
			_PA.info.endPage = Math.floor(_PA.info.startPage + _PA.info.pageBlock - 1); //끝 페이지 구하기
			if(_PA.info.endPage > _PA.info.pageCount)_PA.info.endPage = _PA.info.pageCount;
					if (_PA.info.startPage > _PA.info.pageBlock) {
						pstr += " <img src='./img/prev.gif' border='0' align='absmiddle'  style='cursor: pointer;' onclick='_CS.init.Doropaging("+(_PA.info.startPage - _PA.info.pageBlock)+");'/> "; 
					}else{
					}

					for (i = _PA.info.startPage; i <= _PA.info.endPage; i++) {
						if(i == _PA.info.currentPage){
							pstr += " <a href='javascript:_CS.init.Doropaging("+i+")'><b>" + i + "</b></a> ";
						}else{
							pstr += " <a href='javascript:_CS.init.Doropaging("+i+")'>" + i + "</a> ";
						}
						if(i != _PA.info.endPage){
							pstr += " | ";
						}
					}
					if (_PA.info.endPage < _PA.info.pageCount) {
						pstr += " <img src='./img/next.gif' border='0' align='absmiddle' style='cursor: pointer;' onclick='_CS.init.Doropaging("+(_PA.info.startPage + _PA.info.pageBlock)+");'/> ";
					}else{
					}
				return pstr;
		},
		//통합검색 페이지처리
		intebotpageing : function(){
			var pstr="";
			_PA.info.pageCount = Math.floor((_PA.info.count / _PA.info.pageSize) + (_PA.info.count % _PA.info.pageSize == 0 ? 0 : 1));
			_PA.info.startPage = Math.floor(_PA.info.currentPage - ((_PA.info.currentPage-1)%_PA.info.pageBlock));
			_PA.info.endPage = Math.floor(_PA.info.startPage + _PA.info.pageBlock - 1); //끝 페이지 구하기
			if(_PA.info.endPage > _PA.info.pageCount)_PA.info.endPage = _PA.info.pageCount;
			
					if (_PA.info.startPage > _PA.info.pageBlock) {
						pstr += " <img src='./img/prev.gif' border='0' align='absmiddle'  style='cursor: pointer;' onclick='_CS.init.Intepaging("+(_PA.info.startPage - _PA.info.pageBlock)+");'/> "; 
					}else{
					}

					for (i = _PA.info.startPage; i <= _PA.info.endPage; i++) {
						if(i == _PA.info.currentPage){
							pstr += " <a href='javascript:_CS.init.Intepaging("+i+")'><b>" + i + "</b></a> ";
						}else{
							pstr += " <a href='javascript:_CS.init.Intepaging("+i+")'>" + i + "</a> ";
						}
						if(i != _PA.info.endPage){
							pstr += " | ";
						}
					}
					if (_PA.info.endPage < _PA.info.pageCount) {
						pstr += " <img src='./img/next.gif' border='0' align='absmiddle' style='cursor: pointer;' onclick='_CS.init.Intepaging("+(_PA.info.startPage + _PA.info.pageBlock)+");'/> ";
					}else{
					}
				
				return pstr;
		},
		//통합검색 페이지처리 2011-12-16
		intebotJijukpageing : function(){
			var pstr="";
			_PA.info.pageCount = Math.floor((_PA.info.count / _PA.info.pageSize) + (_PA.info.count % _PA.info.pageSize == 0 ? 0 : 1));
			_PA.info.startPage = Math.floor(_PA.info.currentPage - ((_PA.info.currentPage-1)%_PA.info.pageBlock));
			_PA.info.endPage = Math.floor(_PA.info.startPage + _PA.info.pageBlock - 1); //끝 페이지 구하기
			if(_PA.info.endPage > _PA.info.pageCount)_PA.info.endPage = _PA.info.pageCount;
			
					if (_PA.info.startPage > _PA.info.pageBlock) {
						pstr += " <img src='./img/prev.gif' border='0' align='absmiddle'  style='cursor: pointer;' onclick='_CS.init.InteJijukpaging("+(_PA.info.startPage - _PA.info.pageBlock)+");'/> "; 
					}else{
					}

					for (i = _PA.info.startPage; i <= _PA.info.endPage; i++) {
						if(i == _PA.info.currentPage){
							pstr += " <a href='javascript:_CS.init.InteJijukpaging("+i+")'><b>" + i + "</b></a> ";
						}else{
							pstr += " <a href='javascript:_CS.init.InteJijukpaging("+i+")'>" + i + "</a> ";
						}
						if(i != _PA.info.endPage){
							pstr += " | ";
						}
					}
					if (_PA.info.endPage < _PA.info.pageCount) {
						pstr += " <img src='./img/next.gif' border='0' align='absmiddle' style='cursor: pointer;' onclick='_CS.init.InteJijukpaging("+(_PA.info.startPage + _PA.info.pageBlock)+");'/> ";
					}else{
					}
				return pstr;
		}

}



