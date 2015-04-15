package com.aiwo.server.service;

import java.util.List;
import java.util.Map;

import com.aiwo.server.dao.AddrDao;
import com.aiwo.server.dao.ProductDao;
import com.aiwo.server.pojo.Addr;
import com.aiwo.server.pojo.Product;

public class ProductService {

	
	private ProductDao productDao = new ProductDao();
	private AddrDao addrDao = new AddrDao();
	
	/**
	 * 
	 * 插入产品信息
	 * 
	 * 
	 * @param params
	 */
	public void insertProduct(Map<String, String> params) {
		
		this.productDao.insert(params);
	}

	
	/**
	 * 
	 * 
	 * 参数
	 * 
	 * @param params
	 * 
	 * 
	 * @param proId
	 */
	
	
	public void update(Map<String, String> params, String proId) {
		
		this.productDao.update(params,proId);
	}

	
	/**
	 * 
	 * 
	 * 
	 * 查询所有的内容
	 * @return
	 */

	public List<Product> queryAll(String page) {
		
		int  pageNum = 1 ;
		if(page == null){
			pageNum =  1 ;
		}else{
			pageNum = Integer.parseInt(page) ;
		}
		
		int total = this.productDao.queryCount();
		total = total%10==0 ? total/10:(total/10)+1 ;
		
		if(pageNum>total&&total>0){
			pageNum = total ;
		}
		
		int pageCount = (pageNum-1) * 10 ;
		
		List<Product>  listProduct = this.productDao.queryAll(pageCount);
		
		return listProduct;
	}


	
	/**
	 * 
	 * 实体查询
	 * @param proId
	 * @return
	 */
	public Product queryObject(String proId) {
			
		return this.productDao.queryObject(proId);
	}


	public int getCount() {
		
		return this.productDao.queryCount() ;
	}


	public void delete(String proId) {
		this.productDao.delete(proId) ;
	}


	/**
	 * 
	 * 获取用户下的
	 * @param openId
	 * @return
	 */
	public Addr getAddr(String openId) {
		
		return this.addrDao.queryAddr(openId);
		
	}		
	
	
	
	
	

	
}
