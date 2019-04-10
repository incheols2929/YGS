package geomex.utils;

/**
 * Hex 유틸리티 클래스
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class Hex {

    /**
     * HEX코드를 byte배열로 변환한다.
     * 
     * @param h HEX코드
     * @return byte[] 변환결과
     */
    public static byte[] hexToBytes(String h) {
        int bytes = h.length() / 2;
        byte rs[] = new byte[bytes];
        for (int i = 0; i < bytes; i++) {
            String c = h.substring(0, 2);
            rs[i] = (byte) Integer.parseInt(c, 16);
            h = h.substring(2);
        }
        return rs;
    }

    /**
     * ByteBuffer의 데이터를 Hex 코드로 변환한다.
     * 
     * @param bytes 변환대상 데이터
     * @return String HEX코드 문자열
     */
    public static String bytesToHex(java.nio.ByteBuffer bytes) {
        StringBuilder buf = new StringBuilder(bytes.remaining());
        for (int i = 0; i < bytes.remaining(); i++) {
            byte b = bytes.get(); // bytes[i];
            buf.append(byteToHex(b));
        }
        String rv = buf.toString();
        buf = null;
        return rv;
    }

    /**
     * byte 배열의 데이터를 Hex 코드로 변환한다.
     * 
     * @param bytes 변환대상 데이터
     * @return String HEX코드 문자열
     */
    public static String bytesToHexd(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            buf.append(byteToHex(b));
        }
        String rv = buf.toString();
        buf = null;
        return rv;
    }

    /**
     * byte를 Hex 코드로 변환한다.
     * 
     * @param b 변환대상 데이터
     * @return String HEX코드
     */
    public static String byteToHex(byte b) {
        int i = b & 0xff;
        if (i < 16) {
            return "0" + Integer.toHexString(i);
        }
        return Integer.toHexString(i);
    }

    /**
     * int형의 데이터를 Hex 코드로 변환한다.
     * 
     * @param n 변환대상 숫자
     * @return String HEX코드
     */
    public static char toHexDigit(int n) {
        if (n < 0 || n > 15) {
            throw new IllegalArgumentException("Nibble value out of range: "
                + n);
        }
        if (n <= 9) {
            return (char) ('0' + n);
        }
        return (char) ('A' + (n - 10));
    }
}
