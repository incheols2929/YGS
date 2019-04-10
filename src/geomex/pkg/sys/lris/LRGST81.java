package geomex.pkg.sys.lris;

import geomex.svc.handler.GetLrgstXML;
import geomex.svc.webctrl.Const;
import geomex.utils.Utils;

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
 * 일필지기본사항 조회
 */
public class LRGST81 {
    public String MAP_NO_BONO = ""; // 도호
    public String MAP_NO_BUNO = ""; // 도호순번
    public String SCALE_CODE = ""; // 축척코드
    public String SCALE_NM = ""; // 축척명
    public String JIMK = ""; // 지목코드
    public String JIMK_NM = ""; // 지목명
    public String AREA = ""; // 면적
    public String LAND_MOVE_WHY_CODE = ""; // 최종토지이동사유코드
    public String LAND_MOVE_WHY_CODE_NM = ""; // 최종토지이동사유
    public String LAND_MOVE_YMD = ""; // 최종토지이동일자
    public String LAND_MOVE_RELL_JIBN = ""; // 토지이동관련지번
    public String BSIN_ENF_NT_GBN = ""; // 사업시행신고구분
    public String OWNSP_CH_CAU_GBN = ""; // 최종소유권변동사유코드
    public String OWNSP_CH_CAU_GBN_NM = ""; // 최종소유권변동사유
    public String OWNSP_CH_YMD = ""; // 최종소유권변동일자
    public String OWNR_REG_NO = ""; // 소유자등록번호
    public String OWNR_REG_SNO = ""; // 소유자등록일련번호
    public String OWN_GBN = ""; // 소유구분코드
    public String OWN_GBN_NM = ""; // 소유구분명
    public String SHAP_NUM = ""; // 공유인수
    public String OWNR_NM = ""; // 소유자명
    public String OWNR_ADDR = ""; // 소유자주소
    public String LAND_LAST_ORD = ""; // 최종토지이동연혁순번
    public String OWNSP_CH_HIST_LAST_ORD = ""; // 최종소유권변동연혁순번
    public String RELL_CO_BDNG_SNO = ""; // 관련집합건물
    public String LV = ""; // 토지등급
    public String LV_CH_YMD = ""; // 토지등급변동일자

    public String JIGA = ""; // 공시지가
    public String ADDR = ""; // 소재지

    public LRGST81() {

    }

