if(!window._MTUSR) _MTUSR = {};
//pw까지 입력 후 엔터를 치면 depcrcd_Check()로 넘어감
$(document).ready(function(){
	$("#usr_pw").keyup(function(e){ //키보드를 눌렀다가 땔 때 이벤트 발생
		if(e.which == 13){ //Enter키는 숫자로 13
			//_MTUSR.login.check();
			_MTUSR.form.deprcd_Check();
		}
	});
	
});

_MTUSR.form = {
		jqfade : function(){
			$("#warning").fadeIn(800); 
			setTimeout("$('#warning').fadeOut(800);", 5000);//말풍선 5초동안 보였다가 사라짐
		},
		//로그인 체크 및 유효성 검사로 이동
		login : function(){
				if($("#usr_id").val() == ""){
					/*$("#message").text("* 아이디를 입력하여 주십시오!");
					this.jqfade();*/
					$("#usr_id").focus().addClass("Warning_Focus");
					return false;	
				}else{
					$("#usr_id").removeClass("Warning_Focus");
				}
				
				if($("#usr_pw").val() == ""){
					/*$("#message").text("* 비밀번호를  입력 하여 주십시오!");
					this.jqfade();*/
					$("#usr_pw").focus().addClass("Warning_Focus");
					return false;	
				}else{
					$("#usr_pw").removeClass("Warning_Focus");
				}
				_MTUSR.login.check(); //로그인체크 및 유효성 검사
		},
		//로그인 버튼 클릭했을 때
		deprcd_Check : function(){
			if($("#usr_id").val() == ""){ //ID 입력하는 부분이 공백이면 다시 입력 할수 있도록 포커스를 ID쪽으로 잡아주고
				/*$("#message").text("* 아이디를 입력하여 주십시오!");
				this.jqfade();*/
				$("#usr_id").removeClass("Warning_Focus").focus().addClass("Warning_Focus");
				return false;	
			}else{ //입력 되어있으면 입력하는 창의 클래스를 지움 
				$("#usr_id").removeClass("Warning_Focus");
			}
			
			if($("#usr_pw").val() == ""){  //PW 입력하는 부분이 공백이면 다시 입력 할수 있도록 포커스를 PW쪽으로 잡아주고
				/*$("#message").text("* 비밀번호를  입력 하여 주십시오!");
				this.jqfade();*/
				$("#usr_pw").removeClass("Warning_Focus").focus().addClass("Warning_Focus");
				return false;	
			}else{ //입력 되어있으면 입력하는 창의 클래스를 지움
				$("#usr_pw").removeClass("Warning_Focus");
			}
			_MTUSR.login.deptcheck(); 	//사용자 로그인 기존사용자(정책결정, 투자유치) 부서코드를 검색하여 존재여부 판단
		},
		//사용자 등록신청
		reg : function(){
			
			if($("#usr_id").val() == ""){
				//$("#message").text("* 아이디를 입력하여 주십시오!");
				//this.jqfade();
				$("#usr_id").focus();
				return false;	
			}
			
			if($("#usr_id").val().length < 6){
				//$("#message").text("* 아이디는 6자리 이상 입력하여 주십시오!");
				//this.jqfade();
				$("#usr_id").focus();
				return false;	
			}
			
			if($("#usr_pw").val() != $("#usr_pw_re").val()){
				//$("#message").text("* 패스워드가 같지 않습니다.");
				//this.jqfade();
				$("#usr_pw").val("");
				$("#usr_pw_re").val("");
				$("#usr_pw").focus();
				return false;	
			}
			
			if($("#usr_pw").val().length < 6){
				//$("#message").text("* 패스워드는 6자리 이상 입력하여 주십시오!");
				//this.jqfade();
				$("#usr_pw").val("");
				$("#usr_pw_re").val("");
				$("#usr_pw").focus();
				return false;	
			}
			
			if($("#usr_nm").val() == ""){
				//$("#message").text("* 사용자명을 입력하여 주십시오!");
				//this.jqfade();
				$("#usr_nm").focus();
				return false;	
			}
			
			if($("#dept_nm").val() == ""){
				//$("#message").text("* 부서를 선택하여 주십시오!");
				//this.jqfade();
				$("#dept_nm").focus();
				return false;	
			}
			
			if($("#tel_num").val() == ""){
				//$("#message").text("* 연락처를 입력하여 주십시오!");
				//this.jqfade();
				$("#tel_num").focus();
				return false;	
			}
			
			var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;
			
			if(filter.test($('#email').val()) != true){
				//$("#message").text("* 이메일 형식이 아닙니다!");
				//this.jqfade();
				$('#email').focus();
				return false;
			}
			
			_MTUSR.reg.appl();
		},
		//계정문의
		acc : function(){
			if($("#usr_id").val() == ""){
				//$("#message").text("* 아이디를 입력하여 주십시오!");
				//this.jqfade();
				$("#usr_id").focus();
				return false;	
			}
			
			if($("#usr_nm").val() == ""){
				//$("#message").text("* 사용자명을 입력하여 주십시오!");
				//this.jqfade();
				$("#usr_nm").focus();
				return false;	
			}
			
			if($("#dept_nm").val() == ""){
				//$("#message").text("* 부서를 선택하여 주십시오!");
				//this.jqfade();
				$("#dept_cd").focus();
				return false;	
			}
			
			if($("#tel_num").val() == ""){
				//$("#message").text("* 연락처를 입력하여 주십시오!");
				//this.jqfade();
				$("#tel_num").focus();
				return false;	
			}
			
			var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;
			
			if(filter.test($('#email').val()) != true){
				//$("#message").text("* 이메일 형식이 아닙니다!");
				//this.jqfade();
				$('#email').focus();
				return false;
			}
			
			_MTUSR.acc.inquiry();
			
		},
		edit : function(){
			
			if($("#usr_pw").val() == ""){
				//$("#message").text("* 패스워드를 입력하여 주십시오!");
				//this.jqfade();
				$("#usr_pw").focus();
				return false;	
			}
			
			if($("#usr_pw").val() != $("#usr_pw_re").val()){
				//$("#message").text("* 패스워드가 같지 않습니다.");
				//this.jqfade();
				$("#usr_pw").val("");
				$("#usr_pw_re").val("");
				$("#usr_pw").focus();
				return false;	
			}
			
			if($("#usr_nm").val() == ""){
				//$("#message").text("* 사용자명을 입력하여 주십시오!");
				//this.jqfade();
				$("#usr_nm").focus();
				return false;	
			}
			
			if($("#tel_num").val() == ""){
				//$("#message").text("* 연락처를 입력하여 주십시오!");
				//this.jqfade();
				$("#tel_num").focus();
				return false;	
			}
			
			var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;
			
			if(filter.test($('#email').val()) != true){
				//$("#message").text("* 이메일 형식이 아닙니다!");
				//this.jqfade();
				$('#email').focus();
				return false;
			}
			_MTUSR.UE.edit();
		},
		reset : function(){
			$("input").val("");
			//$("#usr_nm").val("");
			//$("#dept_nm").val("");
			//$("#tel_num").val("");
			//$("#email").val("");
		},
		//부서코드를가져온다. 대분류
		deptcode : function(){
			
			$.ajax({
		    	url: '../../inteXML/deptXML.jsp',
		    	dataType: 'xml',
		    	success: function(xml){
					var select_box = "";
					$(xml).find("dept").each(function(){
						var code = $(this).find("code").text();
						var selname = $(this).find("name").text();
						select_box += "<option value='" + code + "'>" + selname + "</option>";
					});
					$("#dept_cd").append(select_box);	
				},
		    	error: function(){
		        	//alert('Error loading XML document');
		    	}
			});
		},
		//중분류
		deptcodeMiddle : function(obj){
		
			 $("#dept_cd2").removeOption(/./);
			 $("#dept_cd2").addOption("", ":: 선택 하여 주십시오! ::", true);
			 $("#dept_cd3").removeOption(/./);
			 $("#dept_cd3").addOption("", ":: 선택 하여 주십시오! ::", true);
			
			var deptArr = new Array(14);
			var deptYN = "";
			// 소방서와 도립대학 부서코드
			deptArr[0] = "6420116";
			deptArr[1] = "6420127";
			deptArr[2] = "6420138";
			deptArr[3] = "6420146";
			deptArr[4] = "6420154";
			deptArr[5] = "6420164";
			deptArr[6] = "6420173";
			deptArr[7] = "6420181";
			deptArr[8] = "6420188";
			deptArr[9] = "6420392";
			deptArr[10] = "6420432";
			deptArr[11] = "6420599";
			deptArr[12] = "6420627";
			deptArr[13] = "6420553";

			for(var i=0; i<deptArr.length; i++){
				if(deptArr[i] == obj){
					deptYN = "N";
					break;
				}else{
					deptYN = "Y";
				}
			}

			if(deptYN == "Y"){
		
				$.ajax({
					type:"POST",
			    	url: '../../inteXML/deptMiXML.jsp',
			    	data : {dept_cd : obj},
			    	dataType: 'xml',
			    	success: function(xml){
						var select_box = "";
						if($(xml).find("dept").length != 0){
							$(xml).find("dept").each(function(){
								var code = $(this).find("code").text();
								var selname = $(this).find("name").text();
								select_box += "<option value='" + code + "'>" + selname + "</option>";
							});
							$("#dept_cd2").append(select_box);
							
							document.all['dept_cd2'].disabled = false;
							document.all['dept_cd3'].disabled = false;
							//$("#dept_cd2").attr("disabled", "");
							//$("#dept_cd3").attr("disabled", "");
						}else{
							document.all['dept_cd2'].disabled = true;
							document.all['dept_cd3'].disabled = true;
							//$("#dept_cd2").attr("disabled", "disabled");
							//$("#dept_cd3").attr("disabled", "disabled");
						}
					},
			    	error: function(){
			        	//alert('Error loading XML document');
			    	}
				});
			}else{
				document.all['dept_cd2'].disabled = true;
				document.all['dept_cd3'].disabled = true;
				//$("#dept_cd2").attr("disabled", "disabled");
				//$("#dept_cd3").attr("disabled", "disabled");
			}	
		 
		},
		//소분류
		deptcodeBottom : function(obj){
			
			$("#dept_cd3").removeOption(/./);
			$("#dept_cd3").addOption("", ":: 선택 하여 주십시오! ::", true);
			
			$.ajax({
				type:"POST",
		    	url: '../../inteXML/deptBoXML.jsp',
		    	data : {dept_cd : obj},
		    	dataType: 'xml',
		    	success: function(xml){
					var select_box = "";
					if($(xml).find("dept").length != 0){
						$(xml).find("dept").each(function(){
							var code = $(this).find("code").text();
							var selname = $(this).find("name").text();
							select_box += "<option value='" + code + "'>" + selname + "</option>";
						});
						$("#dept_cd3").append(select_box);
						//$("#dept_cd3").attr("disabled", false);
						document.all['dept_cd3'].disabled = false;
					}else{
						document.all['dept_cd3'].disabled = true;
						//$("#dept_cd3").attr("disabled", true);
					}
				},
		    	error: function(){
		        	//alert('Error loading XML document');
		    	}
			});
		},
		
		//부서정보를 like검색으로 정보를 가지고온다.
		
		deptLikeSrch : function(){
			
			var dept_nm = $("#dept_nm").val();
			
			if(dept_nm == ""){
				alert("부서명을 입력하여 주십시오!");
				$("#dept_nm").focus();
				return false;
			}
			
		 $.ajax({
				type:"POST",
		    	url: '/inteXML/dept_srch_txt.jsp',
		    	data : {dept_nm : dept_nm},
		    	dataType: 'xml',
		    	success: function(xml){
					var select_box = "";
					if($(xml).find("dept").length != 0){
						$("#dept_cd").removeOption(/./, true);
						$("#dept_cd").addOption("", ":: 선택 하여 주십시오! ::", true);///초기화시 기본 ""빈공란값추가
						select_box += "<option class='abc' value=''>:: 선택 하여 주십시오! ::</option>";
						$(xml).find("dept").each(function(){ //dept태그의 총개수만큼 실행문실행
							var code = $(this).find("code").text();//dept태그만큼 함수 내의 실행문이 실행될때마다$this에는 dept태그가 순차적으로 선택 그리고 code태그에 텍스트를 변수code에 할당
							var selname = $(this).find("name").text(); //dept태그만큼 함수 내의 실행문이 실행될때마다$this에는 dept태그가 순차적으로 선택 그리고 selename태그에 텍스트를 변수code에 할당
						   	select_box += "<option class='abc' value='" + code + "'>" + selname + "</option>";
						});
					//	alert(select_box);///여기서 강원도 데이터를 가져오고있음
						$("#dept_cd").html(select_box);
					 document.all['dept_cd'].disabled = false;
					    
					}else{
						
						select_box += "<option class='abc' value=''>:: 선택 하여 주십시오! ::</option>";
						$("#dept_cd").removeOption(/./, true);
					    $("#dept_cd").html(select_box);
						document.all['dept_cd'].disabled = true;//비활성화 시켜놓음
						alert("검색하신 부서명이 존재 하지 않습니다.!!");
						
					}
				},
		    	error: function(){
		        	//alert('Error loading XML document');
		    	}
			});
		
		}
};

