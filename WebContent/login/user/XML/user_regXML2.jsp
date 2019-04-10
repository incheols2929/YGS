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
		
		String usr_id = Utils.getParam(request, "usr_id");//usr_id는 변수,넘겨옴
		String usr_nm = Utils.getParam(request, "usr_nm"); //밑에까지 동일
		String usr_pw = Utils.getParam(request, "usr_pw");//위와 동일
		String dept_cd = Utils.getParam(request, "dept_cd"); //위와 동일
		String tel_num = Utils.getParam(request, "tel_num");//위와동일
		String email = Utils.getParam(request, "email");//위와 동일
		
		UserinfoBean UB = new UserinfoBean();
		boolean divbool = UB.getDivCheck(usr_id); //기존시스템에있는 사용자인지 판단한다.
%>

<data>
	<%
		if(divbool){
	%>
		<result>div_same</result>  <!-- 기존의 등록되어 있는 사용자라면 -->
	<%		
		}else{
			boolean idbool = UB.getIDCheck(usr_id);	//아이디 중복확인
			if(idbool){
	%>
		 <result>id_same</result> <!-- 같은 아이디가 존재한다면 -->
	<%
			}else{						
				boolean bool = UB.getUserReg(usr_id, usr_nm, usr_pw, dept_cd, tel_num, email); //사용자 등록신청
				if(bool){
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

