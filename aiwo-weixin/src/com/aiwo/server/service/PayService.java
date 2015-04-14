package com.aiwo.server.service;

import java.util.List;
import java.util.Map;

import com.aiwo.pojo.Bill;
import com.aiwo.server.dao.PayDao;

public class PayService {
	
	private PayDao payDao = new PayDao() ;

	
	/**
	 * 
	 * 
	 * 接收解析的数据 插入数据库
	 * 
	 * 
	 * @param map
	 */
	public void insert(Map<String, String> map) {
		this.payDao.insert(map);
	}


	public List<Bill> query(String page) throws Exception {
		
		int  pageNum = 1 ;
		if(page == null){
			pageNum =  1 ;
		}else{
			pageNum = Integer.parseInt(page) ;
		}
		
		int total = this.payDao.getCount();
		
		if(pageNum>total&&total>0){
			pageNum = total ;
		}
		
		int pageCount = (pageNum-1) * 10 ;
		
		return this.payDao.query(pageCount) ;
	}


	/**
	 * 
	 * 
	 * 页数
	 * @return
	 */
	public int getCount() {
		
		int total = this.payDao.getCount();
		total = total % 10 == 0 ? total / 10 : (total / 10) + 1;
		return total;
	}

}