/*
 * 로그인 화면 스크립트
 */
_MTUSR.login = {
		//로그인체크 및 유효성 검사
		check : function(){
	
			var p_usr_id = $("#usr_id").val(); 
			var p_usr_pw = Base64.encode($("#usr_pw").val());

			$.ajax({
				type:"POST",
				url:"./user/XML/login_check.jsp",
				data : {usr_id : p_usr_id, usr_pw : p_usr_pw},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("loginresult").length != 0){
						if($(xml).find("loginresult").text() == "success"){ //로그인 정보가 맞으면
							window.location = "../login/bookmark.jsp"; //bookmark.jsp로 이동
						}else if($(xml).find("loginresult").text() == "no"){ //정보가 없으면
							alert("* 관리자의 승인이 이루어 지지 않았습니다.");
						}
					}
				},
				beforeSend : function(){},
				complete : function(){},
				error: function(xhr, status, error) {
					//alert(error);
				}
			});
		},
		//사용자 로그인 기존사용자(정책결정, 투자유치) 부서코드를 검색하여 존재여부 판단
		//2012-01-25
		deptcheck : function(){
			
			var p_usr_id = $("#usr_id").val(); 
			var p_usr_pw = Base64.encode($("#usr_pw").val());

			$.ajax({
				type:"POST",
				url:"./user/XML/dept_check.jsp",
				data : {usr_id : p_usr_id, usr_pw : p_usr_pw},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("deptchk").length != 0){
						if($(xml).find("deptchk").text() == "success"){
							_MTUSR.login.check(); //로그인체크 및 유효성 검사
						}else if($(xml).find("deptchk").text() == "no"){
							alert("로그인 할 수 없습니다.\n관리자에게 문의하여 주십시오.");
						}else if($(xml).find("deptchk").text() == "id_fail"){//id다르면
							alert("계정을 다시한번 확인해 주세요.");//이게 출력
						}else if($(xml).find("deptchk").text() == "pw_fail"){//pw다르면
							alert("비밀번호를 다시한번 확인해 주세요.");//이게 출력
						}
					}
				},
				beforeSend : function(){},
				complete : function(){},
				error: function(xhr, status, error) {
					//alert(error);
				}
			});
		}
};

