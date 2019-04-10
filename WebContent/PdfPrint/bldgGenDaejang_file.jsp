<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W4C//DTD HTML 4.01 Transitional//EN" "http://www.w4.org/TR/html4/loose.dtd">
<%@page import="geomex.utils.pdf.BldgGenExportURLPDF"%>
<%@page import="geomex.utils.FileDown"%>
<%@page import="geomex.utils.Utils"%>
<%
	request.getParameter("UTF-8");
	//일반건축물대장
	String key =  request.getParameter("key");
	String pnu =  request.getParameter("pnu");
	String user_name =  request.getParameter("user_name");
	
	BldgGenExportURLPDF EP = new BldgGenExportURLPDF();
	String filneme = EP.CreateURLPDF(key, pnu, user_name);

	ServletContext context = getServletContext();	
	String uFolder = context.getRealPath("WEB-INF\\temp");
	
	//OutputStream스트림은 jsp페이지에서 안되지만 아래 두줄을
	//써줌으로 강제적으로 가능하게함.
	if(!"".equals(Utils.chkNull(filneme)))
	{
		out.clear(); //out--> jsp
		out=pageContext.pushBody(); //out--> jsp	
		FileDown fd = new FileDown(request, response);
		fd.write(uFolder+"\\"+filneme, filneme, "aapplication/x-msdownload");
	}else{
%>
	<script>alert("파일이 존재하지 않습니다.");</script>
<%
	}
%>
 
 
 