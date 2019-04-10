<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="geomex.svc.handler.Code"%>
<%@ include file="../ssesion/ssesion.jsp" %>
<%
	String BIZ_ID = "AW001";
	session.setAttribute("SESSION_BIZID", BIZ_ID); //단위업무관리ID를 세션에 담는다.
	Object SESSION_BIZID = session.getAttribute("SESSION_BIZID");
	String session_bizid = (String)SESSION_BIZID;
	String authCheckB = Code.getAuthCheckB2(session_bizid, id, session_ugrp_id); ////사용자 권한체크
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>:: 영광군 농업기반시설물시스템 ::</title>
<link rel="shortcut icon" type="image/x-icon" href="/favicon_1.ico">
<link rel="stylesheet" type="text/css" href="./css/main.css" />
<link rel="stylesheet" type="text/css" href="./css/layout-default-latest.css" />
<link rel="stylesheet" type="text/css" href="./css/layout_style.css" />
<link rel="stylesheet" type="text/css" href="./css/luris.css" />
<script type="text/javascript" src="./script/jquery-1.6.2.js"></script>
<script type="text/javascript" src="./script/jquery.layout-latest.js"></script>
<script type="text/javascript" src="./script/jquery-ui-latest.js"></script>
<script type="text/javascript" src="./script/jquery.event.drag-2.0.min.js"></script>
<script type="text/javascript" src="./script/elementsize.js"></script>
<script type="text/javascript" src="./script/gws.Ctrl.js"></script>
<script type="text/javascript" src="./script/gws.condSrch.js"></script>
<script type="text/javascript" src="./script/gws.pageCtrl.js"></script>
<script type="text/javascript" src="./script/gws.utils.js"></script>
<script type="text/javascript" src="./script/jquery.spin.min.js"></script>
<script type="text/javascript" src="./script/tooltipsy.min.js"></script>
<script type="text/javascript" src="./script/base64.js"></script>
<script type="text/javascript" src="./script/jquery.inputmask.js"></script>
<script type="text/javascript" src="./script/jquery.inputmask.date.extensions.js"></script>
<script type="text/javascript" src="./script/jquery.showLoading.js"></script>
<script type="text/javascript" src="./script/jquery.loading.js"></script>
<script type="text/javascript" src="./script/jquery.outerhtml.js"></script>
<script type="text/javascript" src="./script/jquery.selectboxes.js"></script>
<script type="text/javascript" src="./script/download.jQuery.js"></script>
<script type="text/javascript" src="../geomex/geomex.js"></script>
<script type="text/javascript" src="../geomex/gmx.Ctrl.js"></script>
<script type="text/javascript" src="./script/deployJava.js"></script> 
<!-- 개발용 -->
<SCRIPT type="text/javascript" src="http://openapi.map.naver.com/js/naverMap.naver?key=vRXUlPSleCV7ZZYCs_KM"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="http://map.vworld.kr/js/sopMapInit.js.do?apiKey=2C24BCCE-41A4-310B-B78E-D3E5960B3270"></SCRIPT>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d224b1563e4e1925566cb4ce7cc83085"></script>
<!-- 운영용 -->
<!-- <SCRIPT type="text/javascript" src="http://openapi.map.naver.com/js/naverMap.naver?key=91f2a665fe05d6c65df448541821db2b"></SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript" src="http://map.vworld.kr/js/sopMapInit.js.do?apiKey=A9771D5D-44F3-3AD6-86CE-34CF27A72D45"></SCRIPT>
<SCRIPT type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=35e5000a87473ead64120ac54a64b48214889d99" charset="utf-8"></SCRIPT> -->
                                                                                               <!--025d36798bfd5aca098c74fd5647e4bf  실제운용발급키--> 
<script type="text/javascript">
var _MAINMAP; //메인지도를 담을 변수선언
var _MAPCTRL;
var SESSION_ID = "<%=id%>";  
var SESSION_UGRP_ID = "<%=session_ugrp_id%>"; 
var SESSION_BIZID = "<%=session_bizid%>";


