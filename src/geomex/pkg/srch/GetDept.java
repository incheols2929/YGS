package geomex.pkg.srch;

import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;
import geomex.pkg.srch.ConditionSearch;
import geomex.svc.handler.Code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * 해당 부서코드를 가져온다.
 * 
 * @author 최기연
 */
public class GetDept implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO 처리 모듈 구현하기
        // 아래는 테스트

        StringBuilder sb = new StringBuilder();

        ArrayList<ConditionSearch> CS = new ArrayList<ConditionSearch>();
        CS = Code.getDeptCode();

        sb.append("<data>");
        for (int i = 0; i < CS.size(); i++) {
            sb.append("<dept>");
            sb.append("<code>").append(CS.get(i).dept_cd).append("</code>");
            sb.append("<name>").append(CS.get(i).dept_nm).append("</name>");
            sb.append("<note>").append(CS.get(i).dept_note).append("</note>");
            sb.append("</dept>");
        }
        sb.append("</data>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());
        //WebUtil.sendError(res, Const.ERR_INVALID_REQUEST);
    }
}
