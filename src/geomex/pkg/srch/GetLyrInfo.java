package geomex.pkg.srch;

import java.io.IOException;
import java.net.URLDecoder;
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
 * 레이어 항목별 속성정보를 얻는다.
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class GetLyrInfo implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        String lyr_id = URLDecoder.decode(kvp.get("LYR_ID"), "utf-8"); //한글처리

        ConditionSearchBean CS = new ConditionSearchBean();
        //String tbl_id = Code.getLyrTBL(lyr_id);
        String tbl_id = Code.getLyrNM(lyr_id);
        ArrayList<ConditionSearch> cslist = CS.getTBL_COLS(tbl_id);

        StringBuilder sb = new StringBuilder();
        sb.append("<lyr-list>");
        for (int i = 0; i < cslist.size(); i++) {
            sb.append("<칼럼정보>");
            sb.append("<칼럼명>").append(cslist.get(i).getCode()).append("</칼럼명>");
            sb.append("<한글명>").append(cslist.get(i).getValue()).append("</한글명>");
            sb.append("</칼럼정보>");
        }
        sb.append("</lyr-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }

}
