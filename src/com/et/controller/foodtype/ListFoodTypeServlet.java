package com.et.controller.foodtype;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.et.model.FoodTypeModel;
import com.et.tool.PageTool;

public class ListFoodTypeServlet extends HttpServlet {

	static FoodTypeModel ftm = new FoodTypeModel();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String updateID= request.getParameter("updateID");
		String typename = request.getParameter("typename");
		String curPage= request.getParameter("curPage");
		Integer curPageInt=1;
		System.out.println("��ǰҳ��"+curPage);
		if(curPage!=null){
			curPageInt=Integer.parseInt(curPage);
		}
		try {
			PageTool FoodTypeList=ftm.getFoodTypePager(typename,curPageInt);			
            request.setAttribute("FoodTypeList",FoodTypeList);
            request.setAttribute("updateID", updateID);
            System.out.println("��Ʒ����");
            request.getRequestDispatcher("/detail/cuisineList.jsp").forward(request, response);
     
		} catch (Exception e) {					 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
		
	}

}
