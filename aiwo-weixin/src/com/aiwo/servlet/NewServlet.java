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

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.aiwo.server.connect.Config;
import com.aiwo.server.pojo.New;
import com.aiwo.server.service.NewService;
import com.aiwo.util.CommonUtil;

public class NewServlet extends HttpServlet {

	private NewService newServcie = new NewService();
	/**
	 * 
	 */
	private static final long serialVersionUID = -7316863050476581007L;

	/**
	 * Constructor of the object.
	 */
	public NewServlet() {
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

		String method = request.getParameter("news");

		if (method.equals(Config.QUERY)) {
			queryNews(request, response);
		} else if (method.equals(Config.INSERT)) {
			insertNews(request, response);
		} else if (method.equals(Config.UPDATE)) {
			updateNews(request, response);
		} else if (method.equals(Config.DELETE)) {
			deleteNews(response, request);
		} else if (method.equals(Config.QUERYSINGLE)) {
			querySigleNew(request, response);
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void querySigleNew(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String newId = request.getParameter("newId");
		String page = request.getParameter("page");
		New n = this.newServcie.queryNew(newId);
		if (page != null) {
			request.getSession(true).setAttribute("newSingle", n);
			response.sendRedirect(this.getServletContext().getContextPath()
					+ "/control/newsup.jsp");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			JSONObject jsonObjet = JSONObject.fromObject(n);
			pw.write(jsonObjet.toString());
			pw.flush();
			pw.close();
		}

	}

	/**
	 * 
	 * 
	 * @param response
	 * @param request
	 */

	private void deleteNews(HttpServletResponse response,
			HttpServletRequest request) {
		String newId = request.getParameter("newId");
		this.newServcie.deleteNew(newId);
	}

	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	private void updateNews(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		DiskFileItemFactory df = new DiskFileItemFactory();
		String path = request.getRealPath("/control/new");
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
						String f = CommonUtil.getFileName()
								+ fileName.substring(fileName.lastIndexOf("."));
						list.write(new File(path, f));
						params.put("newImage", "/control/new/" + f);
					}
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String newId = params.get("newId");
		params.remove("newId");

		this.newServcie.updateNew(params, newId);

		response.sendRedirect(this.getServletContext().getContextPath()
				+ "/control/news.jsp");

	}

	private void queryNews(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		List<New> newList = this.newServcie.queryAll(page);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("newList", newList);
		int total = this.newServcie.getCount();
		total = total % 10 == 0 ? total / 10 : (total / 10) + 1;
		mapJson.put("size", total);
		JSONObject jsonObject = JSONObject.fromObject(mapJson);
		pw.write(jsonObject.toString());
		pw.flush();
		pw.close();

	}

	@SuppressWarnings("deprecation")
	private void insertNews(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		DiskFileItemFactory df = new DiskFileItemFactory();
		String path = request.getRealPath("/control/new");
		df.setSizeThreshold(1024 * 1024);
		Map<String, String> params = new HashMap<String, String>();
		ServletFileUpload fileUpload = new ServletFileUpload(df);
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
						String f = CommonUtil.getFileName()
								+ fileName.substring(fileName.lastIndexOf("."));
						list.write(new File(path, f));
						params.put("newImage", "/control/new/" + f);
					}
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.newServcie.insertNew(params);

		response.sendRedirect(this.getServletContext().getContextPath()
				+ "/control/news.jsp");

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
