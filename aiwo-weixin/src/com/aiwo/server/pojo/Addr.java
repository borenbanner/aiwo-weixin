package com.aiwo.server.pojo;

public class Addr {

	public int addrId; // 编号自增涨
	public String openId; // 微信用户唯一标识
	public String userName; // 用户名称
	public String tel; // 电话
	public String addr; // 地址
	public String postal_code; // 邮编

	public int getAddrId() {
		return addrId;
	}

	public void setAddrId(int addrId) {
		this.addrId = addrId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public Addr() {
		super();
	}

	public Addr(int addrId, String openId, String userName, String tel,
			String addr, String postal_code) {
		super();
		this.addrId = addrId;
		this.openId = openId;
		this.userName = userName;
		this.tel = tel;
		this.addr = addr;
		this.postal_code = postal_code;
	}

}
