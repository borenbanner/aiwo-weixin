package com.aiwo.server.service;

import java.util.Map;

import com.aiwo.server.dao.AddrDao;

public class AddrService {

	private AddrDao addrDao = new AddrDao() ;
	
	
	/**
	 * 
	 * 保存用户设置的地址  挂载到微信用户名下
	 * 
	 * 
	 * @param params
	 */
	public void insertAddr(Map<String, String> params) {

		this.addrDao.insertAddr(params);
	}

}
