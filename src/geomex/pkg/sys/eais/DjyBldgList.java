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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DjyBldgList implements Comparable<DjyBldgList> {

    public String LAND_CD = ""; //고유번호
    public String BLDRGST_PK = ""; //대장종류(키)
    public String BLDRGST_NM = ""; //대장종류
    public String JBLDRGST_PK = ""; //전유부(키)
    public String JBLDRGST_NM = ""; //전유부종류
    public String BLD_NM = ""; //명칭및번호
    public String USABILITY = ""; //주용도
    public String TOTALAREA = ""; //연면적

    public ArrayList<DjyBldgList> getBldgList(String pnu) throws ParserConfigurationException, SAXException, IOException {
        GetBldgXML gbx = new GetBldgXML();
        String getXML = gbx.getBldgXML(Const.getLrgstURL(), "GetBldgList", pnu, "");
        ArrayList<DjyBldgList> list = new ArrayList<DjyBldgList>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc;
        try {
            doc = docBuilder.parse(new InputSource(new StringReader(getXML)));
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            return null;
        }

        NodeList nl1 = doc.getElementsByTagName("건축물리스트");
        NodeList nl2 = doc.getElementsByTagName("고유번호");

        if (nl1.getLength() != 0) {
            for (int i = 0; i < nl1.getLength(); i++) {
                DjyBldgList dbl = new DjyBldgList();
                Node node = nl1.item(i);
                Node node2 = nl2.item(0);
                if (node2.getNodeName().equalsIgnoreCase("고유번호")) {
                    dbl.LAND_CD = node2.getTextContent();
                }
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList _child = node.getChildNodes();
                    for (int j = 0; j < _child.getLength(); j++) {
                        Node _ele = _child.item(j);
                        Element _attr = (Element) _ele;
                        if (_ele.getNodeType() == Node.ELEMENT_NODE) {
                            String _name = _ele.getNodeName();
                            String _content = _ele.getTextContent();
                            if (_name.equalsIgnoreCase("대장종류")) {
                                dbl.BLDRGST_NM = _content;
                                dbl.BLDRGST_PK = _attr.getAttribute("KEY");
                            } else if (_name.equalsIgnoreCase("전유부")) {
                                dbl.JBLDRGST_NM = _content;
                                dbl.JBLDRGST_PK = _attr.getAttribute("J_KEY");
                            } else if (_name.equalsIgnoreCase("명칭및번호")) {
                                dbl.BLD_NM = _content;
                            } else if (_name.equalsIgnoreCase("주용도")) {
                                dbl.USABILITY = _content;
                            } else if (_name.equalsIgnoreCase("연면적")) {
                                dbl.TOTALAREA = _content;
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
