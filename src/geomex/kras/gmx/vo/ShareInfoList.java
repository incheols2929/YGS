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


@XmlRootElement(name = "공유지연명부목록")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShareInfoList {

	@XmlElement(name = "공유지연명부")
	private ArrayList<ShareInfo> shareInfoList = new ArrayList<ShareInfoList.ShareInfo>();
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ShareInfo {

		@XmlElement(name = "공유인일련번호")
		private String shapSno = "";
		@XmlElement(name = "소유권변동사유코드")
		private String ownspChCauGbn = "";
		@XmlElement(name = "소유권변동사유")
		private String ownspChCauGbnNm = "";
		@XmlElement(name = "소유권변동일자")
		private String ownspChYmd = "";
		@XmlElement(name = "소유자등록번호")
		private String ownrRegNo = "";
		@XmlElement(name = "소유자명")
		private String ownrNm = "";
		@XmlElement(name = "소유자주소")
		private String ownrAddr = "";
		@XmlElement(name = "공유지분")
		private String ownspCosm = "";
		@XmlElement(name = "말소일자")
		private String transYmd = "";
		@XmlElement(name = "소유구분코드")
		private String ownGbn = "";
		@XmlElement(name = "소유구분")
		private String ownGbnNm = "";
		
		
		public String getShapSno() {
			return shapSno;
		}

		public void setShapSno(String shapSno) {
			this.shapSno = shapSno;
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

		public String getOwnspCosm() {
			return ownspCosm;
		}

		public void setOwnspCosm(String ownspCosm) {
			this.ownspCosm = ownspCosm;
		}

		public String getTransYmd() {
			return transYmd;
		}

		public void setTransYmd(String transYmd) {
			this.transYmd = transYmd;
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
	
	
	public ArrayList<ShareInfo> getShareInfoList() {
		return shareInfoList;
	}

	public void setShareInfoList(ArrayList<ShareInfo> shareInfoList) {
		this.shareInfoList = shareInfoList;
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
