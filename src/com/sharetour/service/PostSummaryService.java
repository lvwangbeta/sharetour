package com.sharetour.service;

import com.sharetour.util.JsoupParser;
import com.sharetour.util.Parser;

public class PostSummaryService {
	
	private String html;
    /*
     * 获得文章内容的摘要
     * @param {String} origin html
     * @return {String} summary of content (plain text)
     */
    public String getSummary(String html)
    {
    	this.html = html;
    	Parser parser = new JsoupParser(this.html);
    	return parser.getSummary();
    }
}
