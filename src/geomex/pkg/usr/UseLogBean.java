package geomex.pkg.usr;

import geomex.svc.webctrl.Const;
import geomex.utils.db.DBHandler;

public class UseLogBean {

    public UseLogBean() {

    }

    /*
     * 기능사용이력정보 기록(로그인, 로그아웃포함)
     */
    public boolean setUseRecord(String use_resn, String user_id, String dept_id, String func_id, String biz_id, String use_time,
        String note, String use_ip) {

        DBHandler handler = new DBHandler();
        boolean UR = false;
        StringBuilder sb = new StringBuilder();
              
        sb.append(" INSERT INTO mt_use_log(usr_id, dept_cd, biz_id, func_id, use_resn, use_time, use_note, ip_addr) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, user_id);
            handler.setString(2, dept_id);
            handler.setString(3, biz_id);
            handler.setString(4, func_id);
            handler.setString(5, use_resn);
            handler.setString(6, use_time);
            handler.setString(7, note);
            handler.setString(8, use_ip);
            handler.execute();
            UR = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return UR;
    }

    /*
     * 로그인 기록이 남음
     */
    public boolean setUseLoginRecord(String use_resn, String user_id, String dept_id, String func_id, String biz_id,
        String use_time, String use_ip) {

        DBHandler handler = new DBHandler();
        boolean UR = false;
        StringBuilder sb = new StringBuilder();

        sb.append(" INSERT INTO mt_use_log(usr_id, dept_cd, biz_id, func_id, use_resn, use_time, ip_addr) VALUES (?, ?, ?, ?, ?, ?, ?) ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, user_id);//사용자id
            handler.setString(2, dept_id);//부서관리코드
            handler.setString(3, biz_id);//단위업무관리id
            handler.setString(4, func_id);//단위기능관리id
            handler.setString(5, use_resn);//사용목적
            handler.setString(6, use_time);//사용시간
            handler.setString(7, use_ip); //접속ip
            handler.execute();
            UR = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return UR;
    }

    /*
     * 사용자접속정보기록
     */
    /*
     * public void setConnLogRecord(String id, String session_dept, String
     * session_ip, String conn_time){ DBHandler handler = new DBHandler();
     * boolean UR = false; StringBuilder sb = new StringBuilder(); sb.append(
     * " INSERT INTO mt_conn_log(usr_id, dept_cd, remote_ip, conn_time) VALUES (?, ?, ?, ?) "
     * ); try { handler.open(); handler.setQuery(sb.toString());
     * handler.setString(1, id); handler.setString(2, session_dept);
     * handler.setString(3, session_ip); handler.setString(4, conn_time);
     * handler.execute(); } catch (Exception e) { e.printStackTrace(); } finally
     * { handler.close(); } }
     */

    /*
     * 사용자접속정보 로그아웃시 접속종료시간 기록
     */
    /*
     * public void setConnLogDistime(String id, String session_time_2, String
     * now_time){ DBHandler handler = new DBHandler(); boolean UR = false;
     * StringBuilder sb = new StringBuilder(); sb.append(
     * " UPDATE mt_conn_log SET discon_time=? WHERE usr_id= ? and conn_time=? "
     * ); try { handler.open(); handler.setQuery(sb.toString());
     * handler.setString(1, now_time); handler.setString(2, id);
     * handler.setString(3, session_time_2); handler.execute(); } catch
     * (Exception e) { e.printStackTrace(); } finally { handler.close(); } }
     */

    /*
     * 시스템연계 로그정보
     */
    public boolean setLinkLogInsert(String usrid, String org_cd, String sys_cd, String link_typ, String worknm, String stime) {

        DBHandler handler = new DBHandler();
        boolean UR = false;
        StringBuilder sb = new StringBuilder();

        sb.append(" INSERT INTO mt_link_log(usr_id, org_cd, sys_cd, link_typ, work_nm, st_time) VALUES (?, ?, ?, ?, ?, ?) ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, usrid);
            handler.setString(2, org_cd);
            handler.setString(3, sys_cd);
            handler.setString(4, link_typ);
            handler.setString(5, worknm);
            handler.setString(6, stime);
            handler.execute();
            UR = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return UR;
    }

    /*
     * 시스템연계 로그정보 2011-12-20
     */
    public boolean setLinkLogUpdate(String usrid, String stime, String rslt_cd, String etime, String err_desc, String logtime) {

        DBHandler handler = new DBHandler();
        boolean UR = false;
        StringBuilder sb = new StringBuilder();

        sb.append(" UPDATE mt_link_log ");
        sb.append(" SET rslt_cd=?, ed_time=?, err_desc=?, reg_time=?");
        sb.append(" WHERE usr_id='" + usrid + "' and st_time = '" + stime + "' ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, rslt_cd);
            handler.setString(2, etime);
            handler.setString(3, err_desc);
            handler.setString(4, logtime);
            handler.execute();
            UR = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return UR;
    }
}
