package geomex.pkg.srch;

import geomex.svc.handler.Code;
import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;
import geomex.pkg.srch.ConditionSearch;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * 통합검색의 용도지역 대분류
 */
public class GetUzneCodeDae implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO 처리 모듈 구현하기
        // 아래는 테스트

        //ConditionSearchBean CS = new ConditionSearchBean();

        ArrayList<ConditionSearch> cslist = Code.getUzneCodeDae();

        StringBuilder sb = new StringBuilder();

        sb.append("<uzne-list>");

        for (int i = 0; i < cslist.size(); i++) {
            sb.append("<코드>");
            sb.append("<코드값>").append(cslist.get(i).getCode()).append("</코드값>");
            sb.append("<코드명>").append(cslist.get(i).getValue()).append("</코드명>");
            sb.append("</코드>");
        }
        sb.append("</uzne-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());
        //WebUtil.sendError(res, Const.ERR_INVALID_REQUEST);
    }
}
