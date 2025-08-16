package stepdefinations;

import java.io.IOException;

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

	@Then("the response {string} should be {string}")
	public void the_response_should_be(String string, String string2) {

	}

}
