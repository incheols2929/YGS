<%@page import="geomex.utils.FileDown"%>
<%@page import="geomex.utils.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String imgName = Utils.getParam(request, "fileName");
	ServletContext context = getServletContext();	
	String uFolder = context.getRealPath("WEB-INF\\temp");
	//String uFolder = "G:\\WEB-APPS\\geomex\\WEB-INF\\temp";

	//OutputStream스트림은 jsp페이지에서 안되지만 아래 두줄을
	//써줌으로 강제적으로 가능하게함.
	if(!"".equals(Utils.chkNull(imgName)))
	{
		out.clear(); //out--> jsp
		out=pageContext.pushBody(); //out--> jsp	
		FileDown fd = new FileDown(request, response);
		fd.write(uFolder+"\\"+imgName,"map.png","application/x-msdownload");
	}else{
%>
	<script>alert("이미지가 존재하지 않습니다.");</script>
<%
	}
%>