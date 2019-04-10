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


@XmlRootElement(name = "총괄표제부")
@XmlAccessorType(XmlAccessType.FIELD)
public class DjyrecaptitleSet {

	@XmlElement(name = "대지위치")
	private String daejiPosition = "";
	@XmlElement(name = "지번")
	private String jibunDesc = "";
	@XmlElement(name = "명칭및번호")
	private String djyDongNm = "";
	@XmlElement(name = "대지면적")
	private String platArea = "";
	@XmlElement(name = "연면적")
	private String totArea = "";
	@XmlElement(name = "건축물수")
	private String mainBldCnt = "";
	@XmlElement(name = "건축면적")
	private String archArea = "";
	@XmlElement(name = "용적률산정연면적")
	private String vlRatEstmTotarea = "";
	@XmlElement(name = "총호수")
	private String hoCnt = "";
	@XmlElement(name = "건폐율")
	private String bcRat = "";
	@XmlElement(name = "용적율")
	private String vlRat = "";
	@XmlElement(name = "총주차대수")
	private String totPkngCnt = "";
	@XmlElement(name = "주용도")
	private String mainPurpsCdNm = "";
	@XmlElement(name = "부속건축물")
	private String atchBld = "";
	@XmlElement(name = "특이사항")
	private String spCmt = "";

