/**
 * @author manoj.ghadei
 *
 */

package com.avs.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import com.avs.base.BaseClass;

public class TestUtilities extends BaseClass {
	
	
	public String convert_StringDate_to_ActualDate_inCertainFormat(String dt) throws ParseException {
		SimpleDateFormat formating = new SimpleDateFormat("dd MMM yyyy");
		String dateinString = dt;
		// System.out.println(dateString);
		Date date = formating.parse(dateinString);

		// System.out.println(date);
		// System.out.println(formating.format(date));

		SimpleDateFormat formatter = new SimpleDateFormat("MM-d-yyyy");
		String strDate = formatter.format(date);
		// System.out.println("Date Format with MM-d-yyyy : "+strDate);
		return strDate;
	}
	
	public String get_CurrentDate_inCertainFormat(String dtFormat) throws ParseException {
		SimpleDateFormat formating = new SimpleDateFormat(dtFormat);		
		Date date = new Date();
		
		String strDate = formating.format(date);
		//System.out.println(formating.format(date));
		return strDate;
	}

	// Method to call the below method to capture screenshot when a Test Fails
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		FileUtils.copyFile(scrFile,
				new File("C:\\Users\\manoj.ghadei\\git\\VRT\\VRT\\Screenshots" + "_" + timestamp + ".png"));
		// FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" +
		// System.currentTimeMillis() + ".png"));
	}

	public static String getFailedTCScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyy_MM_dd_hhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public static String getPassTCScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyy_MM_dd_hhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/PassTCScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

}
