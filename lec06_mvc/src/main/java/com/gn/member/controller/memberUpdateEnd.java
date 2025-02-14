package com.gn.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.gn.member.service.MemberService;
import com.gn.member.vo.Member;

/**
 * Servlet implementation class memberUpdateEnd
 */
@WebServlet(name="memberupdateend" , urlPatterns="/memberUpdateEnd")
public class memberUpdateEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public memberUpdateEnd() {
        super();
      
    }

	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("member_name");
		String pw = request.getParameter("member_pw");
		String no = request.getParameter("member_no");
		System.out.println(name + pw + no);
		
		JSONObject obj = new JSONObject();
		obj.put("res_code", "500");
		obj.put("res_msg", "수정 중 오류가 발생했어요🥹");
		
		int result = new MemberService().updateMember(name,pw,no);
		
		if(result > 0 ) {
			//session 재설정
			//(1) member_no 정보를 기준으로 단일 회원 정보(Member)조회
			//(2) 새롭게 조회된 Member 정보를 Session에 재 설정
			HttpSession session = request.getSession(false);
			Member m = new MemberService().RealUpdateMember(no);
					session.setAttribute("member" , m);
					session.setMaxInactiveInterval(60*30);
			
		
			
			//정상적으로 실행하였을 때의 코드는 200
			obj.put("res_code", "200");
			obj.put("res_msg", "정상적으로 수정되었습니다");
		}
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(obj);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
