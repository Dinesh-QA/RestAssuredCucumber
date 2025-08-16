package methods;

import java.io.FileOutputStream;
import java.io.PrintStream;

import Utilities.ConvertToJson;
import Utilities.getTestData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import pojo.Login;
import testdata.GetEndPoint;

public class Ecom_Login {
	private RequestSpecification basereq; // Base specification (common settings like baseUri, filters, headers)
	private RequestSpecification req; // Actual request (base spec + body)
	private Response response; // Stores API response
	private Login login; // POJO reference for login payload
	private ConvertToJson convert; // Utility to convert POJO to JSON string

	public void loginPayload() throws Exception {
		// Log all requests & responses into logging.txt file
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

		// Base request specification (server URL, logging, headers, etc.)
		basereq = new RequestSpecBuilder().setBaseUri(getTestData.GetTestData("baseURL"))
				.addFilter(RequestLoggingFilter.logRequestTo(log)) // logs outgoing request
				.addFilter(ResponseLoggingFilter.logResponseTo(log)) // logs incoming response
				.setContentType(ContentType.JSON) // request body is JSON
				.build();

		// Convert login POJO into JSON string just for debugging (prints to console)
		System.out.println("🔹 Request Payload (JSON): " + convert.ConvertObjectToJson(
				login.LoginBodyBuilder(getTestData.GetTestData("userEmail"), getTestData.GetTestData("userPassword"))));

		// Attach body (POJO) to request → RestAssured auto-serializes POJO into JSON
		req = given().spec(basereq).body(
				login.LoginBodyBuilder(getTestData.GetTestData("userEmail"), getTestData.GetTestData("userPassword")));

		System.out.println("✅ Login request prepared successfully, ready to hit API...");
	}

	public void callLoginAPI(String apiName) {
		System.out.println("🚀 Calling Login API endpoint: /api/ecom/auth/login");

		GetEndPoint endPoint = GetEndPoint.valueOf(apiName);

		response = req.when().post(endPoint.getEndPoint()) // send POST request
				.then().log().all() // log response details in console
				.extract().response();

		System.out.println("✅ Login API executed. Response captured successfully.");
	}

}
