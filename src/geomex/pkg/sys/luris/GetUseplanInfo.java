package geomex.pkg.sys.luris;

import geomex.kras.gmx.KrasGmxConn;
import geomex.kras.gmx.KrasGmxConn.SVC;
import geomex.pkg.usr.UseLogBean;
import geomex.svc.handler.Code;
import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;
import geomex.utils.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * 행위제한법령정보를 가지고온다.
 * 
 * @author 이주영
 * @return XML
 */
public class GetUseplanInfo implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO 처리 모듈 구현하기

        StringBuilder sb = new StringBuilder();

        String pnu = kvp.get("PNU");

        try {
        	KrasGmxConn kgc = new KrasGmxConn();
            sb.append(kgc.getData(SVC.GetUseZoneList, pnu));
        } catch (Exception e) {
        	WebUtil.sendError(res, "NoData");
        }
		
        WebUtil.sendNoneHeaderXML(res, sb.toString());
    }
}
