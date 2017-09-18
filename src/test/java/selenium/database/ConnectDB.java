package selenium.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * KEREM ULUSOY
 */
public class ConnectDB {

	static Connection crunchifyConn = null;
	static Statement stmt = null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		makeJDBCConnection();
		createTable();
	}

	private static void makeJDBCConnection() {
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Congrats - Seems your MySQL JDBC Driver Registered!");
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
			e.printStackTrace();
			return;
		}
 
		try {
			// DriverManager: The basic service for managing a set of JDBC drivers.
			crunchifyConn = DriverManager.getConnection("jdbc:mysql://217.182.91.245:3306/automation", "root", "root");
			if (crunchifyConn != null) {
				System.out.println("Connection Successful! Enjoy. Now it's time to push data");
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException e) {
			System.out.println("MySQL Connection Failed!");
			e.printStackTrace();
			return;
		}
 
	}

	private static void createTable(){
		
		try {
			System.out.println("Creating table in given database...");
		      stmt = crunchifyConn.createStatement();
		      
		      String sql = "CREATE TABLE REGISTRATION " +
		                   "(id INTEGER not NULL, " +
		                   " first VARCHAR(255), " + 
		                   " last VARCHAR(255), " + 
		                   " age INTEGER, " + 
		                   " PRIMARY KEY ( id ))"; 

		      stmt.executeUpdate(sql);
		      System.out.println("Created table in given database...");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
