package com.sharetour.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;
import com.sharetour.dao.UserDAO;
import com.sharetour.model.UserInfo;

/*
 * 用户信息服务类
 * 完成注册信息检测，登录信息检测任务
 */
public class UserService {
	
	private UserDAO userdao;
	public UserService(){
		userdao = new UserDAO();
	}
	/*
	 * 检测用户名是否重复
	 * exist return true
	 * not exist return false
	 */
	public static boolean checkUsername(String username){
		
		if(new UserDAO().checkUsername(username) == null){
			return false;
		}
		return true;
		
	}
	/*
	 * 检测邮箱格式是否合法
	 */
	public static boolean emailValidate(String email){
		return false;
	}
	
	/*
	 * 检测邮箱是否存在
	 */
	public static boolean checkEmailExist(String email){
		return false;
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
		return new UserDAO().find(username, EncoderByMD5(password));
	}
	
}
