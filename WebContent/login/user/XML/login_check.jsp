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
<%@page import="geomex.pkg.usr.UseLog"%>
<%@page import="geomex.pkg.usr.UseLogBean"%>
<!-- 로그인 체크 및 유효성 검사 -->
<%
		request.setCharacterEncoding("utf-8");
		String usr_id = Utils.getParam(request, "usr_id");//usr_id는 변수,넘겨옴
		String usr_pw = Utils.getParam(request, "usr_pw");//usr_pw는 변수,넘겨옴
		
		String pp = Utils.getParam(request, "usr_pw");//usr_pw는 변수,넘겨옴

		UserinfoBean UB = new UserinfoBean(); //사용자에 관한 모든 정보
		Userinfo UI = new Userinfo(); //사용자 정보
		UI = UB.getLoginList(usr_id, usr_pw); //사용자 정보를 가져옴
		
		//int checkIDX = UB.getLoginCheck(usr_id, usr_pw);
%>

<data>
	<%
			if("N".equals(UI.getApp_yn())){ //app_yn 승인여부 DB데이터가 N와 같으면
	%>
			<loginresult>no</loginresult><!-- no -->
	<%			
			}else{
				
				String ampm = "";
				String login_time = "";
				String conn_time = "";
							
				login_time = Utils.formatTxtDate(Utils.getStrSec()); //접속시간-1
				conn_time = Utils.getStrSec(); //현재 년월일시분초 문자열을 반환한다.
				
				String user_ip = request.getRemoteAddr();
				//session.setMaxInactiveInterval(20*60);	//세션타임
				session.setAttribute("SESSION_NAME", UI.getUsr_nm()); //사용자명
				session.setAttribute("SESSION_ID", UI.getUsr_id()); //사용자아이디
				session.setAttribute("SESSION_DEPT", UI.getDept_cd()); //사용자부서코드
				session.setAttribute("SESSION_IP", user_ip); //사용자IP
				//session.setAttribute("SESSION_TIME", login_time.substring(10, login_time.length())); //접속시간
				session.setAttribute("SESSION_TIME", login_time); //접속시간-1
				session.setAttribute("SESSION_TIME_2", conn_time); //접속시간-2
				session.setAttribute("SESSION_UGRP_ID", UI.getUgrp_id()); //사용자 그룹ID
				session.setAttribute("SESSION_DEPT_YN", UI.getDept_cha_yn()); //추가 부서코드 변경에 필요한 세션코드
				
				
				//화면접속시 세션의 정보를 가져와서 사용자 접속정보를 입력한다. 즉 접속기록이 남음
				UseLogBean ULB = new UseLogBean();
				ULB.setUseLoginRecord("사용자로그인접속", UI.getUsr_id(), UI.getDept_cd(), "L0001", "L", conn_time, user_ip);
				//ULB.setConnLogRecord(UI.getUsr_id(), UI.getDept_cd(), user_ip, conn_time);
				
	%>
			<loginresult>success</loginresult>
	<%			
			}
	%>
</data>

