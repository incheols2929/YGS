package geomex.utils.pdf;

import geomex.svc.webctrl.Const;
import geomex.utils.Utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.FSEntityResolver;
import com.lowagie.text.pdf.BaseFont;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.itextpdf.text.DocumentException;

//건축물대장 전유부

public class BldgJunyouExportURLPDF {

    public BldgJunyouExportURLPDF() {}

    public String CreateURLPDF(String key, String pnu, String ouln_pk, String user_name) throws Exception, DocumentException {

        //건축물대장 전유부
        String urlString = "http://127.0.0.1/intra/form/pdfprint/bldg/bldg_junyou_daejang.jsp?key="
            + key + "&pnu=" + pnu + "&ouln_pk=" + ouln_pk + "&user_name=" + user_name;

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        String pdfName = "건축물대장(전유부)_" + Utils.getStrSec() + ".pdf";

        //String outputFile = "D:\\workspace_web\\gws\\web\\pdf\\bldg\\"+ pdfName;
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
