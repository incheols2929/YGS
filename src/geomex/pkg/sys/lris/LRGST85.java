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
 * 소유권변동연혁조회 (소유자)
 */
public class LRGST85 implements Comparable<LRGST85> {
    public String OWNSP_CH_HIST_ORD = ""; // 연혁순번
    public String OWNSP_CH_CAU_GBN = ""; // 소유권변동사유코드
    public String OWNSP_CH_CAU_GBN_NM = ""; // 소유권변동사유
    public String OWNSP_CH_YMD = ""; // 변동일자
    public String OWNR_REG_SNO = ""; // 등록번호
    public String OWNR_NM = ""; // 소유자명
    public String OWNR_ADDR = ""; // 소유자주소
    public String OWN_GBN = ""; // 소유구분코드
    public String OWN_GBN_NM = ""; // 소유구분
    public String SHAP_NUM = ""; // 공유인수
    public String OWNSP_CHR_NO = ""; // 처리담당자
    public String TRANS_YMD = ""; // 말소일자

    public LRGST85() {

    }

    public ArrayList<LRGST85> getBaseInfo(String pnu) throws SAXException, IOException, ParserConfigurationException {
        GetLrgstXML gtx = new GetLrgstXML();
        String getXML = gtx.getLrgstXML(pnu, Const.getLrgstURL(), "GetOwnerHistInfo");
        ArrayList<LRGST85> list = new ArrayList<LRGST85>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc = docBuilder.parse(new InputSource(new StringReader(getXML)));
        doc.getDocumentElement().normalize();
        NodeList nl1 = doc.getElementsByTagName("소유권변동연혁");

        if (nl1.getLength() != 0) {
            for (int i = 0; i < nl1.getLength(); i++) {
                LRGST85 lrgst85 = new LRGST85();
                Node node = nl1.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList _child = node.getChildNodes();
                    for (int j = 0; j < _child.getLength(); j++) {
                        Node _ele = _child.item(j);
                        if (_ele.getNodeType() == Node.ELEMENT_NODE) {
                            String _name = _ele.getNodeName();
                            String _content = _ele.getTextContent();
                            if (_name.equalsIgnoreCase("연혁순번")) {
                                lrgst85.OWNSP_CH_HIST_ORD = _content;
                            } else if (_name.equalsIgnoreCase("소유권변동사유코드")) {
                                lrgst85.OWNSP_CH_CAU_GBN = _content;
                            } else if (_name.equalsIgnoreCase("소유권변동사유")) {
                                lrgst85.OWNSP_CH_CAU_GBN_NM = _content;
                            } else if (_name.equalsIgnoreCase("변동일자")) {
                                lrgst85.OWNSP_CH_YMD = _content;
                            } else if (_name.equalsIgnoreCase("등록번호")) {
                                lrgst85.OWNR_REG_SNO = _content;
                            } else if (_name.equalsIgnoreCase("소유자명")) {
                                lrgst85.OWNR_NM = _content;
                            } else if (_name.equalsIgnoreCase("소유자주소")) {
                                lrgst85.OWNR_ADDR = _content;
                            } else if (_name.equalsIgnoreCase("소유구분코드")) {
                                lrgst85.OWN_GBN = _content;
                            } else if (_name.equalsIgnoreCase("소유구분")) {
                                lrgst85.OWN_GBN_NM = _content;
                            } else if (_name.equalsIgnoreCase("공유인수")) {
                                lrgst85.SHAP_NUM = _content;
                            } else if (_name.equalsIgnoreCase("처리담당자")) {
                                lrgst85.OWNSP_CHR_NO = _content;
                            } else if (_name.equalsIgnoreCase("말소일자")) {
                                lrgst85.TRANS_YMD = _content;
                            }
                        }
                    }
                }
                list.add(lrgst85);
            }
        }

        return list;
    }

    @Override
    public int compareTo(LRGST85 o) {
        try {
            int thisVal = Integer.parseInt(OWNSP_CH_HIST_ORD);
            int anotherVal = Integer.parseInt(o.OWNSP_CH_HIST_ORD);
            return (thisVal < anotherVal ? -1 : (thisVal == anotherVal ? 0 : 1));
        } catch (Exception e) {

        }
        return 0;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("<소유권변동연혁>");
        sb.append("<연혁순번>").append(OWNSP_CH_HIST_ORD).append("</연혁순번>");
        sb.append("<소유권변동사유코드>").append(OWNSP_CH_CAU_GBN).append("</소유권변동사유코드>");
        sb.append("<소유권변동사유>").append(OWNSP_CH_CAU_GBN_NM).append("</소유권변동사유>");
        sb.append("<변동일자>").append(OWNSP_CH_YMD).append("</변동일자>");
        sb.append("<등록번호>").append(OWNR_REG_SNO).append("</등록번호>");
        sb.append("<소유자명>").append(OWNR_NM).append("</소유자명>");
        sb.append("<소유자주소>").append(OWNR_ADDR).append("</소유자주소>");
        sb.append("<소유구분코드>").append(OWN_GBN).append("</소유구분코드>");
        sb.append("<소유구분>").append(OWN_GBN_NM).append("</소유구분>");
        sb.append("<공유인수>").append(SHAP_NUM).append("</공유인수>");
        sb.append("<처리담당자>").append(OWNSP_CHR_NO).append("</처리담당자>");
        sb.append("<말소일자>").append(TRANS_YMD).append("</말소일자>");
        sb.append("</소유권변동연혁>");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("연혁순번=").append(OWNSP_CH_HIST_ORD).append("\r\n");
        sb.append("소유권변동사유코드=").append(OWNSP_CH_CAU_GBN).append("\r\n");
        sb.append("소유권변동사유=").append(OWNSP_CH_CAU_GBN_NM).append("\r\n");
        sb.append("변동일자=").append(OWNSP_CH_YMD).append("\r\n");
        sb.append("등록번호=").append(OWNR_REG_SNO).append("\r\n");
        sb.append("소유자명=").append(OWNR_NM).append("\r\n");
        sb.append("소유자주소=").append(OWNR_ADDR).append("\r\n"); // 소유자명이 올라옴 된장
        sb.append("소유구분코드=").append(OWN_GBN).append("\r\n"); // 소유자 주소가 올라옴 된장
        sb.append("소유구분=").append(OWN_GBN_NM).append("\r\n");
        sb.append("공유인수=").append(SHAP_NUM).append("\r\n");
        sb.append("처리담당자=").append(OWNSP_CHR_NO).append("\r\n");
        sb.append("말소일자=").append(TRANS_YMD).append("\r\n");
        return sb.toString();
    }

}
