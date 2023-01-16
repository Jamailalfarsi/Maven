import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class dataManagment {

	//public static void updateById() {
	public static void main(String[] args) throws IOException, InterruptedException {
		String url = "jdbc:mysql://localhost:3306/dataapi";

		// Username and password to access DB
		// Custom initialization
		String user = "root";
		String pass = "root";

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter id: ");
		Integer idInput = scanner.nextInt();

		System.out.println("Enter alpha_two_code: ");
		String alpha_two_codeInput = scanner.next();

		

		String sql2 = "UPDATE University SET alpha_two_code='" + alpha_two_codeInput 
				+ "' WHERE id='" + idInput + "'";
		
		Connection con = null;

		try {

			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Registering drivers
			DriverManager.registerDriver(driver);

			// Reference to connection interface
			con = DriverManager.getConnection(url, user, pass);

			// Creating a statement
			java.sql.Statement st = con.createStatement();
			int m = st.executeUpdate(sql2);

			

		}

		// Catch block to handle exceptions
		catch (Exception ex) {
			// Display message when exceptions occurs
			System.err.println(ex);
		}
	}

}
