package geomex.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * <PRE>
 * 파일명   : DateUtil.java
 * 파일설명 : 날짜 유틸리티 클래스
 * 수정이력 : 
 *       2013. 6. 19.  김경호  : 최초작성
 * 
 * </PRE>
 * 
 */
public class DateUtil {

    /**
     * 년월일시분초 형태의 문자열을 2007-11-10 23:05:03 형식으로 변환한다.
     * 
     * @param ymdhms 년월일시분초 문자열
     * @return String 2007-11-10 23:05:03 형식의 문잘열
     */
    public static String formatDate(String ymdhms) {
        return new StringBuilder(19).append(ymdhms.substring(0, 4)).append("-")
            .append(ymdhms.substring(4, 6)).append("-")
            .append(ymdhms.substring(6, 8)).append(" ")
            .append(ymdhms.substring(8, 10)).append(":")
            .append(ymdhms.substring(10, 12)).append(":")
            .append(ymdhms.substring(12, 14)).toString();
    }

    /**
     * 현재날짜의 요일 문자열을 반환한다
     * 
     * @return String 요일
     * 
     */
    public static String getYoil() {
        Calendar cal = Calendar.getInstance(); // 현재 날짜/시간 등의 각종 정보 얻기
        // 1     2     3     4     5     6     7
        final String[] week = { "일", "월", "화", "수", "목", "금", "토" };
        return week[cal.get(Calendar.DAY_OF_WEEK) - 1] + "요일";

    }

    /**
     * 년월일시분초 문자열을 long 형 Timestamp로 바꾸어 변환한다.
     * 
     * @param ymdhms 년월일시분초 문자열
     * @return long TimeStamp 값
     */
    public static long toTimeMillisSec(String ymdhms) {
        GregorianCalendar cal = new GregorianCalendar(Integer.parseInt(ymdhms
            .substring(0, 4)),
            Integer.parseInt(ymdhms.substring(4, 6)) - 1,
            Integer.parseInt(ymdhms.substring(6, 8)),
            Integer.parseInt(ymdhms.substring(8, 10)),
            Integer.parseInt(ymdhms.substring(10, 12)),
            Integer.parseInt(ymdhms.substring(12, 14)));
        return cal.getTimeInMillis();
    }

    /**
     * 현재 년월일 문자열을 얻는다.
     * 
     * @return String 현재 년월일 문자열
     */
    public static String getStrDay() {
        GregorianCalendar cal = new GregorianCalendar();
        return new StringBuilder(8)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .toString();
    }

    /**
     * long형 timestamp를 년월일 형태의 문자열로 변환한다.
     * 
     * @param stamps timestamp
     * @return String 년월일 문자열
     */
    public static String getStrDay(long stamps) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(stamps);
        return new StringBuilder(8)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .toString();
    }

    /**
     * 현재 년월일시 문자열을 반환한다.
     * 
     * @return String 년월일시 문자열
     */
    public static String getStrHour() {
        GregorianCalendar cal = new GregorianCalendar();
        return new StringBuilder(10)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.HOUR_OF_DAY), "00"))
            .toString();
    }

    /**
     * long형 timestamp를 년월일시 형태의 문자열로 변환한다.
     * 
     * @param stamps timestamp
     * @return String 년월일시 문자열
     */
    public static String getStrHour(long stamps) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(stamps);
        return new StringBuilder(10)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.HOUR_OF_DAY), "00"))
            .toString();
    }

    /**
     * 현재 년월일시분 문자열을 반환한다.
     * 
     * @return String 년월일시분 문자열
     */
    public static String getStrMin() {
        GregorianCalendar cal = new GregorianCalendar();
        return new StringBuilder(12)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.HOUR_OF_DAY), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.MINUTE), "00"))
            .toString();
    }

    /**
     * long형 timestamp를 년월일시분 형태의 문자열로 변환한다.
     * 
     * @param stamps timestamp
     * @return 년월일시분 문자열
     */
    public static String getStrMin(long stamps) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(stamps);
        return new StringBuilder(12)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.HOUR_OF_DAY), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.MINUTE), "00"))
            .toString();
    }

    /**
     * 현재 년월일시분초 문자열을 반환한다.
     * 
     * @return String 년월일시분초 문자열
     */
    public static String getStrSec() {
        GregorianCalendar cal = new GregorianCalendar();
        return new StringBuilder(14)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.HOUR_OF_DAY), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.MINUTE), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.SECOND), "00"))
            .toString();
    }

    /**
     * long형 timestamp를 년월일시분초 형태의 문자열로 반환한다.
     * 
     * @param stamps timestamp
     * @return String 년월일시분초 문자열
     */
    public static String getStrSec(long stamps) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(stamps);
        return new StringBuilder(14)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.HOUR_OF_DAY), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.MINUTE), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.SECOND), "00"))
            .toString();
    }

}
