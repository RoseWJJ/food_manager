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

		// �Ƿ����ϴ��ļ�������
		Boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			// ��Ϊ�ļ��ϴ������������� ����Ҫ�����������ļ� ��Ҫnew һ���ļ��ϴ��������������ļ��ϴ�������
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// ����һ���µ��ļ��ϴ��������

			// ServletFileUpload��ר�Ž����ļ��ϴ������ ���ڴ������н������ļ�
			ServletFileUpload upload = new ServletFileUpload(factory);
			// �����ϴ����ļ���С�����ֵ upload.setSize
			upload.setHeaderEncoding("UTF-8");
			List fileItems = null;
			ServletContext sc = this.getServletContext();
			// ��ȡ����·����
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
				// �����ϴ����ļ���
				FileItem fi = (FileItem) i.next();
				if (fi.isFormField()) {// fi.isFormFiled()==true ��ʾ��������ı��� �����ļ�
					if (fi.getFieldName().equals("cid")) {
						cid = fi.getString();
					} else if (fi.getFieldName().equals("foodname")) {
						foodName = fi.getString("UTF-8");
					} else if (fi.getFieldName().equals("price")) {
						price = fi.getString();
					}

				} else {// ���ı����ļ�
					// ��ȡ�ļ���
					fileName = fi.getName();
					System.out.print(fileName);
					// ��ȡ�ļ���
					InputStream is = fi.getInputStream();
					// ����·��
					destPath = spaPath + "/" + fileName;
					System.out.println(destPath);
					// ����� ���ϴ������ݽ��л�д
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
