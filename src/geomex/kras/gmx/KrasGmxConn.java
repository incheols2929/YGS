package geomex.kras.gmx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;


public class KrasGmxConn {

	private static String CONN_IP = "";
	private static int TIMEOUT = 3000;
	
	private static String PROXY_URL = "";
	private static boolean PROXY_USE = false;
	
	static {
		HashMap<String, String> cfg = KrasGmxConnCfg.getCfg(); //실제 운용될 ip불러오기
		
		CONN_IP = cfg.get("ip");
		if ( !cfg.get("port").equals("80") ) CONN_IP += ( ":" + cfg.get("port") );
		TIMEOUT = Integer.valueOf(cfg.get("timeout")); 
		
		PROXY_URL = cfg.get("proxy_url").trim();
		if ( cfg.get("proxy_use").toLowerCase().equals("true") ) PROXY_USE = true;
		
	}
	
	public enum SVC{

		GetBldgInfo("GetBldgInfo"),//건축물간략정보
		GetBldgList("GetBldgList"),//건축물대장 리스트를xml형태로 받음
		GetDjyexpos("GetDjyexpos"),//전유부의 정보 들고옴
		GetDjyrecaptitle("GetDjyrecaptitle"),//총괄표제부 들고옴
		GetDjytitle("GetDjytitle"),//표제부 정보를 가지고옴
		GetJeonyubldg("GetJeonyubldg"),//전유부의 호명칭과 키 값을 가지고 
		GetJigaInfo("GetJigaInfo"), //공시지가 정보를 얻음
		GetLandBldgChk("GetLandBldgChk"),
		GetLandInfo("GetLandInfo"),//토지 기본정보
		GetShareInfo("GetShareInfo"),//공유지 연명부 목록을 얻음
		GetTojiDaejangPrint("GetTojiDaejangPrint"),
		GetTojiDaejangPrint2("GetTojiDaejangPrint2"),
		GetUseZoneList("GetUseZoneList");

		private final String value;
		
		SVC(String val) {
			this.value = val;
		}

		public static SVC lookup(String val) {
			for( SVC svcType : SVC.values() ) {
				if ( svcType.value().equals(val) ) return svcType;
			}
			return null;
		}

		public String value() {
			return value;
		}
	}
	
	
	public String getData(SVC svc, String pnu) {
		return getData(svc, pnu, null);
	}

	public String getData(SVC svc, String pnu, String bnoOrUser) {
		
		StringBuilder sb = new StringBuilder();    

		HttpURLConnection httpConn = null;
		LinkedHashMap<String, String> paramMap = new LinkedHashMap<String, String>();
		
		String urlStr = "";
		
		if ( !PROXY_USE ) {
			urlStr = "http://" + CONN_IP + "/kras/svc/" + svc.value();

			paramMap.put("pnu", pnu);
			
		} else { //proxy서버와 연결이 성공하면 이메시지가 뜸
			System.out.println("======================================");
			System.out.println("===>>>    KrasGmxConn Use Proxy !!!   ");
			System.out.println("======================================");
			
			urlStr = PROXY_URL;
			
			paramMap.put("svc", svc.value());
			paramMap.put("pnu", pnu);
			
			TIMEOUT *= 2;
		}
		if ( bnoOrUser != null ) {
			if ( svc.equals(SVC.GetTojiDaejangPrint)
					|| svc.equals(SVC.GetTojiDaejangPrint) )
			{
				String user = "";
				try {
					user = URLEncoder.encode(bnoOrUser, "UTF-8");
				} catch (Exception e) {}
				paramMap.put("user", user);
			} else {
				paramMap.put("bno", bnoOrUser);
			}
		}

		
		try {

			URL svrUrl = new URL(urlStr);

		    httpConn = (HttpURLConnection)svrUrl.openConnection();
		    httpConn.setConnectTimeout(TIMEOUT);
		    httpConn.setRequestMethod("POST");
		    httpConn.setDoOutput(true);
		    httpConn.connect();
					
			OutputStream os = (OutputStream)httpConn.getOutputStream();
			os.write(map2param(paramMap).getBytes());
			os.flush();
			os.close();

			BufferedReader br = new BufferedReader( new InputStreamReader( httpConn.getInputStream(), "UTF-8" ) );
			String line;
			while ( (line = br.readLine()) != null ) {
				sb.append(line);
			}

		} 	  
		catch (Exception e) {
			e.printStackTrace();
		} finally {
		    if (httpConn != null) httpConn.disconnect();
		}
		
		
		return sb.toString();
	}

	private static String map2param(Map<String, String> map) {
		String tmp = "";
		String kvSep = "=";
		String elSep = "&";
		for ( String key : map.keySet() ) {
			tmp += ( key + kvSep + map.get(key) + elSep);
		}
		return tmp.substring(0, (tmp.length() - elSep.length()));
	}
	
}
