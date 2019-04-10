package geomex.kras.gmx.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@XmlRootElement(name = "일필지기본목록")
@XmlAccessorType(XmlAccessType.FIELD)
public class LandInfoSet {

	@XmlElement(name = "일필지기본")
	private LandInfo landInfo = new LandInfo();
	@XmlElement(name = "공시지가")
	private String jiga = "";
	@XmlElement(name = "소재지")
	private String addr = "";
	
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class LandInfo {

		@XmlElement(name = "도호")
		private String mapNoBono = "";
		@XmlElement(name = "도호순번")
		private String mapNoBuno = "";
		@XmlElement(name = "축척코드")
		private String scaleCode = "";
		@XmlElement(name = "축척명")
		private String scaleNm = "";
		@XmlElement(name = "지목")
		private String jimk = "";
		@XmlElement(name = "지목명")
		private String jimkNm = "";
		@XmlElement(name = "면적")
		private String area = "";
		@XmlElement(name = "최종토지이동사유코드")
		private String landMoveWhyCode = "";
		@XmlElement(name = "최종토지이동사유")
		private String landMoveWhyCodeNm = "";
		@XmlElement(name = "최종토지이동일자")
		private String landMoveYmd = "";
		@XmlElement(name = "토지이동관련지번")
		private String landMoveRellJibn = "";
		@XmlElement(name = "사업시행신고구분")
		private String bsinEnfNtGbn = "";
		@XmlElement(name = "최종소유권변동사유코드")
		private String ownspChCauGbn = "";
		@XmlElement(name = "최종소유권변동사유")
		private String ownspChCauGbnNm = "";
		@XmlElement(name = "최종소유권변동일자")
		private String ownspChYmd = "";
		@XmlElement(name = "소유자등록번호")
		private String ownrRegNo = "";
		@XmlElement(name = "소유자등록일련번호")
		private String ownrRegSno = "";
		@XmlElement(name = "소유구분코드")
		private String ownGbn = "";
		@XmlElement(name = "소유구분명")
		private String ownGbnNm = "";
		@XmlElement(name = "공유인수")
		private String shapNum = "";
		@XmlElement(name = "소유자명")
		private String ownrNm = "";
		@XmlElement(name = "소유자주소")
		private String ownrAddr = "";
		@XmlElement(name = "최종토지이동연혁순번")
		private String landLastOrd = "";
		@XmlElement(name = "최종소유권변동연혁순번")
		private String ownspChHistLastOrd = "";
		@XmlElement(name = "관련집합건물")
		private String rellCoBdngSno = "";
		@XmlElement(name = "토지등급")
		private String lv = "";
		@XmlElement(name = "토지등급변동일자")
		private String lvChYmd = "";
		
		
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
		public String getScaleNm() {
			return scaleNm;
		}
		public void setScaleNm(String scaleNm) {
			this.scaleNm = scaleNm;
		}
		public String getJimk() {
			return jimk;
		}
		public void setJimk(String jimk) {
			this.jimk = jimk;
		}
		public String getJimkNm() {
			return jimkNm;
		}
		public void setJimkNm(String jimkNm) {
			this.jimkNm = jimkNm;
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
		public String getLandMoveWhyCodeNm() {
			return landMoveWhyCodeNm;
		}
		public void setLandMoveWhyCodeNm(String landMoveWhyCodeNm) {
			this.landMoveWhyCodeNm = landMoveWhyCodeNm;
		}
		public String getLandMoveYmd() {
			return landMoveYmd;
		}
		public void setLandMoveYmd(String landMoveYmd) {
			this.landMoveYmd = landMoveYmd;
		}
		public String getLandMoveRellJibn() {
			return landMoveRellJibn;
		}
		public void setLandMoveRellJibn(String landMoveRellJibn) {
			this.landMoveRellJibn = landMoveRellJibn;
		}
		public String getBsinEnfNtGbn() {
			return bsinEnfNtGbn;
		}
		public void setBsinEnfNtGbn(String bsinEnfNtGbn) {
			this.bsinEnfNtGbn = bsinEnfNtGbn;
		}
		public String getOwnspChCauGbn() {
			return ownspChCauGbn;
		}
		public void setOwnspChCauGbn(String ownspChCauGbn) {
			this.ownspChCauGbn = ownspChCauGbn;
		}
		public String getOwnspChCauGbnNm() {
			return ownspChCauGbnNm;
		}
		public void setOwnspChCauGbnNm(String ownspChCauGbnNm) {
			this.ownspChCauGbnNm = ownspChCauGbnNm;
		}
		public String getOwnspChYmd() {
			return ownspChYmd;
		}
		public void setOwnspChYmd(String ownspChYmd) {
			this.ownspChYmd = ownspChYmd;
		}
		public String getOwnrRegNo() {
			return ownrRegNo;
		}
		public void setOwnrRegNo(String ownrRegNo) {
			this.ownrRegNo = ownrRegNo;
		}
		public String getOwnrRegSno() {
			return ownrRegSno;
		}
		public void setOwnrRegSno(String ownrRegSno) {
			this.ownrRegSno = ownrRegSno;
		}
		public String getOwnGbn() {
			return ownGbn;
		}
		public void setOwnGbn(String ownGbn) {
			this.ownGbn = ownGbn;
		}
		public String getOwnGbnNm() {
			return ownGbnNm;
		}
		public void setOwnGbnNm(String ownGbnNm) {
			this.ownGbnNm = ownGbnNm;
		}
		public String getShapNum() {
			return shapNum;
		}
		public void setShapNum(String shapNum) {
			this.shapNum = shapNum;
		}
		public String getOwnrNm() {
			return ownrNm;
		}
		public void setOwnrNm(String ownrNm) {
			this.ownrNm = ownrNm;
		}
		public String getOwnrAddr() {
			return ownrAddr;
		}
		public void setOwnrAddr(String ownrAddr) {
			this.ownrAddr = ownrAddr;
		}
		public String getLandLastOrd() {
			return landLastOrd;
		}
		public void setLandLastOrd(String landLastOrd) {
			this.landLastOrd = landLastOrd;
		}
		public String getOwnspChHistLastOrd() {
			return ownspChHistLastOrd;
		}
		public void setOwnspChHistLastOrd(String ownspChHistLastOrd) {
			this.ownspChHistLastOrd = ownspChHistLastOrd;
		}
		public String getRellCoBdngSno() {
			return rellCoBdngSno;
		}
		public void setRellCoBdngSno(String rellCoBdngSno) {
			this.rellCoBdngSno = rellCoBdngSno;
		}
		public String getLv() {
			return lv;
		}
		public void setLv(String lv) {
			this.lv = lv;
		}
		public String getLvChYmd() {
			return lvChYmd;
		}
		public void setLvChYmd(String lvChYmd) {
			this.lvChYmd = lvChYmd;
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


	public LandInfo getLandInfo() {
		return landInfo;
	}
	public void setLandInfo(LandInfo landInfo) {
		this.landInfo = landInfo;
	}

	public String getJiga() {
		return jiga;
	}
	public void setJiga(String jiga) {
		this.jiga = jiga;
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
