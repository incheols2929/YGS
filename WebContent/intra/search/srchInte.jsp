<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- script type="text/javascript">
//enter 적용
$("#f_layer_name, #layer_item, #cond_type, #included, #match, #range_1, #range_2").keypress(function(e) {
    var code=(e.keyCode?e.keyCode:e.which);
    if(code==13) { // Enter keycode
    	_CS.init.InteReset();
    }
});
</script-->

<!-- 동합검색 -->
<div id="top_tab" style="border-bottom:1px solid #d2d2d2; height:26px;">
	<img src="img/tab_tong.gif" style="margin-top:2px;">
</div>
<div id="jibun_sch_box" style="width:100%; height:175px; border:0px solid #000000;">
	<div style="height:24px; border:0px solid #000000; margin-top:3px;">
		<ul>
			<li style="float: left; width:75px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 대상레이어</p></li>
			<li style="float: left; width:189px;list-style: none;">
				<!-- select id="layer_name" name="layer_name" style="width:189px;" onchange="_CS.init.GetLyrInfo(this.value);">
					<option value=''>:: 선택 하여 주십시오! ::</option>
				</select -->
				<select id="f_layer_name" name="f_layer_name" style="width:189px;" onchange="_CS.init.GetLyrInfo(this.value);">
					<option value=''>:: 선택 하여 주십시오! ::</option>
				</select>
			</li>
		</ul>
	</div>
	<div id="general_box" style="border:0px solid #000000; display:block;">
		<div style="height:24px; border:0px solid #000000;">
			<ul>
				<li style="float: left; width:75px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 대상항목</p></li>
				<li style="float: left; width:189px;list-style: none;">
					<select id="layer_item" name="layer_item" style="width:189px;">
						<option value=''>:: 선택 하여 주십시오! ::</option>
					</select>
				</li>
			</ul>
		</div>
		<div style="height:24px; border:0px solid #000000;">
			<ul>
				<li style="float: left; width:75px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 조건</p></li>
				<li style="float: left; width:189px;list-style: none;">
					<select id="cond_type" name="cond_type" style="width:189px;" onchange="_CS.init.termsChange(this.value);">
						<option value="">:: 선택 하여 주십시오! ::</option>
						<option value="INCLUDED">포함조건</option>
						<option value="MATCH">일치조건</option>
						<option value="RANGE">범위조건</option>
					</select>
				</li>
			</ul>
		</div>
		<div style="border:0px solid #000000; display:block;">
			<div id="included_box" style="border:0px solid #000000; width:100%; display:none;">
				<p style="padding:0 0 0 74px;"><input type="text"  style="width:95px;" id="included" name="included" value=""/> 을(를) 포함한다.</p>
			</div>
			<div id="match_box" style="border:0px solid #000000; width:100%; display:none;">
				<p style="padding:0 0 0 74px;"><input type="text"  style="width:95px;" id="match" name="match" value=""/> 과(와) 일치한다.</p>
			</div>
			<div id="range_box" style="border:0px solid #000000; width:100%; display:none;">
				<p style="padding:0 0 0 74px;"><input type="text"  style="width:95px;" id="range_1" name="range_1" value=""/> 보다 크거나 같고</p>
				<p style="padding:2px 0 0 74px;"><input type="text"  style="width:95px;" id="range_2" name="range_2" value=""/> 보다 작거나 같다.</p>
			</div>
		</div>
	</div>
	<!-- 연속지적의 경우 검색박스를 다르게 설정한다. -->
	<div id="jijuk_srch_box" style="border:0px solid #000000; display:none; position: relative; ">
		<div style="height:24px; border:0px solid #000000;">
			<ul>
				<li style="float: left; width:75px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 행정구역</p></li>
				<li style="float: left; width:189px;list-style: none;">
					<select id="sgg" name="sgg" style="width:189px;" onchange="_CS.init.GetUMD(this.value);">
						<option value=''>:: 시군구 ::</option>
					</select>
				</li>
			</ul>
		</div>
		<div style="height:24px; border:0px solid #000000;">
			<ul>
				<li style="float: left; width:75px; list-style: none;"><p style="padding:5px"></li>
				<li style="float: left; width:189px;list-style: none;">
					<select id="umd" name="umd" style="width:189px;" onchange="_CS.init.GetRI(this.value);">
						<option value=''>:: 읍면동 ::</option>
					</select>
				</li>
			</ul>
		</div>
		<div style="height:24px; border:0px solid #000000;">
			<ul>
				<li style="float: left; width:75px; list-style: none;"><p style="padding:5px"></li>
				<li style="float: left; width:189px;list-style: none;">
					<select id="ri" name="ri" style="width:189px;">
						<option value=''>:: 리 ::</option>
					</select>
				</li>
			</ul>
		</div>
		<div style="height:24px; border:0px solid #000000;">
			<ul>
				<li style="float: left; width:75px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 소유구분</p></li>
				<li style="float: left; width:189px;list-style: none;">
					<select id="owngb" name="owngb" style="width:189px;">
						<option value="">:: 선택 하여 주십시오! ::</option>
					</select>
				</li>
			</ul>
		</div>
		<div style="height:24px; border:0px solid #000000;">
			<ul>
				<li style="float: left; width:75px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 면적</p></li>
				<li style="float: left; width:189px;list-style: none;">
					<input type="text"  style="width:45px;" id="area1" name="area1" value=""/> 보다크고 <input type="text"  style="width:45px;" id="area2" name="area2" value=""/> 작다
				</li>
			</ul>
		</div>
		<div style="height:24px; border:0px solid #000000;">
			<ul>
				<li style="float: left; width:75px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 지목</p></li>
				<li style="float: left; width:189px;list-style: none;">
					<select id="jimok" name="jimok" style="width:189px;">
						<option value="">:: 선택 하여 주십시오! ::</option>
					</select>
				</li>
			</ul>
		</div>
		<div style="height:24px; border:0px solid #000000;">
			<ul>
				<li style="float: left; width:75px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 용도지역</p></li>
				<li style="float: left; width:189px;list-style: none;">
					<select id="uznecode1" name="uznecode1" style="width:189px;" onchange="_CS.init.GetUzneCodeJoong(this.value);">
						<option value=''>:: 대분류 ::</option>
					</select>
				</li>
			</ul>
		</div>
		<div style="height:24px; border:0px solid #000000;">
			<ul>
				<li style="float: left; width:75px; list-style: none;"><p style="padding:5px"></p></li>
				<li style="float: left; width:189px;list-style: none;">
					<select id="uznecode2" name="uznecode2" style="width:189px;" onchange="_CS.init.GetUzneCodeSo(this.value);">
						<option value=''>:: 중분류 ::</option>
					</select>
				</li>
			</ul>
		</div>
		<div style="height:24px; border:0px solid #000000;">
			<ul>
				<li style="float: left; width:75px; list-style: none;"><p style="padding:5px"></p></li>
				<li style="float: left; width:189px;list-style: none;">
					<select id="uznecode3" name="uznecode3" style="width:189px;">
						<option value=''>:: 소분류 ::</option>
					</select>
				</li>
			</ul>
		</div>
		<div style="height:24px; border:0px solid #000000;">
			<ul>
				<li style="float: left; width:75px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 공시지가</p></li>
				<li style="float: left; width:189px;list-style: none;">
					<select id="base_year" name="base_year" style="width:189px;">
						<option value=''>::기준년도::</option>
					</select>
				</li>
			</ul>
		</div>
		<div style="height:24px; border:0px solid #000000;">
			<ul>
				<li style="float: left; width:75px; list-style: none;"><p style="padding:5px"></p></li>
				<li style="float: left; width:189px;list-style: none;">
					<input type="text"  style="width:45px;" id="jiga1" name="jiga1" value=""/> 보다크고 <input type="text"  style="width:45px;" id="jiga2" name="jiga2" value=""/> 작다
				</li>
			</ul>
		</div>
	</div>
	
	<div style="border:0px solid #000000; text-align: center; margin-top:3px;">
		<a id="hidden_btn" href="#" onclick="_CS.init.InteReset();"><img id="svh_btn_img" src="img/sch_btn.gif" style="cursor: pointer;"  onmouseover="this.src='img/sch_btn_on.gif'"  onmouseout="this.src='img/sch_btn.gif'"/></a>
	</div> <!-- onclick="_CS.init.GetIntegration();" -->
	<div style="height:22px; border:1px solid #d1d1d1; margin-top:3px; text-align: center; background-color: #f7f7f7;">
		<p id="mess_txt_box" style="padding-top:5px;">* 대상레이어는  필수 선택항목 입니다.</p>
	</div>
