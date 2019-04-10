package geomex.pkg.layer;

import geovlet.geom.TypeDef;
import geovlet.io.Connector;
import geovlet.io.SelectDB;
import geovlet.io.XColumn;
import geovlet.io.geotrans.Base;
import geovlet.io.geotrans.Ellipsoid;
import geovlet.io.geotrans.GeoModel;
import geovlet.view.cfg.MapCfg;
import geovlet.utils.Debug;
import geovlet.utils.IOUtil;
import geovlet.utils.StrUtil;
import geovlet.utils.XMLUtil;
import geovlet.view.LayerInfo;
import geovlet.view.MenuItem;
import geovlet.view.OpenMapLayerInfo;
import geovlet.view.style.FillStyle;
import geovlet.view.style.LabelItem;
import geovlet.view.style.LineStyle;
import geovlet.view.style.StrokeManager;
import geovlet.view.style.Style;
import geovlet.view.style.SymbolStyle;
import geovlet.view.style.TextStyle;
import geovlet.view.style.ThemeStyle;

import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class StyleDBHelper {

    public StyleDBHelper() {

    }

    public MapCfg getUserStyle(Connection con, String user, String nm)
        throws Exception {
        String sql = "select _contents from _gmx_style where _owner=? and _title=?";
        MapCfg cfg = null;
        SelectDB select = null;
        try {
            select = new SelectDB(con, sql);
            select.setString(1, user);
            select.setString(2, nm);
            select.execute();
            select.next();
            String xml = select.getString(1);
            //
            cfg = new MapCfg();
            cfg.loadCfg(xml);
        } catch (Exception e) {
            throw new Exception(IOUtil.getStackTrace(e));
        } finally {
            if (select != null) {
                select.cleanUp();
            }
        }
        return cfg;
    }

    public void ensuerLayerInfo(Connection con, String grp, MapCfg cfg, String lyrName)
        throws Exception {
        String lyrid = getLayerID(con, lyrName);
        //System.out.println("lyrid >>>>> " + lyrid);
        if (lyrid == null) {
            throw new Exception("Can not found layerID " + lyrName);
        }
        LayerInfo info = makeLayerInfo(con, cfg, lyrid); // LayerInfo를
        if (info == null) {
            throw new Exception("LayerInfo is null...." + lyrName);
        }
        info.setGroupName(grp);
        setLayerThemeStyle(con, cfg, lyrid, info);// 주제 스타일 설정
        setLayerColumnInfo(con, cfg, lyrid, info); // 레이어칼럼정보 설정
        setLayerMenu(info); // 레이어 메뉴 설정
        //
        cfg.addLayerInfo(info); // MapCfg에 LayerInfo를 등록한다.
    }

    private String getLayerID(Connection con, String lyrName) throws Exception {

        String sql = "select lyr_id from mt_lyr_desc where lyr_nm=?";

        String lyrid = null;
        SelectDB select = null;

        // System.out.println(sql);
        // System.out.println(lyrName);

        try {
            select = new SelectDB(con, sql);
            select.setString(1, lyrName);
            select.execute();
            while (select.next()) {
                lyrid = select.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(IOUtil.getStackTrace(e));
        } finally {
            if (select != null) {
                select.cleanUp();
            }
        }
        return lyrid;
    }

    private void setLayerMenu(LayerInfo info) {
        if (info.getDataset().equalsIgnoreCase("lp_pa_cbnd")) {
            MenuItem items[] = new MenuItem[] {
                    //new MenuItem("기본정보", "ShowBasicInfo"),
                    new MenuItem("토지대장", "ShowLRGInfo"),
                    new MenuItem("건축물대장", "ShowEAISInfo"),
                    new MenuItem("행위제한법령정보", "ShowRestrictLawInfo"),
                    new MenuItem("지역지구별행위제한가능여부", "ShowRestrictByZone"),
                    new MenuItem("조건별행위제한가능여부", "ShowRestrictByCond"),
            };
            info.setMenuVisible(true);
            info.setMenuMinScale(info.getLayerMinScale());
            info.setMenuMaxScale(info.getLayerMaxScale());
            info.setMenuItems(items);

        } else {
            /*
             * MenuItem items[] = new MenuItem[] { new MenuItem("상세정보",
             * "ShowDetailInfo") }; info.setMenuVisible(true);
             * info.setMenuMinScale(info.getLayerMinScale());
             * info.setMenuMaxScale(info.getLayerMaxScale());
             * info.setMenuItems(items);
             */
        }
    }

    private void setLayerThemeStyle(Connection con, MapCfg cfg, String lyrid, LayerInfo info)
        throws Exception {
        String sql = " select lyr_id || '_' ||thm.theme_seq as sname, lyr.lyr_typ," +
            " lyr.theme_typ,thm.theme_nm,thm.theme_seq,theme_col,theme_val, " +
            " thm.sym_img_nm, thm.sym_size, thm.sym_rgba, " +
            " thm.lbl_size,thm.lbl_rgba,thm.lbl_halo_rgba,thm.lbl_halo_size, " +
            " thm.line_typ,thm.line_rgba,thm.line_width, " +
            " thm.fill_rgba,thm.fill_img_nm " +
            " from mt_lyr_desc lyr, mt_lyr_thm thm " +
            " where " +
            " lyr.theme_id = thm.theme_id " +
            " and lyr.theme_yn='Y'  " +
            " and lyr.lyr_id = ? " +
            " order by thm.theme_seq  ";

        SelectDB select = null;
        try {
            select = new SelectDB(con, sql);
            select.setString(1, lyrid);
            select.execute();
            while (select.next()) {
                //
                String style_nm = select.getString(1);
                String lyr_typ = select.getString(2);
                String theme_typ = select.getString(3);
                String theme_nm = select.getString(4);
                String theme_seq = select.getString(5);
                String theme_col = select.getString(6);
                String theme_val = select.getString(7);
                //

                // 여기부터 스타일..
                String sym_img_nm = select.getString(8);
                String sym_size = select.getString(9);
                String sym_rgba = select.getString(10);
                //
                String lbl_size = select.getString(11);
                String lbl_rgba = select.getString(12);
                String lbl_halo_rgba = select.getString(13);
                String lbl_halo_size = select.getString(14);
                //
                String line_typ = select.getString(15);
                String line_rgba = select.getString(16);
                String line_width = select.getString(17);
                //
                String fill_rgba = select.getString(18);
                String fill_img_nm = select.getString(19);
                //
                Style style = new Style(style_nm);
                style.setSymbolStyle(parseSymbolStyle(sym_img_nm, sym_size, sym_rgba));

                style.setTextStyle(info.getDefaultStyle().getTextStyle().clone());
                //style.setTextStyle(parseTextStyle(lbl_size, lbl_rgba, lbl_halo_rgba, lbl_halo_size));

                if (lyr_typ.equalsIgnoreCase("ML") || lyr_typ.equalsIgnoreCase("SL")) {
                    style.setLineStyle(parseLineStyle(line_typ, line_rgba, line_width));
                }
                if (lyr_typ.equalsIgnoreCase("MA") || lyr_typ.equalsIgnoreCase("SA")) {
                    style.setLineStyle(parseLineStyle(line_typ, line_rgba, line_width));
                    style.setFillStyle(parseFillStyle(fill_rgba, fill_img_nm));
                }
                cfg.addStyle(style);
                int seq = XMLUtil.getInt(theme_seq);
                ThemeStyle thmStyle = new ThemeStyle(theme_nm, theme_val, style, true, seq);
                info.setThemeColumn(theme_col);
                info.setThemeStyle(true);
                info.addThemeStyle(theme_val, thmStyle);
                info.setRangeTheme(isRange(theme_typ));
            } // while
              //
        } catch (Exception e) {
            throw new Exception(IOUtil.getStackTrace(e));
        } finally {
            if (select != null) {
                select.cleanUp();
            }
        }
    }

    private void setLayerColumnInfo(Connection con, MapCfg cfg, String lyrid, LayerInfo info)
        throws Exception {
        String sql = "select col_nm, han_nm, col_typ, tip_yn, key_yn " +
            " from mt_tbl_cols col, mt_lyr_desc lyr " +
            " where col.tbl_id = lyr.tbl_id and (tip_yn =\'Y\' or key_yn=\'Y\') " +
            " and lyr.lyr_id = ? ";
        
        SelectDB select = null;
        try {
            select = new SelectDB(con, sql);
            select.setString(1, lyrid);
            select.execute();
            //
            ArrayList<XColumn> columns = new ArrayList<XColumn>();
            columns.add(new XColumn("_gid", TypeDef.LONG_TYPE, "", "_gid", false));
            columns.add(new XColumn("_annox", TypeDef.DOUBLE_TYPE, "", "_annox", false));
            columns.add(new XColumn("_annoy", TypeDef.DOUBLE_TYPE, "", "_annoy", false));
            columns.add(new XColumn("_geometry", TypeDef.GMX_TYPE, "", "_geom", false));
            //
            String keyColumn = "_gid";
            boolean selectable = false;
            while (select.next()) {
                String col_nm = select.getString(1);
                String han_nm = select.getString(2);
                String col_typ = select.getString(3);
                String tip_yn = select.getString(4);
                String key_yn = select.getString(5);
                //
                if (!col_nm.equalsIgnoreCase("_gid") && !col_nm.equalsIgnoreCase("_annox") &&
                    !col_nm.equalsIgnoreCase("_annoy") && !col_nm.equalsIgnoreCase("_geometry")) {
                    XColumn col = new XColumn(col_nm, getColType(col_typ), "", han_nm, true);
                    columns.add(col);
                }
                if (key_yn.equalsIgnoreCase("Y")) {
                    keyColumn = col_nm; // 반드시 한개의 key가 있어야 한다.
                }
                //
                if (tip_yn.equalsIgnoreCase("Y")) {
                    selectable = true; // 한개라도 간략정보항목이 있으며 true이다.
                }
            }// while
            info.setExtentColumns(columns.toArray(new XColumn[columns.size()]));
            info.setLayerSelectable(selectable);
            info.setKeyColumn(keyColumn);
        } catch (Exception e) {
            throw new Exception(IOUtil.getStackTrace(e));
        } finally {
            if (select != null) {
                select.cleanUp();
            }
        }
    }

    /**
     * 레이어 기본정보를 생성한다.
     * 
     * @param con
     * @param cfg
     * @param lyrid
     * @return LayerInfo
     * @throws Exception
     */
    private LayerInfo makeLayerInfo(Connection con, MapCfg cfg, String lyrid)
        throws Exception {
        String sql = " select tbl_id, lyr_nm, grp_nm, lyr_typ, where_txt, " +
            " lyr_show_yn,lyr_scale," +
            " sym_show_yn,sym_scale," +
            " lbl_show_yn,lbl_cols,lbl_scale," +
            " sym_img_nm, sym_size,sym_rgba, " +
            " lbl_size,lbl_rgba,lbl_halo_rgba,lbl_halo_size, " +
            " line_typ,line_rgba,line_width, " +
            " fill_rgba,fill_img_nm,lyr.zidx" +
            " from mt_lyr_desc lyr, mt_lyr_grp grp" +
            " where grp.grp_id = lyr.grp_id" +
            " and lyr.lyr_id = ? ";

        ArrayList<LayerInfo> infos = cfg.getIndexInfos();
        LayerInfo idxInfo = infos.get(0);
        Connector gmx = idxInfo.getConnector();
        String node = idxInfo.getNodeName();

        SelectDB select = null;
        LayerInfo info = null;
        try {
            // System.out.println(sql + " / " + lyrid);
            select = new SelectDB(con, sql);
            select.setString(1, lyrid);
            select.execute();

            select.next();
            // while(select.next()){
            //
            String tbl_id = select.getString(1);
            String lyr_nm = select.getString(2);
            String grp_nm = select.getString(3);
            String lyr_typ = select.getString(4);
            String where_txt = select.getString(5);
            //
            int lyrType = getLayerType(lyr_typ);
            if (lyrType == LayerInfo.LAYER_OPENMAP) {
                info = new OpenMapLayerInfo();
            } else {
                info = new LayerInfo();
            }
            //
            info.setDataset(tbl_id);
            info.setLayerName(lyr_nm);
            info.setGroupName(grp_nm);
            info.setLayerType(lyrType);
            info.setNodeName(node);
            info.setCrsName("5186");
            info.setConnector(gmx.createNew());

            //
            if (lyrType == LayerInfo.LAYER_OPENMAP) {
                OpenMapLayerInfo opInfo = (OpenMapLayerInfo) info;
                opInfo.setAppBase(new Base("TM127", GeoModel.TM, 600000.0, 200000.0, 38.0, 127.0, 1.0));
                opInfo.setAppEllipsoid(new Ellipsoid("wgs84", 6378137.0, 298.257223563));
                //opInfo.setAppBase(new Base("TM129", GeoModel.TM, 500000.0, 200000.0, 38.0, 129.00289, 1.0));
                //opInfo.setAppEllipsoid(new Ellipsoid("bessel", 6377397.155, 0.00334277317994));
                //
                if (!StrUtil.isEmpty(where_txt)) {
                    parsetTileBaseParam(where_txt, opInfo);
                }
                //
            } else {
                if (!StrUtil.isEmpty(where_txt)) {
                    info.setExtentClause(where_txt);
                }
            }
            //
            String lyr_show_yn = select.getString(6);
            String lyr_scale = select.getString(7);
            info.setLayerVisible(isYes(lyr_show_yn));
            info.setFullLoading(false);
            info.setLayerEditable(false);
            //
            int minScale = XMLUtil.getInt(StrUtil.left(lyr_scale, ","));
            int maxScale = XMLUtil.getInt(StrUtil.right(lyr_scale, ","));
            info.setLayerMaxScale(maxScale);
            info.setLayerMinScale(minScale);
            // ////

            String sym_show_yn = select.getString(8);
            String sym_scale = select.getString(9);
            info.setSymbolVisible(isYes(sym_show_yn));
            int symMin = XMLUtil.getInt(StrUtil.left(sym_scale, ","));
            int symMax = XMLUtil.getInt(StrUtil.right(sym_scale, ","));
            info.setSymbolMinScale((symMin <= 0) ? minScale : symMin);
            info.setSymbolMaxScale((symMax <= 0) ? maxScale : symMax);
            //
            String lbl_show_yn = select.getString(10);
            String lbl_cols = select.getString(11);
            String lbl_scale = select.getString(12);
            //
            info.setLabelVisible(isYes(lbl_show_yn));
            info.setLabelItems(getLabelItems(lbl_cols, lbl_scale));
            // info.setL
            info.setCachable(true);

            // 여기부터 스타일..
            String sym_img_nm = select.getString(13);
            String sym_size = select.getString(14);
            String sym_rgba = select.getString(15);
            //
            String lbl_size = select.getString(16);
            String lbl_rgba = select.getString(17);
            String lbl_halo_rgba = select.getString(18);
            String lbl_halo_size = select.getString(19);
            //
            String line_typ = select.getString(20);
            String line_rgba = select.getString(21);
            String line_width = select.getString(22);
            //
            String fill_rgba = select.getString(23);
            String fill_img_nm = select.getString(24);
            //
            Style style = new Style(lyrid);
            style.setSymbolStyle(parseSymbolStyle(sym_img_nm, sym_size, sym_rgba));
            style.setTextStyle(parseTextStyle(lbl_size, lbl_rgba, lbl_halo_rgba, lbl_halo_size));

            if (lyr_typ.equalsIgnoreCase("ML") || lyr_typ.equalsIgnoreCase("SL")) {
                style.setLineStyle(parseLineStyle(line_typ, line_rgba, line_width));
            }
            if (lyr_typ.equalsIgnoreCase("MA") || lyr_typ.equalsIgnoreCase("SA")) {
                style.setLineStyle(parseLineStyle(line_typ, line_rgba, line_width));
                style.setFillStyle(parseFillStyle(fill_rgba, fill_img_nm));
            }
            cfg.addStyle(style);
            info.setDefaultStyle(style);
            // }
            int zIndex = select.getInt(25);
            info.setZIndex(zIndex);
            //
        } catch (Exception e) {
            throw new Exception(IOUtil.getStackTrace(e));
        } finally {
            if (select != null) {
                select.cleanUp();
            }
        }
        return info;
    }

    /**
     * 심볼스타일을 생성한다.
     * 
     * @param img
     * @param size
     * @param rgba
     * @return SymbolStyle
     */
    private SymbolStyle parseSymbolStyle(String img, String size, String rgba) {
        int minSize = XMLUtil.getInt(StrUtil.left(size, ","));
        int maxSize = XMLUtil.getInt(StrUtil.right(size, ","));
        if (minSize <= 0 && maxSize <= 0) {
            return null;
        }

        SymbolStyle s = new SymbolStyle();
        if (!StrUtil.isEmpty(img)) {
            s.setName(img);
        }
        //
        Color c = XMLUtil.getColor(rgba);
        s.setColor(c);
        s.setSize(minSize, maxSize);
        //
        return s;
    }

    /**
     * 문자스타일을 생성한다.
     * 
     * @param size
     * @param rgba
     * @param halo_rgba
     * @param halo_size
     * @return TextStyle
     */
    private TextStyle parseTextStyle(String size, String rgba, String halo_rgba, String halo_size) {
        int minSize = XMLUtil.getInt(StrUtil.left(size, ","));
        int maxSize = XMLUtil.getInt(StrUtil.right(size, ","));
        if (minSize <= 0 && maxSize <= 0) {
            return null;
        }
        //
        Color fntColor = XMLUtil.getColor(rgba);
        Color haloColor = XMLUtil.getColor(halo_rgba);
        int haloSize = XMLUtil.getInt(halo_size);

        TextStyle s = new TextStyle("Dialog", Font.PLAIN, minSize, maxSize, fntColor, haloColor, haloSize);
        return s;
    }

    /**
     * 라인스타일을 생성한다.
     * 
     * @param typ
     * @param rgba
     * @param w
     * @return LineStyle
     */
    private LineStyle parseLineStyle(String typ, String rgba, String w) {
        if (StrUtil.isEmpty(typ)) {
            return null;
        }
        int type = XMLUtil.getInt(typ);

        Color c = XMLUtil.getColor(rgba);
        if (c == null) {
            c = new Color(204, 204, 204, 0);
        }
        float width = XMLUtil.getFloat(w);
        if (width <= 1.0f) width = 0.25f;
        //
        int lineType = StrokeManager.ONE_LINE_SOLID;
        switch (type) {
            case 100:
                lineType = StrokeManager.ONE_LINE_SOLID;
                break;
            case 101:
                lineType = StrokeManager.ONE_LINE_DOT;
                break;
            case 102:
                lineType = StrokeManager.ONE_LINE_DASH;
                break;
            case 103:
                lineType = StrokeManager.ONE_LINE_DASH_DOT;
                break;
            case 104:
                lineType = StrokeManager.ONE_LINE_DASH_2DOT;
                break;
        }

        boolean isArrow = false;
        if (type >= 500) {
            isArrow = true;
        }
        LineStyle s = new LineStyle(lineType, width, c, isArrow);
        return s;
    }

    /**
     * 채우기 스타일을 생성한다.
     * 
     * 
     * @param rgba
     * @param img
     * @return FillStyle
     */
    private FillStyle parseFillStyle(String rgba, String img) {
        Color c = XMLUtil.getColor(rgba);
        if (c == null) {
            c = new Color(204, 204, 204, 0);
        }
        //
        if (StrUtil.isEmpty(img)) {
            return new FillStyle(c, null, 1.0f);
        }
        return new FillStyle(c, img, 1.0f);
    }

    private int getColType(String typ) {
    	if (typ.equalsIgnoreCase("V")) {
            return TypeDef.STRING_TYPE;
        }
        if (typ.equalsIgnoreCase("C")) {
            return TypeDef.STRING_TYPE;
        }
        if (typ.equalsIgnoreCase("N")) {
            return TypeDef.INTEGER_TYPE;
        }
        if (typ.equalsIgnoreCase("F")) {
            return TypeDef.DOUBLE_TYPE;
        }
        if (typ.equalsIgnoreCase("D")) {
            return TypeDef.DOUBLE_TYPE;
        }
        return TypeDef.UNKNOWN;
    }

    private int getLayerType(String typ) {
        if (typ.equalsIgnoreCase("SP")) {
            return LayerInfo.LAYER_POINT;
        }
        if (typ.equalsIgnoreCase("SL")) {
            return LayerInfo.LAYER_LINESTRING;
        }
        if (typ.equalsIgnoreCase("SA")) {
            return LayerInfo.LAYER_POLYGON;
        }
        if (typ.equalsIgnoreCase("ML")) {
            return LayerInfo.LAYER_MULTILINESTRING;
        }
        if (typ.equalsIgnoreCase("MA")) {
            return LayerInfo.LAYER_MULTIPOLYGON;
        }
        if (typ.equalsIgnoreCase("ST")) {
            return LayerInfo.LAYER_OPENMAP;
        }
        return TypeDef.UNKNOWN;
    }

    private boolean isYes(String y) {
        if (y.equalsIgnoreCase("Y")) {
            return true;
        }
        return false;
    }

    private boolean isRange(String typ) {
        if (typ.equalsIgnoreCase("R")) {
            return true;
        }
        return false;
    }

    private LabelItem[] getLabelItems(String cols, String scale) {
        if (cols == null || scale == null) {
            return null;
        }
        int minScale = XMLUtil.getInt(StrUtil.left(scale, ","));
        int maxScale = XMLUtil.getInt(StrUtil.right(scale, ","));
        if (minScale <= 0 && maxScale <= 0) {
            return null;
        }

        String str[] = cols.split(",");
        LabelItem item[] = new LabelItem[str.length];
        for (int x = 0; x < str.length; x++) {
            item[x] = new LabelItem(str[x], minScale, maxScale);
        }
        return item;
    }
    
    //20140807 추가됨
    private double[] getZoomScale(String strs) {
        String arr[] = strs.split(",");
        double scale[] = new double[arr.length];
        for (int x = 0; x < scale.length; x++) {
            scale[x] = Double.parseDouble(arr[x].trim());
        }
        return scale;
    }

    private void parsetTileBaseParam(String xml, OpenMapLayerInfo info) throws UnsupportedEncodingException {
    	Element root = XMLUtil.parseDOM(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        // Element tile = XMLUtil.getElementByName(root, "TileBaseParam");        
        // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>" + tile.getTagName());
        //
        int tileSize = XMLUtil.getInt(root.getAttributes(), "tileSize");
        double minx = XMLUtil.getDouble(root.getAttributes(), "minx");
        double miny = XMLUtil.getDouble(root.getAttributes(), "miny");
        double maxx = XMLUtil.getDouble(root.getAttributes(), "maxx");
        double maxy = XMLUtil.getDouble(root.getAttributes(), "maxy");
        String format = XMLUtil.getString(root.getAttributes(), "format");
        //
        Element ellip = XMLUtil.getElementByName(root, "Ellipsoid");
        String e_name = XMLUtil.getString(ellip.getAttributes(), "name");
        double e_a = XMLUtil.getDouble(ellip.getAttributes(), "a");
        double e_f = XMLUtil.getDouble(ellip.getAttributes(), "f");
        //
        Element base = XMLUtil.getElementByName(root, "Base");
        String b_name = XMLUtil.getString(base.getAttributes(), "name");
        double xn = XMLUtil.getDouble(base.getAttributes(), "xn");
        double ye = XMLUtil.getDouble(base.getAttributes(), "ye");
        double lat = XMLUtil.getDouble(base.getAttributes(), "lat");
        double lon = XMLUtil.getDouble(base.getAttributes(), "lon");
        double sf = XMLUtil.getDouble(base.getAttributes(), "sf");
        //
        Element zoom = XMLUtil.getElementByName(root, "ZoomScale");
        int level = XMLUtil.getInt(zoom.getAttributes(), "level");
        double scale = XMLUtil.getDouble(zoom.getAttributes(), "scale");

        String url = XMLUtil.getTagValue(XMLUtil.getElementByName(root, "BaseURL"));

        info.setTileSize(tileSize);
        info.setFormat(format);
        info.setBound(minx, miny, maxx, maxy);
        info.setBaseEllipsoid(new Ellipsoid(e_name, e_a, e_f));
        //
        int mode = GeoModel.TM;
        if (StrUtil.isEmpty(XMLUtil.getString(base.getAttributes(), "xn")) ||
            StrUtil.isEmpty(XMLUtil.getString(base.getAttributes(), "ye"))) {
            mode = GeoModel.GP;
        }
        info.setBaseBase(new Base(b_name, mode, xn, ye, lat, lon, sf));

        info.setZoomScale(level, scale);
        info.setBaseURL(url);
    }

    public static void main(String args[]) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("<TileBaseParam tileSize='256' basex='362727.85600000003' basey='469825.29553792503'  ");
        sb.append("                w='13606.33526280' h='15283.408462075' format='png'>   ");
        sb.append("   <Ellipsoid name='bessel' a='6377397.155'  f='299.152813' />");
        sb.append("   <Base name='tm127' xn='500000.0' ye='200000.0' lat='38.0' lon='127.00289' sf='1.0'/>   ");
        sb.append("   <ZoomScale xyfactor='1.123256789'>53.1497471203358600,26.5748735601679300,13.2874367800839650,6.64371839004198250,3.32185919502099130,1.66092959751049560,0.83046479875524781,0.41523239937762391</ZoomScale>   ");
        sb.append("   <BaseURL>http://127.0.0.1:8080/tile/</BaseURL>   ");
        sb.append("</TileBaseParam> ");
        // DBHelper mk = new DBHelper(); //
        // mk.parsetTileBaseParam(sb.toString());
    }

}
