package kr.co.jimmy.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {
	
	public Connection getConnection() {
		Connection con = null;
		try {
			InitialContext initctx = new InitialContext();
			Context ctx = (Context) initctx.lookup("java:comp/env/");
			DataSource ds = (DataSource) ctx.lookup("jdbc/oracle");
			con = ds.getConnection();
		} catch (NamingException e) {
			System.out.println("네이밍 오류");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("sql 오류");
			e.printStackTrace();
		}
		return con;
	}

	public void ConnectionClose(Connection con, Statement stmt, ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
