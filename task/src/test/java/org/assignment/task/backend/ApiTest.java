package org.assignment.task.backend;

import org.assignment.task.client.UserClient;
import org.assignment.task.common.TestUtil;
import org.assignment.task.model.CreateResponse;
import org.assignment.task.model.RegisterInfo;
import org.assignment.task.model.UserResponse;
import org.assignment.task.util.JsonParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

public class ApiTest {

	private JsonParser parser;
	private UserClient sut;

	@BeforeClass
	public void beforeClass() {
		sut = new UserClient();
		parser = new JsonParser();
	}

	@Test
	public void givenUserId_Api_WillRespondUserInformation() throws Exception {

		int userId = 2;

		Response response = sut.getSingleUser(userId);

		Assert.assertEquals(response.getStatusCode(), 200, "The api could not be reached..");

		String body = response.asString();
		UserResponse userResponse = (UserResponse) parser.deserializeJson(body, UserResponse.class);

		String userDataActual = userResponse.getData().toString();
		String userDataExpected = TestUtil.readUserInfo().toString();

		Assert.assertEquals(userDataActual, userDataExpected, "The response does not match to api contract..");

	}

	@Test
	public void givenUserInfo_User_CanCreateNewUser() throws Exception {

		RegisterInfo registerDetails = new RegisterInfo();
		registerDetails.setName("morpheus");
		registerDetails.setJob("leader");

		String registerBody = parser.serializeObject(registerDetails);

		Response response = sut.createUser(registerBody);

		Assert.assertEquals(response.getStatusCode(), 201, "The api could not be reached..");

		String body = response.asString();
		CreateResponse createResponse = (CreateResponse) parser.deserializeJson(body, CreateResponse.class);

		Assert.assertNotNull(createResponse.getId());
		Assert.assertNotNull(createResponse.getCreatedAt());

	}

}
