package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import model.User;

/**
 * @author Chamika Abesekara
 *
 */
@Path("/User")//@Path annotation is used to bind URI pattern to a Java method. 
public class UserService 
{ 
	 User userObj = new User(); 
	@GET
	@Path("/") //@Path annotation is used to bind URI pattern to a Java method.
	@Produces(MediaType.TEXT_HTML) 
	
	public String readUser() 
	 { 
		 return userObj.readUser();
	 } 
	
	

	 
	@POST//used to update resource available on the server.
	@Path("/") //@Path annotation is used to bind URI pattern to a Java method.
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) //@Consumes all the response methods accept the specified MIME types by default.
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String insertUser(@FormParam("name") String name, 
	 @FormParam("type") String type, 
	 @FormParam("email") String email, 
	 @FormParam("username") String username,
	 @FormParam("password") String password) 
	{ 
	 String output = userObj.insertUser(name, type, email, username, password); 
	return output; 
	}

	

	@PUT//used to update resource available on the server.
	@Path("/") //@Path annotation is used to bind URI pattern to a Java method.
	@Consumes(MediaType.APPLICATION_JSON) //@Consumes all the response methods accept the specified MIME types by default.
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updateUser(String itemData) 
	{ 
		
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); //Convert the input string to a JSON object 
	
	 String userID = itemObject.get("userID").getAsString(); //Read the values from the JSON object
	 String name = itemObject.get("name").getAsString(); 
	 String type = itemObject.get("type").getAsString(); 
	 String email = itemObject.get("email").getAsString(); 
	 String username = itemObject.get("username").getAsString();
	 String password = itemObject.get("password").getAsString();
	 String output = userObj.updateUser(userID, name, type, email, username, password); 
	return output; 
	}
	
	 
	@DELETE
	@Path("/") //@Path annotation is used to bind URI pattern to a Java method.
	@Consumes(MediaType.APPLICATION_XML) //@Consumes all the response methods accept the specified MIME types by default.
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String deleteUser(String itemData) 
	{ 
	
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); //Convert the input string to an XML document
	 
	
	 String userID = doc.select("userID").text(); //Read the value from the element <userID>
	 String output = userObj.deleteUser(userID); 
	return output; 
	}	
	

}
