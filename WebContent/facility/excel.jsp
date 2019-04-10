<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.svc.handler.Code"%>
<%@ include file="../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->
<%
	//단위업무관리ID를 생성
	Object SESSION_BIZID = session.getAttribute("SESSION_BIZID");
	String session_bizid = (String)SESSION_BIZID;
	String dept_nm = Code.getDeptNM(session_dept);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
	.border-line1{
		border-right:1px solid #b2b2b2;
		border-bottom:1px solid #b2b2b2;
	}
	.border-line2{
		border-bottom:1px solid #b2b2b2;
	}
	.table_border{
		border:4px solid #0068b7;
	}
</style>

<div  id="excelfrm" style="display:none; position: absolute; top:50%; left:50%; margin:-100px 0 0 -160px; width:350px; height:250px; background-color:#ffffff;">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" class="table_border">
		<tr>
			<td colspan="3" height="25" align="center" class="border-line2" style="background-color: #c4d7f1;"><b>- 엑셀내려받기 -</b></td>
		</tr>
		<tr align="center">
			<td rowspan="3" class="border-line1" style="background-color: #c4d7f1;">사용자정보</td>
			<td height="25" class="border-line1">&nbsp;사용자명</td>
			<td height="25" class="border-line2" align="left">&nbsp;<%=session_name %></td>
		</tr>
		<tr>
			<td height="25" width="20%" class="border-line1" align="center">&nbsp;사용자아이디</td>
			<td height="25" class="border-line2" align="left">&nbsp;<%=id%></td>
		</tr>
		<tr>
			<td height="25" class="border-line1" align="center">&nbsp;부서명</td>
			<td height="25" class="border-line2" align="left">&nbsp;<%=dept_nm %></td>
		</tr>
		<tr align="center">
			<td width="20%" height="80" class="border-line1" style="background-color: #c4d7f1;">사용목적</td>
			<td width="80%" colspan="2" class="border-line2">
				<textarea id="use_resn" name="use_resn" rows="4" style="width:95%;"></textarea>
			</td>
		</tr>
		<tr>
			<td align="center" height="25" colspan="3"  class="border-line2" style="background-color: #e3e4e4;">* 사용목적을 입력해 주세요!</td>
		</tr>
		<tr>
			<td height="25" colspan="3" align="center" bgcolor="#e3e4e4"><a href="#" onclick="_PC.init.excelFcltLogInsert();">확인</a> | <a href="#" onclick="_PC.init.excelFormClose();">닫기</a></td>
		</tr>
		<input type="hidden" id="user_id" name="user_id" value="<%=id%>"/><!-- 사용자아이디 -->
		<input type="hidden" id="dept_id" name="dept_id" value="<%=session_dept%>"/><!-- 부서코드 -->
		<input type="hidden" id="func_id" name="func_id" value="FW010"/><!-- 단위기능관리ID -->
		<input type="hidden" id="biz_id" name="biz_id" value="<%=session_bizid%>"/><!-- 단위업무관리ID -->
	</table>
</div>