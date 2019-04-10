package geomex.pkg.usr;

import java.util.ArrayList;

import geomex.svc.webctrl.Const;
import geomex.utils.db.DBHandler;
import geomex.utils.Utils;

public class NoticeBean {

    public NoticeBean() {

    }
    //공지사항
    public String getNoticecount() {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        String cnt = "";
         //테이블안에 몇개의 데이터가 있는지
        sb.append(" select count(*) as cnt from mt_notice ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.execute();

            if (handler.next()) {
                cnt = Utils.chkNull(handler.getString("cnt"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return cnt;
    }
    //공지사항 정보관리
    public ArrayList<Notice> getNoticelist(String start, String end) {

        DBHandler handler = new DBHandler();
        ArrayList<Notice> list = new ArrayList<Notice>(); //ArrayList생성
        StringBuilder sb = new StringBuilder();

        int st = Integer.parseInt(start);
        int en = Integer.parseInt(end);

        sb.append(" SELECT req_seq, reg_usr_id, subj_txt, conts_txt, notc_typ, read_cnt, reg_date ");
        sb.append(" FROM mt_notice where 1=1 ");
        sb.append(" ORDER BY notc_typ = '1', req_seq desc limit " + en + " offset " + (st - 1));

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                Notice NT = new Notice();
                NT.req_seq = Utils.chkNull(handler.getInt("req_seq"));
                NT.reg_usr_id = Utils.chkNull(handler.getString("reg_usr_id"));
                NT.subj_txt = Utils.chkNull(handler.getString("subj_txt"));
                NT.conts_txt = Utils.chkNull(handler.getString("conts_txt"));
                NT.notc_typ = Utils.chkNull(handler.getString("notc_typ"));
                NT.read_cnt = Utils.chkNull(handler.getInt("read_cnt"));
                NT.reg_date = Utils.chkNull(handler.getString("reg_date"));
                list.add(NT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    public ArrayList<Notice> getNoticeview(String key) {

        DBHandler handler = new DBHandler();
        ArrayList<Notice> list = new ArrayList<Notice>();
        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT req_seq, reg_usr_id, subj_txt, conts_txt, notc_typ, read_cnt, reg_date FROM mt_notice where 1=1 and req_seq = '"
            + key + "' ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                Notice NT = new Notice();
                NT.req_seq = Utils.chkNull(handler.getInt("req_seq"));
                NT.reg_usr_id = Utils.chkNull(handler.getString("reg_usr_id"));
                NT.subj_txt = Utils.chkNull(handler.getString("subj_txt"));
                NT.conts_txt = Utils.chkNull(handler.getString("conts_txt"));
                NT.notc_typ = Utils.chkNull(handler.getString("notc_typ"));
                NT.read_cnt = Utils.chkNull(handler.getInt("read_cnt"));
                NT.reg_date = Utils.chkNull(handler.getString("reg_date"));
                list.add(NT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    public void getReadcountedit(String key) {

        DBHandler handler = new DBHandler();
        //ArrayList<Notice> list = new ArrayList<Notice>();
        StringBuilder sb = new StringBuilder();

        sb.append(" update mt_notice set read_cnt = (read_cnt+1) where req_seq = '" + key + "' ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
    }

    public boolean setNoriceInsert(String user, String subject, String category, String content, String date) {

        DBHandler handler = new DBHandler();
        //ArrayList<Notice> list = new ArrayList<Notice>();
        StringBuilder sb = new StringBuilder();
        boolean bool = false;

        sb.append(" INSERT INTO mt_notice(reg_usr_id, subj_txt, conts_txt, notc_typ, reg_date) ");
        sb.append(" VALUES (?, ?, ?, ?, ?) ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, user);
            handler.setString(2, subject);
            handler.setString(3, content);
            handler.setString(4, category);
            handler.setString(5, date);
            handler.execute();
            bool = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return bool;

    }

    public boolean setNoticeDelete(String seq) {

        DBHandler handler = new DBHandler();
        //ArrayList<Notice> list = new ArrayList<Notice>();
        StringBuilder sb = new StringBuilder();
        boolean bool = false;

        int num = Integer.parseInt(seq);

        sb.append(" delete from mt_notice where req_seq = ? ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setInt(1, num);
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
