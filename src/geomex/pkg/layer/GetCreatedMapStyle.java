package geomex.pkg.layer;

import geomex.svc.webctrl.Const;
import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;
import geomex.utils.db.DBConnection;
import geomex.utils.db.DBPoolManager;
import geovlet.view.cfg.MapCfg;
import geovlet.utils.IOUtil;
import geovlet.utils.XMLUtil;
import geovlet.view.LayerGroup;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

/**
 * 전달된 그룹 및 레이어 정보를 가지고 MapStyle을 신규행성한다.
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class GetCreatedMapStyle implements Handler {
    protected StyleDBHelper helper = null;

    public GetCreatedMapStyle() {
        helper = new StyleDBHelper();
    }

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        String xml = URLDecoder.decode(kvp.get("XML"), "UTF-8");
        // System.out.println(xml);
        DBPoolManager manager = DBPoolManager.getManager();
        DBConnection dbConn = null;
        Connection conn = null;
        String result = null;
        try {
            dbConn = manager.getConnection(Const.CONTEXT_NAME);
            conn = dbConn.connection();
            conn.setAutoCommit(true);
            //
            result = make(conn, xml).toXML();
            //
        } catch (Exception e) {
            throw new ServletException(IOUtil.getStackTrace(e));
        } finally {
            manager.freeConnection(dbConn, Const.CONTEXT_NAME);
        }
        //
        if (result == null) {
            WebUtil.sendError(res, "GetCreatedMapStyle Fail..");
        }
        WebUtil.sendNoneHeaderXML(res, result);
    }

    public MapCfg make(Connection con, String xml) throws Exception {
        ArrayList<UserLayer> userLyrs = parse(xml);
        MapCfg cfg = getMapCfgTemplate();

        // 레이어 그룹을 먼저 등록한다.
        addLayerGroup(cfg, userLyrs);
        // 각 레이어 별로 레이어 정보와 스타일정보를 생성한다.
        int grpSize = userLyrs.size();

        for (int x = 0; x < grpSize; x++) {
            UserLayer grp = userLyrs.get(x);
            String lyr[] = grp.getLayers();
            for (int y = 0; y < lyr.length; y++) {
                helper.ensuerLayerInfo(con, grp.grpName, cfg, lyr[y]);
            }
        }
        return cfg;
    }

    /**
     * 레이어 그룹 등록 (2015-07-13 임시레이어 자동등록으로 인해 메소드 수정)
     * 
     * @param cfg
     * @param lyrs
     */
    protected void addLayerGroup(MapCfg cfg, ArrayList<UserLayer> lyrs) {
        for (int x = 0; x < lyrs.size(); x++) {
        	if(!"임시레이어".equals(lyrs.get(x).grpName)){
        		cfg.addLayerGroup(new LayerGroup(lyrs.get(x).grpName, x));
        	}
        }
    }

    protected MapCfg getMapCfgTemplate() throws Exception {
        InputStream xml = null;
        MapCfg cfg = new MapCfg();
        try {
            xml = new FileInputStream(Const.getRealMapStyleFile());
            cfg.loadCfg(xml);
            // System.out.println(cfg.toXML());
        } catch (Exception e) {
            throw new Exception(IOUtil.getStackTrace(e));
        } finally {
            IOUtil.close(xml);
        }
        return cfg;
    }

    protected ArrayList<UserLayer> parse(String xml) throws Exception {
        ArrayList<UserLayer> userLyrs = new ArrayList<UserLayer>();
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            Element root = XMLUtil.parseDOM(is);
            Element group[] = XMLUtil.getChildElements(root, "Group");
            for (int x = 0; x < group.length; x++) {
                NamedNodeMap attr = group[x].getAttributes();
                String grpName = XMLUtil.getString(attr, "name");
                // System.out.println("grp =" + grpName);
                //
                UserLayer ulyr = new UserLayer(grpName);
                Element lyr[] = XMLUtil.getChildElements(group[x], "Layer");
                for (int y = 0; y < lyr.length; y++) {
                    String lyrName = XMLUtil.getTagValue(lyr[y]);
                    ulyr.addLayer(lyrName);
                }
                userLyrs.add(ulyr);
            }
        } catch (Exception e) {
            throw new Exception(IOUtil.getStackTrace(e));
        } finally {
            IOUtil.close(is);
        }
        return userLyrs;
    }

    class UserLayer {
        String grpName;
        ArrayList<String> lyrName;

        public UserLayer(String grpName) {
            this.grpName = grpName;
            this.lyrName = new ArrayList<String>();
        }

        public void addLayer(String lyr) {
            lyrName.add(lyr);
        }

        public String[] getLayers() {
            int size = lyrName.size();
            String v[] = new String[size];
            for (int x = 0; x < size; x++) {
                v[size - 1 - x] = lyrName.get(x);
            }
            return v;
        }
    }

}
