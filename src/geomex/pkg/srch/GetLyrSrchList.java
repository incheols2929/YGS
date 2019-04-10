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
 * 레어어 통합검색 결과 목록을 얻는다.
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class GetLyrSrchList implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        //String lry_id = kvp.get("LRY_ID");
        String lry_id = URLDecoder.decode(kvp.get("LRY_ID"), "utf-8"); //한글처리    	
        String lry_nm = kvp.get("LRY_NM");
        String cond_type = kvp.get("COND_TYPE");
        String included = URLDecoder.decode(kvp.get("INCLUDED"), "utf-8"); //한글처리
        String match = URLDecoder.decode(kvp.get("MATCH"), "utf-8"); //한글처리
        String range_1 = URLDecoder.decode(kvp.get("RANGE_1"), "utf-8"); //한글처리
        String range_2 = URLDecoder.decode(kvp.get("RANGE_2"), "utf-8"); //한글처리
        String snum = kvp.get("SNUM");
        String pagenum = kvp.get("PAGENUM");

        ConditionSearchBean CS = new ConditionSearchBean();
        //String tbl_id = Code.getLyrTBL(lry_id);
        String tbl_id = Code.getLyrNM(lry_id);
        ArrayList<ConditionSearch> cslist = CS.getLyrSrchList(tbl_id, lry_nm, cond_type, included, match, range_1, range_2, snum,
            pagenum);

        StringBuilder sb = new StringBuilder();

        sb.append("<inte-list>");
        for (int i = 0; i < cslist.size(); i++) {
            sb.append("<통합>");
            sb.append("<관리코드>").append(cslist.get(i).getCode()).append("</관리코드>");
            sb.append("<결과값>").append(cslist.get(i).getValue()).append("</결과값>");
            sb.append("</통합>");
        }
        sb.append("</inte-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }

}
