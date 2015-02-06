package com.aiwo.server.service;

import java.util.Map;

import net.sf.json.JSONObject;

import com.aiwo.server.dao.UsDao;
import com.aiwo.server.pojo.Us;

public class UsService {

	
	private UsDao usDao = new UsDao();
	
	
	public void updateUs(Map<String, String> params) {
		
		this.usDao.updateUs(params) ;
	}


	/**
	 * 查询
	 * 
	 * @return
	 */
	public String queryUs() {
		
		Us us = this.usDao.queryUs();
		JSONObject jsonObject = JSONObject.fromObject(us) ;
		String usString = jsonObject.toString() ;
		return usString;
	}

	
	
	
}
