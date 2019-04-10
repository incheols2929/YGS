package geomex.pkg.srch;

import geomex.pkg.sys.luris.Luris;
import geomex.svc.handler.Code;
import geomex.svc.webctrl.Const;
import geomex.utils.Utils;
import geomex.utils.db.DBHandler;

import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConditionSearchBean {

	//dept_cd로 dept_nm 가져오기(부서관리코드로 전체기관명 찾기)
    public String getDeptName(String dept_cd) {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        String dept_nm = "";

        sb.append(" select dept_nm  ");
        sb.append(" from mt_dept_desc  ");
        sb.append(" where dept_cd = ?   ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.setString(1, dept_cd.substring(0, 3) + "0000");
            handler.execute();
            if (handler.next()) {
                dept_nm = handler.getString("dept_nm");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return dept_nm;
    }

    //법정동코드 가져오기
    public String getBjd_cd(String bjd_nm) {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        String bjd_cd = "";

        sb.append(" select bjd_cd  ");
        sb.append(" from mt_bjd_cd  ");
        sb.append(" where bjd_nm = ?   ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.setString(1, bjd_nm);
            handler.execute();
            if (handler.next()) {
                bjd_cd = handler.getString("bjd_cd");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return bjd_cd;
    }

    //시군구 dept_cd 사용
    public ArrayList<ConditionSearch> getSggCode(String code)
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        sb.append(" select bjd_cd  as code, sgg_nm as value ");
        sb.append(" from mt_bjd_cd  ");
        sb.append(" where 1 =1   ");
        sb.append(" and sgg_nm <> '' and emd_nm = '' and ri_nm = ''  ");
        sb.append(" and bjd_cd = ? ");
        sb.append(" order by bjd_cd  ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.setString(1, code);
            handler.execute();
            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString("code");
                sm.value = handler.getString("value");
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return list;

    }
//=============농업기반=======================
    //시설물
    public ArrayList<ConditionSearch> getFacilityAll()
    {
        DBHandler handler = new DBHandler();//DB연결
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();
        
       /* sb.append(" select bjd_cd  as code, sgg_nm as value ");//bjd_cd필드로 찾고 sgg_num필드로 화면에 출력
        sb.append(" from ag_facil_reservoir  ");//테이블 mt_bjd_cd부터
        sb.append(" where 1 =1   "); //검색할 조건 (모든 행이 조회됨)
        sb.append(" and sgg_nm <> '' and emd_nm = '' and ri_nm = ''  "); //찾는 데이터만 가져오기 위해
        sb.append(" and substring(bjd_cd,1,4) = '4687' "); //bjd_cd필드에서 1번째 부터 2번째 글짜가 '42'만 출력
        sb.append(" order by bjd_cd  "); //bjd_cd를 기준으로 정렬*/
        sb.append("select g2_code as code, g2_value as value,g2_value1 as value1,g2_value2 as value2 ");
        sb.append("from g2s_codeddomains ");
        sb.append("where 1=1 ");
        sb.append("and g2_domainid = '1' ");
        // sb.append("and substring(g2_code,1,3) = 'FTR' ");
        sb.append("order by g2_code");
        
        try {
            handler.open(Const.CONTEXT_NAME); //jss프로젝트 OPEN
            handler.setQuery(sb.toString()); //쿼리문 불러옴
            handler.execute();//SQL문장이 들어오면 바로실행,필드를 가리킴
            while (handler.next()) { //커서를 옮김
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString("code");
                sm.value = handler.getString("value");
                sm.value1 = handler.getString("value1");
                sm.value2 = handler.getString("value2");
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close(); //DB종료
        }

        return list;

    }
    
    public ArrayList<ConditionSearch> getSggAll2()
    {
        DBHandler handler = new DBHandler();//DB연결
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        sb.append(" select bjd_cd  as code, sgg_nm as value ");//bjd_cd필드로 찾고 sgg_num필드로 화면에 출력
        sb.append(" from mt_bjd_cd  ");//테이블 mt_bjd_cd부터
        sb.append(" where 1 =1   "); //검색할 조건 (모든 행이 조회됨)
        sb.append(" and sgg_nm <> '' and emd_nm = '' and ri_nm = ''  "); //찾는 데이터만 가져오기 위해
        sb.append(" and substring(bjd_cd,1,4) = '4687' "); //bjd_cd필드에서 1번째 부터 2번째 글짜가 '42'만 출력
        sb.append(" order by bjd_cd  "); //bjd_cd를 기준으로 정렬

        try {
            handler.open(Const.CONTEXT_NAME); //jss프로젝트 OPEN
            handler.setQuery(sb.toString()); //쿼리문 불러옴
            handler.execute();//SQL문장이 들어오면 바로실행,필드를 가리킴
            while (handler.next()) { //커서를 옮김
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString("code");
                sm.value = handler.getString("value");
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close(); //DB종료
        }

        return list;

    }
    

    //읍면동
    public ArrayList<ConditionSearch> getUmdAll2(String sggCode)
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        sb.append(" select bjd_cd as code, emd_nm as value ");
        sb.append(" from mt_bjd_cd ");
        sb.append(" where 1 =1 ");
        sb.append(" and sgg_nm <> '' and emd_nm <> '' and ri_nm = '' ");
        sb.append(" and substring(bjd_cd,1,5) = '" + sggCode.substring(0, 5) + "' ");
        sb.append(" order by code ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString("code");
                sm.value = handler.getString("value");
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //리검색
    public ArrayList<ConditionSearch> getRi2(String umdCode)
    {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        sb.append(" select bjd_cd as code, ri_nm as value ");
        sb.append(" from mt_bjd_cd ");
        sb.append(" where 1 =1 ");
        sb.append(" and sgg_nm <> '' and emd_nm <> '' and ri_nm <> '' ");
        sb.append(" and substring(bjd_cd,1,8) = '" + umdCode.substring(0, 8) + "' ");
        sb.append(" order by code ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString("code");
                sm.value = handler.getString("value");
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }
    
    public ArrayList<ConditionSearch> getTableCode(String facility)
    {
    	 DBHandler handler = new DBHandler();
    	 ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();
    	 StringBuilder sb = new StringBuilder();
    	 
    	 sb.append("select g2_code as code, g2_value1 as tables from g2s_codeddomains where 1=1 and g2_code ='"+ facility +"' ");
    	 try {
             handler.open(Const.CONTEXT_NAME);;
             handler.setQuery(sb.toString());
             handler.execute();
             while (handler.next()) {
            	 ConditionSearch cs = new ConditionSearch();
             	 cs.facility = Utils.chkNull(handler.getString("tables"));   
                 //System.out.println(NT.pp_gbn);
                 list.add(cs);
             }
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             handler.close();
         }
         return list;
     }
    
   
    /*public ArrayList<ConditionSearch> getTableCode(String facility)
    {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();
        System.out.println("===1===");
        System.out.println(facility);
        
        sb.append(" select g2_code as code, g2_value1 as tables from g2s_codeddomains where 1=1 and g2_code ='"+ facility +"'");
        
        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                ConditionSearch cs = new ConditionSearch();
                cs.facility = Utils.chkNull(handler.getString("facility"));
                System.out.println(cs.facility);
                list.add(cs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }*/

//여기까지 농업기반=======================================
///행정지원시스템=================
    //시군구
    public ArrayList<ConditionSearch> getSggAll()
    {
        DBHandler handler = new DBHandler();//DB연결
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        sb.append(" select bjd_cd  as code, sgg_nm as value ");//bjd_cd필드로 찾고 sgg_num필드로 화면에 출력
        sb.append(" from mt_bjd_cd  ");//테이블 mt_bjd_cd부터
        sb.append(" where 1 =1   "); //검색할 조건 (모든 행이 조회됨)
        sb.append(" and sgg_nm <> '' and emd_nm = '' and ri_nm = ''  "); //찾는 데이터만 가져오기 위해
        sb.append(" and substring(bjd_cd,1,4) = '4687' "); //bjd_cd필드에서 1번째 부터 2번째 글짜가 '42'만 출력
        sb.append(" order by bjd_cd  "); //bjd_cd를 기준으로 정렬

        try {
            handler.open(Const.CONTEXT_NAME); //jss프로젝트 OPEN
            handler.setQuery(sb.toString()); //쿼리문 불러옴
            handler.execute();//SQL문장이 들어오면 바로실행,필드를 가리킴
            while (handler.next()) { //커서를 옮김
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString("code");
                sm.value = handler.getString("value");
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close(); //DB종료
        }

        return list;

    }
    

    //읍면동
    public ArrayList<ConditionSearch> getUmdAll(String sggCode)
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        sb.append(" select bjd_cd as code, emd_nm as value ");
        sb.append(" from mt_bjd_cd ");
        sb.append(" where 1 =1 ");
        sb.append(" and sgg_nm <> '' and emd_nm <> '' and ri_nm = '' ");
        sb.append(" and substring(bjd_cd,1,5) = '" + sggCode.substring(0, 5) + "' ");
        sb.append(" order by code ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString("code");
                sm.value = handler.getString("value");
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //리검색
    public ArrayList<ConditionSearch> getRi(String umdCode)
    {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        sb.append(" select bjd_cd as code, ri_nm as value ");
        sb.append(" from mt_bjd_cd ");
        sb.append(" where 1 =1 ");
        sb.append(" and sgg_nm <> '' and emd_nm <> '' and ri_nm <> '' ");
        sb.append(" and substring(bjd_cd,1,8) = '" + umdCode.substring(0, 8) + "' ");
        sb.append(" order by code ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString("code");
                sm.value = handler.getString("value");
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }
    /*
     * 지번 카운트
     */
    public int getJibunTotalCnt(String sgg, String umd, String ri, String bon, String bu, String san) {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        int tCnt = 0;

        sb.append(" select count(*) as CNT from lp_pa_cbnd where 1=1 ");

        if (!"".equals(sgg)) {
            sb.append(" and substring(pnu, 1, 5) = '" + sgg.substring(0, 5) + "' ");
        }
        if (!"".equals(umd)) {
            sb.append(" and substring(pnu, 1, 8) = '" + umd.substring(0, 8) + "' ");
        }
        if (!"".equals(ri)) {
            sb.append(" and substring(pnu, 1, 10) = '" + ri.substring(0, 10) + "' ");
        }
        if (!"".equals(san)) {
            sb.append(" and substring(pnu, 11, 1) = '" + san + "' ");
        }
        if (!"".equals(bon)) {
            //sb.append(" and cast(substring(pnu, 12, 4) as  as numeric) like '%" + bon +"' ");
            sb.append(" and cast(substring(pnu, 12, 4) as numeric) = " + Integer.parseInt(bon) + " ");
        }
        if (!"".equals(bu)) {
            //sb.append(" and substring(pnu, 16, 4) like '%" + bu +"' ");
            sb.append(" and cast(substring(pnu, 16, 4) as numeric) = " + Integer.parseInt(bu) + " ");
        }

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            if (handler.next()) {
                tCnt = Integer.parseInt(Utils.chkNull(handler.getString("CNT")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return tCnt;
    }

  //농업기반시설물
    public int getJibunTotalCnt3(String dcode,String facility,String rsvnm ,String bon, String bu, String san) {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        int tCnt = 0;
        int bonNum = 0;
        int buNum = 0;
        if (bon.length() == 1) {
            bon = "000" + bon;
            bonNum = Integer.parseInt(bon); //전달받은 인자를 숫자로 바꿔서 bonNum에 저장
        } else if (bon.length() == 2) {
            bon = "00" + bon;
            bonNum = Integer.parseInt(bon); //전달받은 인자를 숫자로 바꿔서 bonNum에 저장
        } else if (bon.length() == 3) {
            bon = "0" + bon;
            bonNum = Integer.parseInt(bon); //전달받은 인자를 숫자로 바꿔서 bonNum에 저장
        }

        if (bu.length() == 1) {
            bu = "000" + bu;
            buNum = Integer.parseInt(bu); //전달받은 인자를 숫자로 바꿔서 bonNum에 저장
        } else if (bu.length() == 2) {
            bu = "00" + bu;
            buNum = Integer.parseInt(bu); //전달받은 인자를 숫자로 바꿔서 bonNum에 저장
        } else if (bu.length() == 3) {
            bu = "0" + bu;
            buNum = Integer.parseInt(bu); //전달받은 인자를 숫자로 바꿔서 bonNum에 저장
        }
        sb.append(" select distinct  ");
        sb.append(" 	case when cd.ri_nm='' then cd.sgg_nm||' '|| cd.emd_nm  ");
        sb.append("			else cd.sgg_nm||' '||cd.emd_nm||' '||cd.ri_nm  ");
        sb.append(" 	end as emd, ");
        sb.append(" 	case when substr(pnu,11,1)='2' then '산' else ''  end  || ");
        sb.append(" 		ltrim(substr(pnu,12,4),'0')  ||  ");
        sb.append(" 	case when ltrim(substr(pnu,16,4),'0')='' then '' ");
        sb.append(" 		else '-' || ltrim(substr(pnu,16,4),'0') end as jibn, pnu, ");
        sb.append(" 	case bd.buld_se_cd when '0' then '' when '1' then '지하 ' when '2' then '공중 ' else '' end ||  ");
        sb.append(" 		rd.rn || ' ' || bd.buld_mnnm ||  ");
        sb.append(" 	case  when bd.buld_slno = 0 then ''  else '-' || bd.buld_slno  end as bldg_no, ");
        sb.append("			bd.rn_cd , bd.buld_mnnm, bd.buld_slno, bd.mntn_yn, bd.lnbr_mnnm, bd.lnbr_slno ");
        sb.append(" from lp_pa_cbnd cb ");
        sb.append(" left outer join tl_spbd_buld bd on bd.sig_cd = '" + dcode.substring(0, 5) + "'  ");
        sb.append("									and bd.emd_cd ='" + dcode.substring(5, 8) + "'  ");
        sb.append("									and bd.li_cd ='" + dcode.substring(8) + "'  ");
        if (!bon.equals("")) {
            sb.append("								and bd.lnbr_mnnm = " + bonNum + " ");
        } else {
            sb.append("								and bd.lnbr_mnnm = cast(substr(cb.pnu,12,4) as numeric) ");
        }
        if (!bu.equals("")) {
            sb.append("								and bd.lnbr_slno = " + buNum + " ");
        } else {
            sb.append("								and bd.lnbr_slno = cast(substr(cb.pnu,16,4) as numeric) ");
        }
        sb.append("									and bd.mntn_yn = (case when substr(pnu,11,1)='2' then '1' else '0' end)	");
        sb.append("	left outer join  mt_bjd_cd cd  on  cd.bjd_cd= '" + dcode + "'  ");
        sb.append("	left outer join  tl_sprd_manage rd on bd.rds_man_no = rd.rds_man_no and bd.rn_cd = rd.rn_cd  ");
        sb.append(" where 1=1  ");
        if (!bon.equals("") && !bu.equals("")) {
            sb.append(" and pnu like '" + dcode + "" + san + "" + bon + "" + bu + "' ");
        } else {
            sb.append(" and pnu like '" + dcode + "" + san + "%' ");
            if (!bon.equals("")) {
                sb.append("								and cast(substring(pnu, 12, 4) as numeric) = " + bonNum + " ");
            }
            if (!bu.equals("")) {
                sb.append("								and cast(substring(pnu, 16, 4) as numeric) = " + buNum + " ");
            }
        }
        sb.append(" order by pnu ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                tCnt = (tCnt + 1);
                //tCnt  = Integer.parseInt(Utils.chkNull(handler.getString("CNT")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return tCnt;
    }
    //수정 2013-12-02
    public int getJibunTotalCnt2(String dcode, String bon, String bu, String san) {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        int tCnt = 0;
        int bonNum = 0;
        int buNum = 0;
        if (bon.length() == 1) {
            bon = "000" + bon;
            bonNum = Integer.parseInt(bon); //전달받은 인자를 숫자로 바꿔서 bonNum에 저장
        } else if (bon.length() == 2) {
            bon = "00" + bon;
            bonNum = Integer.parseInt(bon); //전달받은 인자를 숫자로 바꿔서 bonNum에 저장
        } else if (bon.length() == 3) {
            bon = "0" + bon;
            bonNum = Integer.parseInt(bon); //전달받은 인자를 숫자로 바꿔서 bonNum에 저장
        }

        if (bu.length() == 1) {
            bu = "000" + bu;
            buNum = Integer.parseInt(bu); //전달받은 인자를 숫자로 바꿔서 bonNum에 저장
        } else if (bu.length() == 2) {
            bu = "00" + bu;
            buNum = Integer.parseInt(bu); //전달받은 인자를 숫자로 바꿔서 bonNum에 저장
        } else if (bu.length() == 3) {
            bu = "0" + bu;
            buNum = Integer.parseInt(bu); //전달받은 인자를 숫자로 바꿔서 bonNum에 저장
        }

        sb.append(" select distinct  ");
        sb.append(" 	case when cd.ri_nm='' then cd.sgg_nm||' '|| cd.emd_nm  ");
        sb.append("			else cd.sgg_nm||' '||cd.emd_nm||' '||cd.ri_nm  ");
        sb.append(" 	end as emd, ");
        sb.append(" 	case when substr(pnu,11,1)='2' then '산' else ''  end  || ");
        sb.append(" 		ltrim(substr(pnu,12,4),'0')  ||  ");
        sb.append(" 	case when ltrim(substr(pnu,16,4),'0')='' then '' ");
        sb.append(" 		else '-' || ltrim(substr(pnu,16,4),'0') end as jibn, pnu, ");
        sb.append(" 	case bd.buld_se_cd when '0' then '' when '1' then '지하 ' when '2' then '공중 ' else '' end ||  ");
        sb.append(" 		rd.rn || ' ' || bd.buld_mnnm ||  ");
        sb.append(" 	case  when bd.buld_slno = 0 then ''  else '-' || bd.buld_slno  end as bldg_no, ");
        sb.append("			bd.rn_cd , bd.buld_mnnm, bd.buld_slno, bd.mntn_yn, bd.lnbr_mnnm, bd.lnbr_slno ");
        sb.append(" from lp_pa_cbnd cb ");
        sb.append(" left outer join tl_spbd_buld bd on bd.sig_cd = '" + dcode.substring(0, 5) + "'  ");
        sb.append("									and bd.emd_cd ='" + dcode.substring(5, 8) + "'  ");
        sb.append("									and bd.li_cd ='" + dcode.substring(8) + "'  ");
        if (!bon.equals("")) {
            sb.append("								and bd.lnbr_mnnm = " + bonNum + " ");
        } else {
            sb.append("								and bd.lnbr_mnnm = cast(substr(cb.pnu,12,4) as numeric) ");
        }
        if (!bu.equals("")) {
            sb.append("								and bd.lnbr_slno = " + buNum + " ");
        } else {
            sb.append("								and bd.lnbr_slno = cast(substr(cb.pnu,16,4) as numeric) ");
        }
        sb.append("									and bd.mntn_yn = (case when substr(pnu,11,1)='2' then '1' else '0' end)	");
        sb.append("	left outer join  mt_bjd_cd cd  on  cd.bjd_cd= '" + dcode + "'  ");
        sb.append("	left outer join  tl_sprd_manage rd on bd.rds_man_no = rd.rds_man_no and bd.rn_cd = rd.rn_cd  ");
        sb.append(" where 1=1  ");
        if (!bon.equals("") && !bu.equals("")) {
            sb.append(" and pnu like '" + dcode + "" + san + "" + bon + "" + bu + "' ");
        } else {
            sb.append(" and pnu like '" + dcode + "" + san + "%' ");
            if (!bon.equals("")) {
                sb.append("								and cast(substring(pnu, 12, 4) as numeric) = " + bonNum + " ");
            }
            if (!bu.equals("")) {
                sb.append("								and cast(substring(pnu, 16, 4) as numeric) = " + buNum + " ");
            }
        }
        sb.append(" order by pnu ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                tCnt = (tCnt + 1);
                //tCnt  = Integer.parseInt(Utils.chkNull(handler.getString("CNT")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return tCnt;
    }

    /*
     * 새주소 지번 카운트
     */
    public int getJibunNewTotalCnt(String dcode, String bon, String bu, String san) {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        int tCnt = 0;

        sb.append(" select count(*) as CNT ");
        sb.append(" from ");
        sb.append(" v_tl_spbd_buld t1,  ");
        sb.append(" v_tl_sprd_manage t2 ");
        sb.append(" where 1=1 ");
        sb.append(" and t1.sig_cd = t2.sig_cd  ");
        sb.append(" and t1.rds_man_no = t2.rds_man_no  ");
        sb.append(" and t1.rn_cd = t2.rn_cd  ");
        if (!"".equals(dcode)) {
            sb.append(" and t1.sig_cd||t1.emd_cd||t1.li_cd = '" + dcode + "' ");
        }
        if (!"".equals(san)) {
            sb.append(" and case t1.mntn_yn when '0' then '1' when '1' then '2' end = '" + san + "' ");
        }
        if (!"".equals(bon)) {
            sb.append(" and t1.lnbr_mnnm = '" + bon + "' ");
        }
        if (!"".equals(bu)) {
            sb.append(" and t1.lnbr_slno = '" + bu + "' ");
        }

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            if (handler.next()) {
                tCnt = Integer.parseInt(Utils.chkNull(handler.getString("CNT")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return tCnt;
    }

    //수정 2012-06-27
    public int getJibunNewTotalCnt2(String dcode, String bon, String bu, String san) {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        int tCnt = 0;

        sb.append(" select distinct buld_se_cd, rd_se_lbl, sig_cd, emd_cd, li_cd , mntn_yn, lpad(cast(lnbr_mnnm as character varying) , 4 , '0') as lnbr_mnnm, ");
        sb.append(" lpad(cast(lnbr_slno as character varying), 4 , '0') as lnbr_slno , ");
        sb.append(" rd_addr, ln_addr, buld_nm ");
        sb.append(" from ");
        sb.append(" v_tl_spbd_buld ");
        sb.append(" where 1=1 ");
        if (!"".equals(dcode)) {
            sb.append(" and substring(bd_mgt_sn, 1, 10) = '" + dcode + "' ");
        }
        if (!"".equals(san)) {
            sb.append(" and case mntn_yn when '0' then '1' when '1' then '2' end = '" + san + "' ");
        }
        if (!"".equals(bon)) {
            sb.append(" and lnbr_mnnm = '" + bon + "' ");
        }
        if (!"".equals(bu)) {
            sb.append(" and lnbr_slno = '" + bu + "' ");
        }

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                tCnt = (tCnt + 1);
                //tCnt  = Integer.parseInt(Utils.chkNull(handler.getString("CNT")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return tCnt;
    }

    //지번검색
    public ArrayList<ConditionSearch> getSrchjibun(String sgg, String umd, String ri, String bon, String bu, String san,
        String snum, String pagenum)
    {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        int sn = Integer.parseInt(snum);
        int en = Integer.parseInt(pagenum);

        sb.append(" select pnu, jibun from lp_pa_cbnd where 1=1 ");
        if (!"".equals(sgg)) {
            sb.append(" and substring(pnu, 1, 5) = '" + sgg.substring(0, 5) + "' ");
        }
        if (!"".equals(umd)) {
            sb.append(" and substring(pnu, 1, 8) = '" + umd.substring(0, 8) + "' ");
        }
        if (!"".equals(ri)) {
            sb.append(" and substring(pnu, 1, 10) = '" + ri.substring(0, 10) + "' ");
        }
        if (!"".equals(san)) {
            sb.append(" and substring(pnu, 11, 1) = '" + san + "' ");
        }
        if (!"".equals(bon)) {
            sb.append(" and cast(substring(pnu, 12, 4) as numeric) = " + Integer.parseInt(bon) + " ");
        }
        if (!"".equals(bu)) {
            sb.append(" and cast(substring(pnu, 16, 4) as numeric) = " + Integer.parseInt(bu) + " ");
        }

        sb.append(" order by pnu asc ");
        sb.append(" LIMIT " + en + " OFFSET " + (sn - 1));

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            while (handler.next()) {
                ConditionSearch cs = new ConditionSearch();
                cs.pnu = Utils.chkNull(handler.getString("pnu"));
                cs.jibun = Utils.chkNull(handler.getString("jibun"));
                list.add(cs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }
    //시설물지번수정
    /**
     * @author 
     * @작성일:2016. 9. 28.
     * @작성자:신인철
     * @이메일:zlola@naver.com
     * @변경이력: v1
     * @Method설명: 시설물에 대한 정보
     */
    public ArrayList<ConditionSearch> getSrchjibun3(String dcode,String ftr_nm,String tbl_nm, String lyr_id) {
    	
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();
       /* sb.append("select FTR_IDN,FTR_CDE,MNG_CDE,PNU,JUSO,FTR_NM ");
        sb.append("from " +facility+"  ");
        sb.append("where ri_cde = '"+ dcode +"' and ftr_nm like '%" + ftr_nm +"%' order by pnu ");
        */
        sb.append(" select tbl.ftr_cde as ftr_cde,tbl.ri_cde as ri_cde,tbl.juso as juso,tbl.ftr_nm as ftr_nm,tbl.ftr_idn as ftr_idn,   ");
        sb.append(" dsc.lyr_id as lyr_id,  ");
        sb.append(" dsc.lyr_nm as lyr_nm,  "); 
        sb.append(" count(tbl._gid) as cnt, array_to_string(array_agg(tbl._gid), ',') as _gid ");
        sb.append(" from " + tbl_nm + " tbl, "); 
        sb.append(" mt_lyr_desc dsc ");   
        sb.append(" where dsc.lyr_id ='" + lyr_id + "' "); 
        sb.append(" and dsc.tbl_id='" + tbl_nm + "' ");  
        /*sb.append(" and tbl.ri_cde='" + ri_code + "%' ");*/
        sb.append(" and tbl.ri_cde like '" + dcode + "%' ");
        sb.append(" and ftr_nm like '%" + ftr_nm +"%' ");
        sb.append(" group by dsc.lyr_id, dsc.lyr_nm, tbl.ftr_cde, tbl.ri_cde,tbl.juso,tbl.ftr_nm,tbl.ftr_idn ");
        //System.out.println("시설물 정보 쿼리" + sb.toString());
        
        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                ConditionSearch cs = new ConditionSearch();
                cs.ftr_cde = Utils.chkNull(handler.getString("ftr_cde"));
                cs.ri_cde = Utils.chkNull(handler.getString("ri_cde"));
                cs.juso = Utils.chkNull(handler.getString("juso"));
                cs.ftr_nm = Utils.chkNull(handler.getString("ftr_nm"));
                cs.ftr_idn = Utils.chkNull(handler.getString("ftr_idn"));
                cs.lyr_id = handler.getString("lyr_id");
                cs.lyr_nm = handler.getString("lyr_nm");
                cs.cnt = handler.getInt("cnt");
                cs.gid = handler.getString("_gid");
                list.add(cs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }
 ///저수지 관리기관을 찾음
/*public ArrayList<ConditionSearch> getSrchFacilityGB(String mngcde) {
    	
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();
        
        sb.append("select FTR_IDN,FTR_CDE,MNG_CDE,PNU,JUSO,FTR_NM ");
        sb.append("from g2s_codeddomains  ");
        sb.append("where ri_cde = '"+ dcode +"' and ftr_nm like '%" + ftr_nm +"%' order by pnu ");
        
        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                ConditionSearch cs = new ConditionSearch();
                cs.ftr_idn = Utils.chkNull(handler.getString("ftr_idn"));
                cs.ftr_cde = Utils.chkNull(handler.getString("ftr_cde"));
                cs.mng_cde = Utils.chkNull(handler.getString("mng_cde"));
                cs.pnu = Utils.chkNull(handler.getString("pnu"));
                cs.juso = Utils.chkNull(handler.getString("juso"));           
                cs.ftr_nm = Utils.chkNull(handler.getString("ftr_nm"));
                list.add(cs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }*/


    //지번검색 수정 2012-06-28
    public ArrayList<ConditionSearch> getSrchjibun2(String dcode, String bon, String bu, String san, String snum, String pagenum) {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        int sn = Integer.parseInt(snum);
        int en = Integer.parseInt(pagenum);
        int bonNum = 0;
        int buNum = 0;
        if (bon.length() == 1) {
            bon = "000" + bon;
            bonNum = Integer.parseInt(bon);
        } else if (bon.length() == 2) {
            bon = "00" + bon;
            bonNum = Integer.parseInt(bon);
        } else if (bon.length() == 3) {
            bon = "0" + bon;
            bonNum = Integer.parseInt(bon);
        }

        if (bu.length() == 1) {
            bu = "000" + bu;
            buNum = Integer.parseInt(bu);
        } else if (bu.length() == 2) {
            bu = "00" + bu;
            buNum = Integer.parseInt(bu);
        } else if (bu.length() == 3) {
            bu = "0" + bu;
            buNum = Integer.parseInt(bu);
        }

        sb.append(" select distinct  ");
        sb.append(" 	case when cd.ri_nm='' then cd.sgg_nm||' '|| cd.emd_nm  ");
        sb.append("			else cd.sgg_nm||' '||cd.emd_nm||' '||cd.ri_nm  ");
        sb.append(" 	end as emd, ");
        sb.append(" 	case when substr(pnu,11,1)='2' then '산' else ''  end  || ");
        sb.append(" 		ltrim(substr(pnu,12,4),'0')  ||  ");
        sb.append(" 	case when ltrim(substr(pnu,16,4),'0')='' then '' ");
        sb.append(" 		else '-' || ltrim(substr(pnu,16,4),'0') end as jibn, pnu, ");
        sb.append(" 	case bd.buld_se_cd when '0' then '' when '1' then '지하 ' when '2' then '공중 ' else '' end ||  ");
        sb.append(" 		rd.rn || ' ' || bd.buld_mnnm ||  ");
        sb.append(" 	case  when bd.buld_slno = 0 then ''  else '-' || bd.buld_slno  end as bldg_no, ");
        sb.append("			bd.rn_cd , bd.buld_mnnm, bd.buld_slno, bd.mntn_yn, bd.lnbr_mnnm, bd.lnbr_slno ");
        sb.append(" from lp_pa_cbnd cb ");
        sb.append(" left outer join tl_spbd_buld bd on bd.sig_cd = '" + dcode.substring(0, 5) + "'  ");
        sb.append("									and bd.emd_cd ='" + dcode.substring(5, 8) + "'  ");
        sb.append("									and bd.li_cd ='" + dcode.substring(8) + "'  ");
        if (!bon.equals("")) {
            sb.append("								and bd.lnbr_mnnm = " + bonNum + " ");
        } else {
            sb.append("								and bd.lnbr_mnnm = cast(substr(cb.pnu,12,4) as numeric) ");
        }
        if (!bu.equals("")) {
            sb.append("								and bd.lnbr_slno = " + buNum + " ");
        } else {
            sb.append("								and bd.lnbr_slno = cast(substr(cb.pnu,16,4) as numeric) ");
        }
        /*
         * if(!san.equals("")){
         * sb.append("								and bd.mntn_yn = '"+san+"'	"); }else{ }
         */
        sb.append("								and bd.mntn_yn = (case when substr(pnu,11,1)='2' then '1' else '0' end)	");
        sb.append("	left outer join  mt_bjd_cd cd  on  cd.bjd_cd= '" + dcode + "'  ");
        sb.append("	left outer join  tl_sprd_manage rd on bd.rds_man_no = rd.rds_man_no and bd.rn_cd = rd.rn_cd  ");
        sb.append(" where 1=1  ");
        if (!bon.equals("") && !bu.equals("")) {
            sb.append(" and pnu like '" + dcode + "" + san + "" + bon + "" + bu + "' ");
        } else {
            /*
             * if(!san.equals("")){
             * sb.append("								and substring(pnu, 11, 1) = '"+san+"'	"); }
             */
            sb.append(" and pnu like '" + dcode + "" + san + "%' ");
            if (!bon.equals("")) {
                sb.append("								and cast(substring(pnu, 12, 4) as numeric) = " + bonNum + " ");
            }
            if (!bu.equals("")) {
                sb.append("								and cast(substring(pnu, 16, 4) as numeric) = " + buNum + " ");
            }
        }
        sb.append(" order by pnu ");
        sb.append(" LIMIT " + en + " OFFSET " + (sn - 1));

        /*
         * sb.append(""); sb.append(""); sb.append(" where 1=1 ");
         * if(!"".equals(dcode)){ sb.append(" and substring(t1.pnu, 1, 10) = '"
         * + dcode +"' "); } if(!"".equals(san)){
         * sb.append(" and substring(t1.pnu, 11, 1) = '" + san +"' "); }
         * if(!"".equals(bon)){
         * sb.append(" and cast(substring(t1.pnu, 12, 4) as numeric) = " +
         * Integer.parseInt(bon) +" "); } if(!"".equals(bu)){
         * sb.append(" and cast(substring(t1.pnu, 16, 4) as numeric) = " +
         * Integer.parseInt(bu) + " "); } sb.append(" order by t1.pnu asc ");
         * sb.append(" LIMIT " + en + " OFFSET " + (sn-1));
         */

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            while (handler.next()) {
                ConditionSearch cs = new ConditionSearch();
                cs.pnu = Utils.chkNull(handler.getString("pnu"));
                cs.jibun = Utils.chkNull(handler.getString("jibn"));
                cs.emd_nm = Utils.chkNull(handler.getString("emd"));
                cs.bldg_no = Utils.chkNull(handler.getString("bldg_no"));
                cs.rn_cd = Utils.chkNull(handler.getString("rn_cd"));
                cs.buld_mnnm = Utils.chkNull(handler.getString("buld_mnnm"));
                cs.buld_slno = Utils.chkNull(handler.getString("buld_slno"));
                cs.mntn_yn = Utils.chkNull(handler.getString("mntn_yn"));
                cs.lnbr_mnnm = Utils.chkNull(handler.getString("lnbr_mnnm"));
                cs.lnbr_slno = Utils.chkNull(handler.getString("lnbr_slno"));
                list.add(cs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //새주소 지번검색
    public ArrayList<ConditionSearch> getSrchNewjibun(String dcode, String bon, String bu, String san, String snum, String pagenum)
    {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        int sn = Integer.parseInt(snum);
        int en = Integer.parseInt(pagenum);

        sb.append(" select distinct t1.sig_cd as sig_cd, t1.emd_cd as emd_cd , t1.li_cd as li_cd ");
        sb.append(" , t1.mntn_yn as mntn_yn, lpad(cast(t1.lnbr_mnnm as character varying) , 4 , '0') as lnbr_mnnm ");
        sb.append(" , lpad(cast(t1.lnbr_slno as character varying), 4 , '0') as lnbr_slno ");
        sb.append(" , t1.rd_addr as rd_addr, t1.ln_addr as ln_addr, t1.buld_nm as buld_nm ");
        sb.append(" from ");
        sb.append(" v_tl_spbd_buld t1,  ");
        sb.append(" v_tl_sprd_manage t2 ");
        sb.append(" where 1=1 ");
        sb.append(" and t1.sig_cd = t2.sig_cd  ");
        sb.append(" and t1.rds_man_no = t2.rds_man_no  ");
        sb.append(" and t1.rn_cd = t2.rn_cd  ");
        if (!"".equals(dcode)) {
            sb.append(" and t1.sig_cd||t1.emd_cd||t1.li_cd = '" + dcode + "' ");
        }
        if (!"".equals(san)) {
            sb.append(" and case t1.mntn_yn when '0' then '1' when '1' then '2' end = '" + san + "' ");
        }
        if (!"".equals(bon)) {
            sb.append(" and t1.lnbr_mnnm = '" + bon + "' ");
        }
        if (!"".equals(bu)) {
            sb.append(" and t1.lnbr_slno = '" + bu + "' ");
        }
        sb.append(" order by sig_cd, emd_cd, li_cd, mntn_yn, lnbr_mnnm, lnbr_slno asc ");
        sb.append(" LIMIT " + en + " OFFSET " + (sn - 1));

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            while (handler.next()) {
                ConditionSearch cs = new ConditionSearch();
                cs.sig_cd = Utils.chkNull(handler.getString("sig_cd"));
                cs.emd_cd = Utils.chkNull(handler.getString("emd_cd"));
                cs.li_cd = Utils.chkNull(handler.getString("li_cd"));
                cs.mntn_yn = Utils.chkNull(handler.getString("mntn_yn"));
                cs.lnbr_mnnm = Utils.chkNull(handler.getString("lnbr_mnnm"));
                cs.lnbr_slno = Utils.chkNull(handler.getString("lnbr_slno"));
                cs.rd_addr = Utils.chkNull(handler.getString("rd_addr"));
                cs.ln_addr = Utils.chkNull(handler.getString("ln_addr"));
                cs.buld_nm = Utils.chkNull(handler.getString("buld_nm"));
                list.add(cs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //새주소 지번검색 2012-06-27 수정
    public ArrayList<ConditionSearch> getSrchNewjibun2(String dcode, String bon, String bu, String san, String snum, String pagenum)
    {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        int sn = Integer.parseInt(snum);
        int en = Integer.parseInt(pagenum);

        sb.append(" select distinct buld_se_cd, rd_se_lbl, sig_cd, emd_cd, li_cd");
        sb.append(" , mntn_yn, lpad(cast(lnbr_mnnm as character varying) , 4 , '0') as lnbr_mnnm ");
        sb.append(" , lpad(cast(lnbr_slno as character varying), 4 , '0') as lnbr_slno ");
        sb.append(" , rd_addr, ln_addr, buld_nm, buld_mnnm, buld_slno, rn_cd");
        sb.append(" from ");
        sb.append(" v_tl_spbd_buld ");
        sb.append(" where 1=1 ");
        if (!"".equals(dcode)) {
            sb.append(" and sig_cd||emd_cd||li_cd = '" + dcode + "' ");
        }
        if (!"".equals(san)) {
            sb.append(" and case mntn_yn when '0' then '1' when '1' then '2' end = '" + san + "' ");
        }
        if (!"".equals(bon)) {
            sb.append(" and lnbr_mnnm = '" + bon + "' ");
        }
        if (!"".equals(bu)) {
            sb.append(" and lnbr_slno = '" + bu + "' ");
        }
        sb.append(" order by sig_cd, emd_cd, li_cd, mntn_yn, lnbr_mnnm, lnbr_slno asc ");
        sb.append(" LIMIT " + en + " OFFSET " + (sn - 1));

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            while (handler.next()) {
                ConditionSearch cs = new ConditionSearch();
                cs.sig_cd = Utils.chkNull(handler.getString("sig_cd"));
                cs.emd_cd = Utils.chkNull(handler.getString("emd_cd"));
                cs.li_cd = Utils.chkNull(handler.getString("li_cd"));
                cs.mntn_yn = Utils.chkNull(handler.getString("mntn_yn"));
                cs.lnbr_mnnm = Utils.chkNull(handler.getString("lnbr_mnnm"));
                cs.lnbr_slno = Utils.chkNull(handler.getString("lnbr_slno"));
                cs.rd_addr = Utils.chkNull(handler.getString("rd_addr"));
                cs.ln_addr = Utils.chkNull(handler.getString("ln_addr"));
                cs.buld_nm = Utils.chkNull(handler.getString("buld_nm"));
                cs.rd_se_lbl = Utils.chkNull(handler.getString("rd_se_lbl"));
                cs.buld_mnnm = Utils.chkNull(handler.getString("buld_mnnm"));
                cs.buld_slno = Utils.chkNull(handler.getString("buld_slno"));
                cs.rn_cd = Utils.chkNull(handler.getString("rn_cd"));
                list.add(cs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //도로명을 가져온다.
    public ArrayList<ConditionSearch> getNewDoroname(String sgg, String ch) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        //sb.append(" select rds_man_no, rn from st_rd_ls where 1=1 ");
        sb.append(" select sig_cd, rn, rn_cd from v_tl_sprd_manage where 1=1 ");
        sb.append(" and sig_cd = ? ");
        if ("ㄱ".equals(ch)) {
            sb.append(" and rn >= 'ㄱ' and rn < 'ㄴ'  ");
        } else if ("ㄴ".equals(ch)) {
            sb.append(" and rn >= 'ㄴ' and rn < 'ㄷ'  ");
        } else if ("ㄷ".equals(ch)) {
            sb.append(" and rn >= 'ㄷ' and rn < 'ㄹ'  ");
        } else if ("ㄹ".equals(ch)) {
            sb.append(" and rn >= 'ㄹ' and rn < 'ㅁ' ");
        } else if ("ㅁ".equals(ch)) {
            sb.append(" and rn >= 'ㅁ' and rn < 'ㅂ'  ");
        } else if ("ㅂ".equals(ch)) {
            sb.append(" and rn >= 'ㅂ' and rn < 'ㅅ'  ");
        } else if ("ㅅ".equals(ch)) {
            sb.append(" and rn >= 'ㅅ' and rn < 'ㅇ'  ");
        } else if ("ㅇ".equals(ch)) {
            sb.append(" and rn >= 'ㅇ' and rn < 'ㅈ'  ");
        } else if ("ㅈ".equals(ch)) {
            sb.append(" and rn >= 'ㅈ' and rn < 'ㅊ'  ");
        } else if ("ㅊ".equals(ch)) {
            sb.append(" and rn >= 'ㅊ' and rn < 'ㅋ'  ");
        } else if ("ㅋ".equals(ch)) {
            sb.append(" and rn >= 'ㅋ' and rn < 'ㅌ'  ");
        } else if ("ㅌ".equals(ch)) {
            sb.append(" and rn >= 'ㅌ' and rn < 'ㅍ'  ");
        } else if ("ㅍ".equals(ch)) {
            sb.append(" and rn >= 'ㅍ' and rn < 'ㅎ'  ");
        } else if ("ㅎ".equals(ch)) {
            sb.append(" and rn >= 'ㅎ' ");
        }

        sb.append(" group by rn, rn_cd, sig_cd order by rn asc  ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.setString(1, sgg.substring(0, 5));
            handler.execute();

            while (handler.next()) {
                ConditionSearch cs = new ConditionSearch();
                cs.value = Utils.chkNull(handler.getString("rn"));
                cs.code = Utils.chkNull(handler.getString("rn_cd"));
                cs.sig_cd = Utils.chkNull(handler.getString("sig_cd"));
                list.add(cs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //교차로명을 가져온다.
    public ArrayList<ConditionSearch> getNewCrossname(String sgg, String ch) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        sb.append(" select distinct sig_cd, crsrd_sn, kor_crsrd from tl_sprd_crsrd where 1=1 ");
        sb.append(" and sig_cd = ? ");
        if ("ㄱ".equals(ch)) {
            sb.append(" and rn >= 'ㄱ' and rn < 'ㄴ'  ");
        } else if ("ㄴ".equals(ch)) {
            sb.append(" and rn >= 'ㄴ' and rn < 'ㄷ'  ");
        } else if ("ㄷ".equals(ch)) {
            sb.append(" and rn >= 'ㄷ' and rn < 'ㄹ'  ");
        } else if ("ㄹ".equals(ch)) {
            sb.append(" and rn >= 'ㄹ' and rn < 'ㅁ' ");
        } else if ("ㅁ".equals(ch)) {
            sb.append(" and rn >= 'ㅁ' and rn < 'ㅂ'  ");
        } else if ("ㅂ".equals(ch)) {
            sb.append(" and rn >= 'ㅂ' and rn < 'ㅅ'  ");
        } else if ("ㅅ".equals(ch)) {
            sb.append(" and rn >= 'ㅅ' and rn < 'ㅇ'  ");
        } else if ("ㅇ".equals(ch)) {
            sb.append(" and rn >= 'ㅇ' and rn < 'ㅈ'  ");
        } else if ("ㅈ".equals(ch)) {
            sb.append(" and rn >= 'ㅈ' and rn < 'ㅊ'  ");
        } else if ("ㅊ".equals(ch)) {
            sb.append(" and rn >= 'ㅊ' and rn < 'ㅋ'  ");
        } else if ("ㅋ".equals(ch)) {
            sb.append(" and rn >= 'ㅋ' and rn < 'ㅌ'  ");
        } else if ("ㅌ".equals(ch)) {
            sb.append(" and rn >= 'ㅌ' and rn < 'ㅍ'  ");
        } else if ("ㅍ".equals(ch)) {
            sb.append(" and rn >= 'ㅍ' and rn < 'ㅎ'  ");
        } else if ("ㅎ".equals(ch)) {
            sb.append(" and rn >= 'ㅎ' ");
        }

        sb.append(" order by kor_crsrd asc  ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.setString(1, sgg.substring(0, 5));
            handler.execute();

            while (handler.next()) {
                ConditionSearch cs = new ConditionSearch();
                cs.sig_cd = Utils.chkNull(handler.getString("sig_cd"));
                cs.crsrd_sn = Utils.chkNull(handler.getInt("crsrd_sn"));
                cs.kor_crsrd = Utils.chkNull(handler.getString("kor_crsrd"));
                list.add(cs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //도로명 카운트
    public int getDoroTotalCnt(String sgg, String road, String buld_bon, String buld_bu) {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        int tCnt = 0;

        sb.append(" select distinct t1.sig_cd as sig_cd, t1.eqb_man_sn as eqb_man_sn, t2.rn_cd as rn_cd, t2.rn as rn");
        sb.append(" , t1.buld_mnnm as buld_mnnm, t1.buld_slno as buld_slno, t1.sig_cd||t1.emd_cd||t1.li_cd as dcode ");
        sb.append(" , t1.mntn_yn as san, t1.lnbr_mnnm as bon, t1.lnbr_slno as bu, t1.buld_nm as buld_nm ");
        sb.append(" from v_tl_spbd_buld t1, v_tl_sprd_manage t2 ");
        sb.append(" where t1.sig_cd = t2.sig_cd and t1.rds_man_no = t2.rds_man_no and t1.rn_cd = t2.rn_cd ");
        sb.append(" and 1=1 ");
        sb.append(" and t1.sig_cd = '" + sgg.substring(0, 5) + "' ");
        if (!"".equals(road)) {
            sb.append(" and t1.rn_cd = '" + road + "' ");
        }
        if (!"".equals(buld_bon)) {
            sb.append(" and t1.buld_mnnm = '" + buld_bon + "' ");
        }
        if (!"".equals(buld_bu)) {
            sb.append(" and t1.buld_slno = '" + buld_bu + "' ");
        }
        sb.append(" order by t1.buld_mnnm, t1.buld_slno asc ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                tCnt = (tCnt + 1);
                //tCnt  = Integer.parseInt(Utils.chkNull(handler.getString("CNT")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return tCnt;
    }

    //도로명 카운트 수정 2012-06-26
    public int getDoroTotalCnt2(String sgg, String road, String buld_bon, String buld_bu, String buld_se_cd) {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        int tCnt = 0;

        sb.append(" select distinct ln_addr, rd_se_lbl, sig_cd, eqb_man_sn, rn_cd, buld_mnnm, buld_slno, sig_cd||emd_cd||li_cd as dcode, ");
        sb.append(" mntn_yn, lnbr_mnnm, lnbr_slno, buld_nm, buld_se_cd ");
        sb.append(" from v_tl_spbd_buld where ");
        sb.append(" sig_cd = '" + sgg.substring(0, 5) + "' ");
        if (!"".equals(road)) {
            sb.append(" and rn_cd = '" + road + "' ");
        }
        if (!"".equals(buld_bon)) {
            sb.append(" and  buld_mnnm = '" + buld_bon + "' ");
        }
        if (!"".equals(buld_bu)) {
            sb.append(" and buld_slno = '" + buld_bu + "' ");
        }
        if (!"".equals(buld_se_cd)) {
            sb.append(" and buld_se_cd = '" + buld_se_cd + "' ");
        }
        sb.append(" order by buld_mnnm, buld_slno asc");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                tCnt = (tCnt + 1);
                //tCnt  = Integer.parseInt(Utils.chkNull(handler.getString("CNT")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return tCnt;
    }

    //도로명주소 정보를 가져온다.
    public ArrayList<ConditionSearch> getDoroNamesrch(String sgg, String road, String buld_bon, String buld_bu, String snum,
        String pagenum) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        int sn = Integer.parseInt(snum);
        int en = Integer.parseInt(pagenum);

        sb.append(" select distinct t1.sig_cd as sig_cd, t1.eqb_man_sn as eqb_man_sn, t2.rn_cd as rn_cd, t2.rn as rn ");
        sb.append(" , t1.buld_mnnm as buld_mnnm, t1.buld_slno as buld_slno, t1.sig_cd||t1.emd_cd||t1.li_cd as dcode ");
        sb.append(" , t1.mntn_yn as san, t1.lnbr_mnnm as bon, t1.lnbr_slno as bu, t1.buld_nm as buld_nm ");
        sb.append(" from v_tl_spbd_buld t1, v_tl_sprd_manage t2 ");
        sb.append(" where t1.sig_cd = t2.sig_cd and t1.rds_man_no = t2.rds_man_no and t1.rn_cd = t2.rn_cd ");
        sb.append(" and 1=1 ");
        sb.append(" and t1.sig_cd = '" + sgg.substring(0, 5) + "' ");
        if (!"".equals(road)) {
            //sb.append(" and t2.rn = '" + road + "' ");
            sb.append(" and t1.rn_cd = '" + road + "' ");
        }
        if (!"".equals(buld_bon)) {
            sb.append(" and t1.buld_mnnm = '" + buld_bon + "' ");
        }
        if (!"".equals(buld_bu)) {
            sb.append(" and t1.buld_slno = '" + buld_bu + "' ");
        }
        sb.append(" order by t1.buld_mnnm, t1.buld_slno asc ");
        sb.append(" LIMIT " + en + " OFFSET " + (sn - 1));

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            while (handler.next()) {
                ConditionSearch cs = new ConditionSearch();
                //cs.bul_man_no = Utils.chkNull(handler.getString("bul_man_no"));
                cs.sig_cd = Utils.chkNull(handler.getString("sig_cd"));
                cs.eqb_man_sn = Utils.chkNull(handler.getString("eqb_man_sn"));
                cs.rn_cd = Utils.chkNull(handler.getString("rn_cd"));
                cs.rn = Utils.chkNull(handler.getString("rn"));
                cs.buld_mnnm = Utils.chkNull(handler.getString("buld_mnnm"));
                cs.buld_slno = Utils.chkNull(handler.getString("buld_slno"));
                cs.dcode = Utils.chkNull(handler.getString("dcode"));
                cs.san = Utils.chkNull(handler.getString("san"));
                cs.bon = Utils.chkNull(handler.getString("bon"));
                cs.bu = Utils.chkNull(handler.getString("bu"));
                cs.buld_nm = Utils.chkNull(handler.getString("buld_nm"));
                list.add(cs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //도로명주소 정보를 가져온다. 수정 2014-02-26
    public ArrayList<ConditionSearch> getDoroNamesrch2(String sgg, String road, String buld_bon, String buld_bu, String snum,
        String pagenum, String buld_se_cd) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        int sn = Integer.parseInt(snum);
        int en = Integer.parseInt(pagenum);

        sb.append(" select distinct ln_addr, rd_se_lbl, sig_cd, eqb_man_sn, rn_cd, buld_mnnm, buld_slno, sig_cd||emd_cd||li_cd as dcode, ");
        sb.append(" mntn_yn, lnbr_mnnm, lnbr_slno, buld_nm, buld_se_cd ");
        sb.append(" from v_tl_spbd_buld where ");
        sb.append(" sig_cd = '" + sgg.substring(0, 5) + "' ");
        if (!"".equals(road)) {
            sb.append(" and rn_cd = '" + road + "' ");
        }
        if (!"".equals(buld_bon)) {
            sb.append(" and  buld_mnnm = '" + buld_bon + "' ");
        }
        if (!"".equals(buld_bu)) {
            sb.append(" and buld_slno = '" + buld_bu + "' ");
        }
        if (!"".equals(buld_se_cd)) {
            sb.append(" and buld_se_cd = '" + buld_se_cd + "' ");
        }
        sb.append(" order by buld_mnnm, buld_slno asc");
        sb.append(" LIMIT " + en + " OFFSET " + (sn - 1));

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            while (handler.next()) {
                ConditionSearch cs = new ConditionSearch();
                cs.ln_addr = Utils.chkNull(handler.getString("ln_addr"));
                cs.rd_se_lbl = Utils.chkNull(handler.getString("rd_se_lbl"));
                cs.sig_cd = Utils.chkNull(handler.getString("sig_cd"));
                cs.eqb_man_sn = Utils.chkNull(handler.getString("eqb_man_sn"));
                cs.rn_cd = Utils.chkNull(handler.getString("rn_cd"));
                cs.buld_mnnm = Utils.chkNull(handler.getString("buld_mnnm"));
                cs.buld_slno = Utils.chkNull(handler.getString("buld_slno"));
                cs.dcode = Utils.chkNull(handler.getString("dcode"));
                cs.san = Utils.chkNull(handler.getString("mntn_yn"));
                cs.bon = Utils.chkNull(handler.getString("lnbr_mnnm"));
                cs.bu = Utils.chkNull(handler.getString("lnbr_slno"));
                cs.buld_nm = Utils.chkNull(handler.getString("buld_nm"));
                list.add(cs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //도로명 주소 기본정보를 가져온다.
    public ArrayList<ConditionSearch> getBaseAddrInfo(String code) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        sb.append(" select t2.rn as rn, t2.eng_rn as eng_rn, t2.rbp_cn as rbp_cn, t2.rep_cn as rep_cn, t2.road_bt as road_bt, t2.road_lt as road_lt, t2.alwnc_resn as alwnc_resn ");
        sb.append(" from v_tl_spbd_buld t1, v_tl_sprd_manage t2   ");
        sb.append(" where t1.rn_cd = t2.rn_cd and t1.rds_man_no = t2.rds_man_no ");
        sb.append(" and 1=1   ");
        sb.append(" and t1.sig_cd||t1.emd_cd||t1.li_cd =  '" + code.substring(0, 10) + "'   ");
        sb.append(" and case  ");
        sb.append(" when t1.mntn_yn = '1' then '2'  ");
        sb.append(" when t1.mntn_yn = '0' then '1' ");
        sb.append(" end = '" + code.substring(10, 11) + "'   ");
        sb.append(" and t1.lnbr_mnnm = " + Integer.parseInt(code.substring(12, 15)) + "  ");
        sb.append(" and t1.lnbr_slno = " + Integer.parseInt(code.substring(15, 19)) + "   ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            if (handler.next()) {
                ConditionSearch cs = new ConditionSearch();
                cs.rn = Utils.chkNull(handler.getString("rn"));
                cs.eng_rn = Utils.chkNull(handler.getString("eng_rn"));
                cs.rbp_cn = Utils.chkNull(handler.getString("rbp_cn"));
                cs.rep_cn = Utils.chkNull(handler.getString("rep_cn"));
                cs.road_bt = Utils.chkNull(handler.getString("road_bt"));
                cs.road_lt = Utils.chkNull(handler.getString("road_lt"));
                cs.alwnc_resn = Utils.chkNull(handler.getString("alwnc_resn"));
                list.add(cs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //레이어정보
    public ArrayList<ConditionSearch> getLYR_DESC()
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        sb.append(" SELECT lyr_id, tbl_id, lyr_nm, lyr_show_yn FROM mt_lyr_desc ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString("tbl_id");
                sm.value = handler.getString("lyr_nm");
                sm.lyr_show_yn = handler.getString("lyr_show_yn");
                sm.lyr_id = handler.getString("lyr_id");
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return list;

    }

    //테이블 칼럼 정보
    public ArrayList<ConditionSearch> getTBL_COLS(String tbl_id)
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        sb.append(" SELECT col_nm, han_nm FROM mt_tbl_cols where 1=1 ");
        sb.append(" and tbl_id = ? ");
        sb.append(" and sch_yn = 'Y' ");
        sb.append(" order by han_nm asc ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.setString(1, tbl_id);
            handler.execute();
            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString("col_nm");
                sm.value = handler.getString("han_nm");
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return list;

    }

    //테이블 칼럼 Key값 가져오기
    public String getTBL_COLS_KEY(String tbl_id)
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        String key = "";

        sb.append(" SELECT col_nm FROM mt_tbl_cols where 1=1 ");
        sb.append(" and tbl_id = ? ");
        sb.append(" and key_yn = 'Y' ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.setString(1, tbl_id);
            handler.execute();
            if (handler.next()) {
                key = handler.getString("col_nm");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return key;

    }
    
    

    //통합검색 카운트
    public int getInteTotalCnt(String tbl_id, String lry_nm, String cond_type, String included, String match, String range_1,
        String range_2) {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        int tCnt = 0;

        sb.append(" SELECT count(*) as CNT FROM " + tbl_id + " where 1=1 ");

        if ("INCLUDED".equals(cond_type)) {
            sb.append(" and " + lry_nm + " like '%" + included.replaceAll(" ", "") + "%' ");
        }

        if ("MATCH".equals(cond_type)) {
            sb.append(" and " + lry_nm + " = '" + match.replaceAll(" ", "") + "' ");
        }

        if ("RANGE".equals(cond_type)) {
            sb.append(" and " + lry_nm + " >= '" + range_1.replaceAll(" ", "") + "' ");
            sb.append(" and " + lry_nm + " <= '" + range_2.replaceAll(" ", "") + "' ");
        }

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            if (handler.next()) {
                tCnt = Integer.parseInt(Utils.chkNull(handler.getString("CNT")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return tCnt;
    }

    //통합검색 결과리스트
    public ArrayList<ConditionSearch> getLyrSrchList(String tbl_id, String lry_nm, String cond_type, String included, String match,
        String range_1, String range_2, String snum, String pagenum)
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        int sn = Integer.parseInt(snum);
        int en = Integer.parseInt(pagenum);

        String key = getTBL_COLS_KEY(tbl_id); //검색테이블의 키값을 가져온다.

        sb.append(" SELECT " + key + ", " + lry_nm + " FROM " + tbl_id + " where 1=1 ");

        if ("INCLUDED".equals(cond_type)) {
            sb.append(" and " + lry_nm + " like '%" + included.replaceAll(" ", "") + "%' ");
        }

        if ("MATCH".equals(cond_type)) {
            sb.append(" and " + lry_nm + " = '" + match.replaceAll(" ", "") + "' ");
        }

        if ("RANGE".equals(cond_type)) {
            sb.append(" and " + lry_nm + " >= '" + range_1.replaceAll(" ", "") + "' ");
            sb.append(" and " + lry_nm + " <= '" + range_2.replaceAll(" ", "") + "' ");
        }
        sb.append(" order by " + lry_nm + " asc ");
        sb.append(" LIMIT " + en + " OFFSET " + (sn - 1));

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString(key);
                String pnulen = handler.getString(lry_nm);
                if (pnulen.length() == 19) {
                    String addr = Code.getFullAddr(handler.getString(lry_nm).substring(0, 10));
                    String san = handler.getString(lry_nm).substring(10, 11);
                    int bon = Integer.parseInt(handler.getString(lry_nm).substring(11, 15));
                    int bu = Integer.parseInt(handler.getString(lry_nm).substring(15, 19));

                    if (!"1".equals(san)) {
                        san = "산";
                    } else {
                        san = "";
                    }
                    sm.value = addr + " " + san + "" + bon + "-" + bu;
                } else {
                    sm.value = handler.getString(lry_nm);
                }
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return list;

    }

    //통합검색 결과리스트
    public ArrayList<ConditionSearch> getLyrSrchList(String tbl_id, String lry_nm, String cond_type, String included, String match,
        String range_1, String range_2)
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        String key = getTBL_COLS_KEY(tbl_id); //검색테이블의 키값을 가져온다.

        sb.append(" SELECT " + key + ", " + lry_nm + " FROM " + tbl_id + " where 1=1 ");

        if ("INCLUDED".equals(cond_type)) {
            sb.append(" and " + lry_nm + " like '%" + included.replaceAll(" ", "") + "%' ");
        }

        if ("MATCH".equals(cond_type)) {
            sb.append(" and " + lry_nm + " = '" + match.replaceAll(" ", "") + "' ");
        }

        if ("RANGE".equals(cond_type)) {
            sb.append(" and " + lry_nm + " >= '" + range_1.replaceAll(" ", "") + "' ");
            sb.append(" and " + lry_nm + " <= '" + range_2.replaceAll(" ", "") + "' ");
        }
        sb.append(" order by " + lry_nm + " asc ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString(key);
                String pnulen = handler.getString(lry_nm);
                if (pnulen.length() == 19) {
                    String addr = Code.getFullAddr(handler.getString(lry_nm).substring(0, 10));
                    String san = handler.getString(lry_nm).substring(10, 11);
                    int bon = Integer.parseInt(handler.getString(lry_nm).substring(11, 15));
                    int bu = Integer.parseInt(handler.getString(lry_nm).substring(15, 19));

                    if (!"1".equals(san)) {
                        san = "산";
                    } else {
                        san = "";
                    }
                    sm.value = addr + " " + san + "" + bon + "-" + bu;
                } else {
                    sm.value = handler.getString(lry_nm);
                }
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return list;

    }

    //테이블 칼럼정보를 가져온다.
    public ArrayList<ConditionSearch> getTBLcolList(String tbl_id) {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        //String key = getTBL_COLS_KEY(tbl_id); //검색테이블의 키값을 가져온다.

        sb.append(" select han_nm, col_nm ");
        sb.append(" from mt_tbl_cols ");
        sb.append(" where 1=1 ");
        sb.append(" and tbl_id = '" + tbl_id + "' ");
        sb.append(" and show_yn = 'Y' ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString("col_nm");
                sm.value = handler.getString("han_nm");
                System.out.println(sm.code);
                System.out.println(sm.value);
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return list;

    }

    //통합검색 상세정보 리스트
    public String getLyrDetailList(String key, String tbl_id, String keycol, String colnm)
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();

        String rut_val = "";
        sb.append(" select " + colnm + " from " + tbl_id + " where " + keycol + "='" + key + "' ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            if (handler.next()) {
                rut_val = handler.getString(colnm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return rut_val;
    }

    //사각형, 다각형 검색 결과 리스트, 버퍼검색
    public ArrayList<ConditionSearch> getSrchBoxPoly(String[] lrys, String srch_cond, String wtk)
    {
        DBHandler handler = new DBHandler();//DB연결
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        try {

            String tbl_nm = "";
            String lyr_id = "";

            for (int i = 0; i < lrys.length; i++) {

                //2012-03-12일 수정				
               tbl_nm = Code.getLyrNM(new String(lrys[i].getBytes("8859_1"), "UTF-8")); //한글처리
                lyr_id = Code.getLyrID(new String(lrys[i].getBytes("8859_1"), "UTF-8")); //한글처리
                //tbl_nm = Code.getLyrNM(URLDecoder.decode(lrys[i], "utf-8")); //한글처리 
                //lyr_id = Code.getLyrID(URLDecoder.decode(lrys[i], "utf-8")); //한글처리

                if (!"".equals(tbl_nm)) {
                    sb.append(" select dsc.lyr_id as lyr_id,  ");//dsc(mt_lyr_desc)테이블에있는 lyr_id를 lyr_id에 담음
                    sb.append(" dsc.lyr_nm as lyr_nm,  "); //dsc(mt_lyr_desc)테이블에있는 lyr_nm를 lyr_nm에 담음
                    sb.append(" count(tbl._gid) as cnt, array_to_string(array_agg(tbl._gid), ',') as _gid ");
                    sb.append(" from " + tbl_nm + " tbl, "); 
                    sb.append(" mt_lyr_desc dsc ");    //mt_lyr_desc 테이블은 dsc로 담음
                    sb.append(" where dsc.lyr_id ='" + lyr_id + "' "); //검색대상 lyr_id의(예:연속지적도 이면'L0012') 정보가 있음
                    sb.append(" and dsc.tbl_id='" + tbl_nm + "' ");  //검색대상 tbl_id의(예:연속지적도 이면'lp_pa_cbnd') 정보가 있음
                    if ("lt_c_uzone".equals(tbl_nm)) {
                        String wheretxt = Code.getWhereTxt(new String(lrys[i].getBytes("8859_1"), "UTF-8")); //한글처리
                        sb.append(" and tbl." + wheretxt + " ");
                    }
                    //검색방법이 포함이면
                    if ("contains".equals(srch_cond)) {
                        sb.append(" and st_contains(ST_GeomFromText('" + wtk + "', 5186), tbl._geometry) "); //포함
                    }
                    //검색방법이 교차이면
                    if ("intersects".equals(srch_cond)) {
                        sb.append(" and st_intersects(ST_GeomFromText('" + wtk + "', 5186), tbl._geometry) "); //교차
                    }

                    sb.append(" group by dsc.lyr_id, dsc.lyr_nm ");
                    if ((i + 1) != lrys.length) {
                        sb.append(" union all ");
                    }
                }

                /*
                 * sb.append(" select dsc.lyr_id as lyr_id,  ");
                 * sb.append(" dsc.lyr_nm as lyr_nm,  ");
                 * sb.append(" count(tbl._gid) as cnt "); //sb.append(" from " +
                 * Code.getLyrTBL(lrys[i]) + " tbl, "); sb.append(" from " +
                 * Code.getLyrNM(lrys[i]) + " tbl, ");
                 * sb.append(" mt_lyr_desc dsc ");
                 * sb.append(" where dsc.lyr_id ='" + lrys[i] + "' ");
                 * sb.append(" and dsc.tbl_id='" + Code.getLyrTBL(lrys[i]) +
                 * "' "); if("contains".equals(srch_cond)){
                 * sb.append(" and st_contains(GeomFromText('" + wtk +
                 * "'), tbl._geometry) "); //포함 }
                 * if("intersects".equals(srch_cond)){
                 * sb.append(" and st_intersects(GeomFromText('" + wtk +
                 * "'), tbl._geometry) "); //교차 }
                 * sb.append(" group by dsc.lyr_id, dsc.lyr_nm "); if((i+1) !=
                 * lrys.length){ sb.append(" union all "); }
                 */
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            
            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.lyr_id = handler.getString("lyr_id");
                sm.lyr_nm = handler.getString("lyr_nm");
                sm.cnt = handler.getInt("cnt");
                sm.gid = handler.getString("_gid");
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //반경 검색 결과 리스트
    public ArrayList<ConditionSearch> getSrchCricle(String[] lrys, String srch_cond, String cx, String cy, String distance)
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        try {

            String tbl_nm = "";
            String lyr_id = "";

            for (int i = 0; i < lrys.length; i++) {

                //2012-03-12일 수정
                tbl_nm = Code.getLyrNM(new String(lrys[i].getBytes("8859_1"), "UTF-8")); //한글처리
                lyr_id = Code.getLyrID(new String(lrys[i].getBytes("8859_1"), "UTF-8")); //한글처리

                //tbl_nm = Code.getLyrNM(URLDecoder.decode(lrys[i], "utf-8")); //한글처리
                //lyr_id = Code.getLyrID(URLDecoder.decode(lrys[i], "utf-8")); //한글처리

                if (!"".equals(tbl_nm)) {
                    sb.append(" select dsc.lyr_id as lyr_id,  ");
                    sb.append(" dsc.lyr_nm as lyr_nm,  ");
                    sb.append(" count(tbl._gid) as cnt, array_to_string(array_agg(tbl._gid), ',') as _gid ");
                    sb.append(" from " + tbl_nm + " tbl, ");
                    sb.append(" mt_lyr_desc dsc ");
                    sb.append(" where dsc.lyr_id ='" + lyr_id + "' ");
                    sb.append(" and dsc.tbl_id='" + tbl_nm + "' ");
                    if ("lt_c_uzone".equals(tbl_nm)) {
                        String wheretxt = Code.getWhereTxt(new String(lrys[i].getBytes("8859_1"), "UTF-8")); //한글처리
                        sb.append(" and tbl." + wheretxt + " ");
                    }
                    if ("contains".equals(srch_cond)) {
                        sb.append(" and st_contains(ST_Buffer(ST_GeomFromText('POINT("
                            + cx + " " + cy + ")', 5186), " + distance + "), tbl._geometry) ");
                    }
                    if ("intersects".equals(srch_cond)) {
                        sb.append(" and st_intersects(ST_Buffer(ST_GeomFromText('POINT("
                            + cx + " " + cy + ")', 5186), " + distance + "), tbl._geometry) ");
                    }
                    sb.append(" group by dsc.lyr_id, dsc.lyr_nm ");
                    if ((i + 1) != lrys.length) {
                        sb.append(" union all ");
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.lyr_id = handler.getString("lyr_id");
                sm.lyr_nm = handler.getString("lyr_nm");
                sm.cnt = handler.getInt("cnt");
                sm.gid = handler.getString("_gid");
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //레이어상세정보(영역검색_사각형,다각형)
    public ArrayList<ConditionSearch> getSrchLrycontent(String tbl_id, String colkey, String wtk, String srch_cond, String lyr_nm)
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        sb.append(" select " + colkey + " ");
        sb.append(" from " + tbl_id + " tbl ");
        sb.append(" where 1=1 ");
        if ("lt_c_uzone".equals(tbl_id)) {
            String wheretxt = Code.getWhereTxt(lyr_nm); //한글처리
            sb.append(" and " + wheretxt + " ");
        }
        if ("contains".equals(srch_cond)) {
            sb.append(" and st_contains(ST_GeomFromText('" + wtk + "', 5186), _geometry) "); //포함
        }
        if ("intersects".equals(srch_cond)) {
            sb.append(" and st_intersects(ST_GeomFromText('" + wtk + "', 5186), _geometry) "); //교차
        }
        sb.append(" order by " + colkey + " asc ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString(colkey);
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //저수지 저수량 관리
    public ArrayList<ConditionSearch> getReservoir(String ftr_idn){
    	 DBHandler handler = new DBHandler();
         StringBuilder sb = new StringBuilder();
         ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();
         
         sb.append(" SELECT rid, ftr_idn, ftr_nm, juso, res_ymd, res_sto, res_to, res_eff, ben_area, bas_area, etc ");
         sb.append(" FROM reservoir_mtce ");
         sb.append(" WHERE ftr_idn='" + ftr_idn+"' ");
         try {
             handler.open(Const.CONTEXT_NAME);
             handler.setQuery(sb.toString());
             handler.execute();
             while (handler.next()) {
                 ConditionSearch sm = new ConditionSearch();
                 sm.rid = handler.getInt("rid");
                 sm.ftr_idn = handler.getString("ftr_idn");
                 sm.ftr_nm = handler.getString("ftr_nm");
			   	 sm.juso = handler.getString("juso");
                 sm.res_ymd = handler.getString("res_ymd");
                 sm.res_sto = handler.getString("res_sto");
                 sm.res_to = handler.getString("res_to");
                 sm.res_eff = handler.getString("res_eff");
                 sm.ben_area = handler.getString("ben_area");
                 sm.bas_area = handler.getString("bas_area");
                 sm.etc = handler.getString("etc");
                 list.add(sm);
             }
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             handler.close();
         }
         return list;
     }
    //저수지 검색결과 엑셀 저장
    public ArrayList<ConditionSearch> reservoirExcelExport(String ftr_idn){
    	DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();
        
        sb.append(" SELECT rid, ftr_idn, ftr_nm, juso, res_ymd, res_sto, res_to, res_eff, ben_area, bas_area, etc ");
        sb.append(" FROM reservoir_mtce ");
        sb.append(" WHERE ftr_idn='" + ftr_idn+"' ");
        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.rid = handler.getInt("rid");
                sm.ftr_idn = handler.getString("ftr_idn");
                sm.ftr_nm = handler.getString("ftr_nm");
			   	sm.juso = handler.getString("juso");
                sm.res_ymd = handler.getString("res_ymd");
                sm.res_sto = handler.getString("res_sto");
                sm.res_to = handler.getString("res_to");
                sm.res_eff = handler.getString("res_eff");
                sm.ben_area = handler.getString("ben_area");
                sm.bas_area = handler.getString("bas_area");
                sm.etc = handler.getString("etc");
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }
    
    //레이어상세정보(영역검색_사각형,다각형) 페이징
    public ArrayList<ConditionSearch> getSrchLrycontent(String tbl_id, String colkey, String wtk, String srch_cond, String snum,
        String pagenum, String lyr_nm)
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        int sn = Integer.parseInt(snum);
        int en = Integer.parseInt(pagenum);
        System.out.println(colkey);
        System.out.println(tbl_id);
        sb.append(" select " + colkey + " ");
        sb.append(" from " + tbl_id + " tbl ");
        sb.append(" where 1=1 ");
        if ("lt_c_uzone".equals(tbl_id)) {
            String wheretxt = Code.getWhereTxt(lyr_nm); //where_txt 칼럼명을 가져온다.    
            sb.append(" and " + wheretxt);
        }
        if ("contains".equals(srch_cond)) {
            sb.append(" and st_contains(ST_GeomFromText('" + wtk + "', 5186), _geometry) "); //포함
        }
        if ("intersects".equals(srch_cond)) {
            sb.append(" and st_intersects(ST_GeomFromText('" + wtk + "', 5186), _geometry) "); //교차
        }
        sb.append(" order by " + colkey + " asc ");
        sb.append(" LIMIT " + en + " OFFSET " + (sn - 1));

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString(colkey);
                System.out.println(sm.code);
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //레이어상세정보(반경검색) 페이징 처리
    public ArrayList<ConditionSearch> getSrchLryCircle(String tbl_id, String colkey, String cx, String cy, String distance,
        String srch_cond, String snum, String pagenum, String lyr_nm)
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        int sn = Integer.parseInt(snum);
        int en = Integer.parseInt(pagenum);

        sb.append(" select " + colkey + " ");
        sb.append(" from " + tbl_id + " tbl ");
        sb.append(" where 1=1 ");
        if ("lt_c_uzone".equals(tbl_id)) {
            String wheretxt = Code.getWhereTxt(lyr_nm); //where_txt 칼럼명을 가져온다.    
            sb.append(" and " + wheretxt);
        }
        if ("contains".equals(srch_cond)) {
            sb.append(" and st_contains(ST_Buffer(ST_GeomFromText('POINT("
                + cx + " " + cy + ")', 5186), " + distance + "), _geometry) ");
        }
        if ("intersects".equals(srch_cond)) {
            sb.append(" and st_intersects(ST_Buffer(ST_GeomFromText('POINT("
                + cx + " " + cy + ")', 5186), " + distance + "), _geometry) ");
        }
        sb.append(" order by " + colkey + " asc ");
        sb.append(" LIMIT " + en + " OFFSET " + (sn - 1));

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString(colkey);
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //레이어상세정보(반경검색)
    public ArrayList<ConditionSearch> getSrchLryCircle(String tbl_id, String colkey, String cx, String cy, String distance,
        String srch_cond, String lyr_nm)
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        sb.append(" select " + colkey + " ");
        sb.append(" from " + tbl_id + " tbl ");
        sb.append(" where 1=1 ");
        if ("lt_c_uzone".equals(tbl_id)) {
            String wheretxt = Code.getWhereTxt(lyr_nm); //where_txt 칼럼명을 가져온다.    
            sb.append(" and " + wheretxt);
        }
        if ("contains".equals(srch_cond)) {
            sb.append(" and st_contains(ST_Buffer(ST_GeomFromText('POINT("
                + cx + " " + cy + ")', 5186), " + distance + "), _geometry) ");
        }
        if ("intersects".equals(srch_cond)) {
            sb.append(" and st_intersects(ST_Buffer(ST_GeomFromText('POINT("
                + cx + " " + cy + ")', 5186), " + distance + "), _geometry) ");
        }
        sb.append(" order by " + colkey + " asc ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();

            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.code = handler.getString(colkey);
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    /*
     * 통합검색의 연속지적 선택했을때
     */

    public int getInteCbndTotalCnt(String dcode, String owngb,
        String jimok, String area1, String area2, String uznecode2,
        String uznecode3, String base_year,
        String jiga1, String jiga2)
    {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        int tCnt = 0;

        sb.append(" select count(cbnd.pnu) as CNT ");
        sb.append(" from lp_pa_cbnd cbnd, ");
        if (!"".equals(base_year)) {
            sb.append(" anvm_jiga jiga, ");
        }
        if (!"".equals(uznecode2) || !"".equals(uznecode3)) {
            sb.append("lt_c_uzone uzone, ");
        }
        sb.append(" et_lrgst lrg ");

        sb.append(" where 1=1 ");
        //sb.append(" and cbnd.pnu = lrg.adm_dist_cd || lrg.land_site_cd || lrg.lg_gbn || lpad(lrg.bo_jibn,4,'0') || lpad(lrg.bu_jibn,4,'0') ");
        sb.append(" and cbnd.pnu = lrg.pnu ");
        if (!"".equals(base_year)) {
            sb.append(" and cbnd.pnu = jiga.land_cd ");
        }
        if (!"".equals(uznecode2) || !"".equals(uznecode3)) {
            sb.append(" and st_intersects(cbnd._geometry, uzone._geometry) ");
        }

        //행정구역코드 10자리
        if (!"".equals(dcode)) {
            sb.append(" and substring(cbnd.pnu,1,10)='" + dcode + "' ");
        }

        //토지대장 
        if (!"".equals(jimok)) {
            sb.append(" and lrg.jimk_cd = '" + jimok + "' ");
        }
        if (!"".equals(area1)) {
            sb.append(" and lrg.land_area >= " + area1 + " ");
        }
        if (!"".equals(area2)) {
            sb.append(" and lrg.land_area <= " + area2 + " ");
        }
        if (!"".equals(owngb)) {
            sb.append(" and lrg.own_gbn_cd = '" + owngb + "' ");
        }

        //공시지가
        if (!"".equals(base_year)) {
            sb.append(" and jiga.base_year = '" + base_year + "' ");
        }
        if (!"".equals(jiga1)) {
            sb.append(" and jiga.jiga >= " + jiga1 + " ");
        }
        if (!"".equals(jiga2)) {
            sb.append(" and jiga.jiga <= " + jiga2 + " ");
        }

        //용도지역지구
        if (!"".equals(uznecode2)) {
            sb.append(" and uzone.ulyr = '" + uznecode2 + "' ");
        }
        if (!"".equals(uznecode3)) {
            sb.append(" and uzone.ucode = '" + uznecode3 + "' ");
        }

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            if (handler.next()) {
                tCnt = Integer.parseInt(Utils.chkNull(handler.getString("CNT")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return tCnt;
    }

    //통합검색 연속지적, 토지대장 결과리스트
    public ArrayList<ConditionSearch> getLyrSrchCbndList(String dcode, String owngb, String jimok,
        String area1, String area2, String uznecode2,
        String uznecode3, String base_year, String jiga1,
        String jiga2, String snum, String pagenum)
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        int sn = Integer.parseInt(snum);
        int en = Integer.parseInt(pagenum);

        sb.append(" select cbnd.pnu as pnu, cbnd.jibun as jibun ");
        sb.append(" from lp_pa_cbnd cbnd, ");
        if (!"".equals(base_year)) {
            sb.append(" anvm_jiga jiga, ");
        }
        if (!"".equals(uznecode2) || !"".equals(uznecode3)) {
            sb.append("lt_c_uzone uzone, ");
        }
        sb.append(" et_lrgst lrg ");
        //
        //sb.append(" --lt_c_uzone uzone ");
        sb.append(" where 1=1 ");
        //sb.append(" and cbnd.pnu = lrg.adm_dist_cd || lrg.land_site_cd || lrg.lg_gbn || lpad(lrg.bo_jibn,4,'0') || lpad(lrg.bu_jibn,4,'0') ");
        sb.append(" and cbnd.pnu = lrg.pnu ");
        if (!"".equals(base_year)) {
            sb.append(" and cbnd.pnu = jiga.land_cd ");
        }
        if (!"".equals(uznecode2) || !"".equals(uznecode3)) {
            sb.append(" and st_intersects(cbnd._geometry, uzone._geometry) ");
        }
        //sb.append(" --and cbnd.pnu ");

        //행정구역코드 10자리
        if (!"".equals(dcode)) {
            sb.append(" and substring(cbnd.pnu,1,10)='" + dcode + "' ");
        }

        //토지대장 
        if (!"".equals(jimok)) {
            sb.append(" and lrg.jimk_cd = '" + jimok + "' ");
        }
        if (!"".equals(area1)) {
            sb.append(" and lrg.land_area >= " + area1 + " ");
        }
        if (!"".equals(area2)) {
            sb.append(" and lrg.land_area <= " + area2 + " ");
        }
        if (!"".equals(owngb)) {
            sb.append(" and lrg.own_gbn_cd = '" + owngb + "' ");
        }

        //공시지가
        if (!"".equals(base_year)) {
            sb.append(" and jiga.base_year = '" + base_year + "' ");
        }
        if (!"".equals(jiga1)) {
            sb.append(" and jiga.jiga >= " + jiga1 + " ");
        }
        if (!"".equals(jiga2)) {
            sb.append(" and jiga.jiga <= " + jiga2 + " ");
        }

        //용도지역지구
        if (!"".equals(uznecode2)) {
            sb.append(" and uzone.ulyr = '" + uznecode2 + "' ");
        }
        if (!"".equals(uznecode3)) {
            sb.append(" and uzone.ucode = '" + uznecode3 + "' ");
        }

        sb.append(" order by cbnd.pnu asc ");
        sb.append(" LIMIT " + en + " OFFSET " + (sn - 1));

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.code = Utils.chkNull(handler.getString("pnu"));
                sm.value = Utils.chkNull(handler.getString("pnu"));
                sm.jibun = Utils.chkNull(handler.getString("jibun"));
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return list;

    }

    //통합검색 연속지적, 토지대장 결과리스트 -엑셀
    public ArrayList<ConditionSearch> getLyrSrchCbndList(String dcode, String owngb, String jimok,
        String area1, String area2, String uznecode2,
        String uznecode3, String base_year, String jiga1,
        String jiga2)
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();

        sb.append(" select cbnd.pnu as pnu, cbnd.jibun as jibun ");
        sb.append(" from lp_pa_cbnd cbnd, ");
        if (!"".equals(base_year)) {
            sb.append(" anvm_jiga jiga, ");
        }
        if (!"".equals(uznecode2) || !"".equals(uznecode3)) {
            sb.append("lt_c_uzone uzone, ");
        }
        sb.append(" et_lrgst lrg ");
        //
        //sb.append(" --lt_c_uzone uzone ");
        sb.append(" where 1=1 ");
        //sb.append(" and cbnd.pnu = lrg.adm_dist_cd || lrg.land_site_cd || lrg.lg_gbn || lpad(lrg.bo_jibn,4,'0') || lpad(lrg.bu_jibn,4,'0') ");
        sb.append(" and cbnd.pnu = lrg.pnu ");
        if (!"".equals(base_year)) {
            sb.append(" and cbnd.pnu = jiga.land_cd ");
        }
        if (!"".equals(uznecode2) || !"".equals(uznecode3)) {
            sb.append(" and st_intersects(cbnd._geometry, uzone._geometry) ");
        }
        //sb.append(" --and cbnd.pnu ");

        //행정구역코드 10자리
        if (!"".equals(dcode)) {
            sb.append(" and substring(cbnd.pnu,1,10)='" + dcode + "' ");
        }

        //토지대장 
        if (!"".equals(jimok)) {
            sb.append(" and lrg.jimk_cd = '" + jimok + "' ");
        }
        if (!"".equals(area1)) {
            sb.append(" and lrg.land_area >= " + area1 + " ");
        }
        if (!"".equals(area2)) {
            sb.append(" and lrg.land_area <= " + area2 + " ");
        }
        if (!"".equals(owngb)) {
            sb.append(" and lrg.own_gbn_cd = '" + owngb + "' ");
        }

        //공시지가
        if (!"".equals(base_year)) {
            sb.append(" and jiga.base_year = '" + base_year + "' ");
        }
        if (!"".equals(jiga1)) {
            sb.append(" and jiga.jiga >= " + jiga1 + " ");
        }
        if (!"".equals(jiga2)) {
            sb.append(" and jiga.jiga <= " + jiga2 + " ");
        }

        //용도지역지구
        if (!"".equals(uznecode2)) {
            sb.append(" and uzone.ulyr = '" + uznecode2 + "' ");
        }
        if (!"".equals(uznecode3)) {
            sb.append(" and uzone.ucode = '" + uznecode3 + "' ");
        }

        sb.append(" order by cbnd.pnu asc ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                sm.code = Utils.chkNull(handler.getString("pnu"));
                sm.value = Utils.chkNull(handler.getString("pnu"));
                sm.jibun = Utils.chkNull(handler.getString("jibun"));
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }

        return list;

    }

    //용도지역지구	
    public ArrayList<Luris> getIncludeUzone(String pnu) {

        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<Luris> list = new ArrayList<Luris>();

        sb.append(" SELECT DISTINCT T2.UCODE AS UCODE, T2.ULYR AS ULYR FROM LP_PA_CBND T1, LT_C_UZONE T2  WHERE 1=1 ");
        sb.append(" AND ST_INTERSECTS(T1._GEOMETRY, T2._GEOMETRY)  ");
        sb.append(" AND T1.PNU = '" + pnu + "' ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                Luris ls = new Luris();
                ls.ucode = handler.getString("UCODE");
                ls.ulyr = handler.getString("ULYR");
                list.add(ls);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    //읍면동
    public ArrayList<ConditionSearch> getReservoir_info(String umdCode)
    {
        DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();
        
        sb.append(" select ftr_idn  as code, ftr_nm as value ");
        sb.append(" from ag_reservoir_as ");
        sb.append(" where 1=1 ");
        sb.append(" and substring(emd_cde,1,8) = '" + umdCode.substring(0, 8) + "' ");
        sb.append(" order by value ");

        try {
            handler.open(Const.CONTEXT_NAME);
            handler.setQuery(sb.toString());
            handler.execute();
            while (handler.next()) {
                ConditionSearch sm = new ConditionSearch();
                 sm.code = handler.getString("code");
                 sm.value = handler.getString("value");
                list.add(sm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return list;
    }

    public int getReservoirTotalCnt(String umd,String res) {
    	DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
		int tCnt = 0;
		
		sb.append(" SELECT distinct _gid ");
		sb.append(" from ag_reservoir_as ");
		sb.append(" where 1=1 ");
        if (!"".equals(umd)) {
            sb.append(" and substring(emd_cde, 1, 8) = '" +  umd.substring(0, 8)  + "' ");
        }
        if (!"".equals(res)) {
            sb.append(" and ftr_idn = '" +  res  + "' ");
        }
        
        
        System.out.println(sb.toString());
		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {
				tCnt = (tCnt + 1);
				// tCnt =
				// Integer.parseInt(Utils.chkNull(handler.getString("CNT")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return tCnt;
	}
    
    public ArrayList<ConditionSearch> getReservoir_Result(String umd,String res, String snum, String pagenum) {
    	DBHandler handler = new DBHandler();
		StringBuilder sb = new StringBuilder();
        ArrayList<ConditionSearch> list = new ArrayList<ConditionSearch>();
        int sn = Integer.parseInt(snum);
        int en = Integer.parseInt(pagenum);
		
		sb.append(" SELECT ftr_idn,ftr_nm,juso,fns_ymd,mng_nam,tel_num ");
		sb.append(" from ag_reservoir_as ");
		sb.append(" where 1=1 ");
        if (!"".equals(umd)) {
            sb.append(" and substring(emd_cde, 1, 8) = '" +  umd.substring(0, 8)  + "' ");
        }
        if (!"".equals(res)) {
            sb.append(" and ftr_idn = '" +  res  + "' ");
        }
        
        sb.append(" LIMIT " + en + " OFFSET " + (sn - 1));
        
        System.out.println(sb.toString());
		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			while (handler.next()) {
				ConditionSearch cs = new ConditionSearch();
				cs.ftr_idn = Utils.chkNull(handler.getString("ftr_idn"));
				cs.ftr_nm = Utils.chkNull(handler.getString("ftr_nm"));
				cs.juso = Utils.chkNull(handler.getString("juso"));
				cs.fns_ymd = Utils.chkNull(handler.getString("fns_ymd"));
				cs.mng_nam = Utils.chkNull(handler.getString("mng_nam"));
				cs.tel_num = Utils.chkNull(handler.getString("tel_num"));
                list.add(cs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
		 return list;
	}
  //저수량 신규저장
    public boolean getReservoir_Mtce_insert(int rid,String ftr_idn,String ftr_nm,String juso,String res_ymd,String res_sto,String res_to,String res_eff,String ben_area,String bas_area,String ftr_gbn,String etc) {

        DBHandler handler = new DBHandler();
        boolean UR = false;
        StringBuilder sb = new StringBuilder();
        sb.append(" insert into reservoir_mtce(rid,ftr_idn,ftr_nm,juso,res_ymd,res_sto,res_to,res_eff,ben_area, bas_area, ftr_gbn, etc) ");
        sb.append(" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        System.out.println(sb.toString());
        try {
            handler.open(Const.CONTEXT_NAME);;
            handler.setQuery(sb.toString());
            handler.setInt(1, rid);
            handler.setString(2, ftr_idn);
            handler.setString(3, ftr_nm);
            handler.setString(4, juso);
            handler.setString(5, res_ymd);
            handler.setString(6, res_sto);
            handler.setString(7, res_to);
            handler.setString(8, res_eff);
            handler.setString(9, ben_area);
            handler.setString(10, bas_area);
            handler.setString(11, ftr_gbn);
            handler.setString(12, etc);
            handler.execute();
            UR = true;
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
        return UR;
    }
    
    //저수지 ftr_idn값 널확인
	public static String getReservoirIdnGbn(String ftr_idn) {
		DBHandler handler = new DBHandler();
        StringBuilder sb = new StringBuilder();
        String gid = "";
        
        
        sb.append(" select ftr_gbn from reservoir_mtce where ftr_idn = '" + ftr_idn +"' "); 
      
		try {
			handler.open(Const.CONTEXT_NAME);
			handler.setQuery(sb.toString());
			handler.execute();
			if (handler.next()) {
				
				gid = Utils.chkNull(handler.getString("ftr_gbn"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handler.close();
		}

		return gid;
	}
}
    

