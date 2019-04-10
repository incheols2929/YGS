package geomex.pkg.sys.lris;

import java.util.ArrayList;
import java.util.Arrays;

public class LRGST {

    /**
     * 일필지 기본정보를 얻는다.
     * 
     * @param pnu
     * @return LRGST81[]
     */
    public static LRGST83[] getJiga(String pnu) throws Exception {
        LRGST83 st83 = new LRGST83();
        ArrayList<LRGST83> r = st83.getBaseInfo(pnu);
        LRGST83 arr[] = new LRGST83[r.size()];
        Arrays.sort(r.toArray(arr));
        return arr;
    }

    /**
     * 공시지가 정보를 얻는다.
     * 
     * @param pnu
     * @return XML
     */
    public static String getJigaXML(String pnu) throws Exception {
        LRGST83 r[] = getJiga(pnu);
        StringBuilder sb = new StringBuilder(500);

        sb.append("<공시지가>");
        for (LRGST83 lrg : r) {
            sb.append(lrg.toXML());
        }
        sb.append("</공시지가>");
        return sb.toString();
    }

    /**
     * 일필지 기본정보를 얻는다.
     * 
     * @param pnu
     * @return LRGST81[]
     */
    public static LRGST81[] getBase(String pnu) throws Exception {
        LRGST81 st81 = new LRGST81();
        ArrayList<LRGST81> r = st81.getBaseInfo(pnu);
        LRGST81 arr[] = new LRGST81[r.size()];
        Arrays.sort(r.toArray(arr));
        return arr;
    }

    /**
     * 일필지 기본정보를 얻는다.
     * 
     * @param pnu
     * @return XML
     */
    public static String getBaseXML(String pnu) throws Exception {
        LRGST81 r[] = getBase(pnu);
        StringBuilder sb = new StringBuilder(500);
        //개별공시지가

        sb.append("<일필지기본목록>");
        for (LRGST81 lrg : r) {
            sb.append(lrg.toXML());
        }
        sb.append("</일필지기본목록>");
        return sb.toString();
    }

    /**
     * 공유지연명부
     * 
     * @param pnu
     * @return LRGST82[]
     */
    public static LRGST82[] getShareInfo(String pnu) throws Exception {
        LRGST82 st82 = new LRGST82();
        ArrayList<LRGST82> r = st82.getBaseInfo(pnu);

        LRGST82 arr[] = new LRGST82[r.size()];
        Arrays.sort(r.toArray(arr));

        return reverse82(arr);
    }

    private static LRGST82[] reverse82(LRGST82[] arr) {
        LRGST82[] tmp = new LRGST82[arr.length];
        for (int x = 0; x < arr.length; x++) {
            tmp[x] = arr[arr.length - 1 - x];
        }

        return tmp;
    }

    /**
     * 공유지연명부 년월일 늦은순으로 2012-03-27
     * 
     * @param pnu
     * @return LRGST82[]
     */
    public static LRGST82[] getShareInfoPDF(String pnu) throws Exception {
        LRGST82 st82 = new LRGST82();
        ArrayList<LRGST82> r = st82.getBaseInfo(pnu);

        LRGST82 arr[] = new LRGST82[r.size()];
        Arrays.sort(r.toArray(arr));

        return arr;
    }

    /**
     * 공유지연명부
     * 
     * @param pnu
     * @return XML
     */
    public static String getShareInfoXML(String pnu) throws Exception {
        LRGST82 r[] = getShareInfo(pnu);
        StringBuilder sb = new StringBuilder(500);
        sb.append("<공유지연명부목록>");
        for (LRGST82 lrg : r) {
            sb.append(lrg.toXML());
        }
        sb.append("</공유지연명부목록>");
        return sb.toString();
    }

    /**
     * 토지이동연혁 정보를 얻는다
     * 
     * @param pnu
     * @return LRGST84[]
     * @throws Exception
     */
    public static LRGST84[] getLandHist(String pnu) throws Exception {
        return getLandHist(pnu, -1);
    }

    /**
     * 토지이동연혁 정보를 얻는다 2012-03-27 소트정렬
     * 
     * @param pnu
     * @return LRGST84[]
     * @throws Exception
     */
    public static LRGST84[] getLandHistPDF(String pnu) throws Exception {
        return getLandHistPDF(pnu, -1);
    }

    /**
     * 토지이동연혁 정보를 얻는다. 최대 limit갯수만큼 가져온다. <br>
     * limit < 0 이면 모든 결과 정보를 가져온다.
     * 
     * @param pnu
     * @return LRGST84[]
     */
    public static LRGST84[] getLandHist(String pnu, int limit) throws Exception {
        LRGST84 st84 = new LRGST84();
        ArrayList<LRGST84> r = st84.getBaseInfo(pnu);

        LRGST84 arr[] = new LRGST84[r.size()];
        Arrays.sort(r.toArray(arr));

        if (limit < 0 || arr.length <= limit) {
            return reverse84(arr);
        }

        LRGST84[] rs = new LRGST84[limit];
        for (int x = 0; x < rs.length; x++) {
            rs[x] = arr[arr.length - rs.length + x];
        }

        return reverse84(rs);
    }

