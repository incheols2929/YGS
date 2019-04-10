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

<%
		request.setCharacterEncoding("utf-8");
		String usr_id = Utils.getParam(request, "usr_id"); //usr_id는 변수,넘겨옴
		String usr_pw = Utils.getParam(request, "usr_pw");//usr_pw는 변수,넘겨옴
		
		String pp = Utils.getParam(request, "usr_pw"); //usr_pw는 변수,넘겨옴

		UserinfoBean UB = new UserinfoBean();//사용자에 관한 모든 정보
		Userinfo UI = new Userinfo();  //사용자정보
		
		int checkIDX = UB.getLoginCheck(usr_id, usr_pw); //사용자유무확인
        
%>

<data>
	<%
		if(checkIDX == 1){
			
			UI = UB.getLoginList(usr_id, usr_pw); //사용자 정보를 가져온다 17,18줄에서 넘겨온 id,pw를 여기서 사용
			
			if(" ".equals(UI.getDept_cd())){//공백과 dept_cd의 값이 같으면 no
	%>
			<deptchk>no</deptchk>
	<%			
			}else{
	%>
			<deptchk>success</deptchk> <!-- 다르면 success 즉 새로운 데이터면 success임 -->
	<%			
			}
		}else if(checkIDX == 0){
	%>
		<deptchk>pw_fail</deptchk> 
	<%
		}else{
	%>
		<deptchk>id_fail</deptchk>
	<%
		}
	%>
</data>

