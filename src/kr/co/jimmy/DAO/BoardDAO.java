package kr.co.jimmy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.jimmy.VO.BoardVO;
import kr.co.jimmy.connection.ConnectionManager;

public class BoardDAO {

	// 게시판 등록
	public void InsertBoard(BoardVO vo) {
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO board VALUES (seq_board_no.nextval, ?,?,0,sysdate,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getUser_no());

			pstmt.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.ConnectionClose(con, pstmt, null);
		}
	}

	// 게시판 리스트
	public ArrayList<BoardVO> ListBoard() {
		ArrayList<BoardVO> list = null;
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO vo = null;

		String sql = "SELECT * FROM board";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVO>();

			while (rs.next()) {
				vo = new BoardVO();
				vo.setNumber(rs.getInt("no"));
				vo.setTitle(rs.getString("title"));
				vo.setUser_no(rs.getInt("user_no"));
				vo.setHit(rs.getInt("hit"));
				vo.setReg_date(rs.getString("reg_date"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mgr.ConnectionClose(con, pstmt, rs);
		}

		return list;
	}

	// 게시판 보기
	public BoardVO SelectBoard(String no) {
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		BoardVO vo = null;
		ResultSet rs = null;
		String sql = "SELECT title, content FROM board WHERE no LIKE ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new BoardVO();
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.ConnectionClose(con, pstmt, rs);
		}
		return vo;
	}

	// 게시판 수정 가능 체크
	public int isCheckBoard(String no) {
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM member,board WHERE member.no = board.user_no AND no LIKE ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return 1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
