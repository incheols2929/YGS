package geomex.utils;

/**
 * Debug 클래스 <br>
 * setDebug(true)이면 Debug 메세지를 화면에 출력한다.
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class Debug {

    private static boolean isDebug = true;

    /**
     * Debug옵션인지 여부를 얻는다.
     * 
     * @return true, false
     */
    public static boolean isDebug() {
        return isDebug;
    }

    /**
     * Debug 옵션을 설정한다.
     * 
     * @param d Debug 옵션 설정(true, false)
     */
    public synchronized static void setDebug(boolean d) {
        isDebug = d;
    }

    public static void debug(String msg) {
        if (isDebug) {
            System.out.println("DEBUG >> " + msg);
        }
    }
}
