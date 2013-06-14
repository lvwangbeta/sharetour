package com.sharetour.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sharetour.util.POJO;

/*
 * 与被关注表对应
 */
public class Follower extends POJO{

	private static final long serialVersionUID = -6172191721435101763L;
	private Long uid;
	private Long followerid;
	private Date ctime;
	public Follower() {
		super("followers");
	}
	public Follower(Long id) {
		super("followers");
		setId(id);
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Long getFollowerid() {
		return followerid;
	}
	public void setFollowerid(Long followerid) {
		this.followerid = followerid;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	@Override
	public Map<String, Object> ListInsertableFields() {
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("uid", getUid());
		 map.put("followerid", getFollowerid());
		 return map;
	}
}
