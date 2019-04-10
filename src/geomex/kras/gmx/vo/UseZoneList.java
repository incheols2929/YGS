package geomex.kras.gmx.vo;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@XmlRootElement(name = "용도지역목록")
@XmlAccessorType(XmlAccessType.FIELD)
public class UseZoneList {

	@XmlElement(name = "용도지역")
	private ArrayList<UseZoneInfo> allPlanList = new ArrayList<UseZoneInfo>();

	@XmlElement(name = "국토계획이용")
	private ArrayList<UseZoneInfo> ctrPlanList;

	@XmlElement(name = "다른법령")
	private ArrayList<UseZoneInfo> othPlanList;

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class UseZoneInfo {
	
		@XmlElement(name = "용도지역지구코드")
		private String ucode = "";
		@XmlElement(name = "용도지역코드번호")
		private String divno = "";
		@XmlElement(name = "용도지역지구형태")
		private String gubun = "";
		@XmlElement(name = "저촉여부")
		private String ctype = "";
		@XmlElement(name = "용도지역지구명_저촉여부")
		private String unm = "";
		@XmlElement(name = "일련번호")
		private String seq = "";
		@XmlElement(name = "법률명")
		private String lawNm = "";
		@XmlElement(name = "용도지역지구명")
		private String uname = "";
		
		
		public String getUcode() {
			return ucode;
		}
		public void setUcode(String ucode) {
			this.ucode = ucode;
		}
		public String getDivno() {
			return divno;
		}
		public void setDivno(String divno) {
			this.divno = divno;
		}
		public String getGubun() {
			return gubun;
		}
		public void setGubun(String gubun) {
			this.gubun = gubun;
		}
		public String getCtype() {
			return ctype;
		}
		public void setCtype(String ctype) {
			this.ctype = ctype;
		}
		public String getUnm() {
			return unm;
		}
		public void setUnm(String unm) {
			this.unm = unm;
		}
		public String getSeq() {
			return seq;
		}
		public void setSeq(String seq) {
			this.seq = seq;
		}
		public String getLawNm() {
			return lawNm;
		}
		public void setLawNm(String lawNm) {
			this.lawNm = lawNm;
		}
		public String getUname() {
			return uname;
		}
		public void setUname(String uname) {
			this.uname = uname;
		}
	
		
		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		}

		@Override
		public int hashCode() {
			return HashCodeBuilder.reflectionHashCode(this, false);
		}

		@Override
		public boolean equals(Object obj) {
			return EqualsBuilder.reflectionEquals(this, obj, false);
		}
		
	}	

	
	public ArrayList<UseZoneInfo> getAllPlanList() {
		return allPlanList;
	}

	public void setAllPlanList(ArrayList<UseZoneInfo> allPlanList) {
		this.allPlanList = allPlanList;
		
		ctrPlanList = new ArrayList<UseZoneInfo>();
		othPlanList = new ArrayList<UseZoneInfo>();

		String ucodeH = "";
		for ( int i = 0; i < allPlanList.size(); i++ ) {
			ucodeH = StringUtils.trimToEmpty(allPlanList.get(i).getUcode()) + "__";
			if ( ucodeH.substring(0, 2).equals("UQ") ) {
				ctrPlanList.add(allPlanList.get(i));
			} else {
				othPlanList.add(allPlanList.get(i));
			}
		}
		
	}
	
	public ArrayList<UseZoneInfo> getCtrPlanList() {
		return ctrPlanList;
	}

	public ArrayList<UseZoneInfo> getOthPlanList() {
		return othPlanList;
	}

	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}
}
