package com.sharetour.control;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sharetour.model.UserInfo;
import com.sharetour.service.SubscriptionService;
import com.sharetour.util.Action;

public class SubscriptionAction implements Action{

	public static Log log = LogFactory.getLog(SubscriptionAction.class);
	@Override
	public String execute(HttpServletRequest request) {
		log.info("in subscribe action");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			return ERROR;
		}
		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
		if(user == null){
			log.info("not login");
			return NOLOGIN;
		}
		else{
			Long uid = user.getId();
			String tagname = request.getParameter("tagname");
			try {
				tagname = new String(tagname.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				log.info("decode error");
				return DECODEERROR;
			}
			String action = request.getParameter("action");
			if("sub".equals(action)){
				if(new SubscriptionService().subscribe(uid, tagname)){
					log.info(uid+" subscribe "+tagname+" success");
					return SUCCESS;
				}
			}
			else if("undosub".equals(action)){
				if(new SubscriptionService().undoSubscribe(uid, tagname)){
					log.info(uid+" undo subscribe "+tagname+" success");
					return SUCCESS;
				}
			}
			log.info(uid+" "+action+" "+tagname+" error");
		}
		return ERROR;
	}

}
