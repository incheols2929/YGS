package geomex.pkg.sys.lris;

import geomex.kras.gmx.KrasGmxConn;
import geomex.kras.gmx.KrasGmxConn.SVC;
import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * 공유지연명부 목록을 얻는다.
 */
public class GetShareInfo implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {

        String pnu = kvp.get("PNU");

        StringBuilder sb = new StringBuilder();

        try {
        	KrasGmxConn kgc = new KrasGmxConn();
            sb.append(kgc.getData(SVC.GetShareInfo, pnu));
        } catch (Exception e) {
        	WebUtil.sendError(res, "NoData");
        }

        WebUtil.sendNoneHeaderXML(res, sb.toString());

    }

}
