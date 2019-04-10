package geomex.pkg.layer;

import geomex.svc.handler.Code;
import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * 기본스타일을 가지고온다.
 */
public class GetBaseStyle implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO Auto-generated method stub

        StringBuilder sb = new StringBuilder();
        String geoStyle = Code.getGeovletStyle();

        try {
            sb.append(geoStyle);
        } catch (Exception e) {
            // TODO: handle exception
        }

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }

}
