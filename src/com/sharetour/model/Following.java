package com.sharetour.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.sharetour.util.POJO;

/*
 * 与关注表对应
 */
public class Following extends POJO{
	
	private static final long serialVersionUID = 1762657441291032957L;
	private Long uid;
	private Long followingid;
	private Date ctime;
	
	public Following() {
		super("following");
	}
	public Following(Long id) {
		super("following");
		setId(id);
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Long getFollowingid() {
		return followingid;
	}
	public void setFollowingid(Long followingid) {
		this.followingid = followingid;
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
		map.put("followingid", getFollowingid());
		return map;
	}
}
