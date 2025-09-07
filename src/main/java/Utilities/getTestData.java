package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class getTestData {

	public static String GetTestData(String key) throws Exception {
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Admin\\git\\repository\\CucumberRestAssuredFramework\\src\\main\\java\\testdata\\testdata.properties");
		pro.load(fis);

		return pro.getProperty(key);
	}
}
