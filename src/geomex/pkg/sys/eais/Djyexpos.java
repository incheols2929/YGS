package geomex.pkg.sys.eais;

import geomex.svc.handler.GetBldgXML;
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

public class Djyexpos implements Comparable<DjyBldgList> {

    //전유현황
    public String BL_TYPE = ""; // 구분
    public String FLR_NM = ""; // 층별
    public String STRCT = ""; // 구조
    public String USABILITY = ""; // 용도
    public String AREA = ""; // 면적

    //소유자현황
    public String OWNSP_CH_YMD = ""; // 변동일자
    public String OWNSP_CH_CAU_GBN_NM = ""; // 변동원인
    public String EQUITY = ""; // 지분
    public String OWNR_NM = ""; // 성명및명칭
    public String OWNR_ADDR = ""; // 주소
    public String OWNR_NUMBER = ""; // 주민번호

    public ArrayList<Djyexpos> getExposList(String key, String ouln_pk) throws ParserConfigurationException, SAXException,
        IOException {
        GetBldgXML gbx = new GetBldgXML();
        String getXML = gbx.getBldgXML(Const.getLrgstURL(), "GetDjyexpos", key, ouln_pk);
        ArrayList<Djyexpos> list = new ArrayList<Djyexpos>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc;
        try {
            doc = docBuilder.parse(new InputSource(new StringReader(getXML)));
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            return null;
        }

        NodeList nl1 = doc.getElementsByTagName("전유현황");

        if (nl1.getLength() != 0) {
            for (int i = 0; i < nl1.getLength(); i++) {
                Djyexpos dbl = new Djyexpos();
                Node node = nl1.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList _child = node.getChildNodes();
                    for (int j = 0; j < _child.getLength(); j++) {
                        Node _ele = _child.item(j);
                        if (_ele.getNodeType() == Node.ELEMENT_NODE) {
                            String _name = _ele.getNodeName();
                            String _content = _ele.getTextContent();
                            if (_name.equalsIgnoreCase("구분")) {
                                dbl.BL_TYPE = _content;
                            } else if (_name.equalsIgnoreCase("층별")) {
                                dbl.FLR_NM = _content;
                            } else if (_name.equalsIgnoreCase("구조")) {
                                dbl.STRCT = _content;
                            } else if (_name.equalsIgnoreCase("용도")) {
                                dbl.USABILITY = _content;
                            } else if (_name.equalsIgnoreCase("면적")) {
                                dbl.AREA = _content;
                            }
                        }
                    }
                }
                list.add(dbl);
            }
        }
        return list;
    }

    public ArrayList<Djyexpos> getOwnerList(String key, String ouln_pk) throws ParserConfigurationException, SAXException,
        IOException {
        GetBldgXML gbx = new GetBldgXML();
        String getXML = gbx.getBldgXML(Const.getLrgstURL(), "GetDjyexpos", key, ouln_pk);
        ArrayList<Djyexpos> list = new ArrayList<Djyexpos>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc;
        try {
            doc = docBuilder.parse(new InputSource(new StringReader(getXML)));
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            return null;
        }

        NodeList nl1 = doc.getElementsByTagName("소유자현황");

        if (nl1.getLength() != 0) {
            for (int i = 0; i < nl1.getLength(); i++) {
                Djyexpos dbl = new Djyexpos();
                Node node = nl1.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList _child = node.getChildNodes();
                    for (int j = 0; j < _child.getLength(); j++) {
                        Node _ele = _child.item(j);
                        if (_ele.getNodeType() == Node.ELEMENT_NODE) {
                            String _name = _ele.getNodeName();
                            String _content = _ele.getTextContent();
                            if (_name.equalsIgnoreCase("변동일자")) {
                                dbl.OWNSP_CH_YMD = _content;
                            } else if (_name.equalsIgnoreCase("변동원인")) {
                                dbl.OWNSP_CH_CAU_GBN_NM = _content;
                            } else if (_name.equalsIgnoreCase("지분")) {
                                dbl.EQUITY = _content;
                            } else if (_name.equalsIgnoreCase("성명및명칭")) {
                                dbl.OWNR_NM = _content;
                            } else if (_name.equalsIgnoreCase("주소")) {
                                dbl.OWNR_ADDR = _content;
                            } else if (_name.equalsIgnoreCase("주민번호")) {
                                dbl.OWNR_NUMBER = _content;
                            }
                        }
                    }
                }
                list.add(dbl);
            }
        }
        return list;
    }

    @Override
    public int compareTo(DjyBldgList arg0) {
        // TODO Auto-generated method stub
        return 0;
    }
}
