package geomex.pkg.srch;

import java.io.IOException;
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
 * 지번,도로명 주소 검색 결과 목록을 얻는다.
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class GetAddrNewSrchList implements Handler {

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
        //ArrayList<ConditionSearch> cslist = CS.getSrchNewjibun(dcode, bon, bu, san, snum, pagenum);
        ArrayList<ConditionSearch> cslist = CS.getSrchNewjibun2(dcode, bon, bu, san, snum, pagenum);

        StringBuilder sb = new StringBuilder();

        String sig_cd = "";
        String emd_cd = "";
        String li_cd = "";
        String mntn_yn = "";
        String lnbr_mnnm = "";
        String lnbr_slno = "";
        String pnu = "";

        sb.append("<srch-list>");
        for (int i = 0; i < cslist.size(); i++) {

            sig_cd = cslist.get(i).getSig_cd();
            emd_cd = cslist.get(i).getEmd_cd();
            li_cd = cslist.get(i).getLi_cd();

            if ("0".equals(cslist.get(i).getMntn_yn())) {
                mntn_yn = "1";
            } else {
                mntn_yn = "2";
            }

            lnbr_mnnm = cslist.get(i).getLnbr_mnnm();
            lnbr_slno = cslist.get(i).getLnbr_slno();

            pnu = sig_cd + "" + emd_cd + "" + li_cd + "" + mntn_yn + "" + lnbr_mnnm + "" + lnbr_slno;

            String eqbnm = Code.getRoadeqb_man_snKey(pnu);

            sb.append("<리스트>");
            sb.append("<pnu>").append(pnu).append("</pnu>");
            sb.append("<지번주소>").append(cslist.get(i).getLn_addr()).append("</지번주소>");
            sb.append("<도로주소>").append(cslist.get(i).getRd_se_lbl()).append("</도로주소>");

            if (!"0".equals(eqbnm) & !"".equals(eqbnm)) {
                //건물군일련번호가 있을시
                String _gid = Code.getEqbGidKey(sgg.substring(0, 5), eqbnm);
                sb.append("<레이어명>").append("건물군").append("</레이어명>");
                sb.append("<도로키값>").append(_gid).append("</도로키값>");
                sb.append("<빌딩명>").append(cslist.get(i).getBuld_nm()).append("</빌딩명>");
            } else {
                //건물군일련번호가 없을시
                //String _gid = Code.getRoadGidKey(pnu);
                String _gid = Code.getRoadKeyVal(cslist.get(i).getSig_cd(), cslist.get(i).getBuld_mnnm(), cslist.get(i)
                    .getBuld_slno(), cslist.get(i).getMntn_yn(), cslist.get(i).getLnbr_mnnm(), cslist.get(i).getLnbr_slno(), cslist
                    .get(i).getRn_cd());
                sb.append("<레이어명>").append("건물").append("</레이어명>");
                sb.append("<도로키값>").append(_gid).append("</도로키값>");
                sb.append("<빌딩명>").append(cslist.get(i).getBuld_nm()).append("</빌딩명>");
            }

            sb.append("</리스트>");

            //////////////////////////////////////////////////////////////////////////////////////////////////////////

            /*
             * sig_cd = cslist.get(i).getSig_cd(); emd_cd =
             * cslist.get(i).getEmd_cd(); li_cd = cslist.get(i).getLi_cd();
             * if("0".equals(cslist.get(i).getMntn_yn())){ mntn_yn = "1"; }else{
             * mntn_yn = "2"; } lnbr_mnnm = cslist.get(i).getLnbr_mnnm();
             * lnbr_slno = cslist.get(i).getLnbr_slno(); pnu =
             * sig_cd+""+emd_cd+""+li_cd+""+mntn_yn+""+lnbr_mnnm+""+lnbr_slno;
             * String eqbnm = Code.getRoadeqb_man_snKey(pnu);
             * sb.append("<리스트>");
             * sb.append("<pnu>").append(pnu).append("</pnu>");
             * sb.append("<지번주소>"
             * ).append(cslist.get(i).getLn_addr()).append("</지번주소>");
             * sb.append(
             * "<도로주소>").append(cslist.get(i).getRd_se_lbl()).append("</도로주소>");
             * if(!"0".equals(eqbnm) & !"".equals(eqbnm)){ //건물군일련번호가 있을시 String
             * _gid = Code.getEqbGidKey(sgg.substring(0,5), eqbnm);
             * sb.append("<레이어명>").append("건물군").append("</레이어명>");
             * sb.append("<도로키값>").append(_gid).append("</도로키값>");
             * sb.append("<빌딩명>"
             * ).append(cslist.get(i).getBuld_nm()).append("</빌딩명>"); }else{
             * //건물군일련번호가 없을시 String _gid = Code.getRoadGidKey(pnu);
             * sb.append("<레이어명>").append("건물").append("</레이어명>");
             * sb.append("<도로키값>").append(_gid).append("</도로키값>");
             * sb.append("<빌딩명>"
             * ).append(cslist.get(i).getBuld_nm()).append("</빌딩명>"); }
             * sb.append("</리스트>");
             */
        }
        sb.append("</srch-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }

}
