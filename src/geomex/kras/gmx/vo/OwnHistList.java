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


@XmlRootElement(name = "소유권변동연혁목록")
@XmlAccessorType(XmlAccessType.FIELD)
public class OwnHistList {

	@XmlElement(name = "소유권변동연혁")
	private ArrayList<OwnHist> ownHistList = new ArrayList<OwnHistList.OwnHist>();
	
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class OwnHist {

		@XmlElement(name = "연혁순번")
		private String ownspChHistOrd = "";
		@XmlElement(name = "소유권변동사유코드")
		private String ownspChCauGbn = "";
		@XmlElement(name = "소유권변동사유")
		private String ownspChCauGbnNm = "";
		@XmlElement(name = "변동일자")
		private String ownspChYmd = "";
		@XmlElement(name = "등록번호")
		private String ownrRegSno = "";
		@XmlElement(name = "소유자명")
		private String ownrNm = "";
		@XmlElement(name = "소유자주소")
		private String ownrAddr = "";
		@XmlElement(name = "소유구분코드")
		private String ownGbn = "";
		@XmlElement(name = "소유구분")
		private String ownGbnNm = "";
		@XmlElement(name = "공유인수")
		private String shapNum = "";
		@XmlElement(name = "처리담당자")
		private String ownspChrNo = "";
		@XmlElement(name = "말소일자")
		private String transYmd = "";

		
		public String getOwnspChHistOrd() {
			return ownspChHistOrd;
		}

		public void setOwnspChHistOrd(String ownspChHistOrd) {
			this.ownspChHistOrd = ownspChHistOrd;
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

		public String getOwnrRegSno() {
			return ownrRegSno;
		}

		public void setOwnrRegSno(String ownrRegSno) {
			this.ownrRegSno = ownrRegSno;
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

		public String getOwnspChrNo() {
			return ownspChrNo;
		}

		public void setOwnspChrNo(String ownspChrNo) {
			this.ownspChrNo = ownspChrNo;
		}

		public String getTransYmd() {
			return transYmd;
		}

		public void setTransYmd(String transYmd) {
			this.transYmd = transYmd;
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
	
	
	public ArrayList<OwnHist> getOwnHistList() {
		return ownHistList;
	}

	public void setOwnHistList(ArrayList<OwnHist> ownHistList) {
		this.ownHistList = ownHistList;
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
