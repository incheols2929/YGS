package geomex.pkg.srch;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;
import geomex.pkg.srch.ConditionSearch;
import geomex.pkg.srch.ConditionSearchBean;

/**
 * 도로명 목록을 얻는다.
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class GetRoad implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        String sgg = kvp.get("SGG");
        String cons = URLDecoder.decode(kvp.get("CONS"), "utf-8"); //한글처리

        //System.out.println(sgg + ", " + cons);
        StringBuilder sb = new StringBuilder();

        ConditionSearchBean CS = new ConditionSearchBean();
        ArrayList<ConditionSearch> cslist = CS.getNewDoroname(sgg, cons); //선택한 DB정보를 불러온다

        sb.append("<road-list>");
        for (int i = 0; i < cslist.size(); i++) {
            sb.append("<도로>");
            sb.append("<도로코드>").append(cslist.get(i).code).append("</도로코드>");
            sb.append("<도로명>").append(cslist.get(i).value).append("</도로명>");
            sb.append("</도로>");
        }
        sb.append("</road-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }

}
