package geomex.pkg.sys.luris;

import geomex.svc.webctrl.Const;
import geomex.utils.db.DBHandler;

import java.util.ArrayList;

public class LurisBean {

    /*
     * 공간연산을 통한 UCODE 값을 가지고 온다.
     */
    public ArrayList<Luris> getUcode(String pnu) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<Luris> list = new ArrayList<Luris>();

        sb.append(" SELECT T2.UCODE AS UCODE FROM LP_PA_CBND T1, LT_C_UZONE T2 ");
        sb.append(" WHERE  ");
        sb.append(" T1.PNU = '" + pnu + "' ");
        sb.append(" AND ST_INTERSECTS(T1._GEOMETRY, T2._GEOMETRY)  ");
        sb.append(" AND NOT ST_TOUCHES(T1._GEOMETRY,T2._GEOMETRY)  ");

        //System.out.println(sb.toString());

        try {
            //handler.open("gwsdb");
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                Luris ls = new Luris();
                ls.ucode = handler.getString("UCODE");
                list.add(ls);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    /*
     * 국토의 계획및 이용에 관한 법률에 포함되는것 을 가지고온다.
     */
    public ArrayList<Luris> getIncludeUcode(String pnu) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<Luris> list = new ArrayList<Luris>();

        sb.append(" SELECT DISTINCT T2.UCODE AS UCODE, T2.ULYR AS ULYR FROM LP_PA_CBND T1, LT_C_UZONE T2 ");
        sb.append(" WHERE  ");
        sb.append(" T1.PNU = '" + pnu + "' ");
        sb.append(" AND ST_INTERSECTS(T1._GEOMETRY, T2._GEOMETRY)  ");
        sb.append(" AND NOT ST_TOUCHES(T1._GEOMETRY,T2._GEOMETRY)  ");
        sb.append(" AND SUBSTRING(T2.ULYR, 1, 2) = 'UQ'  ");
        //sb.append(" AND T2.ULYR = 'UQ111'  ");
        //sb.append(" AND T2.ULYR IN ('UQ114','UQ102','UQ131','UQ129','UQ122','UQ126','UQ130','UQ125','UQ112','UQ141','UQ115','UQ162','UQ151','UQ121','UQ124','UQ167','UQ166','UQ168','UQ163','UQ123','UQ111','UQ165','UQ113','UQ101','UQ161','UQ164','UQ127', 'UQ128') ");  

        //System.out.println(sb.toString());

        try {
            //handler.open("gwsdb");
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                Luris ls = new Luris();
                ls.ucode = handler.getString("UCODE");
                ls.ulyr = handler.getString("ULYR");
                list.add(ls);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    /*
     * 다른법령등에 따른 지역.지구를 가지고온다.
     */
    public ArrayList<Luris> getDifUcode(String pnu) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<Luris> list = new ArrayList<Luris>();

        sb.append(" SELECT DISTINCT T2.UCODE AS UCODE FROM LP_PA_CBND T1, LT_C_UZONE T2 ");
        sb.append(" WHERE  ");
        sb.append(" T1.PNU = '" + pnu + "' ");
        sb.append(" AND ST_INTERSECTS(T1._GEOMETRY, T2._GEOMETRY)  ");
        sb.append(" AND NOT ST_TOUCHES(T1._GEOMETRY,T2._GEOMETRY)  ");
        sb.append(" AND SUBSTRING(T2.ULYR, 1, 2) <> 'UQ'  ");
        //sb.append(" AND T2.ULYR != 'UQ111'  ");
        //sb.append(" AND T2.ULYR NOT IN ('UQ114','UQ102','UQ131','UQ129','UQ122','UQ126','UQ130','UQ125','UQ112','UQ141','UQ115','UQ162','UQ151','UQ121','UQ124','UQ167','UQ166','UQ168','UQ163','UQ123','UQ111','UQ165','UQ113','UQ101','UQ161','UQ164','UQ127', 'UQ128') ");

        try {
            //handler.open("gwsdb");
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                Luris ls = new Luris();
                ls.ucode = handler.getString("UCODE");
                list.add(ls);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }
}
