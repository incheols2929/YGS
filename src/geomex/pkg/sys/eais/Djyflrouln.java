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

public class Djyflrouln implements Comparable<Djyflrouln> {

    public String HO_NM = ""; // 호명칭
    public String H_KEY = ""; // H_KEY

    public ArrayList<Djyflrouln> getFlrList(String key) throws ParserConfigurationException, SAXException, IOException {
        GetBldgXML gbx = new GetBldgXML();
        String getXML = gbx.getBldgXML(Const.getLrgstURL(), "GetJeonyubldg", key, "");
        ArrayList<Djyflrouln> list = new ArrayList<Djyflrouln>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc;
        try {
            doc = docBuilder.parse(new InputSource(new StringReader(getXML)));
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            return null;
        }

        NodeList nl1 = doc.getElementsByTagName("호명칭리스트");

        if (nl1.getLength() != 0) {
            for (int i = 0; i < nl1.getLength(); i++) {
                Node node = nl1.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList _child = node.getChildNodes();
                    for (int j = 0; j < _child.getLength(); j++) {
                        Djyflrouln dbl1 = new Djyflrouln();
                        Node _ele = _child.item(j);
                        Element _attr = (Element) _ele;
                        if (_ele.getNodeType() == Node.ELEMENT_NODE) {
                            String _name = _ele.getNodeName();
                            String _content = _ele.getTextContent();
                            if (_name.equalsIgnoreCase("호명칭")) {
                                dbl1.HO_NM = _content;
                                dbl1.H_KEY = _attr.getAttribute("H_KEY");
                            }
                        }
                        list.add(dbl1);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public int compareTo(Djyflrouln arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

}
