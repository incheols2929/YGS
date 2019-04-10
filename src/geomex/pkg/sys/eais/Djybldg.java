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

public class Djybldg implements Comparable<DjyBldgList> {

    //일반표제부
    public String BLDG_TYPE = ""; // 표제부_일반건축물
    public String ADDR = ""; // 대지위치
    public String BLD_NM = ""; // 명칭및번호
    public String PLATAREA = ""; // 대지면적
    public String TOTALAREA = ""; // 연면적
    public String BL_NUM = ""; // 호수
    public String ARCHAREA = ""; // 건축면적
    public String VL_RAT_AREA = ""; // 용적률산정연면적
    public String FLR = ""; // 층수
    public String BC_RAT = ""; // 건폐율
    public String VL_RAT = ""; // 용적율
    public String HEIGHT = ""; // 높이
    public String BL_USABILITY = ""; // 주용도
    public String ACC_BL = ""; // 부속건축물
    public String BL_STRCT = ""; // 주구조
    public String BL_ROOF = ""; // 지붕
    public String PMT_YMD = ""; // 허가일자
    public String CON_YMD = ""; // 착공일자
    public String ACC_YMD = ""; // 사용승인일자

    //층별현황
    public String FLR_TYPE = ""; // 구분
    public String FLR_NM = ""; // 층별
    public String FLR_STRCT = ""; // 구조
    public String FLR_USABILITY = ""; // 용도 
    public String FLR_AREA = ""; // 면적

    //소유현황
    public String OWNSP_CH_YMD = ""; // 변동일자
    public String OWNSP_CH_CAU_GBN_NM = ""; // 변동사유
    public String EQUITY = ""; // 지분
    public String OWNR_NM = ""; // 성명및명칭
    public String OWNR_NUMBER = ""; // 주민번호
    public String OWNR_ADDR = ""; // 주소

    //옥내외 대수 및 면적
    public String INSIDE_MT_NUM = ""; // 옥내_기계식_대수
    public String INSIDE_MT_AREA = ""; // 옥내_기계식_면적
    public String OUTSIDE_MT_NUM = ""; // 옥외_기계식_대수
    public String OUTSIDE_MT_AREA = ""; // 옥외_기계식_면적
    public String INSIDE_SF_NUM = ""; // 옥내_자주식_대수
    public String INSIDE_SF_AREA = ""; // 옥내_자주식_면적
    public String OUTSIDE_SF_NUM = ""; // 옥외_자주식_대수
    public String OUTSIDE_SF_AREA = ""; // 옥외_자주식_면적

    //승용
    public String RIDING = ""; // 승용
    public String RIDING_EMER = ""; // 비승용

    // 에너지
    public String ENG_CLASS = ""; // 에너지효율_등급
    public String ENG_SAVE = ""; // 에너지효율_절감율
    public String ENG_PFMC = ""; // 에너지성능지표_점

    // 친환경
    public String GBL_CLASS = ""; // 친환경건축물인증_등급
    public String GBL_NUM = ""; // 친환경건축물인증_점
    public String IBL_CLASS = ""; // 지능형건축물인증_등급
    public String IBL_NUM = ""; // 지능형건축물인증_점

