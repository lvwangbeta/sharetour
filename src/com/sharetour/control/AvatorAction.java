package com.sharetour.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import com.sharetour.model.Avator;
import com.sharetour.model.Crop;
import com.sharetour.model.UserInfo;
import com.sharetour.service.AvatorService;
import com.sharetour.util.Action;
import com.sharetour.util.JSONHelper;
import com.sharetour.util.ObjectIdGenerator;

public class AvatorAction implements Action{

	private static final Log log = LogFactory.getLog(AvatorAction.class);
	@Override
	public String execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error("request decode error");
			return Action.DECODEERROR;
		}
		
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo)session.getAttribute("user");
		String avator_orig_id = (String)session.getAttribute("avator");
		String crop_info;
		try {
			crop_info = JSONHelper.retrieveJSON(request.getReader());
		} catch (IOException e) {
			log.error("avator crop data retrieve error");
			return Action.ERROR;
		}
		log.info(crop_info);
		Crop crop = JSONHelper.json2obj(crop_info, Crop.class);
		
		AvatorService avatorService = new AvatorService();
		ObjectId id = null;
		try {
			id = avatorService.generateAvatorThumb(new ObjectId(avator_orig_id), crop);
		} catch (IOException e) {
			return ERROR;
		}
		if(id == null)
			return Action.ERROR;
		Avator avator = new Avator();
		avator.setId(ObjectIdGenerator.generate());
		avator.setUid(user.getId());
		avator.setUsername(user.getUsername());
		avator.setAvatorId(id);
		if( avatorService.saveAvator(avator)){
			return Action.SUCCESS;
		}
		return Action.ERROR;
	}

}
