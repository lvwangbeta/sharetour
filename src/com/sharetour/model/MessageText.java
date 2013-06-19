package com.sharetour.model;

import java.util.Map;
import com.sharetour.util.POJO;

public class MessageText extends POJO{

	private static final long serialVersionUID = 1865845924709371854L;
	private String title;
	private String message;
	
	public MessageText() {
		super("messageText");
	}
	public MessageText(Long id) {
		super("messageText");
		setId(id);
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public Map<String, Object> ListInsertableFields() {
		// TODO Auto-generated method stub
		return null;
	}
}
