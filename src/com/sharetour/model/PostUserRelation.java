package com.sharetour.model;

import java.util.HashMap;
import java.util.Map;

import com.sharetour.util.POJO;

/*
 * post 与 user之间的关系表
 * 记录like share等信息
 */
public class PostUserRelation extends POJO{

	private static final long serialVersionUID = 5025653538790099011L;
	private long pid;
	private long uid;
	private String lik;
	private String share;
	
	public PostUserRelation(){
		super("post_user_relation");
	}
	public PostUserRelation(long id) {
		super("post_user_relation");
		setId(id);
	}

	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getLik() {
		return lik;
	}
	public void setLik(String lik) {
		this.lik = lik;
	}
	public String getShare() {
		return share;
	}
	public void setShare(String share) {
		this.share = share;
	}
	@Override
	public Map<String, Object> ListInsertableFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pid", getPid());
		map.put("tid", getUid());
		map.put("lik", getLik());
		map.put("share", getShare());
		return map;
	}
}
