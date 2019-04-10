// ////////////////////////////////////////////////////////////////////////////
// GEOMEX Applet에서 호출되는 전역 Method 시작
// Applet에서 자바스크립트 객체명와 함께 호출되며 해당 자바스크립트 객체의 메소드를 수행한다.
// ////////////////////////////////////////////////////////////////////////////

// 애플릿이 로드 되었을 때 호출된다.
function _gmxMapLoaded(gmxid) {
    var _func = gmxid + "._gmxMapLoaded()";
    eval(_func);
}

// 사용자 그리기 도구에서 이미지 삽입을 할때 이미지를 선택하기 위하여 호출된다.
// 사용안함 2014.05.13 by 김경호
// function _gmxGetGrImage(gmxid) {
// var _func = gmxid + "._gmxGetGrImage()";
// eval(_func);
// }

// 지도화면 그리기를 완료할 경우 호출된다. (인자 : 지도영역좌표)
function _gmxMapDrawFinished(gmxid, minx, miny, maxx, maxy) {
    var _func = gmxid + "._gmxMapDrawFinished(\"" 
    + minx + "\",\"" + miny + "\",\"" + maxx + "\",\"" + maxy + "\")";
    eval(_func);
}

// 지도화면에서 레이어 메뉴를 선택하면 호출된다.(인자: 레이어명, 데이터셋명, 명령, 컬럼, 컬럼값)
function _gmxLayerMenuClicked(gmxid, layer, dataset, cmd, col, value) {
    var _func = gmxid + "._gmxLayerMenuClicked(\"" + layer + "\",\"" 
    + dataset + "\",\"" + cmd + "\",\"" + col + "\",\"" + value + "\")";
    eval(_func);
}

// 지도화면에서 검색용 영역(사각형 , 버퍼, 폴리곤)을 선택하면 호출된다.(인자: polygon wkt문자열)
function _gmxPolygonSelected(gmxid, wktPolygon) {
    var _func = gmxid + "._gmxPolygonSelected(\"" + wktPolygon + "\")";
    eval(_func);
}

// 지도화면에서 검색용 영역(반경)을 선택하면 호출된다.(인자: 중심좌표, 반경)
function _gmxRadiusSelected(gmxid, cx, cy, distance) {
    var _func = gmxid + "._gmxRadiusSelected(\"" 
    + cx + "\",\"" + cy + "\",\"" + distance + "\")";
    eval(_func);
}

// 지도화면에서 분석용(횡단) 시작점,종료섬을 선택하면 호출된다.(인자: 시작점, 종료점)
function _gmxCrossLineSelected(gmxid, spx, spy, epx, epy) {
    var _func = gmxid + "._gmxCrossLineSelected(\"" 
    + spx + "\",\"" + spy + "\",\"" + epx + "\",\"" + epy + "\")";
    eval(_func);
}

// 지도화면에서 마우스로 선택한 레이어의 특정 객체값을 전달한다.(인자: 레이어, 칼럼명, 칼럼값)
function _gmxLayerObjectSelected(gmxid, lyr, col, key) {
    var _func = gmxid + "._gmxLayerObjectSelected(\"" 
    + decodeURIComponent(lyr) + "\",\"" 
    + decodeURIComponent(col) + "\",\"" 
    + decodeURIComponent(key) + "\")";
    eval(_func);
}

//지도화면에서 마우스로 checked/unchecked한  객체값을 전달한다.(인자: 레이어, 칼럼명, 칼럼값, checked/unchecked)
function _gmxLayerObjectChecked(gmxid,lyr,col,key,chked){
    var _func = gmxid + "._gmxLayerObjectChecked(\"" 
    + decodeURIComponent(lyr) + "\",\"" 
    + decodeURIComponent(col) + "\",\"" 
    + decodeURIComponent(key) + "\",\"" 
    + decodeURIComponent(chked) + "\")";
    eval(_func);
}

// 지도화면에서 로드뷰 위치를 지정하면 호출된다.
function _gmxRoadViewSelected(gmxid, cx, cy) {
    var _func = gmxid + "._gmxRoadViewSelected(\"" + cx + "\",\"" + cy + "\")";
    eval(_func);
}

