package geomex.pkg.srch;

public class ConditionSearch {

    public String code = "";
    public String value = "";
    public String value1 = "";
    public String value2 = "";
    public String facility = "";
    public int cnt;
    public String jibun = "";
    public String emd_nm = "";
    public String bldg_no = "";
    public String g2_code = "";//관리기관명

	//시설물 정보
    public String ri_cde = "";
    public int rid;
	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public String ftr_idn = "";
    public String ftr_cde = "";
    public String ftr_nm ="";
	public String getFtr_nm() {
		return ftr_nm;
	}

	public void setFtr_nm(String ftr_nm) {
		this.ftr_nm = ftr_nm;
	}

	public String mng_cde ="";
    public String pnu = "";
    public String juso = "";
    public String mng_nam = "";
    public String tel_num = "";
    public String beg_ymd = "";
    public String fns_ymd = "";
    public String rsv_vol = "";
    public String pdg_unt = "";
    public String ful_ara = "";
    public String form_exp = "";
    public String byc_len = "";
    public String tot_jea = "";
    public String avg_jea = "";
    public String wid_len = "";
    public String lat_in_sug = "";
    public String lat_out_su = "";
    public String slo_in_sug = "";
    public String slo_out_su = "";
    public String rsv_ara = "";
    public String dra_wal = "";
    public String orf_dth = "";
    public String sct_cmd = "";
    public String wsw_len = "";
    public String max_un_dra = "";
    public String avg_un_dra = "";
    public String ren_dra = "";
    public String max_dra = "";
    public String min_dra = "";
    public String avg_dra = "";
    public String wtr_dra = "";
    public String cde_wtl = "";
    public String ren_wtl = "";
    public String sec_h_wtl = "";
    public String sec_b_wtl = "";
    public String sec_p_wtl = "";
    public String str_wtl = "";
    public String sec_wtl = "";
    public String std_wtl = "";
    public String int_wtl = "";
    public String mai_way = "";
    public String bra_way = "";
    public String chu_way = "";
    public String rac_way = "";
    public String aqu_way = "";
    public String drain_way = "";
    public String str_ara = "";
    public String vis_ara = "";
    public String tri_ara = "";
    public String opr_nam = "";
    public String gcn_nam = "";
    public String nat_amt = "";
    public String bnd_amt = "";
    public String cit_amt = "";
    public String sel_amt = "";
    public String cet_amt = "";
    public String tct_amt = "";
    
    
    //레이어정보
    public String lyr_show_yn = "";
    public String lyr_id = "";
    public String lyr_nm = "";

    //도로명 주소
    public String dcode = "";
    public String san = "";
    public String bon = "";
    public String bu = "";
    public String bul_man_no = "";
    public String buld_mnnm = "";
    public String eqb_man_sn = "";
    public String rn_cd = "";
    public String buld_slno = "";
    public String rn = "";
    public String buld_nm = "";
    public String eng_rn = "";
    public String rbp_cn = "";
    public String rep_cn = "";
    public String road_bt = "";
    public String road_lt = "";
    public String alwnc_resn = "";
    public String emd_cd = "";
    public String li_cd = "";
    public String mntn_yn = "";
    public String lnbr_mnnm = "";
    public String lnbr_slno = "";
    public String rd_addr = "";
    public String ln_addr = "";
    public String rd_name = "";
    public String rd_se_lbl = "";
    public String bd_mgt_sn = "";
    public String gid = "";

    //교차로명
    public String sig_cd = "";
    public int crsrd_sn;
    public String kor_crsrd = "";

    /*
     * 부서정보 필드명
     */
    public String dept_cd = "";
    public String dept_nm = "";
    public String dept_note = "";
    public String res_ymd="";
    public String res_sto="";
    public String res_to="";
    public String res_eff="";
    public String ben_area="";
    public String bas_area="";
    public String etc = "";
    public String ftr_gbn = "";
    
    
    //필지정보
    
	public String landarea;
	public String traarea;
	public String ownrnm;
	public String getLandarea() {
		return landarea;
	}

	public void setLandarea(String landarea) {
		this.landarea = landarea;
	}

	public String getTraarea() {
		return traarea;
	}

	public void setTraarea(String traarea) {
		this.traarea = traarea;
	}

	public String getOwnrnm() {
		return ownrnm;
	}

