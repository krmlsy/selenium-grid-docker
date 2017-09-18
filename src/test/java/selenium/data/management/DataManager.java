package selenium.data.management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import selenium.database.ConnectDB;

public class DataManager {

	static Connection crunchifyConn = ConnectDB.makeJDBCConnection();
	static Statement stmt = null;
	
	
	public static void main(String[] args) {
		DataManager dm=new DataManager();
		dm.generateData();
	}
	
	public void generateData(){
		
		int nodeCount=5;
		
		for (int i = 0; i < nodeCount; i++) {
			
			insertData("'SeleniumTest'", "'titleControl'" , "'url'" , "'http://www.google.com.tr'");
			insertData("'SeleniumTest'", "'titleControl'" , "'searchKey'" , "'Araba'");
			
			insertData("'SeleniumTest'", "'titleControl2'" , "'url'" , "'http://www.google.com.tr'");
			insertData("'SeleniumTest'", "'titleControl2'" , "'searchKey'" , "'Araba'");

			insertData("'SeleniumTest'", "'titleControl3'" , "'url'" , "'http://www.google.com.tr'");
			insertData("'SeleniumTest'", "'titleControl3'" , "'searchKey'" , "'Araba'");
		}

		
	}

	public void insertData(String scenarioName ,String testStep, String variable,String value) {

		try {
			stmt = crunchifyConn.createStatement();
			
			Integer maxId = getMaxId() + 1;

			String insertTableSQL = "INSERT INTO SCENARIO_INPUTS"
					+ "(ID, SCENARIO_NAME, TEST_STEP, VARIABLE, VALUE, ACTIVE_FLAG) " + "VALUES"
					+ "(" + maxId + "," +scenarioName+", "+testStep+", "+variable+", "+value+", '1')";
			System.out.println("Insert Query: " + insertTableSQL);
			stmt.executeUpdate(insertTableSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<String> getScenarioData(String scenarioName, String testStep, String key) {

		ArrayList<String> result = new ArrayList<String>();

		try {
			stmt = crunchifyConn.createStatement();

			// get active data
			String selectTableSQL = "SELECT ID, VALUE, ACTIVE_FLAG from SCENARIO_INPUTS WHERE SCENARIO_NAME = '"
					+ scenarioName + "' AND TEST_STEP = '" + testStep + "' AND VARIABLE = '" + key + "' AND ACTIVE_FLAG = '" + "1" + "' LIMIT 1";
			System.out.println("Select Query: " + selectTableSQL);
			ResultSet rs = stmt.executeQuery(selectTableSQL);
			String id =null;
			while (rs.next()) {

				 id = rs.getString("ID");
				result.add(id);

				String value = rs.getString("VALUE");
				result.add(value);
				
				System.out.println("id = " + id + " value = " + value);
			}
			
			lockScenarioDataById(Integer.parseInt(id));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	public void lockScenarioDataById(Integer scenarioId) {

		try {
			stmt = crunchifyConn.createStatement();

			String updateTableSQL = "UPDATE SCENARIO_INPUTS SET ACTIVE_FLAG = 2 WHERE ID = " + scenarioId;

			stmt.executeUpdate(updateTableSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void unlockScenarioDataById(Integer scenarioId) {

		try {
			stmt = crunchifyConn.createStatement();

			String updateTableSQL = "UPDATE SCENARIO_INPUTS SET ACTIVE_FLAG = 1 WHERE ID = " + scenarioId;

			stmt.executeUpdate(updateTableSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public Integer getMaxId() {
		int recordCount=0;
		String maxId = "";
		
		try {
			stmt = crunchifyConn.createStatement();

			// get active data
			String getRecordCount = "SELECT COUNT(*) FROM SCENARIO_INPUTS";
			ResultSet rs = stmt.executeQuery(getRecordCount);
			
				while (rs.next()) {
					recordCount = rs.getInt(1);
					
					if(recordCount!=0){
						
										try {
											stmt = crunchifyConn.createStatement();
				
											// get active data
											String selectTableSQL = "SELECT MAX(ID) from SCENARIO_INPUTS";
											System.out.println("Select Query: " + selectTableSQL);
											ResultSet rs2 = stmt.executeQuery(selectTableSQL);
											
												while (rs2.next()) {
													maxId = rs2.getString("MAX(ID)");
													
													System.out.println("Maximum Id = " + maxId);
												}
				
										} catch (SQLException e) {
											e.printStackTrace();
										}
					}
					else{
						maxId = "0";
					}
				}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Integer.parseInt(maxId);
	}

}
