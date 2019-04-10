package geomex.pkg.usr;

import java.util.ArrayList;

import geomex.svc.webctrl.Const;
import geomex.utils.db.DBHandler;
import geomex.utils.Utils;

public class WorkBean {

    public WorkBean() {

    }

    public ArrayList<Work> getWork(String id, String session_grp_id) {

        ArrayList<Work> list = new ArrayList<Work>();

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();

        String seq = "";

        String sortlink = getUserWork(id).trim();
        String[] SA = sortlink.split(",");
        //List<String> sort = Arrays.asList(SA);
        //Collections.reverse(sort);

        if (SA.length != 1) {
            for (int i = 0; i < SA.length; i++) {
                if ((i + 1) != SA.length) {
                    seq += "case when biz_seq = " + SA[i] + " then 1 else 2 end,";
                } else {
                    seq += "case when biz_seq = " + SA[i] + " then 1 else 2 end";
                }
            }
        } else {
            seq += "biz_seq";
        }

        //sb.append("select biz_seq, biz_id, biz_nm, conn_url, img_nm, biz_note, reg_date, reg_id from mt_biz_desc order by " + seq + " asc");

        sb.append(" select biz_seq, max(biz_id) as biz_id, max(biz_nm) as biz_nm, max(conn_url) as conn_url, max(img_nm) as img_nm, max(biz_note) as biz_note ");
        sb.append(" , max(reg_date) as reg_date, max(r_auth_yn) as r_auth_yn from( ");
        sb.append(" select biz.biz_seq, biz.biz_id, biz.biz_nm, biz.conn_url, biz.img_nm, biz.biz_note, biz.reg_date, auth.r_auth_yn ");
        sb.append(" from mt_biz_desc biz ");
        sb.append(" left outer join mt_ugrp_auth auth on auth.ugrp_id='" + session_grp_id + "' ");
        sb.append(" and biz.biz_id = auth.auth_id ");
        sb.append(" and auth.auth_typ='B' ");
        sb.append(" union all ");
        sb.append(" select biz.biz_seq, biz.biz_id, biz.biz_nm, biz.conn_url, biz.img_nm, biz.biz_note, biz.reg_date, auth.r_auth_yn ");
        sb.append(" from mt_biz_desc biz ");
        sb.append(" left outer join mt_usr_auth auth on auth.usr_id='" + id + "' ");
        sb.append(" and biz.biz_id = auth.auth_id  ");
        sb.append(" and auth.auth_typ='B' ");
        sb.append(" ) C ");
        sb.append(" where substring(biz_id, 1, 2) = 'BW' ");
        sb.append(" group by biz_seq order by " + seq + " asc");

        //System.out.println(sb.toString()); 

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                Work bi = new Work();
                bi.biz_seq = Utils.chkNull(handler.getInt("biz_seq"));
                bi.biz_id = Utils.chkNull(handler.getString("biz_id"));
                bi.biz_nm = Utils.chkNull(handler.getString("biz_nm"));
                bi.conn_url = Utils.chkNull(handler.getString("conn_url"));
                bi.img_nm = Utils.chkNull(handler.getString("img_nm"));
                bi.biz_note = Utils.chkNull(handler.getString("biz_note"));
                bi.reg_date = Utils.chkNull(handler.getString("reg_date"));
                bi.r_auth_yn = Utils.chkNull(handler.getString("r_auth_yn"));
                list.add(bi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    public String getUserWork(String id) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();

        String sortlink = "";
        sb.append("select sort_txt from mt_biz_seq where usr_id = '" + id + "'");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.execute();
            if (handler.next()) {
                sortlink = Utils.chkNull(handler.getString("sort_txt"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return sortlink;
    }

    /*
     * 업무기능을 업데이트 또는 인서트한다.
     */
    public boolean getWorkUpdate(String id, String sortingnum) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        boolean bool = false;

        int chknum = getWorkCheck(id);

        if (chknum == 1) {
            sb.append(" UPDATE mt_biz_seq SET sort_txt='" + sortingnum + "' WHERE usr_id = '" + id + "' ");
        } else {
            sb.append(" INSERT INTO mt_biz_seq(usr_id, sort_txt)VALUES ('" + id + "', '" + sortingnum + "') ");
        }

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.execute();
            bool = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return bool;
    }

    /*
     * 업무기능을 업데이트 또는 인서트하기전 아이디 체크를 한다.
     */
    public int getWorkCheck(String id) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        int idx = -1;

        sb.append(" select * from mt_biz_seq where usr_id = ? ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, id);
            handler.execute();
            if (handler.next()) {
                if (id.equals(Utils.chkNull(handler.getString("usr_id")))) {
                    idx = 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return idx;
    }

    public boolean getWorkInsert(String sitename, String urlname, String imgname, String content, String sTimestr) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        boolean bool = false;

        sb.append(" INSERT INTO mt_biz_desc(biz_nm, conn_url, img_nm, biz_note, reg_date) VALUES (?, ?, ?, ?, ?) ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, sitename);
            handler.setString(2, urlname);
            handler.setString(3, imgname);
            handler.setString(4, content);
            handler.setString(5, sTimestr);
            handler.execute();
            bool = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return bool;
    }

}
