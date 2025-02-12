package com.gn.controller.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;


@WebServlet("/GuestBookEndServlet")
public class GuestBookEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public GuestBookEndServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String guestArr = request.getParameter("guestArr");
		String msg = request.getParameter("msg");
		System.out.println(guestArr);
		System.out.println(msg);
		JSONObject o1 = new JSONObject();
		o1.put("name", guestArr);
		o1.put("message", msg);
		
		response.setContentType("application/json; charset = utf-8");
		response.getWriter().print(o1);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
