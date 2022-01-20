/**
* @author manoj.ghadei
*
*/

package com.avs.utility;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.net.*;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.avs.base.BaseClass;
import com.avs.pages.MainHubPage;


public class TestUtilities extends BaseClass {

	public TestUtilities() throws IOException {
		super();
	}

	// Convert dd MMM yyyy type of Date input to a MM-dd-yyyy date format
	public String convert_StringDate_to_ActualDate_inCertainFormat(String dt) throws ParseException {
		SimpleDateFormat formating = new SimpleDateFormat("dd MMM yyyy");
		String dateinString = dt;
		// System.out.println(dateString);
		Date date = formating.parse(dateinString);

		// System.out.println(date);
		// System.out.println(formating.format(date));

		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		String strDate = formatter.format(date);
		// System.out.println("Date Format with MM-dd-yyyy : "+strDate);
		return strDate;
	}

	// Convert dd MMM yyyy type of Date input to a MM/dd/yyyy date format
	public String convert_StringDate_to_ActualDate_inCertainFormat2(String dt) throws ParseException {
		SimpleDateFormat formating = new SimpleDateFormat("dd MMM yyyy");
		String dateinString = dt;
		// System.out.println(dateString);
		Date date = formating.parse(dateinString);

		// System.out.println(date);
		// System.out.println(formating.format(date));

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);
		// System.out.println("Date Format with MM/dd/yyyy : "+strDate);
		return strDate;
	}

	// Convert dd-MMM-yyyy type of Date input to a dd-MM-yyyy date format
	public String convert_StringDate_to_ActualDate_inCertainFormat3(String dt) throws ParseException {
		SimpleDateFormat formating = new SimpleDateFormat("dd-MMM-yyyy");
		String dateinString = dt;
		// System.out.println(dateString);
		Date date = formating.parse(dateinString);

		// System.out.println(date);
		// System.out.println(formating.format(date));

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = formatter.format(date);
		// System.out.println("Date Format with dd-MM-yyyy : "+strDate);
		return strDate;
	}

	// Convert MM-dd-yyyy type of Date input to a dd-MM-yyyy date format
	public String convert_StringDate_to_ActualDate_inCertainFormat4(String dt) throws ParseException {
		SimpleDateFormat formating = new SimpleDateFormat("MM-dd-yyyy");
		String dateinString = dt;
		// System.out.println(dateString);
		Date date = formating.parse(dateinString);

		// System.out.println(date);
		// System.out.println(formating.format(date));

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = formatter.format(date);
		// System.out.println("Date Format with dd-MM-yyyy : "+strDate);
		return strDate;
	}

	// Convert dd:MM:yyyy:hh:mm:ss type of Date input to a dd-MM-yyyy date format
	public String convert_StringDate_to_ActualDate_inCertainFormat5(String dt) throws ParseException {
		SimpleDateFormat formating = new SimpleDateFormat("dd:MM:yyyy:hh:mm:ss");
		String dateinString = dt;
		// System.out.println(dateString);
		Date date = formating.parse(dateinString);

		// System.out.println(date);
		// System.out.println(formating.format(date));

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = formatter.format(date);
		// System.out.println("Date Format with dd-MM-yyyy : "+strDate);
		return strDate;
	}
	
	// Convert MM-dd-yyyy type of Date input to a MM/dd/yyyy date format
	public String convert_StringDate_to_ActualDate_inCertainFormat6(String dt) throws ParseException {
		SimpleDateFormat formating = new SimpleDateFormat("MM-dd-yyyy");
		String dateinString = dt;
		// System.out.println(dateString);
		Date date = formating.parse(dateinString);

		// System.out.println(date);
		// System.out.println(formating.format(date));

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);
		// System.out.println("Date Format with MM/dd/yyyy : "+strDate);
		return strDate;
	}

	// Convert MM/dd/yyyy type of Date input to a dd-MMM-yyyy date format
	public String convert_StringDate_to_ActualDate_inCertainFormat7(String dt) throws ParseException {
		SimpleDateFormat formating = new SimpleDateFormat("MM/dd/yyyy");
		String dateinString = dt;
		// System.out.println(dateString);
		Date date = formating.parse(dateinString);

		// System.out.println(date);
		// System.out.println(formating.format(date));

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String strDate = formatter.format(date);
		// System.out.println("Date Format with MM/dd/yyyy : "+strDate);
		return strDate;
	}


	// get_CurrentDate_inCertainFormat:Rqd Date format MM-dd-YYYY = 12-31-2019 or
	// MM/dd/YYYY = 12/31/2019
	public String get_CurrentDate_inCertainFormat(String dtFormat) throws ParseException {
		SimpleDateFormat formating = new SimpleDateFormat(dtFormat);
		Date date = new Date();

		String strDate = formating.format(date);
		// System.out.println(formating.format(date));
		return strDate;
	}

	// get_CurrentDate & Tomestamp_inCertainFormat:dd:MM:yyyy:HH:mm:ss
	public String get_CurrentDateandTimeStamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy:HH:mm:ss");
		// SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		// method 1
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// System.out.println(timestamp);
		/*
		 * // method 2 - via Date Date date = new Date(); System.out.println(new
		 * Timestamp(date.getTime())); // return number of milliseconds since January 1,
		 * 1970, 00:00:00 GMT System.out.println(timestamp.getTime());
		 */
		// format timestamp
		return sdf.format(timestamp);
	}

	// get_CurrentDate & Tomestamp_in any Format like:dd-MM-yyyy-HH-mm-ss
	public String get_CurrentDateandTimeStamp2(String DtTimeFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(DtTimeFormat);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// System.out.println(timestamp);
		return sdf.format(timestamp);
	}

	// Fetch back Date based on subtracting # of weeks
	public String getBackDate_weeks(int noOfWeeks) throws IOException {
		// create Calendar instance
		Calendar now = Calendar.getInstance();
		int Cyear = now.get(Calendar.YEAR);
		int Cday = now.get(Calendar.DAY_OF_MONTH);
		int Cmonth = now.get(Calendar.MONTH) + 1;
		String CrntDt = (Cmonth < 10 ? ("0" + Cmonth) : (Cmonth)) + "-" + (Cday < 10 ? ("0" + Cday) : (Cday)) + "-"
				+ Cyear;
		// System.out.println("Current date : " +CrntDt);

		// Subtract week from current date
		now = Calendar.getInstance();
		now.add(Calendar.WEEK_OF_YEAR, -noOfWeeks);
		int Byear = now.get(Calendar.YEAR);
		int Bday = now.get(Calendar.DAY_OF_MONTH);
		int Bmonth = now.get(Calendar.MONTH) + 1;
		String bckDt = (Bmonth < 10 ? ("0" + Bmonth) : (Bmonth)) + "-" + (Bday < 10 ? ("0" + Bday) : (Bday)) + "-"
				+ Byear;
		// System.out.println("date before " +noOfWeeks+ " week(s) : " +bckDt);
		return bckDt;
	}

	// Fetch future Date based on adding # of week(s)
	public String getFutureDate_weeks(int noOfWeeks) throws IOException {
		// create Calendar instance
		Calendar now = Calendar.getInstance();
		int Cyear = now.get(Calendar.YEAR);
		int Cday = now.get(Calendar.DAY_OF_MONTH);
		int Cmonth = now.get(Calendar.MONTH) + 1;
		String CrntDt = (Cmonth < 10 ? ("0" + Cmonth) : (Cmonth)) + "-" + (Cday < 10 ? ("0" + Cday) : (Cday)) + "-"
				+ Cyear;
		// System.out.println("Current date : " +CrntDt);

		// Add week from current date
		now = Calendar.getInstance();
		now.add(Calendar.WEEK_OF_YEAR, +noOfWeeks);
		int Byear = now.get(Calendar.YEAR);
		int Bday = now.get(Calendar.DAY_OF_MONTH);
		int Bmonth = now.get(Calendar.MONTH) + 1;
		String futrDt = (Bmonth < 10 ? ("0" + Bmonth) : (Bmonth)) + "-" + (Bday < 10 ? ("0" + Bday) : (Bday)) + "-"
				+ Byear;
		// System.out.println("date after " +noOfWeeks+ " week(s) : " +futrDt);
		return futrDt;
	}

	// Method to add any number to year, month, day, hour, minute and second to the
	// current date.
	// Ref: https://mkyong.com/java/java-how-to-add-days-to-current-date/
	public String add_to_Crrnt_DateandTimeStamp(int dt, int mnth, int Yr, int hr, int mnt, int sec) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy:HH:mm:ss");
		// SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		Date currentDate = new Date();
		// System.out.println(sdf.format(currentDate));

		// convert date to calendar
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);

		// manipulate date
		c.add(Calendar.YEAR, Yr);
		c.add(Calendar.MONTH, mnth);
		c.add(Calendar.DATE, dt); // same with c.add(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.HOUR, hr);
		c.add(Calendar.MINUTE, mnt);
		c.add(Calendar.SECOND, sec);

		// convert calendar to date
		Date currentDatePlusOne = c.getTime();

		return sdf.format(currentDatePlusOne);
	}

	// Method to change System(PC) Date to any date
	public void change_SystemDate(String Date) throws IOException, InterruptedException, ParseException {
		String command = "cmd /c start date " + Date;

		// Changing the System Date to Future Date
		// System.out.println(command);
		Runtime.getRuntime().exec(command);
		Thread.sleep(1000);
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
		// System.out.println("New Changed Future Date:
		// "+get_CurrentDate_inCertainFormat("dd-MM-yyyy"));
		Thread.sleep(5000);
	}

	// Capture Screenshot of a particular WebElement
	public void capture_element_screenshot(WebDriver driver, WebElement element, String DestinationFldrName,
			String screenshotName) throws IOException {
		// Take screen shot of whole page
		File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Calculate the width and height of the webElement
		Point p = element.getLocation();
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();

		// Create Rectangle of same width as of Web Element
		Rectangle rect = new Rectangle(width, height);

		BufferedImage img = null;
		img = ImageIO.read(screenShot);

		// Crop Image of web element from the screen shot
		BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);

		// write cropped image into File Object
		ImageIO.write(dest, "png", screenShot);

		// Copy Image into particular directory
		String destination = System.getProperty("user.dir") + "/src/test/resources/" + DestinationFldrName + "/"
				+ screenshotName + ".png";
		FileUtils.copyFile(screenShot, new File(destination));

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
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + "_"
				+ dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public static String getPassTCScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyy_MM_dd_hhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "PassTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/PassTCScreenshots/" + screenshotName + "_" + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public static String captureFailedTCScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyy_MM_dd_hhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("system.dir") + "/FailedTestsScreenshots/" + screenshotName + "_"
				+ dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	// Upload Documents method
	public void uploadDoc(String filename) throws AWTException, IOException, InterruptedException {

		// switch to the file upload window
		WebElement alert = driver.switchTo().activeElement();
		Thread.sleep(1000);

		// enter the filename
		String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\" + filename;
		// System.out.println(filepath);
		alert.sendKeys(filepath);
		Thread.sleep(500);

		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

		// switch back
		driver.switchTo().activeElement();
		Thread.sleep(500);
	}

	// Select Folder method
	public void selectFolder(String foldername) throws AWTException, IOException, InterruptedException {
		// switch to the file upload window
		WebElement alert = driver.switchTo().activeElement();
		Thread.sleep(1000);

		// enter the filename
		String folderpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\" + foldername;
		// System.out.println(foldername);
		alert.sendKeys(folderpath);
		Thread.sleep(500);

		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_ENTER);

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		// switch back
		driver.switchTo().activeElement();
		Thread.sleep(500);
	}

	// Method to retrieve all the file names present in any folder
	public List<String> get_fileNamesList(String folderpath) {
		List<String> results = new ArrayList<String>();

		// enter the folder path
		// String folderpath = System.getProperty("user.dir") +
		// \\src\\test\\resources\\TestData\\ + foldername;
		File[] files = new File(folderpath).listFiles();
		// If this pathname does not denote a directory, then listFiles() returns null.

		for (File file : files) {
			if (file.isFile()) {
				results.add(file.getName());
			}
		}
		return results;
	}

	// Clear/Delete ALL files from a directory/ folder
	public void DeleteFiles(String fldrPath) {
		File file = new File(fldrPath);
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isFile() && f.exists()) {
				f.delete();
				// System.out.println("successfully deleted");
			} else {
				System.out.println("cant delete a file due to open or error");
			}
		}
	}

	// Deleting a particular FILE method
	public void deleteFile(String filePath, String fileName) throws IOException {

		// File path
		String filepath = filePath;
		File file = new File(filepath + "/" + fileName);

		// System.out.println(file.exists());
		if (!file.exists()) {
			// file.createNewFile();
			System.out.println("Target File: " + fileName + " NOT present");
		} else {
			if (file.delete()) {
				System.out.println(fileName + " file deleted");
			}
		}
	}

	// Create a Folder/Directory in target location
	public void create_Folder(String fldrPath) {
		File file = new File(fldrPath);

		if (file.mkdir()) {
			// System.out.println("Folder is successfully created");
		} else {
			System.out.println("cant create a folder");
		}
	}

	// Close alert message if visible
	public void click_Close_alertmsg() throws InterruptedException {
		if (!IsElementVisibleStatus(driver.findElementByAccessibilityId("displayMessageTextBlock"))) {
			System.out.println("Buttom Appbar Alert message not displayed");
		} else {
			WebElement alertMsg_CloseBtn = driver.findElementByAccessibilityId("btnDelete");
			clickOn(alertMsg_CloseBtn);
		}
	}

	// Fetch the alert message data in the bottom app bar
	public String get_AlertMsg_text() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}

	// Fetch the popup message data by virtue of Name attribute
	public String get_popup_text() {
		WebElement LogMsg = driver.findElementByAccessibilityId("Content_String");
		return LogMsg.getAttribute("Name");
	}

	// Right click on the Asset Creation page to invoke the bottom apps bar
	public void Right_Click__Buttom_Menuoptions() {
		Actions ac = new Actions(driver);
		ac.contextClick().build().perform();
	}

	// Verify the presence of Home button in the bottom apps bar
	public boolean check_Home_Buttom_AppBar_Presence() {
		WebElement bottomMenu_Home_Icon = driver.findElementByAccessibilityId("HomeAppBarButton");
		return IsElementVisibleStatus(bottomMenu_Home_Icon);
	}

	// Verify the presence of Apps Help icon/button in the bottom apps bar
	public boolean check_Help_Buttom_AppBar_Presence() {
		WebElement bottomMenu_AppHelp_Icon = driver.findElementByAccessibilityId("HelpAppBarButton");
		return IsElementVisibleStatus(bottomMenu_AppHelp_Icon);
	}

	// Verify the presence of WndsHelp Help icon/button in the bottom apps bar
	public boolean check_WndsHelp_Buttom_AppBar_Presence() {
		WebElement bottomMenu_WndsHelp_Icon = driver.findElementByAccessibilityId("WindowsHelpAppBarButton");
		return IsElementVisibleStatus(bottomMenu_WndsHelp_Icon);
	}

	// Verify the presence of About window on clicking the ABout icon in the bottom
	// apps bar
	public boolean check_About_wndw_Presence() {
		WebElement About_Wndw = driver.findElementByName("About");
		return IsElementVisibleStatus(About_Wndw);
	}

	// Click on the Home icon of the bottom apps bar to move to Main Hub page with Alert message
	public MainHubPage Click_Home_Icon_AppBar() throws InterruptedException, IOException {
		Actions ac = new Actions(driver);
		ac.contextClick().build().perform();

		WebElement bottomMenu_Home_Icon = driver.findElementByAccessibilityId("HomeAppBarButton");
		clickOn(bottomMenu_Home_Icon);
		WebElement Yesbtn = driver.findElementByName("Yes");
		clickOn(Yesbtn);
		Thread.sleep(3000);
		return new MainHubPage();
	}
	
	// Click on the Home icon of the bottom apps bar to move to Main Hub page without Alert message
	public MainHubPage Click_Home_Icon_AppBar1() throws InterruptedException, IOException {
		Actions ac = new Actions(driver);
		ac.contextClick().build().perform();

		WebElement bottomMenu_Home_Icon = driver.findElementByAccessibilityId("HomeAppBarButton");
		clickOn(bottomMenu_Home_Icon);

		Thread.sleep(1000);
		return new MainHubPage();
	}


	// Click on the Help icon of the bottom apps bar to move to Main Hub page
	public void Click_Help_Icon_AppBar() throws InterruptedException {
		Actions ac = new Actions(driver);
		ac.contextClick().build().perform();

		WebElement bottomMenu_AppHelp_Icon = driver.findElementByAccessibilityId("HelpAppBarButton");
		clickOn(bottomMenu_AppHelp_Icon);
		Thread.sleep(500);
	}

	// Click on the WndsHelp icon of the bottom apps bar
	public void Click_WndsHelp_Icon_AppBar() throws InterruptedException {
		Actions ac = new Actions(driver);
		ac.contextClick().build().perform();

		WebElement bottomMenu_WndsHelp_Icon = driver.findElementByAccessibilityId("WindowsHelpAppBarButton");
		clickOn(bottomMenu_WndsHelp_Icon);
		Thread.sleep(500);
	}

	// Click on the About icon of the bottom apps bar to invoke the ABout window
	public void Click_About_Icon_AppBar() throws InterruptedException {
		Actions ac = new Actions(driver);
		ac.contextClick().build().perform();

		WebElement bottomMenu_About_Icon = driver.findElementByAccessibilityId("AboutAppBarButton");
		clickOn(bottomMenu_About_Icon);
		Thread.sleep(500);
	}

	// Get the Help context header text on clicking Help icon of the bottom apps bar
	public String get__HelpMenu_HdrText() {
		WebElement AsstCreation_HelpMenu = driver.findElementByAccessibilityId("helpHeader");
		return FetchText(AsstCreation_HelpMenu);
	}

	// program to find IP address of your computer
	public String system_IPadress() throws UnknownHostException {
		InetAddress localhost = InetAddress.getLocalHost();
		// System.out.println("System IP Address : " +
		// (localhost.getHostAddress()).trim());
		return localhost.getHostAddress().trim();
	}

	// program to find IP address from the About window of app
	public String consoleIP_InAboutWndw() {
		WebElement consoleIPText_InAboutWndw = driver.findElementByAccessibilityId("ConsoleIPTextBlock");
		String CnslIP = consoleIPText_InAboutWndw.getText().split(":")[1];

		return CnslIP.substring(1);
	}

	// program to Get the Sw version info from the About window on clicking About
	// icon of the bottom apps bar
	public String SWversion_InAboutWndw() {
		WebElement SWVersion_About_info = driver.findElementByAccessibilityId("SoftwareVersion");
		String SWVersionText = SWVersion_About_info.getText().split(":")[1];

		return SWVersionText;
	}

	// Click ALT+f4 button TO CLOSE ANY APP
	public void click_ALTf4_KeyStroke_ToCloseApp() throws InterruptedException, AWTException {
		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ALT);
		r.keyPress(KeyEvent.VK_F4);
		r.keyRelease(KeyEvent.VK_F4);
		r.keyRelease(KeyEvent.VK_ALT);
		r = null;

	}
	

	// Verify Application switch from PDF window to Application
	public void perform_alt_tab_OP() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_TAB);
		// robot.delay(100);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot = null;
	}
	
}
