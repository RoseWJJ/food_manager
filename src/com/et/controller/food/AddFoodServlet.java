package com.et.controller.food;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.et.model.Food;

public class AddFoodServlet extends HttpServlet {
	// static String SAVE_DIR="F/myImages";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Food f = new Food();
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cid = null;
		String foodName = null;
		String price = null;
		String destPath = null;
		String fileName=null;
		// String path =
		// "E:/MyEclipseWork/food_manager/WebRoot/detail/style/images";

		// 是否是上传文件的请求
		Boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			// 因为文件上传是在请求体中 所以要从请求中拿文件 就要new 一个文件上传工厂类来处理文件上传的数据
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 创建一个新的文件上传处理程序；

			// ServletFileUpload是专门解析文件上传请求的 用于从请求中解析出文件
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 允许上传的文件大小的最大值 upload.setSize
			upload.setHeaderEncoding("UTF-8");
			List fileItems = null;
			ServletContext sc = this.getServletContext();
			// 获取绝对路径；
			// String absultPath = sc.getRealPath("/myImage");
			String spaPath = "E:/myImage";

			try {
				fileItems = upload.parseRequest(request);
			} catch (FileUploadException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Iterator i = fileItems.iterator();
			while (i.hasNext()) {
				// 处理上传的文件项
				FileItem fi = (FileItem) i.next();
				if (fi.isFormField()) {// fi.isFormFiled()==true 表示输入的是文本框 不是文件
					if (fi.getFieldName().equals("cid")) {
						cid = fi.getString();
					} else if (fi.getFieldName().equals("foodname")) {
						foodName = fi.getString("UTF-8");
					} else if (fi.getFieldName().equals("price")) {
						price = fi.getString();
					}

				} else {// 非文本框及文件
					// 获取文件名
					fileName = fi.getName();
					System.out.print(fileName);
					// 获取文件流
					InputStream is = fi.getInputStream();
					// 定义路径
					destPath = spaPath + "/" + fileName;
					System.out.println(destPath);
					// 输出流 将上传的数据进行回写
					FileOutputStream fis = new FileOutputStream(destPath);
					byte[] b = new byte[1024];
					int readN = -1;
					while ((readN = is.read(b)) != -1) {
						fis.write(b, 0, readN);
					}
					is.close();
					fis.close();
				}
			}
			try {
				f.saveFood(foodName, price, cid, fileName);
				request.getRequestDispatcher("FoodList").forward(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
