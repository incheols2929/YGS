package geomex.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 문자열 유틸리티 클래스
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class StrUtil {

    /**
     * 문자열 끝의 \r\n문자를 제거한다.
     * 
     * @param str 대상 문자열
     * @return String 개행문자를 제거한 문자열
     */
    public static String removeCRLF(String str) {
        if (str.endsWith("\r\n")) {
            return str.substring(0, str.lastIndexOf("\r\n"));
        }
        return str;
    }

    /**
     * Null인경우 공백문자열을 반환한다.
     * 
     * @param str
     * @return String
     */
    public static String chkNull(String str) {
        if (str == null) return "";
        return str;
    }

    /**
     * 문자열이 null 이거나 length ==0 인지를 체크한다.
     * 
     * @param str 체크할 문자열
     * @return true : str == null or str.length() == 0 <br>
     *         false : other case
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 문자열 배열에 해당문자열이 포함되는지 여부를 체크한다.
     * 
     * @param data 문자열 배열
     * @param key 체크할 문자열
     * @return true : data[]에 key 문자열이 포함되어 있다. <br>
     *         false : data[]에 key문자열이 없다.
     */
    public static boolean contains(String data[], String key) {
        if (key == null || data == null) {
            return false;
        }
        for (int i = 0; i < data.length; i++) {
            if (key.equals(data[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 실수를 포맷형태의 문자열로 변환한다.
     * 
     * @param s 포맷 문자열로 변환할 수
     * @param f 포맷 문자열
     * @return String 포맷 적용 결과 문자열
     */
    public static String format(double s, String f) {
        DecimalFormat format = new DecimalFormat(f);
        return format.format(s);
    }

    /**
     * 정수를 포맷형태의 문자열로 변환한다.
     * 
     * @param v 포맷 문자열로 변환할 수
     * @param format 포맷 문자열
     * @return String 포맷 적용 결과 문자열
     */
    public static String numFormat(int v, String format) {
        NumberFormat formatter = new DecimalFormat(format);
        return formatter.format(v);
    }

    /**
     * 문자가 한글인지 여부를 판단한다.
     * 
     * @param c 체크할 문자
     * @return true : c가 한글이다. <br>
     *         false : c가 한글이 아니다
     */
    public static boolean isHangul(char c) {
        // ( 0xAC00 <= c && c <= 0xD7A3 ) 초중종성이 모인 한글자
        // ( 0x3131 <= c && c <= 0x318E ) 자음 모음
        if (!((0xAC00 <= c && c <= 0xD7A3) || (0x3131 <= c && c <= 0x318E))) {
            return false;
        }
        return true;
    }

    /**
     * 문자가 숫자인지 여부를 판단한다.
     * 
     * @param ch 체크할 문자
     * @return true : ch가 숫자이다. <br>
     *         false : ch가 숫자가 아니다
     */
    public static boolean isNumber(char ch) {
        if (0x30 <= ch && ch <= 0x39) {
            return true;
        }
        return false;
    }

    /**
     * 문자가 영문자인지 여부를 판단한다.
     * 
     * @param ch 체크할 문자
     * @return true : ch가 영문자이다. <br>
     *         false : ch가 영문자가 아니다
     */
    public static boolean isAlphabet(char ch) {
        if (0x41 <= ch && ch <= 0x5A) {
            return true;
        } // 대문자
        if (0x61 <= ch && ch <= 0x7A) {
            return true;
        } // 소문자
        return false;
    }

    /**
     * 파일 확장자 명을 얻는다.
     * 
     * @param file
     * @return 확장자
     */
    public static String fileExtName(String file) {
        return file.substring(file.lastIndexOf(".") + 1);
    }

    /**
     * 확장자를 제외한 파일명을 얻는다.
     * 
     * @param file
     * @return 파일명
     */
    public static String fileName(String file) {
        return file.substring(0, file.lastIndexOf("."));
    }

    /**
     * HashCode를 얻는다.
     * 
     * @param str
     * @return HashCode
     */
    public static String getHashCode(String str) {
        String rv = Integer.toHexString(str.hashCode()).toUpperCase();
        for (int x = 0; x < (8 - rv.length()); x++) {
            rv = rv + "F";
        }
        return rv;
        // return Integer.toHexString(str.hashCode()).toUpperCase();
    }

    /**
     * 문자열 자르기
     * 
     * @param str
     * @param d
     * @return String
     */
    public static String left(String str, String d) {
        if (str.contains(d)) {
            return str.substring(0, str.indexOf(d)).trim();
        }
        return str.trim();
    }

    /**
     * str문자열중 d문자열 이후 문자열을 얻는다.
     * 
     * @param str
     * @param d
     * @return String
     */
    public static String right(String str, String d) {
        if (str.contains(d)) {
            return str.substring(str.indexOf(d) + 1).trim();
        }
        return str.trim();
    }

    /**
     * String배열을 콤마(",")분리된 문자열로 변환한다.
     * 
     * @param s 배열
     * @param delimiter 구분자
     * @return 문자열
     */
    public static String arrayToString(String[] s, char delimiter) {
        StringBuilder res = new StringBuilder(s.length * 20);

        for (int i = 0; i < s.length; i++) {
            res.append(s[i]);

            if (i < (s.length - 1)) {
                res.append(delimiter);
            }
        }
        return res.toString();
    }
}
