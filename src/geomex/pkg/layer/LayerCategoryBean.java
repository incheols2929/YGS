package geomex.pkg.layer;

import geomex.svc.webctrl.Const;
import geomex.utils.StrUtil;
import geomex.utils.db.DBHandler;

import java.util.ArrayList;

public class LayerCategoryBean {

    //레이어그룹정보를 가지고온다.
    public ArrayList<LayerCategory> getGrpLayer()
    {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<LayerCategory> list = new ArrayList<LayerCategory>();

        sb.append(" select grp_id, grp_nm from mt_lyr_grp where grp_id != 'A111' order by grp_id asc ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                LayerCategory lc = new LayerCategory();
                lc.GRP_ID = StrUtil.chkNull(handler.getString("grp_id"));
                lc.GRP_NM = StrUtil.chkNull(handler.getString("grp_nm"));
                list.add(lc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //레이어정보를 가지고온다.
    public ArrayList<LayerCategory> getLayerDesc(String grpid)
    {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<LayerCategory> list = new ArrayList<LayerCategory>();

        sb.append(" select lyr_id, tbl_id, lyr_nm from mt_lyr_desc where grp_id = '"
            + grpid + "' and use_typ = 'C' and use_yn='Y' order by where_txt asc ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                LayerCategory lc = new LayerCategory();
                lc.LYR_ID = StrUtil.chkNull(handler.getString("lyr_id"));
                lc.TBL_ID = StrUtil.chkNull(handler.getString("tbl_id"));
                lc.LYR_NM = StrUtil.chkNull(handler.getString("lyr_nm"));
                list.add(lc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //데이터수집정보를 가지고온다.(레이어관리창에 쓰임)
    public ArrayList<LayerCategory> getClctData(String tblnm)
    {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<LayerCategory> list = new ArrayList<LayerCategory>();

        /*
         * sb.append(" select * from mt_clct_log "); sb.append(" where 1=1 ");
         * sb.append(" and tbl_nm = '" + tblnm + "' ");
         * sb.append(" order by reg_time desc ");
         */

        sb.append(" select org_cd, sys_cd, max(reg_time) as reg_time from mt_clct_log  ");
        sb.append(" where 1=1  ");
        sb.append(" and tbl_nm = '" + tblnm + "' ");
        sb.append(" and err_cnt = 0 ");
        sb.append(" group by org_cd, sys_cd ");
        sb.append(" order by org_cd asc ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                LayerCategory lc = new LayerCategory();
                lc.ORG_CD = StrUtil.chkNull(handler.getString("org_cd"));
                lc.SYS_CD = StrUtil.chkNull(handler.getString("sys_cd"));
                lc.REG_TIME = StrUtil.chkNull(handler.getString("reg_time"));
                /*
                 * lc.SUC_CNT = Utils.chkNull(handler.getString("suc_cnt"));
                 * lc.ST_TIME = Utils.chkNull(handler.getString("st_time"));
                 * lc.ED_TIME = Utils.chkNull(handler.getString("ed_time"));
                 */
                list.add(lc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    public String getClctData(String tblnm, String orgcd)
    {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<LayerCategory> list = new ArrayList<LayerCategory>();
        String etime = "";

        sb.append(" select max(ed_time) as ed_time from  mt_clct_log where tbl_nm = '"
            + tblnm + "' and org_cd = '" + orgcd + "' and err_cnt = 0 and suc_cnt != 0 ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            if (handler.next()) {
                etime = StrUtil.chkNull(handler.getString("ed_time"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return etime;
    }

    public ArrayList<LayerCategory> getOrgCD()
    {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<LayerCategory> list = new ArrayList<LayerCategory>();

        /*
         * sb.append(" select * from mt_clct_log "); sb.append(" where 1=1 ");
         * sb.append(" and tbl_nm = '" + tblnm + "' ");
         * sb.append(" order by reg_time desc ");
         */

        sb.append(" select org_cd from  mt_clct_log group by org_cd order by org_cd asc ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                LayerCategory lc = new LayerCategory();
                lc.ORG_CD = StrUtil.chkNull(handler.getString("org_cd"));
                list.add(lc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

}
