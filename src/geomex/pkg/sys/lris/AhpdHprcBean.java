package geomex.pkg.sys.lris;

import geomex.svc.webctrl.Const;
import geomex.utils.Utils;
import geomex.utils.db.DBHandler;

import java.util.ArrayList;

/*
 * 개별주택가격용 클래스 
 */
public class AhpdHprcBean {
	
    /*
     * PNU에 해당하는 개별주택가격의 정보를 모두 가져온다.
     */
    public ArrayList<AhpdHprc> getHprcList(String pnu){
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<AhpdHprc> list = new ArrayList<AhpdHprc>();

        sb.append(" SELECT DISTINCT * FROM ahpd_hprc ");
        sb.append(" WHERE land_cd = ? ");
        sb.append(" order by hprc_year desc, base_mon asc ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, pnu);
            handler.execute();
            while(handler.next()) {
            	AhpdHprc ah = new AhpdHprc();
            	ah.land_cd = Utils.chkNull(handler.getString("land_cd"));
            	ah.bldg_regno = Utils.chkNull(handler.getString("bldg_regno"));
            	ah.dong_seqno = Utils.chkNull(handler.getString("dong_seqno"));
            	ah.base_mon = Utils.chkNull(handler.getString("base_mon"));
            	ah.hprc_year = Utils.chkNull(handler.getString("hprc_year"));
            	ah.umd_cd = Utils.chkNull(handler.getString("umd_cd"));
            	ah.ri_cd = Utils.chkNull(handler.getString("ri_cd"));
            	ah.land_gbn = Utils.chkNull(handler.getString("land_gbn"));
            	ah.bobn = Utils.chkNull(handler.getString("bobn"));
            	ah.bubn = Utils.chkNull(handler.getString("bubn"));
            	ah.hprc = Utils.chkNull(handler.getString("hprc"));
            	ah.pyo_yn = Utils.chkNull(handler.getString("pyo_yn"));
            	ah.pann_yn = Utils.chkNull(handler.getString("pann_yn"));
            	ah.land_area = Utils.chkNull(handler.getString("land_area"));
            	ah.calc_area = Utils.chkNull(handler.getString("calc_area"));
            	ah.bldg_garea = Utils.chkNull(handler.getString("bldg_garea"));
            	ah.res_area = Utils.chkNull(handler.getString("res_area"));
            	ah.org_cd = Utils.chkNull(handler.getString("org_cd"));
                list.add(ah);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }
    
    /*
     * 해당 PNU, 동 번호를 사용하여 개별주택가격을 가져온다.
     */
    public ArrayList<AhpdHprc> getHprcList(String pnu, String dong_seqno){
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<AhpdHprc> list = new ArrayList<AhpdHprc>();

        sb.append(" SELECT DISTINCT * FROM ahpd_hprc ");
        sb.append(" WHERE land_cd = ? ");
        sb.append(" AND dong_seqno = ? ");
        sb.append(" order by hprc_year desc, base_mon asc ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, pnu);
            handler.setString(2, dong_seqno);
            handler.execute();
            while(handler.next()) {
            	AhpdHprc ah = new AhpdHprc();
            	ah.land_cd = Utils.chkNull(handler.getString("land_cd"));
            	ah.bldg_regno = Utils.chkNull(handler.getString("bldg_regno"));
            	ah.dong_seqno = Utils.chkNull(handler.getString("dong_seqno"));
            	ah.base_mon = Utils.chkNull(handler.getString("base_mon"));
            	ah.hprc_year = Utils.chkNull(handler.getString("hprc_year"));
            	ah.umd_cd = Utils.chkNull(handler.getString("umd_cd"));
            	ah.ri_cd = Utils.chkNull(handler.getString("ri_cd"));
            	ah.land_gbn = Utils.chkNull(handler.getString("land_gbn"));
            	ah.bobn = Utils.chkNull(handler.getString("bobn"));
            	ah.bubn = Utils.chkNull(handler.getString("bubn"));
            	ah.hprc = Utils.chkNull(handler.getString("hprc"));
            	ah.pyo_yn = Utils.chkNull(handler.getString("pyo_yn"));
            	ah.pann_yn = Utils.chkNull(handler.getString("pann_yn"));
            	ah.land_area = Utils.chkNull(handler.getString("land_area"));
            	ah.calc_area = Utils.chkNull(handler.getString("calc_area"));
            	ah.bldg_garea = Utils.chkNull(handler.getString("bldg_garea"));
            	ah.res_area = Utils.chkNull(handler.getString("res_area"));
            	ah.org_cd = Utils.chkNull(handler.getString("org_cd"));
                list.add(ah);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }
}
