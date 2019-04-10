<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>    
<%@ page import="java.util.*" %>
<%@ page import = "java.io.*" %>
<%@page import="geomex.pkg.usr.Notice"%>
<%@page import="geomex.pkg.usr.NoticeBean"%>
<%@page import="geomex.utils.Utils"%>  

<%
	String key = Utils.getParam(request, "key");

	NoticeBean NB = new NoticeBean();
	ArrayList<Notice> NS = new ArrayList<Notice>();
	NB.getReadcountedit(key);
	NS = NB.getNoticeview(key);
	
	/////// 아이콘 해당파일명을 가져온다. /////////
	/*String path = "/";
	ServletContext context = getServletContext();
	String iconFolder = context.getRealPath(path);
	File dir = new File(iconFolder+"login\\img\\icon\\");
	String[] filename = dir.list();
	for(int i=0; i< filename.length; i++){
		System.out.println(filename[i]);
	}
	*/
%>

<data>
	<%
		for(int i =0; i<NS.size(); i++){
	%>		
	<notice>
		<seq><%= NS.get(i).getReq_seq() %></seq>
		<reguser><%= NS.get(i).getReg_usr_id() %></reguser>
		<subject><%= NS.get(i).getSubj_txt() %></subject>
		<content> <![CDATA[<%=NS.get(i).getConts_txt().replaceAll("\r\n", "<br>") %>]]></content>
		<category><%= NS.get(i).getNotc_typ() %></category>
		<reg_date><%= NS.get(i).getReg_date() %></reg_date>
		<read_count><%= NS.get(i).getRead_cnt() %></read_count>
	</notice>
	<%

		}
	%>

	
</data>