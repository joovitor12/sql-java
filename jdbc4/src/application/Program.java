package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement rs = null;
		try {
			conn = DB.getConnection();
			rs = conn.prepareStatement(
					"update seller "
					+ "set BaseSalary = BaseSalary + ? "
					+ "where "
					+ "(DepartmentId = ?)");
			rs.setDouble(1, 200.0);
			rs.setInt(2, 3);
			
			int rows = rs.executeUpdate();
			
			System.out.println("Done! " + rows + " affected.");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatements(rs);
			DB.closeConnection();
		}
}
}