package com.aiwo.server.pojo;

public class Mac {

	private int user_mac;
	private int mac_id;
	private String macName;
	private String mac;

	public int getUser_mac() {
		return user_mac;
	}

	public void setUser_mac(int user_mac) {
		this.user_mac = user_mac;
	}

	public int getMac_id() {
		return mac_id;
	}

	public void setMac_id(int mac_id) {
		this.mac_id = mac_id;
	}

	public String getMacName() {
		return macName;
	}

	public void setMacName(String macName) {
		this.macName = macName;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Mac() {
		super();
	}

	public Mac(int user_mac, int mac_id, String macName, String mac) {
		super();
		this.user_mac = user_mac;
		this.mac_id = mac_id;
		this.macName = macName;
		this.mac = mac;
	}

}
