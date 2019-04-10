<%@page import="geomex.utils.Utils"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.OutputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String map = Utils.getParam(request, "map");
	
	ServletContext context = getServletContext();	
	String uFolder = context.getRealPath("WEB-INF\\temp");
	
	//OutputStream스트림은 jsp페이지에서 안되지만 아래 두줄을
	//써줌으로 강제적으로 가능하게함.
	if(!"".equals(Utils.chkNull(map)))
	{
		out.clear(); 
		out=pageContext.pushBody();
		response.setHeader("Content-Type", "image/png");	
		OutputStream os = null;		
		FileInputStream fis = null;
		try {							
			os=response.getOutputStream();			
		    byte[] b = new byte[1024];
		    int length = 0;
			fis = new FileInputStream(uFolder+"\\"+map);
			while((length = fis.read(b)) > -1){
				os.write(b, 0, length);
			}			
			os.flush();
		} catch(Exception e) {
			e.printStackTrace();
		}finally{
			try{
				fis.close();
			}catch(Exception e){}
			try{
				os.close();
			}catch(Exception e){}
		}	
		
	}else{
%>
	<script>alert("이미지가 존재하지 않습니다.");</script>
<%
	}
%>