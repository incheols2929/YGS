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
 * 토지이동연혁조회
 */
public class LRGST84 implements Comparable<LRGST84> {
    public String MAP_NO_BONO = ""; // 도호본번
    public String MAP_NO_BUNO = ""; // 도호부번
    public String SCALE_CODE = ""; // 축척코드
    public String SCALE_NM = ""; // 코드내용
    public String JIMK_CODE = ""; // 지목코드
    public String AREA = ""; // 현재면적
    public String LAND_MOVE_WHY_CODE = ""; // 토지이동사유코드
    public String LAND_MOVE_YMD = ""; // 이동일자
    public String LAND_HIST_ORD = ""; // 연혁순번
    public String JIMK_CODE_1 = ""; // 지목코드
    public String JIMK_NM = ""; // 지목명
    public String LAND_HIST_AREA = ""; // 연혁상_면적
    public String LAND_MOVE_WHY_CODE_1 = ""; // 토지이동사유코드
    public String LAND_MOVE_WHY_NM = ""; // 최종토지이동사유
    public String LAND_MOVE_YMD_1 = ""; // 토지이동일자
    public String LAND_MOVE_RELL_JIBN = ""; // 관련지번

    public LRGST84() {

    }

    public ArrayList<LRGST84> getBaseInfo(String pnu) throws SAXException, IOException, ParserConfigurationException {
        GetLrgstXML gtx = new GetLrgstXML();
        String getXML = gtx.getLrgstXML(pnu, Const.getLrgstURL(), "GetLandHistInfo");
        ArrayList<LRGST84> list = new ArrayList<LRGST84>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc = docBuilder.parse(new InputSource(new StringReader(getXML)));
        doc.getDocumentElement().normalize();
        NodeList nl1 = doc.getElementsByTagName("토지이동연혁");

        if (nl1.getLength() != 0) {
            for (int i = 0; i < nl1.getLength(); i++) {
                LRGST84 lrgst84 = new LRGST84();
                Node node = nl1.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList _child = node.getChildNodes();
                    for (int j = 0; j < _child.getLength(); j++) {
                        Node _ele = _child.item(j);
                        if (_ele.getNodeType() == Node.ELEMENT_NODE) {
                            String _name = _ele.getNodeName();
                            String _content = _ele.getTextContent();
                            if (_name.equalsIgnoreCase("도호본번")) {
                                lrgst84.MAP_NO_BONO = _content;
                            } else if (_name.equalsIgnoreCase("도호부번")) {
                                lrgst84.MAP_NO_BUNO = _content;
                            } else if (_name.equalsIgnoreCase("축척코드")) {
                                lrgst84.SCALE_CODE = _content;
                            } else if (_name.equalsIgnoreCase("코드내용")) {
                                lrgst84.SCALE_NM = _content;
                            } else if (_name.equalsIgnoreCase("지목코드")) {
                                lrgst84.JIMK_CODE = _content;
                            } else if (_name.equalsIgnoreCase("현재면적")) {
                                lrgst84.AREA = _content;
                            } else if (_name.equalsIgnoreCase("이동일자")) {
                                lrgst84.LAND_MOVE_YMD = _content;
                            } else if (_name.equalsIgnoreCase("연혁순번")) {
                                lrgst84.LAND_HIST_ORD = _content;
                            } else if (_name.equalsIgnoreCase("지목코드")) {
                                lrgst84.JIMK_CODE_1 = _content;
                            } else if (_name.equalsIgnoreCase("지목")) {
                                lrgst84.JIMK_NM = _content;
                            } else if (_name.equalsIgnoreCase("연혁상_면적")) {
                                lrgst84.LAND_HIST_AREA = _content;
                            } else if (_name.equalsIgnoreCase("토지이동사유코드")) {
                                lrgst84.LAND_MOVE_WHY_CODE_1 = _content;
                            } else if (_name.equalsIgnoreCase("토지이동사유")) {
                                lrgst84.LAND_MOVE_WHY_NM = _content;
                            } else if (_name.equalsIgnoreCase("토지이동일자")) {
                                lrgst84.LAND_MOVE_YMD_1 = _content;
                            } else if (_name.equalsIgnoreCase("관련지번")) {
                                lrgst84.LAND_MOVE_RELL_JIBN = _content;
                            }
                        }
                    }
                }
                list.add(lrgst84);
            }
        }

