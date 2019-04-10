<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="top_tab">
	<img src="img/tab_img/tab3_2.png" width="100%" usemap="#area_tab">
</div>
<div id="jibun_sch_box" style="width:100%; height:360px; border:0px solid #000000;">
	<div style="height:75px; margin-top:5px; padding:2px 0 0 0; border:1px solid #c9c8c8; background-color: #f7f6f6;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%" height="auto">
			<tr>
				<td width="75px"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 버퍼레이어</p></td>
				<td>
					<select id="layer_name" name="layer_name" style="width:185px;" onchange="_CS.init.setSelectObjArr(this.value);"><!-- _CS.init.radioCtrl(this.value); -->
						<option value=''>:: 선택 하여 주십시오! ::</option>
					</select>
				</td>
			</tr>
			<!-- <tr>
				<td width="70px"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 직접그리기</p></td>
				<td>
					<input type="radio" id="srch_target" name="srch_target" style="vertical-align:middle;"/>점
					<input type="radio" id="srch_target" name="srch_target" style="vertical-align:middle;"/>선
					<input type="radio" id="srch_target" name="srch_target" style="vertical-align:middle;"/>면
				</td>
			</tr> -->
			<tr>
				<td width="70px"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 버퍼길이</p></td>
				<td><input type="text" id="buff_length" name="buff_length" style="width:60px;"/> m</td>
			</tr>
		</table>
	</div>
	<div style="height:24px; border:0px solid #000000; margin-top:3px;">
		<ul>
			<li style="float: left; width:70px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 검색대상</p></li>
		</ul>
	</div>
	<div id="layer_name_box" style="height:100px; border:1px solid #c9c8c8; padding:3px; background-color: #f7f6f6; overflow: auto;">
		<!--<table border="0" cellpadding="0" cellspacing="0" width="100%" height="auto">
			<tr>
				<td align="center" width="10%"><input type="checkbox" id="" name="" value=""/></td>
				<td width="90%">지적</td>
			</tr>
		</table>-->
	</div>
	<div style="height:24px; border:0px solid #000000; margin-top:3px;">
		<ul>
			<li style="float: left; width:65px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 검색조건</p></li>
		</ul>
	</div>
	<div id="srch_item" style="height:43px; border:1px solid #c9c8c8; background-color: #f7f6f6;">
		<p style="padding:3px;">
		<input type="radio" id="srch_cond" name="srch_cond" value="contains" checked="checked" style="vertical-align: middle;"/> 선택 영역에 포함하는 데이터 <br/>
		<input type="radio" id="srch_cond" name="srch_cond" value="intersects" style="vertical-align: middle;"/> 선택 영역에 포함하거나 교차하는 데이터 <br/>
		</p>
	</div>
	<div style="border:0px solid #000000; text-align: center; margin-top:3px;">
		<!-- <img src="img/start_btn.gif" onclick="_CS.init.GetBufferList('POLYGON((369152.2418079958 469814.4860354594,369519.088016339 469623.9165765798,369066.48555149994 469416.195866401,369152.2418079958 469814.4860354594))');" style="cursor: pointer;">-->
		<!-- img src="img/start_btn.gif" onclick="_MAINMAP.searchBuffer('실폭도로', '3', '');" style="cursor: pointer;" -->
		<img src="img/start_btn.gif" onclick="_MAINMAP.setBuffer(); gwsUtils.setMapControlImgChange('');" style="cursor: pointer;">
		<!--<img src="img/start_btn.gif" onclick="_CS.init.GetBufferList();"/>-->
	</div>
	<div style="height:35px; border:1px solid #CAE0E5; margin-top:3px; text-align: center; background-color: #E5F5F9;">
		<p style="padding:10px; color: #5A9BAB; font-weight: bold;">* <b>버퍼레이어</b>는 필수 선택항목 입니다.</p>
	</div>
</div>
<!-- <div id="sch_line"></div> -->
<div id="sch_result">
	<div id="" style="height:21px; border:0px solid #000000;"><img src="img/sch_result.png"/></div>
	<div id="buf_result_content_box" style="border:1px solid #c9c8c8; background-color: #f7f6f6; height: 300px;">
		<div id="top_subject" style="height:25px; padding:2px; border:0px solid #000000;">
			<table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" bgcolor="#dcefff" style="border:1px solid #b0b0b0">
				<tr align="center">
					<td width="45%" style="border-right:1px solid #b0b0b0;"><b>레이어명</b></td>
					<td width="55%"><b>검색결과</b></td>
				</tr>
			</table>
		</div>
		<div id="buf_result_content" style="padding:2px; border:0px solid #000000; overflow: auto; position: relative; ">
			<div style='border: 1px solid #E6BBB3; text-align:center; width:80%; background-color: #FDE8E4; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>
				<p style="padding: 20px 0 0 0;color: #CF4425;font-weight: bold;">검색조건을 입력하여 주십시오!</p>
			</div>
		</div>
		<div id="page_list" style="text-align: center">
			<p id="total_page" style="padding:3px 0 0 0 "><!-- <img src="img/prev.gif" align="absmiddle"/> <b>1</b> 2 3 4 5 <img src="img/next.gif"  align="absmiddle"/> --></p>
		</div>
	</div>
</div>
<!--<div id="sch_line" style="margin-top:5px;"></div>-->
<!--<div id="detail_info" style="display:none; border:0px solid #000000;">
		<div id='' style='height:21px; border:0px solid #000000;'>
			<div style='float: left;'><img src='img/toji_info.gif'/></div>
		</div>
		<div>
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="27" bgcolor="#dcefff" style="border:1px solid #b0b0b0">
				<tr align="center">
					<td width="30%" style="border-right:1px solid #b0b0b0;">항목</td>
					<td width="70%">값</td>
				</tr>
			</table>
		</div>
		<div id='detail_info_box' style='overflow:auto; border-left:1px solid #c9c8c8; border-right:1px solid #c9c8c8; border-bottom:1px solid #c9c8c8; background-color: #f7f6f6; height:153px;'></div>
</div>-->