	@XmlElementWrapper(name = "동별현황리스트")
	@XmlElement(name = "동별현황")
	private ArrayList<Djytitle> djytitleList;
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Djytitle {

		@XmlElement(name = "구분")
		private String mainAtchGbCdNm = "";
		@XmlElement(name = "건축물명칭")
		private String dongNm = "";
		@XmlElement(name = "구조")
		private String strctCdNm = "";
		@XmlElement(name = "지붕")
		private String roofCdNm = "";
		@XmlElement(name = "층수")
		private String flrCnt = "";
		@XmlElement(name = "용도")
		private String mainPurpsCdNm = "";
		@XmlElement(name = "면적")
		private String totarea = "";
		
		
		public String getMainAtchGbCdNm() {
			return mainAtchGbCdNm;
		}

		public void setMainAtchGbCdNm(String mainAtchGbCdNm) {
			this.mainAtchGbCdNm = mainAtchGbCdNm;
		}

		public String getDongNm() {
			return dongNm;
		}

		public void setDongNm(String dongNm) {
			this.dongNm = dongNm;
		}

		public String getStrctCdNm() {
			return strctCdNm;
		}

		public void setStrctCdNm(String strctCdNm) {
			this.strctCdNm = strctCdNm;
		}

		public String getRoofCdNm() {
			return roofCdNm;
		}

		public void setRoofCdNm(String roofCdNm) {
			this.roofCdNm = roofCdNm;
		}

		public String getFlrCnt() {
			return flrCnt;
		}

		public void setFlrCnt(String flrCnt) {
			this.flrCnt = flrCnt;
		}

		public String getMainPurpsCdNm() {
			return mainPurpsCdNm;
		}

		public void setMainPurpsCdNm(String mainPurpsCdNm) {
			this.mainPurpsCdNm = mainPurpsCdNm;
		}

		public String getTotarea() {
			return totarea;
		}

		public void setTotarea(String totarea) {
			this.totarea = totarea;
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
	
	@XmlElement(name = "옥내_기계식_대수")
	private String indrMechUtcnt = "";
	@XmlElement(name = "옥내_기계식_면적")
	private String indrMechArea = "";
	@XmlElement(name = "옥외_기계식_대수")
	private String oudrMechUtcnt = "";
	@XmlElement(name = "옥외_기계식_면적")
	private String oudrMechArea = "";
	@XmlElement(name = "옥내_자주식_대수")
	private String indrAutoUtcnt = "";
	@XmlElement(name = "옥내_자주식_면적")
	private String indrAutoArea = "";
	@XmlElement(name = "옥외_자주식_대수")
	private String oudrAutoUtcnt = "";
	@XmlElement(name = "옥외_자주식_면적")
	private String oudrAutoArea = "";
	@XmlElement(name = "에너지효율_등급")
	private String engrGrade = "";
	@XmlElement(name = "에너지효율_절감율")
	private String engrRat = "";
	@XmlElement(name = "에너지성능지표_점")
	private String engrEpi = "";
	@XmlElement(name = "친환경건축물인증_등급")
	private String gnBldGrade = "";
	@XmlElement(name = "친환경건축물인증_점")
	private String gnBldCert = "";
	@XmlElement(name = "지능형건축물인증_등급")
	private String itgBldGrade = "";
	@XmlElement(name = "지능형건축물인증_점")
	private String itgBldCert = "";

	
	public String getDaejiPosition() {
		return daejiPosition;
	}

	public void setDaejiPosition(String daejiPosition) {
		this.daejiPosition = daejiPosition;
	}

	public String getJibunDesc() {
		return jibunDesc;
	}

	public void setJibunDesc(String jibunDesc) {
		this.jibunDesc = jibunDesc;
	}

	public String getDjyDongNm() {
		return djyDongNm;
	}

	public void setDjyDongNm(String djyDongNm) {
		this.djyDongNm = djyDongNm;
	}

	public String getPlatArea() {
		return platArea;
	}

	public void setPlatArea(String platArea) {
		this.platArea = platArea;
	}

	public String getTotArea() {
		return totArea;
	}

	public void setTotArea(String totArea) {
		this.totArea = totArea;
	}

	public String getMainBldCnt() {
		return mainBldCnt;
	}

	public void setMainBldCnt(String mainBldCnt) {
		this.mainBldCnt = mainBldCnt;
	}

	public String getArchArea() {
		return archArea;
	}

	public void setArchArea(String archArea) {
		this.archArea = archArea;
	}

	public String getVlRatEstmTotarea() {
		return vlRatEstmTotarea;
	}

	public void setVlRatEstmTotarea(String vlRatEstmTotarea) {
		this.vlRatEstmTotarea = vlRatEstmTotarea;
	}

	public String getHoCnt() {
		return hoCnt;
	}

	public void setHoCnt(String hoCnt) {
		this.hoCnt = hoCnt;
	}

	public String getBcRat() {
		return bcRat;
	}

	public void setBcRat(String bcRat) {
		this.bcRat = bcRat;
	}

	public String getVlRat() {
		return vlRat;
	}

	public void setVlRat(String vlRat) {
		this.vlRat = vlRat;
	}

	public String getTotPkngCnt() {
		return totPkngCnt;
	}

	public void setTotPkngCnt(String totPkngCnt) {
		this.totPkngCnt = totPkngCnt;
	}

	public String getMainPurpsCdNm() {
		return mainPurpsCdNm;
	}

	public void setMainPurpsCdNm(String mainPurpsCdNm) {
		this.mainPurpsCdNm = mainPurpsCdNm;
	}

	public String getAtchBld() {
		return atchBld;
	}

	public void setAtchBld(String atchBld) {
		this.atchBld = atchBld;
	}

	public String getSpCmt() {
		return spCmt;
	}

	public void setSpCmt(String spCmt) {
		this.spCmt = spCmt;
	}

	public ArrayList<Djytitle> getDjytitleList() {
		return djytitleList;
	}

	public void setDjytitleList(ArrayList<Djytitle> djytitleList) {
		this.djytitleList = djytitleList;
	}

	public String getIndrMechUtcnt() {
		return indrMechUtcnt;
	}

	public void setIndrMechUtcnt(String indrMechUtcnt) {
		this.indrMechUtcnt = indrMechUtcnt;
	}

	public String getIndrMechArea() {
		return indrMechArea;
	}

	public void setIndrMechArea(String indrMechArea) {
		this.indrMechArea = indrMechArea;
	}

	public String getOudrMechUtcnt() {
		return oudrMechUtcnt;
	}

	public void setOudrMechUtcnt(String oudrMechUtcnt) {
		this.oudrMechUtcnt = oudrMechUtcnt;
	}

	public String getOudrMechArea() {
		return oudrMechArea;
	}

	public void setOudrMechArea(String oudrMechArea) {
		this.oudrMechArea = oudrMechArea;
	}

	public String getIndrAutoUtcnt() {
		return indrAutoUtcnt;
	}

	public void setIndrAutoUtcnt(String indrAutoUtcnt) {
		this.indrAutoUtcnt = indrAutoUtcnt;
	}

	public String getIndrAutoArea() {
		return indrAutoArea;
	}

	public void setIndrAutoArea(String indrAutoArea) {
		this.indrAutoArea = indrAutoArea;
	}

	public String getOudrAutoUtcnt() {
		return oudrAutoUtcnt;
	}

	public void setOudrAutoUtcnt(String oudrAutoUtcnt) {
		this.oudrAutoUtcnt = oudrAutoUtcnt;
	}

	public String getOudrAutoArea() {
		return oudrAutoArea;
	}

	public void setOudrAutoArea(String oudrAutoArea) {
		this.oudrAutoArea = oudrAutoArea;
	}

	public String getEngrGrade() {
		return engrGrade;
	}

	public void setEngrGrade(String engrGrade) {
		this.engrGrade = engrGrade;
	}

	public String getEngrRat() {
		return engrRat;
	}

	public void setEngrRat(String engrRat) {
		this.engrRat = engrRat;
	}

	public String getEngrEpi() {
		return engrEpi;
	}

	public void setEngrEpi(String engrEpi) {
		this.engrEpi = engrEpi;
	}

	public String getGnBldGrade() {
		return gnBldGrade;
	}

	public void setGnBldGrade(String gnBldGrade) {
		this.gnBldGrade = gnBldGrade;
	}

	public String getGnBldCert() {
		return gnBldCert;
	}

	public void setGnBldCert(String gnBldCert) {
		this.gnBldCert = gnBldCert;
	}

	public String getItgBldGrade() {
		return itgBldGrade;
	}

	public void setItgBldGrade(String itgBldGrade) {
		this.itgBldGrade = itgBldGrade;
	}

	public String getItgBldCert() {
		return itgBldCert;
	}

	public void setItgBldCert(String itgBldCert) {
		this.itgBldCert = itgBldCert;
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
