package application;

import java.sql.*;

//Used to connect to MySQL database.
public class DBConnection {
	
	public Connection getConnection() throws SQLException, ClassNotFoundException{	
		
	          
	      Class.forName("com.mysql.jdbc.Driver");
	      String url = "jdbc:mysql://localhost:3306/BookLog"; 
	      Connection conn = DriverManager.getConnection(url,"root","data");   
	      return conn;
		 
	
	}

}	
	
	