/*
 * 사용자등록신청
 */
_MTUSR.reg = {
		appl : function(){
	
			var p_usr_id = $("#usr_id").val();
			var p_usr_nm = $("#usr_nm").val();
			var p_usr_pw = Base64.encode($("#usr_pw").val());
			
			var p_dept_cd = $("#dept_cd").val();
			//var p_dept_cd2 = $("#dept_cd2").val();
			//var p_dept_cd3 = $("#dept_cd3").val();
			
			var p_tel_num = $("#tel_num").val();
			var p_email = $("#email").val();
			//var dept_chk = "";
			
			/*if(p_dept_cd3 == ""){
				if(p_dept_cd2 == ""){
					dept_chk = p_dept_cd;
				}else{
					dept_chk = p_dept_cd2;
				}
			}else{
				dept_chk = p_dept_cd3;
			}*/
		
			//alert(p_dept_cd + ", " + p_dept_cd2 + ", " + p_dept_cd3 + ", " + dept_chk);

			$.ajax({
				type:"POST",
				url:"../user/XML/user_regXML2.jsp",
				data : {usr_id : p_usr_id, usr_nm : p_usr_nm, usr_pw : p_usr_pw, dept_cd: p_dept_cd, tel_num : p_tel_num, email : p_email},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("result").length != 0){
						if($(xml).find("result").text() == "success"){
							alert("등록신청을 하였습니다.\n관리자의 승인이 이루어져야 사용하실수 있습니다. 승인을 위해 관리자에게 연락주시기 바랍니다.\n");
							window.location = "../login.jsp"; //사용자 등록신청 완료후 첫 login화면으로 돌아감
						}else if($(xml).find("result").text() == "fail"){
							alert("등록신청에 실패 하였습니다.\n다시 입력하여 주십시오!");
						}else if($(xml).find("result").text() == "div_same"){
							alert("기존시스템에 등록된 사용자 입니다.\r기존의 아이디로 접속하시거나 담당자에게 문의하여 주십시오!");
							window.location = "../login.jsp";
						}else if($(xml).find("result").text() == "id_same"){
							alert("같은 아이디가 존재 합니다.");
							$("#usr_id").val("");
							$("#usr_id").focus();
						}
					}
				},
				beforeSend : function(){},
				complete : function(){},
				error: function(xhr, status, error) {
					//alert(error);
				}
			});
		}
}

