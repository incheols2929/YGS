package geomex.pkg.srch;

import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * 다음 wgs84좌표를 tm좌표로 변환한다.
 * 
 * @author 최기연
 */
public class GetDaumAddrCoord implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO 처리 모듈 구현하기
        // 아래는 테스트

        String q = URLDecoder.decode(kvp.get("Q"), "utf-8");
        String key = new String(kvp.get("KEY").getBytes("8859_1"), "UTF-8");
        String stnum = kvp.get("STNUM");
        String pagenum = kvp.get("PAGENUM");

        String str_url = "http://apis.daum.net/local/geo/addr2coord?apikey="
            + key + "&q=" + URLEncoder.encode(q, "UTF-8") + "&output=xml&result=" + pagenum + "&pageno=" + stnum;

        URL url = null;
        StringBuilder sb = new StringBuilder();

        try {
            url = new URL(str_url);
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            while (true) {
                String inStr = br.readLine();
                if (inStr != null) {
                    sb.append(inStr + "\r\n");
                } else {
                    break;
                }
            }

            br.close();
            isr.close();
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        WebUtil.sendNoneHeaderXML(res, sb.toString());
    }
}
