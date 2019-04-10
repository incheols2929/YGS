package geomex.pkg.sys.eais;

import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * 표제부를 정보를 가지고온다.
 */
public class GetDjytitle implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {

        String key = kvp.get("KEY");

        Djytitle dtl = new Djytitle();
        ArrayList<Djytitle> list = new ArrayList<Djytitle>();
        StringBuilder sb = new StringBuilder();

        try {
            list = dtl.getDjytitle(key);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        try {
            sb.append("<표제부>");
            sb.append("<표제부_일반건축물>").append(list.get(0).BLDG_TYPE).append("</표제부_일반건축물>");
            sb.append("<대지위치>").append(list.get(0).ADDR).append("</대지위치>");
            sb.append("<명칭및번호>").append(list.get(0).BLD_NM).append("</명칭및번호>");
            sb.append("<대지면적>").append(list.get(0).PLATAREA).append("</대지면적>");
            sb.append("<연면적>").append(list.get(0).TOTALAREA).append("</연면적>");
            sb.append("<호수>").append(list.get(0).BL_NUM).append("</호수>");
            sb.append("<건축면적>").append(list.get(0).ARCHAREA).append("</건축면적>");
            sb.append("<용적률산정용연면적>").append(list.get(0).VL_RAT_AREA).append("</용적률산정용연면적>");
            sb.append("<층수>").append(list.get(0).FLR).append("</층수>");
            sb.append("<건폐율>").append(list.get(0).BC_RAT).append("</건폐율>");
            sb.append("<용적율>").append(list.get(0).VL_RAT).append("</용적율>");
            sb.append("<높이>").append(list.get(0).HEIGHT).append("</높이>");
            sb.append("<주용도>").append(list.get(0).BL_USABILITY).append("</주용도>");
            sb.append("<부속건축물>").append(list.get(0).ACC_BL).append("</부속건축물>");
            sb.append("<주구조>").append(list.get(0).BL_STRCT).append("</주구조>");
            sb.append("<지붕구조>").append(list.get(0).BL_ROOF).append("</지붕구조>");
            sb.append("<허가일자>").append(list.get(0).PMT_YMD).append("</허가일자>");
            sb.append("<착공일자>").append(list.get(0).CON_YMD).append("</착공일자>");
            sb.append("<사용승인일자>").append(list.get(0).ACC_YMD).append("</사용승인일자>");
            if (list.size() != 0) {
                sb.append("<층별현황리스트>");
                for (int i = 0; i < list.size(); i++) {
                    sb.append("<층별현황>");
                    sb.append("<구분>").append(list.get(i).FLR_TYPE).append("</구분>");
                    sb.append("<층별>").append(list.get(i).FLR_NM).append("</층별>");
                    sb.append("<구조>").append(list.get(i).FLR_STRCT).append("</구조>");
                    sb.append("<용도>").append(list.get(i).FLR_USABILITY).append("</용도>");
                    sb.append("<면적>").append(list.get(i).FLR_AREA).append("</면적>");
                    sb.append("</층별현황>");
                }
                sb.append("</층별현황리스트>");
            }
            sb.append("<옥내_기계식_대수>").append(list.get(0).INSIDE_MT_NUM).append("</옥내_기계식_대수>");
            sb.append("<옥내_기계식_면적>").append(list.get(0).INSIDE_MT_AREA).append("</옥내_기계식_면적>");
            sb.append("<옥외_기계식_대수>").append(list.get(0).OUTSIDE_MT_NUM).append("</옥외_기계식_대수>");
            sb.append("<옥외_기계식_면적>").append(list.get(0).OUTSIDE_SF_AREA).append("</옥외_기계식_면적>");
            sb.append("<옥내_자주식_대수>").append(list.get(0).INSIDE_SF_NUM).append("</옥내_자주식_대수>");
            sb.append("<옥내_자주식_면적>").append(list.get(0).INSIDE_SF_AREA).append("</옥내_자주식_면적>");
            sb.append("<옥외_자주식_대수>").append(list.get(0).OUTSIDE_SF_NUM).append("</옥외_자주식_대수>");
            sb.append("<옥외_자주식_면적>").append(list.get(0).OUTSIDE_SF_AREA).append("</옥외_자주식_면적>");
            sb.append("<승용>").append(list.get(0).RIDING).append("</승용>");
            sb.append("<비상용>").append(list.get(0).RIDING_EMER).append("</비상용>");
            sb.append("<에너지효율_등급>").append(list.get(0).ENG_CLASS).append("</에너지효율_등급>");
            sb.append("<에너지효율_절감율>").append(list.get(0).ENG_SAVE).append("</에너지효율_절감율>");
            sb.append("<에너지성능지표_점>").append(list.get(0).ENG_PFMC).append("</에너지성능지표_점>");
            sb.append("<친환경건축물인증_등급>").append(list.get(0).GBL_CLASS).append("</친환경건축물인증_등급>");
            sb.append("<친환경건축물인증_점>").append(list.get(0).GBL_NUM).append("</친환경건축물인증_점>");
            sb.append("<지능형건축물인증_등급>").append(list.get(0).IBL_CLASS).append("</지능형건축물인증_등급>");
            sb.append("<지능형건축물인증_점>").append(list.get(0).IBL_NUM).append("</지능형건축물인증_점>");

            sb.append("</표제부>");

        } catch (Exception e) {
            WebUtil.sendError(res, "NoData");
        }
        WebUtil.sendNoneHeaderXML(res, sb.toString());
    }
}
