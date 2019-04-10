package geomex.kras.gmx;

import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class KrasGmxConnCfg extends HttpServlet {

	private static final long serialVersionUID = 3248461299365444470L;

	
	private static final String CFG_FILE = "/WEB-INF/KrasGmxConn.cfg";
    private static HashMap<String, String> cfg = new HashMap<String, String>();

    
    public KrasGmxConnCfg() {
        super();
	}

    @Override
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ServletContext context = conf.getServletContext();

        FileReader fr = null;
        try {
            context.log("KrasGmxConn Config Load...");

            fr = new FileReader(context.getRealPath(CFG_FILE));
            Properties prop = new Properties();
            prop.load(fr);
            for (Enumeration<?> e = prop.propertyNames(); e.hasMoreElements();) {
                String key = (String) e.nextElement();
                String val = prop.getProperty(key).trim();
                if ( val == null ) val = "";
                cfg.put(key, val);
                context.log(key+" => "+val);
            }
            context.log("KrasGmxConn Config Loaded!!");
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	if ( fr != null ) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
    }

	public static HashMap<String, String> getCfg() {
		return cfg;
	}
    
    
}
