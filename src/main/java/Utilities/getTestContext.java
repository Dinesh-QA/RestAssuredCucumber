package utilities;

public class getTestContext {
	private static getTestContext instance;
	private String token;
	private String userId;
	private String productId;
	private String productOrderId;

	private getTestContext() {

	}

	public static getTestContext getInstance() {
		if (instance == null) {
			instance = new getTestContext();
		}
		return instance;
	}

	public String getProductOrderId() {
		return productOrderId;
	}

	public void setProductOrderId(String productOrderId) {
		this.productOrderId = productOrderId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}
