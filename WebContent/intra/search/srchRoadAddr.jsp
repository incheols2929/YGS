<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
//enter 적용
/*$("#sgg, #road, #buld_bon, #buld_bu").keypress(function(e) {
    var code=(e.keyCode?e.keyCode:e.which);
    if(code==13) { // Enter keycode
    	_CS.init.DoroReset(0);
    }
});*/

$("#buld_bon, #buld_bu").focus(function(e) {
	$("#svh_btn_img").css({"border":"0px dashed red"});
});

$("#buld_bon").keypress(function(e) {
    var code=(e.keyCode?e.keyCode:e.which);
    if(code==13) { // Enter keycode
    	$("#buld_bu").focus();
    }
});

$("#buld_bu").keypress(function(e) {
    var code=(e.keyCode?e.keyCode:e.which);
    if(code==13) { // Enter keycode
    	$("#svh_btn_img").css({"border":"1px dashed red"});
    	$("#hidden_btn").focus();
    }
});

$("#roadMoveBTN").click(function(){
	if($("#road").val().length === 0){ //도로명이 아무것도 선택되어있지 않다면
		alert("도로명을 선택해 주세요!"); //이 메시지가 출력
		$("#road").focus();
	}else{
		_MAINMAP.searchAndMoveWhere("도로구간","rn_cd='"+$("#road").val()+"'");  // 레이어에서 조건쿼리를 만족하는 객체를 하이라이트하고 영역으로 이동한다.(인자: 레이어명, 조건문)
	}
});

</script>

<div id="top_tab" style="height:30px;">
	<img src="img/tab_img/tab1_2.png" width="100%" usemap="#srch_tab">
</div>
<div id="jibun_sch_box" style="width:100%; height:167px; border:0px solid #000000;">
	<div style="height:24px; border:0px solid #000000; margin-top:5px;">
		<ul>
			<li style="float: left; width:60px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 시군</p></li>
			<li style="float: left; width:207px;list-style: none;">
				<select id="sgg" name="sgg" style="width:205px;">
					<option value="">:: 선택 하여 주십시오! ::</option>
				</select>
			</li>
		</ul>
	</div>
	<div style="height:24px; border:0px solid #000000;">
		<ul>
			<li style="float: left; width:60px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 도로명</p></li>
			<li style="float: left; width:207px;list-style: none; text-align: center; ">
				<p style="padding:4px 0 0 0; font-size: 10px; font-family: gullim,  굴림;">
					<a href="#" onclick="_CS.init.GetRoad('ㄱ');"><b>ㄱ</b></a> 
					<a href="#" onclick="_CS.init.GetRoad('ㄴ');"><b>ㄴ</b></a>
					<a href="#" onclick="_CS.init.GetRoad('ㄷ');"><b>ㄷ</b></a>
					<a href="#" onclick="_CS.init.GetRoad('ㄹ');"><b>ㄹ</b></a>
					<a href="#" onclick="_CS.init.GetRoad('ㅁ');"><b>ㅁ</b></a>
					<a href="#" onclick="_CS.init.GetRoad('ㅂ');"><b>ㅂ</b></a>
					<a href="#" onclick="_CS.init.GetRoad('ㅅ');"><b>ㅅ</b></a>
					<a href="#" onclick="_CS.init.GetRoad('ㅇ');"><b>ㅇ</b></a>
					<a href="#" onclick="_CS.init.GetRoad('ㅈ');"><b>ㅈ</b></a>
					<a href="#" onclick="_CS.init.GetRoad('ㅊ');"><b>ㅊ</b></a>
					<a href="#" onclick="_CS.init.GetRoad('ㅋ');"><b>ㅋ</b></a>
					<a href="#" onclick="_CS.init.GetRoad('ㅌ');"><b>ㅌ</b></a>
					<a href="#" onclick="_CS.init.GetRoad('ㅍ');"><b>ㅍ</b></a>
					<a href="#" onclick="_CS.init.GetRoad('ㅎ');"><b>ㅎ</b></a>
				</p>
			</li>
		</ul>
	</div>
	<div style="height:24px; border:0px solid #000000;">
		<ul>
			<li style="float: left; width:60px; list-style: none;"><p style="padding:5px"></p></li>
			<li style="float: left; width:207px;list-style: none;">
				<select id="road" name="road" style="float: left; width:155px;">
					<option value="">:: 선택 하여 주십시오! ::</option>
				</select>
				<button id="roadMoveBTN" class="grayBTN" style="margin-top: -3px;">위치</button>
			</li>
		</ul>
	</div>
	<div style="height:24px; border:0px solid #000000;">
		<ul>
			<li style="float: left; width:60px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 건물번호</p></li>
			<li style="float: left; width:207px;list-style: none;">
				<p style="padding:3px 0 0 0;">
					<select id="buld_se_cd" name="buld_se_cd" style="width: 50px; vertical-align: middle;">
						<option value="0" selected="selected">지상</option>
						<option value="1">지하</option>
						<option value="2">공중</option>
					</select>
					<input type="text" id="buld_bon" name="buld_bon" maxlength="4" style="width:40px;" /> - <input type="text" id="buld_bu" name="buld_bu" maxlength="4" style="width:40px;" />
				</p>
			</li>
		</ul>
	</div>
	<div style="border:0px solid #000000; text-align: center; margin-top:3px;">
		
		<a id="hidden_btn" href="#" onclick="_CS.init.DoroReset(0);"><img id="svh_btn_img" src="img/sch_btn.gif" style="cursor: pointer;"  onmouseover="this.src='img/sch_btn_on.gif'"  onmouseout="this.src='img/sch_btn.gif'"/></a>
	</div>
	<div style="height:35px; border:1px solid #CAE0E5; margin-top:3px; text-align: center; background-color: #E5F5F9;">
		<p id="help_txt" style="padding:10px; color: #5A9BAB; font-weight: bold;" id="messg_txt">* 도로명주소로 검색 합니다.</p>
	</div>
</div>
<!-- <div id="sch_line"></div> -->
<div id="sch_result">
	<div id="" style="height:21px; border:0px solid #000000;"><img src="img/sch_result.png"/></div>
	<div id="result_content_box" style="border:1px solid #c9c8c8; background-color: #f7f6f6; height:150px;">
		<div id="result_content" style="position:relative; padding:2px; border:0px solid #000000; overflow: auto;">
			<div style='border: 1px solid #E6BBB3; text-align:center; width:80%; background-color: #FDE8E4; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>
				<p style="padding: 20px 0 0 0;color: #CF4425;font-weight: bold;">검색조건을 입력하여 주십시오!</p>
			</div>
		</div>
		<div id="page_list" style=" text-align: center">
			<p id="total_page"  style="padding:3px 0 0 0 "><!-- <img src="img/prev.gif" align="absmiddle"/> <b>1</b> 2 3 4 5 <img src="img/next.gif"  align="absmiddle"/> --></p>
		</div>
	</div>
</div>
<!-- <div id="sch_line" style="margin-top:5px;"></div> -->
<div id="detail_info" style="display:none; border:0px solid #000000;"></div>