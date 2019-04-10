<%@page import="geomex.utils.Utils"
%><%@page import="java.util.Calendar"
%><%
    
	Object SESSION_ID = session.getAttribute("SESSION_ID"); //아이디
	Object SESSION_TIME = session.getAttribute("SESSION_TIME"); //접속시간
	Object SESSION_TIME_2 = session.getAttribute("SESSION_TIME_2"); //접속시간
	Object SESSION_NAME = session.getAttribute("SESSION_NAME"); //사용자명
	Object SESSION_DEPT = session.getAttribute("SESSION_DEPT"); //부서코드
	Object SESSION_IP = session.getAttribute("SESSION_IP"); //접속아이피
	Object SESSION_UGRP_ID = session.getAttribute("SESSION_UGRP_ID"); //사용자 그룹정보
	Object SESSION_DEPT_YN = session.getAttribute("SESSION_DEPT_YN"); //부서코드변경
	Object SESSION_PURPOSE = session.getAttribute("SESSION_PURPOSE");
    Object SESSION_FTR_IDN = session.getAttribute("SESSION_FTR_IDN"); 
	
    String id = (String)SESSION_ID;	
	String session_time = (String)SESSION_TIME;
	String session_time_2 = (String)SESSION_TIME_2;
	String session_name = (String)SESSION_NAME;	
	String session_dept = (String)SESSION_DEPT;	
	String session_ip = (String)SESSION_IP;	
	String session_ugrp_id = (String)SESSION_UGRP_ID;
	String session_dept_yn = (String)SESSION_DEPT_YN;
	String session_purpose = (String)SESSION_PURPOSE;
	String session_ftr_idn = (String)SESSION_FTR_IDN; 
	String now_time = Utils.getStrSec(); //2013.10.08�
	
	/*Calendar today = Calendar.getInstance();
	int year = today.get(Calendar.YEAR);
	int month = today.get(Calendar.MONTH)+1;
	int date = today.get(Calendar.DATE);
	int hour = today.get(Calendar.HOUR);
	int minute = today.get(Calendar.MINUTE);
	int second = today.get(Calendar.SECOND);
	now_time = year+""+month+""+date+""+hour+""+minute+""+second;*/
	
%>