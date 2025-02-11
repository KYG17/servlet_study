package com.gn.account.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.account.vo.Account;


@WebServlet("/loginEnd")
public class LoginEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginEndServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountId = request.getParameter("account_id");
		String accountPw = request.getParameter("account_pw");
		String rememberId = request.getParameter("remember_id");
		
		System.out.println("아이디 : " + accountId);
		System.out.println("비밀번호 : " + accountPw);		
		System.out.println("아이디 저장 유무 : " + rememberId);
		
		//1. 아이디,비밀번호 일치하는 데이터 있는지 확인 -> 데이더 베이스에게 애네 있니?
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Account account = null;
		try {
			//Select
			//기준 아이디와 비밀번호 일치(LIKE X)
			//전체 정보 조회 -> account 객체에 담기
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/login_project";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url,id,pw);
			String sql = "SELECT * FROM account WHERE account_id = ? AND account_pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1 , accountId );
			pstmt.setString(2, accountPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				account = new Account();
				account.setAccountNo(rs.getInt("account_no"));
				account.setAccountId(rs.getString("account_id"));
				account.setAccountPw(rs.getString("account_pw"));
				account.setAccountName(rs.getString("account_name"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		//2-1 있으면 -> 사용자의 정보 (번호,아이디,비밀번호,이름) 담고 있는 객체 Session에 저장
		//		   -> 아이디 정보 저장 O : Cookie에 아이디 저장
		if(account != null) {
			//		   -> 아이디 정보 저장 X : 저장 X
			//         -> 홈 화면 이동 : 로그인한 사용자 정보 노출
			
			System.out.println(account);
		}else {
			//2-2 없으면 -> 로그인 페이지 다시 요청
			
			System.out.println("아무코토 없는데용");
		}
		
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
