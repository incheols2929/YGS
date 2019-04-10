package geomex.pkg.usr;

import geomex.svc.webctrl.Const;
import geomex.utils.db.DBHandler;
import geomex.utils.Utils;

public class FavoritesBean {

    public FavoritesBean() {

    }

    /*
     * /////////////////////////////////////////////// 사용자즐겨찾기 기능 시작
     *///////////////////////////////////////////////

    public String getXMLcreate(String id) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();

        String xml = "";

        sb.append("select favr_xml from mt_favr_web where usr_id='" + id + "' ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.execute();
            if (handler.next()) {
                xml = Utils.chkNull(handler.getString("favr_xml"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return xml;
    }
 /**즐겨찾기 폴더 추가 하는 부분
  * DB favr_xml 메모장에 열어보면 즐겨찾기 주소 및 이름이 정의 되어있음 
  * @param id
  * @param xml
  * @return
  */
    public boolean getUserxmlUpdate(String id, String xml) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        boolean bool = false;
       
        sb.append(" UPDATE mt_favr_web SET favr_xml='" + xml + "' WHERE usr_id = '" + id + "' ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.execute();
            bool = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return bool;
    }

    public boolean getUserxmlInsert(String id, String xml) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        boolean bool = false;

        sb.append(" INSERT INTO mt_favr_web(usr_id, favr_xml) VALUES ( '" + id + "', '" + xml + "') ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.execute();
            bool = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return bool;
    }

    public boolean getUserDEL(String id) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        boolean bool = false;

        sb.append(" delete from mt_favr_web where usr_id = '" + id + "' ");

        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.execute();
            bool = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return bool;
    }

}
