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
@Table(name = "dr_miniblog_item")
public class Weibo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8715698316616461312L;

	private Long id;

	private String wid;

	private String content;

	private Integer commentCount;

	private Integer forwardCount;

	private Long authorID;

	private String fromText;

	private String link;

	private Long publishAt;

	private int status;

	private String forwardID;
	
	private List<String> keywords;

	private Long updateAt;

	private Long createAt;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "i_item_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "g_item_id")
	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	@Column(name = "c_item_text")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "i_item_comment")
	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	@Column(name = "i_item_rt_src")
	public Integer getForwardCount() {
		return forwardCount;
	}

	public void setForwardCount(Integer forwardCount) {
		this.forwardCount = forwardCount;
	}

	@Column(name = "i_user_id")
	public Long getAuthorID() {
		return authorID;
	}

	public void setAuthorID(Long authorID) {
		this.authorID = authorID;
	}

	@Column(name = "c_source_name")
	public String getFromText() {
		return fromText;
	}

	public void setFromText(String fromText) {
		this.fromText = fromText;
	}

	@Column(name = "c_item_url")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(name = "i_item_pub")
	public Long getPublishAt() {
		return publishAt;
	}

	public void setPublishAt(Long publishAt) {
		this.publishAt = publishAt;
	}

	@Column(name = "status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "g_item_ref")
	public String getForwardID() {
		return forwardID;
	}

	public void setForwardID(String forwardID) {
		this.forwardID = forwardID;
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
	
	
	
    @Transient
	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
