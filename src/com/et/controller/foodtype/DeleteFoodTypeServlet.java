package com.et.controller.foodtype;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.FoodTypeModel;

public class DeleteFoodTypeServlet extends HttpServlet {

	static FoodTypeModel fym = new FoodTypeModel();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			String TYPEID= request.getParameter("deleteid");
			System.out.println("É¾³ýid"+TYPEID);
			try {
				fym.deleteFoodType(TYPEID);
				request.getRequestDispatcher("/ListFoodTypeServlet").forward(request, response);
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
