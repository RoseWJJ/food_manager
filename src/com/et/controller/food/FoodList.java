package com.et.controller.food;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.Food;
import com.et.tool.PageTool;

public class FoodList extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Food f = new Food();
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("FOODNAME");
		String curPage= request.getParameter("curPage");
		Integer curPageInt=1;
		System.out.println("µ±Ç°Ò³£º"+curPage);
		if(curPage!=null){
			curPageInt=Integer.parseInt(curPage);
		}
		try {
			PageTool foodList=f.getFoodPager(name, curPageInt);			
            request.setAttribute("food",foodList);
            request.getRequestDispatcher("/detail/foodList.jsp").forward(request, response);
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
