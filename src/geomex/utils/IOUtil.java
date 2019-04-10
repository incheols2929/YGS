package geomex.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * Input/Output(Exception)관련 유틸리티 클래스
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class IOUtil {

    /**
     * Throwable 정보를 문자열로 변환한다.
     * 
     * @param e Throwable
     * @return String Exception 정보문자열
     */
    public static String getStackTrace(Throwable e) {
        java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
        java.io.PrintWriter writer = new java.io.PrintWriter(bos);
        e.printStackTrace(writer);
        writer.flush();
        return bos.toString();
    }

    /**
     * InputStream Data를 OutputStream으로 복사한다.
     * 
     * @param in
     * @param out
     * @throws IOException
     */
    public static void copyStream(InputStream in, OutputStream out)
        throws IOException {
        byte buf[] = new byte[4096];
        int len = 0;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.flush();
    }

    /**
     * OutputStream을 닫는다.
     * 
     * @param os
     */
    public static void close(OutputStream os) {
        if (os == null)
            return;
        try {
            os.close();
        } catch (Exception e) {}
    }

    /**
     * InputStream을 닫는다.
     * 
     * @param is
     */
    public static void close(InputStream is) {
        if (is == null)
            return;
        try {
            is.close();
        } catch (Exception e) {}
    }

    /**
     * Writer를 닫는다.
     * 
     * @param w
     */
    public static void close(Writer w) {
        if (w == null)
            return;
        try {
            w.close();
        } catch (Exception e) {}
    }

    /**
     * Reader를 닫는다.
     * 
     * @param r
     */
    public static void close(Reader r) {
        if (r == null)
            return;
        try {
            r.close();
        } catch (Exception e) {}
    }
}
