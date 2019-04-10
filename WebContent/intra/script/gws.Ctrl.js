if(!window._CON) _CON = {};
var _SUBMAP;
var outerLayout, middleLayout, innerLayout; 
var windowWidth, windowHeight;
var attribute_code = 0;
var navermap = null;
var daummap = null;
var googlemap = null;

var SIZE = null;

var ATTR_TAB = false;
var MAP_TAB = false;
var S_DIV = "";
var southType = "";

var pageAddr = null; //페이지주소 등록
var KEY = ""; //페이지 키값을 등록한다.

var widthDiv;
var heightDiv;
var paneEast = false;
var paneSouth = false;
var paneWest = true;
var paneName = "";

var tt = "";
var _vWorldObj; //VWORLD DIV오브젝트 
var _NaverObj;
var popId;
var tid;
var counter_cnt = 30*60;

/////////////////////////////////////////////////////////////////////////////////////////////////
if(paneWest){
	widthDiv =  ($(window).width()-273)/2;
}else{
	widthDiv =  ($(window).width())/2;
}
heightDiv = ($(window).height()-80)/2;

function beginSize(){
	if(paneWest){
		widthDiv =  ($(window).width()-273)/2;
	}else{
		widthDiv =  ($(window).width())/2;
	}
	heightDiv = ($(window).height()-80)/2;
}
/////////////////////////////////////////////////////////////////////////////////////////////////

$(document).ready(function () { 
	
	outerLayout = $('body').layout({ 
		center__paneSelector:	".outer-center" 
	,	west__paneSelector:		".outer-west" 
	,	west__size:				300 
	,   west__minSize :         225 
	,   west__maxSize :         300
	,	spacing_open:			5 
	,	spacing_closed:			5 
	,	north__size:			45 // 상위사이즈떄문에 변경 기본 80
	,   north__initHidden     :	false
	,   north__initClosed     :	false
	,   north__spacing_open   :	0		
	,   north__spacing_closed :	0
	,   west__onopen_end : function(){
			paneWest = true;
			
		}
	,   west__onclose_end : function(){
			paneWest = false;
			
		}
	}); 
	middleLayout = $('div.outer-center').layout(layoutSettings_Outer);
	outerLayout.disableResizable("west"); //리사이즈안하게 고정
	middleLayout.disableResizable("east"); //리사이즈안하게 고정
	middleLayout.disableResizable("south"); //리사이즈안하게 고정

}); 

var layoutSettings_Outer = {
		name: "middleLayout"
    , center : {
		paneSelector : ".inner-center"			
	}
	, east : {
			paneSelector : ".inner-east"
		,   initHidden      :  true
		,   initClosed      :  true
		,	spacing_open:			5 
		,	spacing_closed:			0 
		,   fxName          : "none"
		,   size            : widthDiv
		,   onclose_end : function (fff){
			if(!paneSouth){
				_CON.skill.division("f_div");
			}
			paneEast = false;
			//paneName = "";
			$("#size_arrow_right").hide();
			$("#vworldViewBox").hide();
			
			if(!$('input:radio[name="bugamap"]:input[value="roadview"]').attr("checked")){
				_MAINMAP.DaumRoadviewLV("다음로드뷰", false);
			}
		}
		,   onopen_end : function (){
			paneEast = true;
			//paneName = "east";
			$("#size_arrow_right").show();
		}
		,  onresize_end: function(){
			//naverdd();
		}
		//,	fxSpeed_open:			750  // 슬라이드 열리는 속도
		//,	fxSpeed_close:			1500 // 슬라이드 닫히는 속도
	}
	, south : {
			paneSelector : ".inner-south"
		,   initHidden      :	true
		,   initClosed      :	true
		,	spacing_open:	 5 
		,	spacing_closed:	0 
		,   fxName          : "none"
		,   size            : heightDiv
		,   onclose_end : function (){
				if(!paneEast){
					_CON.skill.division("f_div");
				}
				paneSouth = false;
				paneName = "";
				$("#vworldViewBox").hide();
				if(!$('input:radio[name="bugamap"]:input[value="roadview"]').attr("checked")){
					_MAINMAP.DaumRoadviewLV("다음로드뷰", false);
				}
		}
	    ,   onopen_end : function (){
	    		paneSouth = true;
	    		paneName = "south";
	    		$("#size_arrow_right").hide();
		 }
	    ,  onresize_end: function(){
				//alert("리사이즈 사용 종료시 발생!");
		}
	  	//,	fxSpeed_open:			750  // 슬라이드 열리는 속도
		//,	fxSpeed_close:			1500 // 슬라이드 닫히는 속도
	}
};


