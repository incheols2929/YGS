package geomex.pkg.srch;

import geomex.svc.webctrl.Handler;
import geomex.svc.webctrl.WebUtil;
import geomex.pkg.srch.ConditionSearch;
import geomex.pkg.srch.ConditionSearchBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * 시도에 해당하는 시군구 목록을 얻는다.
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class GetSGG2 implements Handler {

    @Override
    public void perform(Map<String, String> kvp, HttpServletResponse res)
        throws ServletException, IOException {
        // TODO 처리 모듈 구현하기
        // 아래는 테스트
        //String sido = kvp.get("SI");
        //String sido2 = kvp.get("RI");

        ConditionSearchBean CS = new ConditionSearchBean();
        ArrayList<ConditionSearch> cslist = CS.getSggAll2();  //선택한 시군구 전체 목록을 들고옴
        
        StringBuilder sb = new StringBuilder();

        sb.append("<sgg-list>");

        for (int i = 0; i < cslist.size(); i++) {//불러온DB테이블에서 하나씩 증가하며 원하는 값을 찾는다
            sb.append("<시군구>"); //gws.condSrch.js에 있는 기능 여기에 정의 97번 줄부터 
            sb.append("<시군구코드>").append(cslist.get(i).getCode()).append("</시군구코드>");
            sb.append("<시군구명>").append(cslist.get(i).getValue()).append("</시군구명>");
            sb.append("</시군구>");
          //여기를 바꿔주면 gws.condSrch.js도 바꿔줘야 함!그전에 DB정보부터 해당 지역에 맞게 교체 되어야함
        }
        sb.append("</sgg-list>");

        WebUtil.sendNoneHeaderXML(res, sb.toString()); //결과 xml정보를 클라이언트로 전송한다.
        //WebUtil.sendError(res, Const.ERR_INVALID_REQUEST);
    }
}
