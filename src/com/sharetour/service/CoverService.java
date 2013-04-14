package com.sharetour.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 生成文章的封面
 */
public class CoverService {
	private String content;
	public String getCover(String content){
		this.content = content;
		Pattern pattern;
		Matcher matcher;
		String imgRex = "<img.*src=(.*?)[^>]*?>";
		pattern = Pattern.compile(imgRex, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(this.content);
		if(matcher.find())
		{
			return matcher.group();
		}
		return null;
	}
}
