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


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "개별공시지가목록")
public class JigaInfoList2 {

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
	

	public ArrayList<JigaInfo> getJigaList() {
		return jigaList;
	}

	public void setJigaList(ArrayList<JigaInfo> jigaList) {
		this.jigaList = jigaList;
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
