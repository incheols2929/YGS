package geomex.utils;

import geovlet.io.geotrans.Base;
import geovlet.io.geotrans.Ellipsoid;
import geovlet.io.geotrans.GeoParam;
import geovlet.io.geotrans.GeoTrans;
import geovlet.io.geotrans.LatiLong;
import geovlet.io.geotrans.GeoModel;

import java.util.ArrayList;

import geomex.svc.webctrl.Const;
import geomex.utils.db.DBHandler;
import geomex.utils.Utils;
import geomex.pkg.openapi.GmxLayerDefinition;
import geomex.pkg.openapi.LayerInfo;

public class GeometryToKml {
    public static GmxLayerDefinition createGoogleKml(String layerName, String key, String minx, String miny, String maxx,
        String maxy)
    {

        //String kml = "";
        //String tableName = "";
        //String keyColName = "";
        //String clause = "";
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        GmxLayerDefinition layerDef = new GmxLayerDefinition();
        try {
            LayerInfo layerInfo = new LayerInfo();
            ArrayList<GmxLayerDefinition> list = layerInfo.getLayerDef();
            for (int i = 0; i < list.size(); i++) {
                if (layerName.equals(list.get(i).Layer_layername)) {
                    //tableName = list.get(i).Layer_dataset;
                    //keyColName = list.get(i).Extent_pkey;
                    //clause = list.get(i).Clause;
                    layerDef = list.get(i);
                }
            }

            sb.append("select st_askml(ST_GeomFromText(_geometry,4326)) as kml ");

            //라벨필드를 정의한다.
            //if(GmxLayerDefinition.LABEL_VISIBLE_YES.equals(layerDef.Label_visible.toLowerCase())) {
            for (int i = 0; i < layerDef.LabelItems.size(); i++) {
                if (!"".equals(layerDef.LabelItems.get(i).LabelItem_column.trim()))
                    sb.append(" ," + layerDef.LabelItems.get(i).LabelItem_column + " as layer");
            }
            //}

            sb.append(" from " + layerDef.Layer_dataset + " where 1 = 1 ");

            if (layerDef.Clauses.size() != 0) {
                for (int i = 0; i < layerDef.Clauses.size(); i++) {
                    if (!"".equals(layerDef.Clauses.get(i).Clause.trim())) {
                        sb.append(" and " + layerDef.Clauses.get(i).Clause + "");
                    }
                }
            }

            if (!"".equals(Utils.chkNull(key)))
                sb.append(" and " + layerDef.Extent_pkey + " = '" + key + "' ");
            if (!"".equals(Utils.chkNull(minx)) && !"".equals(Utils.chkNull(miny))
                && !"".equals(Utils.chkNull(maxx)) && !"".equals(Utils.chkNull(maxy)))
                sb.append("  and st_intersects(GeomFromText('POLYGON(("
                    + minx + " " + miny + "," + maxx + " " + miny + "," + maxx + " " + maxy + "," + minx + " " + maxy + "," + minx
                    + " " + miny + "))'), _geometry) ");

            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.execute();

            //int labelCnt = 0;
            while (handler.next()) {

                String kml = handler.getString("kml");
                //System.out.println("라벨필드>>>>");
                //라벨필드를 정의한다.
                //if(GmxLayerDefinition.LABEL_VISIBLE_YES.equals(layerDef.Label_visible.toLowerCase())) {
                ArrayList<String> labelNames = new ArrayList<String>();
                ArrayList<String> labelValues = new ArrayList<String>();
                for (int i = 0; i < layerDef.LabelItems.size(); i++) {
                    if (!"".equals(layerDef.LabelItems.get(i).LabelItem_column.trim())) {
                        labelNames.add(layerDef.LabelItems.get(i).LabelItem_column);
                        //labelValues.add(handler.getString(layerDef.LabelItems.get(i).LabelItem_column));
                        labelValues.add(handler.getString("layer"));
                        //System.out.println(handler.getString(layerDef.LabelItems.get(i).LabelItem_column));
                    }
                }
                //}
                layerDef.kml.kmlLabelName.add(labelNames);
                layerDef.kml.kmlLabelValue.add(labelValues);
                //Bessel TM중부좌표를 WGS84 경위도로 바꾸기
                //서부 TM125, 중부 TM127, 동부 TM129
                //GeoParam src = new GeoParam(new Ellipsoid(Ellipsoid.ENUM.WGS84), new Base(Base.ENUM.UTMK));
                //GeoParam tgt = new GeoParam(new Ellipsoid(Ellipsoid.ENUM.WGS84), new Base(Base.ENUM.TM127));

                GeoParam src = new GeoParam(new Ellipsoid("wgs84", 6377397.155, 299.152813), new Base("tm127", GeoModel.TM,
                    600000.0, 200000.0, 38.0, 127.0, 1.0), -6, 10);
                GeoParam tgt = new GeoParam(new Ellipsoid("wgs84", 6377397.155, 299.152813), new Base("tm127", GeoModel.TM,
                    600000.0, 200000.0, 38.0, 127.0, 1.0));

                GeoTrans trans = new GeoTrans();
                trans.setModel(src, tgt);
                String tagOuterBndS = "";
                String tagOuterBndE = "";
                String tagInnerBndS = "";
                String tagInnerBndE = "";

                if ("6".equals(Utils.chkNull(layerDef.Layer_type)) || "3".equals(Utils.chkNull(layerDef.Layer_type))) {
                    tagOuterBndS = "<outerBoundaryIs><LinearRing><coordinates>";
                    tagOuterBndE = "</coordinates></LinearRing></outerBoundaryIs>";
                    tagInnerBndS = "<innerBoundaryIs><LinearRing><coordinates>";
                    tagInnerBndE = "</coordinates></LinearRing></innerBoundaryIs>";
                } else {
                    tagOuterBndS = "<coordinates>";
                    tagOuterBndE = "</coordinates>";
                    tagInnerBndS = "<coordinates>";
                    tagInnerBndE = "</coordinates>";

                }

                String outerBoundCoord = "";
                String innerBoundCoord = "";

                if (kml.indexOf(outerBoundCoord) > -1)
                    outerBoundCoord = kml.substring(kml.indexOf(tagOuterBndS) + tagOuterBndS.length(), kml.indexOf(tagOuterBndE));
                if (kml.indexOf(tagInnerBndS) > -1)
                    innerBoundCoord = kml.substring(kml.indexOf(tagInnerBndS) + tagInnerBndS.length(), kml.indexOf(tagInnerBndE));

                String[] outerBndList = outerBoundCoord.split(" ");
                String[] innerBndList = innerBoundCoord.split(" ");

                //ArrayList<LatiLong> outerBoundLatLng = new ArrayList<LatiLong>();
                //ArrayList<LatiLong> innerBoundLatLng = new ArrayList<LatiLong>();
                StringBuffer outerBoundLatLng = new StringBuffer();
                StringBuffer innerBoundLatLng = new StringBuffer();

                double tempMaxCenterLat = 0;
                double tempMinCenterLat = 99999999;
                double tempMaxCenterLng = 0;
                double tempMinCenterLng = 99999999;
                for (int i = 0; outerBndList != null && i < outerBndList.length; i++)
                {
                    String x = outerBndList[i].split(",")[0];
                    String y = outerBndList[i].split(",")[1];
                    LatiLong ltlg = trans.STM2TGP(Double.parseDouble(y), Double.parseDouble(x));
                    outerBoundLatLng.append(ltlg.longitude + "," + ltlg.latitude + ",0 ");
                    //객체당 센터좌표구하기
                    if (tempMaxCenterLat < ltlg.latitude) tempMaxCenterLat = ltlg.latitude;
                    if (tempMinCenterLat > ltlg.latitude) tempMinCenterLat = ltlg.latitude;
                    if (tempMaxCenterLng < ltlg.longitude) tempMaxCenterLng = ltlg.longitude;
                    if (tempMinCenterLng > ltlg.longitude) tempMinCenterLng = ltlg.longitude;
                }
                for (int i = 0; innerBndList != null && i < innerBndList.length; i++)
                {
                    if (innerBndList[i].indexOf(",") > -1) {
                        String x = innerBndList[i].split(",")[0];
                        String y = innerBndList[i].split(",")[1];
                        LatiLong ltlg = trans.STM2TGP(Double.parseDouble(y), Double.parseDouble(x));
                        innerBoundLatLng.append(ltlg.longitude + "," + ltlg.latitude + ",0 ");
                    }
                }
                layerDef.kml.kmlOuterCoords.add(outerBoundLatLng);
                layerDef.kml.kmlInnerCoords.add(innerBoundLatLng);
                double tempCLat = tempMinCenterLat + (tempMaxCenterLat - tempMinCenterLat) / 2;
                double tempCLng = tempMinCenterLng + (tempMaxCenterLng - tempMinCenterLng) / 2;
                layerDef.kml.kmlCenterCoordLat.add("" + tempCLat);
                layerDef.kml.kmlCenterCoordLng.add("" + tempCLng);
                layerDef.kml.kmlMaxCoordLat.add("" + tempMaxCenterLat);
                layerDef.kml.kmlMinCoordLat.add("" + tempMinCenterLat);
                layerDef.kml.kmlMaxCoordLng.add("" + tempMaxCenterLng);
                layerDef.kml.kmlMinCoordLng.add("" + tempMinCenterLng);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return layerDef;
    }

    //모든레이어
    public static ArrayList<GmxLayerDefinition> getGoogleKml(String minx, String miny, String maxx, String maxy, double scale)
        throws Exception
    {
        LayerInfo layerInfo = new LayerInfo();
        ArrayList<GmxLayerDefinition> list = layerInfo.getLayerDef();

        try {

            for (int i = 0; i < list.size(); i++) {
                //네이버나 다음같은 타업체 영상이면 처리하지않음.
                int minscale = Integer.parseInt(list.get(i).Layer_minScale);
                int maxscale = Integer.parseInt(list.get(i).Layer_maxScale);
                if (scale > minscale && scale <= maxscale && !"200".equals(list.get(i).Layer_type)) {
                    GmxLayerDefinition layerDef = new GmxLayerDefinition();
                    layerDef = createGoogleKml(list.get(i).Layer_layername, "", minx, miny, maxx, maxy);
                    list.get(i).kml = layerDef.kml;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    //레이어 하나만 선택했을시
    public static GmxLayerDefinition getGoogleKml(String layeName, String minx, String miny, String maxx, String maxy, double scale)
        throws Exception
    {

        LayerInfo layerInfo = new LayerInfo();
        GmxLayerDefinition list = new GmxLayerDefinition();
        ArrayList<GmxLayerDefinition> layerList = layerInfo.getLayerDef();

        try {

            for (int i = 0; i < layerList.size(); i++) {
                //int minscale = Integer.parseInt(layerList.get(i).Layer_minScale);
                int maxscale = Integer.parseInt(layerList.get(i).Layer_maxScale);
                //if(scale >  minscale && scale <= maxscale && layeName.equals(layerList.get(i).Layer_dataset) && !"200".equals(layerList.get(i).Layer_type)){
                //if(layeName.equals(layerList.get(i).Layer_dataset) && !"200".equals(layerList.get(i).Layer_type)){
                //if(scale <= maxscale && layeName.equals(layerList.get(i).Layer_dataset) && !"200".equals(layerList.get(i).Layer_type)){
                if (scale <= maxscale
                    && layeName.equals(layerList.get(i).Layer_layername) && !"200".equals(layerList.get(i).Layer_type)) {
                    GmxLayerDefinition layerDef = new GmxLayerDefinition();
                    layerDef = createGoogleKml(layerList.get(i).Layer_layername, "", minx, miny, maxx, maxy);
                    layerList.get(i).kml = layerDef.kml;
                    list = layerList.get(i);
                }

            }

            /*
             * for(int i = 0 ; i < layerList.size();i++) {
             * if(layeName.equals(layerList.get(i).Layer_dataset)){
             * GmxLayerDefinition layerDef = new GmxLayerDefinition(); layerDef
             * = createGoogleKml(layerList.get(i).Layer_layername,"",minx, miny,
             * maxx, maxy); layerList.get(i).kml = layerDef.kml; list =
             * layerList.get(i); } }
             */

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    //레이어의 객체하나만 선택했을시
    public static GmxLayerDefinition getGoogleKml(String layeName, String key, String minx, String miny, String maxx, String maxy,
        double scale) throws Exception
    {
        LayerInfo layerInfo = new LayerInfo();
        GmxLayerDefinition list = new GmxLayerDefinition();
        ArrayList<GmxLayerDefinition> layerList = layerInfo.getLayerDef();
        //System.out.println("layerName:" + layeName);
        try {

            for (int i = 0; i < layerList.size(); i++) {
                //int minscale = Integer.parseInt(layerList.get(i).Layer_minScale);
                int maxscale = Integer.parseInt(layerList.get(i).Layer_maxScale);

                //if(scale >  minscale && scale <= maxscale && layeName.equals(layerList.get(i).Layer_dataset) && !"200".equals(layerList.get(i).Layer_type)){
                if (scale <= maxscale
                    && layeName.equals(layerList.get(i).Layer_dataset) && !"200".equals(layerList.get(i).Layer_type)) {
                    GmxLayerDefinition layerDef = new GmxLayerDefinition();

                    layerDef = createGoogleKml(layerList.get(i).Layer_layername, key, minx, miny, maxx, maxy);
                    layerList.get(i).kml = layerDef.kml;

                    list = layerList.get(i);
                }
                //System.out.println("===");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }
}
