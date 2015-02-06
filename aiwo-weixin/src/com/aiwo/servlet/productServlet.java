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

import com.aiwo.server.pojo.Product;
import com.aiwo.server.service.ProductService;
import com.aiwo.util.CommonUtil;

public class productServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6917806365100651282L;

	private ProductService productServcie = new ProductService();

	/**
	 * Constructor of the object.
	 */
	public productServlet() {
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
		String method = request.getParameter("method");

		if ("insert".equals(method)) {
			insertMethod(request, response);
		} else if ("update".equals(method)) {
			updateProduct(request, response);
		} else if ("queryAll".equals(method)) {
			queryAll(request, response);
		} else if ("querysigle".equals(method)) {
			querySilge(request, response);
		} else if ("delete".equals(method)) {
			delete(request, response);
		}
	}

	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) {

		String proId = request.getParameter("proId");
		this.productServcie.delete(proId);
	}

	private void querySilge(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String proId = request.getParameter("proId");
		String update = request.getParameter("update");
		Product product = this.productServcie.queryObject(proId);
		if (update == null) {
			request.getSession().setAttribute("productsigel", product);
			response.sendRedirect("control/releaseup.jsp");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			JSONObject jsonObject = JSONObject.fromObject(product);
			pw.write(jsonObject.toString());
			pw.flush();
			pw.close();
		}

	}

	/**
	 * 
	 * 查询所有数据
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void queryAll(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String page = request.getParameter("page");
		List<Product> proList = this.productServcie.queryAll(page);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("productList", proList);
		int total = this.productServcie.getCount();
		total = total % 10 == 0 ? total / 10 : (total / 10) + 1;
		mapJson.put("size", total);
		JSONObject jsonObject = JSONObject.fromObject(mapJson);
		pw.write(jsonObject.toString());
		pw.flush();
		pw.close();
	}

	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	private void updateProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		DiskFileItemFactory df = new DiskFileItemFactory();
		String path = request.getRealPath("/control/product");
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
						params.put("proImage", "/control/product/" + f);
					}
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String proId = params.get("proId");
		params.remove("proId");

		this.productServcie.update(params, proId);

		response.sendRedirect(this.getServletContext().getContextPath()
				+ "/control/release.jsp");
	}

	@SuppressWarnings("deprecation")
	private void insertMethod(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		DiskFileItemFactory df = new DiskFileItemFactory();
		String path = request.getRealPath("/control/product");
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
						params.put("proImage", "/control/product/" + f);
					}
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		productServcie.insertProduct(params);

		response.sendRedirect(this.getServletContext().getContextPath()
				+ "/control/release.jsp");
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
