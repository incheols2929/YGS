package geomex.pkg.sys.eais;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;

/**
 * 건축물 간략정보를 얻는다.
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class GetBldgInfo implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub


        StringBuilder sb = new StringBuilder();
        sb.append("<bldg-list>");
        /*
         * if(DTLIST != null){ for(int i=0; i<DTLIST.size(); i++){
         * sb.append("<건축물정보>");
         * sb.append("<지번주소>").append(Code.getAddrcreate(pnu
         * )).append("</지번주소>");
         * sb.append("<도로명주소>").append(Code.getDoroAddr(pnu
         * )).append("</도로명주소>");
         * sb.append("<주용도>").append(DTLIST.get(i).getMAIN_PURPS_CD
         * ()).append("</주용도>");
         * sb.append("<건물수>").append(DTLIST.get(i).getMAIN_BLD_CNT
         * ()).append("</건물수>");
         * sb.append("<대지면적>").append(Utils.getCommaCreate(
         * DTLIST.get(i).getPLAT_AREA()) + "㎡").append("</대지면적>");
         * sb.append("<건축면적>"
         * ).append(Utils.getCommaCreate(DTLIST.get(i).getARCH_AREA())+
         * "㎡").append("</건축면적>");
         * sb.append("<연면적>").append(Utils.getCommaCreate
         * (DTLIST.get(i).getTOTAREA()) + "㎡").append("</연면적>");
         * sb.append("<건페율>").append(DTLIST.get(i).getBC_RAT() +
         * "%").append("</건페율>");
         * sb.append("<용적율>").append(DTLIST.get(i).getVL_RAT() +
         * "%").append("</용적율>"); sb.append("</건축물정보>"); } }else{
         * sb.append("<건축물정보>");
         * sb.append("<지번주소>").append(Code.getAddrcreate(pnu
         * )).append("</지번주소>");
         * sb.append("<도로명주소>").append(Code.getDoroAddr(pnu
         * )).append("</도로명주소>");
         * sb.append("<주용도>").append("").append("</주용도>");
         * sb.append("<건물수>").append("").append("</건물수>");
         * sb.append("<대지면적>").append("㎡").append("</대지면적>");
         * sb.append("<건축면적>").append("㎡").append("</건축면적>");
         * sb.append("<연면적>").append("㎡").append("</연면적>");
         * sb.append("<건페율>").append("%").append("</건페율>");
         * sb.append("<용적율>").append("%").append("</용적율>");
         * sb.append("</건축물정보>"); }
         */
        sb.append("</bldg-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }

}
