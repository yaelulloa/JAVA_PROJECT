package package1;

import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
 
public class JdbcSelectTest {   // Save as "JdbcSelectTest.java"
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
         // Step 3: Write a SQL query string. Execute the SQL query via the 'Statement'.
         //  The query result is returned in a 'ResultSet' object called 'rset'.
         String strSelect = "select title, price, qty from books";
         System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
 
         ResultSet rset = stmt.executeQuery(strSelect);
 
         // Step 4: Process the 'ResultSet' by scrolling the cursor forward via next().
         //  For each row, retrieve the contents of the cells with getXxx(columnName).
         System.out.println("The records selected are:");
         int rowCount = 0;
         // Row-cursor initially positioned before the first row of the 'ResultSet'.
         // rset.next() inside the whole-loop repeatedly moves the cursor to the next row.
         // It returns false if no more rows.
         while(rset.next()) {   // Repeatedly process each row
            String title = rset.getString("title");  // retrieve a 'String'-cell in the row
            double price = rset.getDouble("price");  // retrieve a 'double'-cell in the row
            int    qty   = rset.getInt("qty");       // retrieve a 'int'-cell in the row
            System.out.println(title + ", " + price + ", " + qty);
            ++rowCount;
         }
         System.out.println("Total number of records = " + rowCount);
 
      } catch(SQLException ex) {
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
   }
}