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
 * 레이어 목록을 얻는다.
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class GetLayers implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        ConditionSearchBean CS = new ConditionSearchBean();
        ArrayList<ConditionSearch> cslist = CS.getLYR_DESC();

        StringBuilder sb = new StringBuilder();
        sb.append("<lyr-list>");
        for (int i = 0; i < cslist.size(); i++) {
            sb.append("<레이어>");
            sb.append("<레이어관리ID>").append(cslist.get(i).getLyr_id()).append("</레이어관리ID>");
            sb.append("<테이블ID>").append(cslist.get(i).getCode()).append("</테이블ID>");
            sb.append("<레이어명>").append(cslist.get(i).getValue()).append("</레이어명>");
            sb.append("<레이어보이기여부>").append(cslist.get(i).lyr_show_yn).append("</레이어보이기여부>");
            sb.append("</레이어>");
        }
        sb.append("</lyr-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }

}
