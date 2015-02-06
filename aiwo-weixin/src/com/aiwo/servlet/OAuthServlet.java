package com.aiwo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aiwo.pojo.SNSUserInfo;
import com.aiwo.pojo.WeixinOauth2Token;
import com.aiwo.server.connect.Config;
import com.aiwo.server.pojo.Device;
import com.aiwo.server.service.UserService;
import com.aiwo.server.service.WeixinService;
import com.aiwo.util.AdvancedUtil;
import com.aiwo.util.CommonUtil;

/**
 * 
 * 网页授权回调请求处理
 * 
 * @author Liuxp
 * 
 * @date 2015年1月4日 11:47:41
 * 
 */
public class OAuthServlet extends HttpServlet {

	private static final int INDEX = 1;
	private static final int MALL = 2;
	private static WeixinService wx = new WeixinService();
	private UserService userService = new UserService();
	public String openId;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// 用户授权后，能获取到code
		String code = request.getParameter("code");
		System.out.println("网页授权code:" + code);
		// System.out.println("code" + code);
		// 用户同意授权
		if (!"authdeny".equals(code)) {
			// 获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil
					.getOauth2AccessToken(Config.APPID, Config.APPSECRET, code);
			// 网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();

			// 获取凭证为空时 再获取一次
			if (accessToken == null) {
				WeixinOauth2Token weixinOauth2Token2 = AdvancedUtil
						.getOauth2AccessToken(Config.APPID, Config.APPSECRET,
								code);
				accessToken = weixinOauth2Token2.getAccessToken();
			}

			// 用户标识
			openId = weixinOauth2Token.getOpenId();
			// 获取用户信息
			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken,
					openId);

			// 设置要传递的参数
			request.getSession(true).setAttribute("openId", openId);
			request.setAttribute("snsUserInfo", snsUserInfo);
		}
		// 跳转到index.jsp

		String state = request.getParameter("state");
		int key = Integer.parseInt(state);
		System.out.println(wx.isbandWx(openId));
		int userId = wx.getUserId(openId);
		if (userId == 0) {
			request.getSession(true).setAttribute("key", key);
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		} else {
			List<Device> deviceList = this.userService.getAllDate(userId);
			switch (key) {
			case INDEX:
				if (deviceList.size() < 1) {
					request.getRequestDispatcher(
							"no.jsp?noCache=" + CommonUtil.getFileName())
							.forward(request, response);
				} else {
					request.getSession(true).setAttribute("deviceList",
							deviceList);
					request.getSession(true).setAttribute("userId", userId);
					request.getRequestDispatcher(
							"index.jsp?noCache=" + CommonUtil.getFileName())
							.forward(request, response);
				}

				break;
			case MALL:
				if (deviceList.size() < 1) {
					request.getRequestDispatcher(
							"no.jsp?noCache=" + CommonUtil.getFileName())
							.forward(request, response);
				} else {
					request.getSession(true).setAttribute("userId", userId);
					request.getRequestDispatcher("manager.jsp").forward(
							request, response);
				}
				break;
			default:
				break;
			}

		}

	}
}
