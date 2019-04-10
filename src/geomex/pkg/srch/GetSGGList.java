package geomex.pkg.srch;

import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

public class GetSGGList implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO 처리 모듈 구현하기
        // 아래는 테스트
        String code = kvp.get("BJD_CD");

        ConditionSearchBean CS = new ConditionSearchBean();
        ArrayList<ConditionSearch> cslist = CS.getSggCode(code);

        StringBuilder sb = new StringBuilder();

        sb.append("<sgg-list>");

        for (int i = 0; i < cslist.size(); i++) {

            sb.append("<시군구>");
            sb.append("<시군구코드>").append(cslist.get(i).getCode()).append("</시군구코드>");
            sb.append("<시군구명>").append(cslist.get(i).getValue()).append("</시군구명>");
            sb.append("</시군구>");

        }
        sb.append("</sgg-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());
        //WebUtil.sendError(res, Const.ERR_INVALID_REQUEST);
    }

}
