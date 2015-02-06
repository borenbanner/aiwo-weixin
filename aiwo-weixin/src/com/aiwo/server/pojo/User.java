package com.aiwo.server.pojo;

public class User {

	private int userId;
	private String userName;
	private String userPwd;
	private String weixinBand;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getWeixinBand() {
		return weixinBand;
	}

	public void setWeixinBand(String weixinBand) {
		this.weixinBand = weixinBand;
	}

	public User(int userId, String userName, String userPwd, String weixinBand) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.weixinBand = weixinBand;
	}

	public User() {
		super();
	}

}
