package com;

import model.Item;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/")
public class InvestingFunds {
	Item itemObj = new Item();

	@GET
	@Path("/readItems")
	@Produces(MediaType.TEXT_HTML)

	public String readItems() {
		return itemObj.readItems();
	}

	@POST
	@Path("/insertItems")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("investName") String investName,
			@FormParam("researcherName") String researcherName, 
			@FormParam("investPrice") String investPrice,
			@FormParam("investDet") String investDet) {
		String output = itemObj.insertItem(investName, researcherName, investPrice, investDet);
		return output;
	}

	@PUT
	@Path("/updateItems") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String itemData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String investID = itemObject.get("investID").getAsString(); 
	 String investName = itemObject.get("investName").getAsString(); 
	 String researcherName = itemObject.get("researcherID").getAsString(); 
	 String investPrice = itemObject.get("investPrice").getAsString(); 
	 String investDet = itemObject.get("investDet").getAsString(); 
	 String output = itemObj.updateItem(investID, investName, researcherName, investPrice, investDet); 
	return output; 
	}

	@DELETE
	@Path("/deleteItems") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String itemData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <investID>
	 String investID = doc.select("investID").text(); 
	 String output = itemObj.deleteItem(investID); 
	return output; 
	}

}
