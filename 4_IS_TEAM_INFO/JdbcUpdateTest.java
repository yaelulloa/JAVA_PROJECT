package package1;

import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
 
public class JdbcUpdateTest {   // Save as "JdbcSelectTest.java"
   public static void main(String[] args) {
	   String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   String DB_URL = "jdbc:mysql://localhost/ebookshop";
	   try {
		   Class.forName("com.mysql.jdbc.Driver");
	   }catch(ClassNotFoundException ex) {
		   ex.printStackTrace();
	   }

	   //  Database credentials
	    String USER = "root";
	    String PASS = "shree";
	    Connection conn = null;
	    Statement stmt = null;
	    
      try {
    	  
    	   System.out.println("Connecting to database...");
    	   conn = DriverManager.getConnection(DB_URL,USER,PASS);

    	      //STEP 4: Execute a query
    	      System.out.println("CONNECTION OBJECT CONNECTED !!!!!!!...");
 
         // Step 2: Construct a 'Statement' object called 'stmt' inside the Connection created
          stmt = conn.createStatement();
       // Step 3 & 4: Execute a SQL UPDATE via executeUpdate()
          //   which returns an int indicating the number of rows affected.
          // Increase the price by 7% and qty by 1 for id=1001
          String strUpdate = "update books set price = price*1.07, qty = qty+1 where id = 1001";
          System.out.println("The SQL statement is: " + strUpdate + "\n");  // Echo for debugging
          int countUpdated = stmt.executeUpdate(strUpdate);
          System.out.println(countUpdated + " records affected.\n");
  
          // Step 3 & 4 (again): Issue a SELECT (via executeQuery()) to check the UPDATE.
          String strSelect = "select * from books where id = 1001";
          System.out.println("The SQL statement is: " + strSelect + "\n");  // Echo for debugging
          ResultSet rset = stmt.executeQuery(strSelect);
          while(rset.next()) {   // Move the cursor to the next row
             System.out.println(rset.getInt("id") + ", "
                     + rset.getString("author") + ", "
                     + rset.getString("title") + ", "
                     + rset.getDouble("price") + ", "
                     + rset.getInt("qty"));
          }
       } catch(SQLException ex) {
          ex.printStackTrace();
       }  // Step 5: Close conn and stmt - Done automatically by try-with-resources
    }
 }     