// 레이어 설정 버튼을 선택하였다.
function _gmxLayerConfigSelected(gmxid) {
    var _func = gmxid + "._gmxLayerConfigSelected()";
    eval(_func);
}

// 인쇄를 요청한다.(인자 : 스타일, 지도영역좌표)
function _gmxShowPrint(gmxid, xml, minx, miny, maxx, maxy) {
    var _func = gmxid + "._gmxShowPrint(\"" + xml + "\",\"" 
    + minx + "\",\"" + miny + "\",\"" + maxx + "\",\"" + maxy + "\")";
    eval(_func);
}
//거리,면적등 추가레이어를 포함한. 인쇄를 요청한다.
function _gmxShowAddonPrint(gmxid, xml, addon, minx, miny, maxx, maxy) {
    var _func = gmxid + "._gmxShowAddonPrint(\"" + xml +"\",\"" + addon
    + "\",\"" + minx + "\",\"" + miny + "\",\"" + maxx + "\",\"" + maxy + "\")";
    eval(_func);
}

// /////////////////////////////////////////////////////////////////////////////

// ////////////////////////////////////////////////////////////////////////////
// GEOMEX Applet에서 호출되는 Method 끝
// ////////////////////////////////////////////////////////////////////////////

// /////////////////////////////////////////////////////////////////////////////
var geomex = {};

geomex.GMXMAP = function(elementid) {
    this.gmxid = elementid;
    this.element = document.getElementById(elementid);

    this.CTRL = {
        ROADVIEW : 'RoadView',
        SAT_OFFSET : 'SatOffset',
        ZOOMIN : 'ZoomIn',
        ZOOMOUT : 'ZoomOut',
        PANNING : 'Panning',
        CALC_DIST : 'CalcDist',
        MCALC_DIST : 'MultiCalcDist',
        CALC_AREA : 'CalcArea',
        MCALC_AREA : 'MultiCalcArea',
        INFO : 'InfoView',
        SRCH_BOX : 'SrchBox',
        SRCH_POLY : 'SrchPoly',
        SRCH_RADIUS : 'SrchRadius'
    };
};

