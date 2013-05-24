package com.sharetour.model;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;

public class PhotoComment implements Serializable{

	private static final long serialVersionUID = -4748627776571020506L;
	private ObjectId id;
	private String comment;
	private Long uid;
	private String username;
	private Date ctime;
	private ObjectId parent;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public ObjectId getParent() {
		return parent;
	}
	public void setParent(ObjectId parent) {
		this.parent = parent;
	}
}
