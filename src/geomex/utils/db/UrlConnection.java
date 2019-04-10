package geomex.utils.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import geomex.utils.Utils;

public class UrlConnection {

    public static StringBuffer getUrlContent(String sUrl, String params) {
        return getUrlContent(sUrl, params, "utf-8");
    }

    public static StringBuffer getUrlContent(String sUrl, String params, String chDecoder) {

        URL url = null;
        URLConnection urlConnection = null;
        StringBuffer sb = new StringBuffer();
        if ("".equals(Utils.chkNull(chDecoder))) chDecoder = "utf-8";
        try {
            // Get방식으로 전송 하기
            url = new URL(sUrl + "?" + params);
            urlConnection = url.openConnection();
            sb.append(printByInputStream(urlConnection.getInputStream(), chDecoder));

            // Post방식으로 전송 하기
            //            url = new URL(sUrl);
            //            urlConnection = url.openConnection();
            //            urlConnection.setDoOutput(true);
            //            printByOutputStream(urlConnection.getOutputStream(), params);
            //            printByInputStream(urlConnection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb;
    }

    // 웹 서버로 부터 받은 웹 페이지 결과를 콘솔에 출력하는 메소드
    private static StringBuffer printByInputStream(InputStream is, String chDecoder) {

        StringBuffer text = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, chDecoder));
            String i = "";

            while ((i = reader.readLine()) != null) {
                text.append(i + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    // 웹 서버로 파라미터명과 값의 쌍을 전송하는 메소드
    public void printByOutputStream(OutputStream os, String msg) {
        try {
            byte[] msgBuf = msg.getBytes("UTF-8");
            os.write(msgBuf, 0, msgBuf.length);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
