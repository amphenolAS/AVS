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

public class assetCreationUtility extends BaseClass{
	
	// Read TestData from the Excel sheet
	public static String TestData_sheetPath = System.getProperty("user.dir") + "/TestData/" + "AssetNameTestData.xlsx";

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
	//ASSET Creation module related Test Data reference
	@DataProvider(name = "ASST03")
	public static Object[][] getAstNameInvalidTestData() {

		// "AstName" sheet is the sheet name from which Data related to Asset Name field
		// is read
		String sheetName = "ASST03";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST02")
	public static Object[][] getAstNameValidTestData() {

		String sheetName = "ASST02";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST06")
	public static Object[][] getEqpIDinValidTestData() {
		String sheetName = "ASST06";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST05")
	public static Object[][] getEqpIDValidTestData() {
		String sheetName = "ASST05";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST10")
	public static Object[][] getAstTypeInValidTestData() {
		String sheetName = "ASST10";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "ASST09")
	public static Object[][] getAstTypeValidTestData() {
		String sheetName = "ASST09";
		Object[][] data = getTestData(sheetName);
		return data;
	}
	
	@DataProvider(name = "getAstMdlInValidTestData")
	public static Object[][] getAstMdlInValidTestData() {
		String sheetName = "InvalidAstMdl";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "getAstMdlValidTestData")
	public static Object[][] getAstMdlValidTestData() {
		String sheetName = "ValidAstMdl";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "getAstSizeInValidTestData")
	public static Object[][] getAstSizeInValidTestData() {
		String sheetName = "InvalidAstSize";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "getAstSizeValidTestData")
	public static Object[][] getAstSizeValidTestData() {
		String sheetName = "ValidAstSize";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "getAstSizeUnitInValidTestData")
	public static Object[][] getAstSizeUnitInValidTestData() {
		String sheetName = "InvalidAstSizeUnit";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "getAstSizeUnitValidTestData")
	public static Object[][] getAstSizeUnitValidTestData() {
		String sheetName = "ValidAstSizeUnit";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "getAstMakerInValidTestData")
	public static Object[][] getAstMakerInValidTestData() {
		String sheetName = "InvalidAstMaker";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "getAstMakerValidTestData")
	public static Object[][] getAstMakerValidTestData() {
		String sheetName = "ValidAstMaker";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "getAstLocationInValidTestData")
	public static Object[][] getAstLocationInValidTestData() {
		String sheetName = "InvalidAstLocation";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "getAstLocationValidTestData")
	public static Object[][] getAstLocationValidTestData() {
		String sheetName = "ValidAstLocation";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "getAstALLData")
	public static Object[][] getAstALLData() {
		String sheetName = "AllAssetDetails";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "tc147")
	public static Object[][] tc147() {
		String sheetName = "tc147";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "tc149")
	public static Object[][] tc149() {
		String sheetName = "tc149";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "tcasst011")
	public static Object[][] tcasst011() {
		String sheetName = "tcasst011";
		Object[][] data = getTestData(sheetName);
		return data;
	}

	@DataProvider(name = "tcasst014")
	public static Object[][] tcasst014() {
		String sheetName = "tcasst014";
		Object[][] data = getTestData(sheetName);
		return data;
	}

}
