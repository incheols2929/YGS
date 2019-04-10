package geomex.pkg.sys.eais;

import geomex.kras.gmx.KrasGmxConn;
import geomex.kras.gmx.KrasGmxConn.SVC;
import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * 전유부의 호명칭과 키값을 가지고온다.
 */
public class GetJeonyubldg implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {

        String pnu = kvp.get("PNU");
        String blnm = URLDecoder.decode(kvp.get("BLNM"), "utf-8");

        StringBuilder sb = new StringBuilder();

        try {
        	KrasGmxConn kgc = new KrasGmxConn();
            sb.append(kgc.getData(SVC.GetJeonyubldg, pnu, blnm));
        } catch (Exception e) {
            WebUtil.sendError(res, "NoData");
        }

        WebUtil.sendNoneHeaderXML(res, sb.toString());
    }
}
