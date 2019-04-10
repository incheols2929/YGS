package geomex.pkg.sys.luris;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import geomex.svc.webctrl.WebUtil;

public class LurisParsing {
    /*
     * 지역지구별행위제한
     */
    public String getLurisArZoneParsing(String url, String param) {

        String context = "";
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder(500);
        StringBuilder sb2 = new StringBuilder();

        try {
            //luris는 euc-kr인코딩 되어 있다...
            reader = WebUtil.getPOSTReader(url, param, "euc-kr");
            //String line = null;

            while (true) {
                String inStr = reader.readLine();
                if (inStr != null) {
                    sb.append(inStr.replace("../../", "http://luris.moct.go.kr/web/") + "\r\n");
                } else {
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(sb.toString());

        sb2.append(sb.toString().replaceAll("\"", "\""));
        String tag_str1 = "<span id=\"printData\">";
        String tag_str2 = "<div id=\"content\">";

        if (sb2.toString().indexOf(tag_str1) != -1) {
            context = sb2.toString().substring(sb2.toString().indexOf(tag_str1), sb2.toString().indexOf(tag_str2));
            context = context.replaceAll("<span id=\"printData\">", "<div>").replaceAll("width=\"735\"", "width=\"100%\"")
                .replaceAll("width=\"730\"", "width=\"100%\"");
        } else {
            context = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"100%\">";
            context += "<tr>";
            context += "	<td align=\"center\">값이 존재 하지 않습니다.</td>";
            context += "</tr>";
            context += "</table>";
        }
        return context;
    }

    /*
     * 토지이용계획
     */
    public String getLurisUsePlanParsing(String url, String param) {

        String context = "";
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder(500);
        StringBuilder sb2 = new StringBuilder();

        try {
            //luris는 euc-kr인코딩 되어 있다...
            reader = WebUtil.getPOSTReader(url, param, "euc-kr");
            //String line = null;

            while (true) {
                String inStr = reader.readLine();
                if (inStr != null) {
                    sb.append(inStr.replace("../../", "http://luris.moct.go.kr/web/") + "\r\n");
                } else {
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(sb.toString());

        sb2.append(sb.toString().replaceAll("\"", "\""));
        String tag_str1 = "<div id=\"infoZone\">";
        String tag_str2 = "</SPAN></div>";

        if (sb2.toString().indexOf(tag_str1) != -1) {
            context = sb2.toString().substring(sb2.toString().indexOf(tag_str1), sb2.toString().indexOf(tag_str2))
                + "</SPAN></div>";
        } else {
            context = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"100%\">";
            context += "<tr>";
            context += "	<td align=\"center\">값이 존재 하지 않습니다.</td>";
            context += "</tr>";
            context += "</table>";
        }
        return context;
    }

    /*
     * 토지이용행위제한
     */
    public String getLurisArParcelParsing(String url, String param) {

        String context = "";
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder(500);
        StringBuilder sb2 = new StringBuilder();

        try {
            //luris는 euc-kr인코딩 되어 있다...
            //reader = WebUtil.getPOSTReader(url, param, "euc-kr");
            reader = WebUtil.getGETReader(url + "?" + param, "euc-kr");
            //String line = null;

            while (true) {
                String inStr = reader.readLine();
                if (inStr != null) {
                    sb.append(inStr.replace("../../", "http://luris.moct.go.kr/web/") + "\r\n");
                } else {
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(sb.toString());
        sb2.append(sb.toString().replaceAll("\"", "\""));
        String tag_str1 = "<!-- 검색결과 시작 -->";
        String tag_str2 = "<!-- 행위제한/건폐율/용적율 end -->";

        if (sb2.toString().indexOf(tag_str1) != -1) {
            context = sb2.toString().substring(sb2.toString().indexOf(tag_str1), sb2.toString().indexOf(tag_str2));
            context = context.replace("width=\"735\"", "width=\"100%\"");
        } else {
            context = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"100%\">";
            context += "<tr>";
            context += "	<td align=\"center\">값이 존재 하지 않습니다.</td>";
            context += "</tr>";
            context += "</table>";
        }
        return context;
    }

    /*
     * 토지이용행위제한 팝업 가져오기
     */
    public String getLurisArLanfPopup(String url) {

        String context = "";
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder(500);
        StringBuilder sb2 = new StringBuilder();

        try {
            //luris는 euc-kr인코딩 되어 있다...
            //reader = WebUtil.getPOSTReader(url, param, "euc-kr");
            reader = WebUtil.getGETReader(url, "euc-kr");
            //String line = null;

            while (true) {
                String inStr = reader.readLine();
                if (inStr != null) {
                    sb.append(inStr.replace("../../", "http://luris.moct.go.kr/web/") + "\r\n");
                } else {
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(sb.toString());
        sb2.append(sb.toString().replaceAll("\"", "\""));
        String tag_str1 = "<html>";
        String tag_str2 = "</html>";

        if (sb2.toString().indexOf(tag_str1) != -1) {
            context = sb2.toString().substring(sb2.toString().indexOf(tag_str1), sb2.toString().indexOf(tag_str2));
            //context = context.replace("opener.document.forms['frm'].luGrStr.value = txt;", "");
        } else {
            context = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"100%\">";
            context += "<tr>";
            context += "	<td align=\"center\">값이 존재 하지 않습니다.</td>";
            context += "</tr>";
            context += "</table>";
        }

        //System.out.println(context);

        return context;
    }

}
