<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>
<%@page import="geomex.utils.Utils"%>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@page import="geomex.pkg.usr.Favorites"%>
<%@page import="geomex.pkg.usr.FavoritesBean"%>

<%
	Object SESSION_ID = session.getAttribute("SESSION_ID");
	String id = (String)SESSION_ID;

	FavoritesBean FB = new FavoritesBean();
	boolean bool = FB.getUserDEL(id);
%>

<data>
	<%
		if(bool){
	%>
		<val>success</val>
	<%
		}else{
	%>
		<val>fail</val>
	<%
		}
	%>
</data>