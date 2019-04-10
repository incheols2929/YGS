package geomex.utils.pdf;

import geomex.svc.webctrl.Const;
import geomex.utils.Utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.FSEntityResolver;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;

//토지대장 및 공유인 정보

public class TojiExportURLPDF {

    public TojiExportURLPDF() {}

    public String CreateURLPDF(String pnu, String user_name) throws Exception, DocumentException {

        //토지대장 및 공유지 연명부
        String urlString = "http://127.0.0.1/intra/form/tojiDaejang.jsp?pnu=" + pnu + "&user_name=" + user_name;

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        String pdfName = "토지대장_" + Utils.getStrSec() + ".pdf";

        //String outputFile = "D:\\workspace_web\\gws\\web\\pdf\\toji\\"+ pdfName;
        String outputFile = Const.getTempPath() + pdfName;

        OutputStream os = new FileOutputStream(outputFile);

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        builder.setEntityResolver(FSEntityResolver.instance());

        ITextRenderer renderer = new ITextRenderer();

        try {

            Document doc = builder.parse(con.getInputStream());

            renderer.setDocument(doc, null);
            renderer.getFontResolver().addFont(Const.getFontFile(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, null);
            renderer.layout();
            renderer.createPDF(os, false);
            renderer.finishPDF();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //fls.close();
            os.flush();
            os.close();
        }
        return pdfName;

    }
}
