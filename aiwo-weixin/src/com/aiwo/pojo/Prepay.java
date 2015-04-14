package com.aiwo.pojo;

public class Prepay {

	public String appid; // 公众号Id
	public String mch_id;// 商户号
	public String nonce_str;// 随机字符串
	public String sign; // 签名
	public String body; // 商品描述
	public String out_trade_no;// 商户订单
	public int total_fee; //总额
	public String spbill_create_ip; //终端ip
	public String notify_url;  
	public String trade_type;
	public String openid;

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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Prepay(String appid, String mch_id, String nonce_str, String sign,
			String body, String out_trade_no, int total_fee,
			String spbill_create_ip, String notify_url, String trade_type,
			String openid) {
		super();
		this.appid = appid;
		this.mch_id = mch_id;
		this.nonce_str = nonce_str;
		this.sign = sign;
		this.body = body;
		this.out_trade_no = out_trade_no;
		this.total_fee = total_fee;
		this.spbill_create_ip = spbill_create_ip;
		this.notify_url = notify_url;
		this.trade_type = trade_type;
		this.openid = openid;
	}

	public Prepay() {
		super();
	}

}
