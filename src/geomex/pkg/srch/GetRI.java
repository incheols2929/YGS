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
 * 시군구,읍면동에 해당하는 리 목록을 얻는다.
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class GetRI implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        String umd = kvp.get("UMD");

        ConditionSearchBean CS = new ConditionSearchBean();
        ArrayList<ConditionSearch> cslist = CS.getRi(umd); //선택한 리 정보를 가져옴

        StringBuilder sb = new StringBuilder();
        sb.append("<ri-list>");
        for (int i = 0; i < cslist.size(); i++) {
            sb.append("<리>");
            sb.append("<리코드>").append(cslist.get(i).getCode()).append("</리코드>");
            sb.append("<리명>").append(cslist.get(i).getValue()).append("</리명>");
            sb.append("</리>");
        }
        sb.append("</ri-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString()); //xml형태로 클라이언트로 전송

    }

}
