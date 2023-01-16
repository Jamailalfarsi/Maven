import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

import com.google.gson.Gson;

public class DataApi {
	public static void main(String[] args) throws IOException, InterruptedException {
		 String jsonUrl = "http://universities.hipolabs.com/search?country=United+States";
		        HttpClient client = HttpClient.newHttpClient();
		        HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create(jsonUrl))
		                .build();
		        HttpResponse<String> response = client.send(request,
		                HttpResponse.BodyHandlers.ofString());
		       // System.out.println("The JSON of the API is :" +fetchAPI.body());
		        
		       
		     // Creating the connection using Oracle DB
		        // Note: url syntax is standard, so do grasp
		        String url = "jdbc:mysql://localhost:3306/dataapi";

		        // Username and password to access DB
		        // Custom initialization
		        String user = "root";
		        String pass = "root";
		        
		        Gson gsonObject=new Gson();
		        JSON[] fetchGson=gsonObject.fromJson(response.body(),JSON[].class);
		        
		       for (JSON varaible:fetchGson) {
		    	   System.out.println("web_pages is:"+varaible.getWeb_pages()[0]);
		    	   System.out.println("State_province is:"+varaible.getState_province());
		    	   System.out.println("Alpha_two_code is:"+varaible.getAlpha_two_code());
		    	   System.out.println("Name is:"+varaible.getName());
		    	   System.out.println("Country is:"+varaible.getCountry());
		    	   System.out.println("Domains is:"+varaible.getDomains()[0]);
		    	   
		    	   // Inserting data using SQL query
			       String sql = "insert into University (Web_pages,state_province,alpha_two_code,name,country,domains)values('" +varaible.getWeb_pages()[0]
		             + "','"+varaible.getState_province()+"','"+varaible.getAlpha_two_code()+"','"+varaible.getName()+"','"+varaible.getCountry()+"','"+varaible.getDomains()[0]+"')"; 
			       
			       System.out.println(sql);
			       // Connection class object
			        Connection con = null;

			        // Try block to check for exceptions
			        try {

			            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			            // Registering drivers
			            DriverManager.registerDriver(driver);

		            // Reference to connection interface
			            con = DriverManager.getConnection(url, user,
		                    pass);

			            // Creating a statement
			            Statement st = con.createStatement();

			            // Executing query
			            int m = st.executeUpdate(sql);
			           if (m >=  1)
			               System.out.println(
			                        "inserted successfully : " + sql);
		           else
			                System.out.println("insertion failed");

			            // Closing the connections
			            con.close();
			        }

		        // Catch block to handle exceptions
		        catch (Exception ex) {
		            // Display message when exceptions occurs
		            System.err.println(ex);
		        }
		        
		       }
		       
		        
		        
		   
		        
		        
		    }
}
