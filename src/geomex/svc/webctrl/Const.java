package geomex.svc.webctrl;

import geovlet.utils.IOUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Const {
	
	public static String CONTEXT_NAME = "YGS";
    // WEB-INF -> temp 寃쎈줈
    public static String tempPath = "";

    // MapStyle template
    public static final String MAP_STYLE = "/WEB-INF/mapstyle-template.xml";
    public static volatile String realMapStyleFile = "";

    // �뷀뀒��誘몃뱾�⑥뼱 �섍꼍�ㅼ젙 �뚯씪
    public static final String ENTERA_ENV = "/WEB-INF/lris_entera.env";
    public static final String ENTERA = "/WEB-INF/entera.env";

    public static final String XML_HEADER = "<?xml version='1.0' encoding='UTF-8'?>";
    //
    public static final String ERR_INVALID_REQUEST = "�섎せ���붿껌�뺣낫 �낅땲��";
    // public static final String ERR_INVALID_REQUEST = "沅뚰븳���놁뒿�덈떎.";

    // �좎���옣 議고쉶 愿�젴 �꾩뿭蹂�닔
    public static volatile String realEnteraEnvFile = "";
    public static volatile Properties propLRIS = new Properties();

    // �몃� 議고쉶��IP蹂�닔
    public static String LrgstURL = "";

    // PDF 異쒕젰���고듃
    public static final String fontFile = "/WEB-INF/NanumGothic.ttf";
    public static String realPdfFont = "";

    // URL ////////////////////////////////////////  
    public static final void setLrgstURL(String url) {
        LrgstURL = url;
    }

    public static String getLrgstURL() {
        return LrgstURL;
    }

    // Temp ////////////////////////////////////////
    public static final void setTempPath(String path) {
        tempPath = path;
    }

    public static String getTempPath() {
        return tempPath;
    }

    // Font ////////////////////////////////////////
    public static void setFontFile(String fontFile) {
        Const.realPdfFont = fontFile;
    }

    public static String getFontFile() {
        return realPdfFont;
    }

    // MapStyle - start ////////////////////////////////////////////////////

    public static void setEnteraEnvFile(String f) {
        realEnteraEnvFile = f;
    }

    public static String getRealMapStyleFile() {
        return realMapStyleFile;
    }

    // MapStyle - end////////////////////////////////////////////////////
    public static void setRealMapStyleFile(String realMapStyleFile) {
        Const.realMapStyleFile = realMapStyleFile;
    }

    public static String getEnteraEnvFile() {
        return realEnteraEnvFile;
    }

    public static void loadLRISProperties(File f) throws IOException {
        propLRIS = loadProperties(f);
    }

    public static Properties getLRISProperties() {
        return propLRIS;
    }

    public static String getLRISProperties(String key) {
        return propLRIS.getProperty(key);
    }

    //
    // TODO �좏삎蹂��먮윭硫붿꽭吏�� �뺤쓽�섏옄.....

    public static Properties loadProperties(File f) throws IOException {
        FileReader reader = null;
        Properties prop = new Properties();
        try {
            reader = new FileReader(f);
            prop.load(reader);
        } catch (Exception e) {
            throw new IOException(IOUtil.getStackTrace(e));
        } finally {
            IOUtil.close(reader);
        }
        return prop;
    }

}
