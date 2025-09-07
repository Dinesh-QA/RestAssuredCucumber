package utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingExcel {
	public static String getTestData(String key) throws Exception {
		XSSFWorkbook workbook;
		XSSFSheet sheet = null;
		int rowCount;
		int cellCount;
		String testData = null;
		FileInputStream fis = new FileInputStream("./testdata.xlsx");
		workbook = new XSSFWorkbook(fis);
		int sheetCount = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetCount; i++) {
			if (workbook.getSheetAt(i).getSheetName().equalsIgnoreCase("Actual")) {
				sheet = workbook.getSheetAt(i);
			}
		}
		rowCount = sheet.getLastRowNum() + 1;
		for (int i = 0; i < rowCount; i++) {
			cellCount = sheet.getRow(i).getLastCellNum();
			for (int j = 0; j < cellCount; j++) {
				if (sheet.getRow(i).getCell(j).getStringCellValue().equalsIgnoreCase(key)) {
					testData = sheet.getRow(i).getCell(j + 1).getStringCellValue();
				}
			}
		}
		workbook.close();
		return testData;
	}
}