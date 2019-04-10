<?xml version="1.0" encoding="UTF-8"?>
<%@page import="java.net.URLDecoder"%>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="geomex.utils.Utils"%>
<%@page import="geomex.svc.handler.Code"%>
<%@page import="java.util.List" %>    
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@page import="geomex.pkg.usr.Userinfo"%>
<%@page import="geomex.pkg.usr.UserinfoBean"%>

<%
		request.setCharacterEncoding("utf-8");
		
		String usr_id = Utils.getParam(request, "usr_id");
		String usr_nm = Utils.getParam(request, "usr_nm");
		String usr_pw = Utils.getParam(request, "usr_pw");
		String dept_cd = Utils.getParam(request, "dept_cd");
		String tel_num = Utils.getParam(request, "tel_num");
		String email = Utils.getParam(request, "email");
		
		UserinfoBean UB = new UserinfoBean();
		boolean idnmbool = UB.getUsrNMCheck(usr_id, usr_nm);
%>

<data>
	<%
		if(idnmbool){
	%>
		<result>same</result>
	<%		
		}else{
			
			boolean idbool = UB.getIDCheck(usr_id);	
			if(idbool){
	%>
		 <result>id_same</result>
	<%
			}else{						
				//boolean bool = UB.getUserReg(usr_id, usr_nm, usr_pw, dept_cd, tel_num, email);
				if(false){
	%>
					<result>success</result>
	<%		
					}else{
	%>
				<result>fail</result>
	<%			
				}
			}
		}
	%>
</data>

