/*
 * 博文标签类
 */
package com.sharetour.model;

import java.util.HashMap;
import java.util.Map;

import com.sharetour.util.POJO;

public class PostTag extends POJO{
	
	private static final long serialVersionUID = 1L;
	private String tagname;
	private int postcount;			//该标签的post数
	public PostTag()
	{
		super("posts_tags");
	}
	
	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public int getPostcount() {
		return postcount;
	}

	public void setPostcount(int postcount) {
		this.postcount = postcount;
	}
	@Override
	public Map<String, Object> ListInsertableFields() {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tagname", getTagname());
		map.put("postcount", getPostcount());
		return map;
	}
	
}
