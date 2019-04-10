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
 * 도로명주소 기본정보
 * 
 */
public class GetRoadAddrInfo implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        String pnu = kvp.get("PNU");

        StringBuilder sb = new StringBuilder();

        ConditionSearchBean CS = new ConditionSearchBean();
        ArrayList<ConditionSearch> cslist = CS.getBaseAddrInfo(pnu);

        sb.append("<road-list>");

        for (int i = 0; i < cslist.size(); i++) {
            sb.append("<도로정보>");
            sb.append("<도로명>").append(cslist.get(i).getRn()).append("</도로명>");
            sb.append("<영문명>").append(cslist.get(i).getEng_rn()).append("</영문명>");
            sb.append("<기점>").append(cslist.get(i).getRbp_cn()).append("</기점>");
            sb.append("<종점>").append(cslist.get(i).getRep_cn()).append("</종점>");
            sb.append("<도로폭>").append(cslist.get(i).getRoad_bt()).append("</도로폭>");
            sb.append("<도록길이>").append(cslist.get(i).getRoad_lt()).append("</도록길이>");
            sb.append("<부여사유>").append(cslist.get(i).getAlwnc_resn()).append("</부여사유>");
            sb.append("</도로정보>");

        }
        sb.append("</road-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }

}
