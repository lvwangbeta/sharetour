package com.sharetour.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sharetour.model.Message;
import com.sharetour.model.MessageText;
import com.sharetour.util.QueryHelper;

public class Notification {
	
	private static final Log log = LogFactory.getLog(Notification.class);
	public static final String POST_COMMENT = "post_comment";
	public static final String ALBUM_COMMENT = "album_comment";
	public static final String POST_LIKE = "post_like";
	public static final String ALBUM_LIKE = "album_like";
	public static final String FOLLOW = "follow";
	public static final int UNREAD = 0;
	public static final int READ = 1;
	/*
	 * 保存新产生的通知
	 */
	public void newNotification(Long senderid, Long receiverid, String title, String message) {
		log.info("new notification");
		QueryHelper helper = new QueryHelper();
		MessageText messageText = new MessageText();
		messageText.setTitle(title);
		messageText.setMessage(message);
		messageText.setQueryHelper(helper);
		Long messageId = messageText.Save();
		log.info(senderid + " send a notification to " + receiverid + " for" + title);
		Message msg = new Message();
		msg.setMessageid(messageId);
		msg.setSenderid(senderid);
		msg.setReceiverid(receiverid);
		msg.setQueryHelper(helper);
		msg.Save();
		log.info("save message text : "+title);
		helper.closeConnection();
	}
	/*
	 * 根据message id删除通知
	 */
	public void deleteNotifation(Long id) {
		QueryHelper helper = new QueryHelper();
		Message msg = new Message(id);
		msg.setQueryHelper(helper);
		msg.Delete();
		helper.closeConnection();
	}
	/*
	 * 更新通知状态
	 */
	public void updateNotificationStatus(Long id, int status) {
		QueryHelper helper = new QueryHelper();
		helper.update("update message set status=? where id=?", status, id);
		helper.closeConnection();
	}
}
