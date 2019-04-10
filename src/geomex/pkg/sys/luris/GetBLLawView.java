package geomex.pkg.sys.luris;

import geomex.pkg.usr.UseLogBean;
import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;
import geomex.utils.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * 행위제한법령정보를 가지고온다.
 * 
 * @author 최기연
 */
public class GetBLLawView implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO 처리 모듈 구현하기
        // 아래는 테스트
        String pnu = kvp.get("PNU"); //PNU값
        String ucode = kvp.get("UCODE"); //지역지구 코드
        //String authCd = pnu.substring(0, 5);
        String usrid = kvp.get("USRID"); //사용자아이디
        String isClick = kvp.get("ISCLICK"); //사용자아이디
        String key = "SENTLVNZU1RFTS1TRUNSRVQtS0VZ"; //행위제한법령정보 키값

        URL url = null;
        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        //시스템연계로그정보
        String worknm = "행위제한법령정보조회";
        String org_cd = "00000";
        String sys_cd = "LRIS";
        String link_typ = "WBV";
        String stime = Utils.getStrSec();

        UseLogBean UB = new UseLogBean();
        UB.setLinkLogInsert(usrid, org_cd, sys_cd, link_typ, worknm, stime);

        String rslt_cd = "";
        String err_desc = "";
        String etime = "";
        String logtime = "";

        try {

            //url = new URL(luris_url+"Rest/BL/BLLawView?ucodes=" + ucode + "&authCd=" + authCd + "&isClick="+isClick+"&key=" + key + "");
            url = new URL("http://25.151.218.239:8080/dxml/dxml_check.jsp?pkey="
                + key + "&pagetype=bllawview&pnu=" + pnu + "&ucode=" + ucode + "&isclick=" + isClick);
            System.out.println(url);
            is = url.openStream();
            isr = new InputStreamReader(is, "UTF-8");
            br = new BufferedReader(isr);

            while (true) {
                String inStr = br.readLine();
                if (inStr != null) {
                    sb.append(inStr + "\r\n");
                } else {
                    break;
                }
            }

            rslt_cd = "S";
            etime = Utils.getStrSec();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            rslt_cd = "F";
            err_desc = e.toString();
        } finally {
            br.close();
            isr.close();
            is.close();
        }

        //시스템연계로그기록
        logtime = Utils.getStrSec();
        UB.setLinkLogUpdate(usrid, stime, rslt_cd, etime, err_desc, logtime);

        WebUtil.sendNoneHeaderXML(res, sb.toString());
    }
}
