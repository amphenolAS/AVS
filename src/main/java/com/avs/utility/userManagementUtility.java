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

public class userManagementUtility extends BaseClass{
	
	// Read TestData from the Excel sheet
	public static String TestData_sheetPath = System.getProperty("user.dir") + "/TestData/" + "UserManagementTestData.xlsx";

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
	//ADMIN module related Test Data reference
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@DataProvider(name = "tcADMN124")
	public static Object[][] tcADMN124() {
		String sheetName = "tcADMN124";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "tcADMN125")
	public static Object[][] tcADMN125() {
		String sheetName = "tcADMN125";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "tcADMN125a")
	public static Object[][] tcADMN125a() {
		String sheetName = "tcADMN125a";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "tcADMN129")
	public static Object[][] tcADMN129() {
		String sheetName = "tcADMN129";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "tcADMN129a")
	public static Object[][] tcADMN129a() {
		String sheetname = "tcADMN129a";
		Object[][] data = getTestData(sheetname);
		return data;
	}

	@DataProvider(name = "tcADMN133")
	public static Object[][] tcADMN133() {
		String sheetname = "tcADMN133";
		Object[][] data = getTestData(sheetname);
		return data;
	}

	@DataProvider(name = "tcADMN134")
	public static Object[][] tcADMN134() {
		String sheetname = "tcADMN134";
		Object[][] data = getTestData(sheetname);
		return data;
	}

	@DataProvider(name = "tcADMN146")
	public static Object[][] tcADMN146() {
		String sheetname = "tcADMN146";
		Object[][] data = getTestData(sheetname);
		return data;
	}
	
	@DataProvider(name="tcADMN146a")
	public static Object[][] tcADMN146a() {
		String sheetname = "tcADMN146a";
		 Object[][] data = getTestData(sheetname);
		 return data;
	}
	
	@DataProvider(name="tcADMN147a")
	public static Object[][] tcADMN147a() {
		String sheetname = "tcADMN147a";
		 Object[][] data = getTestData(sheetname);
		 return data;
	}
	@DataProvider(name="tcADMN150")
	public static Object[][] tcADMN150() {
		String sheetname = "tcADMN150";
		 Object[][] data = getTestData(sheetname);
		 return data;
	}
	@DataProvider(name="tcADMN150a")
	public static Object[][] tcADMN150a() {
		String sheetname = "tcADMN150a";
		 Object[][] data = getTestData(sheetname);
		 return data;
	}
	@DataProvider(name="tcADMN159")
	public static Object[][] tcADMN159() {
		String sheetname = "tcADMN159";
		 Object[][] data = getTestData(sheetname);
		 return data;
	}
	@DataProvider(name="tcADMN160")
	public static Object[][] tcADMN160() {
		String sheetname = "tcADMN160";
		 Object[][] data = getTestData(sheetname);
		 return data;
	}

	@DataProvider(name="tcADMN188a")
	public static Object[][] tcADMN188a() {
		String sheetname = "tcADMN188a";
		 Object[][] data = getTestData(sheetname);
		 return data;
	}
}