	public void setOwnrnm(String ownrnm) {
		this.ownrnm = ownrnm;
	}

	public String getJimok() {
		return jimok;
	}

	public void setJimok(String jimok) {
		this.jimok = jimok;
	}

	public String jimok;
    
    
    
    public String getFtr_gbn() {
		return ftr_gbn;
	}

	public void setFtr_gbn(String ftr_gbn) {
		this.ftr_gbn = ftr_gbn;
	}

	public String getBas_area() {
		return bas_area;
	}

	public void setBas_area(String bas_area) {
		this.bas_area = bas_area;
	}

	public String getBen_area() {
		return ben_area;
	}

	public void setBen_area(String ben_area) {
		this.ben_area = ben_area;
	}

	public String getRes_eff() {
		return res_eff;
	}

	public void setRes_eff(String res_eff) {
		this.res_eff = res_eff;
	}

	public String getRes_to() {
		return res_to;
	}

	public void setRes_to(String res_to) {
		this.res_to = res_to;
	}

	public String getRes_sto() {
		return res_sto;
	}

	public void setRes_sto(String res_sto) {
		this.res_sto = res_sto;
	}

	public String getRes_ymd() {
		return res_ymd;
	}

	public void setRes_ymd(String res_ymd) {
		this.res_ymd = res_ymd;
	}

	public String getRi_cde() {
		return ri_cde;
	}

	public void setRi_cde(String ri_cde) {
		this.ri_cde = ri_cde;
	}

    public String getG2_code() {
		return g2_code;
	}

	public void setG2_code(String g2_code) {
		this.g2_code = g2_code;
	}

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getBd_mgt_sn() {
        return bd_mgt_sn;
    }

    public void setBd_mgt_sn(String bd_mgt_sn) {
        this.bd_mgt_sn = bd_mgt_sn;
    }

    public String getRd_se_lbl() {
        return rd_se_lbl;
    }

    public void setRd_se_lbl(String rd_se_lbl) {
        this.rd_se_lbl = rd_se_lbl;
    }

    public String getRd_name() {
        return rd_name;
    }

