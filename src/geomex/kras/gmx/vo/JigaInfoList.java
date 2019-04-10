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
@XmlRootElement(name = "공시지가")
public class JigaInfoList {

    @XmlElement(name = "기준년월일")
    private ArrayList<String> baseYmd = new ArrayList<String>();

    @XmlElement(name = "개별공시지가")
    private ArrayList<String> jiga = new ArrayList<String>();


	public ArrayList<String> getBaseYmd() {
		return baseYmd;
	}

	public void setBaseYmd(ArrayList<String> baseYmd) {
		this.baseYmd = baseYmd;
	}

	public ArrayList<String> getJiga() {
		return jiga;
	}

	public void setJiga(ArrayList<String> jiga) {
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