if(SESSION_ID != "null"){ //로그인 아이디가 들어옴
	
	if("<%=authCheckB%>" != "N"){  //admin아이디가 사용자 권한이 DB에 Y로 되어있음 즉 Y != N 가 되는거임
		$(document).ready(function(){
 			
			//_CS.init.srchjibunNewLoding();  //새주소에서 갬색
			_CS.init.srchFacilityLoding(); //새주소에서 시설물 검색
			
			
			gwsUtils.setWorkImgChange('1');  //메인 메뉴 이미지가 변경
			gwsUtils.setMapControlImgChange('4'); //지도 맵 메뉴 이미지가 변경
			
			$(".work_btn").each(function(e){/// work_btn에 순차적으로 하나씩 접근
				var auth = $(this).attr("auth");//해당하는 메뉴에 권한대상아이디를  auth에 담음
				var elem = $(this);
				if(auth != "" && auth != null){ //권한대상아이디가 공백이아니거나 널이 아니면
					var authList = auth.split(",");// 문자열을 , 으로 쪼개기위해
					for(var i=0; i<authList.length; i++){ //현재 배열에 저장된 값의 개수를 구해서 반복문 실행, auth5개이니 i가 5보다 작을때까지
						gwsUtils.checkAuth(authList[i], elem); //업무권한체크
					}
				}
			});
			
			$(".work_btn").hover( //마우스 오버일때와 아웃일때 경우 처리하기위해
				function(){ //마우스가 오버일 경우 실행
					var thisIndex = $(this).index(".work_btn");///work_btn태그중 이벤트가 발생한 work_btn의 인덱스 값을 변수 thisIndex에 할당
					var selected = $(this).attr("select");///속성을select로 변경하고 변수 selected에 할당
					var index = false;
					index = (selected == "true") ? thisIndex : false;
					
					if(index !== thisIndex){
						$(this).attr("src", "./img/btn_top/"+(thisIndex+1)+"_over.png"); /// thisIndex는 이미지 앞 번호가 됨 마우스가 오버되면 이미지가 바뀜
					}
				},function(){ //마우스가 아웃될 경우에 실행
					var thisIndex = $(this).index(".work_btn");
					var selected = $(this).attr("select");
					var index = false;
					index = (selected == "true") ? thisIndex : false;
					
					if(index !== thisIndex){
						$(this).attr("src", "./img/btn_top/"+(thisIndex+1)+"_normal.png");/// thisIndex는 이미지 앞 번호가 됨 마우스가 아웃되면 이미지가 바뀜
					}
				}
			);
			
			$(".bjimg").click(function(){//보조맵 클릭했을때 이벤트 발생
				$(".bjimg").each(function(i){
					$(this).attr("src", "./img/btn/bj"+(parseInt(i)+1)+".png"); //parseInt도 이미지 bj로 시작하는 이미지 클릭하면 변경
				});
				$(this).attr("src", "./img/btn/bj"+(parseInt($(".bjimg").index(this))+1)+"_on.png"); //위와 반대
			});
			
			$("label").click(function(){ //label클릭했을때 이벤트 발생
			    if($(this).attr("for") != ""){
			        $("#" + $(this).attr("for")).click(); 
			    }
			});
			
			$(".bottom_btn").hover( //마우스가  지도메뉴bottom_btn에 오버될을때
				function(){//마우스 오버일때 실행
					var thisIndex = $(this).index(".bottom_btn");///bottom_btn태그중 이벤트가 발생한 bottom_btn의 인덱스 값을 변수 thisIndex에 할당
					var selected = $(this).attr("select");///속성을select로 변경하고 변수 selected에 할당
					var index = false;
					index = (selected == "true") ? thisIndex : false;
					
					if(index !== thisIndex){
						$(this).attr("src", "./img/btn/"+(thisIndex+1)+"_over.png");// thisIndex는 이미지 앞 번호가 됨 마우스가 아웃되면 이미지가 바뀜
					}
				}, function(){//마우스 아웃일때 실행
					var thisIndex = $(this).index(".bottom_btn");//위와 동일
					var selected = $(this).attr("select");
					var index = false;
					index = (selected == "true") ? thisIndex : false; 
					
					if(index !== thisIndex){
						$(this).attr("src", "./img/btn/"+(thisIndex+1)+"_normal.png");
					}
				}
			);
	        //지도메뉴에 마우스 올려놓게되면 세부설명(도움말)이 나오게 하는 부분	
			$('.bottom_btn, .sub_map_control_east, .sub_map_control_south').tooltipsy({
				delay: 0,
			    offset: [10, 0],
			    css: { //제어 세부설명(도움말) 디자인 부분
			        'padding': '5px', //단락 안 여백 5px
			        'max-width': '100px',
			        'color': '#EBEBEB', //세부설명(도움말) 글자색 
			        'font-weight': 'bold', //글자를 굵게
			        'background-color': '#5D636C', //세부설명(도움말) 배경
			        'border': '1px solid #gray', //1px 회색의 실선테두리
			        '-moz-box-shadow': '0 0 10px rgba(0, 0, 0, .5)', 
			        '-webkit-box-shadow': '0 0 10px rgba(0, 0, 0, .5)',
			        'box-shadow': '0 0 10px rgba(0, 0, 0, .5)',
			        'text-shadow': 'none',
			        'cursor': 'default'}
			});
		});
	   //body부분(HTML 문단 태그가 로딩된 후)이 모두 화면에 나온 후 그 뒤에 실행(즉 지도가 화면에 나오는 부분)  여긴 너무어려움 우선 보류
		window.onload = function(){ 
			gmx.Init.prototype = new geomex.GMXMAP("ageomex");
			_MAINMAP = new gmx.Init("ageomex"); //메인지도 애플릿 호출
			_MAINMAP.insertAppletTag("gmxmap_view");//메인지도 애플릿 호출
			sessionTimeInit(); //세션 시간
		};
		function sizeset(){
			_S.utils.autoParentHeight("result_content_box", "result_content", 30);
		}
		 var bannerLeft=0;
		 var first=1;
		 var last;
		 var imgCnt=0;
		 var $img = $(".banner_wraper img");
		 var $first;
		 var $last;

		 $img.each(function(){ // 5px 간격으로 배너 처음 위치 시킴
			 $(this).css("left",bannerLeft);
			 bannerLeft += $(this).width()+5;
			 $(this).attr("id", "banner"+(++imgCnt)); // img에 id 속성 추가
			 });

		 
         if( imgCnt > 9){                //배너 9개 이상이면 이동시킴
             last = imgCnt;
             setInterval(function() {
                 $img.each(function(){
                     $(this).css("left", $(this).position().left-1); // 1px씩 왼쪽으로 이동
                 });
                 $first = $("#RollDiv"+first);
                 $last = $("#RollDiv"+last);
                 if($first.position().left < -200) {    // 제일 앞에 배너 제일 뒤로 옮김
                     $first.css("left", $last.position().left + $last.width()+5 );
                     first++;
                     last++;
                     if(last > imgCnt) { last=1; }   
                     if(first > imgCnt) { first=1; }
                 }
             }, 50);   //여기 값을 조정하면 속도를 조정할 수 있다.(위에 1px 이동하는 부분도 조정하면 
         }		
	}else{
		alert("권한이 없어 시스템을 사용 할 수 없습니다.\n관리자에게 문의해주세요.");
		parent.close();
		window.close();
		self.close();
	}
}else{
	alert("세션이 존재하지 않습니다.\r\n로그인을 다시하여 주십시오!");
	parent.close();
	window.close();
	self.close();
	parent.opener.location.href = "../login/logout.jsp";
}

