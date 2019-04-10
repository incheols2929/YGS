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

public class Djyrecaptitle implements Comparable<DjyBldgList> {

    // 총괄표제부
    public String ADDR = ""; // 대지위치
    public String BLD_NM = ""; // 명칭및번호
    public String PLATAREA = ""; // 대지면적
    public String TOTALAREA = ""; // 연면적
    public String BL_CNT = ""; // 건축물수
    public String ARCHAREA = ""; // 건축면적
    public String VL_RAT_AREA = ""; // 용적률산정연면적
    public String TOTAL_BL_NUM = ""; // 총호수
    public String BC_RAT = ""; // 건폐율
    public String VL_RAT = ""; // 용적율
    public String PARKING_CNT = ""; // 총주차대수
    public String BL_USABILITY = ""; // 주용도
    public String ACC_BL = ""; // 부속건축물
    public String REMARK = ""; // 특이사항

    // 동별현황리스트
    public String BL_TYPE = ""; // 구분
    public String BL_NM = ""; // 건축물명칭
    public String STRCT = ""; // 구조
    public String ROOF = ""; // 지붕
    public String FLR = ""; // 층수
    public String USABILITY = ""; // 용도
    public String AREA = ""; // 면적

    // 옥내외 대수 및 면적
    public String INSIDE_MT_NUM = ""; // 옥내_기계식_대수
    public String INSIDE_MT_AREA = ""; // 옥내_기계식_면적
    public String OUTSIDE_MT_NUM = ""; // 옥외_기계식_대수
    public String OUTSIDE_MT_AREA = ""; // 옥외_기계식_면적
    public String INSIDE_SF_NUM = ""; // 옥내_자주식_대수
    public String INSIDE_SF_AREA = ""; // 옥내_자주식_면적
    public String OUTSIDE_SF_NUM = ""; // 옥외_자주식_대수
    public String OUTSIDE_SF_AREA = ""; // 옥외_자주식_면적

    // 에너지
    public String ENG_CLASS = ""; // 에너지효율_등급
    public String ENG_SAVE = ""; // 에너지효율_절감율
    public String ENG_PFMC = ""; // 에너지성능지표_점

    // 친환경
    public String GBL_CLASS = ""; // 친환경건축물인증_등급
    public String GBL_NUM = ""; // 친환경건축물인증_점
    public String IBL_CLASS = ""; // 지능형건축물인증_등급
    public String IBL_NUM = ""; // 지능형건축물인증_점

