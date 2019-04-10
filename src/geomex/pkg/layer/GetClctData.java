package geomex.pkg.layer;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import geomex.svc.handler.Code;
import geomex.utils.Utils;
import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;
import geomex.pkg.layer.LayerCategory;
import geomex.pkg.layer.LayerCategoryBean;

/**
 * 데이터자료수집로그정보를 가지고온다.(레이어관리창에 쓰임) 2011-12-22
 */
public class GetClctData implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        String ucode = "";
        String tblnm = kvp.get("TBLNM");
        String lyrnm = URLDecoder.decode(kvp.get("LYRNM"), "UTF-8");

        if ("v_tl_sprd_manage".equals(tblnm)) {
            tblnm = "tl_sprd_manage";
        }

        if ("lt_c_uzone".equals(tblnm)) {
            ucode = Code.getLyrWhereTxt(lyrnm);
            tblnm = "lt_c_" + ucode;
        }

        LayerCategoryBean LB = new LayerCategoryBean();
        ArrayList<LayerCategory> org = LB.getOrgCD();
        //ArrayList<LayerCategory> list = LB.getClctData(tblnm);

        String Clctdata = "";

        StringBuilder sb = new StringBuilder();
        sb.append("<clct-list>");
        for (int i = 0; i < org.size(); i++) {
            Clctdata = LB.getClctData(tblnm, org.get(i).getORG_CD());
            sb.append("<수집정보>");
            sb.append("<관리기관>").append(Code.getSGGNM(org.get(i).getORG_CD() + "00000")).append("</관리기관>");
            //sb.append("<시스템명>").append(Code.getSysNm(list.get(i).getSYS_CD())).append("</시스템명>");
            //sb.append("<시스템명>").append(list.get(i).getSYS_CD()).append("</시스템명>");
            sb.append("<시스템명>").append("").append("</시스템명>");
            if (!"".equals(Clctdata)) {
                sb.append("<업데이트날짜>").append(Utils.formatDate(Clctdata)).append("</업데이트날짜>");
            } else {
                sb.append("<업데이트날짜>").append("내용 없음").append("</업데이트날짜>");
            }

            sb.append("</수집정보>");
        }

        /*
         * for(int i=0; i<list.size(); i++){ sb.append("<수집정보>");
         * sb.append("<관리기관>"
         * ).append(Code.getSGGNM(list.get(i).getORG_CD()+"00000"
         * )).append("</관리기관>");
         * //sb.append("<시스템명>").append(Code.getSysNm(list.
         * get(i).getSYS_CD())).append("</시스템명>");
         * sb.append("<시스템명>").append(list
         * .get(i).getSYS_CD()).append("</시스템명>");
         * sb.append("<업데이트날짜>").append(Utils
         * .formatDate(list.get(i).getREG_TIME())).append("</업데이트날짜>");
         * sb.append("</수집정보>"); }
         */
        sb.append("</clct-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }

}