</script>
</head>
<body>
	<div id="gw_layout-north" class="ui-layout-north">
		<div id="gw_top_bg">
			<div id="gw_top">
				<img id="backImg" src="./img/back/top_bg.png" />
				<div id="gw_logo_box" align="center">
					<img id="logo" src="./img/back/logo.png" style="margin:0 0 0 10px;" onclick=""/>
				</div>
				<div id="gw_controler">
					<div id="gw_top_btn">
						<ul>
						    <li style="margin-left: 25px;"><img src="./img/btn_top/1_normal.png" class="work_btn" onclick="_CS.init.setSearchLinkType('ADDR'); _CS.init.srchFacilityLoding(); gwsUtils.setWorkImgChange('1');" title="시설물검색"/></li>
							<li><img src="./img/btn_top/2_normal.png" class="work_btn" onclick="_CS.init.srchjibunLoding(); gwsUtils.setWorkImgChange('2');" title="주소검색"/></li> 
							<li><img src="./img/btn_top/3_normal.png" class="work_btn" onclick="_CS.init.srchInfoLoding(); gwsUtils.setWorkImgChange('3');" title="속성보기"/></li>
							<li><img src="./img/btn_top/4_normal.png" class="work_btn" onclick="_CS.init.srchPreserveLoding(); gwsUtils.setWorkImgChange('4');" title="영역검색"/></li>
							<li><img src="./img/btn_top/5_normal.png" class="work_btn" onclick="_CS.init.srchBufferLoding(); gwsUtils.setWorkImgChange('5');" title="버퍼검색"/></li>
							<li><img src="./img/btn_top/6_normal.png" class="work_btn" onclick="_CS.init.setOffset(); gwsUtils.setWorkImgChange('6');" title="영상오프셋"/></li>
							<li><img src="./img/btn_top/7_normal.png" class="work_btn" auth="FW002" onclick="_MAINMAP.setLogImgPop(); gwsUtils.setWorkImgChange('7');" title="이미지저장"/></li>
							<li><img src="./img/btn_top/8_normal.png" class="work_btn" auth="FW001" onclick="_MAINMAP.showPrintPop(); gwsUtils.setWorkImgChange('8');" title="프린트"/></li>
							<li><img src="./img/btn_top/9_normal.png" class="work_btn" onclick="_PC.init.Reservoir_pop();" title="저수량 이력관리"/></li>
							<li><img src="./img/btn_top/10_normal.png" class="work_btn" onclick="_MAINMAP.toggleMapCtrlVisible();" title="관리도구"/></li>
							<li><img src="./img/btn_top/div.png" /></li>
							<li style="margin-left: 5px; margin-top: 12px;"><img src="img/sc_2.png" class="divisionIcon" id="divsero" /></li>
							<li style="margin-left: 2px; margin-top: 12px;"><img src="img/sc_3.png" class="divisionIcon" id="divgaro" /></li>
							<li style="margin-top: 12px;"><input type="radio" id="gmxbojo" name="bugamap" value="gmxbojo" onclick="_CON.skill.bugamap(this.value, 1); _MAINMAP.setMapCtrlVisible(false);"/>
							<label for="gmxbojo"><img class="bjimg" src="./img/btn/bj1.png" /></label></li>
							<li style="margin-top: 12px;"><input type="radio" id="daum" name="bugamap" value="daum" onclick="_CON.skill.bugamap(this.value, 1); _MAINMAP.setMapCtrlVisible(false);"/>
							<label for="daum"><img class="bjimg" src="./img/btn/bj2.png" /></label></li>
							<li style="margin-top: 12px;"><input type="radio" id="naver" name="bugamap" value="naver" onclick="_CON.skill.bugamap(this.value, 1); _MAINMAP.setMapCtrlVisible(false);"/>
							<label for="naver"><img class="bjimg" src="./img/btn/bj3.png" /></label></li>
							<li style="margin-top: 12px;"><input type="radio" id="vm" name="bugamap" value="vm" onclick="_CON.skill.bugamap(this.value, 1); _MAINMAP.setMapCtrlVisible(false);"/>
							<label for="vm"><img class="bjimg" src="./img/btn/bj4.png" /></label></li>
							<li style="margin-top: 12px;"><input type="radio" id="roadview" name="bugamap" value="roadview" onclick="_MAINMAP.DaumRoadviewLV('다음로드뷰', true); _MAINMAP.setMapCtrlVisible(false);"/>
							<label for="roadview"><img class="bjimg" src="./img/btn/bj5.png" /></label></li>
							
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="outer-west">
		<!-- 검색조건 -->
		<div id="con_sch_full_box" style="overflow: auto;"></div>
		
		<!-- 검색조건 끝-->	
	</div>
	<div class="outer-center">
	
		<div class="inner-center">
			<div id="map_full_box" style="width:100%; height:100%;">
				<img class="sub_backImg" src="./img/btn/sub_bg2.png" />
				<div id="map_controler_bg" align="center">
					<div style="float: left;">
						<img id="panelHide" class="paneToggle" src="./img/btn/panel_hide.png" onclick="_CON.skill.westPanelClose();" style="cursor: pointer;" title="좌측영역 닫기"/>
						<img id="panelShow" class="paneToggle" src="./img/btn/panel_show.png" onclick="_CON.skill.westPanelOpen();" style="display: none; cursor: pointer;" title="좌측영역 열기" />
					</div>
					<div id="map_scale_box" style="border:0px solid #000000; float: left; text-align:left; margin:3px 0 0 0; width:150px; height:23px;"><!--  background:url(img/scale_back_bg.gif) no-repeat; -->
						<div class="scaleWrap" style="float: left; margin: 5px 3px 0 5px;">
							<img src="img/input_text.png" />
						</div>
						<div class="scaleWrap" style="float: left;">
							<input id="scaletxt" type="text" value="" style="font-weight:bold; border:1px solid #BDBDBD; text-align:left; background-color: #ffffff; height:15px; width:70px;" />
						</div>
						<div class="scaleWrap">
							<img src="img/ok_btn_kkk.png" align="absmiddle" style="margin: 1px 0 0 4px; cursor: pointer;" onclick="_MAINMAP.ScaleMove();" /> <!-- 축적 값 입력 -->
						</div>
						<!--<p style="padding:6px 0 0 0;"><b>1</b> : <input type="text"  style="border:0px; background-color: #ffffff; height:15px; width:70px;" /><span id="scaletxt">0</span></p>-->
					</div>
					<div id="map_controler">
						<ul>
							<li><img src="./img/btn/1_normal.png" class="bottom_btn map_control_btn" onclick="_MAINMAP.setMapTool('ZoomIn'); gwsUtils.setMapControlImgChange('1');" title="확대"/></li>
							<li><img src="./img/btn/2_normal.png" class="bottom_btn map_control_btn" onclick="_MAINMAP.setMapTool('ZoomOut'); gwsUtils.setMapControlImgChange('2');" title="축소"/></li>
							<li><img src="./img/btn/3_normal.png" class="bottom_btn" onclick="_MAINMAP.fullExtent();" title="전체보기"/></li>
							<li><img src="./img/btn/4_normal.png" class="bottom_btn map_control_btn" onclick="_MAINMAP.setMapTool('Panning'); gwsUtils.setMapControlImgChange('4');" title="이동"/></li>
							<li><img src="./img/btn/5_normal.png" class="bottom_btn" onclick="_MAINMAP.setMapTool('Prev');" title="이전"/></li>
							<li><img src="./img/btn/6_normal.png" class="bottom_btn" onclick="_MAINMAP.setMapTool('Next');" title="다음"/></li>
							<li><img src="./img/btn/7_normal.png" class="bottom_btn" onclick="_MAINMAP.clearAddOnScreen();" title="화면지우기"/></li>
							<li><img src="./img/btn/8_normal.png" class="bottom_btn" onclick="_MAINMAP.doClearScreen();" title="화면초기화"/></li>
							<li><img src="./img/btn/9_normal.png" class="bottom_btn map_control_btn" onclick="_MAINMAP.setMapTool('CalcDist'); gwsUtils.setMapControlImgChange('9');" title="거리측정"/></li>
							<li><img src="./img/btn/10_normal.png" class="bottom_btn map_control_btn" onclick="_MAINMAP.setMapTool('CalcArea'); gwsUtils.setMapControlImgChange('10');" title="면적측정"/></li>
						</ul>
					</div>
					<div id="size_arrow_right">
						<img src="img/left_d_btn.gif" onclick="_CON.skill.sizeControlPlus('full');" id="left_d" style="cursor: pointer;"/> <img src="img/left_s_btn.gif" onclick="_CON.skill.sizeControlPlus(0);" id="left_s" style="cursor: pointer; margin:4px 2px 0 0;"/>
					</div>
				</div>
			
				
				
				
				
				<!-- 메인지도 -->
	      		<div id="gmxmap_view"> 
				</div>
				
				
				
			</div> 
		</div>
		<div id="eastbox" class="inner-east"> 
			<div id="east_box" style="width:100%; height:100%;"> 
				<div id="tab_header1" class="headerTab">
					<div style="width:100%; height:30px;">
						<img class="sub_backImg" src="./img/btn/sub_bg2.png" />
						<!-- 창조절 아이콘 -->
						<div id="size_arrow_left"> 
							<img src="img/right_s_btn.gif" onclick="_CON.skill.sizeControlMinus(0);" id="right_s" style="cursor: pointer; margin:4px 0 0 2px;"/> <img src="img/right_d_btn.gif" onclick="_CON.skill.sizeControlMinus('close');" id="right_d" style="cursor: pointer;"/>
						</div>
						<!--<a href="#"onclick="_SMAP.mapsync.vmImportKml();">kml</a>-->
						<div id="syc_box_east">
							<!-- <p style="padding:0 2px 0 2px;"><img id="syc_btn1" src="img/syc_btn1.gif" style="margin-top:2px;" onclick="_MAP.SUBGMXMAP.syncMAPCtrl(1);" title="지도동기화"/> <img id="syc_btn2" src="img/syc_btn2_on.gif" onclick="_MAP.SUBGMXMAP.syncMAPCtrl(2);" title="한쪽만 동기화"/> <img id="syc_btn3" src="img/syc_btn3.gif" onclick="_MAP.SUBGMXMAP.syncMAPCtrl(3);" title="해제"/></p> -->
							<p style="padding:0 2px 0 2px;"><img id="syc_btn1" src="img/syc_btn1.gif" style="margin-top:2px;" onclick="_MAP.SUBGMXMAP.syncMAPCtrl(1);" title="지도동기화"/> <img id="syc_btn2" style="margin-top:2px;" src="img/syc_btn2_on.gif" onclick="_MAP.SUBGMXMAP.syncMAPCtrl(2);" title="한쪽만 동기화"/> <img id="syc_btn3" style="margin-top:2px;" src="img/syc_btn3.gif" onclick="_MAP.SUBGMXMAP.syncMAPCtrl(3);" title="해제"/></p>
						</div>
						<div id="subgmx_ctrl_east">
							<img class="sub_backImg" src="./img/btn/sub_bg2.png" />
							<ul>
								<li style="list-style: none; float: left;"><img src="img/btn/1_normal.png" style="cursor: pointer;" class="sub_map_control_east sub_map_control_btn" onclick="_SUBMAP.setMapTool('ZoomIn'); gwsUtils.setSubMapControlImgChange(this, '1','east');" title="확대"/></li>
								<li style="list-style: none; float: left;"><img src="img/btn/2_normal.png" style="cursor: pointer;" class="sub_map_control_east sub_map_control_btn" onclick="_SUBMAP.setMapTool('ZoomOut'); gwsUtils.setSubMapControlImgChange(this, '2','east');" title="축소"/></li>
								<li style="list-style: none; float: left;"><img src="img/btn/3_normal.png" style="cursor: pointer;" class="sub_map_control_east" onmouseover="this.src='img/btn/3_over.png'" onmouseout="this.src='img/btn/3_normal.png'" onclick="_SUBMAP.fullExtent();" title="전체보기"/></li>
								<li style="list-style: none; float: left;"><img src="img/btn/4_on.png" style="cursor: pointer;" class="sub_map_control_east sub_map_control_btn" onclick="_SUBMAP.setMapTool('Panning'); gwsUtils.setSubMapControlImgChange(this, '4','east');" title="이동"/></li>
								<li style="list-style: none; float: left;"><img src="img/btn/5_normal.png" style="cursor: pointer;" class="sub_map_control_east" onmouseover="this.src='img/btn/5_over.png'" onmouseout="this.src='img/btn/5_normal.png'" onclick="_SUBMAP.setMapTool('Prev');" title="이전"/></li>
								<li style="list-style: none; float: left;"><img src="img/btn/6_normal.png" style="cursor: pointer;" class="sub_map_control_east" onmouseover="this.src='img/btn/6_over.png'" onmouseout="this.src='img/btn/6_normal.png'" onclick="_SUBMAP.setMapTool('Next');" title="다음"/></li>
							</ul>
						</div>
						<!-- <div id="w_tab"><img id="tab_bojo1" src="img/tab_bojo_1.gif" usemap="#tab_bojo_w"/></div> -->
					</div>
				</div>
				<div id="eastContent" style="overflow: auto;"></div>
			</div>
		</div>
		<div id="southbox"  class="inner-south">
			<div id="south_box" style="width:100%; height:100%;">
				<div id="tab_header2" class="headerTab">
					<div style="width:100%; height:30px;">
						<img class="sub_backImg" src="./img/btn/sub_bg2.png" /> <!-- 서브메뉴 바 이미지 -->
						<div id="size_arrow_height">
							<img src="img/height_d_btn_1.gif" onclick="_CON.skill.sizeControlPlus('full');" id="left_d_height" style="cursor: pointer; margin-top:4px;"/>
							<img src="img/height_s_btn_2.gif" onclick="_CON.skill.sizeControlPlus(0);" id="left_s_height" style="cursor: pointer;"/>
							<img src="img/height_s_btn_3.gif" onclick="_CON.skill.sizeControlMinus(0);" id="right_s_height" style="cursor: pointer;"/>
							<img src="img/height_d_btn_4.gif" onclick="_CON.skill.sizeControlMinus('close');" id="right_d_height" style="cursor: pointer;"/>
							<!--<a href="#"onclick="_SMAP.mapsync.vmImportKml();">kml</a>-->
						</div>
						<div id="syc_box_south">
							<p style="padding:0 2px 0 2px;"><img id="syc_btn4" src="img/syc_btn1.gif" onclick="_MAP.SUBGMXMAP.syncMAPCtrl(1);" title="지도동기화" style="margin-top:2px;"/> <img id="syc_btn5" src="img/syc_btn2_on.gif"  onclick="_MAP.SUBGMXMAP.syncMAPCtrl(2);" style="margin-top:2px;" title="한쪽만 동기화"/> <img id="syc_btn6" src="img/syc_btn3.gif" style="margin-top:2px;" onclick="_MAP.SUBGMXMAP.syncMAPCtrl(3);" title="해제"/></p>
						</div>
						<div id="subgmx_ctrl_south">
							<img class="sub_backImg" src="./img/btn/sub_bg2.png" /> <!-- 서브메뉴 바 이미지 -->
							<ul>
								<li style="list-style: none; float: left;"><img src="img/btn/1_normal.png" style="cursor: pointer;" class="sub_map_control_south sub_map_control_btn_south" onclick="_SUBMAP.setMapTool('ZoomIn'); gwsUtils.setSubMapControlImgChange(this, '1','south');" title="확대"/></li>
								<li style="list-style: none; float: left;"><img src="img/btn/2_normal.png" style="cursor: pointer;" class="sub_map_control_south sub_map_control_btn_south" onclick="_SUBMAP.setMapTool('ZoomOut'); gwsUtils.setSubMapControlImgChange(this, '2','south');" title="축소"/></li>
								<li style="list-style: none; float: left;"><img src="img/btn/3_normal.png" style="cursor: pointer;" class="sub_map_control_south" onmouseover="this.src='img/btn/3_over.png'" onmouseout="this.src='img/btn/3_normal.png'" onclick="_SUBMAP.fullExtent();" title="전체보기"/></li>
								<li style="list-style: none; float: left;"><img src="img/btn/4_on.png" style="cursor: pointer;" class="sub_map_control_south sub_map_control_btn_south" onclick="_SUBMAP.setMapTool('Panning'); gwsUtils.setSubMapControlImgChange(this, '4','south');" title="이동"/></li>
								<li style="list-style: none; float: left;"><img src="img/btn/5_normal.png" style="cursor: pointer;" class="sub_map_control_south" onmouseover="this.src='img/btn/5_over.png'" onmouseout="this.src='img/btn/5_normal.png'" onclick="_SUBMAP.setMapTool('Prev');" title="이전"/></li>
								<li style="list-style: none; float: left;"><img src="img/btn/6_normal.png" style="cursor: pointer;" class="sub_map_control_south" onmouseover="this.src='img/btn/6_over.png'" onmouseout="this.src='img/btn/6_normal.png'" onclick="_SUBMAP.setMapTool('Next');" title="다음"/></li>
							</ul>
						</div>
						<!-- <div id="h_tab"><img id="tab_bojo2" src="img/tab_bojo_1.gif" usemap="#tab_bojo_h"/></div> -->
					</div>
				</div>
				<div id="southContent" style="overflow: auto;"></div>
			</div>
		</div>
	</div>
	
	<!-- 브이월드 지도 생성박스 -->
	<div id="vworldViewBox" style="border:0px solid #000000; position: absolute; display:none; top:0; left:0; z-index:30; width:500px; height:500px;"></div>
	
	
