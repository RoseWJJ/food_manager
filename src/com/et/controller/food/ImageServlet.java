package com.et.controller.food;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final static String IMG_PATH = "E:/myImage/";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("imageServlert");
		String imgName = request.getParameter("imgName");

		System.out.println(imgName);
		File f = new File(IMG_PATH + "/" + imgName);
		System.out.println(f.exists());
		BufferedImage bi = ImageIO.read(f);
		ImageIO.write(bi, "png", response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
