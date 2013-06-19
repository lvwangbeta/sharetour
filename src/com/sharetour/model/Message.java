package com.sharetour.model;

import java.util.Date;
import java.util.Map;
import com.sharetour.util.POJO;

public class Message extends POJO{

	private static final long serialVersionUID = -2499087201713625463L;
	private Long senderid;
	private Long receiverid;
	private Long messageid;
	private int status;			//0 unread, 1 read
	private Date ctime;
	
	public Message() {
		super("message");
	}
	public Message(Long id) {
		super("message");
		setId(id);
	}

	public Long getSenderid() {
		return senderid;
	}
	public void setSenderid(Long senderid) {
		this.senderid = senderid;
	}
	public Long getReceiverid() {
		return receiverid;
	}
	public void setReceiverid(Long receiverid) {
		this.receiverid = receiverid;
	}
	public Long getMessageid() {
		return messageid;
	}
	public void setMessageid(Long messageid) {
		this.messageid = messageid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	@Override
	public Map<String, Object> ListInsertableFields() {
		return null;
	}
}
