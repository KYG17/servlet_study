package com.gn.member.dao;
import static com.gn.common.sql.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gn.member.vo.Member;
public class MemberDao {
	
	//createMember 메소드
	//매개변수로 Connection , Member 받아서
	//DB에 INSERT(member_id,member_pw,member_name)
	//ResultSet X , exectueUpdate O
	//결과를 int로 반환
	public int createMember(Member m , Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0 ;
		try {
			String sql = "INSERT INTO member (member_id,member_pw,member_name) "
						+ "VALUES(?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			result = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public Member loginMember(String id, String pw , Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//Member m 을 null 로 선언
		Member m = null;
		try {
			String sql = "SELECT * FROM member WHERE member_id = ? AND member_pw = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//그리고 if문 안에서 m을 재할당
				m = new Member();
				m.setMemberNo(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPw(rs.getString("member_pw"));
				m.setMemberName(rs.getString("member_name"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}

}
