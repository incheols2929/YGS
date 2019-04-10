var TITLE;
if(!window._LYR) _LYR = {};

$(document).ready(function(){
	
	jQuery.fn.center = function () {
		this.css("margin-top", (jQuery(window).height() - this.outerHeight()) / 2 + jQuery(window).scrollTop() + "px");
		return this;
	},jQuery('#popstyle').center();
	
	//레이어목록을 가지고 온다.
	_LYR.ctrl.getLyrCategory();
	//현재레이어화면의 레이어목록을 가지고온다.
	_LYR.ctrl.getLyrReal();
	//TITLE명이 스타일 기본설정이면 현재스타일갱신버튼을 바꿔준다.
	_LYR.ctrl.btnIconChange();
	
	
});

//그룹명수정시 사용
var GRPNUM;

_LYR.ctrl = {
		//레이어목록을 가지고온다.
		getLyrCategory : function(){
			$.ajax({
				type: "POST",
				url: "/svc",
				data: {svc : "GetLyrCategory", id:user_id, grp_id:session_grp_id},
				dataType: "xml",
				success: function(xml){
					if($(xml).find('레이어관리').length != 0){
						var str = "";
						var mes = "클릭하시면 해당 레이어의\r수집정보를 보실수 있습니다.";
						$(xml).find('레이어관리').each(function(grpidx, item){
							var grpnm = $(this).find("레이어그룹").text();
							var grpid = $(this).find("레이어그룹").attr("grpid");
							if($(this).find('레이어명').length != 0){
								str += "<div id='lyr_group_"+grpidx+"'  style='padding:5px;'>";
								//str += "<b>" + grpnm + "("+grpid+")</b>";
								str += "<div class='lyr_name_"+grpidx+"'>";
								str += "<input type='checkbox'  class='grpcheck' id='chk_"+grpidx+"' name='chk_"+grpidx+"' value='lyr_name_"+grpidx+"' onclick='_LYR.ctrl.checkBoxGrpAll(\""+grpidx+"\");' style='vertical-align: middle;'/>";
								str += "<a href='#' onclick='_LYR.ctrl.ulSlide(\"ul_lyr_"+grpidx+"\");'><b>" + grpnm + "</a></b>";
								str += "</div>";
								if($(this).find('레이어목록').length != 0){
									$(this).find('레이어목록').each(function(ulidx, item){
										if($(this).find('레이어명').length != 0){
											str += "<ul id='ul_lyr_"+grpidx+"' style='display:none;'>";
											$(this).find('레이어명').each(function(lyridx, item){
												var lyrnm = $(this).text();
												//var lyrid = $(this).attr("lyrid");
												var tblid = $(this).attr("tblid");
												str += "<li id='li_lyr_"+grpidx+"_"+lyridx+"' style='height:20px; margin-left:10px; list-style: none;'>";
												str += "<p style='padding:5px 0 0 0;'>";
													str += "<input type='checkbox' class='lyr_name' id='lyr_name_"+grpidx+"' name='lyr_name_"+grpidx+"' value='"+lyrnm+"' style='vertical-align: middle;'/>";
													str += lyrnm;
													//str += "<span id='span_lyr_"+grpidx+"_"+lyridx+"' style='display:none;'>"+lyrid+"</span>";
													//str += "<a href='#' onclick='_LYR.ctrl.addLyr(\"lyr_"+grpidx+"_"+lyridx+"\");'>테스트</a>";
												str += "</p>";
												str += "</li>";
											});
											str += "</ul>";
										}
									});
								}
									str += "</div>";
							}else{
								var tstr = "";
								tstr += "<table border='0' cellspacing='0' cellpadding='0' width='100%' height='100%'>";
								tstr += "<tr>";
								tstr += "<td align='center'>레이어목록이 존재하지 않습니다.</td>";
								tstr += "</tr>";
								tstr += "</table>";
								$("#lyr_category").html(tstr);
							}
						});
						$("#lyr_category").html(str);
					}else{
						var tstr = "";
						tstr += "<table border='0' cellspacing='0' cellpadding='0' width='100%' height='100%'>";
						tstr += "<tr>";
						tstr += "<td align='center'>레이어목록이 존재하지 않습니다.</td>";
						tstr += "</tr>"; 
						tstr += "</table>";
						$("#lyr_category").html(tstr);
					}
				},
				beforeSend : function(){
					$("#lyr_category").showLoading();
				},
				complete : function(){
					$("#lyr_category").hideLoading();
				},
				error: function(xhr, status, error){
					alert("에러");
				}
			});
		},
		//지도화면의 현재축척의 레이어목록을 가지고온다.
		getLyrReal : function(){
			
			var xml = "<?xml version='1.0' encoding='UTF-8'?>";
			xml += opener.parent.index._MAINMAP.getLayerXML();
			
			var xmlDoc = $.parseXML(xml);
			var $xml = $(xmlDoc);
			
			if($xml.find("MapLayers").length != 0){
				TITLE = $xml.find("Title").text();

				var str = "";
				$xml.find("Group").each(function(grpidx, item){
					var grpnm = $(this).attr("name"); 
					str += "<div id='map_lyr_group_"+grpidx+"' style='padding:5px;'>";
						str += "<div style='height:20px;'>";
						str += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>";
						str += "<tr>";
							str += "<td width='75%'>";
							str += "<div id='inner_map_lyr_group_"+grpidx+"' class='inner_map_daeSubject'>";
								str += "<input type='radio' id='addlyr' name='addlyr' value='"+grpidx+"' style='vertical-align: middle;'/><b>" + grpnm + "</b>";
							str += "</div>";
							str += "</td>";
							str += "<td width='25%' align='center'>";
							str += "<div>" +
										"<a href='#' onclick='_LYR.ctrl.editGrpnmPop(\""+grpidx+"\");'>" +
											"<img src='./img/icon1_normal.png' onmouseover='this.src=&#39;./img/icon1_over.png&#39;' onmouseout='this.src=&#39;./img/icon1_normal.png&#39;' align='absmiddle' title='그룹명수정'/>" +
										"</a> <a href='#' onclick='_LYR.ctrl.GrpRemove(\""+grpidx+"\");'>" +
												"<img src='./img/icon2_normal.png' onmouseover='this.src=&#39;./img/icon2_over.png&#39;' onmouseout='this.src=&#39;./img/icon2_normal.png&#39;' align='absmiddle' title='그룹삭제'/>" +
										"<a>" +
									"</div>";
							str += "</td>";
						str += "</tr>";
						str += "</table>";
						str += "</div>";
						str += "<ul id='map_ul_lyr_"+grpidx+"'>";
						$(this).find("Layer").each(function(lyridx){
							var lyrnm = $(this).text();
							str += "<li id='map_li_lyr_"+grpidx+"_"+lyridx+"' style='height:20px; margin-left:15px; list-style: none;'>";
								str += "<p style='padding:5px 0 0 0;'>";
									str += "<input type='checkbox' class='r_lyr_name' id='r_lyr_name_"+grpidx+"' name='r_lyr_name_"+grpidx+"' value='map_li_lyr_"+grpidx+"_"+lyridx+"' style='vertical-align: middle;'/>" + lyrnm;
								str += "</p>";
							str += "</li>";
						});
						str += "</ul>";
					str += "</div>";
				});
				$("#lyr_real").html(str);
			}
			
		},
		getLyrId : function(lyrnm){
			//아이디값을 가지고온다.
			return lyrnm;
		},
		addGrpPop : function(){
			$("#grpnmadd_box").show("fast");
		},
		addGrpClose : function(){
			var grpnm = $("#grpnm").val();
			var fil_dae = _LYR.ctrl.DaeLryfilter(grpnm);
			
			if(fil_dae != "N"){
				this.addGrp(grpnm);
				$("#grpnm").val("");
				$("#grpnmadd_box").hide("fast");
			}else{
				alert(grpnm + "의 그룹명이 존재합니다.");
			} 

		},
		addGrp : function(obj){
		 	var adddiv = "";
		 	var grpidx = $("#lyr_real > div").length + 1;
		 	
		 	if(obj != ""){
		 		adddiv += "<div id='map_lyr_group_"+grpidx+"' style='padding:5px;'>";
				 	adddiv += "<div style='height:20px;'>";
					 	adddiv += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>";
						 	adddiv += "<tr>";
							 	adddiv += "<td width='80%'>";
								 	adddiv += "<div id='inner_map_lyr_group_"+grpidx+"' class='inner_map_daeSubject'>";
								 	adddiv += "<input type='radio' id='addlyr' name='addlyr' value='"+grpidx+"' style='vertical-align: middle;'/><b>" + obj + "</b>";
								 	adddiv += "</div>";
							 	adddiv += "</td>";
							 	adddiv += "<td width='20%' align='center'>";
							 		adddiv += "<div><a href='#' onclick='_LYR.ctrl.editGrpnmPop(\""+grpidx+"\");'><img src='../img/group_edit.gif' align='absmiddle' title='그룹명수정'/></a> <a href='#' onclick='_LYR.ctrl.GrpRemove(\""+grpidx+"\");'><img src='../img/group_remove.gif' align='absmiddle' title='그룹삭제'/></a></div>";
							 	adddiv += "</td>";
						 	adddiv += "</tr>";
					 	adddiv += "</table>";
				 	adddiv += "</div>";
			 	adddiv += "<ul id='map_ul_lyr_"+grpidx+"'></ul>";
			 	adddiv += "</div>";
				$("#lyr_real").append(adddiv);
		 	}
		},
		addGrpALL : function(obj, gnm){
		 	var adddiv = "";
		 	var grpidx = $("#lyr_real > div").length + 1;
		 	
		 	if(obj != ""){
		 		adddiv += "<div id='map_lyr_group_"+grpidx+"' style='padding:5px;'>";
				 	adddiv += "<div style='height:20px;'>";
					 	adddiv += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>";
						 	adddiv += "<tr>";
							 	adddiv += "<td width='80%'>";
								 	adddiv += "<div id='inner_map_lyr_group_"+grpidx+"' class='inner_map_daeSubject'>";
								 	adddiv += "<input type='radio' id='addlyr' name='addlyr' value='"+grpidx+"' style='vertical-align: middle;'/><b>" + obj + "</b>";
								 	adddiv += "</div>";
							 	adddiv += "</td>";
							 	adddiv += "<td width='20%' align='center'>";
							 		adddiv += "<div><a href='#' onclick='_LYR.ctrl.editGrpnmPop(\""+grpidx+"\");'><img src='../img/group_edit.gif' align='absmiddle' title='그룹명수정'/></a> <a href='#' onclick='_LYR.ctrl.GrpRemove(\""+grpidx+"\");'><img src='../img/group_remove.gif' align='absmiddle' title='그룹삭제'/></a></div>";
							 	adddiv += "</td>";
						 	adddiv += "</tr>";
					 	adddiv += "</table>";
				 	adddiv += "</div>";
			 	adddiv += "<ul id='map_ul_lyr_"+grpidx+"'>";
				 	$("input[id="+gnm+"]:checkbox:checked").each(function(idx){
						var s_lyrnm = $(this).val();
					  	//추가할 레이어목록에 같은레이어 이름이 있으면 N을 반환 한다.
				    	var filchr = _LYR.ctrl.Lryfilter(s_lyrnm);
					  	if(filchr != "N"){
					  		adddiv += "<li id='map_li_lyr_"+grpidx+"_"+(idx+1)+"' style='height:20px; margin-left:15px; list-style: none;'>";
					  		adddiv += "<p style='padding:5px 0 0 0;'>";
					  		adddiv += "<input type='checkbox' class='r_lyr_name' id='r_lyr_name_"+grpidx+"' name='r_lyr_name_"+grpidx+"' value='map_li_lyr_"+grpidx+"_"+(idx+1)+"' style='vertical-align: middle;'/>" + s_lyrnm;
							adddiv += "</p>";
							adddiv += "</li>";
							
							//alert("r_lyr_name_"+grpidx+"");
					  	}
					});
				 adddiv += "</ul>";
			 	adddiv += "</div>";
			 				 	
				$("#lyr_real").append(adddiv);
				
		 	}
		},
		
		
		addLyr : function(){
			
			var tlen = $("input[class=lyr_name]:checkbox:checked").length;
			var grpcheck = $("input[class=grpcheck]:checkbox:checked").length;
			
			if(tlen != 0){
				if($("input[name=addlyr]:radio:checked").length == 0){
					if(grpcheck != 0){
						$("input[class=grpcheck]:checkbox:checked").each(function(idx){
							var d_lyrnm = $(this).val();
							var d_lyrnm2 = $("."+d_lyrnm).text();
							var fil_dae = _LYR.ctrl.DaeLryfilter(d_lyrnm2);
							if(fil_dae != "N"){
								_LYR.ctrl.addGrpALL(d_lyrnm2, d_lyrnm);
							}else{
								alert(d_lyrnm2 + "의 그룹명이 존재합니다.");
							}
						});
						$("input[class=grpcheck]").attr("checked", false);
						$("input[class=lyr_name]").attr("checked", false);
					}
				}else{
					var str = ""; 
					var cleck = $("input[name=addlyr]:radio:checked").val();
					var maplyrnm = "";
					var lyrnm = "";
					var num = "";
					
					$("input[class=lyr_name]:checkbox:checked").each(function(idx){
					  //추가할레이어명
					  	lyrnm = $(this).val();
					  	num = $("#map_lyr_group_"+cleck+" > ul > li").length + (idx+1);
					  	//추가할 레이어목록에 같은레이어 이름이 있으면 N을 반환 한다.
				    	var filchr = _LYR.ctrl.Lryfilter(lyrnm);
					  	if(filchr != "N"){
					  		str += "<li id='map_li_lyr_"+cleck+"_"+num+"' style='height:20px; margin-left:15px; list-style: none;'>";
							str += "<p style='padding:5px 0 0 0;'>";
								str += "<input type='checkbox' class='r_lyr_name' id='r_lyr_name_"+cleck+"' name='r_lyr_name_"+cleck+"' value='map_li_lyr_"+cleck+"_"+num+"' style='vertical-align: middle;'/>" + lyrnm;
								//str += lyrnm;
							str += "</p>";
							str += "</li>";
					  	}
					});
					if($("input[name=addlyr]:radio:checked").length != 0){
						var addlyrnm = $("input[name=addlyr]:radio:checked").val();
						$("#map_ul_lyr_"+addlyrnm).append(str);
						$("input[class=grpcheck]").attr("checked", false);
						$("input[class=lyr_name]").attr("checked", false);
						$("input[id=addlyr]").attr("checked", false);
						
					}else{
						alert("추가할곳의 레이어 그룹을 선택하여 주십시오!");
					}
				}
			}else{
				alert("추가할 레이어를 선택하여 주십시오!");
			}
		},
		ulSlide : function(obj){ 
			 $("#"+obj).slideToggle("fast"); 
		},
		lyrCreateXML : function(){
			var xml = "";
			xml += "<MapLayers>";
			$("#lyr_real > div").each(function(idx){
				var idname = $(this).attr("id");
				var grpnm = $("#inner_"+idname).text();				
				xml += "<Group name='"+grpnm+"'>";
				
				$("#"+idname+" > ul > li").each(function(idx){
					var lyrid = $(this).text();	
					xml += "<Layer>"+lyrid+"</Layer>";
				});
				
				xml += "</Group>";
			});
			xml += "</MapLayers>";
			
			return xml;
			
		},
		checkBoxGrpAll : function(obj){
			if($("input[name=chk_"+obj+"]").is(":checked")){
				$("input[name=lyr_name_"+obj+"]").attr("checked", true);	
			}else{
				$("input[name=lyr_name_"+obj+"]").attr("checked", false);
			}
		},
		lyrBoxCheckRemove : function(){
			var r_lyr = $("input[class=r_lyr_name]:checkbox:checked").length;
			if(r_lyr != 0){
				$("input[class=r_lyr_name]:checkbox:checked").each(function(idx){
					var lyrnm = $(this).val();
					$("#"+lyrnm).remove();
				});	
			}else{
				alert("삭제할 레이어목록을 선택하여 주십시오!");
			}
		},
		//문자비교하여 걸러낸다.
		Lryfilter : function(lyrnm){
			var fil = "Y";
			$("#lyr_real > div > ul > li").each(function(idx){
				var ss = $(this).text();
				if(lyrnm == ss){
					fil = "N";
					return false;
				}else{
					fil= "Y";
					return true;
				}
			});
			return fil;
		},
		//문자비교하여 걸러낸다.
		DaeLryfilter : function(lyrnm){
			var fil = "Y";
			//alert($(".inner_map_daeSubject").length);
			$(".inner_map_daeSubject").each(function(idx){
				var ss = $(this).text();
				if(lyrnm == ss){
					fil = "N";
					return false;
				}else{
					fil= "Y";
					return true;
				}
			});
			return fil;
		},
		//레이어그룹명 수정
		editGrpnmPop : function(grpnum){
			GRPNUM = grpnum;
			var grpnm = $("#inner_map_lyr_group_"+GRPNUM).text();
			$("#grpnmedit").val(grpnm);
			$("#grpnmedit_box").show("fast");
		},
		editGrpnm : function(){
			var grpnm = $("#grpnmedit").val();
			var tag = "<input type='radio' id='addlyr' name='addlyr' value='"+GRPNUM+"' style='vertical-align: middle;'/><b>" + grpnm + "</b>";
			$("#inner_map_lyr_group_"+GRPNUM).html(tag);
			$("#grpnmedit").val("");
			$("#grpnmedit_box").hide("fast");
			GRPNUM = "";
		},
		GrpRemove : function(grpnum){
			var grpnm = $("#inner_map_lyr_group_"+grpnum).text();
			var txt = confirm("그룹 " + grpnm + "을(를) 삭제 하시겠습니까?");
			if(txt){
				$("#map_lyr_group_"+grpnum).remove();	
			}
		},
		//신규추가시 호출
		CreatedMapStyle : function(titlenm){

			var xml = this.lyrCreateXML();
			var result = confirm("스타일을 신규추가 하시겠습니까?");
			var title = titlenm;
			
			if(result){
				$.ajax({
					type: "POST",
					url: "/svc",
					data: {svc : "GetCreatedMapStyle", xml:encodeURIComponent(xml)},
					dataType: "text",
					success: function(txt){
						opener.parent.index._MAINMAP.addMapCfgXML(title, txt);
						window.close();
					},
					beforeSend : function(){},
					complete : function(){},
					error: function(xhr, status, error){
						alert("에러");
					}
				});
			}
		},
		EditTitleName : function(){
			$("#styleTitle_box").show("fast");
		},
		CloseTitleName : function(){
			
			var stylenm = $("#styleTirle").val();
			
			if(stylenm == ""){
				alert("스타일명을 입력 하여 주십시오!");
				$("#styleTirle").focus();
				return false;
			}
			
			$.ajax({
				type: "POST",
				url: "./xml/stitleSrch.jsp",
				data: {title:stylenm, user:encodeURIComponent(user_id)},
				dataType: "xml",
				success: function(xml){
					var til = $(xml).find("title").text(); 
					 
					if(til == "T"){
						alert("같은 스타일명이 존재합니다.");
						$("#styleTirle").val("");
						$("#styleTirle").focus();
						return false;
					}else{
						$("#styleTitle_box").hide("fast");
						_LYR.ctrl.CreatedMapStyle(stylenm);
						$("#styleTirle").val("");
					}
				},
				beforeSend : function(){},
				complete : function(){},
				error: function(xhr, status, error){alert("에러"); }
			});
		},
		//현재스타일을 갱신할때 호출
		ReplacedMapStyle : function(){
			
			var xml = this.lyrCreateXML();
			var title = TITLE;
			var result = confirm("스타일을 갱신 하시겠습니까?");
			if(result){
				$.ajax({
					type: "POST",
					url: "/svc",
					data: {svc : "GetReplacedMapStyle", user:encodeURIComponent(user_id), title:encodeURIComponent(title), xml:encodeURIComponent(xml)},
					dataType: "text",
					success: function(txt){
						opener.parent.index._MAINMAP.replaceMapCfgXML(txt);
						window.close();
					},
					beforeSend : function(){},
					complete : function(){},
					error: function(xhr, status, error){
						alert("스타일 갱신 에러");
					}
				});
			}
		},
		
		//데이터수집정보를 읽어온다.
		dataClctInfo : function(tblnm, grpnum, lyrnum, lyrnm){
			
			var lyrnm = $("#li_lyr_"+grpnum+"_"+lyrnum).text();
			
			$.ajax({
				type: "POST",
				url: "/svc",
				data: {svc : "GetClctData", tblnm:tblnm, lyrnm:encodeURIComponent(lyrnm)},
				dataType: "xml",
				success: function(xml){
					if($(xml).find('수집정보').length != 0){
						var str = "";
						str += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='auto'>";
						$(xml).find('수집정보').each(function(idx, item){
							var orgcd = $(this).find("관리기관").text();
							var syscd = $(this).find("시스템명").text();
							var updatedate = $(this).find("업데이트날짜").text();
							str += "<tr align='center'>";
								str += "<td height='18' class='style_border_1' width='20%'>"+orgcd+"</td>";
								//str += "<td class='style_border_1' width='25%'>"+syscd+"</td>";
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
					$("#stylenm").html("<b>"+lyrnm+"</b>");
					$("#lyrClctTable").show();
				},
				beforeSend : function(){},
				complete : function(){},
				error: function(xhr, status, error){
					alert("에러");
				}
			});
		},
		dataClctInfoClose : function(){
			$("#lyrClctTable").hide();
		},
		//현제레이어의 선택된 체크박스, 라디오박스를 해제한다.
		CheckRemove : function(){
			$("input[class=r_lyr_name]").attr("checked", false);
			$("input[id=addlyr]").attr("checked", false);
		},
		btnIconChange : function(){
			if(TITLE == "시스템 기본설정"){
				$("#lyr_btn1").attr("src", "./img/btn2.png").attr("onmouseover", "this.src='./img/btn2_over.png'").attr("onmouseout", "this.src='./img/btn2.png'");
				$("#lyr_btn1").attr("title", "관리자가 설정한 스타일로 변경");
				$("#lyr_btn2").show();
				var lyr_btn1 = document.getElementById("lyr_btn1");
				lyr_btn1.onclick = function(){
					_LYR.ctrl.BaseStyleReset();
				};
			}
		},
		BaseStyleReset : function(){
			
			var xml = opener._MAINMAP.getLayerXML();
			var title = TITLE;
			var result = confirm("관리가가 설정한 스타일로 적용 하시겠습니까?");
			
			if(result){
				$.ajax({
					type: "POST",
					url: "/svc",
					data: {svc : "GetCreatedMapStyle", xml:encodeURIComponent(xml)},
					dataType: "text",
					success: function(txt){
						opener.parent.index._MAINMAP.replaceMapCfgXML(txt);
						window.close();
					},
					beforeSend : function(){},
					complete : function(){},
					error: function(xhr, status, error){
						alert("에러");
					}
				});
			}
		},
		//_gmx_style의 지오멕스 초기 스타일을 가지고온다.
		_geoBaseStyleReset : function(){
			
			var result = confirm("시스템에 초기설정된 스타일로 적용 하시겠습니까?");
			
			if(result){
				$.ajax({
					type: "POST",
					url: "/svc",
					data: {svc : "GetBaseStyle"},
					dataType: "text",
					success: function(txt){
						opener.parent.index._MAINMAP.replaceMapCfgXML(txt);
						window.close();
					},
					beforeSend : function(){},
					complete : function(){},
					error: function(xhr, status, error){
						alert("에러");
					}
				});
			}
		}
}