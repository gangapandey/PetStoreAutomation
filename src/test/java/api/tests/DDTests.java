package api.tests;

import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import junit.framework.Assert;


public class DDTests {
	
	//POST Request
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostuser(String userID, String userName, String fname, String lname, String useremail, String pwd, String ph)
	{
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response = UserEndpoints.createUser(userPayload);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	
	}
	
	//GET Request
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testGetUserByName(String userName)
	{
		Response response = UserEndpoints.readUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	//UPDATE User Request
	@Test(priority = 3,dataProvider="Data", dataProviderClass=DataProviders.class )
	public void testUpdateByUser(String userID, String userName, String fname, String lname, String useremail, String pwd, String ph)
	{
		
		//update data using the-payload
		User userPayload = new User();
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);

		Response response = UserEndpoints.updateUser(userName, userPayload);
				
		Assert.assertEquals(response.getStatusCode(), 200);
				
		//checking data after update
		Response responseAfterUpdate = UserEndpoints.readUser(userName);
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
	}
	
	//DELETE request
	@Test(priority=4, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
		Response response = UserEndpoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
