package methods;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.testng.Assert;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AddProduct;
import pojo.Login;
import pojo.PlaceOrder;
import testdata.GetEndPoint;
import utilities.ConvertJsonPath;
import utilities.ConvertToJson;
import utilities.getTestContext;
import utilities.getTestData;

public class Ecom_Login {
	private RequestSpecBuilder builder;
	private RequestSpecification basereq; // Base specification (common settings like baseUri, filters, headers)
	private RequestSpecification req; // Actual request (base spec + body)
	private Response response; // Stores API response
	private Login login; // POJO reference for login payload
	private ConvertToJson convert; // Utility to convert POJO to JSON string
	private static File file;
	getTestContext textContext = getTestContext.getInstance();

	public void prepareRequest(String apiType) throws Exception {
		// Dynamic log file name for each API type
		String logFile = "logging_" + apiType + ".txt";
		PrintStream log = new PrintStream(new FileOutputStream(logFile));

		builder = new RequestSpecBuilder().setBaseUri(getTestData.GetTestData("baseURL"))
				.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log));

		if (apiType.equalsIgnoreCase("login")) {
			// Login Payload

			Login loginPayload = login.LoginBodyBuilder(getTestData.GetTestData("userEmail"),
					getTestData.GetTestData("userPassword"));

			System.out.println("🔹 Login Request Payload (JSON): " + convert.ConvertObjectToJson(loginPayload));

			basereq = builder.setContentType(ContentType.JSON).build();
			req = given().spec(basereq).body(loginPayload);

		} else if (apiType.equalsIgnoreCase("addProduct")) {
			// Add Product Payload

			file = new File(getTestData.GetTestData("filePath"));
			AddProduct product = AddProduct.addProductBuilder("Shoes", textContext.getUserId(), "fashion", "shoes",
					1000, "Addidas Shoes", "Men", file);

			System.out.println("🔹 Add Product Request Payload (JSON): " + convert.ConvertObjectToJson(product));

			basereq = builder.setContentType(ContentType.MULTIPART).addHeader("Authorization", textContext.getToken())
					.build();

			req = given().spec(basereq).multiPart("productName", product.getProductName())
					.multiPart("productAddedBy", product.getProductAddedBy())
					.multiPart("productCategory", product.getProductCategory())
					.multiPart("productSubCategory", product.getProductSubCategory())
					.multiPart("productPrice", String.valueOf(product.getProductPrice()))
					.multiPart("productDescription", product.getProductDescription())
					.multiPart("productFor", product.getProductFor())
					.multiPart("productImage", product.getProductImage());
		} else if (apiType.equalsIgnoreCase("placeOrder")) {
			// place Order Payload

			PlaceOrder placeOrderPayload = PlaceOrder.buildPlaceOrder("India", textContext.getProductId());

			System.out.println(
					"🔹 Place Order Request Payload (JSON): " + convert.ConvertObjectToJson(placeOrderPayload));

			basereq = builder.setContentType(ContentType.JSON).addHeader("Authorization", textContext.getToken())
					.build();
			req = given().spec(basereq).body(placeOrderPayload);

		} else if (apiType.equalsIgnoreCase("deleteProduct")) {
			// Delete Added Product Payload

			basereq = builder.setContentType(ContentType.JSON).addHeader("Authorization", textContext.getToken())
					.build();

			req = given().spec(basereq);

			System.out.println("✅ " + apiType + " request prepared successfully, ready to hit API...");
		}
	}

	public void callAPI(String apiName, String requestMethod) {
		GetEndPoint endPoint = GetEndPoint.valueOf(apiName);
		if (requestMethod.equals("POST")) {
			response = req.when().post(endPoint.getEndPoint()) // send POST request
					.then().log().all() // log response details in console
					.extract().response();
		} else if (requestMethod.equals("DELETE")) {

			response = req.when().delete(endPoint.getEndPoint() + textContext.getProductId()) // send DELETE request
					.then().log().all() // log response details in console
					.extract().response();

			System.out.println("✅ API executed. Response captured successfully.");
		}
	}

	public void checkResponseCode(int value) {
		Assert.assertEquals(response.getStatusCode(), value);
	}

	public boolean validateResponseBody(String key, String value) {
		String actualValue;
		if (key.equalsIgnoreCase("status")) {
			String statusLine = response.getStatusLine();
			actualValue = statusLine.substring(statusLine.indexOf(" ") + 1).split(" ", 2)[1];
		} else {
			actualValue = ConvertJsonPath.convertJsonpath(response.asString(), key);
		}

		return value.equalsIgnoreCase(actualValue);

	}

	public void storeValue(String key) {
		if (key.equalsIgnoreCase("token")) {
			textContext.setToken(ConvertJsonPath.convertJsonpath(response.asString(), key));
		} else if (key.equalsIgnoreCase("userId")) {
			textContext.setUserId(ConvertJsonPath.convertJsonpath(response.asString(), key));
		} else if (key.equalsIgnoreCase("productId")) {
			textContext.setProductId(ConvertJsonPath.convertJsonpath(response.asString(), key));
		} else if (key.equalsIgnoreCase("productOrderId")) {
			textContext.setProductOrderId(ConvertJsonPath.convertJsonpath(response.asString(), key.concat("[0]")));
		}
	}

}
