<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 영역검색 페이지 -->
<div id="top_tab">
	<img src="img/tab_img/tab3_1.png" width="100%" usemap="#area_tab">
</div>
<div id="jibun_sch_box" style="width:100%; height:300px; border:0px solid #000000;">
	<div style="height:24px; border:0px solid #000000; margin-top:3px;">
		<ul>
			<li style="float: left; width:70px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 검색대상</p></li>
		</ul>
	</div>
	<div id="layer_name_box" style="height:120px; border:1px solid #c9c8c8; padding:3px; background-color: #f7f6f6; overflow: auto;">
		<!--<table border='0' cellpadding='0' cellspacing='0' width='100%' height='auto'>
			<tr>
				<td align='center' width='10%'><input type='checkbox' id='lry_name' name='lry_name'  /></td>
				<td width='90%'>지적</td>
			</tr>
		</table>-->
	</div>
	<div style="height:24px; border:0px solid #000000;">
		<ul>
			<li style="float: left; width:60px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 검색조건</p></li>
		</ul>
	</div>
	<div id="srch_item" style="height:43px; border:1px solid #c9c8c8; background-color: #f7f6f6;">
		<p style="padding:3px;">
			<input type="radio" id="srch_cond" name="srch_cond" value="contains" checked="checked" style="vertical-align: middle;"/> 선택 영역에 포함하는 데이터 <br/>
			<input type="radio" id="srch_cond" name="srch_cond" value="intersects" style="vertical-align: middle;"/> 선택 영역에 포함하거나 교차하는 데이터 <br/>
		</p>
	</div>
	<div style="height:30px; border:0px solid #000000;">
		<ul>
			<li style="float: left; width:60px; list-style: none;"><p style="padding:5px"><img src="img/p.gif" align="absmiddle"> 검색방법</p></li>
			<li style="float: left; width:204px;list-style: none;">
				<div id="srch_how" style="border:0px solid #000000; height:25px;">
					<p style="padding:5px 0 0 0;">
						<img src="img/btn_pp_1.gif" onclick="_MAINMAP.PolygonDraw('SrchBox'); gwsUtils.setMapControlImgChange('');" style="cursor: pointer;"><!-- 사각형, 폴리곤 검색시 초기호출 -->
						<img src="img/btn_pp_2.gif" onclick="_MAINMAP.PolygonDraw('SrchRadius'); gwsUtils.setMapControlImgChange('');" style="cursor: pointer;"><!-- 반경, 폴리곤 검색시 초기호출 -->
						<img src="img/btn_pp_3.gif" onclick="_MAINMAP.PolygonDraw('SrchPoly'); gwsUtils.setMapControlImgChange('');" style="cursor: pointer;"> <!-- 다각형, 폴리곤 검색시 초기호출 -->
						<!-- <img src="img/btn_pp_1.gif" onclick="_MAINMAP.clearAddOnScreen();_MAINMAP.setMapTool('SrchBox');" style="cursor: pointer;">
						<img src="img/btn_pp_2.gif" onclick="_MAINMAP.clearAddOnScreen();_MAINMAP.setMapTool('SrchRadius');" style="cursor: pointer;">
						<img src="img/btn_pp_3.gif" onclick="_MAINMAP.clearAddOnScreen();_MAINMAP.setMapTool('SrchPoly');" style="cursor: pointer;"> -->
					</p>
				</div>
			</li>
		</ul>
	</div>
	<div style="height:35px; border:1px solid #CAE0E5; margin-top:3px; text-align: center; background-color: #E5F5F9;">
		<p id="messg_win" style="padding:10px; color: #5A9BAB; font-weight: bold;">* 검색대상은 필수 선택항목 입니다.</p>
	</div>
</div>
<!-- <div id="sch_line"></div> -->
<div id="sch_result">
	<div id="" style="height:21px; border:0px solid #000000;"><img src="img/sch_result.png"/></div>
	<div id="pre_result_content_box" style="border:1px solid #c9c8c8; background-color: #f7f6f6; height: 300px;">
		<div id="top_subject" style="height:25px; padding:2px; border:0px solid #000000;">
			<table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" bgcolor="#dcefff" style="border:1px solid #b0b0b0">
				<tr align="center">
					<td width="45%" style="border-right:1px solid #b0b0b0;"><b>레이어명</b></td>
					<td width="55%"><b>검색결과</b></td>
				</tr>
			</table>
		</div>
		<div id="pre_result_content" style="padding:2px; border:0px solid #000000; overflow: auto; position: relative; ">
			<div style='border: 1px solid #E6BBB3; text-align:center; width:80%; background-color: #FDE8E4; position: absolute; height:50px; top:50%; left:50%; margin:-30px 0 0 -105px;'>
				<p style="padding: 20px 0 0 0;color: #CF4425;font-weight: bold;">검색조건을 입력하여 주십시오!</p>
			</div>
		</div>
		<div id="page_list" style="text-align: center">
			<p id="total_page" style="padding:3px 0 0 0 "><!-- <img src="img/prev.gif" align="absmiddle"/> <b>1</b> 2 3 4 5 <img src="img/next.gif"  align="absmiddle"/> --></p>
		</div>
	</div>
</div>
