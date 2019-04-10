package geomex.pkg.sys.eais;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;
import geomex.pkg.sys.eais.Djyrecaptitle;

/**
 * 총괄표제부를 가지고온다.
 */
public class GetDjyrecaptitle implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {

        String key = kvp.get("KEY");

        Djyrecaptitle dbl = new Djyrecaptitle();
        ArrayList<Djyrecaptitle> list = new ArrayList<Djyrecaptitle>();

        try {
            list = dbl.getDjyrecaptitle(key);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        try {
            sb.append("<총괄표제부>");
            sb.append("<대지위치>").append(list.get(0).ADDR).append("</대지위치>");
            sb.append("<명칭및번호>").append(list.get(0).BLD_NM).append("</명칭및번호>");
            sb.append("<대지면적>").append(list.get(0).PLATAREA).append("</대지면적>");
            sb.append("<연면적>").append(list.get(0).TOTALAREA).append("</연면적>");
            sb.append("<건축물수>").append(list.get(0).BL_CNT).append("</건축물수>");
            sb.append("<건축면적>").append(list.get(0).ARCHAREA).append("</건축면적>");
            sb.append("<용적률산정연면적>").append(list.get(0).VL_RAT_AREA).append("</용적률산정연면적>");
            sb.append("<총호수>").append(list.get(0).TOTAL_BL_NUM).append("</총호수>");
            sb.append("<건폐율>").append(list.get(0).BC_RAT).append("</건폐율>");
            sb.append("<용적율>").append(list.get(0).VL_RAT).append("</용적율>");
            sb.append("<총주차대수>").append(list.get(0).PARKING_CNT).append("</총주차대수>");
            sb.append("<주용도>").append(list.get(0).BL_USABILITY).append("</주용도>");
            sb.append("<부속건축물>").append(list.get(0).ACC_BL).append("</부속건축물>");
            sb.append("<특이사항>").append(list.get(0).REMARK).append("</특이사항>");
            if (list.size() != 0) {
                sb.append("<동별현황리스트>");
                for (int i = 0; i < list.size(); i++) {
                    sb.append("<동별현황>");
                    sb.append("<구분>").append(list.get(i).BL_TYPE).append("</구분>");
                    sb.append("<건축물명칭>").append(list.get(i).BL_NM).append("</건축물명칭>");
                    sb.append("<구조>").append(list.get(i).STRCT).append("</구조>");
                    sb.append("<지붕>").append(list.get(i).ROOF).append("</지붕>");
                    sb.append("<층수>").append(list.get(i).FLR).append("</층수>");
                    sb.append("<용도>").append(list.get(i).USABILITY).append("</용도>");
                    sb.append("<면적>").append(list.get(i).AREA).append("</면적>");
                    sb.append("</동별현황>");
                }
                sb.append("</동별현황리스트>");
            }
            sb.append("<옥내_기계식_대수>").append(list.get(0).INSIDE_MT_NUM).append("</옥내_기계식_대수>");
            sb.append("<옥내_기계식_면적>").append(list.get(0).INSIDE_MT_AREA).append("</옥내_기계식_면적>");
            sb.append("<옥외_기계식_대수>").append(list.get(0).OUTSIDE_MT_NUM).append("</옥외_기계식_대수>");
            sb.append("<옥외_기계식_면적>").append(list.get(0).OUTSIDE_MT_AREA).append("</옥외_기계식_면적>");
            sb.append("<옥내_자주식_대수>").append(list.get(0).INSIDE_SF_NUM).append("</옥내_자주식_대수>");
            sb.append("<옥내_자주식_면적>").append(list.get(0).INSIDE_SF_AREA).append("</옥내_자주식_면적>");
            sb.append("<옥외_자주식_대수>").append(list.get(0).OUTSIDE_SF_NUM).append("</옥외_자주식_대수>");
            sb.append("<옥외_자주식_면적>").append(list.get(0).OUTSIDE_SF_AREA).append("</옥외_자주식_면적>");
            sb.append("<에너지효율_등급>").append(list.get(0).ENG_CLASS).append("</에너지효율_등급>");
            sb.append("<에너지효율_절감율>").append(list.get(0).ENG_SAVE).append("</에너지효율_절감율>");
            sb.append("<에너지성능지표_점>").append(list.get(0).ENG_PFMC).append("</에너지성능지표_점>");
            sb.append("<친환경건축물인증_등급>").append(list.get(0).GBL_CLASS).append("</친환경건축물인증_등급>");
            sb.append("<친환경건축물인증_점>").append(list.get(0).GBL_NUM).append("</친환경건축물인증_점>");
            sb.append("<지능형건축물인증_등급>").append(list.get(0).IBL_CLASS).append("</지능형건축물인증_등급>");
            sb.append("<지능형건축물인증_점>").append(list.get(0).IBL_NUM).append("</지능형건축물인증_점>");

            sb.append("</총괄표제부>");

        } catch (Exception e) {
            WebUtil.sendError(res, "NoData");
        }
        WebUtil.sendNoneHeaderXML(res, sb.toString());
    }
}