    public ArrayList<LRGST81> getBaseInfo(String pnu) throws SAXException, IOException, ParserConfigurationException {
        GetLrgstXML gtx = new GetLrgstXML();
        String getXML = gtx.getLrgstXML(pnu, Const.getLrgstURL(), "GetLandInfo");
        ArrayList<LRGST81> list = new ArrayList<LRGST81>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();

        Document doc = docBuilder.parse(new InputSource(new StringReader(getXML)));
        doc.getDocumentElement().normalize();
        NodeList nl1 = doc.getElementsByTagName("일필지기본");
        NodeList nl2 = doc.getElementsByTagName("공시지가");
        NodeList nl3 = doc.getElementsByTagName("소재지");

        if (nl1.getLength() != 0) {
            for (int i = 0; i < nl1.getLength(); i++) {
                LRGST81 lrgst81 = new LRGST81();
                Node node = nl1.item(i);
                Node node2 = nl2.item(0);
                Node node3 = nl3.item(0);

                if (node2.getNodeName().equalsIgnoreCase("공시지가")) {
                    lrgst81.JIGA = node2.getTextContent();
                }
                if (node3.getNodeName().equalsIgnoreCase("소재지")) {
                    lrgst81.ADDR = node3.getTextContent();
                }

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList _child = node.getChildNodes();
                    for (int j = 0; j < _child.getLength(); j++) {
                        Node _ele = _child.item(j);
                        if (_ele.getNodeType() == Node.ELEMENT_NODE) {
                            String _name = _ele.getNodeName();
                            String _content = _ele.getTextContent();
                            if (_name.equalsIgnoreCase("도호")) {
                                lrgst81.MAP_NO_BONO = _content;
                            } else if (_name.equalsIgnoreCase("도호순번")) {
                                lrgst81.MAP_NO_BUNO = _content;
                            } else if (_name.equalsIgnoreCase("축척코드")) {
                                lrgst81.SCALE_CODE = _content;
                            } else if (_name.equalsIgnoreCase("축척명")) {
                                lrgst81.SCALE_NM = _content;
                            } else if (_name.equalsIgnoreCase("지목코드")) {
                                lrgst81.JIMK = _content;
                            } else if (_name.equalsIgnoreCase("지목명")) {
                                lrgst81.JIMK_NM = _content;
                            } else if (_name.equalsIgnoreCase("면적")) {
                                lrgst81.AREA = _content;
                            } else if (_name.equalsIgnoreCase("최종토지이동사유코드")) {
                                lrgst81.LAND_MOVE_WHY_CODE = _content;
                            } else if (_name.equalsIgnoreCase("최종토지이동사유")) {
                                lrgst81.LAND_MOVE_WHY_CODE_NM = _content;
                            } else if (_name.equalsIgnoreCase("최종토지이동일자")) {
                                lrgst81.LAND_MOVE_YMD = Utils.formatTxtYMD(_content);
                            } else if (_name.equalsIgnoreCase("토지이동관련지번")) {
                                lrgst81.LAND_MOVE_RELL_JIBN = _content;
                            } else if (_name.equalsIgnoreCase("사업시행신고구분")) {
                                lrgst81.BSIN_ENF_NT_GBN = _content;
                            } else if (_name.equalsIgnoreCase("최종소유권변동사유코드")) {
                                lrgst81.OWNSP_CH_CAU_GBN = _content;
                            } else if (_name.equalsIgnoreCase("최종소유권변동사유")) {
                                lrgst81.OWNSP_CH_CAU_GBN_NM = _content;
                            } else if (_name.equalsIgnoreCase("최종소유권변동일자")) {
                                lrgst81.OWNSP_CH_YMD = Utils.formatTxtYMD(_content);
                            } else if (_name.equalsIgnoreCase("소유자등록번호")) {
                                lrgst81.OWNR_REG_NO = _content;
                            } else if (_name.equalsIgnoreCase("소유자등록일련번호")) {
                                lrgst81.OWNR_REG_SNO = _content;
                            } else if (_name.equalsIgnoreCase("소유구분코드")) {
                                lrgst81.OWN_GBN = _content;
                            } else if (_name.equalsIgnoreCase("소유구분명")) {
                                lrgst81.OWN_GBN_NM = _content;
                            } else if (_name.equalsIgnoreCase("공유인수")) {
                                lrgst81.SHAP_NUM = _content;
                            } else if (_name.equalsIgnoreCase("소유자명")) {
                                lrgst81.OWNR_NM = _content;
                            } else if (_name.equalsIgnoreCase("소유자주소")) {
                                lrgst81.OWNR_ADDR = _content;
                            } else if (_name.equalsIgnoreCase("최종토지이동연혁순번")) {
                                lrgst81.LAND_LAST_ORD = _content;
                            } else if (_name.equalsIgnoreCase("최종소유권변동연혁순번")) {
                                lrgst81.OWNSP_CH_HIST_LAST_ORD = _content;
                            } else if (_name.equalsIgnoreCase("관련집합건물")) {
                                lrgst81.RELL_CO_BDNG_SNO = _content;
                            } else if (_name.equalsIgnoreCase("토지등급")) {
                                lrgst81.LV = _content;
                            } else if (_name.equalsIgnoreCase("토지등급변동일자")) {
                                lrgst81.LV_CH_YMD = Utils.formatTxtYMD(_content);
                            }
                        }
                    }
                }
                list.add(lrgst81);
            }
        }

