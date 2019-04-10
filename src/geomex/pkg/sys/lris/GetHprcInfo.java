package geomex.pkg.sys.lris;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;

/**
 * 개별주택가격 정보
 * @return HPRC XML
 */
public class GetHprcInfo implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        String pnu = kvp.get("PNU");
        StringBuilder sb = new StringBuilder();
        AhpdHprcBean ah = new AhpdHprcBean();
        
        try {
        	ArrayList<AhpdHprc> list = ah.getHprcList(pnu);
        	
            sb.append("<개별주택가격>");
            	for(int i=0; i<list.size(); i++){
            		sb.append("<주택가격정보>");
            			sb.append("<토지코드>").append(list.get(i).land_cd).append("</토지코드>");
            			sb.append("<건물고유번호>").append(list.get(i).bldg_regno).append("</건물고유번호>");
            			sb.append("<동일련번호>").append(list.get(i).dong_seqno).append("</동일련번호>");
            			sb.append("<기준월>").append(list.get(i).base_mon).append("</기준월>");
            			sb.append("<기준년도>").append(list.get(i).hprc_year).append("</기준년도>");
            			sb.append("<읍면동>").append(list.get(i).umd_cd).append("</읍면동>");
            			sb.append("<리>").append(list.get(i).ri_cd).append("</리>");
            			sb.append("<토지구분>").append(list.get(i).land_gbn).append("</토지구분>");
            			sb.append("<본번>").append(list.get(i).bobn).append("</본번>");
            			sb.append("<부번>").append(list.get(i).bubn).append("</부번>");
            			sb.append("<주택공시가격>").append(list.get(i).hprc).append("</주택공시가격>");
            			sb.append("<표준주택여부>").append(list.get(i).pyo_yn).append("</표준주택여부>");
            			sb.append("<공시여부>").append(list.get(i).pann_yn).append("</공시여부>");
            			sb.append("<토지대장면적>").append(list.get(i).land_area).append("</토지대장면적>");
            			sb.append("<토지산정면적>").append(list.get(i).calc_area).append("</토지산정면적>");
            			sb.append("<연면적>").append(list.get(i).bldg_garea).append("</연면적>");
            			sb.append("<주거용도면적>").append(list.get(i).res_area).append("</주거용도면적>");
            		sb.append("</주택가격정보>");
            	}
            sb.append("</개별주택가격>");
        } catch (Exception e) {
            // TODO: handle exception
        }

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }

}
