package geomex.pkg.openapi;

import java.util.ArrayList;

public class GmxLayerDefinition {
    public static final String LAYER_FULLLOADING_NO = "false";
    public static final String LAYER_FULLLOADING_YES = "true";
    public static final String LAYER_EDITABLE_NO = "false";
    public static final String LAYER_EDITABLE_YES = "true";
    public static final String LAYER_SELECTABLE_NO = "false";
    public static final String LAYER_SELECTABLE_YES = "true";
    public static final String LAYER_VISIBLE_NO = "false";
    public static final String LAYER_VISIBLE_YES = "true";
    public static final String LYAER_TYPE_MULTIPOLYGON = "6";
    public static final String LYAER_TYPE_MULTILINESTRING = "5";
    public static final String LYAER_TYPE_POINT = "1";
    public static final String LABEL_VISIBLE_NO = "false";
    public static final String LABEL_VISIBLE_YES = "true";
    public static final String LABEL_ANGLED_NO = "false";
    public static final String LABEL_ANGLED_YES = "true";
    public static final String STYLE_THEME_NO = "false";
    public static final String STYLE_THEME_YES = "true";
    public static final String STYLE_RANGE_NO = "false";
    public static final String STYLE_RANGE_YES = "true";
    public static final String SYMBOL_VISIBLE_NO = "false";
    public static final String SYMBOL_VISIBLE_YES = "true";
    public static final String SYMBOL_ANGLED_NO = "false";
    public static final String SYMBOL_ANGLED_YES = "true";
    public static final String MENU_VISIBLE_NO = "false";
    public static final String MENU_VISIBLE_YES = "true";

    public String Layer_dataset = "";
    public String Layer_layername = "";
    public String Layer_groupname = "";
    public String Layer_zIndex = "";
    public String Layer_type = "";
    public String Layer_connector = "";
    public String Layer_nodeName = "";
    public String Layer_fullLoading = "";
    public String Layer_editable = "";
    public String Layer_selectable = "";
    public String Layer_visible = "";
    public String Layer_minScale = "";
    public String Layer_maxScale = "";

    public String Extent_pkey = "";
    public String Extent_heightColumn = "";
    public String Extent_angleColumn = "";
    public ArrayList<GmxLayerDefinitionExtentItem> ExtentItems = new ArrayList<GmxLayerDefinitionExtentItem>();

    public String Clause = "";
    public ArrayList<GmxLayerDefinitionClause> Clauses = new ArrayList<GmxLayerDefinitionClause>();

    public String Label_visible = "";
    public String Label_angled = "";
    public String Label_priority = "";
    public ArrayList<GmxLayerDefinitionLabelItem> LabelItems = new ArrayList<GmxLayerDefinitionLabelItem>();

    public String Style_default = "";
    public String Style_searched = "";
    public String Style_selected = "";
    public String Style_themeColumn = "";
    public String Style_theme = "";
    public String Style_range = "";
    public GmxStyleDefinition StyleDef = new GmxStyleDefinition();
    public ArrayList<GmxLayerDefinitionStyleItem> StyleItems = new ArrayList<GmxLayerDefinitionStyleItem>();

    public String Symbol_visible = "";
    public String Symbol_angled = "";
    public String Symbol_minScale = "";
    public String Symbol_maxScale = "";

    public String Menu_visible = "";
    public String Menu_minScale = "";
    public String Menu_maxScale = "";
    public ArrayList<GmxLayerDefinitionMenuItem> MenuItems = new ArrayList<GmxLayerDefinitionMenuItem>();

    public GmxKmlDefinition kml = new GmxKmlDefinition();

}
