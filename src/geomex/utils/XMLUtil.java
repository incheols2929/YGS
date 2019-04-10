package geomex.utils;

import java.awt.Color;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

/**
 * XML 문자열관련 유틸리티 클래스
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class XMLUtil {

    /**
     * XML을 파싱하여 DOM tree를 생성한다.
     * 
     * @param is InputStream
     * @return DocumentElement
     * @throws Exception
     */
    public static Element parseDOM(InputStream is) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(is).getDocumentElement();

    }

    /**
     * Element의 하위 Element중 name을 갖는 Element을 얻는다.
     * 
     * @param root 시작 Element
     * @param name Element 이름
     * @return Element
     */
    public static Element getElementByName(Element root, String name) {
        return (Element) root.getElementsByTagName(name).item(0);
    }

    /**
     * Attribute 값을 얻는다.
     * 
     * @param map NamedNodeMap
     * @param attrName Attribute 명
     * @return Attribute 값
     */
    public static String getAttrValue(NamedNodeMap map, String attrName) {
        Node node = map.getNamedItem(attrName);
        return (node == null) ? null : node.getNodeValue();
    }

    /**
     * root Element에 포함된 자식중 Element이름이 name인 것을 얻는다.<br>
     * 바로 아래 단계의 children만 얻는다.
     * 
     * @param root 시작 Element
     * @param tagName 자식 Element 이름
     * @return Element[]
     */
    public static Element[] getChildElements(Element root, String name) {
        ArrayList<Element> tags = new ArrayList<Element>();
        NodeList nodes = root.getChildNodes();
        int items = nodes.getLength();
        for (int x = 0; x < items; x++) {
            Node node = nodes.item(x);
            if (node.getNodeType() == Node.ELEMENT_NODE
                && node.getNodeName().equals(name)) {
                tags.add((Element) node);
            }
        }
        return tags.toArray(new Element[tags.size()]);
    }

    /**
     * Element의 값을 얻는다. <br>
     * 만약 Element가 하위 Element를 가지면 공백을 리턴한다.
     * 
     * @param node Element 명
     * @return Element value
     */
    public static String getTagValue(Element node) {
        String value = "";
        NodeList children = node.getChildNodes();
        if (children == null)
            return null;

        for (int i = 0; i < children.getLength(); i++) {
            int type = children.item(i).getNodeType();
            if (type == Node.TEXT_NODE || type == Node.CDATA_SECTION_NODE) {
                value = children.item(i).getNodeValue().trim();
            }
        }

        return value;
    }

    /**
     * XML데이터를 handler롤 파싱한다.
     * 
     * @param is XML데이터 스트림
     * @param handler XML 파싱 핸들러
     * @throws Exception
     */
    public static void parseSAX(InputStream is, DefaultHandler handler)
        throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(new InputSource(is), handler);
    }

    /**
     * Color객체를 'r,g,b,a' 형식의 문자열로 변환한다.
     * 
     * @param c 칼라 객체
     * @return String 'r,g,b,a' 형식의 문자열
     */
    public static String cvt(Color c) {
        if (c == null) {
            return "\'\'";
        }
        return "\'" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ","
            + c.getAlpha() + "\'";
    }

    /**
     * boolean을 'true' or 'false' 형식의 문자열로 변환한다.
     * 
     * @param s boolean 값
     * @return String 'true' or 'false' 형식의 문자열
     */
    public static String cvt(boolean s) {
        return "\'" + Boolean.toString(s) + "\'";
    }

    /**
     * 정수를 '1' 형식의 문자열로 변환한다.
     * 
     * @param s 정수 값
     * @return String '1' 형식의 문자열
     */
    public static String cvt(int s) {
        return "\'" + s + "\'";
    }

    /**
     * float를 '1.0' 형식의 문자열로 변환한다.
     * 
     * @param s 실수 값
     * @return String '1.0' 형식의 문자열
     */
    public static String cvt(float s) {
        return "\'" + s + "\'";
    }

    /**
     * double을 '1.0' 형식의 문자열로 변환한다.
     * 
     * @param s 실수 값
     * @return String '1.0' 형식의 문자열
     */
    public static String cvt(double s) {
        return "\'" + s + "\'";
    }

    /**
     * String을 'aaa' 형식의 문자열로 변환한다.
     * 
     * @param s 문자열
     * @return String 'aaa' 형식의 문자열
     */
    public static String cvt(String s) {
        if (s == null) {
            s = "";
        }
        return "\'" + s + "\'";
    }

    /**
     * 문자열 value를 double로 변환한다.
     * 
     * @param value 변환할 문자열
     * @return double
     */
    public static double getDouble(String value) {
        if (value == null) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0.0;
        }
    }

    /**
     * 문자열 value를 float로 변환한다.
     * 
     * @param value 변환할 문자열
     * @return float
     */
    public static float getFloat(String value) {
        if (value == null) {
            return 0.0f;
        }
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            return 0.0f;
        }
    }

    /**
     * 문자열 value를 float로 변환한다.
     * 
     * @param value 변환할 문자열
     * @return int
     */
    public static int getInt(String value) {
        if (value == null) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 문자열을 boolean으로 변환한다.
     * 
     * @param value 문자열
     * @return boolean
     */
    public static boolean getBoolean(String value) {
        if (value == null) {
            return false;
        }
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 문자열 value를 Color객체로 변환한다.
     * 
     * @param value 변환할 문자열
     * @return Color
     */
    public static Color getColor(String value) {
        if (value == null || value.length() < 1) {
            return null;
        }
        try {
            String str[] = value.split(",");
            return new Color(getInt(str[0].trim()), getInt(str[1].trim()),
                getInt(str[2].trim()), getInt(str[3].trim()));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Element 값을 얻는다.
     * 
     * @param e Element
     * @param name Element 명
     * @return Element 값
     */
    public static String getString(Element e, String name) {
        Element rv[] = XMLUtil.getChildElements(e, name);
        return XMLUtil.getTagValue(rv[0]);
    }

    /**
     * Element 값을 얻는다.
     * 
     * @param e Element
     * @param name Element명
     * @return Element 값
     */
    public static int getInt(Element e, String name) {
        Element rv[] = XMLUtil.getChildElements(e, name);
        return XMLUtil.getInt(XMLUtil.getTagValue(rv[0]));
    }

    /**
     * Attribute 값을 얻는다.
     * 
     * @param attr NamesNodeMap
     * @param name Attribute명
     * @return Attribute Value
     */
    public static boolean getBoolean(NamedNodeMap attr, String name) {
        return XMLUtil.getBoolean(XMLUtil.getAttrValue(attr, name));
    }

    /**
     * Attribute 값을 얻는다.
     * 
     * @param attr NamesNodeMap
     * @param name Attribute명
     * @return Attribute Value
     */
    public static int getInt(NamedNodeMap attr, String name) {
        return XMLUtil.getInt(XMLUtil.getAttrValue(attr, name));
    }

    /**
     * Attribute 값을 얻는다.
     * 
     * @param attr NamesNodeMap
     * @param name Attribute명
     * @return Attribute Value
     */
    public static double getDouble(NamedNodeMap attr, String name) {
        return XMLUtil.getDouble(XMLUtil.getAttrValue(attr, name));
    }

    /**
     * Attribute 값을 얻는다.
     * 
     * @param attr NamesNodeMap
     * @param name Attribute명
     * @return Attribute Value
     */
    public static String getString(NamedNodeMap attr, String name) {
        return XMLUtil.getAttrValue(attr, name);
    }
}
