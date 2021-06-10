package package1;

import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
 
public class JdbcDeleteTest {   // Save as "JdbcSelectTest.java"
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
       // Step 3 & 4: Execute a SQL INSERT|DELETE statement via executeUpdate(),
          //   which returns an int indicating the number of rows affected.
  
          // DELETE records with id>=3000 and id<4000
          String sqlDelete = "delete from books where id >= 3000 and id < 4000";
          System.out.println("The SQL statement is: " + sqlDelete + "\n");  // Echo for debugging
          int countDeleted = stmt.executeUpdate(sqlDelete);
          System.out.println(countDeleted + " records deleted.\n");
  
          // INSERT a record
          String sqlInsert = "insert into books values (3001, 'Gone Fishing', 'Kumar', 11.11, 11)";
          System.out.println("The SQL statement is: " + sqlInsert + "\n");  // Echo for debugging
          int countInserted = stmt.executeUpdate(sqlInsert);
          System.out.println(countInserted + " records inserted.\n");
  
          // INSERT multiple records
          sqlInsert = "insert into books values "
                + "(3002, 'Gone Fishing 2', 'Kumar', 22.22, 22),"
                + "(3003, 'Gone Fishing 3', 'Kumar', 33.33, 33)";
          System.out.println("The SQL statement is: " + sqlInsert + "\n");  // Echo for debugging
          countInserted = stmt.executeUpdate(sqlInsert);
          System.out.println(countInserted + " records inserted.\n");
  
          // INSERT a partial record
          sqlInsert = "insert into books (id, title, author) values (3004, 'Fishing 101', 'Kumar')";
          System.out.println("The SQL statement is: " + sqlInsert + "\n");  // Echo for debugging
          countInserted = stmt.executeUpdate(sqlInsert);
          System.out.println(countInserted + " records inserted.\n");
  
          // Step 3 & 4: Issue a SELECT (via executeQuery()) to check the changes
          String strSelect = "select * from books";
          System.out.println("The SQL statement is: " + strSelect + "\n");  // Echo For debugging
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