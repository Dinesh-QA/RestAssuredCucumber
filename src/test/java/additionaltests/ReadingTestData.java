package additionaltests;

import utilities.ReadingExcel;

public class ReadingTestData {
	public static void main(String[] args) throws Exception {
		System.out.println(ReadingExcel.getTestData("client_id"));
		System.out.println(ReadingExcel.getTestData("client_secret"));
		System.out.println(ReadingExcel.getTestData("grant_type"));
		System.out.println(ReadingExcel.getTestData("state"));
		System.out.println(ReadingExcel.getTestData("session_state"));
	}
}
