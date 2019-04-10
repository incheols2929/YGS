package geomex.svc.handler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class GetBldgXML {

    public String getBldgXML(String lrgstURL, String type, String key, String subKey) {

        String xml = "";
        StringBuilder sb = new StringBuilder();
        URL url = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            //url = new URL("http://106.0.2.131/dxml/dxml_check.jsp?pkey="+KEY+"&pagetype=tojidaejangprint&pnu="+pnu);
            if (type.equals("GetBldgList")) {
                url = new URL(lrgstURL + "ctrl?svc=" + type + "&pnu=" + key);
            } else if (type.equals("GetDjyexpos")) {
                url = new URL(lrgstURL + "ctrl?svc=" + type + "&key=" + key + "&ouln_pk=" + subKey);
            } else if (type.equals("GetDjytitle")) {
                url = new URL(lrgstURL + "ctrl?svc=" + type + "&key=" + key);
            } else {
                url = new URL(lrgstURL + "ctrl?svc=" + type + "&key=" + key);
            }
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

            xml = sb.toString();

            br.close();
            isr.close();
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {}

        return xml;
    }
}
