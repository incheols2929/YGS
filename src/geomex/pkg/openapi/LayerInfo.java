package geomex.pkg.openapi;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import geomex.svc.webctrl.Const;
import geomex.utils.db.DBHandler;
import geomex.utils.Utils;

public class LayerInfo {

    private String owner = "user";
    private String title = "시스템 기본설정";
    private String xml = "";

    public LayerInfo() {
        xml = getStyleXmlFromDB();
    }

    public LayerInfo(String _owner, String _title) {
        // TODO Auto-generated constructor stub
        owner = _owner;
        title = getStyleXmlTitleNM(_owner);
        xml = getStyleXmlFromDB();
    }

    public LayerInfo(String _xml) {
        // TODO Auto-generated constructor stub
        xml = _xml;
    }

    public ArrayList<GmxLayerDefinitionLayerGroupItem> getLayerGroupDef() throws Exception {
        ArrayList<GmxLayerDefinitionLayerGroupItem> groupList = new ArrayList<GmxLayerDefinitionLayerGroupItem>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc = docBuilder.parse(new InputSource(new StringReader(xml)));
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("GroupItem");

        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                GmxLayerDefinitionLayerGroupItem groupItem = new GmxLayerDefinitionLayerGroupItem();

                Element firstElement = (Element) node;
                //<GroupItem> 속성 설정, 대소문자 구별없이 하기위한 절차.
                for (int j = 0; j < firstElement.getAttributes().getLength(); j++)
                {
                    if ("index".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        groupItem.GroupItem_index = firstElement.getAttributes().item(j).getNodeValue();
                    if ("name".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        groupItem.GroupItem_name = firstElement.getAttributes().item(j).getNodeValue();
                }
                groupList.add(groupItem);
            }
        }
        sortGroupItem(groupList);
        return groupList;
    }

