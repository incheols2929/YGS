package geomex.svc.webctrl;

import geomex.utils.IOUtil;
import geomex.utils.StrUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <PRE>
 * 파일명    : WebCtrl.java
 * 파일설명 : 클라이언트 요청을 요청별로 분류하여 각 핸들러에게 작업을 전달한다.
 * 수정이력 : 
 *       2013. 6. 19.  김경호  : 최초작성
 * 
 * </PRE>
 * 
 */
public class WebUtil {
    public static final String XML_HEADER = "<?xml version='1.0' encoding='UTF-8'?>";

    /**
     * 요청 파라메타를 분류한다.
     * 
     * @param request
     * @return Map<String, String>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> toMap(HttpServletRequest request) {

        Map<String, String> result = new HashMap<String, String>();
        Map<String, String[]> map = request.getParameterMap();
        result = new HashMap<String, String>();
        for (String key : map.keySet()) {
            String[] tmp = map.get(key);
            for (int i = 0; i < tmp.length; i++) {
                tmp[i] = tmp[i].trim();
            }
            result.put(key.toUpperCase(), arrayToString(tmp, ','));
        }
        return result;
    }

    /**
     * 요청인자를 문자열로 dump한다.
     * 
     * @param request
     * @return 요청인자 문자열
     */
    public static String dumpParam(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder(4096);
        sb.append("URL    : " + request.getRequestURL()).append("\r\n");
        sb.append("Ref    : " + request.getHeader("referer")).append("\r\n");
        sb.append("Remote : " + getRemote(request)).append("\r\n");
        Enumeration<?> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            sb.append("param : ").append(name);
            sb.append(" = ").append(request.getParameter(name)).append("\r\n");
        }
        return sb.toString();
    }
    
    /**
     * 결과 Json정보를 클라이언트로 전송한다.
     * 
     * @param res
     * @param xml
     */
    public static void sendJson(HttpServletResponse res, String json) {
        if (res == null) return;
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/javascript");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(res.getWriter());
            pw.write(json);
            pw.flush();
        } catch (Exception ea) {
            // getServletContext().log(IOUtil.getStackTrace(ea));
        } finally {
            IOUtil.close(pw);
        }
    }

    /**
     * 결과 xml정보를 클라이언트로 전송한다.
     * 
     * @param res
     * @param xml
     */
    public static void sendNoneHeaderXML(HttpServletResponse res, String xml) { //웹서버에서 req메소드를 호출한다.이때 클라이언트에서 보낸 요청(여기에서 데이터를 뽑아낼수 있음)객체를 전달///클라이언트가 요청한 정보를 담음

        if (res == null) return;
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/xml");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(res.getWriter()); //클라이언트로 전송
            pw.write(xml);
            pw.flush();
        } catch (Exception ea) {
            // getServletContext().log(IOUtil.getStackTrace(ea));
        } finally {
            IOUtil.close(pw);
        }
    }

    /**
     * 문자열 Array를 합친다.
     * 
     * @param s
     * @param delimiter
     * @return String
     */
    public static String arrayToString(String[] s, char delimiter) {
        StringBuilder res = new StringBuilder(s.length * 20);

        for (int i = 0; i < s.length; i++) {
            res.append(s[i]);

            if (i < (s.length - 1)) {
                res.append(delimiter);
            }
        }

        return res.toString();
    }

    /**
     * 클라이언트 IP정보를 얻는다ㅣ
     * 
     * @param request
     * @return IP,Port
     */
    public static String getRemote(HttpServletRequest request) {
        return request.getRemoteAddr() + ":" + request.getRemotePort();
    }

    /**
     * Http Error메세지를 보낸다.
     * 
     * @param res
     * @param msg
     */
    public static void sendError(HttpServletResponse res, String msg) {
        if (res == null) return;
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/xml");
        PrintWriter pw = null;
        StringBuilder sb = new StringBuilder(50);
        sb.append(Const.XML_HEADER);
        sb.append("<error>");
        sb.append(msg);
        sb.append("</error>");

        try {
            pw = new PrintWriter(res.getWriter());
            pw.write(sb.toString());
            pw.flush();
        } catch (Exception ea) {
            // getServletContext().log(IOUtil.getStackTrace(ea));
        } finally {
            IOUtil.close(pw);
        }
    }

    /**
     * 클라이언트로 암호호되지 않은 데이터(json)를 전송한다.
     * 
     * @param res HttpServletResponse
     * @param data json data
     */
    public static void send(HttpServletResponse res, String data) {
        if (StrUtil.isEmpty(data))
            return;
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(res.getWriter());
            pw.write(data);
            pw.flush();
        } catch (IOException ea) {
            ea.printStackTrace();
        } finally {
            IOUtil.close(pw);
        }
    }

    /**
     * HttpServletRequest에서 name으로 주어진 파라메터의 값을 반환한다. <br>
     * 만약 req가 null이거나 예외 발생시 null을 반환한다.
     * 
     * @param req HttpServletRequest
     * @param name 파라메터 이름
     * @return 파라메터 값
     */
    public static String getParam(HttpServletRequest req, String name) {
        if (req == null) {
            return "";
        }
        String value = null;
        try {
            value = req.getParameter(name);
        } catch (Exception e) {
            value = "";
        }
        return value;
    }

    /**
     * 클라이언트 요청별 처리기를 ctrl.properties에서 읽어 Properties에 저장하여 반환한다.
     * 
     * @param f 설정 파일
     * @return 요청처리기 Properties
     * @throws IOException
     */
    public static Properties loadProperties(File _f) throws IOException {
        FileReader _reader = null;
        Properties _prop = new Properties();
        try {
            _reader = new FileReader(_f);
            _prop.load(_reader);
        } catch (Exception e) {
            throw new IOException(IOUtil.getStackTrace(e));
        } finally {
            IOUtil.close(_reader);
        }
        return _prop;
    }

    /**
     * HttpPost연결을 하고 BufferedReader를 얻는다.
     * 
     * @param u
     * @param param
     * @param enc
     * @return BufferedReader
     * @throws Exception
     */
    public static BufferedReader getPOSTReader(String url, String param, String enc)
        throws Exception {
        PrintWriter out = null;
        BufferedReader reader = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("POST");
            conn.setAllowUserInteraction(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //
            out = new PrintWriter(conn.getOutputStream());
            out.println(param);
            out.flush();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), enc));
        } catch (Exception e) {
            throw new Exception(IOUtil.getStackTrace(e));
        } finally {
            IOUtil.close(out);
        }
        return reader;
    }

    /**
     * HTTP Get방식으로 호출하고 BufferedReader를 얻는다.
     * 
     * @param url
     * @param enc
     * @return BufferedReader
     * @throws Exception
     */
    public static BufferedReader getGETReader(String url, String enc)
        throws Exception {

        HttpURLConnection conn = null;
        BufferedReader reader = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setAllowUserInteraction(false);
            conn.setDoOutput(false);
            conn.setDoInput(true);
            //
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), enc));
        } catch (Exception e) {
            throw new Exception(IOUtil.getStackTrace(e));
        }
        // conn > disconnect하면 안됨.
        return reader;
    }
}
