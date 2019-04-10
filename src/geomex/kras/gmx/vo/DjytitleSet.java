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


@XmlRootElement(name = "표제부")
@XmlAccessorType(XmlAccessType.FIELD)
public class DjytitleSet {

	@XmlElement(name = "표제부_일반건축물")
	private String kindName = "";	// "일반건축물" or "표제부"
	@XmlElement(name = "대지위치")
	private String daejiPosition = "";
	@XmlElement(name = "외필지")
	private String jibun = "";
	@XmlElement(name = "명칭및번호")
	private String djyDongNm = "";
	@XmlElement(name = "대지면적")
	private String platArea = "";
	@XmlElement(name = "연면적")
	private String totArea = "";
	@XmlElement(name = "호수")
	private String hoCnt = "";
	@XmlElement(name = "건축면적")
	private String archArea = "";
	@XmlElement(name = "용적률산정연면적")
	private String vlRatEstmTotarea = "";
	@XmlElement(name = "층수")
	private String flrCnt = "";
	@XmlElement(name = "건폐율")
	private String bcRat = "";
	@XmlElement(name = "용적율")
	private String vlRat = "";
	@XmlElement(name = "높이")
	private String heit = "";
	@XmlElement(name = "주용도")
	private String mainPurpsCdNm = "";
	@XmlElement(name = "부속건축물")
	private String atchBld = "";
	@XmlElement(name = "주구조")
	private String strctCdNm = "";
	@XmlElement(name = "지붕구조")
	private String roofCdNm = "";
	@XmlElement(name = "허가일자")
	private String pmsDay = "";
	@XmlElement(name = "착공일자")
	private String stcnsDay = "";
	@XmlElement(name = "사용승인일자")
	private String useaprDay = "";

	@XmlElementWrapper(name = "층별현황리스트")
	@XmlElement(name = "층별현황")
	private ArrayList<Djyflrouln> djyflroulnList;
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Djyflrouln {

		@XmlElement(name = "구분")
		private String flrGbCdNm = "";
		@XmlElement(name = "건축물명칭")
		private String flrNoNm = "";
		@XmlElement(name = "구조")
		private String strctCdNm = "";
		@XmlElement(name = "용도")
		private String etcPurps = "";
		@XmlElement(name = "면적")
		private String area = "";
		
		
		public String getFlrGbCdNm() {
			return flrGbCdNm;
		}

		public void setFlrGbCdNm(String flrGbCdNm) {
			this.flrGbCdNm = flrGbCdNm;
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

		public String getEtcPurps() {
			return etcPurps;
		}

		public void setEtcPurps(String etcPurps) {
			this.etcPurps = etcPurps;
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


	@XmlElement(name = "소유자현황")
	private ArrayList<OwnerInfo> ownerInfoList;

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class OwnerInfo {

		@XmlElement(name = "변동일자")
		private String chgYmd = "";
		@XmlElement(name = "소유자명")
		private String ownerNm = "";
		@XmlElement(name = "등록번호")
		private String dregno = "";
		@XmlElement(name = "상세주소")
		private String detlAddr = "";
		@XmlElement(name = "최종여부")
		private String lastYn = "";
		@XmlElement(name = "변동원인명")
		private String chgRsnNm = "";
		@XmlElement(name = "소유구분명")
		private String ownGbnNm = "";
		@XmlElement(name = "주민구분명")
		private String resGbnNm = "";
		@XmlElement(name = "등기여부")
		private String regtYn = "";
		@XmlElement(name = "지분내역")
		private String jibunDesc = "";
		
		
		public String getChgYmd() {
			return chgYmd;
		}

		public void setChgYmd(String chgYmd) {
			this.chgYmd = chgYmd;
		}

		public String getOwnerNm() {
			return ownerNm;
		}

		public void setOwnerNm(String ownerNm) {
			this.ownerNm = ownerNm;
		}

		public String getDregno() {
			return dregno;
		}

		public void setDregno(String dregno) {
			this.dregno = dregno;
		}

		public String getDetlAddr() {
			return detlAddr;
		}

		public void setDetlAddr(String detlAddr) {
			this.detlAddr = detlAddr;
		}

		public String getLastYn() {
			return lastYn;
		}

		public void setLastYn(String lastYn) {
			this.lastYn = lastYn;
		}

		public String getChgRsnNm() {
			return chgRsnNm;
		}

		public void setChgRsnNm(String chgRsnNm) {
			this.chgRsnNm = chgRsnNm;
		}

		public String getOwnGbnNm() {
			return ownGbnNm;
		}

		public void setOwnGbnNm(String ownGbnNm) {
			this.ownGbnNm = ownGbnNm;
		}

		public String getResGbnNm() {
			return resGbnNm;
		}

		public void setResGbnNm(String resGbnNm) {
			this.resGbnNm = resGbnNm;
		}

		public String getRegtYn() {
			return regtYn;
		}

		public void setRegtYn(String regtYn) {
			this.regtYn = regtYn;
		}

		public String getJibunDesc() {
			return jibunDesc;
		}

		public void setJibunDesc(String jibunDesc) {
			this.jibunDesc = jibunDesc;
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
	@XmlElement(name = "승용")
	private String rideUseElvtCnt = "";
	@XmlElement(name = "비상용")
	private String emgenUseElvtCnt = "";
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


	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public String getDaejiPosition() {
		return daejiPosition;
	}

	public void setDaejiPosition(String daejiPosition) {
		this.daejiPosition = daejiPosition;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
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

	public String getHoCnt() {
		return hoCnt;
	}

	public void setHoCnt(String hoCnt) {
		this.hoCnt = hoCnt;
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

	public String getFlrCnt() {
		return flrCnt;
	}

	public void setFlrCnt(String flrCnt) {
		this.flrCnt = flrCnt;
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

	public String getHeit() {
		return heit;
	}

	public void setHeit(String heit) {
		this.heit = heit;
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

	public String getPmsDay() {
		return pmsDay;
	}

	public void setPmsDay(String pmsDay) {
		this.pmsDay = pmsDay;
	}

	public String getStcnsDay() {
		return stcnsDay;
	}

	public void setStcnsDay(String stcnsDay) {
		this.stcnsDay = stcnsDay;
	}

	public String getUseaprDay() {
		return useaprDay;
	}

	public void setUseaprDay(String useaprDay) {
		this.useaprDay = useaprDay;
	}

	public ArrayList<Djyflrouln> getDjyflroulnList() {
		return djyflroulnList;
	}

	public void setDjyflroulnList(ArrayList<Djyflrouln> djyflroulnList) {
		this.djyflroulnList = djyflroulnList;
	}

	public ArrayList<OwnerInfo> getOwnerInfoList() {
		return ownerInfoList;
	}

	public void setOwnerInfoList(ArrayList<OwnerInfo> ownerInfoList) {
		this.ownerInfoList = ownerInfoList;
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

	public String getRideUseElvtCnt() {
		return rideUseElvtCnt;
	}

	public void setRideUseElvtCnt(String rideUseElvtCnt) {
		this.rideUseElvtCnt = rideUseElvtCnt;
	}

	public String getEmgenUseElvtCnt() {
		return emgenUseElvtCnt;
	}

	public void setEmgenUseElvtCnt(String emgenUseElvtCnt) {
		this.emgenUseElvtCnt = emgenUseElvtCnt;
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
