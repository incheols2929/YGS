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


@XmlRootElement(name = "토지대장")
@XmlAccessorType(XmlAccessType.FIELD)
public class TojiDaejangSet {

	@XmlElement(name = "기본정보")
	private Base base = new Base();

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Base {
	
		@XmlElement(name = "고유번호")
		private String gouNum = "";
		@XmlElement(name = "도면번호")
		private String mapNoBono = "";
		@XmlElement(name = "발급번호")
		private String issNo = "";
		@XmlElement(name = "토지소재")
		private String tojiAddr = "";
		@XmlElement(name = "처리시각")
		private String procTime = "";
		@XmlElement(name = "지번")
		private String jibun = "";
		@XmlElement(name = "축척")
		private String scaleNm = "";
		@XmlElement(name = "비고")
		private String note = "";
		@XmlElement(name = "작성자")
		private String user = "";
		
		
		public String getGouNum() {
			return gouNum;
		}

		public void setGouNum(String gouNum) {
			this.gouNum = gouNum;
		}

		public String getMapNoBono() {
			return mapNoBono;
		}

		public void setMapNoBono(String mapNoBono) {
			this.mapNoBono = mapNoBono;
		}

		public String getIssNo() {
			return issNo;
			//return Utils.getStrSec();
		}

		public void setIssNo(String issNo) {
			this.issNo = issNo;
		}

		public String getTojiAddr() {
			return tojiAddr;
		}

		public void setTojiAddr(String tojiAddr) {
			this.tojiAddr = tojiAddr;
		}

		public String getProcTime() {
			return procTime;
			//return Utils.formatTxtHMS(Utils.getStrSec());
		}

		public void setProcTime(String procTime) {
			this.procTime = procTime;
		}

		public String getJibun() {
			return jibun;
		}

		public void setJibun(String jibun) {
			this.jibun = jibun;
		}

		public String getScaleNm() {
			return scaleNm;
		}

		public void setScaleNm(String scaleNm) {
			this.scaleNm = scaleNm;
		}

		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
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
	
	@XmlElement(name = "토지표시_소유자")
	private LandMovOwnHist landMovOwnHist = new LandMovOwnHist();
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class LandMovOwnHist {
	
		@XmlElement(name = "토지표시")
		private ArrayList<LandMov> landMovList;
		
		@XmlAccessorType(XmlAccessType.FIELD)
		public static class LandMov {
		
			@XmlElement(name = "지목코드")
			private String jimokCode = "";
			@XmlElement(name = "지목")
			private String jimok = "";
			@XmlElement(name = "면적")
			private String area = "";
			@XmlElement(name = "사유년도")
			private String sayouYear = "";
			@XmlElement(name = "사유")
			private String landMoveWhy = "";
			
			
			public String getJimokCode() {
				return jimokCode;
			}

			public void setJimokCode(String jimokCode) {
				this.jimokCode = jimokCode;
			}

			public String getJimok() {
				return jimok;
			}

			public void setJimok(String jimok) {
				this.jimok = jimok;
			}

			public String getArea() {
				return area;
			}

			public void setArea(String area) {
				this.area = area;
			}

			public String getSayouYear() {
				return sayouYear;
			}

			public void setSayouYear(String sayouYear) {
				this.sayouYear = sayouYear;
			}

			public String getLandMoveWhy() {
				return landMoveWhy;
			}

			public void setLandMoveWhy(String landMoveWhy) {
				this.landMoveWhy = landMoveWhy;
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
		
		@XmlElement(name = "소유자")
		private ArrayList<Own> ownList;
		
		@XmlAccessorType(XmlAccessType.FIELD)
		public static class Own {
		
			@XmlElement(name = "변동일자")
			private String ownspChYmd = "";
			@XmlElement(name = "주소")
			private String ownrAddr = "";
			@XmlElement(name = "변동원인")
			private String ownspChCauGbnNm = "";
			@XmlElement(name = "성명또는명칭")
			private String ownrNm = "";
			@XmlElement(name = "등록번호")
			private String ownrRegSno = "";
			@XmlElement(name = "공유인수")
			private String shrCnt = "";
			
			
			public String getOwnspChYmd() {
				return ownspChYmd;
			}

			public void setOwnspChYmd(String ownspChYmd) {
				this.ownspChYmd = ownspChYmd;
			}

			public String getOwnrAddr() {
				return ownrAddr;
			}

			public void setOwnrAddr(String ownrAddr) {
				this.ownrAddr = ownrAddr;
			}

			public String getOwnspChCauGbnNm() {
				return ownspChCauGbnNm;
			}

			public void setOwnspChCauGbnNm(String ownspChCauGbnNm) {
				this.ownspChCauGbnNm = ownspChCauGbnNm;
			}

			public String getOwnrNm() {
				return ownrNm;
			}

			public void setOwnrNm(String ownrNm) {
				this.ownrNm = ownrNm;
			}

			public String getOwnrRegSno() {
				return ownrRegSno;
			}

			public void setOwnrRegSno(String ownrRegSno) {
				this.ownrRegSno = ownrRegSno;
			}

			public String getShrCnt() {
				return shrCnt;
			}

			public void setShrCnt(String shrCnt) {
				this.shrCnt = shrCnt;
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

		
		public ArrayList<LandMov> getLandMovList() {
			return landMovList;
		}

		public void setLandMovList(ArrayList<LandMov> landMovList) {
			this.landMovList = landMovList;
		}

		public ArrayList<Own> getOwnList() {
			return ownList;
		}

		public void setOwnList(ArrayList<Own> ownList) {
			this.ownList = ownList;
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
	
	@XmlElementWrapper(name = "개별공시지가기준일")
	@XmlElement(name = "개별공시지가")
	private ArrayList<JigaInfo> jigaList = new ArrayList<JigaInfo>();
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class JigaInfo {
	
		@XmlElement(name = "년도")
		private String baseYear = "";
		@XmlElement(name = "공시지가")
		private String jiga = "";
		
			
		public String getBaseYear() {
			return baseYear;
		}

		public void setBaseYear(String baseYear) {
			this.baseYear = baseYear;
		}

		public String getJiga() {
			return jiga;
		}

		public void setJiga(String jiga) {
			this.jiga = jiga;
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
	
	@XmlElementWrapper(name = "공유지연명부")
	@XmlElement(name = "공유지")
	private ArrayList<ShareInfo> shareInfoList = new ArrayList<ShareInfo>();
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ShareInfo {
	
		@XmlElement(name = "순번")
		private String shapSno = "";
		@XmlElement(name = "변동일자")
		private String ownspChYmd = "";
		@XmlElement(name = "소유권지분")
		private String ownspCosm = "";
		@XmlElement(name = "주소")
		private String ownrAddr = "";
		@XmlElement(name = "등록번호")
		private String ownrRegNo = "";
		@XmlElement(name = "변동원인")
		private String ownspChCauGbnTxt = "";
		@XmlElement(name = "성명또는명칭")
		private String ownrNm = "";
		@XmlElement(name = "말소일자")
		private String transYmd = "";

		
		public String getShapSno() {
			return shapSno;
		}

		public void setShapSno(String shapSno) {
			this.shapSno = shapSno;
		}

		public String getOwnspChYmd() {
			return ownspChYmd;
		}

		public void setOwnspChYmd(String ownspChYmd) {
			this.ownspChYmd = ownspChYmd;
		}

		public String getOwnspCosm() {
			return ownspCosm;
		}

		public void setOwnspCosm(String ownspCosm) {
			this.ownspCosm = ownspCosm;
		}

		public String getOwnrAddr() {
			return ownrAddr;
		}

		public void setOwnrAddr(String ownrAddr) {
			this.ownrAddr = ownrAddr;
		}

		public String getOwnrRegNo() {
			return ownrRegNo;
		}

		public void setOwnrRegNo(String ownrRegNo) {
			this.ownrRegNo = ownrRegNo;
		}

		public String getOwnspChCauGbnTxt() {
			return ownspChCauGbnTxt;
		}

		public void setOwnspChCauGbnTxt(String ownspChCauGbnTxt) {
			this.ownspChCauGbnTxt = ownspChCauGbnTxt;
		}

		public String getOwnrNm() {
			return ownrNm;
		}

		public void setOwnrNm(String ownrNm) {
			this.ownrNm = ownrNm;
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
	
	
	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public LandMovOwnHist getLandMovOwnHist() {
		return landMovOwnHist;
	}

	public void setLandMovOwnHist(LandMovOwnHist landMovOwnHist) {
		this.landMovOwnHist = landMovOwnHist;
	}

	public ArrayList<JigaInfo> getJigaList() {
		return jigaList;
	}

	public void setJigaList(ArrayList<JigaInfo> jigaList) {
		this.jigaList = jigaList;
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
