var gmx = {};
var Bool_BUF = false;

//지도 컨트롤 스크립트
var _MAP = {};
var _SMAP = {};
var minx, miny, maxx, maxy;
var bothbool;
var onebool;

var _GOOGLE_POPUP;
var _VW_POPUP;
var _kmlRequest;
var _daumRequest;
var _naverRequest;
var _googleRequest;
var _google3dRequest;
var _transCoordRequest;
var _sellipsoid = "bessel"; //구글,다음,네이버 좌표변환시 오리지날 좌표체계 타원체
var _sbase = "tm127"; //구글,다음,네이버 좌표변환시 오리지날 좌표체계 원점
var _GMXDAUM = null;	//다음맵
var _GMXNAVER = null; //네이버맵
var _GMXGOOGLE = null; //구글맵3D
var SOPPlugin = null; //vworld

var SYNC_DOUBLE= false;
var SYNC_ONE = true;
var SYNC_NONE = true;
var _vW = true;

var ROADLATLNG = null;
var RC = null;
var RV = null;

//로드뷰의 좌표값을 가지고온다.
var R_CX = null;
var R_CY = null;

var N_M = 0;

gmx.utils = {
		
		geomexURL : "/geomex/",
		ele : function(obj){
			return (typeof(obj) == "string")? document.getElementById(obj) : obj;
		},
		createAppletTag : function(attributes, parameters) {

			var s = '<' + 'applet ';
			var codeAttribute = false;
			var appletTag  = "";
			for ( var attribute in attributes) {
				s += (' ' + attribute + '="' + attributes[attribute] + '"');
				if (attribute == 'code') {
					codeAttribute = true;
				}
			}
			if (!codeAttribute) {
				s += (' code="dummy"');
			}
			s += '>';
			
			//document.write(s);
			appletTag += s;
			if (parameters != 'undefined' && parameters != null) {
				var codebaseParam = false;
				for ( var parameter in parameters) {
					if (parameter == 'codebase_lookup') {
						codebaseParam = true;
					}
					s = '<param name="' + parameter + '" value="'
							+ parameters[parameter] + '">';
					//document.write(s);
					appletTag += s;
				}
				if (!codebaseParam) {
					appletTag += '<param name="codebase_lookup" value="false">';
				}
			}
			//document.write('<' + '/' + 'applet' + '>');
			appletTag += '<' + '/' + 'applet' + '>';
			
			return appletTag;
			
		}
		
		
};


