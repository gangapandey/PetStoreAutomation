package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import api.payloads.User;

// It contains the CRUD method implementations.

public class UserEndpoints2 {
	
	//load properties file
	//method created for getting URL's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //  here "route" is the name of property file
		return routes;
	}
	
	//POST User
	public static Response createUser(User payload)
	{
		String post_url = getURL().getString("post_url");
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				
			.when()
				.post(post_url);
		
		return response;
	}
	
	//GET User 
	public static Response readUser(String userName)
	{
		String get_url = getURL().getString("get_url");
		Response response = given()
				.pathParam("username", userName)
				
			.when()
				.get(get_url);
		
		return response;
	}
	
	//UPDATE User
	public static Response updateUser(String userName, User payload)
	{
		String update_url = getURL().getString("update_url");
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", userName)
				
			.when()
				.put(update_url);
		
		return response;
	}
	
	//DELETE User
	public static Response deleteUser(String userName)
	{
		String delete_url = getURL().getString("delete_url");
		Response response = given()
				.pathParam("username", userName)
				
			.when()
				.delete(delete_url);
		
		return response;
	}

}
