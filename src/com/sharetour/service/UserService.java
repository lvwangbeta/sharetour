package com.sharetour.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;

import sun.misc.BASE64Encoder;

import com.sharetour.dao.AvatorDAO;
import com.sharetour.dao.UserDAO;
import com.sharetour.model.Avator;
import com.sharetour.model.UserInfo;

/*
 * 用户信息服务类
 * 完成注册信息检测，登录信息检测任务
 */
public class UserService {
	
	private static final String DEFAULT_AVATOR = "51bb2a557d00f3ac2dabf649";
	private static Log log = LogFactory.getLog(UserService.class);
	private UserDAO userdao = new UserDAO();
	
	/*
	 * field empty check
	 * @param username
	 * @param password
	 * @param confirm
	 * @param email
	 * @param gender
	 */
	public static boolean emptyCheck(String username, String password,String confirm,
			String email, String gender){
		if( (username == null || username.length() == 0)
			|| (password == null || username.length() == 0)
			|| (confirm == null || confirm.length() == 0)
			|| (email== null || email.length() == 0)
			|| (gender == null || gender.length() == 0)
		  ){
			return false;
		}
		else{
			return true;
		}
	}
	
	/*
	 * @param password
	 * @param confirm
	 */
	public static boolean confirmPassword(String password, String confirm){
		if(password.equals(confirm)){
			return true;
		}
		return false;
	}
	/*
	 * 检测用户名是否重复
	 * exist return true
	 * not exist return false
	 */

	/*
	 * 检测邮箱格式是否合法
	 */
	public static boolean emailValidate(String email){
		return true;
	}
	
	/*
	 * 检测邮箱是否存在
	 */
	public static boolean checkEmailExist(String email){
		return false;
	}
	
	/*
	 * gender check
	 * if not validate return false
	 */
	public static boolean checkGenderValidate(String gender){
		if(gender == null || gender.length() == 0){
			return false;
		}
		else{
			if(gender.equals("0") || gender.equals("1")){
				return true;
			}
			return false;
		}
	}
	
	/*
	 * 密码加密
	 */
	public static String EncoderByMD5(String password)
	{
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64 = new BASE64Encoder();
			String encodedpwd = base64.encode(md5.digest(password.getBytes("utf-8")));
			return encodedpwd;
		} catch (NoSuchAlgorithmException e) {
			return password;
		} catch (UnsupportedEncodingException e) {
			return password;
		}		
	}
	
	/*
	 * 登录信息检测
	 */
	public UserInfo loginCheck(String username, String password){
		log.info(username+" login");
		return userdao.find(username, EncoderByMD5(password));
	}
	
	
	/*
	 * 注册新用户
	 */
	public boolean Register(UserInfo user){
		user.setPassword(EncoderByMD5(user.getPassword()));
		log.info("register new user:"+user.getUsername());
		Long uid = userdao.registerNewUser(user);
		setDefaultAvator(uid, user.getUsername());
		return true;
	}
	
	private void setDefaultAvator(Long uid, String username) {
		Avator avator = new Avator();
		avator.setUid(uid);
		avator.setUsername(username);
		avator.setAvatorId(new ObjectId(DEFAULT_AVATOR));
		new AvatorDAO().saveAvator(avator);
	}
	
	/*
	 * 根据用户名获得ID
	 */
	public int getAuthorid(String username){
		return userdao.getAuthorid(username);
	}
	
	public UserInfo findUserById(Long uid) {
		return userdao.findUserById(uid);
	}
	
	public List<UserInfo> getPopUsers(int limit) {
		return userdao.getUsersOrderByPostCount(limit);
	}
	
	public boolean updateAccount(Long uid, String nickname, String intro) {
		if(userdao.updateAccount(uid, nickname, intro) > 0) 
			return true;
		return false;
	}
	public boolean checkUsernameExist(String username){
		
		if(new UserDAO().checkUsername(username) == null){
			return false;
		}
		return true;
		
	}
}
