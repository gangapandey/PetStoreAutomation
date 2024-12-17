package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payloads.User;
import io.restassured.response.Response;
import junit.framework.Assert;

public class UserTests2 {
	Faker faker;
	User userPayload;
	
	public Logger logger;
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		logger.debug("Debugging.......");
	}
	
	//POST Request-Testcase
	@Test(priority = 1)
	public void testPostUser() 
	{
		logger.info("**** Creating User ****");
		Response response = UserEndpoints2.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**** User created succesfully ****");
	}
	
	
	//GET request-Testcase
	@Test(priority =2)
	public void testGetUserByName()
	{
		logger.info("**** Reading/Getting User ****");
		Response response = UserEndpoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("**** Read User successfully ****");
	}
	
	//UPDATE request-Testcase
	@Test(priority = 3)
	public void testUpdateUserByName()
	{
		logger.info("**** Updating User ****");
		//update data using the-payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndpoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//checking data after update
		Response responseAfterUpdate = UserEndpoints2.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
		logger.info("**** User updated successfully ****");
	}
	
	
	//DELETE request-Testcases
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("**** Deleting User ****");
		Response response = UserEndpoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**** User deleted successfully ****");
	}

}
