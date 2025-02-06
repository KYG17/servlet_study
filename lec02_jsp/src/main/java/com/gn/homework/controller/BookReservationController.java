package com.gn.homework.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/borrow")
public class BookReservationController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("userName");
		String phone = req.getParameter("userPhone");
		String email = req.getParameter("userEmail");
		String [] book = req.getParameterValues("select");
		String number = req.getParameter("days");
		
		//결과값 확인하기
//		System.out.println(name);
//		System.out.println(phone);
//		System.out.println(email);
//		for(int i = 0 ; i<book.length ; i++) {
//			System.out.println(book[i]);
//		}
//		System.out.println(number);
		
		
		
		int overdate = 0;
		if(!number.equals("")) {
			overdate = Integer.parseInt(number) - 1;
		}
		
		int borrowFee = 0;
		 
			switch(book[0]) {
			case "1" : borrowFee = 1500; break;
			case "2" : borrowFee = 1800; break;
			case "3" : borrowFee = 2000; break;
	
		}	
		int totalprice = borrowFee;
		for(int i = 1 ; i <= overdate ; i++) {
			totalprice += 500;
		}
		System.out.println(totalprice);
		
		
		
		
		
//		int borrowFee = 500 * Integer.parseInt(number);
//		Map<String,String>map = new HashMap<String,String>();
//		map.put("1","1500");
//		map.put("2","1800");
//		map.put("3","2000");
////		String[] arr = new String[book.length];
//		for(int i = 0 ; i < book.length ; i++) {
//			book[i] = map.get(book[i]);
//			if(number.equals("1")) {
//				map.get(book[i]);
//			}else {
//				map.get(book[i]+borrowFee);
//			}
//		}
//		System.out.println(map);
		
		
		RequestDispatcher view = req.getRequestDispatcher("views/bookConfirmation.jsp");
		
		req.setAttribute("name", name);
		req.setAttribute("phone", phone);
		req.setAttribute("email", email);
		req.setAttribute("book", book);
		req.setAttribute("number", number);
		req.setAttribute("totalprice", totalprice);
		
		view.forward(req, resp);
		
		
		
	}

	
}
