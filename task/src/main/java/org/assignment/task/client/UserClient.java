package org.assignment.task.client;

import org.assignment.task.common.Config;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

public class UserClient {

	private static final String SingleUserEndPoint = "/api/users";

	public UserClient() {
		Config.initilize();
		RestAssured.baseURI = Config.getApiUrl();
	}

	public Response getSingleUser(int userId) {

		String uri = SingleUserEndPoint + "/" + userId;
		Response response = RestAssured.given().get(uri);

		return response;
	}

	public Response createUser(String createBody) {

		Response response = RestAssured.given().body(createBody).post(SingleUserEndPoint);

		return response;

	}

}
