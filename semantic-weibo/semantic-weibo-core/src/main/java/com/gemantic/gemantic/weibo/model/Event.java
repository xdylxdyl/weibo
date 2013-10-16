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
@Table(name = "event")
public class Event implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6013793496066466816L;	
	
	public static final String Source_Weibo = "weibo";
	public static final String Source_News = "news";
	public static final String Source_Forum = "forum";
		
   	 
    private  Long id;
	
  	 
    private  String title;
	
  	 
    private  String summary;
	
  	 
    private  String source;
	
  	 
    private  Long start_at;
	
  	 
    private  Long end_at;
	
  	 
    private  String eventKeyword;
	
  	 
    private  String eventQuery;
	
  	 
    private  Long updateAt;
	
  	 
    private  Long createAt;
	
  
	
		 	
         	 	   @Id
     	   @GeneratedValue(strategy = GenerationType.AUTO)
              	@Column(name = "id")
	public Long getId() {
		return id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
		 	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	
	
	public void setTitle(String title) {
		this.title = title;
	}
		 	@Column(name = "summary")
	public String getSummary() {
		return summary;
	}
	
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
		 	@Column(name = "source")
	public String getSource() {
		return source;
	}
	
	
	public void setSource(String source) {
		this.source = source;
	}
		 	@Column(name = "start_at")
	public Long getStart_at() {
		return start_at;
	}
	
	
	public void setStart_at(Long start_at) {
		this.start_at = start_at;
	}
		 	@Column(name = "end_at")
	public Long getEnd_at() {
		return end_at;
	}
	
	
	public void setEnd_at(Long end_at) {
		this.end_at = end_at;
	}
		 	@Column(name = "event_keyword")
	public String getEventKeyword() {
		return eventKeyword;
	}
	
	
	public void setEventKeyword(String eventKeyword) {
		this.eventKeyword = eventKeyword;
	}
		 	@Column(name = "event_query")
	public String getEventQuery() {
		return eventQuery;
	}
	
	
	public void setEventQuery(String eventQuery) {
		this.eventQuery = eventQuery;
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