        return list;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder(300);
        sb.append("<일필지기본>");
        sb.append("<도호>").append(MAP_NO_BONO).append("</도호>");
        sb.append("<도호순번>").append(MAP_NO_BUNO).append("</도호순번>");
        sb.append("<축척코드>").append(SCALE_CODE).append("</축척코드>");
        sb.append("<축척명>").append(SCALE_NM).append("</축척명>");
        sb.append("<지목>").append(JIMK).append("</지목>");
        sb.append("<지목명>").append(JIMK_NM).append("</지목명>");
        sb.append("<면적>").append(AREA).append("</면적>");
        sb.append("<최종토지이동사유코드>").append(LAND_MOVE_WHY_CODE).append("</최종토지이동사유코드>");
        sb.append("<최종토지이동사유>").append(LAND_MOVE_WHY_CODE_NM).append("</최종토지이동사유>");
        sb.append("<최종토지이동일자>").append(LAND_MOVE_YMD).append("</최종토지이동일자>");
        sb.append("<토지이동관련지번>").append(LAND_MOVE_RELL_JIBN).append("</토지이동관련지번>");
        sb.append("<사업시행신고구분>").append(BSIN_ENF_NT_GBN).append("</사업시행신고구분>");
        sb.append("<최종소유권변동사유코드>").append(OWNSP_CH_CAU_GBN).append("</최종소유권변동사유코드>");
        sb.append("<최종소유권변동사유>").append(OWNSP_CH_CAU_GBN_NM).append("</최종소유권변동사유>");
        sb.append("<최종소유권변동일자>").append(OWNSP_CH_YMD).append("</최종소유권변동일자>");
        sb.append("<소유자등록번호>").append(OWNR_REG_NO).append("</소유자등록번호>");
        sb.append("<소유자등록일련번호>").append(OWNR_REG_SNO).append("</소유자등록일련번호>");
        sb.append("<소유구분코드>").append(OWN_GBN).append("</소유구분코드>");
        sb.append("<소유구분명>").append(OWN_GBN_NM).append("</소유구분명>");
        sb.append("<공유인수>").append(SHAP_NUM).append("</공유인수>");
        sb.append("<소유자명>").append(OWNR_NM).append("</소유자명>");
        sb.append("<소유자주소>").append(OWNR_ADDR).append("</소유자주소>");
        sb.append("<최종토지이동연혁순번>").append(LAND_LAST_ORD).append("</최종토지이동연혁순번>");
        sb.append("<최종소유권변동연혁순번>").append(OWNSP_CH_HIST_LAST_ORD).append("</최종소유권변동연혁순번>");
        sb.append("<관련집합건물>").append(RELL_CO_BDNG_SNO).append("</관련집합건물>");
        sb.append("<토지등급>").append(LV).append("</토지등급>");
        sb.append("<토지등급변동일자>").append(LV_CH_YMD).append("</토지등급변동일자>");
        sb.append("<공시지가>").append(JIGA).append("</공시지가>");
        sb.append("<소재지>").append(ADDR).append("</소재지>");
        sb.append("</일필지기본>");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("도호=").append(MAP_NO_BONO).append("\r\n");
        sb.append("도호순번=").append(MAP_NO_BUNO).append("\r\n");
        sb.append("축척코드=").append(SCALE_CODE).append("\r\n");
        sb.append("축척명=").append(SCALE_NM).append("\r\n");
        sb.append("지목=").append(JIMK).append("\r\n");
        sb.append("지목명=").append(JIMK_NM).append("\r\n");
        sb.append("면적=").append(AREA).append("\r\n");
        sb.append("최종토지이동사유코드=").append(LAND_MOVE_WHY_CODE).append("\r\n");
        sb.append("최종토지이동사유=").append(LAND_MOVE_WHY_CODE_NM).append("\r\n");
        sb.append("최종토지이동일자=").append(LAND_MOVE_YMD).append("\r\n");
        sb.append("토지이동관련지번=").append(LAND_MOVE_RELL_JIBN).append("\r\n");
        sb.append("사업시행신고구분=").append(BSIN_ENF_NT_GBN).append("\r\n");
        sb.append("최종소유권변동사유코드=").append(OWNSP_CH_CAU_GBN).append("\r\n");
        sb.append("최종소유권변동사유=").append(OWNSP_CH_CAU_GBN_NM).append("\r\n");
        sb.append("최종소유권변동일자=").append(OWNSP_CH_YMD).append("\r\n");
        sb.append("소유자등록번호=").append(OWNR_REG_NO).append("\r\n");
        sb.append("소유자등록일련번호=").append(OWNR_REG_SNO).append("\r\n");
        sb.append("소유구분코드=").append(OWN_GBN).append("\r\n");
        sb.append("소유구분명=").append(OWN_GBN_NM).append("\r\n");
        sb.append("공유인수=").append(SHAP_NUM).append("\r\n");
        sb.append("소유자명=").append(OWNR_NM).append("\r\n");
        sb.append("소유자주소=").append(OWNR_ADDR).append("\r\n");
        sb.append("최종토지이동연혁순번=").append(LAND_LAST_ORD).append("\r\n");
        sb.append("최종소유권변동연혁순번=").append(OWNSP_CH_HIST_LAST_ORD).append("\r\n");
        sb.append("관련집합건물=").append(RELL_CO_BDNG_SNO).append("\r\n");
        sb.append("토지등급=").append(LV).append("\r\n");
        sb.append("토지등급변동일자=").append(LV_CH_YMD).append("\r\n");
        sb.append("공시지가=").append(JIGA).append("\r\n");
        sb.append("소재지=").append(ADDR).append("\r\n");
        return sb.toString();
    }

}
