package com.sharetour.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.sharetour.util.POJO;

public class Subscription extends POJO{

	private static final long serialVersionUID = 1L;
	private Long uid;
	private Long tid;
	private PostTag posttag = new PostTag();
	private Date subtime;
	
	public Subscription() {
		super("subscription");
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}


	public Date getSubtime() {
		return subtime;
	}

	public void setSubtime(Date subtime) {
		this.subtime = subtime;
	}
	
	public void setTagname(String tagname){
		this.posttag.setTagname(tagname);
	}
	public String getTagname(){
		return posttag.getTagname();
	}
	@Override
	public Map<String, Object> ListInsertableFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", getUid());
		map.put("tid", getTid());
		return map;
	}	
}
