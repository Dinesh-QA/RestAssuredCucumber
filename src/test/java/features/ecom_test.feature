Feature: Test E-commerce Site Functionality  
  This feature covers login, adding a product, placing an order, and deleting a product.  

  @LoginTest  
  Scenario: Verify successful login into the application  
    Given I prepare a login payload
    When the user calls the "login" API with a POST HTTP request  
    Then the response "status" should be "200"  
    And the response "scope" should be "OK"  