geomex.GMXMAP.prototype = {
    // ////////////////////////////////////////////////////////////////////////
    // 애플릿에서 호출되는 Method 시작
    // ////////////////////////////////////////////////////////////////////////

    // 애플릿이 로드 되었을 때 호출된다.
    _gmxMapLoaded : function() {
        if (this.element) {
            // alert(this.element + "---> _gmxMapLoaded");
        }
    },
    // 지도화면 그리기를 완료할 경우 호출된다. (인자 : 지도영역좌표)
    _gmxMapDrawFinished : function(minx, miny, maxx, maxy) {
        if (this.element) {
            // alert(this.element + "---> _gmxMapDrawFinished");
        }
    },
    // 지도화면에서 레이어 메뉴를 선택하면 호출된다.(인자: 레이어명, 데이터셋명, 명령, 컬럼, 컬럼값)
    _gmxLayerMenuClicked : function(layer, dataset, cmd, col, value) {
        if (this.element) {
            alert(this.element + "---> _gmxLayerMenuClicked");
        }
    },
    // 지도화면에서 검색용 영역(사각형 , 버퍼, 폴리곤)을 선택하면 호출된다.(인자: polygon wkt문자열)
    _gmxPolygonSelected : function(wktPolygon) {
        if (this.element) {
            alert(this.element + "---> _gmxPolygonSelected " + wktPolygon);
        }
    },
    // 지도화면에서 검색용 영역(반경)을 선택하면 호출된다.(인자: 중심좌표, 반경)
    _gmxRadiusSelected : function(cx, cy, distance) {
        if (this.element) {
            alert(this.element + "---> _gmxRadiusSelected");
        }
    },
    // 지도화면에서 분석용(횡단) 시작점,종료섬을 선택하면 호출된다.(인자: 시작점, 종료점)
    _gmxCrossLineSelected : function(spx, spy, epx, epy) {
        if (this.element) {
            alert(this.element + "---> _gmxCrossLineSelected ");
        }
    },
    // 지도화면에서 마우스로 선택한 레이어의 특정 객체값을 전달한다.(인자: 레이어, 칼럼명, 칼럼값)
    _gmxLayerObjectSelected : function(lyr, col, key) {
        if (this.element) {
            alert(this.element + "---> _gmxLayerObjectSelected " + lyr + " / " + col + " / " + key);
        }
    },
    //지도에서 마우스로 check/uncheck 한 객체값을 전달한다.(인자: 레이어, 칼럼명, 칼럼값, checked/unchecked)
     _gmxLayerObjectChecked : function(lyr, col, key, chked){
         if (this.element) {
             alert(this.element + "---> _gmxLayerObjectChecked " + lyr + " / " + col + " / " + key+ " / " + chked);
         }
    },
    // 지도화면에서 로드뷰 위치를 지정하면 호출된다.
    _gmxRoadViewSelected : function(cx, cy) {
        if (this.element) {
            alert(this.element + "---> _gmxRoadViewSelected " + cx + " / " + cy);
        }
    },
    // 레이어 설정 버튼을 선택하였다.
    _gmxLayerConfigSelected : function() {
        if (this.element) {
            alert(this.element + "---> _gmxLayerConfigSelected ");
        }
    },
    // 인쇄를 요청한다.(인자 : 스타일, 지도영역좌표)
    _gmxShowPrint : function(xml, minx, miny, maxx, maxy) {
        if (this.element) {
            alert(this.element + "---> _gmxShowPrint");
        }
    },

    // 인쇄를 요청한다.(인자 : 스타일, 거리/면적 도형, 지도영역좌표)
    _gmxShowAddonPrint : function(xml, addon, minx, miny, maxx, maxy) {
        if (this.element) {
            alert(this.element + "---> _gmxShowAddonPrint");
        }
    },
    
    // /////////////////////////////////////////////////////////////////////////
    // WebPages -> 애플릿으로 호출하는 Method 시작 /////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////
    // 지도 도구 설정 ///////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // 지도도구를 설정한다.CTRL 참고
    setMapTool : function(tool) {
        this.element.setMapTool(tool);
    },
    // 지도도구를 설정한다. checked할 레이어를 설정한다.
    setCheckedMapTool : function(lyr){
        this.element.setCheckedMapMapTool(lyr);  
    },
    //지도화면에 레이어 Object를 Checked설정을 한다.(인자: 레이어명, 키칼럼명, 칼럼값,checked/unchecked)
    setCheckedObject : function(lyr, key, val, chked){
        this.element.setCheckedObject(lyr, key, val,chked); 
    },
    // 지도화면을 버퍼검색 모드로 변경한다.(인자: 레이어명, 버퍼거리, 스타일xml)
    searchBuffer : function(layer, meter, style) {
        this.element.searchBuffer(layer, meter, style);
    },
    // 지도화면의 로드뷰 위치및 방향값을 재설정한다.
    // 외부 로드뷰 화면에서 이동 및 회선시 값을 얻어서 지도화면으로 전달한다.
    setRoadViewpoint : function(px, py, deg) {
        this.element.setRoadViewpoint(px, py, deg);
    },
    // 특정 영상레이어의 offset을 설정하고 화면을 다시 그린다.
    setOpenMapOffset : function(lyr, xn, ye) {
        this.element.setOpenMapOffset(lyr, xn, ye);
    },
    // 현재화면에서 보이는(on설정) 영상레이어의 offset을 설정하고 화면을 다시 그린다.
    setVisibleOpenMapOffset : function(xn, ye) {
        this.element.setVisibleOpenMapOffset(xn, ye);
    },
    // 영상레이어의 Offset값을 얻는다. return (double[0]:가로, double[1]:세로)
    getOpenMapOffset : function(lyr) {
        return this.element.getOpenMapOffset(lyr);
    },
    // 레이어의 특정 객체를 마우스로 선택하도록 도구를 설정한다.
    // 객체를 호출하면_gmxLayerObjectSelected가 호출된다.
    selectLayerObject : function(lyr) {
        this.element.selectLayerObject(lyr);
    },
    // 현재지도화면 캡쳐이미지의 URL을 얻는다.
    getMapImage : function() {
        return this.element.getMapImage();
    },
    // 워터마크가 포함된 지도화면의 캡처 이미지 경로를 얻는다.
    getMarkedMapImage : function(str) {
        return this.element.getMarkedMapImage(str);
    },
    // 지도화면 스케일 값을 얻는다.
    getScaleFactor : function() {
        return this.element.getScaleFactor();
    },
    // 지도화면 영역값(배역)을 얻는다. (minx,miny,maxx,maxy);
    getBounds : function() {
        return this.element.getMapBounds();
    },
    // 지도화면 영역MinX 값을 얻는다.
    getMinX : function() {
        return this.element.getMapBounds()[0];
    },
    // 지도화면 영역MinY 값을 얻는다.
    getMinY : function() {
        return this.element.getMapBounds()[1];
    },
    // 지도화면 영역MaxX 값을 얻는다.
    getMaxX : function() {
        return this.element.getMapBounds()[2];
    },
    // 지도화면 영역MaxY 값을 얻는다.
    getMaxY : function() {
        return this.element.getMapBounds()[3];
    },
    // 지도화면 중심좌표 X값을 얻는다.
    getCenterX : function() {
        return this.element.getCenterX();
    },
    // 지도화면 중심좌표 Y값을 얻는다.
    getCenterY : function() {
        return this.element.getCenterY();
    },
    // 지도화면인쇄를 요청한다.
    // 지도화면 인쇄를 요청하면 applet에서 _gmxShowPrint 스크립트를 호출하여 필요한 값을 넘긴다
    printScreen : function() {
        this.element.setPrint();
    },
    // 지도화면인쇄를 요청한다.
    // 지도화면 인쇄를 요청하면 applet에서 _gmxShowAddonPrint 스크립트를 호출하여 필요한 값을 넘긴다
    printAddonScreen : function() {
        this.element.setAddonPrint();
    },
    // /////////////////////////////////////////////////////////////////////////
    // Layer,Label,Symbol,그리기도구, 관리도구, 레이어메뉴, 인덱스, 축척, 방위표시 , 2.5D /////
    // /////////////////////////////////////////////////////////////////////////
    // 레이어 보이기/감추기를 설정한다.
    setLayerVisible : function(layer, visible) {
        this.element.setLayerVisible(layer, visible);
    },
    // 레이어 현재 보이기/감추기 값을 얻는다.
    isLayerVisible : function(layer) {
        return this.element.isLayerVisible(layer);
    },
    // 레이어 보이기 설정값을 바꾼다.
    toggleLayerVisible : function(layer) {
        var toggle = this.isLayerVisible(layer) ? false : true;
        this.setLayerVisible(layer, toggle);
    },
    // 라벨 보이기/감추기를 설정한다.
    setLabelVisible : function(layer, visible) {
        this.element.setLabelVisible(layer, visible);
    },
    // 라벨 현재 보이기/감추기 값을 얻는다.
    isLabelVisible : function(layer) {
        return this.element.isLabelVisible(layer);
    },
    // 라벨 보이기 설정값을 바꾼다.
    toggleLabelVisible : function(layer) {
        var toggle = this.isLabelVisible(layer) ? false : true;
        this.setLabelVisible(layer, toggle);
    },
    // 심볼이미지 보이기/감추기를 설정한다.
    setSymbolVisible : function(layer, visible) {
        this.element.setSymbolVisible(layer, visible);
    },
    // 심볼이미지 현재 보이기/감추기 값을 얻는다.
    isSymbolVisible : function(layer) {
        return this.element.isSymbolVisible(layer);
    },
    // 심볼이미지 보이기 설정값을 바꾼다.
    toggleSymbolVisible : function(layer) {
        var toggle = this.isSymbolVisible(layer) ? false : true;
        this.setSymbolVisible(layer, toggle);
    },
    // 그리기 도구 메뉴 보이기 여부를 설정한다.
    setDrawToolVisible : function(visible) {
        this.element.setDrawToolVisible(visible);
    },
    // 그리기 도구메뉴 보이기 여부를 반환한다.
    isDrawToolVisible : function() {
        return this.element.isDrawToolVisible();
    },
    // 그리기 도구메뉴 보이기 설정값을 바꾼다.
    toggleDrawToolVisible : function() {
        var toggle = this.isDrawToolVisible() ? false : true;
        this.setDrawToolVisible(toggle);
    },
    // 관리도구 화면의 특정 탭 보이기를 선택한다.
    // value: TAB_LEGEND,TAB_ACTIVE,TAB_LAYER,TAB_STYLE,TAB_BOOKMARK,TAB_DRAW
    showMapCtrlTab : function(tab) {
        this.element.showMapCtrlTab(tab);
    },
    // 관리도구 화면 보이기 여부를 설정한다.
    setMapCtrlVisible : function(visible) {
        this.element.setMapCtrlVisible(visible);
    },
    // 관리도구 화면 보이기 여부를 반환한다.
    isMapCtrlVisible : function(visible) {
        return this.element.isMapCtrlVisible();
    },
    // 관리도구화면 보이기 설정값을 바꾼다.
    toggleMapCtrlVisible : function() {
        var toggle = this.isMapCtrlVisible() ? false : true;
        this.setMapCtrlVisible(toggle);
    },
    // 인덱스맵 화면 보이기 여부를 설정한다.
    setIndexMapVisible : function(visible) {
        this.element.setIndexMapVisible(visible);
    },
    // 인덱스맵 화면 보이기 여부를 설정한다.
    isIndexMapVisible : function(visible) {
        return this.element.isIndexMapVisible();
    },
    // 인덱스맵 보이기 설정값을 바꾼다.
    toggleIndexMapVisible : function() {
        var toggle = this.isIndexMapVisible() ? false : true;
        this.setIndexMapVisible(toggle);
    },
    // 지도화면에 스케일바 그리기를 설정한다.
    setScaleBarVisible : function(visible) {
        this.element.setScaleBarVisible(visible);
    },
    // 지도화면에 스케일바 그리기 여부를 얻는다.
    isScaleBarVisible : function() {
        return this.element.isScaleBarVisible();
    },
    // 지도화면에 스케일바 그리기 설정값을 바꾼다.
    toggleScaleBarVisible : function() {
        var toggle = this.isScaleBarVisible() ? false : true;
        this.setScaleBarVisible(toggle);
    },
    // 지도화면에 방위각 표시 그리기를 설정한다.
    setNorthSignVisible : function(visible) {
        this.element.setNorthSignVisible(visible);
    },
    // 지도화면에 방위각 표시여부를 얻는다.
    isNorthSignVisible : function() {
        return this.element.isNorthSignVisible();
    },
    // 지도화면에 방위각 그리기 설정값을 바꾼다.
    toggleNorthSignVisible : function() {
        var toggle = this.isNorthSignVisible() ? false : true;
        this.setNorthSignVisible(toggle);
    },
    // 맵 전체를 대상으로 레이어메뉴 보기 여부를 설정한다.
    // 스타일 설정보다 우선한다.
    setLayerMenuVisible : function(visible) {
        this.element.setLayerMenuVisible(visible);
    },
    // 폴리곤 2.5D View를 설정한다.
    setView25D : function(visible) {
        this.element.setView25D(visible);
    },
    // 2.5D View 여부를 얻는다.
    isView25D : function() {
        return this.element.isView25D();
    },
    // 2.5D View 설정값을 바꾼다.
    toggleView25D : function() {
        var toggle = this.isView25D() ? false : true;
        this.setView25D(toggle);
    },

    // //////////////////////////////////////////////////////////////////////////
    // 지도이동, 화면지우기 ///////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////
    // 지도화면(거리,면저, 그리기도구, 검색결과 등)을 지우도록 설정한다.
    // 바로 지우지 않고 다음 화면 그릴시 지워진다.
    setClearScreen : function() {
        this.element.setClearScreen();
    },
    // 지도화면(거리,면저, 그리기도구, 검색결과 등)을 지우고 바로 다시 그린다.
    doClearScreen : function() {
        this.element.doClearScreen();
    },
    // AddOnScreen(추가객체) 화면만(거리,면적,그리기도구등)을 지우고 화면을 다시그린다.
    clearAddOnScreen : function() {
        this.element.clearAddOnScreen();
    },
    // AddOnScreen(추가객체) 화면만(거리,면적,그리기도구등)을 다시그린다.
    redrawAddOnScreen : function() {
        this.element.redrawAddOnScreen();
    },
    // 지도화면 전체를 다시그린다. 서버에서 데이터를 다시 받아오지 않는다.
    // AddOnScreen도 다시 그린다.
    redrawScreen : function() {
        this.elememt.redrawScreen();
    },
    // 서버에서 데이터를 다시 읽어와 화면을 다시 그린다.
    resetExtent : function() {
        this.element.resetExtent();
    },
    // 초기화면설정 영역으로 지도화면을 이동한다.
    initExtent : function() {
        this.element.initExtent();
    },
    // 전체화면설정 영역으로 지도화면을 이동한다.
    fullExtent : function() {
        this.element.fullExtent();
    },
    // 히스토리에 저장된 이전화면으로 이동한다.
    prevExtent : function() {
        this.element.prevExtent();
    },
    // 히스토리에 저장된 다음화면으로 이동한다.
    nextExtent : function() {
        this.element.nextExtent();
    },
    // 현재 지도화면영역을 기준으로 해당스케일로 지도화면을 이동한다.
    moveExtentScale : function(scale) {
        this.element.moveExtent(scale);
    },
    // 해당영역으로 지도화면을 이동한다.
    moveExtentCoord : function(minx, miny, maxx, maxy) {
        this.element.moveExtent(minx, miny, maxx, maxy);
    },
    // 중심좌표(cx,cy)와 지도 스케일 값으로 지도를 이동한다.
    moveExtentCenter : function(cx, cy, scale) {
        this.element.moveExtent(cx, cy, scale);
    },
    // 중심좌표(cx,cy) 값으로 지도화면을 이동하고 위치표시를 한다.
    locateCenter : function(cx, cy) {
        this.element.locateCenter(cx, cy);
    },
    // 중심좌표(cx,cy) 값으로 지도를 이동하고 Scale을 적용하여 위치표시를 한다.
    locateCenterScale : function(cx, cy, scale) {
        this.element.locateCenter(cx, cy, scale);
    },

    // //////////////////////////////////////////////////////////////////////////
    // 속성 검색 ///////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////
    // 지도화면에 하이라이트할 레이어 객체들을 등록한다.(인자: 레이어명, 키값)
    // 내부적으로 searchValue로 등록된 후 searchAndMove 호출시 반영되어 지도화면에 표시된다.
    addSearchLayer : function(layer, key) {
        this.element.addSearchLayer(layer, key);
    },
    // 지도화면에 하이라이트할 레이어 객체들을 등록한다. (인자: 레이어명, 키값 배열)
    // 내부적으로 searchValue로 등록된 후 searchAndMove 호출시 반영되어 지도화면에 표시된다.
    addSearchLayers : function(layer, keys) {
        this.element.addSearchLayers(layer, keys);
    },
    // 검색할 레이어 객체를 설정하고 지도를 이동한다.(인자: 레이어명, 키값, 축척) return: true/false
    // addSearchLayer로 설정한 결과도 검색결과 영역내 존재하는 경우만 보여진다.
    searchAndMove : function(layer, key, scale) {
        return this.element.searchAndMove(layer, key, scale);
    },
    // 검색할 레이어 객체들을 설정하고 지도를 이동한다.(인자: 레이어명, 키값, 축척) return: true/false
    // addSearchLayer로 설정한 결과도 검색결과 영역내 존재하는 경우만 보여진다.
    searchAndMoves : function(layer, keys, scale) {
        return this.element.searchAndMoves(layer, keys, scale);
    },
    // 검색할 레이어 객체를 설정하고 지도를 이동한다.(인자: 레이어명, 키값) return: true/false
    // addSearchLayer로 설정한 결과도 검색결과 영역내 존재하는 경우만 보여진다.
    // 축척은 자동으로 계산하여 이동한다.
    searchAndMoveNoScale : function(layer, key) {
        return this.element.searchAndMove(layer, key);
    },
    // 검색할 레이어객체들을 설정하고 지도를 이동한다.(인자: 레이어명, 키값) return: true/false
    // addSearchLayer로 설정한 결과도 검색결과 영역내 존재하는 경우만 보여진다.
    // 축척은 자동으로 계산하여 이동한다.
    searchAndMovesNoScale : function(layer, keys) {
        return this.element.searchAndMoves(layer, keys);
    },
    // 레이어에서 조건쿼리를 만족하는 객체를 하이라이트하고 영역으로 이동한다.(인자: 레이어명, 조건문)
    // return: true/false
    // addSearchLayer로 설정한 결과도 검색결과 영역내 존재하는 경우만 보여진다.
    // 축척은 자동으로 계산하여 이동한다.
    searchAndMoveWhere : function(layer, where) {
        return this.element.searchAndMoveWhere(layer, where);
    },
    // 레이어에서 조건쿼리를 만족하는 객체를 하이라이트하고 영역으로 이동한다.(인자: 레이어명, 조건문)
    // return: true/false
    // addSearchLayer로 설정한 결과도 검색결과 영역내 존재하는 경우만 보여진다.
    searchAndMoveWhereScale : function(layer, where, scale) {
        return this.element.searchAndMoveWhere(layer, where, scale);
    },
    // 현재화면에서 하이라이트할 레이어 객체를 화면(AddOnScreen)에 표시한다. 지도이동은 하지 않는다.
    // return: true/false
    setSearch : function(layer, key) {
        return this.element.setSearch(layer, key);
    },
    // 현재화면에서 하이라이트할 레이어 객체들를 화면(AddOnScreen)에 표시한다. 지도이동은 하지 않는다.
    // return: true/false
    setSearchs : function(layer, keys) {
        return this.element.setSearchs(layer, keys);
    },

    // /////////////////////////////////////////////////////////////////////////
    // 사용자 도형(AddOnShape)그리기//////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // 지도화면을 지우거나 이동하지 않고 사용자 도형을 추가(AddOnShape)한다.
    // 인자: style- 스타일xml, wkt- 도형정보
    addShape : function(style, wkt) {
        this.element.addShape(style, wkt, false, false);
    },
    // 지도화면을 지우거나 이동하지 않고 사용자 도형을 추가(AddOnShape)한다.
    // wkt배열의 도형에 동일한 스타일 정보를 적용한다.
    // 인자: style- 스타일xml, wkt- 도형정보배열[]
    addOneStyleShapes : function(style, wkt) {
        this.element.addOneStyleShapes(style, wkt, false);
    },
    // 지도화면을 지우거나 이동하지 않고 사용자 도형을 추가(AddOnShape)한다.
    // wkt[]배열의 도형에 동일한 크기의 style[]배열의 정보를 적용한다.
    // 인자: style- 스타일xml[], wkt- 도형정보배열[]
    addMultiStyleShapes : function(style, wkt) {
        this.element.addMultiStyleShapes(style, wkt, false);
    },

    // 지도화면을 지우지 않고 사용자 도형을 추가(AddOnShape)한 후 지도화면을 이동한다.
    // 인자: style- 스타일xml, wkt- 도형정보
    addShapeAndMove : function(style, wkt) {
        this.element.addShape(style, wkt, false, true);
    },
    // 지도화면을 지우지 않고 사용자 도형을 추가(AddOnShape)한 후 지도화면을 이동한다.
    // wkt배열의 도형에 동일한 스타일 정보를 적용한다.
    // 인자: style- 스타일xml, wkt- 도형정보배열[]
    addOneStyleShapesAndMove : function(style, wkt) {
        this.element.addOneStyleShapes(style, wkt, false, true);
    },
    // 지도화면을 지우지 않고 사용자 도형을 추가(AddOnShape)한 후 지도화면을 이동한다.
    // wkt[]배열의 도형에 동일한 크기의 style[]배열의 정보를 적용한다.
    // 인자: style- 스타일xml[], wkt- 도형정보배열[]
    addMultiStyleShapesAndMove : function(style, wkt) {
        this.element.addMultiStyleShapes(style, wkt, false, true);
    },
    // 지도화면을 우선 지운 후 사용자 도형을 추가(AddOnShape)한 후 지도화면을 이동한다.
    // 인자: style- 스타일xml, wkt- 도형정보
    resetShapeAndMove : function(style, wkt) {
        this.element.addShape(style, wkt, true, true);
    },
    // 지도화면을 우선 지운 후사용자 도형을 추가(AddOnShape)한 후 지도화면을 이동한다.
    // wkt배열의 도형에 동일한 스타일 정보를 적용한다.
    // 인자: style- 스타일xml, wkt- 도형정보배열[]
    resetOneStyleShapesAndMove : function(style, wkt) {
        this.element.addOneStyleShapes(style, wkt, true, true);
    },
    // 지도화면을 우선 지운 후 사용자 도형을 추가(AddOnShape)한 후 지도화면을 이동한다.
    // wkt[]배열의 도형에 동일한 크기의 style[]배열의 정보를 적용한다.
    // 인자: style- 스타일xml[], wkt- 도형정보배열[]
    resetMultiStyleShapesAndMove : function(style, wkt) {
        this.element.addMultiStyleShapes(style, wkt, true, true);
    },

    // /////////////////////////////////////////////////////////////////////////
    // 스타일 설정 //////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // 지도도구의 기본스타일을 설정한다. 거리,면적재기등 사용하는 스타일 정보(xml)
    setMapToolStyle : function(style) {
        this.element.setMapToolStyle(style);
    },
    // 특정 레이어의 검색결과 스타일을 설정한다.(인자: 레이어명, 스타일(XML)
    setSearchedStyle : function(lyr, style) {
        this.element.setSearchedStyle(lyr, style);
    },
    // 특정 레이어의 마우스 선택시 스타일을 설정한다.(인자: 레이어명, 스타일(XML)
    setSelectedStyle : function(lyr, style) {
        this.element.setSelectedStyle(lyr, style);
    },
    // 현재 축척에서 가능한 레이어 그룹 및 레이어 목록 XML을 얻는다.
    getVisibleLayerXML : function() {
        return this.element.getLayerXML(false);
    },
    // 지도화면에 등록된 레이어 그룹 및 레이어 목록 XML을 얻는다.
    getLayerXML : function() {
        return this.element.getLayerXML(true);
    },
    // 지도화면에 적용된 레이어의 Default 스타일 XML을 얻는다.
    getStyleXML : function(lyr) {
        return this.element.getStyleXML(lyr);
    },
    // 지도전체 설정정보 XML을 얻는다.
    getMapCfgXML : function() {
        return this.element.getMapCfgXML();
    },
    // 현재 지도 화면에 맵 설정정보를 적용하나 DB를 갱신하지는 않는다.
    applyMapCfgXML : function(xml) {
        return this.element.applyMapCfgXML(xml);
    },
    // 현재 지도 화면에 맵 설정정보를 적용하고 DB를 갱신한다.
    // 현재 지도화면의 설정정보를 변경하고 DB를 갱신한다.
    replaceMapCfgXML : function(xml) {
        return this.element.replaceMapCfgXML(xml);
    },
    // 현재 지도 화면에 설정정보를 적용하고 DB에 추가한다.
    // 현재 지도화면의 설정정보를 변경하고 db에 추가하여 저장한다.
    addMapCfgXML : function(title, xml) {
        return this.element.addMapCfgXML(title, xml);
    },
    //지도에 워터마크 이미지를 등록한다.
    //url   :  워터마크 이미지 url
    //alpha : 워커마크 이미지 투명동(0.0~1.0)
    setWatermark : function(url, alpha){
       this.element.setWatermarkImage(url, alpha);
    },
    
    //워터마크 그리기 여부를 설정한다. 
    //draw : true,false
    setDrawWatermark : function(draw){
    	this.element.setDrawWatermark(draw);
    },
    // 애플릿 객체를 강제로 종료한다.
    destroy : function() {
        this.element.destroy();
    }
};
