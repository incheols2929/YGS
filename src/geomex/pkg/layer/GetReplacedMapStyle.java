package geomex.pkg.layer;

import geomex.svc.webctrl.Const;
import geomex.svc.webctrl.WebUtil;
import geomex.utils.db.DBConnection;
import geomex.utils.db.DBPoolManager;
import geovlet.view.cfg.MapCfg;
import geovlet.utils.IOUtil;
import geovlet.view.LayerInfo;
import geovlet.view.style.Style;
import geovlet.view.style.ThemeStyle;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * 전달된 사용자id, 기존스타일명, 그룹및레이어정보를 가지고 기존 MapStyle을 갱신한다.
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class GetReplacedMapStyle extends GetCreatedMapStyle {

    public GetReplacedMapStyle() {
        super();
    }

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        String user = URLDecoder.decode(kvp.get("USER"), "UTF-8");
        String title = URLDecoder.decode(kvp.get("TITLE"), "UTF-8");
        String xml = URLDecoder.decode(kvp.get("XML"), "UTF-8");

        //System.out.println(xml);
        DBPoolManager manager = DBPoolManager.getManager();
        DBConnection dbConn = null;
        Connection conn = null;
        String result = null;
        try {
            dbConn = manager.getConnection(Const.CONTEXT_NAME);
            conn = dbConn.connection();
            conn.setAutoCommit(true);
            //
            //1.DB에 저장된 XML정보를 읽어온다.
            //2.현재 화면에 보이는 스타일이다
            MapCfg oldCfg = helper.getUserStyle(conn, user, title);
            //1.사용자 화면을 가지고 DB table을 참조하여 스타일을 만든다. 
            //2.바꾸고자 하는 테이블이다.
            MapCfg newCfg = make(conn, xml);
            //
            //newCfg에 oldCfg를 반영한다.
            replaceMapCfg(oldCfg, newCfg);
            result = newCfg.toXML();

        } catch (Exception e) {
            throw new ServletException(IOUtil.getStackTrace(e));
        } finally {
            manager.freeConnection(dbConn, Const.CONTEXT_NAME);
        }
        //
        if (result == null) {
            WebUtil.sendError(res, "GetReplacedMapStyle Fail..");
        }
        WebUtil.sendNoneHeaderXML(res, result);
    }

    /**
     * 기존과 같은이름의 레이어일 경우 기존 스타일을 적용한다.
     * 
     * @param oldCfg
     * @param newCfg
     */
    private void replaceMapCfg(MapCfg oldCfg, MapCfg newCfg) {
        // 스타일을 변경한다.
        HashMap<String, Style> oldStyles = oldCfg.getStyles(); //현재화면에 보이는 레이어
        HashMap<String, Style> newStyles = newCfg.getStyles(); //새로운 레이어

        //for (String newNm : newStyles.keySet()) {
        //    Style os = oldStyles.get(newNm);
        //    if (os != null) {
        //        newStyles.put(newNm, os);
        //    }
        //}
        // 레이어 zIndex를 변경한다.
        ArrayList<LayerInfo> oldLyr = oldCfg.getLayerInfos();
        ArrayList<LayerInfo> newLyr = newCfg.getLayerInfos();

        for (int x = 0; x < newLyr.size(); x++) {
            LayerInfo newInfo = newLyr.get(x);
            //System.out.println(">> "+newInfo.toLayerXML());
            for (int y = 0; y < oldLyr.size(); y++) {
                LayerInfo oldInfo = oldLyr.get(y);
                //System.out.println(">> "+oldInfo.toLayerXML());
                if (newInfo.getLayerName().equalsIgnoreCase(oldInfo.getLayerName())) {
                    newInfo.setZIndex(oldInfo.getZIndex());
                    newInfo.setLayerVisible(oldInfo.isLayerVisible());

                    newInfo.setLabelVisible(oldInfo.isLabelVisible());
                    newInfo.setLabelItems(oldInfo.getLabelItems());

                    newInfo.setSymbolVisible(oldInfo.isSymbolVisible());
                    newInfo.setSymbolMinScale(oldInfo.getSymbolMinScale());
                    newInfo.setSymbolMaxScale(oldInfo.getSymbolMaxScale());
                    //
                    newInfo.setThemeColumn(oldInfo.getThemeColumn());
                    newInfo.setThemeStyle(oldInfo.isThemeStyle());
                    newInfo.setRangeTheme(oldInfo.isRangeTheme());

                    Style dStyle = oldInfo.getDefaultStyle();
                    if (dStyle != null) {
                        newStyles.put(dStyle.getName(), dStyle);
                        newInfo.setDefaultStyle(dStyle);
                    }
                    //
                    if (oldInfo.isThemeStyle()) {
                        newInfo.removeThemeStyle();
                        ThemeStyle[] thm = oldInfo.toThemeArray();
                        if (thm != null) {
                            for (ThemeStyle s : thm) {
                                String n = s.getThemeName();
                                String v = s.getThemeValue();
                                Style ns = newStyles.get(s.getStyle().getName());
                                if (ns == null) {
                                    String tmp = s.getStyle().getName();
                                    Style os = oldStyles.get(tmp);
                                    if (os != null) {
                                        newStyles.put(tmp, os);
                                        ns = os;
                                    }
                                }
                                boolean isV = s.isVisible();
                                int seq = s.getSequence();
                                ThemeStyle nts = new ThemeStyle(n, v, ns, isV, seq);

                                newInfo.addThemeStyle(v, nts);
                            }
                        }
                    }
                    break;
                }
            }
        }
    }
}
