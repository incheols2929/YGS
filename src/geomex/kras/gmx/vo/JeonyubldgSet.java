package geomex.kras.gmx.vo;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@XmlRootElement(name = "전유부")
@XmlAccessorType(XmlAccessType.FIELD)
public class JeonyubldgSet {

	@XmlElement(name = "대지위치")
	private String daejiPosition = "";
	@XmlElement(name = "명칭및번호")
	private String dongNm = "";
	@XmlElement(name = "KEY")
	private String key = "";
	@XmlElement(name = "bno")
	private String bno = "";
	
	@XmlElementWrapper(name = "호명칭리스트")
	@XmlElement(name = "호명칭")
	private ArrayList<HoName> hoNameList;
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class HoName {
	
		@XmlAttribute(name = "H_KEY")
		private String hKey;
		@XmlAttribute
		private String bno;
		@XmlValue
		private String val;
		
		
		public String gethKey() {
			return hKey;
		}

		public void sethKey(String hKey) {
			this.hKey = hKey;
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
	
	
	public String getDaejiPosition() {
		return daejiPosition;
	}

	public void setDaejiPosition(String daejiPosition) {
		this.daejiPosition = daejiPosition;
	}

	public String getDongNm() {
		return dongNm;
	}

	public void setDongNm(String dongNm) {
		this.dongNm = dongNm;
	}

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

	public ArrayList<HoName> getHoNameList() {
		return hoNameList;
	}

	public void setHoNameList(ArrayList<HoName> hoNameList) {
		this.hoNameList = hoNameList;
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
