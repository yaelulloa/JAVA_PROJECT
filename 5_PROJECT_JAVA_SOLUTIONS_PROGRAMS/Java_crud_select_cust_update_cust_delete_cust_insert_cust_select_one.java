package javapack;
import java.util.Scanner;
import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
public class Java_crud_select_cust_update_cust_delete_cust_insert_cust_select_one {
	
	public static void main(String[] args) {
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		String DB_URL = "jdbc:mysql://localhost/ebookshop";
		String USER = "root";
	    String PASS = "shree";
	    Connection conn = null;
	    Statement stmt = null;
	   
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection(DB_URL,USER,PASS);
			 stmt = conn.createStatement();
		   }catch(SQLException ex) {
			   ex.printStackTrace();
		   }catch(ClassNotFoundException ex) {
			   ex.printStackTrace();
		   }
		Scanner in = new Scanner(System.in);
		display_menu();
		while (true) {
		String s = in.nextLine();       // nextLine - read string data
		System.out.println("You entered string "+s);
		if (s.contentEquals("sel")) {
			
			System.out.println(" invoke display on books");
			mysql_db_slelct(conn,stmt);
			
		} else if (s.contentEquals("upd")){
			
			System.out.println(" invoke upd  on book");
			mysql_db_update(conn,stmt);
			
		} else if ( s.contentEquals("del")) {
			
			System.out.println(" invoke del  on book");
			mysql_db_delete(conn,stmt);
			
		} else if ( s.contentEquals("add")) {
			
			System.out.println(" invoke add  on book");
			mysql_db_insert(conn,stmt);
			
		} else if ( s.contentEquals("selone")) {
			
			System.out.println(" invoke one select read row  on book");
			mysql_db_select_one(conn,stmt);

		} else if ( s.contentEquals("exit")) {
			System.out.println(" invoke exit  on book");
			break;
		}else {
			System.out.println(" SORRY, enter the right command");
		}
		display_menu();
	}
	}
	public static void display_menu(){
			System.out.println();
			System.out.println();
			System.out.println(" WELCOME TO BOOKS MAINTENANCE TASK ");
			System.out.println();
			System.out.println(" sel - display books ");
			System.out.println(" upd - update books");
			System.out.println(" del - delete books");
			System.out.println(" add - insert books");
			System.out.println(" exit - exit the program");
			System.out.println(" Enter a command ");
			}
	public static void mysql_db_select_one(Connection conn,Statement stmt) {
		try {
		Scanner in = new Scanner(System.in);
		System.out.println(" Emter book ID ");
		int rid = in.nextInt();
		String strSelect = "select title, price, qty from books where id = '"+rid+"'";
        System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
        ResultSet rset = stmt.executeQuery(strSelect);
        int rowCount = 0;
        while(rset.next()) {   // Repeatedly process each row
            String title = rset.getString("title");  // retrieve a 'String'-cell in the row
            double price = rset.getDouble("price");  // retrieve a 'double'-cell in the row
            int    qty   = rset.getInt("qty");       // retrieve a 'int'-cell in the row
            System.out.println(title + ", " + price + ", " + qty);
            ++rowCount;
         }
         System.out.println("Total number of records = " + rowCount);
		}catch(SQLException ex) {
	         ex.printStackTrace();
	      } 
	};
	public static void mysql_db_slelct(Connection conn,Statement stmt) {
	
		try {
	    	  //STEP 4: Execute a query
	    	  System.out.println("CONNECTION OBJECT CONNECTED !!!!!!!...");
	 
	         // Step 2: Construct a 'Statement' object called 'stmt' inside the Connection created
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
	          
	       }catch(SQLException ex) {
	         ex.printStackTrace();
	      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
	}

	public static void mysql_db_update(Connection conn,Statement stmt) {
 
	      try {
	    	  
	    	   
	       // Step 3 & 4: Execute a SQL UPDATE via executeUpdate()
	          //   which returns an int indicating the number of rows affected.
	          // Increase the price by 7% and qty by 1 for id=1001
	    	  Scanner in = new Scanner(System.in);
	           System.out.println("enter lower range id");
	           int v1 = in.nextInt();
	           System.out.println("enter upper range id");
	           int v2 = in.nextInt();
	          String strUpdate = "update books set price = price*1.07, qty = qty+1 where id >= '"+v1+"' and id < '"+v2+"'";
	          System.out.println("The SQL statement is: " + strUpdate + "\n");  // Echo for debugging
	          int countUpdated = stmt.executeUpdate(strUpdate);
	          System.out.println(countUpdated + " records affected.\n");
	          // Step 3 & 4 (again): Issue a SELECT (via executeQuery()) to check the UPDATE.
	          String strSelect = "select * from books where id >= '"+v1+"' and id < '"+v2+"'";
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
	public static void mysql_db_delete(Connection conn,Statement stmt) {
		
		    
	      try {
	    	  
	           // Step 3 & 4: Execute a SQL INSERT|DELETE statement via executeUpdate(),
	          //   which returns an int indicating the number of rows affected.
	           Scanner in = new Scanner(System.in);
	           System.out.println("enter lower range id");
	           int v1 = in.nextInt();
	           System.out.println("enter upper range id");
	           int v2 = in.nextInt();
	          // DELETE records with id>=3004 and id<3005
	          String sqlDelete = "delete from books where id >= '"+v1+"' and id < '"+v2+"'";
	          System.out.println("The SQL statement is: " + sqlDelete + "\n");  // Echo for debugging
	          int countDeleted = stmt.executeUpdate(sqlDelete);
	          System.out.println(countDeleted + " records deleted.\n");
	      }catch(SQLException ex) {
	          ex.printStackTrace();
	       }  // Step 5: Close conn and stmt - Done automatically by try-with-resources
		
	};
    public static void mysql_db_insert(Connection conn,Statement stmt){
    	
 	    
       
    	   Scanner in = new Scanner(System.in);
    	   String flag = "YES";
    	   try {
    		   while(true) { 
    		   if (flag.contentEquals("NO")) break;
               System.out.println("Enter Book ID");
               int bid = in.nextInt(); 
               System.out.println("Enter Book title");
               String title = in.next();
               System.out.println("Enter Book Quantity");
               int  qty = in.nextInt();
               System.out.println("Enter Book price");
               double price = in.nextDouble();
               System.out.println("Enter Book Author");
               String author = in.next();       
               
               String sqlInsert = "insert into books values ('"+bid+"', '"+title+"','"+author+"', '"+price+"', '"+qty+"')";
               System.out.println("your insert statement is " + sqlInsert);
               int irecs = stmt.executeUpdate(sqlInsert);
               
               System.out.println("number of rows inserted = " + irecs);
               System.out.println("Do you want to insert more rows? yes /no");
               flag = in.next().toUpperCase(); // byte,short,int,long 
    		   }
    	   }catch(SQLException ex) {
               ex.printStackTrace();
           }        
    }
    }


