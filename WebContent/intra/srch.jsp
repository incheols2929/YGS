<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../ssesion/ssesion.jsp" %>
<%
	String BIZ_ID = request.getParameter("bizid");
	session.setAttribute("SESSION_BIZID", BIZ_ID);
	Object SESSION_BIZID = session.getAttribute("SESSION_BIZID");
	String session_bizid = (String)SESSION_BIZID;
	
	Object USR_DEPT = session.getAttribute("SESSION_DEPT");
	String dept = (String)USR_DEPT;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="shortcut icon" type="image/x-icon" href="/favicon.ico">
<link rel="stylesheet" type="text/css" href="./css/main.css" />
<link rel="stylesheet" type="text/css" href="./css/layout-default-latest.css" />
<link rel="stylesheet" type="text/css" href="./css/layout_style.css" />
<link rel="stylesheet" type="text/css" href="./css/luris.css" />
<link rel="stylesheet" type="text/css" href="../login/css/fnd.css" />
<link rel="stylesheet" type="text/css" href="../login/css/qtip.css" />
<script type="text/javascript" src="./script/jquery-1.6.2.js"></script>
<script type="text/javascript" src="./script/gws.condSrch.js"></script>
<script type="text/javascript" src="./script/gws.pageCtrl.js"></script>
<script type="text/javascript" src="./script/gws.Ctrl.js"></script>
<script type="text/javascript" src="./script/jquery.showLoading.js"></script>
<script type="text/javascript" src="./script/jquery.outerhtml.js"></script>
<script type="text/javascript" src="./script/jquery.selectboxes.js"></script>
<script type="text/javascript" src="./script/download.jQuery.js"></script>
<script type="text/javascript" src="../login/script/jquery.bpopup-0.5.1.min.js"></script>
<script type="text/javascript" src="../login/script/jquery.qtip-1.0.0-rc3.js"></script>
<script type="text/javascript" src="../geomex/geomex.js"></script>
<script type="text/javascript" src="../geomex/gmx.Ctrl.js"></script>
</head>

<script type="text/javascript">
var SESSION_BIZID = "<%=session_bizid%>";
var SESSION_ID = "<%=id%>";
var SESSION_UGRP_ID = "<%=session_ugrp_id%>";
var SESSION_DEPT = "<%=dept%>";
</script>

<body>

</body>
</html>