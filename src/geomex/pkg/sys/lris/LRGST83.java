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
 * 공시지가 조회
 */
public class LRGST83 implements Comparable<LRGST83> {
    public String SHAP_SNO = "";
    public String JIGA = ""; // 개별공지시가
    public String JIGA_YMD = ""; // 기준년월일

    public LRGST83() {

    }

    public ArrayList<LRGST83> getBaseInfo(String pnu) throws SAXException, IOException, ParserConfigurationException {
        GetLrgstXML gtx = new GetLrgstXML();
        String getXML = gtx.getLrgstXML(pnu, Const.getLrgstURL(), "GetJigaInfo");
        ArrayList<LRGST83> list = new ArrayList<LRGST83>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc = docBuilder.parse(new InputSource(new StringReader(getXML)));
        doc.getDocumentElement().normalize();
        NodeList nl1 = doc.getElementsByTagName("개별공시지가");
        NodeList nl2 = doc.getElementsByTagName("기준년월일");

        if (nl1.getLength() != 0) {
            Node node1 = nl1.item(0);
            if (node1.getNodeType() == Node.ELEMENT_NODE) {
                for (int i = 0; i < nl1.getLength(); i++) {
                    LRGST83 lrgst83 = new LRGST83();
                    Node _ele1 = nl1.item(i);
                    Node _ele2 = nl2.item(i);
                    if (_ele1.getNodeType() == Node.ELEMENT_NODE) {
                        String _name1 = _ele1.getNodeName();
                        String _name2 = _ele2.getNodeName();
                        String _content1 = _ele1.getTextContent();
                        String _content2 = _ele2.getTextContent();
                        if (_name1.equalsIgnoreCase("개별공시지가")) {
                            lrgst83.JIGA = _content1;
                        }
                        if (_name2.equalsIgnoreCase("기준년월일")) {
                            lrgst83.JIGA_YMD = _content2;
                        }
                    }
                    list.add(lrgst83);
                }
            }
        }

        return list;
    }

    @Override
    public int compareTo(LRGST83 o) {
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
        sb.append("<지가>");
        sb.append("<기준년월일>").append(JIGA_YMD).append("</기준년월일>");
        sb.append("<개별공시지가>").append(JIGA).append("</개별공시지가>");
        sb.append("</지가>");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("기준년월일=").append("JIGA").append("\r\n");
        sb.append("개별공시지가=").append("JIGA_YMD").append("\r\n");
        return sb.toString();
    }

    /*
     * public static void main(String[] args) throws SAXException, IOException,
     * ParserConfigurationException{ LRGST83 st83 = new LRGST83();
     * ArrayList<LRGST83> list = st83.getBaseInfo("4211011400100990001");
     * for(int i=0; i<list.size(); i++){ System.out.println("기준년월일 " +i+" : "+
     * list.get(i).JIGA_YMD); System.out.println("개별공시지가 "
     * +i+" : "+list.get(i).JIGA); } }
     */

}
