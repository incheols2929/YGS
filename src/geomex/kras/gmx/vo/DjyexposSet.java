package geomex.kras.gmx.vo;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@XmlRootElement(name = "전유부")
@XmlAccessorType(XmlAccessType.FIELD)
public class DjyexposSet {

	@XmlElementWrapper(name = "전유현황리스트")
	@XmlElement(name = "전유현황")
	private ArrayList<Djyflrouln> djyflroulnList;
	
	@XmlElementWrapper(name = "소유자현황리스트")
	@XmlElement(name = "소유자현황")
	private ArrayList<Djyownr> djyownrList;

	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Djyflrouln {

		@XmlElement(name = "구분")
		private String mainAtchGbCdNm = "";
		@XmlElement(name = "층별")
		private String flrNoNm = "";
		@XmlElement(name = "구조")
		private String strctCdNm = "";
		@XmlElement(name = "용도")
		private String mainPurpsCdNm = "";
		@XmlElement(name = "면적")
		private String area = "";
		
		
		public String getMainAtchGbCdNm() {
			return mainAtchGbCdNm;
		}
		public void setMainAtchGbCdNm(String mainAtchGbCdNm) {
			this.mainAtchGbCdNm = mainAtchGbCdNm;
		}
		public String getFlrNoNm() {
			return flrNoNm;
		}
		public void setFlrNoNm(String flrNoNm) {
			this.flrNoNm = flrNoNm;
		}
		public String getStrctCdNm() {
			return strctCdNm;
		}
		public void setStrctCdNm(String strctCdNm) {
			this.strctCdNm = strctCdNm;
		}
		public String getMainPurpsCdNm() {
			return mainPurpsCdNm;
		}
		public void setMainPurpsCdNm(String mainPurpsCdNm) {
			this.mainPurpsCdNm = mainPurpsCdNm;
		}
		public String getArea() {
			return area;
		}
		public void setArea(String area) {
			this.area = area;
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
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Djyownr {

		@XmlElement(name = "변동일자")
		private String changCausDay = "";
		@XmlElement(name = "변동원인")
		private String changCausCdNm = "";
		@XmlElement(name = "지분")
		private String jibunDesc = "";
		@XmlElement(name = "성명및명칭")
		private String nm = "";
		@XmlElement(name = "주민번호")
		private String regNo = "";
		@XmlElement(name = "주소")
		private String addr = "";

		
		public String getChangCausDay() {
			return changCausDay;
		}

		public void setChangCausDay(String changCausDay) {
			this.changCausDay = changCausDay;
		}

		public String getChangCausCdNm() {
			return changCausCdNm;
		}

		public void setChangCausCdNm(String changCausCdNm) {
			this.changCausCdNm = changCausCdNm;
		}

		public String getJibunDesc() {
			return jibunDesc;
		}

		public void setJibunDesc(String jibunDesc) {
			this.jibunDesc = jibunDesc;
		}

		public String getNm() {
			return nm;
		}

		public void setNm(String nm) {
			this.nm = nm;
		}

		public String getRegNo() {
			return regNo;
		}

		public void setRegNo(String regNo) {
			this.regNo = regNo;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
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

	
	public ArrayList<Djyflrouln> getDjyflroulnList() {
		return djyflroulnList;
	}

	public void setDjyflroulnList(ArrayList<Djyflrouln> djyflroulnList) {
		this.djyflroulnList = djyflroulnList;
	}

	public ArrayList<Djyownr> getDjyownrList() {
		return djyownrList;
	}

	public void setDjyownrList(ArrayList<Djyownr> djyownrList) {
		this.djyownrList = djyownrList;
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
