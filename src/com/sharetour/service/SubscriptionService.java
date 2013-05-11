package com.sharetour.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sharetour.dao.SubscriptionDAO;
import com.sharetour.model.Post;
import com.sharetour.model.PostTag;
import com.sharetour.model.UserInfo;

public class SubscriptionService {
	
	private static Log log = LogFactory.getLog(SubscriptionService.class);
	private SubscriptionDAO subdao;
	
	public SubscriptionService(){
		subdao = new SubscriptionDAO();
	}
	
	/*
	 * 订阅标签
	 * @param Long uid
	 * @param String tagname
	 * @return
	 */
	public boolean subscribe(Long uid, String tagname){
		Long tid = new TagService().getTagId(tagname);
		if(subdao.saveSub(uid, tid)){
			return true;
		}
		else{
			return false;
		}
	}
	
	/*
	 * 取消标签订阅
	 * @param Long uid
	 * @param String tagname
	 * @return 
	 */
	public boolean undoSubscribe(Long uid, String tagname){
		return subdao.undoSub(uid, tagname);
	}
	
	/*
	 * 获得某人订阅的所有标签
	 * @param Long uid
	 * @return
	 */
	public List<PostTag> getAllTagsOfUser(Long uid){
		return subdao.getAllTagsOfUser(uid);		
	}
	
	/*
	 * 检查用户关于某标签的订阅状态
	 * @param Long uid
	 * @param String tagname
	 * @return 返回true表示已经订阅, false相反
	 */
	public boolean checkSubStatus(Long uid, String tagname){
		return subdao.findSubTagOfUser(uid, tagname)==null?false:true;
	}
	
	/*
	 * 获得订阅某标签的所有人
	 * @param String tagname
	 * @return 
	 */
	public List<UserInfo> getAllUsersOfTag(String tagname){
		return subdao.getAllUsersOfTag(tagname);
	}
	
	/*
	 * 获得与tag set中对应的所有的post
	 * @param Set<Posttag> tags
	 * @return 
	 */
	public List<Post> getPostsOfTags(List<String> tags){
		List<Long> tids = new TagService().getTagsId(tags);
		return subdao.getPostsOfTags(tids);
	}
	
	/*
	 * 获得某人订阅的所有文章
	 * @param Long uid
	 * @return
	 */
	public List<Post> getPostsOfSubByUser(Long uid){
		List<PostTag> tlist = getAllTagsOfUser(uid);
		List<String> tags = new ArrayList<String>();
		for(PostTag tag:tlist){
			tags.add(tag.getTagname());
		}
		return getPostsOfTags(tags);
	}
	
	
}
