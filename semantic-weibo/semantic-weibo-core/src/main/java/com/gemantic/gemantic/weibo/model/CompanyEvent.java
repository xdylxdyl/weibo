package com.gemantic.gemantic.weibo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Table(name = "company_event")
public class CompanyEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4821914498404907008L;

	private Long id;

	private String companyUri;

	private Long eid;

	private String source;

	

	private Long updateAt;

	private Long createAt;
	
	private Long publishAt;
	
	@Column(name = "publish_at")
	public Long getPublishAt() {
		return publishAt;
	}

	public void setPublishAt(Long publishAt) {
		this.publishAt = publishAt;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "company_uri")
	public String getCompanyUri() {
		return companyUri;
	}

	public void setCompanyUri(String companyUri) {
		this.companyUri = companyUri;
	}

	@Column(name = "eid")
	public Long getEid() {
		return eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
	}

	@Column(name = "source")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}



	@Column(name = "update_at")
	public Long getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Long updateAt) {
		this.updateAt = updateAt;
	}

	@Column(name = "create_at")
	public Long getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Long createAt) {
		this.createAt = createAt;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
