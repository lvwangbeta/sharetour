/*
 * 用户信息类
 */

package com.sharetour.model;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.sharetour.util.POJO;

public class UserInfo extends POJO implements Serializable {

	private static final long serialVersionUID = -6477608831652054079L;
	private String username;
	private String password;
	private String email;
	private int type;		//用户类型
	private int gender;		//0 male; 1 female
	private String birth;
	private String contry;
	private String province;
	private String city;
	private Date reg_time;
	private int status;
	private Date this_login_time;
	private String this_login_ip;
	private String nickname;
	private String intro;
	
	public UserInfo()
	{
		super("users");
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getContry() {
		return contry;
	}
	public void setContry(String contry) {
		this.contry = contry;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getReg_time() {
		return reg_time;
	}
	public void setReg_time(Date reg_time) {
		this.reg_time = reg_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getThis_login_time() {
		return this_login_time;
	}
	public void setThis_login_time(Date this_login_time) {
		this.this_login_time = this_login_time;
	}
	public String getThis_login_ip() {
		return this_login_ip;
	}
	public void setThis_login_ip(String this_login_ip) {
		this.this_login_ip = this_login_ip;
	}
	
	@Override
	public Map<String, Object> ListInsertableFields() {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", getUsername());
		map.put("password", getPassword());
		map.put("email", getEmail());
		map.put("type", getType());
		map.put("gender", getGender());
		map.put("birth", getBirth());
		return map;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
}
