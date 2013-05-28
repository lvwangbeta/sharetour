package com.sharetour.util;

public interface Parser {
	
	/*
	 * 返回html代码段的纯文本内容(去除标签)
	 */
	public String getText();
	
	/*
	 * 获得摘要信息
	 */
	public String getSummary();
}
