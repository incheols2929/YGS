package geomex.svc.handler;

import geomex.pkg.srch.ConditionSearch;
import geomex.pkg.srch.srchReservoirRecord;
import geomex.svc.webctrl.Const;
import geomex.utils.Utils;
import geomex.utils.db.DBHandler;

import java.util.ArrayList;

import com.itextpdf.text.log.SysoLogger;

public class Code {

	/**
	 * 기능카운트를 가져온다.
	 * 
	 * @return
	 */
	public static ArrayList<String> getFuncList() {
		DBHandler handler = new DBHandler();
		ArrayList<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		// func_id FW로 시작하는 것만 불러온다
		sb.append(" select func_id from mt_func_desc where func_id like 'FW%' order by func_id  ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {
				list.add(Utils.chkNull(handler.getString("func_id")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return list;
	}

	/*
	 * 부서코드를가져온다. 대분류
	 */
	public static ArrayList<ConditionSearch> getDeptCode() {

		DBHandler handler = new DBHandler();
		ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();
		StringBuilder sb = new StringBuilder();

		// sb.append(" select dept_cd, dept_nm, dept_note from mt_dept_desc ");

		sb.append(
				" select dept_cd, dept_nm, shrt_nm from mt_dept_desc where head_DEPT_CD = '4990000' and inuse = '0' and degree = '3' order by dept_cd asc ");

		/// sb.append(" select dept_cd, dept_nm, shrt_nm from mt_dept_desc where
		/// head_DEPT_CD = '4990000' and inuse = '0' and degree = '2' order by
		/// dept_cd asc ");
		/// 완도는 degree가 2로 되어있는게 없음

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {
				ConditionSearch CS = new ConditionSearch();
				CS.dept_cd = Utils.chkNull(handler.getString("dept_cd"));
				CS.dept_nm = Utils.chkNull(handler.getString("dept_nm"));
				// CS.dept_note = Utils.chkNull(handler.getString("dept_note"));
				list.add(CS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return list;
	}

	/*
	 * 부서코드를가져온다. 중분류
	 */
	public static ArrayList<ConditionSearch> getDeptCodeMi(String dept_cd) {

		DBHandler handler = new DBHandler();
		ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();
		StringBuilder sb = new StringBuilder();

		sb.append(
				" select dept_cd, dept_nm, shrt_nm from mt_dept_desc where head_DEPT_CD = '4990000' and inuse = '0' and degree = '4' and parent_dept_cd = '"
						+ dept_cd + "' order by dept_cd asc ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {
				ConditionSearch CS = new ConditionSearch();
				CS.dept_cd = Utils.chkNull(handler.getString("dept_cd"));
				CS.dept_nm = Utils.chkNull(handler.getString("dept_nm"));
				// CS.dept_note = Utils.chkNull(handler.getString("dept_note"));
				list.add(CS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return list;
	}

	/*
	 * 부서코드를가져온다. 소분류
	 */
	public static ArrayList<ConditionSearch> getDeptCodeBo(String dept_cd) {

		DBHandler handler = new DBHandler();
		ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();
		StringBuilder sb = new StringBuilder();

		sb.append(
				" select dept_cd, dept_nm, shrt_nm from mt_dept_desc where head_DEPT_CD = '4990000' and inuse = '0' and degree = '5' and parent_dept_cd = '"
						+ dept_cd + "' order by dept_cd asc ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {
				ConditionSearch CS = new ConditionSearch();
				CS.dept_cd = Utils.chkNull(handler.getString("dept_cd"));
				CS.dept_nm = Utils.chkNull(handler.getString("dept_nm"));
				// CS.dept_note = Utils.chkNull(handler.getString("dept_note"));
				list.add(CS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return list;
	}

	/*
	 * 부서코드의 정보를 like 검색으로 search 한다. 2012-01-19
	 */
	public static ArrayList<ConditionSearch> getDeptSrchTxt(String deptnm) {

		DBHandler handler = new DBHandler();
		ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();
		StringBuilder sb = new StringBuilder();
		sb.append(" select dept_cd, dept_nm from mt_dept_desc where root_dept_cd = '6460000' and dept_nm like '%"
				+ deptnm + "%' and inuse = '0' order by shrt_nm asc  ");
		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {
				ConditionSearch CS = new ConditionSearch();
				CS.dept_cd = Utils.chkNull(handler.getString("dept_cd"));
				CS.dept_nm = Utils.chkNull(handler.getString("dept_nm"));
				// CS.dept_note = Utils.chkNull(handler.getString("dept_note"));
				list.add(CS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return list;
	}

	public static String getFullAddr(String code) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String addr_name = "";

		sb.append(" select  sgg_nm, emd_nm, ri_nm from mt_bjd_cd where bjd_cd = ? ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.setString(1, code);
			handler.execute();
			if (handler.next()) {
				addr_name = Utils.chkNull(handler.getString("sgg_nm")) + " "
						+ Utils.chkNull(handler.getString("emd_nm")) + " " + Utils.chkNull(handler.getString("ri_nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return addr_name;
	}

	// 20120906 추가 도로명에서 dgg_nm, emd_nm을 가지고온다.
	public static String getRoademd(String code) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String addr_name = "";

		sb.append(" select  sgg_nm, emd_nm from mt_bjd_cd where bjd_cd = ? ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.setString(1, code);
			handler.execute();
			if (handler.next()) {
				addr_name = Utils.chkNull(handler.getString("sgg_nm")) + " "
						+ Utils.chkNull(handler.getString("emd_nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return addr_name;
	}

	public static String getFullAddrNew(String code) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String addr_name = "";

		sb.append(" select bjd_nm from mt_bjd_cd where bjd_cd = ? ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.setString(1, code);
			handler.execute();
			if (handler.next()) {
				addr_name = Utils.chkNull(handler.getString("bjd_nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return addr_name;
	}

	// 시군구명을 가지고온다.
	public static String getSGGNM(String code) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String addr_name = "";

		sb.append(" select  sgg_nm from mt_bjd_cd where bjd_cd = ? ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.setString(1, code);
			handler.execute();
			if (handler.next()) {
				if (!"".equals(Utils.chkNull(handler.getString("sgg_nm")))) {
					addr_name = Utils.chkNull(handler.getString("sgg_nm"));
				} else {
					addr_name = "전라남도";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return addr_name;
	}

	// 도로명 주소를 가져온다.
	public static String getDoroAddr(String code) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String doro_name = "";

		sb.append(" select distinct t2.rn as rn, t1.buld_mnnm as buld_mnnm, t1.buld_slno as buld_slno ");
		sb.append(" from v_tl_spbd_buld t1, v_tl_sprd_manage t2 ");
		sb.append(" where t1.rn_cd = t2.rn_cd and t1.rds_man_no = t2.rds_man_no ");
		sb.append(" and 1=1 ");
		sb.append(" and t1.sig_cd||t1.emd_cd||t1.li_cd = '" + code.substring(0, 10) + "' ");
		sb.append(" and case  ");
		sb.append(" when t1.mntn_yn = '1' then '2' ");
		sb.append(" when t1.mntn_yn = '0' then '1' 	 ");
		sb.append(" end = '" + code.substring(10, 11) + "' ");
		sb.append(" and t1.lnbr_mnnm = " + Integer.parseInt(code.substring(11, 15)) + " ");
		sb.append(" and t1.lnbr_slno = " + Integer.parseInt(code.substring(15, 19)) + " ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			// handler.setString(1, code);
			handler.execute();
			if (handler.next()) {
				doro_name += getSGGNM(code.substring(0, 10)) + " " + Utils.chkNull(handler.getString("rn")) + " "
						+ Utils.chkNull(handler.getInt("buld_mnnm"));
				if (Utils.chkNull(handler.getInt("buld_slno")) != 0) {
					doro_name += "-" + Utils.chkNull(handler.getInt("buld_slno"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return doro_name;
	}

	// 토지검색시 도로명주소와 건물명을 가지고온다. 2012-06-26 수정
	public static ArrayList<ConditionSearch> getDoroNewAddr(String dcode) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

		sb.append(" select distinct buld_nm, buld_se_cd from v_tl_spbd_buld where substring(bd_mgt_sn, 1, 19) = '"
				+ dcode + "'  ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();

			while (handler.next()) {
				ConditionSearch cs = new ConditionSearch();
				cs.rd_name = Utils.chkNull(handler.getString("buld_nm"));
				list.add(cs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return list;
	}

	// 건물군의 건물군일련번호를 가지고온다.(지번검색시)
	public static String getRoadeqb_man_snKey(String code) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String eqb_man_sn = "";

		sb.append(" select distinct t1.eqb_man_sn as eqb_man_sn ");
		sb.append(" from v_tl_spbd_buld t1, v_tl_sprd_manage t2 ");
		sb.append(" where 1=1 and t1.rn_cd = t2.rn_cd and t1.rds_man_no = t2.rds_man_no ");
		sb.append(" and t1.sig_cd||t1.emd_cd||t1.li_cd = '" + code.substring(0, 10) + "' ");
		sb.append(" and case  ");
		sb.append(" when t1.mntn_yn = '1' then '2' ");
		sb.append(" when t1.mntn_yn = '0' then '1' 	 ");
		sb.append(" end = '" + code.substring(10, 11) + "' ");
		sb.append(" and t1.lnbr_mnnm = " + Integer.parseInt(code.substring(11, 15)) + " ");
		sb.append(" and t1.lnbr_slno = " + Integer.parseInt(code.substring(15, 19)) + " ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			// handler.setString(1, code);
			handler.execute();
			if (handler.next()) {
				eqb_man_sn = Utils.chkNull(handler.getString("eqb_man_sn"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return eqb_man_sn;
	}

	// 건물군의 건물군일련번호를 가지고온다.(지번검색시)
	public static String getRoadGidKey(String code) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String gid = "";

		sb.append(" select distinct t1._gid as gid ");
		sb.append(" from v_tl_spbd_buld t1, v_tl_sprd_manage t2 ");
		sb.append(" where t1.rn_cd = t2.rn_cd and t1.rds_man_no = t2.rds_man_no ");
		sb.append(" and 1=1 ");
		sb.append(" and t1.sig_cd||t1.emd_cd||t1.li_cd = '" + code.substring(0, 10) + "' ");
		sb.append(" and case  ");
		sb.append(" when t1.mntn_yn = '1' then '2' ");
		sb.append(" when t1.mntn_yn = '0' then '1' 	 ");
		sb.append(" end = '" + code.substring(10, 11) + "' ");
		sb.append(" and t1.lnbr_mnnm = " + Integer.parseInt(code.substring(11, 15)) + " ");
		sb.append(" and t1.lnbr_slno = " + Integer.parseInt(code.substring(15, 19)) + " ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			// handler.setString(1, code);
			handler.execute();
			if (handler.next()) {
				gid = Utils.chkNull(handler.getString("gid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return gid;
	}

	// 건물군의 건물군일련번호를 가지고온다.(지번검색시)
	public static String getEqbGidKey(String sgg, String code) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String gid = "";
		// KAIS건물군 테이블에서 시군구코드(SIG_CD)를 찾는다 건물군일련번호까지
		sb.append(" select _gid from tl_spbd_eqb where sig_cd = '" + sgg + "' and eqb_man_sn = '" + code + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			// handler.setString(1, code);
			handler.execute();
			if (handler.next()) {
				gid = Utils.chkNull(handler.getString("_gid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return gid;
	}

	// 시설물 테이블 이름으로 시설물 종류 이름을 찾음
	public static String getTableCode(String facility) {
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String lyrnm = "";

		sb.append("select g2_code as code, g2_value as tables from g2s_codeddomains where 1=1 and g2_value1 ='"
				+ facility + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				lyrnm = Utils.chkNull(handler.getString("tables"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return lyrnm;
	}

	// 시설물의 시설물 일련번호를 가져온다.
	public static String getFacilityKeyVal(String facility, String FTR_IDN, String MNG_CDE, String PNU, String FTR_NM) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String key = "";
		// KAIS건물 테이블에서 _gid를 이용해서 찾음
		sb.append(" select _gid from " + facility + "   ");
		sb.append(" where 1=1 ");
		sb.append(" and FTR_IDN = '" + FTR_IDN + "' ");// 산여부
		sb.append(" and MNG_CDE = '" + MNG_CDE + "' ");// 지번본번
		sb.append(" and PNU = '" + PNU + "' ");// 지번부번
		sb.append(" and FTR_NM = '" + FTR_NM + "' ");// 도로명 코드

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				key = Utils.chkNull(handler.getString("_gid"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return key;
	}

	//// 건물군의 건물군일련번호를 가지고온다.(도로명주소검색시 사용)
	public static String getRoadKeyVal(String sig_cd, String buld_mnnm, String buld_slno, String san, String bon,
			String bu, String rn_cd) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String key = "";
		// KAIS건물 테이블에서 _gid를 이용해서 찾음
		sb.append(" select _gid from v_tl_spbd_buld ");
		sb.append(" where 1=1 ");
		sb.append(" and sig_cd = '" + sig_cd + "' ");// 시군구코드
		sb.append(" and buld_mnnm = '" + buld_mnnm + "' ");// 건물본번
		sb.append(" and buld_slno = '" + buld_slno + "' ");// 건물부번
		sb.append(" and mntn_yn = '" + san + "' ");// 산여부
		sb.append(" and lnbr_mnnm = '" + bon + "' ");// 지번본번
		sb.append(" and lnbr_slno = '" + bu + "' ");// 지번부번
		sb.append(" and rn_cd = '" + rn_cd + "' ");// 도로명 코드

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				key = Utils.chkNull(handler.getString("_gid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return key;
	}

	// 건물군의 건물명을 가지고온다.
	public static String getBuldNM(String sig_cd, String eqb_man_no) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String buldnm = "";

		sb.append(" select eqb_nm from tl_spbd_eqb where sig_cd = '" + sig_cd + "' and eqb_man_sn = '" + eqb_man_no
				+ "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				buldnm = Utils.chkNull(handler.getString("eqb_nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return buldnm;
	}

	// 건물명을 가져온다.
	public static String getBuldName(String code) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String blud_name = "";

		sb.append(" select distinct t1.buld_nm as buld_nm ");
		sb.append(" from v_tl_spbd_buld t1, v_tl_sprd_manage t2 ");
		sb.append(" where t1.rn_cd = t2.rn_cd and t1.rds_man_no = t2.rds_man_no ");
		sb.append(" and 1=1 ");
		sb.append(" and t1.sig_cd||t1.emd_cd||t1.li_cd = '" + code.substring(0, 10) + "' ");
		sb.append(" and case  ");
		sb.append(" when t1.mntn_yn = '1' then '2' ");
		sb.append(" when t1.mntn_yn = '0' then '1' 	 ");
		sb.append(" end = '" + code.substring(10, 11) + "' ");
		sb.append(" and t1.lnbr_mnnm = " + Integer.parseInt(code.substring(11, 15)) + " ");
		sb.append(" and t1.lnbr_slno = " + Integer.parseInt(code.substring(15, 19)) + " ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			// handler.setString(1, code);
			handler.execute();
			if (handler.next()) {
				blud_name = Utils.chkNull(handler.getString("buld_nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		if (!"".equals(blud_name)) {
			return blud_name;
		} else {
			return "-";
		}
	}

	// pnu코드 생성
	public static String getCreatePNU(String dcode, String san, String bon, String bu) {

		String pnu = "";
		int bonlen, bulen;

		if ("0".equals(san)) {
			san = "1";
		} else {
			san = "2";
		}

		bonlen = (4 - bon.length());
		bulen = (4 - bu.length());

		for (int i = 0; i < bonlen; i++) {
			bon = "0" + bon;
		}
		for (int i = 0; i < bulen; i++) {
			bu = "0" + bu;
		}

		pnu = dcode + "" + san + "" + bon + "" + bu;

		return pnu;
	}

	// 레이어관리ID를 가지고 테이블ID를 가져온다.
	public static String getLyrTBL(String lyr_id) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String tbl_id = "";

		sb.append(" select tbl_id from mt_lyr_desc where lyr_id = '" + lyr_id + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				tbl_id = Utils.chkNull(handler.getString("tbl_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return tbl_id;
	}

	// 레이어명을 가지고 테이블 이름을 자지고온다.
	public static String getLyrNM(String lyr_nm) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String tbl_id = "";

		sb.append(" select tbl_id from mt_lyr_desc where lyr_nm = '" + lyr_nm + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				tbl_id = Utils.chkNull(handler.getString("tbl_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return tbl_id;
	}

	// 레이어명을 가지고 where_txt 필드를 가지고 온다.
	public static String getWhereTxt(String lyr_nm) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String where_txt = "";

		sb.append(" select where_txt from mt_lyr_desc where lyr_nm = '" + lyr_nm + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				where_txt = Utils.chkNull(handler.getString("where_txt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return where_txt;
	}

	// 레이어명을 가지고 where_txt 칼럼을 가지고 온다.
	public static String getLyrWhereTxt(String lyr_nm) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String wtxt = "";

		sb.append(" select where_txt from mt_lyr_desc where lyr_nm = '" + lyr_nm + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				wtxt = Utils.chkNull(handler.getString("where_txt")).toLowerCase();
				wtxt = wtxt.replaceAll("ulyr='", "");
				wtxt = wtxt.replaceAll("'", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return wtxt;
	}

	// 레이어명을 가지고 레이어관리 아이디를 가지고온다.
	public static String getLyrID(String lyr_nm) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String lyr_id = "";

		sb.append(" select lyr_id from mt_lyr_desc where lyr_nm = '" + lyr_nm + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				lyr_id = Utils.chkNull(handler.getString("lyr_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return lyr_id;
	}

	// mt_tbl_cols테이블에서 해당테이블의 키값을 가져온다.
	public static String getTblkey(String tbl_id) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String key = "";

		sb.append(" select col_nm from mt_tbl_cols where 1=1 and tbl_id = '" + tbl_id + "' and key_yn = 'Y' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				key = Utils.chkNull(handler.getString("col_nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return key;
	}

	// 건축물대장 _대지_구분_코드
	public static String getPLATCD(String PLAT_GB_CD) {

		String gb_cd = "";

		if ("1".equals(PLAT_GB_CD)) {
			gb_cd = "1";
		} else if ("2".equals(PLAT_GB_CD)) {
			gb_cd = "2";
		} else if ("3".equals(PLAT_GB_CD)) {
			gb_cd = "3";
		}
		return gb_cd;
	}

	// 건축물대장의 중간자리 고유번호
	public static String geGoyouMiNum(String ADM_DIST_CODE, String LAND_SITE_CODE, String LG_GBN, String BO_JIBN,
			String BU_JIBN) {

		String gomi = "";
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT DISTINCT PLAT_GB_CD, REGSTR_GB_CD FROM V_DJYBLDRGST_GIS  WHERE 1=1 ");
		sb.append(" AND SIGUNGU_CD = '" + ADM_DIST_CODE + "' ");
		sb.append(" AND BJDONG_CD = '" + LAND_SITE_CODE + "' ");
		sb.append(" AND BUN = '" + BO_JIBN + "' ");
		sb.append(" AND JI = '" + BU_JIBN + "' ");
		sb.append(" AND REGSTR_KIND_CD IN('1','2','3')  ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {

				String REGSTR_GB_CD = Utils.chkNull(handler.getString("REGSTR_GB_CD")); // 일반1,
																						// 집합2
				String PLAT_GB_CD = Utils.chkNull(handler.getString("PLAT_GB_CD"));

				if ("1".equals(REGSTR_GB_CD)) { // 일반
					if ("0".equals(PLAT_GB_CD)) {
						// 대지일경우
						gomi = "1";
					} else if ("1".equals(PLAT_GB_CD)) {
						// 산일경우
						gomi = "2";
					}
				} else if ("2".equals(REGSTR_GB_CD)) { // 집합
					if ("0".equals(PLAT_GB_CD)) {
						// 대지일경우
						gomi = "3";
					} else if ("1".equals(PLAT_GB_CD)) {
						// 산일경우
						gomi = "4";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return gomi;

	}

	// SELECT CS_NM FROM V_CMPCOMMCDMGM WHERE SGRP_CD = '15000' AND LGRP_CD =
	// 'CM024'
	// 주용도코드 이름을 가지고온다.
	public static String getMAIN_PURPS_CD(String SGRP_CD) {

		String CD_NM = "";
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT CD_NM FROM V_CMPCOMMCDMGM WHERE SGRP_CD = '" + SGRP_CD + "'  AND LGRP_CD = 'CM024' ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CD_NM = Utils.chkNull(handler.getString("CD_NM"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return CD_NM;
	}

	/*
	 * 대장 구분코드(이름) 가져온다. / 대장 종류 코드를 가지고온다. 주_부속명을 가지고온다.
	 */

	public static String getGBCD(String GBCD, String LC) {

		String GBNM = "";
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT CD_NM FROM V_CMPCOMMCDMGM WHERE SGRP_CD = '" + GBCD + "'  AND LGRP_CD = '" + LC + "' ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				GBNM = Utils.chkNull(handler.getString("CD_NM"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return GBNM;
	}

	/*
	 * 표제부및 일반건축물의 주_부속건물 명을 가져온다.
	 */

	public static String getMAINATCHGB(String CD) {

		String CHGB = "";
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("  SELECT MAIN_ATCH_GB_CD FROM V_DJYTITLE_GIS  WHERE MGM_BLDRGST_PK = '" + CD + "' ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CHGB = Code.getGBCD(Utils.chkNull(handler.getString("MAIN_ATCH_GB_CD")), "CM031");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return CHGB;
	}

	/*
	 * 총괄표제부 동번호 및 번호를 가져온다.
	 */
	public static String getDjyDONGNM(String CD) {

		String CHGB = "";
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("  SELECT BLD_NM FROM V_DJYBLDRGST_GIS WHERE MGM_BLDRGST_PK = '" + CD + "' ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CHGB = Utils.chkNull(handler.getString("BLD_NM"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return CHGB;
	}

	/*
	 * 동번호 및 번호를 가져온다.
	 */
	public static String getDONGNM(String CD) {

		String CHGB = "";
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("  SELECT DONG_NM FROM V_DJYTITLE_GIS  WHERE MGM_BLDRGST_PK = '" + CD + "' ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CHGB = Utils.chkNull(handler.getString("DONG_NM"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return CHGB;
	}

	/*
	 * 총괄표제부 주용도코드를 가지고 와서 명칭으로보여준다.
	 */
	public static String getDjyPURPSNM(String CD) {

		String CHGB = "";
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("  SELECT MAIN_PURPS_CD   FROM V_DJYRECAPTITLE_GIS  WHERE MGM_BLDRGST_PK = '" + CD + "' ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CHGB = Code.getGBCD(Utils.chkNull(handler.getString("MAIN_PURPS_CD")), "CM024");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return CHGB;
	}

	/*
	 * 주용도코드를 가지고 와서 명칭으로보여준다.
	 */
	public static String getPURPSNM(String CD) {

		String CHGB = "";
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("  SELECT MAIN_PURPS_CD   FROM V_DJYTITLE_GIS  WHERE MGM_BLDRGST_PK = '" + CD + "' ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CHGB = Code.getGBCD(Utils.chkNull(handler.getString("MAIN_PURPS_CD")), "CM024");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return CHGB;
	}

	/*
	 * 총괄표제부테이블에서 연면적을 가져온다.
	 */
	public static double getDjyTOTAREA(String CD) {

		double CHGB = 0;
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("  SELECT TOTAREA   FROM V_DJYRECAPTITLE_GIS  WHERE MGM_BLDRGST_PK = '" + CD + "' ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CHGB = handler.getDouble("TOTAREA");
				;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return CHGB;
	}

	/*
	 * 표제부테이블에서 연면적을 가져온다.
	 */
	public static double getTOTAREA(String CD) {

		double CHGB = 0;
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("  SELECT TOTAREA   FROM V_DJYTITLE_GIS  WHERE MGM_BLDRGST_PK = '" + CD + "' ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CHGB = handler.getDouble("TOTAREA");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return CHGB;
	}

	/*
	 * 전유부가 있는지 없는지 카운트 갯수를 가져온다.
	 */
	public static boolean getDJYEXPOSCNT(String CD) {

		boolean bool = false;
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(
				"  SELECT COUNT(*) AS CNT FROM V_DJYBLDRGST_GIS WHERE  REGSTR_KIND_CD = '4' AND MGM_UPPER_BLDRGST_PK = '"
						+ CD + "'  ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				if (Utils.chkNull(handler.getInt("CNT")) != 0) {
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
	 * 전유부의 호명칭을 가지고온다.
	 */
	public static String getDJYEXPOS_honm(String CD) {

		String HONM = "";
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT  * FROM V_DJYEXPOS_GIS WHERE MGM_BLDRGST_PK = '" + CD + "' ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				HONM = Utils.chkNull(handler.getString("HO_NM"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return HONM;
	}

	/*
	 * 전유부인지 일반건축물인지를 가지고온다.
	 */
	public static String getKIND_name(String key) {

		String kindname = "";
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("  SELECT REGSTR_KIND_CD FROM V_DJYBLDRGST_GIS WHERE  MGM_BLDRGST_PK = '" + key + "' ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				if ("2".equals(Utils.chkNull(handler.getString("REGSTR_KIND_CD")))) {
					kindname = "일반건축물";
				} else if ("3".equals(Utils.chkNull(handler.getString("REGSTR_KIND_CD")))) {
					kindname = "표제부";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return kindname;
	}

	/*
	 * 전유부의 층_번호를 가지고온다.
	 */
	public static String getDJYEXPOS_FLR_NO(String CD) {

		String FLR_NO = "";
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT FLR_NO FROM V_DJYEXPOS_GIS WHERE MGM_BLDRGST_PK = '" + CD + "' ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				FLR_NO = Utils.chkNull(handler.getString("FLR_NO"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return FLR_NO;
	}

	/*
	 * 연월일을 나눈다.
	 */
	public static String getYEAR(String year) {

		String yeartxt = "";

		if (year.length() == 8) {
			yeartxt = year.substring(0, 4) + "월 " + year.substring(4, 6) + "월 " + year.substring(6, 8) + "일";
		} else {
			yeartxt = year;
		}

		return yeartxt;
	}

	// pnu를 가지고 주소를 만든다.
	public static String getAddrcreate(String pnu) {

		String addr = "";
		// select 법정동명 from 법정동코드 where 법정동코드 = ''

		// int pbool = getPnuSearch(pnu);

		String dcode = pnu.substring(0, 10);
		String san = pnu.substring(10, 11);
		int bon = Integer.parseInt(pnu.substring(11, 15));
		int bu = Integer.parseInt(pnu.substring(15, 19));

		if (!"1".equals(san)) {
			san = "산";
		} else {
			san = "";
		}

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" select bjd_nm from mt_bjd_cd where bjd_cd =  '" + dcode + "' ");

		try {
			// handler.open("eais");
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				addr += Utils.chkNull(handler.getString("bjd_nm"));
				addr += " " + san;
				addr += " " + bon;
				if (bu != 0) {
					addr += " - " + bu;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return addr;
	}

	// pnu값이 있는지 없는지 존재여부 확인
	public static boolean getPnuSearch(String pnu) {

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		boolean bool = false;

		sb.append(" select pnu from lp_pa_cbnd where pnu = '" + pnu + "' ");

		try {
			// handler.open("eais");
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				if (pnu == Utils.chkNull(handler.getString("pnu"))) {
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

	// 대지위치 이름을 가지고온다.
	public static String getDaejiPosition(String key) {

		String position = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT SIGUNGU_CD, BJDONG_CD, PLAT_GB_CD, BUN, JI FROM V_DJYBLDRGST_GIS WHERE MGM_BLDRGST_PK = '"
				+ key + "' ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				position += getCMPBJDONGMGM(Utils.chkNull(handler.getString("SIGUNGU_CD")),
						Utils.chkNull(handler.getString("BJDONG_CD")));
				position += " " + Code.getGBCD(Utils.chkNull(handler.getString("PLAT_GB_CD")), "CM008");
				;
				position += " " + Integer.parseInt(Utils.chkNull(handler.getString("BUN")));
				if (Integer.parseInt(Utils.chkNull(handler.getString("JI"))) != 0) {
					position += " - " + Integer.parseInt(Utils.chkNull(handler.getString("JI")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return position;
	}

	// 인쇄용 대지위치
	public static String getDaejiPositionPrint(String key) {

		String position = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT SIGUNGU_CD, BJDONG_CD, PLAT_GB_CD, BUN, JI FROM V_DJYBLDRGST_GIS WHERE MGM_BLDRGST_PK = '"
				+ key + "' ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				position = getCMPBJDONGMGM(Utils.chkNull(handler.getString("SIGUNGU_CD")),
						Utils.chkNull(handler.getString("BJDONG_CD")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return position;
	}

	// 인쇄용 지번 및 필지
	public static String getDaejiJibun(String key) {

		String position = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT BUN, JI, BYLOT_CNT  FROM V_DJYBLDRGST_GIS WHERE MGM_BLDRGST_PK = '" + key + "' ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				position += " " + Integer.parseInt(Utils.chkNull(handler.getString("BUN")));
				if (Integer.parseInt(Utils.chkNull(handler.getString("JI"))) != 0) {
					position += " - " + Integer.parseInt(Utils.chkNull(handler.getString("JI")));
				}
				position += "외 " + Utils.chkNull(handler.getInt("BYLOT_CNT")) + " 필지";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return position;
	}

	// 법정동 코드 위치에서 시군구명과 법정동명을 가지고온다.
	public static String getCMPBJDONGMGM(String sigungu_cd, String bjdong_cd) {

		String SBNM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT SIGUNGU_NM, BJDONG_NM FROM V_CMPBJDONGMGM WHERE SIGUNGU_CD = '" + sigungu_cd
				+ "' AND BJDONG_CD = '" + bjdong_cd + "'  ");

		try {
			handler.open("eais");
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {

				SBNM = Utils.chkNull(handler.getString("SIGUNGU_NM")) + " "
						+ Utils.chkNull(handler.getString("BJDONG_NM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return SBNM;
	}

	// 토지이용계획의 국토의 계획 및 이용에 관한 법률에 따른 지역지구명을 가지고온다.
	public static String getMtuznelyrnm(String ucode) {

		String uzne_nm = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		// sb.append(" select uzne_nm from mt_uzne_cd where uzne_cd = '" + ucode
		// + "' and lyr_cd = 'UQ111' ");
		sb.append("  select uzne_nm  from mt_uzne_cd  where uzne_cd = '" + ucode + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				uzne_nm = Utils.chkNull(handler.getString("uzne_nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return uzne_nm;
	}

	// 법령명
	public static String getLAW_NM(String ucode) {

		String law_nm = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("  select law_nm  from mt_uzne_cd  where uzne_cd = '" + ucode + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				law_nm = Utils.chkNull(handler.getString("law_nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return law_nm;
	}

	/*
	 * 연속지적의 발급승인코드를 가지고 와서 해당명칭으로 바꾼후 리턴시킨다.
	 */
	public static String getBchknm(String pnu) {

		String bchk_nm = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" select bchk from lp_pa_cbnd where pnu = '" + pnu + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				String bchk_cd = Utils.chkNull(handler.getString("bchk"));
				if ("0".equals(bchk_cd)) {
					bchk_nm = "미승인";
				} else if ("1".equals(bchk_cd)) {
					bchk_nm = "승인";
				} else if ("2".equals(bchk_cd)) {
					bchk_nm = "속성승인";
				} else if ("3".equals(bchk_cd)) {
					bchk_nm = "불승인";
				} else if ("4".equals(bchk_cd)) {
					bchk_nm = "도면승인";
				} else if ("5".equals(bchk_cd)) {
					bchk_nm = "기존승인";
				} else if ("8".equals(bchk_cd)) {
					bchk_nm = "미승인(이동정리미반영)";
				} else if ("9".equals(bchk_cd)) {
					bchk_nm = "미승인(이동정리반영)";
				} else if ("".equals(bchk_cd)) {
					bchk_nm = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return bchk_nm;
	}

	/****************************************************************************************************************************
	 * 사용자 정보관련
	 **************************************************************************************************************************/
	// 부서명
	public static String getDeptNM(String dept_code) {

		String dept_nm = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		// 찾고자 하는 부서를 찾기위해
		sb.append(" select dept_nm from mt_dept_desc where dept_cd = '" + dept_code + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				dept_nm = Utils.chkNull(handler.getString("dept_nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return dept_nm;
	}

	/****************************************************************************************************************************
	 * 통합검색에 필요한 코드를 가지고온다. 2011-12-15
	 **************************************************************************************************************************/

	// 지목코드를 가지고온다.
	public static ArrayList<ConditionSearch> getJimokCode() {
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

		sb.append(" select det_cd, det_nm from mt_cde_tbl where gbn_cd = 'JIMOK' order by det_nm asc ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {
				ConditionSearch sm = new ConditionSearch();
				sm.code = handler.getString("det_cd");
				sm.value = handler.getString("det_nm");
				list.add(sm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return list;

	}

	// 지목코드를 가지고온다.
	public static String getJimokCode(String jimok) {
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String jimoknm = "";

		sb.append(" select det_nm from mt_cde_tbl where gbn_cd = 'JIMOK' and det_cd = '" + jimok + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				jimoknm = Utils.chkNull(handler.getString("det_nm")); // 건물명
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return jimoknm;

	}

	// 소유자구분코드를 가지고온다.
	public static ArrayList<ConditionSearch> getOwngbCode() {
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

		sb.append(" select det_cd, det_nm from mt_cde_tbl where gbn_cd = 'OWNGB' order by det_nm asc ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {
				ConditionSearch sm = new ConditionSearch();
				sm.code = handler.getString("det_cd");
				sm.value = handler.getString("det_nm");
				list.add(sm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return list;

	}

	// 공시지가 년도를 가지고온다.
	public static ArrayList<ConditionSearch> getBaseYear() {
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

		sb.append(
				" select distinct base_year from anvm_jiga where  cast(base_year as numeric) >= 2005 order by base_year desc ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {
				ConditionSearch sm = new ConditionSearch();
				sm.code = handler.getString("base_year");
				sm.value = handler.getString("base_year");
				list.add(sm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return list;

	}

	// 용도지역지구 대분류
	public static ArrayList<ConditionSearch> getUzneCodeDae() {
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

		sb.append(" select law_gbn from mt_uzne_cd group by law_gbn order by law_gbn asc ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {
				ConditionSearch sm = new ConditionSearch();
				sm.code = handler.getString("law_gbn");
				sm.value = handler.getString("law_gbn");
				list.add(sm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return list;

	}

	// 용도지역지구 중분류
	public static ArrayList<ConditionSearch> getUzneCodeJoong(String law_gbn) {
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

		sb.append(" select lyr_cd, lyr_nm from mt_uzne_cd where law_gbn = '" + law_gbn
				+ "' group by lyr_cd, lyr_nm order by lyr_nm asc ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {
				ConditionSearch sm = new ConditionSearch();
				sm.code = handler.getString("lyr_cd");
				sm.value = handler.getString("lyr_nm");
				list.add(sm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return list;

	}

	// 용도지역지구 소분류
	public static ArrayList<ConditionSearch> getUzneCodeSo(String lyr_cd) {
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

		sb.append(" select uzne_cd, uzne_nm from mt_uzne_cd where lyr_cd = '" + lyr_cd + "' order by uzne_nm asc ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {
				ConditionSearch sm = new ConditionSearch();
				sm.code = handler.getString("uzne_cd");
				sm.value = handler.getString("uzne_nm");
				list.add(sm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return list;

	}

	// 토지대장 대장구분
	public static String getLGGBN(String lggbncode) {

		String lggbn = "";

		if ("1".equals(lggbncode)) {
			lggbn = "토지";
		} else if ("8".equals(lggbncode)) {
			lggbn = "토지패쇄";
		} else if ("D".equals(lggbncode)) {
			lggbn = "토지부책";
		} else if ("2".equals(lggbncode)) {
			lggbn = "임야";
		} else if ("9".equals(lggbncode)) {
			lggbn = "임야패쇄";
		} else if ("E".equals(lggbncode)) {
			lggbn = "임야부책";
		}

		return lggbn;
	}

	// 시스템명을 가지고온다.
	public static String getSysNm(String sys) {
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String sysnm = "";

		sb.append(" select det_nm from mt_cde_tbl where gbn_cd = 'SYS' and det_cd = '" + sys + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				sysnm = Utils.chkNull(handler.getString("det_nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return sysnm;

	}

	/*****************************************************************************
	 * 
	 * 업무기능 권한체크 2011-12-27
	 * 
	 *****************************************************************************/
	public static String getAuthCheck(String func_id, String id, String session_ugrp_id) {
		DBHandler handler = new DBHandler();// DBHandler객체 생성
		StringBuilder sb = new StringBuilder(); // 문자열저장 및 변경하기위해
												// StringBuilder객체 생성
		String auth = "N";

		sb.append("select func_id, max(func_nm) as func_nm, max(r_auth_yn) as r_auth_yn from (                  ");
		sb.append("select fnc.func_id,fnc.func_nm,auth.r_auth_yn   from mt_func_desc fnc, mt_ugrp_auth auth ");
		sb.append("where auth.ugrp_id='" + session_ugrp_id + "'                                                 ");
		sb.append("and fnc.func_id = auth.auth_id                                                           ");
		sb.append("and auth.auth_typ='F'                                                                    ");
		sb.append("and fnc.func_id='" + func_id + "'                                                            ");
		sb.append("and auth.r_auth_yn='Y'                                                                   ");
		sb.append("union all                                                                                    ");
		sb.append("select fnc.func_id,fnc.func_nm,auth.r_auth_yn                                            ");
		sb.append("from mt_func_desc fnc, mt_usr_auth auth                                                  ");
		sb.append("where auth.usr_id='" + id + "'                                                               ");
		sb.append("and fnc.func_id = auth.auth_id                                                           ");
		sb.append("and auth.auth_typ='F'                                                                    ");
		sb.append("and fnc.func_id='" + func_id + "'                                                            ");
		sb.append("and auth.r_auth_yn='Y'                                                                   ");
		sb.append(") C                                                                                          ");
		sb.append("where r_auth_yn = 'Y'                                                                        ");
		sb.append("group by func_id                                                    							");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {
				String r_auth_yn = Utils.chkNull(handler.getString("r_auth_yn"));
				// String auth_gubun =
				// Utils.chkNull(handler.getString("auth_gubun"));
				auth = r_auth_yn; // 조회권한auth
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return auth;
	}

	public static String getAuthCheckL(String lyr_id, String id, String session_ugrp_id) {
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String auth = "N";

		sb.append(
				" select r_auth_yn from  (select cast(ugrp_id as varchar(10)) ,auth_id,grp_nm,auth_gubun, r_auth_yn from ( ");
		sb.append(" select ugrp_id,auth_id,grp_nm, auth_typ,'G' as auth_gubun, r_auth_yn  ");
		sb.append(" from mt_ugrp_auth a  ");
		sb.append(" inner join mt_lyr_grp b on a.auth_id = b.grp_id ");
		sb.append(" where a.auth_typ ='L' and a.ugrp_id= '" + session_ugrp_id + "' and a.r_auth_yn = 'Y' ");
		sb.append(" UNION ALL ");
		sb.append(" select usr_id,auth_id,grp_nm, auth_typ,'U' as auth_gubun, r_auth_yn ");
		sb.append(" from mt_usr_auth a  ");
		sb.append(" inner join mt_lyr_grp b on a.auth_id = b.grp_id ");
		sb.append(" where a.auth_typ ='L' and a.usr_id='" + id + "' and a.r_auth_yn = 'Y' ");
		sb.append(" ) c order by auth_gubun desc) d inner join mt_lyr_desc e on d.auth_id=e.grp_id ");
		sb.append(" where ");
		// .sb.append(" ugrp_id='"+id+"' ");
		sb.append(" e.lyr_id='" + lyr_id + "' ");
		// sb.append(" and e.lyr_typ in ('MA','ML','SP') ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {
				String r_auth_yn = Utils.chkNull(handler.getString("r_auth_yn"));
				auth = r_auth_yn;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return auth;
	}

	// 주소로 치고올 경우 권한 체크 2012-02-10 농업기반시설물 권한 체크
	public static String getAuthCheckB2(String bizid, String id, String session_ugrp_id) {
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String auth = "N";// 권한 초기 값

		// MT_UGRP_AUTH에서 계정정보를 가지고 권한테이블의 값을 찾음
		sb.append(" select distinct r_auth_yn, auth_id  from( ");
		sb.append(" select r_auth_yn, auth_id from mt_ugrp_auth where ugrp_id='" + session_ugrp_id
				+ "' and auth_typ = 'B' and auth_id = '" + bizid + "' ");
		sb.append(" union all ");
		sb.append(" select r_auth_yn, auth_id from mt_usr_auth where usr_id = '" + id
				+ "' and auth_typ = 'B' and auth_id = '" + bizid + "' ");
		sb.append(" ) A where r_auth_yn = 'Y' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {

				String r_auth_yn = Utils.chkNull(handler.getString("r_auth_yn"));
				// r_auth_yn에 들어있는 값이 Y인지 찾음 찾으면 "Y"와 비교하여 같으면 auth에 넣어줌
				if ("Y".equals(r_auth_yn)) {
					auth = r_auth_yn;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return auth;
	}

	// 행정지원시스템 권한체크
	public static String getAuthCheckB(String bizid, String id, String session_ugrp_id) {
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		String auth = "N";

		sb.append(" select distinct r_auth_yn  from( ");
		sb.append(" select r_auth_yn from mt_ugrp_auth where ugrp_id='" + session_ugrp_id
				+ "' and auth_typ = 'B'  and auth_id = '" + bizid + "' ");
		sb.append(" union all ");
		sb.append(" select r_auth_yn from mt_usr_auth where usr_id = '" + id + "' and auth_typ = 'B' and auth_id = '"
				+ bizid + "' ");
		sb.append(" ) A where r_auth_yn = 'Y' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {
				String r_auth_yn = Utils.chkNull(handler.getString("r_auth_yn"));

				// r_auth_yn에 들어있는 값이 Y인지 찾음 찾으면 "Y"와 비교하여 같으면 auth에 넣어줌
				if ("Y".equals(r_auth_yn)) {
					auth = r_auth_yn;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return auth;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 토지대장 pdf 고유번호샌성
	public static String getInherentNum(String pnu) {
		String IN = "";

		String dcode = pnu.substring(0, 10);
		String code_1 = pnu.substring(10, 15);
		String code_2 = pnu.substring(15, 19);

		IN = dcode + "-" + code_1 + "-" + code_2;

		return IN;

	}

	// 토지대장 pnu 지번 생성
	public static String getJibunCreate(String pnu) {
		String IN = "";

		String code_1 = pnu.substring(11, 15);
		String code_2 = pnu.substring(15, 19);

		IN = code_1 + "-" + code_2;

		return IN;

	}

	// pdf 토지소재지를 만든다.
	public static String getTojiAddrCreate(String pnu) {

		String addr = "";

		String dcode = pnu.substring(0, 10);

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" select bjd_nm from mt_bjd_cd where bjd_cd =  '" + dcode + "' ");

		try {
			// handler.open("eais");
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				addr = Utils.chkNull(handler.getString("bjd_nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return addr;
	}

	// _gmx_style의 초기스타일을 가지고온다.
	public static String getGeovletStyle() {

		String GS = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" select _contents from _gmx_style where _owner = '_geovlet' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				GS = Utils.chkNull(handler.getString("_contents"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return GS;
	}

	// _gmx_style의 사용자의 타이틀 명이 같은지 찾는다.
	public static String getStyleTitle(String user, String title) {

		String GS = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" select _title from _gmx_style where _owner = '" + user + "' and _title = '" + title + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {

				if (title.equals(Utils.chkNull(handler.getString("_title")))) {
					GS = "T";
				} else {
					GS = "F";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return GS;
	}

	// 저수지 시설물명 에 대한 인쇄 작업
	public static String getReservoirNmPositionPrint(String ftridn) {

		String ftrnm = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				ftrnm = Utils.chkNull(handler.getString("FTR_NM"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return ftrnm;
	}

	// 저수지 시설물 주소 에 대한 인쇄 작업
	public static String getReservoirJusoPositionPrint(String ftridn) {

		String juso = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				juso = Utils.chkNull(handler.getString("JUSO"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return juso;
	}

	// 저수지 시설물 주소 에 대한 인쇄 작업
	public static String getReservoirMNGPositionPrint(String ftridn) {

		String mngcde = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select g2_value ");
		sb.append("from g2s_codeddomains  ");
		sb.append("where g2_code IN( ");
		sb.append("select mng_cde from ag_reservoir_as ");
		sb.append("where  FTR_IDN = '" + ftridn + "') ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				mngcde = Utils.chkNull(handler.getString("g2_value"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return mngcde;
	}

	// 양수장 시설물 주소 에 대한 인쇄 작업
	public static String getPumpMNGPositionPrint(String ftridn) {

		String mngcde = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select g2_value ");
		sb.append("from g2s_codeddomains  ");
		sb.append("where g2_code IN( ");
		sb.append("select mng_cde from ag_pump_ps ");
		sb.append("where  FTR_IDN = '" + ftridn + "') ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				mngcde = Utils.chkNull(handler.getString("g2_value"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return mngcde;
	}
    //관정
	public static String getTbwMNGPositionPrint(String ftridn) {

		String mngcde = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select g2_value ");
		sb.append("from g2s_codeddomains  ");
		sb.append("where g2_code IN( ");
		sb.append("select mng_cde from ag_tbw_ps ");
		sb.append("where  FTR_IDN = '" + ftridn + "') ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				mngcde = Utils.chkNull(handler.getString("g2_value"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return mngcde;
	}
	//방조제
	public static String getSeawallMNGPositionPrint(String ftridn) {

		String mngcde = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select g2_value ");
		sb.append("from g2s_codeddomains  ");
		sb.append("where g2_code IN( ");
		sb.append("select mng_cde from ag_seawall_ps ");
		sb.append("where  FTR_IDN = '" + ftridn + "') ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				mngcde = Utils.chkNull(handler.getString("g2_value"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return mngcde;
	}
	// 배수장 ag_drain_ps
	public static String getDrainMNGPositionPrint(String ftridn) {

		String mngcde = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select g2_value ");
		sb.append("from g2s_codeddomains  ");
		sb.append("where g2_code IN( ");
		sb.append("select mng_cde from ag_drain_ps ");
		sb.append("where  FTR_IDN = '" + ftridn + "') ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				mngcde = Utils.chkNull(handler.getString("g2_value"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return mngcde;
	}
	// 취입보 ag_cwip_as
	public static String getCwipMNGPositionPrint(String ftridn) {

		String mngcde = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select g2_value ");
		sb.append("from g2s_codeddomains  ");
		sb.append("where g2_code IN( ");
		sb.append("select mng_cde from ag_cwip_as ");
		sb.append("where  FTR_IDN = '" + ftridn + "') ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				mngcde = Utils.chkNull(handler.getString("g2_value"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return mngcde;
	}
	//ag_culvert_ps 집수암거
	
	public static String getCulvertMNGPositionPrint(String ftridn) {

		String mngcde = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select g2_value ");
		sb.append("from g2s_codeddomains  ");
		sb.append("where g2_code IN( ");
		sb.append("select mng_cde from ag_culvert_ps ");
		sb.append("where  FTR_IDN = '" + ftridn + "') ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				mngcde = Utils.chkNull(handler.getString("g2_value"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return mngcde;
	}
	
	// 저수지 시설물 관리자을 찾음
	public static String getSrchFacilityMNGNAM(String ftridn) {
		String mngnam = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, MNG_NAM ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				mngnam = Utils.chkNull(handler.getString("MNG_NAM"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return mngnam;
	}

	// 저수지 시설물 관리자전화번호을 찾음
	public static String getSrchFacilityTELNUM(String ftridn) {
		String telnum = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,TEL_NUM ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				telnum = Utils.chkNull(handler.getString("TEL_NUM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return telnum;
	}

	// 저수지 시설물 착공일자
	public static String getSrchFacilityBEG_YMD(String ftridn) {
		String BEG_YMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, BEG_YMD ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				BEG_YMD = Utils.chkNull(handler.getString("BEG_YMD"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return BEG_YMD;
	}
	
	
	// 저수지 시설물 준공일자
	public static String getSrchFacilityFNS_YMD(String ftridn) {
		String FNS_YMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, FNS_YMD ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				FNS_YMD = Utils.chkNull(handler.getString("FNS_YMD"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return FNS_YMD;
	}
	// 저수지 시설물 저수량
	public static String getSrchFacilityRSV_VOL(String ftridn) {
		String rsvvol = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,RSV_VOL ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				rsvvol = Utils.chkNull(handler.getString("RSV_VOL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return rsvvol;
	}

	// 저수지 시설물 단위 저수량
	public static String getSrchFacilityPDG_UNT(String ftridn) {
		String pdgunt = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,PDG_UNT ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				pdgunt = Utils.chkNull(handler.getString("PDG_UNT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return pdgunt;
	}

	// 저수지 시설물 만수면적
	public static String getSrchFacilityFUL_ARA(String ftridn) {
		String fulara = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,FUL_ARA ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				fulara = Utils.chkNull(handler.getString("FUL_ARA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return fulara;
	}

	// 저수지 시설물 형식
	public static String getSrchFacilityFORM_EXP(String ftridn) {
		String formexp = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		sb.append("select FTR_IDN, FTR_NM, JUSO,FORM_EXP ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				formexp = Utils.chkNull(handler.getString("FORM_EXP"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return formexp;
	}

	// 저수지 시설물 제당연장
	public static String getSrchFacilityBYC_LEN(String ftridn) {
		String byclen = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,BYC_LEN ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				byclen = Utils.chkNull(handler.getString("BYC_LEN"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return byclen;
	}

	// 저수지 시설물 제고 최고
	public static String getSrchFacilityTOT_JEA(String ftridn) {
		String totjea = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,TOT_JEA ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				totjea = Utils.chkNull(handler.getString("TOT_JEA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return totjea;
	}

	// 저수지 시설물 제고 평고
	public static String getSrchPumpAVG_JEA(String ftridn) {
		String avgjea = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,AVG_JEA ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				avgjea = Utils.chkNull(handler.getString("AVG_JEA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return avgjea;
	}

	// 저수지 시설물 제고 정폭
	public static String getSrchFacilityWID_LEN(String ftridn) {
		String widlen = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,WID_LEN ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				widlen = Utils.chkNull(handler.getString("WID_LEN"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return widlen;
	}

	// 저수지 시설물 법면내제
	public static String getSrchFacilityLAT_IN_SUG(String ftridn) {
		String LAT_IN_SUG = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,LAT_IN_SUG ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				LAT_IN_SUG = Utils.chkNull(handler.getString("LAT_IN_SUG"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return LAT_IN_SUG;
	}

	// 저수지 시설물 법면외제
	public static String getSrchFacilityLAT_OUT_SU(String ftridn) {
		String LAT_OUT_SU = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,LAT_OUT_SU ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				LAT_OUT_SU = Utils.chkNull(handler.getString("LAT_OUT_SU"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return LAT_OUT_SU;
	}

	// 저수지 시설물 보호공 내제
	public static String getSrchFacilitySLO_IN_SUG(String ftridn) {
		String SLO_IN_SUG = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,SLO_IN_SUG ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				SLO_IN_SUG = Utils.chkNull(handler.getString("SLO_IN_SUG"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return SLO_IN_SUG;
	}

	// 저수지 시설물 보호공 외제
	public static String getSrchFacilitySLO_OUT_SU(String ftridn) {
		String SLO_OUT_SU = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,SLO_OUT_SU ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				SLO_OUT_SU = Utils.chkNull(handler.getString("SLO_OUT_SU"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return SLO_OUT_SU;
	}

	// 저수지 시설물 유역면적
	public static String getSrchFacilityRSV_ARA(String ftridn) {
		String RSV_ARA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,RSV_ARA ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				RSV_ARA = Utils.chkNull(handler.getString("RSV_ARA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return RSV_ARA;
	}

	// 저수지 시설물 홍수량
	public static String getSrchFacilityDRA_WAL(String ftridn) {
		String DRA_WAL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,DRA_WAL ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DRA_WAL = Utils.chkNull(handler.getString("DRA_WAL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DRA_WAL;
	}

	// 저수지 시설물 일류수심
	public static String getSrchFacilityORF_DTH(String ftridn) {
		String ORF_DTH = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,ORF_DTH ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				ORF_DTH = Utils.chkNull(handler.getString("ORF_DTH"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return ORF_DTH;
	}

	// 저수지 시설물 구조형식
	public static String getSrchFacilitySCT_CMD(String ftridn) {
		String SCT_CMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,SCT_CMD ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				SCT_CMD = Utils.chkNull(handler.getString("SCT_CMD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return SCT_CMD;
	}

	// 저수지 시설물 언체연장
	public static String getSrchFacilityWSW_LEN(String ftridn) {
		String WSW_LEN = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,WSW_LEN ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				WSW_LEN = Utils.chkNull(handler.getString("WSW_LEN"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return WSW_LEN;
	}

	// 저수지 시설물 언체고 최고
	public static String getSrchFacilityMAX_UN_DRA(String ftridn) {
		String MAX_UN_DRA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,MAX_UN_DRA ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				MAX_UN_DRA = Utils.chkNull(handler.getString("MAX_UN_DRA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return MAX_UN_DRA;
	}

	// 저수지 시설물 언체고 평균
	public static String getSrchFacilityAVG_UN_DRA(String ftridn) {
		String AVG_UN_DRA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,AVG_UN_DRA ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				AVG_UN_DRA = Utils.chkNull(handler.getString("AVG_UN_DRA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return AVG_UN_DRA;
	}

	// 저수지 시설물 방수로연장
	public static String getSrchFacilityREN_DRA(String ftridn) {
		String REN_DRA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,REN_DRA ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				REN_DRA = Utils.chkNull(handler.getString("REN_DRA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return REN_DRA;
	}

	// 저수지 시설물 방수로폭 최대
	public static String getSrchFacilityMAX_DRA(String ftridn) {
		String MAX_DRA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,MAX_DRA ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				MAX_DRA = Utils.chkNull(handler.getString("MAX_DRA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return MAX_DRA;
	}

	// 저수지 시설물 방수로폭 최소
	public static String getSrchFacilityMIN_DRA(String ftridn) {
		String MIN_DRA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,MIN_DRA ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				MIN_DRA = Utils.chkNull(handler.getString("MIN_DRA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return MIN_DRA;
	}

	// 저수지 시설물 방수로폭 평균
	public static String getSrchFacilityAVG_DRA(String ftridn) {
		String AVG_DRA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,AVG_DRA ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				AVG_DRA = Utils.chkNull(handler.getString("AVG_DRA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return AVG_DRA;
	}

	// 저수지 시설물 홍수위상제고
	public static String getSrchFacilityWTR_DRA(String ftridn) {
		String WTR_DRA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,WTR_DRA ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				WTR_DRA = Utils.chkNull(handler.getString("WTR_DRA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return WTR_DRA;
	}

	// 저수지 시설물 통관형식
	public static String getSrchFacilityCDE_WTL(String ftridn) {
		String CDE_WTL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,CDE_WTL ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CDE_WTL = Utils.chkNull(handler.getString("CDE_WTL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return CDE_WTL;
	}

	// 저수지 시설물 통관연장
	public static String getSrchFacilityREN_WTL(String ftridn) {
		String REN_WTL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,REN_WTL ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				REN_WTL = Utils.chkNull(handler.getString("REN_WTL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return REN_WTL;
	}

	// 저수지 시설물 통관단면∮
	public static String getSrchFacilitySEC_P_WTL(String ftridn) {
		String SEC_P_WTL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,SEC_P_WTL ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				SEC_P_WTL = Utils.chkNull(handler.getString("SEC_P_WTL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return SEC_P_WTL;
	}

	// 저수지 시설물 통관단면 H
	public static String getSrchFacilitySEC_H_WTL(String ftridn) {
		String SEC_H_WTL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,SEC_H_WTL ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				SEC_H_WTL = Utils.chkNull(handler.getString("SEC_H_WTL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return SEC_H_WTL;
	}

	// 저수지 시설물 통관단면 B
	public static String getSrchFacilitySEC_B_WTL(String ftridn) {
		String SEC_B_WTL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,SEC_B_WTL ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				SEC_B_WTL = Utils.chkNull(handler.getString("SEC_B_WTL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return SEC_B_WTL;
	}

	// 저수지 시설물 취수시설구조
	public static String getSrchFacilitySTR_WTL(String ftridn) {
		String STR_WTL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,STR_WTL ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				STR_WTL = Utils.chkNull(handler.getString("STR_WTL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return STR_WTL;
	}

	// 저수지 시설물 취수공
	public static String getSrchFacilitySEC_WTL(String ftridn) {
		String SEC_WTL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,SEC_WTL ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				SEC_WTL = Utils.chkNull(handler.getString("SEC_WTL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return SEC_WTL;
	}

	// 저수지 시설물 취수공 규격
	public static String getSrchFacilitySTD_WTL(String ftridn) {
		String STD_WTL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,STD_WTL ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				STD_WTL = Utils.chkNull(handler.getString("STD_WTL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return STD_WTL;
	}

	// 저수지 시설물 취수능력
	public static String getSrchFacilityINT_WTL(String ftridn) {
		String INT_WTL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,INT_WTL ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				INT_WTL = Utils.chkNull(handler.getString("INT_WTL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return INT_WTL;
	}

	// 저수지 시설물 용수간선
	public static String getSrchFacilityMAI_WAY(String ftridn) {
		String MAI_WAY = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,MAI_WAY ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				MAI_WAY = Utils.chkNull(handler.getString("MAI_WAY"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return MAI_WAY;
	}

	// 저수지 시설물 용수지선
	public static String getSrchFacilityBRA_WAY(String ftridn) {
		String BRA_WAY = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,BRA_WAY ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				BRA_WAY = Utils.chkNull(handler.getString("BRA_WAY"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return BRA_WAY;
	}

	// 저수지 시설물 추도
	public static String getSrchFacilityCHU_WAY(String ftridn) {
		String CHU_WAY = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,CHU_WAY ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CHU_WAY = Utils.chkNull(handler.getString("CHU_WAY"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return CHU_WAY;
	}

	// 저수지 시설물 도수로
	public static String getSrchFacilityRAC_WAY(String ftridn) {
		String CHU_WAY = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,RAC_WAY ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CHU_WAY = Utils.chkNull(handler.getString("RAC_WAY"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return CHU_WAY;
	}

	// 저수지 시설물 송수로
	public static String getSrchFacilityAQU_WAY(String ftridn) {
		String CHU_WAY = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,AQU_WAY ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CHU_WAY = Utils.chkNull(handler.getString("AQU_WAY"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return CHU_WAY;
	}

	// 저수지 시설물 배수로
	public static String getSrchFacilityDRAIN_WAY(String ftridn) {
		String CHU_WAY = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,DRAIN_WAY ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CHU_WAY = Utils.chkNull(handler.getString("DRAIN_WAY"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return CHU_WAY;
	}

	// 저수지 시설물 인가
	public static String getSrchFacilitySTR_ARA(String ftridn) {
		String STR_ARA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,STR_ARA ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				STR_ARA = Utils.chkNull(handler.getString("STR_ARA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return STR_ARA;
	}

	// 저수지 시설물 내한면적
	public static String getSrchFacilityVIS_ARA(String ftridn) {
		String VIS_ARA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,VIS_ARA ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				VIS_ARA = Utils.chkNull(handler.getString("VIS_ARA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return VIS_ARA;
	}

	// 저수지 시설물 부족면적
	public static String getSrchFacilityTRI_ARA(String ftridn) {
		String TRI_ARA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,TRI_ARA ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TRI_ARA = Utils.chkNull(handler.getString("TRI_ARA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TRI_ARA;
	}

	// 설치비 시공자
	public static String getSrchFacilityOPR_NAM(String ftridn) {
		String OPR_NAM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,OPR_NAM ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				OPR_NAM = Utils.chkNull(handler.getString("OPR_NAM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return OPR_NAM;
	}

	// 설치비 시공자
	public static String getSrchFacilityGCN_NAM(String ftridn) {
		String GCN_NAM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,GCN_NAM ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				GCN_NAM = Utils.chkNull(handler.getString("GCN_NAM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return GCN_NAM;
	}

	// 설치비 국비보조
	public static String getSrchFacilityNAT_AMT(String ftridn) {
		String NAT_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,NAT_AMT ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				NAT_AMT = Utils.chkNull(handler.getString("NAT_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return NAT_AMT;
	}

	// 설치비 융자
	public static String getSrchFacilityBND_AMT(String ftridn) {
		String BND_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,BND_AMT ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				BND_AMT = Utils.chkNull(handler.getString("BND_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return BND_AMT;
	}

	// 설치비 시군도비
	public static String getSrchFacilityCIT_AMT(String ftridn) {
		String CIT_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,CIT_AMT ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CIT_AMT = Utils.chkNull(handler.getString("CIT_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return CIT_AMT;
	}

	// 설치비 자부담
	public static String getSrchFacilitySEL_AMT(String ftridn) {
		String SEL_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,SEL_AMT ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				SEL_AMT = Utils.chkNull(handler.getString("SEL_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return SEL_AMT;
	}

	// 설치비 기타
	public static String getSrchFacilityCET_AMT(String ftridn) {
		String CET_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,CET_AMT ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CET_AMT = Utils.chkNull(handler.getString("CET_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return CET_AMT;
	}

	// 설치비 기타
	public static String getSrchFacilityTCT_AMT(String ftridn) {
		String TCT_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,TCT_AMT ");
		sb.append("from ag_reservoir_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TCT_AMT = Utils.chkNull(handler.getString("TCT_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TCT_AMT;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////// 공사유지관리
	////////////////////////////////////////////////////////////////////////////////////////////////////// 필지///////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	// 공사유지관리필지 공사관리 년도
	public static ArrayList<srchReservoirRecord> getSrchFacilityCONS_YMD(String ftridn) {
		ArrayList<srchReservoirRecord> list = new ArrayList<srchReservoirRecord>();
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select * ");
		sb.append("from ag_cons_dt  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by FTR_IDN ");
		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();

			while (handler.next()) {
				srchReservoirRecord sfr = new srchReservoirRecord();
				sfr.CONS_YMD = Utils.chkNull(handler.getString("CONS_YMD"));
				sfr.OPR_NAM = Utils.chkNull(handler.getString("OPR_NAM"));
				sfr.CONS_TOT = Utils.chkNull(handler.getString("CONS_TOT"));
				sfr.NAT_AMT = Utils.chkNull(handler.getString("NAT_AMT"));
				sfr.CIT_AMT = Utils.chkNull(handler.getString("CIT_AMT"));
				sfr.EXP_AMT = Utils.chkNull(handler.getString("EXP_AMT"));
				sfr.BND_AMT = Utils.chkNull(handler.getString("BND_AMT"));
				sfr.DPC_AMT = Utils.chkNull(handler.getString("DPC_AMT"));
				sfr.DGV_AMT = Utils.chkNull(handler.getString("DGV_AMT"));
				sfr.DET_AMT = Utils.chkNull(handler.getString("DET_AMT"));
				sfr.CONS_DES = Utils.chkNull(handler.getString("CONS_DES"));
				sfr.ETC = Utils.chkNull(handler.getString("ETC"));
				list.add(sfr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return list;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////// 필지정보///////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	// 공사유지관리필지 공사관리 년도
	public static ArrayList<srchReservoirRecord> getSrchFacilityAg_parc_dt(String ftridn,String tableName,String gbn) {
		ArrayList<srchReservoirRecord> list = new ArrayList<srchReservoirRecord>();
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" select t1.land_area,t1.tra_area,t1.ownr_nm,t1.jimok,t1.etc,t2.juso ");
		sb.append(" from ag_parc_dt t1 ");
		sb.append(" left join "+tableName+" t2 on t1.ftr_idn = t2.ftr_idn ");
		sb.append(" where t1.ftr_cde = '"+gbn+"' ");
		sb.append(" and t1.ftr_idn = '"+ftridn+"' ");
		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();

			while (handler.next()) {
				srchReservoirRecord sfr = new srchReservoirRecord();
				sfr.LAND_AREA = Utils.chkNull(handler.getString("land_area"));
				sfr.TRA_AREA = Utils.chkNull(handler.getString("tra_area"));
				sfr.OWNR_NM = Utils.chkNull(handler.getString("ownr_nm"));
				sfr.jimok = Utils.chkNull(handler.getString("jimok"));
				sfr.ETC = Utils.chkNull(handler.getString("etc"));
				sfr.juso = Utils.chkNull(handler.getString("juso"));
				list.add(sfr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return list;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////// 필지정보///////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	// 공사유지관리필지 공사관리 년도
	public static ArrayList<srchReservoirRecord> getSrchFacilityAg_rep_dt(String ftridn) {
		ArrayList<srchReservoirRecord> list = new ArrayList<srchReservoirRecord>();
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select * ");
		sb.append("from Ag_rep_dt  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by FTR_IDN ");
		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();

			while (handler.next()) {
				srchReservoirRecord sfr = new srchReservoirRecord();
				sfr.REP_YMD = Utils.chkNull(handler.getString("rep_ymd"));
				sfr.REP_DES = Utils.chkNull(handler.getString("rep_des"));
				sfr.U_OPR_NAM = Utils.chkNull(handler.getString("opr_nam"));
				//sfr.REP_AMT = Utils.chkNull(handler.getString("REP_AMT"));
				sfr.U_ETC = Utils.chkNull(handler.getString("etc"));
				list.add(sfr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return list;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////// 필지정보///////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	// 공사유지관리필지 공사관리 년도
	public static ArrayList<srchReservoirRecord> getSrchFacilityImages(String ftridn) {
		ArrayList<srchReservoirRecord> list = new ArrayList<srchReservoirRecord>();
		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		

		sb.append("select * ");
		sb.append("from _gmx_images  ");
		sb.append("where key_value = '" + ftridn + "' and pnt_yn = 'Y' order by key_value ");
		
		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();

			while (handler.next()) {
				srchReservoirRecord sfr = new srchReservoirRecord();
				sfr.FILE_PATH = Utils.chkNull(handler.getString("file_path"));
				sfr.FILE_DESC = Utils.chkNull(handler.getString("file_desc"));
				sfr.IMG_NM = Utils.chkNull(handler.getString("img_nm"));
				
				list.add(sfr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return list;
	}
	
	
	///////// 배수장
	////배수장 시설개요 - 소재지
	public static String getSrchDrainFTR_NM(String ftridn) {
		String FTR_NM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,FTR_NM ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				FTR_NM = Utils.chkNull(handler.getString("FTR_NM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return FTR_NM;
	}

	////배수장 시설개요 - 소재지
	public static String getSrchDrainJUSO(String ftridn) {
		String JUSO = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				JUSO = Utils.chkNull(handler.getString("JUSO"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return JUSO;
	}
	
////배수장 시설개요 - 소재지
	public static String getSrchDrainMNG_NAM(String ftridn) {
		String MNG_NAM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, MNG_NAM ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				MNG_NAM = Utils.chkNull(handler.getString("MNG_NAM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return MNG_NAM;
	}
	
////배수장 시설개요 - 관리자연락처
	public static String getSrchDrainTEL_NUM(String ftridn) {
		String TEL_NUM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, TEL_NUM ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TEL_NUM = Utils.chkNull(handler.getString("TEL_NUM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TEL_NUM;
	}
	
	
////배수장 시설개요 - 착공일자
	public static String getSrchDrainBEG_YMD(String ftridn) {
		String BEG_YMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, BEG_YMD ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				BEG_YMD = Utils.chkNull(handler.getString("BEG_YMD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return BEG_YMD;
	}

////배수장 시설개요 - 유역면적
	public static String getSrchDrainRSV_AREA(String ftridn) {
		String RSV_AREA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, RSV_AREA ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				RSV_AREA = Utils.chkNull(handler.getString("RSV_AREA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return RSV_AREA;
	}
	
	
////배수장 시설개요 - 준공일자
	public static String getSrchDrainFNS_YMD(String ftridn) {
		String FNS_YMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, FNS_YMD ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				FNS_YMD = Utils.chkNull(handler.getString("FNS_YMD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return FNS_YMD;
	}
	

////배수장 시설개요 - 수계명
	public static String getSrchDrainWSS_NM(String ftridn) {
		String WSS_NM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, WSS_NM ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				WSS_NM = Utils.chkNull(handler.getString("WSS_NM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return WSS_NM;
	}
	
	
////배수장제원 관개면적 - 신규(ha)
	public static String getSrchDrainIRR_NEW(String ftridn) {
		String IRR_NEW = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, IRR_NEW ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				IRR_NEW = Utils.chkNull(handler.getString("IRR_NEW"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return IRR_NEW;
	}
	

////배수장제원 - 보강(ha)
	public static String getSrchDrainIRR_FIX(String ftridn) {
		String IRR_FIX = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, IRR_FIX ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				IRR_FIX = Utils.chkNull(handler.getString("IRR_FIX"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return IRR_FIX;
	}
	
////배수장제원 - 향후개발(ha)
	public static String getSrchDrainIRR_DEV(String ftridn) {
		String IRR_DEV = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, IRR_DEV ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				IRR_DEV = Utils.chkNull(handler.getString("IRR_DEV"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return IRR_DEV;
	}
	
	
////배수장제원 - 구역외(ha)
	public static String getSrchDrainIRR_ETC(String ftridn) {
		String IRR_ETC = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, IRR_ETC ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				IRR_ETC = Utils.chkNull(handler.getString("IRR_ETC"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return IRR_ETC;
	}
	
	

////배수장제원 - 홍수량
	public static String getSrchDrainDRA_WAL(String ftridn) {
		String DRA_WAL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DRA_WAL ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DRA_WAL = Utils.chkNull(handler.getString("DRA_WAL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DRA_WAL;
	}
	

////배수장제원 - 갈수량
	public static String getSrchDrainTHR_WAL(String ftridn) {
		String THR_WAL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, THR_WAL ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				THR_WAL = Utils.chkNull(handler.getString("THR_WAL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return THR_WAL;
	}
	

////배수장제원 - 갈수량
	public static String getSrchDrainDRAIN_WAL(String ftridn) {
		String DRAIN_WAL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DRAIN_WAL ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DRAIN_WAL = Utils.chkNull(handler.getString("DRAIN_WAL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DRAIN_WAL;
	}
	
	

////배수장제원 - 실양정
	public static String getSrchDrainAVT_HEAD(String ftridn) {
		String AVT_HEAD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, AVT_HEAD ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				AVT_HEAD = Utils.chkNull(handler.getString("AVT_HEAD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return AVT_HEAD;
	}
	
////배수장제원 - 전양정
	public static String getSrchDrainDEV_HEAD(String ftridn) {
		String DEV_HEAD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DEV_HEAD ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DEV_HEAD = Utils.chkNull(handler.getString("DEV_HEAD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DEV_HEAD;
	}
	
	
////배수장제원 - 엔진
	public static String getSrchDrainENG_HP(String ftridn) {
		String ENG_HP = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, ENG_HP ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				ENG_HP = Utils.chkNull(handler.getString("ENG_HP"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return ENG_HP;
	}
	
////배수장제원 - 펌프
	public static String getSrchDrainPUMP_MM(String ftridn) {
		String PUMP_MM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, PUMP_MM ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				PUMP_MM = Utils.chkNull(handler.getString("PUMP_MM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return PUMP_MM;
	}
	
	
	
////배수장제원 - 변압기
	public static String getSrchDrainTRANS_VOL(String ftridn) {
		String TRANS_VOL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, TRANS_VOL ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TRANS_VOL = Utils.chkNull(handler.getString("TRANS_VOL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TRANS_VOL;
	}
	

////배수장제원 - 배전선로
	public static String getSrchDrainDIST_LINE(String ftridn) {
		String DIST_LINE = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DIST_LINE ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DIST_LINE = Utils.chkNull(handler.getString("DIST_LINE"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DIST_LINE;
	}
	

////배수장제원 - 기중장비
	public static String getSrchDrainCRAN_EQUI(String ftridn) {
		String CRAN_EQUI = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, CRAN_EQUI ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CRAN_EQUI = Utils.chkNull(handler.getString("CRAN_EQUI"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return CRAN_EQUI;
	}
	

////배수장제원 - 도수로
	public static String getSrchDrainRAC_WAY(String ftridn) {
		String RAC_WAY = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, RAC_WAY ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				RAC_WAY = Utils.chkNull(handler.getString("RAC_WAY"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return RAC_WAY;
	}
	

////배수장제원 - 송수로
	public static String getSrchDrainAQU_WAY(String ftridn) {
		String AQU_WAY = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, AQU_WAY ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				AQU_WAY = Utils.chkNull(handler.getString("AQU_WAY"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return AQU_WAY;
	}
	

////배수장제원 - 용수로
	public static String getSrchDrainIRR_WAY(String ftridn) {
		String IRR_WAY = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, IRR_WAY ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				IRR_WAY = Utils.chkNull(handler.getString("IRR_WAY"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return IRR_WAY;
	}
	

////배수장제원 - 배수로
	public static String getSrchDrainDRAIN_WAY(String ftridn) {
		String DRAIN_WAY = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DRAIN_WAY ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DRAIN_WAY = Utils.chkNull(handler.getString("DRAIN_WAY"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DRAIN_WAY;
	}
	

////배수장제원 - 터널
	public static String getSrchDrainTRN_LEN(String ftridn) {
		String TRN_LEN = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, TRN_LEN ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TRN_LEN = Utils.chkNull(handler.getString("TRN_LEN"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TRN_LEN;
	}
	

////배수장 설치비 제원별 - 국비보조
	public static String getSrchDrainNAT_AMT(String ftridn) {
		String NAT_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, NAT_AMT ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				NAT_AMT = Utils.chkNull(handler.getString("NAT_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return NAT_AMT;
	}
	

////배수장 설치비 제원별 - 지방비
	public static String getSrchDrainCIT_AMT(String ftridn) {
		String CIT_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, CIT_AMT ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CIT_AMT = Utils.chkNull(handler.getString("CIT_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return CIT_AMT;
	}
	
////배수장 설치비 제원별 - 융자
	public static String getSrchDrainBND_AMT(String ftridn) {
		String BND_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, BND_AMT ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				BND_AMT = Utils.chkNull(handler.getString("BND_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return BND_AMT;
	}
	
////배수장 설치비 제원별 - 자부담등
	public static String getSrchDrainSEL_AMT(String ftridn) {
		String SEL_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, SEL_AMT ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				SEL_AMT = Utils.chkNull(handler.getString("SEL_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return SEL_AMT;
	}
	
////배수장 설치비 제원별 - 계
	public static String getSrchDrainALL_AMT(String ftridn) {
		String ALL_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, ALL_AMT ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				ALL_AMT = Utils.chkNull(handler.getString("ALL_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return ALL_AMT;
	}
	

////배수장 설치비 지출내역 - 순공사비
	public static String getSrchDrainDPC_AMT(String ftridn) {
		String DPC_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DPC_AMT ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DPC_AMT = Utils.chkNull(handler.getString("DPC_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DPC_AMT;
	}
	


////배수장 설치비 지출내역 - 자재비
	public static String getSrchDrainDGV_AMT(String ftridn) {
		String DGV_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DGV_AMT ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DGV_AMT = Utils.chkNull(handler.getString("DGV_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DGV_AMT;
	}
	

////배수장 설치비 지출내역 - 용지매수비
	public static String getSrchDrainLAND_AMT(String ftridn) {
		String LAND_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, LAND_AMT ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				LAND_AMT = Utils.chkNull(handler.getString("LAND_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return LAND_AMT;
	}
	
////배수장 설치비 지출내역 - 설계공감비
	public static String getSrchDrainDSN_AMT(String ftridn) {
		String DSN_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DSN_AMT ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DSN_AMT = Utils.chkNull(handler.getString("DSN_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DSN_AMT;
	}
	

////배수장 설치비 지출내역 - 관리비 기타
	public static String getSrchDrainDET_AMT(String ftridn) {
		String DET_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DET_AMT ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DET_AMT = Utils.chkNull(handler.getString("DET_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DET_AMT;
	}
	

////배수장 설치비 지출내역 - 계
	public static String getSrchDrainTCO_AMT(String ftridn) {
		String TCO_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, TCO_AMT ");
		sb.append("from ag_drain_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TCO_AMT = Utils.chkNull(handler.getString("TCO_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TCO_AMT;
	}
	
	

	///////// 양수장
	////시설개요 - 소재지
	public static String getSrchPumpFTR_NM(String ftridn) {
		String FTR_NM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,FTR_NM ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				FTR_NM = Utils.chkNull(handler.getString("FTR_NM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return FTR_NM;
	}

	////시설개요 - 소재지
	public static String getSrchPumpJUSO(String ftridn) {
		String JUSO = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				JUSO = Utils.chkNull(handler.getString("JUSO"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return JUSO;
	}
	
////시설개요 - 소재지
	public static String getSrchPumpMNG_NAM(String ftridn) {
		String MNG_NAM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, MNG_NAM ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				MNG_NAM = Utils.chkNull(handler.getString("MNG_NAM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return MNG_NAM;
	}
	
////시설개요 - 관리자연락처
	public static String getSrchPumpTEL_NUM(String ftridn) {
		String TEL_NUM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, TEL_NUM ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TEL_NUM = Utils.chkNull(handler.getString("TEL_NUM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TEL_NUM;
	}
	
	
////시설개요 - 착공일자
	public static String getSrchPumpBEG_YMD(String ftridn) {
		String BEG_YMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, BEG_YMD ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				BEG_YMD = Utils.chkNull(handler.getString("BEG_YMD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return BEG_YMD;
	}

////시설개요 - 유역면적
	public static String getSrchPumpRSV_AREA(String ftridn) {
		String RSV_AREA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, RSV_AREA ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				RSV_AREA = Utils.chkNull(handler.getString("RSV_AREA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return RSV_AREA;
	}
	
	
////시설개요 - 준공일자
	public static String getSrchPumpFNS_YMD(String ftridn) {
		String FNS_YMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, FNS_YMD ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				FNS_YMD = Utils.chkNull(handler.getString("FNS_YMD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return FNS_YMD;
	}
	

////시설개요 - 수계명
	public static String getSrchPumpWSS_NM(String ftridn) {
		String WSS_NM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, WSS_NM ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				WSS_NM = Utils.chkNull(handler.getString("WSS_NM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return WSS_NM;
	}
	
	
////양수장제원 관개면적 - 신규(ha)
	public static String getSrchPumpIRR_NEW(String ftridn) {
		String IRR_NEW = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, IRR_NEW ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				IRR_NEW = Utils.chkNull(handler.getString("IRR_NEW"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return IRR_NEW;
	}
	

////양수장제원 - 보강(ha)
	public static String getSrchPumpIRR_FIX(String ftridn) {
		String IRR_FIX = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, IRR_FIX ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				IRR_FIX = Utils.chkNull(handler.getString("IRR_FIX"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return IRR_FIX;
	}
	
////양수장제원 - 향후개발(ha)
	public static String getSrchPumpIRR_DEV(String ftridn) {
		String IRR_DEV = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, IRR_DEV ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				IRR_DEV = Utils.chkNull(handler.getString("IRR_DEV"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return IRR_DEV;
	}
	
	
////양수장제원 - 구역외(ha)
	public static String getSrchPumpIRR_ETC(String ftridn) {
		String IRR_ETC = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, IRR_ETC ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				IRR_ETC = Utils.chkNull(handler.getString("IRR_ETC"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return IRR_ETC;
	}
	
	

////양수장제원 - 홍수량
	public static String getSrchPumpDRA_WAL(String ftridn) {
		String DRA_WAL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DRA_WAL ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DRA_WAL = Utils.chkNull(handler.getString("DRA_WAL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DRA_WAL;
	}
	

////양수장제원 - 갈수량
	public static String getSrchPumpTHR_WAL(String ftridn) {
		String THR_WAL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, THR_WAL ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				THR_WAL = Utils.chkNull(handler.getString("THR_WAL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return THR_WAL;
	}
	

////양수장제원 - 갈수량
	public static String getSrchPumpPUMP_WAL(String ftridn) {
		String PUMP_WAL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, PUMP_WAL ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				PUMP_WAL = Utils.chkNull(handler.getString("PUMP_WAL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return PUMP_WAL;
	}
	
	

////양수장제원 - 실양정
	public static String getSrchPumpAVT_HEAD(String ftridn) {
		String AVT_HEAD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, AVT_HEAD ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				AVT_HEAD = Utils.chkNull(handler.getString("AVT_HEAD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return AVT_HEAD;
	}
	
////양수장제원 - 전양정
	public static String getSrchPumpDEV_HEAD(String ftridn) {
		String DEV_HEAD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DEV_HEAD ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DEV_HEAD = Utils.chkNull(handler.getString("DEV_HEAD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DEV_HEAD;
	}
	
	
////양수장제원 - 엔진
	public static String getSrchPumpENG_HP(String ftridn) {
		String ENG_HP = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, ENG_HP ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				ENG_HP = Utils.chkNull(handler.getString("ENG_HP"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return ENG_HP;
	}
	
////양수장제원 - 펌프
	public static String getSrchPumpPUMP_MM(String ftridn) {
		String PUMP_MM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, PUMP_MM ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				PUMP_MM = Utils.chkNull(handler.getString("PUMP_MM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return PUMP_MM;
	}
	
	
	
////양수장제원 - 변압기
	public static String getSrchPumpTRANS_VOL(String ftridn) {
		String TRANS_VOL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, TRANS_VOL ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TRANS_VOL = Utils.chkNull(handler.getString("TRANS_VOL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TRANS_VOL;
	}
	

////양수장제원 - 배전선로
	public static String getSrchPumpDIST_LINE(String ftridn) {
		String DIST_LINE = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DIST_LINE ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DIST_LINE = Utils.chkNull(handler.getString("DIST_LINE"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DIST_LINE;
	}
	

////양수장제원 - 기중장비
	public static String getSrchPumpCRAN_EQUI(String ftridn) {
		String CRAN_EQUI = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, CRAN_EQUI ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CRAN_EQUI = Utils.chkNull(handler.getString("CRAN_EQUI"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return CRAN_EQUI;
	}
	

////양수장제원 - 도수로
	public static String getSrchPumpRAC_WAY(String ftridn) {
		String RAC_WAY = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, RAC_WAY ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				RAC_WAY = Utils.chkNull(handler.getString("RAC_WAY"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return RAC_WAY;
	}
	

////양수장제원 - 송수로
	public static String getSrchPumpAQU_WAY(String ftridn) {
		String AQU_WAY = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, AQU_WAY ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				AQU_WAY = Utils.chkNull(handler.getString("AQU_WAY"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return AQU_WAY;
	}
	

////양수장제원 - 용수로
	public static String getSrchPumpIRR_WAY(String ftridn) {
		String IRR_WAY = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, IRR_WAY ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				IRR_WAY = Utils.chkNull(handler.getString("IRR_WAY"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return IRR_WAY;
	}
	

////양수장제원 - 배수로
	public static String getSrchPumpDRAIN_WAY(String ftridn) {
		String DRAIN_WAY = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DRAIN_WAY ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DRAIN_WAY = Utils.chkNull(handler.getString("DRAIN_WAY"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DRAIN_WAY;
	}
	

////양수장제원 - 터널
	public static String getSrchPumpTRN_LEN(String ftridn) {
		String TRN_LEN = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, TRN_LEN ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TRN_LEN = Utils.chkNull(handler.getString("TRN_LEN"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TRN_LEN;
	}
	

////양수장 설치비 제원별 - 국비보조
	public static String getSrchPumpNAT_AMT(String ftridn) {
		String NAT_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, NAT_AMT ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				NAT_AMT = Utils.chkNull(handler.getString("NAT_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return NAT_AMT;
	}
	

////양수장 설치비 제원별 - 지방비
	public static String getSrchPumpCIT_AMT(String ftridn) {
		String CIT_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, CIT_AMT ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CIT_AMT = Utils.chkNull(handler.getString("CIT_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return CIT_AMT;
	}
	
////양수장 설치비 제원별 - 융자
	public static String getSrchPumpBND_AMT(String ftridn) {
		String BND_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, BND_AMT ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				BND_AMT = Utils.chkNull(handler.getString("BND_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return BND_AMT;
	}
	
////양수장 설치비 제원별 - 자부담등
	public static String getSrchPumpSEL_AMT(String ftridn) {
		String SEL_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, SEL_AMT ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				SEL_AMT = Utils.chkNull(handler.getString("SEL_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return SEL_AMT;
	}
	
////양수장 설치비 제원별 - 계
	public static String getSrchPumpALL_AMT(String ftridn) {
		String ALL_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, ALL_AMT ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				ALL_AMT = Utils.chkNull(handler.getString("ALL_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return ALL_AMT;
	}
	

////양수장 설치비 지출내역 - 순공사비
	public static String getSrchPumpDPC_AMT(String ftridn) {
		String DPC_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DPC_AMT ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DPC_AMT = Utils.chkNull(handler.getString("DPC_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DPC_AMT;
	}
	


////양수장 설치비 지출내역 - 자재비
	public static String getSrchPumpDGV_AMT(String ftridn) {
		String DGV_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DGV_AMT ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DGV_AMT = Utils.chkNull(handler.getString("DGV_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DGV_AMT;
	}
	

////양수장 설치비 지출내역 - 용지매수비
	public static String getSrchPumpLAND_AMT(String ftridn) {
		String LAND_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, LAND_AMT ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				LAND_AMT = Utils.chkNull(handler.getString("LAND_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return LAND_AMT;
	}
	
////양수장 설치비 지출내역 - 설계공감비
	public static String getSrchPumpDSN_AMT(String ftridn) {
		String DSN_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DSN_AMT ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DSN_AMT = Utils.chkNull(handler.getString("DSN_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DSN_AMT;
	}
	

////양수장 설치비 지출내역 - 관리비 기타
	public static String getSrchPumpDET_AMT(String ftridn) {
		String DET_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, DET_AMT ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DET_AMT = Utils.chkNull(handler.getString("DET_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DET_AMT;
	}
	

////양수장 설치비 지출내역 - 계
	public static String getSrchPumpTCO_AMT(String ftridn) {
		String TCO_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, TCO_AMT ");
		sb.append("from ag_pump_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TCO_AMT = Utils.chkNull(handler.getString("TCO_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TCO_AMT;
	}

	
////취입보
////취입보 주소
	public static String getSrchCwipJUSO(String ftridn) {
		String JUSO = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO ");
		sb.append("from ag_cwip_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				JUSO = Utils.chkNull(handler.getString("JUSO"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return JUSO;
	}

////취입보 시설명 
	public static String getSrchCwipFTR_NM(String ftridn) {
		String FTR_NM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, FTR_NM ");
		sb.append("from ag_cwip_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				FTR_NM = Utils.chkNull(handler.getString("FTR_NM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return FTR_NM;
	}
	
////취입보 관리자
	public static String getSrchCwipMNG_NAM(String ftridn) {
		String MNG_NAM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, MNG_NAM ");
		sb.append("from ag_cwip_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				MNG_NAM = Utils.chkNull(handler.getString("MNG_NAM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return MNG_NAM;
	}
	
////취입보 관리자연락처
	public static String getSrchCwipTEL_NUM(String ftridn) {
		String TEL_NUM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, TEL_NUM ");
		sb.append("from ag_cwip_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TEL_NUM = Utils.chkNull(handler.getString("TEL_NUM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TEL_NUM;
	}
	

////취입보 착공일자
	public static String getSrchCwipBEG_YMD(String ftridn) {
		String BEG_YMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, BEG_YMD ");
		sb.append("from ag_cwip_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				BEG_YMD = Utils.chkNull(handler.getString("BEG_YMD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return BEG_YMD;
	}


////취입보 준공일자
	public static String getSrchCwipFNS_YMD(String ftridn) {
		String FNS_YMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, FNS_YMD ");
		sb.append("from ag_cwip_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				FNS_YMD = Utils.chkNull(handler.getString("FNS_YMD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return FNS_YMD;
	}
	
	
////취입보 제원 - 하천명
	public static String getSrchCwipRIV_NAM(String ftridn) {
		String RIV_NAM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, RIV_NAM ");
		sb.append("from ag_cwip_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				RIV_NAM = Utils.chkNull(handler.getString("RIV_NAM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return RIV_NAM;
	}
	
	
////취입보 제원 - 규모 L(m)
	public static String getSrchCwipLIT_L(String ftridn) {
		String LIT_L = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, LIT_L ");
		sb.append("from ag_cwip_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				LIT_L = Utils.chkNull(handler.getString("LIT_L"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return LIT_L;
	}
	
////취입보 제원 - 규모 H(m)
	public static String getSrchCwipLIT_H(String ftridn) {
		String LIT_H = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, LIT_H ");
		sb.append("from ag_cwip_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				LIT_H = Utils.chkNull(handler.getString("LIT_H"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return LIT_H;
	}
	
	
////취입보 제원 - 수혜면적
	public static String getSrchCwipPRS_ARA(String ftridn) {
		String PRS_ARA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, PRS_ARA ");
		sb.append("from ag_cwip_as  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				PRS_ARA = Utils.chkNull(handler.getString("PRS_ARA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return PRS_ARA;
	}
	
////집수암거
////집수암거 시설개요 - 주소
	public static String getSrchCulvertJUSO(String ftridn) {
		String JUSO = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO ");
		sb.append("from ag_culvert_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				JUSO = Utils.chkNull(handler.getString("JUSO"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return JUSO;
	}
	
////집수암거 시설개요 - 시설명
	public static String getSrchCulvertFTR_NM(String ftridn) {
		String FTR_NM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, FTR_NM ");
		sb.append("from ag_culvert_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				FTR_NM = Utils.chkNull(handler.getString("FTR_NM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return FTR_NM;
	}
	
////집수암거 시설개요 - 관리자
	public static String getSrchCulvertMNG_NAM(String ftridn) {
		String MNG_NAM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, MNG_NAM ");
		sb.append("from ag_culvert_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				MNG_NAM = Utils.chkNull(handler.getString("MNG_NAM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return MNG_NAM;
	}
	
////집수암거 시설개요 - 관리자연락처
	public static String getSrchCulvertTEL_NUM(String ftridn) {
		String TEL_NUM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, TEL_NUM ");
		sb.append("from ag_culvert_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TEL_NUM = Utils.chkNull(handler.getString("TEL_NUM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TEL_NUM;
	}
	
	
////집수암거 시설개요 - 착공일자
	public static String getSrchCulvertBEG_YMD(String ftridn) {
		String BEG_YMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, BEG_YMD ");
		sb.append("from ag_culvert_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				BEG_YMD = Utils.chkNull(handler.getString("BEG_YMD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return BEG_YMD;
	}
	
	
////집수암거 시설개요 - 준공일자
	public static String getSrchCulvertFNS_YMD(String ftridn) {
		String FNS_YMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, FNS_YMD ");
		sb.append("from ag_culvert_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				FNS_YMD = Utils.chkNull(handler.getString("FNS_YMD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return FNS_YMD;
	}
	
	
////집수암거 시설개요 - 수원공구분
	public static String getSrchCulvertITW_CDE(String ftridn) {
		String ITW_CDE = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, ITW_CDE ");
		sb.append("from ag_culvert_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				ITW_CDE = Utils.chkNull(handler.getString("ITW_CDE"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return ITW_CDE;
	}
	
	
////집수암거 집수암거제원 - 관경
	public static String getSrchCulvertPIP_DIP(String ftridn) {
		String PIP_DIP = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, PIP_DIP ");
		sb.append("from ag_culvert_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				PIP_DIP = Utils.chkNull(handler.getString("PIP_DIP"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return PIP_DIP;
	}
	
////집수암거 집수암거제원 - 매설심도
	public static String getSrchCulvertPIP_DEP(String ftridn) {
		String PIP_DEP = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, PIP_DEP ");
		sb.append("from ag_culvert_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				PIP_DEP = Utils.chkNull(handler.getString("PIP_DEP"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return PIP_DEP;
	}
	
////집수암거 집수암거제원 - 매설길이
	public static String getSrchCulvertPIP_LEN(String ftridn) {
		String PIP_LEN = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, PIP_LEN ");
		sb.append("from ag_culvert_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				PIP_LEN = Utils.chkNull(handler.getString("PIP_LEN"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return PIP_LEN;
	}
	
	
////집수암거 집수암거제원 - 자연수위
	public static String getSrchCulvertNAT_WAL(String ftridn) {
		String NAT_WAL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, NAT_WAL ");
		sb.append("from ag_culvert_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				NAT_WAL = Utils.chkNull(handler.getString("NAT_WAL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return NAT_WAL;
	}
	
////집수암거 집수암거제원 - 안정수위
	public static String getSrchCulvertSAF_WAL(String ftridn) {
		String SAF_WAL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, SAF_WAL ");
		sb.append("from ag_culvert_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				SAF_WAL = Utils.chkNull(handler.getString("SAF_WAL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return SAF_WAL;
	}
	

////집수암거 집수암거제원 - 수혜면적
	public static String getSrchCulvertPRS_ARA(String ftridn) {
		String PRS_ARA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO, PRS_ARA ");
		sb.append("from ag_culvert_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				PRS_ARA = Utils.chkNull(handler.getString("PRS_ARA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return PRS_ARA;
	}
	
	
	///////방조제
////방조제 시설개요 - 주소
	public static String getSrchSeawallJUSO(String ftridn) {
		String JUSO = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				JUSO = Utils.chkNull(handler.getString("JUSO"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return JUSO;
	}
	
////방조제 시설개요 - 시설명
	public static String getSrchSeawallFTR_NM(String ftridn) {
		String FTR_NM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,FTR_NM ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				FTR_NM = Utils.chkNull(handler.getString("FTR_NM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return FTR_NM;
	}
	
////방조제 시설개요 - 관리자
	public static String getSrchSeawallMNG_NAM(String ftridn) {
		String FTR_NM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,FTR_NM ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				FTR_NM = Utils.chkNull(handler.getString("FTR_NM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return FTR_NM;
	}
	

////방조제 시설개요 - 관리자연락처
	public static String getSrchSeawallTEL_NUM(String ftridn) {
		String TEL_NUM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,TEL_NUM ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TEL_NUM = Utils.chkNull(handler.getString("TEL_NUM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TEL_NUM;
	}
	

////방조제 시설개요 - 착공일자
	public static String getSrchSeawallBEG_YMD(String ftridn) {
		String BEG_YMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,BEG_YMD ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				BEG_YMD = Utils.chkNull(handler.getString("BEG_YMD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return BEG_YMD;
	}
	

////방조제 시설개요 - 준공일자
	public static String getSrchSeawallFNS_YMD(String ftridn) {
		String FNS_YMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,FNS_YMD ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				FNS_YMD = Utils.chkNull(handler.getString("FNS_YMD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return FNS_YMD;
	}
	
////방조제 시설제원 - 구역면적
	public static String getSrchSeawallZON_ARA(String ftridn) {
		String ZON_ARA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,ZON_ARA ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				ZON_ARA = Utils.chkNull(handler.getString("ZON_ARA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return ZON_ARA;
	}
	
////방조제 시설제원 - 몽리면적
	public static String getSrchSeawallDEN_ARA(String ftridn) {
		String DEN_ARA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,DEN_ARA ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DEN_ARA = Utils.chkNull(handler.getString("DEN_ARA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DEN_ARA;
	}
	

////방조제 시설제원 - 방조제연장
	public static String getSrchSeawallWAL_LEN(String ftridn) {
		String WAL_LEN = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,WAL_LEN ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				WAL_LEN = Utils.chkNull(handler.getString("WAL_LEN"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return WAL_LEN;
	}
	

////방조제 시설제원 - 배수갑문 개소
	public static String getSrchSeawallGAT_NUM(String ftridn) {
		String GAT_NUM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,GAT_NUM ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				GAT_NUM = Utils.chkNull(handler.getString("GAT_NUM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return GAT_NUM;
	}
	

////방조제 시설제원 - 배수갑문 연수
	public static String getSrchSeawallGAT_EACH(String ftridn) {
		String GAT_EACH = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,GAT_EACH ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				GAT_EACH = Utils.chkNull(handler.getString("GAT_EACH"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return GAT_EACH;
	}
	

////방조제 시설제원 - 포용조수량
	public static String getSrchSeawallEBB_FLW(String ftridn) {
		String EBB_FLW = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,EBB_FLW ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				EBB_FLW = Utils.chkNull(handler.getString("EBB_FLW"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return EBB_FLW;
	}
	

////방조제 시설제원 - 대안거리
	public static String getSrchSeawallEFEC_LEN(String ftridn) {
		String EFEC_LEN = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,EFEC_LEN ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				EFEC_LEN = Utils.chkNull(handler.getString("EFEC_LEN"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return EFEC_LEN;
	}

	
////방조제 시설제원 - 제고(최고)
	public static String getSrchSeawallTOT_JEA(String ftridn) {
		String TOT_JEA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,TOT_JEA ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TOT_JEA = Utils.chkNull(handler.getString("TOT_JEA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TOT_JEA;
	}
	
	
////방조제 시설제원 - 제고(평고)
	public static String getSrchSeawallAVG_JEA(String ftridn) {
		String AVG_JEA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,AVG_JEA ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				AVG_JEA = Utils.chkNull(handler.getString("AVG_JEA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return AVG_JEA;
	}
	
////방조제 시설제원 - 여유고
	public static String getSrchSeawallSPR_JEA(String ftridn) {
		String SPR_JEA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,SPR_JEA ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				SPR_JEA = Utils.chkNull(handler.getString("SPR_JEA"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return SPR_JEA;
	}
	
////방조제 시설제원 - 편입년도
	public static String getSrchSeawallTRN_YMD(String ftridn) {
		String TRN_YMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,TRN_YMD ");
		sb.append("from ag_seawall_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			;
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TRN_YMD = Utils.chkNull(handler.getString("TRN_YMD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TRN_YMD;
	}
	
////관정
////관정 시설개요 - 시설명
	public static String getSrchTbwFTR_NM(String ftridn) {
		String FTR_NM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" select t1.ftr_nm  ");
		sb.append(" from ag_tbw_ps  t1 ");
		sb.append(" left join g2s_codeddomains t2 on t1.twm_cde = t2.g2_code  ");
		sb.append(" where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				FTR_NM = Utils.chkNull(handler.getString("FTR_NM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return FTR_NM;
	}
	
////관정 시설개요 - 주소
	public static String getSrchTbwJUSO(String ftridn) {
		String JUSO = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				JUSO = Utils.chkNull(handler.getString("JUSO"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return JUSO;
	}
	
////관정 시설개요 - 관리자
	public static String getSrchTbwMNG_NAM(String ftridn) {
		String MNG_NAM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select mng_nam ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");
		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				MNG_NAM = Utils.chkNull(handler.getString("mng_nam"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return MNG_NAM;
	}
	
////관정 시설개요 - 관리자연락처
	public static String getSrchTbwTEL_NUM(String ftridn) {
		String TEL_NUM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,TEL_NUM ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TEL_NUM = Utils.chkNull(handler.getString("TEL_NUM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TEL_NUM;
	}
	
////관정 시설개요 - 관리상황
	public static String getSrchTbwTWM_CDE(String ftridn) {
		String TWM_CDE = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" select t2.g2_value as twm_cde ");
		sb.append(" from ag_tbw_ps  t1");
		sb.append(" left join g2s_codeddomains t2 on t1.twp_cde = t2.g2_code");
		sb.append(" where FTR_IDN = '" + ftridn + "' order by pnu ");
		
		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TWM_CDE = Utils.chkNull(handler.getString("TWM_CDE"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TWM_CDE;
	}
	
////관정 시설개요 - 착공일자
	public static String getSrchTbwBEG_YMD(String ftridn) {
		String BEG_YMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,BEG_YMD ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				BEG_YMD = Utils.chkNull(handler.getString("BEG_YMD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return BEG_YMD;
	}
	
////관정 시설개요 - 준공일자
	public static String getSrchTbwFNS_YMD(String ftridn) {
		String FNS_YMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,FNS_YMD ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				FNS_YMD = Utils.chkNull(handler.getString("FNS_YMD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return FNS_YMD;
	}
	
////관정 시설개요 - 설치용도
	public static String getSrchTbwTWP_CDE(String ftridn) {
		String TWP_CDE = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" select t2.g2_value as twp_cde ");
		sb.append(" from ag_tbw_ps t1 ");
		sb.append(" left join g2s_codeddomains t2 on t1.twt_cde = t2.g2_code ");
		sb.append(" where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TWP_CDE = Utils.chkNull(handler.getString("TWP_CDE"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TWP_CDE;
	}
	
////관정 시설개요 - 시설유형
	public static String getSrchTbwTWT_CDE(String ftridn) {
		String TWT_CDE = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append(" select t2.g2_value as twt_cde ");
		sb.append(" from ag_tbw_ps  t1");
		sb.append(" left join g2s_codeddomains t2 on t1.twt_cde = t2.g2_code ");
		sb.append(" where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TWT_CDE = Utils.chkNull(handler.getString("TWT_CDE"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TWT_CDE;
	}
	
////관정 시설개요 - 생활용수(호)
	public static String getSrchTbwLIF_WAL(String ftridn) {
		String LIF_WAL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,LIF_WAL ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				LIF_WAL = Utils.chkNull(handler.getString("LIF_WAL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return LIF_WAL;
	}
	
////관정 시설개요 - 급수인구(명)
	public static String getSrchTbwPEO_NUM(String ftridn) {
		String PEO_NUM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,PEO_NUM ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				PEO_NUM = Utils.chkNull(handler.getString("PEO_NUM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return PEO_NUM;
	}
	
////관정 시설개요 - 몽리면적
	public static String getSrchTbwDEN_ARA(String ftridn) {
		String DEN_ARA = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select ROUND(den_ara,2) as den_ara ");
		sb.append(" from ag_tbw_ps  ");
		sb.append(" where FTR_IDN = '" + ftridn + "' order by pnu ");
		
		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DEN_ARA = Utils.chkNull(handler.getString("den_ara"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DEN_ARA;
	}
	
////관정 착정 및 이용시설내용 - 구경 착정
	public static String getSrchTbwDIG_CAL(String ftridn) {
		String DIG_CAL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select ROUND(DIG_CAL,2) as dig_cal  ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");
		
		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				DIG_CAL = Utils.chkNull(handler.getString("dig_cal"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return DIG_CAL;
	}
	
////관정 착정 및 이용시설내용 - 구경 우물
	public static String getSrchTbwWEL_CAL(String ftridn) {
		String WEL_CAL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select ROUND(WEL_CAL,2) as wel_cal ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				WEL_CAL = Utils.chkNull(handler.getString("wel_cal"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return WEL_CAL;
	}
	
////관정 착정 및 이용시설내용 - 심도
	public static String getSrchTbwPIP_DEP(String ftridn) {
		String PIP_DEP = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select ROUND(PIP_DEP,2) as pip_dep ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				PIP_DEP = Utils.chkNull(handler.getString("pip_dep"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return PIP_DEP;
	}
	
////관정 착정 및 이용시설내용 - 채수량
	public static String getSrchTbwWTS_CAR(String ftridn) {
		String WTS_CAR = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select ROUND(WTS_CAR,2) AS WTS_CAR ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				WTS_CAR = Utils.chkNull(handler.getString("WTS_CAR"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return WTS_CAR;
	}
	
////관정 착정 및 이용시설내용 - 전동기 일반
	public static String getSrchTbwREG_MOT(String ftridn) {
		String REG_MOT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,REG_MOT ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				REG_MOT = Utils.chkNull(handler.getString("REG_MOT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return REG_MOT;
	}
	
////관정 착정 및 이용시설내용 - 전동기 수중
	public static String getSrchTbwUND_MOT(String ftridn) {
		String UND_MOT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,UND_MOT ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				UND_MOT = Utils.chkNull(handler.getString("UND_MOT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return UND_MOT;
	}
	
////관정 착정 및 이용시설내용 - 원동기 엔진
	public static String getSrchTbwENG_MOR(String ftridn) {
		String ENG_MOR = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,ENG_MOR ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				ENG_MOR = Utils.chkNull(handler.getString("ENG_MOR"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return ENG_MOR;
	}
	
////관정 착정 및 이용시설내용 - 원동기 양수기
	public static String getSrchTbwWAT_MOR(String ftridn) {
		String WAT_MOR = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,WAT_MOR ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				WAT_MOR = Utils.chkNull(handler.getString("WAT_MOR"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return WAT_MOR;
	}
	
////관정 착정 및 이용시설내용 - 전기인입거리
	public static String getSrchTbwELE_DTN(String ftridn) {
		String ELE_DTN = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select ROUND(ELE_DTN,2) AS ELE_DTN ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				ELE_DTN = Utils.chkNull(handler.getString("ELE_DTN"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return ELE_DTN;
	}
	
////관정 착정 및 이용시설내용 - 송수거리
	public static String getSrchTbwSUP_DTN(String ftridn) {
		String SUP_DTN = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select ROUND(SUP_DTN,2) as SUP_DTN ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				SUP_DTN = Utils.chkNull(handler.getString("SUP_DTN"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return SUP_DTN;
	}
	
////관정 착정 및 이용시설내용 - 오염방지시설
	public static String getSrchTbwPOL_FAC(String ftridn) {
		String POL_FAC = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,POL_FAC ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				POL_FAC = Utils.chkNull(handler.getString("POL_FAC"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return POL_FAC;
	}
	
	
////관정 착정 및 이용시설내용 - 자연수위
	public static String getSrchTbwNAT_WAL(String ftridn) {
		String NAT_WAL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select ROUND(NAT_WAL,2) AS NAT_WAL ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				NAT_WAL = Utils.chkNull(handler.getString("NAT_WAL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return NAT_WAL;
	}
	
	
////관정 착정 및 이용시설내용 - 안정수위
	public static String getSrchTbwSAF_WAL(String ftridn) {
		String SAF_WAL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select ROUND(SAF_WAL,2) AS SAF_WAL ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				SAF_WAL = Utils.chkNull(handler.getString("SAF_WAL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return SAF_WAL;
	}
	
	
////관정 착정 및 이용시설내용 - 우물자재PIPE
	public static String getSrchTbwWEL_WAL(String ftridn) {
		String WEL_WAL = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select ROUND(WEL_WAL,2) AS WEL_WAL ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				WEL_WAL = Utils.chkNull(handler.getString("WEL_WAL"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return WEL_WAL;
	}
	
	
////관정 착정 및 이용시설내용 - 유공관
	public static String getSrchTbwPFE_PMP(String ftridn) {
		String PFE_PMP = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select ROUND(PFE_PMP,2) AS PFE_PMP ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				PFE_PMP = Utils.chkNull(handler.getString("PFE_PMP"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return PFE_PMP;
	}
	
	
////관정 착정 및 이용시설내용 - 기타사항
	public static String getSrchTbwETC(String ftridn) {
		String ETC = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,ETC ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				ETC = Utils.chkNull(handler.getString("ETC"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return ETC;
	}
	
	
////관정 착정 및 이용시설내용 - 일자
	public static String getSrchTbwCMP_YMD(String ftridn) {
		String CMP_YMD = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,CMP_YMD ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CMP_YMD = Utils.chkNull(handler.getString("CMP_YMD"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return CMP_YMD;
	}
	
	
////관정 착정 및 이용시설내용 - 직급
	public static String getSrchTbwLNK_NM(String ftridn) {
		String LNK_NM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,LNK_NM ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				LNK_NM = Utils.chkNull(handler.getString("LNK_NM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return LNK_NM;
	}
	
	
////관정 착정 및 이용시설내용 - 성명
	public static String getSrchTbwCMP_NM(String ftridn) {
		String CMP_NM = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,CMP_NM ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CMP_NM = Utils.chkNull(handler.getString("CMP_NM"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return CMP_NM;
	}
	
	
////관정 착정 및 이용시설내용 - 국비보조
	public static String getSrchTbwNAT_AMT(String ftridn) {
		String NAT_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,NAT_AMT ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				NAT_AMT = Utils.chkNull(handler.getString("NAT_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return NAT_AMT;
	}
	
	
////관정 착정 및 이용시설내용 - 지방비
	public static String getSrchTbwCIT_AMT(String ftridn) {
		String CIT_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,CIT_AMT ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				CIT_AMT = Utils.chkNull(handler.getString("CIT_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return CIT_AMT;
	}
	
	
////관정 착정 및 이용시설내용 - 융자
	public static String getSrchTbwBND_AMT(String ftridn) {
		String BND_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,BND_AMT ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				BND_AMT = Utils.chkNull(handler.getString("BND_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return BND_AMT;
	}
	
////관정 착정 및 이용시설내용 - 자부담등
	public static String getSrchTbwSEL_AMT(String ftridn) {
		String SEL_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,SEL_AMT ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				SEL_AMT = Utils.chkNull(handler.getString("SEL_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return SEL_AMT;
	}
	
	
////관정 착정 및 이용시설내용 - 계
	public static String getSrchTbwTCT_AMT(String ftridn) {
		String TCT_AMT = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select FTR_IDN, FTR_NM, JUSO,TCO_AMT ");
		sb.append("from ag_tbw_ps  ");
		sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				TCT_AMT = Utils.chkNull(handler.getString("TCO_AMT"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return TCT_AMT;
	}
	
	
////저수지 저수량 추가 시설명 가져옴
	public static String getSrchRes_ftr_nm(String ftr_idn) {
		String ftr_nm = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select ftr_nm ");
		sb.append("from  ag_reservoir_as ");
		sb.append("where FTR_IDN = '" + ftr_idn + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				ftr_nm = Utils.chkNull(handler.getString("ftr_nm"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return ftr_nm;
	}
	
////저수지 저수량 추가 시설명 가져옴
	public static String getSrchRes_juso(String ftr_idn) {
		String juso = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select juso ");
		sb.append("from  ag_reservoir_as ");
		sb.append("where FTR_IDN = '" + ftr_idn + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				juso = Utils.chkNull(handler.getString("juso"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return juso;
	}
	
////저수지 저수량 추가 총 저수량
	public static String getSrchRes_rsv_vol(String ftr_idn) {
		String rsv_vol = "";

		DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();

		sb.append("select rsv_vol ");
		sb.append("from  ag_reservoir_as ");
		sb.append("where FTR_IDN = '" + ftr_idn + "' ");

		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				rsv_vol = Utils.chkNull(handler.getString("rsv_vol"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}
		return rsv_vol;
	}
	 /**
		 * 저수량  관리번호를 가져온다.
		 * @return
		 */
	   
		public static int getidnCount() {
			DBHandler handler = new DBHandler();
	        StringBuilder sb = new StringBuilder();
	        int idn = 0;
	        
	        sb.append(" select max(rid)+1 as value from reservoir_mtce " );
	        //시퀀스 _gid와 관리번호를 찾아 1씩증가한다.
	        
			try {
				handler.open(Const.CONTEXT_NAME);
				handler.setQuery(sb.toString());
				handler.execute();
				if (handler.next()) {
					
					idn = Integer.parseInt(Utils.chkNull(handler.getString("value")));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				handler.close();
			}

			return idn;
		}
		//저수지 YorN
		public static String getSrchRes_ftr_gbn(String ftr_idn) {
			DBHandler handler = new DBHandler();
			StringBuilder sb = new StringBuilder();
			String auth = "N";// 권한 초기 값

			// 저수지 ftr_idn 값이있으면 Y 없으면 N
			sb.append(" select ftr_cde from ag_reservoir_as where 1=1  and ftr_idn='"+ftr_idn+"' and ftr_cde='FTR001' ");

			try {
				handler.open(Const.CONTEXT_NAME);
				handler.setQuery(sb.toString());
				handler.execute();
				while (handler.next()) {

					String r_auth_yn = Utils.chkNull(handler.getString("ftr_cde"));
					// r_auth_yn에 들어있는 값이 Y인지 찾음 찾으면 "Y"와 비교하여 같으면 auth에 넣어줌
					if ("FTR001".equals(r_auth_yn)) {
						auth = r_auth_yn;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				handler.close();
			}
			return auth;
		}
		
		public static int getSrch_rid() {
			DBHandler handler = new DBHandler();
	        StringBuilder sb = new StringBuilder();
	        int idn = 0;
	        
	        sb.append(" select rid from reservoir_mtce " );
	        
			try {
				handler.open(Const.CONTEXT_NAME);
				handler.setQuery(sb.toString());
				handler.execute();
				if (handler.next()) {
					
					idn = Integer.parseInt(Utils.chkNull(handler.getString("rid")));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				handler.close();
			}

			return idn;
		}

	////주상도l1
		public static String getSrchJoosangL1(String ftridn) {
			String data = "";

			DBHandler handler = new DBHandler();
			StringBuilder sb = new StringBuilder();

			sb.append("select l1 as data ");
			sb.append("from ag_tbw_ps  ");
			sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

			try {
				handler.open(Const.CONTEXT_NAME);
				handler.setQuery(sb.toString());
				handler.execute();
				if (handler.next()) {
					data = Utils.chkNull(handler.getString("data"));

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				handler.close();
			}
			return data;
		}
		////주상도l1_v
		public static String getSrchJoosangL1_v(String ftridn) {
			String data = "";

			DBHandler handler = new DBHandler();
			StringBuilder sb = new StringBuilder();

			sb.append("select l1_v as data ");
			sb.append("from ag_tbw_ps  ");
			sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

			try {
				handler.open(Const.CONTEXT_NAME);
				handler.setQuery(sb.toString());
				handler.execute();
				if (handler.next()) {
					data = Utils.chkNull(handler.getString("data"));

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				handler.close();
			}
			return data;
		}
		////그라우팅
		public static String getSrchJoosangGrouting(String ftridn) {
			String data = "";

			DBHandler handler = new DBHandler();
			StringBuilder sb = new StringBuilder();

			sb.append("select grouting as data ");
			sb.append("from ag_tbw_ps  ");
			sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

			try {
				handler.open(Const.CONTEXT_NAME);
				handler.setQuery(sb.toString());
				handler.execute();
				if (handler.next()) {
					data = Utils.chkNull(handler.getString("data"));

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				handler.close();
			}
			return data;
		}
		////주상도l2
		public static String getSrchJoosangL2(String ftridn) {
			String data = "";

			DBHandler handler = new DBHandler();
			StringBuilder sb = new StringBuilder();

			sb.append("select l2 as data ");
			sb.append("from ag_tbw_ps  ");
			sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

			try {
				handler.open(Const.CONTEXT_NAME);
				handler.setQuery(sb.toString());
				handler.execute();
				if (handler.next()) {
					data = Utils.chkNull(handler.getString("data"));

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				handler.close();
			}
			return data;
		}
	 ////주상도l2_v
			public static String getSrchJoosangL2_v(String ftridn) {
				String data = "";

				DBHandler handler = new DBHandler();
				StringBuilder sb = new StringBuilder();

				sb.append("select l2_v as data ");
				sb.append("from ag_tbw_ps  ");
				sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

				try {
					handler.open(Const.CONTEXT_NAME);
					handler.setQuery(sb.toString());
					handler.execute();
					if (handler.next()) {
						data = Utils.chkNull(handler.getString("data"));

					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					handler.close();
				}
				return data;
			}
		////케이싱
			public static String getSrchJoosangCasing(String ftridn) {
				String data = "";

				DBHandler handler = new DBHandler();
				StringBuilder sb = new StringBuilder();

				sb.append("select casing as data ");
				sb.append("from ag_tbw_ps  ");
				sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

				try {
					handler.open(Const.CONTEXT_NAME);
					handler.setQuery(sb.toString());
					handler.execute();
					if (handler.next()) {
						data = Utils.chkNull(handler.getString("data"));

					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					handler.close();
				}
				return data;
			}
			
		////주상도l3
			public static String getSrchJoosangL3(String ftridn) {
				String data = "";

				DBHandler handler = new DBHandler();
				StringBuilder sb = new StringBuilder();

				sb.append("select l3 as data ");
				sb.append("from ag_tbw_ps  ");
				sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

				try {
					handler.open(Const.CONTEXT_NAME);
					handler.setQuery(sb.toString());
					handler.execute();
					if (handler.next()) {
						data = Utils.chkNull(handler.getString("data"));

					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					handler.close();
				}
				return data;
			}
		 ////주상도l3_v
				public static String getSrchJoosangL3_v(String ftridn) {
					String data = "";

					DBHandler handler = new DBHandler();
					StringBuilder sb = new StringBuilder();

					sb.append("select l3_v as data ");
					sb.append("from ag_tbw_ps  ");
					sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

					try {
						handler.open(Const.CONTEXT_NAME);
						handler.setQuery(sb.toString());
						handler.execute();
						if (handler.next()) {
							data = Utils.chkNull(handler.getString("data"));

						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						handler.close();
					}
					return data;
				}
			////주상도l4
				public static String getSrchJoosangL4(String ftridn) {
					String data = "";

					DBHandler handler = new DBHandler();
					StringBuilder sb = new StringBuilder();

					sb.append("select l4 as data ");
					sb.append("from ag_tbw_ps  ");
					sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

					try {
						handler.open(Const.CONTEXT_NAME);
						handler.setQuery(sb.toString());
						handler.execute();
						if (handler.next()) {
							data = Utils.chkNull(handler.getString("data"));

						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						handler.close();
					}
					return data;
				}
			 ////주상도l4_v
					public static String getSrchJoosangL4_v(String ftridn) {
						String data = "";

						DBHandler handler = new DBHandler();
						StringBuilder sb = new StringBuilder();

						sb.append("select l4_v as data ");
						sb.append("from ag_tbw_ps  ");
						sb.append("where FTR_IDN = '" + ftridn + "' order by pnu ");

						try {
							handler.open(Const.CONTEXT_NAME);
							handler.setQuery(sb.toString());
							handler.execute();
							if (handler.next()) {
								data = Utils.chkNull(handler.getString("data"));

							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							handler.close();
						}
						return data;
					}
}