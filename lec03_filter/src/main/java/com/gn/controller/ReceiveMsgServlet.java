package com.gn.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//filter를 위해 name생성
@WebServlet(name="receiveMsgServlet" , urlPatterns = "/receive/msg")
public class ReceiveMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ReceiveMsgServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8"); ->필터로 걸어줄거야
		System.out.println( "----  확인 ---- ");
		
		
		String msg = request.getParameter("msg");
		System.out.println(msg);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/msgShow.jsp");
		
		request.setAttribute("msg", msg);
		
		view.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
