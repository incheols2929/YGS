/**
 * 
 */
package geomex.svc.webctrl;

import geomex.utils.StrUtil;

/**
 * <PRE>
 * 파일명   : ErrorCode.java
 * 파일설명 : 요청처리중 에러 메세지 정의
 * 수정이력 : 
 *       2013. 6. 19.  김경호  : 최초작성
 * 
 * </PRE>
 * 
 */
public class ErrorCode {
    private static final String DEFAULT_MSG = "유효하지 않는 요청정보입니다.";

    // 요청 파라메터에러를 포함한 처리중 에러
    public static final ErrorCode INVALID_REQUEST = new ErrorCode("InvalidRequest");
    // 권한없는 서비스이용 및 허용되지 않는 접근경로 접근 등
    public static final ErrorCode INVALID_ACCESS = new ErrorCode("InvalidAccess");
    // 서버요청 처리중 오류가 발생
    public static final ErrorCode HANDLE_ERROR = new ErrorCode("HandleError");

    private String code; // 에러코드
    private String msg; // 에러메세지

    /**
     * 생성자
     * 
     * @param code 에러코드
     */
    public ErrorCode(String code) {
        this(code, DEFAULT_MSG);
    }

    /**
     * 생성자
     * 
     * @param code
     * @param msg
     */
    public ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * getter
     * 
     * @return 코드
     */
    public String getCode() {
        return code;
    }

    /**
     * getter
     * 
     * @return 코드
     */
    public String getMsg() {
        return msg;
    }

    ///**
    // * 에러정보를 XML문서로 얻는다.
    // * 
    // * @return XML문서
    // */
    //public String toXML() {
    //    StringBuilder sb = new StringBuilder(100);
    //    sb.append(WebUtil.XML_HEADER);
    //    sb.append("<error>");
    //    sb.append("<code>").append(code).append("</code>");
    //    sb.append("<msg>").append(StrUtil.chkNull(msg)).append("</msg>");
    //    sb.append("</error>");
    //    return sb.toString();
    //}

    /**
     * 에러정보를 JSON문자열로 얻는다.
     * 
     * @return
     */
    public String toJSON() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("{\"error\":").append("\"").append(code).append("\"");
        sb.append(",").append("\"msg\":").append("\"").append(StrUtil.chkNull(msg)).append("\"");
        sb.append("}");
        return sb.toString();
    }

    // public static void main(String args[]) {
    //     ErrorCode c = ErrorCode.INVALID_REQUEST;
    //     System.out.println(c.toJSON());
    // }
}
