package geomex.pkg.srch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import geomex.svc.handler.Code;
import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;
import geomex.pkg.sys.lris.LRGST;
import geomex.pkg.srch.ConditionSearch;
import geomex.pkg.srch.ConditionSearchBean;

/**
 * 레어어 항목및 결과값을 얻는다.
 */
public class GetLyrDetailList implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        String key = kvp.get("KEY");
        //String lry_id = kvp.get("LRY_ID");
        String lry_id = new String(kvp.get("LRY_ID").getBytes("8859_1"), "UTF-8");

        ConditionSearchBean CS = new ConditionSearchBean();
        //String tbl_id = Code.getLyrTBL(lry_id);
        String tbl_id = Code.getLyrNM(lry_id);
        ArrayList<ConditionSearch> TL = CS.getTBLcolList(tbl_id);
        String colkey = Code.getTblkey(tbl_id); //키필드 칼럼명을 가져온다. 

        //연속지적의 테이블명과 토지대장의 기본정보를 가지고온다. 
        String base = "";
        String YN = "";

        if ("lp_pa_cbnd".equals(tbl_id)) {
            try {
                base = LRGST.getBaseXML(key);
                YN = "Y";
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        } else {
            YN = "N";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<inte-list>");
        //연속지적 비교하여 일필지 기본정보를 가지고오는 
        sb.append("<존재여부>").append(YN).append("</존재여부>");
        sb.append(base);
        for (int i = 0; i < TL.size(); i++) {
            String cont = CS.getLyrDetailList(key, tbl_id, colkey, TL.get(i).getCode());
            sb.append("<상세정보>");
            sb.append("<항목>").append(TL.get(i).getValue()).append("</항목>");
            sb.append("<값>").append(cont).append("</값>");
            sb.append("</상세정보>");
        }
        sb.append("</inte-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }

}
