package com.sharetour.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sharetour.util.POJO;

public class PostComment extends POJO{

	private static final long serialVersionUID = 1L;

	private long authorid;
	private long postid;
	private long parentid;		//父级评论
	private String name;
	private String email;
	private Date ctime;
	private String content;
	
	
	public PostComment()
	{
		super("posts_comments");
	}
	public PostComment(long id)
	{
		super("posts_comments");
		setId(id);
	}
	
	public long getAuthorid() {
		return authorid;
	}
	public void setAuthorid(long authorid) {
		this.authorid = authorid;
	}
	public long getPostid() {
		return postid;
	}
	public void setPostid(long postid) {
		this.postid = postid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getParentid() {
		return parentid;
	}
	public void setParentid(long parentid) {
		this.parentid = parentid;
	}
	@Override
	public Map<String, Object> ListInsertableFields() {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("authorid", getAuthorid());
		map.put("postid", getPostid());
		map.put("name", getName());
		map.put("ctime", getCtime());
		map.put("content", getContent());
		return map;
	}

}
