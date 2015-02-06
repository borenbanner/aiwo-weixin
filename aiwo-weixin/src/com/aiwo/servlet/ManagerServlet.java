package com.aiwo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.aiwo.server.pojo.Device;
import com.aiwo.server.pojo.Mac;
import com.aiwo.server.service.UserService;

public class ManagerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 689990874433088400L;
	private UserService userService = new UserService();
	private static final int INSERT = 111;
	private static final int UPDATE = 222;
	private static final int DELETE = 333;
	private static final int QUERY = 444;

	/**
	 * Constructor of the object.
	 */
	public ManagerServlet() {
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
	 * @param Mac
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String method = request.getParameter("method");

		if (method != null) {
			int key = Integer.parseInt(method);
			switch (key) {
			case INSERT:
				// 预留方法

				// 是否允许微信用户添加设备 手动添加设备 是否无限制 app添加可以通过扫描 和搜索添加设备
				// 限制用户手动添加避免无效数据

				String macMa = request.getParameter("mac");
				String macName = request.getParameter("macName");

				Map<String, String> fieldMap = new HashMap<String, String>();
				fieldMap.put("mac", macMa);
				fieldMap.put("macName", macName);

				insert(fieldMap, userId);

				break;
			case UPDATE:

				String macId = request.getParameter("macId");
				String macNameUpdate = request.getParameter("macName");
				Map<String, String> mapUpdate = new HashMap<String, String>();
				mapUpdate.put("macId", macId);
				mapUpdate.put("macName", macNameUpdate);
				update(mapUpdate);
				break;
			case DELETE:

				String macDeleteId = request.getParameter("macId");
				delete(macDeleteId);
				break;

			case QUERY:
				
				List<Device> macList = userService.getAllDate(Integer.parseInt(userId)) ;
				JSONArray json = JSONArray.fromObject(macList) ;
				response.setContentType("text/html;charset=utf-8");
				PrintWriter pw = response.getWriter();
				pw.write(json.toString());
				pw.flush();
				pw.close();
				break;

			default:
				break;
			}
		} else {
			List<Mac> macList = userService.getAllMac(userId);

			request.getSession(true).setAttribute("macList", macList);
		}
	}

	/**
	 * 
	 * 
	 * 设备管理的各种处理方式
	 * 
	 * 
	 * 
	 * @param mapUpdate
	 */
	private void update(Map<String, String> mapUpdate) {
		this.userService.update(mapUpdate);
	}

	private void insert(Map<String, String> fieldMap, String userId) {
		this.userService.insert(fieldMap, userId);
	}

	private void delete(String macId) {
		this.userService.delete(macId);
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
