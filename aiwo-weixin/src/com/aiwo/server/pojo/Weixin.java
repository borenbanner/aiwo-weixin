package com.aiwo.server.pojo;

public class Weixin {

	private int wxId;
	private int userId;
	private String openId;
	private String niceName;
	private String sex;
	private String country;
	private String provice;
	private String city;

	public int getWxId() {
		return wxId;
	}

	public void setWxId(int wxId) {
		this.wxId = wxId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNiceName() {
		return niceName;
	}

	public void setNiceName(String niceName) {
		this.niceName = niceName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvice() {
		return provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Weixin(int wxId, int userId, String openId, String niceName,
			String sex, String country, String provice, String city) {
		super();
		this.wxId = wxId;
		this.userId = userId;
		this.openId = openId;
		this.niceName = niceName;
		this.sex = sex;
		this.country = country;
		this.provice = provice;
		this.city = city;
	}

	public Weixin() {
		super();
	}

}
