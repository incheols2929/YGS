package geomex.pkg.sys.lris;

public class PNU {
    // 행정구역코드
    private String ADM_DIST_CODE = "";
    // 소재지코드
    private String LAND_SITE_CODE = "";
    // 대장구분 1:토지, 2:임야
    private String LG_GBN = "";
    // 본번
    private String BO_JIBN = "";
    // 부번
    private String BU_JIBN = "";

    /**
     * 
     * Creates a new instance of PNU
     */
    public PNU(String pnu) {
        // 행정코드(10) + 구분(1)[1:토지,2:임야,3:수치,8:폐쇄토지,9:폐쇄임야] + 본번(4) + 부번(4)
        this.ADM_DIST_CODE = pnu.substring(0, 5);
        this.LAND_SITE_CODE = pnu.substring(5, 10);
        this.LG_GBN = pnu.substring(10, 11);
        this.BO_JIBN = pnu.substring(11, 15);
        this.BU_JIBN = pnu.substring(15);
    }

    public String getADM_DIST_CODE() {
        return ADM_DIST_CODE;
    }

    public String getLAND_SITE_CODE() {
        return LAND_SITE_CODE;
    }

    public String getLG_GBN() {
        return LG_GBN;
    }

    public String getBO_JIBN() {
        return BO_JIBN;
    }

    public String getBU_JIBN() {
        return BU_JIBN;
    }

    @Override
    public String toString() {
        // return
        // ADM_DIST_CODE+":"+LAND_SITE_CODE+":"+LG_GBN+":"+BO_JIBN+":"+BU_JIBN;
        return ADM_DIST_CODE + LAND_SITE_CODE + LG_GBN + BO_JIBN + BU_JIBN;
    }
}
