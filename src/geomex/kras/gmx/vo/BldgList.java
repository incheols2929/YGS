package geomex.kras.gmx.vo;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@XmlRootElement(name = "건축물대장")
@XmlAccessorType(XmlAccessType.FIELD)
public class BldgList {

	@XmlElement(name = "고유번호")
	private String serialNo = "";
	
	@XmlElement(name = "건축물리스트")
	private ArrayList<BldgInfo> bldgInfoList;
	
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class BldgInfo {

		@XmlElement(name = "대장종류")
		private BldgGbn bldgGbn;

		@XmlAccessorType(XmlAccessType.FIELD)
		public static class BldgGbn {
		
			@XmlAttribute(name = "KEY")
			private String key;
			@XmlAttribute
			private String bno;
			@XmlAttribute
			private String code;
			@XmlValue
			private String val;
			
			
			public String getKey() {
				return key;
			}
			public void setKey(String key) {
				this.key = key;
			}
			public String getBno() {
				return bno;
			}
			public void setBno(String bno) {
				this.bno = bno;
			}
			public String getCode() {
				return code;
			}
			public void setCode(String code) {
				this.code = code;
			}
			public String getVal() {
				return val;
			}
			public void setVal(String val) {
				this.val = val;
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

		@XmlElement(name = "전유부")
		private ExclPoss exclPoss;
		
		@XmlAccessorType(XmlAccessType.FIELD)
		public static class ExclPoss {
		
			@XmlAttribute(name = "J_KEY")
			private String jKey;
			@XmlAttribute
			private String bno;
			@XmlValue
			private String val = "전유부";
			
			
			public String getjKey() {
				return jKey;
			}
			public void setjKey(String jKey) {
				this.jKey = jKey;
			}
			public String getBno() {
				return bno;
			}
			public void setBno(String bno) {
				this.bno = bno;
			}
			public String getVal() {
				return val;
			}
			public void setVal(String val) {
				this.val = val;
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

		@XmlElement(name = "명칭및번호")
		private String dongNm = "";
		@XmlElement(name = "주용도")
		private String mainUse = "";
		@XmlElement(name = "연면적")
		private String garea = "";
		
		
		public BldgGbn getBldgGbn() {
			return bldgGbn;
		}
		public void setBldgGbn(BldgGbn bldgGbn) {
			this.bldgGbn = bldgGbn;
		}
		public ExclPoss getExclPoss() {
			return exclPoss;
		}
		public void setExclPoss(ExclPoss exclPoss) {
			this.exclPoss = exclPoss;
		}
		public String getDongNm() {
			return dongNm;
		}
		public void setDongNm(String dongNm) {
			this.dongNm = dongNm;
		}
		public String getMainUse() {
			return mainUse;
		}
		public void setMainUse(String mainUse) {
			this.mainUse = mainUse;
		}
		public String getGarea() {
			return garea;
		}
		public void setGarea(String garea) {
			this.garea = garea;
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


	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public ArrayList<BldgInfo> getBldgInfoList() {
		return bldgInfoList;
	}
	public void setBldgInfoList(ArrayList<BldgInfo> bldgInfoList) {
		this.bldgInfoList = bldgInfoList;
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
