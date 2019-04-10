package geomex.kras.gmx.vo;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@XmlRootElement(name = "토지이동연혁목록")
@XmlAccessorType(XmlAccessType.FIELD)
public class LandMovHistList {

	@XmlElement(name = "토지이동연혁")
	private ArrayList<LandMovHist> landMovHistList = new ArrayList<LandMovHist>();
	
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class LandMovHist {

		@XmlElement(name = "도호본번")
		private String mapNoBono = "";
		@XmlElement(name = "도호부번")
		private String mapNoBuno = "";
		@XmlElement(name = "축척코드")
		private String scaleCode = "";
		@XmlElement(name = "코드내용")
		private String scaleCodeNm = "";
		@XmlElement(name = "지목코드")
		private String jimkCode = "";
		@XmlElement(name = "현재면적")
		private String area = "";
		@XmlElement(name = "토지이동사유")
		private String landMoveWhyCode = "";
		@XmlElement(name = "이동일자")
		private String landMoveYmd = "";
		@XmlElement(name = "연혁순번")
		private String landHistOrd = "";
		@XmlElement(name = "지목코드")
		private String jimkCode1 = "";
		@XmlElement(name = "지목")
		private String jimkNm1 = "";
		@XmlElement(name = "연혁상_면적")
		private String area1 = "";
		@XmlElement(name = "토지이동사유코드")
		private String landMoveWhyCode1 = "";
		@XmlElement(name = "토지이동사유")
		private String landMoveWhyNm1 = "";
		@XmlElement(name = "토지이동일자")
		private String landMoveYmd1 = "";
		@XmlElement(name = "관련지번")
		private String landMoveRellJibn = "";
		
		
		public String getMapNoBono() {
			return mapNoBono;
		}
		public void setMapNoBono(String mapNoBono) {
			this.mapNoBono = mapNoBono;
		}
		public String getMapNoBuno() {
			return mapNoBuno;
		}
		public void setMapNoBuno(String mapNoBuno) {
			this.mapNoBuno = mapNoBuno;
		}
		public String getScaleCode() {
			return scaleCode;
		}
		public void setScaleCode(String scaleCode) {
			this.scaleCode = scaleCode;
		}
		public String getScaleCodeNm() {
			return scaleCodeNm;
		}
		public void setScaleCodeNm(String scaleCodeNm) {
			this.scaleCodeNm = scaleCodeNm;
		}
		public String getJimkCode() {
			return jimkCode;
		}
		public void setJimkCode(String jimkCode) {
			this.jimkCode = jimkCode;
		}
		public String getArea() {
			return area;
		}
		public void setArea(String area) {
			this.area = area;
		}
		public String getLandMoveWhyCode() {
			return landMoveWhyCode;
		}
		public void setLandMoveWhyCode(String landMoveWhyCode) {
			this.landMoveWhyCode = landMoveWhyCode;
		}
		public String getLandMoveYmd() {
			return landMoveYmd;
		}
		public void setLandMoveYmd(String landMoveYmd) {
			this.landMoveYmd = landMoveYmd;
		}
		public String getLandHistOrd() {
			return landHistOrd;
		}
		public void setLandHistOrd(String landHistOrd) {
			this.landHistOrd = landHistOrd;
		}
		public String getJimkCode1() {
			return jimkCode1;
		}
		public void setJimkCode1(String jimkCode1) {
			this.jimkCode1 = jimkCode1;
		}
		public String getJimkNm1() {
			return jimkNm1;
		}
		public void setJimkNm1(String jimkNm1) {
			this.jimkNm1 = jimkNm1;
		}
		public String getArea1() {
			return area1;
		}
		public void setArea1(String area1) {
			this.area1 = area1;
		}
		public String getLandMoveWhyCode1() {
			return landMoveWhyCode1;
		}
		public void setLandMoveWhyCode1(String landMoveWhyCode1) {
			this.landMoveWhyCode1 = landMoveWhyCode1;
		}
		public String getLandMoveWhyNm1() {
			return landMoveWhyNm1;
		}
		public void setLandMoveWhyNm1(String landMoveWhyNm1) {
			this.landMoveWhyNm1 = landMoveWhyNm1;
		}
		public String getLandMoveYmd1() {
			return landMoveYmd1;
		}
		public void setLandMoveYmd1(String landMoveYmd1) {
			this.landMoveYmd1 = landMoveYmd1;
		}
		public String getLandMoveRellJibn() {
			return landMoveRellJibn;
		}
		public void setLandMoveRellJibn(String landMoveRellJibn) {
			this.landMoveRellJibn = landMoveRellJibn;
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


	public ArrayList<LandMovHist> getLandMovHistList() {
		return landMovHistList;
	}
	public void setLandMovHistList(ArrayList<LandMovHist> landMovHistList) {
		this.landMovHistList = landMovHistList;
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
