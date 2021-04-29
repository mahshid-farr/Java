import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
/**
 * @author Mahshid Farrahinia
 * 
 * This code  query the employees table in my Oracle database
 * for the employee’s full name, email and salary refer to employee ID given by user
 *
 */
public class ShowEmployeeInfoByID {


	public static void main(String[] args) {
	
	      Connection connection = null;
	      PreparedStatement statement  = null;
	      ResultSet  resultSet  = null;
	      
	      try {
	    	   //Step1.Loading drivers
	    	  Class.forName(OracleInfo.Driver_Class_Oracle);
	    	  
	    	  //Step2.Establishing Connections
	    	  connection = DriverManager.getConnection(OracleInfo.URL, OracleInfo.Username, OracleInfo.Password);
			  System.out.println("Connected database successfully...");
			  
			  //Step3.Creating and executing statements
			  String query = "select FIRST_NAME, LAST_NAME, EMAIL, SALARY from EMPLOYEES "
				           	+ "where EMPLOYEE_ID = ?  order by 2 desc";
			  //Create a prepared statement
			  statement = connection.prepareStatement(query);
			  
			 //Requesting user input 
			  Scanner input = new Scanner(System.in);
			  System.out.println("Please enter employee Id: ");
			  int employeeId = input.nextInt();
			  
			  input.close();
			  
			 //Setting user input
			  statement.setInt(1, employeeId);
			
				
			  //Step4.Processing ResultSet
			  resultSet = statement.executeQuery();
			  System.out.println("\nFirstName, LastName, EmailAddress, Salary");
			  while (resultSet.next()) {
				  for (int i = 1; i <= 4; i++) {
						System.out.print(resultSet.getString(i) + " ");
					}
				  System.out.println();
	         }

	}catch (SQLException se) {
		// Handle errors for JDBC
		se.printStackTrace();
	} catch (Exception e) {
		// Handle errors for Class.forName
		e.printStackTrace();
	}

	//Step5.Close the connection in finally block.
	finally {
		try {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
		}
	}

	}

}
