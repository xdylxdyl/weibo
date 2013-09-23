package com.gemantic.gemantic.weibo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Table(name = "company_extendnews")
public class CompanyExtendnews implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3040182714855457792L;

	private Long id;

	private String companyUri;

	private String entityUri="";

	private String eid;

	private String source;

	private Integer type;

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

	@Column(name = "entity_uri")
	public String getEntityUri() {
		return entityUri;
	}

	public void setEntityUri(String entityUri) {
		this.entityUri = entityUri;
	}

	@Column(name = "company_uri")
	public String getCompanyUri() {
		return companyUri;
	}

	public void setCompanyUri(String companyUri) {
		this.companyUri = companyUri;
	}

	@Column(name = "source")
	public String getSource() {
		return source;
	}

	@Column(name = "nid")
	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
