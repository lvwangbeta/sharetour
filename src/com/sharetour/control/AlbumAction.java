package com.sharetour.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sharetour.model.Album;
import com.sharetour.model.UserInfo;
import com.sharetour.service.AlbumService;
import com.sharetour.util.Action;
import com.sharetour.util.JSONHelper;

public class AlbumAction implements Action{

	private static final Log log = LogFactory.getLog(AlbumAction.class);
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error("decode error");
			return Action.DECODEERROR;
		}
		String paramjson = null;
		try {
			paramjson = JSONHelper.retrieveJSON(request.getReader());
			log.info("request json:"+paramjson);
		} catch (IOException e) {
			log.error("format params to json error");
			return Action.JSONRETERROR;
		}
		Album album = JSONHelper.json2obj(paramjson, Album.class);
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		album.setUid(user.getId());
		album.setUsername(user.getUsername());
		if(new AlbumService().saveAlbum(album)){
			log.info("album save success");
			return Action.SUCCESS;
		}
		log.error("album save error");
		return Action.ERROR;
	}
}
