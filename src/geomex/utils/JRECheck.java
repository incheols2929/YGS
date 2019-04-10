/**
 * 
 */
package geomex.utils;

import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
       
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTML;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.parser.ParserDelegator;
       
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
       
import org.json.*;

/**
 * <PRE>
 * 파일명   : JRECheck.java
 * 파일설명 : 
 * 수정이력 : 
 *       2014. 1. 15.  이규하  : 최초작성
 * </PRE>
 *
 * @author 이규하
 *
 */
public class JRECheck implements Handler {

	private boolean verInfoChk = false;

	private JSONObject rtnInfo = null;


	private class CallbackHandler extends HTMLEditorKit.ParserCallback {
	    
	    public void handleText(char[] data, int pos) {
			if ( verInfoChk ) {
				String[] javaVer = (new String(data)).trim().split(" ");
				try {
					rtnInfo.put("ver", javaVer[1]);
					rtnInfo.put("update", javaVer[3]);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    }

	    public void handleStartTag(HTML.Tag tag, MutableAttributeSet a, int pos) {
			//System.out.println(tag.toString());
	 		if ( tag == HTML.Tag.STRONG ) {
	 			verInfoChk = true;
	 		}
	    }
	    
		public void handleEndTag(HTML.Tag tag, int pos) {
			//System.out.println(tag.toString());
	 		if ( tag == HTML.Tag.STRONG ) {
	 			verInfoChk = false;
	 		}
		}

		public void handleError(String errorMsg, int pos) {
		}
	}

	/**
	 * 
	 */
	public JRECheck() {

		String urlString = "http://www.java.com/ko/download/index.jsp";

		rtnInfo = new JSONObject();
		
		URL url = null;
		try {

			url = new URL(urlString);

			HttpURLConnection httpUrlCon = (HttpURLConnection)url.openConnection(); 
			InputStreamReader reader = new InputStreamReader(httpUrlCon.getInputStream(),"UTF-8");
			
		    new ParserDelegator().parse(reader, new CallbackHandler(), true);
			
			httpUrlCon.disconnect();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String getVersion() {
		return rtnInfo.toString();
	}

	@Override
	public void perform(Map<String, String> kvp, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		JRECheck jre = new JRECheck();
		WebUtil.sendJson(res, jre.getVersion());
	}

}