/*
 * 사용자계정문의
 */
_MTUSR.acc = {
		inquiry : function(){
	
			var p_usr_id = $("#usr_id").val();
			var p_usr_nm = $("#usr_nm").val();
			var p_dept_cd = $("#dept_cd").val();
			//var p_dept_cd2 = $("#dept_cd2").val();
			//var p_dept_cd3 = $("#dept_cd3").val();
			var p_tel_num = $("#tel_num").val();
			var p_email = $("#email").val();
			
			//var dept_chk = "";
			
			/*if(p_dept_cd3 == ""){
				if(p_dept_cd2 == ""){
					dept_chk = p_dept_cd;
				}else{
					dept_chk = p_dept_cd2;
				}
			}else{
				dept_chk = p_dept_cd3;
			}*/
			
			$.ajax({
				type:"POST",
				url:"../user/XML/user_accXML.jsp",
				data : {usr_id : p_usr_id, usr_nm : p_usr_nm, dept_cd: p_dept_cd, tel_num : p_tel_num, email : p_email},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("result").attr("id") == "success"){
						$("#acc_display1").hide("fade");
						$("#sp_id").text($(xml).find("result").find("id").text());
						$("#sp_pw").text($(xml).find("result").find("pw").text());
						$("#acc_display2").show("fade");
					}else{
						alert("검색하신 조건에 맞지 않습니다. 다시 한번 확인 바랍니다.");
					}
				},
				beforeSend : function(){},
				complete : function(){},
				error: function(xhr, status, error) {
					//alert(error);
				}
			});
		}
}
/*
 * 사용자정보수정
 */
