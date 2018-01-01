package com.et.controller.order;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.Order;
import com.et.tool.PageTool;

public class OrderList extends HttpServlet {
	Order order = new Order();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			String curPage= request.getParameter("curPage");
			Integer curPageInt=1;
			System.out.println("µ±Ç°Ò³£º"+curPage);
			if(curPage!=null){
				curPageInt=Integer.parseInt(curPage);
			}
			try {
				PageTool orderList=order.getFoodPager( curPageInt);			
	            request.setAttribute("orderList",orderList);
	            request.getRequestDispatcher("/detail/orderList.jsp").forward(request, response);
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
