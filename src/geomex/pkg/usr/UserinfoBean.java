package geomex.pkg.usr;

import geomex.svc.webctrl.Const;
import geomex.utils.db.DBHandler;
import geomex.utils.Base64Util;
import geomex.utils.Utils;

public class UserinfoBean {

    public UserinfoBean() {

    }

    /*
     * 사용자유무확인
     */
    public int getLoginCheck(String id, String pw) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        int idx = -1;
        
        sb.append(" select * from mt_usr_desc where usr_id = ? ");
      
        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, id);
            //handler.setString(2, pw);
            handler.execute();
           
            if (handler.next()) {
                if (pw.equals(Utils.chkNull(handler.getString("usr_pw")))) {
                    idx = 1;
                 
                } else {
                    idx = 0;
                }
            } else {
                idx = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return idx;
    }

    /*
     * 기존사용자의 부서코드확인
     */
    public int getDeptCheck(String id, String pw) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        int idx = -1;

        sb.append(" select dept_cd, usr_pw from mt_usr_desc where usr_id = ? ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, id);
            //handler.setString(2, pw);
            handler.execute();
            if (handler.next()) {
                if (" ".equals(Utils.chkNull(handler.getString("dept_cd")))) {
                    idx = 1;
                } else {
                    idx = 0;
                }
            } else {
                idx = -1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return idx;
    }

    /*
     * 사용자 정보를 가져온다. 칼럼추가 2012-08-07
     */
    public Userinfo getLoginList(String id, String pw) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        Userinfo UI = new Userinfo();
        //id와 pw가 맞으면 mt_usr_desc에서 사용자 정보를 가져온다
        sb.append(" select usr_id, usr_nm, dept_cd, tel_num, email, req_date, app_yn, app_date, usr_pw, ugrp_id, dept_cha_yn ");
        sb.append(" from mt_usr_desc where usr_id = ? and usr_pw = ? ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, id);
            handler.setString(2, pw);
            handler.execute();
            if (handler.next()) {
                UI.setUsr_id(Utils.chkNull(handler.getString("usr_id")));
                UI.setUsr_nm(Utils.chkNull(handler.getString("usr_nm")));
                UI.setDept_cd(Utils.chkNull(handler.getString("dept_cd")));
                UI.setTel_num(Utils.chkNull(handler.getString("tel_num")));
                UI.setEmail(Utils.chkNull(handler.getString("email")));
                UI.setReq_date(Utils.chkNull(handler.getString("req_date")));
                UI.setApp_yn(Utils.chkNull(handler.getString("app_yn")));
                UI.setApp_date(Utils.chkNull(handler.getString("app_date")));
                UI.setUsr_pw(Utils.chkNull(handler.getString("usr_pw")));
                UI.setUgrp_id(Utils.chkNull(handler.getString("ugrp_id")));
                UI.setDept_cha_yn(Utils.chkNull(handler.getString("dept_cha_yn")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return UI;
    }

    /*
     * 사용자 등록신청
     */
    public boolean getUserReg(String usr_id, String usr_nm, String usr_pw, String dept_cd, String tel_num, String email) {

        DBHandler handler = new DBHandler();
        boolean UR = false;
        StringBuilder sb = new StringBuilder();

        sb.append(" INSERT INTO mt_usr_desc(usr_id, usr_nm, dept_cd, tel_num, email, req_date, app_yn, usr_pw, dept_cha_yn) ");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'Y') ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, usr_id);
            handler.setString(2, usr_nm);
            handler.setString(3, dept_cd);
            handler.setString(4, tel_num);
            handler.setString(5, email);
            handler.setString(6, Utils.getStrHour());
            handler.setString(7, "N");
            handler.setString(8, usr_pw);
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
     * 아이디 중복확인
     */
    public boolean getIDCheck(String id) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();

        boolean bool = false;

        sb.append(" select * from mt_usr_desc where usr_id = ? ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, id);
            //handler.setString(2, pw);
            handler.execute();
            if (handler.next()) {
                if (id.equals(Utils.chkNull(handler.getString("usr_id")))) {
                    bool = true;
                } else {
                    bool = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return bool;
    }

    /*
     * 사용자명 및 아이디 중복확인
     */
    public boolean getUsrNMCheck(String id, String nm) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();

        boolean bool = false;

        sb.append(" select usr_id from mt_usr_desc where usr_id = ? and usr_nm = ? ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, id);
            handler.setString(2, nm);
            //handler.setString(2, pw);
            handler.execute();
            if (handler.next()) {
                if (!"".equals(Utils.chkNull(handler.getString("usr_id")))) {
                    bool = true;
                } else {
                    bool = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return bool;
    }

    /*
     * 기존시스템에있는 사용자인지 판단한다.
     */
    public boolean getDivCheck(String id) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();

        boolean bool = false;

        sb.append(" select usr_div from mt_usr_desc where usr_id = ?");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, id);
            handler.execute();
            if (handler.next()) {
                if ("E".equals(Utils.chkNull(handler.getString("usr_div")))) {
                    bool = true;
                } else {
                    bool = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return bool;
    }

    /*
     * 계정문의확인
     */
    public boolean getInquiry(String usr_id, String usr_nm, String dept_cd, String tel_num, String email) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();

        boolean bool = false;

        sb.append(" select * from mt_usr_desc where usr_id = ? ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, usr_id);
            handler.execute();
            if (handler.next()) {
                if (usr_id.equals(Utils.chkNull(handler.getString("usr_id")))
                    && usr_nm.equals(Utils.chkNull(handler.getString("usr_nm")))
                    && dept_cd.equals(Utils.chkNull(handler.getString("dept_cd")))
                    && tel_num.equals(Utils.chkNull(handler.getString("tel_num")))
                    && email.equals(Utils.chkNull(handler.getString("email")))) {
                    bool = true;
                } else {
                    bool = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return bool;
    }

    /*
     * 계정문의 및 사용자 정보수정시 사용한다.
     */
    public Userinfo getUserInfo(String id) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        Userinfo UI = new Userinfo();

        sb.append(" select usr_id, usr_nm, dept_cd, tel_num, email, usr_pw  ");
        sb.append(" from mt_usr_desc where usr_id = ? ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, id);
            handler.execute();
            if (handler.next()) {
                UI.setUsr_id(Utils.chkNull(handler.getString("usr_id")));
                UI.setUsr_nm(Utils.chkNull(handler.getString("usr_nm")));
                UI.setDept_cd(Utils.chkNull(handler.getString("dept_cd")));
                UI.setTel_num(Utils.chkNull(handler.getString("tel_num")));
                UI.setEmail(Utils.chkNull(handler.getString("email")));
                UI.setUsr_pw(Utils.chkNull(Base64Util.base64Encode(handler.getString("usr_pw").getBytes())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return UI;
    }

    /*
     * 사용자 정보수정
     */
    public boolean getUserUpdate(String usr_id, String usr_nm, String usr_pw, String tel_num, String email) {

        DBHandler handler = new DBHandler();
        boolean UP = false;
        StringBuilder sb = new StringBuilder();

        sb.append(" UPDATE mt_usr_desc SET usr_nm=?, tel_num=?, email=? , usr_pw=? ");
        sb.append(" WHERE  usr_id=? ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, usr_nm);
            handler.setString(2, tel_num);
            handler.setString(3, email);
            handler.setString(4, Base64Util.base64Encode(usr_pw.getBytes()));
            handler.setString(5, usr_id);
            handler.execute();
            UP = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return UP;
    }

    /*
     * 사용자 정보수정 칼럼추가 2012-08-07
     */
    public boolean getUserDeptUpdate(String usr_id, String usr_nm, String usr_pw, String tel_num, String email, String dept_cd,
        String reg_date) {

        DBHandler handler = new DBHandler();
        boolean UP = false;
        StringBuilder sb = new StringBuilder();

        sb.append(" UPDATE mt_usr_desc SET usr_nm=?, tel_num=?, email=? , usr_pw=?, dept_cd=?, req_date=?, dept_cha_yn = 'Y' ");
        sb.append(" WHERE  usr_id=? ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, usr_nm);
            handler.setString(2, tel_num);
            handler.setString(3, email);
            handler.setString(4, Base64Util.base64Encode(usr_pw.getBytes()));
            handler.setString(5, dept_cd);
            handler.setString(6, reg_date);
            handler.setString(7, usr_id);
            handler.execute();
            UP = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return UP;
    }

    /*
     * 부서명 변경
     */
    public boolean setDeptcdChange(String dept_cd, String usr_id) {

        DBHandler handler = new DBHandler();
        boolean UP = false;
        StringBuilder sb = new StringBuilder();

        sb.append(" UPDATE mt_usr_desc SET dept_cd=?, dept_cha_yn = 'Y' ");
        sb.append(" WHERE  usr_id = ? ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, dept_cd);
            handler.setString(2, usr_id);
            handler.execute();
            UP = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return UP;
    }

    /*
     * 부서명 유지
     */
    public boolean setDeptcdKeep(String usr_id) {

        DBHandler handler = new DBHandler();
        boolean UP = false;
        StringBuilder sb = new StringBuilder();

        sb.append(" UPDATE mt_usr_desc SET dept_cha_yn = 'N' ");
        sb.append(" WHERE usr_id = ? ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, usr_id);
            handler.execute();
            UP = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return UP;
    }

    /*
     * 사용목적 가져오기
     */
    public String getPurpose(String usr_id) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        String purpose = "";
        //테이블에서 시스템사용목적을 사용자ID로 찾음
        sb.append(" select use_purp from mt_usr_desc ");
        sb.append(" WHERE usr_id = ? ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, usr_id);
            handler.execute();
            if (handler.next()) {
                purpose = handler.getString("use_purp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return purpose;
    }

    /*
     * 사용목적등록
     */
    public void setPurpose(String purpose, String usr_id) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();

        sb.append(" UPDATE mt_usr_desc SET use_purp = ? ");
        sb.append(" WHERE usr_id = ? ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setString(1, purpose);
            handler.setString(2, usr_id);
            handler.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
    }

}