    public void setRd_name(String rd_name) {
        this.rd_name = rd_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }
    
    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }
    
    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }
    

    public String getPnu() {
        return pnu;
    }

    public void setPnu(String pnu) {
        this.pnu = pnu;
    }
    
    public String getFtr_idn() {
        return ftr_idn;
    }

    public void setFtr_idn(String ftr_idn) {
        this.ftr_idn = ftr_idn;
    }

    
    public String getJuso() {
        return juso;
    }

    public void setJuso(String juso) {
        this.juso = juso;
    }

    public String getJibun() {
        return jibun;
    }

    public void setJibun(String jibun) {
        this.jibun = jibun;
    }

    public String getEmd_nm() {
        return emd_nm;
    }

    public void setEmd_nm(String emd) {
        this.emd_nm = emd;
    }

    public String getBldg_no() {
        return bldg_no;
    }

    public void setBldg_no(String Bldg_no) {
        this.bldg_no = Bldg_no;
    }

    public String getDcode() {
        return dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    public String getSan() {
        return san;
    }

    public void setSan(String san) {
        this.san = san;
    }

    public String getBon() {
        return bon;
    }

    public void setBon(String bon) {
        this.bon = bon;
    }

    public String getBu() {
        return bu;
    }

    public void setBu(String bu) {
        this.bu = bu;
    }

    public String getBul_man_no() {
        return bul_man_no;
    }

    public void setBul_man_no(String bul_man_no) {
        this.bul_man_no = bul_man_no;
    }

    public String getBuld_mnnm() {
        return buld_mnnm;
    }

    public void setBuld_mnnm(String buld_mnnm) {
        this.buld_mnnm = buld_mnnm;
    }

    public String getBuld_slno() {
        return buld_slno;
    }

    public void setBuld_slno(String buld_slno) {
        this.buld_slno = buld_slno;
    }

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public String getBuld_nm() {
        return buld_nm;
    }

    public void setBuld_nm(String buld_nm) {
        this.buld_nm = buld_nm;
    }

    public String getDept_cd() {
        return dept_cd;
    }

    public void setDept_cd(String dept_cd) {
        this.dept_cd = dept_cd;
    }

    public String getDept_nm() {
        return dept_nm;
    }

    public void setDept_nm(String dept_nm) {
        this.dept_nm = dept_nm;
    }

    public String getDept_note() {
        return dept_note;
    }

    public void setDept_note(String dept_note) {
        this.dept_note = dept_note;
    }

    public String getSig_cd() {
        return sig_cd;
    }

    public void setSig_cd(String sig_cd) {
        this.sig_cd = sig_cd;
    }

    public int getCrsrd_sn() {
        return crsrd_sn;
    }

    public void setCrsrd_sn(int crsrd_sn) {
        this.crsrd_sn = crsrd_sn;
    }

    public String getKor_crsrd() {
        return kor_crsrd;
    }

    public void setKor_crsrd(String kor_crsrd) {
        this.kor_crsrd = kor_crsrd;
    }

    public String getLyr_show_yn() {
        return lyr_show_yn;
    }

    public void setLyr_show_yn(String lyr_show_yn) {
        this.lyr_show_yn = lyr_show_yn;
    }

    public String getLyr_id() {
        return lyr_id;
    }

    public void setLyr_id(String lyr_id) {
        this.lyr_id = lyr_id;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getLyr_nm() {
        return lyr_nm;
    }

    public void setLyr_nm(String lyr_nm) {
        this.lyr_nm = lyr_nm;
    }

    public String getEng_rn() {
        return eng_rn;
    }

    public void setEng_rn(String eng_rn) {
        this.eng_rn = eng_rn;
    }

    public String getRbp_cn() {
        return rbp_cn;
    }

    public void setRbp_cn(String rbp_cn) {
        this.rbp_cn = rbp_cn;
    }

    public String getRep_cn() {
        return rep_cn;
    }

    public void setRep_cn(String rep_cn) {
        this.rep_cn = rep_cn;
    }

    public String getRoad_bt() {
        return road_bt;
    }

    public void setRoad_bt(String road_bt) {
        this.road_bt = road_bt;
    }

    public String getRoad_lt() {
        return road_lt;
    }

    public void setRoad_lt(String road_lt) {
        this.road_lt = road_lt;
    }

    public String getAlwnc_resn() {
        return alwnc_resn;
    }

    public void setAlwnc_resn(String alwnc_resn) {
        this.alwnc_resn = alwnc_resn;
    }

    public String getEqb_man_sn() {
        return eqb_man_sn;
    }

    public void setEqb_man_sn(String eqb_man_sn) {
        this.eqb_man_sn = eqb_man_sn;
    }

    public String getRn_cd() {
        return rn_cd;
    }

    public void setRn_cd(String rn_cd) {
        this.rn_cd = rn_cd;
    }

    public String getEmd_cd() {
        return emd_cd;
    }

    public void setEmd_cd(String emd_cd) {
        this.emd_cd = emd_cd;
    }

    public String getLi_cd() {
        return li_cd;
    }

    public void setLi_cd(String li_cd) {
        this.li_cd = li_cd;
    }

    public String getMntn_yn() {
        return mntn_yn;
    }

    public void setMntn_yn(String mntn_yn) {
        this.mntn_yn = mntn_yn;
    }

    public String getLnbr_mnnm() {
        return lnbr_mnnm;
    }

    public void setLnbr_mnnm(String lnbr_mnnm) {
        this.lnbr_mnnm = lnbr_mnnm;
    }

    public String getLnbr_slno() {
        return lnbr_slno;
    }

    public void setLnbr_slno(String lnbr_slno) {
        this.lnbr_slno = lnbr_slno;
    }

    public String getRd_addr() {
        return rd_addr;
    }

    public void setRd_addr(String rd_addr) {
        this.rd_addr = rd_addr;
    }

    public String getLn_addr() {
        return ln_addr;
    }

    public void setLn_addr(String ln_addr) {
        this.ln_addr = ln_addr;
    }
    //시설물

	public String getMng_cde() {
		return mng_cde;
	}

	public void setMng_cde(String mng_cde) {
		this.mng_cde = mng_cde;
	}

	public String getMng_nam() {
		return mng_nam;
	}

	public void setMng_nam(String mng_nam) {
		this.mng_nam = mng_nam;
	}

	public String getTel_num() {
		return tel_num;
	}

	public void setTel_num(String tel_num) {
		this.tel_num = tel_num;
	}

	public String getBeg_ymd() {
		return beg_ymd;
	}

	public void setBeg_ymd(String beg_ymd) {
		this.beg_ymd = beg_ymd;
	}

	public String getFns_ymd() {
		return fns_ymd;
	}

	public void setFns_ymd(String fns_ymd) {
		this.fns_ymd = fns_ymd;
	}

	public String getRsv_vol() {
		return rsv_vol;
	}

	public void setRsv_vol(String rsv_vol) {
		this.rsv_vol = rsv_vol;
	}

	public String getPdg_unt() {
		return pdg_unt;
	}

	public void setPdg_unt(String pdg_unt) {
		this.pdg_unt = pdg_unt;
	}

	public String getFul_ara() {
		return ful_ara;
	}

	public void setFul_ara(String ful_ara) {
		this.ful_ara = ful_ara;
	}

	public String getForm_exp() {
		return form_exp;
	}

	public void setForm_exp(String form_exp) {
		this.form_exp = form_exp;
	}

	public String getByc_len() {
		return byc_len;
	}

	public void setByc_len(String byc_len) {
		this.byc_len = byc_len;
	}

	public String getTot_jea() {
		return tot_jea;
	}

	public void setTot_jea(String tot_jea) {
		this.tot_jea = tot_jea;
	}

	public String getAvg_jea() {
		return avg_jea;
	}

	public void setAvg_jea(String avg_jea) {
		this.avg_jea = avg_jea;
	}

	public String getWid_len() {
		return wid_len;
	}

	public void setWid_len(String wid_len) {
		this.wid_len = wid_len;
	}

	public String getLat_in_sug() {
		return lat_in_sug;
	}

	public void setLat_in_sug(String lat_in_sug) {
		this.lat_in_sug = lat_in_sug;
	}

	public String getLat_out_su() {
		return lat_out_su;
	}

	public void setLat_out_su(String lat_out_su) {
		this.lat_out_su = lat_out_su;
	}

	public String getSlo_in_sug() {
		return slo_in_sug;
	}

	public void setSlo_in_sug(String slo_in_sug) {
		this.slo_in_sug = slo_in_sug;
	}

	public String getSlo_out_su() {
		return slo_out_su;
	}

	public void setSlo_out_su(String slo_out_su) {
		this.slo_out_su = slo_out_su;
	}

	public String getRsv_ara() {
		return rsv_ara;
	}

	public void setRsv_ara(String rsv_ara) {
		this.rsv_ara = rsv_ara;
	}

	public String getDra_wal() {
		return dra_wal;
	}

	public void setDra_wal(String dra_wal) {
		this.dra_wal = dra_wal;
	}

	public String getOrf_dth() {
		return orf_dth;
	}

	public void setOrf_dth(String orf_dth) {
		this.orf_dth = orf_dth;
	}

	public String getSct_cmd() {
		return sct_cmd;
	}

	public void setSct_cmd(String sct_cmd) {
		this.sct_cmd = sct_cmd;
	}

	public String getWsw_len() {
		return wsw_len;
	}

	public void setWsw_len(String wsw_len) {
		this.wsw_len = wsw_len;
	}

	public String getMax_un_dra() {
		return max_un_dra;
	}

	public void setMax_un_dra(String max_un_dra) {
		this.max_un_dra = max_un_dra;
	}

	public String getAvg_un_dra() {
		return avg_un_dra;
	}

	public void setAvg_un_dra(String avg_un_dra) {
		this.avg_un_dra = avg_un_dra;
	}

	public String getRen_dra() {
		return ren_dra;
	}

	public void setRen_dra(String ren_dra) {
		this.ren_dra = ren_dra;
	}

	public String getMax_dra() {
		return max_dra;
	}

	public void setMax_dra(String max_dra) {
		this.max_dra = max_dra;
	}

	public String getMin_dra() {
		return min_dra;
	}

	public void setMin_dra(String min_dra) {
		this.min_dra = min_dra;
	}

	public String getAvg_dra() {
		return avg_dra;
	}

	public void setAvg_dra(String avg_dra) {
		this.avg_dra = avg_dra;
	}

	public String getWtr_dra() {
		return wtr_dra;
	}

	public void setWtr_dra(String wtr_dra) {
		this.wtr_dra = wtr_dra;
	}

	public String getCde_wtl() {
		return cde_wtl;
	}

	public void setCde_wtl(String cde_wtl) {
		this.cde_wtl = cde_wtl;
	}

	public String getRen_wtl() {
		return ren_wtl;
	}

	public void setRen_wtl(String ren_wtl) {
		this.ren_wtl = ren_wtl;
	}

	public String getSec_h_wtl() {
		return sec_h_wtl;
	}

	public void setSec_h_wtl(String sec_h_wtl) {
		this.sec_h_wtl = sec_h_wtl;
	}

	public String getSec_b_wtl() {
		return sec_b_wtl;
	}

	public void setSec_b_wtl(String sec_b_wtl) {
		this.sec_b_wtl = sec_b_wtl;
	}

	public String getSec_p_wtl() {
		return sec_p_wtl;
	}

	public void setSec_p_wtl(String sec_p_wtl) {
		this.sec_p_wtl = sec_p_wtl;
	}

	public String getStr_wtl() {
		return str_wtl;
	}

	public void setStr_wtl(String str_wtl) {
		this.str_wtl = str_wtl;
	}

	public String getSec_wtl() {
		return sec_wtl;
	}

	public void setSec_wtl(String sec_wtl) {
		this.sec_wtl = sec_wtl;
	}

	public String getStd_wtl() {
		return std_wtl;
	}

	public void setStd_wtl(String std_wtl) {
		this.std_wtl = std_wtl;
	}

	public String getInt_wtl() {
		return int_wtl;
	}

	public void setInt_wtl(String int_wtl) {
		this.int_wtl = int_wtl;
	}

	public String getMai_way() {
		return mai_way;
	}

	public void setMai_way(String mai_way) {
		this.mai_way = mai_way;
	}

	public String getBra_way() {
		return bra_way;
	}

	public void setBra_way(String bra_way) {
		this.bra_way = bra_way;
	}

	public String getChu_way() {
		return chu_way;
	}

	public void setChu_way(String chu_way) {
		this.chu_way = chu_way;
	}

	public String getRac_way() {
		return rac_way;
	}

	public void setRac_way(String rac_way) {
		this.rac_way = rac_way;
	}

	public String getAqu_way() {
		return aqu_way;
	}

	public void setAqu_way(String aqu_way) {
		this.aqu_way = aqu_way;
	}

	public String getDrain_way() {
		return drain_way;
	}

	public void setDrain_way(String drain_way) {
		this.drain_way = drain_way;
	}

	public String getStr_ara() {
		return str_ara;
	}

	public void setStr_ara(String str_ara) {
		this.str_ara = str_ara;
	}

	public String getVis_ara() {
		return vis_ara;
	}

	public void setVis_ara(String vis_ara) {
		this.vis_ara = vis_ara;
	}

	public String getTri_ara() {
		return tri_ara;
	}

	public void setTri_ara(String tri_ara) {
		this.tri_ara = tri_ara;
	}

	public String getOpr_nam() {
		return opr_nam;
	}

	public void setOpr_nam(String opr_nam) {
		this.opr_nam = opr_nam;
	}

	public String getGcn_nam() {
		return gcn_nam;
	}

	public void setGcn_nam(String gcn_nam) {
		this.gcn_nam = gcn_nam;
	}

	public String getNat_amt() {
		return nat_amt;
	}

	public void setNat_amt(String nat_amt) {
		this.nat_amt = nat_amt;
	}

	public String getBnd_amt() {
		return bnd_amt;
	}

	public void setBnd_amt(String bnd_amt) {
		this.bnd_amt = bnd_amt;
	}

	public String getCit_amt() {
		return cit_amt;
	}

	public void setCit_amt(String cit_amt) {
		this.cit_amt = cit_amt;
	}

	public String getSel_amt() {
		return sel_amt;
	}

	public void setSel_amt(String sel_amt) {
		this.sel_amt = sel_amt;
	}

	public String getCet_amt() {
		return cet_amt;
	}

	public void setCet_amt(String cet_amt) {
		this.cet_amt = cet_amt;
	}

	public String getTct_amt() {
		return tct_amt;
	}

	public void setTct_amt(String tct_amt) {
		this.tct_amt = tct_amt;
	}
    public String getFtr_cde() {
		return ftr_cde;
	}

	public void setFtr_cde(String ftr_cde) {
		this.ftr_cde = ftr_cde;
	}


}
