package geomex.pkg.srch;

import geomex.svc.webctrl.Handler;
import geomex.svc.handler.Code;
import geomex.svc.webctrl.WebUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 * @작성일:2016. 9. 28.
 * @작성자:신인철
 * @이메일:zlola@naver.com
 * @변경이력: v1
 * @Method설명: 저수지 시설물의 데이터를 찾음
 */
public class GetFaciliAddrSrchList implements Handler {

	@Override
	public void perform(Map<String, String> kvp, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String sgg = kvp.get("SGG");
		String umd = kvp.get("UMD");
		String ri = kvp.get("RI");
		String facility = kvp.get("FACILITY");
		String ftr_nm = kvp.get("FTRNM");
		String dcode = "";
		StringBuilder sb = new StringBuilder();
		String ri_code = "";
		if ("".equals(umd)) {
			dcode = sgg;
		} else {
			dcode = umd;
		}

		if ("".equals(ri)) {
			ri_code = umd;
			dcode = ri_code.substring(0, 8);
			System.out.println("문자 자르기" + ri_code );
		} else {
			dcode = ri;
		}
	
		String lyr_nm = Code.getTableCode(facility);
		String tbl_nm = Code.getLyrNM(lyr_nm);
		String lyr_id = Code.getLyrID(lyr_nm);
		
		ConditionSearchBean CS = new ConditionSearchBean();
		ArrayList<ConditionSearch> cslist = CS.getSrchjibun3(dcode, ftr_nm, tbl_nm, lyr_id);

		
		sb.append("<srch-list>");
		for (int i = 0; i < cslist.size(); i++) {
			sb.append("<시설물>");
			sb.append("<시설구분>").append(cslist.get(i).getFtr_cde()).append("</시설구분>");
			sb.append("<리코드>").append(cslist.get(i).getRi_cde()).append("</리코드>");
			sb.append("<주소>").append(cslist.get(i).getJuso()).append("</주소>");
			sb.append("<시설명>").append(cslist.get(i).getFtr_nm()).append("</시설명>");
			sb.append("<관리번호>").append(cslist.get(i).getFtr_idn()).append("</관리번호>");
			sb.append("<레이어명>").append(cslist.get(i).getLyr_nm()).append("</레이어명>");
            sb.append("<레이어ID>").append(cslist.get(i).getLyr_id()).append("</레이어ID>");
            sb.append("<결과값>").append(cslist.get(i).getCnt()).append("</결과값>");
            sb.append("<_gid>").append(cslist.get(i).getGid()).append("</_gid>");
			sb.append("</시설물>");
		}
		sb.append("</srch-list>");

		WebUtil.sendNoneHeaderXML(res, sb.toString()); // xml정보를 클라이언트로 전송

	}

}
/*
 * public class GetFaciliAddrSrchList implements Handler {
 * 
 * @Override public void perform(Map<String, String> kvp, HttpServletResponse
 * res) throws ServletException, IOException { // TODO Auto-generated method
 * stub
 * 
 * String sgg = kvp.get("SGG"); String umd = kvp.get("UMD"); String ri =
 * kvp.get("RI"); String facility = kvp.get("FACILITY"); String rsvnm
 * =kvp.get("RSVNM");
 * 
 * 
 * 
 * 
 * 시군이 정해지면 관련된 읍면동만 검색되고 관련된 리만 검색될수 있게 해줌 jibun_cnt.jsp에 있는 기능과 같음
 * 
 * String dcode = ""; if ("".equals(umd)) { dcode = sgg; } else { dcode = umd; }
 * 
 * if ("".equals(ri)) { dcode = umd; } else { dcode = ri; }
 * 
 * ConditionSearchBean CS = new ConditionSearchBean();
 * ArrayList<ConditionSearch> cslist = CS.getSrchjibun3(dcode,facility,rsvnm);
 * 
 * StringBuilder sb = new StringBuilder(); sb.append("<srch-list>"); for (int i
 * = 0; i < cslist.size(); i++) { sb.append("<시설물>");
 * sb.append("<관리번호>").append(cslist.get(i).getFtr_idn()).append("</관리번호>");
 * sb.append("<관리기관>").append(cslist.get(i).getMng_cde()).append("</관리기관>");
 * sb.append("<PNU>").append(cslist.get(i).getPnu()).append("</PNU>");
 * sb.append("<주소>").append(cslist.get(i).getJuso()).append("</주소>");
 * sb.append("<시설명>").append(cslist.get(i).getFtr_nm()).append("</시설명>");
 * sb.append("<관리자>").append(cslist.get(i).getMng_nam()).append("</관리자>");
 * sb.append("<관리자연락처>").append(cslist.get(i).getTel_num()).append("</관리자연락처>");
 * sb.append("<착공일자>").append(cslist.get(i).getBeg_ymd()).append("</착공일자>");
 * sb.append("<준공일자>").append(cslist.get(i).getFns_ymd()).append("</준공일자>");
 * sb.append("<저수량>").append(cslist.get(i).getRsv_vol()).append("</저수량>");
 * sb.append("<단위저수량>").append(cslist.get(i).getPdg_unt()).append("</단위저수량>");
 * sb.append("<만수면적>").append(cslist.get(i).getFul_ara()).append("</만수면적>");
 * sb.append("<형식>").append(cslist.get(i).getForm_exp()).append("</형식>");
 * sb.append("<제당연장>").append(cslist.get(i).getByc_len()).append("</제당연장>");
 * sb.append("<제고최고>").append(cslist.get(i).getTot_jea()).append("</제고최고>");
 * sb.append("<제고평고>").append(cslist.get(i).getAvg_jea()).append("</제고평고>");
 * sb.append("<정폭>").append(cslist.get(i).getWid_len()).append("</정폭>");
 * sb.append("<법면내제>").append(cslist.get(i).getLat_in_sug()).append("</법면내제>");
 * sb.append("<법면외제>").append(cslist.get(i).getLat_out_su()).append("</법면외제>");
 * sb
 * .append("<보호공내제>").append(cslist.get(i).getSlo_in_sug()).append("</보호공내제>");
 * sb
 * .append("<보호공외제>").append(cslist.get(i).getSlo_out_su()).append("</보호공외제>");
 * sb.append("<유역면적>").append(cslist.get(i).getRsv_ara()).append("</유역면적>");
 * sb.append("<홍수량>").append(cslist.get(i).getDra_wal()).append("</홍수량>");
 * sb.append("<일류수심>").append(cslist.get(i).getOrf_dth()).append("</일류수심>");
 * sb.append("<구조형식>").append(cslist.get(i).getSct_cmd()).append("</구조형식>");
 * sb.append("<언체연장>").append(cslist.get(i).getWsw_len()).append("</언체연장>");
 * sb.append
 * ("<언체고최고>").append(cslist.get(i).getMax_un_dra()).append("</언체고최고>");
 * sb.append
 * ("<언체고평균>").append(cslist.get(i).getAvg_un_dra()).append("</언체고평균>");
 * sb.append("<방수로연장>").append(cslist.get(i).getRen_dra()).append("</방수로연장>");
 * sb.append("<방수로폭최대>").append(cslist.get(i).getMax_dra()).append("</방수로폭최대>");
 * sb.append("<방수로폭최소>").append(cslist.get(i).getMin_dra()).append("</방수로폭최소>");
 * sb.append("<방수로폭평균>").append(cslist.get(i).getAvg_dra()).append("</방수로폭평균>");
 * sb.append("<홍수위상제고>").append(cslist.get(i).getWtr_dra()).append("</홍수위상제고>");
 * sb.append("<통관형식>").append(cslist.get(i).getCde_wtl()).append("</통관형식>");
 * sb.append("<통관연장>").append(cslist.get(i).getRen_wtl()).append("</통관연장>");
 * sb.append("<통관단면h>").append(cslist.get(i).getSec_h_wtl()).append("</통관단면h>");
 * sb.append("<통관단면b>").append(cslist.get(i).getSec_b_wtl()).append("</통관단면b>");
 * sb.append("<통관단면p>").append(cslist.get(i).getSec_p_wtl()).append("</통관단면p>");
 * sb.append("<취수시설구조>").append(cslist.get(i).getStr_wtl()).append("</취수시설구조>");
 * sb.append("<취수공개소>").append(cslist.get(i).getSec_wtl()).append("</취수공개소>");
 * sb.append("<취수공규격>").append(cslist.get(i).getStd_wtl()).append("</취수공규격>");
 * sb.append("<취수능력>").append(cslist.get(i).getInt_wtl()).append("</취수능력>");
 * sb.append("<용수간선>").append(cslist.get(i).getMai_way()).append("</용수간선>");
 * sb.append("<용수지선>").append(cslist.get(i).getBra_way()).append("</용수지선>");
 * sb.append("<추도>").append(cslist.get(i).getChu_way()).append("</추도>");
 * sb.append("<도수로>").append(cslist.get(i).getRac_way()).append("</도수로>");
 * sb.append("<송수로>").append(cslist.get(i).getAqu_way()).append("</송수로>");
 * sb.append("<배수로>").append(cslist.get(i).getDrain_way()).append("</배수로>");
 * sb.append("<인가>").append(cslist.get(i).getStr_ara()).append("</인가>");
 * sb.append("<내한면적>").append(cslist.get(i).getVis_ara()).append("</내한면적>");
 * sb.append("<부족면적>").append(cslist.get(i).getTri_ara()).append("</부족면적>");
 * sb.append("<시공자>").append(cslist.get(i).getOpr_nam()).append("</시공자>");
 * sb.append("<도급자>").append(cslist.get(i).getGcn_nam()).append("</도급자>");
 * sb.append("<국비보조>").append(cslist.get(i).getNat_amt()).append("</국비보조>");
 * sb.append("<융자>").append(cslist.get(i).getBnd_amt()).append("</융자>");
 * sb.append("<시군도비>").append(cslist.get(i).getCit_amt()).append("</시군도비>");
 * sb.append("<자부담>").append(cslist.get(i).getSel_amt()).append("</자부담>");
 * sb.append("<기타>").append(cslist.get(i).getCet_amt()).append("</기타>");
 * sb.append("<합계>").append(cslist.get(i).getTct_amt()).append("</합계>");
 * sb.append("</시설물>"); } sb.append("</srch-list>");
 * 
 * WebUtil.sendNoneHeaderXML(res, sb.toString()); //xml정보를 클라이언트로 전송
 * 
 * }
 * 
 * }
 */
