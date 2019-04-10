package geomex.pkg.sys.eais;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;

/**
 * 전유부의정보를 가지고온다.
 */
public class GetDjyexpos implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {

        String key = kvp.get("KEY");
        String ouln_pk = kvp.get("OULN_PK");

        Djyexpos dbl = new Djyexpos();
        // 전유현황
        ArrayList<Djyexpos> list = new ArrayList<Djyexpos>();
        // 소유자현황
        ArrayList<Djyexpos> list2 = new ArrayList<Djyexpos>();

        try {
            list = dbl.getExposList(key, ouln_pk);
            list2 = dbl.getOwnerList(key, ouln_pk);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        try {
            sb.append("<전유부>");
            if (list.size() != 0) {
                sb.append("<전유현황리스트>");
                for (int i = 0; i < list.size(); i++) {
                    sb.append("<전유현황>");
                    sb.append("<구분>").append(list.get(i).BL_TYPE).append("</구분>");
                    sb.append("<층별>").append(list.get(i).FLR_NM).append("</층별>");
                    sb.append("<구조>").append(list.get(i).STRCT).append("</구조>");
                    sb.append("<용도>").append(list.get(i).USABILITY).append("</용도>");
                    sb.append("<면적>").append(list.get(i).AREA).append("</면적>");
                    sb.append("</전유현황>");
                }
                sb.append("</전유현황리스트>");
                sb.append("<소유자현황리스트>");
                for (int j = 0; j < list2.size(); j++) {
                    sb.append("<소유자현황>");
                    sb.append("<변동일자>").append(list2.get(j).OWNSP_CH_YMD).append("</변동일자>");
                    sb.append("<변동원인>").append(list2.get(j).OWNSP_CH_CAU_GBN_NM).append("</변동원인>");
                    sb.append("<지분>").append(list2.get(j).EQUITY).append("</지분>");
                    sb.append("<성명및명칭>").append(list2.get(j).OWNR_NM).append("</성명및명칭>");
                    sb.append("<주소>").append(list2.get(j).OWNR_ADDR).append("</주소>");
                    sb.append("</소유자현황>");
                }
                sb.append("</소유자현황리스트>");
                sb.append("</전유부>");
            }
        } catch (Exception e) {
            WebUtil.sendNoneHeaderXML(res, "NoData");
        }

        WebUtil.sendNoneHeaderXML(res, sb.toString());
    }
}
