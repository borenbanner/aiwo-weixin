package com.aiwo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.aiwo.pojo.PayReturnCode;
import com.aiwo.server.connect.Config;
import com.aiwo.util.AdvancedUtil;
import com.aiwo.util.CommonUtil;
import com.aiwo.util.Md5;

public class PrepayIdServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public PrepayIdServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String appid = Config.APPID;
		String mch_id = "1234087902";
		String nonce_str = AdvancedUtil.getNoceStrPay();
		String body = request.getParameter("body");
		String productid = request.getParameter("productid") ;
		String timeStamp = AdvancedUtil.getTimeStamp();
		String out_trade_no = "2015" + timeStamp;
		String total_fee = String.valueOf((int) (Float.parseFloat(request
				.getParameter("total_fee")) * 100));
		String spbill_create_ip = request.getRemoteAddr();
		String notify_url = "http://weixin.eworlding.com/pay/notifyServlet/";
		String trade_type = "JSAPI";
		String openId = request.getParameter("openId");
//		String key = "jfaeiNFA8inoaenFOa4neanFIOAEnfoa";

		Map<String,String> map = new TreeMap<String,String>();
		map.put("appid", appid);
		map.put("attach", productid) ;
		map.put("mch_id", mch_id);
		map.put("nonce_str", nonce_str);
		map.put("body", body);
		map.put("out_trade_no", out_trade_no);
		map.put("total_fee", total_fee);
		map.put("spbill_create_ip", spbill_create_ip);
		map.put("notify_url", notify_url);
		map.put("trade_type", trade_type);
		map.put("openid", openId);
		
		String sign = getSign(map);

//		Prepay p = new Prepay(appid, mch_id, nonce_str, sign, body,
//				out_trade_no, Integer.parseInt(total_fee), spbill_create_ip,
//				notify_url, trade_type, openId);
//		String xml = MessageUtil.ObejctToxml(p);
		String xml = "<xml>" +
				"<appid>"+appid+"</appid>" +
				"<attach>"+productid+"</attach>"+
				"<mch_id>"+mch_id+"</mch_id>"+
				"<body>"+body+"</body>"+
				"<nonce_str>"+nonce_str+"</nonce_str>"+
				"<notify_url>"+notify_url+"</notify_url>"+
				"<openid>"+openId+"</openid>"+
				"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
				"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
				"<total_fee>"+total_fee+"</total_fee>"+
				"<trade_type>JSAPI</trade_type>"+
				"<sign>"+sign+"</sign>"+
				"</xml>" ;
//		System.out.println(xml);
		String requestUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		PayReturnCode payCode = (PayReturnCode) CommonUtil.httpsRequestxml(
				requestUrl, "GET", xml);

		String prepay_id = "prepay_id=" + payCode.getPrepay_id();

		System.out.println("prepay:"+prepay_id);
		Map finalMap = new TreeMap();
		finalMap.put("appId", appid);
		finalMap.put("timeStamp", timeStamp);
		finalMap.put("nonceStr", nonce_str);
		finalMap.put("package", prepay_id);
		finalMap.put("signType", "MD5");

		String paySign = getSign(finalMap) ;

		Map pageMap = new HashMap();
		pageMap.put("appId", Config.APPID);
		pageMap.put("timeStamp", timeStamp);
		pageMap.put("packages", prepay_id);
		pageMap.put("nonceStr", nonce_str);
		pageMap.put("paySign", paySign);

		String pageJson = JSONObject.fromObject(pageMap).toString();
//		System.out.println(pageJson);
		PrintWriter out = response.getWriter();
		out.write(pageJson);
		out.flush();
		out.close();
	}

	/**
	 * 
	 * 
	 *  返回加密签名
	 *  
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String getSign(Map<String, String> map) throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer() ;
		for (Map.Entry<String, String> m : map.entrySet()) {
			sb.append(m.getKey() + "=" + m.getValue() + "&");
		}
		sb.append("key=jfaeiNFA8inoaenFOa4neanFIOAEnfoa");
		String s = sb.toString() ;
//		System.out.println(s);
		return Md5.getPwd(s).toUpperCase() ;
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
