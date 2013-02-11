package com.sharetour.model;

public class Tag {
	private int id;
	private String tag;
	
	public Tag(int id, String tag)
	{
		this.id = id;
		this.tag = tag;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return id;
	}
	public void setTag(String tag)
	{
		this.tag = tag;
	}
	public String getTag()
	{
		return tag;
	}
}
