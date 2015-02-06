package com.aiwo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.aiwo.server.pojo.Device;
import com.aiwo.server.service.UserService;

public class ShowDeviceSingle extends HttpServlet {

	private static final long serialVersionUID = 9132448963089837489L;
	private static UserService userService = new UserService();

	/**
	 * Constructor of the object.
	 */
	public ShowDeviceSingle() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy();
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

		String userId = request.getParameter("userId");
		List<Device> deviceList = userService.getAllDate(Integer
				.parseInt(userId));
		// System.out.println(deviceList.size());
		// deviceList.
		String mac = request.getParameter("mac");
		Device device = userService.getDevice(deviceList, mac);
		if (device != null) {
			deviceList.remove(deviceList.indexOf(device));
			deviceList.add(0, device);

			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.write(JSONArray.fromObject(deviceList).toString());
			pw.flush();
			pw.close();
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
