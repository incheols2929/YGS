if(!window.fnb) fnb = {};
var ITEM_ID;

fnb.connect = {
		
		/*//사용목적 null체크 및 시스템진입
		purposeAdd : function(){
		   if(session_id == "" || session_id == null){
				alert("접속권한이 존재 하지 않습니다.\n관리자에게 문의하세요.");
				window.location = "./bookmark.jsp";
			}else{
				       alert("농업기반시스템입니다");
						var pop = window.open("../facility/main.jsp", "_blank"); //시스템 진입
			}	
		},
		
		//사용목적 null체크 및 시스템진입
		purposeAdd2 : function(){
		   if(session_id == "" || session_id == null){
			   alert("접속권한이 존재 하지 않습니다.\n관리자에게 문의하세요.");
				window.location = "./bookmark.jsp";
			}else{
				alert("행정지원시스템시스템입니다");		
				var pop = window.open("../intra/main.jsp", "_blank"); //시스템 진입
			}	
		},*/
		//사용목적 null체크 및 시스템진입
		purposeAdd : function(){
			var inputValue = $("#input_purpose").val();
			
			if(inputValue == null || inputValue == ""){
				alert("사용목적을 입력해주세요!");
			}else if(dept == "" || dept == null){
				alert("부서코드가 존재하지않아 시스템에 진입할 수 없습니다!\n부서코드를 입력해 주세요!");
				fnb.connect.deptChangePOP();
			}else{
				$.ajax({
					type:"POST",
					url:"./XML/use_purposeCheck.jsp",
					data: {
						values : inputValue
					},
					success: function(msg){
						/*if(navigator.appName == 'Netscape'){
							var width = window.screen.availWidth;
							var height = window.screen.availHeight;
							var pop = window.open("../intra/main.jsp", ":: 철원군 행정업무지원 공간정보시스템 ::", 'height='+ height +' ,width=' + width + ',menubar=no, scrollbars=yes, status=no, resizable=yes');
							pop.focus();
						}else{
							var width = window.screen.availWidth;
							var height = window.screen.availHeight;
							var pop = window.open("../intra/main.jsp", "", 'height='+ height +' ,width=' + width + ',menubar=no, scrollbars=yes, status=no, resizable=yes');
							pop.focus();
						}*/
						var pop = window.open("../facility/main.jsp", "_blank"); //시스템 진입
					},
					error: function(xhr, status, error) {
						//alert(xhr.status + ">>>" + error);
					}
				});
			}	
		},
		
		//사용목적 쿠키체크 및 사용
		cookieCheck : function(){
			$.ajax({
				type:"POST",
				url:"./XML/use_cookies.jsp",
				dataType : "xml",
				success: function(xml){
					$("#input_purpose").val("");
					$("#input_purpose").val($(xml).find("cookie").text());
				},
				error: function(xhr, status, error) {
					//alert(xhr.status + ">>>" + error);
				}
			});
		},
		
		//즐겨찾기 리스트 가져오기
		favoritescreate : function(){

			$.ajax({
				type:"POST",
				url:"./XML/favoriteslist.jsp",
				dataType : "xml",
				success: function(xml){
					var str= "";
					if($(xml).find("favorites").length != 0){
		
						$(xml).find("favorites").each(function(idx, item){
							var dname = $(this).find("dforder").find("name").text();
							var did = $(this).find("dforder").find("id").text();
							var linkitemid = $(this).find("dforder").find("linkitemid").text();
		
							str+="<div class='d_forder' id='" + did + "'>";
							str+="<div class='forder_name'>";
							str+="	<div style='float:left;'><p style='padding:4px;'><img src='img/bookmark/folder_img.png'/></p></div>";
							str+="	<div style='float:left; border:0px solid #000000; text-overflow:ellipsis; overflow:hidden; width:150px;'><p style='padding:6px 6px 6px 0;' class='p_style'><b>" + dname + "</b></p></div>";
							str+="	<div style='float:right;'><p style='padding:6px 0 6px 0;'><img src='img/bookmark/plus.png' onclick='fnb.connect.showItemPOP(\""+linkitemid+"\");' title='사이트추가'/> <img src='img/bookmark/minus.png' onclick='fnb.connect.removeFolder(\""+did+"\");' title='폴더삭제'/></p></div>";
							str+="</div>";
							str+="<div class='linkbox' id='" + linkitemid + "'>";
							
							if($(this).find("sforder").length != 0){
								$(this).find("sforder").each(function(idx, item){
									var url  = $(this).find("url").text();
									var sitename = $(this).find("sitename").text(); 
									var id = $(this).find("id").text(); 
			
									str+="	<div id='" + id + "'>";
									str+="		<div class='sitename'>";
									str+="			<p style='padding:0 0 0 20px;'><a id='urlpath' target='_blank' href='" + url + "'>" + sitename + "</a></p>";
									str+="			<div style='position:absolute; top: 0px; right: 1px;'><img src='img/bookmark/minus.png' onclick='fnb.connect.removeItem(\""+id+"\")'/></div>";
									str+="		</div>";
									str+="	</div>";
									
								});	
							}
							str+="</div>";
							str+="</div>";
						});
						
						$("#enjoysch").html(str);
						fnb.connect.initsortable();
						//사용자의 즐겨찾기가 없을 경우
						if($(xml).find("user").text() == "true"){
							//fnb.connect.initsortable();
							setTimeout("fnb.connect.createXML('insert')", 500);
						}
						
					}else{
						var pstr = "";
						pstr +="<div id='favornone' style='display:block; position:absolute; top:50%; left:50%; background-color:#f3f5f9; text-align:center; margin:-30px 0 0 -130px; width:260px; height:35px; border:2px solid #009e96;'>";
						pstr +="<p style='padding:10px;'>즐겨찾기의 추가버튼을 눌러 등록하여 주십시오!</p>";
						pstr +="</div>";
						$("#enjoysch").html(pstr);
					}
				},
				beforeSend : function(){},
				complete : function(){},
				error: function(xhr, status, error) {
						//alert(error);
				}
			});
		},
		//즐겨찾기 드래그 기능 활성화
		initsortable : function(){
			
			$("#enjoysch").sortable({
				axis:"y",
				connectWith: "#enjoysch",
				cursor: 'pointer',
				handle: '.forder_name',
				placeholder: 'placeholder',
				forcePlaceholderSize: true,
				opacity: 0.4,
				stop : function(event, ui){
					fnb.connect.createXML("edit");//XML을 생성
				}
				}).disableSelection();
			
			$(".linkbox").sortable({
				axis:"y",
				connectWith: ".linkbox",
				cursor: 'pointer',
				handle: '.sitename',
				placeholder: 'placeholder',
				forcePlaceholderSize: true,
				opacity: 0.4,
				stop : function(event, ui){
					fnb.connect.createXML("edit");
				}
				}).disableSelection();
			
		},
		//업무기능 리스트를 가져온다.
		workdrag : function(){
			
		},
		//2012-03-16일 수정
		workBtnClick : function(obj){
			
			var urlpath = $("#"+obj).find("#url_"+obj).text();
			var w = screen.availWidth+1000;
			var h = screen.availHeight+1000;
			
			var jre = deployJava.getJREs();
			
			
			if(jre != ""){
				if(urlpath != "N"){
					POP = window.open(urlpath, "pop", "top=0, left=0, width=" + w + ", height=" + h + ",  status=no, resizable=yes, location=no, toolbar=no, menubar=no");
				}else{
					alert("사용 권한을 가지고 있지 않습니다.");
				}
			}else{
				 userInput = confirm("사용자님의 PC에 자바가 설치되어 있지 않습니다.\r\n지금 바로 설치 하시겠습니까?\r\n(* 설치완료 후 시스템을 선택하여 주십시오!)");
			        if (userInput == true) {
			            deployJava.returnPage = location.href;
			            deployJava.installLatestJRE();
			        	//location.href = "img/file/jre-6u33-windows-i586.exe";
			     }
			}
			
			//자바가 설치 되었는지를 검사한다.
		    /*if (deployJava.versionCheck('1.6.0_30+') == false) {
		        userInput = confirm("사용자님의 PC에 자바가 설치되어 있지 않습니다.\r\n지금 바로 설치 하시겠습니까?\r\n(* 설치완료 후 시스템을 선택하여 주십시오!)");
		        if (userInput == true) {
		            deployJava.returnPage = location.href;
		            deployJava.installLatestJRE();
		        } 
		    }else{
		    	if(urlpath != "N"){
					POP = window.open(urlpath, "pop", "top=0, left=0, width=" + w + ", height=" + h + ",  status=no, resizable=yes, location=no, toolbar=no, menubar=no");
				}else{
					alert("사용 권한을 가지고 있지 않습니다.");
				}
		    }*/
			
		},
		//업무기능을 업데이트한다.(드래그시)
		workupdate : function(objsort){
			$.ajax({
				type:"POST",
				url:"./XML/workedit.jsp",
				dataType : "xml",
				data : {sortnum:objsort},
				success: function(xml){
					var val = $(xml).find("val").text();
					if(val == "success"){
						//alert("갱신");	
					}else{
						alert("수정을 하지 못 하였습니다.");	
					}	
				},
				error: function(xhr, status, error) {
						//alert("에러");
				}
			});
		},
		DIVMOVE : function(){
			var rd = REDIPS.drag;
			rd.init();
			//rd.mark.exception.d8 = 'smile';
			rd.drop_option = 'switching';
			fnb.connect.tooltips(); //tooltip생성
		},
		// XML을 생성한다.
		createXML : function(obj){
			
			try{
				var str = "";
				str+="<data>\r\n";
				$(".d_forder").each(function(didx){
					var columnId = $(this).attr('id');
					var fordername =  $(this).find('.p_style').text(); 
					
				str+="	<favorites>\r\n";
				str+="		<dforder>\r\n";
				str+="			<name>" + fordername + "</name>\r\n";
				str+="			<id>" + columnId + "</id>\r\n";
				str+="			<linkitemid>linkitem_" + (didx+1) + "</linkitemid>\r\n";
				str+="		</dforder>\r\n";
					$(this).find(".linkbox").each(function(sidx){
						var itemorder = $(this).sortable('toArray');
						var splitni = itemorder.toString().split(",");
						if(itemorder.toString() != ""){
							for(var i=0; i<splitni.length; i++){
								var sfoderid = splitni[i];
								var sitename =  $(this).find("#"+splitni[i]).find('.sitename').text(); 
								var url =  $(this).find("#"+splitni[i]).find('#urlpath').attr("href"); //url
					str+="		<sforder>\r\n";
					str+="			<id>sfoderid_"+ (didx+1) +"_"+(i+1)+"</id>\r\n";
					str+="			<sitename>" + sitename + "</sitename>\r\n";
					str+="			<url>" + url + "</url>\r\n";
					str+="		</sforder>\r\n";
							}
						}
					});
				str+="	</favorites>\r\n";
				});
				str+="</data>";
				
			}catch(err){
				//alert(">>>>>>>>>>>>>>> " + err);
			}
			
			//alert(str);			
			//// 생성한 XML을  insert또는 업데이트 한다.
			
			var url_path = "";
			
			if(obj == "edit"){
				url_path = "./XML/favoritesedit.jsp";
			}else if(obj == "insert"){
				url_path = "./XML/favoritesinsert.jsp";
			}
			
			$.ajax({
				type:"POST",
				url:url_path,
				dataType : "xml",
				data : {createxml:str},
				success: function(xml){
					var val = $(xml).find("val").text();
					if(val == "success"){
						//alert("갱신");	
					}else{
						alert("수정을 하지 못 하였습니다.");	
					}	
				},
				error: function(xhr, status, error) {
						//alert("에러");
				}
			});
			
		},
		//폴더를생성하기 위한 팝업을 연다. 
		showPOP : function(){
			$('#folder_create').bPopup({
				fadeSpeed:'slow', 
				modalColor:'gray', 
				scrollBar:false,
				opacity:0.3	
			});
		},
		//폴더를 생성한다.
		createForder : function(){
			
						
			var forder_cnt = $(".d_forder").length;
			var pNum = (forder_cnt+1);
			var forder_name = $("#forder_name").val();
			
			if(forder_name == ""){ //폴더명이 공백이면
	    		 alert("폴더명을 입력하여 주십시오!");//이 메시지가 뜸 
	    		 $("#forder_name").focus();
	    		 return false;
	    	 }
			
			
			var change = "";
			var str = "";
			//스타일정의
			var style1 = "border:0px solid #000000; margin-top:2px;";
			var style2 = "border-left:1px solid #eaeaea;";
			var style3 = "margin:3px 0px 0px 0px;background:#fff;border:1px solid #ffffff;-moz-border-radius:3px;-webkit-border-radius:3px;";
			
			str += "<div class='d_forder' id='d_forder" + pNum + "' >";
			str += "	<div class='forder_name'>";
			str += "		<div style='float:left;'><p style='padding:4px;'><img src='img/bookmark/folder_img.png'/></p></div>";
			str += "		<div style='float:left; border:0px solid #000000; text-overflow:ellipsis; overflow:hidden; width:150px;'><p style='padding:6px 6px 6px 0;' class='p_style'><b>" + forder_name + "</b></div>";
			str += "		<div style='float:right;'><p style='padding:6px 0 6px 0;'><img src='img/bookmark/plus.png' onclick='fnb.connect.showItemPOP(\"linkitem_"+pNum+"\");'/> <img src='img/bookmakr/minus.png' onclick='fnb.connect.removeFolder(\"d_forder"+pNum+"\");'/></div>";
			str += "	</div>";
			str += "	<div class='linkbox' id='linkitem_"+pNum+"'></div>";
			str += "</div>";
			$("#favornone").hide();
			$("#enjoysch").append(str);
			fnb.connect.initsortable(); //소트테이블에 등록시킨다.
			if(forder_cnt != 0){ 
				fnb.connect.createXML("edit");
			}else{
				fnb.connect.createXML("insert");
			}
			$("#forder_name").val("");
			$('#folder_create').bPopup().close();
			//fnb.connect.favoritescreate();
		},
		//아이템을만들기위한 팝업을 연다.
		showItemPOP : function(objid){			
			ITEM_ID = objid;
			$('#item_create').bPopup({
				fadeSpeed:'slow', 
				modalColor:'gray', 
				scrollBar:false,
				opacity:0.3	
			});
		},
		//아이템을 생성한다.
		createItems : function(){
			
			var item_cnt = $("#"+ITEM_ID).find('.sitename').length;
			var iNum = (item_cnt+1);
			var sitename = $("#sitename").val();
			var urlname = $("#urlname").val();
			
			
			if(sitename == ""){
	    		 alert("사이트명을 입력하여 주십시오!");
	    		 $("#sitename").focus();
	    		 return false;
	    	 }
			
			if(urlname == "" || urlname == "http://"){
	    		 alert("URL명을 입력하여 주십시오!");
	    		 $("#urlname").focus();
	    		 return false;
	    	 }
			
			var str = "";

			var style = "margin:0;font-size:12px;padding:5px;background:#f0f0f0;color:#000;border-bottom:1px solid #dedddd;font-family:Verdana;cursor:pointer;position: relative;";
			
			str+="	<div id='sfoderid_"+ (item_cnt+1) +"'>";
			str+="		<div class='sitename'>";
			str+="		<p style='padding:0 0 0 20px;'><a id='urlpath' target='_blank' href='" + urlname + "'>" + sitename + "</a></p>";
			str+="		<div style='position:absolute; top: 0px; right: 1px;'><img src='img/bookmark/minus.png' onclick='fnb.connect.removeItem(\"sfoderid_"+(item_cnt+1)+"\")'/></div>";
			str+="</div>";
			str+="	</div>";		
			$("#"+ITEM_ID).append(str);
			fnb.connect.initsortable(); //소트테이블에 등록시킨다.
			fnb.connect.createXML("edit");
			
			$("#sitename").val("");
			$("#urlname").val("http://");
			$('#item_create').bPopup().close();
		},
		//해당폴더를 삭제한다.
		removeFolder : function(objid){
			
			var result = confirm("해당 폴더를 삭제 하시겠습니까?");
			if(result){
				$("#"+objid).remove();
				var forder_cnt = $(".d_forder").length;
				if(forder_cnt == 0){
					fnb.connect.deletefavorites();//해당폴더가 없으면 DB를 삭제해 준다.
				}else{
					fnb.connect.initsortable(); //소트테이블에 등록시킨다.
					fnb.connect.createXML("edit"); // 업데이트
				}
			}
		},
		//해당아이템를 삭제한다.
		removeItem : function(objid){
			var result = confirm("해당 사이트를 삭제 하시겠습니까?");
			if(result){
				$("#"+objid).remove();
				fnb.connect.initsortable(); //소트테이블에 등록시킨다.
				fnb.connect.createXML("edit"); // 업데이트
			}
		},
		deletefavorites : function(){
			$.ajax({
				type:"POST",
				url:"./XML/favoritesdel.jsp",
				dataType : "xml",
				//data : {createxml:str},
				success: function(xml){
					var val = $(xml).find("val").text();
					if(val == "success"){
						fnb.connect.favoritescreate();	
					}else{
						alert("수정을 하지 못 하였습니다.");	
					}	
				},
				error: function(xhr, status, error) {
						//alert("에러");
				}
			});
		},
		tooltips : function(){
			
			$(".tTip[title]").qtip({
				style: { name: 'green', tip: true },
				position: { corner:{target:"topRight", tooltip:"bottomLeft"}}
				
			});
	     },
	     downloadContent : function(e, objid){
	    	 $("#"+objid).fadeIn('fast');
	     },
	     downloadHide : function(e, objid){
	    	 $("#"+objid).fadeOut('fast');
	     },
	     systemregon : function(){
	    	 $('#system_create').bPopup({
					fadeSpeed:'slow', 
					modalColor:'gray', 
					scrollBar:false,
					opacity:0.3	
				});
	    	 
	    	 $.ajax({
					type:"POST",
					url:"./XML/workiconlist.jsp",
					dataType : "xml",
					success: function(xml){
						var val = $(xml).find("val").text();
						var icon_box = "";
						if($(xml).find("icon").length != 0){
							 $("#system_imgname").removeOption(/./);
						     $("#system_imgname").addOption("", ":: 선택하여주십시오! ::", true);
							$(xml).find("icon").each(function(idx){
								var iconname = $(this).find("iconname").text();
								var iconreal_name = $(this).find("iconrealname").text();
								$("#system_imgname").addOption(iconreal_name, iconname, false);
							});
						}
					},
					error: function(xhr, status, error) {
							//alert("에러");
					}
				});
	    	 
	    	 
	     },
	     createSystem : function(){
	    	 
	    	 var system_sitename = $("#system_sitename").val();
	    	 var system_urlname = $("#system_urlname").val();
	    	 var system_imgname = $("#system_imgname").val();
	    	 var system_content = $("#system_content").val();
	    	 
	    	 if(system_sitename == ""){
	    		 alert("사이트명을 입력하여 주십시오!");
	    		 $("#system_sitename").focus();
	    		 return false;
	    	 }
	    	 
	    	 if(system_urlname == ""){
	    		 alert("주소명을 입력하여 주십시오!");
	    		 $("#system_urlname").focus();
	    		 return false;
	    	 }
	    	 
	    	 if(system_imgname == ""){
	    		 alert("이미지명을 입력하여 주십시오!");
	    		 $("#system_imgname").focus();
	    		 return false;
	    	 }
	    	 
	    	 if(system_content == ""){
	    		 alert("설명을 입력하여 주십시오!");
	    		 $("#system_content").focus();
	    		 return false;
	    	 }
	    	 
	    	 $.ajax({
					type:"POST",
					url:"./XML/workinsert.jsp",
					dataType : "xml",
					data : {sitename:system_sitename, urlname : system_urlname, imgname : system_imgname, content : system_content},
					success: function(xml){
						var val = $(xml).find("val").text();
						if(val == "success"){							
							//fnb.connect.workdrag();
							setTimeout("location.reload()", 800);
						}else{
							alert("수정을 하지 못 하였습니다.");	
						}	
					},
					error: function(xhr, status, error) {
							//alert("에러");
					}
				});
	    	 
	    	 $('#system_create').bPopup().close();
	     },
	     slide : function(obj){
	    	 fnb.connect.initsortable(); //다시 소트테이블에 정렬 시켜준다.
	    	 $("#"+obj).slideToggle("fast"); 
	     },
	     provide : function(){
	    	 
	    	 $("#cuga_btn[title]").qtip({
					style: { name: 'blue', tip: true },
					position: { corner:{target:"topRight", tooltip:"bottomLeft"}}
			 });
	    	 
	    	 $("#notice_full[title]").qtip({
					style: { name: 'blue', tip: true },
					position: { corner:{target:"topRight", tooltip:"bottomLeft"}}
			 });
	    	 
	    	 $("#member_btn[title]").qtip({
					style: { name: 'blue', tip: true },
					position: { corner:{target:"topLeft", tooltip:"bottomRight"}}
			 });
	     },
	     logout : function(){
	    	 window.location = "logout.jsp";
	     },
	    ////////////////////////////
	   /////부서코드변경 2012-08-07
		deptChangePOP : function(){
			$('#dept_Change_pop').bPopup({
				fadeSpeed:'slow', 
				modalClose: false,
				modalColor:'gray', 
				scrollBar:false,
				opacity:0.3	
			});
		},
		deptnmList : function(){
			var dept_nm = $("#dept_name_text").val();
			
			if(dept_nm == ""){
				alert("변경된 부서명을 입력하여 주십시오!");
				$("#dept_name_text").focus();
				return false;
			}
			
			$.ajax({
				type:"POST",
		    	url: '../inteXML/dept_srch_txt.jsp',
		    	data : {dept_nm : dept_nm},
		    	dataType: 'xml',
		    	success: function(xml){
					var select_box = "";
					if($(xml).find("dept").length != 0){
						$("#dept_cd").removeOption(/./, true);
						//$("#dept_cd").addOption("", ":: 선택 하여 주십시오! ::", true);
						select_box += "<option class='abc' value=''>:: 선택 하여 주십시오! ::</option>";
						$(xml).find("dept").each(function(){
							var code = $(this).find("code").text();
							var selname = $(this).find("name").text();
							select_box += "<option class='abc' value='" + code + "'>" + selname + "</option>";
						});
						$("#dept_cd").html(select_box);
						document.all['dept_cd'].disabled = false;
						//$("#dept_cd").attr("disabled", false);
					}else{
						select_box += "<option class='abc' value=''>:: 선택 하여 주십시오! ::</option>";
						$("#dept_cd").removeOption(/./, true);
						$("#dept_cd").html(select_box);
						document.all['dept_cd'].disabled = true;
						//$("#dept_cd").attr("disabled", true);
						alert("검색하신 부서명이 존재 하지 않습니다.");
					}
				},
		    	error: function(){
		        	//alert('Error loading XML document');
		    	}
			});
		},
		deptUpdateNew : function(){ 
			
			var dept_cd = $("#dept_cd").val();
			var dept_nm = $("#dept_name_text").val();
			
			if(dept_nm == ""){
				alert("변경된 부서명을 입력하여 주십시오!");
				$("#dept_name_text").focus();
				return false;
			}
			
			if(dept_cd == ""){
				alert("부서명을 선택하여 주십시오!");
				$("#dept_cd").focus();
				return false;
			}
			
			$.ajax({
				type:"POST", 
				url:"./XML/deptChange.jsp",
				dataType : "xml",
				data : {dept_cd:dept_cd, cha : "new"},
				success: function(xml){
					var val = $(xml).find("result").text();
					if(val == "success"){							
						alert("정상적으로 처리하였습니다.");
						location.reload();
					}else{
						alert("수정을 하지 못 하였습니다.");	
					}	
				},
				error: function(xhr, status, error) {
						//alert("에러");
				}
			});
		},
		deptUpdateKeep : function(){
			  var result = confirm("현재 부서로 유지 하시겠습니까?");
			  
			  if (result) {
				  $.ajax({
						type:"POST", 
						url:"./XML/deptChange.jsp",
						dataType : "xml",
						data : {cha:"keep"},
						success: function(xml){
							var val = $(xml).find("result").text();
							if(val == "success"){		
								location.reload();
							}else{
								alert("수정을 하지 못 하였습니다.");	
							}	
						},
						error: function(xhr, status, error) {
								//alert("에러");
						}
					});
			  }
		}
		////////////////////////////////////////////////////////////
}