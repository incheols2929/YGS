var gwsUtils = {};

gwsUtils = {
		//권한체크
	checkAuth : function(auth, elem){
		var bol = true;
		
		$.ajax({
			type: 'POST',
	    	url: '/login/XML/authCheck.jsp',//데이터 전송 및 요청할 주소
	    	data: {
	    		"auth" : auth //전송할 데이터
	    	},
	    	async: false,
	    	success: function(xml){ //전송 및 요청한 데이터를 정상적으로 가져왔오면
	    		if($(xml).find("auth").text() != "Y"){//요청한xml데이터에서 <auth>태그의 값이 Y와 같지 않으면
	    			if(elem != null){
	    				$(elem).attr("onclick", "alert('해당 기능에 대한 권한이 없습니다.');");
	    			}else{
	    				bol = false;
	    			}
	    		}
			},
	    	error: function(){
	    		
	    	}
		});
		return bol;
	},
	/*
	 * 3자리 마다 콤마를 삽입한다.
	 * @param 문자열
	 * @return 콤마삽입된 문자열
	 */
	setComma : function(str){
        var pattern = /(-?[0-9]+)([0-9]{3})/;
        while(pattern.test(str)) {                    
        	str = str.replace(pattern,"$1,$2");
        }
        return str;
	},
	/*
	 * 콤마를 제거한다.
	 * @param 문자열
	 * @return 콤마제거된 문자열
	 */
	removeComma : function(str){
		return (str.replace(/\,/g,""));
	},
	/*
	 * 프로그레스바를 활성화한다.
	 * @param element id
	 */
	showLoading : function(id){
		var opts = {
				  lines: 13, // The number of lines to draw
				  length: 15, // The length of each line
				  width: 6, // The line thickness
				  radius: 10, // The radius of the inner circle
				  corners: 1, // Corner roundness (0..1)
				  rotate: 0, // The rotation offset
				  direction: 1, // 1: clockwise, -1: counterclockwise
				  color: '#000', // #rgb or #rrggbb or array of colors
				  speed: 1, // Rounds per second
				  trail: 60, // Afterglow percentage
				  shadow: true, // Whether to render a shadow
				  hwaccel: false, // Whether to use hardware acceleration
				  className: 'spinner', // The CSS class to assign to the spinner
				  zIndex: 2e9, // The z-index (defaults to 2000000000)
				  top: '50%', // Top position relative to parent in px
				  left: '50%' // Left position relative to parent in px
				};
		var target = $(id);
		var spinner = new Spinner(opts).spin(target);
			target.append(spinner.el);
	},
	/*
	 * 프로그레스바를 비활성화 한다.
	 */
	hideLoading : function(){
		$(".spinner").remove();
	},
	/*
	 * 중단 기본업무 버튼 이미지를 변경한다.
	 * 필요시 이미지 경로를 변경해야한다.
	 * @param index
	 */
	setWorkImgChange : function(idx){
		$("#gw_top_btn").find(".work_btn").each(function(i, e){//gw_top_btn요소에 하위요소인work_btn만 선택하는데 각각순차적으로 하나씩 접근한다
			$(this).attr("src", "./img/btn_top/"+(i+1)+"_normal.png").attr("select", "false"); //이미지를 변경한다
		});
		
		if(idx != "" && idx != null){
			$(".work_btn").eq(idx-1).attr("src", "./img/btn_top/"+idx+"_on.png").attr("select", "true");
		}
	},
	/*
	 * 하단 지도컨트롤 버튼 이미지를 변경한다.
	 * 필요시 이미지 경로를 변경해야한다.
	 * @param index
	 */
	setMapControlImgChange : function(idx){
		$(".bottom_btn").each(function(i, e){
			$(this).attr("src", "./img/btn/"+(i+1)+"_normal.png").attr("select", "false");
		});
		
		if(idx != "" && idx != null){
			$(".bottom_btn").eq(idx-1).attr("src", "./img/btn/"+idx+"_on.png").attr("select", "true");
		}
	},
	/*
	 * 보조 지도컨트롤 버튼 이미지를 변경한다.
	 * 필요시 이미지 경로를 변경해야한다.
	 * @param index
	 */
	setSubMapControlImgChange : function(target, idx, whr){
		if(whr == "east"){
			$(".sub_map_control_east").each(function(i){
				$(this).attr("src", "./img/btn/"+(i+1)+"_normal.png");
			});
			if(idx != "" && idx != null){
				$(target).attr("src", "./img/btn/"+idx+"_on.png");
			}
		}else{
			$(".sub_map_control_south").each(function(i){
				$(this).attr("src", "./img/btn/"+(i+1)+"_normal.png");
			});
			if(idx != "" && idx != null){
				$(target).attr("src", "./img/btn/"+idx+"_on.png");
			}
		}
	},
	/*
	 * 같은 값일경우 TD 를 병합한다.
	 * 반드시 엘리먼트에 key 속성이 존재하여야한다.
	 * @param Table ID
	 */
	setRowSpan : function(id){
		var fstTr = $("#"+id+" tr");
		var pnu = "";
		var sameLength = 1;
		if($(fstTr).length > 1){
			$(fstTr).each(function(i, e){
				if(i != 0){
					if(pnu != $(this).find("td:first").attr("key")){
						pnu = $(this).find("td:first").attr("key");
						sameLength = 1;
					}else{
						sameLength++;
						if(sameLength == 2){
							idx = $(this).prev().index();
						}
						$(fstTr).eq(idx).find("td").each(function(i){
							if(i < 9){
								$(this).attr("rowspan", sameLength);
							}
						}),$(this).find("td").each(function(i){
							if(i < 9){
								$(this).remove();
							}
						});
					}
				}
			});
		}
	},
	/*
	 * 숫자만 기입하였는지 체크한다.
	 * @param str
	 */
	chkNumber : function(str){
		if(!(new RegExp(/^[0-9]*$/).test(str))) {
			alert("숫자만 입력해주세요.");
			return false;
		}else{
			return true;
		}
	},
	/*
	 * 숫자만 기입하였는지, 소수점이 한자리인지 체크한다.
	 * @param str
	 */
	chkNumberComma : function(str){
		if(!(new RegExp(/^[0-9.(1)]*$/).test(str))) {
			alert("숫자만 입력해주세요.\n특수문자는 마침표만 가능합니다.");
			return false;
		}else{
			if(str.split(".").length-1 > 1){
				alert("마침표는 한개만 사용할 수 있습니다.");
				return false;
			}
			return true;
		}
	}
};