function reSize(){
	_S.utils.autoParentHeight("eastbox","eastContent" ,45);
	_S.utils.autoParentWidth("eastbox","eastContent" ,3);
	
	_S.utils.autoParentHeight("southbox","southContent" ,80);
	_S.utils.autoParentWidth("southbox","southContent" ,4);
	
	_S.utils.autoParentWidth("gw_top","gw_controler" ,278);

	if($("#result_content_box").length){
		if($("#detail_info").css("display") == "none"){
			_S.utils.autoParentHeight("con_sch_full_box", "result_content_box",240);//230
		}else{
			_S.utils.autoParentHeight("con_sch_full_box", "result_content_box",435);
		}
	}
	if($("#result_content").length){
		_S.utils.autoParentHeight("result_content_box", "result_content", 30);
		//$("#result_content").css("height","405px");
	}
	
	if($("#desi_result_content_box").length){
		if($("#detail_info").css("display") == "none"){
			_S.utils.autoParentHeight("con_sch_full_box", "desi_result_content_box", 182);
		}else{
			_S.utils.autoParentHeight("con_sch_full_box", "desi_result_content_box", 352);
		}
	}
	if($("#desi_result_content").length){
		_S.utils.autoParentHeight("desi_result_content_box", "desi_result_content", 30);
	}
	
	if($("#doro_result_content_box").length){
		/*if($("#detail_info").css("display") == "none"){
			_S.utils.autoParentHeight("con_sch_full_box", "doro_result_content_box", 192);
		}else{
			_S.utils.autoParentHeight("con_sch_full_box", "doro_result_content_box", 352);
		}*/
		_S.utils.autoParentHeight("con_sch_full_box", "doro_result_content_box", 183);
		_S.utils.autoParentHeight("doro_result_content_box", "doro_result_content", 30);
	}
	if($("#doro_result_content").length){
		//_S.utils.autoParentHeight("doro_result_content_box", "doro_result_content", 30);
	}
	
	//통합검색페이지리사이즈
	if($("#inte_result_content_box").length){
		/*if($("#detail_info").css("display") == "none"){
			_S.utils.autoParentHeight("con_sch_full_box", "inte_result_content_box", 252);
		}else{
			_S.utils.autoParentHeight("con_sch_full_box", "inte_result_content_box", 464);
		}*/
		//통합검색 연속지적선택시 사이즈조절
		if($("#general_box").css("display") == "none"){
			_S.utils.autoParentHeight("con_sch_full_box", "inte_result_content_box", 418);
		}else{
			_S.utils.autoParentHeight("con_sch_full_box", "inte_result_content_box", 243);
		}		
	}
	if($("#inte_result_content").length){
		if($("#general_box").css("display") == "none"){
			_S.utils.autoParentHeight("inte_result_content_box", "inte_result_content", 58);
		}else{
			_S.utils.autoParentHeight("inte_result_content_box", "inte_result_content", 58);
		}	
	}
	
	//영역검색페이지리사이즈
	if($("#pre_result_content_box").length){
		//_S.utils.autoParentHeight("con_sch_full_box", "pre_result_content_box", 358);
		_S.utils.autoParentHeight("pre_result_content_box", "pre_result_content", 58);
	}
	
	//버퍼검색리사이즈
	if($("#buf_result_content_box").length){
		//_S.utils.autoParentHeight("con_sch_full_box", "buf_result_content_box", 418);
		_S.utils.autoParentHeight("buf_result_content_box", "buf_result_content", 58);
	}
	
	//정보보기리사이즈
	if($("#info_result_content_box").length){
		//_S.utils.autoParentHeight("con_sch_full_box", "info_result_content_box", 147);
		_S.utils.autoParentHeight("info_result_content_box", "info_result_content", 30);
	}
	//레이어상세정보
	if($("#lyr_wapp").length){
		
		if(paneName != "south"){
			_S.utils.autoParentHeight("lyr_wapp", "lyr_content_box", 40);
		}else{
			_S.utils.autoParentHeight("lyr_wapp", "lyr_content_box", 10);
		}
		
		
		//_S.utils.autoParentHeight("lyr_content_box", "lyr_result1", 65);
		if($("#lyr_result1").length){
			_S.utils.autoParentHeight("lyr_content_box", "lyr_result1", 32);
		}
		if($("#lyr_result2").length){
			_S.utils.autoParentHeight("lyr_content_box", "lyr_result2", 32);
		}
	}
	
	//토지정보페이지 리사이즈
	if($("#toji_content_view").length){
		if(paneName != "south"){
			_S.utils.autoParentHeight("eastContent", "toji_content_view", 83);
		}else{
			_S.utils.autoParentHeight("southContent", "toji_content_view", 83);
		}
		//_S.utils.autoParentHeight("south_box", "toji_content_view", 85);
	}
	if($("#slide_page_content3").length){
		if(paneName != "south"){
			_S.utils.autoParentHeight("eastContent", "slide_page_content3", 365);
		}else{
			_S.utils.autoParentHeight("southContent", "slide_page_content3", 50);
		}
	}
	if($("#slide_page_content_3").length){
		if(paneName != "south"){
			_S.utils.autoParentHeight("eastContent", "slide_page_content_3", 385);
		}else{
			_S.utils.autoParentHeight("southContent", "slide_page_content_3", 50);
		}
	}
	if($("#act_page_content").length){
		if(paneName != "south"){
			_S.utils.autoParentHeight("eastContent", "act_page_content", 280);
		}else{
			_S.utils.autoParentHeight("southContent", "act_page_content", 50);
		}
	}

	_S.utils.autoParentHeight("map_full_box", "gmxmap_view", 30); //지도화면
	_S.utils.autoParentHeight("east_box","eastContent", 30); //east화면
	_S.utils.autoParentHeight("south_box","southContent", 32); //south화면
	
	//브이월드 사이즈조절
	if($("#vworldViewBox").css("display") == "inline-block"){
		if(paneName == "east"){
			var divObj = document.getElementById("eastbox");
			var innerObj = $("#eastContent");
			var x = _SMAP.mapsync.cumulativeOffset(divObj)[0];
			var y = _SMAP.mapsync.cumulativeOffset(divObj)[1];
			var i_w = innerObj.width();
			var i_h = innerObj.height();
			$("#vworldViewBox").css({"top": (y+29), "left":(x+2), "width":i_w, "height":i_h});
		}else if(paneName == "south"){
			var divObj = document.getElementById("southbox");
			var innerObj = $("#southContent");
			var x = _SMAP.mapsync.cumulativeOffset(divObj)[0];
			var y = _SMAP.mapsync.cumulativeOffset(divObj)[1];
			var i_w = innerObj.width();
			var i_h = innerObj.height();
			$("#vworldViewBox").css({"top":(y+30), "left":(x+2), "width":i_w, "height":i_h});
		}
	}
}
//보조맵 지도 가로,세로로 사용할지 여부판단
_CON.skill = {
		//좌측영역열기
		westPanelOpen : function(){ 
			$("#panelShow").hide();
			$("#panelHide").show();
			outerLayout.open("west");
		},
		//좌측영역 닫기
		westPanelClose : function(){
			$("#panelShow").show();
			$("#panelHide").hide();
			outerLayout.close("west");
		},
		inactivetion : function(){
			$("#divsero").attr("onclick", "");
			$("#divgaro").attr("onclick", "");
			$("#divsero").css({"cursor":""});
			$("#divgaro").css({"cursor":""});
		},
		activation : function(){
			
			if(S_DIV == "W"){
				this.division("w_div");
			}else if(S_DIV == "H"){
				this.division("h_div");
			}else{
				this.division("w_div");
			}
			
			var divsero = document.getElementById("divsero");
			var divgaro = document.getElementById("divgaro");
			
			divsero.onclick = function(){
				_CON.skill.division('w_div');
				S_DIV = "W";
			};
			
			divgaro.onclick = function(){
				_CON.skill.division('h_div');
				S_DIV = "H";
			};
						
			//$("#divsero").attr("onClick", "_CON.skill.division('w_div');");
			//$("#divgaro").attr("onClick", "_CON.skill.division('h_div');");
			$("#divsero").css({"cursor":"pointer"});
			$("#divgaro").css({"cursor":"pointer"});
		},
		srch : function(type){
			southType = "";
			southType = type;
		},
		bugamap : function(obj, tem, cx, cy){
			
			//$("#eastContent").html("");
			//$("#southContent").html("");
			//middleLayout.close("east");
			//middleLayout.close("south");
			
			if(cx != undefined){
				R_CX = cx;
				R_CY = cy;
			}
			
			var tab_bojo1 = $("#tab_bojo1");
			var tab_bojo2 = $("#tab_bojo2");
			
			tab_bojo1.attr("src", "img/tab_bojo_1.gif");
			tab_bojo2.attr("src", "img/tab_bojo_1.gif");
			$("#syc_box_east, #syc_box_south").show();
			
			//MAP_TAB = true;
			
			if(tem == 1){
				ATTR_TAB = false;
				MAP_TAB = true;
			}
			
			/*if(southType == "srch"){
				southType = "";
				middleLayout.close("south");
			}*/
			
			if(paneName == ""){
				if(obj == "gmxbojo"){
					this.activation();///분할한 화면이 세로인지 가로인지 판단
				}else if(obj == "google3D"){
					this.activation();///분할한 화면이 세로인지 가로인지 판단
				}else if(obj  == "naver"){
					this.activation();///분할한 화면이 세로인지 가로인지 판단
				}else if(obj  == "vm"){
					this.activation();///분할한 화면이 세로인지 가로인지 판단
				}else if(obj  == "roadview"){
					this.activation();///분할한 화면이 세로인지 가로인지 판단
				}else if(obj  == "daum"){
					this.activation();///분할한 화면이 세로인지 가로인지 판단
				}

			}else if(paneName == "east"){
				if(obj == "gmxbojo"){
					$("#vworldViewBox").hide();
					$("#eastContent").html("");
					$("#subgmx_ctrl_east, #subgmx_ctrl_south").show();
					gmx.Init.prototype = new geomex.GMXMAP("sub_ageomex");
					_SUBMAP = new gmx.Init("sub_ageomex");
					_SUBMAP.subAppletTag("eastContent");//보조지도 애플릿 호출
					//화면분할시 동기화버튼 나오기
					$("#syc_btn1, #syc_btn4").show();
					_MAP.SUBGMXMAP.syncMAPCtrl(2);
				}else if(obj == "google3D"){
					$("#vworldViewBox").hide();
					var str = "";
					var width =$("#eastContent").width();
					var height =$("#eastContent").height();
					$("#subgmx_ctrl_east, #subgmx_ctrl_south").hide();
					str+="<iframe name='google3d' id='google3d' border='0'  marginwidth='0' marginheight='0'  frameborder='0' width='100%' height='100%'></iframe>";
					$("#eastContent").html("");
					$("#eastContent").html(str);
					$("#google3d").attr("src", "../geomex/utils/google3d.jsp");
					_GOOGLE_POPUP = parent.frames["google3d"];
					
					//화면분할시 동기화버튼 없애기
					$("#syc_btn1, #syc_btn4").hide();
					_MAP.SUBGMXMAP.syncMAPCtrl(2);
					
				}else if(obj  == "daum"){
					$("#vworldViewBox").hide();	
					$("#eastContent").html("");
					$("#subgmx_ctrl_east, #subgmx_ctrl_south").hide();
					var creatediv = $('<div />',{id: 'DAUM_MAP', style:"border:0px solid #000000; width:100%; height:100%;"}); 
					$("#eastContent").append(creatediv);
					_SMAP.mapsync.daumCreate("DAUM_MAP");
					//화면분할시 동기화버튼 없애기
					$("#syc_btn1, #syc_btn4").hide();
					_MAP.SUBGMXMAP.syncMAPCtrl(2);
					
				}else if(obj  == "naver"){
					$("#vworldViewBox").hide();	
					$("#eastContent").html("");
					$("#subgmx_ctrl_east, #subgmx_ctrl_south").hide();
					var creatediv = $('<div />',{id: 'NAVER_MAP', style:"border:0px solid #000000; width:100%; height:100%;"}); 
					$("#eastContent").append(creatediv);
					_SMAP.mapsync.naverCreate("NAVER_MAP");
					//화면분할시 동기화버튼 없애기
					$("#syc_btn1, #syc_btn4").hide();
					_MAP.SUBGMXMAP.syncMAPCtrl(2);
					
				}else if(obj  == "vm"){
						
					$("#vworldViewBox").hide();
					$("#eastContent").html("");
					$("#subgmx_ctrl_east, #subgmx_ctrl_south").hide();
					_SMAP.mapsync.vWorldCreate("vworldViewBox");
					//화면분할시 동기화버튼 없애기
					$("#syc_btn1, #syc_btn4").hide();
					_MAP.SUBGMXMAP.syncMAPCtrl(2);
					
					/*var str = "";
					var width =$("#eastContent").width();
					var height =$("#eastContent").height();
					$("#subgmx_ctrl_east, #subgmx_ctrl_south").hide();
					str+="<iframe name='vworld3d' id='vworld3d' border='0'  marginwidth='0' marginheight='0'  frameborder='0' width='100%' height='100%'></iframe>";
					$("#eastContent").html("");
					$("#eastContent").html(str);
					$("#vworld3d").attr("src", "../geomex/utils/vworld3d.jsp");
					_VW_POPUP = parent.frames["vworld3d"];
					//화면분할시 동기화버튼 없애기
					$("#syc_btn1, #syc_btn4").hide();
					_MAP.SUBGMXMAP.syncMAPCtrl(2);*/
					
				}else if(obj  == "roadview"){
					
					if($("#DR").length){
						_SMAP.mapsync.moveOtherMap(R_CX, R_CY,"roadview");
					}else{
						$("#vworldViewBox").hide();
						$("#eastContent").html("");
						//var creatediv = "<div id='DR' style='width:100%; height:100%;'></div>";
						var creatediv = $('<div />',{id: 'DR', style:"border:0px solid #000000; width:100%; height:100%;"}); 
						$("#eastContent").append(creatediv);
						$("#subgmx_ctrl_east, #subgmx_ctrl_south").hide();
						_SMAP.mapsync.RoadViewCreate("DR", R_CX, R_CY);
						//화면분할시 동기화버튼 없애기
						$("#syc_box_east, #syc_box_south").hide();
						_MAP.SUBGMXMAP.syncMAPCtrl(2);
					}
					
				}
			}else if(paneName == "south"){
				if(obj == "gmxbojo"){
					$("#vworldViewBox").hide();
					$("#southContent").html("");
					$("#subgmx_ctrl_east, #subgmx_ctrl_south").show();
					gmx.Init.prototype = new geomex.GMXMAP("sub_ageomex");
					_SUBMAP = new gmx.Init("sub_ageomex");
					_SUBMAP.subAppletTag("southContent");
					//화면분할시 동기화버튼 나오기
					$("#syc_btn1, #syc_btn4").show();
					_MAP.SUBGMXMAP.syncMAPCtrl(2);
					
				}else if(obj == "google3D"){
					$("#vworldViewBox").hide();
					$("#subgmx_ctrl_east, #subgmx_ctrl_south").hide();
					var str = "";
					str+="<iframe name='google3d' id='google3d' border='0' width='100%' height='100%'></iframe>";
					$("#southContent").html("");
					$("#southContent").html(str);
					$("#google3d").attr("src", "../geomex/utils/google3d.jsp");
					_GOOGLE_POPUP = parent.frames["google3d"];
					
					//화면분할시 동기화버튼 없애기
					$("#syc_btn1, #syc_btn4").hide();
					_MAP.SUBGMXMAP.syncMAPCtrl(2);
					
				}else if(obj  == "daum"){
					$("#vworldViewBox").hide();
					$("#southContent").html("");
					$("#subgmx_ctrl_east, #subgmx_ctrl_south").hide();
					var creatediv = $('<div />',{id: 'DAUM_MAP', style:"border:0px solid #000000; width:100%; height:100%;"}); 
					$("#southContent").append(creatediv);
					_SMAP.mapsync.daumCreate("DAUM_MAP");
					//_SMAP.mapsync.naverCreate("southContent");
					//화면분할시 동기화버튼 없애기
					$("#syc_btn1, #syc_btn4").hide();
					_MAP.SUBGMXMAP.syncMAPCtrl(2);
				}else if(obj  == "naver"){
					$("#vworldViewBox").hide();
					$("#southContent").html("");
					$("#subgmx_ctrl_east, #subgmx_ctrl_south").hide();
					var creatediv = $('<div />',{id: 'NAVER_MAP', style:"border:0px solid #000000; width:100%; height:100%;"}); 
					$("#southContent").append(creatediv);
					_SMAP.mapsync.naverCreate("NAVER_MAP");
					//_SMAP.mapsync.naverCreate("southContent");
					//화면분할시 동기화버튼 없애기
					$("#syc_btn1, #syc_btn4").hide();
					_MAP.SUBGMXMAP.syncMAPCtrl(2);
				}else if(obj  == "vm"){
					$("#vworldViewBox").hide();
					$("#southContent").html("");
					$("#subgmx_ctrl_east, #subgmx_ctrl_south").hide();
					_SMAP.mapsync.vWorldCreate("southContent");
					//화면분할시 동기화버튼 없애기
					$("#syc_btn1, #syc_btn4").hide();
					_MAP.SUBGMXMAP.syncMAPCtrl(2);
				}else if(obj  == "roadview"){
					if($("#DR").length){
						_SMAP.mapsync.moveOtherMap(R_CX, R_CY,"roadview");
					}else{					
						$("#vworldViewBox").hide();
						$("#southContent").html("");
						var creatediv = "<div id='DR' style='width:100%; height:100%;'></div>";
						$("#southContent").append(creatediv);
						$("#subgmx_ctrl_east, #subgmx_ctrl_south").hide();
						_SMAP.mapsync.RoadViewCreate("DR", R_CX, R_CY);
						//화면분할시 동기화버튼 없애기
						$("#syc_box_east, #syc_box_south").hide();
						_MAP.SUBGMXMAP.syncMAPCtrl(2);
					}
				}
			}		
		},
		division : function(obj){
			
			$("#eastContent").html("");
			$("#southContent").html("");
			var tab_bojo1 = $("#tab_bojo1");
			var tab_bojo2 = $("#tab_bojo2");
			var bugaCheck = $("input:radio[name='bugamap']:checked");
			
			tab_bojo1.attr("src", "img/tab_bojo_1.gif");
			tab_bojo2.attr("src", "img/tab_bojo_1.gif");
			
			//네이버영상보조맵
			$("#submap_ctrl17, #submap_ctrl18").attr("src","img/btn/s_naver_map_off.gif");
			N_M=0;
			
			beginSize(); //생성될화면사이즈조절
			
			 if(obj == "f_div"){
			 	middleLayout.close("east");
				middleLayout.close("south");
				$("#divsero").attr("src", "img/sc_2.png");
				$("#divgaro").attr("src", "img/sc_3.png");
				paneName = "";
				bugaCheck.attr("checked", false); // 라디오버튼전체해제
				_CON.skill.inactivetion();
			 }else if(obj == "w_div"){
				middleLayout.sizePane("east", widthDiv); // 사이즈를 다시 설정 한다
				middleLayout.open("east");
				middleLayout.close("south");
				$("#divsero").attr("src", "img/sc_2_on.png");
				$("#divgaro").attr("src", "img/sc_3.png");
				paneName = "east";
				this.bugamap(bugaCheck.val());
				if(ATTR_TAB){
					if(pageAddr != null || pageAddr != ""){
						this.contentAttribute(pageAddr, "base", KEY);
					}
				}
			}else if(obj == "h_div"){
				middleLayout.sizePane("south", heightDiv); // 사이즈를 다시 설정 한다
				middleLayout.open("south");
				middleLayout.close("east"); 
				$("#divsero").attr("src", "img/sc_2.png");
				$("#divgaro").attr("src", "img/sc_3_on.png");
				paneName = "south"; 
				this.bugamap(bugaCheck.val());
				if(ATTR_TAB){
					if(pageAddr != null || pageAddr != ""){
						this.contentAttribute(pageAddr, "base", KEY);
					}
				}
			}
			
		},
		maptab : function(obj, divobj){
			
			/*$("#eastContent").empty();
			$("#southContent").empty();*/
			
			var bugaCheck = $("input:radio[name='bugamap']:checked").val();
			
			if(obj == "map"){
				if(bugaCheck == undefined){
					alert("부과지도가 선택 되어 있지 않습니다.");
					return false;
				}else{
					
					//추가 2012-02-28
					$("#eastContent").empty();
					$("#southContent").empty();
					
					$("#syc_box_east, #syc_box_south").show();
					ATTR_TAB = false;
					MAP_TAB = true;
					this.division(divobj);
				}
			}else if(obj == "att"){
				if(pageAddr != null){
					
					//추가 2012-02-28
					$("#eastContent").empty();
					$("#southContent").empty();
					
					$("#syc_box_east, #syc_box_south").hide();
					ATTR_TAB = true;
					MAP_TAB = false;
					this.contentAttribute(pageAddr, "base", KEY);
				}else{
					alert("검색정보를 선택 하여 주십시오!");
				}
			}
		},
		contentAdd : function(addr, kind, obj){
			alert("2");
			alert(addr);
			alert(kind);
			alert(obj);
			$.ajax({
				type: "POST",
				url: addr,
				data : obj,
				dataType : "html",
				success: function(htm){
					_PC.init.frameOpen();
					var srchFrame = $('body', parent.srch.document);
					$(srchFrame).html("");
					$(srchFrame).html(htm);
					//reSize(); //사이즈조절
					if(addr == "form/toji.jsp"){
						//_PC.init.GetSGG();//시군구목록
						_PC.init.GetSELSB(obj);							
						if(kind == "base"){
							//기본정보
							_PC.init.tojiBasic();
						}else if(kind == "ShowLRGInfo"){
							//토지대장
							_PC.init.tojiDaejang(obj);
						}else if(kind == "ShowEAISInfo"){
							//건축물대장
							_PC.init.bldgDaejang();
						}else if(kind == "ShowRestrictLawInfo"){
							//행위제한법령정보
							_PC.init.tojiUseplan();
						}else if(kind == "ShowRestrictByZone"){
							//지역지구별행위제한가능여부
							_PC.init.rdAct();
						}else if(kind == "ShowRestrictByCond"){
							//조건별행위제한가능여부
							_PC.init.tojiAct();
						}
						
					}else if(addr == ""){}
				},
				beforeSend : function(){
	    			parent.frames[1].location.href = './srch.jsp';

					$("#con_sch_full_box").showLoading();
				},
				complete : function(){
					$("#con_sch_full_box").hideLoading();
				},
				error: function(xhr, status, error){
					alert(">>>>>>>>>>> : 에러");
				}
			});

		},
		contentAttribute : function(addr, kind, obj){

			//**************//
			pageAddr = addr; //페이지주소
			KEY = obj; //pnu값
			//**************//
			
			if(pageAddr != null || pageAddr != ""){

				ATTR_TAB = true;
				MAP_TAB = false;
				
				//var divid = "";
				$("#eastContent").html("");
				$("#southContent").html("");
				
				var tab_bojo1 = $("#tab_bojo1");
				var tab_bojo2 = $("#tab_bojo2");
				
				tab_bojo1.attr("src", "img/tab_bojo_2.gif");
				tab_bojo2.attr("src", "img/tab_bojo_2.gif");
				$("#syc_box_east, #syc_box_south").hide();
				
				
				//서브지도영역의 컨트롤바를 없앤다
				$("#subgmx_ctrl_east").hide();
				$("#subgmx_ctrl_south").hide();
				//브이월드의 지도를 숨긴다.
				$("#vworldViewBox").hide();
				
				if(paneName == "east"){
					divid = "eastContent";
					$("#divsero").attr("src", "img/sc_2_on.png");
					$("#divgaro").attr("src", "img/sc_3.png");
					_SMAP.mapsync.naverRemove();
					$("#eastContent").remove();
					var str = "<div id='eastContent' style='border:0px solid #000000; padding:2px; overflow: auto;'></div>";
					$("#east_box").append(str);
				}else if(paneName == "south"){
					divid = "southContent";
					$("#divsero").attr("src", "img/sc_2.png");
					$("#divgaro").attr("src", "img/sc_3_on.png");
					_SMAP.mapsync.naverRemove();
					$("#southContent").remove();
					var str = "<div id='southContent' style='padding:2px; overflow: auto;'></div>";
					$("#south_box").append(str);
				}else{

					if(S_DIV == "W"){
						_CON.skill.division('w_div');
						//middleLayout.open("east");
						divid = "eastContent";
					}else if(S_DIV == "H"){
						_CON.skill.division('h_div');
						//middleLayout.open("south");
						divid = "southContent";
					}else{
						_CON.skill.division('w_div');
						//middleLayout.open("east");
						divid = "eastContent";
					}
					
					var divsero = document.getElementById("divsero");
					var divgaro = document.getElementById("divgaro");
					
					divsero.onclick = function(){
						_CON.skill.division('w_div');
						S_DIV = "W";
					};
					
					divgaro.onclick = function(){
						_CON.skill.division('h_div');
						S_DIV = "H";
					};
					
					//대장쪽화면을 열때 버튼 활성화
					//$("#divsero").attr("onClick", "_CON.skill.division('w_div')");
					//$("#divgaro").attr("onClick", "_CON.skill.division('h_div')");
					$("#divsero").css({"cursor":"pointer"});
					$("#divgaro").css({"cursor":"pointer"});
					/*$("#divsero").attr("src", "img/sc_2_on.gif");
					$("#divgaro").attr("src", "img/sc_3.gif");*/
				}
				
				//SYNC_NONE = false;
				
				$.ajax({
					type: "POST",
					url: pageAddr,
					dataType : "html",
					success: function(htm){
						$("#"+divid).html(htm);
						reSize(); //사이즈조절
						if(pageAddr == "form/toji.jsp"){
							//_PC.init.GetSGG();//시군구목록
							_PC.init.GetSELSB(KEY);							
							if(kind == "base"){
								//기본정보
								_PC.init.tojiBasic();
							}else if(kind == "ShowLRGInfo"){
								//토지대장
								_PC.init.tojiDaejang();
							}else if(kind == "ShowEAISInfo"){
								//건축물대장
								_PC.init.bldgDaejang();
							}else if(kind == "ShowRestrictLawInfo"){
								//행위제한법령정보
								_PC.init.tojiUseplan();
							}else if(kind == "ShowRestrictByZone"){
								//지역지구별행위제한가능여부
								_PC.init.rdAct();
							}else if(kind == "ShowRestrictByCond"){
								//조건별행위제한가능여부
								_PC.init.tojiAct();
							}
							
						}else if(pageAddr == ""){}
					},
					 beforeSend : function(){
						 if(pageAddr != "form/toji.jsp"){
							 if(paneName == "east"){								 
								 $("#eastContent").showLoading();
							 }else if(paneName == "south"){
								 $("#southContent").showLoading();
							 }
					 	}
					},
					complete : function(){
						if(pageAddr != "form/toji.jsp"){
							 if(paneName == "east"){								 
								 $("#eastContent").hideLoading();
							 }else if(paneName == "south"){
								 $("#southContent").hideLoading();
							 }
					 	}
					},
					error: function(xhr, status, error){
							alert(">>>>>>>>>>> : 에러");
					}
				});
				
			}else{
				alert("검색정보를 선택 하여 주십시오!");
			}
		},
		sizeControlPlus : function(txt){
			var eastWidth = $(".inner-east").width();
			var centerWidth = $(".inner-center").width();
			var southHeight = $(".inner-south").height();
			var centerHeight = $(".inner-center").height();
			
			if(txt != 'full'){
				if(paneEast){
					if(centerWidth >= 400){
						middleLayout.sizePane("east", (eastWidth+100));
						this.vWorldSizeW();
					}else{
						alert("더이상 조절할 수 없습니다.");
					}
				}else if(paneSouth){
					if(centerHeight >= 200){
						middleLayout.sizePane("south", (southHeight+100));
						this.vWorldSizeH();
					}else{
						alert("더이상 조절할 수 없습니다.");
					}
				}
				SIZE = null;
			}else{
				SIZE = txt;
				if(paneEast){
					middleLayout.sizePane("east", (eastWidth+1000));
					this.vWorldSizeW();
				}else if(paneSouth){
					middleLayout.sizePane("south", (eastWidth+1000));
					this.vWorldSizeH();
				}
			}
		},
		sizeControlMinus : function(txt){
			
			var eastWidth = $(".inner-east").width();
			var southHeight = $(".inner-south").height();
			
			if(txt != 'close'){
				if(paneEast){
					if(eastWidth >= 268){
						middleLayout.sizePane("east", (eastWidth-100));
						this.vWorldSizeW();
					}else{
						alert("더이상 조절할 수 없습니다.");
					}
				}else if(paneSouth){
					if(southHeight >= 150){
						middleLayout.sizePane("south", (southHeight-100));
						this.vWorldSizeH();
					}else{
						alert("더이상 조절할 수 없습니다.");
					}
				}
				SIZE = null;
			}else{
				if(SIZE == "full"){
					if(paneEast){
						middleLayout.sizePane("east", "50%");
						this.vWorldSizeW();
					}else if(paneSouth){
						middleLayout.sizePane("south", "50%");
						this.vWorldSizeH();
					}
					SIZE = null;
				}else{
					if(paneEast){
						middleLayout.close("east");
					}else if(paneSouth){
						middleLayout.close("south");
						//middleLayout.sizePane("south", 29);
					}
					////20140808추가
					$(".bjimg").each(function(i){
						$(this).attr("src", "./img/btn/bj"+(parseInt(i)+1)+".png");
					});
				}
			}
		},
		vWorldSizeW : function(){
			if($("#vworldViewBox").css("display") == "inline-block"){
				var divObj = document.getElementById("eastbox");
				var innerObj = $("#eastContent");
				var x = _SMAP.mapsync.cumulativeOffset(divObj)[0];
				var y = _SMAP.mapsync.cumulativeOffset(divObj)[1];
				var i_w = innerObj.width();
				$("#vworldViewBox").css({"top": (y+29), "left":(x+2), "width":i_w});
			}
		},
		vWorldSizeH : function(){
			if($("#vworldViewBox").css("display") == "inline-block"){
				var divObj = document.getElementById("southbox");
				var innerObj = $("#southContent");
				var x = _SMAP.mapsync.cumulativeOffset(divObj)[0];
				var y = _SMAP.mapsync.cumulativeOffset(divObj)[1];
				var i_h = innerObj.height();
				$("#vworldViewBox").css({"top":(y+30), "left":(x+2), "height":i_h});
			}
		},
		/*
		 * 속성 창  슬라이드 액션
		 */
		slideAction : function(imgid, conid){
			var disID = $("#"+conid).css("display");
			$("#"+conid).slideToggle("fast");
			if(disID != "none"){
				$("#"+imgid).attr("src", "img/pp_img_4.gif");
			}else{
				$("#"+imgid).attr("src", "img/pp_img_3.gif");
			}
		},
		/*
		 * 속성 창  슬라이드 액션2
		 */
		slidecontentAction : function(conid){
			$("#"+conid).slideToggle("fast");
		},
		//레이어관리창을 오픈한다.(팝업)
		LyrManagentPopup : function(){
			var browser = deployJava.getBrowser();
			var width = 1250;
			var height = 663;
			if(browser != "MSIE"){
				width = 1270;
				height = 673;
			}
			var x = (screen.availWidth- width)/2;
			var y = (screen.availHeight- height)/2;
			var winState = 'top=' + y + ',left=' + x + ',width=' +width +',height=' +height; winState +=',menubar=no,scrollbars=no,status=no,resizable=no';
			popId = window.open("./lyrmana/layerManagent.jsp", '', winState);
			popId.focus();
		},
		menuimgChange : function(obj){
			if("top_btn_1" == obj){
				$("#top_btn_1").attr("src","img/btn/top_btn_1_on.gif");
				$("#top_btn_2").attr("src","img/btn/top_btn_2.gif");
				$("#top_btn_3").attr("src","img/btn/top_btn_3.png");
				$("#top_btn_4").attr("src","img/btn/top_btn_4.gif");
				$("#top_btn_5").attr("src","img/btn/top_btn_5.png");
				$("#top_btn_6").attr("src","img/btn/top_btn_6.gif");
				$("#top_btn_7").attr("src","img/btn/top_btn_7.png");
				$("#top_btn_8").attr("src","img/btn/top_btn_8.png");
				$("#top_btn_9").attr("src","img/btn/top_btn_9.png");
				$("#top_btn_offset").attr("src","img/btn/top_btn_20.png");
				//_CS.init.srchjibunNewLoding();
				_CS.init.srchjibunLoding(2);
				this.ctrlimgChange('control_img_3');
			}else if("top_btn_2" == obj){
				$("#top_btn_1").attr("src","img/btn/top_btn_1.gif");
				$("#top_btn_2").attr("src","img/btn/top_btn_2_on.gif");
				$("#top_btn_3").attr("src","img/btn/top_btn_3.png");
				$("#top_btn_4").attr("src","img/btn/top_btn_4.gif");
				$("#top_btn_5").attr("src","img/btn/top_btn_5.png");
				$("#top_btn_6").attr("src","img/btn/top_btn_6.gif");
				$("#top_btn_7").attr("src","img/btn/top_btn_7.png");
				$("#top_btn_8").attr("src","img/btn/top_btn_8.png");
				$("#top_btn_9").attr("src","img/btn/top_btn_9.png");
				$("#top_btn_offset").attr("src","img/btn/top_btn_20.png");
				_CS.init.srchjibunLoding(1);
				this.ctrlimgChange('control_img_3');
			}else if("top_btn_3" == obj){
				$("#top_btn_1").attr("src","img/btn/top_btn_1.gif");
				$("#top_btn_2").attr("src","img/btn/top_btn_2.gif");
				$("#top_btn_3").attr("src","img/btn/top_btn_3_on.png");
				$("#top_btn_4").attr("src","img/btn/top_btn_4.gif");
				$("#top_btn_5").attr("src","img/btn/top_btn_5.png");
				$("#top_btn_6").attr("src","img/btn/top_btn_6.gif");
				$("#top_btn_7").attr("src","img/btn/top_btn_7.png");
				$("#top_btn_8").attr("src","img/btn/top_btn_8.png");
				$("#top_btn_9").attr("src","img/btn/top_btn_9.png");
				$("#top_btn_offset").attr("src","img/btn/top_btn_20.png");
				//_CS.init.srchDesiLoding(); 
				_CS.init.srchjibunLoding(2);
				this.ctrlimgChange('control_img_3');
			}else if("top_btn_4" == obj){
				$("#top_btn_1").attr("src","img/btn/top_btn_1.gif");
				$("#top_btn_2").attr("src","img/btn/top_btn_2.gif");
				$("#top_btn_3").attr("src","img/btn/top_btn_3.png");
				$("#top_btn_4").attr("src","img/btn/top_btn_4_on.gif");
				$("#top_btn_5").attr("src","img/btn/top_btn_5.png");
				$("#top_btn_6").attr("src","img/btn/top_btn_6.gif");
				$("#top_btn_7").attr("src","img/btn/top_btn_7.png");
				$("#top_btn_8").attr("src","img/btn/top_btn_8.png");
				$("#top_btn_9").attr("src","img/btn/top_btn_9.png");
				$("#top_btn_offset").attr("src","img/btn/top_btn_20.png");
				_CS.init.srchRoadLoding(); 
				this.ctrlimgChange('control_img_3');
			}else if("top_btn_6" == obj){
				$("#top_btn_1").attr("src","img/btn/top_btn_1.gif");
				$("#top_btn_2").attr("src","img/btn/top_btn_2.gif");
				$("#top_btn_3").attr("src","img/btn/top_btn_3.png");
				$("#top_btn_4").attr("src","img/btn/top_btn_4.gif");
				$("#top_btn_5").attr("src","img/btn/top_btn_5.png");
				$("#top_btn_6").attr("src","img/btn/top_btn_6_on.gif");
				$("#top_btn_7").attr("src","img/btn/top_btn_7.png");
				$("#top_btn_8").attr("src","img/btn/top_btn_8.png");
				$("#top_btn_9").attr("src","img/btn/top_btn_9.png");
				$("#top_btn_offset").attr("src","img/btn/top_btn_20.png");
				_CS.init.srchInteLoding();
				this.ctrlimgChange('control_img_3');
			}else if("top_btn_7" == obj){
				$("#top_btn_1").attr("src","img/btn/top_btn_1.gif");
				$("#top_btn_2").attr("src","img/btn/top_btn_2.gif");
				$("#top_btn_3").attr("src","img/btn/top_btn_3.png");
				$("#top_btn_4").attr("src","img/btn/top_btn_4.gif");
				$("#top_btn_5").attr("src","img/btn/top_btn_5.png");
				$("#top_btn_6").attr("src","img/btn/top_btn_6.gif");
				$("#top_btn_7").attr("src","img/btn/top_btn_7_on.png");
				$("#top_btn_8").attr("src","img/btn/top_btn_8.png");
				$("#top_btn_9").attr("src","img/btn/top_btn_9.png");
				$("#top_btn_offset").attr("src","img/btn/top_btn_20.png");
				_CS.init.srchPreserveLoding();
				this.ctrlimgChange('control_img_3');
			}else if("top_btn_8" == obj){
				$("#top_btn_1").attr("src","img/btn/top_btn_1.gif");
				$("#top_btn_2").attr("src","img/btn/top_btn_2.gif");
				$("#top_btn_3").attr("src","img/btn/top_btn_3.png");
				$("#top_btn_4").attr("src","img/btn/top_btn_4.gif");
				$("#top_btn_5").attr("src","img/btn/top_btn_5.png");
				$("#top_btn_6").attr("src","img/btn/top_btn_6.gif");
				$("#top_btn_7").attr("src","img/btn/top_btn_7.png");
				$("#top_btn_8").attr("src","img/btn/top_btn_8_on.png");
				$("#top_btn_9").attr("src","img/btn/top_btn_9.png");
				$("#top_btn_offset").attr("src","img/btn/top_btn_20.png");
				_CS.init.srchBufferLoding();
				this.ctrlimgChange('control_img_3');
			}else if("top_btn_9" == obj){
				$("#top_btn_1").attr("src","img/btn/top_btn_1.gif");
				$("#top_btn_2").attr("src","img/btn/top_btn_2.gif");
				$("#top_btn_3").attr("src","img/btn/top_btn_3.png");
				$("#top_btn_4").attr("src","img/btn/top_btn_4.gif");
				$("#top_btn_5").attr("src","img/btn/top_btn_5.png");
				$("#top_btn_6").attr("src","img/btn/top_btn_6.gif");
				$("#top_btn_7").attr("src","img/btn/top_btn_7.png");
				$("#top_btn_8").attr("src","img/btn/top_btn_8.png");
				$("#top_btn_9").attr("src","img/btn/top_btn_9_on.png");
				$("#top_btn_offset").attr("src","img/btn/top_btn_20.png");
				_CS.init.srchInfoLoding();
				this.ctrlimgChange('control_img_3');
			}else if("top_btn_5" == obj){
				//좌표검색 팝업창을 연다.
				$("#top_btn_1").attr("src","img/btn/top_btn_1.gif");
				$("#top_btn_2").attr("src","img/btn/top_btn_2.gif");
				$("#top_btn_3").attr("src","img/btn/top_btn_3.png");
				$("#top_btn_4").attr("src","img/btn/top_btn_4.gif");
				$("#top_btn_5").attr("src","img/btn/top_btn_5_on.png");
				$("#top_btn_6").attr("src","img/btn/top_btn_6.gif");
				$("#top_btn_7").attr("src","img/btn/top_btn_7.png");
				$("#top_btn_8").attr("src","img/btn/top_btn_8.png");
				$("#top_btn_9").attr("src","img/btn/top_btn_9.png");
				$("#top_btn_offset").attr("src","img/btn/top_btn_20.png");
				_CS.init.srchCoordLoading();
			}else if("top_btn_offset" == obj){
				//오프셋을 연다.
				$("#top_btn_1").attr("src","img/btn/top_btn_1.gif");
				$("#top_btn_2").attr("src","img/btn/top_btn_2.gif");
				$("#top_btn_3").attr("src","img/btn/top_btn_3.png");
				$("#top_btn_4").attr("src","img/btn/top_btn_4.gif");
				$("#top_btn_5").attr("src","img/btn/top_btn_5.png");
				$("#top_btn_6").attr("src","img/btn/top_btn_6.gif");
				$("#top_btn_7").attr("src","img/btn/top_btn_7.png");
				$("#top_btn_8").attr("src","img/btn/top_btn_8.png");
				$("#top_btn_9").attr("src","img/btn/top_btn_9.png");
				$("#top_btn_offset").attr("src","img/btn/top_btn_20_on.png");
				_CS.init.setOffset();
			}else if("etc" == obj){
				$("#top_btn_1").attr("src","img/btn/top_btn_1.gif");
				$("#top_btn_2").attr("src","img/btn/top_btn_2.gif");
				$("#top_btn_3").attr("src","img/btn/top_btn_3_on.png");
				$("#top_btn_4").attr("src","img/btn/top_btn_4.gif");
				$("#top_btn_5").attr("src","img/btn/top_btn_5.png");
				$("#top_btn_6").attr("src","img/btn/top_btn_6.gif");
				$("#top_btn_7").attr("src","img/btn/top_btn_7.png");
				$("#top_btn_8").attr("src","img/btn/top_btn_8.png");
				$("#top_btn_9").attr("src","img/btn/top_btn_9.png");
				$("#top_btn_offset").attr("src","img/btn/top_btn_20.png");
			}
		},
		ctrlimgChange : function(obj){
			if("control_img_1" == obj){
				$("#control_img_1").attr("src","img/btn/zoom_in_on.png");
				$("#control_img_2").attr("src","img/btn/zoom_out.png");
				$("#control_img_3").attr("src","img/btn/move.png");
				$("#control_img_11").attr("src","img/btn/info_btn.gif");
				$("#top_btn_10").attr("src","img/btn/top_btn_10.png");
				$("#top_btn_11").attr("src","img/btn/top_btn_11.png");
				_MAINMAP.setMapTool('ZoomIn');			
			}else if("control_img_2" == obj){
				$("#control_img_1").attr("src","img/btn/zoom_in.png");
				$("#control_img_2").attr("src","img/btn/zoom_out_on.png");
				$("#control_img_3").attr("src","img/btn/move.png");
				$("#control_img_11").attr("src","img/btn/info_btn.gif");
				$("#top_btn_10").attr("src","img/btn/top_btn_10.png");
				$("#top_btn_11").attr("src","img/btn/top_btn_11.png");
				_MAINMAP.setMapTool('ZoomOut');
			}else if("control_img_3" == obj){
				$("#control_img_1").attr("src","img/btn/zoom_in.png");
				$("#control_img_2").attr("src","img/btn/zoom_out.png");
				$("#control_img_3").attr("src","img/btn/move_on.png");
				$("#control_img_11").attr("src","img/btn/info_btn.gif");
				$("#top_btn_10").attr("src","img/btn/top_btn_10.png");
				$("#top_btn_11").attr("src","img/btn/top_btn_11.png");
				_MAINMAP.setMapTool('Panning');
			}else if("control_img_10" == obj){
				$("#control_img_1").attr("src","img/btn/zoom_in.png");
				$("#control_img_2").attr("src","img/btn/zoom_out.png");
				$("#control_img_3").attr("src","img/btn/move.png");
				$("#control_img_10").attr("src","img/btn/setting_on.png");
				$("#control_img_11").attr("src","img/btn/info_btn.gif");
				$("#top_btn_10").attr("src","img/btn/top_btn_10.png");
				$("#top_btn_11").attr("src","img/btn/top_btn_11.png");
				_MAINMAP.toggleMapCtrlVisible();
			}else if("control_img_11" == obj){
				$("#control_img_1").attr("src","img/btn/zoom_in.png");
				$("#control_img_2").attr("src","img/btn/zoom_out.png");
				$("#control_img_3").attr("src","img/btn/move.png");
				$("#control_img_11").attr("src","img/btn/info_btn_on.gif");
				$("#top_btn_10").attr("src","img/btn/top_btn_10.png");
				$("#top_btn_11").attr("src","img/btn/top_btn_11.png");
				_MAINMAP.setMapTool('GWLandInfoTool');
			}else if("top_btn_10" == obj){
				$("#control_img_1").attr("src","img/btn/zoom_in.png");
				$("#control_img_2").attr("src","img/btn/zoom_out.png");
				$("#control_img_3").attr("src","img/btn/move.png");
				$("#control_img_11").attr("src","img/btn/info_btn.gif");
				$("#top_btn_10").attr("src","img/btn/top_btn_10_on.png");
				$("#top_btn_11").attr("src","img/btn/top_btn_11.png");
				_MAINMAP.setMapTool("CalcDist"); 
			}else if("top_btn_11" == obj){
				$("#control_img_1").attr("src","img/btn/zoom_in.png");
				$("#control_img_2").attr("src","img/btn/zoom_out.png");
				$("#control_img_3").attr("src","img/btn/move.png");
				$("#control_img_11").attr("src","img/btn/info_btn.gif");
				$("#top_btn_10").attr("src","img/btn/top_btn_10.png");
				$("#top_btn_11").attr("src","img/btn/top_btn_11_on.png");
				_MAINMAP.setMapTool("CalcArea"); 
			}else if("etc" == obj){
				$("#control_img_1").attr("src","img/btn/zoom_in.png");
				$("#control_img_2").attr("src","img/btn/zoom_out.png");
				$("#control_img_3").attr("src","img/btn/move.png");
				$("#control_img_11").attr("src","img/btn/info_btn.gif");
				$("#top_btn_10").attr("src","img/btn/top_btn_10.png");
				$("#top_btn_11").attr("src","img/btn/top_btn_11.png");
			}else if("reset" == obj){
				$("#control_img_1").attr("src","img/btn/zoom_in.png");
				$("#control_img_2").attr("src","img/btn/zoom_out.png");
				$("#control_img_3").attr("src","img/btn/move.png");
				$("#control_img_11").attr("src","img/btn/info_btn.gif");
				$("#top_btn_10").attr("src","img/btn/top_btn_10.png");
				$("#top_btn_11").attr("src","img/btn/top_btn_11.png");
			}
		},
		submapImgChange : function(obj){
			
			if("submap_ctrl1" == obj){
				$("#submap_ctrl1, #submap_ctrl9").attr("src","img/btn/zoom_in_on.png");
				$("#submap_ctrl2, #submap_ctrl10").attr("src","img/btn/zoom_out.png");
				$("#submap_ctrl3, #submap_ctrl11").attr("src","img/btn/move.png");
				$("#submap_ctrl7, #submap_ctrl15").attr("src","img/btn/info_btn.png");
				$("#submap_ctrl8, #submap_ctrl16").attr("src","img/btn/setting.png");
				_SUBMAP.setMapTool('ZoomIn'); 
			}else if("submap_ctrl2" == obj){
				$("#submap_ctrl1, #submap_ctrl9").attr("src","img/btn/zoom_in.png");
				$("#submap_ctrl2, #submap_ctrl10").attr("src","img/btn/zoom_out_on.png");
				$("#submap_ctrl3, #submap_ctrl11").attr("src","img/btn/move.png");
				$("#submap_ctrl7, #submap_ctrl15").attr("src","img/btn/info_btn.png");
				$("#submap_ctrl8, #submap_ctrl16").attr("src","img/btn/setting.png");
				_SUBMAP.setMapTool('ZoomOut');
			}else if("submap_ctrl3" == obj){
				$("#submap_ctrl1, #submap_ctrl9").attr("src","img/btn/zoom_in.png");
				$("#submap_ctrl2, #submap_ctrl10").attr("src","img/btn/zoom_out.png");
				$("#submap_ctrl3, #submap_ctrl11").attr("src","img/btn/move_on.png");
				$("#submap_ctrl7, #submap_ctrl15").attr("src","img/btn/info_btn.png");
				$("#submap_ctrl8, #submap_ctrl16").attr("src","img/btn/setting.png");
				_SUBMAP.setMapTool('Panning');
			}else if("submap_ctrl7" == obj){
				$("#submap_ctrl1, #submap_ctrl9").attr("src","img/btn/zoom_in.png");
				$("#submap_ctrl2, #submap_ctrl10").attr("src","img/btn/zoom_out.png");
				$("#submap_ctrl3, #submap_ctrl11").attr("src","img/btn/move.png");
				$("#submap_ctrl7, #submap_ctrl15").attr("src","img/btn/info_btn_on.png");
				$("#submap_ctrl8, #submap_ctrl16").attr("src","img/btn/setting.png");
				_SUBMAP.setMapTool('GWLandInfoTool');
			}else if("submap_ctrl8" == obj){
				if($("#submap_ctrl8, #submap_ctrl16").attr("src") == "img/btn/setting.png"){
					$("#submap_ctrl8, #submap_ctrl16").attr("src", "img/btn/setting_on.png");
					_SUBMAP.setMapCtrlVisible(true);
				}else{
					$("#submap_ctrl8, #submap_ctrl16").attr("src", "img/btn/setting.png");
					_SUBMAP.setMapCtrlVisible(false);
				}
				/*$("#submap_ctrl1, #submap_ctrl9").attr("src","img/btn/zoom_in.png");
				$("#submap_ctrl2, #submap_ctrl10").attr("src","img/btn/zoom_out.gif");
				$("#submap_ctrl3, #submap_ctrl11").attr("src","img/btn/move.gif");
				$("#submap_ctrl7, #submap_ctrl15").attr("src","img/btn/info_btn.gif");
				$("#submap_ctrl8, #submap_ctrl16").attr("src","img/btn/settiong_on.gif");*/
				// _SUBMAP.toggleMapCtrlVisible();
			}
		},
		toggleChange : function(obj){
			
			if(obj == "top_btn_12"){
				//그리기도구 활성화
				/*if($("#top_btn_12").attr("src") == "img/btn/top_btn_12.gif"){
					$("#top_btn_12").attr("src", "img/btn/top_btn_12_on.gif");
					$("#control_img_10").attr("src", "img/btn/settiong_on.gif");
					_MAINMAP.setDrawToolVisible(true);
					
				}else{
					$("#top_btn_12").attr("src", "img/btn/top_btn_12.gif");
					$("#control_img_10").attr("src", "img/btn/setting.gif");
					_MAINMAP.setDrawToolVisible(false);
					_MAINMAP.setMapCtrlVisible(false);
				}*/
				if(_MAINMAP.isDrawToolVisible()){
					$("#top_btn_12").attr("src", "img/btn/top_btn_12.png");
					_MAINMAP.setDrawToolVisible(false);
				}else{
					$("#top_btn_12").attr("src", "img/btn/top_btn_12_on.png");
					_MAINMAP.setDrawToolVisible(true);
				}
			}else if(obj == "top_btn_13"){
				//인덱스맵 활성화
				/*if($("#top_btn_13").attr("src") == "img/btn/top_btn_13.gif"){
					$("#top_btn_13").attr("src", "img/btn/top_btn_13_on.gif");
					$("#control_img_10").attr("src", "img/btn/settiong_on.gif");
					_MAINMAP.setIndexMapVisible(true);
				}else{
					$("#top_btn_13").attr("src", "img/btn/top_btn_13.gif");
					$("#control_img_10").attr("src", "img/btn/setting.gif");
					_MAINMAP.setIndexMapVisible(false);
					_MAINMAP.setMapCtrlVisible(false);
				}*/

				if(_MAINMAP.isIndexMapVisible()){
					$("#top_btn_13").attr("src", "img/btn/top_btn_13.gif");
					_MAINMAP.setIndexMapVisible(false);
				}else{
					$("#top_btn_13").attr("src", "img/btn/top_btn_13_on.gif");
					_MAINMAP.setIndexMapVisible(true);
				}
				
			}else if(obj == "control_img_10"){
				//관리도구활성화
				_MAINMAP.toggleMapCtrlVisible();
				/*if($("#control_img_10").attr("src") == "img/btn/setting.png"){
					$("#control_img_10").attr("src", "img/btn/setting_on.png");
					_MAINMAP.toggleMapCtrlVisible();
				}else{
					$("#control_img_10").attr("src", "img/btn/setting.png");
					$("#top_btn_12").attr("src", "img/btn/top_btn_12.png");
					$("#top_btn_13").attr("src", "img/btn/top_btn_13.gif");
					_MAINMAP.setMapCtrlVisible(false);
				}*/
			}
		}
};

