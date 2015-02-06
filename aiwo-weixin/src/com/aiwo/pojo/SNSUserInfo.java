package com.aiwo.pojo;

import java.util.List;

/**
 * 
 * 
 * 
 * 通过网页授权获取用户息相关信息
 * 
 * @author Liuxp
 * @date 2015年1月4日 10:46:12
 */
public class SNSUserInfo {
	// 用户标识
	private String openId;
	// 用户昵称
	private String nickname;
	// 用户性别
	private int sex;
	// 国家
	private String country;
	// 省份
	private String province;
	// 城市
	private String city;
	// 头像连接
	private String headImgUrl;
	// 用户特权信息
	private List<String> privilegeList;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public List<String> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<String> privilegeList) {
		this.privilegeList = privilegeList;
	}
}
