<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="geovlet.io.geotrans.*"%>
<%@page import="geovlet.io.geotrans.GeoTrans"%>
<%@page import="geovlet.io.geotrans.Base"%>
<%@page import="geovlet.io.geotrans.Ellipsoid"%>
<%@page import="geovlet.io.geotrans.GeoParam"%>
<%@page import="geomex.utils.Utils"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>

<%
String TmX = Utils.getParam(request, "tmx");
String TmY = Utils.getParam(request, "tmy");


//구글에 맞춰서
int _offsetX = 0;
int _offsetY = 0;
boolean _isError = false;
Base  rdBase = null;
Ellipsoid rdEllipsoid = null;
Base gwBase = null;
Ellipsoid gwEllipsoid = null;

/*
<Ellipsoid name='wgs84' a='6378137.0'  f='298.257223563' />
<Base name="tm127" xn="500000.0" ye="200000.0" lat="38.0" lon="127" sf="1.0"/>
*/

rdBase = new Base("tm127", GeoModel.TM, 500000.0, 200000.0, 38.0, 127.0, 1.0);
rdEllipsoid = new Ellipsoid("wgs84", 6378137.0, 298.257223563);

gwBase = new Base("tm129", GeoModel.TM, 500000.0, 200000.0, 38.0, 129.00289, 1.0);
gwEllipsoid = new Ellipsoid("bessel",  6377397.15, 299.152813);


//Bessel TM중부좌표를 WGS84 경위도로 바꾸기
//서부 TM125, 중부 TM127, 동부 TM129
//원본 좌표체계
GeoParam src = new GeoParam(rdEllipsoid, rdBase);
//변활될 자표체계
GeoParam tgt = new GeoParam(gwEllipsoid, gwBase);
GeoTrans trans = new GeoTrans();
trans.setModel(src, tgt);

//LatiLong  v = trans.STM2TGP(Double.parseDouble(TmY),Double.parseDouble(TmX));
//XnYe xy = trans.STM2TTM(Double.parseDouble(TmY),Double.parseDouble(TmX));

XnYe xy = trans.SGP2TTM(Double.parseDouble(TmY), Double.parseDouble(TmX));

%>
<Coord>
	<x><%=xy.y%></x>
	<y><%=xy.x%></y>
</Coord>