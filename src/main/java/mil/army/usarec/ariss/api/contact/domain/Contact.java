package mil.army.usarec.ariss.api.contact.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;



@Entity
@Table(name="contact")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Contact {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private long prId;
	
	@Column
	private Date contactDt;
	
	@Column
	private String actionCd;
	
	@Column
	private String resultCd;
	
	@Column
	private String dispositionCd;
	
	@Column
	private String comment;
	
	@Column
	private long recruiterId;
    
	
	
	public Contact() {
		
	}

	public Contact(long prId, Date contactDt, String actionCd, String resultCd, String dispositionCd,
			String comment, long recruiterId) {
		super();
		
		this.prId = prId;
		this.contactDt = contactDt;
		this.actionCd = actionCd;
		this.resultCd = resultCd;
		this.dispositionCd = dispositionCd;
		this.comment = comment;
		this.recruiterId = recruiterId;
	}

	public long getPrId() {
		return prId;
	}

	public void setPrId(long prid) {
		this.prId = prid;
	}

	public Date getContactDt() {
		return contactDt;
	}

	public void setContactDt(Date contactdt) {
		this.contactDt = contactdt;
	}

	public String getActionCd() {
		return actionCd;
	}

	public void setActionCd(String actioncd) {
		this.actionCd = actioncd;
	}

	public String getResultCd() {
		return resultCd;
	}

	public void setResultCd(String resultcd) {
		this.resultCd = resultcd;
	}

	public String getDispositionCd() {
		return dispositionCd;
	}

	public void setDispositionCd(String dispositioncd) {
		this.dispositionCd = dispositioncd;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(long recruiterid) {
		this.recruiterId = recruiterid;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Contact {" +
                "id=" + id +
                ", prId='" + prId + '\'' +
                ", contactDt='" + contactDt + '\'' +
                ", actionCd='" + actionCd + '\'' +
                ", resultCd='" + resultCd + '\'' +
                ", dispositionCd='" + dispositionCd + '\''+
                ", comment='" + comment + '\'' +
                ", recruiterId='" + recruiterId +
                '}';
	}
	

}
