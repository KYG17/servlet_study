package com.gn.board.dao;

import static com.gn.common.sql.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gn.board.vo.Attach;
import com.gn.board.vo.Board;

public class BoardDao {
	public int insertBoard(Board b , Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//insert ,delete,update는 int로 반환!
		int boardNo = 0;
		try {
			String sql = "INSERT INTO `board` (board_title ,board_content ,board_writer) "
						+ "VALUES(?,?,?)";
			//(1) 매개변수 추가
			pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, b.getBoardWriter());
			
			boardNo = pstmt.executeUpdate();
			//(2) 생성된 키 반환
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				boardNo = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardNo;
	}
	
	public int insertAttach(Attach a , Connection conn) {
		// 1. board_no
		// 2. ori_name
		// 3. new_name
		// 4. attach_path
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			String sql = "INSERT INTO `attach` (board_no ,ori_name ,new_name ,attach_path) "
						+ "VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, a.getBoardNo());
			pstmt.setString(2, a.getOriName());
			pstmt.setString(3, a.getNewName());
			pstmt.setString(4, a.getAttachPath());
			result = pstmt.executeUpdate();	
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public List<Board>selectBoardList(Connection conn,Board option){
		// 게시글 번호(board_no) b.board_no
		// 게시글 제목(board_title) b.board_title
		// 게시글 내용(board_content) b.board_content
		// 게시글 작성자 (board_writer) 
		// 게시글 작성자의 닉네임()
		// 게시글 등록인(reg_date)
		// 게시글 수정일(mod_date)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> result = new ArrayList<>();
		try {
			String sql = "SELECT * "
					+ "FROM board b "
					+ "JOIN member m "
					+ "ON b.board_writer = m.member_no ";
			
			if(option.getBoardTitle() != null) {
				sql += "WHERE board_title Like CONCAT('%','"+option.getBoardTitle()+"','%')";
			}
			/////// 추가 !!! 무적권 있어야함! //////
			sql += "LIMIT " +option.getLimitPageNo()+", "+option.getNumPerPage();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board b = new Board();
				b.setBoardNo(rs.getInt("board_no"));
				b.setBoardTitle(rs.getString("board_title"));
				b.setBoardContent(rs.getString("board_content"));
				b.setMemberName(rs.getString("member_name"));
				b.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
				b.setModDate(rs.getTimestamp("mod_date").toLocalDateTime());
				result.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		System.out.println("dao : "+result);
		return result;
	}
	
	public int selectBoardCount(Connection conn , Board option) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0 ;
		try {
			String sql = "SELECT COUNT(*) FROM board ";
			if(option.getBoardTitle() != null) {
				sql += "WHERE board_title Like CONCAT('%','"+option.getBoardTitle()+"','%') ";
			}
			
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public Board selectBoardOne(Connection conn , int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board b = null;
		try {
			String sql = "SELECT * FROM `board` b "
					+ "JOIN `member` m ON b.board_writer = m.member_no "
					+ "JOIN `attach` a ON b.board_no = a.board_no "
					+ "WHERE b.board_no = ? ";
//			sql += "SELECT * FROM attach";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				b = new Board();
				b.setBoardTitle(rs.getString("board_title"));
				b.setBoardContent(rs.getString("board_content"));
				b.setBoardWriter(rs.getInt("board_writer"));
				b.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
				b.setModDate(rs.getTimestamp("mod_date").toLocalDateTime());
				b.setMemberName(rs.getString("member_name"));
				b.setAttachNo(rs.getInt("attach_no"));
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return b;
	}
	
	public Attach selectAttachOne(Connection conn , int attachNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Attach a = null;
		try {
			String sql = "SELECT * FROM `attach` WHERE attach_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attachNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				a = new Attach();
				//rs.get~ 데이터베이스에서 넘어오는 컬럼명
				a.setAttachNo(rs.getInt("attach_no"));
				a.setOriName(rs.getString("ori_name"));
				//new_name은 사실 필요없음
				a.setNewName(rs.getString("new_name"));
				a.setAttachPath(rs.getString("attach_path"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return a;
	}

}
