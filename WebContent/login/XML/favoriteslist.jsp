<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@page import="geomex.pkg.usr.Favorites"%>
<%@page import="geomex.pkg.usr.FavoritesBean"%>

<%
	Object SESSION_ID = session.getAttribute("SESSION_ID");
	String id = (String)SESSION_ID;
	
	FavoritesBean FB = new FavoritesBean();
	String xmlpar = FB.getXMLcreate(id);
%>

<%
	if(!"".equals(xmlpar)){
%>
	<%= xmlpar%>
<%
	}else{
%>
	<data>
		<user>true</user>
		<favorites>
			<dforder>
				<name>행정업무지원</name>
				<id>d_forder1</id>
				<linkitemid>linkitem_1</linkitemid>
			</dforder>
			<sforder>
				<id>sfoderid_1_1</id>
				<sitename> 반비넷(시도행정정보화)</sitename>
				<url>http://106.0.2.160/sso/Login/Portal/SSOLogin.jsp</url>
			</sforder>
			<sforder>
				<id>sfoderid_1_2</id>
				<sitename> 공직자 통합메일(코리아메일)</sitename>
				<url>http://mail.korea.kr/</url>
			</sforder>
			<sforder>
				<id>sfoderid_1_5</id>
				<sitename> 도로명주소안내시스템</sitename>
				<url>http://www.juso.go.kr/openIndexPage.do</url>
			</sforder>
		</favorites>
		<favorites>
			<dforder>
				<name>GIS업무관련</name>
				<id>d_forder2</id>
				<linkitemid>linkitem_2</linkitemid>
			</dforder>
			<sforder>
				<id>sfoderid_2_1</id>
				<sitename> 국가공간정보통합체계</sitename>
				<url>http://www.nsdi.go.kr/</url>
			</sforder>
			<sforder>
				<id>sfoderid_2_2</id>
				<sitename> 국가GIS통합포털</sitename>
				<url>http://www.ngis.go.kr/</url>
			</sforder>
			<sforder>
				<id>sfoderid_2_3</id>
				<sitename> 국토지리정보원</sitename>
				<url>http://www.ngii.go.kr/index.do</url>
			</sforder>
			<sforder>
				<id>sfoderid_2_4</id>
				<sitename> 국토공간영상정보서비스</sitename>
				<url>http://air.ngii.go.kr/index.do</url>
			</sforder>
		</favorites>
		<favorites>
			<dforder>
				<name>통합포털</name>
				<id>d_forder3</id>
				<linkitemid>linkitem_3</linkitemid>
			</dforder>
			<sforder>
				<id>sfoderid_3_1</id>
				<sitename> 네이버지도</sitename>
				<url>http://map.naver.com/</url>
			</sforder>
			<sforder>
				<id>sfoderid_3_2</id>
				<sitename> 다음지도</sitename>
				<url>http://local.daum.net/map/index.jsp</url>
			</sforder>
		</favorites>
	</data>
<%
	}
%>


