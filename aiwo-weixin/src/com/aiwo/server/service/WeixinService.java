package com.aiwo.server.service;

import net.sf.json.JSONObject;

import com.aiwo.server.dao.UserDao;
import com.aiwo.server.dao.WeixinDao;

public class WeixinService {

	private UserDao userDao = new UserDao();;
	private WeixinDao weixinDao = new WeixinDao();

	/**
	 * 
	 * 
	 * 用户扫描时记录用户信息并且返回<br>
	 * 该微信是否绑定微信的app端
	 * 
	 * 
	 * @param jsonObject
	 *            用户信息
	 * 
	 * 
	 * @return boolean 是否绑定app用户
	 */

	public boolean scan(JSONObject jsonObject) {

		boolean flg = false;

		// System.out.println(jsonObject);
		this.weixinDao.insertOr(jsonObject);

		flg = this.isbandWx(jsonObject.getString("openid"));

		return flg;

	}

	/**
	 * 是否绑定微信
	 * 
	 * @param openid
	 * @return
	 */
	public boolean isbandWx(String openid) {

		return this.weixinDao.bandWx(openid);
	}

	/**
	 * 
	 * 
	 * 取消关注时删除用户<br>
	 * 
	 * 保留方法 后期项目铺开看具体需求是否进行删除用户操作
	 * 
	 * @param openId
	 * 
	 * 
	 */
	public void unscan(String openId) {

		this.weixinDao.delete(openId);

	}

	/**
	 * 
	 * 微信绑定app用户
	 * 
	 * @param userId
	 * 
	 *            用户Id
	 * @param openId
	 * 
	 *            微信用户唯一标识
	 */
	public void bindAppUser(String userId, String openId) {
		this.weixinDao.bindAppUser(userId, openId);
	}

	/**
	 * 根据微信唯一标识 获取用户信息
	 * 
	 * @param openId
	 * @return
	 */
	public int getUserId(String openId) {

		
		return  this.weixinDao.getUserid(openId);
				
	}

}
