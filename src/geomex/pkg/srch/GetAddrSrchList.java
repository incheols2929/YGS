package geomex.pkg.srch;

import geomex.svc.handler.Code;
import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * 지번,도로명 주소 검색 결과 목록을 얻는다.
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class GetAddrSrchList implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        String sgg = kvp.get("SGG");
        String umd = kvp.get("UMD");
        String ri = kvp.get("RI");
        String bon = kvp.get("BON");
        String bu = kvp.get("BU");
        String san = kvp.get("SAN");
        String snum = kvp.get("SNUM");
        String pagenum = kvp.get("PAGENUM");
        /*
         * 시군이 정해지면 관련된 읍면동만 검색되고 관련된 리만 검색될수 있게 해줌
         * jibun_cnt.jsp에 있는 기능과 같음 
         */
        String dcode = "";
        if ("".equals(umd)) {
            dcode = sgg;
        } else {
            dcode = umd;
        }

        if ("".equals(ri)) {
            dcode = umd;
        } else {
            dcode = ri;
        }

        ConditionSearchBean CS = new ConditionSearchBean();
        ArrayList<ConditionSearch> cslist = CS.getSrchjibun2(dcode, bon, bu, san, snum, pagenum);

        StringBuilder sb = new StringBuilder();
        sb.append("<srch-list>");
        for (int i = 0; i < cslist.size(); i++) {

            //String fulladdr = Code.getFullAddr(cslist.get(i).pnu.substring(0, 10));
            String eqbnm = Code.getRoadeqb_man_snKey(cslist.get(i).pnu); //건물군의 건물군일련번호를 가지고온다.(지번검색시)

            sb.append("<리스트>");

            sb.append("<pnu>").append(cslist.get(i).pnu).append("</pnu>");
            if (!"".equals(cslist.get(i).getLn_addr())) {
                sb.append("<지번주소>").append(cslist.get(i).getEmd_nm() + " " + cslist.get(i).getJibun()).append("</지번주소>");
            } else {
                sb.append("<지번주소>").append(cslist.get(i).getEmd_nm() + " " + cslist.get(i).getJibun()).append("</지번주소>");
            }
            sb.append("<도로주소>").append(cslist.get(i).getBldg_no()).append("</도로주소>");
            sb.append("<빌딩명>").append(cslist.get(i).getBuld_nm()).append("</빌딩명>");

            if (!"0".equals(eqbnm) & !"".equals(eqbnm)) {
                //건물군일련번호가 있을시
                String _gid = Code.getEqbGidKey(sgg.substring(0, 5), eqbnm); //KAIS건물군 일련번호를 가지고 온다
                sb.append("<레이어명>").append("건물군").append("</레이어명>");
                sb.append("<도로키값>").append(_gid).append("</도로키값>");
            } else {
                //건물군일련번호가 없을시
                //String _gid = Code.getRoadGidKey(cslist.get(i).pnu);
                if (!"".equals(cslist.get(i).getRn_cd())) {
                    String _gid = Code.getRoadKeyVal(sgg.substring(0, 5), cslist.get(i).getBuld_mnnm(), cslist.get(i) //KAIS건물 일련번호를 가져옴
                        .getBuld_slno(), cslist.get(i).getMntn_yn(), cslist.get(i).getLnbr_mnnm(), cslist.get(i).getLnbr_slno(),
                        cslist.get(i).getRn_cd());
                    sb.append("<레이어명>").append("건물").append("</레이어명>");
                    sb.append("<도로키값>").append(_gid).append("</도로키값>"); 
                } else {
                    sb.append("<레이어명>").append("건물").append("</레이어명>");
                    sb.append("<도로키값>").append("").append("</도로키값>");
                }
            }
            sb.append("</리스트>");
        }
        sb.append("</srch-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString()); //xml정보를 클라이언트로 전송

    }

}
