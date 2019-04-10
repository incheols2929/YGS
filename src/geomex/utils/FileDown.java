package geomex.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class FileDown {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public FileDown(HttpServletRequest req, HttpServletResponse res) {

        request = req;
        response = res;

    }

    public void write(String filePath, String downName) throws Exception {
        write(filePath, downName, "");
    }

    public void write(String filePath, String downName, String contentType) throws Exception {

        File f = new File(filePath);
        write(f, downName, contentType);
    }

    public void write(File file, String downName, String contentType) throws Exception {

        //		FileInputStream fis = new FileInputStream(file);
        OutputStream os = response.getOutputStream();

        int length = 0;

        //버퍼
        byte[] b = new byte[1024];

        downName = (downName == null || "".equals(downName)) ? file.getName() : downName;
        //downName = file.exists() ? downName : downName + "(빈문서)";
        String client = request.getHeader("User-Agent");

        if ("".equals(contentType) || contentType == null) {

            contentType = "application/octet-stream; charset=euc-kr";

        }
        if (client.indexOf("MSIE 5.5") != -1) {
            response.setHeader("Content-Type", contentType);
            response.setHeader("Content-Disposition", "fileName=" + new String(downName.getBytes("euc-kr"), "8859_1"));
        } else {
            response.setHeader("Content-Type", contentType);
            response.setHeader("Content-Disposition", "attachment; fileName=" + new String(downName.getBytes("euc-kr"), "8859_1"));
        }

        //		while((length = fis.read(b)) > -1){
        //			os.write(b, 0, length);
        //		}

        FileInputStream fis = null;
        try {
        	fis = new FileInputStream(file);
            while ((length = fis.read(b)) > -1) {
                os.write(b, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	fis.close();
        }

        os.flush();

    }

    public void write(HSSFWorkbook workbook, String downName) throws Exception {
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-Type", "application/vnd.ms-excel; charset=EUC-KR");
        response.setHeader("Content-Disposition", "fileName=" + new String(downName.getBytes("euc-kr"), "8859_1"));

        workbook.write(os);
        os.flush();
        os.close();
    }
}
