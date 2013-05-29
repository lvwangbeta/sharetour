package com.sharetour.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
 * 生成文章的封面
 */
public class CoverService {
	private String content;
	private static Log log = LogFactory.getLog(CoverService.class);
	
	public String getCover(String content){
		log.info("get cover...");
		this.content = content;
		Pattern pattern;
		Matcher matcher;
		String imgRex = "<img.*src=(.*?)[^>]*?>";
		pattern = Pattern.compile(imgRex, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(this.content);
		if(matcher.find())
		{
			String coverurl = matcher.group();
			log.info("cover url:"+coverurl);
			return coverurl;
		}
		return null;
	}
}
