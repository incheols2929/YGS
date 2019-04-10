<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../ssesion/ssesion.jsp" %> <!-- 디렉티브 인크루드 -->

<%
  //request.setCharacterEncoding("UTF-8");

  String cfgx = request.getParameter("cfgxml");
  //개행문자를 반드시 없애야 한다. java에서 처리했음
  // cfgx = cfgx.replace("\r\n","");
  // cfgx = cfgx.replace("\r","");
  // cfgx = cfgx.replace("\n","");  
  
  request.setCharacterEncoding("UTF-8");
  
  String addon = request.getParameter("addon");
  String minx = request.getParameter("minx");
  String miny = request.getParameter("miny");
  String maxx = request.getParameter("maxx");
  String maxy = request.getParameter("maxy");
  
  String titleName = new String(request.getParameter("titleName").getBytes("8859_1"), "UTF-8");
  String mapName = new String(request.getParameter("mapName").getBytes("8859_1"), "UTF-8");
  String userName = session_name;
%>

<html style="width: 100%; height: 100%;">
<head>
<title>:: 인쇄 ::</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<style type="text/css">
.table-left {

  border-top: 1px solid #7c7c7c;
  border-right: 0px solid #7c7c7c;
  border-bottom: 1px solid #7c7c7c;
  border-left: 1px solid #7c7c7c;
}
html, body, table {
	width: 100%;
	height: 100%;
	margin: 0;
}
</style>

<script language="JavaScript">

  function setPrint(){    
    
	var now = new Date();
	var year = now.getYear();
	var month = now.getMonth()+1;
	var date = now.getDate();
	
	if((month+"").length < 2){
			month = "0"+month;
	}

	if((date+"").length < 2){
		date = "0"+date;
	}
	var today = year +"-"+month+"-"+date;
	    
    var str  = ""; //"http://127.0.0.1:8080/gmxws/";
    var geomex=  document.getElementById("geomex");
    
    
	//geomex.addPrintForm("세로기본", str+"template_a4_port.rxml");    
    //geomex.addPrintForm("가로기본", str+"template_a4_land.rxml");
    geomex.addPrintForm("세로", str+"template_a4_ext_port.rxml");
    geomex.addPrintForm("가로", str+"template_a4_ext_land.rxml");
    //geomex.addPrintForm("세로기본", str+"template_a3_port.rxml");    
    //geomex.addPrintForm("가로기본", str+"template_a3_land.rxml");    
    geomex.addPrintForm("세로", str+"template_a3_ext_port.rxml");
    geomex.addPrintForm("가로", str+"template_a3_ext_land.rxml");
    //geomex.addPrintForm("세로기본", str+"template_a2_port.rxml");    
    //geomex.addPrintForm("가로기본", str+"template_a2_land.rxml");    
    geomex.addPrintForm("세로", str+"template_a2_ext_port.rxml");
    geomex.addPrintForm("가로", str+"template_a2_ext_land.rxml");
    
    geomex.addPrintForm("세로", str+"template_a1_ext_port.rxml");
    geomex.addPrintForm("가로", str+"template_a1_ext_land.rxml");
    
    geomex.addPrintForm("세로", str+"template_a0_ext_port.rxml");
    geomex.addPrintForm("가로", str+"template_a0_ext_land.rxml");
    
        
    geomex.addTextValues("title", "<%=titleName%>"); // 제목
    geomex.addTextValues("mapName", "<%=mapName%>"); //지도이름
    geomex.addTextValues("writer", "<%=userName%>"); // 작성자
    geomex.addTextValues("date", today);
    geomex.addTextValues("scaleValue", "0");
    geomex.addTextValues("note", "본 도면은 참조용으로만 사용하실 수 있습니다.");
    geomex.addTextValues("watermark_url","/geomex/watermark.png"); //워터마크 이미지 url
    geomex.addTextValues("watermark_alpha","0.5"); //투명도(0.0~1.0)
    geomex.showAddonPrint("<%=cfgx%>","<%=addon%>","<%=minx%>","<%=miny%>","<%=maxx%>","<%=maxy%>");
  
  }
</script>
</head>

<body style="width: 100%; height: 100%;" bgcolor="#efefef" text="#000000" leftmargin="0" topmargin="0" scrolling ='auto'  onLoad="javascript:setPrint();">
<table style="width:100%; height:100%; border:0; cellpadding:0; cellspacing:0; bgcolor: #FFFFFF;">
  <tr style="width: 100%; height: 100%;">
    <td valign="top" style="width: 100%; height: 100%;">
      <!-- GEOMEX -->
      
      <script type="text/javascript" src="../../geomex/deployJava.js"></script>
      <script>
      var attributes = {id:'geomex',
              codebase:'/geomex',
              archive:'geomex-apps-20181016.jar',
              code:'geovlet.ria.GMXPRT',
              width:'100%',
              height:'100%',
              scriptable:'true',
              mayscript:'true',
              //java_version:'1.6.0_10',
              separate_jvm:'true',
              image:'./geomex/logo.gif',
              boxborder:'false',
              centerimage:'true'} ;

          var parameters = {owner:'<%=id%>',
              idxLayer:'시군구',
              debug:'true',              
              path:'/GEOMEX:YGS:YGS'} ;
          var version = '1.7.0_45';
          deployJava.runApplet(attributes, parameters, version);
      </script>
      <!-- GEOMEX -->
      
      <!-- GEOMEX -->
    </td>
  </tr>
</table>
</body>

</html>

