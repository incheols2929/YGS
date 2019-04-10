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
 * 도로명 또는 교차로 목록을 얻는다.
 * 
 * @author 최기연
 */
public class GetRoadCross implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        String sgg = kvp.get("SGG");
        String cons = URLDecoder.decode(kvp.get("ROAD"), "utf-8"); //한글처리
        String dorocross = kvp.get("DOROCROSS");

        //System.out.println(sgg + ", " + cons + ", " + dorocross);
        StringBuilder sb = new StringBuilder();

        ConditionSearchBean CS = new ConditionSearchBean();
        ArrayList<ConditionSearch> cslist;

        if ("road".equals(dorocross)) {
            cslist = CS.getNewDoroname(sgg, cons);
            sb.append("<road-list>");
            for (int i = 0; i < cslist.size(); i++) {
                sb.append("<도로>");
                sb.append("<도로코드>").append(cslist.get(i).getCode()).append("</도로코드>");
                sb.append("<도로명>").append(cslist.get(i).getValue()).append("</도로명>");
                sb.append("<시군구코드>").append(cslist.get(i).getSig_cd()).append("</시군구코드>");
                sb.append("<기점>").append("기점").append("</기점>");
                sb.append("<종점>").append("종점").append("</종점>");
                sb.append("<폭>").append("폭").append("</폭>");
                sb.append("<길이>").append("길이").append("</길이>");
                sb.append("<부여사유>").append("부여사유").append("</부여사유>");
                sb.append("</도로>");
            }
            sb.append("</road-list>");
        } else {
            cslist = CS.getNewCrossname(sgg, cons);
            sb.append("<road-list>");
            for (int i = 0; i < cslist.size(); i++) {
                sb.append("<교차로>");
                sb.append("<교차로일련번호>").append(cslist.get(i).getCrsrd_sn()).append("</교차로일련번호>");
                sb.append("<교차로명>").append(cslist.get(i).getKor_crsrd()).append("</교차로명>");
                sb.append("<교차로영문명>").append(cslist.get(i).getKor_crsrd()).append("</교차로영문명>");
                sb.append("<시군구코드>").append(cslist.get(i).getSig_cd()).append("</시군구코드>");
                sb.append("</교차로>");
            }
            sb.append("</road-list>");
        }
        WebUtil.sendNoneHeaderXML(res, sb.toString());
    }

}
