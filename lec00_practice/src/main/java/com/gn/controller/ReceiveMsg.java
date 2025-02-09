package com.gn.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/receive/msg")
public class ReceiveMsg extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String msg = req.getParameter("msg");
		System.out.println(msg);
		
		RequestDispatcher view = getServletContext().getRequestDispatcher("/views/msgShow.jsp");	
		req.setAttribute("msg", msg);
		view.forward(req, resp);
	}
	
	

}
