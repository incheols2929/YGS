package geomex.pkg.sys.lris;

import geomex.svc.handler.GetLrgstXML;
import geomex.svc.webctrl.Const;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 공유지연명부 조회
 */
public class LRGST82 implements Comparable<LRGST82> {
    public String SHAP_SNO = ""; // 공유인 일련번호
    public String OWNSP_CH_CAU_GBN = ""; // 소유권변동사유코드
    public String OWNSP_CH_CAU_GBN_NM = ""; // 소유권변동사유
    public String OWNSP_CH_YMD = ""; // 소유권변동일자
    public String OWNR_REG_NO = ""; // 소유자등록번호
    public String OWNR_NM = ""; // 소유자명
    public String OWNR_ADDR = ""; // 소유자주소
    public String OWNSP_COSM = ""; // 공유지분
    public String TRANS_YMD = ""; // 말소일자
    public String OWN_GBN = ""; // 소유구분코드
    public String OWN_GBN_NM = ""; // 소유구분

    public LRGST82() {

    }

    public ArrayList<LRGST82> getBaseInfo(String pnu) throws SAXException, IOException, ParserConfigurationException {
        GetLrgstXML gtx = new GetLrgstXML();
        String getXML = gtx.getLrgstXML(pnu, Const.getLrgstURL(), "GetShareInfo");
        ArrayList<LRGST82> list = new ArrayList<LRGST82>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc = docBuilder.parse(new InputSource(new StringReader(getXML)));
        doc.getDocumentElement().normalize();
        NodeList nl1 = doc.getElementsByTagName("공유지연명부");

        if (nl1.getLength() != 0) {
            for (int i = 0; i < nl1.getLength(); i++) {
                LRGST82 lrgst82 = new LRGST82();
                Node node = nl1.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList _child = node.getChildNodes();
                    for (int j = 0; j < _child.getLength(); j++) {
                        Node _ele = _child.item(j);
                        if (_ele.getNodeType() == Node.ELEMENT_NODE) {
                            String _name = _ele.getNodeName();
                            String _content = _ele.getTextContent();
                            if (_name.equalsIgnoreCase("공유인일련번호")) {
                                lrgst82.SHAP_SNO = _content;
                            } else if (_name.equalsIgnoreCase("소유권변동사유코드")) {
                                lrgst82.OWNSP_CH_CAU_GBN = _content;
                            } else if (_name.equalsIgnoreCase("소유권변동사유")) {
                                lrgst82.OWNSP_CH_CAU_GBN_NM = _content;
                            } else if (_name.equalsIgnoreCase("소유권변동일자")) {
                                lrgst82.OWNSP_CH_YMD = _content;
                            } else if (_name.equalsIgnoreCase("소유자등록번호")) {
                                lrgst82.OWNR_REG_NO = _content;
                            } else if (_name.equalsIgnoreCase("소유자명")) {
                                lrgst82.OWNR_NM = _content;
                            } else if (_name.equalsIgnoreCase("소유자주소")) {
                                lrgst82.OWNR_ADDR = _content;
                            } else if (_name.equalsIgnoreCase("공유지분")) {
                                lrgst82.OWNSP_COSM = _content;
                            } else if (_name.equalsIgnoreCase("말소일자")) {
                                lrgst82.TRANS_YMD = _content;
                            } else if (_name.equalsIgnoreCase("소유구분코드")) {
                                lrgst82.OWN_GBN = _content;
                            } else if (_name.equalsIgnoreCase("소유구분")) {
                                lrgst82.OWN_GBN_NM = _content;
                            }
                        }
                    }
                }
                list.add(lrgst82);
            }
        }

        return list;
    }

    @Override
    public int compareTo(LRGST82 o) {
        try {
            int thisVal = Integer.parseInt(SHAP_SNO);
            int anotherVal = Integer.parseInt(o.SHAP_SNO);
            return (thisVal < anotherVal ? -1 : (thisVal == anotherVal ? 0 : 1));
        } catch (Exception e) {

        }
        return 0;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("<공유지연명부>");
        sb.append("<공유인일련번호>").append(SHAP_SNO).append("</공유인일련번호>");
        sb.append("<소유권변동사유코드>").append(OWNSP_CH_CAU_GBN).append("</소유권변동사유코드>");
        sb.append("<소유권변동사유>").append(OWNSP_CH_CAU_GBN_NM).append("</소유권변동사유>");
        sb.append("<소유권변동일자>").append(OWNSP_CH_YMD).append("</소유권변동일자>");
        sb.append("<소유자등록번호>").append(OWNR_REG_NO).append("</소유자등록번호>");
        sb.append("<소유자명>").append(OWNR_NM).append("</소유자명>");
        sb.append("<소유자주소>").append(OWNR_ADDR).append("</소유자주소>");
        sb.append("<공유지분>").append(OWNSP_COSM).append("</공유지분>");
        sb.append("<말소일자>").append(TRANS_YMD).append("</말소일자>");
        sb.append("<소유구분코드>").append(OWN_GBN).append("</소유구분코드>");
        sb.append("<소유구분>").append(OWN_GBN_NM).append("</소유구분>");
        sb.append("</공유지연명부>");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("공유인일련번호=").append("SHAP_SNO").append("\r\n");
        sb.append("소유권변동사유코드=").append("OWNSP_CH_CAU_GBN").append("\r\n");
        sb.append("소유권변동사유=").append("OWNSP_CH_CAU_GBN_NM").append("\r\n");
        sb.append("소유권변동일자=").append("OWNSP_CH_YMD").append("\r\n");
        sb.append("소유자등록번호=").append("OWNR_REG_NO").append("\r\n");
        sb.append("소유자명=").append("OWNR_NM").append("\r\n");
        sb.append("소유자주소=").append("OWNR_ADDR").append("\r\n");
        sb.append("공유지분=").append("OWNSP_COSM").append("\r\n");
        sb.append("말소일자=").append("TRANS_YMD").append("\r\n");
        sb.append("소유구분코드=").append("OWN_GBN").append("\r\n");
        sb.append("소유구분=").append("OWN_GBN_NM").append("\r\n");
        return sb.toString();
    }

}
