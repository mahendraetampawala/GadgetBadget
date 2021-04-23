/**
 * 
 */
package com;

/**
 * @author Mahendra
 *
 */

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


//

@Path("/Items")//@Path annotation is used to bind URI pattern to a Java method. 
public class productItemService 
{ 
	 Item itemObj = new Item(); 
	@GET
	@Path("/") //@Path annotation is used to bind URI pattern to a Java method.
	@Produces(MediaType.TEXT_HTML) 
	
	public String readItems() 
	 { 
		 return itemObj.readItems();
	 } 
	
	

	 
	@POST//used to update resource available on the server.
	@Path("/") //@Path annotation is used to bind URI pattern to a Java method.
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) //@Consumes all the response methods accept the specified MIME types by default.
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String insertItem(@FormParam("itemCode") String itemCode, 
	 @FormParam("itemName") String itemName, 
	 @FormParam("itemPrice") String itemPrice, 
	 @FormParam("itemDesc") String itemDesc) 
	{ 
	 String output = itemObj.insertItem(itemCode, itemName, itemPrice, itemDesc); 
	return output; 
	}

	

	@PUT//used to update resource available on the server.
	@Path("/") //@Path annotation is used to bind URI pattern to a Java method.
	@Consumes(MediaType.APPLICATION_JSON) //@Consumes all the response methods accept the specified MIME types by default.
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updateItem(String itemData) 
	{ 
		
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); //Convert the input string to a JSON object 
	
	 String itemID = itemObject.get("itemID").getAsString(); //Read the values from the JSON object
	 String itemCode = itemObject.get("itemCode").getAsString(); 
	 String itemName = itemObject.get("itemName").getAsString(); 
	 String itemPrice = itemObject.get("itemPrice").getAsString(); 
	 String itemDesc = itemObject.get("itemDesc").getAsString(); 
	 String output = itemObj.updateItem(itemID, itemCode, itemName, itemPrice, itemDesc); 
	return output; 
	}
	
	 
	@DELETE
	@Path("/") //@Path annotation is used to bind URI pattern to a Java method.
	@Consumes(MediaType.APPLICATION_XML) //@Consumes all the response methods accept the specified MIME types by default.
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String deleteItem(String itemData) 
	{ 
	
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); //Convert the input string to an XML document
	 
	
	 String itemID = doc.select("itemID").text(); //Read the value from the element <itemID>
	 String output = itemObj.deleteItem(itemID); 
	return output; 
	}

	
	
}

