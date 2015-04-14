package com.aiwo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aiwo.pojo.WeixinOauth2Token;
import com.aiwo.server.connect.Config;
import com.aiwo.server.pojo.Product;
import com.aiwo.server.service.ProductService;
import com.aiwo.util.AdvancedUtil;

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

//		String noCache = CommonUtil.getFileName();
//		String nonceStr = AdvancedUtil.getNonceStr();
//		String timeStamp = AdvancedUtil.getTimeStamp();
		// String key = "jfaeiNFA8inoaenFOa4neanFIOAEnfoa" ;

		System.out.println("网页授权获取的accesstoken:" + wx.getAccessToken());
//		String prepareSign = "accesstoken=" + wx.getAccessToken() + "&appid="
//				+ Config.APPID + "&noncestr=" + nonceStr + "&timestamp="
//				+ timeStamp + "&url=" + request.getScheme() + "://"
//				+ request.getServerName() + request.getContextPath()
//				+ "/pay/pay.jsp?code="+code+"&state=123";

		System.out.println("accesstoken：" +  wx.getAccessToken());
		System.out.println("code:" + code);
		//String addrSign = AdvancedUtil.getaddrSign(prepareSign);

		request.getSession().setAttribute("id", id);
		request.getSession().setAttribute("describe", product.getProName());
		request.getSession().setAttribute("price", product.getProPrice());
		request.getSession().setAttribute("code", code) ;
		request.getSession().setAttribute("accesstoken", wx.getAccessToken()) ;
		//request.getSession().setAttribute("addrSign", addrSign);
//		request.getSession().setAttribute("timeStamp", timeStamp);
//		request.getSession().setAttribute("nonceStr", nonceStr);

//		System.out.println("共享地址签名："+addrSign);
		System.out.println("网页授权获取用户的openid:" + wx.getOpenId());

		request.getSession().setAttribute("openId", wx.getOpenId());
		response.sendRedirect(request.getContextPath() + "/pay/pay.jsp?code="+code+"&state=123");
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
