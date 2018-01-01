package com.et.controller.desk;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.DeskModel;

public class DeleteDeskServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			DeskModel dm = new DeskModel();
			response.setContentType("text/html;charsert=UTF-8");
			request.setCharacterEncoding("UTF-8");
			String deskid= request.getParameter("deleteid");
			System.out.println("É¾³ýid"+deskid);
		
			try {
				dm.deleteDesk(deskid);
				request.getRequestDispatcher("/DeskServlet").forward(request, response);
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
