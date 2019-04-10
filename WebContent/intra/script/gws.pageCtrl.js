if(!window._PC) _PC = {};
var popup;

_PC.init = {
		
		//하단 프레임 오픈
		frameOpen : function(){
			//parent.document.getElementsByTagName( 'frameset' )[0].rows="*,0";
			/*if(arguments.length == 2){
				var lyr_id = arguments[0];
				var gids = eval('['+arguments[1]+']');

				var iCdt = [{f:"_gid",o:"in",w:gids}];
				
				$.ajax({
					type: 'POST',
			    	url: './get_p_k.jsp',
			    	data: {lid: lyr_id},
			    	dataType: 'json',
			    	success: function(tinfo){
			    		if ( tinfo.stat == 1 ) {
			    			parent.lv.act = (tinfo.path+"/list.jsp");
			    			parent.lv.ic = Base64.encode(JSON.stringify(iCdt));
			    			parent.frames[1].location.href = './go_list.jsp';
			    		} else {
			            	alert('Layer Name Error!');
			    		}
					},
			    	error: function(){
			    		alert('Layer Name Error!');
			    	} 
				});
			}*/
			$('frameset', parent.document).attr('rows', '*,300');
		},

		//하단 프레임 클로즈
		frameClose : function(){
			$('frameset', parent.document).attr('rows', '*,0');
		},
		
		PNUCREATE : function(){
			
			var dcode = "";
			var pnu;
			var sgg = $("#p_sgg").val();
			var umd = $("#p_umd").val();
			var ri = $("#p_ri").val();
			var bon = $("#p_bon").val();
			var bu = $("#p_bu").val();
			var san = $("input:checkbox[name=p_san]:checked").val();
			
			var len1,len2;
			
			if(umd == ""){
				dcode = sgg;
			}else{
				dcode = umd;
			}
			
			if(ri == ""){ 
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
						
			KEY = dcode+""+san+""+bon+""+bu;
			this.tojiBasic();
			//위치이동
			_MAINMAP.searchAndMoveNoScale("연속지적", KEY);
			
		},
		GetSELSB : function(pnu){
				
				var selsgg = pnu.substring(0, 5) + "00000";
				var selumd = pnu.substring(0, 8) + "00";
				var selri = pnu.substring(0, 10);
				var san = pnu.substring(10, 11);
				var bon = Number(pnu.substring(11, 15));
				var bu = Number(pnu.substring(15, 19));
				
				this.GetSGG(selsgg);
				this.GetUMD(selumd);
				this.GetRI(selri);
				$("#p_bon", parent.srch.document).val(bon);
				$("#p_bu", parent.srch.document).val(bu);
				
				if(san != 1){
					$("#p_san", parent.srch.document).attr("checked", "checked");
				}else{
					$("#p_san", parent.srch.document).removeAttr("checked");
				}
		},
		//시군구목록을가져온다
		GetSGG : function(selsgg){
			$.ajax({
				type: 'POST',
		    	url: '../svc',
		    	data : {svc : "GetSGG"},
		    	dataType: 'xml',
		    	success: function(xml){
					var conditions_box = "";
					$(xml).find("시군구").each(function(){
						var code = $(this).find("시군구코드").text();
						var name = $(this).find("시군구명").text();
						if(code == selsgg){
							conditions_box += "<option value='" + code + "' selected='selected'>" + name + "</option>";
						}else{
							conditions_box += "<option value='" + code + "'>" + name + "</option>";
						}
						
					});
					$("#p_sgg", parent.srch.document).append(conditions_box);	
				},
		    	error: function(){
		        	alert('Error loading XML document');
		    	}
			});
		},
		//읍면동 목록을가져온다.
		GetUMD : function(sggcode){
		
	       $("#p_umd", parent.srch.document).removeOption(/./);
	       $("#p_umd", parent.srch.document).addOption("", "::선택::", true);
	       $("#p_ri", parent.srch.document).removeOption(/./);
	       $("#p_ri", parent.srch.document).addOption("", "::선택::", true);
	       
	       if(sggcode != ""){
			   $.ajax({
			       type: "POST",
			       url: "../svc",
			       data: {svc : "GetUMD", sgg : sggcode},
			       dataType: "xml",
			       success: function(xml) {
			    	   var conditions_box = "";
			           $(xml).find('읍면동').each(function(idx){
			               var code = $(this).find('읍면동코드').text();
			               var name = $(this).find('읍면동명').text();
			               //alert(code);
			               if(code == sggcode){
			            	   conditions_box += "<option value='" + code + "' selected='selected'>" + name + "</option>";
			               }else{
			            	   conditions_box += "<option value='" + code + "'>" + name + "</option>";   
			               }
				           //$("#umd").addOption(code, name, false);
			           });
			           $("#p_umd", parent.srch.document).append(conditions_box);
			       },
			       error: function(){
			           alert("에러");
			       }
			   	});
	       }
		},
		//읍면동 목록을가져온다.
		GetRI : function(umdcode){
			
	       $("#p_ri", parent.srch.document).removeOption(/./);
	       $("#p_ri", parent.srch.document).addOption("", "::선택::", true);
	       if(umdcode != ""){
			   $.ajax({
			       type: "POST",
			       url: "../svc",
			       data: {svc : "GetRI", umd : umdcode},
			       dataType: "xml",
			       success: function(xml) {
			    	   var conditions_box = "";
			           $(xml).find('리').each(function(idx){
			               var code = $(this).find('리코드').text();
			               var name = $(this).find('리명').text();
			               if(code == umdcode){
			            	   conditions_box += "<option value='" + code + "' selected='selected'>" + name + "</option>";
			               }else{
			            	   conditions_box += "<option value='" + code + "'>" + name + "</option>";   
			               }
				           //$("#ri").addOption(code, name, false);
			           });
			           $("#p_ri", parent.srch.document).append(conditions_box); 
			       },
			       error: function(){
			           alert("에러");
			       } 
			   	});
	       }
		},	
		
	//기본정보
	tojiBasic : function(){
		//var srchFrame = $('body', parent.srch.document);
		$.ajax({
			type: "POST",
	        //url: "./form/tojiBasic.jsp",
			url: "./form/tojiDaejang.jsp",
	        data: {pnu : obj},
	        dataType: "html",
			success: function(htm){
				$("#toji_content_view", parent.srch.document).html(htm);
				$("#toji_tab_img", parent.srch.document).attr("src", "img/toji_tab_1.gif");
			},
			beforeSend : function(){
				//$("#toji_content_view", parent.srch.document).showLoading();
				tabMenu_Loading_ON();
				
			},
			complete : function(){
				//$("#toji_content_view", parent.srch.document).hideLoading();
				tabMenu_Loading_OFF();
			},
			error: function(xhr, status, error){
				alert(">>>>>>>>>>> : 에러");
			}
		});	
	},
	//토지대장
	tojiDaejang : function(obj){
		$.ajax({
 		    type: "POST",
	        //url: "./form/tojiDaejang.jsp",
 		   url: "./form/tojiDaejang.jsp",
	        data: {pnu : KEY},
	        dataType: "html",
			success: function(htm){
				$("#toji_content_view").html(htm);
				$("#toji_tab_img").attr("src", "img/toji_tab_2.gif");
				//reSize(); //사이즈재설정
			},
			beforeSend : function(){
				//$("#toji_content_view").showLoading();
				tabMenu_Loading_ON();
			},
			complete : function(){
				//$("#toji_content_view").hideLoading();
				tabMenu_Loading_OFF();
			},
			error: function(xhr, status, error){
					alert(">>>>>>>>>>> : 에러");
			}
		});	
	},
	//건축물대장
	bldgDaejang : function(){
		$.ajax({
			type: "POST",
	        //url: "./form/bldgDaejang.jsp",
			url: "./form/tojiDaejang.jsp",
	        data: {pnu : KEY},
	        dataType: "html",
			success: function(htm){
				$("#toji_content_view").html(htm);
				$("#toji_tab_img").attr("src", "img/toji_tab_3.gif");
				//reSize(); //사이즈재설정
			},
			beforeSend : function(){
				//$("#toji_content_view").showLoading();
				tabMenu_Loading_ON();
			},
			complete : function(){
				//$("#toji_content_view").hideLoading();
				tabMenu_Loading_OFF();
			},
			error: function(xhr, status, error){
					alert(">>>>>>>>>>> : 에러");
			}
		});	
	},
	//개별주택가격상세팝업
	ahpdHprc : function(pnu, addr, dong_seqno){
		var width = 950;
		var height = 550;
		var x = (screen.availWidth- width)/2;
		var y = (screen.availHeight- height)/2;
		var winState = 'top=' + y + ',left=' + x + ',width=' +width +',height=' +height; winState +=',menubar=no,scrollbars=yes,status=no,resizable=no';
		popId = window.open("./form/ahpdHprc.jsp?pnu="+Base64.encode(pnu)+"&addr="+encodeURIComponent(addr)+"&dong_seqno="+dong_seqno+"", '개별주택가격', winState);
		popId.focus();
	},
	//총괄표제부_일반건축물
	genbldg : function(key, pnu){
		var width = 1000;
		var height = 600;
		var x = (screen.availWidth- width)/2;
		var y = (screen.availHeight- height)/2;
		var winState = 'top=' + y + ',left=' + x + ',width=' +width +',height=' +height; winState +=',menubar=no,scrollbars=no,status=no,resizable=no';
		popId = window.open("./form/genbldg.jsp?key=" + Base64.encode(key) + "&pnu=" + Base64.encode(pnu) + "", '건축물대장', winState);
		popId.focus();
	},
	//표제부
	pyojebldg : function(key, pnu){
		var width = 1000;
		var height = 600;
		var x = (screen.availWidth- width)/2;
		var y = (screen.availHeight- height)/2;
		var winState = 'top=' + y + ',left=' + x + ',width=' +width +',height=' +height; winState +=',menubar=no,scrollbars=no,status=no,resizable=no';
		popId = window.open("./form/pyojebldg.jsp?key=" + Base64.encode(key) + "&pnu=" + Base64.encode(pnu) + "", '건축물대장', winState);
		popId.focus();
	},
	//전유부상세정보
	jeonyubldgInfo : function(key, pnu){
		var ouln_pk = $(".sel_"+ key).val();
		if(key != 0 && ouln_pk != 0){
			var width = 1000;
			var height = 600;
			var x = (screen.availWidth- width)/2;
			var y = (screen.availHeight- height)/2;
			var winState = 'top=' + y + ',left=' + x + ',width=' +width +',height=' +height; winState +=',menubar=no,scrollbars=no,status=no,resizable=no';
			popId = window.open("./form/jeonyubldg.jsp?key=" + Base64.encode(key) + 
								"&ouln_pk=" + Base64.encode(ouln_pk) + "&pnu=" + Base64.encode(pnu) + "", '건축물대장', winState);
			popId.focus();
		}else{
			alert("호수를 선택해주세요!");
		}
	},
	//전유부
	jeonyubldg : function(addr, key, blnm, pnu){
		$.ajax({
	       type: "POST",
	       url: "/svc",
	       data: {svc : "GetJeonyubldg", addr : encodeURIComponent(addr), key : key, blnm : encodeURIComponent(blnm), pnu : pnu},
	       dataType: "xml",
	       success: function(xml){
	    	   if($(xml).text() == "NoData" || $(xml).find("호명칭").length == 0){
	    		   alert("전유부의 검색결과가 없습니다.");
	    	   }else{
	    		   $("select#floorList").removeAttr("disabled");
	    		   $("select#floorList").attr("class", "sel_"+key);
	    		   $("#floorArea").show();
	    		   
	    		   $(".sel_"+ key).removeOption(/./);
	    	       $(".sel_"+ key).addOption("", ":: 호수선택::", true);
	    	       
	    	       var conditions_box = "";
	    		   $(xml).find("호명칭").each(function(){
	    			   var ho = $(this).attr("bno");
	    			   var name = $(this).text();
	    			   conditions_box += "<option value='" + ho + "'>" + name + " 동</option>";
	    		   });
	    		   $(".sel_" + key).append(conditions_box);
    			   var onclick = "_PC.init.setPDFLogPop('전유부', '"+ key +"', '"+ pnu +"', null, 'FW005', '" + blnm + "')";
    			   $("#floorSend").attr("onclick", onclick);
    			   $("#floorList").focus();
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
	//토지이용계획
	tojiUseplan : function(pnu){
		var width = 855;
		var height = 700;
		var x = (screen.availWidth- width)/2;
		var y = (screen.availHeight- height)/2;
		var winState = 'top=' + y + ',left=' + x + ',width=' +width +',height=' +height; winState +=',menubar=no,scrollbars=yes,status=no,resizable=no';
		popId = window.open("./form/tojiUseplan.jsp?pnu="+pnu+"", '건축물대장', winState);
		popId.focus();
	},
	//토지이용계획 luris 파싱
	tojiUseplanPasing : function(ucode){
		//홈페이지 파싱 XML을 가져온다.
		
		//인쇄시필요함
		$("#print_ucode").val(ucode);
		var pnu = $("#pnu_val").val();
		
		$.ajax({
			type: "POST",
			url : "../form/luris/luris_proxy.jsp",
			data : {svc : "GetBLLawView", pnu : pnu, ucode: ucode, usrid : SESSION_ID, isclick:"1"},
			dataType : "xml",
			success: function(xml){
				 if($(xml).find('response').length != 0){
					 if($(xml).find('RG_DESC').length != 0){
						  var rgdesc = $(xml).find('RG_DESC').text();
						  if(rgdesc != ""){
							  $("#slide_page_content_3").html(rgdesc);  
							  $("[name='ArService']").show();
							  $("[name='ArService_law']").show();
						  }else{
							  var str = "";
								 str += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>";
								 str += "<tr>";
								 str += "<td align='center'><b>토지이용규제정보</b>가 존재하지 않습니다.</td>";
								 str += "</tr>";
								 str += "</table>";
								  $("#slide_page_content_3").html(str);
						  }
					 }else{
						 var str = "";
						 str += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>";
						 str += "<tr>";
						 str += "<td align='center'><b>토지이용규제정보</b>가 존재하지 않습니다.</td>";
						 str += "</tr>";
						 str += "</table>";
						  $("#slide_page_content_3").html(str);
					 }
				 }
				//$("#slide_page_content_3").html(htm);
			},
			beforeSend : function(){
				gwsUtils.showLoading("body");
			},
		    complete : function(){
		    	gwsUtils.hideLoading();
		    },
			error: function(xhr, status, error){
					alert(">>>>>>>>>>> : 에러");
			}
		});
	},
	//지역지구별행위제한
	rdAct : function(pnu){
		var width = 855;
		var height = 700;
		var x = (screen.availWidth- width)/2;
		var y = (screen.availHeight- height)/2;
		var winState = 'top=' + y + ',left=' + x + ',width=' +width +',height=' +height; winState +=',menubar=no,scrollbars=yes,status=no,resizable=no';
		popId = window.open("./form/rdAct.jsp?pnu="+pnu+"", '건축물대장', winState);
		popId.focus();
	},
	
	//지역지구별행위제한 Luris 파싱
	getRdActPasing : function(ucode){
		//홈페이지 파싱 XML을 가져온다.
		$("#print_lgs").val(ucode);
		var pnu = $("#pnu_val").val();
		//alert(KEY + ", " + ucode + ", " + usrid);
		
		$.ajax({
			type: "POST",
			url : "../form/luris/luris_proxy.jsp",
			data : {svc : "GetBLViewByUcode", pnu : pnu, ucode: ucode, usrid : SESSION_ID},
			dataType : "xml",
			success: function(xml){
				console.log(xml);
				var str = "";
				var item;
				
				 if($(xml).find('response').length != 0){
					 if($(xml).find('ActRegUcode').length != 0){
						 
						 $(xml).find('ActRegUcode').each(function(idx, item){
							 
							 if($(this).find('actRegList').length != 0){
								 
								 str += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='auto'>";
								 str += "<tr align='center' bgcolor='#f2f2f2'>";
								 str += "<td height='30px' width='50%' style='border-top:2px solid #b8b8b8; border-right:1px solid #b8b8b8; border-bottom:1px solid #b8b8b8;'>토지이용행위</td>";
								 str += "<td width='50%' style='border-top:2px solid #b8b8b8; border-bottom:1px solid #b8b8b8;'>조건.제한.예외사항</td>";
								 str += "</tr>";
								 
								 $(this).find('actRegList').each(function(idx, item){
									 
									 var reg_nm = $(this).find('REG_NM').text();

									if(reg_nm == "가능"){
										
										 str += "<tr>";
										 str += "<td width='50%' style='border-right:1px dotted #7cbaec; border-bottom:1px solid #7cbaec;'>";
										 //str += "<p style='padding:5px;'>";
										 $(this).find('luGrInfoList').each(function(idx, item){
											 var lu_gr_nm =  $(this).find('LU_GR_NM').text();
											 var inn = $(this).find('luInfoList').length;
											 str += "<p style='padding:5px 0 0 2px;'>* <b style='color:#297dc0;'>행위 " + reg_nm + "</b></p>";
											 str += "<p style='padding:5px 0 0 2px;'> <b>" + lu_gr_nm + "</b></p>";
											 str += "<p style='padding: 3px 15px 10px 15px;'>";
											 $(this).find('luInfoList').each(function(idx, item){
												 var  node_desc = $(this).find('NODE_DESC').text();
												 var  lu_ref_law_nm1 = $(this).find(' LU_REF_LAW_NM1').text();
												 var  lu_ref_law_nm2 = $(this).find('LU_REF_LAW_NM2').text();
												 var  lu_ref_law_nm3 = $(this).find('LU_REF_LAW_NM3').text();
												 var  def_ref = $(this).find(' DEF_REF').text();
												 if((idx+1) != inn){
													 str += "" + node_desc + ", ";
												 }else{
													 str += "" + node_desc + "<br/>";
												 }
											 });
											 str += "</p>";
										 });
										 	
										// str += "<p>";
										 str += "</td>";
										 str += "<td width='50%' style='border-bottom:1px solid #7cbaec;'>";
										 //str +="<p style='padding:4px;'>";
											//조건 제한 예외사항
											if($(this).find('QNODE_CONDS').length != 0){
												$(this).find('QNODE_CONDS').each(function(){ //조건제한예외사항
													$(this).find('item').each(function(){
														var itm = $(this).text();
														$(xml).find('QnodeCond').each(function(idx, item){
															var qnode_desc = $(this).find('QNODE_DESC').text();
															var rnum = $(this).find('RNUM').text();
															if(rnum == itm){
																str += "<p style='padding:4px 3px 5px 3px;'> - " + qnode_desc + "</p>";
															}															
														});
													});
												});
											}
											//str +="</p>";
										 str += "</td>";
										 str += "</tr>";

									}else if(reg_nm == "금지"){
										
										 str += "<tr>";
										 str += "<td width='50%' style='border-right:1px dotted #fd6a6a; border-bottom:1px solid #fd6a6a;'>";
										 //str += "<p style='padding:5px;'>";
										 $(this).find('luGrInfoList').each(function(idx, item){
											 var lu_gr_nm =  $(this).find('LU_GR_NM').text();
											 var inn = $(this).find('luInfoList').length;
											 str += "<p style='padding:5px 0 0 2px;'>* <b style='color:#ff2f2f;'>행위 " + reg_nm + "</b></p>";
											 str += "<p style='padding:5px 0 0 2px;'> <b>" + lu_gr_nm + "</b></p>";
											 str += "<p style='padding: 3px 15px 10px 15px;'>";
											 $(this).find('luInfoList').each(function(idx, item){
												 var  node_desc = $(this).find('NODE_DESC').text();
												 var  lu_ref_law_nm1 = $(this).find(' LU_REF_LAW_NM1').text();
												 var  lu_ref_law_nm2 = $(this).find('LU_REF_LAW_NM2').text();
												 var  lu_ref_law_nm3 = $(this).find('LU_REF_LAW_NM3').text();
												 var  def_ref = $(this).find(' DEF_REF').text();
												 if((idx+1) != inn){
													 str += "" + node_desc + ", ";
												 }else{
													 str += "" + node_desc + "<br/>";
												 }
											 });
											 str += "</p>";
										 });
										 	
										// str += "<p>";
										 str += "</td>";
										 str += "<td width='50%' style='border-bottom:1px solid #fd6a6a;'>";
										 //str +="<p style='padding:4px;'>";
											//조건 제한 예외사항
											if($(this).find('QNODE_CONDS').length != 0){
												$(this).find('QNODE_CONDS').each(function(){ //조건제한예외사항
													$(this).find('item').each(function(){
														var itm = $(this).text();
														$(xml).find('QnodeCond').each(function(idx, item){
															var qnode_desc = $(this).find('QNODE_DESC').text();
															var rnum = $(this).find('RNUM').text();
															if(rnum == itm){
																str += "<p style='padding:4px 3px 5px 3px;'> - " + qnode_desc + "</p>";
															}															
														});
													});
												});
											}
											//str +="</p>";
										 str += "</td>";
										 str += "</tr>";
									}
								 });
								 str += "</table>";
								 $("#slide_page_content_3").html(str);
							 }						 
						 });

					 	}else{
					 		
					 		 var pstr = "";
							 pstr += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>";
							 pstr += "<tr>";
							 pstr += "<td align='center'><b>지역.지구별 행위 제한정보</b>가 존재하지 않습니다.</td>";
							 pstr += "</tr>";
							 pstr += "</table>";
							  $("#slide_page_content3").html(pstr);
					 	}
				}

							
				//$("#slide_page_content3").html(htm +"</span>");
			},
			beforeSend : function(){
				gwsUtils.showLoading("body");
			},
		    complete : function(){
		    	gwsUtils.hideLoading();
		    },
			error: function(xhr, status, error){
					alert(">>>>>>>>>>> : 에러");
			}
		});
	},
	
	//토지이용행위제한
	tojiAct : function(pnu){
		var width = 855;
		var height = 700;
		var x = (screen.availWidth- width)/2;
		var y = (screen.availHeight- height)/2;
		var winState = 'top=' + y + ',left=' + x + ',width=' +width +',height=' +height; winState +=',menubar=no,scrollbars=yes,status=no,resizable=no';
		popId = window.open("./form/tojiAct.jsp?pnu="+pnu+"", '건축물대장', winState);
		popId.focus();
	},
	
	//토지이용행위제한
	tojiActPasing : function(){
		
		var lgs = $("#luGrStr").val();
		var pnu = $("#pnu_val").val();
		
		if(lgs == ""){
			alert("토지이용 행위를 입력하여 주십시오!");
			this.ActUseinfopopup();
			return false;
		}
		$("#print_lgs").val(lgs);

		
		
		$.ajax({
			//url: "./form/parsing/lurisArParcel.jsp",
			//contentType : "text/html; charset=utf-8",
			type: "POST",
			url : "../form/luris/luris_proxy.jsp",
			data : {svc : "GetBLView", luGrStr:lgs, pnu:pnu, usrid : SESSION_ID},
			dataType : "xml",
			success: function(xml){
				var str = "";
				var item;
				 if($(xml).find('response').length != 0){
					 
					 if($(xml).find('ActReg').length != 0){

					 str += "<table border=0' cellspacing='0'  cellpadding='0'  width='100%'  height='auto'>";
					 str += "<tr align='center' bgcolor='#f2f2f2'>";
					 str += "<td width='20%' height='30' style='border-top:2px solid #7cbaec; border-right:1px solid #7cbaec; border-bottom:1px solid #7cbaec;'><b>지역.지구</b></td>";
					 str += "<td width='40%' style='border-top:2px solid #7cbaec; border-right:1px solid #7cbaec; border-bottom:1px solid #7cbaec;'><b>가능여부</b></td>";
					 str += "<td width='40%' style='border-top:2px solid #7cbaec;  border-bottom:1px solid #7cbaec;'><b>조건.제한.예외사항</b></td>";
					 str += "</tr>";
					 
					  $(xml).find('ActReg').each(function(idx, item){
						
						  var uname = $(this).find("UNAME").text();//지역지구명
						  var actRegList = $(this).find("actRegList").length; //행위가능여부별 조건제한 예외사항 및 토지이용정보
						  
						  str +="<tr>";
							  str +="<td colspan='3'>";
							  if(actRegList != 0){
								  str +="<table border='0' cellspacing='0'  cellpadding='0'  width='100%'  height='auto'>";
								  if(actRegList != 1){
									  $(this).find('actRegList').each(function(idx){
										  var act_nm = $(this).find("ACT_NM").text(); //행위명
										  var reg_nm = $(this).find("REG_NM").text(); //제한명

										str +="<tr>";
											if(idx == 0){
												str +="<td width='20%' style='border-right:1px dotted #7cbaec; border-bottom:1px solid #7cbaec;' rowspan='"+actRegList+"' align='center'>" + uname + "</td>";
											}
											str +="<td width='40%' style='border-right:1px dotted #7cbaec; border-bottom:1px solid #7cbaec;' >";
												str +="<p style='padding:4px;'>";
												str+= "<b>" + act_nm + " " + reg_nm+ "</b><br/>";
												$(this).find('luInfoList').each(function(idx){
													var node_desc =  $(this).find("NODE_DESC").text(); //토지이용명
													var lu_ref_law_nm1 = $(this).find("LU_REF_LAW_NM1").text(); //참조_법률조항1
													var lu_ref_law_nm2 = $(this).find("LU_REF_LAW_NM2").text(); //참조_법률조항2
													var lu_ref_law_nm3 = $(this).find("LU_REF_LAW_NM3").text(); //참조_법률조항3
													var def_ref = $(this).find("DEF_REF").text(); //정의 및 참조사항
													str+= "- " + node_desc + "<br/>";
												});
												str +="</p>";
											str +="</td>";
											str +="<td width='40%' style=' border-bottom:1px solid #7cbaec;' >";
												str +="<p style='padding:4px;'>";
												//조건 제한 예외사항
												$(this).find('QNODE_CONDS').each(function(){ //조건제한예외사항
													$(this).find('item').each(function(){
														var itm = $(this).text();
														$(xml).find('QnodeCond').each(function(idx, item){
															var qnode_desc = $(this).find('QNODE_DESC').text();
															var rnum = $(this).find('RNUM').text();
															if(rnum == itm){
																str += " - " + qnode_desc + "<br/><br/>";
															}															
														});
													});
												});
												str +="</p>";
											str +="</td>";
										str +="</tr>";
									  });
								  }else{
									  $(this).find('actRegList').each(function(idx, item){
										  var act_nm = $(this).find("ACT_NM").text(); //행위명
										  var reg_nm = $(this).find("REG_NM").text(); //제한명
											str +="<tr>";
										  		str +="<td width='20%' align='center' style='border-right:1px dotted #7cbaec; border-bottom:1px solid #7cbaec;' >" + uname + "</td>";  
												str +="<td width='40%' style='border-right:1px dotted #7cbaec; border-bottom:1px solid #7cbaec;' >";
													str +="<p style='padding:4px;'>";
													if(act_nm != "" || reg_nm != ""){
														str+= "<b>" + act_nm + " " + reg_nm+ "</b><br/>";
														$(this).find('luInfoList').each(function(idx, item){
															var node_desc =  $(this).find("NODE_DESC").text(); //토지이용명
															var lu_ref_law_nm1 = $(this).find("LU_REF_LAW_NM1").text(); //참조_법률조항1
															var lu_ref_law_nm2 = $(this).find("LU_REF_LAW_NM2").text(); //참조_법률조항2
															var lu_ref_law_nm3 = $(this).find("LU_REF_LAW_NM3").text(); //참조_법률조항3
															var DEF_REF = $(this).find("DEF_REF").text(); //정의 및 참조사항
															str+= "- " + node_desc + "<br/>";
														});
													}else{
														$(this).find('luInfoList').each(function(idx, item){
															var node_desc =  $(this).find("NODE_DESC").text(); //토지이용명
															var lu_ref_law_nm1 = $(this).find("LU_REF_LAW_NM1").text(); //참조_법률조항1
															str+= "<b>" + node_desc + "</b><br/>";
														});
													}
													str +="</p>";
												str +="</td>";
												str +="<td width='40%' style='border-bottom:1px solid #7cbaec;' >";
													str +="<p style='padding:4px;'>";
													//조건 제한 예외사항
													if($(this).find('QNODE_CONDS').length != 0){
														$(this).find('QNODE_CONDS').each(function(){ //조건제한예외사항
															$(this).find('item').each(function(){
																var itm = $(this).text();
																$(xml).find('QnodeCond').each(function(idx, item){
																	var qnode_desc = $(this).find('QNODE_DESC').text();
																	var rnum = $(this).find('RNUM').text();
																	if(rnum == itm){
																		str += " - " + qnode_desc + "<br/><br/>";
																	}															
																});
															});
														});
													}
													str +="</p>";
												str +="</td>";
											str +="</tr>"; 
									  });
								  }
								  str +="</table>";
							  }
							  str +="</td>";
						  str +="</tr>";
					  });
					 
					  str += "</table>";
					  $("#act_page_content").html(str);
					  
					 }else{
						 var pstr = "";
						 pstr += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>";
						 pstr += "<tr>";
						 pstr += "<td align='center'><b>조건별행위제한가능여부</b>가 존재하지 않습니다.</td>";
						 pstr += "</tr>";
						 pstr += "</table>";
						 $("#act_page_content").html(str);
					 }
				 }
			},
			beforeSend : function(){
				gwsUtils.showLoading("body");
			},
		    complete : function(){
		    	gwsUtils.hideLoading();
		    },
			error: function(xhr, status, error){
					alert(">>>>>>>>>>> : 에러");
			}
		});
	},
	ActUseinfopopup : function(){
		var winOpts = "top=100, left=100, toolbar=no, location=no, status=no, menubar=no, scrollbars=yes,resizable=0,width=480,height=480";
		var popup = window.open("./luris/BLUseNameSearch.jsp", "lurispop", winOpts);
	},
	/*ActUseinfopopup : function(){
		$.ajax({
			type: "POST",
			url: "./luris/BLUseNameSearch.jsp",
			success: function(html){
				$("#srchLurisArea").html(html);
			},
			error: function(){
				alert("ERROR");
			}
		});
		var winOpts = "top=100, left=100, toolbar=no, location=no, status=no, menubar=no, scrollbars=yes,resizable=0,width=450,height=480";
		popup = window.open("./form/luris/BLUseNameSearch.jsp", "lurispop", winOpts);
	},*/
	//레이어상세정보페이지
	getLyrMoreInfo : function(lyr_nm, lyr_id, srch_cond, resultcnt, wtk){
			
		$.ajax({
			type: "POST",
			url: "../svc",
			data: {svc : "GetLyrMoreResult", lyr_id:lyr_id, srch_cond : srch_cond, wtk : wtk, lyr_nm : encodeURIComponent(lyr_nm)},
			dataType: "xml",
			success: function(xml){
				
				$("#layernm").text(lyr_nm); //레이어명
				$("#layercnt").text(resultcnt); //총갯수
				
				 if($(xml).find('결과목록').length != 0){
					 var str = "";
		    		   $(xml).find('결과목록').each(function(idx, item){
		    			   
	    			    var code = $(this).find("관리코드").text();
		    			var result_val = $(this).find("결과값").text();
		    				
		    			str+="<div style='border-bottom:1px dotted #e3e3e3; height:20px; text-align: center;'>";
		    				str+="<p style='padding:3px;'><a href='#' title='" + result_val + "' onclick='_PC.init.getLyrItem(\""+lyr_nm+"\", \""+code+"\", \""+lyr_id+"\");'>" + result_val + "</a></p>";
		    			str+="</div>";
		    			
		    		   });
			           $("#lyr_result1").html(str);
		    	   }else{
		    		   
		    	   }
			},
			beforeSend : function(){
				//$("#lyr_content_box").showLoading();
			},
			complete : function(){
				//$("#lyr_content_box").hideLoading();
			},
			error: function(xhr, status, error){
					alert(">>>>>>>>>>> : 에러");
			}
		});
	},
	//반경검색_레이어상세정보페이지
	getLyrCircleInfo : function(lyr_nm, lyr_id, srch_cond, resultcnt, cx, cy, distance){
			
		$.ajax({
			type: "POST",
			url: "../svc",
			data: {svc : "GetLyrCircleResult", lyr_id:lyr_id, srch_cond : srch_cond, cx : cx, cy : cy, distance:distance, lyr_nm:encodeURIComponent(lyr_nm)},
			dataType: "xml",
			success: function(xml){
				
				$("#layernm").text(lyr_nm); //레이어명
				$("#layercnt").text(resultcnt); //총갯수
				
				 if($(xml).find('결과목록').length != 0){
					 var str = "";
		    		   $(xml).find('결과목록').each(function(idx, item){
		    			   
	    			    var code = $(this).find("관리코드").text();
		    			var result_val = $(this).find("결과값").text();
		    				
		    			str+="<div style='border-bottom:1px dotted #e3e3e3; height:20px; text-align: center;'>";
		    				str+="<p style='padding:3px;'><a href='#' title='" + result_val + "' onclick='_PC.init.getLyrItem(\""+lyr_nm+"\", \""+code+"\", \""+lyr_id+"\");'>" + result_val + "</a></p>";
		    			str+="</div>";
		    			
		    		   });
			           $("#lyr_result1").html(str);
		    	   }else{
		    		   
		    	   }
			},
			beforeSend : function(){
				//$("#lyr_content_box").showLoading();
			},
			complete : function(){
				//$("#lyr_content_box").hideLoading();
			},
			error: function(xhr, status, error){
					alert(">>>>>>>>>>> : 에러");
			}
		});
	},
	//레이어항목 값가져오기
	getLyrItem : function(lyr_nm, key, lry_id){
		//화면이동 
		_MAP.ctrl.mapMove(lyr_nm, key);
		  
		$.ajax({
			type: "POST",
			url: "../svc",
			data: {svc : "GetLyrDetailList", key:key, lry_id:lry_id},
			dataType: "xml",
			success: function(xml){
				 if($(xml).find('상세정보').length != 0){
					 var str = "";
					 var pstr = "";
					 str +="<table border='0' cellpadding='0' cellspacing='0' width='100%' height='auto'>";
		    		   $(xml).find('상세정보').each(function(idx, item){
	    			    var code = $(this).find("항목").text();
		    			var result_val = $(this).find("값").text();
		    			str +="<tr align='center'>";
		    				str +="<td width='40%' height='20' class='border2'>" + code + "</td>";
		    				str +="<td width='60%' class='border3'>" + result_val + "</td>";
		    			str +="</tr>";
		    		   });
		    		   str +="</table>";
			           $("#lyr_result2").html(str);
		    	   }else{
		    		   pstr +="<table border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>";
		    		   	pstr +="<tr align='center'>";
		    		   		pstr +="<td colspan='2'>결과값이 존재하지 않습니다.</td>";
		    		   	pstr +="</tr>";
		    		   pstr +="</table>";
		    		   $("#lyr_result2").html(pstr);
		    	   }
			},
			beforeSend : function(){
				$("#lyr_result2").showLoading();
			},
			complete : function(){
				$("#lyr_result2").hideLoading();
			},
			error: function(xhr, status, error){
					alert(">>>>>>>>>>> : 에러");
			}
		});
	},
	jeonyuclick : function(key){
		
		var ouln_pk = $("#ho_nm").val();
		
		if(ouln_pk == ""){
			alert("호명칭을 선택 하여주십시오!");
			 $("#ho_nm").focus();
			return false;
		}
				
		$.ajax({
			type: "POST",
	        url: "./form/jeonyuInner.jsp",
	        data: {key :key, ouln_pk:ouln_pk},
	        dataType: "html",
			success: function(htm){
				$("#djyexpos_box").html(htm);
			},
			beforeSend : function(){
				//$("#toji_content_view").showLoading();
			},
			complete : function(){
				//$("#toji_content_view").hideLoading();
			},
			error: function(xhr, status, error){
					alert(">>>>>>>>>>> : 에러");
			}
		});
	},
	//토지대장 실시간정보를 가지고온다.
	TojiRealTime : function(key){
		alert(key);
	},
	excelFormOn : function(){
		$("#excelfrm").show("fast");
		$("#use_resn").focus();
	},
	excelLogInsert : function(){
		
		var use_resn = $("#use_resn").val();
		var user_id = $("#user_id").val();
		var dept_id = $("#dept_id").val();
		var func_id = $("#func_id").val();
		var biz_id = $("#biz_id").val();
		
		
		var lyr_nm = $("#lyr_nm").val();
		var lyr_id = $("#lyr_id").val();
		var srch_cond = $("#srch_cond").val();
		var wtk = $("#wtk").val();
		
		
		
		if(use_resn == ""){
			alert("사용목적을 입력 하여주십시오!");
			$("#use_resn").focus();
			return false;
		}
		
		$.ajax({
			type:"POST",
			url:"./log/xml/LogRecord.jsp",
			data : {use_resn:use_resn, user_id:user_id, dept_id:dept_id, func_id:func_id, biz_id:biz_id},
			dataType : "xml",
			success: function(xml){
				if($(xml).find("result").length != 0){
					var retult = $(xml).find("result").text();
					if(retult == "success"){
						_PC.init.excelFormClose();
						jQuery.download("/intra/lyr/excel/excel_moreInfo.jsp","lyr_nm="+lyr_nm+"&lyr_id="+lyr_id+"&srch_cond="+srch_cond+"&wtk="+wtk+"", "post");
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
	},
	excelLogInsertCrir : function(){
		
		var use_resn = $("#use_resn").val();
		var user_id = $("#user_id").val();
		var dept_id = $("#dept_id").val();
		var func_id = $("#func_id").val();
		var biz_id = $("#biz_id").val();
		
		
		var lyr_nm = $("#lyr_nm").val();
		var lyr_id = $("#lyr_id").val();
		var srch_cond = $("#srch_cond").val();
		var cx = $("#cx").val();
		var cy = $("#cy").val();
		var distance = $("#distance").val();
		
		if(use_resn == ""){
			alert("사용목적을 입력 하여주십시오!");
			$("#use_resn").focus();
			return false;
		}
		
		$.ajax({
			type:"POST",
			url:"./log/xml/LogRecord.jsp",
			data : {use_resn:use_resn, user_id:user_id, dept_id:dept_id, func_id:func_id, biz_id:biz_id},
			dataType : "xml",
			success: function(xml){
				if($(xml).find("result").length != 0){
					var retult = $(xml).find("result").text();
					if(retult == "success"){
						_PC.init.excelFormClose();
						jQuery.download("/intra/lyr/excel/excel_moreInfoCir.jsp","lyr_nm="+lyr_nm+"&lyr_id="+lyr_id+"&srch_cond="+srch_cond+"&cx="+cx+"&cy="+cy+"&distance="+distance+"", "post");
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
	},
	excelLogInsertInteJijuk : function(){
		
		var use_resn = $("#use_resn").val();
		var user_id = $("#user_id").val();
		var dept_id = $("#dept_id").val();
		var func_id = $("#func_id").val();
		var biz_id = $("#biz_id").val();
		
		
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
		
		
		if(use_resn == ""){
			alert("사용목적을 입력 하여주십시오!");
			$("#use_resn").focus();
			return false;
		}
		
		$.ajax({
			type:"POST",
			url:"./log/xml/LogRecord.jsp",
			data : {use_resn:use_resn, user_id:user_id, dept_id:dept_id, func_id:func_id, biz_id:biz_id},
			dataType : "xml",
			success: function(xml){
				if($(xml).find("result").length != 0){
					var retult = $(xml).find("result").text();
					if(retult == "success"){
						_PC.init.excelFormClose();
						jQuery.download("/intra/lyr/excel/excel_InteJijuk.jsp","sgg="+sgg+"&umd="+umd+"&ri="+ri+"&owngb="+owngb+"&jimok="+jimok+"&uznecode1="+uznecode1+"&uznecode2="+uznecode2+"&uznecode3="+uznecode3+"&area1="+area1+"&area2="+area2+"&base_year="+base_year+"&jiga1="+jiga1+"&jiga2="+jiga2+"", "post");
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
	},
	excelLogInteInsert : function(){
		
		var use_resn = $("#use_resn").val();
		var user_id = $("#user_id").val();
		var dept_id = $("#dept_id").val();
		var func_id = $("#func_id").val();
		var biz_id = $("#biz_id").val();
				
		var lry_id = $("#lry_id").val();
		var lry_nm = $("#lry_nm").val();
		var cond_type = $("#cond_type").val();
		var included = $("#included").val();
		var match = $("#match").val();
		var range_1 = $("#range_1").val();
		var range_2 = $("#range_2").val();
		
		if(use_resn == ""){
			alert("사용목적을 입력 하여주십시오!");
			$("#use_resn").focus();
			return false;
		}
		
		$.ajax({
			type:"POST",
			url:"./log/xml/LogRecord.jsp",
			data : {use_resn:use_resn, user_id:user_id, dept_id:dept_id, func_id:func_id, biz_id:biz_id},
			dataType : "xml",
			success: function(xml){
				if($(xml).find("result").length != 0){
					var retult = $(xml).find("result").text();
					if(retult == "success"){
						_PC.init.excelFormClose();
						jQuery.download("/intra/lyr/excel/excel_moreInte.jsp","lry_id="+lry_id+"&lry_nm="+lry_nm+"&cond_type="+cond_type+"&included="+included+"&match="+match+"&range_1="+range_1+"&range_2="+range_2+"", "post");
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
	},
	excelFormClose : function(){
		$("#excelfrm").hide("fast");
		//$("#use_resn").val("");
	},
	
	//테이터수집갱신일
	dataClctInfo : function(tblnm){
			
		$.ajax({
			type: "POST",
			url: "../svc",
			data: {svc : "GetClctData", tblnm:tblnm, lyrnm : ""},
			dataType: "xml",
			success: function(xml){
				if($(xml).find('수집정보').length != 0){
					var str = "";
					str += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='auto'>";
					$(xml).find('수집정보').each(function(idx, item){
						var orgcd = $(this).find("관리기관").text();
						var updatedate = $(this).find("업데이트날짜").text();
						str += "<tr>";
							str += "<td height='18' class='style_border_1' width='20%'>"+orgcd+"</td>";
							str += "<td class='style_border_2' width='80%'>"+updatedate+"</td>";
						str += "</tr>";
					});
					str += "</table>";
					$("#clctdata_box").html(str);
					
				}else{
					var str = "";
					str += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>";
					str += "<tr>";
					str += "<td align='center'>데이터가 존재 하지않습니다.</td>";
					str += "</tr>";
					str += "</table>";
					$("#clctdata_box").html(str);
				}
				$("#lyrClctTable").show("fast");
			},
			beforeSend : function(){},
			complete : function(){},
			error: function(xhr, status, error){
				alert("에러");
			}
		});
	},
	dataClctInfoClose : function(){
		$("#lyrClctTable").hide("fast");
	},
	TojiPrintPop : function(obj){
		var width = 850;
		var height = 768;
		var x = (screen.availWidth- width)/2;
		var y = (screen.availHeight- height)/2;
		var winState = 'top=' + y + ',left=' + x + ',width=' +width +',height=' +height;
			winState +=',menubar=no,scrollbars=yes,status=no,resizable=no';
		popId = window.open("./form/printform/print_tojibasic.jsp?pnu="+obj, 'tojiPop', winState);
		popId.focus();
	},
	//행위제한법령정보 인쇄 팝업
	UseplanPrintPop : function(obj){
		var print_ucode = $("#print_ucode").val();
		var width =850;
		var height = 768;
		var x = (screen.availWidth- width)/2;
		var y = (screen.availHeight- height)/2;
		var winState = 'top=' + y + ',left=' + x + ',width=' +width +',height=' +height;
		winState +=',menubar=no,scrollbars=yes,status=no,resizable=no';
		popId = window.open("./form/printform/print_tojiUseplan.jsp?pnu="+obj+"&ucode="+print_ucode, 'tojiPop', winState);
		popId.focus();
	},
	//조건별 인쇄 팝업
	rdActPrintPop : function(obj){
		var print_ucode = $("#print_ucode").val();
		var width =850;
		var height = 768;
		var x = (screen.availWidth- width)/2;
		var y = (screen.availHeight- height)/2;
		var winState = 'top=' + y + ',left=' + x + ',width=' +width +',height=' +height;
		winState +=',menubar=no,scrollbars=yes,status=no,resizable=no';
		popId = window.open("./form/printform/print_rdAct.jsp?pnu="+obj+"&ucode="+print_ucode, 'tojiPop', winState);
		popId.focus();
	},
	//조건별행위제한가능여부 인쇄 팝업
	tojiActPrintPop : function(obj){
		
		var print_lgs = $("#print_lgs").val();
		var width =850;
		var height = 768;
		var x = (screen.availWidth- width)/2;
		var y = (screen.availHeight- height)/2;
		var winState = 'top=' + y + ',left=' + x + ',width=' +width +',height=' +height;
		winState +=',menubar=no,scrollbars=yes,status=no,resizable=no';
		popId = window.open("./form/printform/print_tojiAct.jsp?pnu="+obj+"&lgs="+print_lgs, 'tojiPop', winState);
		popId.focus();
	},
	
	//토지대장 PDF 다운로드
	TojidaeJangDownload : function(obj, username){
		
		jQuery.download("../PdfPrint/tojiDaejang_file.jsp","pnu="+obj+"&user_name="+encodeURIComponent(username)+"", "post");
		
		/*if(confirm("토지대장을 PDF로 다운로드 하시겠습니까?")) {
			jQuery.download("../PdfPrint/tojiDaejang_file.jsp","pnu="+obj+"&user_name="+encodeURIComponent(username)+"", "post");
        } else {
            return false;
        }*/
	},
	//건축물대장(총괄표제부)PDF 다운로드
	BldgDjydaeJangDownload : function(obj, pnu,  username){
		jQuery.download("../PdfPrint/bldgDjyDaejang_file.jsp","key="+obj+"&pnu="+pnu+"&user_name="+encodeURIComponent(username)+"", "post");
	},
	//건축물대장(표제부)PDF 다운로드
	BldgpyodaeJangDownload : function(obj, kind_nm, pnu, username){
		
		if(kind_nm != "일반건축물"){
			//표제부
			jQuery.download("../PdfPrint/bldgDjyTDaejang_file.jsp","key="+obj+"&pnu="+pnu+"&user_name="+encodeURIComponent(username)+"", "post");
		}else{
			//일반건축물
			jQuery.download("../PdfPrint/bldgGenDaejang_file.jsp","key="+obj+"&pnu="+pnu+"&user_name="+encodeURIComponent(username)+"", "post");
		}
	},
	//건축물대장(전유부)PDF 다운로드
	BldgjundaeJangDownload : function(key, pnu, ouln_pk, username){
		jQuery.download("../PdfPrint/bldgJunyouDaejang_file.jsp","key="+key+"&ouln_pk="+ouln_pk+"&pnu="+pnu+"&user_name="+encodeURIComponent(username)+"", "post");
	},
	printFrmOpen : function(){
		
		if($("#print_ucode").length){
			var print_ucode = $("#print_ucode").val();
			if(print_ucode == ""){
				alert("국토의 계획및 이용계획에 관한 법률을 선택하여 주십시오!");
				return false;
			}
		}
		
		if($("#print_lgs").length){
			var print_lgs = $("#print_lgs").val();
			if(print_lgs == ""){
				alert("토지이용행위를 검색하여 주십시오!");
				return false;
			}
		}

		$("#print_form").show();
	},
	printFrmClose : function(){
		$("#print_form").hide();
		$("#use_resn").val("");
	},
	setPDFLogPop : function(PDFType, key, pnu, sub_key, func_id, bno){
		var w = 400;
		var h = 250;
		var top = (screen.availHeight/2-100);
		var left = (screen.availWidth/2-100);
		var option = "menubar=no, toolbar=no, location=no, status=no, scrollbars=no, resizeble=no, width=" + w + "px, height=" + h + "px, top="+top+" , left="+left+", resizable=yes";
		
		if(PDFType === "전유부"){
			var bno2 = $("#floorList").val();
			if(bno2 == "" || bno2 == undefined || bno2 == null){
				alert("호수를 선택해 주세요.");
			}else{
				var pop = window.open("../intra/log/PdfLogRecord.jsp?func="+PDFType+"&key="+key+"&pnu="+pnu+"&sub_key="+$("#floorList").val()+"&func_id="+func_id+"&bno2="+bno+"&bno="+bno2,"pdfLogPop", option);
				pop.focus();
			}
		}else if(PDFType === ""){
			var pop = window.open("../intra/log/PdfLogRecord.jsp?func="+PDFType+"&key="+key+"&pnu="+pnu+"&sub_key="+sub_key+"&func_id="+func_id,"pdfLogPop", option);
			pop.focus();
		}else{
			var pop = window.open("../intra/log/PdfLogRecord.jsp?func="+PDFType+"&key="+key+"&pnu="+pnu+"&sub_key="+sub_key+"&func_id="+func_id,"pdfLogPop", option);
			pop.focus();
		}
	},
	//출력시 사용목적을 입력한다. 2012-01-31
	LogInsertPrint : function(obj){
				
		var use_resn = $("#use_resn").val();
		var user_id = $("#user_id").val();
		var user_name = $("#user_name").val();
		var dept_id = $("#dept_id").val();
		var func_id = $("#func_id").val();
		var biz_id = $("#biz_id").val();
		var pnu_val = $("#pnu_val").val();
		var key_val = $("#key_val").val();
		var ouln_val = $("#ouln_val").val();
		
		if(use_resn == ""){
			alert("사용목적을 입력 하여주십시오!");
			$("#use_resn").focus();
			return false;
		}
		

		$.ajax({
			type:"POST",
			url:"./log/xml/LogRecord.jsp",
			data : {use_resn:use_resn, user_id:user_id, dept_id:dept_id, func_id:func_id, biz_id:biz_id,pnu_val:KEY,key_val:key_val},
			dataType : "xml",
			success: function(xml){
				if($(xml).find("result").length != 0){
					var retult = $(xml).find("result").text();
					if(retult == "success"){

						if(obj == "tojibasic"){
							_PC.init.TojiPrintPop(pnu_val);
						}else if(obj == "tojidaejang"){
							_PC.init.TojidaeJangDownload(pnu_val, user_name);
						}else if(obj == "bldgDaejang1"){
							_PC.init.BldgDjydaeJangDownload(key_val, user_name);
						}else if(obj == "bldgDaejang2"){
							_PC.init.BldgpyodaeJangDownload(key_val, ouln_val, user_name);
						}else if(obj == "bldgDaejang3"){
							_PC.init.BldgjundaeJangDownload(key_val, ouln_val, user_name);
						}else if(obj == "UseplanPrint"){
							_PC.init.UseplanPrintPop(pnu_val);
						}else if(obj == "rdActPrint"){
							_PC.init.rdActPrintPop(pnu_val);
						}else if(obj == "tojiActPrint"){
							_PC.init.tojiActPrintPop(pnu_val);
						}
						_PC.init.printFrmClose();
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

};

/*
 * luris 페이지 관련 스크립트
 */
function show_detail(selectObject) {
	
	var obj = document.getElementsByName("ArService");
	var obj2 = document.getElementsByName("ArService_law");
	
	for(var i = 0; i < obj.length; i++ ){
		if(selectObject==obj[i]) {
			if(obj2[i].style.display=="block") {
				obj2[i].style.display="none";				
			} else {
				obj2[i].style.display="block";
			}				
		}
		//parent.getReSize();
	}		
}	


function menuOn(name) {
	document.getElementById("submenu"+name).style.visibility = "visible";
}
function menuOff(name) {
    document.getElementById("submenu"+name).style.visibility = "hidden";
}
function menuOno(name) {
	document.getElementById("submenutwe"+name).style.visibility = "visible";
}
function menuOffo(name) {
	document.getElementById("submenutwe"+name).style.visibility = "hidden";
}


function pagechange(id){
	var zBut = document.getElementById("Z");
	var yBut = document.getElementById("Y");
	var gBut = document.getElementById("G");
	var info = document.getElementById("infoZone");
	if(id == "Z"){
		zBut.src="http://luris.moct.go.kr/web/ext/images/button/Tab_actregA.gif";
		zBut.align="absbottom";
		zBut.style.cursor="cursor:default";	
				
		yBut.src="http://luris.moct.go.kr/web/ext/images/button/Tab_CpacovgeB.gif";
		yBut.align="absbottom";
		yBut.style.cursor="hand";

		
		gBut.src="http://luris.moct.go.kr/web/ext/images/button/Tab_buildcovgeB.gif";
		gBut.align="absbottom";
		gBut.style.cursor="hand";		
		info.innerHTML="";			
		info.innerHTML=document.getElementById("infoZ").value;
	}else if(id == "G"){
		gBut.src="http://luris.moct.go.kr/web/ext/images/button/Tab_buildcovgeA.gif";
		gBut.align="absbottom";
		gBut.style.cursor="cursor:default";	
				
		zBut.src="http://luris.moct.go.kr/web/ext/images/button/Tab_actregB.gif";
		zBut.align="absbottom";
		zBut.style.cursor="hand";

		
		yBut.src="http://luris.moct.go.kr/web/ext/images/button/Tab_CpacovgeB.gif";
		yBut.align="absbottom";
		yBut.style.cursor="hand";
		info.innerHTML="";			
		info.innerHTML=document.getElementById("infoG").value;
		
	}else if(id == "Y"){
		yBut.src="http://luris.moct.go.kr/web/ext/images/button/Tab_CpacovgeA.gif";
		yBut.align="absbottom";
		yBut.style.cursor="cursor:default";	
				
		zBut.src="http://luris.moct.go.kr/web/ext/images/button/Tab_actregB.gif";
		zBut.align="absbottom";
		zBut.style.cursor="hand";

		
		gBut.src="http://luris.moct.go.kr/web/ext/images/button/Tab_buildcovgeB.gif";
		gBut.align="absbottom";
		gBut.style.cursor="hand";		
		info.innerHTML="";
		info.innerHTML=document.getElementById("infoY").value;			
	}		
}

//지역.지구관련 스크립트
function lawPopup(lawFullCd) {
	var lawCd = lawFullCd.substring(0, 14);
	var byul = lawFullCd.substring(14, 15);
	var joNo = lawFullCd.substring(15, 21);
	var joSubNo = lawFullCd.substring(21);
	var width =735;
	var height = 500;
	var x = (screen.availWidth- width)/2;
	var y = (screen.availHeight- height)/2;
	var winState = 'top=' + y + ',left=' + x + ',width=' +width +',height=' +height;
	winState +=',menubar=no,scrollbars=yes,status=no,resizable=no';
	popId = window.open("http://luris.moct.go.kr/web/actreg/arservice/RgLawPreviewPopup.jsp?lawCd="+lawCd+"&byul="+byul+"&joNo="+joNo+"&joSubNo="+joSubNo, '', winState);
	popId.focus();
}



function TRbackgroundColoron(obj){
	$(obj).css({"background-color":"#eeeeee"});
}

function TRbackgroundColorout(obj){
	$(obj).css({"background-color":"#ffffff"});
}

function tabMenu_Loading_ON(){
	
	var Tab_1_menu = $("#Tab_1_menu", parent.srch.document);
	var Tab_2_menu = $("#Tab_2_menu", parent.srch.document);
	var Tab_3_menu = $("#Tab_3_menu", parent.srch.document);
	var Tab_4_menu = $("#Tab_4_menu", parent.srch.document);
	var Tab_5_menu = $("#Tab_5_menu", parent.srch.document);
	var Tab_6_menu = $("#Tab_6_menu", parent.srch.document);
	var top_btn_such  = $("#top_btn_such", parent.srch.document);
	
	Tab_1_menu.onclick = function(){};
	Tab_2_menu.onclick = function(){};
	Tab_3_menu.onclick = function(){};
	Tab_4_menu.onclick = function(){};
	Tab_5_menu.onclick = function(){};
	Tab_6_menu.onclick = function(){};
	top_btn_such.onclick = function(){};
	
}

function tabMenu_Loading_OFF(){
	var Tab_1_menu = $("#Tab_1_menu", parent.srch.document);
	var Tab_2_menu = $("#Tab_2_menu", parent.srch.document);
	var Tab_3_menu = $("#Tab_3_menu", parent.srch.document);
	var Tab_4_menu = $("#Tab_4_menu", parent.srch.document);
	var Tab_5_menu = $("#Tab_5_menu", parent.srch.document);
	var Tab_6_menu = $("#Tab_6_menu", parent.srch.document);
	var top_btn_such  = $("#top_btn_such", parent.srch.document);
	
	Tab_1_menu.onclick = function(){
		_PC.init.tojiBasic();
	};
	Tab_2_menu.onclick = function(){
		_PC.init.tojiDaejang();
	};
	Tab_3_menu.onclick = function(){
		_PC.init.bldgDaejang();
	};
	Tab_4_menu.onclick = function(){
		_PC.init.tojiUseplan();
	};
	Tab_5_menu.onclick = function(){
		_PC.init.rdAct();
	};
	Tab_6_menu.onclick = function(){
		_PC.init.tojiAct();
	};
	top_btn_such.onclick = function(){
		_PC.init.PNUCREATE();
	};
}






