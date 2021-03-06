package com.sharetour.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.sharetour.model.Post;
import com.sharetour.model.PostTag;
import com.sharetour.model.Subscription;
import com.sharetour.model.UserInfo;
import com.sharetour.util.QueryHelper;

public class SubscriptionDAO {
	
	
	public boolean saveSub(Long uid, Long tid){
		boolean status = false;
		QueryHelper helper = new QueryHelper();
		Subscription sub = new Subscription();
		sub.setTid(tid);
		sub.setUid(uid);
		sub.setQueryHelper(helper);
		if(sub.Save()>0){
			status = true;
		}
		helper.closeConnection();
		return status;
	}
	
	public boolean undoSub(Long uid, String tagname){
		boolean status = false;
		QueryHelper helper = new QueryHelper();
		if(helper.delete("delete subscription " +
						 "from subscription, posts_tags " +
						 "where tid=posts_tags.id and uid=? and tagname=?", 
						 uid, tagname) > 0){
			status = true;
		}
		helper.closeConnection();
		return status;
	}
	
	public Subscription findSubTagOfUser(Long uid, String tagname){
		QueryHelper helper = new QueryHelper();
		Subscription sub = helper.get(Subscription.class, 
						   "select uid, tid " +
						   "from subscription, posts_tags " +
						   "where subscription.tid=posts_tags.id " +
						   "and uid=? and tagname=?",
						   uid, tagname);
		helper.closeConnection();
		return sub;
	}
	
	
	public List<PostTag> getAllTagsOfUser(Long uid){
		QueryHelper helper = new QueryHelper();
		List<PostTag> tags = helper.executeQuery(PostTag.class, 
								   "select tagname " +
								   "from subscription left join posts_tags " +
								   "on subscription.tid=posts_tags.id " +
								   "where uid=?", 
								   uid);
		helper.closeConnection();
		return tags;
	}
	
	
	public List<UserInfo> getAllUsersOfTag(String tagname){
		QueryHelper helper = new QueryHelper();
		List<UserInfo> users = helper.executeQuery(UserInfo.class, 
									 "select users.id, username " +
									 "from subscription, users, posts_tags " +
									 "where subscription.uid=users.id " +
									 "and subscription.tid=posts_tags.id " +
									 "and posts_tags.tagname=?", 
									 tagname);
		helper.closeConnection();
		return users;
	}
	
	public List<Post> getPostsOfTags(Collection<Long> tids){
		if(tids == null || tids.size() == 0)
			return null;
		QueryHelper helper = new QueryHelper();
		StringBuffer buffer = new StringBuffer("select posts.id,cover, authorid, title, summary, " +
							  "tags, ctime, likes, visit from posts, post_tag_relation " +
							  "where posts.id=post_tag_relation.pid and post_tag_relation.tid in (");
		Iterator<Long> it = tids.iterator();
		for(int i=0; i<tids.size(); i++){
			if(i != 0){
				buffer.append(",");
			}
			buffer.append("'");
			buffer.append(it.next());
			buffer.append("'");
		}
		buffer.append(");");
		List<Post> posts = helper.executeQuery(Post.class, buffer.toString());
		helper.closeConnection();
		return posts;
	}
}
