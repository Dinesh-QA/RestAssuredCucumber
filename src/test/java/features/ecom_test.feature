Feature: Test E-commerce Site Functionality  
  This feature covers login, adding a product, placing an order, and deleting a product.  

  @LoginApp  
  Scenario: Login into application
    Given I prepare a "login" payload
    When the user calls the "login" API with a "POST" HTTP request  
    Then the response code should be 200  
    And the response "status" should be "OK"
    And the response "message" should be "Login Successfully" 
    Then I store "token" from response
    Then I store "userId" from response

  @PlaceProduct  
  Scenario: Place Product after success Login
  	Given I prepare a "addProduct" payload
  	When the user calls the "AddProduct" API with a "POST" HTTP request
  	Then the response code should be 201  
    And the response "status" should be "Created"
    And the response "message" should be "Product Added Successfully"
    Then I store "productId" from response
      	
  @PlaceOrder
  Scenario: Place Order after placing product
  	Given I prepare a "placeOrder" payload  
  	When the user calls the "PlaceOrder" API with a "POST" HTTP request
  	Then the response code should be 201  
    And the response "status" should be "Created"
    And the response "message" should be "Order Placed Successfully"
    Then I store "productOrderId" from response
    
  @DeleteProduct  
  Scenario: Delete Product after which is placed previously
  	Given I prepare a "deleteProduct" payload
  	When the user calls the "deleteProduct" API with a "DELETE" HTTP request
  	Then the response code should be 200  
    And the response "status" should be "OK"
    And the response "message" should be "Product Deleted Successfully" 