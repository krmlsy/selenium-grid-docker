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
	

	public static Connection makeJDBCConnection() {
		 
		Connection crunchifyConn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Congrats - Seems your MySQL JDBC Driver Registered!");
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
			e.printStackTrace();
			return null;
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
			return crunchifyConn;
		}
		
		return crunchifyConn;
 
	}

	@SuppressWarnings("unused")
	private static void createTable(){
		
		try {
			System.out.println("Creating table in given database...");
		      stmt = crunchifyConn.createStatement();
		      
		      String sql = "CREATE TABLE SCENARIO_INPUTS " +
	                  "(ID INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY, " +
	                  " SCENARIO_NAME VARCHAR(100), " + 
	                  " TEST_STEP VARCHAR(100), " + 
	                  " VARIABLE VARCHAR(100), " + 
	                  " VALUE VARCHAR(4000), " + 
	                  " ACTIVE_FLAG VARCHAR(1))"; 

		      stmt.executeUpdate(sql);
		      System.out.println("Created table in given database...");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
