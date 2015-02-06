package com.aiwo.server.pojo;

public class UserMac {

	private int user_mac;
	private String mac;
	private int userId;

	public int getUser_mac() {
		return user_mac;
	}

	public void setUser_mac(int user_mac) {
		this.user_mac = user_mac;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public UserMac() {
		super();
	}

	public UserMac(int user_mac, String mac, int userId) {
		super();
		this.user_mac = user_mac;
		this.mac = mac;
		this.userId = userId;
	}

}
