package geomex.pkg.srch;

import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;

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
 * 다음 wgs84좌표를 tm좌표로 변환한다.
 * 
 * @author 최기연
 */
public class GetDaumCoord implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO 처리 모듈 구현하기
        // 아래는 테스트

        String x = kvp.get("X");
        String y = kvp.get("Y");

        String apikey = kvp.get("APIKEY");
        String fromCoord = kvp.get("FROMCOORD");
        String toCoord = kvp.get("TOCOORD");
        String output = kvp.get("OUTPUT");

        URL url = null;
        StringBuilder sb = new StringBuilder();
        
 

     //System.out.println(">>>>>>>>>>>>http://apis.daum.net/local/geo/transcoord?apikey="+apikey+"&x="+x+"&y="+y+"&fromCoord="+fromCoord+"&toCoord="+toCoord+"&output="+output);

        try {
            url = new URL("http://apis.daum.net/local/geo/transcoord?apikey="
                + apikey + "&x=" + x + "&y=" + y + "&fromCoord=" + fromCoord + "&toCoord=" + toCoord + "&output=" + output);

      
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

        //System.out.println(sb.toString());
        WebUtil.sendNoneHeaderXML(res, sb.toString());
        //WebUtil.sendXML(res, sb.toString());
    }
}
