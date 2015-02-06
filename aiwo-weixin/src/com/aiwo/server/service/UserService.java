package com.aiwo.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aiwo.server.dao.UserDao;
import com.aiwo.server.pojo.Device;
import com.aiwo.server.pojo.Mac;
import com.aiwo.server.pojo.User;

public class UserService {

	private UserDao userDao = new UserDao();

	/**
	 * 
	 * 
	 * 查询用户
	 * 
	 * 
	 * @param userName
	 * 
	 *            用户名称
	 * 
	 * @param userPwd
	 * 
	 *            用户密码
	 * @return
	 * 
	 *         返回user实体
	 * 
	 */
	public User login(String userName, String userPwd) {

		return this.userDao.login(userName, userPwd);

	}

	/**
	 * 
	 * 根据用户Id获取用户的设备信息
	 * 
	 * @param userId
	 */
	public List<Device> getAllDate(int userId) {

		List<Mac> macList = this.userDao.getMac(userId);
		if(macList.size()<1){
			return new ArrayList<Device>() ;
		}
		
		List<Device> deviceList = this.userDao.getAllDate(macList);

		return deviceList;
	}

	/**
	 * 
	 * 
	 * 获取mac码在List中的对象
	 * 
	 * @param deviceList
	 * 
	 * 
	 *            设备列表
	 * @param mac
	 * 
	 * 
	 * 
	 *            mac吗
	 * @return
	 * 
	 * 
	 * 
	 *         返回设备对象
	 */

	public Device getDevice(List<Device> deviceList, String mac) {

		for (int i = 0; i < deviceList.size(); i++) {

			if (mac.equals(deviceList.get(i).getMac())) {
				return deviceList.get(i);
			}
		}

		return null;
	}

	/**
	 * 
	 * 
	 * 查找用户下所有的设备
	 * @param userId
	 * @return
	 */
	public List<Mac> getAllMac(String userId) {

		return this.userDao.getMac(Integer.parseInt(userId));
		
	}

	
	
	/**
	 * 
	 * 
	 * 通过微信 用户手动添加设备
	 * 
	 * @param fieldMap
	 * 
	 * 
	 */
	public void insert(Map<String, String> fieldMap,String userId) {
		
		int macId = this.userDao.insert(fieldMap) ;
		
		
	   	this.userDao.insertUserMac(userId,macId) ;
	   	
	}

	/**
	 * 
	 * 微信用户修改设备名称
	 * 
	 * @param mapUpdate
	 * 	
	 * 		参数
	 */
	
	public void update(Map<String, String> mapUpdate) {

			this.userDao.update(mapUpdate);
			
	}

	
	/**
	 * 
	 * 微信用户删除
	 * 
	 * @param macId
	 */
	public void delete(String macId) {
		
		this.userDao.delete(macId);
		
	}
	
//	public static void main(String[] args) {
//		
//		
//		Map map = new HashMap();
//		map.put("mac", "faeafeafae");
//		map.put("macName", "我饿饿");
//		
//		String userId = "10" ;
//		
//		
//		new UserService().insert(map, userId) ;
//	}
	
}
