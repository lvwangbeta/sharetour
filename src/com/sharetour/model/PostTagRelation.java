/*
 * post与tag的关系表
 */
package com.sharetour.model;

import java.util.HashMap;
import java.util.Map;

import com.sharetour.util.POJO;

public class PostTagRelation extends POJO{

	private static final long serialVersionUID = 1L;
	private long pid;
	private long tid;
	
	public PostTagRelation()
	{
		super("post_tag_relation");
	}

	@Override
	public Map<String, Object> ListInsertableFields() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pid", getPid());
		map.put("tid", getTid());
		return map;
	}


	public long getPid() {
		return pid;
	}


	public void setPid(long pid) {
		this.pid = pid;
	}


	public long getTid() {
		return tid;
	}


	public void setTid(long tid) {
		this.tid = tid;
	}

}
