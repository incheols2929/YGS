<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.svc.handler.Code"%>
<%String geoStyle = Code.getGeovletStyle(); %>
<%=geoStyle.replaceAll("\n", "").replaceAll("	", "")%>
	