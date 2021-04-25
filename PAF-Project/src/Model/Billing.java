package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Billing {
	

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rifak", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	public String insertItem( String amount, String cardnumber, String address , String phonenumber) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " insert into paymentinfo  values (?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setDouble(2,Double.parseDouble(amount));
			preparedStmt.setInt(3, Integer.parseInt(cardnumber));
			preparedStmt.setString(4,address);
			preparedStmt.setInt(5, Integer.parseInt(phonenumber));
			
			// execute the statement
			preparedStmt.execute();

			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// data retrieval from database

	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>User Name</th>" + "<th>User Mobile</th><th>Card Number</th>"
					+ "<th>Amount</th>" + "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from paymentdetail";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String payID = Integer.toString(rs.getInt("paymentCode"));
				String userM = rs.getString("amount");
				String card = rs.getString("cardnumber");
				String amount = Double.toString(rs.getDouble("address"));
				String add = rs.getString("phonenumber");
				// Add a row into the html table
				output += "<tr><td>" + payID + "</td>";
				output += "<td>" + userM + "</td>";
				output += "<td>" + card + "</td>";
				output += "<td>" + amount + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' " + " type='button' value='Update'></td>"
						+ "<td><form method='post' action='add.jsp'>" + "<input name='btnRemove' "
						+ " type='submit' value='Remove'>" + "<input name='paymentID' type='hidden' " + " value='"
						+ payID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// delete values from database

	public String deleteItem(String paymentCode) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from paymentdetail where paymentCode=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(paymentCode));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	/// update record-----------------
	public String updateItem(String payID, String userN, String userM, String cardNo, String Amount) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			String query = "UPDATE paymentInfo SET address=?,phonenumber=?,cardnumber=?,amount=?, WHERE paymentCode=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			
			preparedStmt.setString(1, userN);
			preparedStmt.setInt(2, Integer.parseInt(userM));
			preparedStmt.setInt(3, Integer.parseInt(cardNo));
			preparedStmt.setDouble(4, Double.parseDouble(Amount));
			preparedStmt.setInt(5, Integer.parseInt(payID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}


}

