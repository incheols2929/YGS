/**
 * 
 */
package geomex.svc.webctrl;

import geomex.utils.FileLogger;
import geomex.utils.IOUtil;
import geomex.utils.LogManager;
import geomex.utils.Logger;
import geomex.utils.XMLUtil;
import geomex.utils.db.DBPoolManager;
import geomex.utils.db.DBPoolProp;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

/**
 * <PRE>
 * 파일명   : WebCtrl.java
 * 파일설명 : 환경설정파일 로드 및 초기 객체 구동
 *           클라이언트 요청을 요청별로 분류하여 각 핸들러에게 작업을 전달한다.
 * 수정이력 : 
 *       2013. 6. 19.  김경호  : 최초작성
 * 
 * </PRE>
 * 
 */
public class WebCtrl extends HttpServlet {
    private static final long serialVersionUID = -6861902555580841094L;
    // mapstyle-template 파일상대경로   
    public static final String MAP_STYLE = "/WEB-INF/mapstyle-template.xml";
    // svc.properties 파일상대경로
    private static final String SVC_FILE = "/WEB-INF/svc.properties";
    // JDBC Pool 설정화일
    private static final String DBPOOL_FILE = "/WEB-INF/gmx-dbpool-service.xml";
    // mapstyle-template 파일 절대경로
    private static volatile String realMapStyleFile = "";
    // dbNode명
    private static final String node = "YGS";
    //
    // svc.properties.에 저장된 Handler를 생성하여 저장
    private HashMap<String, Handler> handlers = new HashMap<String, Handler>();

    /**
     * 생성자
     */
    public WebCtrl() {
        super();
    }