    public ArrayList<Djybldg> getDjytitle(String key) throws ParserConfigurationException, SAXException, IOException {
        GetBldgXML gbx = new GetBldgXML();
        String getXML = gbx.getBldgXML(Const.getLrgstURL(), "GetDjytitle", key, "");
        ArrayList<Djybldg> list = new ArrayList<Djybldg>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc;
        try {
            doc = docBuilder.parse(new InputSource(new StringReader(getXML)));
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            return null;
        }

        NodeList nl = doc.getElementsByTagName("층별현황");
        NodeList nl1 = doc.getElementsByTagName("표제부_일반건축물");
        NodeList nl2 = doc.getElementsByTagName("대지위치");
        NodeList nl3 = doc.getElementsByTagName("명칭및번호");
        NodeList nl4 = doc.getElementsByTagName("대지면적");
        NodeList nl5 = doc.getElementsByTagName("연면적");
        NodeList nl6 = doc.getElementsByTagName("호수");
        NodeList nl7 = doc.getElementsByTagName("건축면적");
        NodeList nl8 = doc.getElementsByTagName("용적률산정용연면적");
        NodeList nl9 = doc.getElementsByTagName("층수");
        NodeList nl10 = doc.getElementsByTagName("건폐율");
        NodeList nl11 = doc.getElementsByTagName("용적율");
        NodeList nl12 = doc.getElementsByTagName("높이");
        NodeList nl13 = doc.getElementsByTagName("주용도");
        NodeList nl14 = doc.getElementsByTagName("부속건축물");
        NodeList nl15 = doc.getElementsByTagName("주구조");
        NodeList nl16 = doc.getElementsByTagName("지붕구조");
        NodeList nl17 = doc.getElementsByTagName("허가일자");
        NodeList nl18 = doc.getElementsByTagName("착공일자");
        NodeList nl19 = doc.getElementsByTagName("사용승인일자");
        NodeList nl20 = doc.getElementsByTagName("옥내_기계식_대수");
        NodeList nl21 = doc.getElementsByTagName("옥내_기계식_면적");
        NodeList nl22 = doc.getElementsByTagName("옥외_기계식_대수");
        NodeList nl23 = doc.getElementsByTagName("옥외_기계식_면적");
        NodeList nl24 = doc.getElementsByTagName("옥내_자주식_대수");
        NodeList nl25 = doc.getElementsByTagName("옥내_자주식_면적");
        NodeList nl26 = doc.getElementsByTagName("옥외_자주식_대수");
        NodeList nl27 = doc.getElementsByTagName("옥외_자주식_면적");
        NodeList nl28 = doc.getElementsByTagName("승용");
        NodeList nl29 = doc.getElementsByTagName("비상용");
        NodeList nl30 = doc.getElementsByTagName("에너지효율_등급");
        NodeList nl31 = doc.getElementsByTagName("에너지효율_절감율");
        NodeList nl32 = doc.getElementsByTagName("에너지성능지표_점");
        NodeList nl33 = doc.getElementsByTagName("친환경건축물인증_등급");
        NodeList nl34 = doc.getElementsByTagName("친환경건축물인증_점");
        NodeList nl35 = doc.getElementsByTagName("지능형건축물인증_등급");
        NodeList nl36 = doc.getElementsByTagName("지능형건축물인증_점");

        if (nl.getLength() != 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                Djybldg dbl = new Djybldg();
                Node node = nl.item(i);
                Node node19 = nl19.item(0);
                Node node1 = nl1.item(0);
                Node node20 = nl20.item(0);
                Node node2 = nl2.item(0);
                Node node21 = nl21.item(0);
                Node node3 = nl3.item(0);
                Node node22 = nl22.item(0);
                Node node4 = nl4.item(0);
                Node node23 = nl23.item(0);
                Node node5 = nl5.item(0);
                Node node24 = nl24.item(0);
                Node node6 = nl6.item(0);
                Node node25 = nl25.item(0);
                Node node7 = nl7.item(0);
                Node node26 = nl26.item(0);
                Node node8 = nl8.item(0);
                Node node27 = nl27.item(0);
                Node node9 = nl9.item(0);
                Node node28 = nl28.item(0);
                Node node10 = nl10.item(0);
                Node node29 = nl29.item(0);
                Node node11 = nl11.item(0);
                Node node30 = nl30.item(0);
                Node node12 = nl12.item(0);
                Node node31 = nl31.item(0);
                Node node13 = nl13.item(0);
                Node node32 = nl32.item(0);
                Node node14 = nl14.item(0);
                Node node33 = nl33.item(0);
                Node node15 = nl15.item(0);
                Node node34 = nl34.item(0);
                Node node16 = nl16.item(0);
                Node node35 = nl35.item(0);
                Node node17 = nl17.item(0);
                Node node36 = nl36.item(0);
                Node node18 = nl18.item(0);

                if (node1.getNodeName().equalsIgnoreCase("표제부_일반건축물")) {
                    dbl.BLDG_TYPE = node1.getTextContent();
                }
                if (node2.getNodeName().equalsIgnoreCase("대지위치")) {
                    dbl.ADDR = node2.getTextContent();
                }
                if (node3.getNodeName().equalsIgnoreCase("명칭및번호")) {
                    dbl.BLD_NM = node3.getTextContent();
                }
                if (node4.getNodeName().equalsIgnoreCase("대지면적")) {
                    dbl.PLATAREA = node4.getTextContent();
                }
                if (node5.getNodeName().equalsIgnoreCase("연면적")) {
                    dbl.TOTALAREA = node5.getTextContent();
                }
                if (node6.getNodeName().equalsIgnoreCase("호수")) {
                    dbl.BL_NUM = node6.getTextContent();
                }
                if (node7.getNodeName().equalsIgnoreCase("건축면적")) {
                    dbl.ARCHAREA = node7.getTextContent();
                }
                if (node8.getNodeName().equalsIgnoreCase("용적률산정용연면적")) {
                    dbl.VL_RAT_AREA = node8.getTextContent();
                }
                if (node9.getNodeName().equalsIgnoreCase("층수")) {
                    dbl.FLR = node9.getTextContent();
                }
                if (node10.getNodeName().equalsIgnoreCase("건폐율")) {
                    dbl.BC_RAT = node10.getTextContent();
                }
                if (node11.getNodeName().equalsIgnoreCase("용적율")) {
                    dbl.VL_RAT = node11.getTextContent();
                }
                if (node12.getNodeName().equalsIgnoreCase("높이")) {
                    dbl.HEIGHT = node12.getTextContent();
                }
                if (node13.getNodeName().equalsIgnoreCase("주용도")) {
                    dbl.BL_USABILITY = node13.getTextContent();
                }
                if (node14.getNodeName().equalsIgnoreCase("부속건축물")) {
                    dbl.ACC_BL = node14.getTextContent();
                }
                if (node15.getNodeName().equalsIgnoreCase("주구조")) {
                    dbl.BL_STRCT = node15.getTextContent();
                }
                if (node16.getNodeName().equalsIgnoreCase("지붕구조")) {
                    dbl.BL_ROOF = node16.getTextContent();
                }
                if (node17.getNodeName().equalsIgnoreCase("허가일자")) {
                    dbl.PMT_YMD = node17.getTextContent();
                }
                if (node18.getNodeName().equalsIgnoreCase("착공일자")) {
                    dbl.CON_YMD = node18.getTextContent();
                }
                if (node19.getNodeName().equalsIgnoreCase("사용승인일자")) {
                    dbl.ACC_YMD = node19.getTextContent();
                }
                if (node20.getNodeName().equalsIgnoreCase("옥내_기계식_대수")) {
                    dbl.INSIDE_MT_NUM = node20.getTextContent();
                }
                if (node21.getNodeName().equalsIgnoreCase("옥내_기계식_면적")) {
                    dbl.INSIDE_MT_AREA = node21.getTextContent();
                }
                if (node22.getNodeName().equalsIgnoreCase("옥외_기계식_대수")) {
                    dbl.OUTSIDE_MT_NUM = node22.getTextContent();
                }
                if (node23.getNodeName().equalsIgnoreCase("옥외_기계식_면적")) {
                    dbl.OUTSIDE_MT_AREA = node23.getTextContent();
                }
                if (node24.getNodeName().equalsIgnoreCase("옥내_자주식_대수")) {
                    dbl.INSIDE_SF_NUM = node24.getTextContent();
                }
                if (node25.getNodeName().equalsIgnoreCase("옥내_자주식_면적")) {
                    dbl.INSIDE_SF_AREA = node25.getTextContent();
                }
                if (node26.getNodeName().equalsIgnoreCase("옥외_자주식_대수")) {
                    dbl.OUTSIDE_SF_NUM = node26.getTextContent();
                }
                if (node27.getNodeName().equalsIgnoreCase("옥외_자주식_면적")) {
                    dbl.OUTSIDE_SF_AREA = node27.getTextContent();
                }
                if (node28.getNodeName().equalsIgnoreCase("승용")) {
                    dbl.RIDING = node28.getTextContent();
                }
                if (node29.getNodeName().equalsIgnoreCase("비상용")) {
                    dbl.RIDING_EMER = node29.getTextContent();
                }
                if (node30.getNodeName().equalsIgnoreCase("에너지효율_등급")) {
                    dbl.ENG_CLASS = node30.getTextContent();
                }
                if (node31.getNodeName().equalsIgnoreCase("에너지효율_절감율")) {
                    dbl.ENG_SAVE = node31.getTextContent();
                }
                if (node32.getNodeName().equalsIgnoreCase("에너지성능지표_점")) {
                    dbl.ENG_PFMC = node32.getTextContent();
                }
                if (node33.getNodeName().equalsIgnoreCase("친환경건축물인증_등급")) {
                    dbl.GBL_CLASS = node33.getTextContent();
                }
                if (node34.getNodeName().equalsIgnoreCase("친환경건축물인증_점")) {
                    dbl.GBL_NUM = node34.getTextContent();
                }
                if (node35.getNodeName().equalsIgnoreCase("지능형건축물인증_등급")) {
                    dbl.IBL_CLASS = node35.getTextContent();
                }
                if (node36.getNodeName().equalsIgnoreCase("지능형건축물인증_점")) {
                    dbl.IBL_NUM = node36.getTextContent();
                }

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList _child = node.getChildNodes();
                    for (int j = 0; j < _child.getLength(); j++) {
                        Node _ele = _child.item(j);
                        if (_ele.getNodeType() == Node.ELEMENT_NODE) {
                            String _name = _ele.getNodeName();
                            String _content = _ele.getTextContent();
                            if (_name.equalsIgnoreCase("구분")) {
                                dbl.FLR_TYPE = _content;
                            } else if (_name.equalsIgnoreCase("층별")) {
                                dbl.FLR_NM = _content;
                            } else if (_name.equalsIgnoreCase("구조")) {
                                dbl.FLR_STRCT = _content;
                            } else if (_name.equalsIgnoreCase("용도")) {
                                dbl.FLR_USABILITY = _content;
                            } else if (_name.equalsIgnoreCase("면적")) {
                                dbl.FLR_AREA = _content;
                            }
                        }
                    }
                }
                list.add(dbl);
            }
        }
        return list;
    }

    public ArrayList<Djybldg> getOwnerList(String key) throws ParserConfigurationException, SAXException, IOException {
        GetBldgXML gbx = new GetBldgXML();
        String getXML = gbx.getBldgXML(Const.getLrgstURL(), "GetDjybldg", key, "");
        ArrayList<Djybldg> list = new ArrayList<Djybldg>();

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
                Djybldg dbl = new Djybldg();
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
