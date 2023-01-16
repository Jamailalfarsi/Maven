import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class deletData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/dataapi";

		// Username and password to access DB
		// Custom initialization
		String user = "root";
		String pass = "root";

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter id: ");
		Integer idInput = scanner.nextInt();

		String sql2 = "delete from University where id ='"+idInput + "'";
		Connection con1 = null;

		try {

			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Registering drivers
			DriverManager.registerDriver(driver);

			// Reference to connection interface
			con1 = DriverManager.getConnection(url, user, pass);

			// Creating a statement
			Statement st = (Statement) con1.createStatement();
			int m = st.executeUpdate(sql2);

		}

		// Catch block to handle exceptions
		catch (Exception ex) {
			// Display message when exceptions occurs
			System.err.println(ex);
		}

	}

}
