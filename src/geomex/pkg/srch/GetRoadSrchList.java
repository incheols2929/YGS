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
 * 도로명주소 결과값을 얻는다.
 */
public class GetRoadSrchList implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        String sgg = kvp.get("SGG");
        String road = URLDecoder.decode(kvp.get("ROAD"), "utf-8"); //한글처리
        String buld_bon = kvp.get("BULD_BON");
        String buld_bu = kvp.get("BULD_BU");
        String snum = kvp.get("SNUM");
        String pagenum = kvp.get("PAGENUM");
        String buld_se_cd = kvp.get("BULD_SE_CD");

        StringBuilder sb = new StringBuilder();
        ConditionSearchBean CS = new ConditionSearchBean();
        //ArrayList<ConditionSearch> cslist = CS.getDoroNamesrch(sgg, road, buld_bon, buld_bu, snum, pagenum);
        ArrayList<ConditionSearch> cslist = CS.getDoroNamesrch2(sgg, road, buld_bon, buld_bu, snum, pagenum, buld_se_cd);

        sb.append("<road-list>");
        for (int i = 0; i < cslist.size(); i++) {

            /*
             * String dcode = cslist.get(i).getDcode(); if(dcode.length() == 8){
             * dcode = dcode+"00"; } String san = cslist.get(i).getSan(); String
             * bon = cslist.get(i).getBon(); String bu = cslist.get(i).getBu();
             * String pnu = Code.getCreatePNU(dcode, san, bon, bu);
             * if(!"0".equals(san)){ san = "산"; }else{ san = ""; } String
             * buld_nm = cslist.get(i).getBuld_nm(); if("".equals(buld_nm)){
             * buld_nm = "-"; } //도로명주소를 만든다. String RoadAddr =
             * Code.getSGGNM(dcode) + " " + cslist.get(i).getRn() + " " +
             * cslist.get(i).getBuld_mnnm();
             * if(!"0".equals(cslist.get(i).getBuld_slno())){ RoadAddr += "-" +
             * cslist.get(i).getBuld_slno(); } sb.append("<도로명>");
             * sb.append("<pnu>").append(pnu).append("</pnu>"); //건물군을 비교하여 건물군이
             * 있으면 도로명주소 위치 키값을 건물군 키값으로한다.
             * if(!"0".equals(cslist.get(i).getEqb_man_sn())){ //건물군의 건물명을
             * 가지고온다. String eqb_nm = Code.getBuldNM(cslist.get(i).getSig_cd(),
             * cslist.get(i).getEqb_man_sn());
             * sb.append("<레이어명>").append("건물군").append("</레이어명>");
             * sb.append("<key>"
             * ).append(cslist.get(i).getSig_cd()+""+cslist.get(
             * i).getEqb_man_sn()).append("</key>");
             * sb.append("<건물명>").append(eqb_nm).append("</건물명>"); }else{
             * //건물테이블의 _gid를 가지고온다. String gid =
             * Code.getRoadKeyVal(cslist.get(i).getSig_cd(),
             * cslist.get(i).getBuld_mnnm
             * (),cslist.get(i).getBuld_slno(),cslist.get
             * (i).getSan(),cslist.get(i).getBon(),cslist.get(i).getBu(),
             * cslist.get(i).getRn_cd());
             * sb.append("<레이어명>").append("건물").append("</레이어명>");
             * sb.append("<key>").append(gid).append("</key>");
             * sb.append("<건물명>").append(buld_nm).append("</건물명>"); }
             * sb.append("<도로명코드>"
             * ).append(cslist.get(i).getRn_cd()).append("</도로명코드>");
             * sb.append("<도로명주소>").append(RoadAddr).append("</도로명주소>");
             * sb.append("<지번주소>").append(Code.getFullAddr(dcode) + " " + san +
             * " " + bon + "-" + bu).append("</지번주소>"); sb.append("</도로명>");
             */

            String dcode = cslist.get(i).getDcode();

            if (dcode.length() == 8) {
                dcode = dcode + "00";
            }

            String san = cslist.get(i).getSan();
            String bon = cslist.get(i).getBon();
            String bu = cslist.get(i).getBu();

            String pnu = Code.getCreatePNU(dcode, san, bon, bu);
            String dd = Code.getRoademd(cslist.get(i).getDcode());

            sb.append("<도로명>");
            sb.append("<pnu>").append(pnu).append("</pnu>");
            //건물테이블의 _gid를 가지고온다.
            String gid = Code.getRoadKeyVal(cslist.get(i).getSig_cd(), cslist.get(i).getBuld_mnnm(), cslist.get(i).getBuld_slno(),
                cslist.get(i).getSan(), cslist.get(i).getBon(), cslist.get(i).getBu(), cslist.get(i).getRn_cd());
            sb.append("<레이어명>").append("건물").append("</레이어명>");
            sb.append("<key>").append(gid).append("</key>");
            sb.append("<건물명>").append(cslist.get(i).getBuld_nm()).append("</건물명>");
            //}
            sb.append("<도로명코드>").append(cslist.get(i).getRn_cd()).append("</도로명코드>");
            sb.append("<도로명주소>").append(dd + " " + cslist.get(i).getRd_se_lbl()).append("</도로명주소>");
            sb.append("<지번주소>").append(cslist.get(i).getLn_addr()).append("</지번주소>");

            sb.append("</도로명>");
        }
        sb.append("</road-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());
    }

}
