package com;

import Model.Billing;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Payments")
public class BillingService {

	Billing pay = new Billing();
	
	// read operation through postman
	//////////////
		@GET
		@Path("/readItem")
		@Produces(MediaType.TEXT_HTML)
		public String readItems() {
			return pay.readItems();
		}

		// insert operation through postman
		@POST
		@Path("/insert/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertItem(
				@FormParam("amount") String amount,
				@FormParam("cardnumber") String cardnumber,
				@FormParam("address") String address ,
				@FormParam("phonenumber") String phonenumber)
		
		{
			
			//System.out.println(amt);
			String output = pay.insertItem(amount, cardnumber, address, phonenumber);
			return output;
		}

		// update operation through postman
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateItem(String itemData) {
			// Convert the input string to a JSON object
			JsonObject payObject = new JsonParser().parse(itemData).getAsJsonObject();
			// Read the values from the JSON object
			String payID = payObject.get("paymentCode").getAsString();
			String userN = payObject.get("amount").getAsString();
			String userM = payObject.get("address").getAsString();
			String cardNo = payObject.get("cardnumber").getAsString();
			String Amount = payObject.get("phonenumber").getAsString();
			String output = pay.updateItem(payID, userN, userM, cardNo, Amount);
			return output;
		}

		// delete operations through postman

		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteItem(String itemData) {
			// Convert the input string to an XML document
			Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

			// Read the value from the element <itemID>
			String payID = doc.select("paymentCode").text();
			String output = pay.deleteItem(payID);
			return output;
		}
		

}