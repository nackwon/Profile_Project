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

		String sql = "INSERT INTO board VALUES (seq_board_no.nextval,?,?,0,sysdate,?)";

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

		String sql = "SELECT b.no, b.title, u.name, b.hit, b.reg_date, b.user_no "
					+ "FROM board b, users u "
					+ "WHERE u.no = b.user_no "
					+ "ORDER BY b.no DESC";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVO>();

			while (rs.next()) {
				vo = new BoardVO();
				vo.setNumber(rs.getInt("no"));
				vo.setTitle(rs.getString("title"));
				vo.setUser_name(rs.getString("name"));
				vo.setHit(rs.getInt("hit"));
				vo.setReg_date(rs.getString("reg_date"));
				vo.setUser_no(rs.getInt("user_no"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mgr.ConnectionClose(con, pstmt, rs);
		}

		return list;
	}
	
	// 게시판 수정 시 선택
	public BoardVO UpdateViewBoard(String no) {
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
	
	// 게시판 보기
	public BoardVO SelectBoard(int no) {
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		BoardVO vo = null;
		ResultSet rs = null;
		String sql = "SELECT no, title, content, hit FROM board WHERE no LIKE ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new BoardVO();
				vo.setNumber(rs.getInt("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				int hit = rs.getInt("hit");
				hit = hit + 1;
				vo.setHit(hit);
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
	public int isCheckBoard(int no, int user_no) {
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT user_no "
					+ "FROM board "
					+ "WHERE user_no LIKE ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if(rs.getInt("user_no") == no)
					return 1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	// 게시판 수정 
	public void UpdateBoard(BoardVO vo, String no) {
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "UPDATE board SET title = ?, content = ? WHERE no LIKE ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, no);
			
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mgr.ConnectionClose(con, pstmt, rs);
		}
	}
	
	//게시판 삭제
	public void DeleteBoard(int no) {
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM board WHERE no LIKE ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.ConnectionClose(con, pstmt, null);
		}
	}
	
	
	//게시판 찾기
	public ArrayList<BoardVO> SearchBoard(String title) {
		ArrayList<BoardVO> list = null;
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO vo = null;
		String sql = "SELECT b.no, b.title, u.name, b.hit, b.reg_date "
				+ "FROM board b, users u "
				+ "WHERE b.user_no = u.no "
				+ "AND b.title LIKE ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+title+"%");
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVO>();
			
			while(rs.next()) {
				vo = new BoardVO();
				
				vo.setNumber(rs.getInt("no"));
				vo.setTitle(rs.getString("title"));
				vo.setUser_name(rs.getString("name"));
				vo.setHit(rs.getInt("hit"));
				vo.setReg_date(rs.getString("reg_date"));

				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.ConnectionClose(con, pstmt, rs);
		}
		return list; 
	}
	
	//조회수 
	public void HitBoard(int no) {
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE board SET hit = nvl(hit,0)+1 WHERE no LIKE ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			mgr.ConnectionClose(con, pstmt, null);
		}
	}
	
	//페이징 처리
	public int PagingBoard() {
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) FROM board";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
}