    public String getStyleXmlFromDB() {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        String xml = "";

        sb.append("select _contents from _gmx_style where _owner = '" + owner + "' and _title = '" + title + "' ");
        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            if (handler.next())
            {
                xml = handler.getString("_contents");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return xml;
    }

    public String getStyleXmlTitleNM(String owner) {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        String title = "";

        sb.append("select _title from _gmx_style where _owner = '" + owner + "' and _fixed='T' ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            if (handler.next())
            {
                title = handler.getString("_title");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return title;
    }

    /**
     * DB에 있는 xml문자열을 정의한다.
     * 
     * @throws Exception
     */
    public ArrayList<GmxLayerDefinition> getLayerDef() throws Exception {

        ArrayList<GmxLayerDefinition> layerDefList = new ArrayList<GmxLayerDefinition>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc = docBuilder.parse(new InputSource(new StringReader(xml)));

        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("Layer");
        NodeList nodeStyleDefList = doc.getElementsByTagName("StyleDefinition");
        Node nodeStyle = nodeStyleDefList.item(0);
        Element styleElement = (Element) nodeStyle;
        NodeList nodeStyleList = styleElement.getElementsByTagName("Style");

        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                GmxLayerDefinition layerDef = new GmxLayerDefinition();

                Element firstElement = (Element) node;
                //<Layer> 속성 설정, 대소문자 구별없이 하기위한 절차.
                for (int j = 0; j < firstElement.getAttributes().getLength(); j++)
                {
                    if ("dataset".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Layer_dataset = firstElement.getAttributes().item(j).getNodeValue();
                    if ("layername".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Layer_layername = firstElement.getAttributes().item(j).getNodeValue();
                    if ("groupname".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Layer_groupname = firstElement.getAttributes().item(j).getNodeValue();
                    if ("zindex".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Layer_zIndex = firstElement.getAttributes().item(j).getNodeValue();
                    if ("type".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Layer_type = firstElement.getAttributes().item(j).getNodeValue();
                    if ("connector".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Layer_connector = firstElement.getAttributes().item(j).getNodeValue();
                    if ("nodename".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Layer_nodeName = firstElement.getAttributes().item(j).getNodeValue();
                    if ("fullloading".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Layer_fullLoading = firstElement.getAttributes().item(j).getNodeValue();
                    if ("editable".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Layer_editable = firstElement.getAttributes().item(j).getNodeValue();
                    if ("selectable".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Layer_selectable = firstElement.getAttributes().item(j).getNodeValue();
                    if ("visible".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Layer_visible = firstElement.getAttributes().item(j).getNodeValue();
                    if ("minscale".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Layer_minScale = firstElement.getAttributes().item(j).getNodeValue();
                    if ("maxscale".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Layer_maxScale = firstElement.getAttributes().item(j).getNodeValue();
                    //<Layer><Extent>속성 설정.

                }

                //Extent노드 설정
                NodeList extent = firstElement.getElementsByTagName("Extent");
                setLayerExtentNode(layerDef, extent);

                //Clause노드 설정
                NodeList clause = firstElement.getElementsByTagName("Clause");
                setLayerClauseNode(layerDef, clause);

                //Label노드 설정
                NodeList label = firstElement.getElementsByTagName("Label");
                setLayerLabelNode(layerDef, label);

                //Style노드 설정
                NodeList style = firstElement.getElementsByTagName("Style");
                setLayerStyleNode(layerDef, style, nodeStyleList);

                //Symbol노드 설정
                NodeList symbol = firstElement.getElementsByTagName("Symbol");
                setLayerSymbolNode(layerDef, symbol);

                //Menu노드 설정
                NodeList menu = firstElement.getElementsByTagName("Menu");
                setLayerMenuNode(layerDef, menu);
                //System.out.println(layerDef.Layer_layername+","+layerDef.ExtentItems.size());
                layerDefList.add(layerDef);

            }
        }

        sortLayerDef(layerDefList);
        return layerDefList;
    }

    /**
     * Layer의 Extent를 설정.
     * 
     * @param layerDef
     * @param nodeList
     */
    private void setLayerExtentNode(GmxLayerDefinition layerDef, NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element firstElement = (Element) node;
                NodeList childNodeList = firstElement.getElementsByTagName("ExtentItem");

                //<Layer><Extent> 속성 설정, 대소문자 구별없이 하기위한 절차.
                for (int j = 0; j < firstElement.getAttributes().getLength(); j++)
                {
                    if ("pkey".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Extent_pkey = firstElement.getAttributes().item(j).getNodeValue();
                    if ("heightcolumn".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Extent_heightColumn = firstElement.getAttributes().item(j).getNodeValue();
                    if ("anglecolumn".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Extent_angleColumn = firstElement.getAttributes().item(j).getNodeValue();
                }

                //System.out.println("layerDef.Extent_pkey : " + layerDef.Extent_pkey);
                setLayerExtentExtentItemNode(layerDef, childNodeList);
            }
        }
    }

    /**
     * Layer의 Clause를 설정.
     * 
     * @param layerDef
     * @param nodeList
     */
    private void setLayerClauseNode(GmxLayerDefinition layerDef, NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            GmxLayerDefinitionClause cltxt = new GmxLayerDefinitionClause();
            NodeList head = nodeList.item(i).getChildNodes();
            for (int j = 0; j < head.getLength(); j++) {
                cltxt.Clause = head.item(i).getTextContent();
                //String clausetxt = head.item(i).getTextContent();
                //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  " + vv);
                //layerDef.Clause = clausetxt;
            }
            layerDef.Clauses.add(cltxt);
        }
    }

    /**
     * Layer의 Extent의 ExtentItem을 설정.
     * 
     * @param layerDef
     * @param nodeList
     */
    private void setLayerExtentExtentItemNode(GmxLayerDefinition layerDef, NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            GmxLayerDefinitionExtentItem extentItem = new GmxLayerDefinitionExtentItem();
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element firstElement = (Element) node;

                //<Layer><Extent><ExtentItem> 속성 설정, 대소문자 구별없이 하기위한 절차.
                for (int j = 0; j < firstElement.getAttributes().getLength(); j++)
                {
                    if ("visible".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        extentItem.ExtentItem_visible = firstElement.getAttributes().item(j).getNodeValue();
                    if ("type".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        extentItem.ExtentItem_type = firstElement.getAttributes().item(j).getNodeValue();
                    if ("name".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        extentItem.ExtentItem_name = firstElement.getAttributes().item(j).getNodeValue();
                    if ("alias".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        extentItem.ExtentItem_alias = firstElement.getAttributes().item(j).getNodeValue();
                }
                //System.out.println("extentItem.ExtentItem_name : " + extentItem.ExtentItem_name);
                layerDef.ExtentItems.add(extentItem);
            }
        }
    }

    /**
     * Layer의 Label를 설정.
     * 
     * @param layerDef
     * @param nodeList
     */
    private void setLayerLabelNode(GmxLayerDefinition layerDef, NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element firstElement = (Element) node;
                NodeList childNodeList = firstElement.getElementsByTagName("LabelItem");

                //<Layer><Label> 속성 설정, 대소문자 구별없이 하기위한 절차.
                for (int j = 0; j < firstElement.getAttributes().getLength(); j++)
                {
                    if ("visible".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Label_visible = firstElement.getAttributes().item(j).getNodeValue();
                    if ("angled".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Label_angled = firstElement.getAttributes().item(j).getNodeValue();
                    if ("priority".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Label_priority = firstElement.getAttributes().item(j).getNodeValue();
                }
                //System.out.println("layerDef.Extent_pkey : " + layerDef.Extent_pkey);
                setLayerLabelLabelItemNode(layerDef, childNodeList);
            }
        }
    }

    /**
     * Layer의 Label의 LabelItem을 설정.
     * 
     * @param layerDef
     * @param nodeList
     */
    private void setLayerLabelLabelItemNode(GmxLayerDefinition layerDef, NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            GmxLayerDefinitionLabelItem labelItem = new GmxLayerDefinitionLabelItem();
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element firstElement = (Element) node;

                //<Layer><Label><LabelItem> 속성 설정, 대소문자 구별없이 하기위한 절차.
                for (int j = 0; j < firstElement.getAttributes().getLength(); j++)
                {
                    if ("column".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        labelItem.LabelItem_column = firstElement.getAttributes().item(j).getNodeValue();
                    if ("maxscale".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        labelItem.LabelItem_maxScale = firstElement.getAttributes().item(j).getNodeValue();
                    if ("minscale".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        labelItem.LabelItem_minScale = firstElement.getAttributes().item(j).getNodeValue();
                }
                //System.out.println("labelItem.LabelItem_column : " + labelItem.LabelItem_column);
                layerDef.LabelItems.add(labelItem);
            }
        }
    }

    /**
     * Layer의 Style을 설정.
     * 
     * @param layerDef
     * @param nodeList
     */
    private void setLayerStyleNode(GmxLayerDefinition layerDef, NodeList nodeList, NodeList nodeStyleList) {
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element firstElement = (Element) node;
                NodeList childNodeList = firstElement.getElementsByTagName("StyleItem");

                //<Layer><Label> 속성 설정, 대소문자 구별없이 하기위한 절차.
                for (int j = 0; j < firstElement.getAttributes().getLength(); j++)
                {
                    if ("default".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Style_default = firstElement.getAttributes().item(j).getNodeValue();
                    if ("range".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Style_range = firstElement.getAttributes().item(j).getNodeValue();
                    if ("searched".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Style_searched = firstElement.getAttributes().item(j).getNodeValue();
                    if ("selected".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Style_selected = firstElement.getAttributes().item(j).getNodeValue();
                    if ("theme".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Style_theme = firstElement.getAttributes().item(j).getNodeValue();
                    if ("themecolumn".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Style_themeColumn = firstElement.getAttributes().item(j).getNodeValue();
                }

                //System.out.println("layerDef.Style_default : " + layerDef.Style_default);
                setLayerStyleDef(layerDef, nodeStyleList);
                setLayerStyleStyleItemNode(layerDef, childNodeList, nodeStyleList);
            }
        }
    }

    /**
     * <Layer><Style>의 스타일정보를 설정한다.
     * 
     * @param layerDef
     * @param nodeStyleList
     */
    private void setLayerStyleDef(GmxLayerDefinition layerDef, NodeList nodeStyleList) {
        for (int i = 0; i < nodeStyleList.getLength(); i++)
        {
            Node nodeStyle = nodeStyleList.item(i);

            if (nodeStyle.getNodeType() == Node.ELEMENT_NODE)
            {
                Element firstElement = (Element) nodeStyle;
                Element styleTextElement = (Element) firstElement.getElementsByTagName("Text").item(0);
                Element styleSymbolElement = (Element) firstElement.getElementsByTagName("Symbol").item(0);
                Element styleLineElement = (Element) firstElement.getElementsByTagName("Line").item(0);
                Element styleFillElement = (Element) firstElement.getElementsByTagName("Fill").item(0);
                //레이어에 정의된 스타일과 스타일에정의되어이있는 이름 매칭
                if (layerDef.Style_default.equals(firstElement.getAttribute("name"))) {
                    layerDef.StyleDef.Style_name = firstElement.getAttribute("name");

                    if (styleTextElement != null) {
                        layerDef.StyleDef.Text_align = styleTextElement.getAttribute("align");
                        layerDef.StyleDef.Text_color = styleTextElement.getAttribute("color");
                        layerDef.StyleDef.Text_font = styleTextElement.getAttribute("font");
                        layerDef.StyleDef.Text_haloColor = styleTextElement.getAttribute("haloColor");
                        layerDef.StyleDef.Text_haloSize = styleTextElement.getAttribute("haloSize");
                        layerDef.StyleDef.Text_maxSize = styleTextElement.getAttribute("maxSize");
                        layerDef.StyleDef.Text_minSize = styleTextElement.getAttribute("minSize");
                        layerDef.StyleDef.Text_style = styleTextElement.getAttribute("style");
                        layerDef.StyleDef.Text_xoffset = styleTextElement.getAttribute("xoffset");
                        layerDef.StyleDef.Text_yoffset = styleTextElement.getAttribute("yoffset");

                    }
                    if (styleSymbolElement != null) {
                        layerDef.StyleDef.Symbol_color = styleSymbolElement.getAttribute("color");
                        layerDef.StyleDef.Symbol_maxSize = styleSymbolElement.getAttribute("maxSize");
                        layerDef.StyleDef.Symbol_minSize = styleSymbolElement.getAttribute("minSize");
                        layerDef.StyleDef.Symbol_name = styleSymbolElement.getAttribute("name");
                        layerDef.StyleDef.Symbol_opacity = styleSymbolElement.getAttribute("opacity");
                        layerDef.StyleDef.Symbol_xoffset = styleSymbolElement.getAttribute("xoffset");
                        layerDef.StyleDef.Symbol_yoffset = styleSymbolElement.getAttribute("yoffset");
                    }
                    if (styleLineElement != null) {
                        layerDef.StyleDef.Line_arrow = styleLineElement.getAttribute("arrow");
                        layerDef.StyleDef.Line_color = styleLineElement.getAttribute("color");
                        layerDef.StyleDef.Line_type = styleLineElement.getAttribute("type");
                        layerDef.StyleDef.Line_width = styleLineElement.getAttribute("width");
                    }

                    if (styleFillElement != null) {
                        layerDef.StyleDef.Fill_color = styleFillElement.getAttribute("color");
                        layerDef.StyleDef.Fill_opacity = styleFillElement.getAttribute("opacity");
                        layerDef.StyleDef.Fill_pattern = styleFillElement.getAttribute("pattern");
                    }

                }
            }
        }
    }

    /**
     * Layer의 Style의 StyleItem을 설정.
     * 
     * @param layerDef
     * @param nodeList
     */
    private void setLayerStyleStyleItemNode(GmxLayerDefinition layerDef, NodeList nodeList, NodeList nodeStyleList) {
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            GmxLayerDefinitionStyleItem styleItem = new GmxLayerDefinitionStyleItem();
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element firstElement = (Element) node;

                //<Layer><Label><LabelItem> 속성 설정, 대소문자 구별없이 하기위한 절차.
                for (int j = 0; j < firstElement.getAttributes().getLength(); j++)
                {
                    if ("name".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        styleItem.StyleItem_name = firstElement.getAttributes().item(j).getNodeValue();
                    if ("seq".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        styleItem.StyleItem_seq = firstElement.getAttributes().item(j).getNodeValue();
                    if ("style".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        styleItem.StyleItem_style = firstElement.getAttributes().item(j).getNodeValue();
                    if ("value".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        styleItem.StyleItem_value = firstElement.getAttributes().item(j).getNodeValue();
                    if ("visible".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        styleItem.StyleItem_visible = firstElement.getAttributes().item(j).getNodeValue();
                }
                //System.out.println("styleItem.StyleItem_name : " + styleItem.StyleItem_name);
                setLayerStyleDef(styleItem, nodeStyleList);
                layerDef.StyleItems.add(styleItem);
            }
        }
    }

    /**
     * <Layer><Style><styleItem>의 스타일정보를 설정한다.
     * 
     * @param layerDef
     * @param nodeStyleList
     */
    private void setLayerStyleDef(GmxLayerDefinitionStyleItem styleItem, NodeList nodeStyleList) {
        for (int i = 0; i < nodeStyleList.getLength(); i++)
        {
            Node nodeStyle = nodeStyleList.item(i);

            if (nodeStyle.getNodeType() == Node.ELEMENT_NODE)
            {
                Element firstElement = (Element) nodeStyle;
                Element styleTextElement = (Element) firstElement.getElementsByTagName("Text").item(0);
                Element styleSymbolElement = (Element) firstElement.getElementsByTagName("Symbol").item(0);
                Element styleLineElement = (Element) firstElement.getElementsByTagName("Line").item(0);
                Element styleFillElement = (Element) firstElement.getElementsByTagName("Fill").item(0);
                //레이어에 정의된 스타일과 스타일에정의되어이있는 이름 매칭

                if (styleItem.StyleItem_style.equals(firstElement.getAttribute("name"))) {

                    styleItem.StyleDef.Style_name = firstElement.getAttribute("name");
                    if (styleTextElement != null) {
                        styleItem.StyleDef.Text_align = styleTextElement.getAttribute("align");
                        styleItem.StyleDef.Text_color = styleTextElement.getAttribute("color");
                        styleItem.StyleDef.Text_font = styleTextElement.getAttribute("font");
                        styleItem.StyleDef.Text_haloColor = styleTextElement.getAttribute("haloColor");
                        styleItem.StyleDef.Text_haloSize = styleTextElement.getAttribute("haloSize");
                        styleItem.StyleDef.Text_maxSize = styleTextElement.getAttribute("maxSize");
                        styleItem.StyleDef.Text_minSize = styleTextElement.getAttribute("minSize");
                        styleItem.StyleDef.Text_style = styleTextElement.getAttribute("style");
                        styleItem.StyleDef.Text_xoffset = styleTextElement.getAttribute("xoffset");
                        styleItem.StyleDef.Text_yoffset = styleTextElement.getAttribute("yoffset");
                    }
                    if (styleSymbolElement != null) {
                        styleItem.StyleDef.Symbol_color = styleSymbolElement.getAttribute("color");
                        styleItem.StyleDef.Symbol_maxSize = styleSymbolElement.getAttribute("maxSize");
                        styleItem.StyleDef.Symbol_minSize = styleSymbolElement.getAttribute("minSize");
                        styleItem.StyleDef.Symbol_name = styleSymbolElement.getAttribute("name");
                        styleItem.StyleDef.Symbol_opacity = styleSymbolElement.getAttribute("opacity");
                        styleItem.StyleDef.Symbol_xoffset = styleSymbolElement.getAttribute("xoffset");
                        styleItem.StyleDef.Symbol_yoffset = styleSymbolElement.getAttribute("yoffset");
                    }
                    if (styleLineElement != null) {
                        styleItem.StyleDef.Line_arrow = styleLineElement.getAttribute("arrow");
                        styleItem.StyleDef.Line_color = styleLineElement.getAttribute("color");
                        styleItem.StyleDef.Line_type = styleLineElement.getAttribute("type");
                        styleItem.StyleDef.Line_width = styleLineElement.getAttribute("width");
                    }
                    if (styleFillElement != null) {
                        styleItem.StyleDef.Fill_color = styleFillElement.getAttribute("color");
                        styleItem.StyleDef.Fill_opacity = styleFillElement.getAttribute("opacity");
                        styleItem.StyleDef.Fill_pattern = styleFillElement.getAttribute("pattern");
                    }

                }
            }
        }
    }

    /**
     * Layer의 Symbol을 설정.
     * 
     * @param layerDef
     * @param nodeList
     */
    private void setLayerSymbolNode(GmxLayerDefinition layerDef, NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element firstElement = (Element) node;

                //<Layer><Symbol> 속성 설정, 대소문자 구별없이 하기위한 절차.
                for (int j = 0; j < firstElement.getAttributes().getLength(); j++)
                {
                    if ("angled".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Symbol_angled = firstElement.getAttributes().item(j).getNodeValue();
                    if ("maxscale".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Symbol_maxScale = firstElement.getAttributes().item(j).getNodeValue();
                    if ("minscale".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Symbol_minScale = firstElement.getAttributes().item(j).getNodeValue();
                    if ("visible".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Symbol_visible = firstElement.getAttributes().item(j).getNodeValue();
                }
                //System.out.println("layerDef.Symbol_visible : " + layerDef.Symbol_visible);

            }
        }
    }

    /**
     * Layer의 Menu을 설정.
     * 
     * @param layerDef
     * @param nodeList
     */
    private void setLayerMenuNode(GmxLayerDefinition layerDef, NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element firstElement = (Element) node;
                NodeList childNodeList = firstElement.getElementsByTagName("MenuItem");

                //<Layer><Menu> 속성 설정, 대소문자 구별없이 하기위한 절차.
                for (int j = 0; j < firstElement.getAttributes().getLength(); j++)
                {
                    if ("maxscale".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Menu_maxScale = firstElement.getAttributes().item(j).getNodeValue();
                    if ("minscale".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        layerDef.Menu_minScale = firstElement.getAttributes().item(j).getNodeValue();
                }

                setLayerMenuMenuItemNode(layerDef, childNodeList);
            }
        }
    }

    /**
     * Layer의 Menu의 MenuItem을 설정.
     * 
     * @param layerDef
     * @param nodeList
     */
    private void setLayerMenuMenuItemNode(GmxLayerDefinition layerDef, NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            GmxLayerDefinitionMenuItem menuItem = new GmxLayerDefinitionMenuItem();
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element firstElement = (Element) node;

                //<Layer><Menu><MenuItem> 속성 설정, 대소문자 구별없이 하기위한 절차.
                for (int j = 0; j < firstElement.getAttributes().getLength(); j++)
                {
                    if ("cmd".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        menuItem.MenuItem_cmd = firstElement.getAttributes().item(j).getNodeValue();
                    if ("name".equals(firstElement.getAttributes().item(j).getNodeName().toLowerCase()))
                        menuItem.MenuItem_name = firstElement.getAttributes().item(j).getNodeValue();
                }
                //System.out.println("labelItem.LabelItem_column : " + labelItem.LabelItem_column);
                layerDef.MenuItems.add(menuItem);
            }
        }
    }

    //그룹아이템 정렬
    private void sortGroupItem(ArrayList<GmxLayerDefinitionLayerGroupItem> list)
    {
        Collections.sort(list, new Comparator<GmxLayerDefinitionLayerGroupItem>() {

            @Override
            public int compare(GmxLayerDefinitionLayerGroupItem o1, GmxLayerDefinitionLayerGroupItem o2) {
                GmxLayerDefinitionLayerGroupItem t1 = o1;
                GmxLayerDefinitionLayerGroupItem t2 = o2;
                return Utils.stringNumberToZeroStringNumber(t1.GroupItem_index, 11).compareTo(
                    Utils.stringNumberToZeroStringNumber(t2.GroupItem_index, 11));
            }
        });
    }

    //레이어 정렬
    private void sortLayerDef(ArrayList<GmxLayerDefinition> list)
    {
        Collections.sort(list, new Comparator<GmxLayerDefinition>() {

            @Override
            public int compare(GmxLayerDefinition o1, GmxLayerDefinition o2) {
                GmxLayerDefinition t1 = o1;
                GmxLayerDefinition t2 = o2;
                return Utils.stringNumberToZeroStringNumber(t2.Layer_zIndex, 11).compareTo(
                    Utils.stringNumberToZeroStringNumber(t1.Layer_zIndex, 11));
            }
        });
    }

}
