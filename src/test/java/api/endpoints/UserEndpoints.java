package api.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import api.payloads.User;

// It contains the CRUD method implementations.

public class UserEndpoints {
	//POST User
	public static Response createUser(User payload)
	{
		Response response = given()
				.contentType(ContentType.JSON)    // header 
				.accept(ContentType.JSON)         // header
				.body(payload)
				
			.when()
				.post(Routes.post_url);
		
		return response;
	}
	
	//GET User 
	public static Response readUser(String userName)
	{
		Response response = given()
				.pathParam("username", userName)
				
			.when()
				.get(Routes.get_url);
		
		return response;
	}
	
	//UPDATE User
	public static Response updateUser(String userName, User payload)
	{
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", userName)
				
			.when()
				.put(Routes.update_url);
		
		return response;
	}
	
	//DELETE User
	public static Response deleteUser(String userName)
	{
		Response response = given()
				.pathParam("username", userName)
				
			.when()
				.delete(Routes.delete_url);
		
		return response;
	}

}
