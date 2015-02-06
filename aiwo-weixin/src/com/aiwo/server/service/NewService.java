package com.aiwo.server.service;

import java.util.List;
import java.util.Map;

import com.aiwo.server.dao.NewDao;
import com.aiwo.server.pojo.New;

public class NewService {
	
	
	
	private NewDao newDao = new NewDao();
	/**
	 * 
	 * 
	 * @param params
	 */

	public void insertNew(Map<String, String> params) {
		
		this.newDao.insertNew(params);
	}
	public void updateNew(Map<String, String> params, String newId) {
		
		this.newDao.update(params, newId) ;
	}
	public List<New> queryAll(String page) {
		
		
		int  pageNum = 1 ;
		if(page == null){
			pageNum =  1 ;
		}else{
			pageNum = Integer.parseInt(page) ;
		}
		
		int total = this.newDao.getCount();
		total = total%10==0 ? total/10:(total/10)+1 ;
		
		if(pageNum>total&&total>0){
			
			pageNum = total ;
			
		}
		
		int pageCount = (pageNum-1) * 10 ;
		
		List<New>  listNew = this.newDao.queryAll(pageCount);
		
		
		
		return listNew;
	}
	public int getCount() {
		return  this.newDao.getCount();
	}

	
	
	public New queryNew(String newId) {
		
		
		return this.newDao.queryNew(newId);
	}
	public void deleteNew(String newId) {

		this.newDao.deleteNew(newId);
	}

}
