<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.border1{
		border : 1px solid #e3e3e3;
	}
	.border2{
		border-right : 1px solid #e3e3e3;
		border-bottom : 1px solid #e3e3e3;
	}
	.border3{
		border-bottom : 1px solid #e3e3e3;
	}
	.border4{
		border-right : 1px solid #e3e3e3;
	}
	.table_border1{
		border-right : 1px solid #e3e3e3;
		border-bottom : 1px solid #e3e3e3;
		border-left : 1px solid #e3e3e3;
	}
	
	.border-line1{
		border-right:1px solid #b2b2b2;
		border-bottom:1px solid #b2b2b2;
	}
	.border-line2{
		border-bottom:1px solid #b2b2b2;
	}
	.table_border{
		border:4px solid #0068b7;
	}
</style>

<map name="toji_tab">
    <area shape="rect" coords="0,1,44,22" href="#"  id="Tab_1_menu"  alt="기본정보" onclick="_PC.init.tojiBasic();" onfocus='this.blur();'/>
    <area shape="rect" coords="48,1,100,22" href="#" id="Tab_2_menu" alt="토지대장" onclick="_PC.init.tojiDaejang();" onfocus='this.blur();'/>
    <area shape="rect" coords="104,1,160,22" href="#" id="Tab_3_menu" alt="건축물대장" onclick="_PC.init.bldgDaejang();" onfocus='this.blur();'/>
    <area shape="rect" coords="167,2,251,23" href="#" id="Tab_4_menu" alt="행위제한법령정보" onclick="_PC.init.tojiUseplan();"  onfocus='this.blur();'/>
    <area shape="rect" coords="258,0,354,23" href="#" id="Tab_5_menu" alt="지역.지구별 행위제한" onclick="_PC.init.rdAct();" onfocus='this.blur();'/>
    <area shape="rect" coords="364,1,473,23" href="#" id="Tab_6_menu" alt="조건별행위제한가능여부" onclick="_PC.init.tojiAct();" onfocus='this.blur();'/>
</map>

<div style="border:1px solid #e3e3e3; background-color:#f9f9f9; display: none; ">
		<table border="0" cellspacing="0" cellpadding="0" style="margin:3px;">
			<tbody>
				<tr>
					<td>선택 : </td>
					<td style="padding-left:3px;">
						<select id="p_sgg" name="p_sgg" onchange="_PC.init.GetUMD(this.value);">
							<option value="" >::선택::</option>
						</select>
					</td>
					<td style="padding-left:3px;">
						<select id="p_umd" name="p_umd" onchange="_PC.init.GetRI(this.value);">
							<option value="">::선택::</option>
						</select>
					</td>
					<td style="padding-left:3px;">
						<select id="p_ri" name="p_ri">
							<option value="">::선택::</option>
						</select>
					</td>
					<td style="padding-left:3px;">지번 : </td>
					<td style="padding-left:3px;">
						<input type="text" maxlength="4" style="width:40px;" id="p_bon" name="p_bon"/> 
						<input maxlength="4" style="width:40px;" type="text" id="p_bu" name="p_bu"/>
					</td>
					<td style="padding-left:3px;">
						<input type="checkbox" id="p_san" name="p_san">산
					</td>
					<td style="padding-left:3px;">
						<img id="top_btn_such" src="img/content_btn_ok.gif"  style="cursor: pointer;"  onclick="_PC.init.PNUCREATE();"/>
					</td>
				</tr>
			</tbody>
		</table>
	<p style="padding:5px;">* 시군은 필수 선택사항입니다.</p>
</div>

<div style="height:32px; border-bottom:1px solid #32b69f;">
	<img id="toji_tab_img" src="img/toji_tab_1.gif" style="margin-top:7px;" usemap="#toji_tab"/>
	<button onclick="_PC.init.frameClose();" style="position: absolute; right: 5px; width: 48px; cursor: pointer;">닫기</button>
</div>
<div id="toji_content_view" style="border:0px solid #000000;"></div>
