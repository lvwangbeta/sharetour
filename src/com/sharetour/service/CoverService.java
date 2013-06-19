package com.sharetour.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
 * 生成文章的封面
 */
public class CoverService {
	
	private static final String DEFAULT_COVER = "51bb2ad27d003042fba9cc52"; 
	private static Log log = LogFactory.getLog(CoverService.class);
	private String content;
	
	public String getCover(String base, String content){
		log.info("get cover...");
		this.content = content;
		Pattern pattern;
		Matcher matcher;
		String imgRex = "<img.*src=(.*?)[^>]*?>";
		pattern = Pattern.compile(imgRex, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(this.content);
		String coverurl = null;
		if(matcher.find()) {
			coverurl = matcher.group();
			pattern = Pattern.compile("src=\".*?\"");
			matcher = pattern.matcher(coverurl);
			if(matcher.find()){
				coverurl = matcher.group();
				coverurl = coverurl.substring(coverurl.indexOf("\"")+1, coverurl.lastIndexOf("\""));
			} 
			

		}else {
			//文章中没有图片，采用默认图
			coverurl = base + "/imgs?id=" + DEFAULT_COVER;
		}
		log.info("cover url:"+coverurl);
		return coverurl;
	}
}