//상단의 클릭행위가 이루어 졌을때 세션을 체크한다.
function SessionCHK(){
	$.ajax({
		type: 'POST',
    	url: '../ssesion/session_chk.jsp',
    	dataType: 'xml',
    	success: function(xml){
    		var chk = $(xml).find("chk").text();
    		if(chk != "T"){
    			BrowserClose();
    		}else{
    			CounterReset();
    		}
		},
    	error: function(){
        	alert('Error loading XML document');
    	}
	});
}

function BrowserClose(){
	alert("세션이 종료 되었습니다.\r\n로그인을 다시하여 주십시오!");
	if(opener != undefined){
		if(!opener.closed){
			opener.parent.location = "../login/logout.jsp";	
		}
	}
	if(popId != undefined){
		popId.close();
	}
	if(popup != undefined){
		popup.close();
	}
	self.close();
}


function sessionTimeInit(){
	 tid = setInterval("sessionCounter()",1000);
}

function sessionCounter(){
	$("#counter").text(padZero(parseInt(counter_cnt/60))+":"+padZero(parseInt(counter_cnt%60)));
	//document.all.counter.innerText = padZero(parseInt(counter_cnt/60))+":"+padZero(parseInt(counter_cnt%60));
	counter_cnt--;
    if (counter_cnt < 0) {
        clearInterval(tid);
        SessionCHK();
        //self.location="시험보던폼";
    }
}

function padZero(n) {
    return n>9?n:"0"+n;
}

function CounterReset(){
	 clearInterval(tid);
	 counter_cnt = 30*60;
	 sessionTimeInit();
}