    public ArrayList<Djyrecaptitle> getDjyrecaptitle(String key) throws ParserConfigurationException, SAXException, IOException {
        GetBldgXML gbx = new GetBldgXML();
        String getXML = gbx.getBldgXML(Const.getLrgstURL(), "GetDjyrecaptitle", key, "");
        ArrayList<Djyrecaptitle> list = new ArrayList<Djyrecaptitle>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc;
        try {
            doc = docBuilder.parse(new InputSource(new StringReader(getXML)));
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            return null;
        }

        NodeList nl1 = doc.getElementsByTagName("동별현황");
        NodeList nl2 = doc.getElementsByTagName("대지위치");
        NodeList nl3 = doc.getElementsByTagName("명칭및번호");
        NodeList nl4 = doc.getElementsByTagName("연면적");
        NodeList nl5 = doc.getElementsByTagName("건축물수");
        NodeList nl6 = doc.getElementsByTagName("건축면적");
        NodeList nl7 = doc.getElementsByTagName("용적률산정연면적");
        NodeList nl8 = doc.getElementsByTagName("총호수");
        NodeList nl9 = doc.getElementsByTagName("건폐율");
        NodeList nl10 = doc.getElementsByTagName("용적율");
        NodeList nl11 = doc.getElementsByTagName("총주차대수");
        NodeList nl12 = doc.getElementsByTagName("주용도");
        NodeList nl13 = doc.getElementsByTagName("부속건축물");
        NodeList nl14 = doc.getElementsByTagName("특이사항");
        NodeList nl15 = doc.getElementsByTagName("옥내_기계식_대수");
        NodeList nl16 = doc.getElementsByTagName("옥내_기계식_면적");
        NodeList nl17 = doc.getElementsByTagName("옥외_기계식_대수");
        NodeList nl18 = doc.getElementsByTagName("옥외_기계식_면적");
        NodeList nl19 = doc.getElementsByTagName("옥내_자주식_대수");
        NodeList nl20 = doc.getElementsByTagName("옥내_자주식_면적");
        NodeList nl21 = doc.getElementsByTagName("옥외_자주식_대수");
        NodeList nl22 = doc.getElementsByTagName("옥외_자주식_면적");
        NodeList nl23 = doc.getElementsByTagName("에너지효율_등급");
        NodeList nl24 = doc.getElementsByTagName("에너지효율_절감율");
        NodeList nl25 = doc.getElementsByTagName("에너지성능지표_점");
        NodeList nl26 = doc.getElementsByTagName("친환경건축물인증_등급");
        NodeList nl27 = doc.getElementsByTagName("친환경건축물인증_점");
        NodeList nl28 = doc.getElementsByTagName("지능형건축물인증_등급");
        NodeList nl29 = doc.getElementsByTagName("지능형건축물인증_점");
        NodeList nl30 = doc.getElementsByTagName("대지면적");

        if (nl1.getLength() != 0) {
            for (int i = 0; i < nl1.getLength(); i++) {
                Djyrecaptitle dbl = new Djyrecaptitle();
                Node node = nl1.item(i);
                Node node30 = nl30.item(0);
                Node node2 = nl2.item(0);
                Node node16 = nl16.item(0);
                Node node3 = nl3.item(0);
                Node node17 = nl17.item(0);
                Node node4 = nl4.item(0);
                Node node18 = nl18.item(0);
                Node node5 = nl5.item(0);
                Node node19 = nl19.item(0);
                Node node6 = nl6.item(0);
                Node node20 = nl20.item(0);
                Node node7 = nl7.item(0);
                Node node21 = nl21.item(0);
                Node node8 = nl8.item(0);
                Node node22 = nl22.item(0);
                Node node9 = nl9.item(0);
                Node node23 = nl23.item(0);
                Node node10 = nl10.item(0);
                Node node24 = nl24.item(0);
                Node node11 = nl11.item(0);
                Node node25 = nl25.item(0);
                Node node12 = nl12.item(0);
                Node node26 = nl26.item(0);
                Node node13 = nl13.item(0);
                Node node27 = nl27.item(0);
                Node node14 = nl14.item(0);
                Node node28 = nl28.item(0);
                Node node15 = nl15.item(0);
                Node node29 = nl29.item(0);

                if (node2.getNodeName().equalsIgnoreCase("대지위치")) {
                    dbl.ADDR = node2.getTextContent();
                }
                if (node3.getNodeName().equalsIgnoreCase("명칭및번호")) {
                    dbl.BLD_NM = node3.getTextContent();
                }
                if (node4.getNodeName().equalsIgnoreCase("연면적")) {
                    dbl.TOTALAREA = node4.getTextContent();
                }
                if (node5.getNodeName().equalsIgnoreCase("건축물수")) {
                    dbl.BL_CNT = node5.getTextContent();
                }
                if (node6.getNodeName().equalsIgnoreCase("건축면적")) {
                    dbl.ARCHAREA = node6.getTextContent();
                }
                if (node7.getNodeName().equalsIgnoreCase("용적률산정연면적")) {
                    dbl.VL_RAT_AREA = node7.getTextContent();
                }
                if (node8.getNodeName().equalsIgnoreCase("총호수")) {
                    dbl.TOTAL_BL_NUM = node8.getTextContent();
                }
                if (node9.getNodeName().equalsIgnoreCase("건폐율")) {
                    dbl.BC_RAT = node9.getTextContent();
                }
                if (node10.getNodeName().equalsIgnoreCase("용적율")) {
                    dbl.VL_RAT = node10.getTextContent();
                }
                if (node11.getNodeName().equalsIgnoreCase("총주차대수")) {
                    dbl.PARKING_CNT = node11.getTextContent();
                }
                if (node12.getNodeName().equalsIgnoreCase("주용도")) {
                    dbl.BL_USABILITY = node12.getTextContent();
                }
                if (node13.getNodeName().equalsIgnoreCase("부속건축물")) {
                    dbl.ACC_BL = node13.getTextContent();
                }
                if (node14.getNodeName().equalsIgnoreCase("특이사항")) {
                    dbl.REMARK = node14.getTextContent();
                }
                if (node15.getNodeName().equalsIgnoreCase("옥내_기계식_대수")) {
                    dbl.INSIDE_MT_NUM = node15.getTextContent();
                }
                if (node16.getNodeName().equalsIgnoreCase("옥내_기계식_면적")) {
                    dbl.INSIDE_MT_AREA = node16.getTextContent();
                }
                if (node17.getNodeName().equalsIgnoreCase("옥외_기계식_대수")) {
                    dbl.OUTSIDE_MT_NUM = node17.getTextContent();
                }
                if (node18.getNodeName().equalsIgnoreCase("옥외_기계식_면적")) {
                    dbl.OUTSIDE_MT_AREA = node18.getTextContent();
                }
                if (node19.getNodeName().equalsIgnoreCase("옥내_자주식_대수")) {
                    dbl.INSIDE_SF_NUM = node19.getTextContent();
                }
                if (node20.getNodeName().equalsIgnoreCase("옥내_자주식_면적")) {
                    dbl.INSIDE_SF_AREA = node20.getTextContent();
                }
                if (node21.getNodeName().equalsIgnoreCase("옥외_자주식_대수")) {
                    dbl.OUTSIDE_SF_NUM = node21.getTextContent();
                }
                if (node22.getNodeName().equalsIgnoreCase("옥외_자주식_면적")) {
                    dbl.OUTSIDE_SF_AREA = node22.getTextContent();
                }
                if (node23.getNodeName().equalsIgnoreCase("에너지효율_등급")) {
                    dbl.ENG_CLASS = node23.getTextContent();
                }
                if (node24.getNodeName().equalsIgnoreCase("에너지효율_절감율")) {
                    dbl.ENG_SAVE = node24.getTextContent();
                }
                if (node25.getNodeName().equalsIgnoreCase("에너지성능지표_점")) {
                    dbl.ENG_PFMC = node25.getTextContent();
                }
                if (node26.getNodeName().equalsIgnoreCase("친환경건축물인증_등급")) {
                    dbl.GBL_CLASS = node26.getTextContent();
                }
                if (node27.getNodeName().equalsIgnoreCase("친환경건축물인증_점")) {
                    dbl.GBL_NUM = node27.getTextContent();
                }
                if (node28.getNodeName().equalsIgnoreCase("지능형건축물인증_등급")) {
                    dbl.IBL_CLASS = node28.getTextContent();
                }
                if (node29.getNodeName().equalsIgnoreCase("지능형건축물인증_점")) {
                    dbl.IBL_NUM = node29.getTextContent();
                }
                if (node30.getNodeName().equalsIgnoreCase("대지면적")) {
                    dbl.PLATAREA = node30.getTextContent();
                }

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList _child = node.getChildNodes();
                    for (int j = 0; j < _child.getLength(); j++) {
                        Node _ele = _child.item(j);
                        if (_ele.getNodeType() == Node.ELEMENT_NODE) {
                            String _name = _ele.getNodeName();
                            String _content = _ele.getTextContent();
                            if (_name.equalsIgnoreCase("구분")) {
                                dbl.BL_TYPE = _content;
                            } else if (_name.equalsIgnoreCase("건축물명칭")) {
                                dbl.BL_NM = _content;
                            } else if (_name.equalsIgnoreCase("구조")) {
                                dbl.STRCT = _content;
                            } else if (_name.equalsIgnoreCase("지붕")) {
                                dbl.ROOF = _content;
                            } else if (_name.equalsIgnoreCase("층수")) {
                                dbl.FLR = _content;
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

    @Override
    public int compareTo(DjyBldgList arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

}
