<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../../../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->
<%
    request.setCharacterEncoding("UTF-8");
    String ftr_idn = request.getParameter("ftr_idn");
    session.setAttribute("SESSION_FTR_IDN", ftr_idn); //선택한 시설물 관리번호
    System.out.println("세션저장 : "+ftr_idn);
    
%>a