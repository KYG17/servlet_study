package com.gn.member.service;

import com.gn.member.dao.MemberDao;
import com.gn.member.vo.Member;

import java.sql.Connection;
import static com.gn.common.sql.JDBCTemplate.getConnection;
import static com.gn.common.sql.JDBCTemplate.close;


public class MemberService {
	//createMember 메소드
	//Member를 매개변수로 받아서 
	//Connection 객체 생성
	//MemberDao에게 Connection과 MemberM 전달
	//int 반환
	private MemberDao dao = new MemberDao();
	public int createMember(Member m) {
		Connection conn = getConnection();
		int result = dao.createMember(m , conn);
		close(conn);
		return result;
	}
	
	public Member loginMember(String id , String pw) {
		Connection conn = getConnection();
		Member m = dao.loginMember(id,pw,conn);
		close(conn);
		return m;
	}
	
	public int updateMember(String name, String pw , String no) {
		Connection conn = getConnection();
		int result = dao.updateMember(name,pw,no,conn);
		close(conn);
		return result;
	}
	
	public Member RealUpdateMember(String no) {
		Connection conn = getConnection();
		Member m = dao.RealUpdateMember(no , conn);
		close(conn);
		return m;
	}
}
