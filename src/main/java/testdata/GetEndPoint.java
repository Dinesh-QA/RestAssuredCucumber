package testdata;

public enum GetEndPoint {
	login("/api/ecom/auth/login");

	private String endPoint;

	GetEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getEndPoint() {
		return this.endPoint;
	}
}