<script type="text/javascript">
	$("body").click(function(e){
		//SessionCHK();
		CounterReset();
	});
</script>
	
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////// -->	
<map name="tab_bojo_w">
    <area shape="rect" coords="0,1,69,22" href="#" target="" alt="" onclick="_CON.skill.maptab('map', 'w_div');"/>
    <area shape="rect" coords="74,1,143,23" href="#" target="" alt="" onclick="_CON.skill.maptab('att','w_div');"/>
</map>
<map name="tab_bojo_h">
    <area shape="rect" coords="0,1,69,22" href="#" target="" alt="" onclick="_CON.skill.maptab('map', 'h_div');"/>
    <area shape="rect" coords="74,1,143,23" href="#" target="" alt="" onclick="_CON.skill.maptab('att','h_div');"/>
</map>	
<!-- <map name="srch_tab">
    <area shape="rect" coords="0,0,105,27" href="#" onclick="_CS.init.srchjibunLoding(2);" onfocus="this.blur();" alt="지번검색" />
    <area shape="rect" coords="112,2,216,27" href="#" onclick="_CS.init.srchRoadAddrLoding();" onfocus="this.blur();" alt="도로명검색" />
</map>
 -->
<map name="srch_tab">
    <area shape="rect" coords="0,0,105,27" href="#" onclick="_CS.init.srchFacilityLoding();" onfocus="this.blur();" alt="시설물검색" />
    <area shape="rect" coords="112,2,216,27" href="#" onclick="_CS.init.srchjibunLoding(2);" onfocus="this.blur();" alt="지번검색" />
