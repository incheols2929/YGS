<%@page import="java.util.ArrayList"%>
<%@page import="geomex.svc.handler.Code"%>
<%@page import="geomex.utils.PageNavi"%>
<%@page import="geomex.utils.PageNaviBean"%>
<%@page import="geomex.pkg.srch.ConditionSearch"%>
<%@page import="geomex.pkg.srch.ConditionSearchBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String lry_id = request.getParameter("lry_id");
	String col = request.getParameter("col");
	String val = request.getParameter("val");
	
	String tbl_id = Code.getLyrNM(lry_id); //테이블명을 가지고온다.	
	ConditionSearchBean CS = new ConditionSearchBean();
	ArrayList<ConditionSearch>  TL = CS.getTBLcolList(tbl_id);
%>

<style>
	.border1{
		border : 1px solid #e3e3e3;
	}
	.border2{
		border-right : 1px dotted #dedede;
		border-bottom : 1px dotted #dedede;
	}
	.border3{
		border-right : 1px solid #dedede;
		border-bottom : 1px solid #dedede;
	}
	.border4{
		border-bottom : 1px solid #e3e3e3;
	}
	.border5{
		border-right : 1px dotted #ffffff;
	}
	.table_border1{
		border-right : 1px solid #dedede;
		border-bottom : 1px solid #dedede;
		border-left : 1px solid #dedede;
	}
</style>

<div id="lyr_wapp" style="border:0px solid #000000; height:100%;">
	<div id="lyr_sunject" class="border3" style="height:25px;">
		<p style="padding:5px;"><img src="img/pp_img_1.gif" align="absmiddle"/> <b>레이어 상세정보</b></p>
	</div>
	<!-- div id="lyr_text" style="height:25px;"> </div-->
	<div id="lyr_content_box" style="border:0px solid #000000; overflow: auto;">
		<table border="0" cellpadding="0" cellspacing="0" height="auto" width="100%" class="border1">
			<tr bgcolor="#32b69f;" align="center">
				<td width="30%" height='30' class="border2"><b style='color: #ffffff;'>컬럼명</b></td>
				<td width="60%" class="border4"><b style='color: #ffffff;'>내용</b></td>
			</tr>
			<%
				if(TL.size() != 0){
					for(int i=0; i<TL.size(); i++){
						String cont = CS.getLyrDetailList(val, tbl_id, col, TL.get(i).getCode());
			%>
			<tr align="center">
				<td height='25'class="border2"><%=TL.get(i).getValue() %></td>
				<td align='left'  class="border4">&nbsp;<%=cont %></td>
			</tr>
			<%
					}	
				}
			%>
		</table>
	</div>
</div>
