package geomex.pkg.srch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import geomex.svc.handler.Code;
import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;
import geomex.pkg.srch.ConditionSearch;
import geomex.pkg.srch.ConditionSearchBean;

/**
 * 레이어 통합검색 연속지적일경우 2011-12-16
 * 
 */
public class GetLyrSrchInteList implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        String sgg = kvp.get("SGG");
        String umd = kvp.get("UMD");
        String ri = kvp.get("RI");
        String owngb = kvp.get("OWNGB");
        String jimok = kvp.get("JIMOK");
        String uznecode1 = kvp.get("UZNECODE1");
        String uznecode2 = kvp.get("UZNECODE2");
        String uznecode3 = kvp.get("UZNECODE3");
        String area1 = kvp.get("AREA1");
        String area2 = kvp.get("AREA2");
        String base_year = kvp.get("BASE_YEAR");
        String jiga1 = kvp.get("JIGA1");
        String jiga2 = kvp.get("JIGA2");
        String snum = kvp.get("SNUM");
        String pagenum = kvp.get("PAGENUM");

        String dcode = "";

        if ("".equals(umd)) {
            dcode = sgg;
        } else {
            dcode = umd;
        }

        if ("".equals(ri)) {
            dcode = umd;
        } else {
            dcode = ri;
        }

        ConditionSearchBean CS = new ConditionSearchBean();
        ArrayList<ConditionSearch> cslist = CS.getLyrSrchCbndList(dcode, owngb, jimok, area1, area2, uznecode2, uznecode3,
            base_year, jiga1, jiga2, snum, pagenum);

        StringBuilder sb = new StringBuilder();

        sb.append("<inte-list>");
        for (int i = 0; i < cslist.size(); i++) {
            String fulladdr = Code.getFullAddr(cslist.get(i).getValue().substring(0, 10));
            sb.append("<통합>");
            sb.append("<관리코드>").append(cslist.get(i).getCode()).append("</관리코드>");
            sb.append("<결과값>").append(fulladdr + " " + cslist.get(i).jibun.substring(0, (cslist.get(i).jibun.length() - 1)))
                .append("</결과값>");
            sb.append("</통합>");
        }
        sb.append("</inte-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }

}
