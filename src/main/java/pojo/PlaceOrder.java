package pojo;

import java.util.Arrays;
import java.util.List;

public class PlaceOrder {

	private List<Order> orders;

	// Getter & Setter
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public static PlaceOrder buildPlaceOrder(String country, String productOrderedId) {
		Order order = new Order(country, productOrderedId);
		PlaceOrder placeOrder = new PlaceOrder();
		placeOrder.setOrders(Arrays.asList(order));
		return placeOrder;
	}
}

class Order {
	private String country;
	private String productOrderedId;

	// Constructor
	public Order(String country, String productOrderedId) {
		this.country = country;
		this.productOrderedId = productOrderedId;
	}

	// Getters & Setters
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProductOrderedId() {
		return productOrderedId;
	}

	public void setProductOrderedId(String productOrderedId) {
		this.productOrderedId = productOrderedId;
	}

}
