package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement rs = null;
		try {
			conn = DB.getConnection();
			rs = conn.prepareStatement(
					"delete from seller "
					+ "where "
					+ "Id = ? "
					);
			rs.setInt(1, 8);
			
			int rows = rs.executeUpdate();
			
			System.out.println("Done! " + rows + " row(s) affected.");
		}
		catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatements(rs);
			DB.closeConnection();
		}
}
}