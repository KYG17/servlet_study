package com.gn.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.member.service.MemberService;
import com.gn.member.vo.Member;


@WebServlet(name = "memberCreateEndServlet",urlPatterns="/memberCreateEnd")
public class MemberCreateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MemberCreateEndServlet() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("member_id");
		String pw = request.getParameter("member_pw");
		String name = request.getParameter("member_name");
//		System.out.println(id);
//		System.out.println(pw);
//		System.out.println(name);
		Member m = new Member();
		m.setMemberId(id);
		m.setMemberPw(pw);
		m.setMemberName(name);
		
		//Service에 데이터 전달
		int result = new MemberService().createMember(m);
		//회원가입이 성공적으로 완료 , 혹은 안될 때 화면 전환
		//잘못된 가정을 먼저 쓰고
		//잘됐을때 조건 식으로 쓰고 , view.foward를 밖에다 빼줌
		//반복되는 코드가 너무 많기 때문에!!
		RequestDispatcher view = request.getRequestDispatcher("/views/member/create_fail.jsp");
		if(result > 0) {
			view = request.getRequestDispatcher("/views/member/create_success.jsp");
		}
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
