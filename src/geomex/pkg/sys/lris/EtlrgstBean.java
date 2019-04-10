package geomex.pkg.sys.lris;

import geomex.svc.webctrl.Const;
import geomex.utils.Utils;
import geomex.utils.db.DBHandler;

import java.util.ArrayList;

public class EtlrgstBean {

    // 토지대장의 정보를 가지고온다.
    public ArrayList<Etlrgst> getEtlrgstList(String pnu)
    {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<Etlrgst> list = new ArrayList<Etlrgst>();

        //sb.append(" select * from et_lrgst where 1=1 ");
        //sb.append(" and adm_dist_cd||land_site_cd||lg_gbn||lpad(bo_jibn,4,'0')||lpad(bu_jibn,4,'0') = '" + pnu + "'");

        sb.append(" select * from et_lrgst where 1=1 ");  //et_lrgst 테이블 전체를 
        sb.append(" and pnu = '" + pnu + "'");  //들어온 pnu값을 et_lrgst 테이블 안에 pnu 과 비교

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.execute();
            if (handler.next()) {
                Etlrgst eg = new Etlrgst(); //토지대장 분석용 테이블 칼럼정보
                eg.LG_GBN = Utils.chkNull(handler.getString("LG_GBN"));
                eg.LAND_JIMK = Utils.chkNull(handler.getString("LAND_JIMK")); //지목
                eg.JIMK_CD = Utils.chkNull(handler.getString("JIMK_CD"));
                eg.LAND_AREA = Utils.chkNull(handler.getInt("LAND_AREA")); //지번주소
                eg.LAND_MOVE_WHY = Utils.chkNull(handler.getString("LAND_MOVE_WHY"));
                eg.LAND_MOVE_YMD = Utils.chkNull(handler.getString("LAND_MOVE_YMD"));
                eg.OWN_GBN = Utils.chkNull(handler.getString("OWN_GBN")); //소유구분
                eg.OWNR_NM = Utils.chkNull(handler.getString("OWNR_NM")); //소유자
                eg.OWNSP_CH_CAU = Utils.chkNull(handler.getString("OWNSP_CH_CAU"));
                eg.OWNSP_CH_YMD = Utils.chkNull(handler.getString("OWNSP_CH_YMD"));
                eg.SHAP_NUM = Utils.chkNull(handler.getString("SHAP_NUM"));
                list.add(eg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

}
