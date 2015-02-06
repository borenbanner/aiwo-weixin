package com.aiwo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aiwo.server.connect.Config;
import com.aiwo.server.pojo.Device;
import com.aiwo.server.pojo.User;
import com.aiwo.server.service.UserService;
import com.aiwo.server.service.WeixinService;
import com.aiwo.util.CommonUtil;

public class LoginServlet extends HttpServlet {

	private UserService userService = new UserService();
	private WeixinService wx = new WeixinService();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
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

		String key = request.getParameter("key");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String openId = request.getParameter("openId");

		
		//		System.out.println(key);
		User user = this.userService.login(userName, password);

		if (user != null) {

			wx.bindAppUser(String.valueOf(user.getUserId()), openId);

			List<Device> deviceList = this.userService.getAllDate(user
					.getUserId());
			if(deviceList.size()<1){
				request.getRequestDispatcher("no.jsp?noCache="+CommonUtil.getFileName()).forward(request,
						response);
			}else{
				request.getSession(true).setAttribute("userId", user.getUserId());
				request.getRequestDispatcher("index.jsp?noCache="+CommonUtil.getFileName()).forward(request,
						response);

			}
		} else {

			request.setAttribute("error", "用户名或密码错误！！");
			response.sendRedirect("login.jsp");
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {

	}

}
