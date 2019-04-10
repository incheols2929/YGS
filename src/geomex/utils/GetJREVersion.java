package geomex.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import geomex.svc.webctrl.Handler;
import geovlet.utils.IOUtil;

public class GetJREVersion implements Handler {

    private static final String URL = "http://java.com/applet/JreCurrentVersion2.txt";

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {

        BufferedReader is = null;
        PrintWriter os = null;
        HttpURLConnection http = null;

        try {
            http = (HttpURLConnection) new URL(URL).openConnection();
            is = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String version = is.readLine();
            os = res.getWriter();
            os.write(version);
            os.flush();
        } catch (IOException e) {
            is = null;
        } finally {
            IOUtil.close(os);
            IOUtil.close(is);
            if (http != null) {
                try {
                    http.disconnect();
                } catch (Exception e) {}
            }
        }
    }

}
