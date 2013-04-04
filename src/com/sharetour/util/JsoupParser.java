package com.sharetour.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupParser implements Parser{
	private static int SUMMARY_LEN=200;			//摘要长度
	private String html;	
	private String html_text;
	private Document doc;
	
	public JsoupParser(String html)
	{
		this.html = html;
		init();
	}
	
	public void init()
	{
		this.doc = Jsoup.parse(html);
		
	}
	
	/*
	 * 
	 * @see org.blog.util.Parser#getText()
	 */
	@Override
	public String getText() {
		// TODO Auto-generated method stub
		this.html_text = doc.text();
		return html_text;
	}
	
	/*
	 * 
	 * @see org.blog.util.Parser#getSummary()
	 */
	@Override
	public String getSummary() 
	{
		getText();
		if(html_text != null && html_text.length() != 0)
			return html_text.substring(0, SUMMARY_LEN);
		else
			return null;
	}

}
