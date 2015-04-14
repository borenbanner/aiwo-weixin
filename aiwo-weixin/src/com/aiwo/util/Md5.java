package com.aiwo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class Md5 {

	public final static String getPwd(String pwd) throws UnsupportedEncodingException
	{
		
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		
		byte[] strTemp = pwd.getBytes("utf-8");
		char str[];
		MessageDigest mdTemp;
		try {
			mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			
			int j = md.length;

			str = new char[j * 2];

			int k = 0;

			for (int i = 0; i < j; i++) {

			byte b = md[i];
			
			    str[k++] = hexDigits[b >> 4 & 0xf];

			    str[k++] = hexDigits[b & 0xf] ;
			   }
			
		} catch (NoSuchAlgorithmException e) {
			return null ;
		}
		
		return new String(str);
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(Md5.getPwd("appid=wxa6309997e9c15c83&body=CHOCO LIGHT巧克氛围灯&mch_id=1234087902&nonce_str=6MhzljPyyNZBeYJCZeQp2V3ENhNOcShJ&notify_url=http://weixin.eworlding.com/p ay/notifyServlet/&openid=oWOl7ji9PSDkDjgIv_9fohHBnn84&out_trade_no=2015142891213&spbill_create_ip=123.116.125.76&total_fee=15000&trade_type=JSAPI&key=jfaeiNFA8inoaenFOa4neanFIOAEnfoa"));
	}
}