</map>

<map name="area_tab">
    <area shape="rect" coords="0,0,105,27" href="#" onclick="_CON.skill.menuimgChange('top_btn_7');" onfocus="this.blur();" alt="영역검색" />
    <area shape="rect" coords="112,2,216,27" href="#" onclick="_CON.skill.menuimgChange('top_btn_8');" onfocus="this.blur();" alt="버퍼검색" />
</map>

<map name="toji_tab">
    <area shape="rect" coords="0,1,44,22" href="#"  id="Tab_1_menu"  alt="기본정보" onclick="_PC.init.tojiBasic();" onfocus='this.blur();'/>
    <area shape="rect" coords="48,1,100,22" href="#" id="Tab_2_menu" alt="토지대장" onclick="_PC.init.tojiDaejang();" onfocus='this.blur();'/>
    <area shape="rect" coords="104,1,160,22" href="#" id="Tab_3_menu" alt="건축물대장" onclick="_PC.init.bldgDaejang();" onfocus='this.blur();'/>
    <area shape="rect" coords="167,2,251,23" href="#" id="Tab_4_menu" alt="행위제한법령정보" onclick="_PC.init.tojiUseplan();"  onfocus='this.blur();'/>
    <area shape="rect" coords="258,0,354,23" href="#" id="Tab_5_menu" alt="지역.지구별 행위제한" onclick="_PC.init.rdAct();" onfocus='this.blur();'/>
    <area shape="rect" coords="364,1,473,23" href="#" id="Tab_6_menu" alt="조건별행위제한가능여부" onclick="_PC.init.tojiAct();" onfocus='this.blur();'/>
</map>

<!-- 지도 프린트시 사용 하는 폼 -->
<form name="reform" action="" target="proposal_pop" method="post">
	<input type="hidden" name ="cfgxml" value="">
	<input type="hidden" name ="addon" value="">
	<input type="hidden" name ="minx" value="">
	<input type="hidden" name ="miny" value="">
	<input type="hidden" name ="maxx" value="">
	<input type="hidden" name ="maxy" value="">
	<input type="hidden" id ="titleName" name ="titleName" value="">
	<input type="hidden" id ="mapName" name ="mapName" value="">
</form>
</body>
</html>






