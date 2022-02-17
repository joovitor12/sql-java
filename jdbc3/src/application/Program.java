package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat sdf  = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DB.getConnection();
			
			ps = conn.prepareStatement(
					"insert into seller"
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "values "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, "John Krasinski");
			ps.setString(2, "jim@dundermifflin.com");
			ps.setDate(3, new java.sql.Date(sdf.parse("20/10/1979").getTime()));
			ps.setDouble(4, 3000.0);
			ps.setInt(5, 4);
			
			
			/*ps = conn.prepareStatement("insert into department (Name) values ('D1'), ('D2')" , 
					Statement.RETURN_GENERATED_KEYS);
					*/
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! ID = " + id);
				}
			}else {
				System.out.println("None rows have been affected");
			}
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatements(ps);
			DB.closeConnection();
		}
}
}