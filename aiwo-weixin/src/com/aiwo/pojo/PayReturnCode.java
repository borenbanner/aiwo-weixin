package com.aiwo.pojo;

/**
 * 
 * 
 * <xml> <return_code><![CDATA[SUCCESS]]></return_code>
 * <return_msg><![CDATA[OK]]></return_msg>
 * <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
 * <mch_id><![CDATA[10000100]]></mch_id>
 * <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>
 * <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>
 * <result_code><![CDATA[SUCCESS]]></result_code>
 * <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>
 * <trade_type><![CDATA[JSAPI]]></trade_type> </xml>
 * 
 * 
 * 
 * @author Liuxp
 * 
 */
public class PayReturnCode {

	public String appid;
	public String mch_id;
	public String nonce_str;
	public String sign;
	public String result_code;
	public String prepay_id;
	public String trade_type;
	public String return_code;
	public String return_msg;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public PayReturnCode(String appid, String mch_id, String nonce_str,
			String sign, String result_code, String prepay_id,
			String trade_type, String return_code, String return_msg) {
		super();
		this.appid = appid;
		this.mch_id = mch_id;
		this.nonce_str = nonce_str;
		this.sign = sign;
		this.result_code = result_code;
		this.prepay_id = prepay_id;
		this.trade_type = trade_type;
		this.return_code = return_code;
		this.return_msg = return_msg;
	}

	public PayReturnCode() {
		super();
	}

}
