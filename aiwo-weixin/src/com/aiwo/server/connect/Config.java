package com.aiwo.server.connect;

import java.util.ResourceBundle;

/**
 * 
 * 读取资源文件、定义常量内容
 * 
 * 
 * @author Liuxp
 * 
 */

public class Config {

	public static final String WXBROWSER = "MicroMessenger" ; 
	public static final String QUERY = "query";
	public static final String INSERT = "insert";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	public static final String APPID = "wxa6309997e9c15c83";
	public static final String APPSECRET = "259b1a189b3b8375970c57951ae80617";
	public static final String URL = "http%3A%2F%2Fwww.59so.com%2FoauthServlet%3Fstate%3DKEY";
	public static final String QUERYSINGLE = "querysigle";
	public static final String SITE = "http://weixin.eworlding.com";
	public static String WEBURL = "https%3A%2F%2Fopen.weixin.qq.com%2Fconnect%2Foauth2%2Fauthorize%3Fappid%3DAPPID%26redirect_uri%3DURLURL%26response_type%3Dcode%26scope%3Dsnsapi_userinfo%26state%3D1%23wechat_redirect";

	/**
	 * 
	 * 根据key 提取value
	 * 
	 * @param key
	 * @return
	 */
	public static String getData(String key) {

		ResourceBundle rb = ResourceBundle.getBundle("config");

		return rb.getString(key);
	}

//	 public static void main(String[] args) {
//	 System.out.println(Config.getData("url"));
//	 }
}
