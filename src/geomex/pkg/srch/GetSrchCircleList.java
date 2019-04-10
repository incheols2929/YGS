package geomex.pkg.srch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;
import geomex.pkg.srch.ConditionSearch;
import geomex.pkg.srch.ConditionSearchBean;

/**
 * 반경검색 결과 목록을 얻는다.
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class GetSrchCircleList implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        String[] lrys = kvp.get("LRYS").split(",");
        String srch_cond = kvp.get("SRCH_COND");
        String cx = kvp.get("CX");
        String cy = kvp.get("CY");
        String distance = kvp.get("DISTANCE");

        ConditionSearchBean CS = new ConditionSearchBean();
        ArrayList<ConditionSearch> cslist = CS.getSrchCricle(lrys, srch_cond, cx, cy, distance);

        StringBuilder sb = new StringBuilder();
        sb.append("<circle-list>");
        for (int i = 0; i < cslist.size(); i++) {
            sb.append("<반경검색>");
            sb.append("<레이어명>").append(cslist.get(i).getLyr_nm()).append("</레이어명>");
            sb.append("<레이어ID>").append(cslist.get(i).getLyr_id()).append("</레이어ID>");
            sb.append("<결과값>").append(cslist.get(i).getCnt()).append("</결과값>");
            sb.append("<_gid>").append(cslist.get(i).getGid()).append("</_gid>");
            sb.append("</반경검색>");
        }
        sb.append("</circle-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }

}
