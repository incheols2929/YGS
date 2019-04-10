package geomex.pkg.usr;

public class Work {

    //단위업무 정보
    public int biz_seq;
    public String biz_id = "";
    public String biz_nm = "";
    public String conn_url = "";
    public String img_nm = "";
    public String biz_note = "";
    public String reg_date = "";
    public String reg_id = "";

    public String r_auth_yn = "";

    //단위업무정렬정보

    public String usr_id = "";
    public String sort_txt = "";

    public String getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(String usr_id) {
        this.usr_id = usr_id;
    }

    public String getSort_txt() {
        return sort_txt;
    }

    public void setSort_txt(String sort_txt) {
        this.sort_txt = sort_txt;
    }

    public int getBiz_seq() {
        return biz_seq;
    }

    public void setBiz_seq(int biz_seq) {
        this.biz_seq = biz_seq;
    }

    public String getBiz_id() {
        return biz_id;
    }

    public void setBiz_id(String biz_id) {
        this.biz_id = biz_id;
    }

    public String getBiz_nm() {
        return biz_nm;
    }

    public void setBiz_nm(String biz_nm) {
        this.biz_nm = biz_nm;
    }

    public String getConn_url() {
        return conn_url;
    }

    public void setConn_url(String conn_url) {
        this.conn_url = conn_url;
    }

    public String getImg_nm() {
        return img_nm;
    }

    public void setImg_nm(String img_nm) {
        this.img_nm = img_nm;
    }

    public String getBiz_note() {
        return biz_note;
    }

    public void setBiz_note(String biz_note) {
        this.biz_note = biz_note;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getR_auth_yn() {
        return r_auth_yn;
    }

    public void setR_auth_yn(String r_auth_yn) {
        this.r_auth_yn = r_auth_yn;
    }

}
