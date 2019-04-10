package geomex.pkg.usr;

public class Notice {

    public int req_seq;
    public String reg_usr_id = "";
    public String subj_txt = "";
    public String conts_txt = "";
    public String notc_typ = "";
    public int read_cnt;
    public String reg_date = "";

    public int getReq_seq() {
        return req_seq;
    }

    public void setReq_seq(int req_seq) {
        this.req_seq = req_seq;
    }

    public String getReg_usr_id() {
        return reg_usr_id;
    }

    public void setReg_usr_id(String reg_usr_id) {
        this.reg_usr_id = reg_usr_id;
    }

    public String getSubj_txt() {
        return subj_txt;
    }

    public void setSubj_txt(String subj_txt) {
        this.subj_txt = subj_txt;
    }

    public String getConts_txt() {
        return conts_txt;
    }

    public void setConts_txt(String conts_txt) {
        this.conts_txt = conts_txt;
    }

    public String getNotc_typ() {
        return notc_typ;
    }

    public void setNotc_typ(String notc_typ) {
        this.notc_typ = notc_typ;
    }

    public int getRead_cnt() {
        return read_cnt;
    }

    public void setRead_cnt(int read_cnt) {
        this.read_cnt = read_cnt;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

}