_MTUSR.UE = {
		
		selinfo : function(obj){
			$.ajax({
				type:"POST",
				url:"../user/XML/user_selinfoXML.jsp",
				data : {usr_id : obj},
				dataType : "xml",
				success: function(xml){
					var xmlFo = $(xml).find("result");
					var usr_id = xmlFo.find("usr_id").text();  
					var usr_nm = xmlFo.find("usr_nm").text(); 
					var dept_cd  = xmlFo.find("dept_cd").text();
					var tel_num = xmlFo.find("tel_num").text(); 
					var email = xmlFo.find("email").text(); 
					var usr_pw = xmlFo.find("usr_pw").text();
					
					$("#usr_id").val(usr_id);
					$("#usr_nm").val(usr_nm);
					$("#dept_cd").val(dept_cd);
					$("#tel_num").val(tel_num);
					$("#email").val(email);
					$("#usr_pw").val(usr_pw);
					$("#usr_pw_re").val(usr_pw);

				},
				beforeSend : function(){},
				complete : function(){},
				error: function(xhr, status, error) {
					//alert(error);
				}
			});
		},
		edit : function(){
			
			var p_usr_id = $("#usr_id").val();
			var p_usr_nm = $("#usr_nm").val();
			var p_usr_pw = $("#usr_pw").val();
			var p_tel_num = $("#tel_num").val();
			var p_email = $("#email").val();
			
			$.ajax({
				type:"POST",
				url:"../user/XML/user_updateXML.jsp",
				data : {usr_id : p_usr_id, usr_pw : p_usr_pw, usr_nm : p_usr_nm, tel_num : p_tel_num, email : p_email},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("result").text() == "success"){
						alert("성공적으로 등록하였습니다.");
						window.location = "../bookmark.jsp";
					}else{
						alert("사용자 정보수정에 실패 하였습니다.");
					}
				},
				beforeSend : function(){},
				complete : function(){},
				error: function(xhr, status, error) {
					//alert(error);
				}
			});
		},
		deptedit : function(){
			
			var p_usr_id = $("#usr_id").val();
			var p_usr_nm = $("#usr_nm").val();
			var p_usr_pw = $("#usr_pw").val();
			var p_dept_cd = $("#dept_cd").val();
			var p_tel_num = $("#tel_num").val();
			var p_email = $("#email").val();
			
			
			if($("#usr_id").val() == ""){
				$("#message").text("* 아이디를 입력하여 주십시오!");
				_MTUSR.form.jqfade();
				$("#usr_id").focus();
				return false;	
			}
			
			
			if($("#usr_nm").val() == ""){
				$("#message").text("* 사용자명을 입력하여 주십시오!");
				_MTUSR.form.jqfade();
				$("#usr_nm").focus();
				return false;	
			}
			
			if($("#usr_pw").val() != $("#usr_pw_re").val()){
				$("#message").text("* 패스워드가 같지 않습니다.");
				_MTUSR.form.jqfade();
				$("#usr_pw").val("");
				$("#usr_pw_re").val("");
				$("#usr_pw").focus();
				return false;	
			}
			
			if($("#usr_pw").val().length < 6){
				$("#message").text("* 패스워드는 6자리 이상 입력하여 주십시오!");
				_MTUSR.form.jqfade();
				$("#usr_pw").val("");
				$("#usr_pw_re").val("");
				$("#usr_pw").focus();
				return false;	
			}

			
			if($("#dept_cd").val() == ""){
				$("#message").text("* 부서를 선택하여 주십시오!");
				_MTUSR.form.jqfade();
				$("#dept_cd").focus();
				return false;	
			}
			
			if($("#tel_num").val() == ""){
				$("#message").text("* 연락처를 입력하여 주십시오!");
				_MTUSR.form.jqfade();
				$("#tel_num").focus();
				return false;	
			}
			
			
			var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;
			
			if(filter.test($('#email').val()) != true){
				$("#message").text("* 이메일 형식이 아닙니다!");
				_MTUSR.form.jqfade();
				$('#email').focus();
				return false;
			}
			
			
			$.ajax({
				type:"POST",
				url:"../user/XML/user_deptupdateXML.jsp",
				data : {usr_id : p_usr_id, usr_pw : p_usr_pw, usr_nm : p_usr_nm, tel_num : p_tel_num, email : p_email, dept_cd : p_dept_cd},
				dataType : "xml",
				success: function(xml){
					if($(xml).find("result").text() == "success"){
						alert("정보수정을 하였습니다.\n이시스템은 관리자의 승인이 이루어져야 사용하실수 있습니다.");
						window.location = "../login.jsp";
					}else{
						alert("사용자 정보수정에 실패 하였습니다.");
					}
				},
				beforeSend : function(){},
				complete : function(){},
				error: function(xhr, status, error) {
					//alert(error);
				}
			});
		}
		
}