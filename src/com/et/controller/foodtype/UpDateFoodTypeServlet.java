package com.et.controller.foodtype;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.FoodTypeModel;

public class UpDateFoodTypeServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		FoodTypeModel ftm = new FoodTypeModel();
		String TYPEID= request.getParameter("typeID");
		System.out.println("ÐÞ¸Äid"+TYPEID);
		String TYPENAME =request.getParameter("TYPENAME");
		System.out.println("ÐÞ¸ÄÖµ"+TYPENAME);
		try {
			ftm.updateFoodType(TYPEID, TYPENAME);	
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
