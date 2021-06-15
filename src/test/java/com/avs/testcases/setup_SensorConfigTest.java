package com.avs.testcases;

import java.io.IOException;
import java.text.ParseException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.avs.base.BaseClass;
import com.avs.pages.LoginPage;
import com.avs.pages.MainHubPage;
import com.avs.pages.Setup_SensorConfigPage;
import com.avs.pages.Setup_defineSetupPage;
import com.avs.pages.UserManagementPage;
import com.avs.pages.assetCreationPage;
import com.avs.pages.assetDetailsPage;
import com.avs.pages.assetHubPage;
import com.avs.utility.TestUtilities;
import com.avs.utility.setupCreationUtility;

public class setup_SensorConfigTest extends BaseClass{
	
	// Refer TestUtilities Class for Data provider methods
		// Refer Test data folder>SetupTestData.xlsx sheet for test data i/p

		public ExtentReports extent;
		public ExtentTest extentTest;

		// Initialization of the Pages
		LoginPage LoginPage;
		MainHubPage MainHubPage;
		UserManagementPage UserManagementPage;
		assetHubPage assetHubPage;
		assetCreationPage assetCreationPage;
		assetDetailsPage assetDetailsPage;
		Setup_defineSetupPage defineSetupPage;
		Setup_SensorConfigPage SensorConfigPage;

		// Before All the tests are conducted
		@BeforeTest
		public void AssetCreationSetup() throws InterruptedException, IOException {		

			extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
			extent.addSystemInfo("VRT Version", "1.0.0.37");
			extent.addSystemInfo("BS Version", "0.6.13");
			extent.addSystemInfo("Lgr Version", "1.2.6");
			extent.addSystemInfo("User Name", "Manoj");
			extent.addSystemInfo("TestSuiteName", "AssetHubTest");

			// Rename the User file (NgvUsers.uxx) if exists
			renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
			// Rename the cache Asset file (Asset.txt) if exists
			renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");
			// Rename the Asset folder (Asset) if exists
			renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
			// Rename the cache Setup file (Asset.txt) if exists
			renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache\\ValProbeRT", "Setup.txt");
			// Rename the VRT Setups folder (Asset) if exists
			renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTSetups");

			LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
			Thread.sleep(1000);
			LoginPage = new LoginPage();

			// Method to Create Very 1st User with All privilege
			UserManagementPage=LoginPage.DefaultLogin();
			LoginPage = UserManagementPage.FirstUserCreation("User1", getUID("adminFull"), getPW("adminFull"),
					getPW("adminFull"), "FullAdmin", "12345678", "abc@gmail.com");
			MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
			UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
			UserManagementPage.clickAnyUserinUserList("User1");

			UserManagementPage.clickPrivRunQual();
			UserManagementPage.clickPrivCreateEditAsset();
			UserManagementPage.clickPrivCreateEditSetup();
			UserManagementPage.clickPrivRunCal();

			UserManagementPage.ClickNewUserSaveButton();
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			MainHubPage = UserManagementPage.ClickBackButn();

			// Method to Create 1st Asset 
			assetHubPage=MainHubPage.ClickAssetTile();
			assetCreationPage = assetHubPage.ClickAddAssetBtn();
			assetCreationPage.assetCreationWithAllFieldEntry("Asset01", "01", "HeatBath", "AAS", "Hyderabad", "VRT-RF", "2",
					"cu", "5", "Weeks", "1st Asset Creation");
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

			AppClose();
			Thread.sleep(1000);

		}

		// After All the tests are conducted
		@AfterTest
		public void endReport_releaseMomory() {
			extent.flush();
			extent.close();
			assetHubPage.resetWebElements();
			// System.out.println("Reset Webelement memory released");
		}

		// Before Method(Test) method
		@BeforeMethod(alwaysRun = true)
		public void Setup() throws InterruptedException {
			LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
			Thread.sleep(1000);
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
			assetHubPage = MainHubPage.ClickAssetTile();		
			assetDetailsPage = assetHubPage.click_assetTile("Asset01");
			defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();		
		}

		// TearDown of the App
		@AfterMethod(alwaysRun = true)
		public void Teardown(ITestResult result) throws IOException {
			if (result.getStatus() == ITestResult.FAILURE) {
				// to add name in extent report
				extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getName() + " #");
				// to add error/exception in extent report
				extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getThrowable() + " #");
				// to add screenshot in extent report
				String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
				extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1));
				// to add screencast/video in extent report
				// extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));

			} else if (result.getStatus() == ITestResult.SKIP) {
				extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName() + " #");
				// to add screenshot in extent report
				//String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver, result.getName());
				//extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2));

			}
			extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report

			driver.quit();
		}
		
		
		// Test Cases
		// SET002
		@Test(groups = {
				"Regression" }, description = "SET 002-UI_Verify if on Asset Details  page the _Setups_ tile is active")
		public void SET002() throws InterruptedException {
			extentTest = extent
					.startTest("SET 002-UI_Verify if on Asset Details  page the _Setups_ tile is active");
			SoftAssert sa = new SoftAssert();
			
			defineSetupPage.click_defineSetupPage_backBtn();
			assetDetailsPage=defineSetupPage.click_YesofAlert_msg();
			

			sa.assertEquals(assetDetailsPage.get_Setupheader_txt(), "Setups", "FAIL: SET 002-Setup tile is not Active under Asset details page");
			sa.assertAll();
		}

}
