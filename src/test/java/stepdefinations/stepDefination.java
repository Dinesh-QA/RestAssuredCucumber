package stepdefinations;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import methods.Ecom_Login;

public class stepDefination {
	Ecom_Login ecomLogin = new Ecom_Login();

	@Given("I prepare a login payload")
	public void prepareALoginPayload() throws Exception {
		ecomLogin.loginPayload();
	}

	@When("the user calls the {string} API with a POST HTTP request")
	public void the_user_calls_the_api_with_a_post_http_request(String apiName) {
		ecomLogin.callLoginAPI(apiName);
	}

	@Then("the response code should be {int}")
	public void the_response_should_be(int value) {
		ecomLogin.checkResponseCode(value);
	}

	@And("the response {string} should be {string}")
	public void ResponseBody(String key, String value) {
		Assert.assertEquals(ecomLogin.validateResponseBody(key, value), true,
				"Reponse of " + key + " is not matching with " + value);
	}

}
