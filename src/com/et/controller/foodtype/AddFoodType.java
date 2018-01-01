package com.et.controller.foodtype;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.FoodTypeModel;



public class AddFoodType extends HttpServlet {

	static FoodTypeModel fym = new FoodTypeModel();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String TYPENAME=request.getParameter("TYPENAME");
		System.out.println("添加的菜系名"+TYPENAME);
		try {
				fym.saveFoodType((TYPENAME));
			request.getRequestDispatcher("ListFoodTypeServlet").forward(request, response);
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
