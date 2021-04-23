/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Chamika Abesekara
 *
 */
public class User {
	
	private Connection connect() // method to connect to the DB
	 { 
		 Connection con = null; 
		 try
		 { 
		 Class.forName("com.mysql.jdbc.Driver"); 
		 
		  
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdb", "root", ""); //DB Connection
		 } 
		 catch (Exception e) 
		 {e.printStackTrace();} 
		 return con; 
	 } 
	
	public String insertUser(String name, String type, String email, String username, String password) //declaring a method to insert values to the db
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for inserting."; }
		 
		 // create a prepared statement
		 String query = " insert into user (`userID`,`name`,`type`,`email`,`username`,`password`)"+ " values (?, ?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, name); 
		 preparedStmt.setString(3, type); 
		 preparedStmt.setString(4, email); 
		 preparedStmt.setString(5, username);
		 preparedStmt.setString(6, password);
		 
		// execute the statement3
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Inserted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while inserting the user."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 } 
	
	public String readUser() 
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for reading."; } 
		
		 output = "<table border='1'><tr><th>user Name</th><th>Type</th>" + // Prepare a html table 
		 "<th>email</th>" + 
		 "<th>username</th>" +
		 "<th>password</th>" +
		 "<th>Update</th><th>Remove</th></tr>"; 
		 
		 String query = "select * from user"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 while (rs.next()) // iterate through the rows in the result set
		 { 
		 String userID = Integer.toString(rs.getInt("userID")); 
		 String name = rs.getString("name"); 
		 String type = rs.getString("type"); 
		 String email = rs.getString("email");
		 String username = rs.getString("username"); 
		 String password = rs.getString("password"); 
		
		 output += "<tr><td>" + name + "</td>";  // Add into the html table
		 output += "<td>" + type + "</td>"; 
		 output += "<td>" + email + "</td>"; 
		 output += "<td>" + username + "</td>";
		 output += "<td>" + password + "</td>";
		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='user.jsp'>"
		+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='userID' type='hidden' value='" + userID 
		 + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 
		 output += "</table>"; // Complete the html table
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the user."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 }
	
	public String updateUser(String userID, String name, String type, String email, String username, String password)//declaring a method to update items in database.
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; }
		 
		 // create a prepared statement
		 String query = "UPDATE user SET name=?,type=?,email=?,username=?, password=? WHERE userID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, type); 
		 preparedStmt.setString(3, email); 
		 preparedStmt.setString(4, username);
		 preparedStmt.setString(5, password);
		 preparedStmt.setInt(6, Integer.parseInt(userID));
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the user."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 } 
	
	public String deleteUser(String userID) //declaring a method to delete items from the database
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; }
		 
		 // create a prepared statement
		 String query = "delete from user where userID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(userID));
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the userS."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
} 

}