    private static LRGST84[] reverse84(LRGST84[] arr) {
        LRGST84[] tmp = new LRGST84[arr.length];
        for (int x = 0; x < arr.length; x++) {
            tmp[x] = arr[arr.length - 1 - x];
        }

        return tmp;
    }

    /**
     * 토지이동연혁 정보를 얻는다. 최대 limit갯수만큼 가져온다. <br>
     * limit < 0 이면 모든 결과 정보를 가져온다.
     * 
     * @param pnu
     * @return LRGST84[]
     */
    public static LRGST84[] getLandHistPDF(String pnu, int limit) throws Exception {
        LRGST84 st84 = new LRGST84();
        ArrayList<LRGST84> r = st84.getBaseInfo(pnu);

        LRGST84 arr[] = new LRGST84[r.size()];
        Arrays.sort(r.toArray(arr));

        if (limit < 0 || arr.length <= limit) {
            return arr;
        }

        LRGST84[] rs = new LRGST84[limit];
        for (int x = 0; x < rs.length; x++) {
            rs[x] = arr[arr.length - rs.length + x];
        }

        return rs;
    }

    /**
     * 토지이동연혁
     * 
     * @param pnu
     * @return XML
     */
    public static String getLandHistXML(String pnu) throws Exception {
        return getLandHistXML(pnu, -1);
    }

    /**
     * 토지이동연혁 정보를 얻는다. 최대 limit갯수만큼 가져온다. <br>
     * limit < 0 이면 모든 결과 정보를 가져온다.
     * 
     * @param pnu
     * @param limit
     * @return XML
     */
    public static String getLandHistXML(String pnu, int limit) throws Exception {
        LRGST84 r[] = getLandHist(pnu, limit);
        StringBuilder sb = new StringBuilder(500);
        sb.append("<토지이동연혁목록>");
        for (LRGST84 lrg : r) {
            sb.append(lrg.toXML());
        }
        sb.append("</토지이동연혁목록>");
        return sb.toString();
    }

    // /////////////////////////////////////////////////////

    /**
     * 소유권변동연혁
     * 
     * @param pnu
     * @return LRGST85[]
     */
    public static LRGST85[] getOwnerHist(String pnu) throws Exception {
        return getOwnerHist(pnu, -1);
    }

    /**
     * 소유권변동연혁 2012-03-27
     * 
     * @param pnu
     * @return LRGST85[]
     */
    public static LRGST85[] getOwnerHistPDF(String pnu) throws Exception {
        return getOwnerHistPDF(pnu, -1);
    }

    /**
     * 소유권변동연혁 최대 limit갯수만큼 가져온다. <br>
     * limit < 0 이면 모든 결과 정보를 가져온다.
     * 
     * @param pnu
     * @param limit
     * @return LRGST85[]
     */
    public static LRGST85[] getOwnerHist(String pnu, int limit) throws Exception {
        LRGST85 st85 = new LRGST85();
        ArrayList<LRGST85> r = st85.getBaseInfo(pnu);

        LRGST85 arr[] = new LRGST85[r.size()];
        Arrays.sort(r.toArray(arr));

        if (limit < 0 || arr.length <= limit) {
            return reverse(arr);
        }

        LRGST85[] rs = new LRGST85[limit];
        for (int x = 0; x < rs.length; x++) {
            rs[x] = arr[arr.length - rs.length + x];
        }

        return reverse(rs);
    }

    private static LRGST85[] reverse(LRGST85[] arr) {
        LRGST85[] tmp = new LRGST85[arr.length];
        for (int x = 0; x < arr.length; x++) {
            tmp[x] = arr[arr.length - 1 - x];
        }
        return tmp;
    }

    /**
     * 소유권변동연혁 최대 limit갯수만큼 가져온다. <br>
     * 2012-03-27 limit < 0 이면 모든 결과 정보를 가져온다.
     * 
     * @param pnu
     * @param limit
     * @return LRGST85[]
     */
    public static LRGST85[] getOwnerHistPDF(String pnu, int limit) throws Exception {
        LRGST85 st85 = new LRGST85();
        ArrayList<LRGST85> r = st85.getBaseInfo(pnu);

        LRGST85 arr[] = new LRGST85[r.size()];
        Arrays.sort(r.toArray(arr));

        if (limit < 0 || arr.length <= limit) {
            return arr;
        }

        LRGST85[] rs = new LRGST85[limit];
        for (int x = 0; x < rs.length; x++) {
            rs[x] = arr[arr.length - rs.length + x];
        }

        return rs;
    }

    /**
     * 소유권변동연혁
     * 
     * @param pnu
     * @return XML
     * @throws Exception
     */
    public static String getOwnerHistXML(String pnu) throws Exception {
        return getOwnerHistXML(pnu, -1);
    }

    /**
     * 소유권변동연혁
     * 
     * @param pnu
     * @param limit 최대 limit갯수만큼 가져온다. <br>
     *            limit < 0 이면 모든 결과 정보를 가져온다.
     * @return XML
     */
    public static String getOwnerHistXML(String pnu, int limit) throws Exception {
        LRGST85[] r = getOwnerHist(pnu, limit);
        StringBuilder sb = new StringBuilder(500);
        sb.append("<소유권변동연혁목록>");
        for (LRGST85 lrg : r) {
            sb.append(lrg.toXML());
        }
        sb.append("</소유권변동연혁목록>");
        return sb.toString();
    }
}
