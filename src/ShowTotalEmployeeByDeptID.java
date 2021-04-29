import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Mahshid Farrahinia
 * 
 * This code shows the number of employees in a department by department id,
 * such as 60, through calling PL/SQL stored procedure total_employee_by_dept_id
 *
 */
public class ShowTotalEmployeeByDeptID {


	public static void main(String[] args) {
		
		 Connection connection = null;
		 CallableStatement statement  = null;

	      
	      try {
	    	   //Step1.Loading drivers
	    	  Class.forName(OracleInfo.Driver_Class_Oracle);
	    	  
	    	  //Step2.Establishing Connections
	    	  
	    	  connection = DriverManager.getConnection(OracleInfo.URL, OracleInfo.Username, OracleInfo.Password);
			  System.out.println("Connected database successfully...");
			  
			  //Step3.Creating and executing statements
			  //Constructing the String to call the Store Procedure with 4 Parameter Place Holders 
			  String query = "{call total_emp_by_dept_id(?,?,?,?)}";
			 
			  //Create a prepared statement
			  statement = connection.prepareCall(query);
			 
			  statement.setInt(1, 60);
			  statement.registerOutParameter(2, java.sql.Types.VARCHAR);
			  statement.registerOutParameter(3, java.sql.Types.VARCHAR);
			  statement.registerOutParameter(4, java.sql.Types.NUMERIC);
			
			
				
			  //Step4.Processing ResultSet
			  // execute total_emp_by_dept_id store procedure
			  statement.executeUpdate();

				String firstName = statement.getString(2);
				String lastName =  statement.getString(3);
	            int TotalNumber =  statement.getInt(4);
	            
				System.out.println("First Name : " + lastName);
				System.out.println("Last Name : "  + firstName);
				System.out.println(" is "+ TotalNumber + " employees who works in mentioned department: ");

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
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
		}
	}


	}

}
