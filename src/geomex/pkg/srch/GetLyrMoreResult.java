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
 * 레이어상세정보 결과목록을 가져온다.
 */
public class GetLyrMoreResult implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        String lyr_nm = URLDecoder.decode(kvp.get("LYR_NM"), "utf-8"); //한글처리
        String lyr_id = kvp.get("LYR_ID");
        String wtk = kvp.get("WTK");
        String srch_cond = kvp.get("SRCH_COND");

        String tbl_id = Code.getLyrTBL(lyr_id); //테이블명을 가져온다.
        String colkey = Code.getTblkey(tbl_id); //키필드 칼럼명을 가져온다.
        ConditionSearchBean CS = new ConditionSearchBean();
        ArrayList<ConditionSearch> relist = CS.getSrchLrycontent(tbl_id, colkey, wtk, srch_cond, lyr_nm); //결과목록을 가져온다.*/
        StringBuilder sb = new StringBuilder();

        sb.append("<result-list>");
        for (int i = 0; i < relist.size(); i++) {

            /*
             * if(relist.get(i).getCode().length() >= 19){ result_val =
             * relist.get(i).getCode().substring(0, 13) + "..."; }else{
             * result_val = relist.get(i).getCode(); }
             */

            sb.append("<결과목록>");
            sb.append("<관리코드>").append(relist.get(i).getCode()).append("</관리코드>");
            //sb.append("<결과값>").append(result_val).append("</결과값>");
            sb.append("<결과값>").append(relist.get(i).getCode()).append("</결과값>");
            sb.append("</결과목록>");
        }
        sb.append("</result-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }

}
