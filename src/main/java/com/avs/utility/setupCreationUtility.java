package com.avs.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import com.avs.base.BaseClass;

public class setupCreationUtility extends BaseClass{
	
	// Read TestData from the Excel sheet
	public static String TestData_sheetPath = System.getProperty("user.dir") + "/TestData/" + "SetupTestData.xlsx";

	static Workbook book;
	static Sheet sheet;

	// Read TestData from the Excel sheet
	public static Object[][] getTestData(String sheetName) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(TestData_sheetPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(fis);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		System.out.println(sheet.getLastRowNum() + "--------" + sheet.getRow(0).getLastCellNum());

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				// System.out.println(data[i][j]);
			}
		}
		return data;
	}

	// Data Providers
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//SETUP Creation module related Test Data reference
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@DataProvider(name = "SET019")
	public static Object[][] SET019() {
		String sheetName = "SET019";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET020")
	public static Object[][] SET020() {
		String sheetName = "SET020";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET024")
	public static Object[][] SET024() {
		String sheetName = "SET024";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET025")
	public static Object[][] SET025() {
		String sheetName = "SET025";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET026a")
	public static Object[][] SET026a() {
		String sheetName = "SET026a";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET026b")
	public static Object[][] SET026b() {
		String sheetName = "SET026b";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET027a")
	public static Object[][] SET027a() {
		String sheetName = "SET027a";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET027b")
	public static Object[][] SET027b() {
		String sheetName = "SET027b";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "SET028")
	public static Object[][] SET028() {
		String sheetName = "SET028";
		Object[][] data = getTestData(sheetName);
		return data;
	}

}