gmx.Init = function(elementid){
	
	this.gmx = new geomex.GMXMAP(elementid);
	this.gmxid = gmx.Init.prototype.gmxid;
	this.element = gmx.Init.prototype.element;
	//this.gmx.setMapElement(elementid);
	this.mapDiv;
	this.syncFlag = false; 
	this.other;  
	this.session_id = SESSION_ID; 
	this.curPath = "/geomex/img/"; // 커서이미지 경로
	
	//메인 맵
	this.attributes = {id:'ageomex',
                    codebase:'../geomex',
                    name:'영광군 행정지원시스템',
                    archive:'geomex-apps-20181016.jar', //라이센스,라이브러리키 지오맥스에서 지원받아야함
                    code:'geomex.gws.GWSMAP',                                             
                    width:'100%',       //메인 지도화면 폭
                    height:'100%',      //메인지도화면 넓이
                    scriptable:'true',
                    mayscript:'true',  //
                    //java_version:'1.7.0',
                    separate_jvm:'true',
                    java_arguments:'-Xms256m -Xmx256m',
                    image:'./geomex/logo.gif',
                    boxborder:'false',
                    centerimage:'true'} ;
                     
  	this.parameters = {owner:this.session_id,
  					//owner:'_gws',
  					gmxid:'_MAINMAP',
                    debug:'true',
                    layout:'east:false:false', //관리도구위치(왼쪽:west,오른쪽:east, 관리도구보이기, 인덱스보이기
					//관리도구 버튼 생성(순서대로 생성함) : view:legend:layer:style:bookmark:drawing
					// view : 현재지도범례, legend: 전체범례(tree), layer: 레이어목록(설정)
                    // style : 스타일목록관리, bookmark: 즐겨찾기, drawing: 그리기도록 목록
                    // 대소문자 구분하여 정확이 입력해야 함
                    tool_tab:'view:legend:layer:style:bookmark:drawing:index:config',
                    //landinfourl:'http://106.0.2.134:8080/ctrl?svc=GetLandInfo',
                    path:'/GEOMEX:YGS:YGS'} ; //url:webnode:dbnode
  	
/**************************************************************************************/
  	
    //서브 맵
	this.subattributes = {id:'sub_ageomex',
                    codebase:'../geomex',
                    name:'영광군 행정지원시스템',
                    archive:'geomex-apps-20181016.jar',
                    code:'geomex.gws.GWSMAP',                                              
                    width:'100%',
                    height:'100%',
                    scriptable:'true',
                    mayscript:'true',
                    //java_version:'1.7.0',
                    separate_jvm:'true',
                    java_arguments:'-Xms256m -Xmx256m',
                    image:'./geomex/logo.gif',
                    boxborder:'false',
                    centerimage:'true'} ;

  	this.subparameters = {owner:this.session_id,
  					//owner:'_gws',
  					gmxid:'_SUBMAP',
                    debug:'true',
                    layout:'east:false:false', //관리도구위치(왼쪽:west,오른쪽:east, 관리도구보이기, 인덱스보이기
                    tool_tab:'view:legend:layer',
                    //landinfourl:'http://106.0.2.134:8080/ctrl?svc=GetLandInfo',
                    path:'/GEOMEX:YGS:YGS'} ; //url:webnode:dbnode
  	
/*******************************************************************************************/
  	
	//메인지도의 애플릿을 호출한다.
	this.insertAppletTag = function(div) {
		this.mapDiv =  gmx.utils.ele(div);
		var gmxMapDiv = document.createElement("div");
		gmxMapDiv.style.width = "100%"; //메인지도 폭
		gmxMapDiv.style.height = "100%"; //메인지도 높이
		var appletTag = gmx.utils.createAppletTag(this.attributes,this.parameters);
		gmxMapDiv.innerHTML = appletTag;
		this.mapDiv.appendChild(gmxMapDiv);
		this.gmx.element = document.getElementById("ageomex");
		this.element = document.getElementById("ageomex");
	};
	
	
	//보조지도의 애플릿을 호출한다.
	this.subAppletTag = function(div) {
		this.mapDiv =   gmx.utils.ele(div);
		var gmxMapDiv = document.createElement("div");
		gmxMapDiv.style.width = "100%";
		gmxMapDiv.style.height = "100%";  
		var appletTag =  gmx.utils.createAppletTag(this.subattributes,this.subparameters);
		gmxMapDiv.innerHTML = appletTag;
		this.mapDiv.appendChild(gmxMapDiv);
		this.gmx.element = document.getElementById("sub_ageomex");
		this.element = document.getElementById("sub_ageomex");
	};
	

	//지도화면을 그렸을때 호출된다.
	gmx.Init.prototype._gmxMapDrawFinished = function(minx, miny, maxx, maxy){
		var centerx = Number(minx)+(Number(maxx)-Number(minx))/2;
		var centery = Number(miny)+(Number(maxy)-Number(miny))/2;
		var bugaCheck = $("input:radio[name='bugamap']:checked").val();
		
		//var _minx;
		//var _miny;
		//var _maxx;
		//var _maxy;
		
		if(this.gmxid == "ageomex"){
			//영역검색,버퍼검색,정보보기 사용시 현재지동화면의 레이어목록을 가져온다.
			//현재축척의 레이어정보를 보여준다.
			if($("#layer_name_box").length){
				_CS.init.GetVisibleLayerRadioXML();
			}
			if($("#layer_name").length){
				_CS.init.GetVisibleLayerXML();
			}
			if($("#info_layer_name").length){
				_CS.init.srchInfoLyrXML();
			}
			if($("#scaletxt").length){
				//$("#scaletxt").text(_MAINMAP.getScaleFactor());
				$("#scaletxt").val(_MAINMAP.getScaleFactor());
			}
			//_MAINMAP일경우
			//_minx = _MAINMAP.getMinX();
			//_miny = _MAINMAP.getMinY();
			//_maxx = _MAINMAP.getMaxX();
			//_maxy = _MAINMAP.getMaxY();
		}else{
			//_SUBMAP일경우
			//_minx = _SUBMAP.getMinX();
			//_miny = _SUBMAP.getMinY();
			//_maxx = _SUBMAP.getMaxX();
			//_maxy = _SUBMAP.getMaxY();
		}
		
		//동기화안하기
		if(SYNC_NONE){
			try{
				if(paneName == "east"){
					//메인맵이 움직일때만 동기화
					if(SYNC_ONE){
						if(bugaCheck == "gmxbojo"){
							if(MAP_TAB){
								if(SYNC_DOUBLE){
									if(this.syncFlag==false){  // 사용자가 마우스로 지도를 이동하였다.
										if(this.other != null){
											var _func = this.other+".moveExtentCoord(\"" + minx + "\",\"" + miny + "\",\""+ maxx + "\",\"" + maxy + "\")";
											//var _func = this.other+".moveExtentCoord(\"" + _mix + "\",\"" + _miny + "\",\""+ _maxx + "\",\"" + _maxy + "\")";
											eval(_func);				
										}
									}else{
										this.syncFlag=false;
									}
								}else{
									if(this.gmx.gmxid == "ageomex"){
										_SUBMAP.moveExtentCoord(minx, miny, maxx, maxy);
									}
								}			
							}
						}else if(bugaCheck == "google3D"){
							if(MAP_TAB){
								if(_GOOGLE_POPUP){
									_SMAP.mapsync.moveGoogle3D(Number(minx)+(Number(maxx)-Number(minx))/2, Number(miny)+(Number(maxy)-Number(miny))/2);
								}
							}
						}else if(bugaCheck  == "daum"){
							if(MAP_TAB){
								if(SYNC_DOUBLE){
								}else{
									_SMAP.mapsync.moveOtherMap(centerx,centery,"daum");
								}
							}
						}else if(bugaCheck  == "naver"){
							if(MAP_TAB){
								if(SYNC_DOUBLE){
								}else{
									_SMAP.mapsync.moveOtherMap(centerx,centery,"naver");
								}
							}
						}else if(bugaCheck  == "vm"){
							if(MAP_TAB){
								if(SYNC_DOUBLE){
								}else{
									_SMAP.mapsync.moveOtherMap(centerx,centery,"vm");
								}
							}
						}
					}
						
				}else if(paneName == "south"){
					//메인맵이 움직일때만 동기화
					if(SYNC_ONE){
						if(bugaCheck == "gmxbojo"){
							if(MAP_TAB){
								if(SYNC_DOUBLE){
									if(this.syncFlag==false){  // 사용자가 마우스로 지도를 이동하였다.
										if(this.other != null){
											var _func = this.other + ".moveExtentCoord(\"" + minx + "\",\"" + miny + "\",\""+ maxx + "\",\"" + maxy + "\")";
											eval(_func);				
										}
									}else{
										this.syncFlag=false;
									}
								}else{
									if(this.gmx.gmxid == "ageomex"){
										_SUBMAP.moveExtentCoord(minx, miny, maxx, maxy);
									}
								}	
							}
						}else if(bugaCheck == "google3D"){
							if(MAP_TAB){
								if(_GOOGLE_POPUP){
									_SMAP.mapsync.moveGoogle3D(Number(minx)+(Number(maxx)-Number(minx))/2, Number(miny)+(Number(maxy)-Number(miny))/2);
								}
							}
						}else if(bugaCheck  == "daum"){
							if(MAP_TAB){
								if(SYNC_DOUBLE){
	
								}else{
									_SMAP.mapsync.moveOtherMap(centerx,centery,"daum");
								}
							}
						}else if(bugaCheck  == "naver"){
							if(MAP_TAB){
								if(SYNC_DOUBLE){
	
								}else{
									_SMAP.mapsync.moveOtherMap(centerx,centery,"naver");
								}
							}
						}else if(bugaCheck  == "vm"){
							if(MAP_TAB){
								if(SYNC_DOUBLE){
								}else{
									_SMAP.mapsync.moveOtherMap(centerx,centery,"vm");
								}
							}
						}
					}
				}
			}catch(e){
				alert(e);
			}
		}
	};
	
	
	
	// 애플릿이 로드 되었을 때 호출된다.
	gmx.Init.prototype._gmxMapLoaded = function(){
		if(this.gmxid == "sub_ageomex"){
			_MAINMAP.gmxSetOtherMap("_SUBMAP");
			_SUBMAP.gmxSetOtherMap("_MAINMAP");
			_SUBMAP.setLayerMenuVisible(false);
		}else{
			//$("#scaletxt").text(_MAINMAP.getScaleFactor());
			$("#scaletxt").val(_MAINMAP.getScaleFactor());
			/*var MapCfgXML = $.parseXML(_MAINMAP.getMapCfgXML());
			$(MapCfgXML).find("LayerDefinition").find("LayerGroup").children().each(function(){
				if("임시레이어" == $(this).attr("name")){
					$(this).remove();
				}
			});
			var xmlString;
			//IE
		    if (window.ActiveXObject){
		        xmlString = MapCfgXML.xml;
		    }else{
		        xmlString = (new XMLSerializer()).serializeToString(MapCfgXML);
		    }
		    setTimeout(function(){
		    	_MAINMAP.applyMapCfgXML(xmlString);
		    }, 2000);*/
		}
	};
	
	this.removeGarbageLayerGroup = function(){
		var MapCfgXML = $.parseXML(_MAINMAP.getMapCfgXML());
		$(MapCfgXML).find("LayerDefinition").find("LayerGroup").children().each(function(){
			if("임시레이어" == $(this).attr("name")){
				$(this).remove();
			}
		});
		//_MAINMAP.applyMapCfgXML(MapCfgXML);
	};
	
	this.gmxSetOtherMap = function(o){
		this.other = o;
		
	};
	
	gmx.Init.prototype.moveExtentCoord = function(minx, miny, maxx, maxy){
		this.syncFlag=true;
		this.element.moveExtent(minx, miny, maxx, maxy);
	};
	
	gmx.Init.prototype.moveExtentCenter = function(cx, cy, scale){
		this.element.moveExtent(cx, cy, scale);
	};
	
	//네이버 명칭 검색시 이동화면
	gmx.Init.prototype.locateCenter = function(cx, cy){
		this.element.locateCenter(cx, cy);
	};
	// 지도도구를 설정한다.CTRL 참고
	// 마우스 커서이미지 파일명을 소문자로 반드시 하고 tool과 이름이 반드시 같아야 한다.
	gmx.Init.prototype.setMapTool = function(tool){
		Bool_BUF = false;
		this.element.setMapTool(tool);
		/*if (this.curPath != "") {
			this.element.setCursor(this.curPath + tool + ".gif");
		}*/
	};
	
	//사각형, 폴리곤 검색시 초기호출
	 this.PolygonDraw = function(obj){
		var lty_len = $("input[name='lry_name_chk']:checked").length;
		if(lty_len == 0){ //아무것도 체크를 안했으면 
			 alert("검색대상을 선택하여 주십시오!"); //이 메시지를 띄움
			//$("#messg_win").html("* <b>검색대상</b>을 선택하여 주십시오!");
			return false;
		}
		_MAINMAP.clearAddOnScreen();
		_CON.skill.ctrlimgChange('etc'); //이미지?
		this.element.setMapTool(obj);
		/*if (this.curPath != "") {
			this.element.setCursor(this.curPath + "area_srch_cur.gif");
		}*/
		//_MAINMAP.setMapTool(obj);
	};
	
	// 사각형 검색, 버퍼, 폴리곤 검색시 호출된다.(인자: polygon wkt문자열)
	gmx.Init.prototype._gmxPolygonSelected = function(wktPolygon){
		if(Bool_BUF){
			//버퍼검색
			_CS.init.GetBufferList(wktPolygon);
		}else{
		    //사각형, 폴리곤검색시(난 영역검색 이라고 생각함)
			_CS.init.GetBoxPolyList(wktPolygon);
		}
		
	};
	//반경검색시 호출된다.
	gmx.Init.prototype._gmxRadiusSelected = function(cx, cy, distance) {
		_CS.init.GetCircleList(cx, cy, distance);
	};
	
	//버퍼검색(영역검색)
	gmx.Init.prototype.searchBuffer = function(layer, meter, style){
		Bool_BUF = true;
		this.element.searchBuffer(layer, meter, style);
	};
	//검색한 시설물의 상세정보 창을 띄움
	this.setReservoirPop = function(ftr_idn){
		var pnu = pnu;
		var ftridn = ftridn;
		var w = 1200;
		var h = 800;
		var top = (screen.availHeight/2-100);
		var left = (screen.availWidth/2-100);
		var option = "menubar=no, toolbar=no, location=no, status=no, scrollbars=yes, resizeble=no, width=" + w + "px, height=" + h + "px, top="+top+" , left="+left+", resizable=yes";
		window.open("../facility/log/srchReservoirRecord.jsp?ftr_idn="+ ftr_idn +"","FacilityLogPop", option);//팝업창  중앙에 띄우기
	   
	};	
	this.setPumpPop = function(ftr_idn){
		var pnu = pnu;
		var ftridn = ftridn;
		var w = 1200;
		var h = 800;
		var top = (screen.availHeight/2-100);
		var left = (screen.availWidth/2-100);
		var option = "menubar=no, toolbar=no, location=no, status=no, scrollbars=yes, resizeble=no, width=" + w + "px, height=" + h + "px, top="+top+" , left="+left+", resizable=yes";
		window.open("../facility/log/srchPumpRecord.jsp?ftr_idn="+ ftr_idn +"","FacilityLogPop", option);//팝업창  중앙에 띄우기
	   
	};	
	this.setDrainPop = function(ftr_idn){
		var pnu = pnu;
		var ftridn = ftridn;
		var w = 1200;
		var h = 800;
		var top = (screen.availHeight/2-100);
		var left = (screen.availWidth/2-100);
		var option = "menubar=no, toolbar=no, location=no, status=no, scrollbars=yes, resizeble=no, width=" + w + "px, height=" + h + "px, top="+top+" , left="+left+", resizable=yes";
		window.open("../facility/log/srchDrainRecord.jsp?ftr_idn="+ ftr_idn +"","FacilityLogPop", option);//팝업창  중앙에 띄우기
	   
	};
	
	this.setCwipPop = function(ftr_idn){
		var pnu = pnu;
		var ftridn = ftridn;
		var w = 1200;
		var h = 800;
		var top = (screen.availHeight/2-100);
		var left = (screen.availWidth/2-100);
		var option = "menubar=no, toolbar=no, location=no, status=no, scrollbars=yes, resizeble=no, width=" + w + "px, height=" + h + "px, top="+top+" , left="+left+", resizable=yes";
		window.open("../facility/log/srchCwipRecord.jsp?ftr_idn="+ ftr_idn +"","FacilityLogPop", option);//팝업창  중앙에 띄우기
	   
	};	
	
	this.setCulvertPop = function(ftr_idn){
		var pnu = pnu;
		var ftridn = ftridn;
		var w = 1200;
		var h = 800;
		var top = (screen.availHeight/2-100);
		var left = (screen.availWidth/2-100);
		var option = "menubar=no, toolbar=no, location=no, status=no, scrollbars=yes, resizeble=no, width=" + w + "px, height=" + h + "px, top="+top+" , left="+left+", resizable=yes";
		window.open("../facility/log/srchCulvertRecord.jsp?ftr_idn="+ ftr_idn +"","FacilityLogPop", option);//팝업창  중앙에 띄우기
	   
	};
	
	this.setSeawallPop = function(ftr_idn){
		var pnu = pnu;
		var ftridn = ftridn;
		var w = 1200;
		var h = 800;
		var top = (screen.availHeight/2-100);
		var left = (screen.availWidth/2-100);
		var option = "menubar=no, toolbar=no, location=no, status=no, scrollbars=yes, resizeble=no, width=" + w + "px, height=" + h + "px, top="+top+" , left="+left+", resizable=yes";
		window.open("../facility/log/srchSeawallRecord.jsp?ftr_idn="+ ftr_idn +"","FacilityLogPop", option);//팝업창  중앙에 띄우기
	   
	};
	
	this.setTbwPop = function(ftr_idn){
		var pnu = pnu;
		var ftridn = ftridn;
		var w = 1200;
		var h = 800;
		var top = (screen.availHeight/2-100);
		var left = (screen.availWidth/2-100);
		var option = "menubar=no, toolbar=no, location=no, status=no, scrollbars=yes, resizeble=no, width=" + w + "px, height=" + h + "px, top="+top+" , left="+left+", resizable=yes";
		window.open("../facility/log/srchTbwRecord.jsp?ftr_idn="+ ftr_idn +"","FacilityLogPop", option);//팝업창  중앙에 띄우기
	   
	};
	this.setBasinPop = function(ftr_idn){ //저류지대장팝업
		var pnu = pnu;
		var ftridn = ftridn;
		var w = 1200;
		var h = 800;
		var top = (screen.availHeight/2-100);
		var left = (screen.availWidth/2-100);
		var option = "menubar=no, toolbar=no, location=no, status=no, scrollbars=yes, resizeble=no, width=" + w + "px, height=" + h + "px, top="+top+" , left="+left+", resizable=yes";
		window.open("../facility/log/srchBasinRecord.jsp?ftr_idn="+ ftr_idn +"","FacilityLogPop", option);//팝업창  중앙에 띄우기
		
	};
	//버퍼검색시 초기호출
	this.setBuffer = function(){
		
		var lrynm = $("#layer_name").val();
		var buf_len = $("#buff_length").val();
		
		if(lrynm == ""){   //버퍼레이어가 공백이면
			alert("버퍼레이어를 선택하여주십시오!"); //이 메시지 출력
			$("#layer_name").focus();
			return false;
		}
		
		if(buf_len == ""){ //버퍼길이가 공백이면
 			alert("버퍼길이를 입력하여주십시오!"); //이 메시지 출력
			$("#buff_length").focus();
			return false;
		}

		_CON.skill.ctrlimgChange('etc');
		_MAINMAP.searchBuffer(lrynm, buf_len, "");
		/*if (this.curPath != "") {
			this.element.setCursor(this.curPath+"buffer_mouse_cur.gif");
		}*/
		
	};
	
	//레이어정보보기
	gmx.Init.prototype.selectLayerObject = function(lyr){
		this.element.selectLayerObject(lyr);
	};
	
	//레이어 정보 초기호출
	this.selectInfoLayer = function(){
		var lyrnm = $("#info_layer_name").val();
		if(lyrnm == ""){
		    $("#alert_mea").html("* <b>대상레이어</b>를 선택하세요.");
			return false;
		}else{
			_CON.skill.ctrlimgChange('etc');
			_MAINMAP.selectLayerObject(lyrnm);
			/*if (this.curPath != "") {
				this.element.setCursor(this.curPath + "GWLandInfoTool.gif");
			}*/
		}
	};
	
	//레이어의 특정 객체를 선택하였다.(정보보기)
	gmx.Init.prototype._gmxLayerObjectSelected = function(lyr, col, key){
		/*$.ajax({
			type: 'POST',
	    	url: './get_p_k.jsp',
	    	data : {lid : lyr, gid: key},
	    	dataType: 'json',
	    	async: false,
	    	success: function(json){
	    		// 시설물 레이어라면
	    		if ( json.stat && json.stat == 1 ) {
	    			_CS.init.selectedFmsLryInfo(json.path, json.k);
	    		} else {
	    		}
			},
	    	error: function(){
	    	}
		});*/
		_CS.init.selectedLryInfo(lyr, col, key);
	};
	
	//인쇄시사용
	gmx.Init.prototype._gmxShowPrint = function(xml, minx, miny, maxx, maxy){
		_gmxShowSPZMapPrint(xml, minx, miny, maxx, maxy);
	};
	//인쇄시요청
	gmx.Init.prototype.printScreen = function(){
		this.element.setPrint();
	};
	
	//지도인쇄시 사용목적 팝업창을 연다.
	this.showPrintPop = function(){
		//printAddonScreen(xml, addon, minx, miny, maxx, maxy)
		var w = 400;
		var h = 300;
		var top = (screen.availHeight/2-100);
		var left = (screen.availWidth/2-100);
		var option = "menubar=no, toolbar=no, location=no, status=no, scrollbars=no, resizeble=no, width=" + w + "px, height=" + h + "px, top="+top+" , left="+left+", resizable=yes";
		window.open("../intra/log/PrintLogRecord.jsp","printLogPop", option);//팝업창  중앙에 띄우기
	};
	
	gmx.Init.prototype._gmxShowAddonPrint = function(xml, addon, minx, miny, maxx, maxy){
		_gmxShowSPZMapPrint(xml, addon, minx, miny, maxx, maxy);
		/*var w = 400;
		var h = 300;
		var top = (screen.availHeight/2-100);
		var left = (screen.availWidth/2-100);
		var option = "menubar=no, toolbar=no, location=no, status=no, scrollbars=no, resizeble=no, width=" + w + "px, height=" + h + "px, top="+top+" , left="+left+", resizable=yes";
		window.open("../intra/log/PrintLogRecord.jsp","printLogPop", option);*/
	};
	
	//현재축첵에있는 레이어를 가지고온다.xml
	gmx.Init.prototype.getVisibleLayerXML = function(){
		return this.element.getLayerXML(false);
	};	
	
	//전체레이어xml을 가지고온다.
	gmx.Init.prototype.getLayerXML = function(){
		return this.element.getLayerXML(true);
	};	
	
	//이미지를 저장하기 전에 사용자 로그정보를 입력하는 팝업창을 연다.
	this.setLogImgPop = function(){
		var w = 400;
		var h = 250;
		var top = (screen.availHeight/2-100);
		var left = (screen.availWidth/2-100);
		var option = "menubar=no, toolbar=no, location=no, status=no, scrollbars=no, resizeble=no, width=" + w + "px, height=" + h + "px, top="+top+" , left="+left+", resizable=yes";
		window.open("../intra/log/ImgLogRecord.jsp","imgLogPop", option);//팝업창  중앙에 띄우기
	};
	
	//이미지를 저장한다.
	gmx.Init.prototype.getMapImage = function(){
		var fileName = this.element.getMapImage();
		if(typeof arguments[0] == "undefined"){
			window.location =  gmx.utils.geomexURL + "/utils/mapImgDown.jsp?fileName="+encodeURI(fileName,"UTF-8");
		}else{
			return fileName;
		}
	};
	
	//이미지를 클립보드로 복사한다.
	this.getMapImageClipBoard = function(){
		//투명처리된 배경은 그림판에서 검정으로 표기됨.
		//var browser = deployJava.getBrowser();
		var fileName = _MAINMAP.getMapImage('clip');
		var w = 800;
		var h = 600;
		var top = (screen.availHeight/2);
		var left = (screen.availWidth/2);
		var option = "menubar=no, toolbar=no, location=no, status=no, scrollbars=yes, resizeble=yes, width=" + w + "px, height=" + h + "px, top="+top+" , left="+left+", resizable=yes";
		var pop = window.open("./clipImg.jsp?fileName="+fileName,"ImageClip", option);//팝업창  중앙에 띄우기
			pop.focus();
		//alert("클립보드에 이미지를 복사하였습니다.\n*참고 : 해당 기능은 <Internet Explorer> 에서만 가능합니다.");
	};
	
	//지도화면이동시 스케일없을시
	gmx.Init.prototype.searchAndMoveNoScale = function(layer, key){
		//_MAINMAP.setClearScreen();
		if(!this.element.searchAndMove(layer, key)){
			alert("위치가 존재 하지 않습니다.");
		}
	};
	
	//지도화면이동시 스케일없을시 키값이 여러개일때 배열로 넘긴다.
	gmx.Init.prototype.searchAndMovesNoScale = function(layer, keys){
		if(!this.element.searchAndMoves(layer, keys)){
			alert("위치가 존재 하지 않습니다.");
		}
	};
	
	//지도화면이동시 스케일 설정시
	gmx.Init.prototype.searchAndMove = function(layer, key, scale){
		if(!this.element.searchAndMove(layer, key, scale)){
			alert("위치가 존재 하지 않습니다.");
		}
	};
	 
	//지도화면이동시 스케일 설정시 키값이 여러개일때 배열로 넘긴다.
	gmx.Init.prototype.searchAndMoves = function(layer, key, scale){
		if(!this.element.searchAndMoves(layer, key, scale)){
			alert("위치가 존재 하지 않습니다.");
		}
	}; 
	
	//스타일 갱신
	gmx.Init.prototype.replaceMapCfgXML = function(xml){
		this.element.replaceMapCfgXML(xml);
	};
	
	//신규추가
	gmx.Init.prototype.addMapCfgXML = function(title, xml){
		this.element.addMapCfgXML(title, xml);
	};
	
	//레이어등록
	gmx.Init.prototype._gmxLayerConfigSelected = function(){
		_CON.skill.LyrManagentPopup();
	};
	
	//지도화면에서 메뉴 클릭시 호출된다.
	gmx.Init.prototype._gmxLayerMenuClicked = function(layer, dataset, cmd, col, value){
		_CS.init.srchjibunLoding(2);
		//기본정보
		if(cmd == "ShowBasicInfo"){
			//_CON.skill.contentAttribute("form/toji.jsp", "base", value);
			//_CS.init.srchjibunLoding(2);
			$.ajax({
				url: "./search/srchJibunNew.jsp",
				contentType : "text/html; charset=utf-8",
				dataType : "html",
				success: function(htm){
					outerLayout.open("west");
					$("#con_sch_full_box").html(htm);
					_CS.init.GetSGG();		
					_CON.skill.menuimgChange("etc");
					
				},
				error: function(xhr, status, error){
					alert(">>>>>>>>>>> : 에러");
				}
			});	
			if(gwsUtils.checkAuth("FW004", null)){
				_DE.tojiInfo(value, "");
			};
		}else if(cmd == "ShowLRGInfo"){
			//토지대장
			if(!gwsUtils.checkAuth("FW004", null)){
				alert('해당 기능에 대한 권한이 없습니다.');
				return false;
			};
			SEARCH_LINK_TYPE = "KLIS";
			_DE.tojiInfo(value, "");
			$("#result_content_box").hide();
		}else if(cmd == "ShowEAISInfo"){
			//건축물대장
			if(!gwsUtils.checkAuth("FW005", null)){
				alert('해당 기능에 대한 권한이 없습니다.');
				return false;
			};
			SEARCH_LINK_TYPE = "EAIS";
			_DE.bldgShortInfo(value, "");
			$("#result_content_box").hide();
		}else if(cmd == "ShowRestrictLawInfo"){
			//행위제한법령정보
			if(!gwsUtils.checkAuth("FW006", null)){
				alert('해당 기능에 대한 권한이 없습니다.');
				return false;
			};
			SEARCH_LINK_TYPE = "LURIS";
			_PC.init.tojiUseplan(value);
			$("#result_content_box").hide();
		}else if(cmd == "ShowRestrictByZone"){ 
			//지역지구별행위제한가능여부
			if(!gwsUtils.checkAuth("FW007", null)){
				alert('해당 기능에 대한 권한이 없습니다.');
				return false;
			};
			SEARCH_LINK_TYPE = "LURIS";
			_PC.init.rdAct(value);
			$("#result_content_box").hide();
		}else if(cmd == "ShowRestrictByCond"){ 
			//조건별행위제한가능여부
			if(!gwsUtils.checkAuth("FW008", null)){
				alert('해당 기능에 대한 권한이 없습니다.');
				return false;
			};
			SEARCH_LINK_TYPE = "LURIS";
			_PC.init.tojiAct(value);
			$("#result_content_box").hide();
		}else {
			var url = "lyr/lyr_detailInfo.jsp?";
			var param = "lry_id="+encodeURIComponent(layer)+"&col="+col+"&val="+value+"";
			_CON.skill.contentAttribute(url+""+param);
		}
	};
	
	//도로위치이동
	this.searchAndMoveRoad = function(layer, where){
		var bool = this.element.searchAndMoveWhere(layer, "sig_cd||rn_cd='"+where+"'");
		if(!bool){
			alert("위치가 존재 하지 않습니다.");
		}
	};
	
	//교차로위치이동
	this.searchAndMoveCross = function(layer, where){
		var bool = this.element.searchAndMoveWhere(layer, "sig_cd||crsrd_sn='"+where+"'"); 
		if(!bool){
			alert("위치가 존재 하지 않습니다.");
		}
	};
	
	//시설물,시설물위치로 이동
	//지도화면이동시 스케일없을시
	gmx.Init.prototype.searchFaciliAndMove = function(layer, key){
		//_MAINMAP.setClearScreen();
		alert("레이어명:"+layer);
		alert("키값:"+key);
		if(!this.element.searchAndMove(layer, key)){
			alert("위치가 존재 하지 않습니다.");
		}
	};
	
	
	//건물, 건물군위치이동
	this.searchAndMoveEqb = function(layer, where){
		if(layer == "건물"){
			var bool = this.element.searchAndMoveWhere(layer, "_gid='"+where+"'"); 
			if(!bool){
				alert("위치가 존재 하지 않습니다.");
			}
		}else if(layer == "건물군"){
			var bool = this.element.searchAndMoveWhere(layer, "sig_cd||eqb_man_sn='"+where+"'"); 
			if(!bool){
				alert("위치가 존재 하지 않습니다.");
			}
		}
	};
	
	gmx.Init.prototype.setMapCtrlVisible = function(visible) {
		// 관리도구 화면 보이기 여부를 설정한다.
		this.element.setMapCtrlVisible(visible);
	};
	
	gmx.Init.prototype.toggleMapCtrlVisible = function() {
		var toggle = this.isMapCtrlVisible() ? false : true;
		if(toggle){
			$(".work_btn").eq(8).attr("src", "./img/bottom_btn/9_over.png"); 
		}else{
			$(".work_btn").eq(8).attr("src", "./img/bottom_btn/9.png");
		}
		this.setMapCtrlVisible(toggle);
	};
	
	gmx.Init.prototype.setIndexMapVisible = function(visible) {
		// 인덱스맵 화면 보이기 여부를 설정한다.
		this.element.setIndexMapVisible(visible);
	};
	
	gmx.Init.prototype.setDrawToolVisible = function(visible) {
		// 그리기 도구 메뉴 보이기 여부를 설정한다.
		this.element.setDrawToolVisible(visible);
	};
	
	gmx.Init.prototype.getScaleFactor = function() {
		// 지도화면 스케일 값을 얻는다.
		return this.element.getScaleFactor();
	};
	//축적 검색 
	this.ScaleMov = function(){
		var scaleval = $("#scaletxt").val();
		
		if(scaleval == ""){
			alert("스케일값을 입력하여 주십시오!");
			 $("#scaletxt").focus();
			return false;
		}
				
		this.element.moveExtent(scaleval);
	};
	
	gmx.Init.prototype.setSearch = function(layer, key) {
		// 하이라이트할 레이어 객체를 설정한다. 지도이동은 하지 않는다.
		if(!this.element.setSearch(layer, key)){
			alert("위치가 존재 하지 않습니다.");
		}
	};
	
	gmx.Init.prototype.redrawScreen =  function() {
		// 지도화면 전체를 다시그린다. 서버에서 데이터를 다시 받아오지 않는다.
		this.element.redrawScreen();
	};
	
	//로드뷰호출시 좌표를 가지고온다.
	gmx.Init.prototype._gmxRoadViewSelected = function(cx, cy){
		//alert("좌표값을 가지고온다. " + cx + ", " + cy);
		 $('input:radio[name="bugamap"]:input[value="roadview"]').attr("checked", true);
		_CON.skill.bugamap("roadview", 1, cx, cy);
	};
	
	//로드뷰의 가로각도를 가지고와 지오멕스 지도화면과 연동한다.
	gmx.Init.prototype.setRoadViewpoint = function(px, py, deg){
		//alert("여기");
		this.element.setRoadViewpoint(px, py, deg);
	};
	
	gmx.Init.prototype.isLayerVisible = function(layer){
		//alert();  2013. 10. 18 alert수정
		this.element.isLayerVisible(layer);
	};
	
	gmx.Init.prototype.setLayerVisible = function(layer, visible) {
		// 레이어 보이기/감추기를 설정한다.
		this.element.setLayerVisible(layer, visible);
	};
		
	gmx.Init.prototype.resetExtent = function(){
		this.element.resetExtent();
	};
	
	//로드뷰레이어를 가지고온다.
	this.DaumRoadviewLV = function(layer, visible){
		if(visible){
			this.setLayerVisible(layer, visible);// 레이어 보이기/감추기를 설정한다.
			this.element.resetExtent();
			this.setMapTool('Roadview');
		}else{
			this.setLayerVisible(layer, visible);// 레이어 보이기/감추기를 설정한다.
			this.element.resetExtent();
			this.clearAddOnScreen(); // AddOnScreen(추가객체) 화면만(거리,면적,그리기도구등)을 지우고 화면을 다시그린다.
		}
	};
	
	//네이버영상보이기 보조맵
	this.NaverMapLV = function(layer){
		
		if(N_M == 0){
			N_M = 1;
			this.setLayerVisible(layer, true);
			this.setLayerVisible("다음영상", false);
			this.element.resetExtent();
			$("#submap_ctrl17, #submap_ctrl18").attr("src","img/btn/s_naver_map_on.gif");
		}else{
			N_M = 0;
			this.setLayerVisible(layer, false);
			this.setLayerVisible("다음영상", true);
			this.element.resetExtent();
			$("#submap_ctrl17, #submap_ctrl18").attr("src","img/btn/s_naver_map_off.gif");
		}
	};

}

