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

public class UpdataFoodServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Food f = new Food();
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String FOODID = null;
		String FOODNAME = null;
		String TYPEID = null;
		String PRICE = null;
		String IMAGEPATH = null;
		String spaPath = "E:/myImage";
		String destPath = null;
		String fileName=null;
		if (ServletFileUpload.isMultipartContent(request)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			List fileItems = null;
			ServletContext sc = this.getServletContext();
			// 获取绝对路径；
			// String absultPath = sc.getRealPath("/myImage");

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
					if (fi.getFieldName().equals("foodId")) {
						FOODID = fi.getString();
					} else if (fi.getFieldName().equals("foodName")) {
						FOODNAME = fi.getString("UTF-8");
					} else if (fi.getFieldName().equals("typeId")) {
						TYPEID = fi.getString("UTF-8");
					} else if (fi.getFieldName().equals("price")) {
						PRICE = fi.getString("UTF-8");
					}		
				}else {// 非文本框及文件
					// 获取文件名
					fileName = fi.getName();
					System.out.print(fileName+"----------");
					// 获取文件流
					InputStream is = fi.getInputStream();
					// 定义路径
					destPath = spaPath + "/" + fileName;
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
		}
		try {
			f.updateFood(FOODID, FOODNAME, TYPEID, PRICE, fileName);
			request.getRequestDispatcher("FoodList").forward(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);

	}

}
