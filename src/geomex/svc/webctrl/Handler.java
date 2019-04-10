/**
 * 
 */
package geomex.svc.webctrl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * <PRE>
 * 파일명    : Handler.java
 * 파일설명 : 클라이언트 요청을 처리하는 Handler 인터페이스
 *               ctrl.properties에 등록된 요청별 처리기를 호출하여 처리한다. 
 *               Request문의 SVC이름이 요청구분자이다.
 * 수정이력 : 
 *       2013. 6. 19.  김경호  : 최초작성
 * 
 * </PRE>
 * 
 */
public interface Handler {

    /**
     * 클라이언트 요청을 처리하는 함수
     * 
     * @param json 요청 인자를 저장한 JSONObject
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws HandlerException
     */
    public void perform(Map<String, String> kvp,
        HttpServletResponse res) throws ServletException, IOException;
}
