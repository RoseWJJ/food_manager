package com.et.controller.desk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.DeskModel;
import com.et.tool.PageTool;

public class DeskServlet extends HttpServlet {

	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		DeskModel desk = new DeskModel();
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("dname");
		String curPage= request.getParameter("curPage");
		Integer curPageInt=1;
		System.out.println("µ±Ç°Ò³£º"+curPage);
		if(curPage!=null){
			curPageInt=Integer.parseInt(curPage);
		}
		try {
			PageTool tableList=DeskModel.getTableLsitPager(name,curPageInt);			
            request.setAttribute("tableList",tableList);
            request.getRequestDispatcher("/detail/boardList.jsp").forward(request, response);
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
