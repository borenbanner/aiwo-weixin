package com.aiwo.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.aiwo.server.connect.Config;
import com.aiwo.server.service.UsService;
import com.aiwo.util.CommonUtil;

public class UsServlet extends HttpServlet {

	private UsService usService = new UsService();
	/**
	 * 
	 */
	private static final long serialVersionUID = 2823195602008794323L;

	/**
	 * Constructor of the object.
	 */
	public UsServlet() {
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

		String flg = request.getParameter("flg");

		if (flg.equals(Config.QUERY)) {
			queryUs(request, response);
		} else if (flg.equals(Config.UPDATE)) {
			updateUs(request, response);
		}

	}

	private void updateUs(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		DiskFileItemFactory df = new DiskFileItemFactory();
		String path = request.getRealPath("/control/aboutus");
		df.setSizeThreshold(1024 * 1024);
		Map<String, String> params = new HashMap<String, String>();
		ServletFileUpload fileUpload = new ServletFileUpload(df);
		fileUpload.setHeaderEncoding("utf-8");
		try {
			List<FileItem> listItem = (List<FileItem>) fileUpload
					.parseRequest(request);

			for (FileItem list : listItem) {
				if (list.isFormField()) {
					params.put(list.getFieldName(), list.getString("utf-8"));
				} else {

					int index = list.getName().indexOf("\\");
					String fileName = list.getName().substring(index + 1);
					if (fileName != "") {
						String f = CommonUtil.getFileName()+fileName.substring(fileName.lastIndexOf(".")) ;
						list.write(new File(path, f));
						params.put("usImage", "aboutus/" + f);
					}
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.usService.updateUs(params);
		
		
		response.sendRedirect("control/aboutus.jsp") ;
	}

	private void queryUs(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String us = this.usService.queryUs();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(us);
		pw.flush();
		pw.close();
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
