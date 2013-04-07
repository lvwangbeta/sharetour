package com.sharetour.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.sharetour.util.POJO;

/*
 * 博客分类 e.g (工作日志, 随笔...)
 */
public class PostCategory extends POJO{

	private static final long serialVersionUID = 1L;
	
	private int authorid;		//作者ID
	private int postid;			//
	private String name;		//类名
	private Date ctime;			//创建时间
	
	
	public PostCategory()
	{
		super("posts_category");
	}
	public PostCategory(long id)
	{
		super("posts_category");
		setId(id);
	}
	
	public int getAuthorid() {
		return authorid;
	}
	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	@Override
	public Map<String, Object> ListInsertableFields() {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("authorid", getAuthorid());
		map.put("postid", getPostid());
		map.put("name", getName());
		return map;
	}

}