        return list;
    }

    @Override
    public int compareTo(LRGST84 o) {
        try {
            int thisVal = Integer.parseInt(LAND_HIST_ORD);
            int anotherVal = Integer.parseInt(o.LAND_HIST_ORD);
            return (thisVal < anotherVal ? -1 : (thisVal == anotherVal ? 0 : 1));
        } catch (Exception e) {

        }
        return 0;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("<토지이동연혁>");
        sb.append("<도호본번>").append(MAP_NO_BONO).append("</도호본번>");
        sb.append("<도호부번>").append(MAP_NO_BUNO).append("</도호부번>");
        sb.append("<축척코드>").append(SCALE_CODE).append("</축척코드>");
        sb.append("<코드내용>").append(SCALE_NM).append("</코드내용>");
        sb.append("<지목코드>").append(JIMK_CODE).append("</지목코드>");
        sb.append("<현재면적>").append(AREA).append("</현재면적>");
        sb.append("<토지이동사유>").append(LAND_MOVE_WHY_CODE_1).append("</토지이동사유>");
        sb.append("<이동일자>").append(LAND_MOVE_YMD).append("</이동일자>");
        sb.append("<연혁순번>").append(LAND_HIST_ORD).append("</연혁순번>");
        sb.append("<지목코드>").append(JIMK_CODE).append("</지목코드>");
        sb.append("<지목>").append(JIMK_NM).append("</지목>");
        sb.append("<연혁상_면적>").append(LAND_HIST_AREA).append("</연혁상_면적>");
        sb.append("<토지이동사유코드>").append(LAND_MOVE_WHY_CODE_1).append("</토지이동사유코드>");
        sb.append("<토지이동사유>").append(LAND_MOVE_WHY_NM).append("</토지이동사유>");
        sb.append("<토지이동일자>").append(LAND_MOVE_YMD_1).append("</토지이동일자>");
        sb.append("<관련지번>").append(LAND_MOVE_RELL_JIBN).append("</관련지번>");
        sb.append("</토지이동연혁>");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("도호본번=").append(MAP_NO_BONO).append("\r\n");
        sb.append("도호부번=").append(MAP_NO_BUNO).append("\r\n");
        sb.append("축척코드=").append(SCALE_CODE).append("\r\n");
        sb.append("코드내용=").append(SCALE_NM).append("\r\n");
        sb.append("지목코드=").append(JIMK_CODE).append("\r\n");
        sb.append("현재면적=").append(AREA).append("\r\n");
        sb.append("토지이동사유=").append(LAND_MOVE_WHY_CODE_1).append("\r\n");
        sb.append("이동일자=").append(LAND_MOVE_YMD).append("\r\n");
        sb.append("연혁순번=").append(LAND_HIST_ORD).append("\r\n");
        sb.append("지목코드=").append(JIMK_CODE).append("\r\n");
        sb.append("지목=").append(JIMK_NM).append("\r\n");
        sb.append("연혁상_면적=").append(LAND_HIST_AREA).append("\r\n");
        sb.append("토지이동사유코드=").append(LAND_MOVE_WHY_CODE_1).append("\r\n");
        sb.append("토지이동사유=").append(LAND_MOVE_WHY_NM).append("\r\n");
        sb.append("토지이동일자=").append(LAND_MOVE_YMD_1).append("\r\n");
        sb.append("관련지번=").append(LAND_MOVE_RELL_JIBN).append("\r\n");
        return sb.toString();
    }

}
