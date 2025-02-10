package com.gn.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/createCookie")
public class CreateCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CreateCookieServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("===연결확인===");
		//1.쿠키생성 a태그는 무조건 doGet메소드
		Cookie c = new Cookie("user_id","user01");
		c.setMaxAge(60*60*24);
		response.addCookie(c);
		//2. root 경로 이동 , 생성하기 누르면 쿠키 생성 후 다시 root 페이지로
		response.sendRedirect("/");
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

}