//인쇄시호출
function _gmxShowSPZMapPrint(cfgxml, addon, minx, miny, maxx, maxy){
	   
    var wide = 800;
    var high = 600;
    
    document.reform.cfgxml.value= cfgxml;
    document.reform.addon.value= addon;
    document.reform.minx.value= minx;
    document.reform.miny.value= miny;
    document.reform.maxx.value= maxx;
    document.reform.maxy.value= maxy;
    var size = "width="+wide +",height="+high;
    var proposal_pop = window.open('','proposal_pop',size);
    document.reform.target="proposal_pop";
    document.reform.action="./print/Print.jsp";
    document.reform.submit();                    
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

_MAP.SUBGMXMAP = {
		
		syncMAPCtrl : function(obj){
			if(obj == 1){
				SYNC_DOUBLE = true;
				SYNC_ONE = true;
				SYNC_NONE = true;
				$("#syc_btn1, #syc_btn4").attr("src", "img/syc_btn1_on.gif");
				$("#syc_btn2, #syc_btn5").attr("src", "img/syc_btn2.gif");
				$("#syc_btn3, #syc_btn6").attr("src", "img/syc_btn3.gif");
			}else if(obj == 2){
				SYNC_DOUBLE = false;
				SYNC_ONE = true;
				SYNC_NONE = true;
				$("#syc_btn1, #syc_btn4").attr("src", "img/syc_btn1.gif");
				$("#syc_btn2, #syc_btn5").attr("src", "img/syc_btn2_on.gif");
				$("#syc_btn3, #syc_btn6").attr("src", "img/syc_btn3.gif");
			}else if(obj == 3){
				SYNC_DOUBLE = false;
				SYNC_ONE = false;
				SYNC_NONE = false;
				$("#syc_btn1, #syc_btn4").attr("src", "img/syc_btn1.gif");
				$("#syc_btn2, #syc_btn5").attr("src", "img/syc_btn2.gif");
				$("#syc_btn3, #syc_btn6").attr("src", "img/syc_btn3_on.gif");
			}
		}
	};

	_SMAP.mapsync = {
            //다음지도 생성하기
			daumCreate : function(divid){
				//try {document.execCommand('BackgroundImageCache', false, true);} catch(e) {}
				$.ajax({
					type: 'POST',
					url: '../geomex/utils/transCoordXml.jsp',
					data: {tmx : _MAINMAP.getCenterX(), tmy : _MAINMAP.getCenterY()},
			    	success: function(xml){
			    		_GMXDAUM =  new daum.maps.Map(document.getElementById('DAUM_MAP'), {
							center: new daum.maps.LatLng($(xml).find("y").text(), $(xml).find("x").text())
						});
						var zoomControl = new daum.maps.ZoomControl();
						_GMXDAUM.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
						var mapTypeControl = new daum.maps.MapTypeControl();
						_GMXDAUM.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);
					},
			    	error: function(){
			        	alert('Error loading XML document');
			    	} 
				});
			},
			naverCreate : function(divid){
				//try {document.execCommand('BackgroundImageCache', false, true);} catch(e) {}
				_GMXNAVER = new NMap(document.getElementById(divid),{mapMode:0});
				this.moveOtherMap(_MAINMAP.getCenterX(), _MAINMAP.getCenterY(),"naver");
				var zoom =new NZoomControl();
				zoom.setAlign("right");
				zoom.setValign("top");
				_GMXNAVER.addControl(zoom);
				_GMXNAVER.enableWheelZoom();
				var mapbtns = new NMapBtns();
				mapbtns.setAlign("right");
				mapbtns.setValign("top");
				_GMXNAVER.addControl(mapbtns);
				
			},
			//vworld맵을 생성한다. 2012-02-06
			vWorldCreate : function(divid){

				if(paneName == "east"){
					var divObj = document.getElementById("eastbox");
					var innerObj = $("#eastContent");
					var x = this.cumulativeOffset(divObj)[0];
					var y = this.cumulativeOffset(divObj)[1];
					var i_w = innerObj.width();
					var i_h = innerObj.height();
					$("#vworldViewBox").css({"top": (y+29), "left":(x+2), "display":"inline-block", "width":i_w, "height":i_h});
				}else if(paneName == "south"){
					var divObj = document.getElementById("southbox");
					var innerObj = $("#southContent");
					var x = this.cumulativeOffset(divObj)[0];
					var y = this.cumulativeOffset(divObj)[1];
					var i_w = innerObj.width();
					var i_h = innerObj.height();
					$("#vworldViewBox").css({"top":(y+30), "left":(x+2), "display":"inline-block", "width":i_w, "height":i_h});
				}
				
				if(_vW){
					setTimeout(function(){sop.earth.createInstance("vworldViewBox", _SMAP.mapsync.vmInitCall, _SMAP.mapsync.vmFailureCall);}, 1);
					_vW = false;
				}
			},
			vmInitCall : function(obj){
				SOPPlugin = obj;
			    if(SOPPlugin != null){
			    	_SMAP.mapsync.moveOtherMap(_MAINMAP.getCenterX(), _MAINMAP.getCenterY(),"vm");
				}
			},
			vmFailureCall : function(msg){
				alert(msg);
				//alert("vworld 접속실패");
			},
			vmImportKml : function(){
				
				$.ajax({
					type: 'GET',
			    	url: '/efez/geomex/utils/kmlGmxOnlyLayer.jsp?lineColor=ffff0000&lyr=lp_pa_cbnd&MYVIEWPARAMS=127.7296217105792, 37.88353947874708, 236.18&mapwidth=818&mapheight=852',
			    	dataType: 'text',
			    	success: function(txt){
			    		
			    		var kmlObject = SOPPlugin.parseKml(txt);
			    		
			    		var SOPCamera = SOPPlugin.getViewCamera();
			    		var SOPMap = SOPPlugin.getView();
			    		var defaultAltitude = 1000;
			    		var viewLevel = 5;
			    		
			    		SOPMap.mapReset();
			    		SOPMap.clearInputPoint(); 
			    		SOPPlugin.getAnalysis().clear();
			    		SOPPlugin.getAnalysis().clearArea();
			    		
			    		if(kmlObject != null){
			    			var centerPt = kmlObject.getCenter();
			    			SOPCamera.moveLonLat(centerPt.Longitude,centerPt.Latitude);
			    			//SOPCamera.moveLonLatOval(centerPt.Longitude,centerPt.Latitude,defaultOvalMoveSpeed);
			    			//SOPCamera.setAltitude(defaultAltitude);
			    			SOPMap.addChild(kmlObject, viewLevel);		
			    		}
					},
			    	error: function(){
			        	alert('Error loading XML document');
			    	} 
				});
			},
			cumulativeOffset : function(element) { 
				var valueT = 0, valueL = 0; 
				do { 
					valueT += element.offsetTop || 0; 
					valueL += element.offsetLeft || 0; 
					element = element.offsetParent; 
				} while (element); 
					return [valueL, valueT];
			},
			//다음로드뷰 추가 2012-03-14
			RoadViewCreate : function(divid, cx, cy){
				var opt = {width:300,height:300}; 
				RC = new daum.maps.RoadviewClient();
				RV = new daum.maps.Roadview(document.getElementById(divid), opt);
				_SMAP.mapsync.moveOtherMap(cx, cy,"roadview");
				
				
				//로드뷰의 좌표변환될때 지오멕스 맵도 같이 이동한다.
				daum.maps.event.addListener(RV,"position_changed", function(){
									
					var Dposition = RV.getPosition().toString().replace("(", "").replace(")", "");
					var foo = Dposition.split(",");
					
					var pan = RV.getViewpoint().pan; //로드뷰의 가로각도를 가지고온다.
				
					var y = foo[0];
					var x = foo[1];
					
					//alert(y + ", " + x);
					////다음로드뷰 좌표를 바꿔줘야함 fromCoord 이부분 부동산이 연계된 후에 수정해줘야함
					$.ajax({
						type: "POST",
					    url: "../svc",
					    data: {svc : "GetDaumCoord", apikey:"6402f804eddb06dd403efffbf97fdf6d6a7fd0fd", x:x, y:y, fromCoord:"WGS84", toCoord:"WTM", output:"xml"},
						dataType : "xml",
						success: function(xml){
							if($(xml).find('result').length != 0){
								var x = $(xml).find('result').attr("x");
								var y=Number($(xml).find('result').attr("y"))+100000;
								//var x=Number($(xml).find('result').attr("x"))+100000;
								//var y = $(xml).find('result').attr("y");
								
								R_CX = x;
								R_CY = y;
								
								_MAINMAP.setRoadViewpoint(x, y, pan);
							}else{
								if($(xml).find('apierror').length != 0){
									alert($(xml).find('dmessage').text());
								}
							}
						},
						beforeSend : function(){},
						complete : function(){},
						error: function(xhr, status, error){
							alert(">>>>>> : " + error);
						}	
					});
				});
				
				//로드뷰화면 각도 0~360 
				daum.maps.event.addListener(RV,"viewpoint_changed", function(){
					var pan = RV.getViewpoint().pan;
					_MAINMAP.setRoadViewpoint(R_CX, R_CY, pan);
					
				});
				
			},
			naverRemove : function(){
				_GMXNAVER = null;
			},
			moveOtherMap : function(tmx, tmy, mapType)
			{
				if("google" == mapType) {
					
					///////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					//var url = geomex.utils.geomexURL + "/utils/transCoordXml.jsp?tmx="+ tmx +"&tmy="+tmy+"&sellipsoid=" + _sellipsoid + "&sbase=" + _sbase + "&tellipsoid=wgs84&tbase=gp127&offset_x=-18&offset_y=5&iserr=true";
					
					$.ajax({
						type:"GET",
						url:"../geomex/utils/transCoordXml.jsp",
						data : {tmx : tmx, tmy:tmy, offset_x : "0",offset_y:"0", iserr:true},
						dataType : "xml",
						success: function(xml){
							
							var lng;
							var lat;
							//var scale = _SMAP.mapsync.gmxSacleToNaverLevel(_MAINMAP.getScaleFactor());
							
							if($(xml).find("Coord").length != 0){
								$(xml).find("Coord").each(function(idx, item){
									lng = $(this).find("x").text();
									lat = $(this).find("y").text();
								});	
								
								_SMAP.mapsync.pageGoogle3D(lng, lat);
								
								//_GOOGLE_POPUP.moveGmxToGoogle(lng,lat);
							}
						},
						beforeSend : function(){},
						complete : function(){},
						error: function(xhr, status, error) {
							alert(">>>>>> : " + error);
						}	
					});
				}else if("daum" == mapType) {
					$.ajax({
						type:"GET",
						url:"../geomex/utils/transCoordXml.jsp",
						data : {tmx : tmx, tmy:tmy, offset_x : "0",offset_y:"0", iserr:true},
						dataType : "xml",
						success: function(xml){
							var lng;
							var lat;
							var scale = _SMAP.mapsync.gmxSacleToDaumLevel(_MAINMAP.getScaleFactor());
							if($(xml).find("Coord").length != 0){
								$(xml).find("Coord").each(function(idx, item){
									lng = $(this).find("x").text();
									lat = $(this).find("y").text();
								});	
								_GMXDAUM.setCenter(new daum.maps.LatLng(lat, lng));
								_GMXDAUM.setLevel(scale);
							}
						},
						beforeSend : function(){},
						complete : function(){},
						error: function(xhr, status, error) {
							alert(">>>>>> : " + error);
						}
					});
				}else if("naver" == mapType) {
					
					$.ajax({
						type:"GET",
						url:"../geomex/utils/transCoordXml.jsp",
						data : {tmx : tmx, tmy:tmy, offset_x : "0",offset_y:"0", iserr:true},
						dataType : "xml",
						success: function(xml){
							var lng;
							var lat;
							var scale = _SMAP.mapsync.gmxSacleToNaverLevel(_MAINMAP.getScaleFactor());
							
							if($(xml).find("Coord").length != 0){
								$(xml).find("Coord").each(function(idx, item){
									lng = $(this).find("x").text();
									lat = $(this).find("y").text();
								});	
								_GMXNAVER.setCenterAndZoom(_GMXNAVER.fromLatLngToTM128(new NLatLng(lat,lng)), scale);
								
								//alert(_GMXNAVER.fromLatLngToTM128(new NLatLng(lat,lng)));
								//_GMXNAVER.setCenterAndZoom(_GMXNAVER.formLatLngToTM128(lat, lng), scale);
								//_GMXNAVER.setCenterAndZoom(new NLatLng(293918, 177707), scale);
								//_GMXNAVER.setCenterAndZoom(new NPoint(153381, 386280),3);
							}
						},
						beforeSend : function(){},
						complete : function(){},
						error: function(xhr, status, error) {
							alert(">>>>>> : " + error);
						}
					});
				}else if("vm" == mapType) {
					
					$.ajax({
						type:"GET",
						url:"../geomex/utils/transCoordXml.jsp",
						data : {tmx : tmx, tmy:tmy, offset_x : "0",offset_y:"0", iserr:true},
						dataType : "xml",
						success: function(xml){
							var lng;
							var lat;
							var scale = _MAINMAP.getScaleFactor();
							var v_scale = (scale/2);
							//alert(v_scale);
							
							if($(xml).find("Coord").length != 0){
								$(xml).find("Coord").each(function(idx, item){
									lng = $(this).find("x").text();
									lat = $(this).find("y").text();
								});	
								
								//vworld 위치이동
								//SOPPlugin.getViewCamera().moveLonLat(lng, lat);
								if(SOPPlugin != null){
									var SOPCamera = SOPPlugin.getViewCamera(); 
									SOPCamera.moveLonLat(lng,lat);//vworld 위치이동
									SOPCamera.setAltitude(v_scale);//카메라 고도 설정
								}
							}
						},
						beforeSend : function(){},
						complete : function(){},
						error: function(xhr, status, error) {
							alert(">>>>>> : " + error);
						}
					});
				}else if("roadview" == mapType) {
					
					$.ajax({
						type:"GET",
						url:"../geomex/utils/transCoordXml.jsp",
						data : {tmx : tmx, tmy:tmy, offset_x : "0",offset_y:"0", iserr:true},
						dataType : "xml",
						success: function(xml){
							var lng;
							var lat;
							
							if($(xml).find("Coord").length != 0){
								$(xml).find("Coord").each(function(idx, item){
									lng = $(this).find("x").text();
									lat = $(this).find("y").text();
								});	
								
								ROADLATLNG = new daum.maps.LatLng(lat, lng);
								
								RC.getNearestPanoId(ROADLATLNG, 50, function(panoid) {
									RV.setPanoId(panoid, ROADLATLNG);
									RV.setViewpoint({ pan: 0, tilt: 0, zoom: -3 });
								});
							}
						},
						beforeSend : function(){},
						complete : function(){},
						error: function(xhr, status, error){
							alert(">>>>>> : " + error);
						}
					});
				}
			},
			//다음지도 스케일을 지정한다.
			gmxSacleToDaumLevel : function(scale)	{	
				//if(Math.ceil(_scale/16) == 0 || Math.ceil(_scale/8) == 0 || Math.ceil(_scale/8) == 1) return 0;
				if(scale < 1019){
					return 1;
				}else if(scale > 1019 && scale < 2335){
					return 2;
				}else if(scale > 2335 && scale < 4698){
					return 3;
				}else if(scale > 4698 && scale < 9435){
					return 4;
				}else if(scale > 9435 && scale < 16515){
					return 5;
				}else if(scale > 16515 && scale < 28882){
					return 6;
				}else if(scale > 28882 && scale < 50457){
					return 7;
				}else if(scale > 50457 && scale < 76059){
					return 8;
				}else if(scale > 76059 && scale < 115468){
					return 9;
				}else if(scale > 115468 && scale < 202005){
					return 10;
				}else if(scale > 202005 && scale < 467407){
					return 11;
				}else if(scale > 467407 && scale < 716566){
					return 12;
				}else if(scale > 716566 && scale < 1089096){
					return 13;
				}else{
					return 14;
				}
			},
			//네이버지도 스케일을 지정한다.
			gmxSacleToNaverLevel : function(scale)	{	
				var _scale = scale;
				//if(Math.ceil(_scale/16) == 0 || Math.ceil(_scale/8) == 0 || Math.ceil(_scale/8) == 1) return 0;
				if(Math.ceil(_scale/1900)         == 1 || Math.ceil(_scale/1900)    == 0) return 0;
				else if(Math.ceil(_scale/1900)    == 1 || Math.ceil(_scale/3800)    == 0) return 0;
				else if(Math.ceil(_scale/3800)    == 1 || Math.ceil(_scale/7600)    == 0) return 1;
				else if(Math.ceil(_scale/7600)    == 1 || Math.ceil(_scale/15200)   == 0) return 2;
				else if(Math.ceil(_scale/15200)   == 1 || Math.ceil(_scale/30400)   == 0) return 3;
				else if(Math.ceil(_scale/30400)   == 1 || Math.ceil(_scale/60800)   == 0) return 4;
				else if(Math.ceil(_scale/60800)   == 1 || Math.ceil(_scale/121600)  == 0) return 5;
				else if(Math.ceil(_scale/121600)  == 1 || Math.ceil(_scale/243200)  == 0) return 6;
				else if(Math.ceil(_scale/243200)  == 1 || Math.ceil(_scale/486400)  == 0) return 7;
				else if(Math.ceil(_scale/486400)  == 1 || Math.ceil(_scale/972800)  == 0) return 8;
				else if(Math.ceil(_scale/972800)  == 1 || Math.ceil(_scale/1945600) == 0) return 9;
				else if(Math.ceil(_scale/1945600) == 1 || Math.ceil(_scale/3891200) == 0) return 10;
				else if(Math.ceil(_scale/3891200) == 1 || Math.ceil(_scale/7782400) == 0) return 11;
				else return 12;
			},
			moveGoogle3D : function(tmx, tmy){
				$.ajax({
					type:"GET",
					url:"../geomex/utils/transCoordXml.jsp",
					data : {tmx : tmx, tmy:tmy, offset_x : "0",offset_y:"0", iserr:true},
					dataType : "xml",
					success: function(xml){
						
						var lng;
						var lat;
						//var scale = _SMAP.mapsync.gmxSacleToNaverLevel(_MAINMAP.getScaleFactor());
						if($(xml).find("Coord").length != 0){
							$(xml).find("Coord").each(function(idx, item){
								lng = $(this).find("x").text();
								lat = $(this).find("y").text();
							});	
							_GOOGLE_POPUP.moveGmxToGoogle(lng,lat);
						}
					},
					beforeSend : function(){},
					complete : function(){},
					error: function(xhr, status, error) {
						alert(">>>>>> : " + error);
					}	
				});
			}
	}


