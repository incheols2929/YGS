<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="top_tab" style="height:30px;">
	<img src="img/tab_img/tab2.png" width="100%">
</div>
<div id="desi_sch_box" style="width:100%; height:95px; border:0px solid #000000;">
	<div style="height:24px; border:0px solid #000000; margin-top:4px;">
		<ul>
			<li style="float: left; width:75px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 대상레이어</p></li>
			<li style="float: left; width:192px;list-style: none;">
				<select id="info_layer_name" name="info_layer_name" onchange="_CS.init.setSelectObjArr(this.value); _MAINMAP.selectInfoLayer();" style="width:192px;">
					<option value=''>:: 선택 하여 주십시오! ::</option>
				</select>
			</li>
		</ul>
	</div>
	<div style="border:0px solid #000000; text-align: center;"><img src="img/sch_btn.gif" style="cursor: pointer;" onclick="gwsUtils.setMapControlImgChange(''); _MAINMAP.selectInfoLayer();"/></div>
	<div style="height:35px; border:1px solid #CAE0E5; margin-top:3px; text-align: center; background-color: #E5F5F9;">
		<p id="alert_mea" style="padding:10px; color: #5A9BAB; font-weight: bold;">* 대상레이어를 선택하고 지도에서 객체를 선택해주세요.</p>
	</div>
</div>
<!-- <div id="sch_line"></div> -->
<div id="sch_result">
	<div id="" style="height:21px; border:0px solid #000000;"><img src="img/sch_result.png"/></div>
	<div id="info_result_content_box" style="border:1px solid #c9c8c8; background-color: #f7f6f6; height:500px;">
		<div id="info_result_content" style="position:relative; padding:2px; border:0px solid #000000; ">
			<div style='border: 1px solid #E6BBB3; text-align:center; width:80%; background-color: #FDE8E4; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>
				<p style="padding: 20px 0 0 0;color: #CF4425;font-weight: bold;"><b>레이어</b>를 선택하여 주십시오!</p>
			</div>
		</div>
		<div id="page_list" style="text-align: center">
			<p id="total_page" style="padding:3px 0 0 0 "></p>
		</div>
	</div>
</div>
