package geomex.kras.gmx.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@XmlRootElement(name = "건축물정보")
@XmlAccessorType(XmlAccessType.FIELD)
public class BldgInfoSet {

	@XmlElement(name = "지번주소")
	private String jibunAddr = "";
	@XmlElement(name = "도로명주소")
	private String doroAddr = "";
	@XmlElement(name = "주용도")
	private String mainUseNm = "";
	@XmlElement(name = "건물수")
	private String totMainBldgCnt = "";
	@XmlElement(name = "대지면적")
	private String larea = "";
	@XmlElement(name = "건축면적")
	private String barea = "";
	@XmlElement(name = "연면적")
	private String garea = "";
	@XmlElement(name = "건페율")
	private String blr = "";
	@XmlElement(name = "용적율")
	private String fsi = "";
	
	
	public BldgInfoSet() {
	}
	
	public BldgInfoSet(BldgInfoSet bldgInfo) {
		setMainUseNm(bldgInfo.getMainUseNm());
		setTotMainBldgCnt(bldgInfo.getTotMainBldgCnt());
		setLarea(bldgInfo.getLarea());
		setBarea(bldgInfo.getBarea());
		setGarea(bldgInfo.getGarea());
		setBlr(bldgInfo.getBlr());
		setFsi(bldgInfo.getFsi());
	}


	public String getJibunAddr() {
		return jibunAddr;
	}
	public void setJibunAddr(String jibunAddr) {
		this.jibunAddr = jibunAddr;
	}
	public String getDoroAddr() {
		return doroAddr;
	}
	public void setDoroAddr(String doroAddr) {
		this.doroAddr = doroAddr;
	}
	public String getMainUseNm() {
		return mainUseNm;
	}
	public void setMainUseNm(String mainUseNm) {
		this.mainUseNm = mainUseNm;
	}
	public String getTotMainBldgCnt() {
		return totMainBldgCnt;
	}
	public void setTotMainBldgCnt(String totMainBldgCnt) {
		this.totMainBldgCnt = totMainBldgCnt;
	}
	public String getLarea() {
		return larea;
	}
	public void setLarea(String larea) {
		this.larea = larea;
	}
	public String getBarea() {
		return barea;
	}
	public void setBarea(String barea) {
		this.barea = barea;
	}
	public String getGarea() {
		return garea;
	}
	public void setGarea(String garea) {
		this.garea = garea;
	}
	public String getBlr() {
		return blr;
	}
	public void setBlr(String blr) {
		this.blr = blr;
	}
	public String getFsi() {
		return fsi;
	}
	public void setFsi(String fsi) {
		this.fsi = fsi;
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
