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
 * 버퍼검색 검색결과 목록을 받는다.
 * 
 * 
 */
public class GetSrchBufferList implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        String[] lrys = kvp.get("LRYS").split(",");
        String srch_cond = kvp.get("SRCH_COND");
        String wtk = kvp.get("WTK");

        StringBuilder sb = new StringBuilder();

        ConditionSearchBean CS = new ConditionSearchBean();//여기로 이동을 해서
        ArrayList<ConditionSearch> cslist = CS.getSrchBoxPoly(lrys, srch_cond, wtk); //사각형검색, 다각형검색, 버퍼검색

        sb.append("<buf-list>");
        for (int i = 0; i < cslist.size(); i++) {
            sb.append("<버퍼검색>");
            sb.append("<레이어명>").append(cslist.get(i).getLyr_nm()).append("</레이어명>");
            sb.append("<레이어ID>").append(cslist.get(i).getLyr_id()).append("</레이어ID>");
            sb.append("<결과값>").append(cslist.get(i).getCnt()).append("</결과값>");
            sb.append("<_gid>").append(cslist.get(i).getGid()).append("</_gid>");
            sb.append("</버퍼검색>");
        }
        sb.append("</buf-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString()); //검색을 해서 결과 xml정보를 클라이언트로 전송한다.

    }

}
