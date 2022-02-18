package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		Statement rs = null;
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			rs = conn.createStatement();
			int rows = rs.executeUpdate("update seller set BaseSalary = 7000 where DepartmentId = 4");
			
			/*int x = 1;
			if(x < 2) {
				throw new SQLException("Fake error");
			}
			*/
			int rows2 = rs.executeUpdate("update seller set BaseSalary = 13000 where DepartmentId = 2");
			conn.commit();
			System.out.println(rows);
			System.out.println(rows2);
		}
		catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! caused by " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to roll back! caused by " + e1.getMessage());
				
			}
		}
		finally {
			DB.closeStatements(rs);
			DB.closeConnection();
		}
}
}