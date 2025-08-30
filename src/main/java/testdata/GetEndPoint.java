package testdata;

public enum GetEndPoint {
	login("/api/ecom/auth/login"),
	AddProduct("/api/ecom/product/add-product"),
	PlaceOrder("/api/ecom/order/create-order");

	private String endPoint;

	GetEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getEndPoint() {
		return this.endPoint;
	}
}
