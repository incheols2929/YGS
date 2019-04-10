package geomex.pkg.srch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import geomex.svc.handler.Code;
import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;
import geomex.pkg.layer.LayerCategory;
import geomex.pkg.layer.LayerCategoryBean;

/**
 * 레이어관리창의 레이어목록을 가지고온다. 2011-12-21
 */
public class GetLyrCategory implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        String id = kvp.get("ID");
        String grp_id = kvp.get("GRP_ID");

        LayerCategoryBean LB = new LayerCategoryBean();
        ArrayList<LayerCategory> list = LB.getGrpLayer();
        ArrayList<LayerCategory> lrylist = null;
        StringBuilder sb = new StringBuilder();

        String checkL = "";

        sb.append("<lry-list>");
        for (int i = 0; i < list.size(); i++) {
            lrylist = LB.getLayerDesc(list.get(i).GRP_ID);
            sb.append("<레이어관리>");
            sb.append("<레이어그룹 grpid='" + list.get(i).GRP_ID + "'>").append(list.get(i).GRP_NM).append("</레이어그룹>");
            sb.append("<레이어목록>");
            for (int j = 0; j < lrylist.size(); j++) {
                checkL = Code.getAuthCheckL(lrylist.get(j).getLYR_ID(), id, grp_id);
                if (!"N".equals(checkL)) {
                    sb.append("<레이어명 tblid='" + lrylist.get(j).getTBL_ID() + "'>").append(lrylist.get(j).getLYR_NM())
                        .append("</레이어명>");
                }
            }
            sb.append("</레이어목록>");
            sb.append("</레이어관리>");
        }
        sb.append("</lry-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }

}
