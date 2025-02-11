package com.gn.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/createSession")
public class CreateSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CreateSessionServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//true 없으면 만들겠다, 있으면 있는거 가져온다 -> default가 true
		//false 없으면 안만들고 null인 상태로 만들겠다
		HttpSession session = request.getSession();
//							=request.getSession(true);
		
		if(session.isNew() || session.getAttribute("member_id") == null) {
			session.setAttribute("member_id", "user01");
			session.setMaxInactiveInterval(60*30); //10초 , 일반적으로 30분이 국룰
		}
		
		response.sendRedirect("/");
		
		
//		if(session.isNew() ) {
//			//true -> 새로운 세션 객체 생성
//			System.out.println();
//		}else {
//			//false -> 기존의 세션 객체가 반환
//			if(session.getAttribute("member_id")== null) {
//				
//			}
//			
//		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
