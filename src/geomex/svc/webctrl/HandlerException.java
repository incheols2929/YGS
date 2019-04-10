/**
 * 
 */
package geomex.svc.webctrl;

/**
 * <PRE>
 * 파일명   : HandlerException.java
 * 파일설명 : HandlerException 클래스 
 * 수정이력 : 
 *       2013. 7. 19.  김경호  : 최초작성
 * 
 * </PRE>
 * 
 */
public class HandlerException extends Exception {
    private static final long serialVersionUID = -2322917932029310575L;
    private String msg = "HandlerException";

    public HandlerException() {}

    public HandlerException(String msg) {
        super();
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
