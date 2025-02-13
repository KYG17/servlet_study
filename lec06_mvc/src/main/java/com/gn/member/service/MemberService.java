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
	//MemberDao에게 Connection과 Member 전달
	//int 반환
	private MemberDao dao = new MemberDao();
	public int createMember(Member m) {
		Connection conn = getConnection();
		int result = dao.createMember(m , conn);
		close(conn);
		return result;
	}
}
