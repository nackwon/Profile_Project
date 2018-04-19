package kr.co.jimmy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.jimmy.VO.GuestVO;
import kr.co.jimmy.connection.ConnectionManager;

public class GuestDAO {

	public void BoardWrite(GuestVO vo) {

		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO guest_tbl VALUES (seq_guest_no.nextval,?,?,?,sysdate)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());

			int count = pstmt.executeUpdate();

			if (count > 0) {
				System.out.println("삽입 완료");
			} else {
				System.out.println("삽입  실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mgr.ConnectionClose(con, pstmt, null);
		}

	}

	public int BoardDelete(String password, String no) {
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "DELETE FROM guest_tbl WHERE no LIKE ? AND password LIKE ?";
		int count = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = 1;
				return count;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.ConnectionClose(con, pstmt, rs);
		}
		return count;
	}

	public ArrayList<GuestVO> BoardList() {
		ArrayList<GuestVO> list = null;
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GuestVO vo = null;

		String sql = "SELECT * FROM guest_tbl";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<GuestVO>();

			while (rs.next()) {
				vo = new GuestVO();

				int no = rs.getInt(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String content = rs.getString(4);
				String req_date = rs.getString(5);

				vo.setNo(no);
				vo.setName(name);
				vo.setPassword(password);
				vo.setContent(content);
				vo.setReg_date(req_date);

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
}
