package geomex.pkg.srch;

import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;
import geomex.pkg.srch.ConditionSearch;
import geomex.pkg.srch.ConditionSearchBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * 시군구에 해당하는 읍면동 목록을 얻는다.
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class GetUMD2 implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        String sgg = kvp.get("SGG");

        ConditionSearchBean CS = new ConditionSearchBean();
        ArrayList<ConditionSearch> cslist = CS.getUmdAll2(sgg); //선택한 읍면동을 모두 들고옴 getSggAll()과 동일

        StringBuilder sb = new StringBuilder();
        sb.append("<umd-list>");

        for (int i = 0; i < cslist.size(); i++) {
            sb.append("<읍면동>");
            sb.append("<읍면동코드>").append(cslist.get(i).getCode()).append("</읍면동코드>");
            sb.append("<읍면동명>").append(cslist.get(i).getValue()).append("</읍면동명>");
            sb.append("</읍면동>");
        }

        sb.append("</umd-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());
    }

}
