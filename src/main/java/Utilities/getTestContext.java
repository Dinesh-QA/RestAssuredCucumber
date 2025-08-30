package Utilities;

public class getTestContext {
    private static getTestContext instance;
	private String token;
	private String userId;
	private String productId;
	
	private getTestContext() {
		
	}
	
	public static getTestContext getInstance() {
        if (instance == null) {
            instance = new getTestContext();
        }
        return instance;
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
