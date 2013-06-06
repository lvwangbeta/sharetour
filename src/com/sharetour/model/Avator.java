package com.sharetour.model;

import org.bson.types.ObjectId;

public class Avator {
	private ObjectId id;
	private Long uid;
	private String username;
	private ObjectId avatorId;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
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
	public ObjectId getAvatorId() {
		return avatorId;
	}
	public void setAvatorId(ObjectId avatorId) {
		this.avatorId = avatorId;
	}
}
