package com.sharetour.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Encoder;
import com.sharetour.dao.UserDAO;
import com.sharetour.model.UserInfo;

/*
 * 用户信息服务类
 * 完成注册信息检测，登录信息检测任务
 */
public class UserService {
	private UserDAO userdao;
	private static Log log = LogFactory.getLog(UserService.class);
	
	public UserService(){
		userdao = new UserDAO();
	}
	
	/*
	 * field empty check
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
	public static boolean checkUsernameExist(String username){
		
		if(new UserDAO().checkUsername(username) == null){
			return false;
		}
		return true;
		
	}
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
	public static UserInfo loginCheck(String username, String password){
		log.info(username+" login");
		return new UserDAO().find(username, EncoderByMD5(password));
	}
	
	
	/*
	 * 注册新用户
	 */
	public static boolean Register(UserInfo user){
		user.setPassword(EncoderByMD5(user.getPassword()));
		log.info("register new user:"+user.getUsername());
		return new UserDAO().registerNewUser(user);
	}
	
	/*
	 * 根据用户名获得ID
	 */
	public static int getAuthorid(String username){
		return new UserDAO().getAuthorid(username);
	}
}
