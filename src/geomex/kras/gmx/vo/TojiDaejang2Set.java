package geomex.kras.gmx.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@XmlRootElement(name = "토지대장")
@XmlAccessorType(XmlAccessType.FIELD)
public class TojiDaejang2Set {

	@XmlElement(name = "토지이동연혁")
	private LandMovHistList.LandMovHist landMovHist = new LandMovHistList.LandMovHist();
	@XmlElement(name = "토지이동연혁목록")
	private LandMovHistList landMovHistList = new LandMovHistList();
	@XmlElement(name = "소유권변동연혁목록")
	private OwnHistList ownHistList = new OwnHistList();
	@XmlElement(name = "개별공시지가목록")
	private JigaInfoList2 jigaList = new JigaInfoList2();
	@XmlElement(name = "공유지연명부목록")
	private ShareInfoList shareInfoList = new ShareInfoList();
	

	public LandMovHistList.LandMovHist getLandMovHist() {
		return landMovHist;
	}

	public void setLandMovHist(LandMovHistList.LandMovHist landMovHist) {
		this.landMovHist = landMovHist;
	}

	public LandMovHistList getLandMovHistList() {
		return landMovHistList;
	}

	public void setLandMovHistList(LandMovHistList landMovHistList) {
		this.landMovHistList = landMovHistList;
	}

	public OwnHistList getOwnHistList() {
		return ownHistList;
	}

	public void setOwnHistList(OwnHistList ownHistList) {
		this.ownHistList = ownHistList;
	}

	public JigaInfoList2 getJigaList() {
		return jigaList;
	}

	public void setJigaList(JigaInfoList2 jigaList) {
		this.jigaList = jigaList;
	}

	public ShareInfoList getShareInfoList() {
		return shareInfoList;
	}

	public void setShareInfoList(ShareInfoList shareInfoList) {
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