</div>
<div id="sch_line"></div>
<div id="sch_result">
	<div id="" style="height:21px; border:0px solid #000000;"><img src="img/sch_result.png"/> <img id="detail_btn" src="img/detail_btn.gif" style="margin-left:128px; cursor: pointer; display:none;" onclick=""/><!-- <a id="detail_btn" href="#" onclick="_CS.init.getLryInteJijukpage(1);">상세보기</a><!-- a href="#" onclick="_CS.init.getLryIntepage(1);">상세보기</a--></div>
	<div id="inte_result_content_box" style="border:1px solid #c9c8c8; background-color: #f7f6f6; height:150px;">
		<div id="top_subject" style="height:25px; padding:2px; border:0px solid #000000;">
			<table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" bgcolor="#dcefff" style="border:1px solid #b0b0b0">
				<tr align="center">
					<!-- <td width="25%" style="border-right:1px solid #b0b0b0;"><b>관리코드</b></td>-->
					<td width="100%"><b>결과값</b></td>
				</tr>
			</table>
		</div>
		<div id="inte_result_content" style="padding:2px; border:0px solid #000000; overflow: auto;  position: relative; ">
			<div style='border:2px dotted red; text-align:center; width:80%; background-color:#ffffff; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>
				<p style='padding: 20px 0 0 0'>검색조건을 입력하여 주십시오!</p>
			</div>
		</div>
		<div id="page_list" style="border:0px solid #000000; text-align: center">
			<p id="total_page" style="padding:3px 0 0 0 "><!-- <img src="img/prev.gif" align="absmiddle"/> <b>1</b> 2 3 4 5 <img src="img/next.gif"  align="absmiddle"/> --></p>
		</div>
	</div>
</div>

<!--<div id="sch_line" style="margin-top:5px;"></div>
<div id="detail_info" style="display:none; border:0px solid #000000;">
		<div id='' style='height:21px; border:0px solid #000000;'>
			<div style='float: left;'><img src='img/sang_info.gif'/></div>
		</div>
		<div>
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="27" bgcolor="#dcefff" style="border:1px solid #b0b0b0">
				<tr align="center">
					<td width="30%" style="border-right:1px solid #b0b0b0;">항목</td>
					<td width="70%">값</td>
				</tr>
			</table>
		</div>
		<div id='detail_info_box' style='overflow:auto; border-left:1px solid #c9c8c8; border-right:1px solid #c9c8c8; border-bottom:1px solid #c9c8c8; background-color: #f7f6f6; height:153px;'>
			
		</div>
</div>-->