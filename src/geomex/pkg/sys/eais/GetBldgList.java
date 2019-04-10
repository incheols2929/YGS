package geomex.pkg.sys.eais;

import geomex.kras.gmx.KrasGmxConn;
import geomex.kras.gmx.KrasGmxConn.SVC;
import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * 건축물대장 리스트를 xml형태로 받는다.
 */
public class GetBldgList implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {

        String pnu = kvp.get("PNU");

        StringBuilder sb = new StringBuilder();
        try {
        	KrasGmxConn kgc = new KrasGmxConn();
            sb.append(kgc.getData(SVC.GetBldgList, pnu));
        } catch (Exception e) {
            WebUtil.sendError(res, "NoData");
        }

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }
}
