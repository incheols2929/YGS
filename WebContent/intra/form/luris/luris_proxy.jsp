<%@ page language="java" contentType="text/plain" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.LinkedHashMap"%>
<%!
private static String map2param(Map<String, String> map) {
    StringBuilder rVal = new StringBuilder();
	String kvSep = "=";
	String elSep = "&";
	String encodeVal = "";
	int i = 0;
	for ( String key : map.keySet() ) {
		encodeVal = map.get(key);
		try {
			encodeVal = URLEncoder.encode(encodeVal, "UTF-8");
		} catch (Exception e) {}
		if ( i > 0 ) rVal.append(elSep);
		rVal.append(key).append(kvSep).append(encodeVal);
		i++;
	}
	return rVal.toString();
}

private static LinkedHashMap<String, String> getParamMap(HttpServletRequest req) {
	LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
    Map<String, String[]> map = req.getParameterMap();
    for (String key : map.keySet()) {
        String[] tmp = map.get(key);
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = tmp[i].trim();
        }
        result.put(key, arrayToString(tmp, ','));
        System.out.println(key+"=>"+arrayToString(tmp, ','));
    }
    return result;
}

private static String arrayToString(String[] s, char delimiter) {
    StringBuilder res = new StringBuilder();
    for ( int i = 0; i < s.length; i++ ) {
        res.append(s[i]);
        if ( i < (s.length - 1) ) res.append(delimiter);
    }
    return res.toString();
}
%>
<%
request.setCharacterEncoding("UTF-8");

StringBuilder sb = new StringBuilder();    

HttpURLConnection httpConn = null;

LinkedHashMap<String, String> paramMap = getParamMap(request);

/*
{svc : "GetBLLawView", pnu : KEY, ucode: ucode, usrid : SESSION_ID, isclick:"1"}
{svc : "GetBLViewByUcode", pnu : KEY, ucode: ucode, usrid : SESSION_ID}
{svc : "GetBLView", luGrStr:lgs, pnu:KEY, usrid : SESSION_ID}
{svc : "GetBLUseNameSearch", landUseNm : encodeURIComponent(blu_name), pageNo : pageNum, usrid : id}
*/

System.out.println(paramMap);
//String urlStr = "http://106.0.2.134:8080/ctrl?";
String urlStr = "http://10.1.73.11:52000/ctrl?";

try {
	
	URL svrUrl = new URL(urlStr);
	
	httpConn = (HttpURLConnection) svrUrl.openConnection();
	httpConn.setConnectTimeout(3000);
	httpConn.setRequestMethod("POST");
	httpConn.setDoOutput(true);
	httpConn.connect();

	OutputStream os = (OutputStream)httpConn.getOutputStream();
	os.write(map2param(paramMap).getBytes());
	os.flush();
	os.close();
	
	BufferedReader br = new BufferedReader( new InputStreamReader( httpConn.getInputStream(), "UTF-8" ) );
	String line;
	while ( (line = br.readLine()) != null ) {
		sb.append(line);
	}

} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (httpConn != null) httpConn.disconnect();
}

out.print(sb);
%>