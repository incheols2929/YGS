<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 키이벤트 스크립트 -->
<!-- <script type="text/javascript" src="./script/gws.formenter.js"></script>-->
<script type="text/javascript">

$("#bon, #bu, #san").focus(function(e) {//bon,bu,san에 포커스가 갔을때 발생
	$("#svh_btn_img").css({"border":"0px dashed red"}); //확인 버튼
});

$("#bon").keypress(function(e) {//bon(요소)에 키보드가 눌렷을때 발생
    var code=(e.keyCode?e.keyCode:e.which);
    if(code==13) { // Enter keycode
    	$("#bu").focus();
    }
});

$("#bu").keypress(function(e) {//bu(요소)에 키보드가 눌렸을때 발쌩
    var code=(e.keyCode?e.keyCode:e.which);
    if(code==13) { // Enter keycode
    	$("#san").focus();
    }
});

$("#san").keypress(function(e) {
    var code=(e.keyCode?e.keyCode:e.which);
    if(code==13) { // Enter keycode
    	$("#svh_btn_img").css({"border":"1px dashed red"});
    	$("#hidden_btn").focus();
    }
});

$("#decode").focus(function(){ 
	if($("#sgg").val().length === 0){
		$("#sgg").focus();
	}
});


</script>

<div id="top_tab" style="height:30px;">
	<img src="img/tab_img/tab1_1.png" width="100%" usemap="#srch_tab">
</div>
 <!-- 저수지이력관리 -->
<!--  <div style="height:34px; width:60px; margin-top:4px;">
 	<img src="/facility/img/reservoirbt.png" onclick="_PC.init.Reservoir_pop();"/>
 </div> --> 
<div id="jibun_sch_box" style="width:100%; height:167px; border:0px solid #000000; margin-top:5px;">
	
	<div style="height:24px; border:0px solid #000000; margin-top:5px;">
		<ul>
			<li style="float: left; width:60px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 시군</p></li>
			<li style="float: left; width:207px;list-style: none;">
				<select id="sgg" name="sgg" style="width:205px;" onchange="_CS.init.GetUMD2(this.value); _CS.init.GetCodeValue(this.value);">
					<option value=''>:: 선택 하여 주십시오! ::</option>
				</select>
			</li>
		</ul>
	</div>
	
	<div style="height:24px; border:0px solid #000000;">
		<ul>
			<li style="float: left; width:60px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 읍면동</p></li>
			<li style="float: left; width:207px;list-style: none;">
				<select id="umd" name="umd" style="width:205px;" onchange="_CS.init.GetRI2(this.value);_CS.init.GetFacility(this.value);_CS.init.GetCodeView(this.value);">
					<option value=''>:: 선택 하여 주십시오! ::</option>
				</select>
			</li>
		</ul>
	</div>
	
	<div style="height:24px; border:0px solid #000000;">
		<ul>
			<li style="float: left; width:60px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 리</p></li>
			<li style="float: left; width:207px;list-style: none;">
				<select id="ri" name="ri" style="width:205px;" onchange="_CS.init.GetFacility(this.value);_CS.init._CS.init.GetCodeView(this.value);">
					<option value=''>:: 선택 하여 주십시오! ::</option>
				</select>
			</li>
		</ul>
	</div>
	
	<div style="height:24px; border:0px solid #000000; margin-top:5px;">
		<ul>
			<li style="float: left; width:60px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 시설물</p></li>
			<li style="float: left; width:207px;list-style: none;">
				<select id="facility" name="facility" style="width:205px;" onchange=" _CS.init.GetCodeValue(this.value);">
					<option value=''>:: 선택 하여 주십시오! ::</option>
				</select> 
			</li>
		</ul>
	</div>
	
    <div style="height:24px; border:0px solid #000000;">
		<ul>
			<li style="float: left; width:60px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 시설명</p></li>
			<input type="text" id="ftr_nm" name="ftr_nm" maxlength="50" value="" style="width:202px;" />
		</ul>
     </div>
    
	
	<div style="border:0px solid #000000; text-align: center; margin-top:3px;">
		<a id="hidden_btn" href="#" onclick="_CS.init.facilitySrch();"><img id="svh_btn_img" src="img/sch_btn.gif" style="cursor: pointer;"  onmouseover="this.src='img/sch_btn_on.gif'"  onmouseout="this.src='img/sch_btn.gif'"/></a>
	</div>
	
	
	
	<div style="height:35px; border:1px solid #CAE0E5; margin-top:3px; text-align: center; background-color: #E5F5F9;">
		<p id="help_txt" style="padding:10px; color: #5A9BAB; font-weight: bold;" id="messg_txt">* 지번주소로 검색 합니다.</p>
	</div>
	
</div>
<!-- <div id="sch_line"></div> -->
<div id="sch_result">
	<div id="" style="height:21px; border:0px solid #000000;"><img src="img/sch_result.png"/></div>
	<div id="result_content_box" style="border:1px solid #c9c8c8;  background-color: #f7f6f6;  height:90px;">
		<div id="result_content" style="position:relative; padding:2px; border:0px solid #000000; overflow: auto;">
			<div style='border: 1px solid #E6BBB3; text-align:center; width:80%; background-color: #FDE8E4; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>
				<p style="padding: 20px 0 0 0;color: #CF4425;font-weight: bold;">검색조건을 입력하여 주십시오!</p>
			</div>
		</div>
		<div id="page_list" style=" text-align: center">
			<p id="total_page" style="padding:3px 0 0 0 "></p>
		</div> 
	</div>
	
</div>
<div id="detail_info" style="display:none; border:0px solid #000000;">
</div> 