    @Override
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ServletContext context = conf.getServletContext();
        // 시스템 인코딩 정보 출력
        context.log("System encoding : " + System.getProperty("file.encoding"));
        //
        FileReader reader = null;
        try {
            //
            initDBPool(context.getRealPath(WebCtrl.DBPOOL_FILE));
            // MapStyle Template 실제경로 생성
            Const.setRealMapStyleFile(context.getRealPath(Const.MAP_STYLE));
            Const.setFontFile(context.getRealPath(Const.fontFile));
            Const.setTempPath(context.getRealPath("") + getServletConfig().getInitParameter("tempPath"));
            Const.setLrgstURL(getServletConfig().getInitParameter("LrgstURL"));
            // 요청처리 Handler 정보를 읽어온다.
            reader = new FileReader(context.getRealPath(SVC_FILE));
            Properties prop = new Properties();
            prop.load(reader);
            // 요청처리 객체를 생성하여 HashMap에 저장한다.
            for (Enumeration<?> e = prop.propertyNames(); e.hasMoreElements();) {
                String ctrl = (String) e.nextElement();
                String hclass = prop.getProperty(ctrl);
                Handler handler = (Handler) Class.forName(hclass).newInstance();
                handlers.put(ctrl, handler);
                context.log("Load Handlers => " + ctrl + " : " + hclass);
            }
            //test
        } catch (Exception e) {
            throw new ServletException(IOUtil.getStackTrace(e));
        } finally {
            IOUtil.close(reader);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        control(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        control(request, response);
    }

    /**
     * 클라이언트 요청을 해석하고 암호화요청인 경우 복호화한다.
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void control(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
        // 클라이언트 요청 KVP정보를 얻는다. <key,value>
        Map<String, String> req = WebUtil.toMap(request);
        // 요청 서비스명을 얻는다.
        String service = req.get("SVC");
        if (service == null) {
            WebUtil.sendError(response, Const.ERR_INVALID_REQUEST);
            getServletContext().log(WebUtil.dumpParam(request));
            throw new ServletException("Service name is null.");
        }

        // 요청 서비스명에 해당하는 처리클래스를 얻는다.
        // 처리클래스가 없으면 에러 전송
        Handler executor = handlers.get(service);

        if (executor == null) {
            WebUtil.sendError(response, Const.ERR_INVALID_REQUEST);
            getServletContext().log(WebUtil.dumpParam(request));
            throw new ServletException("Can not find service handler. > "
                + service);
        } else {
            // 요청을 처리한다.
            executor.perform(req, response);
        }
    }

    /**
     * DB접속Node명을 얻는다.
     * 
     * @return Node명
     */
    public static String getNode() {
        return node;
    }

    /**
     * mapstyle-template.xml의 절대경로를 반환한다.
     * 
     * @return mapstyle-template.xml의 절대경로
     */
    public static String getRealMapStyleFile() {
        return realMapStyleFile;
    }

    /**
     * mapstyle-template.xml의 절대경로를 설정한다.
     * 
     * @param realMapStyleFile 절대경로
     */
    private static void setRealMapStyleFile(String realMapStyleFile) {
        WebCtrl.realMapStyleFile = realMapStyleFile;
    }

    /**
     * DBPoolManger설정정보를 읽고 DBPoolManager를 초기화 한다.
     * 
     * @param path gmx-dbpool-service.xml 경로
     * @throws Exception
     */
    private void initDBPool(String path) throws Exception {
        InputStream is = null;
        Element root = null;
        try {
            is = new FileInputStream(path);
            root = XMLUtil.parseDOM(is);
        } catch (Exception e) {
            throw e;
        } finally {
            IOUtil.close(is);
        }
        // Logger를 생성한다.
        Element logRoot = XMLUtil.getElementByName(root, "LoggerList");
        Element loggers[] = XMLUtil.getChildElements(logRoot, "Logger");
        for (Element log : loggers) {
            NamedNodeMap attr = log.getAttributes();
            String name = XMLUtil.getAttrValue(attr, "name");
            LogManager.getManager().addLogger(
                name,
                new FileLogger(XMLUtil.getString(attr, "path"),
                    XMLUtil.getString(attr, "prefix"),
                    Logger.getLevel(XMLUtil.getString(attr, "level")),
                    XMLUtil.getBoolean(attr, "console")));
        }
        // DBPoolManage를 생성한다.
        Element jdbc[] = XMLUtil.getChildElements(root, "JDBCPool");
        DBPoolProp props[] = new DBPoolProp[jdbc.length];
        for (int x = 0; x < jdbc.length; x++) {
            props[x] = new DBPoolProp();
            props[x].setName(XMLUtil.getString(jdbc[x], "NodeName"));
            props[x].setDriver(XMLUtil.getString(jdbc[x], "Driver"));
            props[x].setUrl(XMLUtil.getString(jdbc[x], "Url"));
            props[x].setProperty("user", XMLUtil.getString(jdbc[x], "User"));
            props[x].setProperty("password", XMLUtil.getString(jdbc[x], "Passwd"));
            props[x].setProperty("encoding", XMLUtil.getString(jdbc[x], "Encoding"));
            props[x].setCheckQuery(XMLUtil.getString(jdbc[x], "CheckQuery"));
            props[x].setMinCapacity(XMLUtil.getInt(jdbc[x], "MinCapacity"));
            props[x].setMaxCapacity(XMLUtil.getInt(jdbc[x], "MaxCapacity"));
            props[x].setWaitTimeout(XMLUtil.getInt(jdbc[x], "WaitTimeout"));
            props[x].setCheckTimeout(XMLUtil.getInt(jdbc[x], "CheckTimeout"));
            props[x].setLoggerName(XMLUtil.getString(jdbc[x], "LoggerName"));
            //System.out.println(props[x].toString());
        }
        DBPoolManager.create(props);
    }

    @Override
    public void destroy() {
        try {
            LogManager logger = LogManager.getManager();
            if (logger != null) logger.destroy();
        } catch (Exception e) {}
        try {
            DBPoolManager dbpool = DBPoolManager.getManager();
            if (dbpool != null) dbpool.destroy();
        } catch (Exception e) {}
        super.destroy();
    }

}
