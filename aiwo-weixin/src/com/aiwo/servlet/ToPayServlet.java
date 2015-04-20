package com.aiwo.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aiwo.pojo.WeixinOauth2Token;
import com.aiwo.server.connect.Config;
import com.aiwo.server.pojo.Addr;
import com.aiwo.server.pojo.Product;
import com.aiwo.server.service.ProductService;
import com.aiwo.util.AdvancedUtil;
import com.aiwo.util.CommonUtil;

public class ToPayServlet extends HttpServlet {

	private ProductService productService = new ProductService();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ToPayServlet() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String code = request.getParameter("code");
		Product product = this.productService.queryObject(id);

		WeixinOauth2Token wx = AdvancedUtil.getOauth2AccessToken(Config.APPID,
				Config.APPSECRET, code);
		Addr addr = this.productService.getAddr(wx.getOpenId());
		
		request.getSession().setAttribute("id", id);
		request.getSession().setAttribute("describe", product.getProName());
		request.getSession().setAttribute("price", product.getProPrice());
		System.out.println("网页授权获取用户的openid:" + wx.getOpenId());
		request.getSession().setAttribute("openId", wx.getOpenId());
		request.getSession().setAttribute("addr", addr);
		//System.out.println(addr);
		
		response.sendRedirect(request.getContextPath() + "/pay/pay.jsp?code="+code+"&state=123");
	}

	
	/**
	 * 
	 * 
	 * sha1 签名
	 * 
	 * @param map
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
	private static String getSign(Map<String, String> map) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		StringBuffer sb = new StringBuffer() ;
		for (Map.Entry<String, String> m : map.entrySet()) {
			sb.append(m.getKey() + "=" + m.getValue() + "&");
		}
		String signTmp = sb.toString().substring(0, sb.toString().length()-1); 
		System.out.println("待加密的字符串："+signTmp);
		MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
        digest.update(signTmp.getBytes("GBK"));
        byte messageDigest[] = digest.digest();
        // Create Hex String
        StringBuffer hexString = new StringBuffer();
        // 字节数组转换为 十六进制 数
        for (int i = 0; i < messageDigest.length; i++) {
            String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        return hexString.toString();
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

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Map map = new TreeMap<String, String>() ;
		map.put("accesstoken", "OezXcEiiBSKSxW0eoylIeBFk1b8VbNtfWALJ5g6aMgZHaqZwK4euEskSn78Qd5pLsfQtuMdgmhajVM5QDm24W8X3tJ18kz5mhmkUcI3RoLm7qGgh1cEnCHejWQo8s5L3VvsFAdawhFxUuLmgh5FRA") ;
		map.put("appid", "wx17ef1eaef46752cb") ;
		map.put("noncestr", "123456") ;
		map.put("timestamp", "1384841012");
		map.put("url", CommonUtil.urlEncodeUTF8("http://open.weixin.qq.com/")) ;
		
		String st = getSign(map) ;
		System.out.println(st);
	}
}
