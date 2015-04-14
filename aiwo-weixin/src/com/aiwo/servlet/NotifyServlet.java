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

import net.sf.json.JSONObject;

import com.aiwo.pojo.Bill;
import com.aiwo.server.service.PayService;
import com.aiwo.util.MessageUtil;

public class NotifyServlet extends HttpServlet {

	private PayService payService = new PayService() ;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public NotifyServlet() {
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
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response) ;
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			try {
				
				//接收微信数据包并解析
				String method = request.getParameter("method") ;
				//System.out.print(method) ;
				if(method.equals("query")){
					queryBill(request,response);
				}else{
					insertPay(request,response) ;
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

	private void insertPay(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String,String> map = MessageUtil.parseXml(request) ;
		
		System.out.println(map);
		
		this.payService.insert(map) ;
		
	}

	private void queryBill(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String page = request.getParameter("page") ;
		
		List<Bill> payList = this.payService.query(page);
		
		//request.getSession().setAttribute("payList", list) ;
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		int total = this.payService.getCount();
		
		mapJson.put("payList", payList);
		mapJson.put("size", total);
		JSONObject jsonObject = JSONObject.fromObject(mapJson);
		pw.write(jsonObject.toString());
		pw.flush();
		pw.close();
		
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
