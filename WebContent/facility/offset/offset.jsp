<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	
	$(document).ready(function (){
		initSatLayer();
	});
	
	function SatOffset(){

		var xn = $("#Xn").val();
		var ye = $("#Ye").val();
		var lyr_name = $("#sat_layer_name option:selected").val();
		
		
		if(xn == ""){
			xn = 0;
		}		
		if(ye == ""){
		    ye = 0; 
		}
		
		if(lyr_name == ""){
			alert("영상레이어를 선택하여 주십시오!");
			$("#sat_layer_name").focus();
			return false;
		}
		
		_MAINMAP.setOpenMapOffset(lyr_name, ye, xn);  // 특정 영상레이어의 offset을 설정하고 화면을 다시 그린다.

	}
	
	//영상초기화 지도화면의 영상레이어가 on되어있는것만 초기화한다.
	function reSetSatOffset(){		
		_MAINMAP.setVisibleOpenMapOffset(0, 0);
		 $("#Xn").val("0");
		 $("#Ye").val("0");
	}
	
	
	function initSatLayer(){
		  $("#sat_layer_name").removeOption(/./);
	      $("#sat_layer_name").addOption("", ":: 선택하여주십시오! ::", true);
	       
			var xml = "<?xml version='1.0' encoding='UTF-8'?>";
			xml += _MAINMAP.getLayerXML();
			
			var xmlDoc = $.parseXML(xml);
			var $xml = $(xmlDoc);
	 
			if($xml.find("MapLayers").length != 0){
				$xml.find("Group").each(function(grpidx, item){
					var grpnm = $(this).attr("name"); 
					if(grpnm == "항공영상" || grpnm == "항공(위성)영상"){
						var sat_box = "";
						$(this).find("Layer").each(function(lyridx){
							var lyrnm = $(this).text();
							var TF = _MAINMAP.isLayerVisible(lyrnm);
							if(TF){
								sat_box += "<option value='" + lyrnm + "' style='background-color:#ffca4b;'>" + lyrnm + "</option>";	
							}else{
								sat_box += "<option value='" + lyrnm + "' style='background-color:#f4f4f4;'>" + lyrnm + "</option>";
							}
						});
						$("#sat_layer_name").append(sat_box);
					}
				});
			}
	}
	
	// 선택한 영상레이어의 offset값을 가지고온다.
	function getOpenMapOffset(obj){
		var tt = _MAINMAP.getOpenMapOffset(obj);
		for(var i=0; i<tt.length; i++){
			if(i == 0){
				var xn = tt[i];
				if(xn != 0){
					$("#Xn").val(tt[i].toFixed(2));
				}else{
					$("#Xn").val(tt[i]);
				}
			}else if(i == 1){
				var ye = tt[i];
				if(ye != 0){
					$("#Ye").val(tt[i].toFixed(2));
				}else{
					$("#Ye").val(tt[i]);
				}
				
			}
		}
	}
</script>

<div id="top_tab">
	<img src="img/tab_img/tab4.png" style="width: 294px;">
</div>
<table width="100%" style="margin-top:5px; border:1px solid #D5D5D5;">
	<tr align="center" >
		<td  width="25%" height="30"><b>영상레이어</b></td>
		<td>
			<select id="sat_layer_name" name="sat_layer_name" style="width:90%" onchange="getOpenMapOffset(this.value);">
				<option value="">:: 선택하여주십시오! ::</option>
			</select>
		</td>
	</tr>
	<tr align="center" >
		<td height="30" colspan="2">
			<table>
				<tr>
					<td width="25%">가로</td>
					<td width="25%"><input type="text" id="Xn" value="0" style="width:60px;"/></td>
					<td width="25%">세로</td>
					<td width="25%"><input type="text" id="Ye" value="0" style="width:60px;"/></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr align="center">
		<td height="30" colspan="2">
			<button onclick="reSetSatOffset();" class="green" style="cursor: pointer;">영상초기화</button>
			<button onclick="SatOffset();" class="green" style="cursor: pointer;">확인</button> 
			<button onclick="gwsUtils.setMapControlImgChange(''); _MAINMAP.setMapTool('SatOffset');" class="green" style="cursor: pointer;">마우스로 조절</button>
		</td>
	</tr>
</table>