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

//String TmX = "376425";
//String TmY = "587015";
//System.out.println(TmX);

//String sBase = Utils.getParam(request,"sbase");
//String sEllipsoid = Utils.getParam(request,"sellipsoid");
//String tBase = Utils.getParam(request,"tbase");
//String tEllipsoid = Utils.getParam(request,"tellipsoid");

String offsetX = Utils.getParam(request,"offset_x");
String offsetY = Utils.getParam(request,"offset_y");
String isError = Utils.getParam(request,"iserr");

//구글에 맞춰서
int _offsetX = -17;
int _offsetY = 25;

boolean _isError = false;
Base  naverBase = null;
Ellipsoid naverEllipsoid = null;
Base gwBase = null;
Ellipsoid gwEllipsoid = null;

/*
<Ellipsoid name='wgs84' a='6378137.0'  f='298.257223563' />
<Base name="tm127" xn="500000.0" ye="200000.0" lat="38.0" lon="127" sf="1.0"/>
*/

naverBase = new Base("tm127", GeoModel.TM, 600000.0, 200000.0, 38.0, 127.0, 1.0);
naverEllipsoid = new Ellipsoid("wgs84", 6378137.0, 298.257222101);
gwBase = new Base("tm127",GeoModel.TM, 500000.0, 200000.0, 38.0, 127.0, 1.0);
gwEllipsoid = new Ellipsoid("wgs84", 6377397.155, 299.152813);


if(!"".equals(Utils.chkNull(offsetX)))
{
	_offsetX = Integer.parseInt(offsetX);
    
}
if(!"".equals(Utils.chkNull(offsetY)))
{
	_offsetY = Integer.parseInt(offsetY);
	
}


//Bessel TM중부좌표를 WGS84 경위도로 바꾸기
//서부 TM125, 중부 TM127, 동부 TM129
//원본 좌표체계
GeoParam src = new GeoParam(naverEllipsoid, naverBase, _offsetX, _offsetY);

//변활될 자표체계
 
GeoParam tgt = new GeoParam(gwEllipsoid, gwBase);
GeoTrans trans = new GeoTrans();
 trans.setModel(src, tgt);


 //trans.STM2TGP(xn, ye)
 LatiLong  v = trans.STM2TGP(Double.parseDouble(TmY),Double.parseDouble(TmX));
 //XnYe xy = trans.STM2TTM(Double.parseDouble(TmY),Double.parseDouble(TmX));

%>
<Coord>
	<x><%=v.longitude%></x>
	<y><%=v.latitude%></y>
</Coord>
