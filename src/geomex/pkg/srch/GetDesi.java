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
 * 네이버 명칭검색
 * 
 * @author 최기연
 */
public class GetDesi implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO 처리 모듈 구현하기
        // 아래는 테스트

        String designation = URLDecoder.decode(kvp.get("QUERY"), "utf-8");
        String key = new String(kvp.get("KEY").getBytes("8859_1"), "UTF-8");
        String stnum = kvp.get("STNUM");
        String pagenum = kvp.get("PAGENUM");

        //String designation = kvp.get("QUERY");
        //String key =  kvp.get("KEY");
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

        URL url = null;
        StringBuilder sb = new StringBuilder();

        try {

            url = new URL("http://openapi.naver.com/search?key="
                + key + "&query=" + URLEncoder.encode("완도군" + designation, "UTF-8") + "&target=local&start=" + stnum + "&display="
                + pagenum);
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            while (true) {
                String inStr = br.readLine();
                if (inStr != null) {
                    if (!xml.equals(inStr)) {
                        sb.append(inStr + "\r\n");
                    }
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
        //WebUtil.sendError(res, Const.ERR_INVALID_REQUEST);
    }
}
